package com.openschool.infrastructure.config;

import com.openschool.identity.port.in.InitRootUserUseCase;
import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import com.openschool.systemsetup.port.in.SetupAdminUseCase;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import com.openschool.systemsetup.service.SystemSetupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemSetupConfig {
    @Bean
    public SystemSetupService systemSetupService(
            SystemSetupRepositoryPort systemSetupRepositoryPort,
            InitRootUserUseCase initRootUserUseCase) {
        return new SystemSetupService(systemSetupRepositoryPort, initRootUserUseCase);
    }

    @Bean
    @Qualifier("getSystemSetupStatusUseCase")
    public GetSystemSetupStatusUseCase getSystemSetupStatusUseCase(
            SystemSetupService systemSetupService
    ) {
        return systemSetupService;
    }

    @Bean
    @Qualifier("setupAdminUseCase")
    public SetupAdminUseCase setupAdminUseCase(
            SystemSetupService systemSetupService
    ) {
        return systemSetupService;
    }
}
