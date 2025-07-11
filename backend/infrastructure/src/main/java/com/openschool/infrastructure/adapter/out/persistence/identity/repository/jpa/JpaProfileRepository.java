package com.openschool.infrastructure.adapter.out.persistence.identity.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByIdentityId(UUID identityId);
}
