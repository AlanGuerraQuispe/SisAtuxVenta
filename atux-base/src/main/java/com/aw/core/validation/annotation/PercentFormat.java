package com.aw.core.validation.annotation;

/**
 * User: User
 * Date: Dec 10, 2007
 */
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface PercentFormat {
    String value() default "";
}
