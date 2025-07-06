package com.openschool.infrastructure.config;

import com.openschool.identity.port.out.UserCredentialsRepository;
import com.openschool.identity.service.InitRootUserService;
import com.openschool.identity.port.in.InitRootUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfig {
    @Bean
    public InitRootUserUseCase initRootUserUseCase(UserCredentialsRepository repository) {
        return new InitRootUserService(repository);
    }
}