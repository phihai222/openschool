package com.openschool.infrastructure.adapter.out.persistence.grade.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.grade.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaGradeRepository extends JpaRepository<GradeEntity, UUID> {
}
