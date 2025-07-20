package com.openschool.academic.port.in;

import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.domain.academic.AcademicYear;

public interface CreateAcademicYearUseCase {
    AcademicYear create(CreateAcademicYearCommand command);
}
