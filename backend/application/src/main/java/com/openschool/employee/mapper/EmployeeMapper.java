package com.openschool.employee.mapper;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.command.CreatedEmployeeCommand;
import com.openschool.employee.port.in.command.UpdatedEmployeeCommand;

public class EmployeeMapper {
    public static Employee toEmployee(CreatedEmployeeCommand command) {
        if (command == null) {
            return null;
        }
        return Employee.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .phoneNumber(command.getPhoneNumber())
                .department(command.getDepartment())
                .position(command.getPosition())
                .employeeType(command.getEmployeeType())
                .build();
    }

    public static Employee toEmployee(UpdatedEmployeeCommand command, Employee employee) {
        if (command == null || command.getEmployeeId() == null) {
            return null;
        }
        return Employee.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(command.getFirstName() != null ? command.getFirstName() : employee.getFirstName())
                .lastName(command.getLastName() != null ? command.getLastName() : employee.getLastName())
                .email(command.getEmail() != null ? command.getEmail() : employee.getEmail())
                .phoneNumber(command.getPhoneNumber() != null ? command.getPhoneNumber() : employee.getPhoneNumber())
                .department(command.getDepartment() != null ? command.getDepartment() : employee.getDepartment())
                .position(command.getPosition() != null ? command.getPosition() : employee.getPosition())
                .employeeType(command.getEmployeeType() != null ? command.getEmployeeType() : employee.getEmployeeType())
                .build();
    }
}
