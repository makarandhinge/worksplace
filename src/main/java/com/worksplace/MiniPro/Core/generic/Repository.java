package com.worksplace.MiniPro.Core.generic;

public interface Repository<T> {
    T findById(long id);
    void save(T entity);
}
