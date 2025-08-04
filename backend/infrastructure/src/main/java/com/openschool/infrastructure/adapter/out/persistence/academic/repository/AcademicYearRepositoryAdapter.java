package com.openschool.infrastructure.adapter.out.persistence.academic.repository;

import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.domain.academic.AcademicYear;
import com.openschool.infrastructure.adapter.out.persistence.academic.entity.AcademicYearEntity;
import com.openschool.infrastructure.adapter.out.persistence.academic.repository.jpa.JpaAcademicYearRepository;
import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AcademicYearRepositoryAdapter implements AcademicYearRepositoryPort {
    private final JpaAcademicYearRepository jpaAcademicYearRepository;

    @Override
    public AcademicYear create(AcademicYear academicYear) {
        // Convert the domain object to an entity
        SchoolEntity school = SchoolEntity.referenceOnly(academicYear.getSchoolId());
        AcademicYearEntity entity = AcademicYearEntity.fromDomain(academicYear, school);
        AcademicYearEntity savedEntity = jpaAcademicYearRepository.save(entity);
        return savedEntity.toDomain();
    }
}
