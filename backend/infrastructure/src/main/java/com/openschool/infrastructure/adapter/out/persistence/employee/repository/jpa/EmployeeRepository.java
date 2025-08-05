package com.openschool.infrastructure.adapter.out.persistence.employee.repository.jpa;

import com.openschool.domain.employee.Employee;
import com.openschool.infrastructure.adapter.out.persistence.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<Employee> findByPhoneNumberOrEmail(String phoneNumber, String email);
}
