package com.aw.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 23-sep-2008
 */
public class AnnotationUtils {

    /**
     * @param obj : Object to be scanned in order to search its annotated fields
     * @param annotationType : Type of the annotation that will be searched
     * @return
     */
    public static List<Field> getAnnotatedFieldsFrom(Object obj,final Class<? extends Annotation> annotationType){
        List<Field> annotatedFields = new ArrayList();
        boolean isInnerClass = obj.getClass().getName().contains("$");
        Class clazz = obj.getClass();
        if (isInnerClass){
            clazz = obj.getClass().getSuperclass();                        
        }
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            if (field.getAnnotation(annotationType) !=null){
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }
}
