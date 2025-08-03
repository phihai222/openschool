package com.openschool.systemsetup.port.in;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.grade.port.in.command.CreateGradeCommand;

import java.util.List;

public interface SetupGradeUseCase {
    SystemSetupStatus createGrades(List<CreateGradeCommand> command);
}
