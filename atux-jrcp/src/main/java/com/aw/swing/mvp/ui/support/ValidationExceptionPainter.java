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

import com.aw.support.IKeyArgs;
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
public class ValidationExceptionPainter implements ExceptionPainter {
    public List getBindingComponents(Throwable exception) {
        List bindingComponents = new ArrayList();
        AWValidationException validationException = (AWValidationException) exception;
        if (validationException.getTypeException() == AWValidationException.EXCEPTION_WITH_LIST_OBJECTS) {
            return getBindingComponentFromList(validationException);
        } else if (validationException.getTypeException() == AWValidationException.EXCEPTION_UNIQUE) {
            return validationException.getBindingComponents();
        }
        return bindingComponents;
    }

    public List getMessagesError(Throwable exception) {
        List messages = new ArrayList();
        AWValidationException validationException = (AWValidationException) exception;
        if (validationException.getTypeException() == AWValidationException.EXCEPTION_WITH_LIST_OBJECTS) {
            return getMessageFronList(validationException);
        } else if (validationException.getTypeException() == AWValidationException.EXCEPTION_UNIQUE) {
            Message message = new Message(validationException.getMessage(), getKeyArgsForMessage(validationException));
            messages.add(message);
            return messages;
        }
        return messages;
    }

    private Object[] getKeyArgsForMessage(IKeyArgs paramKeyArgs) {
        List components = paramKeyArgs.getBindingComponents();
        List args = new ArrayList();

        for (Iterator iterator = components.iterator(); iterator.hasNext();) {
            BindingComponent bindingComponent = (BindingComponent) iterator.next();
            args.add(LabelResolver.getLabelForMsgs(bindingComponent));
        }

        if (paramKeyArgs.getArgs() != null)
            return ListUtils.merge(args.toArray(), paramKeyArgs.getArgs());
        else
            return args.toArray();
    }


    private List getBindingComponentFromList(AWValidationException validationException) {
        List bindingComponents = new ArrayList();
        if (validationException.getListObject() != null) {
            for (Iterator iterator = validationException.getListObject().iterator(); iterator.hasNext();) {
                IKeyArgs iKeyArgs = (IKeyArgs) iterator.next();
                if (iKeyArgs.getBindingComponents() != null) {
                    bindingComponents.addAll(iKeyArgs.getBindingComponents());
                }
            }
        }
        return bindingComponents;
    }

    private List getMessageFronList(AWValidationException validationException) {
        List messages = new ArrayList();
        if (validationException.getListObject() != null) {
            for (Iterator iterator = validationException.getListObject().iterator(); iterator.hasNext();) {
                IKeyArgs iKeyArgs = (IKeyArgs) iterator.next();
                Message message = new Message(iKeyArgs.getMessage(), getKeyArgsForMessage(iKeyArgs));
                messages.add(message);
            }
        }
        return messages;
    }
}
