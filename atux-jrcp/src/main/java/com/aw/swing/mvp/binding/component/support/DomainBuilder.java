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
package com.aw.swing.mvp.binding.component.support;

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
import java.util.ArrayList;
import java.util.Arrays;
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void registerDomain(Object domain) {
        this.domain = domain;
        defaultBeanWrapper = new BeanWrapperImpl(domain);
    }

    public void registerDomain(Class domain) {
        try {
            this.domain = domain.newInstance();
            this.defaultBeanWrapper = new BeanWrapperImpl(domain.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String builPathFieldName(String fieldName, String[] bindingPaths) {
        try {
            defaultBeanWrapper.getPropertyValue(fieldName);
            return fieldName;
        } catch (BeansException ex) {
            List<String> paths = new ArrayList();
            paths.add("domain");
            if (bindingPaths != null && bindingPaths.length > 0) {
                paths.addAll(Arrays.asList(bindingPaths));
            }
            for (String path : paths) {
                String currentfieldName = path + "." + fieldName;
                logger.debug("Searching:" + currentfieldName);
                try {
                    defaultBeanWrapper.getPropertyValue(currentfieldName);
                    return currentfieldName;
                } catch (BeansException ex1) {
                    logger.debug("not found fieldName:<" + currentfieldName+">");
                }
            }
        }
        return null;
    }


    public Method getMethodFor(String fieldName) {
        try {
            return defaultBeanWrapper.getPropertyDescriptor(fieldName).getReadMethod();
        } catch (Throwable ex) {
            logger.error("Error gettting descriptor for :" + fieldName);
            try {
                logger.debug("not found fieldName :" + fieldName + " searching in backBean ...");
                return defaultBeanWrapper.getPropertyDescriptor("backBean." + fieldName).getReadMethod();
            } catch (BeansException ex1) {
                logger.debug("not found fieldName in backBean");
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
