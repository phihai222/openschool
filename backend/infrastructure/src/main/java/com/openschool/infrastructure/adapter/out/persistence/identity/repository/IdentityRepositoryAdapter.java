package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.Identity;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.IdentityEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class IdentityRepositoryAdapter implements IdentityRepositoryPort {
    private final JpaIdentityRepository jpaIdentityRepository;

    @Override
    public void save(Identity identity) {
        jpaIdentityRepository.save(
                IdentityEntity.builder()
                        .id(identity.getId())
                        .build()
        );
    }
}
