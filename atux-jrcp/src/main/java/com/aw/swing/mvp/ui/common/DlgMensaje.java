/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.ui.common;


import javax.swing.*;
import java.awt.*;


public class DlgMensaje extends JDialog {
    FrmMessage message = new FrmMessage();

    public DlgMensaje(String title) {
        this(null, title, false);
    }

    public DlgMensaje(Window parent, String title, boolean modal) {
        super(parent, title, ModalityType.DOCUMENT_MODAL);
        try {
            jbInit(title);
            setUndecorated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(String title) throws Exception {

        this.setSize(new Dimension(385, 113));
        this.getContentPane().setLayout(new BorderLayout());
        this.setFont(new Font("Arial", 0, 12));
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().add(message.pnlMain);
    }

    /**
     * Get the components of the view
     *
     * @return
     */
    public Component[] getJComponents() {
        return this.getContentPane().getComponents();
    }

    public void closeWindow() {
        this.setVisible(false);
        this.dispose();
    }

    public JLabel getLblMessage() {
        return message.lblMensaje;
    }

    public static void main(String[] args) {
        new DlgMensaje("Documento de Venta").setVisible(true);
    }
}
