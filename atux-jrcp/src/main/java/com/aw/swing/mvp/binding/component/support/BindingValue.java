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
package com.aw.swing.mvp.binding.component.support;


import com.aw.support.beans.Attribute;
import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * Date: Oct 3, 2007
 */
public class BindingValue {
    private static final Log logger = LogFactory.getLog(BindingValue.class);

    public static Object getValue(Object object) {
        if (object instanceof JComponent) {
            logger.debug(" parameter is JComponent");
            BindingComponent bindingComponent = (BindingComponent) ((JComponent) object).getClientProperty(BindingComponent.ATTR_BND);
//            bindingComponent.setValueToBean();
            if (bindingComponent != null) {
                return bindingComponent.getValue();
            } else {
                return null;
            }
        } else if (object instanceof Attribute) {
            logger.debug(" parameter is Attribute");
            return ((Attribute) object).getValue();
        }
        return object;
    }
}
