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

import com.aw.swing.g2d.Reflection;
import org.jdesktop.fuse.ResourceInjector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Juan Carlos Vergara
 * Date: 03-nov-2007
 * Time: 17:15:53
 * To change this template use File | Settings | File Templates.
 */
public class FormIcon extends JLabel {

    ////////////////////////////////////////////////////////////////////////////
    // THEME SPECIFIC FIELDS
    ////////////////////////////////////////////////////////////////////////////
//    @InjectedResource
//    private float shadowOpacity;
//    @InjectedResource
//    private Color shadowColor;
//    @InjectedResource
//    private int shadowDistance;
//    @InjectedResource
//    private int shadowDirection;
//    @InjectedResource
//    private Font categoryFont;
//    @InjectedResource
//    private Font categorySmallFont;
//    @InjectedResource
//    private float categorySmallOpacity;
//    @InjectedResource
//    private Color categoryColor;
//    @InjectedResource
//    private Color categoryHighlightColor;

        private Dimension componentDimension = new Dimension(0, 0);
        private BufferedImage image;
        private Rectangle clickable;

    private float shadowOffsetX=3;
    private float shadowOffsetY=3;


        private float ghostValue = 0.0f;
        private float newFraction = 0.0f;

        private int distance_r;
        private int distance_g;
        private int distance_b;

//        private int color_r = 255;
//        private int color_g = 255;
//        private int color_b = 255;

        private final String imageName;

        public FormIcon(String imageName) {
            super();
            ResourceInjector.get().inject(this);
            this.imageName = imageName;
            this.setOpaque(false);
            //this.setVisible(false);

            //new ImageFetcher().execute();

            getImage();

//            color_r = categoryColor.getRed();
//            color_g = categoryColor.getGreen();
//            color_b = categoryColor.getBlue();

//            addMouseListener(new MouseClickHandler());
//            addMouseMotionListener(new GhostHandler());
//            HiglightHandler higlightHandler = new HiglightHandler();
//            addMouseListener(higlightHandler);
//            addMouseMotionListener(higlightHandler);
        }

        private void getImage() {
            try {
                this.image = ImageIO.read(getClass().getResource(imageName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage mask = Reflection.createGradientMask(image.getWidth(),
                                                               image.getHeight());
            //this.image = Reflection.createShortReflectedPicture(image, mask);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        /*@Override
        public Dimension getPreferredSize() {
            return componentDimension;
        }*/

        @Override
        protected void paintComponent(Graphics g) {
            if (!isVisible()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            setupGraphics(g2);

            //float y = paintText(g2);
            paintImage(g2, 40.0f);
        }

        private void paintImage(Graphics2D g2, float y) {
            Insets insets = getInsets();

//            if (ghostValue > 0.0f) {
//                int newWidth = (int) (image.getWidth() * (1.0 + ghostValue / 2.0));
//                int newHeight = (int) (image.getHeight() * (1.0 + ghostValue / 2.0));
//
//                Composite composite = g2.getComposite();
//                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                                                           0.7f * (1.0f - ghostValue)));
//                g2.drawImage(image,
//                             insets.left + (image.getWidth() - newWidth) / 2,
//                             4 + (int) (y - newHeight / (5.0 / 3.0)) -
//                             (image.getWidth() - newWidth) / 2,
//                             newWidth, newHeight, null);
//                g2.setComposite(composite);
//            }

            g2.drawImage(image, null,
                         insets.left+4,
                         -8 + (int) (y - image.getHeight() / (5.0 / 3.0)));
        }

//        private float paintText(Graphics2D g2) {
//            g2.setFont(categoryFont);
//
//            Insets insets = getInsets();
//
//            FontRenderContext context = g2.getFontRenderContext();
//            TextLayout layout = new TextLayout(name,categoryFont, context);
//
//            float x = image.getWidth() + 14.0f;
//            x += insets.left;
//            float y = 10.0f + layout.getAscent() - layout.getDescent();
//            y += insets.top;
//
//            g2.setColor(shadowColor);
//            Composite composite = g2.getComposite();
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                                                       shadowOpacity));
//            layout.draw(g2, shadowOffsetX + x, shadowOffsetY + y);
//            g2.setComposite(composite);
//
//            g2.setColor(new Color(color_r, color_g, color_b));
//            layout.draw(g2, x, y);
//            y += layout.getDescent();
//
//            layout = new TextLayout(description == null ? " " : description,
//                                    categorySmallFont, context);
//            y += layout.getAscent();
//            composite = g2.getComposite();
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                                                       categorySmallOpacity));
//            layout.draw(g2, x, y);
//            g2.setComposite(composite);
//
//            return y;
//        }

        private void setupGraphics(Graphics2D g2) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
}