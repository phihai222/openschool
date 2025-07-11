package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.Role;
import com.openschool.identity.port.out.RoleRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.RoleEntity;
import com.openschool.infrastructure.adapter.out.persistence.identity.repository.jpa.JpaRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {
    private final JpaRoleRepository jpaRoleRepository;

    private Role mapToDomainModel(RoleEntity roleEntity) {
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        var res = jpaRoleRepository.findByName(roleName);
        return res.map(this::mapToDomainModel);
    }
}
