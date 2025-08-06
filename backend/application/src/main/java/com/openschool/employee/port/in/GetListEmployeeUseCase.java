package com.openschool.employee.port.in;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.employee.Employee;

import java.util.List;

public interface GetListEmployeeUseCase {
    PageResult<Employee> getListEmployee(PageInfo pageinfo);
}
