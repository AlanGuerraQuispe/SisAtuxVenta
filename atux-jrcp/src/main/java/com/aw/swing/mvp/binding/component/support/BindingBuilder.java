/**
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.binding.component.support;

/**
 * User: User
 * Date: Oct 10, 2007
 */

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.ParentComponentInfo;
import com.aw.swing.mvp.binding.component.BndICheckBox;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import elaprendiz.gui.passwordField.PasswordFieldRectIcon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: User
 * Date: Oct 9, 2007
 */
public class BindingBuilder {
    protected final Log logger = LogFactory.getLog(getClass());

    Presenter presenter;
    DomainBuilder domainBuilder;
    List<BindingComponent> bindingComponents = new ArrayList<BindingComponent>();
    public static String BINDING_PATHS = "bindingPaths";

    public BindingBuilder(Presenter presenter) {
        this.presenter = presenter;
        if (presenter.getBackBean() != null) {
            domainBuilder = new DomainBuilder(presenter.getBackBean());
        } else {
            domainBuilder = new DomainBuilder(presenter);
        }
    }

    // todo revisar luego - se hizo a la mala
    public void buildBinding() {
//        List<Field> fields = presenter.getIpView().getAttributesFromView();
        List<JComponent> components = presenter.getIpView().getCmpsFromView();
        logger.debug(" JTextField and JCheckBox finds ..<" + components.size() + ">");
        Object vsr = presenter.getViewMgr().getViewSrc();
        for (JComponent component : components) {
            Field field = (Field) component.getClientProperty("Field");
            logger.debug(" execute binding for <" + field.getName() + ">");
            if (component == null) {
                throw new IllegalArgumentException("It does not exist JComponent for " + field.getName());
            }
            BindingComponent bn = (BindingComponent) component.getClientProperty(BindingComponent.ATTR_BND);
            if (bn == null || bn.getFieldName() == null) {
                logger.debug(" executing automatic binding for <" + field.getName() + ">");
                buildBindingFor(field, component);
            }
        }
        int index = -1;
        for (BindingComponent bindingComponent : bindingComponents) {
//            if(bindingComponent==null) continue;
            index = presenter.getBindingMgr().getCurrentInputCmpMgr().getComponents().indexOf(bindingComponent);
            if (index != -1) {
                ((BindingComponent) presenter.getBindingMgr().getCurrentInputCmpMgr().getComponents().get(index)).setFieldName(bindingComponent.getFieldName());
            } else {
                presenter.getBindingMgr().getCurrentInputCmpMgr().addComponent(bindingComponent);
            }
        }
    }


    private void buildBindingFor(Field field, JComponent component) {
        BindingComponent bindingComponent = null;
        String fieldName = getFieldName(field.getName());
        // todo mejorar el performance de esto
//        String[] bindingPaths = (String[]) ((JComponent) component.getParent()).getClientProperty(BINDING_PATHS);
        ParentComponentInfo parentComponentInfo = (ParentComponentInfo) ((JComponent) component.getParent()).getClientProperty(BINDING_PATHS);
        String[] bindingPaths = null;
        if (parentComponentInfo!=null){
            bindingPaths = parentComponentInfo.getPaths();
        }
        String pathFieldName = domainBuilder.builPathFieldName(fieldName, bindingPaths);
        logger.debug("FieldName <" + fieldName + "> with path <" + pathFieldName + ">");

        if (pathFieldName == null) return;
        if (field.getType() == JCheckBox.class) {
            bindingComponent = new BndICheckBox(presenter.getBindingMgr().getCurrentInputCmpMgr(), (JCheckBox) component, domainBuilder.getDomain(), pathFieldName);
        } else if (field.getType() == JTextField.class ||
                   field.getType() == JTextArea.class ||
                   field.getType() == JTextPane.class ||
                   field.getType() == PasswordFieldRectIcon.class ||
                   field.getType() == JPasswordField.class ) {
            bindingComponent = new BndIJTextField(presenter.getBindingMgr().getCurrentInputCmpMgr(), (JTextComponent) component, domainBuilder.getDomain(), pathFieldName);
            presenter.getValidatorMgr().registerBasicRule((BndIJTextField) bindingComponent, "");
        } else {
            throw new IllegalArgumentException("Field JCMP Unknown:" + field.getType());
        }
        if (parentComponentInfo!=null && parentComponentInfo.isAllCmpsReadOnly()){
            bindingComponent.setAsUIReadOnly();
        }
        bindingComponents.add(bindingComponent);
    }

    private String getFieldName(String name) {
        String fieldName = name.substring(3, name.length());
        fieldName = String.valueOf(fieldName.charAt(0)).toLowerCase() + fieldName.substring(1, fieldName.length());
        return fieldName;
    }


}