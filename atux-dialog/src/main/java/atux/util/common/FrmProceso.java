package atux.util.common;

import javax.swing.*;
import java.awt.*;

public class FrmProceso extends JFrame  {

  String strTitulo=null;  

  public JLabel jLabel1 = new JLabel();

  public FrmProceso() {
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public FrmProceso(String pTitulo) {
    try {
      strTitulo=pTitulo;
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }


  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(355, 90));
    jLabel1.setBounds(new Rectangle(65, 5, 225, 55));
    jLabel1.setBackground(Color.white);
    if(strTitulo==null){
      this.setTitle("Procesando Información. Por favor, espere ...");
    }else{
      this.setTitle(strTitulo);
    }
    this.getContentPane().add(jLabel1, null);
  }
  
}