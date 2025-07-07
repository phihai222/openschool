package com.openschool.identity.service;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.identity.exception.RootUserAlreadyExistsException;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.UserCredentialsRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InitRootUserServiceTest {
    private UserCredentialsRepositoryPort repository;
    private PasswordEncoderPort passwordEncoder;
    private InitRootUserService service;

    @BeforeEach
    void setUp() {
        repository = mock(UserCredentialsRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoderPort.class);
        service = new InitRootUserService(repository, passwordEncoder);
    }

    @Test
    void initRoot_createsUser_whenUserDoesNotExist() {
        String username = "root";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        service.initRoot(username, rawPassword);

        verify(repository).save(any(UserCredentials.class));
    }

    @Test
    void initRoot_throwsException_whenUserExists() {
        String username = "root";
        when(repository.findByUsername(username)).thenReturn(Optional.of(mock(UserCredentials.class)));

        assertThrows(RootUserAlreadyExistsException.class, () -> service.initRoot(username, "password"));
        verify(repository, never()).save(any());
    }
}

