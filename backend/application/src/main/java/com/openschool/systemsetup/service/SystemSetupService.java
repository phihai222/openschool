package com.openschool.systemsetup.service;

import com.openschool.academic.port.in.CreateAcademicYearUseCase;
import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.domain.school.School;
import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.grade.port.in.CreateGradeUseCase;
import com.openschool.grade.port.in.command.CreateGradeCommand;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.school.port.in.CreateSchoolUseCase;
import com.openschool.school.port.in.command.CreateSchoolCommand;
import com.openschool.common.exception.ForbiddenSetup;
import com.openschool.systemsetup.port.in.*;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class SystemSetupService implements
        GetSystemSetupStatusUseCase,
        SetupAdminUseCase,
        UpdateSystemStatusUseCase,
        SetupSchoolProfileUseCase,
        SetupAcademicYearUseCase,
        SetupGradeUseCase {
    private final UUID id = UUID.fromString("0c09669c-0e92-487b-8e33-df11ec6d8042");
    private final SystemSetupRepositoryPort systemSetupRepository;
    private final InitRootUserUseCase initRootUserUseCase;
    private final CreateSchoolUseCase createSchoolUseCase;
    private final CreateAcademicYearUseCase createAcademicYearUseCase;
    private final CreateGradeUseCase createGradeUseCase;

    @Override
    public SystemSetupStatus getSystemSetupStatus() {
        Optional<SystemSetupStatus> status = systemSetupRepository.getSystemSetupStatus(id);

        if (status.isEmpty()) {
            SystemSetupStatus initialStatus = new SystemSetupStatus();
            initialStatus.setId(id);
            return systemSetupRepository.saveSystemStatus(initialStatus)
                    .orElseThrow(() -> new RuntimeException("Failed to initialize system setup status"));
        }

        return status.get();
    }

    @Override
    @Transactional
    public SystemSetupStatus createAdminUser(CreateAdminCommand command) {
        // Get Current System Setup Status
        SystemSetupStatus currentStatus = getSystemSetupStatus();

        // Check if the current step is CREATE_ADMIN_USER
        // If not, throw an exception or handle accordingly
        if (currentStatus.getCurrentStep() != SetupStep.CREATE_ADMIN_USER) {
            throw new ForbiddenSetup("Cannot create admin user at this step: " + currentStatus.getCurrentStep());
        }
        // Create the admin user using the provided command
        initRootUserUseCase.initRoot(command.getUsername(), command.getPassword());

        // Mark the CREATE_ADMIN_USER step as completed
        currentStatus.markStepCompleted(SetupStep.CREATE_ADMIN_USER);
        return this.updateSystemStatus(currentStatus);
    }

    @Override
    public SystemSetupStatus updateSystemStatus(SystemSetupStatus status) {
        return systemSetupRepository.saveSystemStatus(status)
                .orElseThrow(() -> new RuntimeException("Failed to update system setup status"));
    }

    @Override
    public SystemSetupStatus createSchoolProfile(CreateSchoolCommand command) {
        SystemSetupStatus currentStatus = getSystemSetupStatus();

        if (currentStatus.getCurrentStep() != SetupStep.CREATE_SCHOOL) {
            throw new ForbiddenSetup("Cannot create school profile at this step: " + currentStatus.getCurrentStep());
        }

        School newSchool = createSchoolUseCase.create(command);

        // Update the current status with the new school ID
        currentStatus.setSchoolId(newSchool.getId());
        currentStatus.markStepCompleted(SetupStep.CREATE_SCHOOL);
        return this.updateSystemStatus(currentStatus);
    }


    @Override
    public SystemSetupStatus create(CreateAcademicYearCommand command) {
        SystemSetupStatus currentStatus = getSystemSetupStatus();

        if (currentStatus.getCurrentStep() != SetupStep.CREATE_ACADEMIC_YEAR) {
            throw new ForbiddenSetup("Cannot create academic year at this step: " + currentStatus.getCurrentStep());
        }

        createAcademicYearUseCase.create(command);
        currentStatus.markStepCompleted(SetupStep.CREATE_ACADEMIC_YEAR);

        return this.updateSystemStatus(currentStatus);
    }

    @Override
    public SystemSetupStatus createGrades(List<CreateGradeCommand> command) {
        SystemSetupStatus currentStatus = getSystemSetupStatus();
        if (currentStatus.getCurrentStep() != SetupStep.CREATE_GRADES) {
            throw new ForbiddenSetup("Cannot create grades at this step: " + currentStatus.getCurrentStep());
        }

        UUID schoolId = command.getFirst().getSchoolId();

        command.forEach(gradeCommand -> createGradeUseCase.createGrade(gradeCommand, schoolId));

        currentStatus.markStepCompleted(SetupStep.CREATE_GRADES);
        return this.updateSystemStatus(currentStatus);
    }
}