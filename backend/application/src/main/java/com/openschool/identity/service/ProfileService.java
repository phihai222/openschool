package com.openschool.identity.service;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.exception.DataNotFound;
import com.openschool.identity.exception.InvalidCredentialsException;
import com.openschool.identity.port.in.GetCurrentUserProfileUseCase;
import com.openschool.identity.port.in.UpdateProfileUseCase;
import com.openschool.identity.port.in.command.UpdateProfileCommand;
import com.openschool.identity.port.out.IdentityRepositoryPort;
import com.openschool.identity.port.out.ProfileRepositoryPort;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class ProfileService implements UpdateProfileUseCase, GetCurrentUserProfileUseCase {
    private final ProfileRepositoryPort profileRepository;
    private final IdentityRepositoryPort identityRepository;

    @Override
    public Profile createProfile(UpdateProfileCommand command) {
        var identity = identityRepository.findById(command.getIdentityId());

        if (identity.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        var currentProfile = profileRepository.findByIdentityId(command.getIdentityId());

        Profile profile = Profile.builder()
                .id(currentProfile.isEmpty() ? UUID.randomUUID() : currentProfile.get().getId())
                .identityId(command.getIdentityId())
                .birthdate(command.getBirthdate())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .createdAt(currentProfile.isEmpty() ? Instant.now() : currentProfile.get().getCreatedAt())
                .updatedAt(Instant.now())
                .build();

        profile = profileRepository.save(profile);

        return profile;
    }

    @Override
    public Profile getCurrentUserProfile(UUID identityId) {
        var profile = profileRepository.findByIdentityId(identityId);
        if (profile.isEmpty()) {
            throw new DataNotFound("Profile not found");
        }

        return profile.get();
    }
}
