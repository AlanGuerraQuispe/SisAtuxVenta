package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Collections;

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

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import atux.inventario.reference.DBInventario;
import atux.replicacion.CmtsReplicacionPk;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.util.common.AtuxGridUtils;
import atux.common.AtuxTableComparator;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Pantalla con los detalles de la Guía<br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * <br>
 *
 * @version 1.0<br>
 */
public class DlgRecepProd extends JDialog {
    private final Log logger = LogFactory.getLog(getClass());
    Frame parentFrame;

    String coLocalOrigen;
    String indicAfectaStock;
    String nuRecepcionProducto = "";
    String nuSolicitudPedido = "";
    String tiSolicitudPedido = "";
    RecepProductoReplicador replicador;

    JPanel pnlCabeceraRecepcionProd = new JPanel();
    JScrollPane scrListaProd = new JScrollPane();
    JLabel lblNumGuiaT = new JLabel();
    JLabel lblNumGuia = new JLabel();
    JLabel lblFechaGuiaT = new JLabel();
    JLabel lblFechaGuia = new JLabel();
    JLabel lblNumItemsT = new JLabel();
    JLabel lblNumProdsT = new JLabel();
    JLabel lblNumItems = new JLabel();
    JLabel lblNumProds = new JLabel();
    JLabel lblAfectaStockT = new JLabel();
    JTable tblListaProd = new JTable();
    JLabel lblEstadoGuiaT = new JLabel();
    JLabel lblAfectaStock = new JLabel();
    JLabel lblEstadoGuia = new JLabel();
    JComboBox cmbFindOption = new JComboBox();
    JTextField txtFindText = new JTextField();
    AtuxTableModel tableModelListaProductosDeGuia;
    XYLayout xYLayout1 = new XYLayout();
    JPanel pnlCabeceraDetalle = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JButton btnBuscar = new JButton();
    JButton btnRelacion = new JButton();
    JLabel lblAfectarTotalT = new JLabel();
    JLabel lblAfectarPaginaT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JLabel lblLocal = new JLabel();
    JLabel lblOpcionesT = new JLabel();
    JPanel pnlBusqueda = new JPanel();
    JLabel lblAfectarProductoT = new JLabel();

    public DlgRecepProd() {
        this(null, "", false);
    }

    public DlgRecepProd(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            pack();
            AtuxUtility.centrarVentana(this);
            inicializarListaProdxGuia();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(xYLayout1);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlCabeceraRecepcionProd.setBackground(SystemColor.control);
        pnlCabeceraRecepcionProd.setLayout(null);
        pnlCabeceraRecepcionProd.setBorder(BorderFactory.createTitledBorder("Datos de Cabecera"));
        pnlCabeceraRecepcionProd.setFont(new Font("SansSerif", 0, 11));
        scrListaProd.setForeground(Color.white);
        scrListaProd.setFont(new Font("SansSerif", 0, 11));
        lblNumGuiaT.setText("Guía:");
        lblNumGuiaT.setBounds(new Rectangle(20, 25, 40, 15));
        lblNumGuiaT.setFont(new Font("SansSerif", 1, 11));
        lblNumGuia.setText("1400055083");
        lblNumGuia.setBounds(new Rectangle(70, 25, 90, 15));
        lblNumGuia.setFont(new Font("SansSerif", 0, 11));
        lblFechaGuiaT.setText("Fecha:");
        lblFechaGuiaT.setBounds(new Rectangle(20, 45, 50, 15));
        lblFechaGuiaT.setFont(new Font("SansSerif", 1, 11));
        lblFechaGuia.setText("27/11/2002 12:00:00");
        lblFechaGuia.setBounds(new Rectangle(70, 45, 135, 15));
        lblFechaGuia.setFont(new Font("SansSerif", 0, 11));
        lblNumItemsT.setText("Items:");
        lblNumItemsT.setBounds(new Rectangle(240, 25, 60, 15));
        lblNumItemsT.setFont(new Font("SansSerif", 1, 11));
        lblNumProdsT.setText("Productos:");
        lblNumProdsT.setBounds(new Rectangle(240, 45, 65, 15));
        lblNumProdsT.setFont(new Font("SansSerif", 1, 11));
        lblNumItems.setText("860");
        lblNumItems.setBounds(new Rectangle(310, 25, 60, 15));
        lblNumItems.setFont(new Font("SansSerif", 0, 11));
        lblNumProds.setText("3677");
        lblNumProds.setBounds(new Rectangle(310, 45, 60, 15));
        lblNumProds.setFont(new Font("SansSerif", 0, 11));
        lblAfectaStockT.setText("Stock:");
        lblAfectaStockT.setBounds(new Rectangle(450, 25, 45, 15));
        lblAfectaStockT.setFont(new Font("SansSerif", 1, 11));
        tblListaProd.setFont(new Font("SansSerif", 0, 11));
        tblListaProd.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                tblListaProd_mouseReleased(e);
            }
        });
        lblEstadoGuiaT.setText("Estado:");
        lblEstadoGuiaT.setBounds(new Rectangle(450, 45, 45, 15));
        lblEstadoGuiaT.setFont(new Font("SansSerif", 1, 11));
        lblAfectaStock.setText("Afectar Stock");
        lblAfectaStock.setBounds(new Rectangle(500, 25, 155, 15));
        lblAfectaStock.setFont(new Font("SansSerif", 0, 11));
        lblEstadoGuia.setText("Stock Pendiende de Cargar a Invent.");
        lblEstadoGuia.setBounds(new Rectangle(500, 45, 230, 15));
        lblEstadoGuia.setFont(new Font("SansSerif", 0, 11));
        cmbFindOption.setBounds(new Rectangle(135, 25, 140, 20));
        cmbFindOption.addItem("Código");
        cmbFindOption.addItem("Descripción");
        cmbFindOption.addItem("Página");
        cmbFindOption.setSelectedIndex(2);
        cmbFindOption.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                cmbFindOption_keyReleased(e);
            }
        });
        cmbFindOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmbFindOption_actionPerformed(e);
            }
        });
        txtFindText.setBounds(new Rectangle(295, 25, 285, 20));
        txtFindText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                txtFindText_keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                txtFindText_keyPressed(e);
            }
        });
        xYLayout1.setWidth(781);
        xYLayout1.setHeight(567);
        pnlCabeceraDetalle.setBackground(new Color(32, 105, 29));
        pnlCabeceraDetalle.setLayout(xYLayout2);
        pnlCabeceraDetalle.setFont(new Font("SansSerif", 0, 11));
        btnBuscar.setText("Buscar por");
        btnBuscar.setBounds(new Rectangle(40, 25, 70, 20));
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
        btnBuscar.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setMnemonic('B');
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        btnRelacion.setText("Relación de Productos Seleccionados");
        btnRelacion.setHorizontalAlignment(SwingConstants.LEFT);
        btnRelacion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRelacion.setFont(new Font("SansSerif", 1, 11));
        btnRelacion.setForeground(Color.white);
        btnRelacion.setBackground(new Color(32, 105, 29));
        btnRelacion.setMnemonic('R');
        btnRelacion.setDefaultCapable(false);
        btnRelacion.setRequestFocusEnabled(false);
        btnRelacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacion_actionPerformed(e);
            }
        });
        lblAfectarTotalT.setText("[ F3 ] Afectar Total");
        lblAfectarTotalT.setFont(new Font("SansSerif", 1, 11));
        lblAfectarTotalT.setForeground(new Color(32, 105, 29));
        lblAfectarPaginaT.setText("[ F2 ] Afectar Página");
        lblAfectarPaginaT.setFont(new Font("SansSerif", 1, 11));
        lblAfectarPaginaT.setForeground(new Color(32, 105, 29));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setForeground(new Color(32, 105, 29));
        lblLocal.setBounds(new Rectangle(550, 55, 90, 20));
        lblLocal.setVisible(false);
        lblLocal.setFont(new Font("SansSerif", 0, 11));
        lblOpcionesT.setText("Opciones:");
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        pnlBusqueda.setBorder(BorderFactory.createTitledBorder("Ingrese criterio de búsqueda"));
        pnlBusqueda.setFont(new Font("SansSerif", 0, 11));
        pnlBusqueda.setLayout(null);
        lblAfectarProductoT.setText("[ F4 ] Afectar por Producto");
        lblAfectarProductoT.setForeground(new Color(32, 105, 29));
        lblAfectarProductoT.setFont(new Font("SansSerif", 1, 11));
        pnlBusqueda.add(btnBuscar, null);
        pnlBusqueda.add(txtFindText, null);
        pnlBusqueda.add(cmbFindOption, null);
        this.getContentPane().add(lblAfectarProductoT,
                new XYConstraints(260, 505, 150, 20));
        this.getContentPane().add(pnlBusqueda, new XYConstraints(10, 90, 755, 60));
        this.getContentPane().add(lblOpcionesT, new XYConstraints(25, 485, 60, 15));
        this.getContentPane().add(lblSalirT,
                new XYConstraints(695, 505, 70, 20));this.getContentPane().add(lblAfectarPaginaT,
                                  new XYConstraints(415, 505, 125, 20));
        this.getContentPane().add(lblAfectarTotalT,
                                  new XYConstraints(10, 505, 120, 20));
        pnlCabeceraDetalle.add(btnRelacion, new XYConstraints(10, 5, 245, 15));
        this.getContentPane().add(pnlCabeceraDetalle, new XYConstraints(10, 155, 755, 25));
        this.getContentPane().add(pnlCabeceraRecepcionProd, new XYConstraints(10, 10, 755, 75));
        scrListaProd.getViewport().add(tblListaProd, null);
        this.getContentPane().add(scrListaProd, new XYConstraints(10, 180, 755, 300));
        pnlCabeceraRecepcionProd.add(lblLocal, null);
        pnlCabeceraRecepcionProd.add(lblEstadoGuia, null);
        pnlCabeceraRecepcionProd.add(lblAfectaStock, null);
        pnlCabeceraRecepcionProd.add(lblEstadoGuiaT, null);
        pnlCabeceraRecepcionProd.add(lblAfectaStockT, null);
        pnlCabeceraRecepcionProd.add(lblNumProds, null);
        pnlCabeceraRecepcionProd.add(lblNumItems, null);
        pnlCabeceraRecepcionProd.add(lblNumProdsT, null);
        pnlCabeceraRecepcionProd.add(lblNumItemsT, null);
        pnlCabeceraRecepcionProd.add(lblFechaGuia, null);
        pnlCabeceraRecepcionProd.add(lblFechaGuiaT, null);
        pnlCabeceraRecepcionProd.add(lblNumGuia, null);
        pnlCabeceraRecepcionProd.add(lblNumGuiaT, null);
    }

    void inicializarListaProdxGuia() {
        tableModelListaProductosDeGuia = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaProductosDeGuia, ConstantsTrasladoProducto.defaultValuesListaProductosDeGuia, 0);
        AtuxUtility.initSimpleList(tblListaProd, tableModelListaProductosDeGuia, ConstantsTrasladoProducto.columnsListaProductosDeGuia);
    }

    public void setValoresCabecera(String coLocalOrigen, String pNumRecepProd, String pNuSolicitudPedido, String pNumeroGuia, String pFecha,
                                   String pNumItems, String pNumProductos, String pEstadoGuia,
                                   String pIndicAfectaStock) {
        this.coLocalOrigen = coLocalOrigen;
        nuRecepcionProducto = pNumRecepProd;
        nuSolicitudPedido = pNuSolicitudPedido;
        replicador = new RecepProductoReplicador(AtuxVariables.vCodigoCompania,
                AtuxVariables.vCodigoLocal, coLocalOrigen,
                nuRecepcionProducto, nuSolicitudPedido);
        lblNumGuia.setText(pNumeroGuia);
        lblFechaGuia.setText(pFecha);
        lblNumItems.setText(pNumItems);
        lblNumProds.setText(pNumProductos);
        lblEstadoGuia.setText(pEstadoGuia);
        indicAfectaStock = pIndicAfectaStock;
        if (indicAfectaStock.equalsIgnoreCase("S")) {
            lblAfectaStock.setText("Afecta Stock");
        } else {
            lblAfectaStock.setText("NO Afecta Stock");
        }
        obtenerProductosGuia();
    }

    void obtenerProductosGuia() {
        try {
            DBTrasladoProducto.obtenerProductosDeLaGuia(tableModelListaProductosDeGuia, nuRecepcionProducto);
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this,
                    "Error al obtener los Productos de la Guía - " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }
    }

    void txtFindText_keyPressed(KeyEvent e) {
        //Si entro por InkVenta_Matriz no debe ajustar Pedido alguno.
        if (AtuxVariables.vInkVenta_Matriz) {
            JOptionPane.showMessageDialog(this, "No es posible realizar esta operacion en Matriz", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AtuxGridUtils.aceptarTeclaPresionada(e, tblListaProd, txtFindText, 1);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana();
        } else if (tblListaProd.getRowCount() > 0) {
            try {
                if (e.getKeyCode() == KeyEvent.VK_F2 && lblAfectarPaginaT.isVisible()) {
                    actualizarValorDeTiSolicitudPedidoSiAplica();
                    afectarPagina();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    actualizarValorDeTiSolicitudPedidoSiAplica();
                    afectarTotal();
                } else if (e.getKeyCode() == KeyEvent.VK_F4 && lblAfectarProductoT.isVisible()) {
                    actualizarValorDeTiSolicitudPedidoSiAplica();
                    afectarProducto();
                }
            } catch (SQLException sqlerr) {
                AtuxUtility.showMessage(this, "Error al obtener el Tipo de Solicitud de Pedido - " + sqlerr.getErrorCode() +
                        "\n" + sqlerr.toString(), null);
                sqlerr.printStackTrace();
                return;
            }
        }
    }

    void txtFindText_keyReleased(KeyEvent e) {
        if ((e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) && (e.getKeyCode() != KeyEvent.VK_ENTER)) {
            String vFindText = txtFindText.getText().toUpperCase();
            for (int k = 0; k < tblListaProd.getRowCount(); k++) {
                String vProduct = ((String) tblListaProd.getValueAt(k, cmbFindOption.getSelectedIndex())).toUpperCase();
                if (vFindText.compareTo(vProduct) <= 0) {
                    AtuxGridUtils.showCell(tblListaProd, k, 0);
                    break;
                }
            }
        }
    }

    private void actualizarValorDeTiSolicitudPedidoSiAplica() throws SQLException {
        if (nuSolicitudPedido != null && !"".equals(nuSolicitudPedido.trim())) {
            if (tiSolicitudPedido == null || "".equals(tiSolicitudPedido)) {
                tiSolicitudPedido = DBTrasladoProducto.obtenerTipoSolicitudPedido(nuSolicitudPedido);
            }
        }
    }

    void afectarProducto() {
        String numeroPag = ((String) tblListaProd.getValueAt(tblListaProd.getSelectedRow(), 7)).trim(),
                codigoProducto = ((String) tblListaProd.getValueAt(tblListaProd.getSelectedRow(), 0)).trim(),
                numeroGuia = lblNumGuia.getText().trim();
        //  valida que el producto no se encuentre afectado
        String productoAfectado = (String) tblListaProd.getValueAt(tblListaProd.getSelectedRow(), ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_STK_AFECTADO);
        if ("S".equalsIgnoreCase(productoAfectado.trim())) {
            return;
        }
        try {
            if (!DBInventario.validaProductoInvRecepGuias(codigoProducto)) {
                JOptionPane.showMessageDialog(this, "El producto se encuentra en inventario.\n No se puede afectar.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this, "Error al validar el producto - " + sqlerr.getErrorCode() +
                    "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Esto Ud. seguro de afectar este producto?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                if (!validarVaFraccionIgualesProducto(tblListaProd.getSelectedRow())) {
                    return;
                }

                String cerrarGuia = DBTrasladoProducto.afectaPRODUCTOGUIA(nuRecepcionProducto, nuSolicitudPedido, codigoProducto, numeroPag, indicAfectaStock, numeroGuia, tiSolicitudPedido);
                boolean guiaFueCerrada = cerrarGuia.equalsIgnoreCase("S");
                
                // salmuz CmtsReplicacionPk[] cmtsReplicacionPks = replicador.replicarXProducto(guiaFueCerrada, tiSolicitudPedido, codigoProducto, numeroPag);
                AtuxUtility.aceptarTransaccion();
                // salmuz DBTrasladoProducto.forzarReplicacion(cmtsReplicacionPks);
                if (guiaFueCerrada) {
                    JOptionPane.showMessageDialog(this, "La Guía ha sido cerrada.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                    cerrarVentana();
                } else {
                    obtenerProductosGuia();
                }
            } catch (Exception e) {
                DBTrasladoProducto.procesarException(this, e, "Error al afectar el Producto de la Guía - ");
            }
        }
    }

    void afectarPagina() {
        String strNumeroGuia = lblNumGuia.getText().trim();
        DlgInvIngNumPag dlgInvIngNumPag = new DlgInvIngNumPag(parentFrame, "Ingrese Número de Página", true);
        dlgInvIngNumPag.setVisible(true);
        if (AtuxVariables.vAceptar) {
            String numeroPag = dlgInvIngNumPag.txtNumeroPagina.getText().trim();
            boolean inPag = false;
            for (int i = 0; i < tblListaProd.getRowCount(); i++) {
                if (((String) tblListaProd.getValueAt(i, 7)).trim().equalsIgnoreCase(numeroPag)) {
                    inPag = true;
                    break;
                }
            }
            if (!inPag) {
                JOptionPane.showMessageDialog(this, "No existe el Número de Página ingresado.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                if (!DBInventario.validaPagGuiaInvRecepGuias(numeroPag)) {
                    JOptionPane.showMessageDialog(this, "Productos de esta Página se encuentran en inventario.\n No se puede afectar.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (SQLException sqlerr) {
                AtuxUtility.showMessage(this, "Error al validar el producto - " + sqlerr.getErrorCode() +
                        "\n" + sqlerr.toString(), null);
                sqlerr.printStackTrace();
                return;
            }

            if (JOptionPane.showConfirmDialog(this, "Esto Ud. seguro de afectar?", "Confirmar Número de Página", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    if(!validarVaFraccionIgualesPagina(numeroPag)){
                        return;
                    }

                    String cerrarGuia = DBTrasladoProducto.afectaPAGINAGUIA(nuRecepcionProducto, nuSolicitudPedido, numeroPag, indicAfectaStock, strNumeroGuia, tiSolicitudPedido);
                    boolean guiaFueCerrada = cerrarGuia.equalsIgnoreCase("S");
                    //salmuz CmtsReplicacionPk[] cmtsReplicacionPks = replicador.replicarXPagina(guiaFueCerrada, tiSolicitudPedido, numeroPag);
                    AtuxUtility.aceptarTransaccion();
                    //salmuz DBTrasladoProducto.forzarReplicacion(cmtsReplicacionPks);
                    if (guiaFueCerrada) {
                        JOptionPane.showMessageDialog(this, "La Guía ha sido cerrada.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                        cerrarVentana();
                    } else {
                        obtenerProductosGuia();
                    }
                } catch (Exception e) {
                    DBTrasladoProducto.procesarException(this, e, "Error en la actualizacion de la Página de la Guía - ");
                }
            }
        }
    }

    void afectarTotal() {
        String strNumeroGuia = lblNumGuia.getText().trim();

        try {
            if (!DBInventario.validaTotGuiaInvRecepGuias()) {
                JOptionPane.showMessageDialog(this, "Producto de la Guía se encuentra en inventario.\n No se puede afectar.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this, "Error al validar el producto - " + sqlerr.getErrorCode() +
                    "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Esto Ud. seguro de afectar la Guía?", "Confirmar Afectar toda la Guía", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if(!validarVaFraccionIgualesTotal()) {
                return;
            }
            try {
                DBTrasladoProducto.afectaGUIATOTAL(nuRecepcionProducto, nuSolicitudPedido, indicAfectaStock, strNumeroGuia, tiSolicitudPedido);
                //salmuz CmtsReplicacionPk[] replicacionPks = replicador.replicarTodo(tiSolicitudPedido);
                AtuxUtility.aceptarTransaccion();
                //salmuz DBTrasladoProducto.forzarReplicacion(replicacionPks);
                JOptionPane.showMessageDialog(this, "La Guía ha sido actualizada correctamente.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                cerrarVentana();
            } catch (Exception e) {
                DBTrasladoProducto.procesarException(this, e, "Error en la actualizacion de la Guia - ");
            }
        }
    }

    private boolean validarVaFraccionIgualesTotal() {
        boolean almacen;

        try {
            almacen = DBTrasladoProducto.esAlmacen(coLocalOrigen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar si el local es un Almacen:\n " + e.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (almacen || ConstantsTrasladoProducto.esPedidoEnUnidadBase(tiSolicitudPedido)) {
            return true;
        }

        int contProdConDistintoVaFraccion =0;
        StringBuffer errorMsg = new StringBuffer();
        for (int i = 0; i < tblListaProd.getRowCount(); i++) {
            String vaFraccionGuia = ((String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_GUIA)).trim();
            String vaFraccionLocal = ((String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_LOCAL)).trim();

            if (!vaFraccionGuia.equals(vaFraccionLocal)){
                contProdConDistintoVaFraccion++;
                String coProducto = (String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_CO_PRODUCTO);
                String deProducto = (String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_DE_PRODUCTO);
                errorMsg.append(coProducto+" "+deProducto);
                errorMsg.append("\n");
                if (contProdConDistintoVaFraccion>10){
                    break;
                }
            }
        }
        if(contProdConDistintoVaFraccion>0){
            if (contProdConDistintoVaFraccion<=10){
                JOptionPane.showMessageDialog(this, "No se puede afectar. Los siguientes Productos tienen distinta unidad fraccion:\n "+errorMsg.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "No se puede afectar. Existen Productos que tienen distinta unidad fraccion\n ", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }
            return false;
        }
        return true;
    }

    private boolean validarVaFraccionIgualesPagina(String pNuPagina) {
        boolean almacen;

        try {
            almacen = DBTrasladoProducto.esAlmacen(coLocalOrigen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar si el local es un Almacen:\n " + e.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (almacen || ConstantsTrasladoProducto.esPedidoEnUnidadBase(tiSolicitudPedido)) {
            return true;
        }

        int contProdConDistintoVaFraccion =0;
        StringBuffer errorMsg = new StringBuffer();
        for (int i = 0; i < tblListaProd.getRowCount(); i++) {
            String vaFraccionGuia = ((String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_GUIA)).trim();
            String vaFraccionLocal = ((String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_LOCAL)).trim();

            if (!((String) tblListaProd.getValueAt(i, 7)).trim().equals(pNuPagina)) {
                continue;
            }
            
            if (!vaFraccionGuia.equals(vaFraccionLocal)){
                contProdConDistintoVaFraccion++;
                String coProducto = (String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_CO_PRODUCTO);
                String deProducto = (String) tblListaProd.getValueAt(i, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_DE_PRODUCTO);
                errorMsg.append(coProducto+" "+deProducto);
                errorMsg.append("\n");
                if (contProdConDistintoVaFraccion>10){
                    break;
                }
            }
        }
        if(contProdConDistintoVaFraccion>0){
            if (contProdConDistintoVaFraccion<=10){
                JOptionPane.showMessageDialog(this, "No se puede afectar. Los siguientes Productos tienen distinta unidad fraccion:\n "+errorMsg.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "No se puede afectar. Existen Productos que tienen distinta unidad fraccion\n ", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }
            return false;
        }
        return true;
    }

    private boolean validarVaFraccionIgualesProducto(int pIndex) {
        boolean almacen;

        try {
            almacen = DBTrasladoProducto.esAlmacen(coLocalOrigen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo determinar si el local es un Almacen:\n " + e.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (almacen || ConstantsTrasladoProducto.esPedidoEnUnidadBase(tiSolicitudPedido)) {
            return true;
        }

        StringBuffer errorMsg = new StringBuffer();
        String vaFraccionGuia = ((String) tblListaProd.getValueAt(pIndex, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_GUIA)).trim();
        String vaFraccionLocal = ((String) tblListaProd.getValueAt(pIndex, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_VA_FRACCION_LOCAL)).trim();

        if (!vaFraccionGuia.equals(vaFraccionLocal)){
            String coProducto = (String) tblListaProd.getValueAt(pIndex, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_CO_PRODUCTO);
            String deProducto = (String) tblListaProd.getValueAt(pIndex, ConstantsTrasladoProducto.LISTA_PRODUCTOS_DE_GUIA_DE_PRODUCTO);
            errorMsg.append(coProducto+" "+deProducto);
            JOptionPane.showMessageDialog(this, "No se puede afectar el siguiente Producto tiene distinta unidad fraccion:\n "+errorMsg.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);

            return false;
        }

        return true;
    }

    void cmbFindOption_actionPerformed(ActionEvent e) {
        int opcion = cmbFindOption.getSelectedIndex();
        int columna = 0;
        if (opcion == 0) columna = 0; //  Ordenado por Codigo
        else if (opcion == 1) columna = 1; //  Ordenado por Descripcion
        else if (opcion == 2) columna = 7; //  Ordenado por Página
        Collections.sort(tableModelListaProductosDeGuia.data, new AtuxTableComparator(columna, true));
        tblListaProd.repaint();
    }

    void tblListaProd_mouseReleased(MouseEvent e) {
        txtFindText.requestFocus();
    }

    void cmbFindOption_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) txtFindText.requestFocus();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) cerrarVentana();
    }

    void btnRelacion_actionPerformed(ActionEvent e) {
        colocarFocoEnLista();
    }

    void btnBuscar_actionPerformed(ActionEvent e) {
        cmbFindOption.requestFocus();
    }

    void this_windowOpened(WindowEvent e) {
        if (tblListaProd.getRowCount() > 0){
            AtuxUtility.setearPrimerRegistro(tblListaProd, txtFindText, 1);
        }
        txtFindText.requestFocus();
    }

    void colocarFocoEnLista() {
        if (tblListaProd.getRowCount() > 0) {
            AtuxUtility.setearPrimerRegistro(tblListaProd, txtFindText, 1);
        }
        txtFindText.requestFocus();
    }

    void cerrarVentana() {
        this.setVisible(false);
        this.dispose();
    }

}