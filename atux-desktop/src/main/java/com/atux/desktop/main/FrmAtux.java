package com.atux.desktop.main;

import com.aw.core.spring.ApplicationBase;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.FramePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 19/05/2009
 */
public class FrmAtux extends JFrame {
    public JMenuBar mnuBar;
    private javax.swing.JDesktopPane dp;

    ImageIcon imageIcon = new ImageIcon(FrmAtux.class.getResource("/images/main-suministros.png"));
    ImageIcon imageIconBig = new ImageIcon(FrmAtux.class.getResource("/images/main-suministros-big.png"));

    public FrmAtux(java.util.List<JMenu> menus) {
        try {
            setupFrameIcons();
            initComponents(menus);
            initScreen();
            setName("mainPst");
            AWWindowsManager.instance().setFrame(this);
            AWWindowsManager.instance().setDp(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFrameIcons() {
        List list = new ArrayList();
        list.add(imageIcon.getImage());
        list.add(imageIconBig.getImage());
        this.setIconImages(list);
    }

    public void initScreen() {

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing Spring Application Context...");
                try {
                    ApplicationBase.instance().destroyAppCtx();
                } catch (Exception ex) {
                    System.out.println("Error closing Spring Context");
                }
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    public void initComponents(List<JMenu> menus) throws Exception {
        this.setTitle("");

        this.getContentPane().setLayout(new BorderLayout());

        FramePanel gp = new FramePanel();
        this.getContentPane().add(gp, BorderLayout.CENTER);
    }

    public void configureMenus(List<JMenu> menus) {
        mnuBar = new JMenuBar();
        mnuBar.removeAll();
        for (JMenu jMenu : menus) {
            mnuBar.add(jMenu);
        }
        mnuBar.repaint();
        this.setJMenuBar(mnuBar);

    }

    public JDesktopPane getDp() {
        return dp;
    }

    public void setDp(JDesktopPane dp) {
        this.dp = dp;
    }
}
