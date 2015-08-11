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
package com.aw.swing.mvp.ui;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 04/11/2007
 * Time: 06:19:12 PM
 */
public class ErrorPanel extends JPanel {
    private static final int HEIGHT_PNL_ERROR = 20;
    private JLabel lblErrorMessage;
    private JPanel errorContent = new JPanel();

    public ErrorPanel() {
        createErrorPanel();
    }

     private void createErrorPanel() {
//        errorPanel = new JPanel();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        hideErrorMessage();
        this.add(errorContent, BorderLayout.CENTER);
//        errorContent.setBackground(new Color(246,123,123));
        errorContent.setBackground(new Color(255,0,0));
        errorContent.add(createErrorLabel());
//        showErrorMessage("This is a error message");
    }

     public JLabel createErrorLabel() {
        lblErrorMessage = new JLabel();
        lblErrorMessage.setFont(new Font(lblErrorMessage.getFont().getName(), Font.BOLD, lblErrorMessage.getFont().getSize()));
        lblErrorMessage.setForeground(new Color(255,255,255));
        lblErrorMessage.setVerticalTextPosition(1);
        lblErrorMessage.setMinimumSize(new Dimension(128, 14));
        lblErrorMessage.setText("El campo es requerido");
        lblErrorMessage.setName("lblErrorMessage");

        return lblErrorMessage;
    }

    public void hideErrorMessage() {
        this.setVisible(false);
        this.setMinimumSize(new Dimension(0, 0));
        this.setPreferredSize(new Dimension(0, 0));
    }

    public void showErrorMessage(String message) {
        lblErrorMessage.setText(message);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(0, 0)); //Hide
        this.setPreferredSize(new Dimension(0, 0));
    }
}
