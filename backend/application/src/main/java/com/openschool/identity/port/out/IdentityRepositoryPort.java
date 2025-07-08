package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.Identity;

import java.util.Optional;
import java.util.UUID;

public interface IdentityRepositoryPort {
    void save(Identity identity);
    Optional<Identity> findById(UUID id);
}