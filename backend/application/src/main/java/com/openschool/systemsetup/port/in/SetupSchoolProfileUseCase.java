package com.openschool.systemsetup.port.in;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.school.port.in.command.CreateSchoolCommand;

public interface SetupSchoolProfileUseCase {
    SystemSetupStatus createSchoolProfile(CreateSchoolCommand command);
}
