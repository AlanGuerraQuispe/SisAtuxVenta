package com.atux.desktop.principal;

import com.atux.config.SPSysProp;
import com.aw.core.spring.ApplicationBase;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

//import com.atux.desktop.comun.infraestructura.VerificadorInstanciaUnica;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class MainFrame extends JFrame {
    public static final String PRINCIPAL = "Principal";

    ImageIcon imageIcon = new ImageIcon(MainFrame.class.getResource("/images/main-suministros.png"));
    ImageIcon imageIconBig = new ImageIcon(MainFrame.class.getResource("/images/main-suministros-big.png"));

    private final TransitionPanel transitionPanel;
    private Component originalOverlay;


//    private ServerSocket serverSocket;

    public MainFrame(final TransitionPanel transPanel) {
        super("Suminet - Menú Principal");

        if(!SPSysProp.permiteMultiplesInstancias()){
//            serverSocket = VerificadorInstanciaUnica.verificar(this);
        }

        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupFrameIcons();
        installDestroyAppCtxHandler();

        ContentPanel contentPanel = new ContentPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(transPanel, BorderLayout.CENTER);
        contentPanel.add(new Footer(), BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new FrameBorder());
        setContentPane(panel);

//        TitlePanel titlePanel = new TitlePanel();
//        add(titlePanel, BorderLayout.NORTH);
//        titlePanel.installListeners();

        add(contentPanel, BorderLayout.CENTER);

//        setSize(1022, 720);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds ());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.originalOverlay = getGlassPane();
        this.transitionPanel = transPanel;

        AWWindowsManager.instance().setFrame(this);
    }

    private void installDestroyAppCtxHandler(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing Spring Application Context...");
                try {
                    ApplicationBase.instance().destroyAppCtx();
                } catch (Exception ex){
                    System.out.println("Error closing Spring Context");
                }
                try {
//                    serverSocket.close();
                } catch (Exception ex){
                    System.out.println("Error closing server socket");
                }
                System.exit(0);
            }
        });
    }

    private void setupFrameIcons() {
        java.util.List list = new ArrayList();
        list.add(imageIcon.getImage());
        list.add(imageIconBig.getImage());
        this.setIconImages(list);
    }

    void showIntroduction() {
        setGlassPane(new IntroductionPanel(transitionPanel));
        getGlassPane().setVisible(true);
        this.transitionPanel.setContentVisible(false);
    }

    void showTransitionPanel() {
        this.transitionPanel.createPanelManager();
        this.transitionPanel.setContentVisible(true);
    }

    void showWaitOverlay() {
        setGlassPane(new WaitOverlay(transitionPanel));
        getGlassPane().setSize(getSize());
        getGlassPane().validate();
    }

    void hideWaitOverlay() {
        getGlassPane().setVisible(false);
    }

    void killOverlay() {
        setGlassPane(originalOverlay);
    }

    void showLoginOverlay() {

    }



}
