package com.openschool.common.pageable;

import com.openschool.domain.employee.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PageResult<T> extends PageInfo {
    private List<T> data;
    private Long totalPages;
    private Long totalElements;

    public PageResult(Integer page, Integer size, List<T> data, Long totalPages, Long totalElements) {
        super(page, size);
        this.data = data;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

}
