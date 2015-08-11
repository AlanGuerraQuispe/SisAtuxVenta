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

import com.aw.core.domain.AWBusinessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: Oct 1, 2007
 */
public class BusinessExceptionPainter implements ExceptionPainter {

    public List getBindingComponents(Throwable exception) {
        return new ArrayList();
    }

    public List getMessagesError(Throwable exception) {
        AWBusinessException awBusinessException = (AWBusinessException) exception;
        awBusinessException.getMessage();
        return Arrays.asList(new Object[]{awBusinessException.getMessage()});
    }
}
