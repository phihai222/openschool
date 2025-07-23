package com.openschool.infrastructure.adapter.in.rest.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {
    private UUID departmentId;
    private String departmentName;
    private String description;
    private String departmentHead;
    private String departmentDeputy;
    private String departmentCode;
    private String departmentEmail;
    private String departmentPhone;
}
