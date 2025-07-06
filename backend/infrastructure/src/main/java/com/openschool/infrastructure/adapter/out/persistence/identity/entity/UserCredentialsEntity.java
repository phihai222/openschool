package com.openschool.infrastructure.adapter.out.persistence.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user_credentials", schema = "identity")
public class UserCredentialsEntity {
    @Id
    private UUID id;

    private String username;
    private String passwordHash;
}