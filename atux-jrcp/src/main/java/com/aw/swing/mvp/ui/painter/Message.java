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
package com.aw.swing.mvp.ui.painter;

import java.util.Arrays;

/**
 * Date: Oct 1, 2007
 */
public class Message {
    private String message;
    private Object[] args;

    public Message(String message, Object[] args) {
        this.args = args;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("com.aw.swing.ui.painter.Message");
        sb.append("{message='").append(message).append('\'');
        sb.append(", args=").append(args == null ? "null" : Arrays.asList(args).toString());
        sb.append('}');
        return sb.toString();
    }
}
