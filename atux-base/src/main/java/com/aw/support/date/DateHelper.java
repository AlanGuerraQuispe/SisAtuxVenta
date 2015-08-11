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
package com.aw.support.date;

/**
 * User: User
 * Date: Oct 12, 2007
 */

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represent a time point, restringed to a date
 *
 * @author jcvergara
 *         Nov 23, 2004
 */
public class DateHelper {
    public static DateHelper build(Date date) {
        return new DateHelper(date);
    }

    Calendar calendar = GregorianCalendar.getInstance();
    public DateHelper(Date date) {
        calendar.setTime(date);
    }

    public DateHelper addDay(int dias) {
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return this;
    }
    public DateHelper addMonth(int meses) {
        calendar.add(Calendar.MONTH, meses);
        return this;
    }

    public DateHelper truncDay() {
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return this;
    }

    public Date date() {
        return calendar.getTime();
    }

}