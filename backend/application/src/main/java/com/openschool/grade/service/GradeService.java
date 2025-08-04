package com.openschool.grade.service;

import com.openschool.domain.grade.Grade;
import com.openschool.domain.school.School;
import com.openschool.grade.port.in.CreateGradeUseCase;
import com.openschool.grade.port.in.command.CreateGradeCommand;
import com.openschool.grade.port.out.GradeRepositoryPort;
import com.openschool.common.exception.DataNotFound;
import com.openschool.school.port.out.SchoolRepositoryPort;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class GradeService implements CreateGradeUseCase {
    private final SchoolRepositoryPort schoolRepository;
    private final GradeRepositoryPort gradeRepository;

    @Override
    public Grade createGrade(CreateGradeCommand command, UUID schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);

        if(school.isEmpty()) {
            throw new DataNotFound("School not found with ID: " + schoolId);
        }

        Grade grade = Grade.builder()
                .schoolId(schoolId)
                .name(command.getName())
                .code(command.getCode())
                .level(command.getLevel())
                .minAge(command.getMinAge())
                .maxAge(command.getMaxAge())
                .displayOrder(command.getDisplayOrder())
                .allowClass(command.isAllowClass())
                .status(command.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return gradeRepository.createGrade(grade);
    }
}
