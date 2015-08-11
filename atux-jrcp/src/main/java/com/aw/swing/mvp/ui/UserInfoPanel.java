package com.aw.swing.mvp.ui;

import com.aw.swing.context.SwingContext;
import com.aw.swing.g2d.GraphicsUtil;
import com.aw.swing.g2d.ShadowRenderer;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.Formatter;
import java.util.Iterator;

/**
 * User: Juan Carlos Vergara
 * Date: 23-nov-2007
 * Time: 16:05:47
 */
public class UserInfoPanel extends JXPanel {

    private BufferedImage shadow;

    private BaseUserInfo userInfo;

    public UserInfoPanel() {
        setPreferredSize(new Dimension(300, 300));
        setOpaque(false);
        setAlpha(0.9f);
//        setVisible(false);
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(shadow.getWidth(), shadow.getHeight());
//    }

//    public void setVisible(boolean visible) {
//        super.setVisible(visible);
//        if (visible) {
//            startFadeIn();
//        }
//    }

//    public void startFadeIn() {
//        Cycle cycle = new Cycle(1500, 10);
//        Envelope envelope = new Envelope(1, 0,
//                                         Envelope.RepeatBehavior.FORWARD,
//                                         Envelope.EndBehavior.HOLD);
//        PropertyRange fadeRange = PropertyRange.createPropertyRangeFloat("alpha", 0.0f, 0.8f);
//        TimingController timer = new TimingController(cycle, envelope,
//                                                      new ObjectModifier(this, fadeRange));
//        timer.setAcceleration(0.4f);
//        timer.setDeceleration(0.1f);
//        timer.start();
//    }

    @Override
    protected void paintComponent(Graphics g) {
        int x = 34;
        int y = 10;
        int w = (int) getPreferredSize().getWidth() - 48;
        int h = (int) getPreferredSize().getHeight() - 58;
        int arc = 15;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

//        g2.setColor(new Color(217, 50, 34));
        g2.setColor(new Color(0, 117, 159));
        g2.fillRoundRect(x, y, w, h, arc, arc);

        g2.setStroke(new BasicStroke(3f));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x, y, w, h, arc, arc);

        if (shadow != null) {
            int xOffset = (shadow.getWidth() - w) / 2;
            int yOffset = (shadow.getHeight() - h) / 2;
            g2.drawImage(shadow, x - xOffset, y - yOffset, null);
        }

        Iterator it = userInfo.getIterator();
        int yAxis=28;
        while (it.hasNext()) {
            String s = (String)it.next();
            paintText(s, g2, yAxis);
            yAxis+=18;
        }



        g2.dispose();
    }

//    public void fadeIn() {
//        setVisible(true);
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                Animator animator = PropertySetter.createAnimator(
//                    400, detailPanel, "alpha", 1.0f);
//                animator.setAcceleration(0.2f);
//                animator.setDeceleration(0.3f);
//                animator.addTarget(
//                    new PropertySetter(DetailsView.this, "alpha", 1.0f));
//                animator.start();
//            }
//        });
//    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        int w = (int) getPreferredSize().getWidth() - 48;
        int h = (int) getPreferredSize().getHeight() - 58;
        int arc = 15;
        int shadowSize = 5;

        shadow = GraphicsUtil.createTranslucentCompatibleImage(w, h);
        Graphics2D g2 = shadow.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, w, h, arc, arc);
        g2.dispose();

        ShadowRenderer renderer = new ShadowRenderer(shadowSize, 1.0f, Color.BLACK);
        shadow = renderer.createShadow(shadow);

        g2 = shadow.createGraphics();
        g2.setColor(Color.RED);
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRoundRect(shadowSize, shadowSize, w, h, arc, arc);
        g2.dispose();
    }


    private void paintText(String text, Graphics2D g2, int y) {
        FontRenderContext context = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(text, (Font)UIManager.get("Label.font"), context);
        layout.draw(g2, 50, y);
    }

    public void setUserInfo(BaseUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static void main(String[] args) {
        SwingContext.initPLAF();
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new BorderLayout());

        BaseUserInfo info = new BaseUserInfo();
        StringBuilder sb = new StringBuilder();

        Formatter formatter = new Formatter(sb);
        formatter.format("Bienvenido, %s", "jcvergara");
        info.addLine(sb.toString());

        sb = new StringBuilder();
        formatter = new Formatter(sb);
        formatter.format("%s ", "San Juan de Miraflores");
        info.addLine(sb.toString());

        sb = new StringBuilder();
        formatter = new Formatter(sb);
        formatter.format("Tipo Cambio al %s: %s ", "21/11/2007", "S/. 3.09");
        info.addLine(sb.toString());


        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        dialog.getContentPane().add(gradientPanel, BorderLayout.CENTER);
        gradientPanel.add(new JLabel("Texto Norte"), BorderLayout.CENTER);

        FrmUserInfo frmUserInfo = new FrmUserInfo();
        ((UserInfoPanel)frmUserInfo.pnlUserInfo).setUserInfo(info);

        dialog.setGlassPane(frmUserInfo.pnlMain);
        dialog.getGlassPane().setVisible(true);
        dialog.setSize(500, 500);
        dialog.setVisible(true);
    }
}
