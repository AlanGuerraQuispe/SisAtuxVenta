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
package com.aw.swing.mvp.binding.component;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.context.AWBaseContext;
import com.aw.core.format.Formatter;
import com.aw.swing.mvp.binding.BndFormattedComponent;
import com.aw.swing.mvp.binding.ComponentManager;

import javax.swing.*;
import java.util.List;

/**
 * Date: Aug 17, 2007
 */
public class BndIJLabelField extends BndFormattedComponent {


    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
    }
    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent) {
        super(inputComponentManager, jInputComponent);
    }

    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, List values, String fieldName) {
        super(inputComponentManager, jInputComponent, values, fieldName);
    }

    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, MetaLoader metaLoader, String fieldName) {
        super(inputComponentManager, jInputComponent, metaLoader, fieldName);
    }

    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
    }

    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, Object bean, List values, String fieldName) {
        super(inputComponentManager, jInputComponent, values, bean, fieldName);

    }

    public BndIJLabelField(ComponentManager inputComponentManager, JLabel jInputComponent, Object bean, MetaLoader metaLoader, String fieldName) {
        super(inputComponentManager, jInputComponent, metaLoader, bean, fieldName);
    }

    /////////////////////////   InputComponent Binding Methods  /////////////////////////
    protected void setFormattedTextToJComponent(String formattedText) {
        JLabel field = (JLabel) jComponent;

        if (dropDownValues.size() > 0) {
            formattedText = (String) getValueDrowList(formattedText);
        }
        field.setText(formattedText);
    }

    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public void cleanJComponentValues() {
        JLabel field = (JLabel) jComponent;
        field.setText(null);
    }

    /**
     * Get the value that contains the input jComponent
     *
     * @return value that contains the input jComponent
     */
    public Object getValue() {
        String text = ((JLabel) jComponent).getText();
        Object value = null;
//        try {
            if (dropDownValues.size() == 0) {
                value = formatter != null ? formatter.parseObject(text) : text;
            } else {
                value = getKeyFromDrowList(text);
            }
//        } catch (ParseException e) {
//            logger.debug("JLabel[" + this.getFieldName() + "] cannot be parsed to '" + text + "'. Error:" + e.getMessage());
//        }
        return value;
    }


    /////////////////////////   Format & Transformation Methods  /////////////////////////
    /**
     * Indicate that this control must accept only date values (and format in this fashion)
     *
     * @return this instance
     */
    public BndIJLabelField registerAsDateFormatted() {
        return (BndIJLabelField) formatAsDateIntern();
    }

    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    public BndIJLabelField registerAsPercentageFormatted() {
        return (BndIJLabelField) registerAsPercentageFormattedIntern(Float.class, AWBaseContext.instance().getConfigInfoProvider().getPercentFormat());
    }


    /**
     * Register the specified formatter fo this control
     *
     * @param customformatter
     */
    public BndIJLabelField registerAsCustomFormatted(Formatter customformatter) {
        return (BndIJLabelField) formatUsingCustomFormatterIntern(customformatter);
    }

    public void initComponent() {
        super.initComponent();
        uiReadOnly = true;
    }


}
