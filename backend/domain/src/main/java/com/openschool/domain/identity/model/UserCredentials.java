package com.openschool.domain.identity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCredentials {
    private String id;
    private String username;
    private String passwordHash;

    public UserCredentials(String id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}