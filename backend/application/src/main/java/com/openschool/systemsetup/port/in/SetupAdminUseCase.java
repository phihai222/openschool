package com.openschool.systemsetup.port.in;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.systemsetup.port.in.command.CreateAdminCommand;

public interface SetupAdminUseCase {
    SystemSetupStatus createAdminUser(CreateAdminCommand command);
}
