package com.openschool.identity.service;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.in.CreateProfileUseCase;
import com.openschool.identity.port.in.command.CreateProfileCommand;
import com.openschool.identity.port.out.ProfileRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class CreateProfileService implements CreateProfileUseCase {
    private final ProfileRepositoryPort profileRepository;

    @Override
    public Profile createProfile(CreateProfileCommand command) {
        Profile profile = Profile.builder()
                .id(UUID.randomUUID())
                .identityId(command.getIdentityId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .birthdate(command.getBirthdate())
                .build();

        profile = profileRepository.save(profile);

        return profile;
    }
}
