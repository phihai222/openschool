package com.openschool.identity.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateProfileCommand {
    private UUID identityId;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
}

