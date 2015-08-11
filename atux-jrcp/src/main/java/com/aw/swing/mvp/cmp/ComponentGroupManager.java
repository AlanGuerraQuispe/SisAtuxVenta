package com.aw.swing.mvp.cmp;


import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;

/**
 * User: Julio Gonzales
 * Date: 05-abr-2008
 */
public class ComponentGroupManager {

    List<ComponentGroup> listOfComponentGroup;


    public ComponentGroupManager(List<ComponentGroup> listOfComponentGroup) {
        this.listOfComponentGroup = listOfComponentGroup;
    }

    public void disableOtherGroups(JComponent jComponent) {
        for (Iterator<ComponentGroup> componentGroupIterator = listOfComponentGroup.iterator(); componentGroupIterator.hasNext();)
        {
            ComponentGroup componentGroup = componentGroupIterator.next();

            if (!componentGroup.getListOfComponents().contains(jComponent)) {
                componentGroup.disable();
            }
//            }
        }
    }

    public void enableAllGroups() {
        for (Iterator<ComponentGroup> componentGroupIterator = listOfComponentGroup.iterator(); componentGroupIterator.hasNext();)
        {
            ComponentGroup componentGroup = componentGroupIterator.next();
            componentGroup.enable();
        }
    }

    public void addKeyListenersForTxt() {
        for (Iterator<ComponentGroup> componentGroupIterator = listOfComponentGroup.iterator(); componentGroupIterator.hasNext();)
        {
            final ComponentGroup componentGroup = componentGroupIterator.next();
            KeyListener kl = new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    if (!enableGroupIfNecesary((JComponent) e.getSource()))
                        disableOtherGroups((JComponent) e.getSource());
                }
            };
            componentGroup.addKeyListener(kl);
        }
    }

    private boolean enableGroupIfNecesary(JComponent jComponent) {

        boolean isAllEmpty = true;

        JTextField jTextField = (JTextField) jComponent;
        if (!StringUtils.hasText(jTextField.getText())) {
            isAllEmpty = isAllEmpty && true;
        } else isAllEmpty = false;

        if (isAllEmpty) enableAllGroups();
        return isAllEmpty;
    }

}
