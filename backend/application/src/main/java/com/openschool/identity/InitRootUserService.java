package com.openschool.identity;

import com.openschool.domain.identity.UserCredentials;
import com.openschool.domain.identity.UserCredentialsRepository;

import java.util.UUID;

public class InitRootUserService implements InitRootUserUseCase {
    private final UserCredentialsRepository repository;

    public InitRootUserService(UserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initRoot(String username, String rawPassword) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Root user already exists");
        }
        String passwordHash = hashPassword(rawPassword);
        UserCredentials user = new UserCredentials(UUID.randomUUID().toString(), username, passwordHash);
        repository.save(user);
    }

    private String hashPassword(String rawPassword) {
        // TODO: Implement BCrypt
        return rawPassword; // temporary placeholder
    }
}
