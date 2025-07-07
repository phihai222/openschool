package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.UserCredentials;

public interface TokenGeneratorPort {
    String generateToken(UserCredentials user);
}
