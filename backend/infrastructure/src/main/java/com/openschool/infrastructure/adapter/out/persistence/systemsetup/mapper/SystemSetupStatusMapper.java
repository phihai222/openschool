package com.openschool.infrastructure.adapter.out.persistence.systemsetup.mapper;

import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.infrastructure.adapter.out.persistence.systemsetup.entity.SystemSetupStatusEntity;

import java.time.Instant;
import java.util.EnumMap;
import java.util.Map;

public class SystemSetupStatusMapper {
    public static SystemSetupStatus toDomain(SystemSetupStatusEntity entity) {
        Map<SetupStep, Boolean> steps = new EnumMap<>(SetupStep.class);
        steps.put(SetupStep.CREATE_ADMIN_USER, entity.isAdminCreated());
        steps.put(SetupStep.CREATE_SCHOOL, entity.isSchoolCreated());
        steps.put(SetupStep.CREATE_ACADEMIC_YEAR, entity.isYearCreated());
        steps.put(SetupStep.CREATE_GRADES, entity.isGradeCreated());

        return SystemSetupStatus.builder()
                .id(entity.getId())
                .schoolId(entity.getSchoolId())
                .currentStep(SetupStep.valueOf(entity.getCurrentStep()))
                .isCompleted(entity.isCompleted())
                .steps(steps)
                .build();
    }

    public static SystemSetupStatusEntity toEntity(SystemSetupStatus domain) {
        SystemSetupStatusEntity e = new SystemSetupStatusEntity();
        e.setId(domain.getId());
        e.setSchoolId(domain.getSchoolId());
        e.setCompleted(domain.isCompleted());
        e.setCurrentStep(domain.getCurrentStep().toString());
        e.setAdminCreated(domain.getSteps().getOrDefault(SetupStep.CREATE_ADMIN_USER, false));
        e.setSchoolCreated(domain.getSteps().getOrDefault(SetupStep.CREATE_SCHOOL, false));
        e.setYearCreated(domain.getSteps().getOrDefault(SetupStep.CREATE_ACADEMIC_YEAR, false));
        e.setGradeCreated(domain.getSteps().getOrDefault(SetupStep.CREATE_GRADES, false));
        e.setUpdatedAt(Instant.now());
        return e;
    }
}
