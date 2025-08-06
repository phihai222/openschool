package com.openschool.common.pageable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PageResult<T> extends PageInfo {
    private List<T> data;
    private Long totalPage;
    private Long totalElements;

    public PageResult(Integer page, Integer size,List<T> data, Long totalPage, Long totalElements) {
        super(page, size);
        this.data = data;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }

}
