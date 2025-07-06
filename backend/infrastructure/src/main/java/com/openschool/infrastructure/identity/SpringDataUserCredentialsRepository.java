package com.openschool.infrastructure.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataUserCredentialsRepository extends JpaRepository<UserCredentialsEntity, UUID> {
    Optional<UserCredentialsEntity> findByUsername(String username);
}
