package atux.vistas.utilitario;

import atux.controllers.CUsuario;
import atux.modelbd.Usuario;
import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class CambiarPassword extends javax.swing.JPanel {
    private CUsuario cus;
    private Usuario us;
    private int seActualizo = -1;
    private JOptionPane op;

    public CambiarPassword(Usuario us) {
        initComponents();
        this.us = us;
        this.cus = new CUsuario();
        lbAviso.setVisible(false);
    }
    
    public JLabel getlblAviso()
    {
        return lbAviso;
    }


    public CambiarPassword() {
        initComponents();
        //this.us = us;
        this.cus = new CUsuario();
        lbAviso.setVisible(false);
    }
    
   public int getSeActualizo() {
        return seActualizo;
    }
    
    private void getOptionPane()
    {
        if(op != null)
        {
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }
    


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        passActual = new elaprendiz.gui.passwordField.PasswordField();
        jLabel2 = new javax.swing.JLabel();
        passNueva = new elaprendiz.gui.passwordField.PasswordField();
        jLabel3 = new javax.swing.JLabel();
        passRepetir = new elaprendiz.gui.passwordField.PasswordField();
        jPanel3 = new javax.swing.JPanel();
        bntGuardar = new elaprendiz.gui.button.ButtonRect();
        bntCancelar = new elaprendiz.gui.button.ButtonRect();

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Proveedor");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 373));
        setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Contraseña Actual:");

        passActual.setPreferredSize(new java.awt.Dimension(250, 21));
        passActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passActualKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Contraseña Nueva:");

        passNueva.setPreferredSize(new java.awt.Dimension(250, 21));
        passNueva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passNuevaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Repita La Contraseña:");

        passRepetir.setPreferredSize(new java.awt.Dimension(250, 21));
        passRepetir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passRepetirKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(passNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(passRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCurves1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        bntGuardar.setBackground(new java.awt.Color(51, 153, 255));
        bntGuardar.setText("Guardar");
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });

        bntCancelar.setBackground(new java.awt.Color(51, 153, 255));
        bntCancelar.setText("Cancelar");
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 100, Short.MAX_VALUE)
                    .addComponent(bntGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(67, 67, 67)
                    .addComponent(bntCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 99, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bntGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        panelCurves1.add(jPanel3, java.awt.BorderLayout.CENTER);

        panelImage1.add(panelCurves1, java.awt.BorderLayout.CENTER);

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void bntGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGuardarActionPerformed
 String pas = String.copyValueOf(this.passActual.getPassword());
 String pasN = String.copyValueOf(this.passNueva.getPassword());
 String pasR = String.copyValueOf(this.passRepetir.getPassword());
    if(!pas.isEmpty() && !pasN.isEmpty() && !pasR.isEmpty())
    {       
        //if(pas.equals(us.desencriptarPass())) //TODO aguerra
        if(pas.equals(us.getDeClave()))
        {
            if(pasN.equals(pasR))
            {
                //us.setClave(pasN);
                //us.encriptarPass();
                int ap = cus.actualizarPass(us);
                if(ap != 0)
                {
                  seActualizo = 1;  
                  getOptionPane();
                  op.setValue(1);
                }
            }else{
                lbAviso.setVisible(true);
                lbAviso.setText("Las contraseÃ±as no coinciden.");
            }
        }else{
            lbAviso.setVisible(true);
            lbAviso.setText("ContraseÃ±a Incorrecta");
            passActual.requestFocus();
        }
    }else{
        lbAviso.setVisible(true);
        lbAviso.setText("No deje espacios en blanco");
        passActual.requestFocus();
    }
}//GEN-LAST:event_bntGuardarActionPerformed

private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
  getOptionPane();  
  op.setValue(1);
}//GEN-LAST:event_bntCancelarActionPerformed

private void passActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passActualKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: passNueva.requestFocus();
             break;
    }    
}//GEN-LAST:event_passActualKeyReleased

private void passNuevaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passNuevaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: passRepetir.requestFocus();
             break;
    }    
}//GEN-LAST:event_passNuevaKeyReleased

private void passRepetirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passRepetirKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: bntGuardar.requestFocus();
             break;
    }  
}//GEN-LAST:event_passRepetirKeyReleased


    
    public JLabel getAviso()
    {
        return lbAviso;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntCancelar;
    private elaprendiz.gui.button.ButtonRect bntGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.passwordField.PasswordField passActual;
    private elaprendiz.gui.passwordField.PasswordField passNueva;
    private elaprendiz.gui.passwordField.PasswordField passRepetir;
    // End of variables declaration//GEN-END:variables
     
}
