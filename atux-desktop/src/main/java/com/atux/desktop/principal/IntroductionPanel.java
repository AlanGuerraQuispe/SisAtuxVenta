package com.atux.desktop.principal;

import com.atux.config.SPSysProp;
import com.aw.swing.g2d.GraphicsUtil;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

//import com.atux.dominio.comun.SVSingleSignOn;
//import com.atux.dominio.entity.UsuarioTmImpl;
//import com.sider.security.service.SiderSecurityLdapDaoAuthenticationProvider;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class IntroductionPanel extends JComponent {
    protected static final Log logger = LogFactory.getLog(IntroductionPanel.class);
    
    @InjectedResource
    private LinearGradientPaint backgroundGradient;
    @InjectedResource
    private BufferedImage logo;

    private final JComponent clip;
    private float fade = 0.0f;
    private float fadeOut = 0.0f;
    private BufferedImage gradientImage;



//    @Autowired
//    SiderSecurityLdapDaoAuthenticationProvider ldapAuthzProvider;





    IntroductionPanel(final JComponent clip) {
        ResourceInjector.get().inject(this);
        this.clip = clip;
//        setOpaque(false);

        this.iconWidth = waitCircle.getWidth();
        this.iconHeight = waitCircle.getHeight();        
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if (visible) {
            startFadeIn();

            loadingTimer = new Animator(1500, Double.POSITIVE_INFINITY, Animator.RepeatBehavior.LOOP, new LoadingMessenger());
            loadingTimer.start();
        }
    }

    private static class AuthData {
    }

    private static AuthData data = new AuthData();

//    private class SpringContextLoader extends org.jdesktop.swingx.util.SwingWorker<AuthData, Object> {
    private class SpringContextLoader extends SwingWorker<AuthData, Object> {
        @SuppressWarnings("unchecked")
        @Override
        public AuthData doInBackground() {
            Application.instance().init();
            Application.instance().autowireFields(IntroductionPanel.this);
            startFadeOut();
            
            return new AuthData();
        }

        @Override
        protected void done() {
        }
    }

    private void verificaSSO(){
        String testUsr = System.getProperty(SPSysProp.ATUX_AUTOLOGIN_USR);
        String testPwd = System.getProperty(SPSysProp.ATUX_AUTOLOGIN_PWD);

    }


    private void doLogin(String username, String passsword, boolean externo) {

    }



    public void setAuditInfo(Object auth) {
        String nameOS = "os.name";
        String versionOS = "os.version";

        try {
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            logger.info("Obteniendo host info: " + i);
            String os = System.getProperty(nameOS) + " " + System.getProperty(versionOS);
            logger.info("Obteniendo OS: " + os);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startFadeIn() {
        Animator timer = PropertySetter.createAnimator(2000,this,"fade", 0.0f, 1.0f);
        timer.addTarget(new TimingTarget() {
            public void end() {
                showLoadingMsg = true;
                (new SpringContextLoader()).execute();
                //startFadeOut();
            }

            public void begin() {
            }

            public void timingEvent(float arg2) {
            }
            public void repeat() {
            }
        });
        timer.setAcceleration(0.1f);
        timer.setDeceleration(0.3f);
        timer.start();
    }

    private void startFadeOut() {
        Animator timer = PropertySetter.createAnimator(1200,this,"fadeOut", 0.0f, 1.0f);
        timer.addTarget(new TimingTarget() {
            public void end() {
//                verificaSSO();
            }

            public void begin() {
                if (loadingTimer != null){
                    loadingTimer.stop();
                    showLoadingMsg = false;
                }
            }

            public void timingEvent(float arg2) {
            }
            public void repeat() {
            }
        });
        timer.setAcceleration(0.7f);
        timer.setDeceleration(0.3f);
        timer.start();
    }

    public void setFade(float fade) {
        this.fade  = fade;
        repaint();
    }

    public float getFade() {
        return fade;
    }

    public void setFadeOut(float fadeOut) {
        this.fadeOut  = fadeOut;
        repaint();
    }

    public float getFadeOut() {
        return fadeOut;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setupGraphics(g2);
        paintBackground(g2);
        paintLogo(g2);
        paintForeground(g2);

        int y = -320;
        float x = paintText(g2, y);
        paintIcon(g2, x, y);
    }

    private void paintForeground(Graphics2D g2) {
        if (fadeOut > 0.0f) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_OFF);
            //jcv:color fondo a constante
            g2.setColor(new Color(0.12f, 0.33f, 0.54f, fadeOut));
            Rectangle rect = g2.getClipBounds().intersection(getClipBounds());
            g2.fillRect(rect.x, rect.y, rect.width, rect.height);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    private static void setupGraphics(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void paintLogo(Graphics2D g2) {
        int x = (getWidth() - logo.getWidth()) / 2;
        int y = (getHeight() - logo.getHeight()) / 2;

        Composite composite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
        g2.drawImage(logo, x, y, null);
        g2.setComposite(composite);
    }

    private Rectangle getClipBounds() {
        Point point = clip.getLocation();
        point = SwingUtilities.convertPoint(clip, point, this);
        return new Rectangle(point.x, point.y, clip.getWidth(), clip.getHeight());
    }

    private void paintBackground(Graphics2D g2) {
        Rectangle rect = getClipBounds();
        if (gradientImage == null) {
            gradientImage = GraphicsUtil.createCompatibleImage(rect.width, rect.height);
            Graphics2D g2d = (Graphics2D) gradientImage.getGraphics();
            g2d.setPaint(backgroundGradient);
            g2d.fillRect(0, 0, rect.width, rect.height);
            g2d.dispose();
        }

        g2.drawImage(gradientImage, rect.x, rect.y, null);
    }

    private double loadingRotation;
    private String text = "SumiNet est� Iniciando...";
    private int iconHeight;
    private int iconWidth;

    @InjectedResource
    private BufferedImage waitCircle;

    @InjectedResource
    private Font messageFont;

    @InjectedResource
    private Color messageColor;

    private Animator loadingTimer;

    private boolean showLoadingMsg = false;
    
    private void paintIcon(Graphics2D g2, float x, int y) {
        if (!showLoadingMsg) return;
        Rectangle rect = getClipBounds();
        Graphics2D g2d = (Graphics2D) g2.create();
        g2d.rotate(loadingRotation * 2.0 * Math.PI,
                   x + iconWidth / 2.0,
                   y + rect.getY() + rect.getHeight() / 2.0);
        g2d.drawImage(waitCircle,
                      (int) x, y + (int) (rect.getY() +
                                         (rect.getHeight() - iconHeight) / 2.0),
                      null);
    }

    private float paintText(Graphics2D g2, int y) {
        if (!showLoadingMsg) return 0;
        g2.setFont(messageFont);
        FontRenderContext context = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(text, messageFont, context);
        Rectangle2D bounds = layout.getBounds();

        Rectangle rect = getClipBounds();
        float x = rect.x + (rect.width - waitCircle.getWidth(this) -
                            (float) bounds.getWidth()) / 2.0f;

        g2.setColor(messageColor);
        layout.draw(g2,
                    x + iconWidth + 10,
                    y + layout.getAscent() +
                    rect.y + (rect.height - layout.getAscent() -
                              layout.getDescent()) / 2.0f);

        return x;
    }

    private class LoadingMessenger implements TimingTarget {
        public void timingEvent(float fraction) {
            loadingRotation = fraction - fraction % (1.0 / 12.0);
//            System.out.println("fraction= " + fraction);
//            System.out.println("Loading rotation= " + loadingRotation);
            repaint();
        }
        public void repeat() {
        }

        public void begin() {
        }

        public void end() {
        }
    }
}
