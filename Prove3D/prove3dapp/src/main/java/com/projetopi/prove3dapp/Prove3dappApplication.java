package com.projetopi.prove3dapp;

import com.profesorfalken.jsensors.JSensors;
import com.projetopi.prove3dapp.telas.TelaLogin;
import java.util.List;
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
        List<com.profesorfalken.jsensors.model.components.Cpu> components = JSensors.get.components().cpus;

        TelaLogin telaLogin = context.getBean(TelaLogin.class);
        telaLogin.setVisible(true);

    }

}
