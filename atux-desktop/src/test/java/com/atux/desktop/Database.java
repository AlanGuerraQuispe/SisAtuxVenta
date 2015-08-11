package com.atux.desktop;

import com.atux.desktop.builder.Builder;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
public class Database {
    private EntityManagerFactory entityManagerFactory;
    List<Object> persistentObjects = new ArrayList<Object>();

    public Database(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void persist(final Builder entity) throws Exception {

    }


    public void cleanUp() throws Exception {


    }

    public void merge(final Object entity) throws Exception {

    }

    public void remove(final Object entity) throws Exception {

    }


    public void removeAll(final Object... entities) throws Exception {


    }
}
