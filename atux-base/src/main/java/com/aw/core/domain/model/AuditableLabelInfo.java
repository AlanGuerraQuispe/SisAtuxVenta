package com.aw.core.domain.model;

/**
 * Representa valores de campos de auditoria
 * User: Juan Carlos Vergara
 * Date: 05/06/2009
 */
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AuditableLabelInfo {
    String codigoLabel() default "Código: ";
}