package com.openschool.employee.port.in;

import com.openschool.domain.employee.EmployeeType;

import java.util.UUID;

public interface SetEmployeeTypeUseCase {
    void setEmployeeType(UUID departmentId, UUID employeeId, EmployeeType position);
}
