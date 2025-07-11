package com.openschool.infrastructure.config;

import com.openschool.identity.port.in.GetCurrentUserProfileUseCase;
import com.openschool.identity.port.in.LoginUseCase;
import com.openschool.identity.port.in.UpdateProfileUseCase;
import com.openschool.identity.port.out.*;
import com.openschool.identity.service.InitRootUserService;
import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.identity.service.LoginService;
import com.openschool.identity.service.ProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfig {
    @Bean
    public InitRootUserUseCase initRootUserUseCase(IdentityRepositoryPort identityRepositoryPort,
                                                   AccountRepositoryPort accountRepositoryPort,
                                                   PasswordEncoderPort passwordEncoder, RoleRepositoryPort roleRepositoryPort) {
        return new InitRootUserService(identityRepositoryPort, accountRepositoryPort, passwordEncoder, roleRepositoryPort);
    }

    @Bean
    public LoginUseCase loginUseCase(AccountRepositoryPort accountRepositoryPort,
                                     PasswordEncoderPort passwordEncoder,
                                     TokenGeneratorPort tokenGeneratorPort) {
        return new LoginService(accountRepositoryPort, passwordEncoder, tokenGeneratorPort);
    }

    @Bean
    public ProfileService profileService(ProfileRepositoryPort profileRepositoryPort, IdentityRepositoryPort identityRepositoryPort) {
        return new ProfileService(profileRepositoryPort, identityRepositoryPort);
    }

    @Bean
    @Qualifier("updateProfileUseCase")
    public UpdateProfileUseCase updateProfileUseCase(ProfileService profileService) {
        return profileService;
    }

    @Bean
    @Qualifier("getCurrentUserProfileUseCase")
    public GetCurrentUserProfileUseCase getCurrentUserProfileUseCase(ProfileService profileService) {
        return profileService;
    }
}