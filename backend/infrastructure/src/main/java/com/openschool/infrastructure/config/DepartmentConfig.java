package com.openschool.infrastructure.config;

import com.openschool.department.port.in.*;
import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.department.service.DepartmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DepartmentConfig {

    @Bean
    @Primary
    public DepartmentService departmentService(DepartmentRepositoryPort departmentRepositoryPort) {
        return new DepartmentService(departmentRepositoryPort);
    }

    @Bean
    public CreateDepartmentUseCase createDepartmentUseCase(DepartmentService departmentService) {
        return departmentService;
    }

    @Bean
    public UpdateDepartmentUseCase updateDepartmentUseCase(DepartmentService departmentService) {
        return departmentService;
    }

    @Bean
    public DeleteDepartmentUseCase deleteDepartmentUseCase(DepartmentService departmentService) {
        return departmentService;
    }

    @Bean
    public GetListDepartmentUseCase getListDepartmentUseCase(DepartmentService departmentService) {
        return departmentService;
    }

    @Bean
    public GetDetailDepartmentUseCase getDetailDepartmentUseCase(DepartmentService departmentService) {
        return departmentService;
    }
}
