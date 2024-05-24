package com.crudbasics.crudbasic.Services.Implementation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.crudbasics.crudbasic.Exception.ModelNotFoundException;
import com.crudbasics.crudbasic.Repositories.InitialRepository;
import com.crudbasics.crudbasic.Services.InitialCrud;
import java.util.List;

public abstract class InitialCrudImpl<T, ID> implements InitialCrud<T, ID> {

    protected abstract InitialRepository<T, ID> getRepository();

    private String getIdFieldName(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            return field.getName();
        }
        return null;
    }

    @Override
    public T save(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        Class<?> clazz = t.getClass();
        String idFieldName = getIdFieldName(clazz);
        String methodName = "set" + Character.toUpperCase(idFieldName.charAt(0)) + idFieldName.substring(1);

        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);

        getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found: " + id));
        return getRepository().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found: " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("Id not found: " + id));
        getRepository().deleteById(id);
    }
}
