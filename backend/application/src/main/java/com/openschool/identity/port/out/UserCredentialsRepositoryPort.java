package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.UserCredentials;

import java.util.Optional;

public interface UserCredentialsRepositoryPort {
    Optional<UserCredentials> findByUsername(String username);
    void save(UserCredentials userCredentials);
}