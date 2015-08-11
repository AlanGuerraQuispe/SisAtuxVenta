package atux.vistas.buscar;

import atux.controllers.CPaises;
import atux.core.JAbstractModelBD;
import atux.modelgui.ModeloTablaPaises;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class BuscarPaises extends javax.swing.JPanel {

    JOptionPane op;
    int opcion=-1;
    private ModeloTablaPaises mtp;
    private CPaises cp;
    
    public BuscarPaises(int opcion) {
        initComponents();
        lbAviso.setVisible(false);
        this.opcion = opcion;
        LlenarGrid();
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
    }
    
    public BuscarPaises() {
        initComponents();
        lbAviso.setVisible(false);
        mtp = new ModeloTablaPaises();
        tblListado.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblListado);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
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

    private void LlenarGrid(){
        String columnas[] = null;
        Object valores[] = null;
        txtDato.setText(txtDato.getText().toUpperCase());
        if(!txtDato.getText().isEmpty()){
            columnas = new String[]{"substr(co_pais,1,1)",
                                    "De_Pais",
                                    "Es_Pais"};
            if(this.rbACentral.isSelected()){
                valores = new Object[]{"3",txtDato.getText(),"A"};
            }else if(this.rbANorte.isSelected()){
                valores = new Object[]{"2",txtDato.getText(),"A"};
            }else if(this.rbASur.isSelected()){
                valores = new Object[]{"5",txtDato.getText(),"A"};
            }else if(this.rbAfrica.isSelected()){
                valores = new Object[]{"1",txtDato.getText(),"A"};
            }else if(this.rbAsia.isSelected()){
                valores = new Object[]{"6",txtDato.getText(),"A"};
            }else if(this.rbCaribe.isSelected()){
                valores = new Object[]{"4",txtDato.getText(),"A"};
            }else if(this.rbEuropa.isSelected()){
                valores = new Object[]{"7",txtDato.getText(),"A"};
            }else if(this.rbOceania.isSelected()){
                valores = new Object[]{"8",txtDato.getText(),"A"};
            }
        }else{
            columnas = new String[]{"substr(co_pais,1,1)",
                                    "Es_Pais"};
            if(this.rbACentral.isSelected()){
                valores = new Object[]{"3","A"};
            }else if(this.rbANorte.isSelected()){
                valores = new Object[]{"2","A"};
            }else if(this.rbASur.isSelected()){
                valores = new Object[]{"5","A"};
            }else if(this.rbAfrica.isSelected()){
                valores = new Object[]{"1","A"};
            }else if(this.rbAsia.isSelected()){
                valores = new Object[]{"6","A"};
            }else if(this.rbCaribe.isSelected()){
                valores = new Object[]{"4","A"};
            }else if(this.rbEuropa.isSelected()){
                valores = new Object[]{"7","A"};
            }else if(this.rbOceania.isSelected()){
                valores = new Object[]{"8","A"};
            }
        }         

        this.mtp = new ModeloTablaPaises(columnas,valores);
        this.tblListado.setModel(mtp);        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rbAfrica = new javax.swing.JRadioButton();
        rbACentral = new javax.swing.JRadioButton();
        rbANorte = new javax.swing.JRadioButton();
        rbASur = new javax.swing.JRadioButton();
        rbAsia = new javax.swing.JRadioButton();
        rbCaribe = new javax.swing.JRadioButton();
        rbEuropa = new javax.swing.JRadioButton();
        rbOceania = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnSeleccionar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        lbAviso = new elaprendiz.gui.label.LabelRect();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        txtDato.setPreferredSize(new java.awt.Dimension(250, 27));

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

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

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        rbAfrica.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAfrica);
        rbAfrica.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbAfrica.setForeground(new java.awt.Color(255, 255, 255));
        rbAfrica.setText("Africa");
        rbAfrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAfricaActionPerformed(evt);
            }
        });

        rbACentral.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbACentral);
        rbACentral.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbACentral.setForeground(new java.awt.Color(255, 255, 255));
        rbACentral.setText("América Central");
        rbACentral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbACentralActionPerformed(evt);
            }
        });

        rbANorte.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbANorte);
        rbANorte.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbANorte.setForeground(new java.awt.Color(255, 255, 255));
        rbANorte.setText("América del Norte");
        rbANorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbANorteActionPerformed(evt);
            }
        });

        rbASur.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbASur);
        rbASur.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbASur.setForeground(new java.awt.Color(255, 255, 255));
        rbASur.setText("América del Sur");
        rbASur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbASurActionPerformed(evt);
            }
        });

        rbAsia.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAsia);
        rbAsia.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbAsia.setForeground(new java.awt.Color(255, 255, 255));
        rbAsia.setText("Asia");
        rbAsia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAsiaActionPerformed(evt);
            }
        });

        rbCaribe.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbCaribe);
        rbCaribe.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbCaribe.setForeground(new java.awt.Color(255, 255, 255));
        rbCaribe.setText("El Caribe");
        rbCaribe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCaribeActionPerformed(evt);
            }
        });

        rbEuropa.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbEuropa);
        rbEuropa.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbEuropa.setForeground(new java.awt.Color(255, 255, 255));
        rbEuropa.setText("Europa");
        rbEuropa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEuropaActionPerformed(evt);
            }
        });

        rbOceania.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbOceania);
        rbOceania.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rbOceania.setForeground(new java.awt.Color(255, 255, 255));
        rbOceania.setSelected(true);
        rbOceania.setText("Oceanía");
        rbOceania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOceaniaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbAsia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbAfrica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbCaribe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbACentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbEuropa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbANorte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rbOceania, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbASur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAfrica)
                    .addComponent(rbACentral)
                    .addComponent(rbANorte)
                    .addComponent(rbASur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAsia)
                    .addComponent(rbOceania)
                    .addComponent(rbCaribe)
                    .addComponent(rbEuropa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        btnSeleccionar.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        jPanel3.add(btnSeleccionar);
        btnSeleccionar.setBounds(150, 7, 115, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);
        btnCancelar.setBounds(270, 7, 94, 25);

        lbAviso.setText(".");
        lbAviso.setColorDeBorde(new java.awt.Color(240, 240, 240));
        lbAviso.setColorDeSegundoBorde(new java.awt.Color(240, 240, 240));
        lbAviso.setColorDeSombra(new java.awt.Color(240, 240, 240));
        lbAviso.setEnabled(false);
        lbAviso.setFocusable(false);
        lbAviso.setFont(new java.awt.Font("Arial", 0, 3)); // NOI18N
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));
        jPanel3.add(lbAviso);
        lbAviso.setBounds(380, 16, 17, 10);

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbAfricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAfricaActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
    }//GEN-LAST:event_rbAfricaActionPerformed

    private void rbACentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbACentralActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 150, true);
    }//GEN-LAST:event_rbACentralActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        LlenarGrid();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        getOptionPane();
        if(this.tblListado.getSelectedRow() != -1)
        {
            cp = new CPaises();
            cp.setPaises(mtp.getFila(tblListado.getSelectedRow()));
            op.setValue(1);
        }else{
            lbAviso.setVisible(true);
        }
        
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
           getOptionPane(); 
           if(tblListado.getSelectedRow() != -1)
            {
              cp = new CPaises();
              cp.setPaises(mtp.getFila(tblListado.getSelectedRow()));
              op.setValue(1);
            }else{
              lbAviso.setVisible(true);
            }                    
       }  
       
    }//GEN-LAST:event_tblListadoMouseClicked

private void rbANorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbANorteActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbANorteActionPerformed

private void rbASurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbASurActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbASurActionPerformed

private void rbAsiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAsiaActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbAsiaActionPerformed

private void rbCaribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCaribeActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbCaribeActionPerformed

private void rbEuropaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEuropaActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbEuropaActionPerformed

private void rbOceaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOceaniaActionPerformed
    LlenarGrid();
}//GEN-LAST:event_rbOceaniaActionPerformed

    public JAbstractModelBD getPais(){
        if(cp!=null){
            return (JAbstractModelBD)cp.getPaises();
        }
        return null;
    }
    
    public JLabel getAviso(){
        return lbAviso;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JRadioButton rbACentral;
    private javax.swing.JRadioButton rbANorte;
    private javax.swing.JRadioButton rbASur;
    private javax.swing.JRadioButton rbAfrica;
    private javax.swing.JRadioButton rbAsia;
    private javax.swing.JRadioButton rbCaribe;
    private javax.swing.JRadioButton rbEuropa;
    private javax.swing.JRadioButton rbOceania;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
     
}
