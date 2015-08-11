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
package com.aw.core.domain;

/**
 * Usado para denotar un error en el programa. Esta excepcion escribe a un archivo o tabla especial
 * que es revisado por el desarrollador, para corregir defectos del programa.
 * Ejemplos tipicos
 * - Condiciones no esperadas (equivalente a assert)
 * - Fallos del Sistema debido a una configuracion incorrecta
 *
 *
 */
public interface AWDeveloperExceptionLogger {

    void log(AWDeveloperException timerTask);
}