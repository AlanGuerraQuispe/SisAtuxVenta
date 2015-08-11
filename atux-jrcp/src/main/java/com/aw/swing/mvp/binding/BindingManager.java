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
package com.aw.swing.mvp.binding;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.format.DateFormatter;
import com.aw.core.util.TimeObserver;
import com.aw.core.view.ViewMode;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.*;
import com.aw.swing.mvp.binding.component.support.BindingBuilder;
import com.aw.swing.mvp.validation.Validator;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Date: Aug 16, 2007
 */
public class BindingManager {
    protected final Log logger = LogFactory.getLog(getClass());
    private Presenter presenter;
    protected BindingBuilder bindingBuilder;
    /**
     * List of InputCmpMgr
     */
    private List<InputCmpMgr> inputsCmpMgrs;
    private InputCmpMgr currentInputCmpMgr;
    private List allDateBindings;


    public BindingManager(Presenter presenter) {
        this.presenter = presenter;
        inputsCmpMgrs = new ArrayList<InputCmpMgr>();
        addInputCmpMgr(new InputCmpMgr(presenter));
    }

    public ParentComponentInfo setBindingPath(JPanel jpanel, String... paths) {
        ParentComponentInfo parentComponentInfo = new ParentComponentInfo(paths);
        jpanel.putClientProperty(BindingBuilder.BINDING_PATHS, parentComponentInfo);
        return parentComponentInfo;
    }

    /**
     * Binding with the default validation rule.
     *
     * @param component
     * @param fieldName
     * @return
     */
    public BndIJTextField bind(JTextComponent component, String fieldName) {
        return bind(component, fieldName, "");
    }

    public BndIJTextField bind(JTextComponent component, String fieldName, String validationRule) {
        BndIJTextField inputComponent = new BndIJTextField(currentInputCmpMgr, component, fieldName);
        presenter.getValidatorMgr().registerBasicRule(inputComponent, validationRule);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJTextField bind(JTextComponent component) {
        BndIJTextField inputComponent = new BndIJTextField(currentInputCmpMgr, component);
        presenter.getValidatorMgr().registerBasicRule(inputComponent, "");
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJTextField bind(JTextComponent component, String fieldName, Validator validator) {
        BndIJTextField inputComponent = new BndIJTextField(currentInputCmpMgr, component, fieldName);
        inputComponent.registerValidator(validator);
        presenter.getValidatorMgr().registerBasicValidator(validator);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    /**
     * Set the model to the JComboBox.
     * Use the defaultBeanWrapper to do the binding.
     * @param component
     * @param fieldName
     */
    public BndIJComboBox bind(JComboBox component, List dropDownValues, String fieldName) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, dropDownValues, fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJComboBox bind(JComboBox component, List dropDownValues, String fieldName, String rule) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, dropDownValues, fieldName);
        presenter.getValidatorMgr().registerBasicRule(inputComponent, rule);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJComboBox bind(JComboBox component, MetaLoader metaLoader, String fieldName) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, decorateMetaLoader(metaLoader), fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJComboBox bind(JComboBox component, MetaLoader metaLoader, String fieldName, String rule) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, decorateMetaLoader(metaLoader), fieldName);
        presenter.getValidatorMgr().registerBasicRule(inputComponent, rule);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJComboBox bind(JComboBox component, MetaLoader metaLoader, Object bean, String fieldName) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, decorateMetaLoader(metaLoader), bean, fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJComboBox bind(JComboBox component, MetaLoader metaLoader, Object bean, String fieldName,String rule) {
        BndIJComboBox inputComponent = new BndIJComboBox(currentInputCmpMgr, component, decorateMetaLoader(metaLoader), bean, fieldName);
        presenter.getValidatorMgr().registerBasicRule(inputComponent, rule);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    protected MetaLoader decorateMetaLoader(MetaLoader metaLoader) {
        String pstViewMode = presenter.getViewMode();
        if (presenter instanceof FindPresenter || ViewMode.MODE_PICK.equals(pstViewMode)) {
            if (!metaLoader.isTopRowSet()) {
                metaLoader =metaLoader.addTodosNRow();
            }
        } else {
            if (!metaLoader.isTopRowSet()) {
                metaLoader = metaLoader.addSeleccioneNRow();
            }
        }
        if (!ViewMode.MODE_READONLY.equals(pstViewMode)) {
            metaLoader =  metaLoader.activeRows();
        }
        return metaLoader;
    }

    public BndIJLabelField bind(JLabel component, String fieldName) {
        BndIJLabelField inputComponent = new BndIJLabelField(currentInputCmpMgr, component, fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJLabelField bind(JLabel component) {
        BndIJLabelField inputComponent = new BndIJLabelField(currentInputCmpMgr, component);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJLabelField bind(JLabel component, List dropDownValues, String fieldName) {
        BndIJLabelField inputComponent = new BndIJLabelField(currentInputCmpMgr, component, dropDownValues, fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndIJLabelField bind(JLabel component, MetaLoader metaLoader, String fieldName) {
        BndIJLabelField inputComponent = new BndIJLabelField(currentInputCmpMgr, component, decorateMetaLoader(metaLoader), fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndICheckBox bind(JCheckBox component, String fieldName) {
        BndICheckBox inputComponent = new BndICheckBox(currentInputCmpMgr, component, fieldName);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    public BndICheckBox bind(JCheckBox component) {
        BndICheckBox inputComponent = new BndICheckBox(currentInputCmpMgr, component);
        currentInputCmpMgr.initComponent(inputComponent);
        return inputComponent;
    }

    ButtonGroup buttonGroup = new ButtonGroup();

    public BndIJRadioButton bind(JRadioButton component, String fieldName, Object selectedValue) {
        BndIJRadioButton inputComponent = bindIntern(component, fieldName, selectedValue, buttonGroup);
        return inputComponent;
    }

    private BndIJRadioButton bindIntern(JRadioButton component, String fieldName, Object selectedValue, ButtonGroup buttonGroup) {
        BndIJRadioButton inputComponent = new BndIJRadioButton(currentInputCmpMgr, component, fieldName, selectedValue);
        currentInputCmpMgr.initComponent(inputComponent);
        buttonGroup.add(component);
        return inputComponent;
    }

    public BndIJButtonGroup bindRadioGroup(String fieldName) {
        return new BndIJButtonGroup(fieldName) {
            protected BndIJRadioButton bindIntern(JRadioButton component, Object selectedValue) {
                return BindingManager.this.bindIntern(component, fieldName, selectedValue, this.buttonGroup);
            }
        };
    }


    /**
     * Add and set as current Input Component Manger the input jComponent sent as parameter.
     *
     * @param inputCmpMgr
     */
    public InputCmpMgr addInputCmpMgr(InputCmpMgr inputCmpMgr) {
        inputsCmpMgrs.add(inputCmpMgr);
        currentInputCmpMgr = inputCmpMgr;
        return inputCmpMgr;
    }

    public InputCmpMgr addInputCmpMgr() {
        return this.addInputCmpMgr(new InputCmpMgr(presenter));
    }

    public void setDefaultBean(Object bean) {
        for (InputCmpMgr inputCmpMgr : inputsCmpMgrs) {
            if (inputCmpMgr.useMainDomain()) {
                inputCmpMgr.setDefaultBean(bean);
            }
        }
    }

    public void updateDomain(Object bean) {
        for (InputCmpMgr inputCmpMgr : inputsCmpMgrs) {
            inputCmpMgr.setDefaultBean(bean);
            List components = inputCmpMgr.getComponents();
            for (Iterator iterator = components.iterator(); iterator.hasNext();) {
                BindingComponent bindingComponent = (BindingComponent) iterator.next();
                bindingComponent.setBeanWrapper(bean);
            }
        }
    }


    public BindingComponent getBindingComponent(JComponent component) {
        return (BindingComponent) component.getClientProperty(BindingComponent.ATTR_BND);
    }


    public void init() {
        for (Iterator iterator = inputsCmpMgrs.iterator(); iterator.hasNext();) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) iterator.next();
            for (Iterator iterator1 = inputCmpMgr.getComponents().iterator(); iterator1.hasNext();) {
                BindingComponent bindingComponent = (BindingComponent) iterator1.next();
                bindingComponent.init();
            }
        }
    }

    public void setValuesToBean() {
        for (int i = 0; i < inputsCmpMgrs.size(); i++) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) inputsCmpMgrs.get(i);
            inputCmpMgr.setValuesToBean();
        }
    }

    public void setValuesToJComponent() {
        for (int i = 0; i < inputsCmpMgrs.size(); i++) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) inputsCmpMgrs.get(i);
            if (inputCmpMgr.getDefaultBeanWrapper() != null) {
                inputCmpMgr.setValueToJComponent();
            }
        }
    }

    public InputCmpMgr getCurrentInputCmpMgr() {
        return currentInputCmpMgr;
    }

    public List getInputsCmpMgrs() {
        return inputsCmpMgrs;
    }

    public List<BindingComponent> getAllBindings() {
        List<BindingComponent> bindingComponents = new ArrayList();
        List inputsCmpMgrs = getInputsCmpMgrs();
        logger.debug("searching bindings registers ...");
        for (Iterator iterator = inputsCmpMgrs.iterator(); iterator.hasNext();) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) iterator.next();
            for (Iterator iterator1 = inputCmpMgr.getComponents().iterator(); iterator1.hasNext();) {
                BindingComponent bindingComponent = (BindingComponent) iterator1.next();
                logger.debug("bindingComponent <" + bindingComponent + ">");
                bindingComponents.add(bindingComponent);
            }
        }
        return bindingComponents;
    }

    public List getAllBindingsName() {
        List bindingComponents = new ArrayList();
        List inputsCmpMgrs = getInputsCmpMgrs();
        logger.debug("searching bindings registers ...");
        for (Iterator iterator = inputsCmpMgrs.iterator(); iterator.hasNext();) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) iterator.next();
            for (Iterator iterator1 = inputCmpMgr.getComponents().iterator(); iterator1.hasNext();) {
                BindingComponent bindingComponent = (BindingComponent) iterator1.next();
                logger.debug("bindingComponent <" + bindingComponent + ">");
                bindingComponent.getJComponent().getClientProperty(BindingComponent.ATTR_BND);
                bindingComponents.add(bindingComponent.getFieldName());
            }
        }
        return bindingComponents;
    }

    public void setAllAsReadOnly() {
        for (int i = 0; i < inputsCmpMgrs.size(); i++) {
            InputCmpMgr inputCmpMgr = (InputCmpMgr) inputsCmpMgrs.get(i);
            inputCmpMgr.setAllCmpsAsUIReadOnly();
        }
    }

    public void executeAutomaticBinding() {
        TimeObserver timeBinding = new TimeObserver("executing automaticBinding");
        timeBinding.empezar();
        bindingBuilder = new BindingBuilder(presenter);
        bindingBuilder.buildBinding();
        timeBinding.terminar();
    }

    public Component[] buildSortedCmp() {
        List<Component> cmp = new ArrayList<Component>();
        for (InputCmpMgr inputCmpMgr : inputsCmpMgrs) {
            cmp.addAll(inputCmpMgr.getJComponents());
        }
        return cmp.toArray(new Component[cmp.size()]);
    }

    public BindingComponent getBindingComponent(String fieldName) {
        List<BindingComponent> bindingComponents = getAllBindings();
        for (BindingComponent bindingComponent : bindingComponents) {
            if (fieldName.equals(bindingComponent.getFieldName())) {
                return bindingComponent;
            }
        }
        return null;
    }

    public List<BndIJTextField> getAllDateBindings() {
        List<BndIJTextField> dateFields = new ArrayList();
        List<BindingComponent> allBindings = getAllBindings();
        for (BindingComponent bindingComponent : allBindings) {
            if ((bindingComponent instanceof BndIJTextField) &&
                    ((BndIJTextField) bindingComponent).getFormatter() instanceof DateFormatter) {
                dateFields.add((BndIJTextField) bindingComponent);
            }
        }
        return dateFields;
    }

    public List<BndIJTextField> getFunctionFields() {
        List functionFields = ListUtils.getSubList(getAllBindings(), new UnaryPredicate() {
            public boolean test(Object o) {
                if (o instanceof BndIJTextField) {
                    return ((BndIJTextField) o).getGridFunction() != null;
                }
                return false;
            }
        });
        return functionFields;
    }

    public void reset() {
        inputsCmpMgrs = new ArrayList<InputCmpMgr>();
        addInputCmpMgr(new InputCmpMgr(presenter));
    }
}