package com.atux.desktop.principal;

import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: Juan Carlos Vergara
 * Date: 20/01/2010
 */
public class NavigationPanel extends JPanel {
    @InjectedResource
    private Color lightColor;
    @InjectedResource
    private Color shadowColor;
    @InjectedResource
    private BufferedImage backgroundGradient;

    public NavigationPanel() {
        ResourceInjector.get().inject(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setupGraphics(g2);
        paintBackground(g2);
    }

    private static void setupGraphics(final Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private void paintBackground(final Graphics2D g2) {
        int height = backgroundGradient.getHeight();

        Rectangle bounds = g2.getClipBounds();
        g2.drawImage(backgroundGradient,
                (int) bounds.getX(), 0,
                (int) bounds.getWidth(), height,
                null);

        g2.setColor(lightColor);
        g2.drawLine(0, height, getWidth(), height);

        g2.setColor(shadowColor);
        g2.drawLine(0, height + 1, getWidth(), height + 1);
    }
}
