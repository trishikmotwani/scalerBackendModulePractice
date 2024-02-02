package com.scaler.spring.javabasics.scopeAndReferences;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {

    BaseEntity(Integer id) {
        this.id = id;
    }
    private Integer id;
}
