package com.openschool.infrastructure.adapter.in.rest.employee.mapper;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.in.command.CreatedEmployeeCommand;
import com.openschool.employee.port.in.command.UpdatedEmployeeCommand;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.request.EmployeeRequestDto;
import com.openschool.infrastructure.adapter.in.rest.employee.dto.response.EmployeeResponseDto;
import com.openschool.infrastructure.adapter.out.persistence.employee.entity.EmployeeEntity;

import java.time.Instant;
import java.util.UUID;

public class EmployeeMapper {

    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        return EmployeeResponseDto.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .department(employee.getDepartment())
                .position(employee.getPosition())
                .employeeType(employee.getEmployeeType())
                .build();
    }

    public static CreatedEmployeeCommand dtoToCreatedEmployeeCommand(EmployeeRequestDto dto) {
        return CreatedEmployeeCommand.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .position(dto.getPosition())
                .employeeType(dto.getEmployeeType())
                .build();
    }

    public static UpdatedEmployeeCommand dtoToUpdatedEmployeeCommand(UUID id, EmployeeRequestDto dto) {
        return UpdatedEmployeeCommand.builder()
                .employeeId(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .position(dto.getPosition())
                .employeeType(dto.getEmployeeType())
                .build();
    }

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        if (employee == null) {
            return null;
        }

        return EmployeeEntity.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .department(employee.getDepartment())
                .position(employee.getPosition())
                .employeeType(employee.getEmployeeType())
                .build();
    }

    public static Employee toEmployee(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return Employee.builder()
                .employeeId(entity.getEmployeeId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .department(entity.getDepartment())
                .position(entity.getPosition())
                .employeeType(entity.getEmployeeType())
                .build();
    }

    public static EmployeeEntity updateEmployeeEntity(EmployeeEntity entity, Employee employee) {
        if (entity == null || employee == null) {
            return null;
        }
        entity.setFirstName(employee.getFirstName() != null ? employee.getFirstName() : entity.getFirstName());
        entity.setLastName(employee.getLastName() != null ? employee.getLastName() : entity.getLastName());
        entity.setEmail(employee.getEmail() != null ? employee.getEmail() : entity.getEmail());
        entity.setPhoneNumber(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : entity.getPhoneNumber());
        entity.setDepartment(employee.getDepartment() != null ? employee.getDepartment() : entity.getDepartment());
        entity.setPosition(employee.getPosition() != null ? employee.getPosition() : entity.getPosition());
        entity.setEmployeeType(employee.getEmployeeType() != null ? employee.getEmployeeType() : entity.getEmployeeType());
        entity.setUpdatedAt(Instant.now());
        return entity;
    }


}
