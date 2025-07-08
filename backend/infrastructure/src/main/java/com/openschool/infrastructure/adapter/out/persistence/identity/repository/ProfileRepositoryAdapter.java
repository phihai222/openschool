package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.out.ProfileRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProfileRepositoryAdapter implements ProfileRepositoryPort {
    private final JpaProfileRepository jpaProfileRepository;

    private ProfileEntity toEntity(Profile profile) {
        return ProfileEntity.builder()
                .id(profile.getId())
                .identityId(profile.getIdentityId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .birthdate(profile.getBirthdate())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }

    private Profile toDomain(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .identityId(entity.getIdentityId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthdate(entity.getBirthdate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public Profile save(Profile profile) {
        ProfileEntity saved = jpaProfileRepository.save(toEntity(profile));
        return toDomain(saved);
    }

    @Override
    public Optional<Profile> findById(Object id) {
        if (!(id instanceof UUID uuid)) return Optional.empty();
        return jpaProfileRepository.findById(uuid).map(this::toDomain);
    }

    @Override
    public void deleteById(Object id) {
        if (id instanceof UUID uuid) {
            jpaProfileRepository.deleteById(uuid);
        }
    }

    @Override
    public Profile update(Profile profile) {
        ProfileEntity updated = jpaProfileRepository.save(toEntity(profile));
        return toDomain(updated);
    }

    @Override
    public Optional<Profile> findByIdentityId(UUID identityId) {
        return jpaProfileRepository.findByIdentityId(identityId)
                .map(this::toDomain);
    }
}

