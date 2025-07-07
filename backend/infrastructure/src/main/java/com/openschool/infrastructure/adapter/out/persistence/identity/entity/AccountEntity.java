package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import com.openschool.domain.identity.model.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
