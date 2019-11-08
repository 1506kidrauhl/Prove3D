package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.dao.TabelaProcessosDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaProcessos;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
            if(enviarDados && (tbProcessos.getUsoCpu() >= 20 || tbProcessos.getUsoMemoria() >= 20)){
                
                    tbProcessos.setFkComputadorP(fkPc);
                    tbProcessos.setFkUsuarioP(fkUser);
                    tabelaProcessosDAO.save(tbProcessos);
                
            }

        }

        return dadosProcessos;
        
    }

}
