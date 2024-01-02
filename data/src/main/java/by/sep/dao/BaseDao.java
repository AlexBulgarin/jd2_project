package by.sep.dao;

import java.io.Serializable;

public interface BaseDao<T> {
    String create(T t);

    <S extends T> S read(Class<S> clazz, Serializable id);

    boolean update(T t);

    boolean delete(T t);

}
