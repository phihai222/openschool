package com.openschool.domain.identity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.Permission;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    private UUID id;
    private String name;
    private Set<Permission> permissions;
}
