package com.openschool.infrastructure.adapter.out.persistence.systemsetup.repository;

import com.openschool.domain.systemsetup.SystemSetupStatus;
import com.openschool.infrastructure.adapter.out.persistence.systemsetup.entity.SystemSetupStatusEntity;
import com.openschool.infrastructure.adapter.out.persistence.systemsetup.mapper.SystemSetupStatusMapper;
import com.openschool.infrastructure.adapter.out.persistence.systemsetup.repository.jpa.JpaSystemSetupRepository;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SystemSetupRepositoryAdapter implements SystemSetupRepositoryPort {
    private final JpaSystemSetupRepository jpaSystemSetupRepository;

    @Override
    public Optional<SystemSetupStatus> getSystemSetupStatus(UUID id) {
        return jpaSystemSetupRepository.findById(id)
                .map(SystemSetupStatusMapper::toDomain);
    }

    @Override
    public Optional<SystemSetupStatus> saveSystemStatus(SystemSetupStatus status) {
        SystemSetupStatusEntity entity = SystemSetupStatusMapper.toEntity(status);

        var result = jpaSystemSetupRepository.save(entity);
        return Optional.of(SystemSetupStatusMapper.toDomain(result));
    }
}
