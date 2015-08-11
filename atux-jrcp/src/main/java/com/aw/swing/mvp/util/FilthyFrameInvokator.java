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
package com.aw.swing.mvp.util;

import com.aw.swing.g2d.LinearGradientTypeLoader;
import org.jdesktop.fuse.ResourceInjector;
import org.jdesktop.fuse.TypeLoaderFactory;
import org.jdesktop.fuse.swing.SwingModule;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 22/10/2007
 * Time: 09:57:26 PM
 *
 * Util method to test individual panels
 */
public class FilthyFrameInvokator extends JFrame {
    JPanel panel = new JPanel();

    public FilthyFrameInvokator() throws HeadlessException {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 780, 614 );
        setLayout(new BorderLayout());
        panel.setSize(300, 300);
        panel.setBackground(new Color(0, 0, 0));
        add(panel);
    }

    //TODO: JCV-REMOVE-DUP
    public void init(){
        ResourceInjector.addModule(new SwingModule());
        ResourceInjector.get().load(FilthyFrameInvokator.class, "/jrcp-uitheme.properties");
        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());
    }

    public JComponent getPanel() {
        return panel;
    }

    @Override
    public void setGlassPane(Component component){
        super.setGlassPane(component);
        this.getRootPane().setGlassPane(component);
        this.getRootPane().getGlassPane().validate();
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        this.getRootPane().setVisible(visible);
    }

    public void initialize(JPanel component) {
        this.getRootPane().setGlassPane(component);
        this.getRootPane().getGlassPane().validate();
        this.getGlassPane().setVisible(true);
        this.setVisible(true);
    }
}
