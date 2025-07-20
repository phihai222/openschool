package com.openschool.school.port.in;

import com.openschool.domain.school.School;
import com.openschool.school.port.in.command.CreateSchoolCommand;

public interface CreateSchoolUseCase {
    School create(CreateSchoolCommand command);
}
