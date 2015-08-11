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

import java.util.Map;

/**
 * User: Kaiser
 */
public interface Pick {

    public Pick registerParam(String fromParam, String toParam);

    public Pick registerParamValue(Object value, String toParam);

    public Pick registerParamProvider(PickParamProvider pickParamProvider, String toParam);

    public Pick registerBind(String toField, String fromField);

    public Map<String, String> getParameters();

    public Map<String, Object> getParameterValues();

//    public Map<String, PickParamProvider> getParameterProviders();

    public Map<String, String> getBinding();

//    public String getView();
    public Class getTargetPstClass();

    public Pick addListener(PickListener pickListener);

    public PickListener getListener();

    public boolean isRefreshGridAtEnd();

    public Pick refreshGridAtEnd();
//
//
//    public void onSelectRow(E selectedRow);
//
//    public void onCancel();
//
//    public List getValues();
//
//    public E execute();
//
//    public void forceShowAllResults(boolean showAllResults);
//
//    public String getErrorMessage();
//
//    public boolean mustBeExecuted(String text);
//
//    public boolean isShowMessage();
//
//    public void setMinLength(int minLength);
//
//    public Object getSelectPickRow();
//
    public void cleanPickedValues();
//
//    public void setExecuteCleanValues(boolean execute);
//
//    public void setPickComponent(JComponent pickComponent);

    Map<String, PickParamProvider> getParameterProviders();
    public Pick setMainAttribute(String attrName);

    void setPickFilled(boolean pickFilled);

    boolean isPickFilled();
}
