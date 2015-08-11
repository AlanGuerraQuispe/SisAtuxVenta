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
package com.aw.support.beans;

import com.aw.support.ObjectConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Class used to represent a bean attribute
 *
 * @author gmateo
 *         05/11/2004
 */
public class Attribute {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * Object to which belongs this attribute
     */
    protected BeanWrapper sourceObject;
    /**
     * Attribute that is represented by this class
     */
    protected String attribute;

    public Attribute(Class sourceClass, String attribute) {
        this.sourceObject = new BeanWrapperImpl(sourceClass);
        this.attribute = attribute;
    }

    public Attribute(BeanWrapper sourceObject, String attribute) {
        this.sourceObject = sourceObject;
        this.attribute = attribute;
    }

    public Attribute(Object sourceObject, String attribute) {
        this.sourceObject = new BeanWrapperImpl(sourceObject);
        this.attribute = attribute;
    }


    /**
     * Get the value of this attribute
     *
     * @return
     */
    public Object getValue() {
        return sourceObject.getPropertyValue(attribute);
    }

    /**
     * Set the value for this attribute
     *
     * @param value
     */
    public void setValue(Object value) {
        Class claz = sourceObject.getPropertyType(attribute);
        if (claz== null)
            throw new IllegalArgumentException("Atribute "+attribute+" does not exist in class "+sourceObject.getWrappedClass()+". Cannot set value:"+value);
        value = ObjectConverter.convert(value, claz);
        sourceObject.setPropertyValue(attribute, value);
//        if((sourceObject.getWrappedInstance() instanceof BindingComponent) && !(sourceObject.getWrappedInstance() instanceof BndIJComboBox)){
//            logger.info("Setting bindingComponent ...");
//
//            ((BindingComponent)sourceObject.getWrappedInstance()).setValueToJComponent();
//        }
    }

    public void setWrappedInstance(Object instance) {
        sourceObject= new BeanWrapperImpl(instance);
    }

    public Object getWrappedInstance() {
        return sourceObject.getWrappedInstance();
    }

    public String toString() {
        return "attribute:"+attribute;
    }
}
