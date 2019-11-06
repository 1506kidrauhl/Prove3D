package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaCpuDAO;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaCpu;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    TabelaCpuDAO tabelaCpuDaAO;

    public synchronized TabelaCpu pegaCpu(TabelaCpu cpu, boolean enviarDados) {

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
        String temp = FormatUtil.formatElapsedSecs(os.getSystemUptime());
        String[] tempo = temp.split(", ");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        
      /*  try {
            String dataFormatada = tempo[1];
            cpu.setTempAtividade(formato.parse(dataFormatada));
      
            Date dtHora = new Date(formato.format(calendar.getTime()));
          
            cpu.setDtHora(dtHora);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Deu ruim");
        }*/

        //Utilização
        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
        Double cpuUtilizacao = hal.getProcessor().getSystemCpuLoadBetweenTicks(prevTicks);
        cpu.setUtilizacao(cpuUtilizacao);

        System.out.println("");
        if (enviarDados) {
//            cpu.setFkComputador(idPc);
            tabelaCpuDaAO.save(cpu);
        }
System.out.println("b");
        return cpu;
       
    }

}
