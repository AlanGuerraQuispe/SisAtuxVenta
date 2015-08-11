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
package com.aw.core.util;

import com.aw.core.domain.AWBusinessException;
import com.aw.support.ObjectConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.sql.SQLException;
import java.util.*;

/**
 * User: Manuel Flores
 * Date: 11/10/2007
 */
public class QTTablaABeanMapper {
    public interface DataRowProvider {
        Object getObject(String colName) throws SQLException, Exception;
    }

    protected final Log logger = LogFactory.getLog(getClass());
    Map colNameToSetters = new HashMap();
    boolean metatadaBuilt = false;

    public void buildMetadata(Collection metaData) {
        if (metatadaBuilt) return;
        for (Iterator iterator = metaData.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            if (colNameToSetters.containsKey(name) ||
                    colNameToSetters.containsKey(name.toUpperCase())) {
                logger.warn("No existe Setter para:"+name);
            } else {
                colNameToSetters.put(name, buildSetterName(name));
            }
        }
        metatadaBuilt = true;
    }

    public Object buildNewBean(Class type, DataRowProvider rs) {
            Object bean = instantiateBean(type);
            BeanWrapper wrap = new BeanWrapperImpl(bean);
            for (Iterator iterator = colNameToSetters.keySet().iterator(); iterator.hasNext();) {
                String colName = (String) iterator.next();
                String setter = (String) colNameToSetters.get(colName);       //vaPrecioCostoVVFUnit
                Object value  = null;
                try {
                    value = rs.getObject(colName);
                    if (wrap.isWritableProperty(setter)) {
                        //logger.debug("Setting "+value+ " "+colName+"-->"+setter);
                        Object convertedValue = ObjectConverter.convert(value, wrap.getPropertyType(setter));
                        wrap.setPropertyValue(setter, convertedValue);
                        //wrap.setPropertyValue(setter, value);
                    } else {
                        logger.debug("Not set " + value + " " + colName + "-->(setter R/O):" + setter);
                    }
                } catch (Throwable e) {
                    logger.error("Error: No se puede settear propertyName:"+setter+" db.ColName:"+colName+" con valor:"+value);
                    throw AWBusinessException.wrapUnhandledException(logger, e);
                }
            }
            return bean;
    }

    protected Object instantiateBean(Class type) {
        try {
            Object bean = type.newInstance();
            return bean;
        } catch (Throwable e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public String buildSetterName(String name) {
        // no requiere transformacion
        if (name.indexOf('_') == -1) return name;

        StringTokenizer tokenizer = new StringTokenizer(name, "_");
        List tokens = new ArrayList();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        String[] partes = (String[]) tokens.toArray(new String[tokens.size()]);//= name.split("_");
        StringBuffer setter = new StringBuffer();
        for (int i = 0; i < partes.length; i++) {
            String primLetra = partes[i].substring(0, 1).toUpperCase();
            if (setter.length() == 0) primLetra = primLetra.toLowerCase();
            String resto = partes[i].length() <= 1 ? "" : partes[i].substring(1).toLowerCase();
            setter.append(primLetra).append(resto);
        }
        return setter.toString();
    }


    public void addColumSetter(String column, String setter) {
        colNameToSetters.put(column, setter);
    }

    public boolean isMetatadaBuilt() {
        return metatadaBuilt;
    }

    public static void main(String[] args) {
        String name = new QTTablaABeanMapper().buildSetterName("CO_LOCAL_PERU");
        System.out.println(name);
    }
}
