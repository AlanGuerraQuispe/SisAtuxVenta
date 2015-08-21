package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import atux.common.AtuxDocumentEditor;
import atux.common.AtuxLoadCVL;
import atux.interfaces.GeneraArchivo;
import atux.inventario.reference.DBInventario;
import atux.replicacion.CmtsReplicacionPk;
import atux.trasladoproducto.reference.AdministradorProdSobrante;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.trasladoproducto.reference.SolicitudPedidoInfo;
import atux.trasladoproducto.reference.SolicitudPedidoInfoProvider;
import atux.trasladoproducto.reference.VariablesTrasladoProducto;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

public class DlgPedidoTraslado extends JDialog {

	/**
	 * Almacena el log
	 */
	private final Log logger = LogFactory.getLog(getClass());

	Frame parentFrame;
	JTable tblListaProducto = new JTable();
	AtuxTableModel tableModelListaProducto;

	AtuxTableModel tableModelPedidoTrasladoDetalle;
	AdministradorProdSobrante administradorProdSobrante;
	boolean seDebeUsarOtrosProds = false;

	JPanel pnlCabeceraPedidoTraslado = new JPanel();
	JScrollPane scrPedidoTraslado = new JScrollPane();
	JTable tblPedidoTrasladoDetalle = new JTable();
	JLabel lblFechaEmisionT = new JLabel();
	XYLayout xYLayout1 = new XYLayout();
	JPanel pnlCabeceraDetalle = new JPanel();
	XYLayout xYLayout2 = new XYLayout();
	JLabel lblFechaEmision = new JLabel();
	JLabel lblAgregarT = new JLabel();
	JLabel lblEliminarT = new JLabel();
	JLabel lblGenerarPedidoTrasladoT = new JLabel();
	JLabel lblSalirT = new JLabel();
	JButton btnRelacion = new JButton();
	JButton btnTipo = new JButton();
	JComboBox cmbTipo = new JComboBox();
	JLabel lblDestinoT = new JLabel();
	JLabel lblStockT = new JLabel();
	JLabel lblStock = new JLabel();
	JLabel lblFraccionT = new JLabel();
	JLabel lblFraccion = new JLabel();
	JLabel lblOpcionesT = new JLabel();
	JTextField txtCodLocal = new JTextField();
	JTextField txtDesLocal = new JTextField();
	private JCheckBox chkLista = new JCheckBox();
	private JTextField txtNuRecepcion = new JTextField();
	private JLabel lblRecepcionT = new JLabel();
	private JComboBox cmbLocalADevolver = new JComboBox();
	private JLabel lblDestinoT1 = new JLabel();
	private JLabel lblLocalADevolverT = new JLabel();
	private JComboBox cmbMotivo = new JComboBox();
	private JLabel lblMotivoT = new JLabel();
	private JComboBox cmbAlmacen = new JComboBox();
	private JLabel lblAlmacenT = new JLabel();
	private JLabel lblAgregarEliminarSobranteT = new JLabel();

	public DlgPedidoTraslado() {
		this(null, "", true);
	}

	public DlgPedidoTraslado(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		parentFrame = parent;

		try {
			jbInit();
			AtuxUtility.centrarVentana(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(766, 545));
		this.getContentPane().setLayout(xYLayout1);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setFont(new Font("SansSerif", 0, 11));
		this.setTitle("Pedido de Traslado");
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				this_windowOpened(e);
			}
		});
		pnlCabeceraPedidoTraslado.setBackground(SystemColor.control);
		pnlCabeceraPedidoTraslado.setLayout(null);
		pnlCabeceraPedidoTraslado.setBorder(BorderFactory.createTitledBorder("Ingrese cabecera"));
		pnlCabeceraPedidoTraslado.setFont(new Font("SansSerif", 0, 11));
		scrPedidoTraslado.setFont(new Font("SansSerif", 0, 11));
		tblPedidoTrasladoDetalle.setFont(new Font("SansSerif", 0, 11));
		tblPedidoTrasladoDetalle.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tblPedidoTraslado_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				tblPedidoTraslado_keyReleased(e);
			}
		});
		lblFechaEmisionT.setText("Fecha de Emision :");
		lblFechaEmisionT.setBounds(new Rectangle(20, 50, 110, 20));
		lblFechaEmisionT.setFont(new Font("SansSerif", 0, 11));
		xYLayout1.setWidth(766);
		xYLayout1.setHeight(545);
		pnlCabeceraDetalle.setBackground(new Color(32, 105, 29));
		pnlCabeceraDetalle.setLayout(xYLayout2);
		pnlCabeceraDetalle.setFont(new Font("SansSerif", 0, 11));
		lblFechaEmision.setText("01/12/2003");
		lblFechaEmision.setBounds(new Rectangle(115, 50, 90, 20));
		lblFechaEmision.setFont(new Font("SansSerif", 0, 11));
		lblAgregarT.setText("[ F2 ] Agregar Producto");
		lblAgregarT.setForeground(new Color(32, 105, 29));
		lblAgregarT.setFont(new Font("SansSerif", 1, 11));
		lblEliminarT.setText("[ F4 ] Eliminar Producto");
		lblEliminarT.setForeground(new Color(32, 105, 29));
		lblEliminarT.setFont(new Font("SansSerif", 1, 11));
		lblGenerarPedidoTrasladoT.setText("[ F10 ] Aceptar");
		lblGenerarPedidoTrasladoT.setForeground(new Color(32, 105, 29));
		lblGenerarPedidoTrasladoT.setFont(new Font("SansSerif", 1, 11));
		lblSalirT.setText("[ Esc ] Salir");
		lblSalirT.setForeground(new Color(32, 105, 29));
		lblSalirT.setFont(new Font("SansSerif", 1, 11));
		btnRelacion.setText("Relacion de Productos");
		btnRelacion.setDefaultCapable(false);
		btnRelacion.setRequestFocusEnabled(false);
		btnRelacion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnRelacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelacion.setFont(new Font("SansSerif", 1, 11));
		btnRelacion.setForeground(Color.white);
		btnRelacion.setBackground(new Color(32, 105, 29));
		btnRelacion.setMnemonic('R');
		btnRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRelacion_actionPerformed(e);
			}
		});
		btnTipo.setText("Tipo :");
		btnTipo.setBounds(new Rectangle(20, 25, 35, 20));
		btnTipo.setFont(new Font("SansSerif", 0, 11));
		btnTipo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnTipo.setHorizontalAlignment(SwingConstants.LEFT);
		btnTipo.setMnemonic('T');
		btnTipo.setDefaultCapable(false);
		btnTipo.setRequestFocusEnabled(false);
		btnTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTipo_actionPerformed(e);
			}
		});
		cmbTipo.setBounds(new Rectangle(50, 25, 195, 20));
		cmbTipo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cmbTipoLocal_keyPressed(e);
			}
		});
		lblDestinoT.setText("Centro Suministrador:");
		lblDestinoT.setBounds(new Rectangle(250, 25, 110, 20));
		lblDestinoT.setFont(new Font("SansSerif", 0, 11));
		lblStockT.setText("Stock Entero:");
		lblStockT.setFont(new Font("SansSerif", 1, 11));
		lblStockT.setForeground(Color.white);
		lblStock.setFont(new Font("SansSerif", 1, 11));
		lblStock.setForeground(Color.white);
		lblStock.setText("0");
		lblFraccionT.setText("Fraccion:");
		lblFraccionT.setFont(new Font("SansSerif", 1, 11));
		lblFraccionT.setForeground(Color.white);
		lblFraccion.setText("0");
		lblFraccion.setFont(new Font("SansSerif", 1, 11));
		lblFraccion.setForeground(Color.white);
		lblOpcionesT.setText("Opciones:");
		lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
		txtCodLocal.setBounds(new Rectangle(370, 25, 50, 20));
		txtCodLocal.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtCodLocal_keyPressed(e);
			}
		});
		txtDesLocal.setBounds(new Rectangle(425, 25, 225, 20));
		txtDesLocal.setEnabled(false);
		chkLista.setBackground(new Color(32, 105, 29));
		chkLista.setEnabled(false);
		chkLista.setSelected(true);
		txtNuRecepcion.setBounds(new Rectangle(370, 50, 150, 20));
		txtNuRecepcion.setDocument(new AtuxDocumentEditor(10));
		txtNuRecepcion.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtNuRecepcion_keyPressed(e);
			}
		});

		lblRecepcionT.setBounds(new Rectangle(250, 50, 85, 20));
		lblRecepcionT.setText("Nro Recepcion : ");
		lblRecepcionT.setFont(new Font("SansSerif", 0, 11));
		cmbLocalADevolver.setBounds(new Rectangle(370, 25, 230, 20));
		cmbLocalADevolver.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cmbLocalADevolver_keyPressed(e);
			}
		});

		lblDestinoT1.setBounds(new Rectangle(255, 25, 110, 20));
		lblLocalADevolverT.setBounds(new Rectangle(260, 25, 110, 20));
		lblLocalADevolverT.setText("Local a Devolver : ");
		lblLocalADevolverT.setFont(new Font("SansSerif", 0, 11));
		cmbMotivo.setBounds(new Rectangle(370, 50, 280, 20));
		cmbMotivo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cmbMotivo_keyPressed(e);
			}
		});

		lblMotivoT.setBounds(new Rectangle(260, 50, 85, 20));
		lblMotivoT.setText("Motivo : ");
		lblMotivoT.setFont(new Font("SansSerif", 0, 11));
		cmbAlmacen.setBounds(new Rectangle(370, 25, 230, 20));
		cmbAlmacen.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cmbAlmacen_keyPressed(e);
			}
		});
		lblAlmacenT.setBounds(new Rectangle(260, 25, 110, 20));
		lblAlmacenT.setText("Centro de Dist. : ");
		lblAlmacenT.setFont(new Font("SansSerif", 0, 11));
		lblAgregarEliminarSobranteT.setText("[ F2 ] Agregar Producto de Guía     [ F3 ] Agregar Otros Productos      [ F4 ] Eliminar Producto");
		lblAgregarEliminarSobranteT.setFont(new Font("SansSerif", 1, 11));
		lblAgregarEliminarSobranteT.setForeground(new Color(32, 105, 29));
		lblAgregarEliminarSobranteT.setVisible(false);
		this.getContentPane().add(lblAgregarEliminarSobranteT, new XYConstraints(15, 490, 510, 20));
		this.getContentPane().add(lblOpcionesT, new XYConstraints(15, 470, 65, 15));
		this.getContentPane().add(lblSalirT, new XYConstraints(675, 490, 70, 20));
		this.getContentPane().add(lblGenerarPedidoTrasladoT, new XYConstraints(585, 490, 85, 20));
		this.getContentPane().add(lblEliminarT, new XYConstraints(160, 490, 130, 20));
		this.getContentPane().add(lblAgregarT, new XYConstraints(15, 490, 135, 20));
		this.getContentPane().add(pnlCabeceraDetalle, new XYConstraints(10, 95, 730, 25));
		scrPedidoTraslado.getViewport().add(tblPedidoTrasladoDetalle, null);
		this.getContentPane().add(scrPedidoTraslado, new XYConstraints(10, 120, 730, 345));
		pnlCabeceraDetalle.add(chkLista, new XYConstraints(695, 5, 30, 20));
		pnlCabeceraDetalle.add(lblFraccion, new XYConstraints(595, 5, 40, 15));
		pnlCabeceraDetalle.add(lblFraccionT, new XYConstraints(535, 5, 55, 15));
		pnlCabeceraDetalle.add(lblStock, new XYConstraints(475, 5, 50, 15));
		pnlCabeceraDetalle.add(lblStockT, new XYConstraints(390, 5, 85, 15));
		pnlCabeceraDetalle.add(btnRelacion, new XYConstraints(10, 5, 225, 15));
		this.getContentPane().add(pnlCabeceraPedidoTraslado, new XYConstraints(10, 5, 730, 85));
		pnlCabeceraPedidoTraslado.add(lblAlmacenT, null);
		pnlCabeceraPedidoTraslado.add(cmbAlmacen, null);
		pnlCabeceraPedidoTraslado.add(lblMotivoT, null);
		pnlCabeceraPedidoTraslado.add(cmbMotivo, null);
		pnlCabeceraPedidoTraslado.add(lblLocalADevolverT, null);
		pnlCabeceraPedidoTraslado.add(lblDestinoT1, null);
		pnlCabeceraPedidoTraslado.add(cmbLocalADevolver, null);
		pnlCabeceraPedidoTraslado.add(lblRecepcionT, null);
		pnlCabeceraPedidoTraslado.add(txtNuRecepcion, null);
		pnlCabeceraPedidoTraslado.add(txtDesLocal, null);
		pnlCabeceraPedidoTraslado.add(txtCodLocal, null);
		pnlCabeceraPedidoTraslado.add(lblDestinoT, null);
		pnlCabeceraPedidoTraslado.add(cmbTipo, null);
		pnlCabeceraPedidoTraslado.add(btnTipo, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmision, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT, null);
		inicializaVariablesDeLaPantalla();
		lblStockT.setVisible(false);
		lblStock.setVisible(false);
		lblFraccion.setVisible(false);
		lblFraccionT.setVisible(false);
	}

	void inicializaVariablesDeLaPantalla() {
		lblMotivoT.setVisible(false);
		cmbMotivo.setVisible(false);
		lblLocalADevolverT.setVisible(false);
		cmbLocalADevolver.setVisible(false);
		txtNuRecepcion.setEnabled(false);
		lblAlmacenT.setVisible(false);
		cmbAlmacen.setVisible(false);
		try {
			String fecha = AtuxSearch.getFechaHoraBD(ConstantsTrasladoProducto.FORMATO_FECHA);
			lblFechaEmision.setText(fecha);
		} catch (SQLException sqlerr) {
		}
		cargarComboBoxes();
		colocarFocoEnLista();
		agregarListenerACmbTipYCmbAlmacen();
		tableModelPedidoTrasladoDetalle = new AtuxTableModel(ConstantsTrasladoProducto.columnsPedidoTrasladoDetalle, ConstantsTrasladoProducto.defaultValuesPedidoTrasladoDetalle, 0);
		AtuxUtility.initSimpleList(tblPedidoTrasladoDetalle, tableModelPedidoTrasladoDetalle, ConstantsTrasladoProducto.columnsPedidoTrasladoDetalle);

		tableModelListaProducto = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaProductos, ConstantsTrasladoProducto.defaultValuesListaProductos, 0);

		AtuxUtility.initSelectList(tblListaProducto, tableModelListaProducto, ConstantsTrasladoProducto.columnsListaProductos);

		limpiarArreglos();
	}

	private void cargarComboBoxes() {
		AtuxLoadCVL.loadCVL(cmbTipo, "CMTR_TIPO_PEDIDO_TRASLADO", "TI_PEDIDO_TRASLADO", "TI_PEDIDO_TRASLADO || ' - '||DE_CORTA_TIPO_PEDIDO_TRASLADO", true,
				"ES_TIPO_PEDIDO_TRASLADO = 'A' AND IN_PEDIDO_TRASLADO = 'S' and CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'", "NU_ORDEN_FILA", "CMTR_TIPO_PEDIDO_TRASLADO");

		AtuxLoadCVL.loadCVL(cmbMotivo, "CMTR_MOTIVO", "CO_MOTIVO", "DE_MOTIVO", true, " CO_GRUPO_MOTIVO = '" + ConstantsTrasladoProducto.GRUPO_MOTIVO_TRAS_PROD + "' " + " AND CO_MOTIVO <> '001' "
				+ " AND ES_MOTIVO = 'A' ", "MOTIVO_TRAS_PROD");

		StringBuffer where = new StringBuffer();
		where.append("   CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'");
		where.append("AND ((ES_LOCAL = 'A' AND TI_LOCAL = 'A' AND IN_IMPLEMENTA_SAP = 'S') OR ");
		where.append("     (CO_LOCAL = '" + ConstantsTrasladoProducto.CO_FORMULARIO_MAGISTRAL + "'))");
		AtuxLoadCVL.loadCVL(cmbAlmacen, "VTTM_LOCAL", "CO_LOCAL", "CO_LOCAL || '-' || DE_CORTA_LOCAL", true, where.toString(), "ALMACEN_DE_REPOSICION");
		try {
			Map valores = DBTrasladoProducto.obtenerLocalesParaDevolucion();
			AtuxLoadCVL.loadCVLfromArrays(cmbLocalADevolver, "LOCAL_A_DEVOLVER", (String[]) valores.get("codigo"), (String[]) valores.get("descripcion"), true);

		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error al obtener la lista de locales a ser usados en la devolucion - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	private void limpiarArreglos() {
		VariablesTrasladoProducto.arrayProductos = new ArrayList();
		VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
		VariablesTrasladoProducto.arrayElementos = new ArrayList();
	}

	private void agregarListenerACmbTipYCmbAlmacen() {
		cmbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					tableModelListaProducto.data = new ArrayList();
					limpiarArreglos();
					tableModelPedidoTrasladoDetalle.clearTable();
					tblPedidoTrasladoDetalle.repaint();
					configurarCajasDeTextoEnBaseAlTipo();
					administradorProdSobrante = null;
					String tipoPedido = obtenerTipoPedidoTraslado();
					boolean esPedidoXSobranteOSobranteCD = ConstantsTrasladoProducto.esPedidoXSobranteOSobranteCD(tipoPedido);
					lblAgregarEliminarSobranteT.setVisible(esPedidoXSobranteOSobranteCD || ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido));
					lblAgregarT.setVisible(!(esPedidoXSobranteOSobranteCD || ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido)));
					lblEliminarT.setVisible(!(esPedidoXSobranteOSobranteCD || ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido)));
				}
			}
		});
		cmbAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					tableModelListaProducto.data = new ArrayList();
					limpiarArreglos();
					tableModelPedidoTrasladoDetalle.clearTable();
					tblPedidoTrasladoDetalle.repaint();
				}
			}
		});
	}

	private boolean esSeleccionValida() {
		String tipoPedido = obtenerTipoPedidoTraslado();

		if (AtuxVariables.vCoSucursal.equals("001")) {
			return ConstantsTrasladoProducto.esPedidoCompaniaEckerd(tipoPedido);
		} else if (AtuxVariables.vCoSucursal.equals("002") || AtuxVariables.vCoSucursal.equals("003") || AtuxVariables.vCoSucursal.equals("004")) {
			return ConstantsTrasladoProducto.esPedidoCompaniaBoticasOAmazonia(tipoPedido);
		} else {
			return ConstantsTrasladoProducto.esPedidoCompaniaFranquicia(tipoPedido);
		}
	}

	private void configurarCajasDeTextoEnBaseAlTipo() {
		String tipoPedido = obtenerTipoPedidoTraslado();
		logger.debug("Re Configurando variables x cambio de tipo de pedido.Tipo Pedido:" + tipoPedido);
		mostrarUOcultarComponentesBadadoEnElTipo(tipoPedido);

		txtCodLocal.setEnabled(true);
		txtNuRecepcion.setEnabled(false);
		if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tipoPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tipoPedido)) {
			txtCodLocal.setEnabled(false);
			txtNuRecepcion.setEnabled(true);
		}
		txtCodLocal.setText("");
		txtDesLocal.setText("");
		txtNuRecepcion.setText("");
	}

	private void mostrarUOcultarComponentesBadadoEnElTipo(String tipoPedido) {
		boolean esTipoDevolucion = ConstantsTrasladoProducto.esPedidoDevolucion(tipoPedido);
		boolean esTipoReposicionManual = ConstantsTrasladoProducto.esPedidoReposicionManual(tipoPedido);
		boolean esDevolucionOReposicionManual = esTipoDevolucion || esTipoReposicionManual;
		boolean esPedidoFranquicia = ConstantsTrasladoProducto.esPedidoFranquicia(tipoPedido);
		lblDestinoT.setVisible(!esDevolucionOReposicionManual);
		txtCodLocal.setVisible(!esDevolucionOReposicionManual);
		txtDesLocal.setVisible(!esDevolucionOReposicionManual);
		lblRecepcionT.setVisible(!esDevolucionOReposicionManual);
		txtNuRecepcion.setVisible(!esDevolucionOReposicionManual);

		lblLocalADevolverT.setVisible(esTipoDevolucion);
		cmbLocalADevolver.setVisible(esTipoDevolucion);
		lblMotivoT.setVisible(esTipoDevolucion);
		cmbMotivo.setVisible(esTipoDevolucion);

		lblAlmacenT.setVisible(esTipoReposicionManual);
		cmbAlmacen.setVisible(esTipoReposicionManual);

		cmbAlmacen.setEnabled(!esPedidoFranquicia);

		// Inicio ID: 002
		AtuxLoadCVL.setSelectedValueInComboBox(cmbLocalADevolver, "LOCAL_A_DEVOLVER", "099");
		cmbLocalADevolver.setEnabled(!esPedidoFranquicia);
		// Fin ID: 002
	}

	private String nuRecepcionOld = "";

	void agregarProducto() {
		if (txtNuRecepcion.isEnabled()) {
			completarNuRecepcion();
		}
		VariablesTrasladoProducto.vTipoPedidoTraslado = obtenerTipoPedidoTraslado();
		SolicitudPedidoInfoProvider solPedInfoProv = obtenerSolPedidoInfoProvider();
		if (!solPedInfoProv.verificarDatosEnBaseATipoPedido()) {
			return;
		}

		String tipoPedido = obtenerTipoPedidoTraslado();
		boolean esPedidoXSobranteOFaltante = ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tipoPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tipoPedido)
				|| ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido);
		if (esPedidoXSobranteOFaltante) {
			String nuRecepcionActual = txtNuRecepcion.getText().trim();
			if (!nuRecepcionActual.equals(nuRecepcionOld)) {
				nuRecepcionOld = nuRecepcionActual;
				tableModelListaProducto.data = new ArrayList();
				administradorProdSobrante = null;
			}
		}

		boolean esPedidoXSobranteOSobranteCD = ConstantsTrasladoProducto.esPedidoXSobranteOSobranteCD(tipoPedido)
				|| ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_SOBRANTE_CD_INTERCOMPANIA.equals(tipoPedido);
		if (esPedidoXSobranteOSobranteCD) {
			inicializarAdmDeProdsSobrantes();
		}

		if (tableModelListaProducto.data.size() == 0) {
			cargarListaProductos(seDebeUsarOtrosProds);
		}

		DlgListaProductos dlgListaProductos = new DlgListaProductos(parentFrame, "Relacion de Productos", true);
		dlgListaProductos.setListaProducto(tableModelListaProducto.data);
		for (int i = 0; i < dlgListaProductos.tblRelProductos.getRowCount(); i++) {
			dlgListaProductos.tblRelProductos.setValueAt(new Boolean(false), i, 0);
		}
		dlgListaProductos.setTipoPedido(tipoPedido);
		String coLocalDevolucion = "";
		if (ConstantsTrasladoProducto.esPedidoDevolucion(tipoPedido)) {
			coLocalDevolucion = obtenerCoLocalADevolver();
		}
		dlgListaProductos.setCoLocalDevolucion(coLocalDevolucion);
		try {
			if (esPedidoXSobranteOSobranteCD) {
				administradorProdSobrante.actualizarProductosSeleccionados();
			}
			dlgListaProductos.initProcess();
		} catch (SQLException e) {
			AtuxUtility.showMessage(this, "Error al inicializar la ventana de Lista de Productos - " + e.getErrorCode() + "\n" + e.toString(), null);
			e.printStackTrace();
			if (esPedidoXSobranteOSobranteCD) {
				administradorProdSobrante.actualizarArrayTrasferencia();
			}
			return;
		}

		if (tipoPedido.equals(ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD)) {
			dlgListaProductos.setNuRecepcionBase(solPedInfoProv.getTxtNuRecepcionText());
		}

		dlgListaProductos.setVisible(true);
		if (AtuxVariables.vAceptar) {
			if (esPedidoXSobranteOSobranteCD) {
				administradorProdSobrante.actualizarProductosASerTrasladados();
			}
			actualizarProductos();
			boolean hayRegistros = tblPedidoTrasladoDetalle.getRowCount() > 0;
			if (esPedidoXSobranteOFaltante) {
				txtNuRecepcion.setEnabled(!hayRegistros);
			}
		} else {
			if (esPedidoXSobranteOSobranteCD) {
				administradorProdSobrante.actualizarArrayTrasferencia();
			}
		}
		if (tblPedidoTrasladoDetalle.getRowCount() > 0) {
			AtuxGridUtils.showCell(tblPedidoTrasladoDetalle, 0, 0);
			tblPedidoTrasladoDetalle.requestFocus();
		} else {
			lblStockT.setVisible(false);
			lblStock.setVisible(false);
			lblFraccion.setVisible(false);
			lblFraccionT.setVisible(false);
		}
	}

	private void inicializarAdmDeProdsSobrantes() {
		if (administradorProdSobrante == null) {
			administradorProdSobrante = new AdministradorProdSobrante();
		}
		administradorProdSobrante.setUsarOtrosProds(seDebeUsarOtrosProds);
		if (administradorProdSobrante.seDebeCambiarListaDeProds()) {
			tableModelListaProducto.data = new ArrayList();
		}
	}

	private SolicitudPedidoInfoProvider obtenerSolPedidoInfoProvider() {
		SolicitudPedidoInfoProvider solPedInfoProv = new SolicitudPedidoInfoProvider();
		solPedInfoProv.inicializar(this, VariablesTrasladoProducto.vTipoPedidoTraslado, txtCodLocal, txtDesLocal, txtNuRecepcion, cmbLocalADevolver, cmbMotivo, cmbAlmacen);
		return solPedInfoProv;
	}

	void cargarListaProductos(boolean seDebeUsarOtrosProds) {
		try {
			String nuRecepcion = txtNuRecepcion.getText().trim();
			String inUsarOtrosProds = seDebeUsarOtrosProds ? "S" : "N";
			String esFormularioMagistral = "N";
			if (cmbAlmacen.isVisible()) {
				esFormularioMagistral = ConstantsTrasladoProducto.CO_FORMULARIO_MAGISTRAL.equals(AtuxLoadCVL.getCVLCode("ALMACEN_DE_REPOSICION", cmbAlmacen.getSelectedIndex())) ? "S" : "N";
			}
			DBTrasladoProducto.cargarListaDeProductos(tableModelListaProducto, nuRecepcion, esFormularioMagistral, inUsarOtrosProds);
			System.out.println("salmuz:" + tableModelListaProducto.data.size());
		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error al obtener la lista de productos - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	void eliminarProducto() {
		int row = tblPedidoTrasladoDetalle.getSelectedRow();
		int size = tblPedidoTrasladoDetalle.getModel().getRowCount();
		if ((row < 0) || (size == 0)) {
			AtuxUtility.showMessage(this, "No existe un Producto seleccionado !!!", null);
			return;
		}
		int rptaDialogo = JOptionPane.showConfirmDialog(this, "Seguro de eliminar el Registro ?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (rptaDialogo == JOptionPane.YES_OPTION) {
			if (administradorProdSobrante != null) {
				administradorProdSobrante.removeRow(row);
			}
			VariablesTrasladoProducto.arrayTransferencia.remove(row);
			tableModelPedidoTrasladoDetalle.deleteRow(row);
			tblPedidoTrasladoDetalle.repaint();
			boolean hayRegistros = tableModelPedidoTrasladoDetalle.getRowCount() > 0;
			if (hayRegistros) {
				if (row > 0) {
					row--;
				}
				AtuxGridUtils.showCell(tblPedidoTrasladoDetalle, row, 0);
			} else {
				lblStock.setText("");
				lblFraccion.setText("");
				lblStock.setVisible(false);
				lblStockT.setVisible(false);
				lblFraccion.setVisible(false);
				lblFraccionT.setVisible(false);
				btnTipo.doClick();
				String tipoPedido = obtenerTipoPedidoTraslado();
				boolean esPedidoXSobranteOFaltante = ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tipoPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tipoPedido);
				if (esPedidoXSobranteOFaltante) {
					txtNuRecepcion.setEnabled(true);
				}
			}
		}
	}

	void tblPedidoTraslado_keyPressed(KeyEvent e) {
		manejaOpciones(e);
		if (tblPedidoTrasladoDetalle.getRowCount() > 0) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				e.consume();
			}
		}
	}

	void btnRelacion_actionPerformed(ActionEvent e) {
		colocarFocoEnLista();
	}

	void colocarFocoEnLista() {
		if (tblPedidoTrasladoDetalle.getSelectedRow() > 0) {
			AtuxGridUtils.showCell(tblPedidoTrasladoDetalle, 0, 0);
		}
		tblPedidoTrasladoDetalle.requestFocus();
	}

	void manejaOpciones(KeyEvent e) {
		seDebeUsarOtrosProds = false;
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			System.out.println("--> F2");
			agregarProducto();
		} else if (e.getKeyCode() == KeyEvent.VK_F3) {
			System.out.println("--> F3");
			seDebeUsarOtrosProds = true;
			agregarProducto();
		} else if (e.getKeyCode() == KeyEvent.VK_F4) {
			eliminarProducto();
		} else if (e.getKeyCode() == KeyEvent.VK_F10) {
			System.out.println("--> F10");
			grabarPedidoTraslado();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			closeWindow(false);
		}
	}

	void btnTipo_actionPerformed(ActionEvent e) {
		cmbTipo.requestFocus();
	}

	void cmbTipoLocal_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!esSeleccionValida()) {
				JOptionPane.showMessageDialog(this, "El tipo de pedido seleccionado no es volido para su local", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);

				cmbTipo.requestFocus();
				return;
			}

			if (cmbLocalADevolver.isVisible()) {
				cmbLocalADevolver.requestFocus();
			} else if (cmbAlmacen.isVisible()) {
				cmbAlmacen.requestFocus();
			} else if (txtCodLocal.isEnabled()) {
				txtCodLocal.requestFocus();
			} else {
				txtNuRecepcion.requestFocus();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			closeWindow(false);
		}
	}

	void cmbLocalADevolver_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cmbMotivo.requestFocus();
		} else {
			manejaOpciones(e);
		}
	}

	void cmbMotivo_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cmbTipo.requestFocus();
		} else {
			manejaOpciones(e);
		}
	}

	void cmbAlmacen_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cmbTipo.requestFocus();
		} else {
			manejaOpciones(e);
		}
	}

	void tblPedidoTraslado_keyReleased(KeyEvent e) {
		if (tblPedidoTrasladoDetalle.getRowCount() > 0) {
			actualizarStockLabel();
		}
	}

	void actualizarStockLabel() {
		try {
			if (tblPedidoTrasladoDetalle.getSelectedRow() <= 0) {
				AtuxGridUtils.showCell(tblPedidoTrasladoDetalle, 0, 0);
			}
			String producto = ((String) tblPedidoTrasladoDetalle.getValueAt(tblPedidoTrasladoDetalle.getSelectedRow(), 0)).trim();
			ArrayList datosProducto = DBInventario.obtieneDatosProducto(producto);
			String in_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(0)).trim();
			String stock_total = ((String) ((ArrayList) datosProducto.get(0)).get(1)).trim();
			String stock_entero = ((String) ((ArrayList) datosProducto.get(0)).get(2)).trim();
			String stock_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(3)).trim();
			if (!lblStock.isVisible()) {
				lblStock.setVisible(true);
				lblStockT.setVisible(true);
			}
			boolean esProdFraccionado = in_fraccion.equalsIgnoreCase("S");
			lblFraccion.setVisible(esProdFraccionado);
			lblFraccionT.setVisible(esProdFraccionado);
			lblStock.setText(stock_entero);
			lblFraccion.setText(stock_fraccion);
		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error al obtener el Stock del Producto !!! - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	void actualizarProductos() {
		// inicializamos el JTable para insertar productos seleccionados
		tableModelPedidoTrasladoDetalle.clearTable();
		// cargamos data desde el ArrayList Master - arrayPedidos
		for (int i = 0; i < VariablesTrasladoProducto.arrayTransferencia.size(); i++) {
			if (logger.isDebugEnabled()) {
				logger.debug("Producto Seleccionado:" + VariablesTrasladoProducto.arrayTransferencia.get(i));
			}
			tableModelPedidoTrasladoDetalle.insertRow((ArrayList) VariablesTrasladoProducto.arrayTransferencia.get(i));
			tableModelPedidoTrasladoDetalle.fireTableDataChanged();
		}
		// refrescamos el JTable para ver los cambios
		tblPedidoTrasladoDetalle.repaint();
		AtuxUtility.setearPrimerRegistro(tblPedidoTrasladoDetalle, null, 0);
	}

	void closeWindow(boolean pAceptar) {
		AtuxVariables.vAceptar = pAceptar;
		VariablesTrasladoProducto.arrayProductos = new ArrayList();
		VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
		tableModelPedidoTrasladoDetalle.data = new ArrayList();
		this.setVisible(false);
		this.dispose();
	}

	void txtCodLocal_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			VariablesTrasladoProducto.vCodLocal = txtCodLocal.getText().trim();
			seleccionarLocal();
		}
		manejaOpciones(e);
	}

	/*
	 * private void txtNuRecepcion_keyTyped(KeyEvent e) { AtuxUtility.admitirDigitos(txtNuRecepcion, e); }
	 */

	private void txtNuRecepcion_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (txtNuRecepcion.getText().trim().length() > 0) {
				if (Character.isLetter(txtNuRecepcion.getText().charAt(0))) {
					if (!txtNuRecepcion.getText().startsWith("CD")) {
						JOptionPane.showMessageDialog(this, "El Número de recepcion ingresado no es volido", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
						txtNuRecepcion.requestFocus();
						return;
					}
				}
			}

			completarNuRecepcion();
		}

		manejaOpciones(e);
	}

	private void completarNuRecepcion() {
		txtNuRecepcion.setText(AtuxUtility.completeWithSymbol(txtNuRecepcion.getText().trim(), 10, "0", "I"));
	}

	void this_windowOpened(WindowEvent e) {
		cmbTipo.requestFocus();
	}

	void seleccionarLocal() {
		if (VariablesTrasladoProducto.vCodLocal.length() == 0) {
			DlgSelLocal dlgSelLocal = new DlgSelLocal(parentFrame, "", true);
			dlgSelLocal.setVisible(true);
			if (AtuxVariables.vAceptar) {
				txtCodLocal.setText(VariablesTrasladoProducto.vCodLocal);
				txtDesLocal.setText(VariablesTrasladoProducto.vNoLocal);
			}
		} else {
			VariablesTrasladoProducto.vCodLocal = AtuxUtility.caracterIzquierda(txtCodLocal.getText().trim(), 3, "0");
			try {
				VariablesTrasladoProducto.vNoLocal = DBTrasladoProducto.getNombreLocal();
				if (VariablesTrasladoProducto.vNoLocal.trim().length() == 0) {
					VariablesTrasladoProducto.vCodLocal = "";
					AtuxUtility.showMessage(this, "El Codigo de Local NO existe. Verifique !!!", null);
					txtCodLocal.selectAll();
					txtDesLocal.setText("");
					AtuxUtility.moveFocus(txtCodLocal);
				} else {
					txtCodLocal.setText(VariablesTrasladoProducto.vCodLocal);
					txtDesLocal.setText(VariablesTrasladoProducto.vNoLocal);
				}
			} catch (SQLException sqlerr) {
				AtuxUtility.showMessage(this, "Error al obtener Nombre del Local. Verifique !!! - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
				sqlerr.printStackTrace();
			}
		}
	}

	private boolean esAutoguia() {
		if (!AtuxVariables.vCoSucursal.equals("001")) {
			if (obtenerTipoPedidoTraslado().equals(ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION)) {
				System.out.println("Es Autoguia : " + AtuxLoadCVL.getCVLCode("LOCAL_A_DEVOLVER", cmbLocalADevolver.getSelectedIndex()));
				if (AtuxLoadCVL.getCVLCode("LOCAL_A_DEVOLVER", cmbLocalADevolver.getSelectedIndex()).equals(AtuxVariables.vCodigoLocal)) {
					return true;
				} else {
					return false;
				}
			}
		}

		return true;
	}

	private void grabarPedidoTraslado() {
		if (!esSeleccionValida()) {
			JOptionPane.showMessageDialog(this, "El tipo de pedido seleccionado no es volido para su local", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			cmbTipo.requestFocus();
			return;
		}

		if (!esAutoguia()) {
			JOptionPane.showMessageDialog(this, "Su Local solo puede usar los Pedidos de Devolucion para hacer AutoGuías", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			cmbTipo.requestFocus();
			return;
		}

		VariablesTrasladoProducto.vTipoPedidoTraslado = obtenerTipoPedidoTraslado();
		SolicitudPedidoInfoProvider solPedInfoProv = obtenerSolPedidoInfoProvider();
		if (!solPedInfoProv.verificarDatosEnBaseATipoPedido()) {
			return;
		}

		if (tblPedidoTrasladoDetalle.getRowCount() == 0) {
			AtuxUtility.showMessage(this, "No existe ningon item para realizar el Pedido de Traslado.", null);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "Desea grabar el Pedido de Traslado?", "Confirmar el Pedido de Traslado", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
			return;
		}
		try {

			SolicitudPedidoInfo solPedidoInfo = solPedInfoProv.getPedidoInfo();
			String coCompania = solPedidoInfo.getCoCompania();
			String colocal = solPedidoInfo.getCoLocal();
			String nuSolicitudPedido = solPedidoInfo.getNuPedidoTraslado();
			String coLocalDestino = solPedidoInfo.getCoLocalDestino();
			String nuRecepcionProducto = solPedidoInfo.getNuRecepcionProducto();
			String coCompaniaBase = solPedidoInfo.getCoCompaniaBase();
			String colocalBase = solPedidoInfo.getCoLocalBase();
			String nuRecepcionProductoBase = solPedidoInfo.getNuRecepcionProductoBase();
			String nuSolicitudOriginal = solPedidoInfo.getNuPedidoOriginal();
			String tiSolicitudOriginal = solPedidoInfo.getTiPedidoOriginal();
			String coMotivo = solPedidoInfo.getCoMotivo();

			logger.warn(solPedidoInfo.toString());

			grabarCabecera(coCompania, colocal, nuSolicitudPedido, coLocalDestino, nuRecepcionProducto, coCompaniaBase, colocalBase, nuRecepcionProductoBase, nuSolicitudOriginal, tiSolicitudOriginal,
					coMotivo);
			grabarDetalle(coCompania, colocal, nuSolicitudPedido);
			//String coLocalDestinoAReplicar = solPedidoInfo.getCoLocalDestinoAReplicar();

			logger.info("PK Grabada: " + coCompania + ";" + colocal + ";" + nuSolicitudPedido);
			logger.info("Local origen: " + colocal + "   Destino: " + coLocalDestino + "   Base: " + colocalBase);

			//if (!ConstantsTrasladoProducto.esPedidoFranquicia(VariablesTrasladoProducto.vTipoPedidoTraslado)) {
			generaArchivoPedidoTraslado(nuSolicitudPedido, colocal, coLocalDestino, VariablesTrasladoProducto.vTipoPedidoTraslado);
			AtuxUtility.aceptarTransaccion();
			
			if (logger.isInfoEnabled()) {
				logger.info("Pedido de Traslado  Generado:" + coCompania + "-" + colocal + "-" + nuSolicitudPedido);
			}

			AtuxUtility.showMessage(this, "Número de Pedido de Traslado Generado : " + nuSolicitudPedido, null);
			closeWindow(true);
		} catch (Exception e) {
			DBTrasladoProducto.procesarException(this, e, "Error al Generar el Pedido de Traslado. Verifique !!! \n");
		}
	}

	private void generaArchivoPedidoTraslado(String pNuSolicitudPedido, String pCoLocalOrigen, String pCoLocalDestino, String pTiSolicitudOriginal) throws Exception {
		// obtener si el local destino es sap
		String coLocal = "";

		logger.info("Se inicia la generacion del archivo");
		logger.info("Tipo de Pedio: " + pTiSolicitudOriginal + " - Invierto los locales");
		if (pTiSolicitudOriginal.equals("ZTTF") || pTiSolicitudOriginal.equals("ZDRF") || pTiSolicitudOriginal.equals("ZDRM")) {
			logger.info("Tipo de Pedio: " + pTiSolicitudOriginal + "-Invierto los locales");
			coLocal = pCoLocalOrigen;
		} else {
			logger.info("Tipo de Pedio: " + pTiSolicitudOriginal + "-No los locales");
			coLocal = pCoLocalDestino;
		}

		boolean localDestinoSAP = AtuxDBUtility.getValueAt("VTTM_LOCAL", "IN_IMPLEMENTA_SAP", "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + coLocal + "'").equals("S");
		logger.info("Query de validacion: " + "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + coLocal + "'");

		// obtener la sociedad a la que pertenezco
		String coSociedad = "001";
		String coSociedadDestino = "001";// salmuz modify

		boolean esAlmacen = esAlmacenORecetario(coLocal);
		GeneraArchivo generaArchivo = new GeneraArchivo();
		logger.info("Parametros: " + localDestinoSAP + "   ,    " + esAlmacen);
		if (!esAlmacen) {
			// Tipos de Pedidos Disponibles entre Locales: ZTTT, ZTTF, ZTTS
			if (localDestinoSAP && coLocal != AtuxVariables.vCodigoLocal) {

				String codInterface = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.pedido.zttt'");
				generaArchivo.setCodInterface(codInterface);
				generaArchivo.setParamCabecera(AtuxVariables.vCodigoCompania + ";" + pCoLocalOrigen + ";" + pNuSolicitudPedido + ";NAME_FILE");
				generaArchivo.setParamDetalle(AtuxVariables.vCodigoCompania + ";" + pCoLocalOrigen + ";" + pNuSolicitudPedido);

				if (pTiSolicitudOriginal.equals("ZTTF")) {
					generaArchivo.setProcCabecera("COPQ_MM_AB_PEDIDO.GET_CAB_DEV_02(?,?,?,?)");
					generaArchivo.setProcDetalle("COPQ_MM_AB_PEDIDO.GET_DET_DEV_02(?,?,?)");
				} else {
					generaArchivo.setProcCabecera("COPQ_MM_AB_PEDIDO.GET_CAB_ABASTO_02(?,?,?,?)");
					generaArchivo.setProcDetalle("COPQ_MM_AB_PEDIDO.GET_DET_ABASTO_02(?,?,?)");
				}

				generaArchivo.setWithCommit(false);
				generaArchivo.generar();
			}
		} else {
			// Tipos de Pedidos Disponibles con los Almacenes: ZTRM (Adicional), ZDRF (Faltante), ZTRS (Sobrante), ZDRM (Devolucion)
			String codInterface = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.pedido.zttt'");

			generaArchivo.setCodInterface(codInterface);
			generaArchivo.setParamCabecera(AtuxVariables.vCodigoCompania + ";" + pCoLocalOrigen + ";" + pNuSolicitudPedido + ";NAME_FILE");
			generaArchivo.setParamDetalle(AtuxVariables.vCodigoCompania + ";" + pCoLocalOrigen + ";" + pNuSolicitudPedido);

			logger.info("Sociedades: " + coSociedad + "   ,    " + coSociedadDestino);
			if (coSociedad.equals(coSociedadDestino)) {
				generaArchivo.setProcCabecera("COPQ_MM_AB_PEDIDO.GET_CAB_ABASTO_02(?,?,?,?)");
				generaArchivo.setProcDetalle("COPQ_MM_AB_PEDIDO.GET_DET_ABASTO_02(?,?,?)");
			} else {
				codInterface = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.reposicion.otros'");
				generaArchivo.setCodInterface(codInterface);
				generaArchivo.setProcCabecera("COPQ_MM_VTA_INTERCOMP.GET_CAB_REPOS_MANUAL_REG_NSAP(?,?,?,?)");
				generaArchivo.setProcDetalle("COPQ_MM_VTA_INTERCOMP.GET_DET_REPOS_MANUAL_REG_NSAP(?,?,?)");
			}

			generaArchivo.setWithCommit(false);
			generaArchivo.generar();
		}
	}

	private boolean esAlmacenORecetario(String coLocal) throws SQLException {
		return DBTrasladoProducto.esAlmacenORecetario(coLocal);
	}

	private String obtenerTipoPedidoTraslado() {
		return AtuxLoadCVL.getCVLCode("CMTR_TIPO_PEDIDO_TRASLADO", cmbTipo.getSelectedIndex());
	}

	private void grabarCabecera(String coCompania, String colocal, String nuSolicitudPedido, String pCoLocalDestino, String pNuRecepcionProducto, String pCoCompaniaBase, String pCoLocalBase,
			String pNuRecepcionBase, String pNuSolicitudOriginal, String pTiSolicitudOriginal, String pCoMotivo) throws SQLException {
		String tipoSolicitudPedido = VariablesTrasladoProducto.vTipoPedidoTraslado;
		int pCantidadProductos = tblPedidoTrasladoDetalle.getRowCount();
		String pEsSolicitudPedido = ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_PENDIENTE;
		String pIdResponsable = AtuxVariables.vIdUsuario;
		String pIdCreaSolicitud = AtuxVariables.vIdUsuario;
		String codigoCompaniaBase = pCoCompaniaBase;
		String codigoLocalBase = pCoLocalBase;
		String nuRecepcionPedidoBase = pNuRecepcionBase;
		String nuSolicitudOriginal = pNuSolicitudOriginal;
		String tiSolicitudOriginal = pTiSolicitudOriginal;
		String coMotivo = pCoMotivo;
		String noResponsable = DBTrasladoProducto.obtenerNoResponsable(pIdResponsable);

		logger.info("Se inicia la grabacion de la cabecera");

		DBTrasladoProducto.grabarCabeceraPedidoTraslado(coCompania, colocal, nuSolicitudPedido, tipoSolicitudPedido, pCoLocalDestino, pNuRecepcionProducto, pCantidadProductos, pEsSolicitudPedido,
				pIdResponsable, noResponsable.trim(), pIdCreaSolicitud, codigoCompaniaBase, codigoLocalBase, nuRecepcionPedidoBase, nuSolicitudOriginal, tiSolicitudOriginal, coMotivo);
	}

	private void grabarDetalle(String coCompania, String colocal, String nuSolicitudPedido) throws SQLException {
		int nuItemSolicitudPedido = 0;
		String idCreaSolicituddet = AtuxVariables.vIdUsuario;
		int nuRows = tableModelPedidoTrasladoDetalle.getRowCount();
		for (int i = 0; i < nuRows; i++) {
			nuItemSolicitudPedido = i + 1;
			String coProducto = (String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_CO_PRODUCTO);
			String nuRevisionProducto = "0";
			String deUnidadProducto = (String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_DE_UNIDAD);
			String deUnidadFraccion = (String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_DE_UNIDAD_FRACCION);
			int caSolicitadaEntera = Integer.parseInt((String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_CA_SOLICITADA));
			String inProdFraccionado = (String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_IN_PROD_FRACCIONADO);
			String vaFraccionStr = (String) tableModelPedidoTrasladoDetalle.getValueAt(i, ConstantsTrasladoProducto.PEDIDO_TRASLADO_COL_VA_FRACCION);
			Integer vaFraccion = (vaFraccionStr == null || vaFraccionStr.equals("")) ? new Integer(0) : new Integer(vaFraccionStr);
			DBTrasladoProducto.grabarDetallePedidoTraslado(coCompania, colocal, nuSolicitudPedido, nuItemSolicitudPedido, coProducto, nuRevisionProducto, caSolicitadaEntera, inProdFraccionado,
					vaFraccion, deUnidadProducto, deUnidadFraccion, idCreaSolicituddet);
		}
	}

	private String obtenerCoLocalADevolver() {
		return AtuxLoadCVL.getCVLCode("LOCAL_A_DEVOLVER", cmbLocalADevolver.getSelectedIndex());
	}

	public static void main(String[] args) {
		TrasladoProductosInterno.init();
		DlgPedidoTraslado dlgPedidoTraslado = new DlgPedidoTraslado(null, "Pedido de Traslado", true);
		dlgPedidoTraslado.setVisible(true);
	}

}
