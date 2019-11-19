package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.dao.TabelaProcessosDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaProcessos;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

@Controller
public class Processos {

    @Autowired
    Config config;

    @Autowired
    TabelaLogDAO tabelaLogDAO;

    @Autowired
    TabelaProcessosDAO tabelaProcessosDAO;

    @Autowired
    EnviarSlack enviarSlack;

    public synchronized List<TabelaProcessos> pegaProcessos(List<TabelaProcessos> dadosProcessos, boolean enviarDados, TabelaComputador fkPc, TabelaUsuario fkUser, OperatingSystem.ProcessSort filtro) {

        //Instanciando classe SystemInfo para podermos pegarmos os dados de Hardware
        SystemInfo si = config.oshi();

        //Variável para coletar dados do Sistema
        OperatingSystem os = si.getOperatingSystem();
        //Variável para coletar dados do Hardware
        HardwareAbstractionLayer hal = si.getHardware();

        /*Criando array para pegar os processos do sistema, o 10 significa o número
        máximo de processos que irão ser coletados.
        Obs: Caso fosse 0, seria coletados os processos sendo executados pelo SO*/
        OSProcess[] processos = os.getProcesses(10, filtro);

        /*Este tipo de for é muito útil para o nosso caso, pois ele seta o array
        um por um por nós,*/
        for (OSProcess processoAtual : processos) {

            TabelaProcessos tbProcessos = new TabelaProcessos();

            // Armazenando todos os dados que serão armazenados na tabela
            int pid = processoAtual.getProcessID();
            String nmProcesso = processoAtual.getName();
            int prioridade = processoAtual.getPriority();

            //Calculo de uso de CPU por processo
            Double cpuPercent = 100d * (processoAtual.getKernelTime() + processoAtual.getUserTime()) / processoAtual.getUpTime();
            //Formatando resultado de Uso de CPU por processo
            String[] percentCpu = String.format("%.2f", cpuPercent).split(",");

            //Calculo de uso de Memória por processo
            Double memoryPercent = 100d * processoAtual.getResidentSetSize() / hal.getMemory().getTotal();
            //Formatando resultado de Uso de Memória por processo
            String[] percentMemory = String.format("%4.1f", memoryPercent).split(",");

            /*Formatando o tempo de atividade do processo, e cortando o retorno
            a partir da vírgula (O retorno seria uma String, ex: 
            17356 days, 13:09:09). O que estou fazendo nada mais é do que cortar 
            a String através da vírgula, o que nos dá um array com dois valores
            (0: 17356 days, 1: 13:09:09). Então quando for mostrar isto na tabela
            será tempo[1], pois quero que mostre o valor que ficará no indice 1*/
            String[] tempo = FormatUtil.formatElapsedSecs(processoAtual.getStartTime()).split(", ");

            tbProcessos.setPid(pid);
            tbProcessos.setProcesso(nmProcesso);
            tbProcessos.setPrioridade(prioridade);
            tbProcessos.setUsoCpu(Double.valueOf(percentCpu[0] + "." + percentCpu[1]));
            tbProcessos.setUsoMemoria(Double.valueOf(percentMemory[0] + "." + percentMemory[1]));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendar.getTime());
            SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");

            try {
                tbProcessos.setTempoAtividade(hora.parse(tempo[1]));
                Date dtHora = new Date(calendar.getTime().getTime());

                tbProcessos.setDataHora(dtHora);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Deu ruim");
            }

            dadosProcessos.add(tbProcessos);
            //ENVIAR DADOS PARA O BANCO APENAS DOS QUE SÃO DO TIPO PROCESSO DE USUÁRIO
            if (enviarDados && (tbProcessos.getUsoCpu() >= 20 || tbProcessos.getUsoMemoria() >= 20)) {

                tbProcessos.setFkComputadorP(fkPc);
                tbProcessos.setFkUsuarioP(fkUser);
                tabelaProcessosDAO.save(tbProcessos);

            }

        }

        return dadosProcessos;

    }

    public void verificaDados(List<TabelaProcessos> dadosProcessos, JTextArea console, TabelaUsuario user) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());

        SystemInfo si = config.oshi();
        OperatingSystem os = si.getOperatingSystem();

        String mensagem = "";

        //Printando na tela apenas se tiverem mais de 350 processos rodando no PC
        if (os.getProcessCount() > 350) {
            mensagem = " - Processos do computador: " + os.getProcessCount() + "\n";
            console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);
        }

        //Ordenando processos de acordo com a maior utilização de CPU
        dadosProcessos.sort(Comparator.comparing(TabelaProcessos::getUsoCpu).reversed());

        if (dadosProcessos.get(0).getUsoCpu() > 30) {

            //Printando no console
            mensagem = " - Processo com maior utilização de CPU: " + dadosProcessos.get(0).getProcesso() + "\n";
            console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

            //Enviando alerta no Slack caso a utilização da CPU seja maior qur 50%
            if (dadosProcessos.get(0).getUsoCpu() > 50) {
                //Preparando mensagem para enviar ao slack
                mensagem = String.format("O processo %s, esta com uma utilizacao de CPU "
                        + "muito alta (%.2f%%)", dadosProcessos.get(0).getProcesso(), dadosProcessos.get(0).getUsoCpu());

                try {
                    enviarSlack.enviarMsg(user.getNome(), mensagem);
                } catch (IOException io) {
                    io.printStackTrace();
                    calendar.setTime(calendar.getTime());
                    console.setText(console.getText() + formato.format(calendar.getTime())
                            + " - Ocorreu um erro ao enviar o alerta de uso CPU ao Slack \n");
                }
            }

        }

        //Ordenando processos de acordo com a maior utilização de Memória
        dadosProcessos.sort(Comparator.comparing(TabelaProcessos::getUsoMemoria).reversed());

        if (dadosProcessos.get(0).getUsoMemoria() > 45) {

            //Printando no Console
            mensagem = " - Processo com maior utilização de Memória: " + dadosProcessos.get(0).getProcesso() + "\n";
            console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

            //Enviando alerta no Slack caso a utilização da Memória seja maior qur 60%
            if (dadosProcessos.get(0).getUsoMemoria() > 60) {
                //Preparando mensagem para enviar ao slack
                mensagem = String.format("O processo %s, está com uma utilização de memória "
                        + "muito alta (%.2f%%)", dadosProcessos.get(0).getProcesso(), dadosProcessos.get(0).getUsoMemoria());

                try {
                    enviarSlack.enviarMsg(user.getNome(), mensagem);
                } catch (IOException io) {
                    io.printStackTrace();
                    calendar.setTime(calendar.getTime());
                    console.setText(console.getText() + formato.format(calendar.getTime())
                            + " - Ocorreu um erro ao enviar o alerta de uso memória ao Slack \n");
                }
            }

        }

        console.setText(console.getText() + formato.format(calendar.getTime()) + " - Finalizando monitoramento de Processos.\n");

    }

}
