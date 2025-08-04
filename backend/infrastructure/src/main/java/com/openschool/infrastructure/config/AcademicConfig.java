package com.openschool.infrastructure.config;

import com.openschool.academic.port.in.CreateAcademicYearUseCase;
import com.openschool.academic.port.out.AcademicYearRepositoryPort;
import com.openschool.academic.service.AcademicYearService;
import com.openschool.school.port.out.SchoolRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicConfig {
    @Bean
    public AcademicYearService getAcademicYearService(
            AcademicYearRepositoryPort academicYearRepositoryPort,
            SchoolRepositoryPort schoolRepositoryPort
    ) {
        return new AcademicYearService(academicYearRepositoryPort, schoolRepositoryPort);
    }

    @Bean
    @Qualifier("createAcademicYearUseCase")
    public CreateAcademicYearUseCase getCreateAcademicYearUseCase(AcademicYearService academicYearService) {
        return academicYearService;
    }
}
