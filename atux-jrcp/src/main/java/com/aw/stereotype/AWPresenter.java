package com.aw.stereotype;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * User: gmc
 * Date: 22/04/2009
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public @interface AWPresenter {
    /** Title that will be used in the page*/
    String title() default "Title";
    /** Points out if this controller will use the security component or not*/
    boolean secure() default true;
}