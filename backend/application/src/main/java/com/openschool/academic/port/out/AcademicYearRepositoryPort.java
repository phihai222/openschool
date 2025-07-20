package com.openschool.academic.port.out;

import com.openschool.domain.academic.AcademicYear;

public interface AcademicYearRepositoryPort {
    AcademicYear create(AcademicYear academicYear);
}
