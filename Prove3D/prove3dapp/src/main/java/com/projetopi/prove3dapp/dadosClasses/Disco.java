
package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaDiscoDAO;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaDisco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class Disco {

    @Autowired
    Config config;

    @Autowired
    TabelaLogDAO tabelaLogDaAO;

    @Autowired
    TabelaDiscoDAO tabelaDiscoDaAO;
    public synchronized TabelaDisco pegaDisco(TabelaDisco disco, TabelaComputador fkPc, List<String> data) {
        SystemInfo si = config.oshi();

        HardwareAbstractionLayer hal = si.getHardware();

        HWDiskStore[] memoriaDisco = hal.getDiskStores();

        for(HWDiskStore atual : memoriaDisco){
            long vleitura = atual.getReadBytes() / atual.getReads();
            long vGravacao = atual.getWriteBytes()/ atual.getWrites();

            data.add(FormatUtil.formatBytesDecimal(vleitura));
            data.add(FormatUtil.formatBytesDecimal(vGravacao));
            
            String splitL1 = FormatUtil.formatBytesDecimal(vleitura).split(" K")[0];
            
            String[] splitLF = splitL1.split(",");

            String splitG1 = FormatUtil.formatBytesDecimal(vGravacao).split(" K")[0];
            String[] splitGF = splitG1.split(",");

            //Velocidade de leitura disco
            disco.setvLeitura(Double.valueOf(splitLF[0] + "." + splitLF[1]));
            // Velocidade de gravação
            disco.setvGravacao(Double.valueOf(splitGF[0] + "." + splitGF[1]));

            //modelo do disco
            disco.setModelo(atual.getModel());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendar.getTime());

            try {
                Date dtHora = new Date(calendar.getTime().getTime());
                disco.setDtHora(dtHora);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Deu ruim");
            }

            disco.setTempAtividade(3);
            disco.setTempResp(3.6);

            disco.setFkComputador(fkPc);
            tabelaDiscoDaAO.save(disco);

        }

        return disco;
    }

}
