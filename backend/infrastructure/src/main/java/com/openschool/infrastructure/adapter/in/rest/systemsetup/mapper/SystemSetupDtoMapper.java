package com.openschool.infrastructure.adapter.in.rest.systemsetup.mapper;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.infrastructure.adapter.in.rest.systemsetup.dto.SystemSetupStatusResponse;

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
}
