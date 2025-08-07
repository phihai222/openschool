package com.openschool.employee.port.in;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.employee.Employee;

import java.util.UUID;

public interface GetListEmployeeInDepartmentUseCase {
    PageResult<Employee> getListEmployee(PageInfo pageInfo, UUID departmentId);
}
