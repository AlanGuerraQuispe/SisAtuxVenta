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

import com.aw.swing.mvp.ui.common.FrmMessage;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import org.jdesktop.fuse.InjectedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
  * User: Juan Carlos Vergara
 * Date: 03-nov-2007
 * Time: 15:14:37
  */
public class MessageBlockerPanel extends GradientPanel {
    public static String PROCESANDO = "Procesando..."; 
    public static String MENSAJE = "Por favor espere unos momentos...";

    @InjectedResource
    private float shadowOpacity;
    @InjectedResource
    private Color shadowColor;
    @InjectedResource
    private int shadowDistance;
    @InjectedResource
    private int shadowDirection;
    @InjectedResource
    private Font categoryFont;
    @InjectedResource
    private Font categorySmallFont;
    @InjectedResource
    private float categorySmallOpacity;
    @InjectedResource
    private Color categoryDisableColor;
    @InjectedResource
    private Color categoryColor;
    @InjectedResource
    private Color categoryHighlightColor;

    private float shadowOffsetX;
    private float shadowOffsetY;    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setupGraphics(g2);
        computeShadow();
        paintText(g2);
    }

    private float paintText(Graphics2D g2) {
        g2.setFont(categoryFont);

        Insets insets = getInsets();

        FontRenderContext context = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(PROCESANDO, categoryFont, context);

        float x = 12.0f;
        x += insets.left;
        float y = 4.0f + layout.getAscent() - layout.getDescent();
        y += insets.top;

        g2.setColor(shadowColor);
        Composite composite = g2.getComposite();
        g2.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, shadowOpacity));
        layout.draw(g2, shadowOffsetX + x, shadowOffsetY + y);
        g2.setComposite(composite);

        g2.setColor(categoryColor);
        layout.draw(g2, x, y);
        y += layout.getDescent();

        layout = new TextLayout(MENSAJE == null ? " " : MENSAJE,
                                categorySmallFont, context);
        y += layout.getAscent();
        composite = g2.getComposite();
        g2.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, categorySmallOpacity));
        layout.draw(g2, x, y+50);
        g2.setComposite(composite);

        return y;
    }

    private void setupGraphics(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    private void computeShadow() {
        double rads = Math.toRadians(shadowDirection);
        shadowOffsetX = (float) Math.cos(rads) * shadowDistance;
        shadowOffsetY = (float) Math.sin(rads) * shadowDistance;
    }    

    public static void main(String[] args) {

//        System.out.println("Creando");
        LookAndFeelManager lf = new LookAndFeelManager();
        lf.initialize();

        long start = System.currentTimeMillis();
        JDialog dlgTest = new JDialog();

        Container contentPane = dlgTest.getContentPane();
        contentPane.setPreferredSize(new Dimension(385, 113));
        contentPane.setLayout(new BorderLayout());

        FrmMessage mfl = new FrmMessage();
        contentPane.add(mfl.pnlMain);

        dlgTest.setSize(new Dimension(385, 113));
        dlgTest.pack();
        dlgTest.setVisible(true);
        System.out.println("Tiempo transcurrido: " + (System.currentTimeMillis()-start) );
    }
}