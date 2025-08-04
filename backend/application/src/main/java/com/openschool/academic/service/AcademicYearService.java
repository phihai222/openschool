package com.openschool.academic.service;

import com.openschool.academic.port.in.CreateAcademicYearUseCase;
import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.domain.academic.AcademicYear;
import com.openschool.domain.academic.Semester;
import com.openschool.domain.school.School;
import com.openschool.common.exception.DataNotFound;
import com.openschool.school.port.out.SchoolRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class AcademicYearService implements CreateAcademicYearUseCase {
    private final AcademicYearRepositoryPort academicYearRepository;
    private final SchoolRepositoryPort schoolRepository;

    @Override
    public AcademicYear create(CreateAcademicYearCommand command) {
        // Validate the school exists
        Optional<School> school = schoolRepository.findById(command.getSchoolId());

        if(school.isEmpty()) {
            throw new DataNotFound("School with ID " + command.getSchoolId() + " does not exist.");
        }

        AcademicYear newAcademicYear = AcademicYear.builder()
                .id(UUID.randomUUID())
                .schoolId(command.getSchoolId())
                .code(command.getCode())
                .name(command.getName())
                .startDate(command.getStartDate())
                .endDate(command.getEndDate())
                .status(command.getStatus())
                .semesters(command.getSemesters().stream()
                        .map(semester -> Semester.builder()
                                .id(UUID.randomUUID())
                                .name(semester.getName())
                                .startDate(semester.getStartDate())
                                .endDate(semester.getEndDate())
                                .build())
                        .toList())
                .build();

        return academicYearRepository.create(newAcademicYear);
    }
}
