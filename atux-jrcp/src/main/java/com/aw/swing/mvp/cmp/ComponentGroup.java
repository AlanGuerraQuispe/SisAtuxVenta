package com.aw.swing.mvp.cmp;

import com.aw.swing.mvp.binding.BindingComponent;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

/**
 * User: Julio Gonzales
 * Date: 05-abr-2008
 */
public class ComponentGroup {
    private List<JComponent> listOfComponents;

    public ComponentGroup(List<JComponent> listOfComponents) {
        this.listOfComponents = listOfComponents;
    }

    public void disable() {
        for (Iterator<JComponent> jComponentIterator = listOfComponents.iterator(); jComponentIterator.hasNext();) {
            JComponent jComponent = jComponentIterator.next();
            BindingComponent bnd = (BindingComponent) jComponent.getClientProperty(BindingComponent.ATTR_BND);
            bnd.setUIReadOnly(true);
        }
    }

    public void enable() {
        for (Iterator<JComponent> jComponentIterator = listOfComponents.iterator(); jComponentIterator.hasNext();) {
            JComponent jComponent = jComponentIterator.next();
            BindingComponent bnd = (BindingComponent) jComponent.getClientProperty(BindingComponent.ATTR_BND);
            bnd.setUIReadOnly(false);
        }
    }


    public List<JComponent> getListOfComponents() {
        return listOfComponents;
    }

    public void setListOfComponents(List<JComponent> listOfComponents) {
        this.listOfComponents = listOfComponents;
    }

    public void addKeyListener(KeyListener kl) {
        for (Iterator<JComponent> jComponentIterator = listOfComponents.iterator(); jComponentIterator.hasNext();) {
            JComponent component = jComponentIterator.next();
            component.addKeyListener(kl);
        }
    }
}
