package com.openschool.employee.port.in;

import java.util.UUID;

public interface MoveEmployeeUseCase {
    void moveEmployee(UUID srcDepartmentId, UUID desDepartmentId, UUID employeeId);
}
