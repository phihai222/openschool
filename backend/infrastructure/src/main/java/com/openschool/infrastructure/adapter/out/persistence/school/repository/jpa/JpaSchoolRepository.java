package com.openschool.infrastructure.adapter.out.persistence.school.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSchoolRepository extends JpaRepository<SchoolEntity, UUID> {
}
