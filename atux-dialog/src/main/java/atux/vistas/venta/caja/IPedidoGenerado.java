package atux.vistas.venta.caja;

import atux.controllers.CClienteDireccion;
import atux.controllers.CSimpleModelo;
import atux.modelbd.Cliente;
import atux.modelbd.ClienteDireccion;
import java.awt.Frame;

import atux.modelbd.PedidoVenta;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.buscar.JBuscarCliente;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author user
 */
public class IPedidoGenerado extends JDialog {

    public boolean bloquearEscape = false;
    public boolean esMultifuncional = false;
    public boolean pedidoAnulado = false;
    
    JInternalFrame myParentFrame;
        
    private DefaultComboBoxModel mTipoPersona;        
    private CSimpleModelo controllerTipoPersona;
    int tiPersona;
    private Cliente cliente;

    public IPedidoGenerado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public IPedidoGenerado(JInternalFrame parent, String title, boolean modal) {
        super(new Frame(),modal);
        myParentFrame = parent;
        try {
            initComponents();
            AtuxUtility.centrarVentana((JDialog)this);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntGroupComprobante = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        lblTipoCliente = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        panelRoundTranslucido1 = new elaprendiz.gui.panel.PanelRoundTranslucido();
        lblTipo = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblComprobante = new javax.swing.JLabel();
        rdbBoleta = new javax.swing.JRadioButton();
        rdbFactura = new javax.swing.JRadioButton();
        cmbTipoCliente = new elaprendiz.gui.comboBox.ComboBoxRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos");
        setBackground(new java.awt.Color(51, 153, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        lblTipoCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTipoCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoCliente.setText("Seleccione al cliente :");

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyPressed(evt);
            }
        });

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Aceptar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
            }
        });

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Tipo Cliente :");

        lblDocumento.setForeground(new java.awt.Color(255, 255, 255));
        lblDocumento.setText("D.N.I. :");

        txtRUC.setText("*** No especificado ***");
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRUCKeyPressed(evt);
            }
        });

        lblCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCliente.setText("Cliente :");

        txtCliente.setText("*** No especificado ***");
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        lblDireccion.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccion.setText("Direccion :");

        txtDireccion.setText("*** No especificado ***");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        lblComprobante.setForeground(new java.awt.Color(255, 255, 255));
        lblComprobante.setText("Comprobante :");

        rdbBoleta.setBackground(new java.awt.Color(32, 39, 55));
        bntGroupComprobante.add(rdbBoleta);
        rdbBoleta.setForeground(new java.awt.Color(255, 255, 255));
        rdbBoleta.setSelected(true);
        rdbBoleta.setText("Boleta");

        rdbFactura.setBackground(new java.awt.Color(32, 39, 55));
        bntGroupComprobante.add(rdbFactura);
        rdbFactura.setForeground(new java.awt.Color(255, 255, 255));
        rdbFactura.setText("Factura");

        cmbTipoCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoClienteItemStateChanged(evt);
            }
        });
        cmbTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelRoundTranslucido1Layout = new javax.swing.GroupLayout(panelRoundTranslucido1);
        panelRoundTranslucido1.setLayout(panelRoundTranslucido1Layout);
        panelRoundTranslucido1Layout.setHorizontalGroup(
            panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                        .addComponent(lblComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbBoleta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbFactura))
                    .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                                        .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblTipo))
                                        .addGap(25, 25, 25))
                                .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                        .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCliente)
                                .addComponent(txtDireccion)
                                .addComponent(txtRUC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        panelRoundTranslucido1Layout.setVerticalGroup(
            panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblDocumento)
                        .addGap(18, 18, 18)
                        .addComponent(lblCliente))
                    .addGroup(panelRoundTranslucido1Layout.createSequentialGroup()
                        .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipo))
                        .addGap(9, 9, 9)
                        .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDireccion))))
                .addGap(18, 18, 18)
                .addGroup(panelRoundTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComprobante)
                    .addComponent(rdbBoleta)
                    .addComponent(rdbFactura))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
            .addComponent(panelRoundTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addComponent(panelRoundTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
        closeWindow(true);
    }//GEN-LAST:event_bntAceptarActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        // TODO add your handling code here:
        closeWindow(false);
    }//GEN-LAST:event_bntSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        controllerTipoPersona = new CSimpleModelo();          
        mTipoPersona = new DefaultComboBoxModel(controllerTipoPersona.getTipoPersona().toArray());        
        this.cmbTipoCliente.setModel(mTipoPersona);
        cmbTipoCliente.setSelectedIndex(getTiPersona());        
    }//GEN-LAST:event_formWindowOpened

    private void cmbTipoClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoClienteItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            if(cmbTipoCliente.getSelectedIndex()==0){//Persona Natural
                setCliente(null);
                //txtRUC.setEditable(false);
                lblDocumento.setText("D.N.I");
                lblTipoCliente.setText("Seleccione cliente :");
                AtuxUtility.moveFocus(txtCliente);                
                //txtDireccion.setEditable(false);
            }
            else{                                    //Persona Juridica
                lblDocumento.setText("R.U.C");
                lblTipoCliente.setText("R.U.C. / Razon Social :");
                txtRUC.setEditable(false);
                txtCliente.setEditable(false);
                txtDireccion.setEditable(false);
                rdbFactura.setSelected(true);
                AtuxUtility.moveFocus(txtBuscarCliente);
            }
        } 
    }//GEN-LAST:event_cmbTipoClienteItemStateChanged

    private void txtBuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

         JBuscarCliente buscarCliente = new JBuscarCliente(1,this,true);
         buscarCliente.setVisible(true);

         if(buscarCliente.getCliente() != null){
             setCliente((Cliente)buscarCliente.getCliente());
             bntAceptar.requestFocus();
         }
      }
    }//GEN-LAST:event_txtBuscarClienteKeyPressed
    
    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cmbTipoCliente.getSelectedIndex()==1) {
                bntAceptar.doClick();
            }
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void cmbTipoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoClienteKeyPressed
     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {         
         if(cmbTipoCliente.getSelectedIndex()==0){
                AtuxUtility.moveFocus(bntAceptar);
            }
            else{
               AtuxUtility.moveFocus(txtBuscarCliente); 
            } 
        }
    }//GEN-LAST:event_cmbTipoClienteKeyPressed

    private void txtRUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtCliente);
        }
    }//GEN-LAST:event_txtRUCKeyPressed

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtDireccion);
        }
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(cmbTipoCliente.getSelectedIndex()==0){
                AtuxUtility.moveFocus(bntAceptar);
            }
            else{
               AtuxUtility.moveFocus(txtBuscarCliente); 
            }            
        }
    }//GEN-LAST:event_txtDireccionKeyPressed
      
                       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private javax.swing.ButtonGroup bntGroupComprobante;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    public elaprendiz.gui.comboBox.ComboBoxRect cmbTipoCliente;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblComprobante;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoCliente;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private elaprendiz.gui.panel.PanelRoundTranslucido panelRoundTranslucido1;
    public javax.swing.JRadioButton rdbBoleta;
    public javax.swing.JRadioButton rdbFactura;
    public javax.swing.JTextField txtBuscarCliente;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtRUC;
    // End of variables declaration//GEN-END:variables
    
    private void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
       if(this.cliente != null)
        {
           ClienteDireccion cliDireccion = new CClienteDireccion().getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,cliente.getCoClienteLocal()}); 
           this.txtCliente.setText(this.cliente.getDeRazonSocial());
           this.txtRUC.setText(this.cliente.getNuDocIdentidad());
           this.txtDireccion.setText(cliDireccion.getDeDireccion());                                       
           rdbFactura.setSelected(cmbTipoCliente.getSelectedIndex()==1?true:false);
           rdbBoleta.setSelected(cmbTipoCliente.getSelectedIndex()==0?true:false);
        }
       else{
           txtBuscarCliente.setText("");
           txtCliente.setText("");
           txtDireccion.setText("");
           txtRUC.setText("");
           rdbBoleta.setSelected(cmbTipoCliente.getSelectedIndex()==0?true:false);
           rdbFactura.setSelected(cmbTipoCliente.getSelectedIndex()==1?true:false);
        }
    }

    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }

    public void setTiPersona(int i) {
        tiPersona = i;
    }
    
     int getTiPersona(){
        return tiPersona;
    }
         
     public void requestFocus() {
        cmbTipoCliente.requestFocus();
    }

    public Cliente getCliente(){
        return cliente;
    }
}
