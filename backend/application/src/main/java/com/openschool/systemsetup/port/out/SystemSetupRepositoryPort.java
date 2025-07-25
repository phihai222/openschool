package com.openschool.systemsetup.port.out;

import com.openschool.domain.systemsetup.SystemSetupStatus;

import java.util.Optional;
import java.util.UUID;

public interface SystemSetupRepositoryPort {
    Optional<SystemSetupStatus> getSystemSetupStatus(UUID id);
    Optional<SystemSetupStatus> saveSystemStatus(SystemSetupStatus status);
}
