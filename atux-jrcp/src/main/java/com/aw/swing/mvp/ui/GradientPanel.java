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

import com.aw.swing.g2d.GraphicsUtil;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: Juan Carlos Vergara
 * Date: 03-nov-2007
 * Time: 15:14:37
 */
public class GradientPanel extends JPanel {

    boolean allowChangeSize;
    //Delta for manage the resize event
    public static final int GRADIENTE_DELTA_CHANGE_SIZE = 700;
    public static final int GRADIENTE_DELTA_ = 300;
    ////////////////////////////////////////////////////////////////////////////
    // THEME SPECIFIC FIELDS
    ////////////////////////////////////////////////////////////////////////////


    @InjectedResource
//    protected LinearGradientPaint backgroundGradient;
    protected GradientPaint backgroundGradient;

    @InjectedResource
    protected BufferedImage light;

    @InjectedResource
    protected float lightOpacity;

    // Intermediate image to avoid gradient repaints
    protected Image gradientImage = null;

    public GradientPanel() {
        ResourceInjector.get().inject(this);
        ResourceInjector.get().inject(true, new Object[]{GradientPanel.this});
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isVisible()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        if (gradientImage == null) {
            int gradienteDelta = getGradienteDelta();
            // Only create this once; this assumes that the size of this
            // container never changes
            gradientImage = GraphicsUtil.createCompatibleImage(getWidth()+gradienteDelta, getHeight());
            Graphics2D g2d = (Graphics2D) gradientImage.getGraphics();
            Composite composite = g2.getComposite();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setPaint(backgroundGradient);
            g2d.fillRect(0, 0, getWidth()+gradienteDelta, getHeight());
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    lightOpacity));
            drawImage(g2d);
            g2d.setComposite(composite);
            g2d.dispose();
            // !!! ONLY BECAUSE WE NEVER RECREATE THE INTERMEDIATE IMAGE
            light = null;
        }

        g2.drawImage(gradientImage, 0, 0, null);
    }

    private int getGradienteDelta() {
        if(allowChangeSize){
            return GRADIENTE_DELTA_CHANGE_SIZE;
        }
        return GRADIENTE_DELTA_;
    }

    protected void drawImage(Graphics2D g2d) {
        g2d.drawImage(light, 0, 0, getWidth()+getGradienteDelta(), light.getHeight(), null);
    }

    public void add(Component comp, Object constraints) {
        super.add(comp, constraints);
        if (comp instanceof JLabel){
            comp.setForeground(new ColorUIResource(42, 56, 91));
        }
    }


    public boolean isAllowChangeSize() {
        return allowChangeSize;
    }

    public void setAllowChangeSize(boolean allowChangeSize) {
        this.allowChangeSize = allowChangeSize;
    }
}
