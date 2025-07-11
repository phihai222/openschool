package com.openschool.identity.service;

import com.openschool.domain.identity.model.Account;
import com.openschool.domain.identity.model.AccountType;
import com.openschool.domain.identity.model.Identity;
import com.openschool.domain.identity.model.Role;
import com.openschool.identity.exception.UserAlreadyExistsException;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.identity.port.out.RoleRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class InitRootUserService implements InitRootUserUseCase {
    private final IdentityRepositoryPort identityRepository;
    private final AccountRepositoryPort accountRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RoleRepositoryPort roleRepository;

    @Transactional
    @Override
    public void initRoot(String username, String rawPassword) {
        // Check if the root user already exists
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Create a new Identity object
        Identity identity = Identity.builder().id(UUID.randomUUID()).build();
        identityRepository.save(identity);

        // Hash the password using the provided password encoder
        String passwordHash = passwordEncoder.encode(rawPassword);

        // Find Admin role from the repository
        Role adminRole = roleRepository.getRoleByName("ROOT")
                .orElseThrow(() -> new IllegalStateException("Admin role not found"));

        // Create a new UserCredentials object and save it to the repository
        Account user = Account.builder()
                .id(UUID.randomUUID())
                .identityId(identity.getId())
                .username(username)
                .passwordHash(passwordHash)
                .accountType(AccountType.LOCAL)
                .roles(Set.of(adminRole))
                .build();

        // Save the user to the account repository
        accountRepository.save(user);
    }
}
