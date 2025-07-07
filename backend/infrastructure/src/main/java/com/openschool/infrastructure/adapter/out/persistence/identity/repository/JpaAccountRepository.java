package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByUsername(String username);
}
