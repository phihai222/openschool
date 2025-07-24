package com.openschool.department.port.in;

import java.util.UUID;

public interface DeleteDepartmentUseCase {
    void deleteDepartment(UUID departmentId);
}
