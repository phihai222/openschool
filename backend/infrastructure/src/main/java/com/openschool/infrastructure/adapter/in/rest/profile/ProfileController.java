package com.openschool.infrastructure.adapter.in.rest.profile;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.in.UpdateProfileUseCase;
import com.openschool.identity.port.in.command.UpdateProfileCommand;
import com.openschool.infrastructure.adapter.in.rest.profile.dto.UpdateProfileRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {
    private final UpdateProfileUseCase updateProfileUseCase;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody UpdateProfileRequest payload) {
        // Convert ProfileRequest to CreateProfileCommand
        UpdateProfileCommand command = UpdateProfileCommand.builder()
                .identityId(UUID.randomUUID()) // TODO Get from token
                .birthdate(payload.getBirthdate())
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .build();
        Profile profile = updateProfileUseCase.createProfile(command);
        return ResponseEntity.ok(profile);
    }
}
