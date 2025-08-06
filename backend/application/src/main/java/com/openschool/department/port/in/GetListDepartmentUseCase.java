package com.openschool.department.port.in;

import com.openschool.common.pageable.PageInfo;
import com.openschool.common.pageable.PageResult;
import com.openschool.domain.department.Department;

public interface GetListDepartmentUseCase {
    PageResult<Department> getDepartmentList(PageInfo pageInfo);
}
