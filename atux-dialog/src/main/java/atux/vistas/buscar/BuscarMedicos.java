package atux.vistas.buscar;

import atux.controllers.CMedicos;
import atux.core.JAbstractModelBD;
import atux.modelgui.ModeloTablaMedicos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class BuscarMedicos extends javax.swing.JPanel {

    JOptionPane op;
    int opcion=-1;
    private ModeloTablaMedicos mtp;
    private CMedicos cp;
    JInternalFrame ifr;
    
    public BuscarMedicos(int opcion,JInternalFrame ifr) {
        initComponents();
        lbAviso.setVisible(false);
        this.opcion = opcion;
        this.ifr = ifr;
        switch(opcion)
        {
            case 0:
            case 1:
                mtp = new ModeloTablaMedicos(opcion);
                break;
            default:                
                mtp = new ModeloTablaMedicos();
        }
        tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos, ModeloTablaMedicos.anchoColumnas);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 100, true);
    }
    
    public BuscarMedicos() {
        initComponents();
        lbAviso.setVisible(false);
        mtp = new ModeloTablaMedicos();
        tblMedicos.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblMedicos);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
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
        txtDato = new elaprendiz.gui.textField.TextField();
        rbCodigo = new javax.swing.JRadioButton();
        rbApellidos = new javax.swing.JRadioButton();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnSeleccionar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();

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
        jLabel1.setText("Buscar Por:");

        txtDato.setPreferredSize(new java.awt.Dimension(250, 27));
        txtDato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDatoFocusLost(evt);
            }
        });

        rbCodigo.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbCodigo);
        rbCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbCodigo.setForeground(new java.awt.Color(255, 255, 255));
        rbCodigo.setText("Código");
        rbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoActionPerformed(evt);
            }
        });

        rbApellidos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbApellidos);
        rbApellidos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbApellidos.setForeground(new java.awt.Color(255, 255, 255));
        rbApellidos.setSelected(true);
        rbApellidos.setText("Apellidos");
        rbApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbApellidosActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(363, 250));

        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMedicos.setOpaque(false);
        tblMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(rbCodigo)
                .addGap(26, 26, 26)
                .addComponent(rbApellidos)
                .addContainerGap(209, Short.MAX_VALUE))
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
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCodigo)
                    .addComponent(rbApellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCurves1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        btnSeleccionar.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelCurves1.add(jPanel3, java.awt.BorderLayout.CENTER);

        panelImage1.add(panelCurves1, java.awt.BorderLayout.CENTER);

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.NUM_LETRAS, 5, false);
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void rbApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbApellidosActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 100, true);
    }//GEN-LAST:event_rbApellidosActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(!txtDato.getText().isEmpty()){
            String columnas[] = null;
            Object valores[] = null;               
            String Estado="";
            if(opcion == 0){
                Estado ="A";
            }else if(opcion != -1){
                Estado ="I";
            }
            
            valores = new Object[]{txtDato.getText()}; 
            if(this.rbCodigo.isSelected()){
                columnas = new String[]{"CO_MEDICO"};
                if(opcion != -1){
                    columnas = new String[]{"ES_MEDICO",
                                            "CO_MEDICO"};
                    valores = new Object[]{Estado,txtDato.getText()};
                } 
                this.mtp = new ModeloTablaMedicos(columnas,valores);
            }else if(this.rbApellidos.isSelected()){
                String Filtro = "";
                if(opcion == -1){
                    Filtro =" AND NO_MEDICO LIKE '%" + txtDato.getText() + "%'";
                }else{
                    Filtro =" AND ES_MEDICO = '" + Estado + "' and  NO_MEDICO LIKE '%" + txtDato.getText() + "%'";
                }
                this.mtp = new ModeloTablaMedicos(Filtro);
            }
        }else{
            switch(opcion)
            {
                case 0:
                case 1:
                    mtp = new ModeloTablaMedicos(opcion);
                    break;
                default:
                    mtp = new ModeloTablaMedicos();
            }
        }
        this.tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos, ModeloTablaMedicos.anchoColumnas);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        getOptionPane();
        if(this.tblMedicos.getSelectedRow() != -1)
        {
            cp = new CMedicos();
            cp.setMedicos(mtp.getFila(tblMedicos.getSelectedRow()));
            op.setValue(1);
        }else{
            lbAviso.setVisible(true);
        }
        
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void tblMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
           getOptionPane(); 
           if(tblMedicos.getSelectedRow() != -1)
            {
              cp = new CMedicos();
              cp.setMedicos(mtp.getFila(tblMedicos.getSelectedRow()));
              op.setValue(1);
            }else{
              lbAviso.setVisible(true);
            }                    
       }  
       
    }//GEN-LAST:event_tblMedicosMouseClicked

private void txtDatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDatoFocusLost
    txtDato.setText(txtDato.getText().toUpperCase());
}//GEN-LAST:event_txtDatoFocusLost

    public JAbstractModelBD getMedicos()
    {
        if(cp!=null)
        {
            return (JAbstractModelBD)cp.getMedicos();
        }
        return null;
    }
    
    public JLabel getAviso()
    {
        return lbAviso;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JTable tblMedicos;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
     
}
