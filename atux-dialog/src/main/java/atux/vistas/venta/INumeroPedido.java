/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * INumeroPedido.java
 *
 * Created on 01/12/2014, 04:07:45 PM
 */
package atux.vistas.venta;

import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class INumeroPedido extends javax.swing.JDialog {

    /** Creates new form INumeroPedido */
    public INumeroPedido(java.awt.Frame parent,String titulo ,boolean modal) {
        super(parent,titulo,modal);
        initComponents();
        AtuxUtility.centrarVentana(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        btnContinuar.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageNumeroPedido = new elaprendiz.gui.panel.PanelImage();
        lblNumPedido = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImageNumeroPedido.setFocusable(false);
        panelImageNumeroPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageNumeroPedido.setNextFocusableComponent(btnContinuar);
        panelImageNumeroPedido.setRequestFocusEnabled(false);

        lblNumPedido.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumPedido.setForeground(new java.awt.Color(255, 255, 255));
        lblNumPedido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumPedido.setText("9999");
        lblNumPedido.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("dd/mm/yyyy");

        btnContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/LogoMenu.png"))); // NOI18N
        btnContinuar.setBorder(null);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setFocusable(false);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImageNumeroPedidoLayout = new javax.swing.GroupLayout(panelImageNumeroPedido);
        panelImageNumeroPedido.setLayout(panelImageNumeroPedidoLayout);
        panelImageNumeroPedidoLayout.setHorizontalGroup(
            panelImageNumeroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageNumeroPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelImageNumeroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImageNumeroPedidoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImageNumeroPedidoLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblNumPedido)))
                .addContainerGap())
        );
        panelImageNumeroPedidoLayout.setVerticalGroup(
            panelImageNumeroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageNumeroPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImageNumeroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelImageNumeroPedidoLayout.createSequentialGroup()
                        .addComponent(lblNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageNumeroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageNumeroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        AtuxVariables.vAceptar = true;
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      try {
      lblFecha.setText(AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA));    
      lblNumPedido.setText(AtuxUtility.completeWithSymbol(AtuxVariables.vNumeroPedidoDiario, 4, "0", "I"));
      btnContinuar.requestFocus();
     } catch(SQLException sqlException) {
      sqlException.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error al obtener Fecha - Hora del Sistema !!! - " + sqlException.getErrorCode(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
     }
    }//GEN-LAST:event_formWindowOpened
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNumPedido;
    private elaprendiz.gui.panel.PanelImage panelImageNumeroPedido;
    // End of variables declaration//GEN-END:variables
}
