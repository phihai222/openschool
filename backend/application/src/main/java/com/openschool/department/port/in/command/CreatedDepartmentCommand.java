package com.openschool.department.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedDepartmentCommand {

    private String departmentName;
    private String description;
    private String departmentHead;
    private String departmentDeputy;
    private String departmentCode;
    private String departmentEmail;
    private String departmentPhone;
}
