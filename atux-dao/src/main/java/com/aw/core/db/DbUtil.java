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

import com.aw.support.date.DateHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: JCM
 * Date: 02/10/2007
 */
public class DbUtil {
    protected final Log logger = LogFactory.getLog(getClass());

    private DataSource dataSource;
    private DbCachedTime cachedTime;
    private static DbUtil instance = new DbUtil();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        cachedTime = new DbCachedTime(dataSource);
    }

    public static DbUtil instance(){
        return instance;
    }
    public Date getCurrentDate() {
        return cachedTime.getCurrentDate();
    }

    public boolean hasOnlyDateInfo(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        if (cal.get(Calendar.HOUR_OF_DAY)!=0) return false;
        if (cal.get(Calendar.MINUTE)!=0) return false;
        if (cal.get(Calendar.SECOND)!=0) return false;
        if (cal.get(Calendar.MILLISECOND)!=0) return false;
        return true;
    }

    public boolean dateIsBeforeToday(Date givenDate) {
        DateHelper today = new DateHelper(DbUtil.instance().getCurrentDate()).truncDay();
        DateHelper given = new DateHelper(givenDate).truncDay()/*.addDay(+1)*/;
        return given.date().before(today.date());
    }

}
