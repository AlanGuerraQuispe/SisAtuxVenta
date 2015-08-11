package com.atux.infraestructura.jpa;

/**
 * User: RAC
 * Date: 07/03/12
 */
public abstract class QueryUnitOfWork<T> implements UnitOfWork {
    public T result;

    public void work() throws Exception {
        result = query();
    }

    public abstract T query() throws Exception;
}

