package com.atux.desktop.main;

import com.aw.core.spring.ApplicationBase;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 19/05/2009
 */
public class FrmAtuxV2 extends JFrame {
    public JMenuBar mnuBar;
    private JDesktopPane dp;
    private atux.util.ClockDigital clockDigital;
    private elaprendiz.gui.panel.PanelCurves panelCurves2;
    private javax.swing.JLabel lblLogoAtux;
    public JPanel pnlFooterPanel;

    ImageIcon imageIcon = new ImageIcon(FrmAtuxV2.class.getResource("/images/main-suministros.png"));
    ImageIcon imageIconBig = new ImageIcon(FrmAtuxV2.class.getResource("/images/main-suministros-big.png"));

    public FrmAtuxV2(List<JMenu> menus) {
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
        dp = new JDesktopPane();
        dp.setBackground(new java.awt.Color(51, 153, 255));
        dp.setPreferredSize(new java.awt.Dimension(1180, 710));
        lblLogoAtux = new javax.swing.JLabel();
        pnlFooterPanel = new JPanel();

        panelCurves2 = new elaprendiz.gui.panel.PanelCurves();
        panelCurves2.setBackground(new java.awt.Color(51, 153, 255));
        panelCurves2.setMinimumSize(new java.awt.Dimension(960, 600));
        panelCurves2.setPreferredSize(new java.awt.Dimension(960, 550));
        panelCurves2.setLayout(null);
        panelCurves2.setBounds(0, 0, 1250, 580);
        lblLogoAtux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/LogoAtux.png"))); // NOI18N
        lblLogoAtux.setBounds(870, 280, 310, 200);
        panelCurves2.add(lblLogoAtux);

        pnlFooterPanel.setBorder(new MatteBorder(new ImageIcon(getClass().getResource("/atux/resources/ImgMenuPrincipalPie.png")))); // NOI18N
        pnlFooterPanel.setBounds(0, 630, 1380, 40);

        dp.add(panelCurves2, JLayeredPane.DEFAULT_LAYER);
        dp.add(pnlFooterPanel, JLayeredPane.DEFAULT_LAYER);
        getContentPane().add(dp);
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
        pack();
    }

    public void initComponents(List<JMenu> menus) throws Exception {
        this.setSize(new Dimension(800, 600));
        this.setTitle("");
        this.getContentPane().setLayout(new BorderLayout());
    }

    public void configureMenus(List<JMenu> menus) {
        mnuBar = new JMenuBar();
        mnuBar.setBackground(new java.awt.Color(79, 129, 189));
        mnuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
