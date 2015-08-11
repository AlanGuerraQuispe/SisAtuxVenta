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
package com.aw.swing.mvp.ui.support;

import com.aw.support.collection.ListUtils;
import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.ui.common.LabelResolver;
import com.aw.swing.mvp.ui.painter.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Oct 1, 2007
 */
public class PaintValidationException implements PaintException {
    public List getBindingComponents(Throwable exception) {
        AWValidationException validationException = (AWValidationException) exception;
        return validationException.getBindingComponents();
    }

    public List getMessagesError(Throwable exception) {
        List messages = new ArrayList();
        AWValidationException validationException = (AWValidationException) exception;

        Message message = new Message(validationException.getMessage(), getKeyArgs(validationException));
        messages.add(message);

        return messages;
    }

    private Object[] getKeyArgs(AWValidationException validationException) {
        List components = validationException.getBindingComponents();
        List args = new ArrayList();

        for (Iterator iterator = components.iterator(); iterator.hasNext();) {
            BindingComponent bindingComponent = (BindingComponent) iterator.next();
            args.add(LabelResolver.getLabelForMsgs(bindingComponent));
        }

        return ListUtils.merge(args.toArray(), validationException.getArgs());
    }
}
