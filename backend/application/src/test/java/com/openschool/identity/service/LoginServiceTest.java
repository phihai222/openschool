package com.openschool.identity.service;

import com.openschool.domain.identity.model.Account;
import com.openschool.identity.exception.InvalidCredentialsException;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.TokenGeneratorPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {
    private AccountRepositoryPort accountRepositoryPort;
    private PasswordEncoderPort passwordEncoderPort;
    private TokenGeneratorPort tokenGeneratorPort;
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        accountRepositoryPort = mock(AccountRepositoryPort.class);
        passwordEncoderPort = mock(PasswordEncoderPort.class);
        tokenGeneratorPort = mock(TokenGeneratorPort.class);
        loginService = new LoginService(accountRepositoryPort, passwordEncoderPort, tokenGeneratorPort);
    }

    @Test
    void login_successful() {
        Account account = mock(Account.class);
        when(accountRepositoryPort.findByUsername("user")).thenReturn(Optional.of(account));
        when(passwordEncoderPort.matches("pass", account.getPasswordHash())).thenReturn(true);
        when(tokenGeneratorPort.generateToken(account)).thenReturn("token123");

        String token = loginService.login("user", "pass");
        assertEquals("token123", token);
    }

    @Test
    void login_invalidUsername_throwsException() {
        when(accountRepositoryPort.findByUsername("user")).thenReturn(Optional.empty());
        assertThrows(InvalidCredentialsException.class, () -> loginService.login("user", "pass"));
    }

    @Test
    void login_invalidPassword_throwsException() {
        Account account = mock(Account.class);
        when(accountRepositoryPort.findByUsername("user")).thenReturn(Optional.of(account));
        when(passwordEncoderPort.matches("wrongpass", account.getPasswordHash())).thenReturn(false);
        assertThrows(InvalidCredentialsException.class, () -> loginService.login("user", "wrongpass"));
    }
}

