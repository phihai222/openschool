package com.openschool.infrastructure.adapter.out.persistence.academic.repository;

import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.domain.academic.AcademicYear;
import com.openschool.infrastructure.adapter.out.persistence.academic.entity.AcademicYearEntity;
import com.openschool.infrastructure.adapter.out.persistence.academic.repository.jpa.JpaAcademicYearRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AcademicYearRepositoryAdapter implements AcademicYearRepositoryPort {
    private final JpaAcademicYearRepository jpaAcademicYearRepository;

    @Override
    public AcademicYear create(AcademicYear academicYear) {
        // Convert the domain object to an entity
        AcademicYearEntity entity = AcademicYearEntity.fromDomain(academicYear);
        AcademicYearEntity savedEntity = jpaAcademicYearRepository.save(entity);
        return savedEntity.toDomain();
    }
}
