package com.openschool.infrastructure.adapter.out.persistence.employee.repository.jpa;

import com.openschool.infrastructure.adapter.out.persistence.employee.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByPhoneNumberOrEmail(String phoneNumber, String email);

    Page<EmployeeEntity> findByDepartment(UUID departmentId, Pageable pageable);
}
