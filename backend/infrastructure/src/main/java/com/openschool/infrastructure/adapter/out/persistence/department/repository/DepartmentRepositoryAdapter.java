package com.openschool.infrastructure.adapter.out.persistence.department.repository;

import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper;
import com.openschool.infrastructure.adapter.out.persistence.department.entity.DepartmentEntity;
import com.openschool.infrastructure.adapter.out.persistence.department.repository.jpa.JpaDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;

@Service
@RequiredArgsConstructor
public class DepartmentRepositoryAdapter implements DepartmentRepositoryPort {

    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Override
    public Department save(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentEntity entity = jpaDepartmentRepository.save(toDepartmentEntity(department));
        return toDepartment(entity);
    }

    @Override
    public Department update(Department department) {
        if (department == null || department.getDepartmentId() == null) {
            return null;
        }
        DepartmentEntity entity = jpaDepartmentRepository.findById(UUID.fromString(department.getDepartmentId().toString()))
                .orElse(null);
        if (entity == null) {
            return null;
        }
        entity = updateDepartmentEntity(entity, department);
        entity = jpaDepartmentRepository.save(entity);
        return toDepartment(entity);
    }

    @Override
    public List<Department> findAll() {
        return jpaDepartmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDepartment)
                .toList();
    }

    @Override
    public Optional<Department> findById(Object id) {
        if (id == null) {
            return Optional.empty();
        }
        DepartmentEntity entity = jpaDepartmentRepository.findById(UUID.fromString(id.toString()))
                .orElse(null);
        return Optional.ofNullable(toDepartment(entity));
    }

    @Override
    public Optional<Department> findByDepartmentName(String departmentName) {
        DepartmentEntity entity = jpaDepartmentRepository.findByDepartmentName(departmentName)
                .orElse(null);
        return Optional.ofNullable(toDepartment(entity));
    }

    @Override
    public boolean delete(Department department) {
        if (department == null || department.getDepartmentId() == null) {
            return false;
        }

        DepartmentEntity entity = jpaDepartmentRepository.findById(UUID.fromString(department.getDepartmentId().toString())).orElse(null);
        if (entity == null) {
            return false;
        }
        jpaDepartmentRepository.delete(entity);
        return true;
    }

}
