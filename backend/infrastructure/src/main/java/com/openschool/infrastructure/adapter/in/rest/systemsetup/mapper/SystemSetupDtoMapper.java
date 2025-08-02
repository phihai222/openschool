package com.openschool.infrastructure.adapter.in.rest.systemsetup.mapper;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.CreateAdminRequest;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.CreateSchoolRequest;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.SystemSetupStatusResponse;
import com.openschool.school.port.in.command.CreateSchoolCommand;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;

public class SystemSetupDtoMapper {
    public static SystemSetupStatusResponse toResponse(SystemSetupStatus domain) {
        SystemSetupStatusResponse.Steps stepsDto = new SystemSetupStatusResponse.Steps(
            domain.getSteps().getOrDefault(SetupStep.CREATE_ADMIN_USER, false),
            domain.getSteps().getOrDefault(SetupStep.CREATE_SCHOOL, false),
            domain.getSteps().getOrDefault(SetupStep.CREATE_ACADEMIC_YEAR, false),
            domain.getSteps().getOrDefault(SetupStep.CREATE_GRADES, false)
        );
        return new SystemSetupStatusResponse(
            domain.getCurrentStep().name(),
            domain.isCompleted(),
            stepsDto
        );
    }

    public static CreateAdminCommand toCommand(CreateAdminRequest request) {
        return CreateAdminCommand.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();
    }

    public static CreateSchoolCommand toCommand(CreateSchoolRequest request) {
        return CreateSchoolCommand.builder()
                .name(request.getName())
                .type(request.getType())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .website(request.getWebsite())
                .defaultLanguage(request.getDefaultLanguage())
                .timezone(request.getTimezone())
                .build();
    }
}
