/*
* Copyright (c) 2001 Eckerd Peru S.A.
*
* @author Rolando Castro Moron
* @version 1.0
*
* Entorno de Desarrollo   : Oracle JDeveloper 3.2.3
* Nombre de la Aplicacion : ListaPrecios.java
*
* Historico de Creacion/Modificacion
* RCASTRO       11.11.2002   Creacion
* CRAMIREZ      19.11.2002   Modificacion (estandarizacion segon
*                            AtuxTableModel y AtuxTableComparator).
* 
*/
package atux.inventario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;
import app.JVentas;
import atux.inventario.reference.ConstantsInventario;
import atux.inventario.reference.DBInventario;
import atux.inventario.reference.VariablesInventario;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.common.AtuxTableComparator;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

/*
*  ID    PROGRAMADOR    FECHA/HORA            TIP                 OBSERVACIoNO
*  001   RAZANERO       14/04/2010 09:21:00   Modificacion         COTIZACION X COMPETENCIA (COSTEO Y CAMBIO EN PANTALLA)
*  002   JREBATTA       08/11/2010 09:21:00   Modificacion         Poner un porcentaje de variacion para las cotizaciones por competencia.
*  003   YCARRANZA      18/02/2011 18:11:11   Modificacion         Validar que los productos no sean analisis clinicos.
*  004   JREBATTA       02/04/2011 12:48:00   Modificacion         Validar que los productos no pertenezcan al laboratorio de virtuales ni de recetario magistral.
*/
public class DlgGuiaIngresoListaProd extends JDialog {
    public ArrayList arrayListProdSel;
    Frame parentFrame;
    AtuxTableModel tableModelPriceList;
    ImageIcon iconAceptar = new ImageIcon(JVentas.class.getResource("/atux/resources/agregar.png"));
    ImageIcon iconCancelar = new ImageIcon(JVentas.class.getResource("/atux/resources/cancel.png"));
    
    boolean vAceptar = false;
    int screenNumber;
    JPanel pnlCriterioBusqueda = new JPanel();
    JTextField txtFindText = new JTextField();
    JScrollPane scrRelProductosGuiaIng = new JScrollPane();
    JTable tblRelProductosGuiaIng = new JTable();
    JLabel lblSeleccionarRegistroT = new JLabel();
    JPanel pnlCabRelProductosGuiaIng = new JPanel();
    XYLayout xYLayout1 = new XYLayout();

    String descripcion = "";
    String precio = "";
    String descuento = "";
    String precioventa = "";
    String laboratorio = "";
    String cantidad = "";
    String cantidadF = "";
    String stockEnt = "";
    String stockFrac = "";
    String linea = " ";
    String bono = "";
    int valorFraccion = 0;
    JLabel lblAceptarT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JButton btnBuscar = new JButton();
    XYLayout xYLayout3 = new XYLayout();
    JLabel lblOpcionesT = new JLabel();
    JButton btnRelacion = new JButton();
    XYLayout xYLayout2 = new XYLayout();
    JLabel lblEstadoProductoInv = new JLabel();

    //Inicio ID: 002
    private double porcentajeVariacion = 0;
    //Fin ID: 002

    /**
     * Constructs a new instance.
     *
     * @param parent
     * @param title
     * @param modal
     */
    public DlgGuiaIngresoListaProd(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            AtuxUtility.setearPrimerRegistro(tblRelProductosGuiaIng, null, 0);
            pack();
            AtuxUtility.moveFocus(txtFindText);
            AtuxUtility.centrarVentana(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a new non-modal unparented instance with a blank title.
     */
    public DlgGuiaIngresoListaProd() {
        this(null, "", false);
    }

    /**
     * Initializes the state of this instance.
     */
    private void jbInit() throws Exception {
        //this.setSize(new Dimension(730, 520));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setLayout(xYLayout2);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlCriterioBusqueda.setBackground(SystemColor.control);
        pnlCriterioBusqueda.setLayout(xYLayout3);
        pnlCriterioBusqueda.setBorder(BorderFactory.createTitledBorder("Ingrese Criterio de búsqueda"));
        pnlCriterioBusqueda.setFont(new Font("SansSerif", 0, 11));
        txtFindText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                txtFindText_keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                txtFindText_keyPressed(e);
            }
        });

        scrRelProductosGuiaIng.setToolTipText("");
        scrRelProductosGuiaIng.setRequestFocusEnabled(false);
        scrRelProductosGuiaIng.setFont(new Font("SansSerif", 0, 11));
        tblRelProductosGuiaIng.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRelProductosGuiaIng.setFont(new Font("SansSerif", 0, 11));
        lblSeleccionarRegistroT.setText("[ Enter ] Seleccionar Producto");
        lblSeleccionarRegistroT.setFont(new Font("SansSerif", 1, 11));
        lblSeleccionarRegistroT.setForeground(new Color(32, 105, 29));
        pnlCabRelProductosGuiaIng.setBackground(new Color(32, 105, 29));
        pnlCabRelProductosGuiaIng.setLayout(xYLayout1);
        pnlCabRelProductosGuiaIng.setFont(new Font("SansSerif", 0, 11));
        lblAceptarT.setText("[ F10 ] Aceptar");
        lblAceptarT.setFont(new Font("SansSerif", 1, 11));
        lblAceptarT.setForeground(new Color(32, 105, 29));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setForeground(new Color(32, 105, 29));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
        btnBuscar.setMnemonic('B');
        btnBuscar.setFont(new Font("SansSerif", 1, 11));
        btnBuscar.setDefaultCapable(false);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        lblOpcionesT.setText("Opciones:");
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        btnRelacion.setText("Relacion de Productos");
        btnRelacion.setBorder(BorderFactory.createLineBorder(new Color(32, 105, 29), 1));
        btnRelacion.setBackground(new Color(32, 105, 29));
        btnRelacion.setForeground(Color.white);
        btnRelacion.setFont(new Font("SansSerif", 1, 11));
        btnRelacion.setHorizontalAlignment(SwingConstants.LEFT);
        btnRelacion.setMnemonic('r');
        btnRelacion.setDefaultCapable(false);
        btnRelacion.setRequestFocusEnabled(false);
        btnRelacion.setBorderPainted(false);
        btnRelacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacion_actionPerformed(e);
            }
        });
        xYLayout2.setWidth(730);
        xYLayout2.setHeight(505);
        lblEstadoProductoInv.setFont(new Font("SansSerif", 1, 11));
        lblEstadoProductoInv.setForeground(Color.red);
        lblEstadoProductoInv.setText("El Producto esto siendo INVENTARIADO !!!");
        tblRelProductosGuiaIng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                tblRelProdcutosGuiaIng_mouseReleased(e);
            }
        });
        pnlCriterioBusqueda.add(btnBuscar, new XYConstraints(20, 10, 65, 20));
        pnlCriterioBusqueda.add(txtFindText, new XYConstraints(100, 10, 320, 20));
        this.getContentPane().add(lblEstadoProductoInv, new XYConstraints(490, 75, 230, 20));
        this.getContentPane().add(pnlCriterioBusqueda, new XYConstraints(15, 10, 695, 65));
        pnlCabRelProductosGuiaIng.add(btnRelacion, new XYConstraints(10, 0, 160, 25));
        this.getContentPane().add(pnlCabRelProductosGuiaIng, new XYConstraints(15, 100, 695, 25));
        scrRelProductosGuiaIng.getViewport().add(tblRelProductosGuiaIng, null);
        this.getContentPane().add(scrRelProductosGuiaIng, new XYConstraints(15, 125, 695, 320));
        this.getContentPane().add(lblSalirT, new XYConstraints(635, 475, 85, 20));
        this.getContentPane().add(lblAceptarT, new XYConstraints(520, 475, 105, 20));
        this.getContentPane().add(lblSeleccionarRegistroT, new XYConstraints(40, 475, 185, 20));
        this.getContentPane().add(lblOpcionesT, new XYConstraints(15, 455, 55, 15));
        lblEstadoProductoInv.setVisible(false);
        initRelProductosGuiaIng();
    }

    void initRelProductosGuiaIng() {
        tableModelPriceList = new AtuxTableModel(ConstantsInventario.columnsProdListInv, ConstantsInventario.defaultValuesProdListInv, 0);
        AtuxUtility.initSelectList(tblRelProductosGuiaIng, tableModelPriceList, ConstantsInventario.columnsProdListInv);
    }

    void setListaProducto(ArrayList pArrayList) {
        tableModelPriceList.data = pArrayList;
        tableModelPriceList.fireTableDataChanged();
        Collections.sort(tableModelPriceList.data, new AtuxTableComparator(2, true));
    }

    void txtFindText_keyPressed(KeyEvent e) {
        if (tblRelProductosGuiaIng.getRowCount() > 0) {
            AtuxGridUtils.aceptarTeclaPresionada(e, tblRelProductosGuiaIng, txtFindText, 2);
            muestraEstadoProductoTomaInv();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                e.consume();
                try {
                    if (!(AtuxUtility.findTextInJTable(tblRelProductosGuiaIng, txtFindText.getText().trim(), 1, 2)) &&
                        !(AtuxUtility.findTextInJTable(tblRelProductosGuiaIng, AtuxSearch.getCodigoProductoBarra(txtFindText.getText().trim()), 1, 2))) {
                        AtuxUtility.showMessage(this, "Producto no encontrado segon Criterio de búsqueda !!!", txtFindText);
                        return;
                    } else {
                        muestraEstadoProductoTomaInv();
                    }

                  
                    //Inicio id: 004
                    if (esVirtualORecetario(((String)tblRelProductosGuiaIng.getValueAt(tblRelProductosGuiaIng.getSelectedRow(),1)).trim())) {
                       AtuxUtility.showMessage(this, "El producto seleccionado es un Producto Virtual o un Producto de Recetario Magistral, \nno se puede agregar al pedido de cotizacion.", txtFindText);
                       return;
                    }
                    //Fin id:004

                    if (getEstadoProductoTomaInv()) {
                        AtuxUtility.showMessage(this, "El Producto se encuentra Inmovilizado por estar siendo Inventariado", null);
                        return;
                    }
                    if (tblRelProductosGuiaIng.getSelectedRow() >= 0) {
                        ingresaCantPedida(tableModelPriceList, tblRelProductosGuiaIng, arrayListProdSel, parentFrame);
                        AtuxUtility.moveFocus(txtFindText);
                    }
                } catch (SQLException sqlerr) {
                    AtuxUtility.showMessage(this, "Error al buscar producto  - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
                    sqlerr.printStackTrace();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_F10) {
                e.consume();
                aceptaOperacion();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            e.consume();
            cancelaOperacion();
        }
    }

    void muestraEstadoProductoTomaInv() {
        if (tblRelProductosGuiaIng.getSelectedRow() >= 0) {
            String strInProductoTomaInv = "";
            strInProductoTomaInv = ((String) tblRelProductosGuiaIng.getValueAt(tblRelProductosGuiaIng.getSelectedRow(), 7)).trim();
            if (strInProductoTomaInv.equalsIgnoreCase("S")) {
                lblEstadoProductoInv.setVisible(true);
            } else {
                lblEstadoProductoInv.setVisible(false);
            }
        }
    }

    boolean getEstadoProductoTomaInv() {
        String strInProductoTomaInv = "";
        boolean blnEstadoProductoTomaInv = true;
        strInProductoTomaInv = ((String) tblRelProductosGuiaIng.getValueAt(tblRelProductosGuiaIng.getSelectedRow(), 7)).trim();
        if (strInProductoTomaInv.equalsIgnoreCase("S")) {
            blnEstadoProductoTomaInv = true;
        } else {
            blnEstadoProductoTomaInv = false;
        }
        return blnEstadoProductoTomaInv;
    }

    void txtFindText_keyReleased(KeyEvent e) {
        if ((e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) && (e.getKeyCode() != KeyEvent.VK_ENTER) && (e.getKeyCode() != KeyEvent.VK_ESCAPE)) {
            String vFindText = txtFindText.getText().toUpperCase();
            for (int k = 0; k < tblRelProductosGuiaIng.getRowCount(); k++) {
                String vProduct = ((String) tblRelProductosGuiaIng.getValueAt(k, 2)).toUpperCase();
                if (vFindText.compareTo(vProduct) <= 0) {
                    AtuxGridUtils.showCell(tblRelProductosGuiaIng, k, 0);
                    break;
                }
            }
        }
    }

    void tblRelProdcutosGuiaIng_mouseReleased(MouseEvent e) {
        if (tblRelProductosGuiaIng.getSelectedColumn() == ConstantsInventario.COL_SELECCION) {
            ingresaCantPedida(tableModelPriceList, tblRelProductosGuiaIng, arrayListProdSel, parentFrame);
        }
        AtuxUtility.moveFocus(txtFindText);
    }

    public void setListProdSel(ArrayList arrayListProd) {
        arrayListProdSel = arrayListProd;
        AtuxUtility.ponerCheckJTable(tblRelProductosGuiaIng, 1, arrayListProdSel, 0);
    }

    public void setScreenNumber(int number) {
        screenNumber = number;
    }

    public void ingresaCantPedida(AtuxTableModel tableModel, JTable table, ArrayList arrayList, Frame parentFrame) {
        int valorFraccion = 0;
        double vprecioProd = 0.00;
        Boolean valor = (Boolean) tableModel.getValueAt(table.getSelectedRow(), 0);

        if (!valor.booleanValue()) {
            int row = table.getSelectedRow();
            //esta parte deve estar aca arriba
            VariablesInventario.vCodigoProducto = (String) table.getValueAt(row, 1);
            seteaVariables(row);

            DlgGuiaIngresoCantidad dlgGuiaIngresoCantidad = new DlgGuiaIngresoCantidad(parentFrame, "Ingrese cantidad solicitada", true);
            dlgGuiaIngresoCantidad.setPorcentajeVariacion(porcentajeVariacion);
            dlgGuiaIngresoCantidad.lblDescripcion.setText(VariablesInventario.vCodigoProducto + " - " + descripcion);
            dlgGuiaIngresoCantidad.lblSActual.setText(stockEnt);
            dlgGuiaIngresoCantidad.lblLaboratorio.setText(laboratorio);
            dlgGuiaIngresoCantidad.lblLinea.setText(linea);
            dlgGuiaIngresoCantidad.lblFActual.setText(stockFrac);
            dlgGuiaIngresoCantidad.lblFraccionT.setVisible(false);
            dlgGuiaIngresoCantidad.txtCantFraccion.setVisible(false);
            // INICIO ID= 01
            if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
                // fin ID= 01
                try {
                    dlgGuiaIngresoCantidad.inProdFraccionado = DBInventario.getIndicProdFraccionado();
                    if (dlgGuiaIngresoCantidad.inProdFraccionado.equalsIgnoreCase("S")) {
                        valorFraccion = DBInventario.getValorFraccion();
                        dlgGuiaIngresoCantidad.seteaValorFaraccion(valorFraccion);
                        dlgGuiaIngresoCantidad.txtCantFraccion.setVisible(true);
                        dlgGuiaIngresoCantidad.lblFraccionT.setVisible(true);
                        dlgGuiaIngresoCantidad.lblDesFraccion.setText(((String) tblRelProductosGuiaIng.getValueAt(row, 4)).trim());
                    } else {
                        dlgGuiaIngresoCantidad.lblDesFraccion.setText(((String) tblRelProductosGuiaIng.getValueAt(row, 3)).trim());
                    }
                } catch (SQLException sqlerr) {
                    sqlerr.printStackTrace();
                    AtuxUtility.showMessage(this, "Error al obtener la fraccion del producto o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
                }

                try {
                    vprecioProd = DBInventario.getPrecioCompraProd("F");
                    dlgGuiaIngresoCantidad.txtPrecioUnitario.setText(String.valueOf(vprecioProd));
                }
                catch (SQLException sqlerr) {
                    sqlerr.printStackTrace();
                    AtuxUtility.showMessage(this, "Error el obtener el Precio de Compra del Producto (Parometro F)o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
                }
            } else if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_MATRIZ) {
                try {
                    vprecioProd = DBInventario.getPrecioCompraProd("E");
                    dlgGuiaIngresoCantidad.txtPrecioUnitario.setText("" + vprecioProd);
                }
                catch (SQLException sqlerr) {
                    sqlerr.printStackTrace();
                    AtuxUtility.showMessage(this, "Error el obtener el Precio de Compra del Producto (Parometro E) o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
                }
            } else if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
                try {
                    dlgGuiaIngresoCantidad.inProdFraccionado = DBInventario.getIndicProdFraccionado();
                    if (dlgGuiaIngresoCantidad.inProdFraccionado.equalsIgnoreCase("S")) {
                        dlgGuiaIngresoCantidad.lblDesFraccion.setText(((String) tblRelProductosGuiaIng.getValueAt(row, 4)).trim());
                    } else {
                        dlgGuiaIngresoCantidad.lblDesFraccion.setText(((String) tblRelProductosGuiaIng.getValueAt(row, 3)).trim());
                    }

                    try {
                        vprecioProd = AtuxUtility.getDecimalNumber(DBInventario.getPrecioProducto(VariablesInventario.vCodigoProducto).trim());
                        dlgGuiaIngresoCantidad.setPrecioProducto(vprecioProd);
                    } catch (SQLException sqlerr) {
                        sqlerr.printStackTrace();
                        AtuxUtility.showMessage(this, "Error el obtener el Precio del Producto o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
                    }
                } catch (SQLException sqlerr) {
                    sqlerr.printStackTrace();
                    AtuxUtility.showMessage(this, "Error al obtener la fraccion del producto o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
                }
            } else {
                dlgGuiaIngresoCantidad.txtPrecioUnitario.setText(String.valueOf(vprecioProd));
            }
            dlgGuiaIngresoCantidad.setVisible(true);
            if (AtuxVariables.vAceptar) {
                tableModel.setValueAt(new Boolean(!valor.booleanValue()), table.getSelectedRow(), 0);
                table.repaint();
                anadeListaProd(tableModel, table, arrayList, dlgGuiaIngresoCantidad.txtCantEntera.getText(),
                        dlgGuiaIngresoCantidad.txtCantFraccion.getText(), valor, dlgGuiaIngresoCantidad.txtPrecioUnitario.getText(),
                        dlgGuiaIngresoCantidad.txtFechaVencimiento.getText(), valorFraccion, dlgGuiaIngresoCantidad.txtLote.getText(),
                        dlgGuiaIngresoCantidad.inProdFraccionado);
            }
            AtuxUtility.moveFocus(table);
            AtuxGridUtils.showCell(table, row, 0);
        } else {
            tableModel.setValueAt(new Boolean(!valor.booleanValue()), table.getSelectedRow(), 0);
            table.repaint();
            anadeListaProd(tableModel, table, arrayList, "0", "0", valor, "0", "0", 0, "0", "N");
        }
    }

    public void seteaVariables(int pRowTable) {
        try {
            //antes se tear estas variables debemos obtener el codigo del producto seleccionado
            descripcion = ((String) tblRelProductosGuiaIng.getValueAt(pRowTable, 2)).trim();
            stockEnt = ((String) tblRelProductosGuiaIng.getValueAt(pRowTable, 5)).trim();
            stockFrac = ((String) tblRelProductosGuiaIng.getValueAt(pRowTable, 6)).trim();

            laboratorio = DBInventario.getDescLaboratorio();
            linea = DBInventario.getLineaProducto();
        }
        catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
            AtuxUtility.showMessage(this, "Error al obtener la descripcion del producto o " + sqlerr.getErrorCode() + "\n" + sqlerr.getMessage(), null);
        }
    }

    public static void anadeListaProd(AtuxTableModel tableModel, JTable table, ArrayList arrayList, String pCantidad,
                                      String pCantidadFrac, Boolean valor, String pPrecio, String pFechaVenc, int pValorFraccion,
                                      String pLote, String pInProdFraccionado) {
        double subTotal = 0.00;

        if (pCantidad.length() == 0) {
            pCantidad = "0";
        }

        if (pCantidadFrac.length() == 0) {
            pCantidadFrac = "0";
        }

        if (pValorFraccion > 0) {
            subTotal = (Integer.parseInt(pCantidad) * pValorFraccion * Double.parseDouble(pPrecio)) + (Integer.parseInt(pCantidadFrac) * Double.parseDouble(pPrecio));
        }

        if (pValorFraccion == 0) {
            subTotal = (Integer.parseInt(pCantidad) * Double.parseDouble(pPrecio));
        }

        ArrayList detalle = (ArrayList) tableModel.getRow(table.getSelectedRow());
        ArrayList myArray = new ArrayList();
        myArray.add(detalle.get(1));
        myArray.add(detalle.get(2));

        if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_COTIZACION) {
            if (pInProdFraccionado.equalsIgnoreCase("S")) {
                myArray.add(detalle.get(4));
            } else {
                myArray.add(detalle.get(3));
            }
        } else {
            myArray.add(detalle.get(3));
        }

        if (VariablesInventario.PANT_GUIA_INGRESO_MANUAL == ConstantsInventario.PANT_GUIA_INGRESO_LOCAL) {
            myArray.add(detalle.get(4));
            myArray.add(pCantidad);
            myArray.add(pCantidadFrac);
        } else {
            myArray.add(pCantidad);
        }
        myArray.add(AtuxUtility.formatNumber(Double.parseDouble(pPrecio), "###0.00"));
        myArray.add(AtuxUtility.formatNumber(subTotal, "###0.00"));
        myArray.add(pFechaVenc);
        myArray.add(pLote);
        AtuxUtility.operaListaProd(arrayList, myArray, new Boolean(!valor.booleanValue()), 1);
    }

    void btnBuscar_actionPerformed(ActionEvent e) {
        AtuxUtility.moveFocus(txtFindText);
    }

    void aceptaOperacion() {
        vAceptar = true;
        this.setVisible(false);
        this.dispose();
    }

    void cancelaOperacion() {
        vAceptar = false;
        this.setVisible(false);
        this.dispose();
    }

    void parentFrame_windowClosing(WindowEvent e) {
        AtuxUtility.removeAllListaProd(arrayListProdSel);
    }

    void parentFrame_windowClosed(WindowEvent e) {
        AtuxUtility.removeAllListaProd(arrayListProdSel);
    }

    void btnRelacion_actionPerformed(ActionEvent e) {
        if (tblRelProductosGuiaIng.getRowCount() > 0) {
            AtuxUtility.setearPrimerRegistro(tblRelProductosGuiaIng, txtFindText, 2);
            muestraEstadoProductoTomaInv();
        }
        AtuxUtility.moveFocus(txtFindText);
    }

    void this_windowOpened(WindowEvent e) {
        if (tblRelProductosGuiaIng.getRowCount() > 0) {
            AtuxUtility.setearPrimerRegistro(tblRelProductosGuiaIng, txtFindText, 2);
            muestraEstadoProductoTomaInv();
        }
        AtuxUtility.moveFocus(txtFindText);
    }

    public void setPorcentajeVariacion(double pPorcentajeVariacion) {
        porcentajeVariacion = pPorcentajeVariacion;
    }

       //Inicio ID: 004
    public boolean esVirtualORecetario(String pCodigo) {
        try {
            String valor = DBInventario.getProductoClinicoORecetario(pCodigo, false);

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
    //Fin ID: 004

}
