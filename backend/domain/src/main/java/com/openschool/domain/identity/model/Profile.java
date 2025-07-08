package com.openschool.domain.identity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Profile {
    private UUID id;
    private UUID identityId;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Instant createdAt;
    private Instant updatedAt;
}

