package atux.trasladoproducto;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.JVentas;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Pantalla usada para el ingreso de Número de Página <br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * <br>
 *
 * @version 1.0<br>
 */
public class DlgInvIngNumPag extends JDialog {
    Frame parentFrame;
    ImageIcon iconAceptar = new ImageIcon(JVentas.class.getResource("/atux/resources/agregar.png"));
    ImageIcon iconCancelar = new ImageIcon(JVentas.class.getResource("/atux/resources/cancel.png"));
    int longitudTexto = 0;
    public boolean firstTime = false;

    JButton btnAceptar = new JButton();
    public JTextField txtNumeroPagina = new JTextField();
    JButton btnCancelar = new JButton();
    JPanel pnlIngresoCantidad = new JPanel();
    JButton btnNumeroPag = new JButton();

    public DlgInvIngNumPag() {
        this(null, "", false);
    }

    public DlgInvIngNumPag(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            AtuxUtility.centrarVentana(this);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(467, 168));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("Ingrese Número de Página");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        btnAceptar.setText("Aceptar");
        btnAceptar.setBounds(new Rectangle(175, 100, 110, 30));
        btnAceptar.setMnemonic('a');
        btnAceptar.setFont(new Font("SansSerif", 1, 11));
        btnAceptar.setIcon(iconAceptar);
        btnAceptar.setRequestFocusEnabled(false);
        btnAceptar.setDefaultCapable(false);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAceptar_actionPerformed(e);
            }
        });
        txtNumeroPagina.setHorizontalAlignment(JTextField.RIGHT);
        txtNumeroPagina.setBounds(new Rectangle(185, 25, 50, 25));
        txtNumeroPagina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtCantidad_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtCantidad_keyReleased(e);
            }
        });
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(new Rectangle(320, 100, 110, 30));
        btnCancelar.setRequestFocusEnabled(false);
        btnCancelar.setMnemonic('c');
        btnCancelar.setFont(new Font("SansSerif", 1, 11));
        btnCancelar.setIcon(iconCancelar);
        btnCancelar.setDefaultCapable(false);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelar_actionPerformed(e);
            }
        });
        pnlIngresoCantidad.setBounds(new Rectangle(15, 20, 430, 65));
        pnlIngresoCantidad.setLayout(null);
        pnlIngresoCantidad.setFont(new Font("SansSerif", 0, 11));
        pnlIngresoCantidad.setBackground(SystemColor.inactiveCaptionText);
        pnlIngresoCantidad.setBorder(BorderFactory.createTitledBorder("Ingrese cantidad"));
        btnNumeroPag.setText("Número de Página:");
        btnNumeroPag.setBounds(new Rectangle(30, 25, 145, 25));
        btnNumeroPag.setFont(new Font("SansSerif", 1, 11));
        btnNumeroPag.setRequestFocusEnabled(false);
        btnNumeroPag.setMnemonic('n');
        btnNumeroPag.setDefaultCapable(false);
        btnNumeroPag.setBorderPainted(false);
        btnNumeroPag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNumeroPag_actionPerformed(e);
            }
        });
        pnlIngresoCantidad.add(btnNumeroPag, null);
        pnlIngresoCantidad.add(txtNumeroPagina, null);
        this.getContentPane().add(pnlIngresoCantidad, null);
        this.getContentPane().add(btnCancelar, null);
        this.getContentPane().add(btnAceptar, null);
    }

    void btnAceptar_actionPerformed(ActionEvent e) {
        if ((txtNumeroPagina.getText().trim().equalsIgnoreCase("")) ||
                (Integer.parseInt(txtNumeroPagina.getText()) <= 0)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una pCantidad valida !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtNumeroPagina.selectAll();
            txtNumeroPagina.requestFocus();
        } else {
            txtNumeroPagina.setText(String.valueOf(Integer.parseInt(txtNumeroPagina.getText())));
            AtuxVariables.vAceptar = true;
            this.setVisible(false);
            this.dispose();
        }
    }

    void btnCancelar_actionPerformed(ActionEvent e) {
        txtNumeroPagina.setText("");
        AtuxVariables.vAceptar = false;
        this.setVisible(false);
        this.dispose();
    }

    void this_windowOpened(WindowEvent e) {
        txtNumeroPagina.requestFocus();
    }

    void txtCantidad_keyPressed(KeyEvent e) {
        longitudTexto = txtNumeroPagina.getText().length();
    }

    void txtCantidad_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!firstTime) btnAceptar.doClick();
            else firstTime = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnCancelar.doClick();
        } else {
            AtuxUtility.admitirSoloDigitos(e, txtNumeroPagina, longitudTexto, this);
        }
    }

    void this_windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(this, "Usted esto cerrando la ventana de manera incorrecta !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
    }

    void btnNumeroPag_actionPerformed(ActionEvent e) {
        txtNumeroPagina.selectAll();
        txtNumeroPagina.requestFocus();
    }


}