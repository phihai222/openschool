package com.openschool.infrastructure.adapter.out.persistence.department.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaDepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {

    Optional<DepartmentEntity> findByDepartmentName(String departmentName);
}
