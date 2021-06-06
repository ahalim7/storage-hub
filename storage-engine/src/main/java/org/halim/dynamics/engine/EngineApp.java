package org.halim.dynamics.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EngineApp {

    public static void main(String[] args) {
        SpringApplication.run(EngineApp.class, args);
    }

}
