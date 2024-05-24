package com.crudbasics.crudbasic.Services;

import java.util.List;

public interface InitialCrud<T, ID> {

    T save(T t) throws Exception;

    T update(T t, ID id) throws Exception;

    List<T> readAll() throws Exception;

    T readById(ID id) throws Exception;

    void delete(ID id) throws Exception;

}
