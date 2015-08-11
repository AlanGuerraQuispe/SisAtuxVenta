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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class used to represent a bean attribute
 *
 * @author gmateo
 *         05/11/2004
 */
public class BeanUtils {
    protected static final Log logger = LogFactory.getLog(BeanUtils.class);

    public static Object getPropertyValueNullSafe(BeanWrapper beanWrapper, String fieldName) {
        while(fieldName.indexOf('.') != -1){
            String beanName = fieldName.substring(0, fieldName.indexOf('.'));
            fieldName = fieldName.substring( fieldName.indexOf('.')+1);
            Object beanRef = beanWrapper.getPropertyValue(beanName);
            if (beanRef ==null){
                logger.debug("[" + fieldName + "] Bean-->Cmpt:<NULL> inner path is null");
                return null;
            }
            beanWrapper = new BeanWrapperImpl(beanRef);
        }
        Object beanValue = beanWrapper.getPropertyValue(fieldName);;
//        if (fieldName.indexOf('.') == -1 ||
//                beanWrapper.getPropertyValue(fieldName.substring(0, fieldName.indexOf('.'))) != null) {
//
//            beanValue = beanWrapper.getPropertyValue(fieldName);
//        } else {
//            logger.debug("[" + fieldName + "] Bean-->Cmpt:<NULL> inner path is null");
//        }
        return beanValue;
    }

    public static boolean equals(Object value1, Object value2) {
        if (value1==null && value2== null) return true;
        if (value1!=null ) return value1.equals(value2);
        else return value2.equals(value1);
    }
//    public static boolean equalsProp(Long id1, Long id2, Object bean1, Object bean2) {
//        if (id1!=null) return equals(id1, id2);
//        else return equals(bean1,bean2);
//    }
//
//
//    public static void hashCodeProp(Long id, Object bean) {
//        return (id != null ? id.hashCode() : super.hashCode());
//        //To change body of created methods use File | Settings | File Templates.
//    }

    public static Object copyProperties(Object source, Object target,
                                      String[] propertyNamesToIgnore,
                                      boolean ignoreProxy,
                                      boolean ignoreCollections) {
        List<String> propertyNamesToIgnoreList = propertyNamesToIgnore==null
                ? Collections.EMPTY_LIST
                : Arrays.asList(propertyNamesToIgnore);
        BeanWrapper sourceWrap = new BeanWrapperImpl(source);
        BeanWrapper targetWrap = new BeanWrapperImpl(target);
        for(PropertyDescriptor propDescriptor: sourceWrap.getPropertyDescriptors()){
            String propName = propDescriptor.getName();
            //chequear que no esta en la lista a ignorar
            if (propertyNamesToIgnoreList.contains(propName )) continue;
            //chequear que se puede leer
            if (!sourceWrap.isReadableProperty(propName )) continue;
            //chequear que se puede escribir
            if (!targetWrap.isWritableProperty(propName )) continue;

            Object sourceValue = sourceWrap.getPropertyValue(propName );

            //chequear que objeto no es un proxy
            if (ignoreProxy && sourceValue!=null && Proxy.isProxyClass(sourceValue.getClass())) continue;

            //chequear que objeto no una collection
            if (ignoreCollections && sourceValue instanceof Collection) continue;

            targetWrap.setPropertyValue(propName , sourceValue);
        }
        return target;
    }

    public static int countPropertyFilled(Object bean) {
        BeanWrapper wrap = new BeanWrapperImpl(bean);
        int count = 0;
        for (PropertyDescriptor descriptor : wrap.getPropertyDescriptors()) {
            if (descriptor.getReadMethod()==null ||descriptor.getWriteMethod()==null) continue; 
            Object value = wrap.getPropertyValue(descriptor.getName());
            if (value instanceof String){
                if (StringUtils.hasText((String) value))
                    count++;
            }else if (value!=null)
                count++; 
        }
        return count;
    }
}
