package com.openschool.infrastructure.adapter.in.rest.systemsetup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
