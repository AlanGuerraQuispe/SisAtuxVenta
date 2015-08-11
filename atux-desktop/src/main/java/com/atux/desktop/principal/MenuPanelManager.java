package com.atux.desktop.principal;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class MenuPanelManager {
    Map panels = new HashMap();

    public void createPanels(MenuPanelConfig menuPanelConfig) {
        processItem(menuPanelConfig);
    }

    private void processItem(MenuPanelConfig menuPanelConfig){
        MenuPanel panel = new MenuPanel(menuPanelConfig.getName());
        panels.put(menuPanelConfig.getName(), panel);
        List menuItems = menuPanelConfig.items();
        panel.createMenuItems(menuItems);

        for (int i = 0; i < menuItems.size(); i++) {
            Object o =  menuItems.get(i);
            if (o instanceof MenuPanelConfig){
                processItem((MenuPanelConfig)o);
            }
        }
    }

    public JPanel getPanel(String name){
        return (JPanel)panels.get(name);
    }
}
