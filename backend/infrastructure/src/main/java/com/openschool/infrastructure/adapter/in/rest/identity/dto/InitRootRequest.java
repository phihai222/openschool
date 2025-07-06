package com.openschool.infrastructure.adapter.in.rest.identity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitRootRequest {
    private String username;
    private String password;
}
