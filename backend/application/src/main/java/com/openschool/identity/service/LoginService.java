package com.openschool.identity.service;

import com.openschool.domain.identity.model.Account;
import com.openschool.identity.exception.InvalidCredentialsException;
import com.openschool.identity.port.in.LoginUseCase;
import com.openschool.identity.port.out.AccountRepositoryPort;
import com.openschool.identity.port.out.PasswordEncoderPort;
import com.openschool.identity.port.out.TokenGeneratorPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class LoginService implements LoginUseCase {
    private AccountRepositoryPort accountRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    public String login(String username, String rawPassword) {
        Optional<Account> user = accountRepositoryPort.findByUsername(username);
        if (user.isEmpty() || !passwordEncoderPort.matches(rawPassword, user.get().getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        return tokenGeneratorPort.generateToken(user.get());
    }
}
