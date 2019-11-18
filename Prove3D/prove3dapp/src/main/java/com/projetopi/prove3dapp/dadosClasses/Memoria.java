
package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.dao.TabelaMemoriaDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaDisco;
import com.projetopi.prove3dapp.tabelas.TabelaMemoria;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.FormatUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextArea;

@Controller
public class Memoria {
    
    
    @Autowired
    Config config;

    @Autowired
    TabelaMemoriaDAO tabelaMemoriaDAO;

    @Autowired
    TabelaLogDAO tabelaLogDAO;


    public TabelaMemoria pegaMemoria(TabelaMemoria memoria, TabelaComputador fkPC, List<String> data) {

        SystemInfo si = config.oshi();
        HardwareAbstractionLayer hal = si.getHardware();

        // DISPONÍVEL
        Long disponivel = hal.getMemory().getAvailable();
        data.add(FormatUtil.formatBytesDecimal(disponivel));
        String splitD = FormatUtil.formatBytesDecimal(disponivel).split(" ")[0];
        String[] disp =  splitD.split(",");

        memoria.setDisponivel(Double.valueOf(disp[0] + "." + disp[1]));

        // CACHE
        Long cache = hal.getMemory().getAvailable();
        data.add(FormatUtil.formatBytesDecimal(cache));
        String splitC = FormatUtil.formatBytesDecimal(cache).split(" ")[0];
        String[] cach =  splitC.split(",");

        memoria.setCache(Double.valueOf(cach[0] + "." + cach[1]));

        // USO
        Long uso = hal.getMemory().getTotal() - disponivel;
        data.add(FormatUtil.formatBytesDecimal(uso));
        String splitU = FormatUtil.formatBytesDecimal(uso).split(" ")[0];
        String[] us =  splitU.split(",");

        memoria.setEmUso(Double.valueOf(us[0] + "." + us[1]));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());

        try {
            Date dtHora = new Date(calendar.getTime().getTime());
            memoria.setDtHora(dtHora);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Deu ruim");
        }

        memoria.setFkComputadorM(fkPC);
        tabelaMemoriaDAO.save(memoria);


        return memoria;
    }

  public void verificaDados(TabelaMemoria dadosMemoria, JTextArea console){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        
        SystemInfo si = config.oshi();
        HardwareAbstractionLayer hal = si.getHardware();
        
        console.setText(console.getText() + formato.format(calendar.getTime()) + " - Finalizando monitoramento de Memória.\n");
  }

}
