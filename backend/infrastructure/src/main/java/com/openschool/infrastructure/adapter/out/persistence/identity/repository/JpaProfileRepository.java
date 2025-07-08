package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.infrastructure.adapter.out.persistence.identity.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaProfileRepository extends JpaRepository<ProfileEntity, UUID> {
}
