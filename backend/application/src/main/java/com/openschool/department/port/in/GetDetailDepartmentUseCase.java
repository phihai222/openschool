package com.openschool.department.port.in;

import com.openschool.domain.department.Department;

public interface GetDetailDepartmentUseCase {
    Department getDetailDepartment(Object departmentId);
}
