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
import javax.swing.text.JTextComponent;
import java.awt.*;

public class SwingUtils {
    /**
     * Locates the given jComponent on the screen's center.
     *
     * @param component the jComponent to be centered
     */
    public static void locateOnScreenCenter(Component component) {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation(
                (screenSize.width - paneSize.width) / 2,
                (screenSize.height - paneSize.height) / 2);
    }

    public static void locateRelativeToMenu(Component component){
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation(
                (screenSize.width - paneSize.width) / 2,
                (screenSize.height - paneSize.height) / 2 + 10);        
    }

    /**
     * Return true if the jComponent could receive the focus
     *
     * @param component
     * @return
     */
    public static boolean isFocusable(Component component) {
        if (component instanceof JTextComponent || component instanceof JComboBox ||
                component instanceof JCheckBox ||
                component instanceof JTable
                ) {
            return true;
        }
        return false;
    }

    /**
     * Return true if the jComponent is a container
     *
     * @param component
     * @return
     */
    public static boolean isContainer(Component component) {
        if (component instanceof JPanel || component instanceof JTabbedPane
                || component instanceof JRootPane || component instanceof JLayeredPane
                || component instanceof JScrollPane || component instanceof JViewport
                )
            return true;

        return false;
    }

//    public static void executeAndWaitInEDT(Runnable task){
//        if (SwingUtilities.isEventDispatchThread()){
//            task.run();
//        } else {
//            try {
//                SwingUtilities.invokeAndWait(task);
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }
//    }

}
