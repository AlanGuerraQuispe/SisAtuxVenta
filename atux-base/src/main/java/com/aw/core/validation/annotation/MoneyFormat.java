package com.aw.core.validation.annotation;

/**
 * User: User
 * Date: Nov 28, 2007
 */
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MoneyFormat {
    String value() default "";
}
