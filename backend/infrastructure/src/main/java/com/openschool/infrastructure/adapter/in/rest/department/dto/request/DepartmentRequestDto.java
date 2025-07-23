package com.openschool.infrastructure.adapter.in.rest.department.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {
    private String departmentName;
    private String description;
    private String departmentHead;
    private String departmentDeputy;
    private String departmentCode;
    private String departmentEmail;
    private String departmentPhone;
}
