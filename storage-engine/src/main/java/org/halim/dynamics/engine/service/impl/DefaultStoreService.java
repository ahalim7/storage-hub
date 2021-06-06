package org.halim.dynamics.engine.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.halim.dynamics.engine.config.StoreConfiguration;
import org.halim.dynamics.engine.service.StoreService;
import org.halim.dynamics.engine.util.Defines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DefaultStoreService implements StoreService {

    @Autowired
    StoreConfiguration storeConfiguration;

    @Override
    public Map<String, String> storeResource(String service, String section, String parent,
                                             List<MultipartFile> multipartFiles) {

        Map<String, String> fileUri = new ConcurrentHashMap<>();

        multipartFiles.forEach(file -> {
            try {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                String expectedPath = File.separator + service + File.separator + section + File.separator + parent;
                Path filePath = Paths.get(storeConfiguration.getPath() + expectedPath).normalize();
                Files.createDirectories(filePath);
                Files.copy(file.getInputStream(), filePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

                fileUri.put(fileName, ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(Defines.REQUEST_MAPPING_STORE)
                        .path(expectedPath + File.separator + fileName).toUriString());

            } catch (IOException e) {
                log.error(e.getMessage(), e.getCause());
            }
        });

        return fileUri;
    }

    @Override
    public Resource grepResource(String service, String section, String parent, String file) {

        try {
            Resource resource = new UrlResource(Paths.get(storeConfiguration.getPath())
                    .resolve(service + File.separator + section + File.separator + parent + File.separator + file)
                    .normalize()
                    .toUri());

            if (resource.exists())
                return resource;

        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return null;
    }

}
