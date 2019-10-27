package com.projetopi.prove3dapp;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.projetopi.prove3dapp.telas.TelaEstatisticas;
import com.projetopi.prove3dapp.telas.TelaLogin;
import com.projetopi.prove3dapp.telas.TelaPrincipal;
import com.projetopi.prove3dapp.telas.TelaProcessos;
import com.projetopi.prove3dapp.telas.TelaRelatórios;
import java.util.Map;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

@Configuration
public class Config {

    @Bean
    @ConditionalOnProperty(name = "oshi.SystemInfo", havingValue = "oshiInfo", matchIfMissing = true)
    public SystemInfo oshi() {
        return new SystemInfo();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaLogin telaLogin() {
        return new TelaLogin();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaPrincipal telaPrincipal() {
        return new TelaPrincipal();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaProcessos telaProcessos() {
        return new TelaProcessos();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaEstatisticas telaEstatiscas() {
        return new TelaEstatisticas();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaRelatórios telaRelatorios() {
        return new TelaRelatórios();
    }

    @Bean
    @ConditionalOnProperty(name = "com.projetopi.prove3dapp.telas", havingValue = "telas", matchIfMissing = true)
    public TelaPrincipal TelaPrincipal() {
        return new TelaPrincipal();
    }
    
    /*
    @Bean
    @ConditionalOnProperty(name = "com.profesorfalken.jsensors.model.components.Components", havingValue = "oshiInfo", matchIfMissing = true)
    public Components jsensors(Map<String, String> config) {
        return JSensors.get.config(config).components();
    }
    
     */
}
