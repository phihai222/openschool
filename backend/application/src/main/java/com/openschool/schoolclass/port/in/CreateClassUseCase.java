package com.openschool.schoolclass.port.in;

import com.openschool.domain.schoolclass.model.SchoolClass;
import com.openschool.schoolclass.port.in.command.CreateClassCommand;

public interface CreateClassUseCase {
    SchoolClass createClass(CreateClassCommand createClassCommand);
}
