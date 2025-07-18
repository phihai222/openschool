package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import com.openschool.domain.identity.model.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {
    @Id
    private UUID id;
    private UUID identityId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String username;
    private String passwordHash;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;
}
