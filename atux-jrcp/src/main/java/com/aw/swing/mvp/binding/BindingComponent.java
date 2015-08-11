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

import com.aw.core.exception.AWSystemException;
import com.aw.core.format.DateFormatter;
import com.aw.support.ObjectConverter;
import com.aw.swing.mvp.binding.component.JComponentDecorator;
import com.aw.swing.mvp.binding.component.support.BndFocusAdapter;
import com.aw.swing.mvp.binding.component.support.PropertyBinding;
import com.aw.swing.mvp.validation.Validator;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Date: Aug 16, 2007
 */
public abstract class BindingComponent<JCmp extends JComponent> {

    protected final Log logger = LogFactory.getLog(getClass());
    public static final String ATTR_BND = "BND";
    public static final String ATTR_LINKED_BUTTON = "LINKED_BUTTON";
    public static final String ATTR_BND_BOOLEAN = "BND_BOOLEAN";
    public static final String ATTR_ICONO = "ICONO";
    public static final String ATTR_EXECUTE_PICK = "EXECUTE_PICK";
    public static final String ATTR_ACTION = "ACTION";
    public static final String ATTR_EXECUTING_PICK_ACTION = "EXECUTING_PICK_ACTION";


    /**
     * Jcomponent used to show the data
     */
    protected JCmp jComponent;
    /**
     * Name of the field which data is shown by the visual jComponent
     */
    protected String fieldName;
    /**
     * It is used to set and get the values to the bean
     */
    private BeanWrapper beanWrapper;
    /**
     * Component manager that holds this jComponent
     */
    protected ComponentManager componentManager;

    protected boolean uiReadOnly = false;

    protected boolean uiReadOnlyChangeColor = true;
    /**
     * Flag used to force the binding of UI readonly components.
     */
    protected boolean bindingUIReadOnly = false;

    protected Validator validator;

    /**
     * property binding
     */
    protected PropertyBinding propertyBinding = new PropertyBinding();

    /**
     * Contains the label that will be used in the error cases
     */
    protected String labelToBeUsedOnError;

    /**
     * Constructor used when it will be used specific values and not attributes of any bean
     *
     * @param jComponent
     */
    public BindingComponent(JCmp jComponent) {
        this.jComponent = jComponent;
    }

    private BindingComponent(JCmp jComponent, String fieldName) {
        this(jComponent);
        this.fieldName = fieldName;
        if ("".equals(jComponent.getName()) || jComponent.getName() == null){
            jComponent.setName(getFieldNameToBeUsed(fieldName));
        }
    }

    /**
     * Constructor used when it will be used the default beanWrapper of the ComponentManager
     *
     * @param componentManager
     * @param jComponent
     * @param fieldName
     */
    protected BindingComponent(ComponentManager componentManager, JCmp jComponent, String fieldName) {
        this(jComponent, fieldName);
        this.componentManager = componentManager;
    }

    protected BindingComponent(ComponentManager componentManager, JCmp jComponent) {
        this(jComponent);
        this.componentManager = componentManager;
    }

    /**
     * Constructor used when it will be used specific beanWrapper
     *
     * @param jComponent
     * @param bean
     * @param fieldName
     */
    protected BindingComponent(ComponentManager inputComponentManager, JCmp jComponent, Object bean, String fieldName) {
        this(inputComponentManager, jComponent, fieldName);
        beanWrapper = new BeanWrapperImpl(bean);
    }

    private static String getFieldNameToBeUsed(String fieldName) {
        if (fieldName.indexOf(".") != -1) {
            return fieldName.substring(fieldName.lastIndexOf(".") + 1, fieldName.length());
        }
        return fieldName;
    }

    protected boolean initialized = false;    
    /**
     * Init the input jComponent.
     */
    public void init() {
        if (logger.isDebugEnabled()) {
            logger.debug("[" + this.fieldName + "]Initializing :<" + this + ">");
        }
        updateUI();
        initComponent();
        initialized = true;
    }


    /**
     * Initialize the jComponent
     */
    public void initComponent() {
    }

    /**
     * Set the value of the bean to the JComponent
     */
    public final void setValueToJComponent() {
        if (SwingUtilities.isEventDispatchThread()){
            setValueToJComponentInternal();
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        setValueToJComponentInternal();                        
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
                throw new AWSystemException("Problems setting values to jcompoent:"+fieldName,e);
            }
        }

    }

    protected void setValueToJComponentInternal(){
        throw new UnsupportedOperationException();
    }
    /**
     * Set the value of the JComponent to the bean
     * This is called based on {@link #isUiReadOnly()} and
     * {@link #isBindingUIReadOnly()} ()}
     */
    public void setValue(Object value) {

    }

    public void setValueToBean() {
    }

    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public abstract void cleanJComponentValues();

    /**
     * Get the value that contains the input jComponent
     *
     * @return value that contains the input jComponent
     */
    public Object getValue() {
        return null;
    }

    /**
     * @return Value from the Bean (without format)
     * @see #getValue()  to inverse operation
     */
    public Object getBeanValue() {
        throw new UnsupportedOperationException();
    }
    /**
     * Set the component as ReadOnly
     *
     * @return
     */
    public BindingComponent setAsUIReadOnly() {
        return setUIReadOnly(true);
    }

    /**
     * Set if the component will be readonly or not.
     * This method also set the properties of the JComponent in order to comply the value set
     * as parameter.
     *
     * @param uiReadOnly
     * @return
     */
    public BindingComponent setUIReadOnly(boolean uiReadOnly) {
        this.uiReadOnly = uiReadOnly;
        if(initialized){
            updateUI();
        }
        return this;
    }

    public BindingComponent setUIReadOnly(boolean uiReadOnly,boolean uiReadOnlyChangeColor) {
        this.uiReadOnly = uiReadOnly;
        this.uiReadOnlyChangeColor= uiReadOnlyChangeColor;
        if(initialized){
            updateUI();
        }
        return this;
    }

    protected void updateUI(){
        if(!initialized && !uiReadOnly){
            return;
        }
        if (SwingUtilities.isEventDispatchThread()){
            updateUIInternal();
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        updateUIInternal();
                    }
                });
            } catch (Throwable e) {
                throw new AWSystemException("Problems updating the UI:"+this,e);  
            }

        }
    }

    private void updateUIInternal() {
        JComponentDecorator.setUIReadOnly(jComponent,uiReadOnly,uiReadOnlyChangeColor);
        JButton linkedButton = (JButton) jComponent.getClientProperty(ATTR_LINKED_BUTTON);
        if (linkedButton!=null){
            linkedButton.setEnabled(!uiReadOnly);
        }
    }

    public BindingComponent setVisible(boolean visible) {
        jComponent.setVisible(visible);
        return this;
    }

    public JCmp getJComponent() {
        return jComponent;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Get the default BeanWrapper of the InputComponentManager if the beanWrapper is null.
     *
     * @return default BeanWrapper of the InputComponentManager
     */
    public BeanWrapper getBeanWrapper() {
        BeanWrapper bw = beanWrapper;
        if (beanWrapper == null && componentManager != null) {
            bw = componentManager.getDefaultBeanWrapper();
        }
        if (bw != null) {
            bw.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
            bw.registerCustomEditor(Date.class, new CustomDateEditor(DateFormatter.DATE_FORMATTER.getDateFormat(), true));
        }
        return bw;
    }

    public void setBeanWrapper(Object obj){
        beanWrapper= new BeanWrapperImpl(obj);
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    public boolean isUiReadOnly() {
        return uiReadOnly;
    }

    public boolean isBindingUIReadOnly() {
        return bindingUIReadOnly;
    }

    public BindingComponent setBindingUIReadOnly(boolean bindingUIReadOnly) {
        this.bindingUIReadOnly = bindingUIReadOnly;
        return this;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public PropertyValidator getPropertyValidator() {
        return this.validator.getPropertyValidator();
    }

    public String getLabelToBeUsedOnError() {
        return labelToBeUsedOnError;
    }

    public BindingComponent setLabelToBeUsedOnError(String labelToBeUsedOnError) {
        this.labelToBeUsedOnError = labelToBeUsedOnError;
        return this;
    }

    /**
     * Refresh the content of the JComponent with the value that is sent as parameter
     */
    public void refresh(Object value) {
        throw new IllegalStateException("[" + this.fieldName + "]This method is not supported by " + this);
    }

    public String toString() {
        return "Component for: <" + fieldName + "> : <" + this.getClass().getName() + "> Validator <" + validator + ">";
    }

    public boolean hasDefaultValidator() {
        return validator == null || validator.hasDefaultValidator();
    }

    public boolean isFocusable() {
        if (!isUiReadOnly()) {
            JComponent jComponent = getJComponent();
            return (jComponent.isVisible() && jComponent.isEnabled() && !(jComponent instanceof JLabel));
        }
        return false;
    }

    public PropertyBinding getPropertyBinding() {
        return propertyBinding;
    }

    public void setPropertyBinding(PropertyBinding propertyBinding) {
        this.propertyBinding = propertyBinding;
    }

    public BindingComponent addFocusListener(BndFocusAdapter bndFocusAdapter) {
        bndFocusAdapter.setBndCmp(this);
        getJComponent().addFocusListener(bndFocusAdapter);
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BindingComponent that = (BindingComponent) o;

        if (!jComponent.equals(that.jComponent)) return false;

        return true;
    }

    public int hashCode() {
        return jComponent.hashCode();
    }

    public boolean isSync() {
        Object uiValue = getValue();
        Object beanValue = getBeanValue();
        if (beanValue !=null && uiValue!=null && beanValue.getClass() != uiValue.getClass() ){
            uiValue = ObjectConverter.convert(uiValue, beanValue.getClass());
            uiValue = ObjectConverter.convert(uiValue, beanValue);
        }
        if (uiValue==null && beanValue==null) return true;
        else if (uiValue!=null ) return uiValue.equals(beanValue);        
        else                     return beanValue.equals(uiValue);
    }

}
