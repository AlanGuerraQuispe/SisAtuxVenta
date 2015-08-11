package com.atux.desktop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import javax.persistence.EntityManagerFactory;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
public abstract class TestPersistenceBase extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    protected DatabaseDriver databaseDriver;


    @BeforeTransaction
    public void setUp() throws Exception {
        databaseDriver = new DatabaseDriver(entityManagerFactory);
    }

    @AfterTransaction
    public void tearDown() throws Exception {
        onTearDownTransaction();
    }

    protected void onTearDownTransaction() throws Exception {
    }

    @BeforeTransaction
    public abstract void init() throws Exception;
}
