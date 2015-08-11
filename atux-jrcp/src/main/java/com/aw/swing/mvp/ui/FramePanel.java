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

import com.aw.swing.mvp.navigation.AWWindowsManager;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: Juan Carlos Vergara
 * Date: 03-nov-2007
 * Time: 15:14:37
 */
public class FramePanel extends GradientPanel {
//    @InjectedResource(key="GradientPanel.backgroundGradient")
//    protected LinearGradientPaint backgroundGradient;

//    @InjectedResource(key="GradientPanel.light")
//    protected BufferedImage light;

//    @InjectedResource(key="GradientPanel.lightOpacity")
//   protected float lightOpacity;

    @InjectedResource
    protected BufferedImage companyLogo;

    public FramePanel() {
        ResourceInjector.get().inject(this);
    }

    protected void drawImage(Graphics2D g2d) {
        int screenSizeX = (int)  AWWindowsManager.instance().getFrame().getSize().getWidth();
        int screenSizeY = (int) AWWindowsManager.instance().getFrame().getSize().getHeight();

//        Toolkit tk = Toolkit.getDefaultToolkit();
//		int screenSizeX = (int) tk.getScreenSize().getWidth();
//		int screenSizeY = (int) tk.getScreenSize().getHeight();

/*
todo RAC logo de la compañia
        g2d.drawImage(companyLogo, (screenSizeX-companyLogo.getWidth())/2, (screenSizeY-companyLogo.getHeight()-50)/2,
                companyLogo.getWidth(), companyLogo.getHeight(), null);
*/
    }
}