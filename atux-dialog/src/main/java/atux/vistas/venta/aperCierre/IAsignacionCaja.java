/*
 * IAsignacionCaja.java
 *
 * Created on 19/12/2014, 12:30:38 PM
 */
package atux.vistas.venta.aperCierre;

import atux.controllers.CSimpleModelo;
import atux.modelbd.CajaPago;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */
public class IAsignacionCaja extends javax.swing.JDialog {

    private CSimpleModelo controllerCaja; 
    private CajaPago cajaPago;
    private final Log logger = LogFactory.getLog(getClass());
    ModeloTablaSimple model;
    
    public IAsignacionCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("Configuración de Impresora");
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageCajas = new elaprendiz.gui.panel.PanelImage();
        jScrollPaneRelCajas = new javax.swing.JScrollPane();
        tblCajas = new javax.swing.JTable();
        bntAgregar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        btnAsignarCajero = new elaprendiz.gui.button.ButtonRect();
        lblCaja = new javax.swing.JLabel();
        lblCajero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImageCajas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        tblCajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Caja", "Nro orden", "Tipo", "Estado", "Indicador Lect."
            }
        ));
        tblCajas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCajasKeyReleased(evt);
            }
        });
        jScrollPaneRelCajas.setViewportView(tblCajas);

        bntAgregar.setBackground(new java.awt.Color(51, 153, 255));
        bntAgregar.setText("Agregar");

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        btnAsignarCajero.setBackground(new java.awt.Color(51, 153, 255));
        btnAsignarCajero.setText("Asignar Cajero");
        btnAsignarCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarCajeroActionPerformed(evt);
            }
        });

        lblCaja.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblCaja.setForeground(new java.awt.Color(255, 255, 255));
        lblCaja.setText("Asignado a:");

        lblCajero.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCajero.setForeground(new java.awt.Color(255, 255, 0));
        lblCajero.setText("User01");

        javax.swing.GroupLayout panelImageCajasLayout = new javax.swing.GroupLayout(panelImageCajas);
        panelImageCajas.setLayout(panelImageCajasLayout);
        panelImageCajasLayout.setHorizontalGroup(
            panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageCajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageCajasLayout.createSequentialGroup()
                        .addComponent(btnAsignarCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImageCajasLayout.createSequentialGroup()
                        .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCajero, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneRelCajas, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
        );
        panelImageCajasLayout.setVerticalGroup(
            panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageCajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addGroup(panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsignarCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panelImageCajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelImageCajasLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPaneRelCajas, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageCajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageCajas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       controllerCaja = new CSimpleModelo();        
       
       model =  new ModeloTablaSimple(controllerCaja.getRelacionCajas(), ModeloTablaSimple.CAJAS);
       tblCajas.setModel(model);
       Helper.ajustarSoloAnchoColumnas(tblCajas, ModeloTablaSimple.anchoColumnasCajas);
       AtuxUtility.setearPrimerRegistro(tblCajas, null, 0);
       
       if(tblCajas.getSelectedRow() != -1){
              cajaPago =(CajaPago)model.getFila(tblCajas.getSelectedRow());
              mostrarAsignacionCaja(cajaPago);                
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void tblCajasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCajasKeyReleased
        //AtuxGridUtils.aceptarTeclaPresionada(evt,tblCajas,null,0);
        
    if ( (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN) ||
         (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) || (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) ){
              
              cajaPago =(CajaPago)model.getFila(tblCajas.getSelectedRow());
              mostrarAsignacionCaja(cajaPago);       
      }      
    }//GEN-LAST:event_tblCajasKeyReleased

    private void btnAsignarCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarCajeroActionPerformed
         
    }//GEN-LAST:event_btnAsignarCajeroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAgregar;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private elaprendiz.gui.button.ButtonRect btnAsignarCajero;
    private javax.swing.JScrollPane jScrollPaneRelCajas;
    private javax.swing.JLabel lblCaja;
    private javax.swing.JLabel lblCajero;
    private elaprendiz.gui.panel.PanelImage panelImageCajas;
    private javax.swing.JTable tblCajas;
    // End of variables declaration//GEN-END:variables
    
    void mostrarAsignacionCaja(CajaPago cajaPago) {
    try {
      String vUsuario = AtuxSearch.getUsuarioAsignado(cajaPago.getNuCaja());
      if ( vUsuario.trim().length()>0 )  
          lblCajero.setText(vUsuario);
      else  
          lblCajero.setText("");
        } 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener usuario asignada a la caja - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
        }
   }
    
}
