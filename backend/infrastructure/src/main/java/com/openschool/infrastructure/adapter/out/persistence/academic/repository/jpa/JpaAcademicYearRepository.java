package com.openschool.infrastructure.adapter.out.persistence.academic.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.academic.entity.AcademicYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAcademicYearRepository extends JpaRepository<AcademicYearEntity, UUID> {
}
