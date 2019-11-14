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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public synchronized TabelaCpu pegaCpu(TabelaCpu cpu, TabelaComputador fkPc) {

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
                    System.out.println("Sensors: ");

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

}
