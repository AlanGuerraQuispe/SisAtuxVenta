package com.aw.swing.mvp.ui;

import com.aw.swing.mvp.util.GUIUtilities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class RoundedTitleBorder extends RoundedBorder{
     private final String title;
    private final Color[] titleGradientColors;

    public RoundedTitleBorder(String title, Color titleGradientColor1, Color titleGradientColor2) {
        super(10);
        this.title = title;
        this.titleGradientColors = new Color[2];
        this.titleGradientColors[0] = titleGradientColor1;
        this.titleGradientColors[1] = titleGradientColor2;
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        Insets borderInsets = super.getBorderInsets(c, insets);
        borderInsets.top = getTitleHeight(c);
        return borderInsets;
    }

    protected int getTitleHeight(Component c) {
        FontMetrics metrics = c.getFontMetrics(c.getFont());
        return (int)(metrics.getHeight() * 1.80);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int titleHeight = getTitleHeight(c);

        // create image with vertical gradient and then use alpha composite to
        // render the image with a horizontal fade
        BufferedImage titleImage = GUIUtilities.createTranslucentImage(width, titleHeight);
        GradientPaint gradient = new GradientPaint(0, 0,
                titleGradientColors[0], 0, titleHeight,
                titleGradientColors[1], false);
        Graphics2D g2 = (Graphics2D)titleImage.getGraphics();
        g2.setPaint(gradient);
        g2.fillRoundRect(x, y, width, height, 10, 10);
        g2.setColor(GUIUtilities.deriveColorHSB(
                titleGradientColors[1], 0, 0, -.2f));
        g2.drawLine(x + 1, titleHeight - 1, width - 2, titleHeight - 1);
        g2.setColor(GUIUtilities.deriveColorHSB(
                titleGradientColors[1], 0, -.5f, .5f));
        g2.drawLine(x + 1, titleHeight, width - 2, titleHeight);
        g2.setPaint(new GradientPaint(0, 0, new Color(0.0f, 0.0f, 0.0f, 1.0f),
                width, 0, new Color(0.0f, 0.0f, 0.0f, 0.0f)));
        g2.setComposite(AlphaComposite.DstIn);
        g2.fillRect(x, y, width, titleHeight);
        g2.dispose();

        g.drawImage(titleImage, x, y, c);

        // draw rounded border
        super.paintBorder(c, g, x, y, width, height);

        // draw title string
        g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(c.getForeground());
        g2.setFont(c.getFont());
        FontMetrics metrics = c.getFontMetrics(c.getFont());
        g2.drawString(title, x + 8,
                y + (titleHeight - metrics.getHeight())/2 + metrics.getAscent());
        g2.dispose();

    }
}
