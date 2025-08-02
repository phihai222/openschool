package com.openschool.infrastructure.adapter.out.persistence.school.repository;

import com.openschool.domain.school.School;
import com.openschool.infrastructure.adapter.out.persistence.school.entity.SchoolEntity;
import com.openschool.infrastructure.adapter.out.persistence.school.repository.jpa.JpaSchoolRepository;
import com.openschool.school.port.out.SchoolRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SchoolRepositoryAdapter implements SchoolRepositoryPort {
    private final JpaSchoolRepository jpaSchoolRepository;
    @Override
    public School create(School school) {
        // Convert School to SchoolEntity
        SchoolEntity schoolEntity = SchoolEntity.builder()
                .id(school.getId())
                .name(school.getName())
                .type(school.getType())
                .address(school.getAddress())
                .phoneNumber(school.getPhoneNumber())
                .email(school.getEmail())
                .website(school.getWebsite())
                .defaultLanguage(school.getDefaultLanguage())
                .timezone(school.getTimezone())
                .build();

        // Save the entity using jpaSchoolRepository
        SchoolEntity savedEntity = jpaSchoolRepository.save(schoolEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<School> findById(UUID schoolId) {
        return jpaSchoolRepository.findById(schoolId)
                .map(SchoolEntity::toDomain);
    }
}
