package com.openschool.identity.port.in;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.in.command.UpdateProfileCommand;

public interface UpdateProfileUseCase {
    Profile createProfile(UpdateProfileCommand command);
}
