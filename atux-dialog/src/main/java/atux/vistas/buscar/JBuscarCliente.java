package atux.vistas.buscar;

import atux.controllers.CCliente;
import atux.core.JAbstractModelBD;
import atux.modelgui.ModeloTablaCliente;
import atux.util.AdminIFrame;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.catalogo.IClientes;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.util.SwingShowDialogAction;

import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import javax.swing.*;

/**
 *
 * @author user
 */
public class JBuscarCliente extends javax.swing.JDialog {

    JOptionPane op;
    int opcion=1;
    private ModeloTablaCliente mtp;
    private CCliente cp;
    String estado = "";
    
    /** Creates new form JBuscarCliente */
    public JBuscarCliente(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public JBuscarCliente(int opcion,javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.opcion = opcion;
        if (this.opcion ==1){
            estado = "A";
        }else{
            estado = "I";
        }

        mtp = new ModeloTablaCliente(AtuxVariables.arrayClientes);
        tblListado.setModel(mtp);

        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaCliente.anchoColumnas);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
        AtuxGridUtils.setearPrimerRegistro(tblListado,txtDato,2);
        AtuxUtility.moveFocus(txtDato);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AtuxUtility.centrarVentana(this);
    }
    
    public JBuscarCliente() {
        initComponents();
        mtp = new ModeloTablaCliente(estado);

        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado, ModeloTablaCliente.anchoColumnas);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
        AtuxUtility.centrarVentana((JDialog) this);
    }

    public JAbstractModelBD getCliente()
    {
        if(cp!=null)
        {
            return cp.getClienteLocal();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField();
        rbRazonSocial = new javax.swing.JRadioButton();
        rbDocumento = new javax.swing.JRadioButton();
        btnBuscarCliente = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        bntCancelar = new elaprendiz.gui.button.ButtonRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Buscar Por:");

        txtDato.setPreferredSize(new java.awt.Dimension(250, 27));
        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDatoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatoKeyReleased(evt);
            }
        });

        rbRazonSocial.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbRazonSocial);
        rbRazonSocial.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbRazonSocial.setForeground(new java.awt.Color(255, 255, 255));
        rbRazonSocial.setSelected(true);
        rbRazonSocial.setText("RUC");

        rbDocumento.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbDocumento);
        rbDocumento.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbDocumento.setForeground(new java.awt.Color(255, 255, 255));
        rbDocumento.setText("Documento");

        btnBuscarCliente.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(363, 250));

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListado.setOpaque(false);
        tblListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(rbRazonSocial)
                .addGap(18, 18, 18)
                .addComponent(rbDocumento)
                .addGap(221, 221, 221))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbRazonSocial)
                    .addComponent(rbDocumento))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        panelCurves1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Seleccionar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelCurves1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed
        // TODO add your handling code here:
       closeWindow(false);
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
        if(this.tblListado.getSelectedRow() != -1)
        {
            cp = new CCliente();
            cp.setCliente(mtp.getFila(tblListado.getSelectedRow()));
            closeWindow(true);
        }

    }//GEN-LAST:event_bntAceptarActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if(tblListado.getSelectedRow() != -1)
            {
                cp = new CCliente();
                cp.setCliente(mtp.getFila(tblListado.getSelectedRow()));
                closeWindow(true);
            }
        }

    }//GEN-LAST:event_tblListadoMouseClicked

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        if(!txtDato.getText().isEmpty()){
            String Condicion ="";
            String Estado="";
            if(opcion == 0){
                Estado ="I";
            }else if(opcion != -1){
                Estado ="A";
            }

            if(this.rbRazonSocial.isSelected()){
                Condicion=" DE_RAZON_SOCIAL like '%" + txtDato.getText() + "%' ";
            }else if(this.rbDocumento.isSelected()){
                Condicion=" NU_DOC_IDENTIDAD like '%" + txtDato.getText() + "%' ";
            }
            if(opcion != -1){
                Condicion = Condicion + " and ES_CLIENTE = '" + Estado + "'";
            }

            this.mtp = new ModeloTablaCliente(Condicion);
            this.tblListado.setModel(mtp);
        }else{
            switch(opcion)
            {
                case 0:
                case 1:
                    mtp = new ModeloTablaCliente(opcion,0,1000);
                    break;
                default:
                    mtp = new ModeloTablaCliente();
            }

            tblListado.setModel(mtp);
        }

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyPressed
        if (tblListado != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            AtuxGridUtils.aceptarTeclaPresionada(evt, tblListado, txtDato, 2);
        }
    }//GEN-LAST:event_txtDatoKeyPressed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
         AtuxGridUtils.buscarDescripcion(evt, tblListado, txtDato, 2);
    }//GEN-LAST:event_txtDatoKeyReleased
         
   public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private elaprendiz.gui.button.ButtonRect bntCancelar;
    private elaprendiz.gui.button.ButtonRect btnBuscarCliente;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JRadioButton rbDocumento;
    private javax.swing.JRadioButton rbRazonSocial;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
}
