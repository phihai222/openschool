package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepositoryPort {
    Profile save(Profile profile);
    Optional<Profile> findById(Object id);
    void deleteById(Object id);
    Profile update(Profile profile);
    Optional<Profile> findByIdentityId(UUID identityId);
}
