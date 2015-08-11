package com.aw.swing.mvp.ui;

import com.aw.swing.mvp.ui.laf.GUIConstants;
import com.aw.swing.mvp.util.GUIUtilities;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class ZonePanel3 extends JXPanel {
    private static final Border roundedBorder = new RoundedBorder(10);
    private static final Insets margin = new Insets(8, 10, 8, 8);

    LoadedZonePanel zonePanel;

       public ZonePanel3(String title) {

        this.zonePanel = new LoadedZonePanel(title);
        setBorder(new EmptyBorder(10,0,10,0));
        this.setLayout(new BorderLayout());
        // remind(aim): how to access resourceMap?
        //resourceMap = getContext().getResourceMap();

//        LoadAnimationPanel loadAnimationPanel = new LoadAnimationPanel();
//
//        add(loadAnimationPanel);
//        loadAnimationPanel.setAnimating(true);
//
//
//
//        try {
//            loadAnimationPanel.setAnimating(false);
//            Animator fadeOutAnimator = new Animator(400,
//                    new FadeOut(ZonePanel.this,
//                            loadAnimationPanel, zonePanel));
//            fadeOutAnimator.setAcceleration(.2f);
//            fadeOutAnimator.setDeceleration(.3f);
//            Animator fadeInAnimator = new Animator(400,
//                    new PropertySetter(ZonePanel.this, "alpha", 0.3f, 1.0f));
//            TimingTrigger.addTrigger(fadeOutAnimator, fadeInAnimator, TimingTriggerEvent.STOP);
//            fadeOutAnimator.start();
//        } catch (Exception ignore) {
//            System.err.println(ignore);
//            ignore.printStackTrace();
//        }

        this.add(zonePanel, BorderLayout.CENTER);
    }

    @Override
    public void setLayout(LayoutManager mgr) {
        if (mgr instanceof com.jgoodies.forms.layout.FormLayout){
            zonePanel.setLayout(mgr);
        } else {
            super.setLayout(mgr);
        }
    }

    @Override
    public void add(Component comp, Object constraints) {
        if (comp == zonePanel){
            super.add(comp, constraints);
            return;
        }
        zonePanel.add(comp, constraints);
//        if (constraints.equals(BorderLayout.CENTER)){
//            super.add(comp, constraints);
//        } else {
//            zonePanel.add(comp, constraints);
//        }
    }

    //    public Demo getDemo() {
//        return demo;
//    }

    private static class FadeOut extends PropertySetter {
        private JXPanel parent;
        private JXPanel out;
        private JComponent in;

        public FadeOut(JXPanel parent, JXPanel out, JComponent in) {
            super(out, "alpha", 1.0f, 0.3f);
            this.parent = parent;
            this.out = out;
            this.in = in;
        }

        public void end() {
            parent.setAlpha(0.3f);
            parent.remove(out);
            parent.add(in);
            parent.revalidate();
        }
    } // Fader

    private static class LoadAnimationPanel extends RoundedPanel {
        private String message;
        private int triState = 0;
        private boolean animating = false;
        private Animator animator;

        public LoadAnimationPanel() {
            super(10);
            setBorder(roundedBorder);
            setBackground(GUIUtilities.deriveColorHSB(
                    UIManager.getColor("Panel.background"), 0, 0, -.06f));

            // remind(aim): get from resource map
            message = "section loading";

            PropertySetter rotator = new PropertySetter(this, "triState", 0, 3);
            animator = new Animator(500, Animator.INFINITE,
                    Animator.RepeatBehavior.LOOP, rotator);
            // Don't animate gears if loading is quick
            animator.setStartDelay(200);
        }

        public void setAnimating(boolean animating) {
            this.animating = animating;
            if (animating) {
                animator.start();
            } else {
                animator.stop();
            }
        }

        public boolean isAnimating() {
            return animating;
        }

        public void setTriState(int triState) {
            this.triState = triState;
            repaint();
        }

        public int getTriState() {
            return triState;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            Dimension size = getSize();

            Color textColor = GUIUtilities.deriveColorHSB(getBackground(), 0, 0, -.3f);
            Color dotColor = GUIUtilities.deriveColorHSB(textColor, 0, .2f, -.08f);
            g2.setColor(textColor);
            g2.setFont(UIManager.getFont("Label.font").deriveFont(32f));
            FontMetrics metrics = g2.getFontMetrics();
            Rectangle2D rect = metrics.getStringBounds(message, g2);
            Rectangle2D dotRect = metrics.getStringBounds(".", g2);
            float x = (float) (size.width - (rect.getWidth() + 3 * dotRect.getWidth())) / 2;
            float y = (float) (size.height - rect.getHeight()) / 2;
            g2.drawString(message, x, y);
            int tri = getTriState();
            float dx = 0;
            for (int i = 0; i < 3; i++) {
                g2.setColor(animator.isRunning() && i == tri ?
                        dotColor :
                        textColor);
                g2.drawString(".", x + (float) (rect.getWidth() + dx), y);
                dx += dotRect.getWidth();
            }
        }
    } // LoadAnimationPanel

    private static class LoadedZonePanel extends RoundedPanel {
        private String zoneName;
        private JComponent descriptionArea;
        private JComponent zoneContent;

        public LoadedZonePanel(String zoneName) {
            super(10);
            setLayout(null);

            URL description = null;// demo.getHTMLDescription();
            if (description != null) {
                descriptionArea = createDescriptionArea(description);
                add(descriptionArea);

                zoneContent = new RoundedPanel(new BorderLayout());
                zoneContent.setBorder(roundedBorder);

            } else {
                // no description
                zoneContent = new JXPanel(new BorderLayout());
                zoneContent.setBorder(new EmptyBorder(10, 10, 10, 10));
            }
            JLabel myLabel = new JLabel("este es mi label");
            zoneContent.add(myLabel);
            add(zoneContent);

            applyDefaults(zoneName);
        }

        @Override
        public void add(Component comp, Object constraints) {
            if (comp instanceof JPanel){
                zoneContent.setSize(comp.getSize());
                zoneContent.setPreferredSize(comp.getSize());
            }
            zoneContent.add(comp, constraints);
        }
        
        @Override
        public void setLayout(LayoutManager mgr) {
            if (zoneContent != null){
                zoneContent.setLayout(mgr);
             } else {
                super.setLayout(mgr);
            }
        }

        private static JComponent createDescriptionArea(URL descriptionURL) {
            JEditorPane descriptionPane = new HTMLPanel();
            descriptionPane.setEditable(false);
            descriptionPane.setMargin(margin);
            descriptionPane.setOpaque(true);
            try {
                descriptionPane.setPage(descriptionURL);
            } catch (IOException e) {
                System.err.println("couldn't load description from URL:" + descriptionURL);
            }
            return descriptionPane;
        }

        @Override
        public void doLayout() {
            if (zoneContent != null) {
                Dimension size = getSize();
                Insets insets = getInsets();

                if (descriptionArea == null) {
                    // Make demo fill entire area within border
                    zoneContent.setBounds(insets.left, insets.top,
                            size.width - insets.left - insets.right,
                            size.height - insets.top - insets.bottom);
                } else {
                    // Split space between HTML description and running demo
                    Dimension demoSize = zoneContent.getPreferredSize();
                    int margin = insets.top / 2;
                    Rectangle bounds = new Rectangle();
                    bounds.width = Math.max(demoSize.width, (int) (size.width * .50));
                    bounds.height = Math.max(demoSize.height, size.height -
                            2 * margin);
                    bounds.x = size.width - bounds.width - margin;
                    bounds.y = margin;
                    zoneContent.setBounds(bounds);
                    descriptionArea.setBounds(insets.left, insets.top,
                            size.width - margin - insets.right - bounds.width,
                            size.height - insets.top - insets.bottom);
                }
            }
        }

        @Override
        public void updateUI() {
            super.updateUI();
//            applyDefaults();
        }

        private void applyDefaults(String zoneName) {
//            zoneName = "Ejemplo de Border";
            setBorder(new RoundedTitleBorder(zoneName,
                    UIManager.getColor(GUIConstants.TITLE_GRADIENT_COLOR1_KEY),
                    UIManager.getColor(GUIConstants.TITLE_GRADIENT_COLOR2_KEY)));

            setFont(UIManager.getFont(GUIConstants.TITLE_FONT_KEY));
            Color bg = GUIUtilities.deriveColorHSB(
                    UIManager.getColor("Panel.background"), 0, 0, -.06f);
            setBackground(bg);
            setForeground(UIManager.getColor(GUIConstants.TITLE_FOREGROUND_KEY));
            if (zoneContent != null) {
                zoneContent.setBackground(GUIUtilities.deriveColorHSB(bg, 0, 0, .04f));
            }
            if (descriptionArea != null) {
                descriptionArea.setBackground(bg);
            }
        }
    }

    public static void main(String[] args) {
    }
}
