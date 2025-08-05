package com.openschool.infrastructure.config;

import com.openschool.employee.port.in.*;
import com.openschool.employee.port.out.EmployeeRepositoryPort;
import com.openschool.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class EmployeeConfig {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Bean
    @Primary
    public EmployeeService EmployeeService() {
        return new EmployeeService(employeeRepositoryPort);
    }

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(EmployeeService employeeService) {
        return employeeService;
    }

    @Bean
    public UpdateEmployeeUseCase updateEmployeeUseCase(EmployeeService employeeService) {
        return employeeService;
    }

    @Bean
    public DeleteEmployeeUseCase deleteEmployeeUseCase(EmployeeService employeeService) {
        return employeeService;
    }

    @Bean
    public GetListEmployeeUseCase getListEmployeeUseCase(EmployeeService employeeService) {
        return employeeService;
    }

    @Bean
    public GetDetailEmployeeUseCase getDetailEmployeeUseCase(EmployeeService employeeService) {
        return employeeService;
    }

}
