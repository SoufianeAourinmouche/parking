package com.dbogheanu.parking.api.servicies.interfaces;

public interface IService<T>
{
    T getEntity(int id);

    T saveEntity(T entity);

    boolean deleteEntity(T entity);
}
