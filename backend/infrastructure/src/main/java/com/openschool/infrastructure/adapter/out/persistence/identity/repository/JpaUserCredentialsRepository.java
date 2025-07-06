package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.UserCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserCredentialsRepository extends JpaRepository<UserCredentialsEntity, UUID> {
    Optional<UserCredentialsEntity> findByUsername(String username);
}
