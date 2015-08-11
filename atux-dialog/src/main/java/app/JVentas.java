package app;

import atux.vistas.MainWindow;

/**
 * Fecha creación : 01/09/2014  v1.0
 * @author aguerra
 */
public class JVentas {
    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                MainWindow  dialog = new MainWindow();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
