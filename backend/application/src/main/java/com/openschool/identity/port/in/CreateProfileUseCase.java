package com.openschool.identity.port.in;

import com.openschool.domain.identity.model.Profile;
import com.openschool.identity.port.in.command.CreateProfileCommand;

public interface CreateProfileUseCase {
    Profile createProfile(CreateProfileCommand command);
}
