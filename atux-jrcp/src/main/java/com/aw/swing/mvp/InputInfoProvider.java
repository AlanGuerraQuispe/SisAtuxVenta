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
package com.aw.swing.mvp;

import com.aw.swing.mvp.binding.BindingManager;
import com.aw.swing.mvp.binding.InputCmpMgr;
import com.aw.swing.mvp.cmp.pick.PickManager;
import com.aw.swing.mvp.validation.ValidatorManager;

import java.util.ArrayList;

/**
 * User: gmc
 * Date: 18-oct-2007
 */
public abstract class InputInfoProvider {
    protected Object backBean;
    protected BindingManager bindingMgr;
    protected PickManager pickManager;
    protected ValidatorManager validatorMgr;
    protected InputCmpMgr inputCmpMgr;


    public InputInfoProvider(Object backBean) {
        this.backBean = backBean;
    }

    /**
     * This method set the binding between the jcomponents and their attributes of the domain
     */
    protected void registerBinding() {
    }

    protected void registerValidations() {

    }

    public java.util.List getDynamicValidations() {
        return new ArrayList();
    }

    protected void registerPicks() {

    }

    public Object getBackBean() {
        return backBean;
    }

    public void setBackBean(Object backBean) {
        this.backBean = backBean;
        if (backBean != null)
            inputCmpMgr.setDefaultBean(backBean);
    }


    public void setBindingMgr(BindingManager bindingMgr) {
        this.bindingMgr = bindingMgr;
    }


    public void setPickManager(PickManager pickManager) {
        this.pickManager = pickManager;
    }


    public void setValidatorMgr(ValidatorManager validatorMgr) {
        this.validatorMgr = validatorMgr;
    }

    public abstract void setVsr(Object vsr);

    public InputCmpMgr getInputCmpMgr() {
        return inputCmpMgr;
    }

    public void setInputCmpMgr(InputCmpMgr inputCmpMgr) {
        this.inputCmpMgr = inputCmpMgr;
    }
}
