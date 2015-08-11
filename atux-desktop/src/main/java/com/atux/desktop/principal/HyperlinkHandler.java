package com.atux.desktop.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class HyperlinkHandler implements MouseListener, MouseMotionListener {
    private static final HyperlinkHandler INSTANCE = new HyperlinkHandler();
    private static final String CURSOR_BOUNDS = "cursor_bounds";

    static void add(JComponent component, Rectangle bounds) {
        component.addMouseListener(INSTANCE);
        component.addMouseMotionListener(INSTANCE);
        component.putClientProperty(CURSOR_BOUNDS, bounds);
    }

    static void remove(JComponent component) {
        component.removeMouseListener(INSTANCE);
        component.removeMouseMotionListener(INSTANCE);
        component.putClientProperty(CURSOR_BOUNDS, null);

        setDefaultCursor(component);
    }

    private static void setDefaultCursor(JComponent component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private static void setHyperlinkCursor(JComponent c) {
        c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        Rectangle r = (Rectangle) c.getClientProperty(CURSOR_BOUNDS);

        if (r == null || r.contains(e.getPoint())) {
            setHyperlinkCursor(c);
        }
    }

    public void mouseExited(MouseEvent e) {
        setDefaultCursor((JComponent) e.getSource());
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        Rectangle r = (Rectangle) c.getClientProperty(CURSOR_BOUNDS);

        if (r != null) {
            if (c instanceof MenuPanel.MenuButton && !c.isEnabled()){
                setDefaultCursor(c);
                return;
            }

            if (r.contains(e.getPoint())) {
                setHyperlinkCursor(c);
            } else {
                setDefaultCursor(c);
            }
        }
    }
}
