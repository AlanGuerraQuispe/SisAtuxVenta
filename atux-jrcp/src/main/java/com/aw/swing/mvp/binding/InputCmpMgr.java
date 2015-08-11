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

import com.aw.swing.mvp.Presenter;


/**
 * Date: Aug 16, 2007
 */
public class InputCmpMgr extends ComponentManager {

    private boolean useMainDomain = true;

    public InputCmpMgr(Presenter presenter) {
        super(presenter);
    }

    /**
     * Init the jComponent and add the jComponent to the list of components
     *
     * @param component
     */
    protected BindingComponent initComponent(BindingComponent component) {
        return addComponent(component);
    }

    /**
     * Add some jComponent to this manager without using the registerBind.... methods
     *
     * @param component
     */
    public BindingComponent addComponent(BindingComponent component) {
        component.getJComponent().putClientProperty(BindingComponent.ATTR_BND, component);
        components.add(component);
        return component;
    }


    public boolean useMainDomain() {
        return useMainDomain;
    }

    public void setUseMainDomain(boolean useMainDomain) {
        this.useMainDomain = useMainDomain;
    }

}
