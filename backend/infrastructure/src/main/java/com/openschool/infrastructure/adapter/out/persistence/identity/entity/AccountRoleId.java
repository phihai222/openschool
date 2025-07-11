package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class AccountRoleId implements Serializable {
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "role_id")
    private UUID roleId;
}