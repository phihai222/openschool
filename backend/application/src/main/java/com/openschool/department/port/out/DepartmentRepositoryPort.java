package com.openschool.department.port.out;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.department.Department;

import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepositoryPort {

    Department save(Department department);

    Department update(Department department);

    PageResult<Department> findAll(PageInfo pageInfo);

    Optional<Department> findById(UUID id);

    Optional<Department> findByDepartmentName(String name);

    boolean delete(Department department);

}