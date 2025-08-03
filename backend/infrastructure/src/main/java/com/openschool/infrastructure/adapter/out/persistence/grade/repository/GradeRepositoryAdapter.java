package com.openschool.infrastructure.adapter.out.persistence.grade.repository;

import com.openschool.domain.grade.Grade;
import com.openschool.grade.port.out.GradeRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.grade.entity.GradeEntity;
import com.openschool.infrastructure.adapter.out.persistence.grade.repository.jpa.JpaGradeRepository;
import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class GradeRepositoryAdapter implements GradeRepositoryPort {
    private final JpaGradeRepository jpaGradeRepository;

    @Override
    public Grade createGrade(Grade grade) {
        var schoolEntity = SchoolEntity.referenceOnly(grade.getSchoolId());
        GradeEntity savedEntity = jpaGradeRepository.save(
                GradeEntity.fromDomain(grade, schoolEntity)
        );

        return savedEntity.toDomain();
    }
}
