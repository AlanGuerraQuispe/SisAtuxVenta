package com.atux.desktop.principal;

import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class FrameBorder implements Border {
    private final Border border;
    private final Border inactiveBorder;

    ////////////////////////////////////////////////////////////////////////////
    // THEME SPECIFIC FIELDS
    ////////////////////////////////////////////////////////////////////////////
    @InjectedResource
    private Color lightColor;
    @InjectedResource
    private Color mediumColor;
    @InjectedResource
    private Color shadowColor;
    @InjectedResource
    private Color inactiveLightColor;
    @InjectedResource
    private Color inactiveMediumColor;
    @InjectedResource
    private Color inactiveShadowColor;

    FrameBorder() {
        ResourceInjector.get().inject(this);

        Border shadow = BorderFactory.createLineBorder(shadowColor);
        Border medium = BorderFactory.createLineBorder(mediumColor);
        Border light = BorderFactory.createLineBorder(lightColor);
        border = new CompoundBorder(light, new CompoundBorder(medium, shadow));

        shadow = BorderFactory.createLineBorder(inactiveShadowColor);
        medium = BorderFactory.createLineBorder(inactiveMediumColor);
        light = BorderFactory.createLineBorder(inactiveLightColor);
        inactiveBorder = new CompoundBorder(light, new CompoundBorder(medium, shadow));
    }

    public void paintBorder(Component c, Graphics g,
                            int x, int y, int width, int height) {
        if (SwingUtilities.getWindowAncestor(c).isActive()) {
            border.paintBorder(c, g, x, y, width, height);
        } else {
            inactiveBorder.paintBorder(c, g, x, y, width, height);
        }
    }

    public Insets getBorderInsets(Component c) {
        return border.getBorderInsets(c);
    }

    public boolean isBorderOpaque() {
        return border.isBorderOpaque();
    }
}
