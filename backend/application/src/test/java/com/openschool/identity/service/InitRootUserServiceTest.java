package com.openschool.identity.service;

import com.openschool.domain.identity.model.Account;
import com.openschool.domain.identity.model.Identity;
import com.openschool.identity.exception.UserAlreadyExistsException;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InitRootUserServiceTest {
    private AccountRepositoryPort accountRepository;
    private IdentityRepositoryPort identityRepository;
    private PasswordEncoderPort passwordEncoder;
    private InitRootUserService service;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepositoryPort.class);
        identityRepository = mock(IdentityRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoderPort.class);
        service = new InitRootUserService(identityRepository, accountRepository, passwordEncoder);
    }

    @Test
    void shouldThrowExceptionIfUserExists() {
        when(accountRepository.findByUsername("root")).thenReturn(Optional.of(mock(Account.class)));
        assertThrows(UserAlreadyExistsException.class, () -> service.initRoot("root", "password"));
    }

    @Test
    void shouldCreateRootUserIfNotExists() {
        when(accountRepository.findByUsername("root")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashed");
        doNothing().when(identityRepository).save(any(Identity.class));

        service.initRoot("root", "password");

        verify(identityRepository).save(any(Identity.class));
        verify(accountRepository).save(any(Account.class));
    }
}
