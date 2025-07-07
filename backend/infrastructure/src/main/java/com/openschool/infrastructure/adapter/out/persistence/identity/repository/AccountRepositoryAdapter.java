package com.openschool.infrastructure.adapter.out.persistence.identity.repository;

import com.openschool.domain.identity.model.Account;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.infrastructure.adapter.out.persistence.identity.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final JpaAccountRepository jpaAccountRepository;

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
                .build());
    }
}
