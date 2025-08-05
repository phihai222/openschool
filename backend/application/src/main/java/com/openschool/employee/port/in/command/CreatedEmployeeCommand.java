package com.openschool.employee.port.in.command;

import com.openschool.domain.employee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedEmployeeCommand {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private EmployeeType employeeType;

}
