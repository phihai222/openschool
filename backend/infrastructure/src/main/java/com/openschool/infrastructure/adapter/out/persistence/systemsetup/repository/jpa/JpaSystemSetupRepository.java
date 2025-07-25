package com.openschool.infrastructure.adapter.out.persistence.systemsetup.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.systemsetup.entity.SystemSetupStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSystemSetupRepository extends JpaRepository<SystemSetupStatusEntity, UUID> {
}
