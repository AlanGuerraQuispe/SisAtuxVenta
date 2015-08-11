package com.table;

import com.table.other.AutoCompleterImpl;
import com.table.other.Java2sAutoTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 17/04/2009
 */
public class DesktopContainer {
    JFrame frame;
    JPanel panel;
    JInternalFrame iframe;
    JButton button1, button2;
    JTextField jTextField;
    JDesktopPane desk;

    public static void main(String[] args) {
        DesktopContainer d = new DesktopContainer();
    }

    public DesktopContainer() {
        frame = new JFrame("Creating a JDesktopPane Container");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iframe = new JInternalFrame("Internal frame", true, true, true, true);
        iframe.setToolTipText("This is internal frame");
        panel = new JPanel();
        button1 = new JButton("Ok");
        button1.setToolTipText("This is Ok button of internal frame");
        panel.add(button1);
        button2 = new JButton("Cancel");
        button2.setToolTipText("This is cancel button of internal frame");
        panel.add(button2);

        List values = new ArrayList();
        values.add("valor1");
        values.add("2valor21");
        values.add("2valor22");
        values.add("3valor31");
        values.add("3valor32");
        values.add("3valor33");
        values.add("3valor34");
        values.add("4valor4");
        jTextField = new Java2sAutoTextField(values);
        jTextField.setMaximumSize(new Dimension(60, 20));
        jTextField.setMinimumSize(new Dimension(60, 20));
        jTextField.setPreferredSize(new Dimension(60, 20));
        panel.add(jTextField);

        JTextField jTextField2 = new JTextField();
        jTextField2.setMaximumSize(new Dimension(60, 20));
        jTextField2.setMinimumSize(new Dimension(60, 20));
        jTextField2.setPreferredSize(new Dimension(60, 20));
        panel.add(jTextField2);
        new AutoCompleterImpl(jTextField2);


        iframe.add(panel);
        iframe.setSize(250, 300);
        iframe.setVisible(true);
        desk = new JDesktopPane();
        desk.add(iframe);
        frame.add(desk);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
