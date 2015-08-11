package com.table.special;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DualModal {

    public static void main(String args[]) {
        Runnable runner = new Runnable() {
            public void run() {
                final JFrame frame1 = new JFrame("Left");
                JMenuBar menuBar = new JMenuBar();
                JMenu menu = new JMenu("Primer Menú");
                menuBar.add(menu);
                JMenuItem menuItem = new JMenuItem("A text-only menu item");
                menu.add(menuItem);
                menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("test:->>>>");
                        final JDialog jdial = new JDialog(frame1, Dialog.ModalityType.APPLICATION_MODAL);
                        JButton jButton = new JButton();
                        jdial.add(jButton);
                        jButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("test2:->>>>");
                                JDialog jdia2 = new JDialog(jdial, Dialog.ModalityType.APPLICATION_MODAL);
                                jdia2.setVisible(true);
                            }
                        });
                        jdial.setVisible(true);
                    }
                });
                frame1.setJMenuBar(menuBar);


                frame1.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                JFrame frame2 = new JFrame("Right");
//                frame2.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JButton button1 = new JButton("Left");
                JButton button2 = new JButton("Right");
                frame1.add(button1, BorderLayout.CENTER);
                frame2.add(button2, BorderLayout.CENTER);
                ActionListener listener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton source = (JButton) e.getSource();
                        String text = getNewText(source);
                        if (!JOptionPane.UNINITIALIZED_VALUE.equals(text)
                                && text.trim().length() > 0) {
                            source.setText(text);
                        }
                    }
                };
                button1.addActionListener(listener);
                button2.addActionListener(listener);
                frame1.setBounds(100, 100, 200, 200);
                frame1.setVisible(true);
                frame2.setBounds(400, 100, 200, 200);
                frame2.setVisible(true);
            }
        };
        EventQueue.invokeLater(runner);
    }

    private static String getNewText(Component parent) {
        JOptionPane pane = new JOptionPane("New label", JOptionPane.QUESTION_MESSAGE);
        pane.setWantsInput(true);
        JDialog dialog = pane.createDialog(parent, "Enter Text");
        // Uncomment line and comment out next
        //  to see application modal
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
//        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);
        return (String) pane.getInputValue();
    }
}
