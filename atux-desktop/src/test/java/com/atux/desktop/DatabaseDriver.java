package com.atux.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
public class DatabaseDriver {
    Database database;
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseDriver.class);

    public DatabaseDriver(EntityManagerFactory entityManagerFactory) {
        database = new Database(entityManagerFactory);
    }

}
