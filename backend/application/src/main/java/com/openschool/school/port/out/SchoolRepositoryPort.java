package com.openschool.school.port.out;

import com.openschool.domain.school.School;

public interface SchoolRepositoryPort {
    School create(School school);
}
