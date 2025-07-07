package com.openschool.domain.identity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Account {
    private UUID id;
    private UUID identityId;
    private AccountType accountType;
    private String email;
    private String username;
    private String passwordHash;
}
