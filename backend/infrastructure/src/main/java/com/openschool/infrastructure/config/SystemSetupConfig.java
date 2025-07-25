package com.openschool.infrastructure.config;

import com.openschool.systemsetup.port.in.GetSystemSetupStatusUseCase;
import com.openschool.systemsetup.port.out.SystemSetupRepositoryPort;
import com.openschool.systemsetup.service.SystemSetupService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemSetupConfig {
    @Bean
    public GetSystemSetupStatusUseCase getSystemSetupStatusUseCase(
            SystemSetupRepositoryPort systemSetupRepositoryPort
    ) {
        return new SystemSetupService(systemSetupRepositoryPort);
    }

}
