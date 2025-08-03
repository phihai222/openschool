package com.openschool.infrastructure.adapter.out.persistence.employee.repository;

import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.out.EmployeeRepositoryPort;
import com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper;
import com.openschool.infrastructure.adapter.out.persistence.employee.entity.EmployeeEntity;
import com.openschool.infrastructure.adapter.out.persistence.employee.repository.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> createEmployee(Employee command) {
        try{
            EmployeeEntity entity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(command));
            return Optional.of(EmployeeMapper.toEmployee(entity));
        }catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> updateEmployee(Employee command) {
    Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(command.getEmployeeId());
    if (existingEmployee.isEmpty()) {
        return Optional.empty();
    }
        try {
            EmployeeEntity entity = employeeRepository.save(EmployeeMapper.updateEmployeeEntity(existingEmployee.get(),command));
            return Optional.of(EmployeeMapper.toEmployee(entity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> getDetailEmployee(UUID employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        try{
            return employeeEntity.map(EmployeeMapper::toEmployee);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> findByPhoneNumberOrEmail(String phoneNumber, String email) {
        return employeeRepository.findByPhoneNumberOrEmail(phoneNumber, email);
    }

    @Override
    public List<Employee> getListEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(EmployeeMapper::toEmployee).collect(Collectors.toList());
    }

    @Override
    public boolean deleteEmployee(UUID employeeId) {
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isEmpty()) {
            return false;
        }

        try{
            employeeRepository.deleteById(existingEmployee.get().getEmployeeId());
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
