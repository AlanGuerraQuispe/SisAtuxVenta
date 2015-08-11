package atux.trasladoproducto;

import java.awt.BorderLayout;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import atux.inventario.reference.VariablesInventario;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.CustomValidator;
import atux.trasladoproducto.reference.VariablesTrasladoProducto;
import atux.util.common.AtuxGridUtils;
import atux.common.AtuxTableComparator;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DlgListaProductos extends JDialog {
    /**
     * Almacena el log
     */
    private final Log logger = LogFactory.getLog(getClass());

    Frame parentFrame;

    AtuxTableModel tableModelProductos;
    String coLocalDevolucion;

    JPanel jPanel1 = new JPanel();
    JPanel pnlBusqueda = new JPanel();
    JTextField txtFindText = new JTextField();
    JScrollPane scrRelProductos = new JScrollPane();
    JTable tblRelProductos = new JTable();
    JLabel lblSeleccionarT = new JLabel();
    XYLayout xYLayout2 = new XYLayout();
    JPanel pnlCabeceraDetalle = new JPanel();
    XYLayout xYLayout1 = new XYLayout();

    JLabel lblAceptarT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JButton btnBuscar = new JButton();
    JLabel lblOpcionesT = new JLabel();
    JLabel lblStockT = new JLabel();
    JLabel lblStock = new JLabel();
    JLabel lblFraccionT = new JLabel();
    JLabel lblFraccion = new JLabel();
    JLabel lblEstadoProdTomaInv = new JLabel();
    JButton btnRelacionProductos = new JButton();
    private String tipoPedido = "";
    private String nuRecepcionBase= "";

    /**
     * Constructs a new instance.
     *
     * @param parent
     * @param title
     * @param modal
     */
    public DlgListaProductos(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            pack();
            txtFindText.requestFocus();
            AtuxUtility.centrarVentana(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a new non-modal unparented instance with a blank title.
     */
    public DlgListaProductos() {
        this(null, "", false);
    }

    /**
     * Initializes the state of this instance.
     */
    private void jbInit() throws Exception {
        this.setSize(new Dimension(774, 529));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        jPanel1.setLayout(xYLayout2);
        jPanel1.setMinimumSize(new Dimension(740, 500));
        jPanel1.setFont(new Font("SansSerif", 0, 11));
        pnlBusqueda.setBackground(SystemColor.control);
        pnlBusqueda.setLayout(null);
        pnlBusqueda.setBorder(BorderFactory.createTitledBorder("Ingrese criterio de búsqueda"));
        pnlBusqueda.setFont(new Font("SansSerif", 0, 11));
        txtFindText.setBounds(new Rectangle(105, 30, 300, 20));
        txtFindText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                txtFindText_keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                txtFindText_keyPressed(e);
            }
        });

        scrRelProductos.setToolTipText("");
        scrRelProductos.setRequestFocusEnabled(false);
        scrRelProductos.setFont(new Font("SansSerif", 0, 11));

        // Carga Combo de Busqueda
        tblRelProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRelProductos.setFont(new Font("SansSerif", 0, 11));
        lblSeleccionarT.setText("[ Enter ] Seleccionar");
        lblSeleccionarT.setFont(new Font("SansSerif", 1, 11));
        lblSeleccionarT.setForeground(new Color(32, 105, 29));
        pnlCabeceraDetalle.setBackground(new Color(32, 105, 29));
        pnlCabeceraDetalle.setLayout(xYLayout1);
        pnlCabeceraDetalle.setFont(new Font("SansSerif", 0, 11));
        lblAceptarT.setText("[ F10 ] Aceptar");
        lblAceptarT.setForeground(new Color(32, 105, 29));
        lblAceptarT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setForeground(new Color(32, 105, 29));
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
        btnBuscar.setMnemonic('B');
        btnBuscar.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setBounds(new Rectangle(25, 30, 65, 20));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        lblOpcionesT.setText("Opciones:");
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        lblStockT.setText("Stock Entero:");
        lblStockT.setFont(new Font("SansSerif", 1, 11));
        lblStockT.setForeground(Color.white);
        lblStock.setText("0");
        lblStock.setFont(new Font("SansSerif", 1, 11));
        lblStock.setForeground(Color.white);
        lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFraccionT.setText("Fraccion:");
        lblFraccionT.setFont(new Font("SansSerif", 1, 11));
        lblFraccionT.setForeground(Color.white);
        lblFraccion.setText("0");
        lblFraccion.setFont(new Font("SansSerif", 1, 11));
        lblFraccion.setForeground(Color.white);
        lblFraccion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEstadoProdTomaInv.setFont(new Font("SansSerif", 1, 11));
        lblEstadoProdTomaInv.setText("El Producto esto siendo INVENTARIADO !!!");
        lblEstadoProdTomaInv.setForeground(Color.red);
        btnRelacionProductos.setText("Relacion de Productos");
        btnRelacionProductos.setBackground(new Color(32, 105, 29));
        btnRelacionProductos.setFont(new Font("SansSerif", 1, 11));
        btnRelacionProductos.setBorderPainted(false);
        btnRelacionProductos.setBorder(BorderFactory.createLineBorder(new Color(32, 105, 29), 1));
        btnRelacionProductos.setFocusPainted(false);
        btnRelacionProductos.setForeground(Color.white);
        btnRelacionProductos.setDefaultCapable(false);
        btnRelacionProductos.setMnemonic('r');
        btnRelacionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacionProductos_actionPerformed(e);
            }
        });
        tblRelProductos.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                tblRelProductos_keyReleased(e);
            }
        });
        tblRelProductos.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                tblRelProductos_mouseReleased(e);
            }
        });
        jPanel1.add(lblEstadoProdTomaInv, new XYConstraints(490, 75, 230, 15));
        jPanel1.add(lblOpcionesT, new XYConstraints(30, 435, 55, 16));
        jPanel1.add(lblSalirT, new XYConstraints(650, 455, 85, 20));
        jPanel1.add(lblAceptarT, new XYConstraints(540, 455, 105, 20));
        jPanel1.add(pnlCabeceraDetalle, new XYConstraints(25, 75, 695, 30));
        scrRelProductos.getViewport().add(tblRelProductos, null);
        jPanel1.add(scrRelProductos, new XYConstraints(25, 105, 695, 320));
        jPanel1.add(pnlBusqueda, new XYConstraints(25, 5, 695, 65));
        jPanel1.add(lblSeleccionarT, new XYConstraints(30, 455, 125, 20));
        pnlCabeceraDetalle.add(btnRelacionProductos, new XYConstraints(5, 10, 140, 15));
        pnlCabeceraDetalle.add(lblFraccion, new XYConstraints(625, 10, 40, 15));
        pnlCabeceraDetalle.add(lblFraccionT, new XYConstraints(565, 10, 55, 15));
        pnlCabeceraDetalle.add(lblStock, new XYConstraints(515, 10, 40, 15));
        pnlCabeceraDetalle.add(lblStockT, new XYConstraints(430, 10, 85, 15));
        pnlBusqueda.add(btnBuscar, null);
        pnlBusqueda.add(txtFindText, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        initListaPrecios();
        lblEstadoProdTomaInv.setVisible(false);
    }

    boolean  debeUsarseUnidadBase = false;
    public void initProcess() throws SQLException {
        debeUsarseUnidadBase = ConstantsTrasladoProducto.debeUsarseUnidadBase(tipoPedido,coLocalDevolucion);
        AtuxUtility.setearPrimerRegistro(tblRelProductos, txtFindText, 2);
        VariablesTrasladoProducto.arrayProductos = new ArrayList();
        for (int i = 0; i < VariablesTrasladoProducto.arrayTransferencia.size(); i++) {
            VariablesTrasladoProducto.arrayProductos.add(VariablesTrasladoProducto.arrayTransferencia.get(i));
        }
        AtuxUtility.ponerCheckJTable(tblRelProductos, 1, VariablesTrasladoProducto.arrayProductos, 0);
        txtFindText.requestFocus();
    }

    void initListaPrecios() {
        tableModelProductos = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaProductos,
                ConstantsTrasladoProducto.defaultValuesListaProductos, 0);
        AtuxUtility.initSelectList(tblRelProductos,
                tableModelProductos, ConstantsTrasladoProducto.columnsListaProductos);
    }

    void setListaProducto(ArrayList pArrayList) {
        tableModelProductos.data = pArrayList;
        tableModelProductos.fireTableDataChanged();
        Collections.sort(tableModelProductos.data, new AtuxTableComparator(2, true));
    }

    void txtFindText_keyPressed(KeyEvent e) {
        AtuxGridUtils.aceptarTeclaPresionada(e, tblRelProductos, txtFindText, 2);
        mostrarEstadoProductoTomaInv();
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            if (tblRelProductos.getSelectedRow() >= 0) {
                if (!(AtuxUtility.findTextInJTable(tblRelProductos, txtFindText.getText().trim(), 1, 2))) {
                    JOptionPane.showMessageDialog(this,
                            "Producto no encontrado segon Criterio de búsqueda !!!",
                            "Mensaje del Sistema",
                            JOptionPane.WARNING_MESSAGE);
                    txtFindText.selectAll();
                    txtFindText.requestFocus();
                    return;
                } else {
                    mostrarEstadoProductoTomaInv();
                }
                if (productoEstaSiendoInventariado()) {
                    JOptionPane.showMessageDialog(this,
                            "El Producto se encuentra Inmovilizado por estar siendo Inventariado",
                            "Mensaje del Sistema",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Inicio Id : 001
                boolean esProductoClinico = esAnalisisClinico(((String)tblRelProductos.getValueAt(tblRelProductos.getSelectedRow(),1)).trim());
                if(esProductoClinico){
                   AtuxUtility.showMessage(this, "El producto es un anolisis clinico, no se puede agregar al pedido de traslado.", txtFindText);
                   return;
                }
                // Fin Id : 001

                //Inicio ID: 002
                if (esVirtualORecetario(((String)tblRelProductos.getValueAt(tblRelProductos.getSelectedRow(),1)).trim())) {
                    AtuxUtility.showMessage(this, "El producto seleccionado es un Producto Virtual o un Producto de Recetario Magistral, \nno se puede agregar al pedido de traslado.", txtFindText);
                    return;
                }
                //Fin ID: 002

                ingresaCantPedida();
                txtFindText.requestFocus();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F10) {
            aceptarSeleccion();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cancelarSeleccion();
        }
    }

    void mostrarEstadoProductoTomaInv() {
        if (tblRelProductos.getSelectedRow() >= 0) {
            String strInProductoTomaInv = ((String) tblRelProductos.getValueAt(tblRelProductos.getSelectedRow(), 7)).trim();
            lblEstadoProdTomaInv.setVisible(strInProductoTomaInv.equalsIgnoreCase("S"));
        }
    }

    private boolean productoEstaSiendoInventariado() {
        String strInProductoTomaInv = ((String) tblRelProductos.getValueAt(tblRelProductos.getSelectedRow(), 7)).trim();
        return "S".equalsIgnoreCase(strInProductoTomaInv);
    }

    void txtFindText_keyReleased(KeyEvent e) {
        if ((e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) && (e.getKeyCode() != KeyEvent.VK_ENTER)) {
            String vFindText = txtFindText.getText().toUpperCase();
            for (int k = 0; k < tblRelProductos.getRowCount(); k++) {
                String vProduct = ((String) tblRelProductos.getValueAt(k, 2)).toUpperCase();
                if (vFindText.compareTo(vProduct) <= 0) {
                    AtuxGridUtils.showCell(tblRelProductos, k, 0);
                    break;
                }
            }
        } else {
            if (tblRelProductos.getRowCount() > 0) {
                actualizarLabelDeStock();
            }
        }
    }

    void tblRelProductos_mouseReleased(MouseEvent e) {
        if (tblRelProductos.getSelectedColumn() == ConstantsTrasladoProducto.COL_SELECCION) {
            ingresaCantPedida();
        }
        txtFindText.requestFocus();
    }

    public void ingresaCantPedida() {
        int row = tblRelProductos.getSelectedRow();
        Boolean productoTieneCheck = (Boolean) tblRelProductos.getValueAt(tblRelProductos.getSelectedRow(), 0);
        if (!productoTieneCheck.booleanValue()) {
            openDlgIngresoCantidad(tableModelProductos, tblRelProductos, productoTieneCheck);
        } else {
            tblRelProductos.setValueAt(new Boolean(!productoTieneCheck.booleanValue()), tblRelProductos.getSelectedRow(), 0);
            tblRelProductos.repaint();
            String coProducto = ((String) tblRelProductos.getValueAt(row, 1)).trim();
            VariablesTrasladoProducto.vCodigoProducto = coProducto;
            for (int i = 0; i < VariablesTrasladoProducto.arrayProductos.size(); i++) {
                if (((String) ((ArrayList) VariablesTrasladoProducto.arrayProductos.get(i)).get(0)).trim().equalsIgnoreCase(coProducto)) {
                    VariablesTrasladoProducto.arrayProductos.remove(i);
                    break;
                }
            }
        }
    }

    
	public static void operaListaProd(AtuxTableModel tableModel, JTable table, String pCantidad, Boolean valor,
                                      String pDescLaboratorio) {
        ArrayList detalle = tableModel.getRow(table.getSelectedRow());
        VariablesTrasladoProducto.arrayElementos = new ArrayList();
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_CO_PRODUCTO));
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_DE_PRODUCTO));
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_UN_PRODUCTO));
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_UN_FRACCION_PRODUCTO));
        VariablesTrasladoProducto.arrayElementos.add(pCantidad);
        VariablesTrasladoProducto.arrayElementos.add(pDescLaboratorio);
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_IN_PROD_INV));
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_IN_PROD_FRACCIONADO));
        VariablesTrasladoProducto.arrayElementos.add(detalle.get(ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_VA_FRACCION));
        AtuxUtility.operaListaProd(VariablesTrasladoProducto.arrayProductos, VariablesTrasladoProducto.arrayElementos, new Boolean(!valor.booleanValue()), 1);
    }

    private void btnBuscar_actionPerformed(ActionEvent e) {
        txtFindText.requestFocus();
    }


    void openDlgIngresoCantidad(AtuxTableModel tableModel, JTable table, Boolean valor) {
        int row = tblRelProductos.getSelectedRow();
        if (row >= 0) {
            VariablesTrasladoProducto.vCodigoProducto = (String) tblRelProductos.getValueAt(row, 1);
            VariablesInventario.vCodigoProducto = VariablesTrasladoProducto.vCodigoProducto;
            String descripcion = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_DE_PRODUCTO)).trim();
            String unidad = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_UN_PRODUCTO)).trim();
            String unidadFraccion = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_UN_FRACCION_PRODUCTO)).trim();
            String inProdFraccionado = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_IN_PROD_FRACCIONADO)).trim();
            DlgIngCantidad dlgIngCantidad = new DlgIngCantidad(parentFrame, "Ingreso de Cantidad", true);
            dlgIngCantidad.setValidarCantidaMayorQueCero(true);
            dlgIngCantidad.lblCodigo.setText(VariablesTrasladoProducto.vCodigoProducto);
            dlgIngCantidad.lblDescripcion.setText(descripcion);
            dlgIngCantidad.txtCantidad.selectAll();
            int valorFraccion = obtenerVaFraccion(row);
            dlgIngCantidad.inProdFraccionado = inProdFraccionado;
            dlgIngCantidad.valorFraccion = valorFraccion;
            dlgIngCantidad.lblValorFraccion.setVisible(true);
            dlgIngCantidad.lblValorFraccion.setText(String.valueOf(valorFraccion));
            dlgIngCantidad.lblValorFrac.setVisible(true);
            validarQueCantidadNoExcedaCantidadDespachada(row, dlgIngCantidad);
            validarQueCantidadNoExcedaCantidadGuiaMatrix(row, dlgIngCantidad);
            dlgIngCantidad.setUnidadBase(unidad);
            dlgIngCantidad.setUnidadFraccion(unidadFraccion);
            dlgIngCantidad.setTipoPedido(tipoPedido);
            dlgIngCantidad.setDebeUsarseUnidadBase(debeUsarseUnidadBase);
            dlgIngCantidad.inicializar();
            dlgIngCantidad.setVisible(true);
            if (AtuxVariables.vAceptar) {
                AtuxVariables.vAceptar = false;
                tblRelProductos.setValueAt(new Boolean(!valor.booleanValue()), tblRelProductos.getSelectedRow(), 0);
                table.repaint();
                operaListaProd(tableModel, table,
                        dlgIngCantidad.txtCantidad.getText(),
                        valor,
                        dlgIngCantidad.lblLaboratorio.getText());
            }
            actualizarLabelDeStock();
        }
    }

    private void validarQueCantidadNoExcedaCantidadDespachada(int row, DlgIngCantidad dlgIngCantidad) {
        if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE.equals(tipoPedido) ||
                ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(tipoPedido)) {
            final String caDespachadaStr = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_CA_RECEPCIONADA)).trim();
            dlgIngCantidad.setCustomValidator(new CustomValidator("La cantidad NO debe ser mayor que la cantidad Entregada:" + caDespachadaStr) {
                public boolean validate(int cantidad) {
                    int caDespachada = (caDespachadaStr == null || "".equals(caDespachadaStr)) ? 0 : Integer.parseInt(caDespachadaStr);
                    return cantidad <= caDespachada;
                }
            });
        }
    }

    
    private void validarQueCantidadNoExcedaCantidadGuiaMatrix(int row, DlgIngCantidad dlgIngCantidad) {
       try {
            if (ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD.equals(tipoPedido)) {
                String caDespachadaStr = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_CA_RECEPCIONADA)).trim();
                String coProducto = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_CO_PRODUCTO)).trim();
                String cantReservadaFaltanteCD = DBInventario.getCantidadReservada(tipoPedido, nuRecepcionBase, coProducto, ConstantsTrasladoProducto.CO_LOCAL_CENTRAL);
                int caDespachada = (caDespachadaStr == null || "".equals(caDespachadaStr)) ? 0 : Integer.parseInt(caDespachadaStr);
                int caReservada  = (cantReservadaFaltanteCD == null || "".equals(cantReservadaFaltanteCD)) ? 0 : Integer.parseInt(cantReservadaFaltanteCD);
                final int caRestante =  caDespachada -  caReservada;
                dlgIngCantidad.setCustomValidatorFaltanteCD(new CustomValidator("La suma de la cantidad ingresada mas la cantidad registrada " +
                        "\nen los otros pedidos NO debe ser mayor a :" + caRestante) {
                    public boolean validate(int cantidad) {
                        return cantidad <= caRestante;
                    }
                });
            }
        } catch (SQLException ex) {
           logger.error("No se puede obtener la informacion de la base de datos.",ex);
           AtuxUtility.showErrorMessage(this,"No se puede obtener la informacion de la base de datos.",null);
        }
    }
    // Fin ID: 002

    /**
     * Devuelve el vaFraccion si es nulo o cero, se devuelve 1
     */
    private int obtenerVaFraccion(int row) {
        String vaFraccionStr = ((String) tblRelProductos.getValueAt(row, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_VA_FRACCION)).trim();
        int vaFraccion = vaFraccionStr == null || "".equals(vaFraccionStr) || "0".equals(vaFraccionStr) ? 1 : Integer.parseInt(vaFraccionStr);
        return vaFraccion;
    }


    void actualizarLabelDeStock() {
        int selectedRow = tblRelProductos.getSelectedRow();
        if (selectedRow <= 0) {
            AtuxGridUtils.showCell(tblRelProductos, 0, 0);
        }
        try {
            String coProducto = ((String) tblRelProductos.getValueAt(selectedRow, ConstantsTrasladoProducto.LISTA_PRODUCTOS_COL_CO_PRODUCTO)).trim();
            ArrayList datosProducto = DBInventario.obtieneDatosProducto(coProducto);
            String in_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(0)).trim();
            String stock_total = ((String) ((ArrayList) datosProducto.get(0)).get(1)).trim();
            String stock_entero = ((String) ((ArrayList) datosProducto.get(0)).get(2)).trim();
            String stock_fraccion = ((String) ((ArrayList) datosProducto.get(0)).get(3)).trim();
            boolean esProdFraccionado = in_fraccion.equalsIgnoreCase("S");
            lblFraccion.setVisible(esProdFraccionado);
            lblFraccionT.setVisible(esProdFraccionado);
            lblStock.setText(stock_entero);
            lblFraccion.setText(stock_fraccion);
            tblRelProductos.setValueAt(lblStock.getText().trim(), selectedRow, 5);
            tblRelProductos.setValueAt(lblFraccion.getText().trim(), selectedRow, 6);
            tblRelProductos.repaint();
            AtuxGridUtils.showCell(tblRelProductos, selectedRow, 0);
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this,
                    "Error al obtener el Stock del Producto !!! - " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }
    }

    void tblRelProductos_keyReleased(KeyEvent e) {
        if (tblRelProductos.getRowCount() > 0) {
            actualizarLabelDeStock();
        }
    }

    void cancelarSeleccion() {
        closeWindow(false);
    }

    void aceptarSeleccion() {
        VariablesTrasladoProducto.arrayTransferencia = new ArrayList();
        if (logger.isInfoEnabled()) {
            logger.info("Se van a agregar al pedido:" + VariablesTrasladoProducto.arrayProductos.size() + " productos");
        }
        for (int i = 0; i < VariablesTrasladoProducto.arrayProductos.size(); i++) {
            VariablesTrasladoProducto.arrayTransferencia.add((ArrayList) VariablesTrasladoProducto.arrayProductos.get(i));
        }
        closeWindow(true);
    }

    void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        VariablesTrasladoProducto.arrayProductos = new ArrayList();
        tableModelProductos.data = new ArrayList();
        this.setVisible(false);
        this.dispose();
    }

    void btnRelacionProductos_actionPerformed(ActionEvent e) {
        if (tblRelProductos.getRowCount() > 0) {
            AtuxUtility.setearPrimerRegistro(tblRelProductos, txtFindText, 2);
            mostrarEstadoProductoTomaInv();
        }
        txtFindText.requestFocus();
    }

    void this_windowOpened(WindowEvent e) {
        if (tblRelProductos.getRowCount() > 0) {
            AtuxUtility.setearPrimerRegistro(tblRelProductos,
                    txtFindText, 2);
            mostrarEstadoProductoTomaInv();
        }
        txtFindText.requestFocus();
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public void setCoLocalDevolucion(String coLocalDevolucion) {
        this.coLocalDevolucion = coLocalDevolucion;
    }

    //Inicio ID: 001
   public boolean esAnalisisClinico(String pCodigo){
        /*Iterator it = VariablesVentas.arrayAnalisisClinicos.iterator();
        while(it.hasNext()){
           ArrayList datos = (ArrayList)it.next();
           if(datos.get(ConstantsVentas.vColCodProdAnalisisClinico).equals(pCodigo)){
               return true;
           }
        }*/
        return false;
    }
    //Fin ID: 001

    //Inicio ID: 002
    public boolean esVirtualORecetario(String pCodigo) {
        try {
            String valor = DBInventario.getProductoClinicoORecetario(pCodigo, true);

            if (valor.equals("0")) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    //Fin ID: 002


    //Inicio ID: 002
    public void setNuRecepcionBase(String nuRecepcionBase) {
        this.nuRecepcionBase = nuRecepcionBase;
    }
    //Fin ID: 002
}
