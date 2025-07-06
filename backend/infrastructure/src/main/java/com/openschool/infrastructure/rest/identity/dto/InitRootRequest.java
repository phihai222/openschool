package com.openschool.infrastructure.rest.identity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitRootRequest {
    private String username;
    private String password;
}
