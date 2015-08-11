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

import com.aw.swing.mvp.binding.InputCmpMgr;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 18-oct-2007
 */
public class InputProviderManager {
    protected Presenter pst;
    protected List inputProviders = new ArrayList();

    protected final Log logger = LogFactory.getLog(getClass());

    public InputProviderManager(Presenter pst) {
        this.pst = pst;
    }

    public InputProvider registerInputProvider(InputInfoProvider infoProvider) {
        configureInfoProvider(infoProvider);
        InputProvider inputProvider = new InputProvider(infoProvider);
        inputProvider.configureElements();
        inputProviders.add(inputProvider);
        pst.getBindingMgr().addInputCmpMgr(new InputCmpMgr(pst));
        return inputProvider;
    }

    private void configureInfoProvider(InputInfoProvider infoProvider) {
        InputCmpMgr inputCmpMgr = pst.getBindingMgr().getCurrentInputCmpMgr();
        inputCmpMgr.setDefaultBean(infoProvider.getBackBean());
        inputCmpMgr.setUseMainDomain(false);
        infoProvider.setBindingMgr(pst.getBindingMgr());
        infoProvider.setPickManager(pst.getPickMgr());
        infoProvider.setValidatorMgr(pst.getValidatorMgr());
        infoProvider.setInputCmpMgr(inputCmpMgr);
        Object realView = pst.getViewMgr().getViewSrc();
        infoProvider.setVsr(realView);
    }
}
