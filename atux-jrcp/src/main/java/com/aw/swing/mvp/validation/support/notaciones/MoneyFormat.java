package com.aw.swing.mvp.validation.support.notaciones;

/**
 * User: User
 * Date: Nov 28, 2007
 */
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MoneyFormat {
    String value() default "";
}
