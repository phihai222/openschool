package com.openschool.infrastructure.adapter.in.rest.profile;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.in.GetCurrentUserProfileUseCase;
import com.openschool.identity.port.in.UpdateProfileUseCase;
import com.openschool.identity.port.in.command.UpdateProfileCommand;
import com.openschool.infrastructure.adapter.in.rest.profile.dto.UpdateProfileRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final UpdateProfileUseCase updateProfileUseCase;
    private final GetCurrentUserProfileUseCase getCurrentUserProfileUseCase;

    public ProfileController(
            @Qualifier("updateProfileUseCase") UpdateProfileUseCase updateProfileUseCase,
            @Qualifier("getCurrentUserProfileUseCase") GetCurrentUserProfileUseCase getCurrentUserProfileUseCase
    ) {
        this.updateProfileUseCase = updateProfileUseCase;
        this.getCurrentUserProfileUseCase = getCurrentUserProfileUseCase;
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody UpdateProfileRequest payload, Authentication authentication) {
        // Convert ProfileRequest to CreateProfileCommand
        UpdateProfileCommand command = UpdateProfileCommand.builder()
                .identityId(UUID.fromString((String) authentication.getPrincipal()))
                .birthdate(payload.getBirthdate())
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .build();
        Profile profile = updateProfileUseCase.createProfile(command);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/me")
    public ResponseEntity<Profile> getCurrentUserProfile(Authentication authentication) {
        UUID identityId = UUID.fromString((String) authentication.getPrincipal());
        Profile profile = getCurrentUserProfileUseCase.getCurrentUserProfile(identityId);
        return ResponseEntity.ok(profile);
    }
}
