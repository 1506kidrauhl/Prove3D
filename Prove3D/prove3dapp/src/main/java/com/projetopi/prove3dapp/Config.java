package com.projetopi.prove3dapp;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
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
    
    /*
    @Bean
    @ConditionalOnProperty(name = "com.profesorfalken.jsensors.model.components.Components", havingValue = "oshiInfo", matchIfMissing = true)
    public Components jsensors(Map<String, String> config) {
        return JSensors.get.config(config).components();
    }
    
*/
}
