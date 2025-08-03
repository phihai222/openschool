package com.openschool.employee.port.in;

import com.openschool.domain.employee.Employee;

import java.util.UUID;

public interface GetDetailEmployeeUseCase {
    Employee getDetailEmployee(UUID employeeId);
}
