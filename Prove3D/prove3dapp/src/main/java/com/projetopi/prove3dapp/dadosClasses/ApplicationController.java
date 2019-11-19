package com.projetopi.prove3dapp.dadosClasses;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaLog;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

    @Autowired
    Config config;

    @Autowired
    TabelaLogDAO tabelaLogDAO;

    TabelaUsuario idUser;

    public void enviarLog(TabelaComputador pc, TabelaUsuario user, String comp,
            String tipo, String desc) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());

        TabelaLog log = new TabelaLog();

        log.setComponente(comp);
        log.setTipo(tipo);
        log.setDescricao(desc);
        log.setDtHora(calendar.getTime());

        log.setFkComputador(pc);
        log.setFkUsuario(user);

        tabelaLogDAO.save(log);
        
    }

}
