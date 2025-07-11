package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_role")
public class AccountRoleEntity {
    @EmbeddedId
    private AccountRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}