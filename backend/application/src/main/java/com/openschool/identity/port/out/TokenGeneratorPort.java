package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.Account;

public interface TokenGeneratorPort {
    String generateToken(Account user);
}
