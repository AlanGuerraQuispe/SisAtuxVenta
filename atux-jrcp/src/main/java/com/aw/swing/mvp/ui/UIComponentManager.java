package com.aw.swing.mvp.ui;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 06/06/2009
 */
public class UIComponentManager {

    public void requestFocus(final JComponent jComponent){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                if (!jComponent.isShowing()){
                    showComponent(jComponent.getTopLevelAncestor(),jComponent,null);
                }
                if (jComponent.isShowing()){
                    jComponent.requestFocusInWindow();
                }
            }
        });
    }

    public void setSelectedRow(JTable jTable, int indexToBeSelected){
        int jtableSize = jTable.getRowCount();
        if ((indexToBeSelected > -1) && (indexToBeSelected < jtableSize)) {
            jTable.setRowSelectionInterval(indexToBeSelected, indexToBeSelected);
            Rectangle rect = jTable.getCellRect(indexToBeSelected, 0, true);
            jTable.scrollRectToVisible(rect);
        } 
    }

    private void showComponent(Container container,Component currentJcmp, final Component childJCmp) {
        if (currentJcmp instanceof JTabbedPane){
            final JTabbedPane jTabbedPane = (JTabbedPane) currentJcmp;
            jTabbedPane.setSelectedComponent(childJCmp);
            return;
        }
        Container parent = currentJcmp.getParent();
        if (parent == container){
            System.out.println("It was impossible get the JTabbedPanel");
            return;
        }
        showComponent(container,parent,currentJcmp);
    }
}
