package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaIdentityRepository extends JpaRepository<IdentityEntity, UUID> {
}
