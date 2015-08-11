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

import com.aw.core.exception.AWException;
import com.aw.core.exception.AWSystemException;
import com.aw.core.exception.FlowBreakSilentlyException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataIntegrityViolationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Util class used to invoke methods using reflection
 *
 * @author gmateo
 *         17-oct-2004
 */
public class MethodInvoker {
    protected static final Log logger = LogFactory.getLog(MethodInvoker.class);
    public static List tiposValidos = Arrays.asList("java.lang.Integer","java.lang.String","java.util.Date", "java.lang.Long", "java.math.BigDecimal", "java.lang.Boolean");
    public static List atributosInvalidos = Arrays.asList("id", "nombrePc", "ip", "codUsuario");

    /**
     * Invoke specific method on the target and returns the value that this method returns
     *
     * @param target
     * @param methodName
     * @return
     */
    public static Object invoke(Object target, String methodName) throws Throwable {
        Object objectToReturn = null;
        try {
            Class cls = target.getClass();
            Method method = cls.getMethod(methodName, null);
            objectToReturn = method.invoke(target, null);
        } catch (Throwable e) {
            logger.error("Cannot execute class:" + target.getClass() + "method :" + methodName);
            Throwable cause = e.getCause();
            if ((cause instanceof FlowBreakSilentlyException) ||
                    (cause instanceof AWException)) {
                throw cause;
            }
            e.printStackTrace();
            throw e;
        }
        return objectToReturn;
    }

    /**
     * @param target
     * @param methodName
     * @param parameter
     * @return
     * @throws Throwable
     */
    public static Object invoke(Object target, String methodName, Object parameter) throws Throwable {
        Object result = null;
        try {
            Class cls = target.getClass();
            Class[] paramTypes = new Class[]{Object.class};
            Method method = cls.getMethod(methodName, paramTypes);
            result = method.invoke(target, new Object[]{parameter});
        } catch (Throwable e) {
            Throwable cause = e.getCause();
            if ((cause instanceof FlowBreakSilentlyException) ||
                    (cause instanceof AWException) ||
                    (cause instanceof DataIntegrityViolationException)) {
                throw cause;
            }
            e.printStackTrace();
            throw e;

        }
        return result;
    }

    /**
     * @param target
     * @param methodName
     * @param parameter
     * @return
     * @throws Throwable
     */
    public static Object invoke(Object target, String methodName, Object parameter, Class parameterType) throws Throwable {
        Object result = null;
        try {
            Class cls = target.getClass();
            Class[] paramTypes = new Class[]{parameterType};
            Method method = cls.getMethod(methodName, paramTypes);
            result = method.invoke(target, new Object[]{parameter});
        } catch (Throwable e) {
            Throwable cause = e.getCause();
            if ((cause instanceof FlowBreakSilentlyException) ||
                    (cause instanceof AWException) ||
                    (cause instanceof DataIntegrityViolationException)) {
                throw cause;
            }
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    // invoke method with varios paremeter
    public static Object invoke(Object target, String methodName, Object[] parameters) throws Throwable {
        Object result = null;
        try {
            Class cls = target.getClass();
            Class[] paramTypes = new Class[parameters.length];
            for (int i = 0; i < paramTypes.length; i++) {
                paramTypes[i] = parameters[i].getClass();
            }
            Method method = cls.getMethod(methodName, paramTypes);
            method.setAccessible(true);
            result = method.invoke(target, parameters);
        } catch (Throwable e) {
            if (e.getCause() instanceof AWException) {
                throw ((AWException) e.getCause());
            }
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    // invoke method with varios paremeter
    public static Object invoke(Object target, Object[] methodNames) throws Throwable {
        java.util.List results = new ArrayList();
        try {
            Class cls = target.getClass();
            for (int i = 0; i < methodNames.length; i++) {
                Method method = cls.getMethod((String) methodNames[i]);
                Object obj = method.invoke(target);
                if (obj != null) {
                    results.add(obj);
                }
            }
        } catch (Throwable e) {

            if (e.getCause() instanceof AWException) {
                e.printStackTrace();
                throw ((AWException) e.getCause());
            }
            e.printStackTrace();
            throw e;
        }
        return results;
    }

    /**
     * Invoke specific method on the target and returns the value that this method returns
     *
     * @param target
     * @return
     */
    public static Object invokeMethodWithPrefix(Object target, String prefix) throws Throwable {
        List results = new ArrayList();
        try {
            Class cls = target.getClass();
            Method[] methods = cls.getMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().startsWith(prefix)) {
                    results.add(methods[i].invoke(target, null));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
        return results;
    }

    public static Object invoke(Object target, Object[] methodName, Object param) throws Throwable {
        List results = new ArrayList();
        try {
            Class cls = target.getClass();
            Class[] paramTypes = new Class[]{Object.class};
            for (int i = 0; i < methodName.length; i++) {
                Method method = cls.getMethod((String) methodName[i], paramTypes);
                Object obj = method.invoke(target, param);
                if (obj != null) {
                    results.add(obj);
                }
            }
        } catch (Throwable e) {
            if (e.getCause() instanceof AWException) {
                e.getCause().printStackTrace();
                throw (AWException) e.getCause();
            }
            e.printStackTrace();
            throw e;
        }
        return results;
    }

    public static boolean existsMethod(Object target, String method) {
        Class cls = target.getClass();
        Method[] methods = cls.getMethods();
        boolean existMethod = false;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].toString().contains(method)) {
                existMethod = true;
                break;
            }
        }
        return existMethod;


    }

    public static boolean validateIfClassImplMethod(Object target, String method) {
        String reflexionMethod = target.getClass().getName() + "." + method + "()";
        logger.info("searching method " + reflexionMethod);
        Class cls = target.getClass();
        Method[] methods = cls.getMethods();
        boolean existMethod = false;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].toString().contains(reflexionMethod)) {
                existMethod = true;
                break;
            }
        }
        return existMethod && reflexionMethod.contains("$");
    }

    public static List getAllAttributes(Object target) {
        logger.info("searching attributes " + target.getClass().getName());
        List attributes = new ArrayList();
        Class cls = target.getClass();
        Field[] fields = cls.getDeclaredFields();
        Field[] superFields = cls.getSuperclass().getDeclaredFields();
        attributes.addAll(procesarFields("",fields));
        attributes.addAll(procesarFields("",superFields));

        // analizamos si tiene domain..
      BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        try {
            Class claz = beanWrapper.getPropertyType("domain");
            if (claz != null) {
                Field[] fieldsDomain = claz.getDeclaredFields();
                Field[] superFieldsDomain = claz.getSuperclass().getDeclaredFields();
                attributes.addAll(procesarFields("domain.",fieldsDomain));
                attributes.addAll(procesarFields("domain.",superFieldsDomain));
            }
        } catch (Exception e) {
            logger.error("No tiene attributo domain");
        }
        return attributes;
    }

    private static List procesarFields(String path,Field[] fields) {
        List attributes = new ArrayList();
        for (int i = 0; i < fields.length; i++) {
            if (tiposValidos.contains(fields[i].getType().getName()) && !atributosInvalidos.contains(fields[i].getName())) {
                attributes.add(path+fields[i].getName());
            }
        }
        return attributes;
    }

    public static List getAttributes(Object target) {
        logger.info("searching attributes " + target.getClass().getName());
        List attributes = new ArrayList();
        List<Field> forms = new ArrayList();
        Class cls = target.getClass();
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            if ((fields[i].getName().startsWith("txt") || fields[i].getName().startsWith("chk")) && !fields[i].getName().startsWith("chkSel")){
                attributes.add(fields[i]);
            }
            if ((fields[i].getType().getSimpleName().startsWith("Frm"))){
                forms.add(fields[i]);
            }
        }
        if (forms.size()>0){
            for (Field field : forms) {
                try {
                    Object formToBeChecked = field.get(target);
                    if(formToBeChecked!=null){
                        List formAttributes =getAttributes(formToBeChecked);
                        if (formAttributes.size()>0){
                            attributes.addAll(formAttributes);
                        }
                    }else{
                        logger.warn("FRM NULL:"+field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new AWSystemException("Problems getting value for:<"+field.getName()+">",e);
                }
            }
        }
        return attributes;
    }

    public static Field getAttribute(Object target, String fielName) {
        Class cls = target.getClass();
        Field field = null;
        try {
            field = cls.getField(fielName);
        } catch (NoSuchFieldException e) {
            field = null;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return field;
    }

    public static Method getMethod(Object target, String methodName) throws Throwable {
        Class cls = target.getClass();
        try {
            return cls.getMethod(methodName);
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Object invoke(Object target, String methodName, Object[] parameters, Class[] parameterTypes) {
        Object result = null;
        try {
            Class cls = target.getClass();
            Class[] paramTypes = parameterTypes;
            for (int i = 0; i < paramTypes.length; i++) {
                if (parameterTypes[i] == null) {
                    paramTypes[i] = parameters[i].getClass();
                }
            }
            Method method = cls.getMethod(methodName, paramTypes);
            method.setAccessible(true);
            result = method.invoke(target, parameters);
        } catch (Throwable e) {
            if (!(e instanceof AWException)){
                if (e.getCause() instanceof AWException) {
                    throw ((AWException) e.getCause());
                }
                e.printStackTrace();
                throw new AWSystemException("Problems calling the method:"+methodName,e);               
            }else{
                throw (AWException)e;
            }
        }
        return result;
    }


}