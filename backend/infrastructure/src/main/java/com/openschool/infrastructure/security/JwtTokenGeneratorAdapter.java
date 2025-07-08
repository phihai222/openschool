package com.openschool.infrastructure.security;

import com.openschool.domain.identity.model.Account;
import com.openschool.identity.port.out.TokenGeneratorPort;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGeneratorAdapter implements TokenGeneratorPort {

    private final JwtProvider jwtProvider;

    public JwtTokenGeneratorAdapter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String generateToken(Account user) {
        return jwtProvider.generateToken(user.getIdentityId().toString(), user.getUsername());
    }
}
