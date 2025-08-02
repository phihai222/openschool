package com.openschool.infrastructure.config;

import com.openschool.school.port.in.CreateSchoolUseCase;
import com.openschool.school.port.out.SchoolRepositoryPort;
import com.openschool.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfig {
    @Bean
    public SchoolService getSchoolService(SchoolRepositoryPort schoolRepository) {
        return new SchoolService(schoolRepository);
    }

    @Bean
    @Qualifier("createSchoolUseCase")
    public CreateSchoolUseCase createSchoolUseCase(SchoolService schoolService) {
        return schoolService;
    }
}
