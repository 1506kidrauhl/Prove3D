package com.projetopi.prove3dapp;

import com.projetopi.prove3dapp.telas.TelaEstatisticas;
import com.projetopi.prove3dapp.telas.TelaLogin;
import com.projetopi.prove3dapp.telas.TelaPrincipal;
import com.projetopi.prove3dapp.telas.TelaProcessos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Prove3dappApplication {
    
    @Autowired
    Config config;

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(Prove3dappApplication.class)
                .headless(false).run(args);
        TelaLogin telaLogin = context.getBean(TelaLogin.class);
        telaLogin.setVisible(true);
        
    }

}
