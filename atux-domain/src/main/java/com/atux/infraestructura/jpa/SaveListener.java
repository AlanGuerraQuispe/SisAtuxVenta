package com.atux.infraestructura.jpa;

/**
 * User: AW
 * Date: 24/02/13
 */
public interface SaveListener {
    void onPersist(Object entity);

    void onUpdate(Object entity);
}
