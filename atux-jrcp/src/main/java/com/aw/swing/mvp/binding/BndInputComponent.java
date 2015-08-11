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

import javax.swing.*;

/**
 * Class used to automatize the binding between input components and domain beans
 *
 * @author gmateo
 *         14/10/2004
 */
public abstract class BndInputComponent<JCmp extends JComponent>extends BindingComponent<JCmp> {

    /**
     * Constructor used when it will be used the default beanWrapper of the InputCmpMgr
     *
     * @param inputComponentManager
     * @param jInputComponent
     * @param fieldName
     */
    protected BndInputComponent(ComponentManager inputComponentManager, JCmp jInputComponent, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
    }
    protected BndInputComponent(ComponentManager inputComponentManager, JCmp jInputComponent) {
        super(inputComponentManager, jInputComponent);
    }

    /**
     * Constructor used when it will be used specific beanWrapper
     *
     * @param jInputComponent
     * @param bean
     * @param fieldName
     */
    protected BndInputComponent(ComponentManager inputComponentManager, JCmp jInputComponent, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
    }


}


