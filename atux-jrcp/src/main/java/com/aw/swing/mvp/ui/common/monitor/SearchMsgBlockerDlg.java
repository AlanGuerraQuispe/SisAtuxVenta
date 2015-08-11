package com.aw.swing.mvp.ui.common.monitor;

import com.aw.swing.mvp.ui.laf.LookAndFeelManager;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 15/12/2009
 */
public class SearchMsgBlockerDlg extends JDialog {
    private FrmRefreshGridMsgBlocker vsr;

    public SearchMsgBlockerDlg(Window parent, String title, Dialog.ModalityType modalityType) {
        super(parent, title, modalityType);
        try {
            jbInit(title);
            setUndecorated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(String title) throws Exception {
        vsr = new FrmRefreshGridMsgBlocker();
        this.setSize(new Dimension(485, 113));
        this.getContentPane().setLayout(new BorderLayout());
        this.setFont(new Font("Arial", 0, 12));
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(vsr.pnlMain);
        getRootPane().setDefaultButton(vsr.btnCancel);
    }

    public FrmRefreshGridMsgBlocker getVsr() {
        return vsr;
    }

    public static void main(String[] args) {
        new LookAndFeelManager().initialize();
        SearchMsgBlockerDlg dialog = new SearchMsgBlockerDlg(null, "test", ModalityType.MODELESS);
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }
}
