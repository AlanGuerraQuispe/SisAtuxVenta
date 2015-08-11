package com.table.other;

/**
 * User: gmc
 * Date: 22/05/2009
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ToolTipEscapeBug extends JDialog {
  public ToolTipEscapeBug(){
    super((Frame)null, true);

    // Add VK_ESCPAPE key listener
    JRootPane pane = this.getRootPane();
    pane.registerKeyboardAction(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          ToolTipEscapeBug.this.setVisible(false);
          System.exit(-1);
        }
      },
      KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
      JComponent.WHEN_IN_FOCUSED_WINDOW
    );

    // Handle closure ourself
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    // Some GUI
    JButton okBtn = new JButton("Meesa OK");
    okBtn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        System.exit(0);
      }
    });

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(okBtn, BorderLayout.CENTER);

    // If the next line is commented out, the dialog closes when you press
    // the ESCAPE key in WinNT
    // Note: Try not to point out that the ESCAPE key removes the tooltip.
    // Wait for a few seconds, allow the tooltip to go away by itself, then
    // press escape.
    okBtn.setToolTipText("Meesa a bug");  // tool tips consume escape key

    this.setSize(100, 100);
    this.setLocation(100, 100);
    this.validate();
  }

  public static void main(String[] args) {
    ToolTipEscapeBug bug = new ToolTipEscapeBug();
    bug.setVisible(true);   // modal
  }

}

