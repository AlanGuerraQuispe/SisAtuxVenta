package com.aw.support.reflection;

import java.lang.reflect.Method;

/**
 * User: gmc
 * Date: 25/06/2009
 */
public class ReflectionUtils {

    public static  Method findMethodByName(Class clazz,String methodName){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }
}
