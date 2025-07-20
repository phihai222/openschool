package com.openschool.school.port.out;

import com.openschool.domain.school.School;

import java.util.Optional;
import java.util.UUID;

public interface SchoolRepositoryPort {
    School create(School school);
    Optional<School> findById(UUID schoolId);
}
