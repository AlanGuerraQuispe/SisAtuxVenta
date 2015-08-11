package atux.vistas.catalogo;

import atux.controllers.CMedicosEspecialidad;
import atux.core.JAbstractModelBD;
import atux.modelgui.ModeloTablaMedicosEspecialidad;
import atux.util.Helper;
import atux.vistas.buscar.BuscarEspecialidadMedicos;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class IMedicosEspecialidad extends javax.swing.JPanel {

    JOptionPane op;
    int opcion=-1;
    private ModeloTablaMedicosEspecialidad mtp;
    private CMedicosEspecialidad cp;
    JInternalFrame ifr;
    
    public IMedicosEspecialidad(int opcion,JInternalFrame ifr) {
        initComponents();
        lbAviso.setVisible(false);
        this.opcion = opcion;
        this.ifr = ifr;
    }

    public void CargaDatos(){
        mtp = new ModeloTablaMedicosEspecialidad(txtCodigo.getText());

        tblEspecialidades.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblEspecialidades);
        
    }
    public void setCodigoMedico(String Codigo){
        txtCodigo.setText(Codigo);        
    }

    public void setCMP(String CMP){
        txtCMP.setText(CMP);        
    }

    public void setApelldosNombres(String ApNombre){
        txtAp_Paterno.setText(ApNombre);        
    }
    
    public IMedicosEspecialidad() {
        initComponents();
        lbAviso.setVisible(false);
        mtp = new ModeloTablaMedicosEspecialidad();
        tblEspecialidades.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblEspecialidades);
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
        panelImageMedico = new elaprendiz.gui.panel.PanelImage();
        panelCurves = new elaprendiz.gui.panel.PanelCurves();
        pnlMedicosEspecialidad = new javax.swing.JPanel();
        jspMedico = new javax.swing.JScrollPane();
        tblEspecialidades = new javax.swing.JTable();
        pnlEntradasMedicos = new javax.swing.JPanel();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        lblCodigo = new javax.swing.JLabel();
        lblCMP = new javax.swing.JLabel();
        lblApPaterno = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtCMP = new elaprendiz.gui.textField.TextField();
        txtAp_Paterno = new elaprendiz.gui.textField.TextField();
        pnlAccionesMedicos = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 373));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        panelImageMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageMedico.setLayout(new java.awt.BorderLayout());

        pnlMedicosEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlMedicosEspecialidad.setOpaque(false);

        jspMedico.setOpaque(false);
        jspMedico.setPreferredSize(new java.awt.Dimension(363, 250));

        tblEspecialidades.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEspecialidades.setOpaque(false);
        tblEspecialidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEspecialidadesMouseClicked(evt);
            }
        });
        jspMedico.setViewportView(tblEspecialidades);

        pnlEntradasMedicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Medicos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasMedicos.setEnabled(false);
        pnlEntradasMedicos.setOpaque(false);
        pnlEntradasMedicos.setPreferredSize(new java.awt.Dimension(748, 120));

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Médico");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");

        lblCMP.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCMP.setText("CMP:");

        lblApPaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApPaterno.setText("Apellidos y Nombres:");

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCMP.setEditable(false);
        txtCMP.setDireccionDeSombra(30);
        txtCMP.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCMP.setFont(new java.awt.Font("Arial", 0, 12));
        txtCMP.setName("pcodigo"); // NOI18N
        txtCMP.setPreferredSize(new java.awt.Dimension(120, 25));

        txtAp_Paterno.setEditable(false);
        txtAp_Paterno.setDireccionDeSombra(30);
        txtAp_Paterno.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtAp_Paterno.setFont(new java.awt.Font("Arial", 0, 12));
        txtAp_Paterno.setName("pdescrip"); // NOI18N
        txtAp_Paterno.setPreferredSize(new java.awt.Dimension(120, 25));

        javax.swing.GroupLayout pnlEntradasMedicosLayout = new javax.swing.GroupLayout(pnlEntradasMedicos);
        pnlEntradasMedicos.setLayout(pnlEntradasMedicosLayout);
        pnlEntradasMedicosLayout.setHorizontalGroup(
            pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblApPaterno)
                    .addComponent(lblCodigo))
                .addGap(18, 18, 18)
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEntradasMedicosLayout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblCMP)
                        .addGap(4, 4, 4)
                        .addComponent(txtCMP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 139, Short.MAX_VALUE))
                    .addComponent(txtAp_Paterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlEntradasMedicosLayout.setVerticalGroup(
            pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCMP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAp_Paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAccionesMedicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesMedicos.setOpaque(false);
        pnlAccionesMedicos.setPreferredSize(new java.awt.Dimension(550, 50));

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Agregar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Eliminar");
        btnModificar.setEnabled(false);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Cerrar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesMedicosLayout = new javax.swing.GroupLayout(pnlAccionesMedicos);
        pnlAccionesMedicos.setLayout(pnlAccionesMedicosLayout);
        pnlAccionesMedicosLayout.setHorizontalGroup(
            pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAccionesMedicosLayout.setVerticalGroup(
            pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlMedicosEspecialidadLayout = new javax.swing.GroupLayout(pnlMedicosEspecialidad);
        pnlMedicosEspecialidad.setLayout(pnlMedicosEspecialidadLayout);
        pnlMedicosEspecialidadLayout.setHorizontalGroup(
            pnlMedicosEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAccionesMedicos, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jspMedico, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
            .addComponent(pnlEntradasMedicos, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMedicosEspecialidadLayout.setVerticalGroup(
            pnlMedicosEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicosEspecialidadLayout.createSequentialGroup()
                .addComponent(pnlEntradasMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCurves.add(pnlMedicosEspecialidad, java.awt.BorderLayout.PAGE_START);

        panelImageMedico.add(panelCurves, java.awt.BorderLayout.CENTER);

        add(panelImageMedico, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tblEspecialidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEspecialidadesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
           getOptionPane(); 
           if(tblEspecialidades.getSelectedRow() != -1)
            {
              cp = new CMedicosEspecialidad();
              cp.setMedicosEspecialidad(mtp.getFila(tblEspecialidades.getSelectedRow()));
              op.setValue(1);
            }else{
              lbAviso.setVisible(true);
            }                    
       }  
       
    }//GEN-LAST:event_tblEspecialidadesMouseClicked

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        getOptionPane();
        op.setValue(1);
}//GEN-LAST:event_btnSalirActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        int tipoSeleccion = -1;
        BuscarEspecialidadMedicos bem = new BuscarEspecialidadMedicos(tipoSeleccion,this);
        JLabel aviso = bem.getAviso();
        bem.setCodigoMedico(txtCodigo.getText());
        
        bem.setPreferredSize(new Dimension(630, 370));
        
        //String msj = (tipoSeleccion==-1?"Mostrando todos los Laboratorios existentes":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj="Mostrando todas las Especialidades Médicas";
        JOptionPane.showInternalOptionDialog(this, bem, msj,JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{aviso},null);
}//GEN-LAST:event_btnNuevoActionPerformed

private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    CargaDatos();
}//GEN-LAST:event_formFocusGained

    public JAbstractModelBD getMedicos()
    {
        if(cp!=null)
        {
            return (JAbstractModelBD)cp.getMedicosEspecialidad();
        }
        return null;
    }
    
    public JLabel getAviso()
    {
        return lbAviso;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jspMedico;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblApPaterno;
    private javax.swing.JLabel lblCMP;
    private javax.swing.JLabel lblCodigo;
    private elaprendiz.gui.panel.PanelCurves panelCurves;
    private elaprendiz.gui.panel.PanelImage panelImageMedico;
    private javax.swing.JPanel pnlAccionesMedicos;
    private javax.swing.JPanel pnlEntradasMedicos;
    private javax.swing.JPanel pnlMedicosEspecialidad;
    private javax.swing.JTable tblEspecialidades;
    private elaprendiz.gui.textField.TextField txtAp_Paterno;
    private elaprendiz.gui.textField.TextField txtCMP;
    private elaprendiz.gui.textField.TextField txtCodigo;
    // End of variables declaration//GEN-END:variables
     
}
