/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.db;

import com.aw.core.db.transaction.TransactionDecorator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

/**
 * User: JCM
 * Date: 08/10/2007
 */
class DbCachedTime {
    protected final Log logger = LogFactory.getLog(getClass());
    private DataSource dataSource;

    private long maximunRetrievalAge = 2000;  //2000 miliseconds
    /**
     * Date on DB,  last time date was retrieved
     */
    private Date lastDbDateRetrieved;
    /**
     * Date on local machine,  last time date was retrieved
     */
    private Date lastDbDateRetrieved_localTimeStmp;

    public DbCachedTime(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Date queryDBForSysdate() {
        return (Date) TransactionDecorator.executeQuery(new DBExecuter() {
            public Object execute() {
                JdbcTemplate jt = new JdbcTemplate(dataSource);
                String sql = "select sysdate from dual";
                Date date = (Date) jt.queryForObject(sql, Date.class);
                return date;
            }
        });
    }

    public Date getCurrentDate() {
        if (needToRetrieveFreshDate())
            retrieveDBDate();
        return currentDate();
    }

    private Date currentDate() {
        long deltaRetrievalTime = System.currentTimeMillis() - lastDbDateRetrieved_localTimeStmp.getTime();
        return new Date(lastDbDateRetrieved.getTime() + deltaRetrievalTime);
    }

    private void retrieveDBDate() {
        try {
            lastDbDateRetrieved = queryDBForSysdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("The current date from the database was not taken.", e);
            lastDbDateRetrieved = new Date();
        }
        lastDbDateRetrieved_localTimeStmp = new Date();
    }

    private boolean needToRetrieveFreshDate() {
        // check for fisrt time retrieval
        if (lastDbDateRetrieved == null || lastDbDateRetrieved_localTimeStmp == null)
            return true;
        // check not big difference local time vs
        long deltaRetrievalTime = Math.abs(System.currentTimeMillis() - lastDbDateRetrieved_localTimeStmp.getTime());
        if (maximunRetrievalAge > 0 && deltaRetrievalTime > maximunRetrievalAge)
            return true;
        // date retrieved is still valid
        return false;
    }

}
