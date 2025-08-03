package com.openschool.employee.port.in;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.command.CreatedEmployeeCommand;

public interface CreateEmployeeUseCase {
    Employee createEmployee(CreatedEmployeeCommand command);
}
