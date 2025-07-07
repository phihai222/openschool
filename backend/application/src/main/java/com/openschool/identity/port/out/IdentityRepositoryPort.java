package com.openschool.identity.port.out;

import com.openschool.domain.identity.model.Identity;

public interface IdentityRepositoryPort {
    void save(Identity identity);
}