package com.atux.desktop.principal;

import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
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

    Footer() {
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
