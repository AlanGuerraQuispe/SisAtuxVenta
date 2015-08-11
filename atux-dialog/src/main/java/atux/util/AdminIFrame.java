package atux.util;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

public class AdminIFrame {
    
    public static void mostrarVentanaCentrada(JDesktopPane dp,JInternalFrame vnt)
    {        
        if(vnt != null && !vnt.isShowing())
       {
           vnt.show();
           dp.remove(vnt);
            try{
                dp.add(vnt, JLayeredPane.DEFAULT_LAYER);                  
            }catch(IllegalArgumentException ex){               
                dp.add(vnt, JLayeredPane.DEFAULT_LAYER);                 
                System.out.println(" IllegalArgumentException");
            }    
       }
        
        if(vnt == null)
        {          
          dp.add(vnt, JLayeredPane.DEFAULT_LAYER);          
        } 
       activarVentana(dp,vnt);
       centrar(dp,vnt);
    }
    
    private static void activarVentana(JDesktopPane dp,JInternalFrame vnt)
    {
        try {
            vnt.setSelected(true);            
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }       
    }
    
    private static void centrar(JDesktopPane desktop, JInternalFrame vnt) {
        Dimension pantalla = desktop.getSize();        
        Dimension ventana =  vnt.getContentPane().getSize();        
        vnt.setLocation(
                (pantalla.width - ventana.width) / 2,
                ((pantalla.height - ventana.height) / 2) -40);
        
        vnt.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);                
        vnt.setVisible(true);        
                
    }        
    
}
