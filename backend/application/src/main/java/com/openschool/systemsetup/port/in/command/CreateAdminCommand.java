package com.openschool.systemsetup.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateAdminCommand {
    private String username;
    private String password;
    private String fullName;
    private String email;
}
