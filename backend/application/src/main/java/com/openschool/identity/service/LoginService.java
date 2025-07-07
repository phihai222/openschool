package com.openschool.identity.service;

import com.openschool.domain.identity.model.UserCredentials;
import com.openschool.identity.exception.InvalidCredentialsException;
import com.openschool.identity.port.in.LoginUseCase;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.TokenGeneratorPort;
import com.openschool.identity.port.out.UserCredentialsRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class LoginService implements LoginUseCase {
    private final UserCredentialsRepositoryPort userCredentialsRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    public String login(String email, String rawPassword) {
        Optional<UserCredentials> user = userCredentialsRepositoryPort.findByUsername(email);
        if (user.isEmpty() || !passwordEncoderPort.matches(rawPassword, user.get().getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        return tokenGeneratorPort.generateToken(user.get());
    }
}
