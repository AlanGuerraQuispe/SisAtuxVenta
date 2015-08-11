/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Prueba.java
 *
 * Created on 03/12/2014, 06:01:51 PM
 */
package atux.vistas.venta.aperCierre;

import atux.controllers.CListadoPedido;
import atux.controllers.CObsCaja;
import atux.modelbd.ObsCaja;
import atux.modelbd.Usuario;
import atux.modelgui.ModeloTablaListadoPedido;
import atux.util.Helper;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author Omar Davila
 */
public class IListadoPedido extends javax.swing.JDialog {
    private static Usuario usuario;
    private final Log logger = LogFactory.getLog(getClass());
    private ObsCaja obs;    
    private CObsCaja controllerListadoPedido;
    private ModeloTablaListadoPedido mtped;
    private boolean CambioRealizado = false;
    private String titulo = "SA-VENTA";
    
    public IListadoPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Limpiar();
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImagePrueba = new elaprendiz.gui.panel.PanelImage();
        btnEsc = new elaprendiz.gui.button.ButtonRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cierre Caja Turno");
        setResizable(false);

        panelImagePrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        btnEsc.setMnemonic('A');
        btnEsc.setText("Esc=Salir");
        btnEsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscActionPerformed(evt);
            }
        });

        tblListado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblListado.setFont(new java.awt.Font("Tahoma", 0, 14));
        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Pedido", "#Correlativo", "Fecha", "Estado", "Caja", "Vendedor", "#Sunat", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListado.setGridColor(new java.awt.Color(0, 0, 0));
        tblListado.setRowHeight(22);
        tblListado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblListadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblListado);
        tblListado.getColumnModel().getColumn(0).setPreferredWidth(100);

        javax.swing.GroupLayout panelImagePruebaLayout = new javax.swing.GroupLayout(panelImagePrueba);
        panelImagePrueba.setLayout(panelImagePruebaLayout);
        panelImagePruebaLayout.setHorizontalGroup(
            panelImagePruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagePruebaLayout.createSequentialGroup()
                .addContainerGap(763, Short.MAX_VALUE)
                .addComponent(btnEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(panelImagePruebaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelImagePruebaLayout.setVerticalGroup(
            panelImagePruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagePruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImagePrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImagePrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnEscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscActionPerformed
// TODO add your handling code here:
    Cerrar();
}//GEN-LAST:event_btnEscActionPerformed
private void Cerrar(){
    if (CambioRealizado == true){
        if (JOptionPane.showConfirmDialog(this, "Esta seguro de Salir sin Guardar", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
            return;
        }
    }
    dispose();
}

private void tblListadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListadoKeyReleased
// TODO add your handling code here:
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ESCAPE: Cerrar();
             break;
    }
}//GEN-LAST:event_tblListadoKeyReleased

    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IListadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void ObtenerPedidos(String fecha){
        CListadoPedido ListadoPedidoCaja = new CListadoPedido();
        ArrayList Data01 = ListadoPedidoCaja.LeerPedidos(AtuxVariables.vCodigoCompania, 
                                                   AtuxVariables.vCodigoLocal, 
                                                   fecha);

        this.mtped = new ModeloTablaListadoPedido(Data01);
        tblListado.setModel(mtped);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaListadoPedido.anchoColumnas);   
       //  AtuxUtility.setearPrimerRegistro(tblListado, null, 0);

    }  

    private void Limpiar(){
        for (int i = tblListado.getRowCount() -1; i >= 0; i--)     {   
            ((DefaultTableModel) tblListado.getModel()).removeRow(i);
        }    
    }   
    
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRound btnEsc;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.panel.PanelImage panelImagePrueba;
    private javax.swing.JTable tblListado;
    // End of variables declaration//GEN-END:variables
}
