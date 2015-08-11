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
package com.aw.support.reflection;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

/**
 * Util class used to get and set Attributes
 *
 * @author gmateo
 *         17-oct-2004
 */
public class AttributeAccessor {
    protected static final Log logger = LogFactory.getLog(AttributeAccessor.class);

    /**
     * @param target
     * @param attrName
     * @return
     */
    public static Object get(Object target, String attrName) {
        Object objectToReturn = null;
        try {
            Class cls = target.getClass();
            Field field = getField(cls, attrName);
            objectToReturn = field.get(target);
        } catch (Throwable e) {
            logger.error("Exception getting attribute value:" + attrName, e);
            e.printStackTrace();
        }
        return objectToReturn;
    }

    /**
     * @param target
     * @return
     */
    public static void set(Object target, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting the value of the attribute:<" + field.getName() + "> of object:<" + target + "> check: Attribute Type:<" + field.getType() + "> value Type: <" + value.getClass() + ">");
        }
    }

    /**
     * @param target
     * @param attrName
     * @return
     */
    public static void set(Object target, String attrName, Object value) {
        Field field = null;
        try {
            Class cls = target.getClass();
            field = getField(cls, attrName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting the value of the attribute:<" + attrName + "> of object:<" + target + "> check: Attribute Type:<" + field.getType() + "> value Type: <" + value.getClass() + ">");
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalStateException("Error setting the value of the attribute:<" + attrName + "> of object:<" + target + ">");
        }
    }

    public static Field getField(Class cls, String attrName) throws Throwable {
        Field field = null;
        try {
            field = cls.getDeclaredField(attrName);
        } catch (NoSuchFieldException e) {
            try {
                // Se va a buscar en la superclase
                field = cls.getSuperclass().getDeclaredField(attrName);
            } catch (NoSuchFieldException e2) {
                try {
                    // Se va a buscar en la superclase 2
                    field = cls.getSuperclass().getSuperclass().getDeclaredField(attrName);
                } catch (NoSuchFieldException e3) {
                    field = cls.getSuperclass().getSuperclass().getSuperclass().getDeclaredField(attrName);

                }
            }
        } catch (SecurityException e) {
            throw e;
        }
        return field;
    }
}
