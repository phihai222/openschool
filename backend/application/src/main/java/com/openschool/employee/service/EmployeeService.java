package com.openschool.employee.service;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.exception.EmployeeException;
import com.openschool.employee.port.in.*;
import com.openschool.employee.port.in.command.CreatedEmployeeCommand;
import com.openschool.employee.port.in.command.UpdatedEmployeeCommand;
import com.openschool.employee.port.out.EmployeeRepositoryPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.openschool.employee.exception.ExceptionMessage.*;
import static com.openschool.employee.mapper.EmployeeMapper.toEmployee;

@RequiredArgsConstructor
@Data
public class EmployeeService implements CreateEmployeeUseCase, UpdateEmployeeUseCase, GetDetailEmployeeUseCase, DeleteEmployeeUseCase, GetListEmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public Employee createEmployee(CreatedEmployeeCommand command) {
        Optional<Employee> currentEmployee = employeeRepositoryPort.findByPhoneNumberOrEmail(command.getPhoneNumber(), command.getEmail());
        if (currentEmployee.isPresent()) {
            throw new EmployeeException(EMPLOYEE_ALREADY_EXISTS);
        }

        return employeeRepositoryPort.createEmployee(toEmployee(command))
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_CREATION_FAIL));
    }

    @Override
    public Employee updateEmployee(UpdatedEmployeeCommand command) {
        Employee currentEmployee = employeeRepositoryPort.getDetailEmployee(command.getEmployeeId())
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND));
        return employeeRepositoryPort.updateEmployee(toEmployee(command, currentEmployee))
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_UPDATE_FAIL));
    }

    @Override
    public void deleteEmployee(UUID employeeId) {
        Employee currentEmployee = employeeRepositoryPort.getDetailEmployee(employeeId)
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND));
        boolean result = employeeRepositoryPort.deleteEmployee(currentEmployee.getEmployeeId());
        if (!result) {
            throw new EmployeeException(EMPLOYEE_DELETION_FAIL);
        }
    }

    @Override
    public Employee getDetailEmployee(UUID employeeId) {
        return employeeRepositoryPort.getDetailEmployee(employeeId)
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND));
    }

    @Override
    public List<Employee> getListEmployee() {
        return employeeRepositoryPort.getListEmployee();
    }

}
