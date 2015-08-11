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
package com.aw.support.beans;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * @author jcvergara
 *         15/11/2004
 */
public class BeanPropertiesComparator implements Comparator {
    private static int ASC =1;
    private static int DESC =-1;
    private static int EQ =0;
    List<PropertyCompInfo> propertyCompInfoList = new ArrayList<PropertyCompInfo>();


    public BeanPropertiesComparator asc(String propertyName){
        propertyCompInfoList.add(new PropertyCompInfo(propertyName, ASC));
        return this;
    }
    public BeanPropertiesComparator desc(String propertyName){
        propertyCompInfoList.add(new PropertyCompInfo(propertyName, DESC));
        return this;
    }
    public BeanPropertiesComparator eq(String propertyName){
        propertyCompInfoList.add(new PropertyCompInfo(propertyName, EQ));
        return this;
    }
    @Override
    public int compare(Object o1, Object o2) {
        BeanWrapper wrap1 = new BeanWrapperImpl(o1);
        BeanWrapper wrap2 = new BeanWrapperImpl(o2);
        for (int i = 0; i < propertyCompInfoList.size(); i++) {
            PropertyCompInfo propertyCompInfo = propertyCompInfoList.get(i);
            int retVal = doCompare((Comparable) wrap1.getPropertyValue(propertyCompInfo.propertyName),
                    (Comparable) wrap2.getPropertyValue(propertyCompInfo.propertyName),
                    propertyCompInfo.order);
            if (retVal!=0) return retVal;
        }
        return 0;
    }

    private int doCompare(Comparable value1 , Comparable value2, int order) {
        int retVal;
        if (value1 != null && value2 != null)
            retVal = value1.compareTo(value2);
        else if (value1 != null)
            retVal  = 1;
        else if (value2 != null)
            retVal = -1;
        else retVal = 0;//ambos son null
        if (order==ASC || order==DESC)
            return order==ASC? retVal: -1*retVal;
        else
            return retVal;
    }

    private static class PropertyCompInfo{
        String propertyName;
        int order ;

        private PropertyCompInfo(String propertyName, int order) {
            this.propertyName = propertyName;
            this.order = order;
        }
    }
}