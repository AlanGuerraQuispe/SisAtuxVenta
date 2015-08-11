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

import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 22/10/2007
 * Time: 04:59:11 PM
 */
public class Footer extends JComponent {
    ////////////////////////////////////////////////////////////////////////////
    // THEME SPECIFIC FIELDS
    ////////////////////////////////////////////////////////////////////////////
    @InjectedResource
    private GradientPaint backgroundGradient;
    @InjectedResource
    private GradientPaint inactiveBackgroundGradient;
    @InjectedResource
    private int preferredHeight;
    @InjectedResource
    private Color lightColor;
    @InjectedResource
    private Color shadowColor;
    @InjectedResource
    private Color inactiveLightColor;
    @InjectedResource
    private Color inactiveShadowColor;

    public Footer() {
        ResourceInjector.get().inject(this);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = preferredHeight;
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension size = super.getMaximumSize();
        size.height = preferredHeight;
        return size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isVisible()) {
            return;
        }

        boolean active = SwingUtilities.getWindowAncestor(this).isActive();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_OFF);

        Paint paint = g2.getPaint();
        g2.setPaint(active ? backgroundGradient : inactiveBackgroundGradient);
        Rectangle clip = g2.getClipBounds();
        clip = clip.intersection(new Rectangle(0, 2, getWidth(), getHeight()));
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
        g2.setPaint(paint);

        g2.setColor(active ? lightColor : inactiveLightColor);
        g2.drawLine(0, 0, getWidth(), 0);

        g2.setColor(active ? shadowColor : inactiveShadowColor);
        g2.drawLine(0, 1, getWidth(), 1);
    }    
}
