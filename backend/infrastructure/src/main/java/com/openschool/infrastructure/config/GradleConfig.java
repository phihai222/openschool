package com.openschool.infrastructure.config;

import com.openschool.grade.port.in.CreateGradeUseCase;
import com.openschool.grade.port.out.GradeRepositoryPort;
import com.openschool.grade.service.GradeService;
import com.openschool.school.port.out.SchoolRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradleConfig {
    @Bean
    public GradeService gradeService(
            SchoolRepositoryPort schoolRepositoryPort,
            GradeRepositoryPort gradeRepositoryPort
    ) {
        return new GradeService(schoolRepositoryPort, gradeRepositoryPort);
    }

    @Bean
    @Qualifier("createGradeUseCase")
    public CreateGradeUseCase createGradeUseCase(GradeService gradeService) {
        return gradeService;
    }
}
