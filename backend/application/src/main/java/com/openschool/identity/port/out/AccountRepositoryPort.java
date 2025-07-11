package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.Account;

import java.util.Optional;

public interface AccountRepositoryPort {
    Optional<Account> findByUsername(String username);
    void save(Account account);
    boolean existsByRoleName(String roleName);
}
