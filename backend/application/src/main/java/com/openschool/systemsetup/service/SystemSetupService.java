package com.openschool.systemsetup.service;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class SystemSetupService implements GetSystemSetupStatusUseCase {
    private final UUID id = UUID.fromString("0c09669c-0e92-487b-8e33-df11ec6d8042");
    private final SystemSetupRepositoryPort systemSetupRepository;

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
}