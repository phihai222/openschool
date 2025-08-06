package com.openschool.common.pageable;

public interface PageConverter<T,V> {

    PageResult<V> convertToResponsePage(PageResult<T> pageResult);
}
