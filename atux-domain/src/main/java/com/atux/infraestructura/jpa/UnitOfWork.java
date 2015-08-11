package com.atux.infraestructura.jpa;

/**
 * User: aglwkrs
 * Date: 01/12/11
 */
public interface UnitOfWork {
    void work() throws Exception;
}
