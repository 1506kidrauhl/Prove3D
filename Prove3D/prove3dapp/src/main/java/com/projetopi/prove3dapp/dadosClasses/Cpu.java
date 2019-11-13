package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaComputadorDAO;
import com.projetopi.prove3dapp.dao.TabelaCpuDAO;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaCpu;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public TabelaCpu pegaCpu(TabelaCpu cpu, TabelaComputador fkPc) {

        SystemInfo si = config.oshi();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        //CPU (Processos, Temperatura, Voltagem, Velocidade, Threads, Tempo de atividade)
        //Quantidade em inteiros de Processos
        cpu.setProcessos(os.getProcessCount());

        //Modelo da cpu
        cpu.setModelo(String.valueOf(hal.getProcessor().getName()));

        //Temperatura
        cpu.setTemperatura(hal.getSensors().getCpuTemperature());

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

        //Utilização
        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
        Double cpuUtilizacao = hal.getProcessor().getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        cpu.setUtilizacao(cpuUtilizacao);

        cpu.setFkComputador(fkPc);
        tabelaCpuDaAO.save(cpu);

        return cpu;
       
    }
    
    public void verificaDados(TabelaCpu cpu, JTextArea console){
        
        
        
    }
    

}
