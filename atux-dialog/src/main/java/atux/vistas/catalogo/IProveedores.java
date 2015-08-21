package atux.vistas.catalogo;

import atux.controllers.CProveedores;
import atux.modelgui.ModeloTablaProveedores;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.VerificadorEntrada;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarProveedor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class IProveedores extends javax.swing.JInternalFrame {
    private CProveedores cp;
    private ModeloTablaProveedores mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    
    
    public IProveedores() {
        initComponents();
        
        cp = new CProveedores();
        mtp = new ModeloTablaProveedores(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();

        tblProveedores.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProveedores,ModeloTablaProveedores.anchoColumnas);
        Helper.ajustarAnchoColumnas(tblProveedores);
        setFiltroTexto();        
        setEventSelectionModel(tblProveedores.getSelectionModel());
        DesActivarCasillas();
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
        if(tblProveedores.getSelectedRow() != -1){
            numRegistros = tblProveedores.getSelectedRow();
            cp.setProveedor(mtp.getFila(tblProveedores.getSelectedRow()));
            setProveedor();           
       }
    }
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtRuc.setText("");
        this.txtProveedor.setText("");
        this.txtCiudad.setText("");
        this.txtContacto.setText("");
        this.txtDireccion.setText("");
        this.txtEmailProveedor.setText("");
        this.txtEmailContacto.setText("");
        this.txtFaxProveedor.setText("");
        this.txtTelefContacto.setText("");
        this.txtMovilContacto.setText("");
        this.txtTelefProveedor.setText("");
        this.txtBanco.setText("");
        this.txtNumCuenta.setText("");
        this.cbxRubro.setSelectedIndex(0);
        chbSetActivo(false);
    }
    private void setProveedor(){
        Limpiar();
        this.txtCodigo.setText(cp.getProveedor().getCoProveedor());
        this.txtRuc.setText(cp.getProveedor().getNuDocIdentidad());
        this.txtProveedor.setText(cp.getProveedor().getDeProveedor());
        this.txtCiudad.setText(cp.getProveedor().getDeCiudad());
        this.txtContacto.setText(cp.getProveedor().getDeContacto());
        this.txtDireccion.setText(cp.getProveedor().getDeDireccion());
        this.txtEmailProveedor.setText(cp.getProveedor().getDeEmailProveedor());
        this.txtEmailContacto.setText(cp.getProveedor().getDeEmailContacto());
        this.txtFaxProveedor.setText(cp.getProveedor().getDeFaxProveedor());
        this.txtTelefContacto.setText(cp.getProveedor().getDeTelefono01Contacto());
        this.txtMovilContacto.setText(cp.getProveedor().getDeTelefono02Contacto());
        this.txtTelefProveedor.setText(cp.getProveedor().getDeTelefonoProveedor());
        this.txtBanco.setText(cp.getProveedor().getDeBancoProveedor());
        this.txtNumCuenta.setText(cp.getProveedor().getNuCuentaBanco());
        this.cbxRubro.setSelectedIndex(0);
        if ("L".equals(cp.getProveedor().getTiProveedor())){
            this.cbxRubro.setSelectedIndex(1);
        }else if ("D".equals(cp.getProveedor().getTiProveedor())){
            this.cbxRubro.setSelectedIndex(2);
        }

        if("A".equals(cp.getProveedor().getEsProveedor())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        lblDirec = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblFax = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();
        lblTelefonoContacto = new javax.swing.JLabel();
        lblMovil = new javax.swing.JLabel();
        lblEmailContacto = new javax.swing.JLabel();
        LblLabDrogueria = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblBanco = new javax.swing.JLabel();
        lblnroCta = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtRuc = new elaprendiz.gui.textField.TextField();
        txtProveedor = new elaprendiz.gui.textField.TextField();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        txtCiudad = new elaprendiz.gui.textField.TextField();
        txtEmailProveedor = new elaprendiz.gui.textField.TextField();
        txtTelefProveedor = new elaprendiz.gui.textField.TextField();
        txtFaxProveedor = new elaprendiz.gui.textField.TextField();
        txtContacto = new elaprendiz.gui.textField.TextField();
        txtTelefContacto = new elaprendiz.gui.textField.TextField();
        txtMovilContacto = new elaprendiz.gui.textField.TextField();
        txtEmailContacto = new elaprendiz.gui.textField.TextField();
        txtBanco = new elaprendiz.gui.textField.TextField();
        txtNumCuenta = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        cbxRubro = new elaprendiz.gui.comboBox.ComboBoxRect();
        pnlAcciones = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        pnlBuscador = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formulario del Proveedor");
        setOpaque(true);
        setRequestFocusEnabled(false);
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setMinimumSize(new java.awt.Dimension(100, 100));

        pnlEntradas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlEntradas.setOpaque(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");

        lblRuc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRuc.setText("RUC:");

        lblProveedor.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProveedor.setText("Proveedor:");

        lblDirec.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDirec.setText("Dirección:");

        lblCiudad.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCiudad.setText("Ciudad:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmail.setText("Email:");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTelefono.setText("Teléfono:");

        lblFax.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFax.setText("Fax:");

        lblContacto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblContacto.setText("Contacto:");

        lblTelefonoContacto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTelefonoContacto.setText("Telefono 2:");

        lblMovil.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMovil.setText("Movil:");

        lblEmailContacto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmailContacto.setText("Email:");

        LblLabDrogueria.setFont(new java.awt.Font("Tahoma", 1, 14));
        LblLabDrogueria.setText("Lab/Drogue.:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        lblBanco.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblBanco.setText("Banco:");

        lblnroCta.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblnroCta.setText("Nro. Cuenta:");

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pruc"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtRuc.setEditable(false);
        txtRuc.setDireccionDeSombra(30);
        txtRuc.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtRuc.setFont(new java.awt.Font("Arial", 0, 12));
        txtRuc.setName("pruc"); // NOI18N
        txtRuc.setPreferredSize(new java.awt.Dimension(120, 25));
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
        });

        txtProveedor.setEditable(false);
        txtProveedor.setFont(new java.awt.Font("Arial", 0, 12));
        txtProveedor.setName("prover"); // NOI18N
        txtProveedor.setPreferredSize(new java.awt.Dimension(320, 25));
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        txtDireccion.setEditable(false);
        txtDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDireccion.setDireccionDeSombra(30);
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtCiudad.setEditable(false);
        txtCiudad.setFont(new java.awt.Font("Arial", 0, 12));
        txtCiudad.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCiudadKeyReleased(evt);
            }
        });

        txtEmailProveedor.setEditable(false);
        txtEmailProveedor.setFont(new java.awt.Font("Arial", 0, 12));
        txtEmailProveedor.setInputVerifier(new VerificadorEntrada(125,VerificadorEntrada.EMAIL));
        txtEmailProveedor.setPreferredSize(new java.awt.Dimension(320, 25));
        txtEmailProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailProveedorKeyReleased(evt);
            }
        });

        txtTelefProveedor.setEditable(false);
        txtTelefProveedor.setFont(new java.awt.Font("Arial", 0, 12));
        txtTelefProveedor.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTelefProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefProveedorKeyReleased(evt);
            }
        });

        txtFaxProveedor.setEditable(false);
        txtFaxProveedor.setFont(new java.awt.Font("Arial", 0, 12));
        txtFaxProveedor.setPreferredSize(new java.awt.Dimension(120, 25));
        txtFaxProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFaxProveedorKeyReleased(evt);
            }
        });

        txtContacto.setEditable(false);
        txtContacto.setFont(new java.awt.Font("Arial", 0, 12));
        txtContacto.setPreferredSize(new java.awt.Dimension(320, 25));
        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactoKeyReleased(evt);
            }
        });

        txtTelefContacto.setEditable(false);
        txtTelefContacto.setFont(new java.awt.Font("Arial", 0, 12));
        txtTelefContacto.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTelefContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefContactoKeyReleased(evt);
            }
        });

        txtMovilContacto.setEditable(false);
        txtMovilContacto.setFont(new java.awt.Font("Arial", 0, 12));
        txtMovilContacto.setPreferredSize(new java.awt.Dimension(120, 25));
        txtMovilContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMovilContactoKeyReleased(evt);
            }
        });

        txtEmailContacto.setEditable(false);
        txtEmailContacto.setFont(new java.awt.Font("Arial", 0, 12));
        txtEmailContacto.setInputVerifier(new VerificadorEntrada(125,VerificadorEntrada.EMAIL));
        txtEmailContacto.setPreferredSize(new java.awt.Dimension(320, 25));
        txtEmailContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailContactoKeyReleased(evt);
            }
        });

        txtBanco.setEditable(false);
        txtBanco.setFont(new java.awt.Font("Arial", 0, 12));
        txtBanco.setPreferredSize(new java.awt.Dimension(320, 25));
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBancoKeyReleased(evt);
            }
        });

        txtNumCuenta.setEditable(false);
        txtNumCuenta.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumCuenta.setPreferredSize(new java.awt.Dimension(150, 25));
        txtNumCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumCuentaKeyReleased(evt);
            }
        });

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

        cbxRubro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------------", "Laboratorio", "Drogueria" }));
        cbxRubro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxRubroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblCodigo)
                        .addGap(12, 12, 12)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblRuc)
                        .addGap(4, 4, 4)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblProveedor)
                        .addGap(10, 10, 10)
                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDirec)
                        .addGap(12, 12, 12)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lblCiudad)
                        .addGap(10, 10, 10)
                        .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblEmail)
                        .addGap(12, 12, 12)
                        .addComponent(txtEmailProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblTelefono)
                        .addGap(10, 10, 10)
                        .addComponent(txtTelefProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblFax)
                        .addGap(4, 4, 4)
                        .addComponent(txtFaxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblContacto)
                        .addGap(10, 10, 10)
                        .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblTelefonoContacto)
                        .addGap(10, 10, 10)
                        .addComponent(txtTelefContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblMovil)
                        .addGap(8, 8, 8)
                        .addComponent(txtMovilContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblEmailContacto)
                        .addGap(10, 10, 10)
                        .addComponent(txtEmailContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(LblLabDrogueria)
                        .addGap(10, 10, 10)
                        .addComponent(cbxRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblEstado)
                        .addGap(4, 4, 4)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblBanco)
                        .addGap(12, 12, 12)
                        .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblnroCta)
                        .addGap(10, 10, 10)
                        .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCodigo))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblRuc))
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblProveedor))
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDirec)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCiudad))
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblEmail))
                    .addComponent(txtEmailProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTelefono))
                    .addComponent(txtTelefProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblFax))
                    .addComponent(txtFaxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblContacto))
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblTelefonoContacto))
                    .addComponent(txtTelefContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblMovil))
                    .addComponent(txtMovilContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblEmailContacto))
                    .addComponent(txtEmailContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LblLabDrogueria))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbxRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblEstado))
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblBanco))
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblnroCta))
                    .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblCodigo.getAccessibleContext().setAccessibleName("COPROVEEDOR");

        pnlAcciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlAcciones.setOpaque(false);
        pnlAcciones.setPreferredSize(new java.awt.Dimension(468, 40));
        pnlAcciones.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnNuevo);
        btnNuevo.setBounds(10, 6, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnModificar);
        btnModificar.setBounds(94, 6, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnGuardar);
        btnGuardar.setBounds(198, 6, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnCancelar);
        btnCancelar.setBounds(293, 6, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnSalir);
        btnSalir.setBounds(393, 6, 63, 25);

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 90));

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProveedores);

        pnlBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        pnlBuscador.setOpaque(false);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnPrimero);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnAnterior);

        btnBuscar.setBackground(new java.awt.Color(102, 204, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnBuscar);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnSiguiente);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscador.add(btnUltimo);

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

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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
    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigo,lblRuc,lblDirec,lblEmail,lblContacto,lblEmailContacto,LblLabDrogueria,lblCiudad,lblTelefono,lblTelefonoContacto,lblMovil,lblFax,lblMovil,lblEstado,lblBanco,lblnroCta,lblProveedor},false,"");
        cbxRubro.setEnabled(true);
        this.tblProveedores.setEnabled(false);
        this.tblProveedores.clearSelection();        
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
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigo,lblRuc,lblDirec,lblEmail,lblContacto,lblEmailContacto,LblLabDrogueria,lblCiudad,lblTelefono,lblTelefonoContacto,lblMovil,lblFax,lblMovil,lblEstado,lblBanco,lblnroCta,lblProveedor},true,"");
        cbxRubro.setEnabled(false);
        this.tblProveedores.setEnabled(true);
        this.tblProveedores.clearSelection();
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

        ECampos.esObligatorio(this.pnlEntradas,false);
        esActualizacion = false;
        
        logger.info(txtCodigo.getText() + " - " + txtProveedor.getText() + " - " + txtRuc.getText());
    }
        
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Limpiar();
        esActualizacion = false;
        cp.getProveedor().setCoProveedor(cp.getNuevoCodigoProveedor());
        txtCodigo.setText(cp.getProveedor().getCoProveedor());
        txtRuc.requestFocus();
        ActivarCasillas();       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }

        DesActivarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!esActualizacion){
            cp.getProveedor().setCoProveedor(cp.getNuevoCodigoProveedor());
        }
            
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

        if(!this.verficarCambios())            {
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaProveedores(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaProveedores(tipoSeleccion,inicioPag,finalPag);
        }
        tblProveedores.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProveedores,ModeloTablaProveedores.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        DesActivarCasillas();
        tblProveedores.requestFocus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaProveedores(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();  
        tblProveedores.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProveedores,ModeloTablaProveedores.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaProveedores(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblProveedores.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProveedores,ModeloTablaProveedores.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbAtivosActionPerformed

    private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaProveedores(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblProveedores.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProveedores,ModeloTablaProveedores.anchoColumnas);
        chbSetActivo(false);
        Limpiar();
    }//GEN-LAST:event_rbNoActivosActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblProveedores.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setProveedor(mtp.getFila(numRegistros));
        setProveedor();           

        return;
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblProveedores.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setProveedor(mtp.getFila(numRegistros));
        setProveedor();           

        return;
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblProveedores.getRowCount()-1;
        numRegistros=finalPag;
        cp.setProveedor(mtp.getFila(numRegistros));
        setProveedor();           

        return;
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblProveedores.getRowCount()-1;
        numRegistros=0;
        cp.setProveedor(mtp.getFila(numRegistros));
        setProveedor();           

        return;
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarProveedor pvc = new BuscarProveedor(tipoSeleccion);
        JLabel aviso = pvc.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Proveedores existentes":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getProveedor() != null){
           cp.setProveedor(pvc.getProveedor());
           setProveedor();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        dispose();
    }//GEN-LAST:event_formInternalFrameClosing

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtRuc.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtProveedor.requestFocus();
             break;
    }
}//GEN-LAST:event_txtRucKeyReleased

private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDireccion.requestFocus();
             break;
    }
}//GEN-LAST:event_txtProveedorKeyReleased

private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtCiudad.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDireccionKeyReleased

private void txtCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudadKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtEmailProveedor.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCiudadKeyReleased

private void txtEmailProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailProveedorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtTelefProveedor.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailProveedorKeyReleased

private void txtTelefProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefProveedorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtFaxProveedor.requestFocus();
             break;
    }
}//GEN-LAST:event_txtTelefProveedorKeyReleased

private void txtFaxProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFaxProveedorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtContacto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtFaxProveedorKeyReleased

private void txtContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtTelefContacto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtContactoKeyReleased

private void txtTelefContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefContactoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtMovilContacto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtTelefContactoKeyReleased

private void txtMovilContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMovilContactoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtEmailContacto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtMovilContactoKeyReleased

private void txtEmailContactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailContactoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cbxRubro.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailContactoKeyReleased

private void cbxRubroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxRubroKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_cbxRubroKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtBanco.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void txtBancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNumCuenta.requestFocus();
             break;
    }
}//GEN-LAST:event_txtBancoKeyReleased

private void txtNumCuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCuentaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNumCuentaKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_chbEstadoActionPerformed

private void tblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProveedoresMouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_tblProveedoresMouseClicked

    
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
    public void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 6, false);
        Helper.setFiltraEntrada(txtRuc.getDocument(), FiltraEntrada.SOLO_NUMEROS, 15, false);
        Helper.setFiltraEntrada(txtDireccion.getDocument(), FiltraEntrada.NUM_LETRAS, 30, true); 
        Helper.setFiltraEntrada(txtContacto.getDocument(), FiltraEntrada.SOLO_LETRAS, 150, true);
        Helper.setFiltraEntrada(txtBanco.getDocument(), FiltraEntrada.SOLO_LETRAS, 30, true);
        Helper.setFiltraEntrada(txtProveedor.getDocument(), FiltraEntrada.NUM_LETRAS, 30, true);
        Helper.setFiltraEntrada(txtCiudad.getDocument(), FiltraEntrada.NUM_LETRAS, 100, true);
        Helper.setFiltraEntrada(txtTelefProveedor.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, true);
        Helper.setFiltraEntrada(txtFaxProveedor.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, true);
        Helper.setFiltraEntrada(txtTelefContacto.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, true);
        Helper.setFiltraEntrada(txtMovilContacto.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, true);
        Helper.setFiltraEntrada(txtNumCuenta.getDocument(), FiltraEntrada.SOLO_NUMEROS, 30, false);
        Helper.setFiltraEntrada(txtEmailProveedor.getDocument(), FiltraEntrada.NUM_LETRAS, 100, true);
        Helper.setFiltraEntrada(txtEmailContacto.getDocument(), FiltraEntrada.NUM_LETRAS, 100, true);
    }
    
    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtCodigo.getText().equals(cp.getProveedor().getCoProveedor())){
            return true;
        }else if(!this.txtRuc.getText().equals(cp.getProveedor().getNuDocIdentidad())){
            return true;
        }else if(!this.txtProveedor.getText().equals(cp.getProveedor().getDeProveedor())){
            return true;
        }else if(!this.txtCiudad.getText().equals(cp.getProveedor().getDeCiudad())){
            return true;
        }else if(!this.txtContacto.getText().equals(cp.getProveedor().getDeContacto())){
            return true;
        }else if(!this.txtDireccion.getText().equals(cp.getProveedor().getDeDireccion())){
            return true;
        }else if(!this.txtEmailProveedor.getText().equals(cp.getProveedor().getDeEmailProveedor())){
            return true;
        }else if(!this.txtEmailContacto.getText().equals(cp.getProveedor().getDeEmailContacto())){
            return true;
        }else if(!this.txtFaxProveedor.getText().equals(cp.getProveedor().getDeFaxProveedor())){
            return true;
        }else if(!this.txtTelefContacto.getText().equals(cp.getProveedor().getDeTelefono01Contacto())){
            return true;
        }else if(!this.txtMovilContacto.getText().equals(cp.getProveedor().getDeTelefono02Contacto())){
            return true;
        }else if(!this.txtTelefProveedor.getText().equals(cp.getProveedor().getDeTelefonoProveedor())){
            return true;
        }else if(!this.txtBanco.getText().equals(cp.getProveedor().getDeBancoProveedor())){
            return true;
        }else if(!this.txtNumCuenta.getText().equals(cp.getProveedor().getNuCuentaBanco())){
            return true;
        }else if(this.cbxRubro.getSelectedIndex()==1){
            if (!"L".equals(cp.getProveedor().getTiProveedor())){
                return true;
            }
        }else if(this.cbxRubro.getSelectedIndex()==2){
            if (!"D".equals(cp.getProveedor().getTiProveedor())){
                return true;
            }
        }else if(chbEstado.isSelected() != ("A".equals(cp.getProveedor().getEsProveedor()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getProveedor().getEsProveedor()))){
            return true;
        }
        return false;
    }

    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }
    
    private boolean guardarActualizar() throws SQLException{
        this.txtCodigo.setText(this.txtCodigo.getText().toUpperCase());
        this.txtRuc.setText(this.txtRuc.getText().toUpperCase());
        this.txtProveedor.setText(this.txtProveedor.getText().toUpperCase());
        this.txtCiudad.setText(this.txtCiudad.getText().toUpperCase());
        this.txtContacto.setText(this.txtContacto.getText().toUpperCase());
        this.txtDireccion.setText(this.txtDireccion.getText().toUpperCase());
        this.txtEmailProveedor.setText(this.txtEmailProveedor.getText().toUpperCase());
        this.txtEmailContacto.setText(this.txtEmailContacto.getText().toUpperCase());
        this.txtFaxProveedor.setText(this.txtFaxProveedor.getText().toUpperCase());
        this.txtTelefContacto.setText(this.txtTelefContacto.getText().toUpperCase());
        this.txtMovilContacto.setText(this.txtMovilContacto.getText().toUpperCase());
        this.txtTelefProveedor.setText(this.txtTelefProveedor.getText().toUpperCase());
        this.txtBanco.setText(this.txtBanco.getText().toUpperCase());
        this.txtNumCuenta.setText(this.txtNumCuenta.getText().toUpperCase());

        cp.getProveedor().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getProveedor().setCoProveedor(txtCodigo.getText());
        cp.getProveedor().setNuDocIdentidad(txtRuc.getText());
        cp.getProveedor().setDeProveedor(this.txtProveedor.getText());
        cp.getProveedor().setDeCiudad(this.txtCiudad.getText());
        cp.getProveedor().setDeContacto(this.txtContacto.getText());
        cp.getProveedor().setDeDireccion(this.txtDireccion.getText());
        cp.getProveedor().setDeEmailProveedor(this.txtEmailProveedor.getText());
        cp.getProveedor().setDeEmailContacto(this.txtEmailContacto.getText());
        cp.getProveedor().setDeFaxProveedor(this.txtFaxProveedor.getText());
        cp.getProveedor().setDeTelefono01Contacto(this.txtTelefContacto.getText());
        cp.getProveedor().setDeTelefono02Contacto(this.txtMovilContacto.getText());
        cp.getProveedor().setDeTelefonoProveedor(this.txtTelefProveedor.getText());
        cp.getProveedor().setDeBancoProveedor(this.txtBanco.getText());
        cp.getProveedor().setNuCuentaBanco(this.txtNumCuenta.getText());

        if (this.cbxRubro.getSelectedIndex()==1){
            cp.getProveedor().setTiProveedor("L");
        }else if(this.cbxRubro.getSelectedIndex()==2){
            cp.getProveedor().setTiProveedor("D");
        }else{
            cp.getProveedor().setTiProveedor(null);
        }
        if (chbEstado.isSelected()){
            cp.getProveedor().setEsProveedor("A");
        }else{
            cp.getProveedor().setEsProveedor("I");
        }

        Boolean EstadoGuardar=false;

        if(!esActualizacion){
            cp.getProveedor().setIdCreaProveedor(AtuxVariables.vIdUsuario);
            cp.getProveedor().setFeCreaProveedor(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getProveedor().setCoProveedor(cp.getNuevoCodigoProveedor());
            txtCodigo.setText(cp.getProveedor().getCoProveedor());
            EstadoGuardar = cp.guardarRegistro(cp.getProveedor());
        }else{
            cp.getProveedor().setIdModProveedor(AtuxVariables.vIdUsuario);
            cp.getProveedor().setFeModProveedor(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getProveedor());
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblLabDrogueria;
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
    private elaprendiz.gui.comboBox.ComboBoxRect cbxRubro;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblDirec;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailContacto;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFax;
    private javax.swing.JLabel lblMovil;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoContacto;
    private javax.swing.JLabel lblnroCta;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblProveedores;
    private elaprendiz.gui.textField.TextField txtBanco;
    private elaprendiz.gui.textField.TextField txtCiudad;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtContacto;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private elaprendiz.gui.textField.TextField txtEmailContacto;
    private elaprendiz.gui.textField.TextField txtEmailProveedor;
    private elaprendiz.gui.textField.TextField txtFaxProveedor;
    private elaprendiz.gui.textField.TextField txtMovilContacto;
    private elaprendiz.gui.textField.TextField txtNumCuenta;
    private elaprendiz.gui.textField.TextField txtProveedor;
    private elaprendiz.gui.textField.TextField txtRuc;
    private elaprendiz.gui.textField.TextField txtTelefContacto;
    private elaprendiz.gui.textField.TextField txtTelefProveedor;
    // End of variables declaration//GEN-END:variables
}
