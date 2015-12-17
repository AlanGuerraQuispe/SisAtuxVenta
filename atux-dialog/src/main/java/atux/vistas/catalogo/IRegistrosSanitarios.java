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

import atux.controllers.CProductoRegSanitario;
import atux.modelgui.ModeloTablaProductoRegSanitario;
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
public class IRegistrosSanitarios extends javax.swing.JPanel {
    private CProductoRegSanitario cp;
    private ModeloTablaProductoRegSanitario mtp;
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
    public IRegistrosSanitarios(JInternalFrame ifr) {
        initComponents();
        cp = new CProductoRegSanitario();
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();        
        this.ifr = ifr;
        lbAviso.setVisible(false);
        dteFechaVencimiento.setBounds(453, 57, 110, 30);
        txtDteFechaVencimiento.setBounds(453, 57, 110, 30);
    }

    public final void CargaDatos(){
        String columnas[] = new String[]{"CO_COMPANIA", "CO_PRODUCTO"};
        Object valores[] = new Object[]{AtuxVariables.vCodigoCompania,txtCodigo.getText()};        
        mtp = new ModeloTablaProductoRegSanitario(columnas,valores);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaProductoRegSanitario.anchoColumnas);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtProductoRegSanitario.getDocument(), FiltraEntrada.SOLO_NUMEROS, 15, false);
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
            cp.setProductoRegSanitario(mtp.getFila(tblListado.getSelectedRow()));
            setProductoRegSanitario();           
            btnModificar.setEnabled(true);
       }
    }

    private void setProductoRegSanitario(){
        Limpiar();

        this.txtProductoRegSanitario.setText(String.valueOf(cp.getProductoRegSanitario().getNuRegSanitario()));
        this.dteFechaVencimiento.setDate(cp.getProductoRegSanitario().getFeVenRegSanitario());
        this.txtDteFechaVencimiento.setText(AtuxUtility.getDateToString(dteFechaVencimiento.getDate(), "dd/MM/yyyy"));
        
        if("A".equals(cp.getProductoRegSanitario().getEsRegSanitario())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
    }
    
    private void Limpiar(){
        this.txtProductoRegSanitario.setText("");
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblfecha, lblCodigo,lblProductoRegSanitario,lblDescripcion,lblUnidad,lblEstado,txtCodigo,txtDescripcion,txtUnidad},false,"");        
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
        this.dteFechaVencimiento.setVisible(true);
        this.txtDteFechaVencimiento.setVisible(false);
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblfecha, lblCodigo,lblProductoRegSanitario,lblDescripcion,lblUnidad,lblEstado,txtCodigo,txtDescripcion,txtUnidad},false,"");        
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
        this.dteFechaVencimiento.setVisible(false);
        this.txtDteFechaVencimiento.setVisible(true);

        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtProductoRegSanitario.getText());
    }

    public boolean verficarCambios(){
        if(!this.txtProductoRegSanitario.getText().equals(String.valueOf(cp.getProductoRegSanitario().getNuRegSanitario()))){
            return true;
        }else if(dteFechaVencimiento.getDate() != cp.getProductoRegSanitario().getFeVenRegSanitario()){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getProductoRegSanitario().getEsRegSanitario()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException{
        cp.getProductoRegSanitario().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getProductoRegSanitario().setCoLocal(AtuxVariables.vCodigoLocal);
        cp.getProductoRegSanitario().setCoProducto(txtCodigo.getText());
        cp.getProductoRegSanitario().setNuRegSanitario(txtProductoRegSanitario.getText());
        cp.getProductoRegSanitario().setFeVenRegSanitario(dteFechaVencimiento.getDate());

        if (chbEstado.isSelected()){
            cp.getProductoRegSanitario().setEsRegSanitario("A");
        }else{
            cp.getProductoRegSanitario().setEsRegSanitario("I");
        }

        Boolean EstadoGuardar=false;

        if(!esActualizacion){
            cp.getProductoRegSanitario().setIdCreaRegSanitario(AtuxVariables.vIdUsuario);
            cp.getProductoRegSanitario().setFeCreaRegSanitario(FormatoFecha(AtuxDBUtility.getFechaHoraBD(0)));
            EstadoGuardar = cp.guardarRegistro(cp.getProductoRegSanitario());
        }else{
            cp.getProductoRegSanitario().setIdModRegSanitario(AtuxVariables.vIdUsuario);
            cp.getProductoRegSanitario().setFeModRegSanitario(FormatoFecha(AtuxDBUtility.getFechaHoraBD(0)));

            int act = cp.actualizarRegistro(cp.getProductoRegSanitario());
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
            Logger.getLogger(IRegistrosSanitarios.class.getName()).log(Level.SEVERE, null, ex);
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
        lblProductoRegSanitario = new javax.swing.JLabel();
        txtProductoRegSanitario = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        lblfecha = new javax.swing.JLabel();
        dteFechaVencimiento = new com.toedter.calendar.JDateChooser();
        txtDteFechaVencimiento = new elaprendiz.gui.textField.TextField();
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

        lblProductoRegSanitario.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProductoRegSanitario.setText("Registro Sanitario");
        pnlEntradas.add(lblProductoRegSanitario);
        lblProductoRegSanitario.setBounds(16, 60, 123, 24);

        txtProductoRegSanitario.setEditable(false);
        txtProductoRegSanitario.setDireccionDeSombra(30);
        txtProductoRegSanitario.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtProductoRegSanitario.setFont(new java.awt.Font("Arial", 0, 12));
        txtProductoRegSanitario.setName("pdescrip"); // NOI18N
        txtProductoRegSanitario.setPreferredSize(new java.awt.Dimension(120, 25));
        txtProductoRegSanitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoRegSanitarioKeyReleased(evt);
            }
        });
        pnlEntradas.add(txtProductoRegSanitario);
        txtProductoRegSanitario.setBounds(143, 57, 162, 30);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");
        pnlEntradas.add(lblEstado);
        lblEstado.setBounds(581, 63, 53, 17);

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
        chbEstado.setBounds(640, 59, 116, 25);

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblfecha.setText("Fecha Vencimiento:");
        pnlEntradas.add(lblfecha);
        lblfecha.setBounds(315, 58, 134, 27);

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
        dteFechaVencimiento.setBounds(453, 61, 110, 22);

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
        txtDteFechaVencimiento.setBounds(345, 87, 20, 10);

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
        cp.setProductoRegSanitario(mtp.getFila(numRegistros));
        setProductoRegSanitario();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setProductoRegSanitario(mtp.getFila(numRegistros));
        setProductoRegSanitario();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setProductoRegSanitario(mtp.getFila(numRegistros));
        setProductoRegSanitario();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setProductoRegSanitario(mtp.getFila(numRegistros));
        setProductoRegSanitario();           

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
        txtProductoRegSanitario.requestFocus();        
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtProductoRegSanitario.requestFocus();
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
            Logger.getLogger(IRegistrosSanitarios.class.getName()).log(Level.SEVERE, null, ex);
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

private void txtProductoRegSanitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoRegSanitarioKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaVencimiento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtProductoRegSanitarioKeyReleased

private void dteFechaVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaVencimientoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaVencimientoKeyReleased

private void txtDteFechaVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDteFechaVencimientoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDteFechaVencimientoKeyReleased

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
    private com.toedter.calendar.JDateChooser dteFechaVencimiento;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JScrollPane lblListado;
    private javax.swing.JLabel lblProductoRegSanitario;
    private javax.swing.JLabel lblUnidad;
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
    private elaprendiz.gui.textField.TextField txtDteFechaVencimiento;
    private elaprendiz.gui.textField.TextField txtProductoRegSanitario;
    private elaprendiz.gui.textField.TextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
