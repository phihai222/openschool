package com.openschool.department.port.in;

import com.openschool.domain.department.Department;

import java.util.List;

public interface GetListDepartmentUseCase {
    List<Department> getDepartmentList();
}
