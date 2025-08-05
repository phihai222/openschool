package com.openschool.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    //    General info
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private EmployeeType employeeType;

    //TODO: personal info

}

