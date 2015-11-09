/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ILaboratorio.java
 *
 * Created on 23/12/2014, 05:32:34 PM
 */
package atux.vistas.catalogo;
import atux.controllers.CCadena;
import atux.controllers.CCompania;
import atux.controllers.CLocales;
import atux.controllers.CPaises;
import atux.controllers.CRegion;
import atux.controllers.CTipoPoblacion;
import atux.controllers.CTipoVia;
import atux.controllers.CUbigeo;
import atux.modelbd.TipoPoblacion;
import atux.modelbd.TipoVia;
import atux.modelgui.ModeloTablaLocales;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarCadena;
import atux.vistas.buscar.BuscarCompania;
import atux.vistas.buscar.BuscarLocales;
import atux.vistas.buscar.BuscarPaises;
import atux.vistas.buscar.BuscarRegion;
import atux.vistas.buscar.BuscarUbigeo;
import elaprendiz.gui.comboBox.ComboBoxRect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Omar Davila
 */
public final class ILocales extends javax.swing.JInternalFrame {
    private CLocales cp;
    private CTipoVia ctv;
    private CTipoPoblacion ctp;    
    private CCadena ctc;    
    private CCompania ctcia;
    private CTipoVia controllerTipoVia;
    private CTipoPoblacion controllerTipoPoblacion;
    private DefaultComboBoxModel mTipoVia;
    private DefaultComboBoxModel mTipoPoblacion;    
    private ModeloTablaLocales mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    private String Busqueda="NO";
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    
    /** Creates new form ILaboratorio */
    public ILocales() {
        initComponents();
        
        cp = new CLocales();
        ctv = new CTipoVia();
        ctp = new CTipoPoblacion();
        ctc = new CCadena();
        ctcia = new CCompania();
//        ModeloTablaLocales(String[] campo,Object[] valor) {
//        mtp = new ModeloTablaLocales(inicioPag,finalPag);
//        numRegistros = mtp.getCantidadRegistros();

        controllerTipoVia = new CTipoVia();
        mTipoVia = new DefaultComboBoxModel(controllerTipoVia.getTipoVia().toArray());
        this.cmbTipoVia.setModel(mTipoVia);
        this.cmbTipoVia.setSelectedIndex(0);

        controllerTipoPoblacion = new CTipoPoblacion();
        mTipoPoblacion = new DefaultComboBoxModel(controllerTipoPoblacion.getTipoPoblacion().toArray());
        this.cmbTipoPoblacion.setModel(mTipoPoblacion);
        this.cmbTipoPoblacion.setSelectedIndex(0);

        Object[] valores = new Object[]{txtCodigoCompania.getText()}; 
        String[] columnas = new String[]{"CO_COMPANIA"};
        valores = new Object[]{txtCodigoCompania.getText()}; 
        mtp = new ModeloTablaLocales(columnas, valores);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaLocales.anchoColumnas);
        Helper.ajustarAnchoColumnas(tblListado);
        setEventSelectionModel(tblListado.getSelectionModel());

        cmbTipoLocal.setBounds(104, 36, 144, 25);
        cmbTipoVia.setBounds(16, 142, 108, 25);
        cmbTipoPoblacion.setBounds(492, 142, 183, 25);
        cmbImprimeTestigo.setBounds(286, 90, 69, 25);
        cmbImprimeComanda.setBounds(394, 90, 69, 25);
        cmbImprimeTicket.setBounds(497, 90, 69, 25);
        cmbTicketBoleta.setBounds(589, 90, 69, 25);
        cmbTicketFactura.setBounds(700, 90, 69, 25);
        cmbAfectoIGV.setBounds(500, 143, 69, 25);
        dteFechaApertura.setBounds(119, 143, 111, 25);
        dteFechaCierre.setBounds(240, 143, 120, 25);

        txtCmbTipoLocal.setBounds(104, 36, 144, 25);
        txtCmbTipoVia.setBounds(16, 142, 108, 25);
        txtCmbTipoPoblacion.setBounds(492, 142, 183, 25);
        txtCmbImprimeTestigo.setBounds(286, 90, 69, 25);
        txtCmbImprimeComanda.setBounds(394, 90, 69, 25);
        txtCmbImprimeTicket.setBounds(497, 90, 69, 25);
        txtCmbTicketBoleta.setBounds(589, 90, 69, 25);
        txtCmbTicketFactura.setBounds(700, 90, 69, 25);
        txtCmbAfectoIGV.setBounds(500, 143, 69, 25);
        txtDteFechaApertura.setBounds(119, 143, 111, 25);
        txtDteFechaCierre.setBounds(240, 143, 120, 25);

        setFiltroTexto();
        Limpiar();
        DesActivarCasillas();
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
        pnlBuscador = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        bntParametrosReposicion = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlAccionesLab = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        pnlEntradasObligatorias = new javax.swing.JPanel();
        txtCmbTipoLocal = new elaprendiz.gui.textField.TextField();
        txtCmbTipoPoblacion = new elaprendiz.gui.textField.TextField();
        txtCmbTipoVia = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        lblCodigo = new javax.swing.JLabel();
        txtCodigoDepartamento = new elaprendiz.gui.textField.TextField();
        txtDepartamento = new elaprendiz.gui.textField.TextField();
        lblDepartamento = new javax.swing.JLabel();
        txtCodigoProvincia = new elaprendiz.gui.textField.TextField();
        txtProvincia = new elaprendiz.gui.textField.TextField();
        lblProvincia = new javax.swing.JLabel();
        txtDistrito = new elaprendiz.gui.textField.TextField();
        txtCodigoDistrito = new elaprendiz.gui.textField.TextField();
        lblDistrito = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        lblTipoLocal = new javax.swing.JLabel();
        txtCodigoPais = new elaprendiz.gui.textField.TextField();
        txtPais = new elaprendiz.gui.textField.TextField();
        lblPais = new javax.swing.JLabel();
        txtDescripcionCorta = new elaprendiz.gui.textField.TextField();
        lblDescripcionCorta = new javax.swing.JLabel();
        cmbTipoVia = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTipoVia = new javax.swing.JLabel();
        lblNombreVia = new javax.swing.JLabel();
        txtNombreVia = new elaprendiz.gui.textField.TextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new elaprendiz.gui.textField.TextField();
        lblDpto = new javax.swing.JLabel();
        txtDpto = new elaprendiz.gui.textField.TextField();
        lblManzana = new javax.swing.JLabel();
        txtManzana = new elaprendiz.gui.textField.TextField();
        txtLote = new elaprendiz.gui.textField.TextField();
        lblLote = new javax.swing.JLabel();
        cmbTipoPoblacion = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTipoVivienda = new javax.swing.JLabel();
        txtNombrePoblacion = new elaprendiz.gui.textField.TextField();
        lblPoblacion = new javax.swing.JLabel();
        cmbTipoLocal = new elaprendiz.gui.comboBox.ComboBoxRect();
        txtCodigoRegion = new elaprendiz.gui.textField.TextField();
        txtRegion = new elaprendiz.gui.textField.TextField();
        lblRegion = new javax.swing.JLabel();
        pnlEntradasComplementarias = new javax.swing.JPanel();
        txtCmbImprimeTestigo = new elaprendiz.gui.textField.TextField();
        txtCmbImprimeComanda = new elaprendiz.gui.textField.TextField();
        txtCmbImprimeTicket = new elaprendiz.gui.textField.TextField();
        txtCmbTicketBoleta = new elaprendiz.gui.textField.TextField();
        txtCmbTicketFactura = new elaprendiz.gui.textField.TextField();
        txtCmbAfectoIGV = new elaprendiz.gui.textField.TextField();
        txtDteFechaApertura = new elaprendiz.gui.textField.TextField();
        txtDteFechaCierre = new elaprendiz.gui.textField.TextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new elaprendiz.gui.textField.TextField();
        lblCodTelefPais = new javax.swing.JLabel();
        txtCodigoTelefPais = new elaprendiz.gui.textField.TextField();
        lblCodTelefCiudad = new javax.swing.JLabel();
        txtCodigoTelefCiudad = new elaprendiz.gui.textField.TextField();
        lblNumeroTelefono = new javax.swing.JLabel();
        txtNumeroTelefono = new elaprendiz.gui.textField.TextField();
        lblNumeroFax = new javax.swing.JLabel();
        txtNumeroFax = new elaprendiz.gui.textField.TextField();
        txtAnexo = new elaprendiz.gui.textField.TextField();
        lblAnexo = new javax.swing.JLabel();
        txtNumeroMovil = new elaprendiz.gui.textField.TextField();
        lblMovil = new javax.swing.JLabel();
        cmbImprimeTestigo = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblImprimeTestigo = new javax.swing.JLabel();
        lblImprimeComanda = new javax.swing.JLabel();
        cmbImprimeComanda = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbImprimeTicket = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblImprimeTicket = new javax.swing.JLabel();
        lblTicketBoleta = new javax.swing.JLabel();
        cmbTicketBoleta = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTicketFactura = new javax.swing.JLabel();
        cmbTicketFactura = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblOrdenApertura = new javax.swing.JLabel();
        txtOrdenApertura = new elaprendiz.gui.textField.TextField();
        lblFechaApertura = new javax.swing.JLabel();
        dteFechaApertura = new com.toedter.calendar.JDateChooser();
        txtMontoCotizacion = new elaprendiz.gui.textField.TextField();
        lblMontoCotizacion = new javax.swing.JLabel();
        lblAfectoIGV = new javax.swing.JLabel();
        cmbAfectoIGV = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTiempoSuministro = new javax.swing.JLabel();
        txtTiempoReposicion = new elaprendiz.gui.textField.TextField();
        lblTiempoReposicion = new javax.swing.JLabel();
        txtTiempoSuministro = new elaprendiz.gui.textField.TextField();
        lblNombreResponsableLocal = new javax.swing.JLabel();
        txtNombreResponsableLocal = new elaprendiz.gui.textField.TextField();
        lblNombreResponsableAlterno = new javax.swing.JLabel();
        txtNombreResponsableAlterno = new elaprendiz.gui.textField.TextField();
        txtMensajeTicket = new elaprendiz.gui.textField.TextField();
        lblMensajeTicket = new javax.swing.JLabel();
        txtZonaSupervision = new elaprendiz.gui.textField.TextField();
        txtCodigoZonaSupervision = new elaprendiz.gui.textField.TextField();
        lblZonaSupervicion = new javax.swing.JLabel();
        lblCadena = new javax.swing.JLabel();
        txtCodigoCadena = new elaprendiz.gui.textField.TextField();
        txtCadena = new elaprendiz.gui.textField.TextField();
        lblFechaCierre = new javax.swing.JLabel();
        dteFechaCierre = new com.toedter.calendar.JDateChooser();
        plnCompania = new javax.swing.JPanel();
        txtCodigoCompania = new elaprendiz.gui.textField.TextField();
        txtDescripcionCompania = new elaprendiz.gui.textField.TextField();
        lblCodigoCompania = new javax.swing.JLabel();
        lblDescripcionCompania = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Maestro de Locales");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscador.setOpaque(false);
        pnlBuscador.setPreferredSize(new java.awt.Dimension(780, 200));
        pnlBuscador.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnPrimero);
        btnPrimero.setBounds(12, 53, 101, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnAnterior);
        btnAnterior.setBounds(12, 84, 101, 25);

        btnBuscar.setBackground(new java.awt.Color(102, 204, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnBuscar);
        btnBuscar.setBounds(12, 115, 101, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnSiguiente);
        btnSiguiente.setBounds(12, 146, 101, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnUltimo);
        btnUltimo.setBounds(12, 177, 101, 25);

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
        pnlBuscador.add(rbTodos);
        rbTodos.setBounds(12, 209, 101, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscador.add(rbAtivos);
        rbAtivos.setBounds(12, 237, 101, 25);

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
        pnlBuscador.add(rbNoActivos);
        rbNoActivos.setBounds(12, 265, 101, 25);

        bntParametrosReposicion.setBackground(new java.awt.Color(0, 204, 0));
        bntParametrosReposicion.setText("REPOSICION");
        bntParametrosReposicion.setAlignmentX(0.1F);
        bntParametrosReposicion.setAlignmentY(0.1F);
        bntParametrosReposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntParametrosReposicionActionPerformed(evt);
            }
        });
        pnlBuscador.add(bntParametrosReposicion);
        bntParametrosReposicion.setBounds(12, 308, 101, 31);

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(tblListado);

        pnlAccionesLab.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesLab.setOpaque(false);
        pnlAccionesLab.setPreferredSize(new java.awt.Dimension(780, 200));
        pnlAccionesLab.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnNuevo);
        btnNuevo.setBounds(12, 2, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnModificar);
        btnModificar.setBounds(96, 2, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnGuardar);
        btnGuardar.setBounds(200, 2, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnCancelar);
        btnCancelar.setBounds(295, 2, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnSalir);
        btnSalir.setBounds(395, 2, 88, 25);

        pnlEntradasObligatorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos Obligatorios", 1, 2));
        pnlEntradasObligatorias.setOpaque(false);
        pnlEntradasObligatorias.setLayout(null);

        txtCmbTipoLocal.setEditable(false);
        txtCmbTipoLocal.setAlignmentX(0.2F);
        txtCmbTipoLocal.setAlignmentY(0.2F);
        txtCmbTipoLocal.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTipoLocal.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTipoLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTipoLocalKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCmbTipoLocal);
        txtCmbTipoLocal.setBounds(800, 120, 20, 10);

        txtCmbTipoPoblacion.setEditable(false);
        txtCmbTipoPoblacion.setAlignmentX(0.2F);
        txtCmbTipoPoblacion.setAlignmentY(0.2F);
        txtCmbTipoPoblacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTipoPoblacion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTipoPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTipoPoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCmbTipoPoblacion);
        txtCmbTipoPoblacion.setBounds(800, 150, 20, 10);

        txtCmbTipoVia.setEditable(false);
        txtCmbTipoVia.setAlignmentX(0.2F);
        txtCmbTipoVia.setAlignmentY(0.2F);
        txtCmbTipoVia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTipoVia.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTipoVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTipoViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCmbTipoVia);
        txtCmbTipoVia.setBounds(800, 130, 20, 10);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Estado:");
        pnlEntradasObligatorias.add(lblEstado);
        lblEstado.setBounds(681, 16, 110, 17);

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
        pnlEntradasObligatorias.add(chbEstado);
        chbEstado.setBounds(686, 36, 130, 25);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigo.setText("Codigo:");
        pnlEntradasObligatorias.add(lblCodigo);
        lblCodigo.setBounds(16, 16, 70, 17);

        txtCodigoDepartamento.setEditable(false);
        txtCodigoDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDepartamento.setAlignmentX(0.2F);
        txtCodigoDepartamento.setAlignmentY(0.2F);
        txtCodigoDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDepartamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoDepartamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoDepartamentoFocusLost(evt);
            }
        });
        txtCodigoDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDepartamentoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoDepartamento);
        txtCodigoDepartamento.setBounds(171, 85, 25, 25);

        txtDepartamento.setEditable(false);
        txtDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDepartamento.setAlignmentX(0.2F);
        txtDepartamento.setAlignmentY(0.2F);
        txtDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtDepartamento);
        txtDepartamento.setBounds(201, 85, 115, 25);

        lblDepartamento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDepartamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDepartamento.setText("Departamento");
        lblDepartamento.setAlignmentX(0.2F);
        lblDepartamento.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDepartamento);
        lblDepartamento.setBounds(171, 67, 145, 17);

        txtCodigoProvincia.setEditable(false);
        txtCodigoProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoProvincia.setAlignmentX(0.2F);
        txtCodigoProvincia.setAlignmentY(0.2F);
        txtCodigoProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoProvincia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoProvincia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoProvinciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoProvinciaFocusLost(evt);
            }
        });
        txtCodigoProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProvinciaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoProvincia);
        txtCodigoProvincia.setBounds(322, 85, 25, 25);

        txtProvincia.setEditable(false);
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProvincia.setAlignmentX(0.2F);
        txtProvincia.setAlignmentY(0.2F);
        txtProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtProvincia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtProvincia);
        txtProvincia.setBounds(352, 85, 115, 25);

        lblProvincia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProvincia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProvincia.setText("Provincia");
        lblProvincia.setAlignmentX(0.2F);
        lblProvincia.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblProvincia);
        lblProvincia.setBounds(326, 67, 141, 17);

        txtDistrito.setEditable(false);
        txtDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDistrito.setAlignmentX(0.2F);
        txtDistrito.setAlignmentY(0.2F);
        txtDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtDistrito.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtDistrito);
        txtDistrito.setBounds(507, 85, 115, 25);

        txtCodigoDistrito.setEditable(false);
        txtCodigoDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDistrito.setAlignmentX(0.2F);
        txtCodigoDistrito.setAlignmentY(0.2F);
        txtCodigoDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDistrito.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDistrito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoDistritoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoDistritoFocusLost(evt);
            }
        });
        txtCodigoDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDistritoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoDistrito);
        txtCodigoDistrito.setBounds(477, 85, 25, 25);

        lblDistrito.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDistrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistrito.setText("Distrito");
        lblDistrito.setAlignmentX(0.2F);
        lblDistrito.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDistrito);
        lblDistrito.setBounds(477, 67, 145, 17);

        txtCodigo.setEditable(false);
        txtCodigo.setAlignmentX(0.2F);
        txtCodigo.setAlignmentY(0.2F);
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigo);
        txtCodigo.setBounds(16, 36, 70, 25);

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcion.setText("Descripción");
        pnlEntradasObligatorias.add(lblDescripcion);
        lblDescripcion.setBounds(275, 16, 220, 17);

        txtDescripcion.setEditable(false);
        txtDescripcion.setAlignmentX(0.2F);
        txtDescripcion.setAlignmentY(0.2F);
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDescripcion);
        txtDescripcion.setBounds(266, 36, 238, 25);

        lblTipoLocal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoLocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoLocal.setText("Tipo Local:");
        pnlEntradasObligatorias.add(lblTipoLocal);
        lblTipoLocal.setBounds(96, 16, 160, 17);

        txtCodigoPais.setEditable(false);
        txtCodigoPais.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoPais.setAlignmentX(0.2F);
        txtCodigoPais.setAlignmentY(0.2F);
        txtCodigoPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoPais.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoPais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoPaisFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoPaisFocusLost(evt);
            }
        });
        txtCodigoPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPaisKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoPais);
        txtCodigoPais.setBounds(16, 85, 25, 25);

        txtPais.setEditable(false);
        txtPais.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPais.setAlignmentX(0.2F);
        txtPais.setAlignmentY(0.2F);
        txtPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtPais.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtPais);
        txtPais.setBounds(46, 85, 115, 25);

        lblPais.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPais.setText("Pais");
        lblPais.setAlignmentX(0.2F);
        lblPais.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblPais);
        lblPais.setBounds(16, 67, 145, 17);

        txtDescripcionCorta.setEditable(false);
        txtDescripcionCorta.setAlignmentX(0.2F);
        txtDescripcionCorta.setAlignmentY(0.2F);
        txtDescripcionCorta.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcionCorta.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDescripcionCorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionCortaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDescripcionCorta);
        txtDescripcionCorta.setBounds(510, 36, 166, 25);

        lblDescripcionCorta.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcionCorta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcionCorta.setText("Descrip Corta");
        pnlEntradasObligatorias.add(lblDescripcionCorta);
        lblDescripcionCorta.setBounds(523, 16, 140, 17);

        cmbTipoVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(cmbTipoVia);
        cmbTipoVia.setBounds(16, 142, 108, 20);

        lblTipoVia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVia.setText("Tipo Via");
        lblTipoVia.setAlignmentX(0.2F);
        lblTipoVia.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblTipoVia);
        lblTipoVia.setBounds(36, 116, 55, 20);

        lblNombreVia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombreVia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreVia.setText("Nombre Via");
        lblNombreVia.setAlignmentX(0.2F);
        lblNombreVia.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblNombreVia);
        lblNombreVia.setBounds(130, 116, 170, 20);

        txtNombreVia.setEditable(false);
        txtNombreVia.setAlignmentX(0.2F);
        txtNombreVia.setAlignmentY(0.2F);
        txtNombreVia.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombreVia.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombreVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNombreVia);
        txtNombreVia.setBounds(130, 142, 170, 25);

        lblNumero.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumero.setText("Num");
        lblNumero.setAlignmentX(0.2F);
        lblNumero.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblNumero);
        lblNumero.setBounds(312, 116, 41, 20);

        txtNumero.setEditable(false);
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumero.setAlignmentX(0.2F);
        txtNumero.setAlignmentY(0.2F);
        txtNumero.setDireccionDeSombra(80);
        txtNumero.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumero.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNumero);
        txtNumero.setBounds(310, 142, 43, 25);

        lblDpto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDpto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDpto.setText("Dpto.");
        lblDpto.setAlignmentX(0.2F);
        lblDpto.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDpto);
        lblDpto.setBounds(359, 116, 50, 20);

        txtDpto.setEditable(false);
        txtDpto.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDpto.setAlignmentX(0.2F);
        txtDpto.setAlignmentY(0.2F);
        txtDpto.setFont(new java.awt.Font("Arial", 0, 12));
        txtDpto.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDpto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDptoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDpto);
        txtDpto.setBounds(359, 142, 50, 25);

        lblManzana.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblManzana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManzana.setText("Mz");
        lblManzana.setAlignmentX(0.2F);
        lblManzana.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblManzana);
        lblManzana.setBounds(415, 116, 29, 20);

        txtManzana.setEditable(false);
        txtManzana.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtManzana.setAlignmentX(0.2F);
        txtManzana.setAlignmentY(0.2F);
        txtManzana.setFont(new java.awt.Font("Arial", 0, 12));
        txtManzana.setPreferredSize(new java.awt.Dimension(120, 25));
        txtManzana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtManzanaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtManzana);
        txtManzana.setBounds(415, 142, 29, 25);

        txtLote.setEditable(false);
        txtLote.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtLote.setAlignmentX(0.2F);
        txtLote.setAlignmentY(0.2F);
        txtLote.setFont(new java.awt.Font("Arial", 0, 12));
        txtLote.setPreferredSize(new java.awt.Dimension(120, 25));
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtLote);
        txtLote.setBounds(454, 142, 32, 25);

        lblLote.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblLote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLote.setText("Lt");
        lblLote.setAlignmentX(0.2F);
        lblLote.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblLote);
        lblLote.setBounds(457, 116, 29, 20);

        cmbTipoPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoPoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(cmbTipoPoblacion);
        cmbTipoPoblacion.setBounds(492, 142, 183, 20);

        lblTipoVivienda.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVivienda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoVivienda.setText("Tipo Pobl.");
        lblTipoVivienda.setAlignmentX(0.2F);
        lblTipoVivienda.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblTipoVivienda);
        lblTipoVivienda.setBounds(492, 116, 183, 20);

        txtNombrePoblacion.setEditable(false);
        txtNombrePoblacion.setAlignmentX(0.2F);
        txtNombrePoblacion.setAlignmentY(0.2F);
        txtNombrePoblacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombrePoblacion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombrePoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNombrePoblacion);
        txtNombrePoblacion.setBounds(681, 142, 116, 25);

        lblPoblacion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPoblacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPoblacion.setText("Población");
        lblPoblacion.setAlignmentX(0.2F);
        lblPoblacion.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblPoblacion);
        lblPoblacion.setBounds(686, 116, 111, 20);

        cmbTipoLocal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tradicional", "Multifuncional" }));
        cmbTipoLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoLocalKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(cmbTipoLocal);
        cmbTipoLocal.setBounds(104, 38, 144, 20);

        txtCodigoRegion.setEditable(false);
        txtCodigoRegion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoRegion.setAlignmentX(0.2F);
        txtCodigoRegion.setAlignmentY(0.2F);
        txtCodigoRegion.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoRegion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoRegion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoRegionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoRegionFocusLost(evt);
            }
        });
        txtCodigoRegion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoRegionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoRegion);
        txtCodigoRegion.setBounds(632, 85, 25, 25);

        txtRegion.setEditable(false);
        txtRegion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRegion.setAlignmentX(0.2F);
        txtRegion.setAlignmentY(0.2F);
        txtRegion.setFont(new java.awt.Font("Arial", 0, 12));
        txtRegion.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtRegion);
        txtRegion.setBounds(662, 85, 160, 25);

        lblRegion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRegion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegion.setText("Región");
        lblRegion.setAlignmentX(0.2F);
        lblRegion.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblRegion);
        lblRegion.setBounds(637, 67, 160, 17);

        pnlEntradasComplementarias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos Complementarios"));
        pnlEntradasComplementarias.setOpaque(false);
        pnlEntradasComplementarias.setLayout(null);

        txtCmbImprimeTestigo.setEditable(false);
        txtCmbImprimeTestigo.setAlignmentX(0.2F);
        txtCmbImprimeTestigo.setAlignmentY(0.2F);
        txtCmbImprimeTestigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbImprimeTestigo.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbImprimeTestigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbImprimeTestigoKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbImprimeTestigo);
        txtCmbImprimeTestigo.setBounds(800, 70, 20, 10);

        txtCmbImprimeComanda.setEditable(false);
        txtCmbImprimeComanda.setAlignmentX(0.2F);
        txtCmbImprimeComanda.setAlignmentY(0.2F);
        txtCmbImprimeComanda.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbImprimeComanda.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbImprimeComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbImprimeComandaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbImprimeComanda);
        txtCmbImprimeComanda.setBounds(800, 80, 20, 10);

        txtCmbImprimeTicket.setEditable(false);
        txtCmbImprimeTicket.setAlignmentX(0.2F);
        txtCmbImprimeTicket.setAlignmentY(0.2F);
        txtCmbImprimeTicket.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbImprimeTicket.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbImprimeTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbImprimeTicketKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbImprimeTicket);
        txtCmbImprimeTicket.setBounds(800, 90, 20, 10);

        txtCmbTicketBoleta.setEditable(false);
        txtCmbTicketBoleta.setAlignmentX(0.2F);
        txtCmbTicketBoleta.setAlignmentY(0.2F);
        txtCmbTicketBoleta.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTicketBoleta.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTicketBoleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTicketBoletaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbTicketBoleta);
        txtCmbTicketBoleta.setBounds(800, 100, 20, 10);

        txtCmbTicketFactura.setEditable(false);
        txtCmbTicketFactura.setAlignmentX(0.2F);
        txtCmbTicketFactura.setAlignmentY(0.2F);
        txtCmbTicketFactura.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTicketFactura.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTicketFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTicketFacturaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbTicketFactura);
        txtCmbTicketFactura.setBounds(800, 110, 20, 10);

        txtCmbAfectoIGV.setEditable(false);
        txtCmbAfectoIGV.setAlignmentX(0.2F);
        txtCmbAfectoIGV.setAlignmentY(0.2F);
        txtCmbAfectoIGV.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbAfectoIGV.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbAfectoIGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbAfectoIGVKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCmbAfectoIGV);
        txtCmbAfectoIGV.setBounds(800, 120, 20, 10);

        txtDteFechaApertura.setEditable(false);
        txtDteFechaApertura.setAlignmentX(0.2F);
        txtDteFechaApertura.setAlignmentY(0.2F);
        txtDteFechaApertura.setFont(new java.awt.Font("Arial", 0, 12));
        txtDteFechaApertura.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDteFechaApertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDteFechaAperturaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtDteFechaApertura);
        txtDteFechaApertura.setBounds(780, 90, 20, 10);

        txtDteFechaCierre.setEditable(false);
        txtDteFechaCierre.setAlignmentX(0.2F);
        txtDteFechaCierre.setAlignmentY(0.2F);
        txtDteFechaCierre.setFont(new java.awt.Font("Arial", 0, 12));
        txtDteFechaCierre.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDteFechaCierre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDteFechaCierreKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtDteFechaCierre);
        txtDteFechaCierre.setBounds(780, 100, 20, 10);

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("E-mail");
        lblEmail.setAlignmentX(0.2F);
        lblEmail.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblEmail);
        lblEmail.setBounds(56, 70, 160, 17);

        txtEmail.setEditable(false);
        txtEmail.setAlignmentX(0.2F);
        txtEmail.setAlignmentY(0.2F);
        txtEmail.setFont(new java.awt.Font("Arial", 0, 12));
        txtEmail.setPreferredSize(new java.awt.Dimension(320, 25));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtEmail);
        txtEmail.setBounds(16, 90, 252, 25);

        lblCodTelefPais.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodTelefPais.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodTelefPais.setText("Cod Telf Pais");
        lblCodTelefPais.setAlignmentX(0.2F);
        lblCodTelefPais.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblCodTelefPais);
        lblCodTelefPais.setBounds(16, 16, 88, 17);

        txtCodigoTelefPais.setEditable(false);
        txtCodigoTelefPais.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCodigoTelefPais.setAlignmentX(0.2F);
        txtCodigoTelefPais.setAlignmentY(0.2F);
        txtCodigoTelefPais.setDireccionDeSombra(80);
        txtCodigoTelefPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoTelefPais.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoTelefPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoTelefPaisKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCodigoTelefPais);
        txtCodigoTelefPais.setBounds(16, 39, 85, 25);

        lblCodTelefCiudad.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodTelefCiudad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodTelefCiudad.setText("Cod Telf Ciudad");
        lblCodTelefCiudad.setAlignmentX(0.2F);
        lblCodTelefCiudad.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblCodTelefCiudad);
        lblCodTelefCiudad.setBounds(110, 16, 111, 17);

        txtCodigoTelefCiudad.setEditable(false);
        txtCodigoTelefCiudad.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCodigoTelefCiudad.setAlignmentX(0.2F);
        txtCodigoTelefCiudad.setAlignmentY(0.2F);
        txtCodigoTelefCiudad.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoTelefCiudad.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoTelefCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoTelefCiudadKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCodigoTelefCiudad);
        txtCodigoTelefCiudad.setBounds(119, 39, 79, 25);

        lblNumeroTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumeroTelefono.setText("Número Teléfono");
        lblNumeroTelefono.setAlignmentX(0.2F);
        lblNumeroTelefono.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblNumeroTelefono);
        lblNumeroTelefono.setBounds(231, 16, 120, 17);

        txtNumeroTelefono.setEditable(false);
        txtNumeroTelefono.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumeroTelefono.setAlignmentX(0.2F);
        txtNumeroTelefono.setAlignmentY(0.2F);
        txtNumeroTelefono.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroTelefono.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroTelefonoKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtNumeroTelefono);
        txtNumeroTelefono.setBounds(231, 39, 120, 25);

        lblNumeroFax.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumeroFax.setText("Número Fax");
        lblNumeroFax.setAlignmentX(0.2F);
        lblNumeroFax.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblNumeroFax);
        lblNumeroFax.setBounds(427, 16, 84, 17);

        txtNumeroFax.setEditable(false);
        txtNumeroFax.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumeroFax.setAlignmentX(0.2F);
        txtNumeroFax.setAlignmentY(0.2F);
        txtNumeroFax.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroFax.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFaxKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtNumeroFax);
        txtNumeroFax.setBounds(411, 39, 120, 25);

        txtAnexo.setEditable(false);
        txtAnexo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtAnexo.setAlignmentX(0.2F);
        txtAnexo.setAlignmentY(0.2F);
        txtAnexo.setFont(new java.awt.Font("Arial", 0, 12));
        txtAnexo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtAnexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnexoKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtAnexo);
        txtAnexo.setBounds(363, 39, 42, 25);

        lblAnexo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblAnexo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnexo.setText("Anexo");
        lblAnexo.setAlignmentX(0.2F);
        lblAnexo.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblAnexo);
        lblAnexo.setBounds(361, 16, 44, 17);

        txtNumeroMovil.setEditable(false);
        txtNumeroMovil.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumeroMovil.setAlignmentX(0.2F);
        txtNumeroMovil.setAlignmentY(0.2F);
        txtNumeroMovil.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroMovil.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroMovil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMovilKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtNumeroMovil);
        txtNumeroMovil.setBounds(537, 39, 120, 25);

        lblMovil.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMovil.setText("Movil");
        lblMovil.setAlignmentX(0.2F);
        lblMovil.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblMovil);
        lblMovil.setBounds(573, 16, 35, 17);

        cmbImprimeTestigo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbImprimeTestigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbImprimeTestigoKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbImprimeTestigo);
        cmbImprimeTestigo.setBounds(286, 90, 69, 20);

        lblImprimeTestigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblImprimeTestigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImprimeTestigo.setText("Imp Testigo");
        pnlEntradasComplementarias.add(lblImprimeTestigo);
        lblImprimeTestigo.setBounds(274, 70, 95, 17);

        lblImprimeComanda.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblImprimeComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImprimeComanda.setText("Imp Comanda");
        pnlEntradasComplementarias.add(lblImprimeComanda);
        lblImprimeComanda.setBounds(375, 70, 110, 17);

        cmbImprimeComanda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbImprimeComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbImprimeComandaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbImprimeComanda);
        cmbImprimeComanda.setBounds(394, 90, 69, 20);

        cmbImprimeTicket.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbImprimeTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImprimeTicketActionPerformed(evt);
            }
        });
        cmbImprimeTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbImprimeTicketKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbImprimeTicket);
        cmbImprimeTicket.setBounds(497, 90, 69, 20);

        lblImprimeTicket.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblImprimeTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImprimeTicket.setText("Imp Ticket");
        pnlEntradasComplementarias.add(lblImprimeTicket);
        lblImprimeTicket.setBounds(495, 70, 74, 17);

        lblTicketBoleta.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTicketBoleta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicketBoleta.setText("Ticket Boleta");
        pnlEntradasComplementarias.add(lblTicketBoleta);
        lblTicketBoleta.setBounds(579, 70, 89, 17);

        cmbTicketBoleta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbTicketBoleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTicketBoletaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbTicketBoleta);
        cmbTicketBoleta.setBounds(589, 90, 69, 20);

        lblTicketFactura.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTicketFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicketFactura.setText("Ticket Factura");
        pnlEntradasComplementarias.add(lblTicketFactura);
        lblTicketFactura.setBounds(686, 70, 97, 17);

        cmbTicketFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbTicketFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTicketFacturaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbTicketFactura);
        cmbTicketFactura.setBounds(700, 90, 69, 20);

        lblOrdenApertura.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblOrdenApertura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrdenApertura.setText("Ord Apertura");
        lblOrdenApertura.setAlignmentX(0.2F);
        lblOrdenApertura.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblOrdenApertura);
        lblOrdenApertura.setBounds(16, 121, 92, 20);

        txtOrdenApertura.setEditable(false);
        txtOrdenApertura.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtOrdenApertura.setAlignmentX(0.2F);
        txtOrdenApertura.setAlignmentY(0.2F);
        txtOrdenApertura.setDireccionDeSombra(80);
        txtOrdenApertura.setFont(new java.awt.Font("Arial", 0, 12));
        txtOrdenApertura.setPreferredSize(new java.awt.Dimension(120, 25));
        txtOrdenApertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrdenAperturaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtOrdenApertura);
        txtOrdenApertura.setBounds(16, 147, 81, 25);

        lblFechaApertura.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaApertura.setText("Fecha Apertura");
        lblFechaApertura.setAlignmentX(0.2F);
        lblFechaApertura.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblFechaApertura);
        lblFechaApertura.setBounds(121, 121, 106, 20);

        dteFechaApertura.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaApertura.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaApertura.setDate(Calendar.getInstance().getTime());
        dteFechaApertura.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaApertura.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaApertura.setRequestFocusEnabled(false);
        dteFechaApertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaAperturaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(dteFechaApertura);
        dteFechaApertura.setBounds(119, 147, 111, 20);

        txtMontoCotizacion.setEditable(false);
        txtMontoCotizacion.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtMontoCotizacion.setAlignmentX(0.2F);
        txtMontoCotizacion.setAlignmentY(0.2F);
        txtMontoCotizacion.setDireccionDeSombra(80);
        txtMontoCotizacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtMontoCotizacion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtMontoCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoCotizacionKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtMontoCotizacion);
        txtMontoCotizacion.setBounds(390, 143, 70, 25);

        lblMontoCotizacion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMontoCotizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMontoCotizacion.setText("Mto Cotización");
        lblMontoCotizacion.setAlignmentX(0.2F);
        lblMontoCotizacion.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblMontoCotizacion);
        lblMontoCotizacion.setBounds(380, 123, 102, 20);

        lblAfectoIGV.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblAfectoIGV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAfectoIGV.setText("Afecto IGV");
        pnlEntradasComplementarias.add(lblAfectoIGV);
        lblAfectoIGV.setBounds(500, 123, 76, 17);

        cmbAfectoIGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        cmbAfectoIGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAfectoIGVKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(cmbAfectoIGV);
        cmbAfectoIGV.setBounds(500, 143, 69, 20);

        lblTiempoSuministro.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTiempoSuministro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTiempoSuministro.setText("Tpo Suministro");
        lblTiempoSuministro.setAlignmentX(0.2F);
        lblTiempoSuministro.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblTiempoSuministro);
        lblTiempoSuministro.setBounds(590, 123, 105, 20);

        txtTiempoReposicion.setEditable(false);
        txtTiempoReposicion.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTiempoReposicion.setAlignmentX(0.2F);
        txtTiempoReposicion.setAlignmentY(0.2F);
        txtTiempoReposicion.setDireccionDeSombra(80);
        txtTiempoReposicion.setFont(new java.awt.Font("Arial", 0, 12));
        txtTiempoReposicion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTiempoReposicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoReposicionKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtTiempoReposicion);
        txtTiempoReposicion.setBounds(725, 143, 83, 25);

        lblTiempoReposicion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTiempoReposicion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTiempoReposicion.setText("Tpo Reposición");
        lblTiempoReposicion.setAlignmentX(0.2F);
        lblTiempoReposicion.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblTiempoReposicion);
        lblTiempoReposicion.setBounds(700, 123, 105, 20);

        txtTiempoSuministro.setEditable(false);
        txtTiempoSuministro.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTiempoSuministro.setAlignmentX(0.2F);
        txtTiempoSuministro.setAlignmentY(0.2F);
        txtTiempoSuministro.setDireccionDeSombra(80);
        txtTiempoSuministro.setFont(new java.awt.Font("Arial", 0, 12));
        txtTiempoSuministro.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTiempoSuministro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoSuministroKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtTiempoSuministro);
        txtTiempoSuministro.setBounds(625, 143, 70, 25);

        lblNombreResponsableLocal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombreResponsableLocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreResponsableLocal.setText("Nombre Responsable Local");
        lblNombreResponsableLocal.setAlignmentX(0.2F);
        lblNombreResponsableLocal.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblNombreResponsableLocal);
        lblNombreResponsableLocal.setBounds(26, 178, 186, 17);

        txtNombreResponsableLocal.setEditable(false);
        txtNombreResponsableLocal.setAlignmentX(0.2F);
        txtNombreResponsableLocal.setAlignmentY(0.2F);
        txtNombreResponsableLocal.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombreResponsableLocal.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombreResponsableLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreResponsableLocalKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtNombreResponsableLocal);
        txtNombreResponsableLocal.setBounds(16, 201, 206, 25);

        lblNombreResponsableAlterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombreResponsableAlterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreResponsableAlterno.setText("Nombre Responsable Alterno");
        lblNombreResponsableAlterno.setAlignmentX(0.2F);
        lblNombreResponsableAlterno.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblNombreResponsableAlterno);
        lblNombreResponsableAlterno.setBounds(234, 178, 202, 17);

        txtNombreResponsableAlterno.setEditable(false);
        txtNombreResponsableAlterno.setAlignmentX(0.2F);
        txtNombreResponsableAlterno.setAlignmentY(0.2F);
        txtNombreResponsableAlterno.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombreResponsableAlterno.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombreResponsableAlterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreResponsableAlternoKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtNombreResponsableAlterno);
        txtNombreResponsableAlterno.setBounds(232, 201, 206, 25);

        txtMensajeTicket.setEditable(false);
        txtMensajeTicket.setAlignmentX(0.2F);
        txtMensajeTicket.setAlignmentY(0.2F);
        txtMensajeTicket.setFont(new java.awt.Font("Arial", 0, 12));
        txtMensajeTicket.setPreferredSize(new java.awt.Dimension(320, 25));
        txtMensajeTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMensajeTicketKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtMensajeTicket);
        txtMensajeTicket.setBounds(444, 201, 206, 25);

        lblMensajeTicket.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMensajeTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeTicket.setText("Mensaje Ticket");
        lblMensajeTicket.setAlignmentX(0.2F);
        lblMensajeTicket.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblMensajeTicket);
        lblMensajeTicket.setBounds(496, 178, 102, 17);

        txtZonaSupervision.setEditable(false);
        txtZonaSupervision.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtZonaSupervision.setAlignmentX(0.2F);
        txtZonaSupervision.setAlignmentY(0.2F);
        txtZonaSupervision.setFont(new java.awt.Font("Arial", 0, 12));
        txtZonaSupervision.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasComplementarias.add(txtZonaSupervision);
        txtZonaSupervision.setBounds(686, 201, 122, 25);

        txtCodigoZonaSupervision.setEditable(false);
        txtCodigoZonaSupervision.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoZonaSupervision.setAlignmentX(0.2F);
        txtCodigoZonaSupervision.setAlignmentY(0.2F);
        txtCodigoZonaSupervision.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoZonaSupervision.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoZonaSupervision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoZonaSupervisionKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCodigoZonaSupervision);
        txtCodigoZonaSupervision.setBounds(656, 201, 25, 25);

        lblZonaSupervicion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblZonaSupervicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaSupervicion.setText("Zona Supervisión");
        lblZonaSupervicion.setAlignmentX(0.2F);
        lblZonaSupervicion.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblZonaSupervicion);
        lblZonaSupervicion.setBounds(672, 178, 119, 17);

        lblCadena.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCadena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadena.setText("Cadena");
        lblCadena.setAlignmentX(0.2F);
        lblCadena.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblCadena);
        lblCadena.setBounds(712, 16, 52, 17);

        txtCodigoCadena.setEditable(false);
        txtCodigoCadena.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoCadena.setAlignmentX(0.2F);
        txtCodigoCadena.setAlignmentY(0.2F);
        txtCodigoCadena.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoCadena.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoCadena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoCadenaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoCadenaFocusLost(evt);
            }
        });
        txtCodigoCadena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoCadenaKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(txtCodigoCadena);
        txtCodigoCadena.setBounds(663, 39, 25, 25);

        txtCadena.setEditable(false);
        txtCadena.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCadena.setAlignmentX(0.2F);
        txtCadena.setAlignmentY(0.2F);
        txtCadena.setFont(new java.awt.Font("Arial", 0, 12));
        txtCadena.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasComplementarias.add(txtCadena);
        txtCadena.setBounds(693, 39, 115, 25);

        lblFechaCierre.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaCierre.setText("Fecha Cierre");
        lblFechaCierre.setAlignmentX(0.2F);
        lblFechaCierre.setAlignmentY(0.2F);
        pnlEntradasComplementarias.add(lblFechaCierre);
        lblFechaCierre.setBounds(257, 121, 85, 20);

        dteFechaCierre.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaCierre.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaCierre.setDate(Calendar.getInstance().getTime());
        dteFechaCierre.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaCierre.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaCierre.setRequestFocusEnabled(false);
        dteFechaCierre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaCierreKeyReleased(evt);
            }
        });
        pnlEntradasComplementarias.add(dteFechaCierre);
        dteFechaCierre.setBounds(240, 147, 120, 20);

        plnCompania.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0)));
        plnCompania.setOpaque(false);
        plnCompania.setLayout(null);

        txtCodigoCompania.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoCompania.setAlignmentX(0.2F);
        txtCodigoCompania.setAlignmentY(0.2F);
        txtCodigoCompania.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoCompania.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoCompania.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoCompaniaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoCompaniaFocusLost(evt);
            }
        });
        txtCodigoCompania.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoCompaniaKeyReleased(evt);
            }
        });
        plnCompania.add(txtCodigoCompania);
        txtCodigoCompania.setBounds(84, 2, 78, 25);

        txtDescripcionCompania.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcionCompania.setAlignmentX(0.2F);
        txtDescripcionCompania.setAlignmentY(0.2F);
        txtDescripcionCompania.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcionCompania.setPreferredSize(new java.awt.Dimension(120, 25));
        plnCompania.add(txtDescripcionCompania);
        txtDescripcionCompania.setBounds(307, 2, 218, 25);

        lblCodigoCompania.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoCompania.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigoCompania.setText("Codigo:");
        plnCompania.add(lblCodigoCompania);
        lblCodigoCompania.setBounds(12, 6, 54, 17);

        lblDescripcionCompania.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcionCompania.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcionCompania.setText("Descripción:");
        plnCompania.add(lblDescripcionCompania);
        lblDescripcionCompania.setBounds(206, 6, 83, 17);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("Pulse F1 para ver Mas Datos");
        plnCompania.add(lblMensaje);
        lblMensaje.setBounds(559, 10, 204, 17);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                            .addComponent(plnCompania, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                            .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(pnlAccionesLab, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(plnCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccionesLab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private boolean guardarActualizar() throws SQLException{
        txtDescripcionCorta.setText(txtDescripcionCorta.getText().toUpperCase());
        txtDescripcion.setText(txtDescripcion.getText().toUpperCase());
        txtNombreVia.setText(txtNombreVia.getText().toUpperCase());
        txtDpto.setText(txtDpto.getText().toUpperCase());
        txtManzana.setText(txtManzana.getText().toUpperCase());
        txtLote.setText(txtLote.getText().toUpperCase());
        txtNombrePoblacion.setText(txtNombrePoblacion.getText().toUpperCase());
        txtNombreResponsableLocal.setText(txtNombreResponsableLocal.getText().toUpperCase());
        txtNombreResponsableAlterno.setText(txtNombreResponsableAlterno.getText().toUpperCase());

        cp.getLocales().setCoCompania(txtCodigoCompania.getText());
        cp.getLocales().setCoLocal(txtCodigo.getText());
        cp.getLocales().setTiLocal("V");
        cp.getLocales().setInTipoCaja(cmbTipoLocal.getSelectedItem().toString().substring(0, 1));
        cp.getLocales().setCoRegion(txtCodigoRegion.getText());
        cp.getLocales().setDeCortaLocal(txtDescripcionCorta.getText());
        cp.getLocales().setDeLocal(txtDescripcion.getText());
        cp.getLocales().setCoPais(txtCodigoPais.getText());
        cp.getLocales().setCoDepartamento(txtCodigoDepartamento.getText());
        cp.getLocales().setCoProvincia(txtCodigoProvincia.getText());
        cp.getLocales().setCoDistrito(txtCodigoDistrito.getText());
        cp.getLocales().setTiVia(((TipoVia)cmbTipoVia.getSelectedItem()).getTiVia());
        cp.getLocales().setNoVia(txtNombreVia.getText());
        if ("".equals(txtNumero.getText().trim())) txtNumero.setText("0");
        cp.getLocales().setNuVia(Integer.parseInt(txtNumero.getText()));
        cp.getLocales().setNuInteriorVia(txtDpto.getText());
        cp.getLocales().setNuManzanaVia(txtManzana.getText());
        cp.getLocales().setNuLoteVia(txtLote.getText());
        cp.getLocales().setTiPoblacion(((TipoPoblacion)cmbTipoPoblacion.getSelectedItem()).getTiPoblacion());
        cp.getLocales().setNoPoblacion(txtNombrePoblacion.getText());
        cp.getLocales().setDeDireccionLocal("");
        cp.getLocales().setCoTelPais(txtCodigoTelefPais.getText());
        cp.getLocales().setCoTelCiudad(txtCodigoTelefCiudad.getText());
        cp.getLocales().setNuTelNormal(txtNumeroTelefono.getText());
        cp.getLocales().setCoExtTelNormal(txtAnexo.getText());
        cp.getLocales().setNuTelFax(txtNumeroFax.getText());
        cp.getLocales().setNuTelMovil(txtNumeroMovil.getText());
        cp.getLocales().setIdRespLocal(txtNombreResponsableLocal.getText());
        cp.getLocales().setIdRespAlternoLocal(txtNombreResponsableAlterno.getText());
        cp.getLocales().setDeEmail(txtEmail.getText());
        cp.getLocales().setCoLocalDelivery(null);
        cp.getLocales().setInImprimeTestigo(cmbImprimeTestigo.getSelectedItem().toString().substring(0,1));
        cp.getLocales().setInImpComanda(cmbImprimeComanda.getSelectedItem().toString().substring(0,1));
        cp.getLocales().setInImprimeTicket(cmbImprimeTicket.getSelectedItem().toString().substring(0,1));
        cp.getLocales().setDeMensajeTicket(txtMensajeTicket.getText());
        cp.getLocales().setInTicketBoleta(cmbTicketBoleta.getSelectedItem().toString().substring(0,1));
        cp.getLocales().setInTicketFactura(cmbTicketFactura.getSelectedItem().toString().substring(0,1));
        cp.getLocales().setCoZonaSupervision(txtCodigoZonaSupervision.getText());
        if ("".equals(txtOrdenApertura.getText().trim())) txtOrdenApertura.setText("0");
        cp.getLocales().setNuOrdenApertura(Integer.parseInt(txtOrdenApertura.getText()));
        cp.getLocales().setFeApertura(dteFechaApertura.getDate());
        
        cp.getLocales().setInProvincia("S");
        if ("014".equals(txtCodigoDepartamento.getText()) && "001".equals(txtCodigoProvincia.getText())){
            cp.getLocales().setInProvincia("N");
        }
        if ("".equals(txtMontoCotizacion.getText().trim())) txtMontoCotizacion.setText("0");
        cp.getLocales().setVaMontoCotizacion(Double.parseDouble(txtMontoCotizacion.getText()));
        cp.getLocales().setInAfectoIgvLocal(cmbAfectoIGV.getSelectedItem().toString().substring(0,1));
        if ("".equals(txtTiempoSuministro.getText().trim())) txtTiempoSuministro.setText("0");
        if ("".equals(txtTiempoReposicion.getText().trim())) txtTiempoReposicion.setText("0");
        cp.getLocales().setTsDias(Integer.parseInt(txtTiempoSuministro.getText()));
        cp.getLocales().setTrDias(Integer.parseInt(txtTiempoReposicion.getText()));
        cp.getLocales().setCoSucursal(" ");
        cp.getLocales().setCoCadenaLocal(txtCodigoCadena.getText());
        
        if (chbEstado.isSelected()){
            cp.getLocales().setEsLocal("A");
        }else{
            cp.getLocales().setFeCierre(dteFechaApertura.getDate());
            cp.getLocales().setEsLocal("I");
        }
        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getLocales().setVaMinimoPedidoDelivery(0);
            cp.getLocales().setNuDiasRotacionPromedio(0);
            cp.getLocales().setNuMinDiasReposicion(0);
            cp.getLocales().setNuMaxDiasReposicion(0);
            cp.getLocales().setPcAdicionalPedidoReposicion(0);
            cp.getLocales().setVaFactorBajaRotacion(0);
            cp.getLocales().setInPedRepEnProc("N");
            cp.getLocales().setFeCalculoMaxMin(null);
            cp.getLocales().setNuDiasInventario(null);
            cp.getLocales().setNuDiasVenta(0);
            cp.getLocales().setInIgnorarProdSinSaldo("N");
            cp.getLocales().setInSumarTiempoSuministro("N");
            cp.getLocales().setInSumarTransito("N");
            cp.getLocales().setInSumarMinExhibicion("N");
            cp.getLocales().setInSumarComprasPendientes("N");
            cp.getLocales().setInTipoOperacion(null);
            cp.getLocales().setInOrigenProductos(null);
            cp.getLocales().setInSoloProdActivos("S");
            cp.getLocales().setInProductosFraccionados("N");
            cp.getLocales().setInFaltaCero("N");
        
            cp.getLocales().setIdCreaLocal(AtuxVariables.vIdUsuario);
            cp.getLocales().setFeCreaLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            cp.getLocales().setCoLocal(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania));
            txtCodigo.setText(cp.getLocales().getCoLocal());
            EstadoGuardar = cp.guardarRegistro(cp.getLocales());
        }else{
            cp.getLocales().setIdModLocal(AtuxVariables.vIdUsuario);
            cp.getLocales().setFeModLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            int act = cp.actualizarRegistro(cp.getLocales());
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
            Logger.getLogger(ILocales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    
    public void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoRegion.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtDescripcionCorta.getDocument(), FiltraEntrada.NUM_LETRAS, 12, true);
        Helper.setFiltraEntrada(txtDescripcion.getDocument(), FiltraEntrada.NUM_LETRAS, 30, true); 
        Helper.setFiltraEntrada(txtCodigoPais.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, true);
        Helper.setFiltraEntrada(txtCodigoDepartamento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, true);
        Helper.setFiltraEntrada(txtCodigoProvincia.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoDistrito.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtNombreVia.getDocument(), FiltraEntrada.NUM_LETRAS, 30, false);
        Helper.setFiltraEntrada(txtNumero.getDocument(), FiltraEntrada.SOLO_NUMEROS, 4, false);
        Helper.setFiltraEntrada(txtDpto.getDocument(), FiltraEntrada.NUM_LETRAS, 4, false);
        Helper.setFiltraEntrada(txtManzana.getDocument(), FiltraEntrada.NUM_LETRAS, 4, false);
        Helper.setFiltraEntrada(txtLote.getDocument(), FiltraEntrada.NUM_LETRAS, 4, false);
        Helper.setFiltraEntrada(txtNombrePoblacion.getDocument(), FiltraEntrada.NUM_LETRAS, 30, false);
        Helper.setFiltraEntrada(txtCodigoTelefPais.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoTelefCiudad.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
        Helper.setFiltraEntrada(txtNumeroTelefono.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtAnexo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
        Helper.setFiltraEntrada(txtNumeroFax.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtNumeroMovil.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtNombreResponsableLocal.getDocument(), FiltraEntrada.NUM_LETRAS, 15, false);
        Helper.setFiltraEntrada(txtNombreResponsableAlterno.getDocument(), FiltraEntrada.NUM_LETRAS, 15, false);
        Helper.setFiltraEntrada(txtEmail.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtMensajeTicket.getDocument(), FiltraEntrada.NUM_LETRAS, 42, false);
        Helper.setFiltraEntrada(txtCodigoZonaSupervision.getDocument(), FiltraEntrada.NUM_LETRAS, 2, false);
        Helper.setFiltraEntrada(txtOrdenApertura.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtMontoCotizacion.getDocument(), FiltraEntrada.NUM_LETRAS, 20, false);
        Helper.setFiltraEntrada(txtTiempoSuministro.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtTiempoReposicion.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoCadena.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
    }    

    public boolean verficarCambios(){
        // TODO aguerra verificar     

        if(!this.txtCodigo.getText().equals(cp.getLocales().getCoLocal())){
            return true;
        }else if(!cmbTipoLocal.getSelectedItem().toString().substring(0, 1).equals(cp.getLocales().getInTipoCaja())){
            return true;
        }else if(!txtCodigoRegion.getText().equals(cp.getLocales().getCoRegion())){
            return true;
        }else if(!txtDescripcionCorta.getText().equals(cp.getLocales().getDeCortaLocal())){
            return true;
        }else if(!txtDescripcion.getText().equals(cp.getLocales().getDeLocal())){
            return true;
        }else if(!txtCodigoPais.getText().equals(cp.getLocales().getCoPais())){
            return true;
        }else if(!txtCodigoDepartamento.getText().equals(cp.getLocales().getCoDepartamento())){
            return true;
        }else if(!txtCodigoProvincia.getText().equals(cp.getLocales().getCoProvincia())){
            return true;
        }else if(!txtCodigoDistrito.getText().equals(cp.getLocales().getCoDistrito())){
            return true;
        }else if(!txtNombreVia.getText().equals(cp.getLocales().getNoVia())){
            return true;
        }else if(Integer.parseInt(txtNumero.getText()) != cp.getLocales().getNuVia()){
            return true;
        }else if(!txtDpto.getText().equals(cp.getLocales().getNuInteriorVia())){
            return true;
        }else if(!txtManzana.getText().equals(cp.getLocales().getNuManzanaVia())){
            return true;
        }else if(!txtLote.getText().equals(cp.getLocales().getNuLoteVia())){
            return true;
        }else if(!txtNombrePoblacion.getText().equals(cp.getLocales().getNoPoblacion())){
            return true;
        }else if(!txtCodigoTelefPais.getText().equals(cp.getLocales().getCoTelPais())){
            return true;
        }else if(!txtCodigoTelefCiudad.getText().equals(cp.getLocales().getCoTelCiudad())){
            return true;
        }else if(!txtNumeroTelefono.getText().equals(cp.getLocales().getNuTelNormal())){
            return true;
        }else if(!txtAnexo.getText().equals(cp.getLocales().getCoExtTelNormal())){
            return true;
        }else if(!txtNumeroFax.getText().equals(cp.getLocales().getNuTelFax())){
            return true;
        }else if(!txtNumeroMovil.getText().equals(cp.getLocales().getNuTelMovil())){
            return true;
        }else if(!txtNombreResponsableLocal.getText().equals(cp.getLocales().getIdRespLocal())){
            return true;
        }else if(!txtNombreResponsableAlterno.getText().equals(cp.getLocales().getIdRespAlternoLocal())){
            return true;
        }else if(!txtEmail.getText().equals(cp.getLocales().getDeEmail())){
            return true;
        }else if(!txtMensajeTicket.getText().equals(cp.getLocales().getDeMensajeTicket())){
            return true;
        }else if(!txtCodigoZonaSupervision.getText().equals(cp.getLocales().getCoZonaSupervision())){
            return true;
        }else if(Integer.parseInt(txtOrdenApertura.getText()) != cp.getLocales().getNuOrdenApertura()){
            return true;
        }else if(!"N".equals(cp.getLocales().getInProvincia())){
            return true;
        }else if(Double.parseDouble(txtMontoCotizacion.getText()) != cp.getLocales().getVaMontoCotizacion()){
            return true;
        }else if(Integer.parseInt(txtTiempoSuministro.getText()) != cp.getLocales().getTsDias()){
            return true;
        }else if(Integer.parseInt(txtTiempoReposicion.getText()) != cp.getLocales().getTrDias()){
            return true;
        }else if(!txtCodigoCadena.getText().equals(cp.getLocales().getCoCadenaLocal())){
            return true;
        }else if(!((TipoVia)cmbTipoVia.getSelectedItem()).getTiVia().equals(cp.getLocales().getTiVia())){
            return true;
        }else if(!((TipoPoblacion)cmbTipoPoblacion.getSelectedItem()).getTiPoblacion().equals(cp.getLocales().getTiPoblacion())){
            return true;
        }else if(!cmbImprimeTestigo.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInImprimeTestigo())){
            return true;
        }else if(!cmbImprimeComanda.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInImpComanda())){
            return true;
        }else if(!cmbImprimeTicket.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInImprimeTicket())){
            return true;
        }else if(!cmbAfectoIGV.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInAfectoIgvLocal())){
            return true;
        }else if(!cmbTicketBoleta.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInTicketBoleta())){
            return true;
        }else if(!cmbTicketFactura.getSelectedItem().toString().substring(0,1).equals(cp.getLocales().getInTicketFactura())){
            return true;
        }else if(dteFechaApertura.getDate() != cp.getLocales().getFeApertura()){
            return true;
        }else if(dteFechaCierre.getDate() != cp.getLocales().getFeCierre()){
            return true;
        }else if(chbEstado.isSelected() != ("A".equals(cp.getLocales().getEsLocal()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getLocales().getEsLocal()))){
            return true;
        }
        return false;
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
            cp.setLocales(mtp.getFila(tblListado.getSelectedRow()));
            setLocales();
            
        }
    }

    private void Limpiar(){
        txtCodigo.setText("");
        txtCodigoRegion.setText("");
        txtDescripcionCorta.setText("");
        txtDescripcion.setText("");
        txtCodigoPais.setText("");
        txtCodigoDepartamento.setText("");
        txtCodigoProvincia.setText("");
        txtCodigoDistrito.setText("");
        txtPais.setText("");
        txtDepartamento.setText("");
        txtProvincia.setText("");
        txtDistrito.setText("");
        txtNombreVia.setText("");
        txtNumero.setText("");
        txtDpto.setText("");
        txtManzana.setText("");
        txtLote.setText("");
        txtNombrePoblacion.setText("");
        txtCodigoTelefPais.setText("");
        txtCodigoTelefCiudad.setText("");
        txtNumeroTelefono.setText("");
        txtAnexo.setText("");
        txtNumeroFax.setText("");
        txtNumeroMovil.setText("");
        txtNombreResponsableLocal.setText("");
        txtNombreResponsableAlterno.setText("");
        txtEmail.setText("");
        txtMensajeTicket.setText("");
        txtCodigoZonaSupervision.setText("");
        txtOrdenApertura.setText("");
        dteFechaApertura.setDate(null);
        dteFechaCierre.setDate(null);
        txtMontoCotizacion.setText("");
        txtTiempoSuministro.setText("");
        txtTiempoReposicion.setText("");
        txtCodigoCadena.setText("");

        cmbTipoLocal.setSelectedIndex(0);
        cmbImprimeTestigo.setSelectedIndex(0);
        cmbImprimeComanda.setSelectedIndex(0);
        cmbImprimeTicket.setSelectedIndex(0);
        cmbTicketBoleta.setSelectedIndex(0);
        cmbTicketFactura.setSelectedIndex(0);
        cmbAfectoIGV.setSelectedIndex(0);
        cmbTipoVia.setSelectedIndex(0);
        cmbTipoPoblacion.setSelectedIndex(0);

        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        
        pnlEntradasObligatorias.setEnabled(false);
        pnlEntradasComplementarias.setEnabled(false);
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        
        ECampos.setEditableTexto(this.plnCompania, false, new Component[]{lblCodigoCompania, lblDescripcionCompania, lblMensaje},false,"");                
        ECampos.setEditableTexto(this.pnlEntradasObligatorias, true, new Component[]{lblCodigo, lblTipoLocal, lblDescripcion, lblDescripcionCorta, lblEstado, lblPais, lblDepartamento, lblProvincia, lblDistrito, lblRegion, lblTipoVia, lblNombreVia, lblNumero, lblDpto, lblManzana, lblLote, lblTipoVivienda, lblPoblacion},false,"");
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, true, new Component[]{lblCodTelefPais, lblCodTelefCiudad, lblNumeroTelefono, lblAnexo, lblNumeroFax, lblMovil, lblCadena, lblEmail, lblImprimeTestigo, lblImprimeComanda, lblImprimeTicket, lblTicketBoleta, lblTicketFactura, lblOrdenApertura, lblFechaApertura, lblFechaCierre, lblMontoCotizacion, lblAfectoIGV, lblTiempoSuministro, lblTiempoReposicion, lblNombreResponsableLocal, lblNombreResponsableAlterno, lblMensajeTicket, lblZonaSupervicion},false,"");
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
        this.btnBuscar.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        
        this.cmbImprimeTestigo.setEnabled(true);
        this.cmbImprimeComanda.setEnabled(true);
        this.cmbImprimeTicket.setEnabled(true);
        this.cmbTicketBoleta.setEnabled(true);
        this.cmbTicketFactura.setEnabled(true);
        this.cmbAfectoIGV.setEnabled(true);
        this.cmbTipoLocal.setEnabled(true);
        this.cmbTipoPoblacion.setEnabled(true);
        this.cmbTipoVia.setEnabled(true);
        this.plnCompania.setEnabled(true);
        this.txtCodigoCompania.setEnabled(true);
        
        this.txtCmbTipoLocal.setVisible(false);
        this.txtCmbTipoVia.setVisible(false);
        this.txtCmbTipoPoblacion.setVisible(false);
        this.txtCmbImprimeTestigo.setVisible(false);
        this.txtCmbImprimeComanda.setVisible(false);
        this.txtCmbImprimeTicket.setVisible(false);
        this.txtCmbTicketBoleta.setVisible(false);
        this.txtCmbTicketFactura.setVisible(false);
        this.txtCmbAfectoIGV.setVisible(false);
        this.txtDteFechaApertura.setVisible(false);
        this.txtDteFechaCierre.setVisible(false);


        dteFechaCierre.setEnabled(true);
        dteFechaApertura.setEnabled(true);
    }
    private void DesActivarCasillas(){
        this.pnlEntradasObligatorias.setEnabled(true);
        this.pnlEntradasComplementarias.setEnabled(true);
        ECampos.setEditableTexto(this.plnCompania, true, new Component[]{lblCodigoCompania, lblDescripcionCompania, lblMensaje},false,"");                        
        ECampos.setEditableTexto(this.pnlEntradasObligatorias, false, new Component[]{lblCodigo, lblTipoLocal, lblDescripcion, lblDescripcionCorta, lblEstado, lblPais, lblDepartamento, lblProvincia, lblDistrito, lblRegion, lblTipoVia, lblNombreVia, lblNumero, lblDpto, lblManzana, lblLote, lblTipoVivienda, lblPoblacion},false,"");
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, false, new Component[]{lblCodTelefPais, lblCodTelefCiudad, lblNumeroTelefono, lblAnexo, lblNumeroFax, lblMovil, lblCadena, lblEmail, lblImprimeTestigo, lblImprimeComanda, lblImprimeTicket, lblTicketBoleta, lblTicketFactura, lblOrdenApertura, lblFechaApertura, lblFechaCierre, lblMontoCotizacion, lblAfectoIGV, lblTiempoSuministro, lblTiempoReposicion, lblNombreResponsableLocal, lblNombreResponsableAlterno, lblMensajeTicket, lblZonaSupervicion},false,"");        
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
        this.btnBuscar.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        this.cmbImprimeTestigo.setEnabled(false);
        this.cmbImprimeComanda.setEnabled(false);
        this.cmbImprimeTicket.setEnabled(false);
        this.cmbTicketBoleta.setEnabled(false);
        this.cmbTicketFactura.setEnabled(false);
        this.cmbAfectoIGV.setEnabled(false);
        this.cmbTipoLocal.setEnabled(false);
        this.cmbTipoPoblacion.setEnabled(false);
        this.cmbTipoVia.setEnabled(false);

        this.txtCmbTipoLocal.setVisible(true);
        this.txtCmbTipoVia.setVisible(true);
        this.txtCmbTipoPoblacion.setVisible(true);
        this.txtCmbImprimeTestigo.setVisible(true);
        this.txtCmbImprimeComanda.setVisible(true);
        this.txtCmbImprimeTicket.setVisible(true);
        this.txtCmbTicketBoleta.setVisible(true);
        this.txtCmbTicketFactura.setVisible(true);
        this.txtCmbAfectoIGV.setVisible(true);
        this.txtDteFechaApertura.setVisible(true);
        this.txtDteFechaCierre.setVisible(true);


        dteFechaCierre.setEnabled(false);
        dteFechaApertura.setEnabled(false);
       
        esActualizacion = false;
        this.pnlBuscador.setVisible(true);
        
        logger.info(txtCodigo.getText() + " - " + txtDescripcion.getText());
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

    private void setLocales(){
        Limpiar();
        txtCodigo.setText(cp.getLocales().getCoLocal());
        txtCodigoRegion.setText(cp.getLocales().getCoRegion());
        txtDescripcionCorta.setText(cp.getLocales().getDeCortaLocal());
        txtDescripcion.setText(cp.getLocales().getDeLocal());
        txtCodigoPais.setText(cp.getLocales().getCoPais());
        txtCodigoDepartamento.setText(cp.getLocales().getCoDepartamento());
        txtCodigoProvincia.setText(cp.getLocales().getCoProvincia());
        txtCodigoDistrito.setText(cp.getLocales().getCoDistrito());
        txtNombreVia.setText(cp.getLocales().getNoVia());
        txtNumero.setText(cp.getLocales().getNuVia().toString());
        txtDpto.setText(cp.getLocales().getNuInteriorVia());
        txtManzana.setText(cp.getLocales().getNuManzanaVia());
        txtLote.setText(cp.getLocales().getNuLoteVia());
        txtNombrePoblacion.setText(cp.getLocales().getNoPoblacion());
        txtCodigoTelefPais.setText(cp.getLocales().getCoTelPais());
        txtCodigoTelefCiudad.setText(cp.getLocales().getCoTelCiudad());
        txtNumeroTelefono.setText(cp.getLocales().getNuTelNormal());
        txtAnexo.setText(cp.getLocales().getCoExtTelNormal());
        txtNumeroFax.setText(cp.getLocales().getNuTelFax());
        txtNumeroMovil.setText(cp.getLocales().getNuTelMovil());
        txtNombreResponsableLocal.setText(cp.getLocales().getIdRespLocal());
        txtNombreResponsableAlterno.setText(cp.getLocales().getIdRespAlternoLocal());
        txtEmail.setText(cp.getLocales().getDeEmail());
        txtMensajeTicket.setText(cp.getLocales().getDeMensajeTicket());
        txtCodigoZonaSupervision.setText(cp.getLocales().getCoZonaSupervision());
        txtOrdenApertura.setText(cp.getLocales().getNuOrdenApertura().toString());
        dteFechaApertura.setDate(cp.getLocales().getFeApertura());
        dteFechaCierre.setDate(cp.getLocales().getFeCierre());
//        dteFechaApertura.setDate(AtuxUtility.getStringToDate(AtuxUtility.getDateToString(cp.getLocales().getFeApertura(), "dd/MM/yyyy"), "dd/MM/yyyy"));

        txtMontoCotizacion.setText(cp.getLocales().getVaMontoCotizacion().toString());
        txtTiempoSuministro.setText(cp.getLocales().getTsDias().toString());
        txtTiempoReposicion.setText(cp.getLocales().getTrDias().toString());
        txtCodigoCadena.setText(cp.getLocales().getCoCadenaLocal());
        
        cmbOpcion(cmbAfectoIGV, cp.getLocales().getInAfectoIgvLocal());
        cmbOpcion(cmbImprimeTestigo, cp.getLocales().getInImprimeTestigo());
        cmbOpcion(cmbImprimeComanda, cp.getLocales().getInImpComanda());
        cmbOpcion(cmbImprimeTicket, cp.getLocales().getInImprimeTicket());
        cmbOpcion(cmbTicketBoleta, cp.getLocales().getInTicketBoleta());
        cmbOpcion(cmbTicketFactura, cp.getLocales().getInTicketFactura());
        cmbOpcion(cmbTipoLocal, cp.getLocales().getInTipoCaja());

//        cp.getLocales().setTiPoblacion(((TipoPoblacion)cmbTipoPoblacion.getSelectedItem()).getTiPoblacion());
//        cp.getLocales().setTiVia(((TipoVia)cmbTipoVia.getSelectedItem()).getTiVia());
        if (cp.getLocales().getTiVia() == null){
            cp.getLocales().setTiVia("000");
        }
        TipoVia tipoVia = new TipoVia();
        tipoVia = ctv.getRegistroPorPk(new Object[]{cp.getLocales().getTiVia()});

        for (int i=0; i<=cmbTipoVia.getItemCount(); i++){
            if (this.cmbTipoVia.getItemAt(i).toString().equals(tipoVia.getDeTipoVia())){
                this.cmbTipoVia.setSelectedIndex(i);
                break;
            }
        }
        if (cp.getLocales().getTiPoblacion() == null){
            cp.getLocales().setTiPoblacion("AA");
        }
        
        if ("".equals(cp.getLocales().getTiPoblacion().trim())){
            cp.getLocales().setTiPoblacion("AA");
        }
        TipoPoblacion tipoPoblacion = new TipoPoblacion();
        tipoPoblacion = ctp.getRegistroPorPk(new Object[]{cp.getLocales().getTiPoblacion()});

        for (int i=0; i<=cmbTipoPoblacion.getItemCount(); i++){
            if (this.cmbTipoPoblacion.getItemAt(i).toString().equals(tipoPoblacion.getDePoblacion())){
                this.cmbTipoPoblacion.setSelectedIndex(i);
                break;
            }
        }

        if("A".equals(cp.getLocales().getEsLocal())){
            chbEstado.setSelected(true);
            chbSetActivo(true); 
        }else{
            chbEstado.setSelected(false);
            chbSetActivo(false); 
        }

        CPaises BG2 = new CPaises();
        if (cp.getLocales().getCoPais()!= null){            
            BG2.getRegistroPorPk(new Object[]{cp.getLocales().getCoPais()});
            txtPais.setText(BG2.getPaises().getDePais());
        }

        CUbigeo BG1 = new CUbigeo();
        if (cp.getLocales().getCoDepartamento()!= null){
            txtDepartamento.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),null,null));
        }
        if (cp.getLocales().getCoProvincia()!= null){
            txtProvincia.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),null));
        }
        if (cp.getLocales().getCoDistrito()!= null){
            txtDistrito.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),txtCodigoDistrito.getText()));
        }

        this.txtCmbTipoLocal.setText(cmbTipoLocal.getSelectedItem().toString());
        this.txtCmbTipoVia.setText(cmbTipoVia.getSelectedItem().toString());
        this.txtCmbTipoPoblacion.setText(cmbTipoPoblacion.getSelectedItem().toString());

        this.txtCmbImprimeTestigo.setText(cmbImprimeTestigo.getSelectedItem().toString());
        this.txtCmbImprimeComanda.setText(cmbImprimeComanda.getSelectedItem().toString());
        this.txtCmbImprimeTicket.setText(cmbImprimeTicket.getSelectedItem().toString());
        this.txtCmbTicketBoleta.setText(cmbTicketBoleta.getSelectedItem().toString());
        this.txtCmbTicketFactura.setText(cmbTicketFactura.getSelectedItem().toString());
        this.txtCmbAfectoIGV.setText(cmbAfectoIGV.getSelectedItem().toString());
        this.txtDteFechaApertura.setText(AtuxUtility.getDateToString(cp.getLocales().getFeApertura(),"dd/MM/yyyy"));
        this.txtDteFechaCierre.setText(AtuxUtility.getDateToString(cp.getLocales().getFeCierre(),"dd/MM/yyyy"));

        this.btnModificar.setEnabled(true);
    }

public void cmbOpcion(ComboBoxRect Combo, String opcion){
        if("S".equals(opcion)){
            Combo.setSelectedIndex(0);
        }else if("N".equals(opcion)){
            Combo.setSelectedIndex(1);
        }else if("T".equals(opcion)){
            Combo.setSelectedIndex(0);
        }else if("M".equals(opcion)){
            Combo.setSelectedIndex(1);
        }
    }
    
private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        if ("".equals(txtDescripcionCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }

        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setLocales(mtp.getFila(numRegistros));
        setLocales();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if ("".equals(txtDescripcionCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setLocales(mtp.getFila(numRegistros));
        setLocales();

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if ("".equals(txtDescripcionCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        BuscarLocales Lab = new BuscarLocales(tipoSeleccion);
        JLabel aviso = Lab.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Locales existentes":(tipoSeleccion==1?"Mostrando todo los Locales activos":"Mostrando todo los Locales No activos"));
        JOptionPane.showInternalOptionDialog(this, Lab, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(Lab.getLocales() != null){
           cp.setLocales(Lab.getLocales());
           setLocales();
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if ("".equals(txtDescripcionCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setLocales(mtp.getFila(numRegistros));
        setLocales();

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        if ("".equals(txtDescripcionCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setLocales(mtp.getFila(numRegistros));
        setLocales();

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
    cargarGrilla("T");
    chbSetActivo(true);
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
    cargarGrilla("A");
    chbSetActivo(true);
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
    cargarGrilla("I");
    chbSetActivo(false);
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;
        Limpiar();
        ActivarCasillas();
        txtCodigo.setText(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania));
        cmbTipoLocal.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasObligatorias,true)){
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
            Logger.getLogger(ILocales.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasObligatorias,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaLocales(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaLocales(tipoSeleccion,inicioPag,finalPag);
        }
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaLocales.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaLocales.anchoColumnas);
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
    dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void bntParametrosReposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntParametrosReposicionActionPerformed
        IParamXReposicion pvc = new IParamXReposicion();
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtDescripcion.getText());
        pvc.setTipoParametroReposicion("LC");
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(630, 480)); 
        
        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando todas los Parametros de Reposición por Producto";
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{pvc.getAviso()},null);
}//GEN-LAST:event_bntParametrosReposicionActionPerformed

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoPais.requestFocus();
            break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void txtCodigoDepartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            txtCodigoProvincia.setText("");
            txtCodigoDistrito.setText("");
            txtProvincia.setText("");
            txtDistrito.setText("");

            BuscarUbigeo pvc = new BuscarUbigeo();

            JLabel aviso = pvc.getAviso();

                        String msj = "Mostrando Departamento";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoDepartamento.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtDepartamento.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoProvincia.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDepartamentoKeyReleased

private void txtCodigoProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            txtCodigoDistrito.setText("");
            txtDistrito.setText("");

            BuscarUbigeo pvc = new BuscarUbigeo(txtCodigoDepartamento.getText());
            pvc.setDetalleDepartamento(txtDepartamento.getText());
            JLabel aviso = pvc.getAviso();

            String msj = "Mostrando Provincias";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoProvincia.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtProvincia.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoDistrito.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoProvinciaKeyReleased

private void txtCodigoDistritoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDistritoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            BuscarUbigeo pvc = new BuscarUbigeo(txtCodigoDepartamento.getText(), txtCodigoProvincia.getText());
            JLabel aviso = pvc.getAviso();
            pvc.setDetalleDepartamento(txtDepartamento.getText());
            pvc.setDetalleProvincia(txtProvincia.getText());
            String msj = "Mostrando Provincias";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoDistrito.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtDistrito.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoRegion.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDistritoKeyReleased

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoLocal.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
                txtDescripcion.setText(txtDescripcion.getText().toUpperCase());
                txtDescripcionCorta.requestFocus();
                break;
    }
}//GEN-LAST:event_txtDescripcionKeyReleased

private void txtCodigoPaisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPaisKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            int tipoSeleccionG=1;    
            BuscarPaises pvc = new BuscarPaises();
            JLabel aviso = pvc.getAviso();

            String msj = (tipoSeleccionG==-1?"Mostrando Listado Paises":(tipoSeleccionG==1?"Mostrando Listado de Paises Activos":"Mostrando Listado de Paises No activos"));
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getPais() != null){
                CPaises cpF = new CPaises();
                cpF.setPaises(pvc.getPais());
                this.txtCodigoPais.setText(cpF.getPaises().getCoPais());
                this.txtPais.setText(cpF.getPaises().getDePais());
            }
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoDepartamento.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoPaisKeyReleased

private void txtDescripcionCortaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionCortaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
                txtDescripcionCorta.setText(txtDescripcionCorta.getText().toUpperCase());
                chbEstado.requestFocus();
                break;
    }
}//GEN-LAST:event_txtDescripcionCortaKeyReleased

private void cmbTipoViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombreVia.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoViaKeyReleased

private void txtNombreViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombreVia.setText(txtNombreVia.getText().toUpperCase());
            txtNumero.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNombreViaKeyReleased

private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDpto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNumeroKeyReleased

private void txtDptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDptoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtManzana.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDptoKeyReleased

private void txtManzanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManzanaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtManzana.setText(txtManzana.getText().toUpperCase());
            txtLote.requestFocus();
            break;
    }
}//GEN-LAST:event_txtManzanaKeyReleased

private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoPoblacion.requestFocus();
             break;
    }
}//GEN-LAST:event_txtLoteKeyReleased

private void cmbTipoPoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoPoblacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombrePoblacion.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoPoblacionKeyReleased

private void txtNombrePoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePoblacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombrePoblacion.setText(txtNombrePoblacion.getText().toUpperCase());
            txtCodigoTelefPais.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNombrePoblacionKeyReleased

private void cmbTipoLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoLocalKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDescripcion.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoLocalKeyReleased

private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            cmbImprimeTestigo.requestFocus();
            break;
    }
}//GEN-LAST:event_txtEmailKeyReleased

private void txtCodigoTelefPaisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTelefPaisKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoTelefCiudad.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoTelefPaisKeyReleased

private void txtCodigoTelefCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTelefCiudadKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNumeroTelefono.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoTelefCiudadKeyReleased

private void txtNumeroTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroTelefonoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtAnexo.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNumeroTelefonoKeyReleased

private void txtNumeroFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFaxKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNumeroMovil.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNumeroFaxKeyReleased

private void txtAnexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnexoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNumeroFax.requestFocus();
            break;
    }
}//GEN-LAST:event_txtAnexoKeyReleased

private void txtNumeroMovilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroMovilKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoCadena.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNumeroMovilKeyReleased

private void cmbImprimeTestigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbImprimeTestigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            cmbImprimeComanda.requestFocus();
            break;
    }
}//GEN-LAST:event_cmbImprimeTestigoKeyReleased

private void cmbImprimeComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbImprimeComandaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            cmbImprimeTicket.requestFocus();
            break;
    }
}//GEN-LAST:event_cmbImprimeComandaKeyReleased

private void cmbImprimeTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImprimeTicketActionPerformed
    if(cmbImprimeTicket.isEnabled()){
        if (cmbImprimeTicket.getSelectedIndex()==0){
            cmbTicketBoleta.setEnabled(true);
            cmbTicketFactura.setEnabled(true);
            cmbTicketBoleta.setSelectedIndex(0);
            cmbTicketFactura.setSelectedIndex(0);
        }else{
            cmbTicketBoleta.setEnabled(false);
            cmbTicketFactura.setEnabled(false);
            cmbTicketBoleta.setSelectedIndex(1);
            cmbTicketFactura.setSelectedIndex(1);
        }        
    }
}//GEN-LAST:event_cmbImprimeTicketActionPerformed

private void cmbImprimeTicketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbImprimeTicketKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            if (cmbImprimeTicket.getSelectedIndex()==0){
                cmbTicketBoleta.requestFocus();
            }else{
                txtOrdenApertura.requestFocus();
            }
            break;
    }
}//GEN-LAST:event_cmbImprimeTicketKeyReleased

private void cmbTicketBoletaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTicketBoletaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_cmbTicketBoletaKeyReleased

private void cmbTicketFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTicketFacturaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_cmbTicketFacturaKeyReleased

private void txtOrdenAperturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdenAperturaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            dteFechaApertura.requestFocus();
            break;
    }
}//GEN-LAST:event_txtOrdenAperturaKeyReleased

private void dteFechaAperturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaAperturaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaCierre.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaAperturaKeyReleased

private void txtMontoCotizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoCotizacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            cmbAfectoIGV.requestFocus();
            break;
    }
}//GEN-LAST:event_txtMontoCotizacionKeyReleased

private void cmbAfectoIGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAfectoIGVKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtTiempoSuministro.requestFocus();
            break;
    }
}//GEN-LAST:event_cmbAfectoIGVKeyReleased

private void txtTiempoReposicionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoReposicionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombreResponsableLocal.requestFocus();
            break;
    }
}//GEN-LAST:event_txtTiempoReposicionKeyReleased

private void txtTiempoSuministroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoSuministroKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtTiempoReposicion.requestFocus();
            break;
    }
}//GEN-LAST:event_txtTiempoSuministroKeyReleased

private void txtNombreResponsableLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreResponsableLocalKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombreResponsableAlterno.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNombreResponsableLocalKeyReleased

private void txtNombreResponsableAlternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreResponsableAlternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtMensajeTicket.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNombreResponsableAlternoKeyReleased

private void txtMensajeTicketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeTicketKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoZonaSupervision.requestFocus();
            break;
    }
}//GEN-LAST:event_txtMensajeTicketKeyReleased

private void txtCodigoRegionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoRegionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            int tipoSeleccionG=1;    
            BuscarRegion pvc = new BuscarRegion(tipoSeleccion);
            JLabel aviso = pvc.getAviso();
            pvc.setCargarDatos();
            String msj = (tipoSeleccionG==-1?"Mostrando Listado de Cadenas":(tipoSeleccionG==1?"Mostrando Listado  de Cadenas Activos":"Mostrando Listado  de Cadenas No activos"));
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getRegion() != null){
                CRegion cpc = new CRegion();
                cpc.setMaestroRegion(pvc.getRegion());
                this.txtCodigoRegion.setText(cpc.getMaestroRegion().getCoRegion());
                this.txtRegion.setText(cpc.getMaestroRegion().getDeRegion());
            }
            break;
        case KeyEvent.VK_ENTER: 
            cmbTipoVia.requestFocus();
            break;
    }    
}//GEN-LAST:event_txtCodigoRegionKeyReleased

private void txtCodigoZonaSupervisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoZonaSupervisionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            btnGuardar.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoZonaSupervisionKeyReleased

private void txtCodigoCadenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCadenaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            int tipoSeleccionG=1;    
            BuscarCadena pvc = new BuscarCadena(tipoSeleccion);
            JLabel aviso = pvc.getAviso();
            pvc.setCodigoCompania(txtCodigoCompania.getText());
            pvc.setCargarDatos();
            String msj = (tipoSeleccionG==-1?"Mostrando Listado de Cadenas":(tipoSeleccionG==1?"Mostrando Listado  de Cadenas Activos":"Mostrando Listado  de Cadenas No activos"));
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getCadena() != null){
                CCadena cpc = new CCadena();
                cpc.setMaestroCadena(pvc.getCadena());
                this.txtCodigoCadena.setText(cpc.getMaestroCadena().getCoCadena());
                this.txtCadena.setText(cpc.getMaestroCadena().getDeCadena());
            }
            break;
        case KeyEvent.VK_ENTER: 
            txtEmail.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoCadenaKeyReleased

private void dteFechaCierreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaCierreKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtMontoCotizacion.requestFocus();
            break;
    }
}//GEN-LAST:event_dteFechaCierreKeyReleased

private void txtCodigoCompaniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCompaniaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            Busqueda="SI";
            int tipoSeleccionG=1;    
            BuscarCompania pvc = new BuscarCompania(tipoSeleccion);
            JLabel aviso = pvc.getAviso();
            String msj = (tipoSeleccionG==-1?"Mostrando Listado de Compania":(tipoSeleccionG==1?"Mostrando Listado  de Compania Activos":"Mostrando Listado  de Compania No activos"));
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getCompania() != null){
//                CCompania ctcia = new CCompania();
                ctcia.setMaestroCompania(pvc.getCompania());
                this.txtCodigoCompania.setText(ctcia.getMaestroCompania().getCoCompania());
                this.txtDescripcionCompania.setText(ctcia.getMaestroCompania().getDeCompania());
            }
            Busqueda="NO";
            break;
        case KeyEvent.VK_ENTER: 
            txtDescripcionCompania.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoCompaniaKeyReleased

private void txtCodigoCompaniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCompaniaFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoCompaniaFocusGained

private void txtCodigoCompaniaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCompaniaFocusLost
        if ("SI".equals(Busqueda)) return;
        
        if ("".equals(txtCodigoCompania.getText())){
            JOptionPane.showInternalMessageDialog(this, "Debe de seleccionar una Compañia", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            txtCodigoCompania.requestFocus();
            return;
        }
        CCompania BCC = new CCompania();
        Object[] valores = new Object[]{txtCodigoCompania.getText()}; 
        BCC.getRegistroPorPk(valores);
        
        if (BCC.existe("CMTM_COMPANIA", "co_compania", txtCodigoCompania.getText())==false){
            JOptionPane.showInternalMessageDialog(this, "Codigo de Compañia no valido", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            txtCodigoCompania.requestFocus();
            return;
            
        }
        txtDescripcionCompania.setText(BCC.getMaestroCompania().getDeCompania().trim());
        Limpiar();
        lblMensaje.setVisible(false);
        cargarGrilla("T");

    
}//GEN-LAST:event_txtCodigoCompaniaFocusLost

private void txtCodigoPaisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPaisFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoPaisFocusGained

private void txtCodigoPaisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPaisFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoPaisFocusLost

private void txtCodigoDepartamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoDepartamentoFocusGained

private void txtCodigoDepartamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoDepartamentoFocusLost

private void txtCodigoProvinciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoProvinciaFocusGained

private void txtCodigoProvinciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoProvinciaFocusLost

private void txtCodigoDistritoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDistritoFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoDistritoFocusGained

private void txtCodigoDistritoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDistritoFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoDistritoFocusLost

private void txtCodigoRegionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoRegionFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoRegionFocusGained

private void txtCodigoRegionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoRegionFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoRegionFocusLost

private void txtCodigoCadenaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCadenaFocusGained
    lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoCadenaFocusGained

private void txtCodigoCadenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCadenaFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoCadenaFocusLost

private void txtCmbTipoLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTipoLocalKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTipoLocalKeyReleased

private void txtCmbTipoPoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTipoPoblacionKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTipoPoblacionKeyReleased

private void txtCmbTipoViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTipoViaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTipoViaKeyReleased

private void txtCmbImprimeTestigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbImprimeTestigoKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbImprimeTestigoKeyReleased

private void txtCmbImprimeComandaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbImprimeComandaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbImprimeComandaKeyReleased

private void txtCmbImprimeTicketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbImprimeTicketKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbImprimeTicketKeyReleased

private void txtCmbTicketBoletaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTicketBoletaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTicketBoletaKeyReleased

private void txtCmbTicketFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTicketFacturaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTicketFacturaKeyReleased

private void txtCmbAfectoIGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbAfectoIGVKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbAfectoIGVKeyReleased

private void txtDteFechaAperturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDteFechaAperturaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDteFechaAperturaKeyReleased

private void txtDteFechaCierreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDteFechaCierreKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtDteFechaCierreKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntParametrosReposicion;
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBuscar;
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
    private elaprendiz.gui.comboBox.ComboBoxRect cmbAfectoIGV;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbImprimeComanda;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbImprimeTestigo;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbImprimeTicket;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTicketBoleta;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTicketFactura;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoLocal;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoPoblacion;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVia;
    private com.toedter.calendar.JDateChooser dteFechaApertura;
    private com.toedter.calendar.JDateChooser dteFechaCierre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAfectoIGV;
    private javax.swing.JLabel lblAnexo;
    private javax.swing.JLabel lblCadena;
    private javax.swing.JLabel lblCodTelefCiudad;
    private javax.swing.JLabel lblCodTelefPais;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoCompania;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcionCompania;
    private javax.swing.JLabel lblDescripcionCorta;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblDpto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaApertura;
    private javax.swing.JLabel lblFechaCierre;
    private javax.swing.JLabel lblImprimeComanda;
    private javax.swing.JLabel lblImprimeTestigo;
    private javax.swing.JLabel lblImprimeTicket;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblManzana;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMensajeTicket;
    private javax.swing.JLabel lblMontoCotizacion;
    private javax.swing.JLabel lblMovil;
    private javax.swing.JLabel lblNombreResponsableAlterno;
    private javax.swing.JLabel lblNombreResponsableLocal;
    private javax.swing.JLabel lblNombreVia;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroFax;
    private javax.swing.JLabel lblNumeroTelefono;
    private javax.swing.JLabel lblOrdenApertura;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPoblacion;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblRegion;
    private javax.swing.JLabel lblTicketBoleta;
    private javax.swing.JLabel lblTicketFactura;
    private javax.swing.JLabel lblTiempoReposicion;
    private javax.swing.JLabel lblTiempoSuministro;
    private javax.swing.JLabel lblTipoLocal;
    private javax.swing.JLabel lblTipoVia;
    private javax.swing.JLabel lblTipoVivienda;
    private javax.swing.JLabel lblZonaSupervicion;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel plnCompania;
    private javax.swing.JPanel pnlAccionesLab;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnlEntradasComplementarias;
    private javax.swing.JPanel pnlEntradasObligatorias;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtAnexo;
    private elaprendiz.gui.textField.TextField txtCadena;
    private elaprendiz.gui.textField.TextField txtCmbAfectoIGV;
    private elaprendiz.gui.textField.TextField txtCmbImprimeComanda;
    private elaprendiz.gui.textField.TextField txtCmbImprimeTestigo;
    private elaprendiz.gui.textField.TextField txtCmbImprimeTicket;
    private elaprendiz.gui.textField.TextField txtCmbTicketBoleta;
    private elaprendiz.gui.textField.TextField txtCmbTicketFactura;
    private elaprendiz.gui.textField.TextField txtCmbTipoLocal;
    private elaprendiz.gui.textField.TextField txtCmbTipoPoblacion;
    private elaprendiz.gui.textField.TextField txtCmbTipoVia;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCodigoCadena;
    private elaprendiz.gui.textField.TextField txtCodigoCompania;
    private elaprendiz.gui.textField.TextField txtCodigoDepartamento;
    private elaprendiz.gui.textField.TextField txtCodigoDistrito;
    private elaprendiz.gui.textField.TextField txtCodigoPais;
    private elaprendiz.gui.textField.TextField txtCodigoProvincia;
    private elaprendiz.gui.textField.TextField txtCodigoRegion;
    private elaprendiz.gui.textField.TextField txtCodigoTelefCiudad;
    private elaprendiz.gui.textField.TextField txtCodigoTelefPais;
    private elaprendiz.gui.textField.TextField txtCodigoZonaSupervision;
    private elaprendiz.gui.textField.TextField txtDepartamento;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtDescripcionCompania;
    private elaprendiz.gui.textField.TextField txtDescripcionCorta;
    private elaprendiz.gui.textField.TextField txtDistrito;
    private elaprendiz.gui.textField.TextField txtDpto;
    private elaprendiz.gui.textField.TextField txtDteFechaApertura;
    private elaprendiz.gui.textField.TextField txtDteFechaCierre;
    private elaprendiz.gui.textField.TextField txtEmail;
    private elaprendiz.gui.textField.TextField txtLote;
    private elaprendiz.gui.textField.TextField txtManzana;
    private elaprendiz.gui.textField.TextField txtMensajeTicket;
    private elaprendiz.gui.textField.TextField txtMontoCotizacion;
    private elaprendiz.gui.textField.TextField txtNombrePoblacion;
    private elaprendiz.gui.textField.TextField txtNombreResponsableAlterno;
    private elaprendiz.gui.textField.TextField txtNombreResponsableLocal;
    private elaprendiz.gui.textField.TextField txtNombreVia;
    private elaprendiz.gui.textField.TextField txtNumero;
    private elaprendiz.gui.textField.TextField txtNumeroFax;
    private elaprendiz.gui.textField.TextField txtNumeroMovil;
    private elaprendiz.gui.textField.TextField txtNumeroTelefono;
    private elaprendiz.gui.textField.TextField txtOrdenApertura;
    private elaprendiz.gui.textField.TextField txtPais;
    private elaprendiz.gui.textField.TextField txtProvincia;
    private elaprendiz.gui.textField.TextField txtRegion;
    private elaprendiz.gui.textField.TextField txtTiempoReposicion;
    private elaprendiz.gui.textField.TextField txtTiempoSuministro;
    private elaprendiz.gui.textField.TextField txtZonaSupervision;
    // End of variables declaration//GEN-END:variables

    private void cargarGrilla(String filtro) {
        Object[] valores = new Object[]{""}; 
        String[] columnas = new String[]{""};
        if ("T".equals(filtro)){
            columnas = new String[]{"CO_COMPANIA"};
            valores = new Object[]{txtCodigoCompania.getText()}; 
        }else{
            valores = new Object[]{txtCodigoCompania.getText(),filtro}; 
            columnas = new String[]{"CO_COMPANIA","ES_LOCAL"};
        }
        mtp = new ModeloTablaLocales(columnas, valores);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaLocales.anchoColumnas);
        setEventSelectionModel(tblListado.getSelectionModel());
    }
}
