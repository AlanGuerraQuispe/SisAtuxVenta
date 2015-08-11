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
package com.aw.swing.mvp.binding.component;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.DBDataLoader;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.support.ObjectConverter;
import com.aw.support.beans.BeanUtils;
import com.aw.swing.mvp.binding.BndInputComponent;
import com.aw.swing.mvp.binding.InputCmpMgr;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import com.aw.swing.mvp.ui.painter.PainterMessages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

/**
 * Input Component representation for JComboBox
 *
 * @author gmateo
 *         14/10/2004
 */
public class BndIJComboBox extends BndInputComponent implements Hierarchyable<BndIJComboBox> {

    /**
     * Register default key
     */
    protected Object defaultKey;

    protected ComponentHierarchyInfo<BndIJComboBox> cmpHierarchyInfo = new ComponentHierarchyInfo<BndIJComboBox>(this);
    /**
     * List of beans that represent the labels and values that will be put in the comboBox
     */
    private List<DropDownRow> dropDownValues;

    private MetaLoader metaLoader;

    private List onValueSelectionListener = null;

    private String changeConfirmationMsg = "";
    private boolean needChangeConfirmation = false;

    public Object getDefaultKey() {
        return defaultKey;
    }

    public BndIJComboBox dependsOn(BndIJComboBox masterCmp) {
        cmpHierarchyInfo.dependsOn(masterCmp);
        return this;
    }

    public void addDependentCmp(BndIJComboBox bndIJComboBox) {
        cmpHierarchyInfo.addDependentCmp(bndIJComboBox);
    }

    /**
     * Initialize the jComponent
     */
    public void initComponent() {
        if (metaLoader != null) {
            if (cmpHierarchyInfo.isMasterCmp()) {
                setDropDownValues(metaLoader.getRows());
                resetDependentCmps();
            } else {
                //setDropDownValues(new ArrayList());
            }
        }
        addValidatorDecoratorIfApply();

        if (cmpHierarchyInfo.hasDependentCmps()) {
            logger.debug("Setting the listener for the master combobox:" + fieldName);
            //final BndIJComboBox bndIJComboBox = this;

            ((JComboBox) jComponent).addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        resetDependentCmps();

                    }
                }
            });
        }
    }

    private void resetDependentCmps() {
        BndIJComboBox bndIJComboBox = this;
        List masterValues = new ArrayList();
        if (cmpHierarchyInfo.hasParentCmps()) {
            masterValues = getMasterValues(bndIJComboBox);
        }
        masterValues.add(getSelectedCode());

        for (BndIJComboBox dependentCmp : cmpHierarchyInfo.getDependentCmps()) {
            MetaLoader metaLoader = dependentCmp.getMetaLoader();
            if (metaLoader != null) {
                if (metaLoader.getDataLoader() instanceof DBDataLoader) {
                    DBDataLoader dataLoader = (DBDataLoader) metaLoader.getDataLoader();
                    dataLoader.setSqlParams(masterValues.toArray());
                    dependentCmp.setDropDownValues(dependentCmp.getMetaLoader().getRows());
                } else {
                    if (metaLoader.getFilter() != null)
                        metaLoader.getFilter().setParams(getSelectedCode());
                    List rows = metaLoader.getRows();
                    logger.warn("Rows(sel code:" + getSelectedCode() + "):" + rows);
                    dependentCmp.setDropDownValues(rows);
                }
            }
            if (dependentCmp.cmpHierarchyInfo.hasDependentCmps()) {
                cleanDropDownValues(dependentCmp.cmpHierarchyInfo.getDependentCmps());
            }
        }
    }

    private void addValidatorDecoratorIfApply() {
        if (validator != null && validator.propertyValueIsRequerid()) {
            ((JComboBox) jComponent).addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Object obj = getValue();
                        if (obj != null) {
                            PainterMessages.cleanException(jComponent);
                        } else {
                            PainterMessages.paintException(jComponent);
                        }
                    }
                }
            });
        }
    }

    private void cleanDropDownValues(List<BndIJComboBox> dependentCmps) {
        for (BndIJComboBox dependentCmp : dependentCmps) {
            List dropDownValues = new ArrayList();
            if (dependentCmp.getMetaLoader() != null && dependentCmp.getMetaLoader().isTopRowSet()) {
                dropDownValues.add(dependentCmp.getMetaLoader().getTopRow());
            }
            dependentCmp.setDropDownValues(dropDownValues);
            if (dependentCmp.cmpHierarchyInfo.hasDependentCmps()) {
                cleanDropDownValues(dependentCmp.cmpHierarchyInfo.dependentCmps);
            }
        }
    }

    public BndIJComboBox(InputCmpMgr inputComponentManager, JComponent jInputComponent, List dropDownValues, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
        setDropDownValues(dropDownValues);
    }

    public BndIJComboBox(InputCmpMgr inputComponentManager, JComponent jInputComponent, List dropDownValues, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
        setDropDownValues(dropDownValues);
    }

    public BndIJComboBox(InputCmpMgr inputComponentManager, JComponent jInputComponent, MetaLoader metaLoader, Object bean, String fieldName) {
        super(inputComponentManager, jInputComponent, bean, fieldName);
        this.metaLoader = metaLoader;
    }

    public BndIJComboBox(InputCmpMgr inputComponentManager, JComponent jInputComponent, MetaLoader metaLoader, String fieldName) {
        super(inputComponentManager, jInputComponent, fieldName);
        this.metaLoader = metaLoader;
    }

    /**
     * Set the value of the bean to the JInputComponent
     */
    protected void setValueToJComponentInternal() {
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + getBeanValue());
        setSelectedCode(getBeanValue());
    }


    /**
     * Set the value of the JInputComponent to the bean
     * If the user has not selected any item then the value will be set to null
     */
    public void setValueToBean() {
        Object value = getSelectedCode();
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Cmpt-->Bean:" + value + " class: " + getBeanWrapper().getWrappedInstance());
        getBeanWrapper().setPropertyValue(fieldName, value);
    }

    public Object getBeanValue() {
        return getBeanWrapper().getPropertyValue(fieldName);
    }

    private Object getSelectedCode() {
        JComboBox comboBox = (JComboBox) jComponent;
        int index = comboBox.getSelectedIndex();
        Object value = null;

        if (index > -1) {
            if (dropDownValues.size() <= index)
                logger.error("[" + this.fieldName + "]Indice incorrrecto ");
            else
                value = ((DropDownRow) dropDownValues.get(index)).getValue();
        }
        return value;
    }

    public void setValue(Object value) {
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + value);
        JComboBox comboBox = (JComboBox) jComponent;
        comboBox.setSelectedIndex(getIndex(value));
    }

    /**
     * Returns the selected object row from the dropdown
     * This object contains all the values of the bean (value, label and may other custom properties)
     *
     * @return
     */
    public Object getSelectedObject() {
        JComboBox comboBox = (JComboBox) jComponent;
        int index = comboBox.getSelectedIndex();
        Object value = null;

        if (index > -1) {
            if (dropDownValues.size() <= index)
                logger.error("[" + this.fieldName + "]Indice incorrrecto ");
            else
                value = ((DropDownRow) dropDownValues.get(index));
        }

        return value;
    }

    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public void cleanJComponentValues() {
        JComboBox comboBox = (JComboBox) jComponent;
        comboBox.setSelectedIndex(getIndex(null));
    }

    /**
     * Return the values that have to be showed in the combo box
     *
     * @return
     */
    private Object[] getValuesToBeShowed() {
        Object[] objectsToBeShowed = new Object[dropDownValues.size()];
        for (int i = 0; i < dropDownValues.size(); i++) {
            DropDownRow dropDownRow = (DropDownRow) dropDownValues.get(i);
            objectsToBeShowed[i] = dropDownRow.getLabel();
        }
        return objectsToBeShowed;
    }

    boolean selectingUsingCode = false;

    public void setSelectedCode(Object code) {
        selectingUsingCode = true;
        JComboBox comboBox = (JComboBox) jComponent;
        comboBox.setSelectedIndex(getIndex(code));
        selectingUsingCode = false;
    }

    /**
     * Get the index that this value has in the list
     *
     * @param propertyValue
     * @return
     */
    public int getIndex(Object propertyValue) {
        int index = -1;
        if (propertyValue != null && !propertyValue.equals("")) {
            index = getIndexIntern(propertyValue);
        } else {
            if (defaultKey != null) {
                index = getIndexIntern(defaultKey);
            } else {
                if (dropDownValues.size() > 0) {
                    index = 0;
                }
            }
        }
        return index;
    }

    private int getIndexIntern(Object propertyValue) {
        int index = -1;
        if (propertyValue != null && !propertyValue.equals("")) {
            int i = 0;
            for (; i < dropDownValues.size(); i++) {
                DropDownRow dropDownRow = (DropDownRow) dropDownValues.get(i);
                Object value = dropDownRow.getValue();
                Object propertyValueConv = value == null ? propertyValue : ObjectConverter.convert(propertyValue, value.getClass());
                if (BeanUtils.equals(value, propertyValueConv) ||
                        ((value instanceof String) && ((String) value).trim().equals(propertyValueConv))) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                logger.error("The value: <" + propertyValue + "> was not found in the combobox");
            }
        }
        return index;
    }

    /**
     * Get the value that contains the input jComponent
     *
     * @return
     */
    public Object getValue() {
        return getSelectedCode();
    }

    /**
     * Get the value that contains the input jComponent
     *
     * @return
     */
    public Object getDescription() {
        return ((JComboBox) jComponent).getSelectedItem();
    }

    /**
     * Set the values that are shown in the ComboBox
     *
     * @param dropDownValues
     */
    public void setDropDownValues(List dropDownValues) {
        this.dropDownValues = dropDownValues;
        logger.debug("[" + this.fieldName + "]Putting the list of values to be showed in JComboBox");
        JComboBox comboBox = (JComboBox) jComponent;
        comboBox.setModel(getJComboBoxModel());
    }

    private DefaultComboBoxModel getJComboBoxModel() {
        DefaultComboBoxModel model  =  new DefaultComboBoxModel(getValuesToBeShowed());
        if (needChangeConfirmation){
            model = new VetoableComboBoxModel(getValuesToBeShowed());
            ((VetoableComboBoxModel) model).addVetoableSelectionListener(new VetoableComboBoxSelectionListener(){
                public boolean selectionChanged(VetoableChangeEvent e) {
                    return  MsgDisplayer.showConfirmMessage(changeConfirmationMsg);
                }
            });

        }
        return model;
    }

    public void reloadDropDownValues() {
        if (metaLoader != null) {
            setDropDownValues(metaLoader.getRows());
        }
    }

    public List getDropDownValues() {
        return dropDownValues;
    }

    public BndIJComboBox registerDefaulValue(Object key) {
        this.defaultKey = key;
        return this;
    }

    ////////////////////// On Code Selection support //////////////////////
    /**
     * Used to add listeners thta will be executed when certaing code is selected
     * in the combo box
     * The listener will be called immediately after the code is selected
     * (even when the control has not lost the focus yet)
     *
     * @param listener
     * @see OnValueExecuterListener class
     */
    public void addOnValueExecuterListener(OnValueExecuterListener listener) {
        if (onValueSelectionListener == null) {
            // no listener registered yet
            onValueSelectionListener = new ArrayList(1);
            addActionListener(new ActionListenerAdapter() {
                public void doActionPerformed(ActionEvent e, Object selectedCode) {
                    selectedCode = getSelectedCode();
                    // when an item is set, just check the list
                    for (Iterator iterator = onValueSelectionListener.iterator(); iterator.hasNext();) {
                        OnValueExecuterListener listener = (OnValueExecuterListener) iterator.next();
                        if (listener.passValueComparison(selectedCode)) {
                            logger.debug("Executing PASS listener for code:" + selectedCode);
                            listener.executeOnComparisonPass();
                        } else {
                            logger.debug("Executing FAIL listener for code:" + selectedCode);
                            listener.executeOnComparisonFail();
                        }
                    }
                }
            });
        }
        onValueSelectionListener.add(listener);
    }

    public BndIJComboBox addActionListener(ActionListenerAdapter actionListenerAdapter) {
        actionListenerAdapter.bnd = this;
        ((JComboBox) jComponent).addActionListener(actionListenerAdapter);

        return this;
    }


    public List getMasterValues(BndIJComboBox bndIJComboBox) {
        List masterValues = new ArrayList();
        BndIJComboBox parentCmp = bndIJComboBox.cmpHierarchyInfo.getParentCmps().get(0);
        masterValues.add(parentCmp.getValue());
        if (parentCmp.cmpHierarchyInfo.hasParentCmps()) {
            masterValues.addAll(getMasterValues(parentCmp));
        }
        return masterValues;
    }

    public static abstract class ActionListenerAdapter implements ActionListener {
        BndIJComboBox bnd;

        public void actionPerformed(ActionEvent e) {
            Object selectedCode = bnd.getSelectedCode();
            doActionPerformed(e, selectedCode);
        }

        abstract protected void doActionPerformed(ActionEvent e, Object selectedCode);
    }

    /**
     * Class used to provide custom behavior based on some code selected in the combo
     *
     * @see #executeOnComparisonPass()
     * @see #executeOnComparisonFail()
     */
    public static abstract class OnValueExecuterListener {
        private Object value;
        private boolean inverse = false;

        /**
         * Constructor
         *
         * @param value used to compare, if this code is selected in the combo, then
         *              the method {@link #executeOnComparisonPass()} will be executed
         *              otherwise the method {@link #executeOnComparisonFail()}
         * @see #setInverse()
         */
        public OnValueExecuterListener(Object value) {
            this.value = value;
        }

        abstract public void executeOnComparisonPass();

        public void executeOnComparisonFail() {
        }

        public boolean passValueComparison(Object selectedCode) {
            boolean match = matchSelectedValue(selectedCode);
            return inverse ? !match : match;
        }

        private boolean matchSelectedValue(Object selectedCode) {
            if (selectedCode == null && value == null) return true;
            return selectedCode != null && selectedCode.equals(value);
        }

        /**
         * Change the behaviour, if value DOES NOT match the code selected in the combo
         * the method {@link #executeOnComparisonPass()} will be executed
         * otherwise the method {@link #executeOnComparisonFail()}
         */
        public void setInverse() {
            inverse = true;
        }
    }

    public MetaLoader getMetaLoader() {
        return metaLoader;
    }

    public boolean isSelectingUsingCode() {
        return selectingUsingCode;
    }

    public BndIJComboBox needChangeConfirmation(String changeConfirmationMsg) {
        this.changeConfirmationMsg = changeConfirmationMsg;
        needChangeConfirmation = true;
        return this;
    }

    /**
     * Clasees needed to manage the confirmation for changing the selected value.
     */
    private static class VetoableChangeEvent extends ChangeEvent {
        private Object newValue;

        public VetoableChangeEvent(Object source, Object newValue) {
            super(source);
            this.newValue = newValue;
        }

        public Object getNewValue() {
            return newValue;
        }
    }

    private interface VetoableComboBoxSelectionListener extends EventListener {
        boolean selectionChanged(VetoableChangeEvent e);
    }

    private class VetoableComboBoxModel extends DefaultComboBoxModel {
        private List<VetoableComboBoxSelectionListener> selectionListeners = new ArrayList();

        VetoableComboBoxModel(Object items[]) {
            super(items);
        }

        public void addVetoableSelectionListener(VetoableComboBoxSelectionListener l) {
            selectionListeners.add(l);
        }

        public void removeVetoableSelectionListener(VetoableComboBoxSelectionListener l) {
            selectionListeners.remove(l);
        }

        protected boolean fireVetoableSelectionChange(Object newValue) {
            VetoableChangeEvent event = new VetoableChangeEvent(this, newValue);
            for (VetoableComboBoxSelectionListener selectionListener : selectionListeners) {
                if (!selectionListener.selectionChanged(event)) {
                    return true;
                }
            }
            return false;
        }


        public void setSelectedItem(final Object anItem) {
            final boolean selectUsingCodeLocal = selectingUsingCode;
            if (SwingUtilities.isEventDispatchThread()){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {
                        selectingUsingCode = selectUsingCodeLocal;
                        setSelectedItemInternal(anItem);
                        selectingUsingCode = false;
                    }
                });
            }else{
                setSelectedItemInternal(anItem);
            }
        }
        private void setSelectedItemInternal(Object anItem){
            if (selectingUsingCode || !fireVetoableSelectionChange(anItem)) {
               super.setSelectedItem(anItem);
            }
        }
    }


}
