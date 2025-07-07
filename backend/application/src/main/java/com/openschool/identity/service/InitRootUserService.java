package com.openschool.identity.service;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.identity.exception.RootUserAlreadyExistsException;
import com.openschool.identity.port.out.UserCredentialsRepositoryPort;
import com.openschool.identity.port.in.InitRootUserUseCase;

import java.util.UUID;

public class InitRootUserService implements InitRootUserUseCase {
    private final UserCredentialsRepositoryPort repository;

    public InitRootUserService(UserCredentialsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void initRoot(String username, String rawPassword) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RootUserAlreadyExistsException();
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
