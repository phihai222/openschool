package com.openschool.systemsetup.port.in;

import com.openschool.domain.systemsetup.SystemSetupStatus;

public interface UpdateSystemStatusUseCase {
    SystemSetupStatus updateSystemStatus(SystemSetupStatus status);
}
