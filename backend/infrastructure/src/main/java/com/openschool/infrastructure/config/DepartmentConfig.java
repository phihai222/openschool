package com.openschool.infrastructure.config;

import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.department.service.DepartmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfig {

    @Bean
    public DepartmentService departmentService(DepartmentRepositoryPort departmentRepositoryPort) {
        return new DepartmentService(departmentRepositoryPort);
    }
}
