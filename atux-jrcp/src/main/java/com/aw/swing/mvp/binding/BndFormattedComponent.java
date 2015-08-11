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
package com.aw.swing.mvp.binding;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.context.AWBaseContext;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;
import com.aw.core.format.NullDecimalFormat;
import com.aw.core.format.NumberRange;
import com.aw.support.beans.BeanUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Date: Aug 16, 2007
 */
public abstract class BndFormattedComponent<JCmp extends JComponent> extends BndInputComponent<JCmp> {

    protected java.util.List dropDownValues = new ArrayList();
    /**
     * Used to transform and format bean attribute value to/from JTextFielt text
     */
    protected Formatter formatter = null;

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent) {
        super(inputComponentManager, jInputComponent);
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, List dropDownValues, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
        setDropDownValues(dropDownValues);
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, MetaLoader metaLoader, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
        setDropDownValues(metaLoader.getRows());
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, List dropDownValues, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
        setDropDownValues(dropDownValues);
    }

    public BndFormattedComponent(ComponentManager inputComponentManager, JCmp jInputComponent, MetaLoader metaLoader, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
        setDropDownValues(metaLoader.getRows());
    }

    /**
     * Set the value of the bean to the JInputComponent
     */
    protected void setValueToJComponentInternal() {
        Object beanValue = getValueToBeSetToJComponent();
        String text = getFormattedValue(beanValue);
        setFormattedTextToJComponent(text);
    }

    public void setValue(Object value) {
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + value);
        getBeanWrapper().setPropertyValue(fieldName,value);
        String text = getFormattedValue(value);
        setFormattedTextToJComponent(text);
    }

    /**
     * @return Value from the Bean (without format)
     * @see #getValue()  to inverse operation
     */
    public Object getBeanValue() {
        return getValueToBeSetToJComponent();
    }

    /**
     * Get the
     *
     * @return Value to be copied to the JComponent
     */
    protected Object getValueToBeSetToJComponent() {
        Object beanValue = null;
        Object[] objects;
        if ((componentManager != null) && (objects = componentManager.getArrayOfObjects()) != null) {
            // If there is not any bean and exists an array of objects the fieldName will cointain the index of the value
            beanValue = objects[Integer.parseInt(fieldName)];
        } else {
            // check inner attribute is not null
            try {
                beanValue = BeanUtils.getPropertyValueNullSafe(getBeanWrapper(), fieldName);
            } catch (Throwable e) {
                logger.error("Problems reading property:"+fieldName,e);
                getJComponent().setBackground(Color.BLUE);
                JOptionPane.showMessageDialog(new Frame(), "El Componente no tiene propertyName válido. No es posible acceder al campo:<" + fieldName +
                        "> dentro de la Clase:"+getBeanWrapper().getWrappedClass().getName()+" Ver componente resaltado en azul.");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + beanValue);
        }
        return beanValue;
    }


    abstract protected void setFormattedTextToJComponent(String formattedText);

    /**
     * Get the value that contains the input jComponent
     *
     * @return value formatted
     */
    protected String getFormattedValue(Object beanValue) {
        if (beanValue == null) return null;
        if (formatter != null) return (String) formatter.format(beanValue);
        if (beanValue instanceof String) return (String) beanValue;

        if (propertyBinding.getTrim()!=null&& propertyBinding.getTrim().booleanValue() && beanValue!=null)
            beanValue = beanValue.toString().trim();

        logger.warn("[" + fieldName + "] cannot cast " + beanValue.getClass() + " to String, consider using a formatter, assumming toString()");
        return beanValue.toString();
    }

    /////////////////////////   Format & Transformation Methods  /////////////////////////

    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * Indicate that this control must accept only date values (and format in this fashion)
     *
     * @return this instance
     */
    protected BndFormattedComponent formatAsDateIntern() {
        this.setLimitTextSizeIntern(AWBaseContext.instance().getConfigInfoProvider().getDateFormatLength());
        return formatUsingCustomFormatterIntern(DateFormatter.DATE_FORMATTER);
    }

    /**
     * Indicate that this control must accept only date values (and format in this fashion)
     *
     * @return this instance
     */
    protected BndFormattedComponent formatAsTimeIntern() {
        this.setLimitTextSizeIntern(AWBaseContext.instance().getConfigInfoProvider().getTimeFormatLength());
        return formatUsingCustomFormatterIntern(DateFormatter.TIME_FORMATTER);
    }
    /**
     * Indicate that this control must accept only date values (and format in this fashion)
     *
     * @return this instance
     */
    protected BndFormattedComponent formatAsDateTimeIntern() {
        this.setLimitTextSizeIntern(AWBaseContext.instance().getConfigInfoProvider().getDateTimeFormatLength());
        return formatUsingCustomFormatterIntern(DateFormatter.DATE_TIME_FORMATTER);
    }


    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    protected BndFormattedComponent registerAsPercentageFormattedIntern(Class modelType, String format) {
        NumberRange numberRange;
        if (modelType == BigDecimal.class)
            numberRange = new NumberRange(new BigDecimal((double) 0), new BigDecimal((double) 100));
        else
            numberRange = new NumberRange(new Float(0), new Float(100));
        numberRange.setOnInvalidRangeAdjust(true);
        Formatter decimalformatter = new NullDecimalFormat(format, numberRange);//"#0.00"
        return formatUsingCustomFormatterIntern(decimalformatter);

    }

    protected BndFormattedComponent registerAsNumberFormattedIntern(Class clazz, Comparable minimun, Comparable maximun, String format) {
        NullDecimalFormat numberFormatter = new NullDecimalFormat(format, new NumberRange(minimun, maximun));
        if (clazz != null) numberFormatter.setReturnClassType(clazz);
        // calculate lenght
        int lenght = -1;
        if (minimun != null) lenght = ((String) numberFormatter.format(minimun)).length();
        if (maximun != null) lenght = Math.max(lenght, ((String) numberFormatter.format(maximun)).length());
        if (lenght < format.length())
            lenght = format.length();
        if (lenght != -1) this.setLimitTextSizeIntern(lenght);

        //register the format
        return formatUsingCustomFormatterIntern(numberFormatter);
    }

    /**
     * Register the specified formatter fo this control
     *
     * @param customformatter
     */
    protected BndFormattedComponent formatUsingCustomFormatterIntern(Formatter customformatter) {
        propertyBinding.setFormatter(customformatter);
        if (customformatter instanceof DropDownFormatter)
            propertyBinding.setHorizontalAligement(JTextField.LEFT);
        else
           propertyBinding.setHorizontalAligement(JTextField.RIGHT);
        return this;
    }

    protected void setLimitTextSizeIntern(int lenght) {

    }

    protected void formatComponentText() {
        if (formatter != null) {
            String textFormatted = "";
            try {
                Object value = getValue();
                textFormatted = getFormattedValue(value);
            } catch (Exception e) {
                // if exist format errors the the value will be reset to "" 
            }
            ((JTextComponent) jComponent).setText(textFormatted);
        }
    }

    protected Object getValueDrowList(String text) {
        for (Iterator iterator = dropDownValues.iterator(); iterator.hasNext();) {
            DropDownRow dropDownRow = (DropDownRow) iterator.next();
            if (dropDownRow.getValue().toString().equals(text)) {
                return dropDownRow.getLabel();
            }
        }
        logger.debug("JLabel[" + text + "] cannot found in dropDownValues");
        return null;
    }

    protected Object getKeyFromDrowList(String text) {
        for (Iterator iterator = dropDownValues.iterator(); iterator.hasNext();) {
            DropDownRow dropDownRow = (DropDownRow) iterator.next();
            if (dropDownRow.getLabel().toString().equals(text)) {
                return dropDownRow.getValue();
            }
        }
        logger.debug("JLabel[" + text + "] cannot found in dropDownValues");
        return null;
    }

    public void setDropDownValues(List dropDownValues) {
        this.dropDownValues = dropDownValues;
    }
}
