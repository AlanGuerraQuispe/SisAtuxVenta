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

import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.List;

/**
 * Date: Aug 16, 2007
 */
public abstract class ErrorPainter {
    protected final Log logger = LogFactory.getLog(getClass());

    public abstract void paintError(List components);

    public abstract void clearError(JComponent jComponent);

    public void clearError(List components) {
        for (int i = 0; i < components.size(); i++) {
            BindingComponent bindingComponent = (BindingComponent) components.get(i);
            clearError(bindingComponent.getJComponent());
        }
    }

}
