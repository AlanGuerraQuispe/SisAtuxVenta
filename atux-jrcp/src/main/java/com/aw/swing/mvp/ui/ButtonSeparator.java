package com.aw.swing.mvp.ui;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 21/08/2009
 */
public class ButtonSeparator extends JPanel {
    public ButtonSeparator() {
        setMinimumSize(new Dimension(10, 20));
        setPreferredSize(new Dimension(10, 20));
//        setLayout(new BorderLayout());
        JLabel separator = new JLabel();
        separator.setBackground(Color.lightGray);
        separator.setOpaque(true);
        separator.setPreferredSize(new Dimension(2, 20));
        setMaximumSize(new Dimension(2, 20));
        add(separator, BorderLayout.NORTH);
    }
}
