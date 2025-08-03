package com.openschool.employee.port.in;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.command.UpdatedEmployeeCommand;

public interface UpdateEmployeeUseCase {
    Employee updateEmployee(UpdatedEmployeeCommand command);
}
