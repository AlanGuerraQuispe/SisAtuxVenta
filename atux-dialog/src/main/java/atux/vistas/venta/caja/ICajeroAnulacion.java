/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ICajeroAnulacion.java
 *
 * Created on 15/12/2014, 03:23:53 PM
 */
package atux.vistas;

import atux.controllers.CSimpleModelo;
import atux.modelbd.Usuario;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */
public class ICajeroAnulacion extends javax.swing.JDialog {
    
    private CSimpleModelo cCajero; 
    private DefaultComboBoxModel mCajero; 
    private final Log logger = LogFactory.getLog(getClass());
    Usuario usuario;
    
    public ICajeroAnulacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageCajeroAnulacion = new elaprendiz.gui.panel.PanelImage();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        panelRectTranslucido1 = new elaprendiz.gui.panel.PanelRectTranslucido();
        lblCajero = new javax.swing.JLabel();
        cmbCajero = new javax.swing.JComboBox();
        lblCaja = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        lblTurno = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        txtMotivo = new javax.swing.JTextField();
        lblTurno1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImageCajeroAnulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Aceptar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
            }
        });

        lblCajero.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCajero.setForeground(new java.awt.Color(255, 255, 255));
        lblCajero.setText("Cajero");

        cmbCajero.setFont(new java.awt.Font("Tahoma", 0, 12));
        cmbCajero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCajero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCajeroItemStateChanged(evt);
            }
        });

        lblCaja.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCaja.setForeground(new java.awt.Color(255, 255, 255));
        lblCaja.setText("Caja");

        txtCaja.setEditable(false);

        lblTurno.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno.setText("Turno");

        txtTurno.setEditable(false);
        txtTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTurnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTurnoKeyTyped(evt);
            }
        });

        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });

        lblTurno1.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTurno1.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno1.setText("Motivo anulación");

        javax.swing.GroupLayout panelRectTranslucido1Layout = new javax.swing.GroupLayout(panelRectTranslucido1);
        panelRectTranslucido1.setLayout(panelRectTranslucido1Layout);
        panelRectTranslucido1Layout.setHorizontalGroup(
            panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTurno1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addComponent(lblCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCajero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelRectTranslucido1Layout.setVerticalGroup(
            panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCajero)
                    .addComponent(lblCajero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaja, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurno1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTurno, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout panelImageCajeroAnulacionLayout = new javax.swing.GroupLayout(panelImageCajeroAnulacion);
        panelImageCajeroAnulacion.setLayout(panelImageCajeroAnulacionLayout);
        panelImageCajeroAnulacionLayout.setHorizontalGroup(
            panelImageCajeroAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageCajeroAnulacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImageCajeroAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageCajeroAnulacionLayout.createSequentialGroup()
                        .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageCajeroAnulacionLayout.createSequentialGroup()
                        .addComponent(panelRectTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelImageCajeroAnulacionLayout.setVerticalGroup(
            panelImageCajeroAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageCajeroAnulacionLayout.createSequentialGroup()
                .addComponent(panelRectTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImageCajeroAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageCajeroAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageCajeroAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cCajero = new CSimpleModelo();
        mCajero = new DefaultComboBoxModel(cCajero.getCajero().toArray());
        this.cmbCajero.setModel(mCajero);
        
        usuario  = (Usuario)cmbCajero.getSelectedItem();        
        AtuxVariables.vNuSecUsuarioCajero = usuario.getNuSecUsuario();
        
        txtCaja.setText(usuario.getNuCaja());
        txtTurno.setText(usuario.getNuTurno());
        
        AtuxUtility.moveFocus(cmbCajero);


    }//GEN-LAST:event_formWindowOpened

    private void txtTurnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)  
            AtuxUtility.moveFocus(txtMotivo);
    }//GEN-LAST:event_txtTurnoKeyReleased

    private void txtTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyTyped
        AtuxUtility.admitirDigitos(txtTurno,evt);
    }//GEN-LAST:event_txtTurnoKeyTyped

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)  AtuxUtility.moveFocus(cmbCajero);
    }//GEN-LAST:event_txtMotivoKeyReleased

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
        
    if ( txtCaja.getText().trim().length()==0 ) {
      JOptionPane.showMessageDialog(this, "El Número de Caja NO es válido. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
      AtuxUtility.moveFocus(cmbCajero);
      return;
    }
    if ( txtTurno.getText().trim().length()==0 || Integer.parseInt(txtTurno.getText().trim())==0 ) {
      JOptionPane.showMessageDialog(this, "El Número de Turno NO es válido. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
      AtuxUtility.moveFocus(txtTurno);
      return;
    }

    if ( AtuxUtility.rptaConfirmDialog(this,"Seguro de realizar la Asignación de la Anulación ?" + "\nCajero : " + usuario.getNombreCompleto() + "\nCaja Número : " + txtCaja.getText().trim() + "\nTurno : " + txtTurno.getText().trim()) ) {       
        AtuxVariables.vNuSecUsuarioCajero = usuario.getNuSecUsuario();        
        AtuxVariables.vNuCaja = txtCaja.getText().trim();
        AtuxVariables.vNuTurno = txtTurno.getText().trim();
	
        if (!existeCajeroCajaTurno()) {
	    JOptionPane.showMessageDialog(this, "El cajero seleccionado no está asociado a dicha caja y turno. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
	    AtuxUtility.moveFocus(txtTurno);
	    return;
	}

      closeWindow(true);
    } else {
      closeWindow(false);
    }
 
    }//GEN-LAST:event_bntAceptarActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        closeWindow(false);
    }//GEN-LAST:event_bntSalirActionPerformed

    private void cmbCajeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCajeroItemStateChanged
        usuario  = (Usuario)cmbCajero.getSelectedItem();        
        AtuxVariables.vNuSecUsuarioCajero = usuario.getNuSecUsuario();
        
        txtCaja.setText(usuario.getNuCaja());
        txtTurno.setText(usuario.getNuTurno());
        AtuxUtility.moveFocus(txtMotivo);   
    }//GEN-LAST:event_cmbCajeroItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.JComboBox cmbCajero;
    private javax.swing.JLabel lblCaja;
    private javax.swing.JLabel lblCajero;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JLabel lblTurno1;
    private elaprendiz.gui.panel.PanelImage panelImageCajeroAnulacion;
    private elaprendiz.gui.panel.PanelRectTranslucido panelRectTranslucido1;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables
    
   void closeWindow(boolean pAceptar) {
    AtuxVariables.vAceptar = pAceptar;
    this.setVisible(false);
    this.dispose();
  }
   
  boolean existeCajeroCajaTurno(){
      
       logger.info("vNuCaja=" + AtuxVariables.vNuCaja);
       logger.info("vNuTurno=" + AtuxVariables.vNuTurno);
       logger.info("vNuSecUsuarioCajero=" + AtuxVariables.vNuSecUsuarioCajero);                        
        try {
                String strExisteRelacion = AtuxDBUtility.getValueAt("VTTV_MOVIMIENTO_CAJA",
                                                                    "COUNT(*)",
                                                                    "     CO_COMPANIA    = '" + AtuxVariables.vCodigoCompania + "'" +
                                                                    " AND CO_LOCAL       = '" + AtuxVariables.vCodigoLocal + "'" +
                                                                    " AND NU_CAJA        = "  + usuario.getNuCaja() +
                                                                    " AND NU_TURNO       = "  + usuario.getNuTurno() +
                                                                    " AND NU_SEC_USUARIO = '" + usuario.getNuSecUsuario() + "'" +
                                                                    " AND FE_DIA_VENTA= TO_DATE(TO_CHAR(SYSDATE,'dd/MM/yyyy'),'dd/MM/yyyy')" +
                                                                    " AND TI_MOV_CAJA = 'A'");                
                logger.info("ExisteRelacion=" + strExisteRelacion);
                if (strExisteRelacion.equalsIgnoreCase("1"))
                        return true;
                else
                        return false;

        } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al verificar validez de Cajero, caja y turno - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    } 
  
}

