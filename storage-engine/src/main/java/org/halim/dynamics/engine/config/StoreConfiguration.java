package org.halim.dynamics.engine.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "default.media.store")
public class StoreConfiguration {

    private String path;

    @PostConstruct
    private void init() {
        File directories = new File(path);
        if (!directories.exists())
            directories.mkdirs();
    }

}
