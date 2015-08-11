package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import atux.interfaces.GeneraArchivo;
import atux.inventario.reference.DBInventario;
import atux.inventario.reference.VariablesInventario;
import atux.replicacion.CmtsReplicacionPk;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.trasladoproducto.reference.GuiaSalidaImpresion;
import atux.trasladoproducto.reference.ManejadorDeNumeroGuia;
import atux.trasladoproducto.reference.VariablesTrasladoProducto;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

/**
 * 
 * @version 1.0<br>
 */
public class DlgDespachoPedidoTraslado extends JDialog {

	private final Log logger = LogFactory.getLog(getClass());
	Frame parentFrame;
	AtuxTableModel tableModelPedidoDetalle;

	String coCompania = "";
	String coLocal = "";
	String nuSolicitudPedido = "";
	String tiSolicitudPedido = "";
	String nuRecepcionProducto = "";
	String coLocalDestino = "";
	ManejadorDeNumeroGuia manejadorDeNumeroGuia;

	JPanel pnlCabeceraPedidoTraslado = new JPanel();
	JScrollPane scrPedidoTraslado = new JScrollPane();
	JTable tblPedidoDetalle = new JTable();
	JLabel lblFechaEmisionT = new JLabel();
	XYLayout xYLayout1 = new XYLayout();
	JPanel pnlCabeceraDetalle = new JPanel();
	XYLayout xYLayout2 = new XYLayout();
	JLabel lblGenerarPedidoTrasladoT = new JLabel();
	JLabel lblSalirT = new JLabel();
	JButton btnRelacion = new JButton();
	JLabel lblStockT = new JLabel();
	JLabel lblStock = new JLabel();
	JLabel lblFraccionT = new JLabel();
	JLabel lblFraccion = new JLabel();
	JLabel lblOpcionesT = new JLabel();
	JTextField txtCoLocalOrigen = new JTextField();
	JTextField txtDeLocalOrigen = new JTextField();
	JCheckBox chkLista = new JCheckBox();
	JLabel lblFechaEmisionT1 = new JLabel();
	JTextField txtTiSolicitudPedido = new JTextField();
	JTextField txtNuSolicitudPedido = new JTextField();
	JLabel lblFechaEmisionT2 = new JLabel();
	JLabel lblFechaEmisionT4 = new JLabel();
	JTextField txtDeLocalDestino = new JTextField();
	JTextField txtCoLocalDestino = new JTextField();
	JLabel lblFechaEmisionT5 = new JLabel();
	JTextField txtFeEmision = new JTextField();
	private JLabel lblIngresarCantidadT = new JLabel();

	public DlgDespachoPedidoTraslado() {
		this(null, "", false);
	}

	public DlgDespachoPedidoTraslado(Frame parent, String title, boolean modal) {
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
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				this_windowOpened(e);
			}
		});
		pnlCabeceraPedidoTraslado.setBackground(SystemColor.control);
		pnlCabeceraPedidoTraslado.setLayout(null);
		pnlCabeceraPedidoTraslado.setBorder(BorderFactory.createTitledBorder("Datos del Pedido"));
		pnlCabeceraPedidoTraslado.setFont(new Font("SansSerif", 0, 11));
		scrPedidoTraslado.setFont(new Font("SansSerif", 0, 11));
		tblPedidoDetalle.setFont(new Font("SansSerif", 0, 11));
		tblPedidoDetalle.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tblPedidoDetalle_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				tblPedidoDetalle_keyReleased(e);
			}
		});
		lblFechaEmisionT.setText("Fecha de Emision :");
		lblFechaEmisionT.setBounds(new Rectangle(350, 25, 100, 20));
		lblFechaEmisionT.setFont(new Font("SansSerif", 0, 11));
		xYLayout1.setWidth(766);
		xYLayout1.setHeight(545);
		pnlCabeceraDetalle.setBackground(new Color(32, 105, 29));
		pnlCabeceraDetalle.setLayout(xYLayout2);
		pnlCabeceraDetalle.setFont(new Font("SansSerif", 0, 11));
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
		txtCoLocalOrigen.setBounds(new Rectangle(55, 50, 50, 20));
		txtCoLocalOrigen.setEnabled(false);
		txtDeLocalOrigen.setBounds(new Rectangle(110, 50, 190, 20));
		txtDeLocalOrigen.setEnabled(false);
		chkLista.setBackground(new Color(32, 105, 29));
		chkLista.setEnabled(false);
		chkLista.setSelected(true);
		lblFechaEmisionT1.setBounds(new Rectangle(15, 25, 35, 20));
		lblFechaEmisionT1.setText("Tipo :");
		lblFechaEmisionT1.setFont(new Font("SansSerif", 0, 11));
		txtTiSolicitudPedido.setBounds(new Rectangle(55, 25, 115, 20));
		txtTiSolicitudPedido.setFont(new Font("SansSerif", 0, 11));
		txtTiSolicitudPedido.setEnabled(false);
		txtNuSolicitudPedido.setBounds(new Rectangle(240, 25, 100, 20));
		txtNuSolicitudPedido.setFont(new Font("SansSerif", 0, 11));
		txtNuSolicitudPedido.setEnabled(false);
		lblFechaEmisionT2.setBounds(new Rectangle(195, 25, 55, 20));
		lblFechaEmisionT2.setText("Número :");
		lblFechaEmisionT2.setFont(new Font("SansSerif", 0, 11));
		lblFechaEmisionT4.setBounds(new Rectangle(15, 50, 45, 20));
		lblFechaEmisionT4.setText("Origen :");
		lblFechaEmisionT4.setFont(new Font("SansSerif", 0, 11));
		txtDeLocalDestino.setBounds(new Rectangle(420, 50, 185, 20));
		txtDeLocalDestino.setFont(new Font("SansSerif", 0, 11));
		txtDeLocalDestino.setEnabled(false);
		txtCoLocalDestino.setBounds(new Rectangle(365, 50, 50, 20));
		txtCoLocalDestino.setFont(new Font("SansSerif", 0, 11));
		txtCoLocalDestino.setEnabled(false);
		lblFechaEmisionT5.setBounds(new Rectangle(320, 50, 50, 20));
		lblFechaEmisionT5.setText("Destino :");
		lblFechaEmisionT5.setFont(new Font("SansSerif", 0, 11));
		txtFeEmision.setBounds(new Rectangle(445, 25, 125, 20));
		txtFeEmision.setFont(new Font("SansSerif", 0, 11));
		txtFeEmision.setEnabled(false);

		lblIngresarCantidadT.setText("[ Enter ] Seleccionar");
		lblIngresarCantidadT.setForeground(new Color(32, 105, 29));
		lblIngresarCantidadT.setFont(new Font("SansSerif", 1, 11));

		this.getContentPane().add(lblIngresarCantidadT, new XYConstraints(15, 490, 130, 20));
		this.getContentPane().add(lblOpcionesT, new XYConstraints(15, 470, 65, 15));
		this.getContentPane().add(lblSalirT, new XYConstraints(675, 490, 70, 20));
		this.getContentPane().add(lblGenerarPedidoTrasladoT, new XYConstraints(585, 490, 85, 20));
		this.getContentPane().add(pnlCabeceraDetalle, new XYConstraints(10, 90, 730, 25));
		scrPedidoTraslado.getViewport().add(tblPedidoDetalle, null);
		this.getContentPane().add(scrPedidoTraslado, new XYConstraints(10, 115, 730, 350));
		pnlCabeceraDetalle.add(chkLista, new XYConstraints(695, 5, 30, 20));
		pnlCabeceraDetalle.add(lblFraccion, new XYConstraints(595, 5, 40, 15));
		pnlCabeceraDetalle.add(lblFraccionT, new XYConstraints(535, 5, 55, 15));
		pnlCabeceraDetalle.add(lblStock, new XYConstraints(475, 5, 50, 15));
		pnlCabeceraDetalle.add(lblStockT, new XYConstraints(390, 5, 85, 15));
		pnlCabeceraDetalle.add(btnRelacion, new XYConstraints(10, 5, 225, 15));
		this.getContentPane().add(pnlCabeceraPedidoTraslado, new XYConstraints(10, 5, 730, 85));
		pnlCabeceraPedidoTraslado.add(txtFeEmision, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT5, null);
		pnlCabeceraPedidoTraslado.add(txtCoLocalDestino, null);
		pnlCabeceraPedidoTraslado.add(txtDeLocalDestino, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT4, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT2, null);
		pnlCabeceraPedidoTraslado.add(txtNuSolicitudPedido, null);
		pnlCabeceraPedidoTraslado.add(txtTiSolicitudPedido, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT1, null);
		pnlCabeceraPedidoTraslado.add(txtDeLocalOrigen, null);
		pnlCabeceraPedidoTraslado.add(txtCoLocalOrigen, null);
		pnlCabeceraPedidoTraslado.add(lblFechaEmisionT, null);
		mostrarTablaPedidoTrasladoDetalle();
		VariablesTrasladoProducto.arrayProductos = new ArrayList();
		VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
		VariablesTrasladoProducto.arrayElementos = new ArrayList();
		lblStockT.setVisible(false);
		lblStock.setVisible(false);
		lblFraccion.setVisible(false);
		lblFraccionT.setVisible(false);
	}

	public void setValoresCabecera(String pCoCompania, String pCoLocal, String pNuSolicitudPedido, String pTiSolicitudPedido, String pNuRecepcionProducto, String pCoLocalDestino) {
		this.coCompania = pCoCompania;
		this.coLocal = pCoLocal;
		this.nuSolicitudPedido = pNuSolicitudPedido;
		this.tiSolicitudPedido = pTiSolicitudPedido;
		this.nuRecepcionProducto = pNuRecepcionProducto;
		this.coLocalDestino = pCoLocalDestino;
	}

	void mostrarTablaPedidoTrasladoDetalle() {
		colocarFocoEnLista();
		tableModelPedidoDetalle = new AtuxTableModel(ConstantsTrasladoProducto.columnsDespachoPedidoTrasladoDetalle, ConstantsTrasladoProducto.defaultValuesDespachoPedidoTrasladoDetalle, 0);
		AtuxUtility.initSimpleList(tblPedidoDetalle, tableModelPedidoDetalle, ConstantsTrasladoProducto.columnsDespachoPedidoTrasladoDetalle);
	}

	public void inicializar(AtuxTableModel listaPedidoTrasladoDetalle) {
		List pedidoDetalle = listaPedidoTrasladoDetalle.data;
		tableModelPedidoDetalle.data = new ArrayList();
		for (int i = 0; i < pedidoDetalle.size(); i++) {
			ArrayList nuevoPedidoDetalle = new ArrayList((Collection) pedidoDetalle.get(i));
			// Se le agrega una columna para almacenar la desc del laboratorio
			nuevoPedidoDetalle.add("");
			String coProducto = (String) nuevoPedidoDetalle.get(0);
			ArrayList productoLocalInfo = obtenerProductoLocalInfo(coProducto);
			nuevoPedidoDetalle.set(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA, "");
			if (productoLocalInfo.size() > 0) {
				String inProductoFraccionadoDestino = (String) productoLocalInfo.get(0);
				String deUnidadFraccionDestino = (String) productoLocalInfo.get(1);
				String vaFraccionDestinoStr = (String) productoLocalInfo.get(2);
				nuevoPedidoDetalle.set(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO, deUnidadFraccionDestino);
				nuevoPedidoDetalle.set(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO_DESTINO, inProductoFraccionadoDestino);
				nuevoPedidoDetalle.set(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION_DESTINO, vaFraccionDestinoStr);
			}
			setearCantidadAtendidaSiAplica(nuevoPedidoDetalle);
			tableModelPedidoDetalle.data.add(nuevoPedidoDetalle);
		}
		tblPedidoDetalle.repaint();
	}

	private void setearCantidadAtendidaSiAplica(ArrayList pedidoDetalle) {
		if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido)
				|| ConstantsTrasladoProducto.esPedidoDevolucion(tiSolicitudPedido) || ConstantsTrasladoProducto.esPedidoFranquicia(tiSolicitudPedido)) {
			String caSolicitada = (String) pedidoDetalle.get(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD);
			pedidoDetalle.set(ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA, caSolicitada);
		}
	}

	private ArrayList obtenerProductoLocalInfo(String coProducto) {
		ArrayList productoLocalInfo = new ArrayList();
		try {
			productoLocalInfo = DBTrasladoProducto.obtenerProductoInfo(coCompania, coLocalDestino, coProducto);
		} catch (SQLException e) {
			AtuxUtility.showMessage(this, "Error al obtener los datos del producto:" + coProducto + " . Verifique !!! - " + e.getErrorCode() + "\n" + e.toString(), null);
			e.printStackTrace();
		}
		return productoLocalInfo;
	}

	void tblPedidoDetalle_keyPressed(KeyEvent e) {
		manejaOpciones(e);
		if (tblPedidoDetalle.getRowCount() > 0) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				e.consume();
				openDlgIngresoCantidad();
			}
		}
	}

	void btnRelacion_actionPerformed(ActionEvent e) {
		colocarFocoEnLista();
	}

	void colocarFocoEnLista() {
		if (tblPedidoDetalle.getSelectedRow() > 0) {
			AtuxGridUtils.showCell(tblPedidoDetalle, 0, 0);
		}
		tblPedidoDetalle.requestFocus();
	}

	void manejaOpciones(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F10) {
			grabarDespachoPedido();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			cancelarDespacho();
		}
	}

	void tblPedidoDetalle_keyReleased(KeyEvent e) {
		if (tblPedidoDetalle.getRowCount() > 0) {
			actualizarLabelDeStock();
		}
	}

	void actualizarLabelDeStock() {
		try {
			if (tblPedidoDetalle.getSelectedRow() <= 0) {
				AtuxGridUtils.showCell(tblPedidoDetalle, 0, 0);
			}
			String coProducto = ((String) tblPedidoDetalle.getValueAt(tblPedidoDetalle.getSelectedRow(), 0)).trim();
			ArrayList datosProducto = DBInventario.obtieneDatosProducto(coProducto);
			String in_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(0)).trim();
			String stock_total = ((String) ((ArrayList) datosProducto.get(0)).get(1)).trim();
			String stock_entero = ((String) ((ArrayList) datosProducto.get(0)).get(2)).trim();
			String stock_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(3)).trim();
			if (!lblStock.isVisible()) {
				lblStock.setVisible(true);
				lblStockT.setVisible(true);
			}
			boolean prodFraccionado = in_fraccion.equalsIgnoreCase("S");
			lblFraccion.setVisible(prodFraccionado);
			lblFraccionT.setVisible(prodFraccionado);
			lblStock.setText(stock_entero);
			lblFraccion.setText(stock_fraccion);
		} catch (SQLException sqlerr) {
			AtuxUtility.showMessage(this, "Error al obtener el Stock del Producto !!! - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
			sqlerr.printStackTrace();
		}
	}

	void closeWindow(boolean pAceptar) {
		AtuxVariables.vAceptar = pAceptar;
		tableModelPedidoDetalle.data = new ArrayList();
		this.setVisible(false);
		this.dispose();
	}

	void this_windowOpened(WindowEvent e) {
		tblPedidoDetalle.requestFocus();
	}

	void cancelarDespacho() {
		// Si el pedido es por sobrante o faltante, todavoa no se hizo la reserva de stock
		if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido)
				|| ConstantsTrasladoProducto.esPedidoDevolucion(tiSolicitudPedido)) {
			closeWindow(false);
			return;
		}
		int nuRows = tableModelPedidoDetalle.getRowCount();
		if (nuRows > 0) {
			for (int i = 0; i < nuRows; i++) {
				try {
					int caAtendida = obtenerInt((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA));
					if (caAtendida == 0) {
						continue;
					}
					String coProducto = (String) tableModelPedidoDetalle.getValueAt(i, 0);
					int vaFraccion = obtenerVaFraccion(i);
					boolean usarUnidadBase = debeUsarseUnidadBase();
					int caADisminuirDelComprometido = usarUnidadBase ? caAtendida * vaFraccion : caAtendida;
					DBInventario.updateDELSTOCKCOMPROMETIDOPRODUCTO(coProducto, caADisminuirDelComprometido, false);
					DBInventario.execRespaldoStock("D", AtuxVariables.vNombrePC, coProducto, "0", "0");
					AtuxUtility.aceptarTransaccion();
				} catch (Exception e) {
					DBTrasladoProducto.procesarException(this, e);
				}
			}
		}
		closeWindow(false);
	}

	/**
	 * Si es vacoo o nulo el string se devuelve Cero, caso contrario se devuelve el entero q es representado por el String
	 */
	private int obtenerInt(String valorStr) {
		return valorStr == null || "".equals(valorStr) ? 0 : Integer.parseInt(valorStr);
	}

	/**
	 * Devuelve el vaFraccion si es nulo o cero, se devuelve 1
	 */
	private int obtenerVaFraccion(int row) {
		String vaFraccionStr = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION)).trim();
		int vaFraccion = vaFraccionStr == null || "".equals(vaFraccionStr) || "0".equals(vaFraccionStr) ? 1 : Integer.parseInt(vaFraccionStr);
		return vaFraccion;
	}

	private void grabarDespachoPedido() {
		// Hacer las validaciones
		if (JOptionPane.showConfirmDialog(this, "Desea Entregar el Pedido de Traslado ?", "Confirmar Entrega del Pedido de Traslado", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
			return;
		}

		if (!validarAlMenosUnProductoADespachar()) {
			return;
		}

		boolean esSobranteOFaltanteCD = ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido);
		boolean esDevolucion = ConstantsTrasladoProducto.esPedidoDevolucion(tiSolicitudPedido);
		boolean esPedidoFranquicia = ConstantsTrasladoProducto.esPedidoCompaniaFranquicia(tiSolicitudPedido);
		boolean despacharEnBloque = ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido) || esSobranteOFaltanteCD || esDevolucion || esPedidoFranquicia;

		if (despacharEnBloque) {
			if (!verificarVafraccionIguales()) {
				return;
			}
		}

		DlgNumeracion dlgNumeracion = new DlgNumeracion(parentFrame, "Mantenimiento de Numeraciones", true);
		dlgNumeracion.setVisible(true);

		if (!AtuxVariables.vAceptar) {
			return;
		}

		try {
			if (despacharEnBloque) {
				if (!verificarStockYActualizarStockComprometido()) {
					return;
				}
			}

			grabarCabecera();
			grabarDetalle();
			grabarKardexActualizarStocks();
			actualizarLgtcSolicitudPedido();
			actualizarLgtdSolicitudPedido();
			// Inicio ID: 001

			CmtsReplicacionPk replicadorPk = null;
			// Fin ID: 001
			logger.info(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "COUNT(1)", "CO_COMPANIA = '001' AND CO_LOCAL = '" + coLocal + "' AND NU_RECEPCION_PRODUCTO = '" + nuRecepcionProducto
					+ "'"));

			if (!esSobranteOFaltanteCD && !esDevolucion) {
				replicadorPk = DBTrasladoProducto.replicarSolitudPedidoUpdateYRecepcionProducto(coCompania, coLocal, nuSolicitudPedido, nuRecepcionProducto, coLocalDestino, coLocal);
			}

			logger.info(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "COUNT(1)", "CO_COMPANIA = '001' AND CO_LOCAL = '" + coLocal + "' AND NU_RECEPCION_PRODUCTO = '" + nuRecepcionProducto
					+ "'"));
			DBInventario.execRespaldoStock("C", AtuxVariables.vNombrePC, "", "", "0");
			logger.info(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "COUNT(1)", "CO_COMPANIA = '001' AND CO_LOCAL = '" + coLocal + "' AND NU_RECEPCION_PRODUCTO = '" + nuRecepcionProducto
					+ "'"));

			logger.info("Número de Entrega Generado: " + nuRecepcionProducto);
			AtuxUtility.showMessage(this, "Número de Entrega Generado: " + nuRecepcionProducto, null);

			boolean localDestinoSAP = AtuxDBUtility.getValueAt("VTTM_LOCAL", "IN_IMPLEMENTA_SAP", "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL = '" + coLocal + "'").equals("S");

			logger.info("Cod Local Origen : " + coLocal);
			logger.info("Cod Local Destino : " + coLocalDestino);

			logger.info(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "COUNT(1)", "CO_COMPANIA = '001' AND CO_LOCAL = '" + coLocal + "' AND NU_RECEPCION_PRODUCTO = '" + nuRecepcionProducto
					+ "'"));
			if (localDestinoSAP && !ConstantsTrasladoProducto.esPedidoFranquicia(tiSolicitudPedido)) {
				GeneraArchivo generaArchivo = new GeneraArchivo();
				String codInterface = AtuxDBUtility
						.getValueAt("CMTR_VARIABLES_SISTEMA", "VA_TEXTO", "CO_COMPANIA = '001' AND CO_LOCAL = '099' AND CO_VARIABLE = 'codigo.interface.despacho.logistica'");

				generaArchivo.setCodInterface(codInterface);
				generaArchivo.setProcCabecera("COPQ_MM_CDTL_DESPACHO.GET_CAB_ENTRADA_NOSAP_SAP(?,?,?,?)");
				generaArchivo.setParamCabecera(AtuxVariables.vCodigoCompania + ";" + coLocal + ";" + nuRecepcionProducto + ";NAME_FILE");
				generaArchivo.setProcDetalle("COPQ_MM_CDTL_DESPACHO.GET_DET_ENTRADA_NOSAP_SAP(?,?,?)");
				generaArchivo.setParamDetalle(AtuxVariables.vCodigoCompania + ";" + coLocal + ";" + nuRecepcionProducto);
				generaArchivo.setWithCommit(false);

				logger.info(AtuxDBUtility.getValueAt("LGTC_RECEPCION_PRODUCTO", "COUNT(1)", "CO_COMPANIA = '001' AND CO_LOCAL = '" + coLocal + "' AND NU_RECEPCION_PRODUCTO = '" + nuRecepcionProducto
						+ "'"));
				generaArchivo.generar();
			}

			AtuxUtility.aceptarTransaccion();

			// if (replicadorPk != null) {
			// DBTrasladoProducto.forzarReplicacion(replicadorPk);
			// }

			String deLocalOrigen = txtDeLocalOrigen.getText().trim();
			int mes = Integer.parseInt(txtFeEmision.getText().substring(3, 5));
			String mesLetra = AtuxUtility.devuelveMesEnLetras(mes);
			String dia = txtFeEmision.getText().substring(0, 2);
			String aoo = txtFeEmision.getText().substring(6, 10);
			// TODO
			String guiaInicial = dlgNumeracion.getStrNumeroGuiaSal();// Integer.parseInt(DBInventario.devuelveNumeroGuiaSalida())-1;
			String serie = guiaInicial.substring(0, 3);
			String numeral = guiaInicial.substring(3);

			GuiaSalidaImpresion.imprimirTransporteProducto(this, tiSolicitudPedido, tblPedidoDetalle, deLocalOrigen, dia, mesLetra, aoo, nuRecepcionProducto, nuSolicitudPedido, serie, numeral);
			closeWindow(true);
		} catch (Exception e) {
			e.printStackTrace();
			DBTrasladoProducto.procesarException(this, e, "Error al Generar la Entrega del Pedido de Traslado!!! - ");
		}
	}

	private boolean verificarVafraccionIguales() {
		int nuRows = tableModelPedidoDetalle.getRowCount();
		StringBuffer errorMsg = new StringBuffer();
		int contProdConDistintoVaFraccion = 0;
		for (int i = 0; i < nuRows; i++) {
			String vaFraccionOrigenStr = ((String) tblPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION)).trim();
			String vaFraccionDestinoStr = ((String) tblPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION_DESTINO)).trim();
			if (!vaFraccionOrigenStr.equals(vaFraccionDestinoStr)) {
				contProdConDistintoVaFraccion++;
				String coProducto = (String) tableModelPedidoDetalle.getValueAt(i, 0);
				String deProducto = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_PRODUCTO);
				errorMsg.append(coProducto + " " + deProducto);
				errorMsg.append("\n");
				if (contProdConDistintoVaFraccion > 10) {
					break;
				}
			}
		}
		if (contProdConDistintoVaFraccion > 0) {
			if (contProdConDistintoVaFraccion <= 10) {
				JOptionPane.showMessageDialog(this, "No se puede entregar los productos. Los siguientes Productos tienen distinta unidad fraccion:\n " + errorMsg.toString(), "Mensaje del Sistema",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "No se puede entregar los productos. Existen Productos que tienen distinta unidad fraccion\n ", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			}
			return false;
		}
		return true;
	}

	private boolean verificarStockYActualizarStockComprometido() throws SQLException {
		int nuRows = tableModelPedidoDetalle.getRowCount();
		List productosVerificados = new ArrayList();
		boolean usarUnidadBase = debeUsarseUnidadBase();

		for (int i = 0; i < nuRows; i++) {
			String coProducto = (String) tableModelPedidoDetalle.getValueAt(i, 0);
			String caAtendidaEnteraStr = ((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			int caAtendidaEntera = "".equals(caAtendidaEnteraStr) ? 0 : Integer.parseInt(caAtendidaEnteraStr);
			int vaFraccion = obtenerVaFraccion(i);
			int nuevoTotal = caAtendidaEntera;
			if (usarUnidadBase) {
				nuevoTotal = caAtendidaEntera * vaFraccion;
			}
			VariablesInventario.vCodigoProducto = coProducto;
			int stockDisponible = DBInventario.stockDisponibleProducto();
			if (nuevoTotal > stockDisponible) {
				limpiarStocksComprometidosDe(productosVerificados);
				JOptionPane.showMessageDialog(this, "La cantidad a Atender: " + nuevoTotal + " excede el stock disponible:" + stockDisponible + " para el producto:" + coProducto + "\n. Verifique!!!",
						"Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			Map productoInfo = new HashMap();
			productoInfo.put("coProducto", coProducto);
			productoInfo.put("cantidad", new Integer(nuevoTotal));
			productosVerificados.add(productoInfo);
			DBInventario.updateADDSTOCKCOMPROMETIDOPRODUCTO(coProducto, nuevoTotal, false);
			DBInventario.execRespaldoStock("A", AtuxVariables.vNombrePC, coProducto, "0", String.valueOf(nuevoTotal));
		}

		return true;
	}

	private boolean debeUsarseUnidadBase() throws SQLException {
		return ConstantsTrasladoProducto.debeUsarseUnidadBase(tiSolicitudPedido, coLocal);
	}

	private void limpiarStocksComprometidosDe(List productos) throws SQLException {

		for (int i = 0; i < productos.size(); i++) {
			Map productoInfo = (Map) productos.get(i);
			String coProducto = (String) productoInfo.get("coProducto");
			Integer cantidad = (Integer) productoInfo.get("cantidad");
			DBInventario.updateDELSTOCKCOMPROMETIDOPRODUCTO(coProducto, cantidad.intValue(), false);
			DBInventario.execRespaldoStock("U", AtuxVariables.vNombrePC, coProducto, "0", "0");
		}
	}

	private void grabarKardexActualizarStocks() throws SQLException {
		DBTrasladoProducto.grabarKardexActualizarStocks(coCompania, AtuxVariables.vCodigoLocal, coLocal, nuRecepcionProducto, ConstantsTrasladoProducto.TIPO_DOCUMENTO_GUIA_TRANSF,
				ConstantsTrasladoProducto.GRUPO_MOTIVO_KARDEX, ConstantsTrasladoProducto.MOTIVO_KARDEX_GUIA_SALIDA, debeUsarseUnidadBase(), tiSolicitudPedido, nuSolicitudPedido);
	}

	private boolean validarAlMenosUnProductoADespachar() {
		int nuRows = tableModelPedidoDetalle.getRowCount();
		for (int i = 0; i < nuRows; i++) {
			String caAtendidaEnteraStr = ((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			int caAtendidaEntera = "".equals(caAtendidaEnteraStr) ? 0 : Integer.parseInt(caAtendidaEnteraStr);
			if (caAtendidaEntera > 0) {
				return true;
			}
		}
		AtuxUtility.showMessage(this, "Ningon producto tiene Cantidad Atendida mayor que cero. Verifique.", null);
		return false;
	}

	private void grabarCabecera() throws SQLException {
		String pIdCreaSolicitud = AtuxVariables.vIdUsuario;
		String tiDocumentoRecepcion = ConstantsTrasladoProducto.TIPO_DOCUMENTO_RECEPCION;
		// String nuDocumentoRecepcion = nuRecepcionProducto;
		String nuDocumentoRecepcion = nuSolicitudPedido;

		logger.info("PK Generada: " + coCompania + ";" + coLocal + ";" + nuRecepcionProducto);
		DBTrasladoProducto.grabarCabeceraRecepcion(coCompania, coLocal, nuRecepcionProducto, nuSolicitudPedido, tiDocumentoRecepcion, nuDocumentoRecepcion, pIdCreaSolicitud, coLocalDestino);
	}

	private void grabarDetalle() throws SQLException {
		String idCreaSolicituddet = AtuxVariables.vIdUsuario;
		int totalProductos = 0;
		int totalItems = 0;
		int nuItemRecepcionProducto = 1;
		int nuFilasConCantidadMayorQCero = obtenerNuFilasConCantidadMayorQueCero();
		manejadorDeNumeroGuia = new ManejadorDeNumeroGuia();
		manejadorDeNumeroGuia.init(nuFilasConCantidadMayorQCero);
		int nuRows = tableModelPedidoDetalle.getRowCount();
		for (int i = 0; i < nuRows; i++) {
			String coProducto = (String) tableModelPedidoDetalle.getValueAt(i, 0);
			String nuRevisionProducto = "0";
			int nuItem = Integer.parseInt((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_NU_ITEM));
			String caAtendidaEnteraStr = ((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			int caAtendidaEntera = "".equals(caAtendidaEnteraStr) ? 0 : Integer.parseInt(caAtendidaEnteraStr);
			if (caAtendidaEntera == 0) {
				continue;
			}
			String inProdFraccionado = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO);
			String vaFraccionStr = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION);
			Integer vaFraccion = vaFraccionStr == null || vaFraccionStr.equals("") ? new Integer(0) : new Integer(vaFraccionStr);
			totalProductos += caAtendidaEntera;
			totalItems++;
			int nuFila = i + 1;
			String nuGuia = manejadorDeNumeroGuia.obtenerSiguiente(nuFila);
			int nuPagina = manejadorDeNumeroGuia.getNuPagina();
			int caItems = manejadorDeNumeroGuia.obtenerCaItemsGuia(nuFila);
			DBTrasladoProducto.grabarDetalleRecepcion(coCompania, coLocal, nuRecepcionProducto, nuItemRecepcionProducto, nuItem, coProducto, nuRevisionProducto, caAtendidaEntera, inProdFraccionado,
					vaFraccion, idCreaSolicituddet, nuGuia, nuPagina, caItems, nuSolicitudPedido, tiSolicitudPedido);
			nuItemRecepcionProducto++;
		}
		if (totalProductos > 0) {
			DBTrasladoProducto.updateLgtcRecepcionProductoCaProducto(coCompania, coLocal, nuRecepcionProducto, totalItems, totalProductos);
			String numeroDisponibleGuia = manejadorDeNumeroGuia.obtenerNumeroGuiaDisponible();
			DBTrasladoProducto.actualizarNumSecGuiaSalida(numeroDisponibleGuia);
		}
	}

	private int obtenerNuFilasConCantidadMayorQueCero() {
		int nuRows = tableModelPedidoDetalle.getRowCount();
		int contador = 0;
		for (int i = 0; i < nuRows; i++) {
			String caAtendidaEnteraStr = ((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			int caAtendidaEntera = "".equals(caAtendidaEnteraStr) ? 0 : Integer.parseInt(caAtendidaEnteraStr);
			if (caAtendidaEntera > 0) {
				contador++;
			}
		}
		return contador;
	}

	private void actualizarLgtcSolicitudPedido() throws SQLException {
		DBTrasladoProducto.updateLgtcSolicitudPedido(coCompania, coLocal, nuSolicitudPedido, ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_ATENDIDO);
	}

	private void actualizarLgtdSolicitudPedido() throws SQLException {
		int nuRows = tableModelPedidoDetalle.getRowCount();
		for (int i = 0; i < nuRows; i++) {
			int nuItem = Integer.parseInt((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_NU_ITEM));
			String caAtendidaEnteraStr = ((String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			int caAtendidaEntera = "".equals(caAtendidaEnteraStr) ? 0 : Integer.parseInt(caAtendidaEnteraStr);
			String inProdFraccionadoDestino = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO_DESTINO);
			String vaFraccionDestinoStr = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION_DESTINO);
			String unProductoDestino = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO);
			String unFraccionDestino = (String) tableModelPedidoDetalle.getValueAt(i, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO);
			Integer vaFraccionDestino = vaFraccionDestinoStr == null || vaFraccionDestinoStr.equals("") ? new Integer(0) : new Integer(vaFraccionDestinoStr);
			DBTrasladoProducto.updateLgtdSolicitudPedido(coCompania, coLocal, nuSolicitudPedido, nuItem, caAtendidaEntera, inProdFraccionadoDestino, vaFraccionDestino, unProductoDestino,
					unFraccionDestino, ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_ATENDIDO);
		}
	}

	void openDlgIngresoCantidad() {
		if (ConstantsTrasladoProducto.esPedidoXSobranteOFaltante(tiSolicitudPedido) || ConstantsTrasladoProducto.esPedidoXSobranteOFaltanteCD(tiSolicitudPedido)
				|| ConstantsTrasladoProducto.esPedidoDevolucion(tiSolicitudPedido)) {
			AtuxUtility.showMessage(this, "No se puede modificar la cantidad Atendida en\n Pedidos por Sobrante/Faltante o Devolucion", null);
			return;
		}

		if (ConstantsTrasladoProducto.esPedidoFranquicia(tiSolicitudPedido)) {
			AtuxUtility.showMessage(this, "No se puede modificar la cantidad Atendida en\n Pedidos de Franquicia", null);
			return;
		}

		int row = tblPedidoDetalle.getSelectedRow();
		if (row >= 0) {
			VariablesTrasladoProducto.vCodigoProducto = (String) tblPedidoDetalle.getValueAt(row, 0);
			VariablesInventario.vCodigoProducto = VariablesTrasladoProducto.vCodigoProducto;
			String unidadFracOrigen = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_ORIGEN)).trim();
			String unidadFracDestino = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_FRACCION_DESTINO)).trim();
			String descripcion = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_PRODUCTO)).trim();
			String unidad = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_UN_PRODUCTO_DESTINO)).trim();
			String inProdFraccionado = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_IN_PRODUCTO_FRACCIONADO)).trim();
			String cantidadAAtender = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA)).trim();
			String vaFraccionOrigenStr = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION)).trim();
			String vaFraccionDestinoStr = ((String) tblPedidoDetalle.getValueAt(row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_VA_FRACCION_DESTINO)).trim();
			if (!vaFraccionOrigenStr.equals(vaFraccionDestinoStr)) {
				unidadFracDestino = unidadFracDestino.trim();
				AtuxUtility.showMessage(this, "Las unidad fraccion es distinta: Origen:(" + vaFraccionOrigenStr + " " + (unidadFracOrigen.trim().length() == 0 ? unidad : unidadFracOrigen)
						+ ") Destino:(" + vaFraccionDestinoStr + " " + (unidadFracDestino.trim().length() == 0 ? unidad : unidadFracDestino) + ")\n No se puede entregar este producto.", null);
				return;
			}
			int vaFraccion = obtenerVaFraccion(row);
			boolean cantidadPrimeraVez = (cantidadAAtender == null || "".equals(cantidadAAtender));
			int cantidadAAtenderOriginal = obtenerInt(cantidadAAtender);
			DlgIngCantidad dlgIngCantidad = new DlgIngCantidad(parentFrame, "Ingrese cantidad a Atender", true);
			dlgIngCantidad.lblCodigo.setText(VariablesTrasladoProducto.vCodigoProducto);
			dlgIngCantidad.lblDescripcion.setText(descripcion);
			dlgIngCantidad.txtCantidad.setText(cantidadAAtender);
			dlgIngCantidad.txtCantidad.selectAll();
			dlgIngCantidad.inProdFraccionado = inProdFraccionado;
			dlgIngCantidad.valorFraccion = vaFraccion;
			dlgIngCantidad.lblValorFraccion.setVisible(true);
			dlgIngCantidad.lblValorFraccion.setText("" + vaFraccion);
			dlgIngCantidad.lblValorFrac.setVisible(true);
			dlgIngCantidad.cantEnteraOld = cantidadAAtenderOriginal;
			dlgIngCantidad.setValidarQueCantidadEsteDentroDeStock(true);
			dlgIngCantidad.setUnidadBase(unidad);
			dlgIngCantidad.setUnidadFraccion(unidadFracDestino);
			dlgIngCantidad.inicializar();
			dlgIngCantidad.setVisible(true);
			if (AtuxVariables.vAceptar) {
				AtuxVariables.vAceptar = false;
				int nuevaCantidadAAtender = Integer.parseInt(dlgIngCantidad.txtCantidad.getText());
				try {
					int nuevoTotal = nuevaCantidadAAtender;
					int antiguoTotal = cantidadAAtenderOriginal;
					if (cantidadPrimeraVez) {
						DBInventario.updateADDSTOCKCOMPROMETIDOPRODUCTO(VariablesInventario.vCodigoProducto, nuevoTotal, false);
						DBInventario.execRespaldoStock("A", AtuxVariables.vNombrePC, VariablesInventario.vCodigoProducto, "0", String.valueOf(nuevoTotal));
						AtuxUtility.aceptarTransaccion();
					} else if (antiguoTotal > nuevoTotal) {
						DBInventario.updateDELSTOCKCOMPROMETIDOPRODUCTO(VariablesInventario.vCodigoProducto, (antiguoTotal - nuevoTotal), false);
						DBInventario.execRespaldoStock("U", AtuxVariables.vNombrePC, VariablesInventario.vCodigoProducto, "0", String.valueOf(nuevoTotal));
						AtuxUtility.aceptarTransaccion();
					} else if (antiguoTotal < nuevoTotal) {
						if (dlgIngCantidad.stockDisponible == 0) {
							AtuxUtility.showMessage(this, "No existe Stock disponible para la nueva Cantidad Ingresada.  Verifique !!!", null);
						} else {
							DBInventario.updateADDSTOCKCOMPROMETIDOPRODUCTO(VariablesInventario.vCodigoProducto, (nuevoTotal - antiguoTotal), false);
							DBInventario.execRespaldoStock("U", AtuxVariables.vNombrePC, VariablesInventario.vCodigoProducto, "0", String.valueOf(nuevoTotal));
							AtuxUtility.aceptarTransaccion();
						}
					}
					tblPedidoDetalle.setValueAt(String.valueOf(nuevaCantidadAAtender), row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_CANTIDAD_ATENDIDA);
					tblPedidoDetalle.setValueAt(dlgIngCantidad.lblLaboratorio.getText().trim(), row, ConstantsTrasladoProducto.DESPACHO_PEDIDO_DETALLE_DE_LABORATORIO);
					tblPedidoDetalle.repaint();
					actualizarLabelDeStock();
				} catch (Exception e) {
					DBTrasladoProducto.procesarException(this, e, "Error al actualizar stock comprometido producto !!! - ");
				}
			}
		}
	}

	public static void main(String[] args) {
		TrasladoProductosInterno.init();
		DlgDespachoPedidoTraslado dlgPedidoTraslado = new DlgDespachoPedidoTraslado(null, "Pedido de Traslado", true);
		dlgPedidoTraslado.setVisible(true);
	}

}
