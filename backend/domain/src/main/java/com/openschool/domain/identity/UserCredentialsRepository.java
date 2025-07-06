package com.openschool.domain.identity;

import java.util.Optional;

public interface UserCredentialsRepository {
    Optional<UserCredentials> findByUsername(String username);
    void save(UserCredentials userCredentials);
}