package atux.vistas.buscar;

import atux.controllers.CEspecialidadMedicos;
import atux.controllers.CMedicosEspecialidad;
import atux.core.JAbstractModelBD;
import atux.modelgui.ModeloTablaEspecialidadMedicos;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxUtility;
import atux.vistas.catalogo.IMedicosEspecialidad;
import java.awt.Component;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class BuscarEspecialidadMedicos extends javax.swing.JPanel {

    JOptionPane op;
    int opcion=-1;
    private ModeloTablaEspecialidadMedicos mtp;
    private CEspecialidadMedicos cp;
    private CMedicosEspecialidad me;
    private String CodigoMedico;
    IMedicosEspecialidad ime;
    
    public BuscarEspecialidadMedicos(int opcion,IMedicosEspecialidad ime) {
        initComponents();
        lbAviso.setVisible(false);
        this.opcion = opcion;
        this.ime = ime;
        switch(opcion)
        {
            case 0:
            case 1:
                mtp = new ModeloTablaEspecialidadMedicos(opcion);
                break;
            default:                
                mtp = new ModeloTablaEspecialidadMedicos();
        }
        tblMedicos.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblMedicos);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 100, true);
    }
    
    public BuscarEspecialidadMedicos() {
        initComponents();
        lbAviso.setVisible(false);
        mtp = new ModeloTablaEspecialidadMedicos();
        tblMedicos.setModel(mtp);
        Helper.ajustarAnchoColumnas(tblMedicos);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
    }

    public void setCodigoMedico(String CodigoMedico) {
        this.CodigoMedico = CodigoMedico;
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
        panelImageEspecialidad = new elaprendiz.gui.panel.PanelImage();
        panelCurves = new elaprendiz.gui.panel.PanelCurves();
        pnlEspecialidad = new javax.swing.JPanel();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField();
        rbCodigo = new javax.swing.JRadioButton();
        rbEspecialidades = new javax.swing.JRadioButton();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();
        pnlAccionesEspecialidad = new javax.swing.JPanel();
        btnSeleccionar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 373));
        setLayout(new java.awt.BorderLayout());

        panelImageEspecialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageEspecialidad.setLayout(new java.awt.BorderLayout());

        pnlEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlEspecialidad.setOpaque(false);

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Seleccionar una especialidad");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

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
        rbCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbCodigo.setForeground(new java.awt.Color(255, 255, 255));
        rbCodigo.setText("Código");
        rbCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodigoActionPerformed(evt);
            }
        });

        rbEspecialidades.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbEspecialidades);
        rbEspecialidades.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbEspecialidades.setForeground(new java.awt.Color(255, 255, 255));
        rbEspecialidades.setSelected(true);
        rbEspecialidades.setText("Especialidades");
        rbEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEspecialidadesActionPerformed(evt);
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

        javax.swing.GroupLayout pnlEspecialidadLayout = new javax.swing.GroupLayout(pnlEspecialidad);
        pnlEspecialidad.setLayout(pnlEspecialidadLayout);
        pnlEspecialidadLayout.setHorizontalGroup(
            pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addGroup(pnlEspecialidadLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEspecialidadLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(pnlEspecialidadLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(rbCodigo)
                        .addGap(26, 26, 26)
                        .addComponent(rbEspecialidades)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEspecialidadLayout.setVerticalGroup(
            pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEspecialidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEspecialidadLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCodigo)
                    .addGroup(pnlEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbEspecialidades)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCurves.add(pnlEspecialidad, java.awt.BorderLayout.PAGE_START);

        pnlAccionesEspecialidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlAccionesEspecialidad.setOpaque(false);

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

        javax.swing.GroupLayout pnlAccionesEspecialidadLayout = new javax.swing.GroupLayout(pnlAccionesEspecialidad);
        pnlAccionesEspecialidad.setLayout(pnlAccionesEspecialidadLayout);
        pnlAccionesEspecialidadLayout.setHorizontalGroup(
            pnlAccionesEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesEspecialidadLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        pnlAccionesEspecialidadLayout.setVerticalGroup(
            pnlAccionesEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesEspecialidadLayout.createSequentialGroup()
                .addGroup(pnlAccionesEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCurves.add(pnlAccionesEspecialidad, java.awt.BorderLayout.CENTER);

        panelImageEspecialidad.add(panelCurves, java.awt.BorderLayout.CENTER);

        add(panelImageEspecialidad, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodigoActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.NUM_LETRAS, 5, false);
    }//GEN-LAST:event_rbCodigoActionPerformed

    private void rbEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEspecialidadesActionPerformed
        txtDato.setText("");
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 100, true);
    }//GEN-LAST:event_rbEspecialidadesActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(!txtDato.getText().isEmpty()){
            String columnas[] = null;
            Object valores[] = null;               
            
            valores = new Object[]{txtDato.getText()}; 
            if(this.rbCodigo.isSelected()){
                columnas = new String[]{"CO_ESPECIALIDAD"};
            }else if(this.rbEspecialidades.isSelected()){
                columnas = new String[]{"DE_ESPECIALIDAD"};
            }
            this.mtp = new ModeloTablaEspecialidadMedicos(columnas,valores);
              this.tblMedicos.setModel(mtp);
        }else{
            switch(opcion)
            {
                case 0:
                case 1:
                    mtp = new ModeloTablaEspecialidadMedicos(opcion);
                    break;
                default:
                    mtp = new ModeloTablaEspecialidadMedicos();
            }
            
           tblMedicos.setModel(mtp);
        }         
         
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
            Boolean EstadoGuardar=false;    
            me = new CMedicosEspecialidad();           
            
            String Cod = mtp.getValueAt(tblMedicos.getSelectedRow(), 0).toString();
            
            me.getMedicosEspecialidad().setCoEspecialidad(Cod);
            me.getMedicosEspecialidad().setCoMedico(CodigoMedico);
            me.getMedicosEspecialidad().setIdCreaMedico(AtuxVariables.vIdUsuario);
            try {
                me.getMedicosEspecialidad().setFeCreaMedico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
                EstadoGuardar = me.guardarRegistro(me.getMedicosEspecialidad());
            } catch (SQLException ex) {
                Logger.getLogger(BuscarEspecialidadMedicos.class.getName()).log(Level.SEVERE, null, ex);
            }
            AtuxUtility.aceptarTransaccion();                                   
            ime.requestFocus();
            getOptionPane();
            op.setValue(1);
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(BuscarEspecialidadMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    private void tblMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicosMouseClicked
        // TODO add your handling code here:
        btnSeleccionarActionPerformed(null);
//        if (evt.getClickCount() == 2) {
//           getOptionPane(); 
//           if(tblMedicos.getSelectedRow() != -1)
//            {
//              cp = new CEspecialidadMedicos();
//              cp.setEspecialidadMedicos(mtp.getFila(tblMedicos.getSelectedRow()));
//              op.setValue(1);
//            }else{
//              lbAviso.setVisible(true);
//            }                    
//       }  
       
    }//GEN-LAST:event_tblMedicosMouseClicked

private void txtDatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDatoFocusLost
    txtDato.setText(txtDato.getText().toUpperCase());
}//GEN-LAST:event_txtDatoFocusLost

    public JAbstractModelBD getEspecialidadMedicos()
    {
        if(cp!=null)
        {
            return (JAbstractModelBD)cp.getEspecialidadMedicos();
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
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private elaprendiz.gui.panel.PanelCurves panelCurves;
    private elaprendiz.gui.panel.PanelImage panelImageEspecialidad;
    private javax.swing.JPanel pnlAccionesEspecialidad;
    private javax.swing.JPanel pnlEspecialidad;
    private javax.swing.JRadioButton rbCodigo;
    private javax.swing.JRadioButton rbEspecialidades;
    private javax.swing.JTable tblMedicos;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
     
}
