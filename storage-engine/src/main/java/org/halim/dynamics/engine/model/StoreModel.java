package org.halim.dynamics.engine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreModel implements Serializable {

    private String service;
    private String section;
    private String parent;
    private String file;

}
