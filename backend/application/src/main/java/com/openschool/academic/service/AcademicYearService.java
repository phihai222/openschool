package com.openschool.academic.service;

import com.openschool.academic.port.in.CreateAcademicYearUseCase;
import com.openschool.academic.port.in.command.CreateAcademicYearCommand;
import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.domain.academic.AcademicYear;
import com.openschool.domain.academic.Semester;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class AcademicYearService implements CreateAcademicYearUseCase {
    private final AcademicYearRepositoryPort academicYearRepository;

    @Override
    public AcademicYear create(CreateAcademicYearCommand command) {
        AcademicYear newAcademicYear = AcademicYear.builder()
                .id(UUID.randomUUID())
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
