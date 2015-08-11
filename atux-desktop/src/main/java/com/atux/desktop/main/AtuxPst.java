package com.atux.desktop.main;

import atux.managerbd.Mac;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import com.atux.comun.context.AppCtx;
import com.atux.config.SPSysProp;
import com.atux.desktop.seguridad.LoginPst;
import com.aw.core.util.StringUtils;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 19/05/2009
 */
public class AtuxPst {
    protected static final Log logger = LogFactory.getLog(AtuxPst.class);

    FrmAtuxV2 frmAtux;

    private void createFrame() {
        List menus = new ArrayList();
        frmAtux = new FrmAtuxV2(menus);
        String version = "1.0";
        //String version = StringUtils.nvl(VersionInfo.instance().getVersion(), "¿ver?");
        AtuxUtility.validaPCName();
        String enviroment = StringUtils.nvl(System.getProperty(SPSysProp.ATUX_ENVIROMENT), "¿ENV?");
        String bdInfo = StringUtils.nvl("TEST", "¿BD?");
        String debugInfo = SPSysProp.isDeveloperDebugEnabled() ? " /DEBUGMODE " : "";
        String appInfo = "[V:" + version + "/Env:" + enviroment + "/BD:" + bdInfo + debugInfo + "]";
        frmAtux.setTitle("Atux " + /*appInfo version + " " +*/ " " + AppCtx.instance().getUsuario().getIdUsuario() +" - IP : [ " + AtuxVariables.vIP_PC.trim()+" ]");
        final MenuBuilder mb = new MenuBuilder();
        frmAtux.setJMenuBar(mb.createMenus());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frmAtux.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frmAtux.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frmAtux.setVisible(true);

//        Mac mac = new Mac();
//        mac.VisualizaVentana(frmAtux);

    }

    public static AtuxPst inicializarContexto() {
        new LookAndFeelManager().initialize();

        //DistApp.main(null);
        Application.instance().init();

        LoginPst loginPst = PstMgr.instance().getPst(LoginPst.class);
        String testUsr = System.getProperty(SPSysProp.ATUX_AUTOLOGIN_USR);
        String testPwd = System.getProperty(SPSysProp.ATUX_AUTOLOGIN_PWD);
        loginPst.init();
        if (errorEnLogin(loginPst)) {
            return null;
        }

        AtuxPst pst = new AtuxPst();
        Application.instance().autowireFields(pst);
        pst.createFrame();

        return pst;
    }

    private static boolean errorEnLogin(LoginPst presenter) {
        return !presenter.isValidUser();
    }

    public static void runApp() {
        if ("true".equals(System.getProperty(SPSysProp.AW_EDT_CHECKER_ENABLED))) {
            System.out.println("\n*************************\n Instalando EDT Checker\n**************************");
            org.fest.swing.edt.FailOnThreadViolationRepaintManager.install();
        }

        try {
            AtuxPst pst = inicializarContexto();

            if (pst == null) {
                // fallo inicializacion
                System.exit(0);
            }
        } finally {
            ProcessMsgBlocker.instance().removeMessage();
        }
    }

    public static void main(String[] args) {
//        runApp();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runApp();
            }
        });

    }

}
