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
package com.aw.swing.mvp.view;

import com.aw.core.exception.AWSystemException;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.support.reflection.MethodInvoker;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.ui.SplitPanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: gmc
 * Date: 07-may-2007
 * Class used to provide the references for several components that are in a Dialog
 */
public class IPView {
    protected final Log logger = LogFactory.getLog(getClass());
    private Object viewSrc;

    public static final String ERROR_LABEL = "lblErrorMessage";

    public IPView(Object viewSrc) {
        this.viewSrc = viewSrc;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //          General Methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get the components of the viewSrc
     *
     * @return
     */
    public Component[] getJComponents() {
        if (viewSrc instanceof JDialog) {
            JDialog dlg = (JDialog) viewSrc;
            return dlg.getContentPane().getComponents();
        } else {
            JPanel pnl = (JPanel) AttributeAccessor.get(viewSrc, "pnlMain");
            return pnl.getComponents();
        }
    }


    /**
     * Check if there is any textField used to do the searching
     *
     * @return
     */
    public boolean existTxtSearch() {
        Object cmp;
        try {
            cmp = AttributeAccessor.get(viewSrc, "txtSearch");
        } catch (Throwable throwable) {
            logger.debug("Dlg:<" + viewSrc + "> does not have the attribute:<txtSearch>");
            return false;
        }
        return cmp != null;
    }

    /**
     * Get the textField that contains the search criteria
     *
     * @return
     */
    public JTextField getTxtSearch() {
        return (JTextField) getComponent("txtSearch");
    }

    /**
     * Get the Panel that contains the search criteria
     *
     * @return
     */
    public JPanel getPnlSchCrit() {
        return (JPanel) getComponent("pnlSchCrit");
    }

    /**
     * Get the button that trigger the search action
     *
     * @return
     */
    public JButton getBtnSearch() {
        return (JButton) getComponent("btnSearch");
    }

    /**
     * Return the last jComponent that is before the search button.
     *
     * @return
     */
    public Component getLastCmpBeforeSearchBtn() {
        return (Component) getComponent("lastCmpBeforeSearchBtn");
    }


    public JLabel getJLabelForComponent(String fieldName) {
        return (JLabel) getComponent("lbl" + fieldName);
    }

    /**
     * Return the Panel that contains the grid Title
     *
     * @return
     */
    public JPanel getPnlTitGrid() {
        return getPnlTitGrid(0);
    }

    /**
     * Get an specific pnlMain title based on the index that is sent as parameter.
     * It is used in the case that exist more than one table per page
     *
     * @param index
     * @return
     */
    public JPanel getPnlGrid(int index) {
        JPanel pnlGrid = null;
        if (index == 0) {
            pnlGrid = (JPanel) getComponentFullScan("pnlGrid");
        } else {
            pnlGrid = (JPanel) getComponentFullScan("pnlGrid" + index);
        }
        if (pnlGrid == null) {
            throw new IllegalStateException("The panel for the grid:<" + index + "> is not defined.");
        }
        return pnlGrid;
    }


    /**
     * Get an specific pnlMain title based on the index that is sent as parameter.
     * It is used in the case that exist more than one table per page
     *
     * @param index
     * @return
     */
    public JPanel getPnlTitGrid(int index) {
        JPanel pnlTitGrid = null;
        if (index == 0) {
            pnlTitGrid = (JPanel) getComponentFullScan("pnlTitGrid");
        } else {
            pnlTitGrid = (JPanel) getComponentFullScan("pnlTitGrid" + index);
        }
        if (pnlTitGrid == null) {
            throw new IllegalStateException("The title panel for the grid:<" + index + "> is not defined.");
        }
        return pnlTitGrid;
    }




    /**
     * Get the table
     *
     * @return
     */
    public JTable getTblGrid() {
//        return (JTable) getComponent("tblGrid");
        return (JTable) getComponentFullScan("tblGrid");
    }

    /**
     * Get an specific table based on the index that is sent as parameter.
     * It is used in the case that exist more than one table per page
     *
     * @param index
     * @return
     */
    public JTable getTblGrid(int index) {
        if (index == 0) {
            return getTblGrid();
        }
        return (JTable) getComponentFullScan("tblGrid" + index);
    }

    /**
     * Return the label that is in the grid title
     *
     * @return
     */
    public JLabel getLblTitGrid() {
        return (JLabel) getComponentFullScan("lblTitGrid");
    }

    /**
     * Return the check box that is associated to a specific grid based on the index sent
     * as parameter
     *
     * @param index
     * @return
     */
    public JLabel getLblTitGrid(int index) {
        if (index == 0) {
            return getLblTitGrid();
        }
        return (JLabel) getComponentFullScan("lblTitGrid" + index);
    }

    /**
     * Return the check box that is associated to a specific grid
     *
     * @return
     */
    public JCheckBox getChkSelector() {
        return (JCheckBox) getComponentFullScan("chkSel");
    }

    public JPanel getJPanelError() {
        return (JPanel) getComponent("pnlError");
    }

    public JLabel getJLabelError() {
        return (JLabel) getComponent(ERROR_LABEL);
    }

    /**
     * Return the check box that is associated to a specific grid based on the index sent
     * as parameter
     *
     * @param index
     * @return
     */
    public JCheckBox getChkSelector(int index) {
        if (index == 0) {
            return getChkSelector();
        }
        return (JCheckBox) getComponentFullScan("chkSel" + index);
    }

    public java.util.List getGridCmps(int index) {
        java.util.List components = new ArrayList();
        JPanel jPanel = getPnlTitGrid(index);
        JPanel pnlToolBarGrid = getPnlToolBarGrid(index);
        components.add(jPanel);
        components.addAll(Arrays.asList(jPanel.getComponents()));
        if (pnlToolBarGrid != null) {
            components.addAll(Arrays.asList(pnlToolBarGrid.getComponents()));
        }
        JTable jTable = getTblGrid(index);
        components.add(jTable);
        JTable fixedTable = (JTable) jTable.getClientProperty(BndSJTable.CP_FIXED_TABLE);  
        if (fixedTable!=null){
            components.add(fixedTable);
        }
        return components;
    }

    public JPanel getPnlToolBar() {
        return (JPanel) getComponent("pnlToolBar");
    }
    private JPanel getPnlToolBarGrid(int index) {
        JPanel pnlToolBarGrid = null;
        if (index == 0) {
            pnlToolBarGrid = (JPanel) getComponentFullScan("pnlToolBarGrid");
        } else {
            pnlToolBarGrid = (JPanel) getComponentFullScan("pnlToolBarGrid" + index);
        }
//        if (pnlToolBarGrid==null){
//            throw new IllegalStateException("The button panel for the grid:<"+index+"> is not defined.");
//        }
        return pnlToolBarGrid;

    }


    public List getAttributesFromView() {
        return MethodInvoker.getAttributes(viewSrc);
    }

    public List<JComponent> getCmpsFromView() {
        return getCmps(viewSrc);
    }


    public static List<JComponent> getCmps(Object target) {
//        logger.info("searching attributes " + target.getClass().getName());
        List components = new ArrayList();
        List<Field> forms = new ArrayList();
        Class cls = target.getClass();
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            if ((fields[i].getName().startsWith("txt") || fields[i].getName().startsWith("chk")) && !fields[i].getName().startsWith("chkSel")) {
                JComponent jComponemt;
                try {
                    jComponemt = (JComponent) fields[i].get(target);
                    if (jComponemt != null) {
                        jComponemt.putClientProperty("Field", fields[i]);
                        components.add(jComponemt);
                    } else {
                        System.out.println("Null:<" + target.getClass() + ">- <" + fields[i].getName() + ">");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new AWSystemException("Error getting teh value of:<" + fields[i].getName() + ">", e);
                }
            }
            if ((fields[i].getType().getSimpleName().startsWith("Frm"))) {
                forms.add(fields[i]);
            }
        }
        if (forms.size() > 0) {
            for (Field field : forms) {
                try {
                    Object formToBeChecked = field.get(target);
                    if (formToBeChecked != null) {
                        List formAttributes = getCmps(formToBeChecked);
                        if (formAttributes.size() > 0) {
                            components.addAll(formAttributes);
                        }
                    } else {
                        throw new IllegalStateException("FRM NULL:" + field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new AWSystemException("Problems getting value for:<" + field.getName() + ">", e);
                }
            }
        }
        return components;
    }


    public JLabel getJLabel(String jlabelName) {
        return (JLabel) getComponent(jlabelName);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //          Util methods
    ///////////////////////////////////////////////////////////////////////////////////////////
    public Object getComponent(String attributeName) {
        return getComponent(attributeName, viewSrc);
    }

    public Object getComponentFullScan(String attributeName) {
        Object obj = getComponentFullScan(attributeName, viewSrc);
        if (obj == null) {
            logger.warn("Component " + attributeName + " does not exist in" + viewSrc);
        }
        return obj;
    }

    private Object getComponentFullScan(String attributeName, Object source) {
        List<Field> forms = new ArrayList();
        Class cls = source.getClass();
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(attributeName)) {
                JComponent jComponemt;
                try {
                    jComponemt = (JComponent) fields[i].get(source);
                    if (jComponemt != null) {
                        jComponemt.putClientProperty("Field", fields[i]);
                        return jComponemt;
                    } else {
                        System.out.println("Null:<" + source.getClass() + ">- <" + fields[i].getName() + ">");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new AWSystemException("Error getting teh value of:<" + fields[i].getName() + ">", e);
                }
            }
            if ((fields[i].getType().getSimpleName().startsWith("Frm"))) {
                forms.add(fields[i]);
            }
        }
        if (forms.size() > 0) {
            for (Field field : forms) {
                try {
                    Object formToBeChecked = field.get(source);
                    if (formToBeChecked != null) {
                        Object cmp = getComponentFullScan(attributeName, formToBeChecked);
                        if (cmp != null) {
                            return cmp;
                        }
                    } else {
                        throw new IllegalStateException("FRM NULL:" + field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new AWSystemException("Problems getting value for:<" + field.getName() + ">", e);
                }
            }
        }
        logger.debug("Searching - Component " + attributeName + " does not exist in:" + source);
        return null;
    }

    public Object getComponent(String attributeName, Object source) {
        Object cmp;
        try {
            cmp = getComponentFullScan(attributeName, source);
            if (cmp == null) {
                logger.warn("Component " + attributeName + " does not exist in" + source);
            }
//            cmp = AttributeAccessor.get(source, attributeName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new IllegalStateException("Dlg:<" + viewSrc + "> does not have the attribute:<" + attributeName + ">");
        }
        return cmp;
    }

    public boolean existComponent(String attributeName) {
        try {
            Object cmp = AttributeAccessor.get(viewSrc, attributeName);
        } catch (Throwable throwable) {
            return false;
        }
        return true;
    }

    SplitPanel splitPanel;
    public SplitPanel getSplitPanel() {
        if (splitPanel==null){
            splitPanel = (SplitPanel) getComponent("splitPanel");
        }
        return splitPanel;
    }
}
