package com.projetopi.prove3dapp.dadosClasses;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaComputadorDAO;
import com.projetopi.prove3dapp.dao.TabelaCpuDAO;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaCpu;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

@Controller
public class Cpu {

    @Autowired
    Config config;

    @Autowired
    TabelaLogDAO tabelaLogDaAO;

    @Autowired
    TabelaComputadorDAO tabelaComputadorDAO;

    @Autowired
    TabelaCpuDAO tabelaCpuDaAO;

    @Autowired
    EnviarSlack enviarSlack;

    @Autowired
    ApplicationController applicationController;

    public TabelaCpu pegaCpu(TabelaCpu cpu, TabelaComputador fkPc) {

        SystemInfo si = config.oshi();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        //CPU (Processos, Temperatura, Voltagem, Velocidade, Threads, Tempo de atividade)
        //Quantidade em inteiros de Processos
        cpu.setProcessos(os.getProcessCount());

        //Modelo da cpu
        cpu.setModelo(String.valueOf(hal.getProcessor().getName()));

        //Verificando se o PC do usuário é Windows, caso seja, usaremos o JSensors para
        //capturar os dados da CPU        
        if (fkPc.getSistemaOperacional().split(" ")[0].equals("Windows")) {
            pegarCPUJsensor(cpu);
        } else {

            //Temperatura
            cpu.setTemperatura(hal.getSensors().getCpuTemperature());

            //Utilização
            long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
            Double cpuUtilizacao = hal.getProcessor().getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            cpu.setUtilizacao(cpuUtilizacao);
        }

        //Voltagem
        Double voltagem = hal.getSensors().getCpuVoltage();
        cpu.setVoltagem(voltagem);

        //Tempo de atividade cpu
        SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        try {

            String[] split = FormatUtil.formatElapsedSecs(os.getSystemUptime()).split(",");
            Date dtHora = new Date(calendar.getTime().getTime());

            cpu.setDtHora(dtHora);
            cpu.setTempAtividade(hora.parse(split[1]));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Deu ruim");
        }

        cpu.setFkComputador(fkPc);
        tabelaCpuDaAO.save(cpu);

        return cpu;

    }

    private void pegarCPUJsensor(TabelaCpu cpu) {

        List<com.profesorfalken.jsensors.model.components.Cpu> components = JSensors.get.components().cpus;

        if (components != null) {
            for (final com.profesorfalken.jsensors.model.components.Cpu cpuComp : components) {

                if (cpuComp.sensors != null) {
                    //Print temperatures
                    List<Temperature> temps = cpuComp.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        if (temp.name.equals("Temp CPU Package")) {
                            cpu.setTemperatura(temp.value);
                            break;
                        }
                    }

                    List<Load> loads = cpuComp.sensors.loads;
                    for (final Load load : loads) {

                        if (load.name.equals("Load CPU Total")) {
                            cpu.setUtilizacao(load.value);
                        }

                    }

                }
            }
        }

    }

    public void verificaDados(TabelaCpu cpu, JTextArea console, TabelaUsuario user, TabelaComputador pc) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        String mensagem = "";
        int aux = 0;

        if (cpu.getTemperatura() > 0) {

            //Verificando temperatua da CPU
            if (cpu.getTemperatura() > 40) {

                mensagem = String.format("Sua CPU está acima da temperatura recomendada "
                        + "(Temperatura: %.2fC, recomendado: 40C)\n", cpu.getTemperatura());
                //Printando log
                console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

                //Verificando temperatura maior ou igual a 50, para ser enviado o alerta ao slack
                if (cpu.getTemperatura() >= 50) {
                    mensagem = String.format("Sua CPU esta com uma temperatura "
                            + "muito alta (%.2fC)", cpu.getTemperatura());
                    aux++;
                    try {
                        //Função para enviar para slack
                        enviarSlack.enviarMsg(user.getNome(), mensagem);

                        //Populando tabela Log em caso de erro
                        applicationController.enviarLog(pc, user, "CPU", "Erro",
                                "Temperatura da CPU muito acima do recomendado");
                    } catch (IOException io) {
                        io.printStackTrace();
                        calendar.setTime(calendar.getTime());
                        console.setText(console.getText() + formato.format(calendar.getTime())
                                + " - Ocorreu um erro ao enviar o alerta de temperatura da CPU ao Slack \n");
                    }

                } else {
                    //Populando tabela Log em caso da temperatura estiver alta, mas 
                    //não acima de 50
                    applicationController.enviarLog(pc, user, "CPU", "Alerta",
                            "Temperatura da CPU está acima do recomendado e aumentando");
                }

            } else {
                //Caso esteja tudo OK com temperatura
                applicationController.enviarLog(pc, user, "CPU", "OK",
                        "Temperatura da CPU OK");
            }

            //Verificando Utilização da CPU
            if (cpu.getUtilizacao() > 50) {
                calendar.setTime(calendar.getTime());
                mensagem = String.format("Sua CPU está com uso excessivo (Uso: %.2f%%, recomendado: 50%%)\n",
                        cpu.getUtilizacao());
                //Printando Log
                console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

                //Verificando se utilização de CPU é maior que 66 para enviar um alerta
                if (cpu.getUtilizacao() > 66) {
                    aux++;
                    mensagem = String.format("A CPU está com a utilização muito acima do recomendado "
                            + "(Uso: %.2f%%, recomendado: 50%%)", cpu.getUtilizacao());

                    try {
                        //Enviando alerta ao slack
                        enviarSlack.enviarMsg(user.getNome(), mensagem);
                        //Populando tabela de log
                        applicationController.enviarLog(pc, user, "CPU", "Erro",
                                "Utilização da CPU muito alta, está causando mal desempenho no"
                                + " computador");
                    } catch (IOException io) {
                        io.printStackTrace();
                        calendar.setTime(calendar.getTime());
                        console.setText(console.getText() + formato.format(calendar.getTime())
                                + " - Ocorreu um erro ao enviar o alerta de uso CPU ao Slack \n");
                    }

                } else {
                    //Populando tabela de Log caso a utilização esteja alta, mas não
                    //acima de 66
                    applicationController.enviarLog(pc, user, "CPU", "Alerta",
                            "Utilização da CPU aumentando, isto pode causar mal desempenho no"
                            + " computador");
                }

            } else {
                //Caso a utilização da CPU esteja dentro do recomendado
                applicationController.enviarLog(pc, user, "CPU", "OK",
                        "Utilização da CPU OK");
            }

            if (aux == 1) {
                applicationController.enviarLog(pc, user, "CPU", "Alerta",
                        "CPU está operando normalmente, mas suas funções podem ser "
                        + "prejudicadas futuramente");
            } else if (aux == 2) {
                applicationController.enviarLog(pc, user, "CPU", "Erro",
                        "CPU possui problemas de funcionamento. Sua temperatura e utilização"
                        + " estão muito acima do recomendado para um bom funcionamento");
            } else {
                applicationController.enviarLog(pc, user, "CPU", "OK",
                        "Tudo OK com a CPU");
            }

        }
    }

}
