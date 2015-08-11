package com.aw.swing.mvp.ui.laf;

import com.aw.swing.mvp.util.GUIUtilities;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class GUIConstants {
    public static final String TITLE_GRADIENT_COLOR1_KEY = "titleGradientColor1";
    public static final String TITLE_GRADIENT_COLOR2_KEY = "titleGradientColor2";

    public static final String TITLE_FOREGROUND_KEY = "titleForegroundColor";

    public static final Border EMPTY_BORDER = new EmptyBorder(0, 0, 0, 0);

    public static final String TITLE_FONT_KEY = "titleFont";

    public static final Border chiselBorder = new ChiselBorder();
    public static final Border panelBorder = new CompoundBorder(
            chiselBorder, new EmptyBorder(6,8,6,0));
    public static final Border categoryBorder = new CompoundBorder(
            chiselBorder, new EmptyBorder(0,0,10,0));
    public static final Border buttonBorder = new CompoundBorder(
            new DemoButtonBorder(), new EmptyBorder(0, 18, 0, 0));

    public static final String CONTROL_MID_SHADOW_KEY = "controlVeryDarkShadowColor";

    private static class ChiselBorder implements Border {
        private Insets insets = new Insets(1, 0, 1, 0);

        public ChiselBorder() {
        }

        public Insets getBorderInsets(Component c) {
            return insets;
        }
        public boolean isBorderOpaque() {
            return true;
        }
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color color = c.getBackground();
            // render highlight at top
            g.setColor(GUIUtilities.deriveColorHSB(color, 0, 0, .2f));
            g.drawLine(x, y, x + width, y);
            // render shadow on bottom
            g.setColor(GUIUtilities.deriveColorHSB(color, 0, 0, -.2f));
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }
    }

    private static class DemoButtonBorder implements Border {
        private Insets insets = new Insets(2, 1, 1, 1);

        public DemoButtonBorder() {
        }

        public Insets getBorderInsets(Component c) {
            return insets;
        }
        public boolean isBorderOpaque() {
            return true;
        }
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            AbstractButton b = (AbstractButton)c;
            if (b.isSelected()) {
                Color color = c.getBackground();
                g.setColor(GUIUtilities.deriveColorHSB(color, 0, 0, -.20f));
                g.drawLine(x, y, x + width, y);
                g.setColor(GUIUtilities.deriveColorHSB(color, 0, 0, -.10f));
                g.drawLine(x, y + 1, x + width, y + 1);
                g.drawLine(x, y + 2, x, y + height - 2);
                g.setColor(GUIUtilities.deriveColorHSB(color, 0, 0, .24f));
                g.drawLine(x, y + height - 1, x + width, y + height-1);
            }
        }
    }
}
