package org.halim.dynamics.engine.api;

import org.halim.dynamics.engine.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("managed-store")
public class StoreController extends AbstractController {

    @Autowired
    StoreService storeService;

    @PostMapping("/resource")
    public ResponseEntity<?> upload(@RequestParam("service") String service,
                                    @RequestParam("section") String section,
                                    @RequestParam("parent") String parent,
                                    @RequestParam("file") List<MultipartFile> multipartFiles) {
        return handle(ResponseEntity.ok(storeService.storeResource(service, section, parent, multipartFiles)));
    }

    @GetMapping("/resource/{service}/{section}/{parent}/{file}")
    public ResponseEntity<Resource> download(@PathVariable("service") String service,
                                             @PathVariable("section") String section,
                                             @PathVariable("parent") String parent,
                                             @PathVariable("file") String file) {

        Resource resource = storeService.grepResource(service, section, parent, file);

        if (resource == null)
            return handle();

        return handle(resource);
    }

}
