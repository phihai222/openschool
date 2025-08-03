package com.openschool.employee.port.in;

import com.openschool.domain.employee.Employee;

import java.util.List;

public interface GetListEmployeeUseCase {
    List<Employee> getListEmployee();
}
