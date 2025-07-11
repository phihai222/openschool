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
    void shouldThrowExceptionIfRootUserExists() {
        // Simulate that the username already exists, so the first check triggers the exception
        when(accountRepository.findByUsername("root")).thenReturn(Optional.of(mock(Account.class)));
        // The following mocks are not needed for this scenario, but kept for completeness
        when(roleRepository.getRoleByName("ROOT")).thenReturn(Optional.of(mock(Role.class)));
        when(accountRepository.existsByRoleName("ROOT")).thenReturn(false);
        assertThrows(UserAlreadyExistsException.class, () -> service.initRoot("root", "password"));
    }

    @Test
    void shouldThrowExceptionIfRootRoleExistsButUsernameNotExists() {
        // Mock role with name "ROOT"
        Role mockRootRole = mock(Role.class);
        when(mockRootRole.getName()).thenReturn("ROOT");

        // No user with username "root"
        when(accountRepository.findByUsername("root")).thenReturn(Optional.empty());

        // Return mock role
        when(roleRepository.getRoleByName("ROOT")).thenReturn(Optional.of(mockRootRole));

        // Simulate that a ROOT role already exists
        when(accountRepository.existsByRoleName("ROOT")).thenReturn(true);

        // Should throw exception
        assertThrows(UserAlreadyExistsException.class, () -> service.initRoot("root", "password"));
    }

    @Test
    void shouldCreateRootUserIfNotExists() {
        // Simulate that neither the username nor the root role user exists
        when(accountRepository.findByUsername("root")).thenReturn(Optional.empty());
        Role adminRole = mock(Role.class);
        when(roleRepository.getRoleByName("ROOT")).thenReturn(Optional.of(adminRole));
        when(accountRepository.existsByRoleName("ROOT")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("hashed");
        doNothing().when(identityRepository).save(any(Identity.class));
        doNothing().when(accountRepository).save(any(Account.class));
        service.initRoot("root", "password");
        verify(accountRepository).save(any(Account.class));
    }
}
