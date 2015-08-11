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

import com.aw.core.spring.ApplicationBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Usado para denotar un error en el programa. Esta excepcion escribe a un archivo o tabla especial
 * que es revisado por el desarrollador, para corregir defectos del programa.
 * Ejemplos tipicos
 * - Condiciones no esperadas (equivalente a assert)
 * - Fallos del Sistema debido a una configuracion incorrecta
 *
 *
 */
public class AWDeveloperException extends AWBusinessException {
    protected Object beanReference;
    protected Map<String, Object> map = new HashMap<String, Object>(2);

    /////////////////////////////////////////
    public AWDeveloperException(String message, Throwable cause) {
        super(message, cause);
        queueLog();
    }

    public AWDeveloperException(String message) {
        super(message);
        queueLog();
    }
    public AWDeveloperException(AWBusinessException ex) {
        super(ex.getMessage(), ex.getCause());
        queueLog();
    }

    private void queueLog() {
        final AWDeveloperExceptionLogger logger= (AWDeveloperExceptionLogger) ApplicationBase.instance().getBean("developerExceptionLogger", null);
        if (logger==null)
            return;
        int retardo = 3000;
        Timer timer = new Timer("AWDeveloperException");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.log(AWDeveloperException.this);
            }
        }, retardo);
    }
    /////////////////////////////////////////


    public AWDeveloperException setBeanReference(Object bean) {
        this.beanReference = bean;
        return this;
    }
    public AWDeveloperException setProperty(String name,  Object value) {
        map.put(name, value);
        return this;
    }
}