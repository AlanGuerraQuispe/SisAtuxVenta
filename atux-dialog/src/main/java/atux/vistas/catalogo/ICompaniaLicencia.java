/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IImpuestoIGV.java
 *
 * Created on 22/12/2014, 04:20:23 PM
 */

package atux.vistas.catalogo;

import atux.controllers.CCompaniaLicencia;
import atux.managerbd.Mac;
import atux.modelgui.ModeloTablaCompaniaLicencia;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author Cesar Ruiz  PC
 */
//public class ICodigoDeBarra extends javax.swing.JInternalFrame {
public class ICompaniaLicencia extends javax.swing.JPanel {
    private CCompaniaLicencia cp;
    private ModeloTablaCompaniaLicencia mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    JInternalFrame ifr;
    private final Log logger = LogFactory.getLog(getClass());    
    JOptionPane op;
    
    /** Creates new form IImpuestoIGV */
    public ICompaniaLicencia(JInternalFrame ifr) {
        initComponents();
        cp = new CCompaniaLicencia();
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();        
        this.ifr = ifr;
        lbAviso.setVisible(false);
//        lblListado.setVisible(true);
//        jScrollPane2.setVisible(false);
        setAnchoColumnas();
    }

    private void setAnchoColumnas() { 
//        int anchoTabla = 638;  //Ancho del jScrollPane1. 
        int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0; 
        //TableColumn nos provee de métodos para minimizar, maximizar,etc. columnas de tabla. 
        TableColumn  columnaTabla = null; 
 //{50,600,100,150}
        for(int i=0; i<tblGeneracion.getColumnCount(); i++) {
            columnaTabla = tblGeneracion.getColumnModel().getColumn(i);
            switch(i) {
                case 0: anchoColumna    = 43;
                        anchoColumnaMin = 43;
                        anchoColumnaMax = 43;
                        break;                 
                case 1: anchoColumna    = 460;
                        anchoColumnaMin = 460;
                        anchoColumnaMax = 460;
                        break;
                case 2: anchoColumna    = 80;
                        anchoColumnaMin = 80;
                        anchoColumnaMax = 80;
                        break;
                case 3: anchoColumna    = 100;
                        anchoColumnaMin = 100;
                        anchoColumnaMax = 100;
            }
          //Aplicamos el ancho para cada columna de la tabla.
            columnaTabla.setPreferredWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumnaMin);
            columnaTabla.setMaxWidth(anchoColumnaMax);
     }
 } 
    
    public final void CargaDatos(){
        String Estado="I";
        if (tipoSeleccion ==1) Estado ="A";
        if (tipoSeleccion==-1){
            String columnas[] = new String[]{"CO_COMPANIA"};
            Object valores[] = new Object[]{txtCodigo.getText()};
            mtp = new ModeloTablaCompaniaLicencia(columnas,valores);
        }else{
            String columnas[] = new String[]{"CO_COMPANIA","ES_CADENA"};
            Object valores[] = new Object[]{txtCodigo.getText(),Estado};
            mtp = new ModeloTablaCompaniaLicencia(columnas,valores);
        }
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaCompaniaLicencia.anchoColumnas);
    }

    private void setEventSelectionModel(ListSelectionModel lsm){
        ListSelectionListener lsl = new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }
    
    private void selectionEvent(ListSelectionEvent e){
        if(tblListado.getSelectedRow() != -1){
            numRegistros = tblListado.getSelectedRow();
            cp.setCompaniaLicencia(mtp.getFila(tblListado.getSelectedRow()));
            setCadena();           

       }
    }
    
    private void setCadena(){
        Limpiar();


    }
    
    private void Limpiar(){
//        this.txtCodigoCadena.setText("");
//        this.txtDescripcionCadena.setText("");
//        this.chbEstado.setSelected(true);
//        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        jScrollPane2.setVisible(true);
        lblListado.setVisible(false);
        
        pnlEntradasCompania.setEnabled(false);
//        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigoCadena,lblDescripcionCadena,lblEstado1},false,"");        
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();
        this.btnGenerar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
    }
    private void DesActivarCasillas(){
        jScrollPane2.setVisible(false);
        lblListado.setVisible(true);
        
//        this.pnlEntradas.setEnabled(true);
        //ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigoCadena,lblDescripcionCadena,lblEstado1},false,"");        
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnGenerar.setEnabled(true);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        esActualizacion = false;
        for (int i = tblGeneracion.getRowCount() -1; i >= 0; i--)     {   
            ((DefaultTableModel) tblGeneracion.getModel()).removeRow(i);
        }
    }

    
    private boolean guardarActualizar() throws SQLException{
        int LicenciasActual = tblListado.getRowCount();
        int LicenciasNuevas = tblGeneracion.getRowCount();
        int i;
        Boolean EstadoGuardar;
        EstadoGuardar=false;
        for (i=LicenciasActual; i<LicenciasNuevas; i++){
            cp.getCompaniaLicencia().setCoCompania(txtCodigo.getText());
            cp.getCompaniaLicencia().setNuLicencia(tblGeneracion.getValueAt(i, 0).toString());
            cp.getCompaniaLicencia().setNuSerieLicencia(tblGeneracion.getValueAt(i, 1).toString());
            cp.getCompaniaLicencia().setEsLicencia("A");
            cp.getCompaniaLicencia().setIdCreaLicencia(AtuxVariables.vIdUsuario);
            cp.getCompaniaLicencia().setFeCreaLicencia(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            EstadoGuardar = cp.guardarRegistro(cp.getCompaniaLicencia());
            if(EstadoGuardar = false){
                return EstadoGuardar;
            }
        }
        return true;
    }
    
    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(ICompaniaLicencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void setCodigoCompania(String CodProducto){
        txtCodigo.setText(CodProducto);
    }
    
    public void setDescripcionCompania(String DescripcionProducto){
        txtDescripcion.setText(DescripcionProducto);
    }

    private void getOptionPane(){
        if(op != null){
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null){
            if(pdr instanceof JOptionPane){
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }
    public JLabel getAviso(){
        return lbAviso;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradasCompania = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        lblListado = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        btnGenerar = new elaprendiz.gui.button.ButtonRect();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGeneracion = new javax.swing.JTable();

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasCompania.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Compañia", 1, 2));
        pnlEntradasCompania.setEnabled(false);
        pnlEntradasCompania.setOpaque(false);
        pnlEntradasCompania.setPreferredSize(new java.awt.Dimension(748, 120));

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Código:");

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setText("Descripción:");

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));

        txtDescripcion.setEditable(false);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setName("pdescrip"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Médico");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        javax.swing.GroupLayout pnlEntradasCompaniaLayout = new javax.swing.GroupLayout(pnlEntradasCompania);
        pnlEntradasCompania.setLayout(pnlEntradasCompaniaLayout);
        pnlEntradasCompaniaLayout.setHorizontalGroup(
            pnlEntradasCompaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCompaniaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblCodigo)
                .addGap(10, 10, 10)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlEntradasCompaniaLayout.setVerticalGroup(
            pnlEntradasCompaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCompaniaLayout.createSequentialGroup()
                .addGroup(pnlEntradasCompaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCompaniaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCompaniaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCompaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        lblListado.setViewportView(tblListado);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setPreferredSize(new java.awt.Dimension(550, 50));

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGenerar.setBackground(new java.awt.Color(51, 153, 255));
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesTDeCambioLayout = new javax.swing.GroupLayout(pnlAccionesTDeCambio);
        pnlAccionesTDeCambio.setLayout(pnlAccionesTDeCambioLayout);
        pnlAccionesTDeCambioLayout.setHorizontalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblGeneracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orden", "Serie Licencia", "Estado", "Fecha Creación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblGeneracion);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlEntradasCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(pnlEntradasCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        
        boolean correcto = false; 

        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        CargaDatos();
        DesActivarCasillas();
        tblListado.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
        return;
    }

    DesActivarCasillas();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        getOptionPane();
        op.setValue(1);
}//GEN-LAST:event_btnSalirActionPerformed

private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
    String cadenaNumero = JOptionPane.showInputDialog(this,"Ingrese cantidad de licencias a Generar"); 
    while(isNumeric(cadenaNumero)== false){
        if (cadenaNumero == null) return;
	JOptionPane.showMessageDialog(null, "Debe de Ingresar un dato tipo numerico", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);	
	cadenaNumero = JOptionPane.showInputDialog(this,"Ingrese cantidad de licencias a Generar");  
    }
    int CantidadLicencia = Integer.parseInt(cadenaNumero);
    int Equipo;
    Object[] Data = new Object[4];
    
    int Fila = tblListado.getRowCount();
//tblListado.setModel(mtp);
    if (jScrollPane2.isVisible()==false)  {
        for (Equipo=0; Equipo<Fila;Equipo++){
            Data[0] =mtp.getValueAt(Equipo, 0);
            Data[1] =mtp.getValueAt(Equipo, 1);
            Data[2] =mtp.getValueAt(Equipo, 2);
            Data[3] =mtp.getValueAt(Equipo, 3);
            ((DefaultTableModel) tblGeneracion.getModel()).addRow(Data);
        }
    }

    Mac mac = new Mac();
    //    mac.getValidaSerie(txtRazonSocial.getText(), AtuxVariables.vCodigoLocal, txtNumeroSerie.getText(), Integer.parseInt(txtNumeroTerminales.getText()));
    
    String Serie;
    
    
    Fila = tblGeneracion.getRowCount();
    for (Equipo=1; Equipo<= CantidadLicencia;Equipo++){
        Serie = mac.getGeneraSerie(txtDescripcion.getText(), AtuxVariables.vCodigoLocal, (Equipo+Fila));
        Data[0] =Fila + Equipo;
        Data[1] =Serie;
        Data[2] ="Activo";
            try {
                Data[3] =AtuxDBUtility.getFechaHoraBD(1);
            } catch (SQLException ex) {
                Logger.getLogger(ICompaniaLicencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        ((DefaultTableModel) tblGeneracion.getModel()).addRow(Data);
    }
    ActivarCasillas();
}//GEN-LAST:event_btnGenerarActionPerformed
private static boolean isNumeric(String cadena){
    try {
        Integer.parseInt(cadena);
        return true;
    } catch (NumberFormatException nfe){
        return false;
    }
}                
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGenerar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane2;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JScrollPane lblListado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlEntradasCompania;
    private javax.swing.JTable tblGeneracion;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
