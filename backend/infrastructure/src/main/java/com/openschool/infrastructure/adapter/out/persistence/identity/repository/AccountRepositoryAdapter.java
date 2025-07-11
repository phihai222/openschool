package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.Account;
import com.openschool.domain.identity.model.Role;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.AccountEntity;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.RoleEntity;
import com.openschool.infrastructure.adapter.out.persistence.identity.repository.jpa.JpaAccountRepository;
import com.openschool.infrastructure.adapter.out.persistence.identity.repository.jpa.JpaAccountRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final JpaAccountRepository jpaAccountRepository;
    private final JpaAccountRoleRepository jpaAccountRoleRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return jpaAccountRepository.findByUsername(username)
                .map(entity -> Account.builder()
                        .id(entity.getId())
                        .identityId(entity.getIdentityId())
                        .username(entity.getUsername())
                        .passwordHash(entity.getPasswordHash())
                        .accountType(entity.getAccountType())
                        .email(entity.getEmail())
                        .roles(entity.getRoles().stream()
                                .map(roleEntity -> Role.builder()
                                        .name(roleEntity.getName())
                                        .id(roleEntity.getId())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build());
    }

    @Override
    public void save(Account account) {
        jpaAccountRepository.save(AccountEntity.builder()
                .id(account.getId())
                .identityId(account.getIdentityId())
                .username(account.getUsername())
                .passwordHash(account.getPasswordHash())
                .accountType(account.getAccountType())
                .email(account.getEmail())
                .roles(account.getRoles().stream()
                        .map(role -> RoleEntity.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build());
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return jpaAccountRoleRepository.existsByRoleName(roleName);
    }
}
