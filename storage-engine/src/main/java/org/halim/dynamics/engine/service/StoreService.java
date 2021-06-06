package org.halim.dynamics.engine.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StoreService {

    Map<String, String> storeResource(String service, String section, String parent, List<MultipartFile> multipartFiles);

    Resource grepResource(String service, String section, String parent, String file);

}
