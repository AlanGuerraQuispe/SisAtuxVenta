package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.util.AtuxLengthText;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Pantalla usada para indicar el Número de Guía inicial <br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000   CRAMIREZ       01/01/2000 10:00:00   Creacion <br>
 * 001   JREBATTA       05/10/2010 13:46:00   Modificacion         Hacer que el Número de serie de la Guía no se pueda modificar.
 * <br>
 *
 * @version 1.0<br>
 */
public class DlgNumeracion extends JDialog {
    Frame parentFrame;
    JPanel pnlIngresoNumeracion = new JPanel();
    JLabel lblSecuenciaT = new JLabel();
    JTextField txtNumGrupo = new JTextField();
    JTextField txtNumSecuencia = new JTextField();
    JLabel lblAceptarT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JLabel lblSerieT = new JLabel();
    JButton btnGuiaSalida = new JButton();
    JLabel lblOpcionesT = new JLabel();
    private String strNumeroGuiaSal;

    public int longitudTexto = 0;

    public DlgNumeracion() {
        this(null, "", false);
    }

    public DlgNumeracion(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
            AtuxUtility.centrarVentana(this);
            parentFrame = parent;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(432, 168));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        pnlIngresoNumeracion.setBounds(new Rectangle(15, 10, 390, 80));
        pnlIngresoNumeracion.setBorder(BorderFactory.createTitledBorder("Ingrese Numeracion"));
        pnlIngresoNumeracion.setFont(new Font("SansSerif", 0, 11));
        pnlIngresoNumeracion.setLayout(null);
        lblSecuenciaT.setText("Secuencia");
        lblSecuenciaT.setBounds(new Rectangle(230, 15, 65, 20));
        lblSecuenciaT.setFont(new Font("SansSerif", 0, 11));
        txtNumGrupo.setBounds(new Rectangle(175, 40, 40, 20));
        txtNumGrupo.setDocument(new AtuxLengthText(3));
        txtNumGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNumGrupo_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtNumGrupo_keyTyped(e);
            }
        });
        //Inicio ID: 001
        txtNumGrupo.setEnabled(false);
        //Fin ID: 001
        txtNumSecuencia.setBounds(new Rectangle(230, 40, 65, 20));
        txtNumSecuencia.setDocument(new AtuxLengthText(7));
        txtNumSecuencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtNumSecuencia_keyPressed(e);
            }

            public void keyTyped(KeyEvent e) {
                txtNumSecuencia_keyTyped(e);
            }
        });
        lblAceptarT.setText("[ F10 ] Aceptar");
        lblAceptarT.setBounds(new Rectangle(230, 115, 95, 15));
        lblAceptarT.setFont(new Font("SansSerif", 1, 11));
        lblAceptarT.setForeground(new Color(32, 105, 29));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setBounds(new Rectangle(330, 115, 75, 15));
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setForeground(new Color(32, 105, 29));
        lblSerieT.setText("Serie   -");
        lblSerieT.setBounds(new Rectangle(175, 15, 55, 20));
        lblSerieT.setFont(new Font("SansSerif", 0, 11));
        btnGuiaSalida.setText("Guia de Salida");
        btnGuiaSalida.setBounds(new Rectangle(20, 40, 130, 20));
        btnGuiaSalida.setMnemonic('S');
        btnGuiaSalida.setFont(new Font("SansSerif", 1, 11));
        btnGuiaSalida.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaptionBorder, 1));
        btnGuiaSalida.setDefaultCapable(false);
        btnGuiaSalida.setRequestFocusEnabled(false);
        btnGuiaSalida.setHorizontalAlignment(SwingConstants.LEFT);
        btnGuiaSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGuiaSalida_actionPerformed(e);
            }
        });
        lblOpcionesT.setText("Opciones:");
        lblOpcionesT.setBounds(new Rectangle(25, 100, 105, 15));
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        pnlIngresoNumeracion.add(btnGuiaSalida, null);
        pnlIngresoNumeracion.add(lblSerieT, null);
        pnlIngresoNumeracion.add(txtNumSecuencia, null);
        pnlIngresoNumeracion.add(txtNumGrupo, null);
        pnlIngresoNumeracion.add(lblSecuenciaT, null);
        this.getContentPane().add(lblOpcionesT, null);
        this.getContentPane().add(pnlIngresoNumeracion, null);
        this.getContentPane().add(lblAceptarT, null);
        this.getContentPane().add(lblSalirT, null);
        inicializaNumeracion();
    }

    void inicializaNumeracion() {
       /* try {
            strNumeroGuiaSal = DBInventario.devuelveNumeroGuiaSalida();
            txtNumGrupo.setText(strNumeroGuiaSal.substring(0, 3));
            txtNumSecuencia.setText(strNumeroGuiaSal.substring(3, 10));
        }
        catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this,
                    "Error al obtener el Número de Guía de Salida o " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }*/
    }

    void txtNumSecuencia_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_F10) {
            aceptaNumeracion();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        }
    }

    private void aceptaNumeracion() {
        // Si entro por InkVenta_Matriz no debe realizar ningon cambio
        if (AtuxVariables.vInkVenta_Matriz) {
            JOptionPane.showMessageDialog(this, "No es posible realizar esta operacion en Matriz", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        longitudTexto = txtNumSecuencia.getText().length();
        String strNumSerie = txtNumSecuencia.getText().trim();
        if (longitudTexto < 7) {
            for (int k = 0; k < 7 - longitudTexto; k++) {
                strNumSerie = '0' + strNumSerie;
            }
        }
        txtNumSecuencia.setText(strNumSerie);
        if (!numeracionIngresadaEsValida()) {
            return;
        }
        actualizarNumeracion();
    }

    private boolean numeracionIngresadaEsValida() {
        //si la serie es cero
        if (Integer.parseInt(txtNumGrupo.getText().trim()) == 0) {
            AtuxUtility.showMessage(this, "El Número de serie ingresado no debe ser cero", txtNumGrupo);
            return false;
        }
        //si el secuencial es cero
        if (Integer.parseInt(txtNumSecuencia.getText().trim()) == 0) {
            AtuxUtility.showMessage(this, "El Número de secuencial ingresado no debe ser cero", txtNumSecuencia);
            return false;
        }
        return true;
    }

    void actualizarNumeracion() {
        /*try {
            if (JOptionPane.showConfirmDialog(this,
                    "Desea actualizar numeracion de Guía de Salida ?",
                    "Mensaje del Sistema",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String numero = txtNumGrupo.getText().trim() + txtNumSecuencia.getText().trim();
                DBInventario.actualizaNumSecGuiaSalida(numero);
                strNumeroGuiaSal = numero;
                JOptionPane.showMessageDialog(this,
                        "Numeracion Actualizada con oxito",
                        "Mensaje del Sistema",
                        JOptionPane.INFORMATION_MESSAGE);
                if (numeracionGuiaAppEsValida()) {
                    cerrarVentana(true);
                }
            }
        }catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this,
                    "Error al actualizar el Número de Guía de Salida o " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }*/
    }

    private boolean numeracionGuiaAppEsValida() {
        /** validacion de Guía de salida
         *  funcion que validara si el Número actual de la Guía de salida no sea haya usado anteriormente
         */
        try {
            String strFlag = String.valueOf(DBTrasladoProducto.verificarNumeracionGuia()).trim();
            boolean numeracionFueUsada = strFlag.equalsIgnoreCase("N");
            if (numeracionFueUsada) {
                AtuxUtility.showMessage(this,
                        "El Número de Guía de Salida ya ha sido ingresado.\n" +
                                "Modifique el No. de Guía.",null);
            }
            return !numeracionFueUsada;
        }
        catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
            return false;
        }
    }


    void txtNumGrupo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            longitudTexto = txtNumGrupo.getText().length();
            String strNumSerie = txtNumGrupo.getText().trim();
            if (longitudTexto < 3)
                for (int k = 0; k < 3 - longitudTexto; k++)
                    strNumSerie = '0' + strNumSerie;
            txtNumGrupo.setText(strNumSerie);
            //Inicio ID: 001
            //txtNumSecuencia.selectAll();
            //txtNumSecuencia.requestFocus();
            //Fin ID: 001
        }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            cerrarVentana(false);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            e.consume();
        }
    }


    void btnGuiaSalida_actionPerformed(ActionEvent e) {
        txtNumGrupo.selectAll();
        txtNumGrupo.requestFocus();
    }

    void cerrarVentana(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        this.dispose();
        this.setVisible(false);
    }

    void this_windowOpened(WindowEvent e) {
        //Inicio ID: 001
        //txtNumGrupo.selectAll();
        //txtNumGrupo.requestFocus();
        txtNumSecuencia.selectAll();
        txtNumSecuencia.requestFocus();
        //Fin ID: 001
    }

    private void txtNumGrupo_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtNumGrupo, e);
    }

    private void txtNumSecuencia_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtNumSecuencia, e);
    }

    public String getStrNumeroGuiaSal() {
        return txtNumGrupo.getText() + txtNumSecuencia.getText();
    }
}