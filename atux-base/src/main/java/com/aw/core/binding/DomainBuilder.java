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
package com.aw.core.binding;

/**
 * User: User
 * Date: Oct 10, 2007
 */

import com.aw.support.reflection.MethodInvoker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.lang.reflect.Method;
import java.util.List;

/**
 * User: User
 * Date: Oct 9, 2007
 */
public class DomainBuilder {
    protected final Log logger = LogFactory.getLog(getClass());
    private Object domain;
    private BeanWrapper defaultBeanWrapper;
    List attributes;


    public DomainBuilder() {
    }

    public DomainBuilder(Object domain) {
        this.domain = domain;
        this.defaultBeanWrapper = new BeanWrapperImpl(domain);
    }

    public DomainBuilder(Class domain) {
        try {
            this.domain = domain.newInstance();
            this.defaultBeanWrapper = new BeanWrapperImpl(domain.newInstance());
        } catch (Exception e) {
            e.printStackTrace();  
        }

    }

    public void registerDomain(Object domain) {
        this.domain = domain;
        defaultBeanWrapper =  new BeanWrapperImpl(domain);
    }

    public void registerDomain(Class domain) {
        try {
            this.domain = domain.newInstance();
            this.defaultBeanWrapper = new BeanWrapperImpl(domain.newInstance());
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    public String builPathFieldName(String fieldName) {
        String path = "";
        try {
            defaultBeanWrapper.getPropertyValue(fieldName);
            return fieldName;
        } catch (BeansException ex) {
            try {
                logger.debug("not found fieldName :" + fieldName + " searching in domain ...");
                defaultBeanWrapper.getPropertyValue("domain." + fieldName);
                return "domain." + fieldName;
            } catch (BeansException ex1) {
                logger.debug("not found fieldName in domain");
            }
        }
        return null;
    }

    public Method getMethodFor(String fieldName) {
        try {
            return defaultBeanWrapper.getPropertyDescriptor(fieldName).getReadMethod();
        } catch (Throwable ex) {
            logger.error("Error gettting descriptor for :"+fieldName);
            try {
                logger.debug("not found fieldName :" + fieldName + " searching in domain ...");
                return defaultBeanWrapper.getPropertyDescriptor("domain." + fieldName).getReadMethod();
            } catch (BeansException ex1) {
                logger.debug("not found fieldName in domain");
            }
        }
        return null;

    }

    public Object getDomain() {
        return domain;
    }

    public void setDomain(Object domain) {
        this.domain = domain;
    }

    public List getAllAttributes() {
        return MethodInvoker.getAllAttributes(domain);
    }
}

