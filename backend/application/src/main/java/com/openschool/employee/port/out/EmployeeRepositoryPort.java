package com.openschool.employee.port.out;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.employee.Employee;
import com.openschool.domain.employee.EmployeeType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepositoryPort {

    Optional<Employee> createEmployee(Employee command);

    Optional<Employee> updateEmployee(Employee command);

    Optional<Employee> getDetailEmployee(UUID employeeId);

    Optional<Employee> findByPhoneNumberOrEmail(String phoneNumber, String email);

    PageResult<Employee> getListEmployee(PageInfo pageInfo);

    boolean deleteEmployee(UUID employeeId);

    boolean assignEmployeeToDepartment(UUID departmentId, List<Employee> employees);

    PageResult<Employee> getListEmployeeInDepartment(PageInfo pageInfo, UUID departmentId);

    boolean moveEmployee(UUID departmentIdDes, Employee employee);

    boolean removeEmployee(Employee employee);

    boolean setEmployee(Employee employee, EmployeeType position);
}
