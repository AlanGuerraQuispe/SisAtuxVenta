package com.atux.desktop.principal;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.ActionEvent;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class MenuPanelItem extends MenuItem {
    protected static final Log logger = LogFactory.getLog(MenuPanelItem.class);

    Class pstClazz;

    public MenuPanelItem(Class presenter, String name) {
        this.imagen = "/menu/note.png";
        this.pstClazz = presenter;
        this.name = name;
    }

    public void actionPerformed(ActionEvent e) {
        logger.debug("Activating presenter: " + pstClazz);
        ProcessMsgBlocker.instance().showMessage("Procesando ...");
        Presenter presenter = PstMgr.instance().getPst(pstClazz);
        presenter.setBackBean(presenter.createBackBean());
        AWWindowsManager.instance().initFlowManagerFrom(presenter,e.getSource());
        presenter.init();
    }
}
