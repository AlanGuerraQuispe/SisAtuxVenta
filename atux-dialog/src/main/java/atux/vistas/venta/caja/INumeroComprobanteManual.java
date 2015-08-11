package atux.vistas.venta.caja;

import atux.util.AtuxLengthText;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class INumeroComprobanteManual extends JDialog {

    JPanel pnlComprobante = new JPanel();
    JLabel lblComprobante = new JLabel();
    JTextField txtSerie = new JTextField();
    JTextField txtNumero = new JTextField();
    JLabel lblGuion = new JLabel();
    JLabel lblF10 = new JLabel();
    JLabel lblEsc = new JLabel();

    private String tiComprobante = "";

    public INumeroComprobanteManual() {
        this(null, "", false);
    }

    public INumeroComprobanteManual(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            txtSerie.setDocument(new AtuxLengthText(3));
            txtNumero.setDocument(new AtuxLengthText(7));
            AtuxUtility.centrarVentana(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(327, 154));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        //this.setTitle("Ticket - Comprobante Manual");
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }

            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        pnlComprobante.setBounds(new Rectangle(20, 20, 280, 60));
        pnlComprobante.setBorder(BorderFactory.createTitledBorder(""));
        pnlComprobante.setLayout(null);
        lblComprobante.setText("Nro. de Comprobante :");
        lblComprobante.setBounds(new Rectangle(15, 20, 120, 20));
        lblComprobante.setFont(new Font("SansSerif", 0, 11));
        txtSerie.setBounds(new Rectangle(135, 20, 35, 20));
        txtSerie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSerie.setFont(new Font("SansSerif", 1, 12));
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtSerie_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtSerie_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtSerie_keyTyped(e);
            }
        });
        txtNumero.setBounds(new Rectangle(190, 20, 65, 20));
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumero.setFont(new Font("SansSerif", 1, 12));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNumero_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtNumero_keyReleased(e);
            }

            public void keyTyped(KeyEvent e) {
                txtNumero_keyTyped(e);
            }
        });
        lblGuion.setText("-");
        lblGuion.setBounds(new Rectangle(170, 20, 20, 20));
        lblGuion.setFont(new Font("SansSerif", 0, 11));
        lblGuion.setHorizontalAlignment(SwingConstants.CENTER);
        lblF10.setText("[ F10 ]  Aceptar");
        lblF10.setBounds(new Rectangle(120, 95, 95, 15));
        lblF10.setFont(new Font("SansSerif", 1, 11));
        lblF10.setForeground(new Color(32, 105, 29));
        lblEsc.setText("[ Esc ]  Salir");
        lblEsc.setBounds(new Rectangle(230, 95, 75, 15));
        lblEsc.setFont(new Font("SansSerif", 1, 11));
        lblEsc.setForeground(new Color(32, 105, 29));
        pnlComprobante.add(lblGuion, null);
        pnlComprobante.add(txtNumero, null);
        pnlComprobante.add(txtSerie, null);
        pnlComprobante.add(lblComprobante, null);
        this.getContentPane().add(lblEsc, null);
        this.getContentPane().add(lblF10, null);
        this.getContentPane().add(pnlComprobante, null);
    }

    void txtSerie_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSerie.setText(AtuxUtility.caracterIzquierda(txtSerie.getText(), 3, "0"));
            txtNumero.requestFocus();
        } else {
            chkKeyPressed(e);
        }
    }

    void txtSerie_keyReleased(KeyEvent e) {

    }

    void txtSerie_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtSerie, e);
    }

    void txtNumero_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNumero.setText(AtuxUtility.caracterIzquierda(txtNumero.getText(), 7, "0"));
            txtSerie.requestFocus();
        }
        chkKeyPressed(e);
    }

    void txtNumero_keyReleased(KeyEvent e) {

    }

    void txtNumero_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtNumero, e);
    }

    void chkKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F10) {
            e.consume();
            checkComprobante();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeWindow(false);
        }
    }

    void checkComprobante() {
        if (txtSerie.getText().trim().length() < 3 || txtNumero.getText().trim().length() < 7) {
            JOptionPane.showMessageDialog(this, "Error en la Numeración del Comprobante Manual. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtSerie.requestFocus();
            return;
        }
        try {
            String nuPedido = AtuxDBUtility.getValueAt("VTTV_COMPROBANTE_PAGO", "NU_PEDIDO", "CO_COMPANIA='" + AtuxVariables.vCodigoCompania + "' AND CO_LOCAL='" + AtuxVariables.vCodigoLocal + "' AND TI_COMPROBANTE='" + this.tiComprobante + "' AND NU_COMPROBANTE_PAGO='" + txtSerie.getText().trim() + txtNumero.getText().trim() + "'");
            if (nuPedido != null && nuPedido.trim().length() > 0) {
                JOptionPane.showMessageDialog(this, "El Número de Comprobante YA SE ENCUENTRA REGISTRADO. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                txtSerie.requestFocus();
                return;
            } else {
                closeWindow(true);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener información de COMPROBANTES DE PAGO", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            closeWindow(false);
        }
    }

    void this_windowOpened(WindowEvent e) {
        txtSerie.requestFocus();
    }

    void this_windowClosing(WindowEvent e) {

    }

    void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    public String getComprobanteManual() {
        return txtSerie.getText().trim() + txtNumero.getText().trim();
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

}