package com.atux.desktop.principal;

import com.aw.swing.g2d.Reflection;
import com.aw.swing.mvp.action.ActionEnableable;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class MenuPanel extends JPanel {
    private JPanel buttonPanel;
//    private AlbumSelector3D albumSelector;

    private float shadowOffsetX;
    private float shadowOffsetY;

    ////////////////////////////////////////////////////////////////////////////
    // THEME SPECIFIC FIELDS
    ////////////////////////////////////////////////////////////////////////////
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

    @InjectedResource
    private BufferedImage javaCup;    

    MenuPanel(String title) {
        ResourceInjector.get().inject(this);

        setOpaque(false);
        setLayout(new BorderLayout());

        computeShadow();

        add(BorderLayout.NORTH, buildMenuPanel(title));
    }

    private JPanel buildMenuPanel(String title) {
        JPanel contacts = new JPanel(new BorderLayout());
        contacts.setOpaque(false);
        contacts.add(BorderLayout.NORTH, new BackgroundTitle(title));
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        contacts.add(BorderLayout.CENTER, buttonPanel);
        contacts.add(BorderLayout.WEST, Box.createHorizontalStrut(60));
        contacts.add(BorderLayout.EAST, Box.createHorizontalStrut(60));
        return contacts;
    }

    private void computeShadow() {
        double rads = Math.toRadians(shadowDirection);
        shadowOffsetX = (float) Math.cos(rads) * shadowDistance;
        shadowOffsetY = (float) Math.sin(rads) * shadowDistance;
    }

    private JButton createButton(String name, String description, String image) {
        return new MenuButton(name, description, image);
    }

    void transitionIsReady() {
        //jcv: changed here
        synchronized (TransitionManager.LOCK) {
            TransitionManager.ready = true;
            TransitionManager.LOCK.notifyAll();
        }
    }

    public void createMenuItems(java.util.List menuItems) {
        int counter = 0;
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem =  (MenuItem) menuItems.get(i);
            JButton button = configButton(menuItem);
            buttonPanel.add(button, getBagConstraint(counter));
            counter++;
        }
    }

    private JButton configButton(MenuItem menuItem){
        JButton button = createButton(menuItem.getName(), menuItem.getDescripcion(), menuItem.getImagen());
        button.addActionListener(menuItem);
        return button;
    }

    //Orig: 30
    private GridBagConstraints getBagConstraint(int counter) {
        return new GridBagConstraints(counter % 2, counter / 2,
                                               1, 1,
                                               0.5, 0.5,
                                               GridBagConstraints.NORTHWEST,
                                               GridBagConstraints.HORIZONTAL,
                                               new Insets(0, 0, 0, (counter + 1) % 2 != 0 ? 10 : 0),
                                               0, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        setupGraphics(g2);

//        paintBackground(g2);
        Composite composite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        int y = (getHeight() - javaCup.getHeight()) / 3 - 60;

        g2.drawImage(javaCup,
                     (getWidth() - javaCup.getWidth()) / 2,
                     y + javaCup.getHeight() / 3, null);


        super.paintComponent(g);
    }

    private static void setupGraphics(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    public class MenuButton extends JButton implements ActionEnableable {
        private Dimension componentDimension = new Dimension(0, 0);
        private BufferedImage image;
        private BufferedImage disabledImage;
        private Rectangle clickable;
        private boolean enabled = true;
        private boolean mouseEnter = false;

        private float ghostValue = 0.0f;
        private float newFraction = 0.0f;

        private int distance_r;
        private int distance_g;
        private int distance_b;

        private int color_r;
        private int color_g;
        private int color_b;

        private int disabled_color_r;
        private int disabled_color_g;
        private int disabled_color_b;

        private final String name;
        private final String description;
        private final String imageName;

        private MenuButton(String name, String description, String imageName) {
            this.name = name;
            this.description = description;
            this.imageName = imageName;

            setFocusable(false);

            setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            new ImageFetcher().execute();

            resetFGColor();

            disabled_color_r = categoryDisableColor.getRed();
            disabled_color_g = categoryDisableColor.getGreen();
            disabled_color_b = categoryDisableColor.getBlue();

            addMouseListener(new MouseClickHandler());
            addMouseMotionListener(new GhostHandler());
            HiglightHandler higlightHandler = new HiglightHandler();
            addMouseListener(higlightHandler);
            addMouseMotionListener(higlightHandler);
        }

        private void resetFGColor() {
            color_r = categoryColor.getRed();
            color_g = categoryColor.getGreen();
            color_b = categoryColor.getBlue();
//            String msg = String.format(">>>>>> Reseting colors to: %d, %d, %d", color_r,
//                    color_g, color_b);
//            System.out.println(msg);
        }

        @Override
        public void setEnabled(boolean b) {
            super.setEnabled(b);
            enabled = b;
        }

        @Override
        protected void fireActionPerformed(ActionEvent e) {
            // consume action performed events here
            // see MouseClickHandler instead
        }

        private class ImageFetcher extends org.jdesktop.swingx.util.SwingWorker {
            @Override
            protected Object doInBackground() throws Exception {
                getImage();
                return image;
            }

            @Override
            protected void done() {
                componentDimension = computeDimension();
                revalidate();
            }
        }

        private void getImage() {
            try {
                this.image = ImageIO.read(getClass().getResource(imageName));
                this.disabledImage = ImageIO.read(getClass().getResource(getDisabledName(imageName)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage mask = Reflection.createGradientMask(image.getWidth(),
                                                               image.getHeight());
            BufferedImage disabledMask = Reflection.createGradientMask(disabledImage.getWidth(),
                                                               disabledImage.getHeight());
            this.image = Reflection.createReflectedPicture(image, mask);
            this.disabledImage = Reflection.createReflectedPicture(disabledImage, disabledMask);
        }

        private String getDisabledName(String imageName){
            int dot = imageName.indexOf(".");
//            System.out.println("Returning disabled: " + imageName.substring(0, dot) + "-disabled.png");
            return imageName.substring(0, dot) + "-disabled.png";
        }

        private Dimension computeDimension() {
            Insets insets = getInsets();

            FontMetrics metrics = getFontMetrics(categoryFont);
            Rectangle2D bounds = metrics.getMaxCharBounds(getGraphics());
            int height = (int) bounds.getHeight() + metrics.getLeading();
            int nameWidth = SwingUtilities.computeStringWidth(metrics, name);

            metrics = getFontMetrics(categorySmallFont);
            bounds = metrics.getMaxCharBounds(getGraphics());
            height += bounds.getHeight();
            int descWidth = SwingUtilities.computeStringWidth(metrics,
                                                              description == null ? "" : description);

            int width = Math.max(nameWidth, descWidth);
            width += image.getWidth() + 10;

            clickable = new Rectangle(insets.left, insets.top /*+ 4*/,
                                      width /*+ insets.left + insets.right*/,
                                      height /*+ insets.top + insets.bottom*/);
            HyperlinkHandler.add(this, clickable);

            height = Math.max(height, image.getHeight());
            height += 4;

            return new Dimension(width + insets.left + insets.right,
                                 height + insets.top + insets.bottom);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getPreferredSize() {
            return componentDimension;
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isVisible()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            setupGraphics(g2);

            float y = paintText(g2);
            paintImage(g2, y);
        }

        private void paintImage(Graphics2D g2, float y) {
            Insets insets = getInsets();

            BufferedImage currentImage = enabled ? image : disabledImage;

            if (ghostValue > 0.0f) {
                int newWidth = (int) (currentImage.getWidth() * (1.0 + ghostValue / 2.0));
                int newHeight = (int) (currentImage.getHeight() * (1.0 + ghostValue / 2.0));

                Composite composite = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                           0.7f * (1.0f - ghostValue)));
                g2.drawImage(currentImage,
                             insets.left + (currentImage.getWidth() - newWidth) / 2,
                             4 + (int) (y - newHeight / (5.0 / 3.0)) -
                             (currentImage.getWidth() - newWidth) / 2,
                             newWidth, newHeight, null);
                g2.setComposite(composite);
            }

            g2.drawImage(currentImage, null,
                         insets.left,
                         4 + (int) (y - currentImage.getHeight() / (5.0 / 3.0)));
        }

        private float paintText(Graphics2D g2) {
            g2.setFont(categoryFont);

            Insets insets = getInsets();

            FontRenderContext context = g2.getFontRenderContext();
            TextLayout layout = new TextLayout(name,
                                               categoryFont, context);

            float x = image.getWidth() + 10.0f;
            x += insets.left;
            float y = 4.0f + layout.getAscent() - layout.getDescent();
            y += insets.top;

            g2.setColor(shadowColor);
            Composite composite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                       shadowOpacity));
            layout.draw(g2, shadowOffsetX + x, shadowOffsetY + y);
            g2.setComposite(composite);

            if (enabled){
                g2.setColor(new Color(color_r, color_g, color_b));
            } else {
                g2.setColor(new Color(disabled_color_r, disabled_color_g, disabled_color_b));
            }
            layout.draw(g2, x, y);
//            y += layout.getDescent();

//            layout = new TextLayout(description == null ? " " : description,
//                                    categorySmallFont, context);
//            y += layout.getAscent();
//            composite = g2.getComposite();
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
//                                                       categorySmallOpacity));
//            layout.draw(g2, x, y);
            g2.setComposite(composite);

            return y;
        }

        private void setupGraphics(Graphics2D g2) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }

        private final class GhostHandler extends MouseMotionAdapter {
            private Animator timer;

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!enabled) {
                    return;
                }
                if (!clickable.contains(e.getPoint()) || mouseEnter) {
                    return;
                }

                if (timer != null && timer.isRunning()) {
                    return;
                }

                distance_r = categoryHighlightColor.getRed() -
                    categoryColor.getRed();
                distance_g = categoryHighlightColor.getGreen() -
                    categoryColor.getGreen();
                distance_b = categoryHighlightColor.getBlue() -
                    categoryColor.getBlue();

                timer = new Animator(450, new AnimateGhost());
                timer.start();
            }
        }

        private final class MouseClickHandler extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!enabled) return;
                if (clickable.contains(e.getPoint())) {
                    for (ActionListener l : getActionListeners()) {
                        l.actionPerformed(new ActionEvent(MenuButton.this, 462, ""));
                    }
                }
            }
        }

        private final class AnimateGhost implements TimingTarget {
            public void timingEvent(float fraction) {
                ghostValue = fraction;
                repaint();
            }

            public void begin() {
                ghostValue = 0.0f;
            }

            public void end() {
                ghostValue = 0.0f;
                repaint();
            }
            public void repeat() {
            }
        }

        private final class HiglightHandler extends MouseMotionAdapter implements MouseListener {
            private Animator timer;

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!enabled){
                    return;
                }
                if (clickable.contains(e.getPoint())) {
                    if (!mouseEnter) {
                        mouseEnter = true;
                        if (timer != null && timer.isRunning()) {
//                            resetFGColor();
                            timer.stop();
                        }
                        timer = new Animator(450, new AnimateHighlight(true));
                        timer.start();
                    }
                } else if (mouseEnter) {
                    mouseEnter = false;
                    if (timer != null && timer.isRunning()) {
//                        resetFGColor();
                        timer.stop();
                    }
                    timer = new Animator(450, new AnimateHighlight(false));
                    timer.start();
                }
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                mouseEnter = false;
            }

            public void mouseExited(MouseEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                resetFGColor();
            }
        }

        private final class AnimateHighlight implements TimingTarget {
            private boolean forward;
            private float oldValue;

            AnimateHighlight(boolean forward) {
                this.forward = forward;
                oldValue = newFraction;
            }

            public void repeat() {
            }

            public void timingEvent(float fraction) {
                newFraction = oldValue + fraction * (forward ? 1.0f : -1.0f);

                if (newFraction > 1.0f) {
                    newFraction = 1.0f;
                } else if (newFraction < 0.0f) {
                    newFraction = 0.0f;
                }

                color_r = (int) (categoryColor.getRed() + distance_r * newFraction);
                color_g = (int) (categoryColor.getGreen() + distance_g * newFraction);
                color_b = (int) (categoryColor.getBlue() + distance_b * newFraction);

                repaint();
            }

            public void begin() {
            }

            public void end() {
            }
        }
    }

}
