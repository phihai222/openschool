package com.openschool.infrastructure.config;

import com.openschool.identity.port.in.LoginUseCase;
import com.openschool.identity.port.in.UpdateProfileUseCase;
import com.openschool.identity.port.out.*;
import com.openschool.identity.service.UpdateProfileService;
import com.openschool.identity.service.InitRootUserService;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.identity.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfig {
    @Bean
    public InitRootUserUseCase initRootUserUseCase(IdentityRepositoryPort identityRepositoryPort,
                                                   AccountRepositoryPort accountRepositoryPort,
                                                   PasswordEncoderPort passwordEncoder) {
        return new InitRootUserService(identityRepositoryPort, accountRepositoryPort, passwordEncoder);
    }

    @Bean
    public LoginUseCase loginUseCase(AccountRepositoryPort accountRepositoryPort,
                                     PasswordEncoderPort passwordEncoder,
                                     TokenGeneratorPort tokenGeneratorPort) {
        return new LoginService(accountRepositoryPort, passwordEncoder, tokenGeneratorPort);
    }

    @Bean
    public UpdateProfileUseCase updateProfileUseCase(IdentityRepositoryPort identityRepositoryPort,
                                                     ProfileRepositoryPort profileRepositoryPort) {
        return new UpdateProfileService(profileRepositoryPort, identityRepositoryPort);
    }
}