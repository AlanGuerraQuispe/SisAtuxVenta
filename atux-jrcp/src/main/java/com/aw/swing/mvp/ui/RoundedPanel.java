package com.aw.swing.mvp.ui;

import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class RoundedPanel extends JXPanel {
    private final int cornerRadius;
    private boolean contentAreaFilled;

    private transient RoundRectangle2D.Float roundBounds;

    public RoundedPanel() {
        this(new BorderLayout());
    }

    public RoundedPanel(LayoutManager layout) {
        this(layout, 10);
    }

    public RoundedPanel(int cornerRadius) {
        this(new FlowLayout(), cornerRadius);
    }

    public RoundedPanel(LayoutManager layout, int cornerRadius) {
        super(layout);
//        this.setBorder(new RoundedBorder(cornerRadius));
        this.cornerRadius = cornerRadius;
        this.roundBounds = new RoundRectangle2D.Float(0, 0, 0, 0,
                cornerRadius, cornerRadius);
        this.contentAreaFilled = true;
        setOpaque(false);
    }

    public void setContentAreaFilled(boolean contentFilled) {
        this.contentAreaFilled = contentFilled;
    }

    public boolean isContentAreaFilled() {
        return contentAreaFilled;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isContentAreaFilled()) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension size = getSize();
            roundBounds.width = size.width;
            roundBounds.height = size.height;
            g2.setColor(getBackground());
            g2.fill(roundBounds);
        }
        super.paintComponent(g);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        RoundedPanel p = new RoundedPanel(new BorderLayout(), 16);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.blue);
        p.add(p2);
        frame.add(p);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
