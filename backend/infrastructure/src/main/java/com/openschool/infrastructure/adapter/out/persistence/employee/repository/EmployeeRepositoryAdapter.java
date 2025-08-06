package com.openschool.infrastructure.adapter.out.persistence.employee.repository;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.employee.Employee;
import com.openschool.employee.port.out.EmployeeRepositoryPort;
import com.openschool.infrastructure.adapter.in.rest.employee.mapper.EmployeeMapper;
import com.openschool.infrastructure.adapter.out.persistence.employee.entity.EmployeeEntity;
import com.openschool.infrastructure.adapter.out.persistence.employee.repository.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public PageResult<Employee> getListEmployee(PageInfo pageInfo) {
        Pageable page = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(page);
        return new PageResult<>(
                pageInfo.getPage(),
                pageInfo.getSize(),
                employeeEntities.stream()
                        .map(EmployeeMapper::toEmployee)
                        .collect(Collectors.toList()),
                (long) employeeEntities.getTotalPages(),
                employeeEntities.getTotalElements()
        );
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

    @Override
    public PageResult<Employee> getListEmployeeInDepartment(PageInfo pageInfo, UUID departmentId) {
        return null;
    }
}
