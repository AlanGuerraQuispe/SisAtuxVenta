package com.aw.core.util.annotation;

import java.lang.annotation.*;

/**
 * User: gmc
 * Date: 29/04/2009
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteableListField {
    /**
     * FieldName: field that has the deleteableList
     */
    String value() default "";
}

