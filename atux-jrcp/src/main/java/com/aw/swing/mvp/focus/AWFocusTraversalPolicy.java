package com.aw.swing.mvp.focus;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.ActionIdentifier;
import com.aw.swing.mvp.action.ActionManager;
import com.aw.swing.mvp.action.ActionNames;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.DlgMensaje;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * User: gmc
 * Date: 16/06/2009
 */
public class AWFocusTraversalPolicy extends FocusTraversalPolicy {
    Map<Container, List<Component>> containerComponents = new HashMap();

    public Component getComponentAfter(Container aContainer, Component aComponent) {


      Container parent = aComponent.getParent();
        if (!containerComponents.containsKey(parent)) {
            addListComponentsFor(parent);
        }

        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        List<Component> cmps = null;
        if (pst != null) {
            cmps = pst.getComponentsWithCustomFocusPolicy();
        }
        if (cmps == null) {
            cmps = containerComponents.get(parent);
        }

        if (cmps.size() == 0) return aComponent;
        int index = cmps.indexOf(aComponent);
        return index == -1 ? null : getNextComponent(cmps, index);
    }

    private Component getNextComponent(List<Component> cmps, int index) {
        for (int i = index + 1; i < cmps.size(); i++) {
            Component component = cmps.get(i);
            if (isFocusable(component)) {
                return component;
            }
        }
        if (index == -1) {
            return null;
        }

        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if(pst!=null){
            com.aw.swing.mvp.action.Action action = pst.getActionRsr().getDefaultPstAction();
            if (action != null)
                ActionManager.instance().executeAction(action);
        }

        return getNextComponent(cmps, -1);
    }

    private boolean isFocusable(Component component) {
        JComponent jComponent = (JComponent) component;
        BindingComponent bnd = (BindingComponent) jComponent.getClientProperty(BindingComponent.ATTR_BND);
        boolean isReadOnly = false;
        if (bnd != null) {
            isReadOnly = bnd.isUiReadOnly();
        }
        return !isReadOnly && (jComponent.isVisible() && jComponent.isEnabled() && !(jComponent instanceof JLabel));
    }

    private void addListComponentsFor(Container aContainer) {
        List<Component> components = new ArrayList();
        Component[] cmps = aContainer.getComponents();
        for (Component cmp : cmps) {
            if (isFocusableTraversal(cmp)) {
                components.add(cmp);
            }
        }
        sortComponents(components);
        containerComponents.put(aContainer, components);
    }

    private void sortComponents(List<Component> components) {
        Collections.sort(components, new ComponentComparator());
    }

    private boolean isFocusableTraversal(Component cmp) {
        if (cmp instanceof JTextComponent ||
                cmp instanceof JComboBox ||
                cmp instanceof JCheckBox ||
                cmp instanceof JTable) {
            return true;
        }
        return false;
    }

    public Component getComponentBefore(Container aContainer, Component aComponent) {
        Container parent = aComponent.getParent();
        if (!containerComponents.containsKey(parent)) {
            addListComponentsFor(parent);
        }
        List<Component> cmps = containerComponents.get(parent);
        int index = cmps.indexOf(aComponent);
        return getPreviousComponent(cmps, index == 0 ? cmps.size() - 1 : index - 1);
    }

    private Component getPreviousComponent(List<Component> cmps, int index) {
        for (int i = index; i >= 0; i--) {
            Component component = cmps.get(i);
            if (isFocusable(component)) {
                return component;
            }
        }
        int maxIndex = cmps.size() - 1;
        if (index == maxIndex) {
            return null;
        }
        return getPreviousComponent(cmps, maxIndex);
    }


    public Component getFirstComponent(Container aContainer) {
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst == null || pst.isReadOnly()) {
            return null;
        }
        Component cmp = pst.getFistCmpToBeFocused();
        if (cmp != null) {
            return cmp;
        }
        return null;
    }

    public Component getLastComponent(Container aContainer) {
        return null;
    }


    Presenter lastPst = null;
    Component lastDefaultCmp = null;

    public Component getDefaultComponent(Container aContainer) {
        if (aContainer instanceof DlgMensaje) {
            return null;
        }
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        if (pst == null || pst.isReadOnly()) {
            return null;
        }
        if (pst == lastPst) {
            return lastDefaultCmp;
        }
        Component cmp = pst.getFistCmpToBeFocused();
        if (cmp != null) {
            return cmp;
        }

        containerComponents = new HashMap();
        JPanel mainPanel = (JPanel) pst.getIpView().getComponentFullScan("pnlMain");

        Component focusabledCmp = getFocusabledComponent(mainPanel);
        if (focusabledCmp == null) return null;
        if (focusabledCmp instanceof JTable) {
            JTable jTable = (JTable) focusabledCmp;
            if (jTable.getRowCount() > 0) {
                jTable.setRowSelectionInterval(0, 0);
            }
            pst.getAWFocusManager().focusGained(jTable);
        }
        lastPst = pst;
        lastDefaultCmp = focusabledCmp;
        return focusabledCmp;
    }


    private Component getFocusabledComponent(JComponent mainPanel) {
        if (mainPanel.getComponents() == null || mainPanel.getComponents().length == 0) {
            return null;
        }
        List<Component> sortedCmps = Arrays.asList(mainPanel.getComponents());
        sortComponents(sortedCmps);
        for (Component component : sortedCmps) {
            if (isFocusableTraversal(component) && isFocusable(component)) {
                return component;
            }
        }

        for (Component component : sortedCmps) {
            if (component instanceof JComponent) {
                Component cmp = getFocusabledComponent((JComponent) component);
                if (cmp != null) {
                    return cmp;
                }
            }
        }
        return null;
    }

}
