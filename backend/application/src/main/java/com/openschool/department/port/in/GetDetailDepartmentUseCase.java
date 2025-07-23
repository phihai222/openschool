package com.openschool.department.port.in;

import com.openschool.domain.department.Department;

import java.util.UUID;

public interface GetDetailDepartmentUseCase {
    Department getDetailDepartment(UUID departmentId);
}
