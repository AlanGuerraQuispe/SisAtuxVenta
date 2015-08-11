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
package com.aw.swing.mvp.cmp.pick;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Date: Sep 19, 2007
 */
public class PickImpl implements Pick {

    protected Presenter presenter;
    protected final Log logger = LogFactory.getLog(getClass());
    private Class targetPstClass;
    private boolean refreshGridAtEnd = false;
    private Map<String, String> parameters = new HashMap();
    private Map<String, Object> parameterValues = new HashMap();
    private Map<String, PickParamProvider> parameterProviders = new HashMap();
    private Map<String, String> binding = new HashMap();
    PickListener listener;
    //jcv porque no funcaba con true
    private boolean pickFilled = false;

    public PickImpl(Class targetPstClass) {
        this.targetPstClass = targetPstClass;
    }


    public Pick addListener(PickListener pickListener) {
        listener = pickListener;
        return this;
    }

    /**
     * Register parameter to be sent to the pick
     *
     * @param fromParam Attribute of the current controller's domain
     * @param toParam   Attribute of the pick controller's domain
     * @return
     */
    public Pick registerParam(String fromParam, String toParam) {
        parameters.put(fromParam, toParam);
        return this;
    }

    public Pick registerParamValue(Object value, String toParam) {
        parameterValues.put(toParam, value);
        return this;
    }

    public Pick registerParamProvider(PickParamProvider pickParamProvider, String toParam) {
        parameterProviders.put(toParam, pickParamProvider);
        return this;
    }

    /**
     * Register parameter to be sent to the pick
     *
     * @param toField   Attribute of the current controller's domain
     * @param fromField Attribute of the selected row from pick controller
     * @return
     */
    public Pick registerBind(String toField, String fromField) {
        binding.put(toField, fromField);
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Map<String, Object> getParameterValues() {
        return parameterValues;
    }


    public Map<String, String> getBinding() {
        return binding;
    }


    public Map<String, PickParamProvider> getParameterProviders() {
        return parameterProviders;
    }

    public PickListener getListener() {
        return listener;
    }

    public Pick refreshGridAtEnd() {
        refreshGridAtEnd = true;
        return this;
    }

    public void cleanPickedValues() {
        pickFilled = false;
        // todo : ver como arreglar esto
        Object backBean = presenter.getBackBean();
        BeanWrapper bw = new BeanWrapperImpl(backBean);
        Iterator<String> iterator = binding.keySet().iterator();
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            if (!contains(fieldName,mainAttribute)){
                bw.setPropertyValue(fieldName, null);
                BindingComponent bnd = presenter.getBindingMgr().getBindingComponent(fieldName);
                if (bnd != null){
                    bnd.setValueToJComponent();
                }
            }
        }
    }

    private boolean contains(String fieldNameContainer, String toCheck) {
        return toCheck.equals(fieldNameContainer) || (fieldNameContainer.endsWith(toCheck));
    }

    public boolean isRefreshGridAtEnd() {
        return refreshGridAtEnd;
    }

    public Class getTargetPstClass() {
        return targetPstClass;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    private String mainAttribute;

    public Pick setMainAttribute(String attrName) {
        mainAttribute = attrName;
        return this;
    }

    public boolean isPickFilled() {
        return pickFilled;
    }

    public void setPickFilled(boolean pickFilled) {
        this.pickFilled = pickFilled;
    }
}
