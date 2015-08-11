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

import com.aw.core.domain.AWBusinessException;
import com.aw.core.util.TimeObserver;
import com.aw.swing.mvp.Presenter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: Aug 16, 2007
 */
public class ComponentManager {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * Wrapper used by default
     */
    private BeanWrapper defaultBeanWrapper;
    /**
     * This will be used in the case that the programmer use an array of object and not any bean
     */
    private Object[] arrayOfObjects;
    /**
     * List of all components that need binding managment
     */
    protected List<BindingComponent> components;
    /**
     * Presenter that used this manager
     */
    protected Presenter presenter;


    public ComponentManager(Presenter presenter) {
        this.presenter = presenter;
        this.components = new ArrayList<BindingComponent>();
    }


    /**
     * Set the values of all the input components to the related beans
     */
    public void setValuesToBean() {
        TimeObserver time = new TimeObserver("Setting values JComponent --> BEAN");
        time.empezar();
//        logger.debug("Setting values JComponent --> BEAN");
        for (int i = 0; i < components.size(); i++) {
            BindingComponent component = (BindingComponent) components.get(i);
            if (presenter.isForceBindingAllComponents() || component.isBindingUIReadOnly() || !component.isUiReadOnly()) {
                component.setValueToBean();
                if (logger.isInfoEnabled()) {
                    logger.debug("The value: <" + component.getValue() + "> was set to Bean's attribute: <" + component.getFieldName() + ">");
                }
            } else {
                if (logger.isInfoEnabled()) {
                    logger.debug("The value: <" + component.getValue() + "> was NOT set to Bean's attribute: <" + component.getFieldName() + "> (skipped)");
                }
            }
        }
        time.terminar();
    }


    /**
     * Set the values of all the input components to the related beans
     */
    public void setValueToJComponent() {
        TimeObserver time = new TimeObserver("Setting values BEAN --> JComponents");
        time.empezar();
//        logger.debug("Setting values BEAN --> JComponents");
        for (int i = 0; i < components.size(); i++) {
            BindingComponent component = (BindingComponent) components.get(i);
            if (logger.isDebugEnabled()) {
                logger.debug("Setting <" + component.getValue() + ">  to JCmp:<" + component + ">");
            }
            try {
                component.setValueToJComponent();
            } catch (Throwable e) {
                logger.error("ERROR Setting <" + component.getValue() + ">  to JCmp:<" + component + ">");
                throw AWBusinessException.wrapUnhandledException(logger, e);
            }
        }
        time.terminar();
    }


    public void setAllCmpsAsUIReadOnly() {
        for (int i = 0; i < components.size(); i++) {
            BindingComponent component = (BindingComponent) components.get(i);
            component.setAsUIReadOnly();
        }
    }

    public void setDefaultBeanWrapper(BeanWrapper beanWrapper) {
        this.defaultBeanWrapper = beanWrapper;
    }

    public BeanWrapper getDefaultBeanWrapper() {
        return defaultBeanWrapper;
    }

    /**
     * Set the default Bean Wrapper
     */
    public void setDefaultBean(Object bean) {
        this.defaultBeanWrapper = new BeanWrapperImpl(bean);
    }

    public Object[] getArrayOfObjects() {
        return arrayOfObjects;
    }

    public void setArrayOfObjects(Object[] arrayOfObjects) {
        this.arrayOfObjects = arrayOfObjects;
    }

    /**
     * Get all of the Bnd Components managed by this class
     *
     * @return
     */
    public List<BindingComponent> getComponents() {
        return components;
    }

    /**
     * Get all JComponents managed by this class
     *
     * @return
     */
    public List<JComponent> getJComponents() {
        List<JComponent> jComponents = new ArrayList<JComponent>();
        for (int i = 0; i < components.size(); i++) {
            BindingComponent bindingComponent = components.get(i);
            jComponents.add(bindingComponent.getJComponent());
        }
        return jComponents;
    }

    public void setComponents(List components) {
        this.components = components;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
