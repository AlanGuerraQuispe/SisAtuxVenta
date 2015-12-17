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

import atux.controllers.CProductoLote;
import atux.modelgui.ModeloTablaProductoLote;
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
import java.util.Calendar;
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
/**
 *
 * @author Cesar Ruiz  PC
 */
//public class ICodigoDeBarra extends javax.swing.JInternalFrame {
public class IProductoLote extends javax.swing.JPanel {
    private String ti_documento_recepcion="";
    private String nu_documento_recepcion="";
    
    private CProductoLote cp;
    private ModeloTablaProductoLote mtp;
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
    public IProductoLote(JInternalFrame ifr) {
        initComponents();
        cp = new CProductoLote();
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();        
        this.ifr = ifr;
        lbAviso.setVisible(false);
        dteFechaIngreso.setBounds(287, 57, 110, 30);
        dteFechaVencimiento.setBounds(495, 57, 110, 30);
        txtDteFechaIngreso.setBounds(287, 57, 110, 30);
        txtDteFechaVencimiento.setBounds(495, 57, 110, 30);
    }

    public final void CargaDatos(){
        
        if (tipoSeleccion == -1){
            String columnas[] = new String[]{"CO_COMPANIA", "CO_PRODUCTO"};
            Object valores[] = new Object[]{AtuxVariables.vCodigoCompania,txtCodigo.getText()};        
            mtp = new ModeloTablaProductoLote(columnas,valores);
        }else{
            String Estado ="";
            if (tipoSeleccion == 1){
                Estado ="A";
            }else{
                Estado ="I";
            }
            String columnas[] = new String[]{"CO_COMPANIA", "CO_PRODUCTO","ES_LOTE"};
            Object valores[] = new Object[]{AtuxVariables.vCodigoCompania,txtCodigo.getText(),Estado};
            mtp = new ModeloTablaProductoLote(columnas,valores);
        }
        
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaProductoLote.anchoColumnas);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtProductoLote.getDocument(), FiltraEntrada.SOLO_NUMEROS, 15, false);
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
            cp.setProductoLote(mtp.getFila(tblListado.getSelectedRow()));
            setProductoLote();           
            btnModificar.setEnabled(true);
       }
    }

    private void setProductoLote(){
        Limpiar();

        this.txtProductoLote.setText(String.valueOf(cp.getProductoLote().getNuLote()));
        this.dteFechaVencimiento.setDate(cp.getProductoLote().getFeVencimiento());

        if("A".equals(cp.getProductoLote().getEsLote())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }

        ti_documento_recepcion = cp.getProductoLote().getTiDocumentoRecepcion();
        nu_documento_recepcion = cp.getProductoLote().getNuDocumentoRecepcion();

        txtDteFechaIngreso.setText(AtuxUtility.getDateToString(dteFechaIngreso.getDate(), "dd/MM/yyyy"));
        txtDteFechaVencimiento.setText(AtuxUtility.getDateToString(dteFechaVencimiento.getDate(), "dd/MM/yyyy"));
    }
    
    private void Limpiar(){
        this.txtProductoLote.setText("");
        this.chbEstado.setSelected(true);
        ti_documento_recepcion="";
        nu_documento_recepcion="";
        txtDteFechaIngreso.setText("");
        txtDteFechaVencimiento.setText("");
        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblfecha, lblVencimiento, lblCodigo,lblProductoLote,lblDescripcion,lblUnidad,lblEstado,txtCodigo,txtDescripcion,txtUnidad},false,"");        
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        
        this.dteFechaIngreso.setVisible(true);
        this.dteFechaVencimiento.setVisible(true);
        this.txtDteFechaIngreso.setVisible(false);
        this.txtDteFechaVencimiento.setVisible(false);
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblfecha, lblVencimiento, lblCodigo,lblProductoLote,lblDescripcion,lblUnidad,lblEstado,txtCodigo,txtDescripcion,txtUnidad},false,"");        
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);

        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        this.dteFechaIngreso.setVisible(false);
        this.dteFechaVencimiento.setVisible(false);
        this.txtDteFechaIngreso.setVisible(true);
        this.txtDteFechaVencimiento.setVisible(true);

        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtProductoLote.getText());
    }

    public boolean verficarCambios(){
        if(!this.txtProductoLote.getText().equals(String.valueOf(cp.getProductoLote().getNuLote()))){
            return true;
        }else if(dteFechaIngreso.getDate() != cp.getProductoLote().getFeVencimiento()){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getProductoLote().getEsLote()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException{
        cp.getProductoLote().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getProductoLote().setCoLocal(AtuxVariables.vCodigoLocal);
        cp.getProductoLote().setCoProducto(txtCodigo.getText());
        cp.getProductoLote().setNuLote(txtProductoLote.getText());
        cp.getProductoLote().setFeIngreso(dteFechaIngreso.getDate());
        cp.getProductoLote().setFeVencimiento(dteFechaVencimiento.getDate());
        

        if (chbEstado.isSelected()){
            cp.getProductoLote().setEsLote("A");
        }else{
            cp.getProductoLote().setEsLote("I");
        }

        Boolean EstadoGuardar=false;

        if(!esActualizacion){
            cp.getProductoLote().setIdCreaLote(AtuxVariables.vIdUsuario);
            cp.getProductoLote().setFeCreaLote(FormatoFecha(AtuxDBUtility.getFechaHoraBD(0)));
            EstadoGuardar = cp.guardarRegistro(cp.getProductoLote());
        }else{
            cp.getProductoLote().setTiDocumentoRecepcion(ti_documento_recepcion);
            cp.getProductoLote().setNuDocumentoRecepcion(nu_documento_recepcion);
            
            cp.getProductoLote().setIdModLote(AtuxVariables.vIdUsuario);
            cp.getProductoLote().setFeModLote(FormatoFecha(AtuxDBUtility.getFechaHoraBD(0)));

            int act = cp.actualizarRegistro(cp.getProductoLote());
            if (act ==1) {
                EstadoGuardar = true;
            }
        }

        if(EstadoGuardar){
            EstadoGuardar = true; 
        }else{
            EstadoGuardar = false; 
        }
        return EstadoGuardar;
    }

    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IProductoLote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void chbSetActivo(boolean opcion){
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104,204,0));
        chbEstado.setForeground(Color.BLACK);
        if(!opcion){
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    public void setCodigoProducto(String CodProducto){
        txtCodigo.setText(CodProducto);
    }
    
    public void setDescripcionProducto(String DescripcionProducto){
        txtDescripcion.setText(DescripcionProducto);
    }
    
    public void setUnidadProducto(String UnidadProducto){
        txtUnidad.setText(UnidadProducto);
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
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        txtUnidad = new elaprendiz.gui.textField.TextField();
        lblProductoLote = new javax.swing.JLabel();
        txtProductoLote = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        lblfecha = new javax.swing.JLabel();
        dteFechaIngreso = new com.toedter.calendar.JDateChooser();
        lblVencimiento = new javax.swing.JLabel();
        dteFechaVencimiento = new com.toedter.calendar.JDateChooser();
        txtProductoLote1 = new elaprendiz.gui.textField.TextField();
        txtDteFechaVencimiento = new elaprendiz.gui.textField.TextField();
        txtDteFechaIngreso = new elaprendiz.gui.textField.TextField();
        lblListado = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        lbAviso = new elaprendiz.gui.label.LabelRect();

        setPreferredSize(new java.awt.Dimension(780, 300));
        setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(780, 300));

        pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Maestro de Producto", 1, 2));
        pnlEntradas.setOpaque(false);
        pnlEntradas.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradas.setLayout(null);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");
        pnlEntradas.add(lblCodigo);
        lblCodigo.setBounds(16, 17, 54, 27);

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setText("Descripcion:");
        pnlEntradas.add(lblDescripcion);
        lblDescripcion.setBounds(167, 19, 83, 24);

        lblUnidad.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUnidad.setText("Unidad:");
        pnlEntradas.add(lblUnidad);
        lblUnidad.setBounds(573, 22, 53, 17);

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtCodigo);
        txtCodigo.setBounds(80, 16, 83, 30);

        txtDescripcion.setEditable(false);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setName("pdescrip"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtDescripcion);
        txtDescripcion.setBounds(254, 16, 301, 30);

        txtUnidad.setEditable(false);
        txtUnidad.setDireccionDeSombra(30);
        txtUnidad.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtUnidad.setFont(new java.awt.Font("Arial", 0, 12));
        txtUnidad.setName("pdescrip"); // NOI18N
        txtUnidad.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtUnidad);
        txtUnidad.setBounds(636, 16, 141, 30);

        lblProductoLote.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProductoLote.setText("Lote");
        pnlEntradas.add(lblProductoLote);
        lblProductoLote.setBounds(16, 60, 31, 24);

        txtProductoLote.setEditable(false);
        txtProductoLote.setDireccionDeSombra(30);
        txtProductoLote.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtProductoLote.setFont(new java.awt.Font("Arial", 0, 12));
        txtProductoLote.setName("pdescrip"); // NOI18N
        txtProductoLote.setPreferredSize(new java.awt.Dimension(120, 25));
        txtProductoLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoLoteKeyReleased(evt);
            }
        });
        pnlEntradas.add(txtProductoLote);
        txtProductoLote.setBounds(51, 57, 162, 30);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");
        pnlEntradas.add(lblEstado);
        lblEstado.setBounds(618, 61, 53, 17);

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setEnabled(false);
        chbEstado.setPreferredSize(new java.awt.Dimension(100, 25));
        chbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEstadoActionPerformed(evt);
            }
        });
        chbEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbEstadoKeyReleased(evt);
            }
        });
        pnlEntradas.add(chbEstado);
        chbEstado.setBounds(677, 57, 100, 25);

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblfecha.setText("Ingreso:");
        pnlEntradas.add(lblfecha);
        lblfecha.setBounds(223, 58, 60, 27);

        dteFechaIngreso.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaIngreso.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaIngreso.setDate(Calendar.getInstance().getTime());
        dteFechaIngreso.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaIngreso.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaIngreso.setRequestFocusEnabled(false);
        dteFechaIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaIngresoKeyReleased(evt);
            }
        });
        pnlEntradas.add(dteFechaIngreso);
        dteFechaIngreso.setBounds(287, 61, 110, 22);

        lblVencimiento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVencimiento.setText("Vencimiento:");
        pnlEntradas.add(lblVencimiento);
        lblVencimiento.setBounds(401, 58, 90, 27);

        dteFechaVencimiento.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaVencimiento.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaVencimiento.setDate(Calendar.getInstance().getTime());
        dteFechaVencimiento.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaVencimiento.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaVencimiento.setRequestFocusEnabled(false);
        dteFechaVencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaVencimientoKeyReleased(evt);
            }
        });
        pnlEntradas.add(dteFechaVencimiento);
        dteFechaVencimiento.setBounds(495, 61, 110, 22);

        txtProductoLote1.setEditable(false);
        txtProductoLote1.setDireccionDeSombra(30);
        txtProductoLote1.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtProductoLote1.setFont(new java.awt.Font("Arial", 0, 12));
        txtProductoLote1.setName("pdescrip"); // NOI18N
        txtProductoLote1.setPreferredSize(new java.awt.Dimension(120, 25));
        txtProductoLote1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoLote1KeyReleased(evt);
            }
        });
        pnlEntradas.add(txtProductoLote1);
        txtProductoLote1.setBounds(223, 114, 21, 15);

        txtDteFechaVencimiento.setEditable(false);
        txtDteFechaVencimiento.setDireccionDeSombra(30);
        txtDteFechaVencimiento.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDteFechaVencimiento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDteFechaVencimiento.setName("pdescrip"); // NOI18N
        txtDteFechaVencimiento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDteFechaVencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDteFechaVencimientoKeyReleased(evt);
            }
        });
        pnlEntradas.add(txtDteFechaVencimiento);
        txtDteFechaVencimiento.setBounds(260, 80, 21, 15);

        txtDteFechaIngreso.setEditable(false);
        txtDteFechaIngreso.setDireccionDeSombra(30);
        txtDteFechaIngreso.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDteFechaIngreso.setFont(new java.awt.Font("Arial", 0, 12));
        txtDteFechaIngreso.setName("pdescrip"); // NOI18N
        txtDteFechaIngreso.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDteFechaIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDteFechaIngresoKeyReleased(evt);
            }
        });
        pnlEntradas.add(txtDteFechaIngreso);
        txtDteFechaIngreso.setBounds(230, 80, 21, 15);

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

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorTDeCambio.setOpaque(false);
        pnlBuscadorTDeCambio.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorTDeCambio.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnPrimero);
        btnPrimero.setBounds(12, 2, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnAnterior);
        btnAnterior.setBounds(65, 2, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnSiguiente);
        btnSiguiente.setBounds(111, 2, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnUltimo);
        btnUltimo.setBounds(156, 2, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbTodos);
        rbTodos.setBounds(209, 2, 69, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setSelected(true);
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbAtivos);
        rbAtivos.setBounds(283, 2, 77, 25);

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbNoActivos);
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbNoActivos);
        rbNoActivos.setBounds(365, 2, 101, 25);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesTDeCambio.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnNuevo);
        btnNuevo.setBounds(12, 2, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnModificar);
        btnModificar.setBounds(96, 2, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnGuardar);
        btnGuardar.setBounds(204, 2, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnCancelar);
        btnCancelar.setBounds(299, 2, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnSalir);
        btnSalir.setBounds(403, 2, 88, 25);

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Médico");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setProductoLote(mtp.getFila(numRegistros));
        setProductoLote();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setProductoLote(mtp.getFila(numRegistros));
        setProductoLote();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setProductoLote(mtp.getFila(numRegistros));
        setProductoLote();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setProductoLote(mtp.getFila(numRegistros));
        setProductoLote();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        ActivarCasillas();
        txtProductoLote.requestFocus();        
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtProductoLote.requestFocus();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradas,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 

        if(!this.verficarCambios()){
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
     
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProductoLote.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas,false);
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

private void txtProductoLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoLoteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaIngreso.requestFocus();
             break;
    }
}//GEN-LAST:event_txtProductoLoteKeyReleased

private void dteFechaIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaIngresoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaVencimiento.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaIngresoKeyReleased

private void dteFechaVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaVencimientoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaVencimientoKeyReleased

private void txtProductoLote1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoLote1KeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtProductoLote1KeyReleased

private void txtDteFechaVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDteFechaVencimientoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDteFechaVencimientoKeyReleased

private void txtDteFechaIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDteFechaIngresoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDteFechaIngresoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private com.toedter.calendar.JDateChooser dteFechaIngreso;
    private com.toedter.calendar.JDateChooser dteFechaVencimiento;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JScrollPane lblListado;
    private javax.swing.JLabel lblProductoLote;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JLabel lblVencimiento;
    private javax.swing.JLabel lblfecha;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtDteFechaIngreso;
    private elaprendiz.gui.textField.TextField txtDteFechaVencimiento;
    private elaprendiz.gui.textField.TextField txtProductoLote;
    private elaprendiz.gui.textField.TextField txtProductoLote1;
    private elaprendiz.gui.textField.TextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
