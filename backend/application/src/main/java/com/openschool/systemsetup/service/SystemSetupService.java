package com.openschool.systemsetup.service;

import com.openschool.domain.systemsetup.SetupStep;
import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.systemsetup.exeption.ForbiddenSetup;
import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import com.openschool.systemsetup.port.in.SetupAdminUseCase;
import com.openschool.systemsetup.port.in.UpdateSystemStatusUseCase;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class SystemSetupService implements GetSystemSetupStatusUseCase, SetupAdminUseCase, UpdateSystemStatusUseCase {
    private final UUID id = UUID.fromString("0c09669c-0e92-487b-8e33-df11ec6d8042");
    private final SystemSetupRepositoryPort systemSetupRepository;
    private final InitRootUserUseCase initRootUserUseCase;

    @Override
    public SystemSetupStatus getSystemSetupStatus() {
        Optional<SystemSetupStatus> status = systemSetupRepository.getSystemSetupStatus(id);

        if(status.isEmpty()) {
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
}