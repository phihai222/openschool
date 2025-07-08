package com.openschool.identity.port.in;

import com.openschool.domain.identity.model.Profile;

import java.util.UUID;

public interface GetCurrentUserProfileUseCase {
    Profile getCurrentUserProfile(UUID identityId);
}
