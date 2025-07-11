package com.openschool.identity.service;

import com.openschool.domain.identity.model.Account;
import com.openschool.domain.identity.model.Identity;
import com.openschool.domain.identity.model.Role;
import com.openschool.identity.exception.UserAlreadyExistsException;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import com.openschool.identity.port.out.RoleRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InitRootUserServiceTest {
    private AccountRepositoryPort accountRepository;
    private IdentityRepositoryPort identityRepository;
    private PasswordEncoderPort passwordEncoder;
    private RoleRepositoryPort roleRepository;
    private InitRootUserService service;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepositoryPort.class);
        identityRepository = mock(IdentityRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoderPort.class);
        roleRepository = mock(RoleRepositoryPort.class);
        service = new InitRootUserService(identityRepository, accountRepository, passwordEncoder, roleRepository);
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
        // Mock role repository to return an Admin role
        Role adminRole = mock(Role.class);
        when(roleRepository.getRoleByName("ROOT")).thenReturn(Optional.of(adminRole));

        service.initRoot("root", "password");

        verify(identityRepository).save(any(Identity.class));
        verify(accountRepository).save(any(Account.class));
        verify(roleRepository).getRoleByName("ROOT");
    }
}
