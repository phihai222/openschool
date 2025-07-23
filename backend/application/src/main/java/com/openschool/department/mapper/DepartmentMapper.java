package com.openschool.department.mapper;

import com.openschool.department.port.in.command.CreatedDepartmentCommand;
import com.openschool.department.port.in.command.UpdateDepartmentCommand;
import com.openschool.domain.department.Department;

public class DepartmentMapper {
    public static Department toDepartment(CreatedDepartmentCommand command){
        return Department.builder()
                .departmentName(command.getDepartmentName())
                .description(command.getDescription())
                .departmentHead(command.getDepartmentHead())
                .departmentDeputy(command.getDepartmentDeputy())
                .departmentCode(command.getDepartmentCode())
                .departmentEmail(command.getDepartmentEmail())
                .departmentPhone(command.getDepartmentPhone())
                .build();
    }

    public static Department toUpdateDepartment(UpdateDepartmentCommand command, Department department){
        if(command == null || department == null) {
            return null;
        }

        department.setDepartmentName(command.getDepartmentName() != null ? command.getDepartmentName() : department.getDepartmentName());
        department.setDescription(command.getDescription() != null ? command.getDescription() : department.getDescription());
        department.setDepartmentHead(command.getDepartmentHead() != null ? command.getDepartmentHead() : department.getDepartmentHead());
        department.setDepartmentDeputy(command.getDepartmentDeputy() != null ? command.getDepartmentDeputy() : department.getDepartmentDeputy());
        department.setDepartmentCode(command.getDepartmentCode() != null ? command.getDepartmentCode() : department.getDepartmentCode());
        department.setDepartmentEmail(command.getDepartmentEmail() != null ? command.getDepartmentEmail() : department.getDepartmentEmail());
        department.setDepartmentPhone(command.getDepartmentPhone() != null ? command.getDepartmentPhone() : department.getDepartmentPhone());

        return department;
    }
}
