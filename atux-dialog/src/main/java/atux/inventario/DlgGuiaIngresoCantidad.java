package atux.inventario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import atux.common.AtuxDocumentEditor;
import atux.inventario.reference.VariablesInventario;
import atux.util.AtuxLengthText;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;


public class DlgGuiaIngresoCantidad extends JDialog {
	String inProdFraccionado = "";
	int valorFraccion = 0;
	int longitudTexto = 0;
	int longitudTextoFechaVence = 0;
	JPanel jPanel1 = new JPanel();
	JLabel lblDescripcion = new JLabel();
	JPanel pnlIngresoCantidades = new JPanel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel lblLaboratorio = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel lblFActual = new JLabel();
	JLabel lblLinea = new JLabel();
	JLabel lblPrecioUnitarioT = new JLabel();
	JLabel lblFechaVencT = new JLabel();
	JLabel lblAceptarT = new JLabel();
	JTextField txtCantEntera = new JTextField();
	JTextField txtPrecioUnitario = new JTextField();
	JTextField txtFechaVencimiento = new JTextField();
	JLabel lblFormatoFechaT = new JLabel();
	JLabel lblSalirT = new JLabel();
	JLabel lblFraccionT = new JLabel();
	JTextField txtCantFraccion = new JTextField();
	JLabel jLabel13 = new JLabel();
	JLabel lblSActual = new JLabel();
	JPanel lblCabeceraDetalleProd = new JPanel();
	JLabel lblDetalleProductoT = new JLabel();
	JLabel lblOpcionesT = new JLabel();
	JButton btnEntero = new JButton();
	JLabel lblLoteT = new JLabel();
	JTextField txtLote = new JTextField();
	JLabel jLabel1 = new JLabel();
	JLabel lblValorFraccion = new JLabel();
	JLabel lblDesFraccion = new JLabel();

	// Inicio ID: 001
	private double porcentajeVariacion = 0;
	private double precioProducto = 0;

	// Fin ID: 001

	public DlgGuiaIngresoCantidad() {
		this(null, "", false);
	}

	public DlgGuiaIngresoCantidad(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		try {
			jbInit();
			AtuxUtility.centrarVentana(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jbInit() throws Exception {
		this.setSize(new Dimension(482, 361));
		this.getContentPane().setLayout(null);
		this.setFont(new Font("SansSerif", 0, 11));
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				this_windowOpened(e);
			}
		});
		jPanel1.setBounds(new Rectangle(15, 30, 440, 145));
		jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		jPanel1.setLayout(null);
		jPanel1.setFont(new Font("SansSerif", 0, 11));
		lblDescripcion.setText("981015 - AB BONCOL");
		lblDescripcion.setBounds(new Rectangle(90, 15, 270, 20));
		lblDescripcion.setFont(new Font("SansSerif", 0, 11));
		pnlIngresoCantidades.setBounds(new Rectangle(15, 185, 440, 90));
		pnlIngresoCantidades.setBorder(BorderFactory.createTitledBorder("Ingrese cantidades"));
		pnlIngresoCantidades.setLayout(null);
		pnlIngresoCantidades.setFont(new Font("SansSerif", 0, 11));
		jLabel2.setText("Descripcion:");
		jLabel2.setBounds(new Rectangle(10, 15, 80, 20));
		jLabel2.setFont(new Font("SansSerif", 1, 11));
		jLabel3.setText("Laboratorio:");
		jLabel3.setBounds(new Rectangle(10, 90, 75, 20));
		jLabel3.setFont(new Font("SansSerif", 1, 11));
		lblLaboratorio.setText("QUIMICA SUIZA S.A.");
		lblLaboratorio.setBounds(new Rectangle(90, 90, 250, 20));
		lblLaboratorio.setFont(new Font("SansSerif", 0, 11));
		jLabel6.setText("Linea:");
		jLabel6.setBounds(new Rectangle(10, 115, 75, 20));
		jLabel6.setFont(new Font("SansSerif", 1, 11));
		jLabel7.setText("Frac Actual:");
		jLabel7.setBounds(new Rectangle(10, 65, 85, 20));
		jLabel7.setFont(new Font("SansSerif", 1, 11));
		lblFActual.setText("0");
		lblFActual.setBounds(new Rectangle(90, 65, 55, 20));
		lblFActual.setFont(new Font("SansSerif", 0, 11));
		lblLinea.setText("FARMACIA");
		lblLinea.setBounds(new Rectangle(90, 115, 265, 20));
		lblLinea.setFont(new Font("SansSerif", 0, 11));
		lblPrecioUnitarioT.setText("Precio Unit.");
		lblPrecioUnitarioT.setBounds(new Rectangle(160, 20, 85, 20));
		lblPrecioUnitarioT.setFont(new Font("SansSerif", 1, 11));
		lblFechaVencT.setText("Fecha Venc.");
		lblFechaVencT.setBounds(new Rectangle(235, 20, 100, 20));
		lblFechaVencT.setFont(new Font("SansSerif", 1, 11));
		lblAceptarT.setText("[ F10 ] Aceptar");
		lblAceptarT.setBounds(new Rectangle(270, 300, 90, 25));
		lblAceptarT.setFont(new Font("SansSerif", 1, 11));
		lblAceptarT.setForeground(new Color(32, 105, 29));
		txtCantEntera.setBounds(new Rectangle(30, 40, 45, 20));
		txtCantEntera.setHorizontalAlignment(JTextField.RIGHT);
		txtCantEntera.setDocument(new AtuxDocumentEditor(5, AtuxDocumentEditor.NUMERIC));
		txtCantEntera.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				txtCantEntera_focusLost(e);
			}
		});
		txtCantEntera.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtCantEntera_keyPressed(e);
			}
		});
		txtPrecioUnitario.setBounds(new Rectangle(160, 40, 65, 20));
		txtPrecioUnitario.setHorizontalAlignment(JTextField.RIGHT);
		txtPrecioUnitario.setDocument(new AtuxLengthText(12));
		txtPrecioUnitario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtPrecioUnitario_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				txtPrecioUnitario_keyReleased(e);
			}
		});
		txtFechaVencimiento.setBounds(new Rectangle(235, 40, 60, 20));
		txtFechaVencimiento.setDocument(new AtuxLengthText(7));
		txtFechaVencimiento.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtFechaVencimiento_keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				txtFechaVencimiento_keyReleased(e);
			}
		});
		lblFormatoFechaT.setText("(MM/yyyy)");
		lblFormatoFechaT.setBounds(new Rectangle(225, 65, 60, 20));
		lblFormatoFechaT.setFont(new Font("SansSerif", 0, 11));
		lblSalirT.setText("[ Esc ] Salir");
		lblSalirT.setBounds(new Rectangle(380, 300, 75, 25));
		lblSalirT.setFont(new Font("SansSerif", 1, 11));
		lblSalirT.setForeground(new Color(32, 105, 29));
		lblFraccionT.setText("Fraccion");
		lblFraccionT.setBounds(new Rectangle(25, 60, 85, 20));
		lblFraccionT.setFont(new Font("SansSerif", 1, 11));
		txtCantFraccion.setBounds(new Rectangle(85, 60, 50, 20));
		txtCantFraccion.setHorizontalAlignment(JTextField.RIGHT);
		txtCantFraccion.setDocument(new AtuxDocumentEditor(5, AtuxDocumentEditor.NUMERIC));
		txtCantFraccion.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				txtCantFraccion_focusLost(e);
			}
		});
		txtCantFraccion.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtCantFraccion_keyPressed(e);
			}

		});
		jLabel13.setText("Stock Actual:");
		jLabel13.setBounds(new Rectangle(10, 40, 80, 20));
		jLabel13.setFont(new Font("SansSerif", 1, 11));
		lblSActual.setText("0");
		lblSActual.setBounds(new Rectangle(90, 40, 55, 20));
		lblSActual.setFont(new Font("SansSerif", 0, 11));
		lblCabeceraDetalleProd.setBounds(new Rectangle(15, 10, 440, 20));
		lblCabeceraDetalleProd.setForeground(new Color(32, 105, 29));
		lblCabeceraDetalleProd.setBackground(new Color(32, 105, 29));
		lblCabeceraDetalleProd.setLayout(null);
		lblCabeceraDetalleProd.setFont(new Font("SansSerif", 0, 11));
		lblDetalleProductoT.setText("Detalle Producto:");
		lblDetalleProductoT.setBounds(new Rectangle(5, 5, 210, 15));
		lblDetalleProductoT.setFont(new Font("SansSerif", 1, 11));
		lblDetalleProductoT.setForeground(Color.white);
		lblOpcionesT.setText("Opciones:");
		lblOpcionesT.setBounds(new Rectangle(40, 285, 65, 15));
		lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
		// btnEntero.setText("Entero");
		btnEntero.setText("Cantidad");
		btnEntero.setBounds(new Rectangle(10, 20, 90, 20));
		btnEntero.setFont(new Font("SansSerif", 1, 11));
		btnEntero.setRequestFocusEnabled(false);
		btnEntero.setDefaultCapable(false);
		btnEntero.setBorderPainted(false);
		btnEntero.setMnemonic('e');
		btnEntero.setHorizontalAlignment(SwingConstants.LEFT);
		btnEntero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEntero_actionPerformed(e);
			}
		});
		lblLoteT.setText("Lote");
		lblLoteT.setBounds(new Rectangle(315, 20, 35, 20));
		lblLoteT.setFont(new Font("SansSerif", 1, 11));
		txtLote.setBounds(new Rectangle(315, 40, 80, 20));
		txtLote.setDocument(new AtuxLengthText(10));
		txtLote.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				txtLote_keyPressed(e);
			}
		});
		jLabel1.setText("fraccion");
		jLabel1.setBounds(new Rectangle(20, 155, 34, 16));
		lblValorFraccion.setText("0");
		lblValorFraccion.setBounds(new Rectangle(70, 155, 34, 16));
		lblDesFraccion.setBounds(new Rectangle(80, 40, 75, 20));
		jPanel1.add(lblValorFraccion, null);
		jPanel1.add(jLabel1, null);
		jPanel1.add(lblSActual, null);
		jPanel1.add(jLabel13, null);
		jPanel1.add(lblLinea, null);
		jPanel1.add(lblFActual, null);
		jPanel1.add(jLabel7, null);
		jPanel1.add(jLabel6, null);
		jPanel1.add(lblLaboratorio, null);
		jPanel1.add(jLabel3, null);
		jPanel1.add(jLabel2, null);
		jPanel1.add(lblDescripcion, null);
		lblCabeceraDetalleProd.add(lblDetalleProductoT, null);
		this.getContentPane().add(lblOpcionesT, null);
		this.getContentPane().add(lblCabeceraDetalleProd, null);
		this.getContentPane().add(lblSalirT, null);
		this.getContentPane().add(lblAceptarT, null);
		this.getContentPane().add(pnlIngresoCantidades, null);
		this.getContentPane().add(jPanel1, null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pnlIngresoCantidades.add(lblDesFraccion, null);
		pnlIngresoCantidades.add(txtLote, null);
		pnlIngresoCantidades.add(lblLoteT, null);
		pnlIngresoCantidades.add(btnEntero, null);
		pnlIngresoCantidades.add(txtCantFraccion, null);
		pnlIngresoCantidades.add(lblFraccionT, null);
		pnlIngresoCantidades.add(lblFormatoFechaT, null);
		pnlIngresoCantidades.add(txtFechaVencimiento, null);
		pnlIngresoCantidades.add(txtPrecioUnitario, null);
		pnlIngresoCantidades.add(txtCantEntera, null);
		pnlIngresoCantidades.add(lblFechaVencT, null);
		pnlIngresoCantidades.add(lblPrecioUnitarioT, null);
	}

	// Metodo que valida la tecla presionada
	void checkKeyPress(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_F10:
			aceptaDatos();
			break;
		case KeyEvent.VK_ESCAPE:
			closeWindow();
			break;
		}
	}

	void aceptaDatos() {
		if ((txtCantEntera.getText().length() == 0 || txtCantEntera.getText().equals("0")) && txtCantFraccion.isEnabled() == false) {
			txtCantEntera.requestFocus();
			txtCantEntera.selectAll();
			JOptionPane.showMessageDialog(this, "Ingrese Cantidad Entera", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if ((txtCantEntera.getText().length() == 0 || txtCantEntera.getText().equals("0")) && (txtCantFraccion.getText().length() == 0 || txtCantFraccion.getText().equals("0"))
				&& txtCantFraccion.isEnabled() == true) {
			txtCantEntera.requestFocus();
			txtCantEntera.selectAll();
			JOptionPane.showMessageDialog(this, "Ingrese Cantidad Entera o Fraccion", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!AtuxUtility.isInteger(txtCantEntera.getText()) && !txtCantEntera.getText().equals("")) {
			AtuxUtility.moveFocus(txtCantEntera);
			JOptionPane.showMessageDialog(this, "Cantidad Entera Incorrecta...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!AtuxUtility.isInteger(txtCantFraccion.getText()) && txtCantFraccion.isEnabled() == true && !txtCantFraccion.getText().equals("")) {
			AtuxUtility.moveFocus(txtCantFraccion);
			JOptionPane.showMessageDialog(this, "Cantidad Fraccion Incorrecta...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!txtCantEntera.getText().equals("") && Integer.parseInt(txtCantEntera.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Cantidad Entera con valor negativo...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtCantEntera);
			return;
		}

		if (!txtCantFraccion.getText().equals("") && Integer.parseInt(txtCantFraccion.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "Cantidad Fraccion con valor negativo...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtCantFraccion);
			return;
		}

		if (txtCantEntera.getText().equals("-0")) {
			AtuxUtility.moveFocus(txtCantEntera);
			JOptionPane.showMessageDialog(this, "Cantidad Entera Incorrecta...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (txtCantFraccion.getText().equals("-0")) {
			AtuxUtility.moveFocus(txtCantFraccion);
			JOptionPane.showMessageDialog(this, "Cantidad Fraccion Incorrecta...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!txtCantFraccion.getText().equals("") && valorFraccion > 0 && Integer.parseInt(txtCantFraccion.getText()) >= valorFraccion) {
			JOptionPane.showMessageDialog(this, "Cantidad de Fraccion mayor o igual al Valor de Fraccion!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtCantFraccion);
			return;
		}

		if (!AtuxUtility.isDouble(txtPrecioUnitario.getText())) {
			JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtPrecioUnitario);
			return;
		}

		if (AtuxUtility.getDecimalNumber(txtPrecioUnitario.getText().trim()) == 0.00) {
			JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtPrecioUnitario);
			return;
		}

		if (!txtPrecioUnitario.getText().equals("") && Double.parseDouble(txtPrecioUnitario.getText().trim()) < 0) {
			JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtPrecioUnitario);
			return;
		}

		if (txtPrecioUnitario.getText().equals("-0")) {
			JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			AtuxUtility.moveFocus(txtPrecioUnitario);
			return;
		}

		double variacion = AtuxUtility.getDecimalNumberRedondeado(precioProducto * porcentajeVariacion);
		double precioIngresado = AtuxUtility.getDecimalNumber(txtPrecioUnitario.getText().trim());
		double precioMaximo = AtuxUtility.getDecimalNumberRedondeado(precioProducto + variacion);
		double precioMinimo = AtuxUtility.getDecimalNumberRedondeado(precioProducto - variacion);

		if (precioIngresado > precioMaximo || precioIngresado < precioMinimo) {
			JOptionPane.showMessageDialog(this, "El Precio a Ingresar debe estar en el rango de " + precioMinimo + " a " + precioMaximo, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!txtFechaVencimiento.getText().trim().equals("")) {
			if (!AtuxUtility.isFechaMesValida((Object) this, txtFechaVencimiento.getText().trim(), "La Fecha Ingresada es Incorrecta !!!!\nRecuerde que solo se ingresa Mes y Aoo (ejmp. 05/2005) ")) {
				AtuxUtility.moveFocus(txtFechaVencimiento);
				return;
			}
		}

		if (txtCantFraccion.isVisible()) {
			VariablesInventario.PANT_SHOW_FRACCION = true;
		} else {
			VariablesInventario.PANT_SHOW_FRACCION = false;
		}

		AtuxVariables.vAceptar = true;
		this.setVisible(false);
		this.dispose();
	}

	void txtCantEntera_keyPressed(KeyEvent e) {
		AtuxUtility.admitirSoloDigitos(e, txtCantEntera, txtCantEntera.getText().length(), this);
		if (e.getKeyCode() == KeyEvent.VK_ENTER && txtCantFraccion.isVisible() == false) {
			txtPrecioUnitario.selectAll();
			txtPrecioUnitario.requestFocus();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && txtCantFraccion.isVisible() == true) {
			txtCantFraccion.selectAll();
			txtCantFraccion.requestFocus();
		}
		checkKeyPress(e);
	}

	void txtCantFraccion_keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			txtPrecioUnitario.selectAll();
			txtPrecioUnitario.requestFocus();
		}
		checkKeyPress(e);
	}

	void txtPrecioUnitario_keyPressed(KeyEvent e) {
		longitudTexto = txtPrecioUnitario.getText().length();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!AtuxUtility.isDouble(txtPrecioUnitario.getText())) {
				JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				AtuxUtility.moveFocus(txtPrecioUnitario);
				return;
			}

			if (!txtPrecioUnitario.getText().equals("") && Double.parseDouble(txtPrecioUnitario.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				AtuxUtility.moveFocus(txtPrecioUnitario);
				return;
			}
			if (txtPrecioUnitario.getText().equals("-0")) {
				JOptionPane.showMessageDialog(this, "Precio incorrecto verifique...!!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
				AtuxUtility.moveFocus(txtPrecioUnitario);
				return;
			}
			/*
			 * String precioUnit = txtPrecioUnitario.getText().trim(); precioUnit = AtuxUtility.formatNumber(Double.parseDouble(precioUnit),2); txtPrecioUnitario.setText("" + precioUnit);
			 */
			txtFechaVencimiento.selectAll();
			txtFechaVencimiento.requestFocus();
		}
		checkKeyPress(e);
	}

	void txtFechaVencimiento_keyPressed(KeyEvent e) {
		longitudTextoFechaVence = txtFechaVencimiento.getText().length();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!txtFechaVencimiento.getText().trim().equals("")) {
				if (!AtuxUtility.isFechaMesValida((Object) this, txtFechaVencimiento.getText().trim(),
						"La Fecha Ingresada es Incorrecta !!!!\nRecuerde que solo se ingresa Mes y Aoo (ejmp. 05/2005) ")) {
					AtuxUtility.moveFocus(txtFechaVencimiento);
					return;
				} else
					txtLote.requestFocus();
			} else
				txtLote.requestFocus();
		} else
			checkKeyPress(e);
	}

	void seteaValorFaraccion(int pValorFraccion) {
		valorFraccion = pValorFraccion;
	}

	void txtCantEntera_focusLost(FocusEvent e) {
		if (txtCantEntera.getText().length() == 0)
			txtCantEntera.setText("0");
	}

	void txtCantFraccion_focusLost(FocusEvent e) {
		if (txtCantFraccion.getText().length() == 0)
			txtCantFraccion.setText("0");
	}

	void txtPrecioUnitario_keyReleased(KeyEvent e) {
		AtuxUtility.admitirSoloDecimales(e, txtPrecioUnitario, longitudTexto, this);
	}

	void txtFechaVencimiento_keyReleased(KeyEvent e) {
		AtuxUtility.admitirSoloDigitos(e, txtFechaVencimiento, longitudTextoFechaVence, this);
		AtuxUtility.dateCompleteMes(txtFechaVencimiento, e);
	}

	void btnEntero_actionPerformed(ActionEvent e) {
		txtCantEntera.selectAll();
		txtCantEntera.requestFocus();
	}

	void this_windowOpened(WindowEvent e) {
		txtCantEntera.requestFocus();
	}

	void closeWindow() {
		txtCantEntera.setText("");
		txtCantFraccion.setText("");
		txtPrecioUnitario.setText("");
		txtFechaVencimiento.setText("");
		AtuxVariables.vAceptar = false;
		this.setVisible(false);
		this.dispose();
	}

	void txtLote_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			aceptaDatos();
		else
			checkKeyPress(e);
	}

	public void setPorcentajeVariacion(double pPorcentajeVariacion) {
		porcentajeVariacion = pPorcentajeVariacion;
	}

	public void setPrecioProducto(double pPrecioProducto) {
		precioProducto = pPrecioProducto;
	}

}