package com.openschool.systemsetup.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAdminCommand {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
