package com.atux.desktop.principal;

import com.aw.swing.g2d.LinearGradientTypeLoader;
import org.jdesktop.fuse.ResourceInjector;
import org.jdesktop.fuse.TypeLoaderFactory;
import org.jdesktop.fuse.swing.SwingModule;

import javax.swing.*;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public class TransitionManager {
    static boolean ready = false;
    static final Object LOCK = new Object();

    private static MainFrame mainFrame;
    private static NavigationHeader navPanel;
    private static TransitionPanel transPanel;

    private TransitionManager() {
    }

    public static MainFrame createMainFrame() {
        ResourceInjector.addModule(new SwingModule());
        ResourceInjector.get().load(MainFrame.class, "/menu.properties");
        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());

        navPanel = new NavigationHeader();
        transPanel = new TransitionPanel(navPanel);
        mainFrame = new MainFrame(transPanel);

        if (System.getProperty("noIntro") == null) {
            showIntroduction();
        }

        return mainFrame;
    }

    static MainFrame getMainFrame() {
        return mainFrame;
    }

    static void showTransitionPanel() {
        mainFrame.showTransitionPanel();
    }

    static void showIntroduction() {
        mainFrame.showIntroduction();
    }

    static void showLoginOverlay() {
        showLoginOverlay(false);
    }

    static void showLoginOverlay(boolean visible) {
//        mainFrame.showLoginOverlay(visible);
    }

    static void showWaitOverlay() {
        mainFrame.showWaitOverlay();

    }

    static void hideWaitOverlay() {
        mainFrame.hideWaitOverlay();
    }

    static void killOverlay() {
        mainFrame.killOverlay();
    }

    static void showMainScreen() {
        transPanel.showMainOptionsPanel();

        navPanel.clearLinks();
        navPanel.addLink(MainFrame.PRINCIPAL);

        synchronized (TransitionManager.LOCK) {
            TransitionManager.ready = true;
            TransitionManager.LOCK.notifyAll();
        }
    }

    static void activateMenu(String menuName){
        navPanel.addLink(menuName);
        transPanel.activateMenu(menuName);
    }

    private static class AuthData {
    }

    private static AuthData data = new AuthData();


    private static class DataLoader extends org.jdesktop.swingx.util.SwingWorker<AuthData, Object> {
        private String userid;
        public DataLoader(String userId) {
            this.userid = userId;
        }

        @SuppressWarnings("unchecked")
        @Override
        public AuthData doInBackground() {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new AuthData();
        }

        @Override
        protected void done() {
            try {
                TransitionManager.showMainScreen();
                new Thread(new Runnable() {
                    public void run() {
                        while (!TransitionManager.ready) {
                            synchronized(LOCK) {
                                try {
                                    LOCK.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                TransitionManager.hideWaitOverlay();
                            }
                        });
                    }
                }).start();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
    }

}
