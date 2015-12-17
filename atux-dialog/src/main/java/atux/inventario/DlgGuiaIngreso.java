package atux.inventario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import atux.common.AtuxDocumentEditor;
import atux.common.AtuxLoadCVL;
import atux.interfaces.GeneraArchivo;
import atux.inventario.reference.ConstantsInventario;
import atux.inventario.reference.DBInventario;
import atux.inventario.reference.VariablesInventario;
import atux.trasladoproducto.DlgSelLocal;
import atux.util.AtuxLengthText;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.util.print.AtuxPrintService;

public class DlgGuiaIngreso extends JDialog {

	Frame parentFrame;
	AtuxTableModel tableModelGuiaIngreso;
	JPanel pnlCabeceraGuia = new JPanel();
	JLabel lblLocalT = new JLabel();
	JLabel lblLocalProv = new JLabel();
	JTextField txtFechaEmision = new JTextField();
	JLabel lblNombreLocal = new JLabel();
	JLabel lblTipoDocumentoT = new JLabel();
	JTextField txtNroDocumento = new JTextField();
	JScrollPane srcGuiaIngreso = new JScrollPane();
	JTable tblGuiaIngreso = new JTable();
	JPanel pnlCabeceraDetalle = new JPanel();
	JButton btnDetalleGuia = new JButton();
	JComboBox cmbDocumento = new JComboBox();
	JLabel lblNroDocumentoT = new JLabel();
	JLabel lblOtroProveedor = new JLabel();
	JTextField txtOtroProveedor = new JTextField();
	JLabel lblAgregarT = new JLabel();
	JLabel lblSalirT = new JLabel();
	JLabel lblGrabarT = new JLabel();
	JButton btnFecha = new JButton();
	JPanel pnlPieRelacionProdGuiaIng = new JPanel();
	JLabel lblTotal = new JLabel();
	JLabel lblTotalT = new JLabel();
	JLabel lblEliminarT = new JLabel();
	JLabel lblTotalItemsT = new JLabel();
	JLabel lblCantItems = new JLabel();
	/* Variables poblicas */
	double totalCompra;
	int cantItems;
	int longitudTexto = 0;
	int tamaooTextoFechaEmision;
	JLabel lblImprimir = new JLabel();
	JLabel lblOpcionesT = new JLabel();
	double dblTotalCantEntera = 0, dblTotalCantFracc = 0;
	String strNumRecepProd = " ";
	boolean blnAceptar = true;

	AtuxTableModel tableModelListaProducto;
	JTable tblListaProducto = new JTable();
	JTextField txtCodLocal = new JTextField();
	JTextField txtDesLocal = new JTextField();
	JTextField txtNroDocumento2 = new JTextField();
	JLabel lblGuion = new JLabel();
	JLabel lblTotaCantEnteraT = new JLabel();
	JLabel lblTotaCantEntera = new JLabel();
	JLabel lblTotaCantFraccT = new JLabel();
	JLabel lblTotaCantFracc = new JLabel();
	JLabel lblAnularGuiaT = new JLabel();
	JLabel lblGuardarT = new JLabel();
	int giAnchoTextoEntero[] = { 30, 50, 50, 16, 16, 16, 30, 30 };
	int giAnchoTextoFraccion[] = { 30, 50, 50, 16, 16, 16, 30, 30, 30, 30 };

	// INICIO ID= 01
	private JLabel lblMontoMaximo = new JLabel();
	double montoConsumido;
	double montoMaximo;
	private JLabel lblEnter = new JLabel();
	// FIN ID= 01

	private double porcentajeVariacion = 0;
	private JComboBox cmbTypeOrden = new JComboBox();
	private JLabel lblTypeOrden = new JLabel();

	public DlgGuiaIngreso() {
		this(null, "", true);
	}

	public DlgGuiaIngreso(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		try {
			jbInit();
			AtuxUtility.centrarVentana(this);
			cargaDatosIniciales();
			parentFrame = parent;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(758, 555));
		this.setTitle("Ingreso Manual");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setFont(new Font("SansSerif", 0, 11));
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				this_windowOpened(e);
			}

			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});
		pnlCabeceraGuia.setLayout(null);
		pnlCabeceraGuia.setBackground(SystemColor.control);
		pnlCabeceraGuia.setBounds(new Rectangle(15, 10, 720, 145));
		pnlCabeceraGuia.setBorder(BorderFactory.createTitledBorder("Ingrese datos de Guía"));
		pnlCabeceraGuia.setFont(new Font("SansSerif", 0, 11));
		lblLocalT.setText("Local:");
		lblLocalT.setBounds(new Rectangle(15, 55, 45, 20));
		lblLocalT.setFont(new Font("SansSerif", 0, 11));
		lblLocalProv.setText("Local Proveedor :");
		lblLocalProv.setBounds(new Rectangle(375, 75, 115, 20));
		lblLocalProv.setFont(new Font("SansSerif", 0, 11));
		txtFechaEmision.setBounds(new Rectangle(125, 80, 75, 20));
		txtFechaEmision.setDocument(new AtuxLengthText(10));
		txtFechaEmision.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtFechaEmision_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				txtFechaEmision_keyReleased(e);
			}
		});
		lblNombreLocal.setText("001 - J.Plaza");
		lblNombreLocal.setBounds(new Rectangle(125, 55, 125, 20));
		lblNombreLocal.setFont(new Font("SansSerif", 1, 11));
		lblNombreLocal.setBackground(SystemColor.control);
		lblTipoDocumentoT.setText("Tipo Documento :");
		lblTipoDocumentoT.setBounds(new Rectangle(10, 115, 105, 20));
		lblTipoDocumentoT.setFont(new Font("SansSerif", 0, 11));
		txtNroDocumento.setBounds(new Rectangle(490, 110, 30, 20));
		txtNroDocumento.setDocument(new AtuxDocumentEditor(3, AtuxDocumentEditor.NUMERIC));

		txtNroDocumento.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtNroDocumento_keyPressed(e);
			}

		});
		srcGuiaIngreso.setBounds(new Rectangle(15, 190, 720, 245));
		srcGuiaIngreso.setFont(new Font("SansSerif", 0, 11));
		tblGuiaIngreso.setFont(new Font("SansSerif", 0, 11));
		tblGuiaIngreso.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				tblGuiaIngreso_mouseReleased(e);
			}

		});
		tblGuiaIngreso.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tblGuiaIngreso_keyPressed(e);
			}

		});
		pnlCabeceraDetalle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		pnlCabeceraDetalle.setLayout(null);
		pnlCabeceraDetalle.setBackground(new Color(32, 105, 29));
		pnlCabeceraDetalle.setBounds(new Rectangle(15, 165, 720, 25));
		pnlCabeceraDetalle.setFont(new Font("SansSerif", 0, 11));
		btnDetalleGuia.setText("Detalle de Guía de Ingreso");
		btnDetalleGuia.setBounds(new Rectangle(10, 5, 215, 15));
		btnDetalleGuia.setMnemonic('D');
		btnDetalleGuia.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnDetalleGuia.setBackground(new Color(32, 105, 29));
		btnDetalleGuia.setBorderPainted(false);
		btnDetalleGuia.setForeground(Color.white);
		btnDetalleGuia.setFont(new Font("SansSerif", 1, 11));
		btnDetalleGuia.setFocusPainted(false);
		btnDetalleGuia.setRequestFocusEnabled(false);
		btnDetalleGuia.setHorizontalAlignment(SwingConstants.LEFT);
		btnDetalleGuia.setDefaultCapable(false);
		btnDetalleGuia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetalleGuia_actionPerformed(e);
			}
		});
		cmbDocumento.setBounds(new Rectangle(125, 115, 155, 20));
		cmbDocumento.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				cmbDocumento_keyPressed(e);
			}
		});
		lblNroDocumentoT.setText("No. Documento :");
		lblNroDocumentoT.setBounds(new Rectangle(375, 110, 115, 20));
		lblNroDocumentoT.setFont(new Font("SansSerif", 0, 11));
		lblOtroProveedor.setText("Otros Proveedores :");
		lblOtroProveedor.setBounds(new Rectangle(10, 160, 80, 20));
		lblOtroProveedor.setFont(new Font("SansSerif", 0, 11));
		txtOtroProveedor.setBounds(new Rectangle(95, 130, 190, 20));
		txtOtroProveedor.setDocument(new AtuxLengthText(50));
		txtOtroProveedor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtOtroProveedor_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				txtOtroProveedor_keyReleased(e);
			}
		});
		lblAgregarT.setText("[ F2 ] Agregar");
		lblAgregarT.setFont(new Font("SansSerif", 1, 11));
		lblAgregarT.setBounds(new Rectangle(25, 485, 100, 15));
		lblAgregarT.setForeground(new Color(32, 105, 29));
		lblSalirT.setText("[ Esc ] Salir");
		lblSalirT.setFont(new Font("SansSerif", 1, 11));
		lblSalirT.setBounds(new Rectangle(655, 485, 80, 15));
		lblSalirT.setForeground(new Color(32, 105, 29));
		lblGrabarT.setText("[ F10 ] Grabar");
		lblGrabarT.setFont(new Font("SansSerif", 1, 11));
		lblGrabarT.setBounds(new Rectangle(550, 485, 100, 15));
		lblGrabarT.setForeground(new Color(32, 105, 29));
		btnFecha.setText("Fecha Emision :");
		btnFecha.setBounds(new Rectangle(10, 80, 95, 20));
		btnFecha.setBorderPainted(false);
		btnFecha.setFont(new Font("SansSerif", 0, 11));
		btnFecha.setHorizontalAlignment(SwingConstants.LEFT);
		btnFecha.setMnemonic('F');
		btnFecha.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		btnFecha.setFocusPainted(false);
		btnFecha.setRequestFocusEnabled(false);
		btnFecha.setDefaultCapable(false);
		btnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFecha_actionPerformed(e);
			}
		});
		pnlPieRelacionProdGuiaIng.setBounds(new Rectangle(15, 435, 720, 25));
		pnlPieRelacionProdGuiaIng.setBackground(new Color(32, 105, 29));
		pnlPieRelacionProdGuiaIng.setLayout(null);
		pnlPieRelacionProdGuiaIng.setFont(new Font("SansSerif", 0, 11));
		lblTotal.setText("0.00");
		lblTotal.setBounds(new Rectangle(645, 5, 65, 15));
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("SansSerif", 1, 11));
		lblTotal.setForeground(Color.white);
		lblTotalT.setText("Total: S/.");
		lblTotalT.setBounds(new Rectangle(565, 5, 75, 15));
		lblTotalT.setFont(new Font("SansSerif", 1, 11));
		lblTotalT.setForeground(Color.white);
		lblTotalT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEliminarT.setText("[ F4 ] Eliminar");
		lblEliminarT.setBounds(new Rectangle(130, 485, 90, 15));
		lblEliminarT.setFont(new Font("SansSerif", 1, 11));
		lblEliminarT.setForeground(new Color(32, 105, 29));
		lblTotalItemsT.setText("Total Items:");
		lblTotalItemsT.setBounds(new Rectangle(10, 5, 65, 15));
		lblTotalItemsT.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalItemsT.setFont(new Font("SansSerif", 1, 11));
		lblTotalItemsT.setForeground(Color.white);
		lblCantItems.setText("0");
		lblCantItems.setBounds(new Rectangle(75, 5, 40, 15));
		lblCantItems.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantItems.setFont(new Font("SansSerif", 1, 11));
		lblCantItems.setForeground(Color.white);
		lblImprimir.setText("[ F11 ] Imprimir");
		lblImprimir.setBounds(new Rectangle(425, 475, 110, 20));
		lblImprimir.setFont(new Font("SansSerif", 1, 11));
		lblImprimir.setForeground(new Color(32, 105, 29));
		lblImprimir.setVisible(false);
		lblOpcionesT.setText("Opciones:");
		lblOpcionesT.setBounds(new Rectangle(20, 465, 80, 15));
		lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
		txtCodLocal.setBounds(new Rectangle(490, 75, 30, 20));
		txtCodLocal.setDocument(new AtuxLengthText(3));
		txtCodLocal.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtCodLocal_keyPressed(e);
			}
		});
		txtDesLocal.setBounds(new Rectangle(530, 75, 180, 20));
		txtDesLocal.setEnabled(false);
		txtNroDocumento2.setBounds(new Rectangle(530, 110, 65, 20));
		txtNroDocumento2.setDocument(new AtuxDocumentEditor(7, AtuxDocumentEditor.NUMERIC));

		txtNroDocumento2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtNroDocumento2_keyPressed(e);
			}
		});
		lblGuion.setText("--");
		lblGuion.setBounds(new Rectangle(520, 110, 10, 15));
		lblGuion.setFont(new Font("SansSerif", 1, 16));
		lblTotaCantEnteraT.setText("Total Cantidad:");
		lblTotaCantEnteraT.setBounds(new Rectangle(135, 5, 85, 15));
		lblTotaCantEnteraT.setFont(new Font("SansSerif", 1, 11));
		lblTotaCantEnteraT.setForeground(Color.white);
		lblTotaCantEntera.setText("0");
		lblTotaCantEntera.setBounds(new Rectangle(220, 5, 40, 15));
		lblTotaCantEntera.setFont(new Font("SansSerif", 1, 11));
		lblTotaCantEntera.setForeground(Color.white);
		lblTotaCantEntera.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotaCantFraccT.setText("T. Cant. Fracc.:");
		lblTotaCantFraccT.setBounds(new Rectangle(280, 5, 85, 15));
		lblTotaCantFraccT.setForeground(Color.white);
		lblTotaCantFraccT.setFont(new Font("SansSerif", 1, 11));
		lblTotaCantFraccT.setVisible(false);
		lblTotaCantFracc.setText("0");
		lblTotaCantFracc.setBounds(new Rectangle(360, 5, 40, 15));
		lblTotaCantFracc.setForeground(Color.white);
		lblTotaCantFracc.setFont(new Font("SansSerif", 1, 11));
		lblTotaCantFracc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotaCantFracc.setVisible(false);
		lblAnularGuiaT.setText("[ F3 ] Anular Guía");
		lblAnularGuiaT.setBounds(new Rectangle(25, 485, 95, 15));
		lblAnularGuiaT.setForeground(new Color(32, 105, 29));
		lblAnularGuiaT.setFont(new Font("SansSerif", 1, 11));
		lblAnularGuiaT.setVisible(false);
		lblGuardarT.setText("[ F9 ] Guardar Archivo");
		lblGuardarT.setFont(new Font("SansSerif", 1, 11));
		lblGuardarT.setBounds(new Rectangle(415, 480, 130, 25));
		lblGuardarT.setForeground(new Color(32, 105, 29));
		// INICIO ID= 01
		lblMontoMaximo.setBounds(new Rectangle(195, 20, 510, 20));
		// FIN ID= 01
		lblMontoMaximo.setFont(new Font("Times New Roman", 1, 12));
		lblEnter.setText("[ ENTER ] Seleccionar");
		lblEnter.setBounds(new Rectangle(570, 5, 125, 15));
		lblEnter.setFont(new Font("SansSerif", 1, 11));
		lblEnter.setForeground(Color.white);
		cmbTypeOrden.setBounds(new Rectangle(489, 45, 186, 20));
		cmbTypeOrden.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbTypeOrdenItemStateChanged(evt);
			}
		});

		lblTypeOrden.setText("Tipo Orden :");
		lblTypeOrden.setBounds(new Rectangle(375, 45, 105, 20));
		lblTypeOrden.setFont(new Font("SansSerif", 0, 11));
		pnlCabeceraGuia.add(lblTypeOrden, null);
		pnlCabeceraGuia.add(cmbTypeOrden, null);

		pnlCabeceraGuia.add(lblMontoMaximo, null);
		pnlCabeceraGuia.add(lblGuion, null);
		pnlCabeceraGuia.add(txtNroDocumento2, null);
		pnlCabeceraGuia.add(txtDesLocal, null);
		pnlCabeceraGuia.add(txtCodLocal, null);
		pnlCabeceraGuia.add(btnFecha, null);
		pnlCabeceraGuia.add(txtOtroProveedor, null);
		pnlCabeceraGuia.add(lblOtroProveedor, null);
		pnlCabeceraGuia.add(lblNroDocumentoT, null);
		pnlCabeceraGuia.add(cmbDocumento, null);
		pnlCabeceraGuia.add(txtNroDocumento, null);
		pnlCabeceraGuia.add(lblTipoDocumentoT, null);
		pnlCabeceraGuia.add(lblNombreLocal, null);
		pnlCabeceraGuia.add(txtFechaEmision, null);
		pnlCabeceraGuia.add(lblLocalProv, null);
		pnlCabeceraGuia.add(lblLocalT, null);
		pnlPieRelacionProdGuiaIng.add(lblTotaCantFracc, null);
		pnlPieRelacionProdGuiaIng.add(lblTotaCantFraccT, null);
		pnlPieRelacionProdGuiaIng.add(lblTotaCantEntera, null);
		pnlPieRelacionProdGuiaIng.add(lblTotaCantEnteraT, null);
		pnlPieRelacionProdGuiaIng.add(lblCantItems, null);
		pnlPieRelacionProdGuiaIng.add(lblTotalItemsT, null);
		pnlPieRelacionProdGuiaIng.add(lblTotalT, null);
		pnlPieRelacionProdGuiaIng.add(lblTotal, null);
		this.getContentPane().add(lblGuardarT, null);
		this.getContentPane().add(lblAnularGuiaT, null);
		this.getContentPane().add(lblOpcionesT, null);
		this.getContentPane().add(lblImprimir, null);
		this.getContentPane().add(lblEliminarT, null);
		this.getContentPane().add(pnlPieRelacionProdGuiaIng, null);
		this.getContentPane().add(lblGrabarT, null);
		this.getContentPane().add(lblSalirT, null);
		this.getContentPane().add(lblAgregarT, null);
		pnlCabeceraDetalle.add(lblEnter, null);
		pnlCabeceraDetalle.add(btnDetalleGuia, null);
		this.getContentPane().add(pnlCabeceraDetalle, null);
		srcGuiaIngreso.getViewport().add(tblGuiaIngreso, null);
		this.getContentPane().add(srcGuiaIngreso, null);
		this.getContentPane().add(pnlCabeceraGuia, null);
	}

	private void cmbTypeOrdenItemStateChanged(ItemEvent evt) {
		if(evt.getStateChange() == ItemEvent.SELECTED)
		{
			if(cmbTypeOrden.getSelectedIndex()==0){
				try {
					initInfoMontos(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else{
				try {
					initInfoMontos(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void cmbTypeOrden_keyPressed(KeyEvent e) {

	}

	// Motodo que carga los datos Iniciales Estandar
	void cargaDatosIniciales() throws Exception {
		String fechaActual = "";
		String condicion = "";
		String nombreCompania = "";
		lblNombreLocal.setText(AtuxVariables.vCodigoLocal + " - " + AtuxVariables.vDescripcionCortaLocal);
		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
			muestraPantallaLocal();
			condicion = "CO_LOCAL <> '" + AtuxVariables.vCodigoLocal + "' AND " + "TI_LOCAL = 'V' ";
			muestraComboLocales(condicion);
			iniciaTablaFraccion();
			VariablesInventario.vTipoLocal = "V";
		}

		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ) {
			muestraPantallaMatriz();
			condicion = "TI_LOCAL = 'A'";
			muestraComboLocales(condicion);
			iniciaTablaEnteros();
			VariablesInventario.vTipoLocal = "A";
		}

		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
			muestraPantallaCotizacion();
			iniciaTablaEnteros();
			initInfoMontos(true);
			try {
				porcentajeVariacion = AtuxUtility.getDecimalNumber(AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_NUMERICO",
						"CO_COMPANIA = '"+AtuxVariables.vCodigoCompania+"' AND CO_LOCAL = '00099' AND CO_VARIABLE = 'porcentaje.variacion.cotizacion'"));
				porcentajeVariacion = porcentajeVariacion / 100;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Problemas al obtener el Porcentaje de Variacion de Precios", "Cotizaciones por Competencia", JOptionPane.WARNING_MESSAGE);
				closeWindow(false);
			}
		}

		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_PROVEEDOR) {
			muestraPantallaProveedor();
			iniciaTablaEnteros();
		}

		AtuxLoadCVL.loadCVL(cmbDocumento, "LGTR_TIPO_DOCUMENTO", "TI_DOCUMENTO", "DE_CORTA_TIPO_DOCUMENTO", true, "CO_COMPANIA = '"+AtuxVariables.vCodigoCompania+"' AND ES_TIPO_DOCUMENTO='A' AND IN_DOCUMENTO_INGRESO='S'");

		AtuxLoadCVL.setSelectedValueInComboBox(cmbDocumento, "LGTR_TIPO_DOCUMENTO", "05");
		try {
			fechaActual = AtuxSearch.getFechaHoraBD(1);
			txtFechaEmision.setText(fechaActual);
			txtFechaEmision.selectAll();
			nombreCompania = DBInventario.getNameCompania();
		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error en  al obtener Hiora del sistema- " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}

		cmbTypeOrden.addItem("Orden por cotizacion");
		cmbTypeOrden.addItem("Registra compras");
	}

	// INICIO ID= 01
	private void initInfoMontos(boolean vConsiderarMonto) throws Exception {
		montoConsumido = DBInventario.obtenerMontoConsumido();
		montoMaximo = DBInventario.obtenerMontoMaximoCotizacion();
		double saldo = montoMaximo - montoConsumido;

		if(vConsiderarMonto)
			lblMontoMaximo.setText("MONTO DISPONIBLE :S/" + AtuxUtility.formatNumber(montoMaximo) + "    SALDO :S/ " + AtuxUtility.formatNumber(saldo));
		else
			lblMontoMaximo.setText("");
	}

	// FIN ID= 01

	void iniciaTablaEnteros() {
		tableModelGuiaIngreso = new AtuxTableModel(ConstantsInventario.columnsGuiaIngresoEnteros, ConstantsInventario.defaultValuesGuiaIngresoEnteros, 0);
		AtuxUtility.initSimpleList(tblGuiaIngreso, tableModelGuiaIngreso, ConstantsInventario.columnsGuiaIngresoEnteros);

		tableModelListaProducto = new AtuxTableModel(ConstantsInventario.columnsProdListInv, ConstantsInventario.defaultValuesProdListInv, 0);
		AtuxUtility.initSelectList(tblListaProducto, tableModelListaProducto, ConstantsInventario.columnsProdListInv);
	}

	void iniciaTablaFraccion() {
		tableModelGuiaIngreso = new AtuxTableModel(ConstantsInventario.columnsGuiaIngresoFraccion, ConstantsInventario.defaultValuesGuiaIngresoFraccion, 0);
		AtuxUtility.initSimpleList(tblGuiaIngreso, tableModelGuiaIngreso, ConstantsInventario.columnsGuiaIngresoFraccion);

		tableModelListaProducto = new AtuxTableModel(ConstantsInventario.columnsProdListInv, ConstantsInventario.defaultValuesProdListInv, 0);
		AtuxUtility.initSelectList(tblListaProducto, tableModelListaProducto, ConstantsInventario.columnsProdListInv);
	}

	void muestraComboLocales(String pcondicion) {
		/*
		 * AtuxLoadCVL.loadCVL(cmbProveedor,"VTTM_LOCAL","CO_LOCAL","DE_CORTA_LOCAL",true, "ES_LOCAL='A' AND CO_COMPANIA='" + AtuxVariables.vCodigoCompania +"' AND " + pcondicion);
		 */
	}

	void muestraPantallaLocal() {
		lblOtroProveedor.setVisible(false);
		txtOtroProveedor.setVisible(false);
	}

	void muestraPantallaMatriz() {
		lblOtroProveedor.setVisible(false);
		txtOtroProveedor.setVisible(false);
	}

	void muestraPantallaCotizacion() {
		AtuxLoadCVL.setSelectedValueInComboBox(cmbDocumento, "LGTR_TIPO_DOCUMENTO", "01");
		cambiaControles();
	}

	void muestraPantallaProveedor() {
		cambiaControles();
	}

	void cambiaControles() {
		lblLocalProv.setVisible(false);
		// cmbProveedor.setVisible(false);
		txtCodLocal.setVisible(false);
		txtDesLocal.setVisible(false);
		lblOtroProveedor.setVisible(true);
		txtOtroProveedor.setVisible(true);
		lblOtroProveedor.setBounds(lblLocalProv.getBounds());
		// txtOtroProveedor.setBounds(cmbProveedor.getBounds());
		txtOtroProveedor.setBounds(txtCodLocal.getBounds());
		txtOtroProveedor.setSize(185, 20);
	}

	void cmbProveedor_keyPressed(KeyEvent e) {
		if (txtCodLocal.isEnabled()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				cmbDocumento.requestFocus();
			}

			checkKeyPress(e);
		}
	}

	void cmbDocumento_keyPressed(KeyEvent e) {
		if (cmbDocumento.isEnabled()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				txtNroDocumento.requestFocus();
			}

			checkKeyPress(e);
		}
	}

	void btnDetalleGuia_actionPerformed(ActionEvent e) {
		if (tableModelGuiaIngreso.getRowCount() > 0) {
			AtuxGridUtils.showCell(tblGuiaIngreso, 0, 0);
			tblGuiaIngreso.requestFocus();
		}
	}

	void cargaListaProductos() {
		try {
			DBInventario.loadInvListaProductos(tableModelListaProducto);
		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error al obtener la relacion de productos - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	void muestraListaProductos() {
		if (lblAgregarT.isVisible()) {
			if (tableModelListaProducto.data.size() == 0) {
				cargaListaProductos();
			}

			DlgGuiaIngresoListaProd dlgGuiaIngresoListaProd = new DlgGuiaIngresoListaProd(parentFrame, "Relación de Productos de Guía Ingreso", true);
			dlgGuiaIngresoListaProd.setPorcentajeVariacion(porcentajeVariacion);

			ArrayList listaInicial = (ArrayList) tableModelGuiaIngreso.data.clone();
			dlgGuiaIngresoListaProd.setListaProducto(tableModelListaProducto.data);
			for (int i = 0; i < dlgGuiaIngresoListaProd.tblRelProductosGuiaIng.getRowCount(); i++) {
				dlgGuiaIngresoListaProd.tblRelProductosGuiaIng.setValueAt(new Boolean(false), i, 0);
			}
			dlgGuiaIngresoListaProd.setListProdSel(tableModelGuiaIngreso.data);

			dlgGuiaIngresoListaProd.setVisible(true);
			if (!dlgGuiaIngresoListaProd.vAceptar) {
				tableModelGuiaIngreso.data = listaInicial;
			} else {
				listaInicial = new ArrayList();
			}

			tableModelGuiaIngreso.fireTableDataChanged();
			tblGuiaIngreso.repaint();
			tblGuiaIngreso.requestFocus();
			if (tableModelGuiaIngreso.getRowCount() > 0) {
				tblGuiaIngreso.requestFocus();
				AtuxGridUtils.showCell(tblGuiaIngreso, 0, 0);
				calculaTotal();
			} else {
				txtNroDocumento2.requestFocus();
			}
		}
	}

	void eliminarProducto() {
		if (tblGuiaIngreso.getRowCount() > 0 && lblEliminarT.isVisible()) {
			if (JOptionPane.showConfirmDialog(this, "Seguro de Eliminar Producto?", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				int row = tblGuiaIngreso.getSelectedRow();
				if (row >= 0) {
					tableModelGuiaIngreso.deleteRow(row);
					tblGuiaIngreso.repaint();
					if (tableModelGuiaIngreso.getRowCount() > 0) {
						if (row > 0) {
							row--;
						}
						AtuxGridUtils.showCell(tblGuiaIngreso, row, 0);
						tblGuiaIngreso.requestFocus();
					}
				}
			}
			calculaTotal();
		}
	}

	void tblGuiaIngreso_keyPressed(KeyEvent e) {
		if (!lblImprimir.isVisible()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (tblGuiaIngreso.getRowCount() > 0) {
					e.consume();
					ingresaCantPedida(tableModelGuiaIngreso, tblGuiaIngreso, parentFrame);
				}
			} else {

				checkKeyPress(e);
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_F9) {
				guardarArchivo();
			} else if (e.getKeyCode() == KeyEvent.VK_F11) {
				imprimeGuiaIngreso();
			} else if (e.getKeyCode() == KeyEvent.VK_F3) {
				anularGuia();
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				closeWindow(false);
			}
		}

	}

	void guardarArchivo() {

		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
			AtuxUtility.saveFile(parentFrame, ConstantsInventario.columnsGuiaIngresoFraccion, tblGuiaIngreso, giAnchoTextoFraccion);
			// INICIO ID= 01
		} else if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ
				|| VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
			AtuxUtility.saveFile(parentFrame, ConstantsInventario.columnsGuiaIngresoEnteros, tblGuiaIngreso, giAnchoTextoEntero);

		} // FIN ID= 01
		else if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_PROVEEDOR) {
			AtuxUtility.saveFile(parentFrame, ConstantsInventario.columnsGuiaIngresoEnteros, tblGuiaIngreso, giAnchoTextoEntero);
		}
	}

	void txtFechaEmision_keyPressed(KeyEvent e) {
		if (txtFechaEmision.isEnabled()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (!AtuxUtility.isFechaValida((Object) this, txtFechaEmision.getText().trim(), "El dato ingresado en el campo Fecha no es VALIDO. Verifique !!!")) {
					AtuxUtility.moveFocus(txtFechaEmision);
					return;
				}

				if (txtCodLocal.isVisible()) {
					txtCodLocal.selectAll();
					txtDesLocal.setText("");
					txtCodLocal.requestFocus();
				} else {
					txtOtroProveedor.requestFocus();
				}
			}

			checkKeyPress(e);
		}
	}

	void txtOtroProveedor_keyPressed(KeyEvent e) {
		if (!lblImprimir.isVisible()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				cmbDocumento.requestFocus();
			}

			checkKeyPress(e);
		}
	}

	void txtNroDocumento2_keyPressed(KeyEvent e) {
		if (txtNroDocumento2.isEnabled()) {
			longitudTexto = txtNroDocumento2.getText().length();
			String strNumSerie = txtNroDocumento2.getText().trim();
			String codLocal = txtCodLocal.getText().trim();
			String tiDocumento = AtuxLoadCVL.getCVLCode("LGTR_TIPO_DOCUMENTO", cmbDocumento.getSelectedIndex());

			if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_F2) {
				if (longitudTexto < 7) {
					for (int k = 0; k < 7 - longitudTexto; k++) {
						strNumSerie = '0' + strNumSerie;
					}
				}
				txtNroDocumento2.setText(strNumSerie);
				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL
						|| VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ) {
					try {
						int indicador = 0;
						// modificado el 02-01-2007 GAZABACHE para que la validacion tenga en cuenta el origuen de la guia
						indicador = DBInventario.validaNumeroDocumento(txtNroDocumento.getText().trim() + txtNroDocumento2.getText().trim(), codLocal, tiDocumento);
						if (indicador == 1) {
							JOptionPane.showMessageDialog(this, "El Número de Documento ya existe. Verifique por favor!!!", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
							txtNroDocumento.selectAll();
							txtNroDocumento.requestFocus();
							return;
						}
					} catch (SQLException sqlerr) {
						AtuxUtility.showMessage(this, "Error al obtener el Número de Documento" + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
						sqlerr.printStackTrace();
					}
				}
				// muestraListaProductos();
			}
			checkKeyPress(e);
		}
	}

	void txtNroDocumento_keyPressed(KeyEvent e) {
		if (txtNroDocumento.isEnabled()) {
			longitudTexto = txtNroDocumento.getText().length();
			String strNumSerie = txtNroDocumento.getText().trim();

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (longitudTexto < 3) {
					for (int k = 0; k < 3 - longitudTexto; k++) {
						strNumSerie = '0' + strNumSerie;
					}
				}
				txtNroDocumento.setText(strNumSerie);
				txtNroDocumento2.requestFocus();
				txtNroDocumento2.selectAll();
			}
			checkKeyPress(e);
		}
	}

	// Metodo que valida la tecla presionada
	void checkKeyPress(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_F2:
			e.consume();
			muestraListaProductos();
			break;
		case KeyEvent.VK_F4:
			eliminarProducto();
			break;
		case KeyEvent.VK_F9:
			if (tblGuiaIngreso.getRowCount() > 0) {
				guardarArchivo();
			}
			break;
		case KeyEvent.VK_F10:
			e.consume();
			aceptaDatos();
			break;
		case KeyEvent.VK_ESCAPE:
			closeWindow(false);
			break;
		}
	}

	void anularGuia() {
		if (lblAnularGuiaT.isVisible()) {
			// verificacion que los productos no esten siendo inventariados
			try {
				if (((String) DBInventario.verificaAnulacionGuiaIngTomaInv(VariablesInventario.vNumeroGuiaIngreso)).trim().equalsIgnoreCase("S")) {
					JOptionPane.showMessageDialog(this, "No se puede anular la Guía de ingreso porque existen productos que estan siendo inventariados", "Mensaje del Sistema",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (SQLException sqlerr) {
				AtuxUtility.showMessage(this, "Error al verificar productos del detalle de la Guía de Ingreso o " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
				sqlerr.printStackTrace();
			}
			if (!(JOptionPane.showConfirmDialog(this, "Esta seguro de anular la Guía de Ingreso Manual?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)) {
				return;
			}

			try {
				if ((((String) DBInventario.verificacionAnulacionGuia(strNumRecepProd)).trim()).equalsIgnoreCase("S")) {
					DBInventario.anulaGuiaIngreso(strNumRecepProd, AtuxLoadCVL.getCVLCode("LGTR_TIPO_DOCUMENTO", cmbDocumento.getSelectedIndex()),
							ConstantsInventario.MOTIVO_KARDEX_ANULA_GUIA_ENTRADA, ConstantsInventario.GRUPO_MOTIVO_KARDEX);
					AtuxUtility.aceptarTransaccion();
					JOptionPane.showMessageDialog(this, "La Guía de Ingreso ha sido anulada satisfactoriamente!!\n            Número de Referencia: " + VariablesInventario.vNumeroGuiaIngreso,
							"Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
					closeWindow(true);
				} else {
					AtuxUtility.liberarTransaccion();
					JOptionPane.showMessageDialog(this, "No es posible anular la Guía de Ingreso por falta de stock", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (SQLException sqlerr) {
				AtuxUtility.liberarTransaccion();
				if (sqlerr.toString().indexOf("CHK_VALIDA_STK_DISP_COMPR") == -1) {
					AtuxUtility.showMessage(this, "Error al anular Guía de ingreso: " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
				} else {
					AtuxUtility.showMessage(this, "No es posible anular la Guía de Ingreso porque " + "no se cuenta con el stock suficiente en uno o mos productos " + sqlerr.getErrorCode() + "\n"
							+ sqlerr.toString(), null);
				}
				sqlerr.printStackTrace();
				return;
			}
		}
	}

	void btnFecha_actionPerformed(ActionEvent e) {
		if (txtFechaEmision.isEnabled()) {
			txtFechaEmision.requestFocus();
		}
	}

	void aceptaDatos() {
		// Si entro por InkVenta_Matriz no debe realizar cambio alguno.
		if (AtuxVariables.vInkVenta_Matriz) {
			JOptionPane.showMessageDialog(this, "No es posible realizar esta operacion en Matriz", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (lblGrabarT.isVisible()) {
			if (!AtuxUtility.isFechaValida((Object) this, txtFechaEmision.getText().trim(), "El dato ingresado en el campo Fecha no es VALIDO. Verifique !!!")) {
				AtuxUtility.moveFocus(txtFechaEmision);
				return;
			}

			if (txtCodLocal.isVisible()) {
				if (txtDesLocal.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(this, "Seleccione Local...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
					txtCodLocal.requestFocus();
					return;
				}
			}

			if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION
					|| VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_PROVEEDOR) {
				if (txtOtroProveedor.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Ingrese Nombre de Proveedor...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
					txtOtroProveedor.requestFocus();
					return;
				}
			}

			if (txtNroDocumento.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Ingrese Número de Documento ...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				txtNroDocumento.requestFocus();
				return;
			}

			if (tblGuiaIngreso.getRowCount() == 0) {
				tblGuiaIngreso.requestFocus();
				JOptionPane.showMessageDialog(this, "No se han selecionado Productos ...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if(cmbTypeOrden.getSelectedIndex()==0) {
				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
					double saldo = montoMaximo - montoConsumido;

					if (totalCompra > saldo) {
						JOptionPane.showMessageDialog(this, "El monto total de compra supera el saldo disponible", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
						return;
					}

				}
			}
			else{

			}
			grabaDatos();
		}
	}

	void calculaTotal() {
		totalCompra = 0.00;
		cantItems = 0;
		dblTotalCantEntera = 0;
		dblTotalCantFracc = 0;

		if (tableModelGuiaIngreso.getRowCount() > 0) {
			for (int i = 0; i < tblGuiaIngreso.getRowCount(); i++) {
				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
					String subTotal = ("" + tblGuiaIngreso.getValueAt(i, 7)).trim();
					subTotal = AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(subTotal), 2);
					tblGuiaIngreso.setValueAt("" + subTotal, i, 7);
					totalCompra = totalCompra + AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 7));
					dblTotalCantEntera += AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 4));
					dblTotalCantFracc += AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 5));
					// INICIO ID= 01
				} else if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION
						|| VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ) {
					// FIN ID= 01
					String subTotal = String.valueOf(tblGuiaIngreso.getValueAt(i, 5)).trim();
					subTotal = AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(subTotal), 2);
					tblGuiaIngreso.setValueAt(String.valueOf(subTotal), i, 5);
					totalCompra = totalCompra + AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 5));
					dblTotalCantEntera += AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 3));
				} else {
					String subTotal = String.valueOf(tblGuiaIngreso.getValueAt(i, 5)).trim();
					subTotal = AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(subTotal), 2);
					tblGuiaIngreso.setValueAt(String.valueOf(subTotal), i, 5);
					totalCompra = totalCompra + AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 5));
					dblTotalCantEntera += AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 4));
					dblTotalCantFracc += AtuxUtility.getDecimalNumber((String) tblGuiaIngreso.getValueAt(i, 5));
				}
			}
			cantItems = tableModelGuiaIngreso.getRowCount();
		}
		lblTotal.setText(AtuxUtility.formatNumber(totalCompra, "##,##0.00"));
		lblCantItems.setText(AtuxUtility.formatNumber(cantItems, "###,##0"));
		lblTotaCantEntera.setText(AtuxUtility.formatNumber(dblTotalCantEntera, "###,##0"));
		lblTotaCantFracc.setText(AtuxUtility.formatNumber(dblTotalCantFracc, "###,##0"));
	}

	void grabaDatos() {
		int valorFraccion = 0;
		int cantMovimientos = 0;
		int cantEntera = 0;
		int cantFraccion = 0;

		try {
			if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Grabar la Guía de Ingreso ?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				String codLocal = "";
				if (txtCodLocal.isVisible()) {
					codLocal = txtCodLocal.getText().trim();
				}

				// S = orden por cotizacion , N = orden normal 
				DBInventario.grabarCabeceraGuiaIngreso(AtuxLoadCVL.getCVLCode("LGTR_TIPO_DOCUMENTO", cmbDocumento.getSelectedIndex()), txtNroDocumento.getText().trim()
						+ txtNroDocumento2.getText().trim(), txtOtroProveedor.getText().trim(), txtFechaEmision.getText().trim(), cantItems, codLocal, totalCompra,
						(cmbTypeOrden.getSelectedIndex() == 0 ? "C" : "R"));

				for (int i = 0; i < tblGuiaIngreso.getRowCount(); i++) {
					VariablesInventario.vCodigoProducto = (String) tblGuiaIngreso.getValueAt(i, 0);
					valorFraccion = DBInventario.getValorFraccion();

					if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
						cantEntera = Integer.parseInt((String) tblGuiaIngreso.getValueAt(i, 4));
						cantFraccion = Integer.parseInt((String) tblGuiaIngreso.getValueAt(i, 5));

						if (valorFraccion > 0) {
							cantMovimientos = (cantEntera * valorFraccion) + cantFraccion;
						} else {
							cantMovimientos = cantEntera;
						}

						DBInventario.grabarDetalleGuiaIngreso(VariablesInventario.vNumeroGuiaIngreso, i + 1, VariablesInventario.vCodigoProducto, cantEntera, cantFraccion,
								(String) tblGuiaIngreso.getValueAt(i, 8), (String) tblGuiaIngreso.getValueAt(i, 9), (String) tblGuiaIngreso.getValueAt(i, 6), (String) tblGuiaIngreso.getValueAt(i, 7));
					} else {
						cantEntera = Integer.parseInt((String) tblGuiaIngreso.getValueAt(i, 3));

						if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
							cantMovimientos = cantEntera;
							cantFraccion = 0;
							if (valorFraccion > 0) {
								cantFraccion = cantEntera % valorFraccion;
								cantEntera = AtuxUtility.trunc(cantEntera / valorFraccion);
							}
						} else {
							if (valorFraccion > 0) {
								cantMovimientos = (cantEntera * valorFraccion) + cantFraccion;
							} else {
								cantMovimientos = cantEntera;
							}
						}

						DBInventario.grabarDetalleGuiaIngreso(VariablesInventario.vNumeroGuiaIngreso, i + 1, VariablesInventario.vCodigoProducto, cantEntera, cantFraccion,
								(String) tblGuiaIngreso.getValueAt(i, 6), (String) tblGuiaIngreso.getValueAt(i, 7), (String) tblGuiaIngreso.getValueAt(i, 4), (String) tblGuiaIngreso.getValueAt(i, 5));

						// INICIO ID= 01
						// todo RAC si un local es sap o no validar al instante
						if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
							DBInventario.grabarKardexGuiaIngresoCosteo(VariablesInventario.vCodigoProducto, cantMovimientos,
									AtuxLoadCVL.getCVLCode("LGTR_TIPO_DOCUMENTO", cmbDocumento.getSelectedIndex()), txtNroDocumento.getText().trim() + txtNroDocumento2.getText().trim(),
									(String) tblGuiaIngreso.getValueAt(i, 4));
						}
						// FIN ID= 01
					}

					if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL != ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
						DBInventario.grabarKardexGuiaIngreso(VariablesInventario.vCodigoProducto, cantMovimientos, AtuxLoadCVL.getCVLCode("LGTR_TIPO_DOCUMENTO", cmbDocumento.getSelectedIndex()),
								txtNroDocumento.getText().trim() + txtNroDocumento2.getText().trim());

					}
				}

				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
					if (AtuxVariables.vModoSAPActivado) {
						if (AtuxVariables.vLocalImplementaSAP) {
							// String codInterfaceLogistica = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO",
							// "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.cotizacion.logistica'");
							String codInterfaceAbastecimiento = AtuxDBUtility.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO",
									"CO_COMPANIA = '"+AtuxVariables.vCodigoCompania+"' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.cotizacion.abastecimiento'");

							// GeneraArchivo generaLogistica = new GeneraArchivo();
							// generaLogistica.setCodInterface(codInterfaceLogistica);
							// generaLogistica.setProcCabecera("COPQ_MM_AB_PEDIDO.GET_CAB_ENTRADA(?,?,?,?)");
							// generaLogistica.setParamCabecera(AtuxVariables.vCodigoCompania + ";" + AtuxVariables.vCodigoLocal + ";" + VariablesInventario.vNumeroGuiaIngreso + ";NAME_FILE");
							// generaLogistica.setProcDetalle("COPQ_MM_AB_PEDIDO.GET_DET_ENTRADA(?,?,?)");
							// generaLogistica.setParamDetalle(AtuxVariables.vCodigoCompania + ";" + AtuxVariables.vCodigoLocal + ";" + VariablesInventario.vNumeroGuiaIngreso);
							// generaLogistica.setWithCommit(false);
							// generaLogistica.generar();

							GeneraArchivo generaAbastecimiento = new GeneraArchivo();
							generaAbastecimiento.setCodInterface(codInterfaceAbastecimiento);
							generaAbastecimiento.setProcCabecera("COPQ_MM_AB_COTIZACION.GET_CAB_ABASTO_05(?,?,?,?)");
							generaAbastecimiento.setParamCabecera(AtuxVariables.vCodigoCompania + ";" + AtuxVariables.vCodigoLocal + ";" + VariablesInventario.vNumeroGuiaIngreso + ";NAME_FILE");
							generaAbastecimiento.setProcDetalle("COPQ_MM_AB_COTIZACION.GET_DET_ABASTO_05(?,?,?)");
							generaAbastecimiento.setParamDetalle(AtuxVariables.vCodigoCompania + ";" + AtuxVariables.vCodigoLocal + ";" + VariablesInventario.vNumeroGuiaIngreso);
							generaAbastecimiento.setWithCommit(false);
							generaAbastecimiento.generar();
						}
					}
				}

				AtuxUtility.aceptarTransaccion();
				JOptionPane.showMessageDialog(this, "La Guía de Ingreso se grabo correctamente!!!\n        Número de Referencia: " + VariablesInventario.vNumeroGuiaIngreso, "Mensaje del sistema",
						JOptionPane.INFORMATION_MESSAGE);
				imprimeGuiaIngreso();
				this.setVisible(false);
				this.dispose();
			}
		} catch (Exception sqlerr) {
			AtuxUtility.liberarTransaccion();
			AtuxUtility.showMessage(this, "Error al grabar guia ingreso - " + "\n" + sqlerr.getMessage(), null);
			sqlerr.printStackTrace();
		}
	}

	void imprimeGuiaIngreso() {
		if (JOptionPane.showConfirmDialog(this, "Desea Imprimir Guía de Ingreso ?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			String vOrigen = "";
			AtuxPrintService vPrint = new AtuxPrintService(65, AtuxVariables.vImpresoraReporte, true);
			// Inicio del Servicio de Impresion
			if (!vPrint.startPrintService()) {
				JOptionPane.showMessageDialog(this, "No se pudo determinar la existencia de la Impresora. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				String fechaActual = AtuxSearch.getFechaHoraBD(1);
				// Seteando el inicio de la Cabecera del Reporte.
				vPrint.setStartHeader();
				vPrint.activateCondensed();
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Local: " + AtuxVariables.vDescripcionCortaLocal, 20) + AtuxPRNUtility.llenarBlancos(70) + "Fecha: " + fechaActual, true);
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Dpto. de Informatica", 20), true);
				vPrint.printBold(AtuxPRNUtility.llenarBlancos(48) + "REPORTE  DE INGRESO MANUAL DE MERCADERIA", true);
				vPrint.printLine("", true);
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Local Destino  : " + lblNombreLocal.getText().toUpperCase(), 50), true);

				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL
						|| VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ) {
					String codLocal = txtCodLocal.getText().trim();
					vOrigen = codLocal + " - " + txtDesLocal.getText().trim();
					vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Local Origen   : " + vOrigen, 50), true);
				} else {
					vOrigen = txtOtroProveedor.getText().toString();
					vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Proveedor      : " + vOrigen, 80), true);
				}
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Tipo Documento : " + cmbDocumento.getSelectedItem().toString(), 50), true);
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("No. Documento  : " + txtNroDocumento.getText().trim() + " - " + txtNroDocumento2.getText().trim(), 45), true);
				vPrint.printBold(AtuxPRNUtility.alinearIzquierda("Fecha Documento: " + txtFechaEmision.getText(), 30), true);
				vPrint.printLine("", true);

				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {

					vPrint.printBold("Codigo Descripcion                   Und.Entera      Und.Fraccion    Cant.Ent.  Cant.Fracc.   Prec.Unit.    Sub.Total     Fecha Venc.", true);
					vPrint.printLine("======================================================================================================================================", true);
				} else {
					vPrint.printBold("Codigo Descripcion                   Unidad            Cant.     Prec.Unit.    Sub.Total     Fecha Venc.", true);
					vPrint.printLine("======================================================================================================================================", true);
				}

				// Seteando el final de la Cabecera del Reporte.
				//vPrint.deactivateCondensed();
				vPrint.setEndHeader();

				// Imprimiendo el Cuerpo del Reporte
				if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
					for (int i = 0; i < tblGuiaIngreso.getRowCount(); i++) {
						vPrint.printCondensed(
								AtuxPRNUtility.alinearIzquierda((String) tblGuiaIngreso.getValueAt(i, 0), 7) + "-"
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 1)).trim(), 25) + "    "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 2)).trim(), 15) + "  "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 3)).trim(), 15) + "  "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 4)).trim(), 5) + "     "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 5)).trim(), 5) + "      "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 6)).trim(), 8) + "   "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 7)).trim(), 13) + "      "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 8)).trim(), 11), true);
					}

				} else {
					for (int i = 0; i < tblGuiaIngreso.getRowCount(); i++) {
						vPrint.printCondensed(
								AtuxPRNUtility.alinearIzquierda((String) tblGuiaIngreso.getValueAt(i, 0), 7) + "-"
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 1)).trim(), 25) + "   "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 2)).trim(), 19) + "  "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 3)).trim(), 5) + "  "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 4)).trim(), 7) + "     "
										+ AtuxPRNUtility.alinearDerecha(((String) tblGuiaIngreso.getValueAt(i, 5)).trim(), 10) + "        "
										+ AtuxPRNUtility.alinearIzquierda(((String) tblGuiaIngreso.getValueAt(i, 6)).trim(), 11), true);
					}
				}

				vPrint.activateBold();
				vPrint.printCondensed("======================================================================================================================================", true);
				vPrint.printCondensed(
						"Total Items: " + lblCantItems.getText() + AtuxPRNUtility.llenarBlancos(75) + "Valor Total S/. "
								+ AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(lblTotal.getText().trim()), "####0.00"), true);
				vPrint.printLine("", true);
				vPrint.printLine("", true);
				vPrint.printLine("", true);
				vPrint.printLine("", true);
				vPrint.printCondensed(AtuxPRNUtility.llenarBlancos(45) + "--------------------------", true);
				vPrint.printCondensed(AtuxPRNUtility.llenarBlancos(45) + "   Vo.Bo. Ingreso Manual ", true);
				vPrint.deactivateBold();
				vPrint.deactivateCondensed();
				// Finalizando Servicio de Impresion.
				vPrint.endPrintService();
			} catch (SQLException sqlErr) {
				sqlErr.printStackTrace();
			}
		}
	}

	public void ingresaCantPedida(AtuxTableModel tableModel, JTable table, Frame parentFrame) {
		int row = table.getSelectedRow();
		String vCodigo = (String) table.getValueAt(row, 0);
		VariablesInventario.vCodigoProducto = vCodigo;
		String vDescripcion = (String) table.getValueAt(row, 1);
		String vEntero = "";
		String vFraccion = "";
		String vLaboratorio = "";
		String vLinea = "";
		String vCantEntera = "";
		String vCantFraccion = "";
		String vFechaVence = "";
		int vStock = 0;
		int vValorFraccion = 0;
		int stockF = 0;
		int valorFraccion = 0;
		double vPrecioProd = 0.00;
		double vSubTotal = 0.00;
		String vPrecioUnit = "";
		ArrayList datosProducto = new ArrayList();

		try {
			datosProducto = DBInventario.obtieneDatosProducto(VariablesInventario.vCodigoProducto);
		} catch (SQLException err) {
			err.printStackTrace();
		}
		vEntero = ((String) ((ArrayList) datosProducto.get(0)).get(2)).trim();
		vFraccion = ((String) ((ArrayList) datosProducto.get(0)).get(3)).trim();

		try {
			vLaboratorio = DBInventario.getDescLaboratorio();
			vLinea = DBInventario.getLineaProducto();
			vStock = DBInventario.getStockProd();
			vPrecioProd = AtuxUtility.getDecimalNumber(DBInventario.getPrecioProducto(VariablesInventario.vCodigoProducto).trim());
		} catch (SQLException sqlErr) {
			sqlErr.printStackTrace();
		}

		DlgGuiaIngresoCantidad dlgGuiaIngresoCantidad = new DlgGuiaIngresoCantidad(parentFrame, "Modificacion de cantidad solicitada", true);
		dlgGuiaIngresoCantidad.setPorcentajeVariacion(porcentajeVariacion);
		dlgGuiaIngresoCantidad.setPrecioProducto(vPrecioProd);
		dlgGuiaIngresoCantidad.lblDescripcion.setText(VariablesInventario.vCodigoProducto + " - " + vDescripcion);
		dlgGuiaIngresoCantidad.lblSActual.setText(vEntero);
		dlgGuiaIngresoCantidad.lblLaboratorio.setText(vLaboratorio);
		dlgGuiaIngresoCantidad.lblLinea.setText(vLinea);
		dlgGuiaIngresoCantidad.lblFActual.setText(vFraccion);
		dlgGuiaIngresoCantidad.lblValorFraccion.setText("" + vValorFraccion);
		dlgGuiaIngresoCantidad.lblFraccionT.setVisible(false);
		dlgGuiaIngresoCantidad.txtCantFraccion.setVisible(false);

		if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
			vCantEntera = (String) table.getValueAt(row, 4);
			vCantFraccion = (String) table.getValueAt(row, 5);
			vFechaVence = (String) table.getValueAt(row, 8);
			vPrecioUnit = (String) table.getValueAt(row, 6);
			try {
				dlgGuiaIngresoCantidad.inProdFraccionado = DBInventario.getIndicProdFraccionado();
				if (dlgGuiaIngresoCantidad.inProdFraccionado.equalsIgnoreCase("S")) {
					valorFraccion = DBInventario.getValorFraccion();
					dlgGuiaIngresoCantidad.seteaValorFaraccion(valorFraccion);
					stockF = DBInventario.getStockFraccProd();
					dlgGuiaIngresoCantidad.lblFraccionT.setVisible(true);
					dlgGuiaIngresoCantidad.txtCantFraccion.setVisible(true);
					dlgGuiaIngresoCantidad.txtCantEntera.setText(vCantEntera);
					dlgGuiaIngresoCantidad.txtCantEntera.selectAll();
					dlgGuiaIngresoCantidad.txtCantFraccion.setText(vCantFraccion);
					dlgGuiaIngresoCantidad.txtPrecioUnitario.setText(vPrecioUnit);
					dlgGuiaIngresoCantidad.txtFechaVencimiento.setText(vFechaVence);
					dlgGuiaIngresoCantidad.lblValorFraccion.setText("" + valorFraccion);
					dlgGuiaIngresoCantidad.lblSActual.setText(vEntero);
					dlgGuiaIngresoCantidad.lblFActual.setText(vFraccion);
				} else {
					dlgGuiaIngresoCantidad.txtCantEntera.setText(vCantEntera);
					dlgGuiaIngresoCantidad.txtCantEntera.selectAll();
					dlgGuiaIngresoCantidad.txtPrecioUnitario.setText(vPrecioUnit);
					dlgGuiaIngresoCantidad.txtFechaVencimiento.setText(vFechaVence);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			vCantEntera = (String) table.getValueAt(row, 3);
			vFechaVence = (String) table.getValueAt(row, 6);
			vPrecioUnit = (String) table.getValueAt(row, 4);

			dlgGuiaIngresoCantidad.txtCantEntera.setText(vCantEntera);
			dlgGuiaIngresoCantidad.txtCantEntera.selectAll();
			dlgGuiaIngresoCantidad.txtPrecioUnitario.setText(vPrecioUnit);
			dlgGuiaIngresoCantidad.txtFechaVencimiento.setText(vFechaVence);
			dlgGuiaIngresoCantidad.lblDesFraccion.setText(((String) table.getValueAt(row, 2)).trim());
			dlgGuiaIngresoCantidad.txtLote.setText((String) table.getValueAt(row, 7));
		}

		dlgGuiaIngresoCantidad.setVisible(true);

		if (AtuxVariables.vAceptar && (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL)) {
			if (VariablesInventario.PANT_SHOW_FRACCION) {
				vSubTotal = (Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText()) * (Integer.parseInt(dlgGuiaIngresoCantidad.txtCantEntera.getText()) * valorFraccion))
						+ (Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText()) * Double.parseDouble(dlgGuiaIngresoCantidad.txtCantFraccion.getText()));
				tableModel.setValueAt(dlgGuiaIngresoCantidad.txtCantEntera.getText(), row, 4);
				tableModel.setValueAt(dlgGuiaIngresoCantidad.txtCantFraccion.getText(), row, 5);
				tableModel.setValueAt(AtuxUtility.formatNumber(Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText())), row, 6);

				tableModel.setValueAt(AtuxUtility.formatNumber(vSubTotal, "###,##0.00"), row, 7);
				tableModel.setValueAt(dlgGuiaIngresoCantidad.txtFechaVencimiento.getText(), row, 8);
				table.repaint();
			} else {
				tableModel.setValueAt(dlgGuiaIngresoCantidad.txtCantEntera.getText(), row, 4);
				tableModel.setValueAt(AtuxUtility.formatNumber(Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText())), row, 6);
				vSubTotal = (Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText()) * Integer.parseInt(dlgGuiaIngresoCantidad.txtCantEntera.getText()));
				tableModel.setValueAt(AtuxUtility.formatNumber(vSubTotal, "###,##0.00"), row, 7);
				tableModel.setValueAt(dlgGuiaIngresoCantidad.txtFechaVencimiento.getText(), row, 8);
				table.repaint();
			}
		} else if (AtuxVariables.vAceptar) {
			tableModel.setValueAt(dlgGuiaIngresoCantidad.txtCantEntera.getText(), row, 3);
			tableModel.setValueAt(dlgGuiaIngresoCantidad.txtCantEntera.getText(), row, 3);
			tableModel.setValueAt(AtuxUtility.formatNumber(Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText())), row, 4);
			vSubTotal = (Double.parseDouble(dlgGuiaIngresoCantidad.txtPrecioUnitario.getText()) * Integer.parseInt(dlgGuiaIngresoCantidad.txtCantEntera.getText()));
			tableModel.setValueAt(AtuxUtility.formatNumber(vSubTotal, "###,##0.00"), row, 5);
			tableModel.setValueAt(dlgGuiaIngresoCantidad.txtFechaVencimiento.getText(), row, 6);
			tableModel.setValueAt(dlgGuiaIngresoCantidad.txtLote.getText(), row, 7);
			table.repaint();
		}

		calculaTotal();
		table.requestFocus();
		AtuxGridUtils.showCell(table, row, 0);
	}

	void txtOtroProveedor_keyReleased(KeyEvent e) {
		txtOtroProveedor.setText(txtOtroProveedor.getText().toUpperCase());
	}

	void tblGuiaIngreso_mouseReleased(MouseEvent e) {
		if (!lblImprimir.isVisible()) {
			if (tblGuiaIngreso.getRowCount() > 0) {
				ingresaCantPedida(tableModelGuiaIngreso, tblGuiaIngreso, parentFrame);
			}
		}
	}

	void txtFechaEmision_keyReleased(KeyEvent e) {
		if (txtFechaEmision.isEnabled()) {
			AtuxUtility.dateComplete(txtFechaEmision, e);
		}
	}

	void cargaConsultaDetalle(String pNumRecepcion, String pTipo) {
		strNumRecepProd = pNumRecepcion;
		try {
			DBInventario.getDetalleIngresoManual(tableModelGuiaIngreso, pNumRecepcion, pTipo);
			calculaTotal();
		} catch (SQLException sqlerr) {
			sqlerr.printStackTrace();
			AtuxUtility.showMessage(this, "Error al obtener el detalle de la Guía de Ingreso o " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	void preparaPantallaDetalle() {
		txtFechaEmision.setEnabled(false);
		cmbDocumento.setEnabled(false);
		txtNroDocumento.setEnabled(false);
		txtNroDocumento2.setEnabled(false);
		txtCodLocal.setEnabled(false);
		txtDesLocal.setEnabled(false);
		txtOtroProveedor.setEnabled(false);
		lblAgregarT.setVisible(false);
		lblEliminarT.setVisible(false);
		lblGrabarT.setVisible(false);
		lblImprimir.setVisible(true);
		lblImprimir.setBounds(lblGrabarT.getBounds());
	}

	void seteaTipoDocumento(String pCodLocOrigen) {
		AtuxLoadCVL.setSelectedValueInComboBox(cmbDocumento, "LGTR_TIPO_DOCUMENTO", pCodLocOrigen);
	}

	void seteaProveedorCombo(String pCodProveedor) {
		txtCodLocal.setText(pCodProveedor);
		VariablesInventario.vCodLocal = pCodProveedor;
		try {
			txtDesLocal.setText(DBInventario.getNombreLocal());
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	void txtCodLocal_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			VariablesInventario.vCodLocal = txtCodLocal.getText().trim();
			if (VariablesInventario.vCodLocal.length() == 0) {
				DlgSelLocal dlgSelLocal = new DlgSelLocal(parentFrame, "", true);
				dlgSelLocal.setVisible(true);
				if (AtuxVariables.vAceptar) {
					txtCodLocal.setText(VariablesInventario.vCodLocal);
					txtDesLocal.setText(VariablesInventario.vNoLocal);
					cmbDocumento.requestFocus();
				}
			} else {
				VariablesInventario.vCodLocal = AtuxUtility.caracterIzquierda(txtCodLocal.getText().trim(), 3, "0");
				try {
					VariablesInventario.vNoLocal = DBInventario.getNombreLocal();
					if (VariablesInventario.vNoLocal.trim().length() == 0) {
						VariablesInventario.vCodLocal = "";
						JOptionPane.showMessageDialog(this, "El Codigo de Local NO existe. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
						txtCodLocal.selectAll();
						txtDesLocal.setText("");
						AtuxUtility.moveFocus(txtCodLocal);
					} else {
						txtCodLocal.setText(VariablesInventario.vCodLocal);
						txtDesLocal.setText(VariablesInventario.vNoLocal);
						cmbDocumento.requestFocus();
					}
				} catch (SQLException sqlerr) {
					AtuxUtility.showMessage(this, "Error al obtener Nombre del Local. Verifique !!! - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
					sqlerr.printStackTrace();
				}
			}
		}

		checkKeyPress(e);
	}

	void this_windowOpened(WindowEvent e) {
		if (tblGuiaIngreso.getRowCount() > 0) {
			AtuxGridUtils.showCell(tblGuiaIngreso, 0, 0);
		}
		if (txtFechaEmision.isEnabled()) {
			txtFechaEmision.requestFocus();
		} else {
			tblGuiaIngreso.requestFocus();
		}
	}

	void closeWindow(boolean pBlnAceptar) {
		if (!lblImprimir.isVisible()) {
			if (JOptionPane.showConfirmDialog(this, "Aún no se ha grabado el Ingreso Manual!!!\nSeguro de Salir?", "Salir del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				this.dispose();
			}
		} else {
			blnAceptar = pBlnAceptar;
			this.setVisible(false);
			this.dispose();
		}
	}

	void this_windowClosing(WindowEvent e) {
		JOptionPane.showMessageDialog(this, "Usted esto cerrando la ventana de manera incorrecta !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
	}

}