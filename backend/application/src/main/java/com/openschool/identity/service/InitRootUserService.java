package com.openschool.identity.service;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.identity.exception.UserAlreadyExistsException;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.UserCredentialsRepositoryPort;
import com.openschool.identity.port.in.InitRootUserUseCase;

import java.util.UUID;

public class InitRootUserService implements InitRootUserUseCase {
    private final UserCredentialsRepositoryPort repository;
    private final PasswordEncoderPort passwordEncoder;

    public InitRootUserService(UserCredentialsRepositoryPort repository, PasswordEncoderPort passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initRoot(String username, String rawPassword) {
        // Check if the root user already exists
        if (repository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Hash the password using the provided password encoder
        String passwordHash = passwordEncoder.encode(rawPassword);

        // Create a new UserCredentials object and save it to the repository
        UserCredentials user = new UserCredentials(UUID.randomUUID().toString(), username, passwordHash);
        repository.save(user);
    }
}
