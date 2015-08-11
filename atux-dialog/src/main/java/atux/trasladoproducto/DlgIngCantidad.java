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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;
import app.JVentas;
import atux.inventario.reference.DBInventario;
import atux.trasladoproducto.reference.CustomValidator;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;


public class DlgIngCantidad extends JDialog {
    Frame parentFrame;
    ImageIcon iconAceptar = new ImageIcon(JVentas.class.getResource("/atux/resources/agregar.png"));
    ImageIcon iconCancelar = new ImageIcon(JVentas.class.getResource("/atux/resources/cancel.png"));

    int longitudTexto = 0;
    public boolean firstTime = false;
    String inProdFraccionado = "";
    int valorFraccion = 0;
    public static int cantEnteraOld = 0;
    String tipoPedido;
    String unidadBase;
    String unidadFraccion;
    boolean debeUsarseUnidadBase =false;

    private CustomValidator customValidator;
    private CustomValidator customValidatorFaltanteCD;

    public JTextField txtCantidad = new JTextField();
    JLabel lblCodigo = new JLabel();
    JLabel lblDescripcion = new JLabel();
    JLabel lblUnidad = new JLabel();
    JButton btnAceptar = new JButton();
    JLabel lblLaboratorioT = new JLabel();
    JLabel lblLaboratorio = new JLabel();
    JLabel lblGrupoT = new JLabel();
    JLabel lblLineaT = new JLabel();
    JLabel lblGrupo = new JLabel();
    JLabel lblLinea = new JLabel();
    JLabel lblCodigoT = new JLabel();
    JLabel lblDescripcionT = new JLabel();
    JButton btnCancelar = new JButton();
    JPanel jPanel1 = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JPanel pnlStock = new JPanel();
    JLabel lblStockTexto = new JLabel();
    XYLayout xYLayout2 = new XYLayout();
    JLabel lblFechaHora = new JLabel();
    JLabel lblStock = new JLabel();
    JLabel lblUnidades = new JLabel();
    JLabel lblValorFrac = new JLabel();
    JLabel lblValorFraccion = new JLabel();
    int stockDisponible = 0;
    JButton btnEntero = new JButton();
    private boolean validarCantidaMayorQueCero = false;
    private boolean validarQueCantidadEsteDentroDeStock = false;

    public DlgIngCantidad() {
        this(null, "", false);
    }

    public DlgIngCantidad(Frame parent, String title, boolean modal) {
       super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            AtuxUtility.centrarVentana(this);
            // Obteniendo FechaHora para mostrar Stock
            lblFechaHora.setText(AtuxSearch.getFechaHoraBD(2));
            // Obteniendo Grupo del Producto
            lblGrupo.setText(DBInventario.getGrupoProducto());
            // Obteniendo Linea del Producto
            DBInventario.getLineaProducto();
            lblLinea.setText(DBInventario.getLineaProducto());
            // Obteniendo el Stock para el producto
            lblStock.setText("0");
            stockDisponible = DBInventario.stockDisponibleProducto();
            lblStock.setText(String.valueOf(stockDisponible));
            //
            lblLaboratorio.setText(DBInventario.getDescLaboratorio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(455, 372));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Ingreso de Cantidad");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        lblCodigo.setText("999999");
        lblCodigo.setFont(new Font("SansSerif", 0, 11));
        lblDescripcion.setFont(new Font("SansSerif", 0, 11));
        lblUnidad.setText("FCO/30 ML");
        lblUnidad.setFont(new Font("SansSerif", 0, 11));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBounds(new Rectangle(195, 295, 110, 30));
        btnAceptar.setMnemonic('a');
        btnAceptar.setFont(new Font("SansSerif", 1, 11));
        btnAceptar.setIcon(iconAceptar);
        btnAceptar.setRequestFocusEnabled(false);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAceptar_actionPerformed(e);
            }
        });
        txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
        txtCantidad.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtCantidad_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtCantidad_keyReleased(e);
            }
        });
        lblLaboratorioT.setText("Laboratorio");
        lblLaboratorioT.setFont(new Font("SansSerif", 1, 11));
        lblLaboratorio.setText("PERFUMERIAS UNIDAS");
        lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
        lblGrupoT.setText("Grupo");
        lblGrupoT.setFont(new Font("SansSerif", 1, 11));
        lblLineaT.setText("Linea");
        lblLineaT.setFont(new Font("SansSerif", 1, 11));
        lblGrupo.setText("COLONIAS,LOCIONES (PERFUMERIA)");
        lblGrupo.setFont(new Font("SansSerif", 0, 11));
        lblLinea.setText("PERFUMERIA SELE");
        lblLinea.setFont(new Font("SansSerif", 0, 11));
        lblCodigoT.setText("Codigo");
        lblCodigoT.setFont(new Font("SansSerif", 1, 11));
        lblDescripcionT.setText("Descripcion");
        lblDescripcionT.setFont(new Font("SansSerif", 1, 11));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(new Rectangle(320, 295, 110, 30));
        btnCancelar.setRequestFocusEnabled(false);
        btnCancelar.setMnemonic('c');
        btnCancelar.setFont(new Font("SansSerif", 1, 11));
        btnCancelar.setIcon(iconCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelar_actionPerformed(e);
            }
        });
        jPanel1.setBounds(new Rectangle(15, 75, 415, 215));
        jPanel1.setLayout(xYLayout1);
        jPanel1.setFont(new Font("SansSerif", 0, 11));
        jPanel1.setBackground(SystemColor.inactiveCaptionText);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        pnlStock.setBounds(new Rectangle(15, 15, 415, 55));
        pnlStock.setFont(new Font("SansSerif", 0, 11));
        pnlStock.setBackground(SystemColor.inactiveCaptionText);
        pnlStock.setLayout(xYLayout2);
        pnlStock.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        lblStockTexto.setText("Stock del Producto al");
        lblStockTexto.setFont(new Font("SansSerif", 0, 11));
        lblFechaHora.setText("dd/mm/yyyy hh:mm:ss");
        lblFechaHora.setFont(new Font("SansSerif", 0, 11));
        lblStock.setText("999,990");
        lblStock.setFont(new Font("SansSerif", 1, 11));
        lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUnidades.setText("unidades");
        lblUnidades.setFont(new Font("SansSerif", 0, 11));
        lblValorFrac.setText("Valor Frac");
        lblValorFrac.setFont(new Font("SansSerif", 1, 11));
        lblValorFraccion.setText("50");
        lblValorFraccion.setFont(new Font("SansSerif", 0, 11));
        btnEntero.setText("Cantidad");
        btnEntero.setMnemonic('t');
        btnEntero.setRequestFocusEnabled(false);
        btnEntero.setDefaultCapable(false);
        btnEntero.setBorderPainted(false);
        btnEntero.setHorizontalAlignment(SwingConstants.LEFT);
        btnEntero.setFont(new Font("SansSerif", 1, 11));
        btnEntero.setHorizontalTextPosition(SwingConstants.LEFT);
        btnEntero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEntero_actionPerformed(e);
            }
        });
        jPanel1.add(btnEntero, new XYConstraints(-1, 59, 90, 20));
        jPanel1.add(lblValorFraccion, new XYConstraints(285, 85, 55, 20));
        jPanel1.add(lblValorFrac, new XYConstraints(285, 65, 70, 20));
        jPanel1.add(lblDescripcionT, new XYConstraints(80, 10, 95, 20));
        jPanel1.add(lblCodigoT, new XYConstraints(15, 10, 55, 20));
        jPanel1.add(lblLinea, new XYConstraints(79, 174, 330, 20));
        jPanel1.add(lblGrupo, new XYConstraints(79, 149, 320, 20));
        jPanel1.add(lblLineaT, new XYConstraints(4, 174, 50, 20));
        jPanel1.add(lblGrupoT, new XYConstraints(4, 149, 50, 20));
        jPanel1.add(lblLaboratorio, new XYConstraints(79, 124, 305, 20));
        jPanel1.add(lblLaboratorioT, new XYConstraints(4, 124, 80, 20));
        jPanel1.add(txtCantidad, new XYConstraints(19, 84, 50, 20));
        jPanel1.add(lblUnidad, new XYConstraints(79, 84, 125, 20));
        jPanel1.add(lblDescripcion, new XYConstraints(75, 30, 295, 20));
        jPanel1.add(lblCodigo, new XYConstraints(15, 30, 55, 20));
        pnlStock.add(lblUnidades, new XYConstraints(330, 10, 60, 20));
        pnlStock.add(lblStock, new XYConstraints(270, 10, 55, 20));
        pnlStock.add(lblFechaHora, new XYConstraints(150, 10, 130, 20));
        pnlStock.add(lblStockTexto, new XYConstraints(25, 10, 125, 20));
        this.getContentPane().add(pnlStock, null);
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(btnCancelar, null);
        this.getContentPane().add(btnAceptar, null);
    }

    void btnAceptar_actionPerformed(ActionEvent e) {
        int cantidad = 0;
        if (txtCantidad.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una Cantidad válida !!!",
                    "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtCantidad.selectAll();
            txtCantidad.requestFocus();
            return;
        }
        if (txtCantidad.getText().trim().length() > 6) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una Cantidad válida.\nEl número no puede tener más de 6 dígitos. !!!",
                    "Mensaje del Sistema",
                    JOptionPane.WARNING_MESSAGE);
            txtCantidad.selectAll();
            txtCantidad.requestFocus();
            return;
        }
        if (validarCantidaMayorQueCero && AtuxUtility.getDecimalNumber(txtCantidad.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una Cantidad válida !!!",
                    "Mensaje del Sistema",
                    JOptionPane.WARNING_MESSAGE);
            txtCantidad.selectAll();
            txtCantidad.requestFocus();
            return;
        }
        cantidad = Integer.parseInt(txtCantidad.getText().trim());
        if (customValidator != null) {
            if (!customValidator.validate(cantidad)) {
                JOptionPane.showMessageDialog(this,
                        customValidator.getMsg(),
                        "Mensaje del Sistema",
                        JOptionPane.WARNING_MESSAGE);
                txtCantidad.selectAll();
                txtCantidad.requestFocus();
                return;
            }
        }

        if( customValidatorFaltanteCD != null){
            if (!customValidatorFaltanteCD.validate(cantidad)) {
                JOptionPane.showMessageDialog(this,
                        customValidatorFaltanteCD.getMsg(),
                        "Mensaje del Sistema",
                        JOptionPane.WARNING_MESSAGE);
                txtCantidad.selectAll();
                txtCantidad.requestFocus();
                return;
            }
        }

        valorFraccion = Integer.parseInt(lblValorFraccion.getText().trim());
        if (validarQueCantidadEsteDentroDeStock) {
            obtenerValorActualDeStock();
            double stockDisponibleCalculado = obtenerStockDisponibleCalculado();
            boolean excedeStock = (cantidad - cantEnteraOld) > stockDisponibleCalculado;
            if (excedeStock) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad ingresada excede a la permitida. Verifique!!!",
                        "Mensaje del Sistema",
                        JOptionPane.WARNING_MESSAGE);
                txtCantidad.selectAll();
                txtCantidad.requestFocus();
                return;
            }
        }
        txtCantidad.setText(String.valueOf(cantidad));
        AtuxVariables.vAceptar = true;
        this.setVisible(false);
        this.dispose();
    }

    private double obtenerStockDisponibleCalculado() {
        double stockDisponibleCalculado = stockDisponible;
        if ("S".equals(inProdFraccionado) && valorFraccion > 1) {
            if (debeUsarseUnidadBase) {
                stockDisponibleCalculado = stockDisponible / valorFraccion;
            }
        }
        return stockDisponibleCalculado;
    }

    void btnCancelar_actionPerformed(ActionEvent e) {
        txtCantidad.setText("");
        AtuxVariables.vAceptar = false;
        this.setVisible(false);
        this.dispose();
    }

    void this_windowOpened(WindowEvent e) {
        txtCantidad.requestFocus();
    }

    void txtCantidad_keyPressed(KeyEvent e) {
        longitudTexto = txtCantidad.getText().length();
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAceptar.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_F10) {
            e.consume();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancelar.doClick();
        }
    }

    void txtCantidad_keyReleased(KeyEvent e) {
        admitirSoloDigitos(e, txtCantidad);
    }

    String cantidadStr = "";

    private void admitirSoloDigitos(KeyEvent e, JTextField textField) {
        if (cantidadStr.length() == textField.getText().length()) {
            return;
        }
        char keyChar = e.getKeyChar();
        if (!Character.isDigit(keyChar)) {
            e.consume();
            admitirMensaje(keyChar, textField, "La tecla presionada no es válida !!!.  Solo se aceptan Números.");
        }
        cantidadStr = textField.getText();
    }

    private void admitirMensaje(char keyChar, JTextField textField, String mensaje) {
        if (!Character.isISOControl(keyChar)) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, mensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            textField.setText(cantidadStr);
            textField.requestFocus();
        }
    }


    void this_windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(this, "Usted esto cerrando la ventana de manera incorrecta !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
    }

    void btnEntero_actionPerformed(ActionEvent e) {
        txtCantidad.selectAll();
        txtCantidad.requestFocus();
    }

    void obtenerValorActualDeStock() {
        try {
            stockDisponible = DBInventario.stockDisponibleProducto();
            mostrarValorDeStock();
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this, "Error al obtener el Stock del Producto !!! - " + sqlerr.getErrorCode() +
                    "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }
    }

    public void setValidarCantidaMayorQueCero(boolean validarCantidaMayorQueCero) {
        this.validarCantidaMayorQueCero = validarCantidaMayorQueCero;
    }

    public void setValidarQueCantidadEsteDentroDeStock(boolean validarQueCantidadEsteDentroDeStock) {
        this.validarQueCantidadEsteDentroDeStock = validarQueCantidadEsteDentroDeStock;
    }

    public void setCustomValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    public void inicializar() {
        lblUnidades.setText(unidadBase);
        lblUnidad.setText(unidadBase);
        if ("S".equals(inProdFraccionado) && valorFraccion > 1) {
            if (debeUsarseUnidadBase) {
                // Calcular stock en unidad base
                mostrarValorDeStock();
            } else {
                lblUnidades.setText(unidadFraccion);
                lblUnidad.setText(unidadFraccion);
            }
        }
    }

    private void mostrarValorDeStock() {
        lblStock.setText(String.valueOf(stockDisponible));
        if ("S".equals(inProdFraccionado) && valorFraccion > 1) {
            if (debeUsarseUnidadBase) {
                // Calcular stock en unidad base
                lblStock.setText((stockDisponible / valorFraccion) + " / " + (stockDisponible % valorFraccion));
            }
        }
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public void setUnidadBase(String unidadBase) {
        this.unidadBase = unidadBase;
    }

    public void setUnidadFraccion(String unidadFraccion) {
        this.unidadFraccion = unidadFraccion;
    }

    public void setDebeUsarseUnidadBase(boolean debeUsarseUnidadBase) {
        this.debeUsarseUnidadBase = debeUsarseUnidadBase;
    }

    public void setCustomValidatorFaltanteCD(CustomValidator customValidator) {
        this.customValidatorFaltanteCD = customValidator;
    }
    
    public static void main(String[] args) {
    	System.out.println(JVentas.class.getResource("JVentas.class"));
    	System.out.println(JVentas.class.getResource("../JStatusBar.class"));
    	System.out.println(JVentas.class.getResource("../atux/EncriptadorPassword.class"));
    	System.out.println(JVentas.class.getResource("../atux/resources/EncriptadorPassword.class"));
    	System.out.println(JVentas.class.getResource("../atux/resources/agregar.png"));
	}
    
}