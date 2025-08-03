package com.openschool.employee.port.out;

import com.openschool.domain.employee.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepositoryPort {

    Optional<Employee> createEmployee(Employee command);

    Optional<Employee> updateEmployee(Employee command);

    Optional<Employee> getDetailEmployee(UUID employeeId);

    Optional<Employee> findByPhoneNumberOrEmail(String phoneNumber, String email);

    List<Employee> getListEmployee();

    boolean deleteEmployee(UUID employeeId);
}
