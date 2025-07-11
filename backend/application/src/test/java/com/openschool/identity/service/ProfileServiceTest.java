package com.openschool.identity.service;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.exception.DataNotFound;
import com.openschool.identity.exception.InvalidCredentialsException;
import com.openschool.identity.port.in.command.UpdateProfileCommand;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import com.openschool.identity.port.out.ProfileRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProfileServiceTest {
    private ProfileRepositoryPort profileRepository;
    private IdentityRepositoryPort identityRepository;
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileRepository = mock(ProfileRepositoryPort.class);
        identityRepository = mock(IdentityRepositoryPort.class);
        profileService = new ProfileService(profileRepository, identityRepository);
    }

    @Test
    void createProfile_shouldThrowInvalidCredentialsException_whenIdentityNotFound() {
        UpdateProfileCommand command = mock(UpdateProfileCommand.class);
        when(command.getIdentityId()).thenReturn(UUID.randomUUID());
        when(identityRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> profileService.createProfile(command));
    }

    @Test
    void createProfile_shouldCreateNewProfile_whenNoCurrentProfileExists() {
        UUID identityId = UUID.randomUUID();
        UpdateProfileCommand command = mock(UpdateProfileCommand.class);
        when(command.getIdentityId()).thenReturn(identityId);
        var identity = mock(com.openschool.domain.identity.model.Identity.class);
        when(identityRepository.findById(identityId)).thenReturn(Optional.of(identity));
        when(profileRepository.findByIdentityId(identityId)).thenReturn(Optional.empty());
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .identityId(identityId)
                .birthdate(LocalDate.now())
                .firstName("John")
                .lastName("Doe")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile result = profileService.createProfile(command);
        assertNotNull(result);
        assertEquals(identityId, result.getIdentityId());
    }

    @Test
    void getCurrentUserProfile_shouldThrowDataNotFound_whenProfileDoesNotExist() {
        UUID identityId = UUID.randomUUID();
        when(profileRepository.findByIdentityId(identityId)).thenReturn(Optional.empty());
        assertThrows(DataNotFound.class, () -> profileService.getCurrentUserProfile(identityId));
    }

    @Test
    void getCurrentUserProfile_shouldReturnProfile_whenProfileExists() {
        UUID identityId = UUID.randomUUID();
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .identityId(identityId)
                .birthdate(LocalDate.now())
                .firstName("Jane")
                .lastName("Smith")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        when(profileRepository.findByIdentityId(identityId)).thenReturn(Optional.of(profile));
        Profile result = profileService.getCurrentUserProfile(identityId);
        assertNotNull(result);
        assertEquals(identityId, result.getIdentityId());
    }
}
