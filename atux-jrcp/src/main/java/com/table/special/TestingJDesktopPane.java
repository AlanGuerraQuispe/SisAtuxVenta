package com.table.special;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TestingJDesktopPane extends JDesktopPane {

    public TestingJDesktopPane() {
        final JInternalFrame intFrame =
                new JInternalFrame("Testing", true, true, true, true);
        intFrame.getContentPane().add(new JLabel("Testing"));
        intFrame.setSize(new Dimension(200, 200));
        intFrame.setPreferredSize(new Dimension(200, 200));
        JButton but = new JButton("Click me");
        but.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MouseInputAdapter adapter = new MouseInputAdapter() {
                };

//                JPanel glass = new JPanel();
//                glass.setOpaque(false);
//                glass.setSize(intFrame.getSize());
//                glass.setLocation(intFrame.getLocation());
//
//                glass.addMouseListener(adapter);
//                glass.addMouseMotionListener(adapter);
//
//                glass.setVisible(true);
//                TestingJDesktopPane.this.add(glass);
//                intFrame.moveToBack();
//                intFrame.setFocusable(false);
//                JOptionPane.showInternalMessageDialog(
//                        intFrame,
//                        "Testing",
//                        "Testing",
//                        JOptionPane.INFORMATION_MESSAGE);
//                System.out.println("After show message dialog");
                
                JDialog dlg = new JDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL);
                dlg.add(new JTextField());
                dlg.add(new JButton());
                dlg.setVisible(true);
                System.out.println("After show message dialog");
//                intFrame.setFocusable(true);
//                glass.setVisible(false);
            }
        });
        intFrame.getContentPane().add(but);
        intFrame.setVisible(true);

        JInternalFrame secondFrame = new JInternalFrame();
        secondFrame.setSize(200, 200);
        secondFrame.getContentPane().add(new JLabel("Second Frame"));
        secondFrame.setVisible(true);

        this.setSize(new Dimension(600, 200));
        this.setOpaque(true);
        this.add(intFrame);
        this.add(secondFrame);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Testing panel !!"));
        this.add(panel);


    }

    public static JFrame frame;
    public static void main(String args[]) {
        frame = new JFrame("Testing JDesktopPane");
        frame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        JDesktopPane jDesktopPane = new TestingJDesktopPane();
        frame.getContentPane().add(jDesktopPane);
        frame.setSize(new Dimension(600, 200));
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

