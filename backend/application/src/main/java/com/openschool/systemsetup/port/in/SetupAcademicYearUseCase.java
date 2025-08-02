package com.openschool.systemsetup.port.in;

import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.domain.systemsetup.SystemSetupStatus;

public interface SetupAcademicYearUseCase {
    SystemSetupStatus create(CreateAcademicYearCommand command);
}
