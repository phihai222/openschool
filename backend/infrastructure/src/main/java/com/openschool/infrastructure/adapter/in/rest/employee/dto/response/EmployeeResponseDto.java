package com.openschool.infrastructure.adapter.in.rest.employee.dto.response;

import com.openschool.domain.employee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String position;
    private EmployeeType employeeType;
}
