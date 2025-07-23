package com.openschool.department.port.out;

import com.openschool.domain.department.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryPort {

    Department save(Department department);

    Department update(Department department);

    List<Department> findAll();

    Optional<Department> findById(Object id);

    Optional<Department> findByDepartmentName(String name);

    boolean delete(Department department);
}
