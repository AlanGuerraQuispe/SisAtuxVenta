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
public class AWDate {
    public static AWDate PAST = new AWDate(1900, 1, 1);
    public static AWDate FUTURE = new AWDate(2100, 1, 1);

    private GregorianCalendar base;

    public AWDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        initialize((GregorianCalendar) calendar);
    }

    public AWDate(int year, int month, int day) {
        initialize(new GregorianCalendar(year, month - 1, day));
    }

    public AWDate(GregorianCalendar arg) {
        initialize(arg);
    }

    private void initialize(GregorianCalendar arg) {
        base = trimToDays(arg);
    }

    private GregorianCalendar trimToDays(GregorianCalendar arg) {
        GregorianCalendar result = arg;
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }

    public GregorianCalendar getCalendar() {
        return base;
    }

    public boolean after(AWDate arg) {
        return getTime().after(arg.getTime());
    }

    public boolean before(AWDate arg) {
        return getTime().before(arg.getTime());
    }

    public int compareTo(Object arg) {
        AWDate other = (AWDate) arg;
        return getTime().compareTo(other.getTime());
    }

    public boolean equals(Object arg) {
        if (this == arg) return true;
        if (!(arg instanceof AWDate)) return false;
        AWDate other = (AWDate) arg;
        return (base.equals(other.base));
    }

    public AWDate addDays(int days) {
        Calendar calendar=base;
        calendar.add(Calendar.DATE,days);
        return new AWDate(calendar.getTime());
    }

    public Date getTime() {
        return base.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date();
       
        AWDate awDate= new AWDate(date);
        //System.out.println(" antes "+awDate.getTime());
        awDate.addDays(15);
        //System.out.println(" antes "+awDate.getTime());


    }
}

