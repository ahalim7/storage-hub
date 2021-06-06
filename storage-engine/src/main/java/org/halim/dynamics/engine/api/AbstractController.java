package org.halim.dynamics.engine.api;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    protected ResponseEntity<?> handle(Object body) {
        return ResponseEntity.ok()
                .body(body);
    }

    protected ResponseEntity<Resource> handle(Resource resource) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    protected ResponseEntity<Resource> handle() {
        return ResponseEntity
                .notFound()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .build();
    }

}
