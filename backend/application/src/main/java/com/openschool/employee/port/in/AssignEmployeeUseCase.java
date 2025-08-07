package com.openschool.employee.port.in;

import java.util.List;
import java.util.UUID;

public interface AssignEmployeeUseCase {
    void assignEmployee(UUID departmentId, List<UUID> employeeId);
}
