package com.openschool.infrastructure.identity;

import com.openschool.domain.identity.UserCredentialsRepository;
import com.openschool.identity.InitRootUserService;
import com.openschool.identity.InitRootUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfig {
    @Bean
    public InitRootUserUseCase initRootUserUseCase(UserCredentialsRepository repository) {
        return new InitRootUserService(repository);
    }
}