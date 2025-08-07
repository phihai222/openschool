package com.openschool.infrastructure.adapter.out.persistence.department.repository;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.department.port.out.DepartmentRepositoryPort;
import com.openschool.domain.department.Department;
import com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper;
import com.openschool.infrastructure.adapter.out.persistence.department.entity.DepartmentEntity;
import com.openschool.infrastructure.adapter.out.persistence.department.repository.jpa.JpaDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.openschool.infrastructure.adapter.in.rest.department.mapper.DepartmentMapper.*;

@Repository
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
        DepartmentEntity entity = jpaDepartmentRepository.findById(department.getDepartmentId())
                .orElse(null);

        entity = updateDepartmentEntity(entity, department);

        if (entity == null) {
            return null;
        }
        entity = jpaDepartmentRepository.save(entity);
        return toDepartment(entity);
    }

    @Override
    public PageResult<Department> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<DepartmentEntity> departmentEntities = jpaDepartmentRepository.findAll(pageable);
        List<Department> departments = departmentEntities.stream().map(DepartmentMapper::toDepartment).toList();
        return new PageResult<>(pageInfo.getPage(), pageInfo.getSize(), departments, (long) departmentEntities.getTotalPages(), departmentEntities.getTotalElements());
    }

    @Override
    public Optional<Department> findById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        DepartmentEntity entity = jpaDepartmentRepository.findById(id)
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

        DepartmentEntity entity = jpaDepartmentRepository.findById(department.getDepartmentId()).orElse(null);
        if (entity == null) {
            return false;
        }
        jpaDepartmentRepository.delete(entity);
        return true;
    }
}
