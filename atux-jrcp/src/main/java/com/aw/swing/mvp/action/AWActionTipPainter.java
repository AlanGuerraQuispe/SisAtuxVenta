package com.aw.swing.mvp.action;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 23/05/2009
 */
public class AWActionTipPainter {
    final static AWActionTipPainter instance = new AWActionTipPainter();

    List<ActionTipInfo> popupsInfo = new ArrayList();

    private AWActionTipPainter() {
    }

    public void showTipWindow(JComponent jComponent, String text) {
        Dimension size;
        Point screenLocation = jComponent.getLocationOnScreen();
        Point location = new Point();
        GraphicsConfiguration gc;
        gc = jComponent.getGraphicsConfiguration();
        Rectangle sBounds = gc.getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        // Take into account screen insets, decrease viewport
        sBounds.x += screenInsets.left;
        sBounds.y += screenInsets.top;
        sBounds.width -= (screenInsets.left + screenInsets.right);
        sBounds.height -= (screenInsets.top + screenInsets.bottom);

        JToolTip tip = jComponent.createToolTip();
        tip.setTipText(text);
        size = tip.getPreferredSize();
        int xPosition = (jComponent.getWidth() -size.width) /2;

        location.x = screenLocation.x + xPosition;
        location.y = screenLocation.y + 20;

        Rectangle popupRect = new Rectangle();
        popupRect.setBounds(location.x, location.y,
                size.width, size.height);

        // Fit as much of the tooltip on screen as possible
        if (location.x < sBounds.x) {
            location.x = sBounds.x;
        } else if (location.x - sBounds.x + size.width > sBounds.width) {
            location.x = sBounds.x + Math.max(0, sBounds.width - size.width);
        }
        if (location.y < sBounds.y) {
            location.y = sBounds.y;
        } else if (location.y - sBounds.y + size.height > sBounds.height) {
            location.y = sBounds.y + Math.max(0, sBounds.height - size.height);
        }

        PopupFactory popupFactory = PopupFactory.getSharedInstance();
        Popup popup = popupFactory.getPopup(jComponent, tip, location.x, location.y);
        popup.show();
        popupsInfo.add(new ActionTipInfo(popup, jComponent, text));
    }


    public void hideTipWindow() {
        for (ActionTipInfo actionTipInfo : popupsInfo) {
            actionTipInfo.popup.hide();
        }
        popupsInfo = new ArrayList();
    }

    /**
     * @return a shared <code>AWActionTipPainter</code> object
     */
    public static AWActionTipPainter instance() {
        return instance;
    }


    private static class ActionTipInfo {
        String text;
        JComponent cmp;
        Popup popup;

        private ActionTipInfo(Popup popup, JComponent cmp, String text) {
            this.popup = popup;
            this.cmp = cmp;
            this.text = text;
        }

    }


}