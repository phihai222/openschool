package com.openschool.infrastructure.adapter.in.rest.department.mapper;

import com.openschool.department.port.in.command.CreatedDepartmentCommand;
import com.openschool.department.port.in.command.UpdateDepartmentCommand;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.dto.request.DepartmentRequestDto;
import com.openschool.infrastructure.adapter.in.rest.department.dto.response.DepartmentResponseDto;
import com.openschool.infrastructure.adapter.out.persistence.department.entity.DepartmentEntity;

import java.time.Instant;
import java.util.UUID;

public class DepartmentMapper {

    public static DepartmentResponseDto toDepartmentResponseDto(Department department) {
        return DepartmentResponseDto.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .description(department.getDescription())
                .departmentHead(department.getDepartmentHead())
                .departmentDeputy(department.getDepartmentDeputy())
                .departmentCode(department.getDepartmentCode())
                .departmentEmail(department.getDepartmentEmail())
                .departmentPhone(department.getDepartmentPhone())
                .build();
    }

    public static CreatedDepartmentCommand dtoToCreatedDepartmentCommand(DepartmentRequestDto dto) {
        return CreatedDepartmentCommand.builder()
                .departmentName(dto.getDepartmentName())
                .description(dto.getDescription())
                .departmentHead(dto.getDepartmentHead())
                .departmentDeputy(dto.getDepartmentDeputy())
                .departmentCode(dto.getDepartmentCode())
                .departmentEmail(dto.getDepartmentEmail())
                .departmentPhone(dto.getDepartmentPhone())
                .build();
    }

    public static UpdateDepartmentCommand dtoToUpdatedDepartmentCommand(UUID id, DepartmentRequestDto dto) {
        return UpdateDepartmentCommand.builder()
                .departmentId(id)
                .departmentName(dto.getDepartmentName())
                .description(dto.getDescription())
                .departmentHead(dto.getDepartmentHead())
                .departmentDeputy(dto.getDepartmentDeputy())
                .departmentCode(dto.getDepartmentCode())
                .departmentEmail(dto.getDepartmentEmail())
                .departmentPhone(dto.getDepartmentPhone())
                .build();
    }

    public static DepartmentEntity toDepartmentEntity(Department department) {
        return DepartmentEntity.builder()
                .departmentId(department.getDepartmentId() != null ? department.getDepartmentId() : UUID.randomUUID())
                .departmentName(department.getDepartmentName())
                .description(department.getDescription())
                .departmentHead(department.getDepartmentHead())
                .departmentDeputy(department.getDepartmentDeputy())
                .departmentCode(department.getDepartmentCode())
                .departmentEmail(department.getDepartmentEmail())
                .departmentPhone(department.getDepartmentPhone())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static Department toDepartment(DepartmentEntity entity) {
        if (entity == null) {
            return null;
        }
        return Department.builder()
                .departmentId(entity.getDepartmentId())
                .departmentName(entity.getDepartmentName())
                .description(entity.getDescription())
                .departmentHead(entity.getDepartmentHead())
                .departmentDeputy(entity.getDepartmentDeputy())
                .departmentCode(entity.getDepartmentCode())
                .departmentEmail(entity.getDepartmentEmail())
                .departmentPhone(entity.getDepartmentPhone())
                .build();
    }

    public static DepartmentEntity updateDepartmentEntity(DepartmentEntity entity, Department department) {
        if (entity == null || department == null) {
            return null;
        }
        entity.setDepartmentName(department.getDepartmentName() != null ? department.getDepartmentName() : entity.getDepartmentName());
        entity.setDescription(department.getDescription() != null ? department.getDescription() : entity.getDescription());
        entity.setDepartmentHead(department.getDepartmentHead() != null ? department.getDepartmentHead() : entity.getDepartmentHead());
        entity.setDepartmentDeputy(department.getDepartmentDeputy() != null ? department.getDepartmentDeputy() : entity.getDepartmentDeputy());
        entity.setDepartmentCode(department.getDepartmentCode() != null ? department.getDepartmentCode() : entity.getDepartmentCode());
        entity.setDepartmentEmail(department.getDepartmentEmail() != null ? department.getDepartmentEmail() : entity.getDepartmentEmail());
        entity.setDepartmentPhone(department.getDepartmentPhone() != null ? department.getDepartmentPhone() : entity.getDepartmentPhone());
        return entity;
    }


}
