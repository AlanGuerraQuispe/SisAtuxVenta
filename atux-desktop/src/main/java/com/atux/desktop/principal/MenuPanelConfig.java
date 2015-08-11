package com.atux.desktop.principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class MenuPanelConfig extends MenuItem {
    protected static final Log logger = LogFactory.getLog(MenuPanelConfig.class);

    public MenuPanelConfig(String name) {
        this.imagen = "/menu/index.png";
        this.name = name;
    }

    public void actionPerformed(ActionEvent e) {
        logger.debug("Activating Menu Panel " + name);
        TransitionManager.activateMenu(name);
    }

    public String getName() {
        return name;
    }
}
