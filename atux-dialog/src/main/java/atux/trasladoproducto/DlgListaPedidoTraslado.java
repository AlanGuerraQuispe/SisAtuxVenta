package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import app.JVentas;
import atux.common.AtuxLoadCVL;
import atux.replicacion.CmtsReplicacionPk;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.trasladoproducto.reference.VariablesTrasladoProducto;
import atux.util.AtuxLengthText;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;

public class DlgListaPedidoTraslado extends JDialog {
    private final Log logger = LogFactory.getLog(getClass());

    ImageIcon iconBuscar = new ImageIcon((getClass().getResource("/atux/resources/search32.png")));

    Frame parentFrame;
    AtuxTableModel tableModelListaPedidos;
    AtuxTableModel tableModelPedidoDetalle;
    boolean enDetalle = false;

    JScrollPane scrPendientes = new JScrollPane();
    JTable tblListaPedidos = new JTable();
    JPanel pnlRelacion = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JLabel jLabel3 = new JLabel();
    JButton btnBuscar = new JButton();
    JPanel pnlItems = new JPanel();
    XYLayout xYLayout3 = new XYLayout();
    JLabel lblItems = new JLabel();
    JLabel lblItemsT = new JLabel();
    JScrollPane scrDetalle = new JScrollPane();
    JTable tblPedidoDetalle = new JTable();
    JLabel lblEsc = new JLabel();
    JButton btnDetalle = new JButton();
    JCheckBox chkRelacion = new JCheckBox();
    JCheckBox chkDetalle = new JCheckBox();
    JButton btnTipoPedido = new JButton();
    JComboBox cmbTipo = new JComboBox();
    JPanel pnlComprobante = new JPanel();
    private JTextField txtCoLocalOrigen = new JTextField();
    private JTextField txtDeLocalOrigen = new JTextField();
    private JTextField txtCoLocalDestino = new JTextField();
    private JTextField txtDeLocalDestino = new JTextField();
    private JComboBox cmbEstado = new JComboBox();
    private JTextField txtFeInicial = new JTextField();
    private JTextField txtFeFinal = new JTextField();
    private JLabel lblF4 = new JLabel();
    private JLabel lblEstadoT = new JLabel();
    private JLabel lblFeEmisionT = new JLabel();
    private JLabel lblCoLocalOrigenT = new JLabel();
    private JLabel lblCoDestinoT = new JLabel();
    private JButton btnPedido = new JButton();
    private JLabel lblF5 = new JLabel();
    private JLabel lblTipoPedidoDescripcion = new JLabel();
    private JLabel lblF11 = new JLabel();

    // Inicio ID:
    private JLabel lblF9 = new JLabel();

    public DlgListaPedidoTraslado() {
        this(null, "", true);
    }

    public DlgListaPedidoTraslado(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            initListaPedidos();
            initListaDetallePedido();
            AtuxUtility.centrarVentana(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(793, 545));
        this.getContentPane().setLayout(null);
        this.setTitle("Lista de Pedidos de Traslado");
        this.setFont(new Font("SansSerif", 0, 11));
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        scrPendientes.setFont(new Font("SansSerif", 0, 11));
        scrPendientes.setBounds(new Rectangle(15, 115, 755, 135));
        tblListaPedidos.setFont(new Font("SansSerif", 0, 11));
        tblListaPedidos.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chkKeyPressed(e);
            }
        });
        tblListaPedidos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) return;
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    logger.debug("Ninguna fila seleccionada.");
                    limpiarDetalleDelPedido();
                } else {
                    int selectedRowIndex = lsm.getMinSelectionIndex();
                    logger.debug("Fila seleccionada:" + selectedRowIndex );
                    actualizaDetallePedido();
                }
            }
        });

        pnlRelacion.setBackground(new Color(32, 105, 29));
        pnlRelacion.setLayout(xYLayout2);
        pnlRelacion.setFont(new Font("SansSerif", 0, 11));
        pnlRelacion.setBounds(new Rectangle(15, 90, 755, 25));
        jLabel3.setText("Opciones :");
        jLabel3.setFont(new Font("SansSerif", 1, 11));
        jLabel3.setBounds(new Rectangle(15, 470, 70, 15));
        btnBuscar.setText("Buscar");
        btnBuscar.setFont(new Font("SansSerif", 0, 11));
        btnBuscar.setMnemonic('b');
        btnBuscar.setIcon(iconBuscar);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setBounds(new Rectangle(630, 20, 110, 30));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBuscar_actionPerformed(e);
            }
        });
        pnlItems.setBackground(new Color(32, 105, 29));
        pnlItems.setFont(new Font("SansSerif", 0, 11));
        pnlItems.setLayout(xYLayout3);
        pnlItems.setBounds(new Rectangle(15, 260, 755, 25));
        lblItems.setText("0");
        lblItems.setFont(new Font("SansSerif", 1, 11));
        lblItems.setForeground(Color.yellow);
        lblItems.setHorizontalAlignment(SwingConstants.RIGHT);
        lblItemsT.setText("Items");
        lblItemsT.setFont(new Font("SansSerif", 1, 11));
        lblItemsT.setForeground(Color.yellow);
        scrDetalle.setFont(new Font("SansSerif", 0, 11));
        scrDetalle.setBounds(new Rectangle(15, 285, 755, 175));
        tblPedidoDetalle.setFont(new Font("SansSerif", 0, 11));
        tblPedidoDetalle.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                tblPedidoDetalle_mousePressed(e);
            }
        });
        lblEsc.setText("[ Esc ]  Salir");
        lblEsc.setFont(new Font("SansSerif", 1, 11));
        lblEsc.setForeground(new Color(32, 105, 29));
        lblEsc.setBounds(new Rectangle(700, 490, 70, 15));
        lblEsc.setHorizontalAlignment(SwingConstants.RIGHT);
        btnDetalle.setText("Detalle del Pedido :");
        btnDetalle.setFont(new Font("SansSerif", 1, 11));
        btnDetalle.setHorizontalAlignment(SwingConstants.LEFT);
        btnDetalle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDetalle.setBackground(new Color(32, 105, 27));
        btnDetalle.setForeground(Color.white);
        btnDetalle.setRequestFocusEnabled(false);
        btnDetalle.setMnemonic('d');
        btnDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDetalle_actionPerformed(e);
            }
        });
        chkRelacion.setFont(new Font("SansSerif", 0, 11));
        chkRelacion.setBackground(new Color(32, 105, 29));
        chkRelacion.setEnabled(false);
        chkRelacion.setHorizontalAlignment(SwingConstants.CENTER);
        chkRelacion.setSelected(true);
        chkDetalle.setFont(new Font("SansSerif", 0, 11));
        chkDetalle.setBackground(new Color(32, 105, 27));
        chkDetalle.setEnabled(false);
        chkDetalle.setHorizontalAlignment(SwingConstants.CENTER);
        chkDetalle.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                chkDetalle_keyPressed(e);
            }
        });
        btnTipoPedido.setText("Tipo :");
        btnTipoPedido.setFont(new Font("SansSerif", 1, 11));
        btnTipoPedido.setHorizontalAlignment(SwingConstants.LEFT);
        btnTipoPedido.setMnemonic('t');
        btnTipoPedido.setRequestFocusEnabled(false);
        btnTipoPedido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnTipoPedido.setBounds(new Rectangle(10, 20, 35, 20));
        btnTipoPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnTipoPedido_actionPerformed(e);
            }
        });
        cmbTipo.setBounds(new Rectangle(45, 20, 160, 20));
        cmbTipo.setFont(new Font("SansSerif", 0, 11));
        cmbTipo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbTipo_keyPressed(e);
            }
        });
        pnlComprobante.setBounds(new Rectangle(15, 15, 755, 75));
        pnlComprobante.setLayout(null);
        pnlComprobante.setFont(new Font("SansSerif", 0, 11));
        pnlComprobante.setBorder(BorderFactory.createTitledBorder("Datos del Pedido"));

        txtCoLocalOrigen.setBounds(new Rectangle(45, 45, 60, 20));
        txtCoLocalOrigen.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent keyEvent) {
                txtCoLocalOrigen_keyTyped(keyEvent);
            }

            public void keyPressed(KeyEvent keyEvent) {
                txtCoLocalOrigen_keyPressed(keyEvent);
            }

        });
        txtDeLocalOrigen.setBounds(new Rectangle(110, 45, 170, 20));
        txtDeLocalOrigen.setEnabled(false);
        txtCoLocalDestino.setBounds(new Rectangle(350, 45, 60, 20));
        txtCoLocalDestino.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent keyEvent) {
                txtCoLocalDestino_keyTyped(keyEvent);
            }

            public void keyPressed(KeyEvent keyEvent) {
                txtCoLocalDestino_keyPressed(keyEvent);
            }
        });
        txtDeLocalDestino.setEnabled(false);
        txtDeLocalDestino.setBounds(new Rectangle(415, 45, 170, 20));
        cmbEstado.setBounds(new Rectangle(255, 20, 95, 20));
        cmbEstado.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                cmbEstado_keyPressed(e);
            }
        });

        txtFeInicial.setBounds(new Rectangle(440, 20, 70, 20));
        txtFeInicial.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                txtFeInicial_keyReleased(keyEvent);
            }

            public void keyPressed(KeyEvent keyEvent) {
                txtFeInicial_keyPressed(keyEvent);
            }
        });
        txtFeFinal.setBounds(new Rectangle(515, 20, 70, 20));
        txtFeFinal.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                txtFeFinal_keyReleased(keyEvent);
            }

            public void keyPressed(KeyEvent keyEvent) {
                txtFeFinal_keyPressed(keyEvent);
            }
        });

        pnlRelacion.add(lblTipoPedidoDescripcion, new XYConstraints(605, 5, 110, 15));
        pnlRelacion.add(btnPedido, new XYConstraints(10, 5, 125, 15));
        pnlRelacion.add(chkRelacion, new XYConstraints(725, 0, 25, 25));
        pnlItems.add(chkDetalle, new XYConstraints(725, 0, 25, 25));
        pnlItems.add(btnDetalle, new XYConstraints(10, 5, 125, 15));
        pnlItems.add(lblItemsT, new XYConstraints(675, 5, 45, 15));
        pnlItems.add(lblItems, new XYConstraints(630, 5, 35, 15));
        pnlComprobante.add(lblCoDestinoT, null);
        pnlComprobante.add(lblCoLocalOrigenT, null);
        pnlComprobante.add(lblFeEmisionT, null);
        pnlComprobante.add(lblEstadoT, null);
        pnlComprobante.add(txtFeFinal, null);
        pnlComprobante.add(txtFeInicial, null);
        pnlComprobante.add(cmbEstado, null);
        pnlComprobante.add(txtDeLocalDestino, null);
        pnlComprobante.add(txtCoLocalDestino, null);
        pnlComprobante.add(txtDeLocalOrigen, null);
        pnlComprobante.add(txtCoLocalOrigen, null);
        pnlComprobante.add(btnTipoPedido, null);
        pnlComprobante.add(cmbTipo, null);
        pnlComprobante.add(btnBuscar, null);
        this.getContentPane().add(lblF11, null);
        this.getContentPane().add(lblF5, null);
        this.getContentPane().add(lblF4, null);
        this.getContentPane().add(pnlComprobante, null);
        scrPendientes.getViewport().add(tblListaPedidos, null);
        this.getContentPane().add(scrPendientes, null);
        this.getContentPane().add(pnlRelacion, null);
        scrDetalle.getViewport().add(tblPedidoDetalle, null);
        this.getContentPane().add(scrDetalle, null);
        this.getContentPane().add(pnlItems, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(lblEsc, null);
        txtFeInicial.setDocument(new AtuxLengthText(10));
        txtFeFinal.setDocument(new AtuxLengthText(10));
        lblF4.setBounds(new Rectangle(15, 490, 100, 15));
        lblF4.setText("[ F4 ] Anular");
        lblF4.setFont(new Font("SansSerif", 1, 11));
        lblF4.setForeground(new Color(32, 105, 29));
        //
        lblF9.setBounds(new Rectangle(15, 490, 100, 15));
        lblF9.setText("[ F4 ] Anular");
        lblF9.setFont(new Font("SansSerif", 1, 11));
        lblF9.setForeground(new Color(32, 105, 29));
        //
        lblEstadoT.setText("Estado :");
        lblEstadoT.setBounds(new Rectangle(215, 20, 45, 20));
        lblEstadoT.setFont(new Font("SansSerif", 0, 11));
        lblFeEmisionT.setText("Fecha Emision :");
        lblFeEmisionT.setBounds(new Rectangle(360, 20, 80, 20));
        lblFeEmisionT.setFont(new Font("SansSerif", 0, 11));
        lblCoLocalOrigenT.setText("Origen :");
        lblCoLocalOrigenT.setBounds(new Rectangle(10, 50, 50, 15));
        lblCoLocalOrigenT.setFont(new Font("SansSerif", 0, 11));
        lblCoDestinoT.setText("Destino :");
        lblCoDestinoT.setBounds(new Rectangle(305, 50, 45, 15));
        lblCoDestinoT.setFont(new Font("SansSerif", 0, 11));
        btnPedido.setText("Pedido");
        btnPedido.setMnemonic('p');

        btnPedido.setFont(new Font("SansSerif", 1, 11));
        btnPedido.setHorizontalAlignment(SwingConstants.LEFT);
        btnPedido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnPedido.setBackground(new Color(32, 105, 27));
        btnPedido.setForeground(Color.white);
        btnPedido.setRequestFocusEnabled(false);
        btnPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPedido_actionPerformed(e);
            }
        });
        lblF5.setBounds(new Rectangle(90, 490, 100, 15));
        lblF5.setText("[ F5 ] Entregar");
        lblF5.setFont(new Font("SansSerif", 1, 11));
        lblF5.setForeground(new Color(32, 105, 29));
        lblTipoPedidoDescripcion.setText("Tipo Pedido");
        lblTipoPedidoDescripcion.setFont(new Font("SansSerif", 1, 11));
        lblTipoPedidoDescripcion.setForeground(Color.yellow);
        lblF11.setBounds(new Rectangle(185, 490, 170, 15));
        lblF11.setText("[ F11 ] Reimpresion de Guías");
        lblF11.setFont(new Font("SansSerif", 1, 11));
        lblF11.setForeground(new Color(32, 105, 29));
        lblF11.setVisible(!AtuxVariables.vOcultarReimpresionGuias);
    }

    private void limpiarDetalleDelPedido() {
        tableModelPedidoDetalle.clearTable();
        tblPedidoDetalle.repaint();
        lblItems.setText("0");
        lblTipoPedidoDescripcion.setText("");
    }                                            


    private void cmbEstado_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtFeInicial);
            return;
        }
        chkKeyPressed(e);
    }


    private void txtFeInicial_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtFeFinal);
            return;
        }
        chkKeyPressed(e);
    }

    private void txtFeInicial_keyReleased(KeyEvent e) {
        AtuxUtility.dateComplete(txtFeInicial, e);
    }

    private void txtFeFinal_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtCoLocalOrigen);
            return;
        }
        chkKeyPressed(e);
    }

    private void txtFeFinal_keyReleased(KeyEvent e) {
        AtuxUtility.dateComplete(txtFeFinal, e);
    }


    private void tblPedidoDetalle_mousePressed(MouseEvent e) {
        if (tblPedidoDetalle.getSelectedRow() >= 0) {
            focoEnProductos();
        }
    }

    void focoEnProductos() {
        chkDetalle.setSelected(true);
    }


    void cmbTipo_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(cmbEstado);
            return;
        }
        chkKeyPressed(e);
    }

    void txtCoLocalOrigen_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesTrasladoProducto.vCodLocal = txtCoLocalOrigen.getText().trim();
            seleccionarLocal(txtCoLocalOrigen, txtDeLocalOrigen);
            AtuxUtility.moveFocus(txtCoLocalDestino);
            return;
        }
        chkKeyPressed(e);
    }

    void txtCoLocalOrigen_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtCoLocalOrigen, e);
    }

    private void txtCoLocalDestino_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            VariablesTrasladoProducto.vCodLocal = txtCoLocalDestino.getText().trim();
            seleccionarLocal(txtCoLocalDestino, txtDeLocalDestino);
            btnBuscar.doClick();
            return;
        }
        chkKeyPressed(e);
    }

    private void txtCoLocalDestino_keyTyped(KeyEvent e) {
        AtuxUtility.admitirDigitos(txtCoLocalDestino, e);
    }


    void seleccionarLocal(JTextField txtCoLocal, JTextField txtDeLocal) {
        if (txtCoLocal.getText().trim().length() == 0) {
            DlgSelLocal dlgSelLocal = new DlgSelLocal(parentFrame, "", true);
            dlgSelLocal.setVisible(true);
            if (AtuxVariables.vAceptar) {
                txtCoLocal.setText(VariablesTrasladoProducto.vCodLocal);
                txtDeLocal.setText(VariablesTrasladoProducto.vNoLocal);
            }
        } else {
            VariablesTrasladoProducto.vCodLocal = AtuxUtility.caracterIzquierda(txtCoLocal.getText().trim(), 3, "0");
            try {
                VariablesTrasladoProducto.vNoLocal = DBTrasladoProducto.getNombreLocal();
                if (VariablesTrasladoProducto.vNoLocal.trim().length() == 0) {
                    VariablesTrasladoProducto.vCodLocal = "";
                    AtuxUtility.showMessage(this,
                            "El Codigo de Local NO existe. Verifique !!!",
                            null);
                    txtCoLocal.selectAll();
                    txtDeLocal.setText("");
                    AtuxUtility.moveFocus(txtCoLocal);
                } else {
                    txtCoLocal.setText(VariablesTrasladoProducto.vCodLocal);
                    txtDeLocal.setText(VariablesTrasladoProducto.vNoLocal);
                }
            } catch (SQLException sqlerr) {
                AtuxUtility.showMessage(this,
                        "Error al obtener Nombre del Local. Verifique !!! - " + sqlerr.getErrorCode() +
                                "\n" + sqlerr.toString(), null);
                sqlerr.printStackTrace();
            }
        }
    }


    void chkDetalle_keyPressed(KeyEvent e) {
        chkKeyPressed(e);
    }


    void chkKeyPressed(KeyEvent e) {
        boolean origenJComboBox = e.getSource() instanceof JComboBox;
        boolean origenJTable = e.getSource() instanceof JTable;
        if (!origenJComboBox) {
            if (!enDetalle) {
                if (!origenJTable) {
                    AtuxGridUtils.aceptarTeclaPresionada(e, tblListaPedidos, null, 0);
                }
            } else {
                AtuxGridUtils.aceptarTeclaPresionada(e, tblPedidoDetalle, null, 0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F5) {
            despacharPedidoTraslado();
        } else if (e.getKeyCode() == KeyEvent.VK_F4) {
            anularPedidoTraslado();
        } else if (e.getKeyCode() == KeyEvent.VK_F11 & lblF11.isVisible()) {
            reimprimirGuiasSalida();
        } else if (e.getKeyCode() == KeyEvent.VK_F10) {
           // closeWindow(true);
            e.consume();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeWindow(false);
        }
    }

    private void reimprimirGuiasSalida() {
        int pedidoElegidoRow = tblListaPedidos.getSelectedRow();
        if (pedidoElegidoRow == -1) {
            JOptionPane.showMessageDialog(this, "Ningon Pedido esto seleccionado. Por favor Verifique.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Obtemer datos del pedido
        String deLocal = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_DE_LOCAL);
        String nuSolicitudPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_PEDIDO);
        String nuRecepcionProducto = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_RECEPCION_PRODUCTO);
        String coLocalDestino = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL_DESTINO);
        String feEmision = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_FE_EMISION);
        // Validaciones
        if (!AtuxVariables.vCodigoLocal.equals(coLocalDestino)) {
            JOptionPane.showMessageDialog(this, "La Guía de este Pedido no puede ser reimpresa en este local.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String esPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_ESTADO);
        if (!ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_ATENDIDO.equals(esPedido)) {
            JOptionPane.showMessageDialog(this, "La Guía de este Pedido no puede ser reimpresa.\n Solo se pueden reimprimir Guías de pedidos atendidos.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tiPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_TI_PEDIDO);
        logger.debug("Se va a Reimprimir la Guía del Pedido de Traslado:" + nuSolicitudPedido);
        String deLocalOrigen = deLocal;
        int mes = Integer.parseInt(feEmision.substring(3, 5));
        String mesLetra = AtuxUtility.devuelveMesEnLetras(mes);
        String dia = feEmision.substring(0, 2);
        String aoo = feEmision.substring(6, 10);
      //salmuz  GuiaSalidaImpresion.imprimirTransporteProducto(this, tiPedido, tblPedidoDetalle, deLocalOrigen, dia, mesLetra, aoo,nuRecepcionProducto, nuSolicitudPedido,"0", "0");
    }

    private void anularPedidoTraslado() {
        int pedidoElegidoRow = tblListaPedidos.getSelectedRow();
        int size = tblListaPedidos.getModel().getRowCount();
        if ((pedidoElegidoRow == -1) || size==0)  {
            JOptionPane.showMessageDialog(this, "Ningon Pedido esto seleccionado. Por favor Verifique.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String coCompania = AtuxVariables.vCodigoCompania;
        String coLocal = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL);
        String nuSolicitudPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_PEDIDO);
        String coLocalDestino = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL_DESTINO);
        String esPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_ESTADO);
        String tiPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_TI_PEDIDO);

        if (JOptionPane.showConfirmDialog(this,
                "Esto seguro de anular el Pedido:"+nuSolicitudPedido+ " ?",
                "Confirmar Anulacion del Pedido de Traslado",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        boolean esTipDevolucionOrFaltante = false;
        if( tiPedido.trim().equalsIgnoreCase(ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_DEVOLUCION) ||
                tiPedido.trim().equalsIgnoreCase(ConstantsTrasladoProducto.PEDIDO_TRASLADO_TIPO_FALTANTE_CD)){
            AtuxUtility.showMessage(this, "Necesita el VB del Quimico para realizar esta operacion.", null);
            /*DlgLogin dlgLogin = new DlgLogin(parentFrame, "", true);
            dlgLogin.setGrabaEnSesion(false);
            dlgLogin.setRolUsuario(EckerdConstants.ROL_QUIMICO);
            dlgLogin.setVisible(true);
            if (!AtuxVariables.vAceptar) {
                return;
            }else{*/
                esTipDevolucionOrFaltante = true;
            //}
        }else{
            if (!AtuxVariables.vCodigoLocal.equals(coLocal)) {
                JOptionPane.showMessageDialog(this, "Este Pedido no puede ser anulado en este local.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


        if (!ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_PENDIENTE.equals(esPedido)) {
            JOptionPane.showMessageDialog(this, "Este Pedido no puede ser anulado.\n Solo se pueden anular pedidos pendientes.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            logger.debug("Se va a Anular el Pedido de Traslado:" + nuSolicitudPedido);
            DBTrasladoProducto.anularPedidoTraslado(coCompania, coLocal, nuSolicitudPedido);

            CmtsReplicacionPk replicadoPk = null;
            if(!esTipDevolucionOrFaltante){
                //Inicio ID: 002
                //CmtsReplicacionPk replicadoPk = DBTrasladoProducto.replicarSolitudPedidoUpdate(coCompania, coLocal, nuSolicitudPedido, coLocal, coLocalDestino);                
                boolean esAlmacenORecetario = DBTrasladoProducto.esAlmacenORecetario(coLocalDestino);
                if (!esAlmacenORecetario){
                    replicadoPk = DBTrasladoProducto.replicarSolitudPedidoUpdate(coCompania, coLocal, nuSolicitudPedido, coLocal, coLocalDestino);
                }
                //Fin ID: 002
            }

            AtuxUtility.aceptarTransaccion();
            if (replicadoPk != null) {
                //salmuz DBTrasladoProducto.forzarReplicacion(replicadoPk);
            }
            AtuxUtility.showMessage(this, "El Pedido: " + nuSolicitudPedido + " fue anulado exitosamente.", null);
            if(logger.isInfoEnabled()){
                logger.info("El Pedido: " + nuSolicitudPedido + " fue anulado exitosamente.");
            }
            btnBuscar_actionPerformed(null);
        } catch (Exception e) {
           DBTrasladoProducto.procesarException(this,e,"Problemas Al anular el Pedido:"+nuSolicitudPedido + " -");
        }
    }

    private void despacharPedidoTraslado() {
        int pedidoElegidoRow = tblListaPedidos.getSelectedRow();
        if (pedidoElegidoRow == -1) {
            JOptionPane.showMessageDialog(this, "Ningon Pedido esto seleccionado. Por favor Verifique.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Obtemer datos del pedido
        String coCompania = AtuxVariables.vCodigoCompania;
        String coLocal = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL);
        String deLocal = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_DE_LOCAL);
        String tiSolicitudPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_TI_PEDIDO);
        String nuSolicitudPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_PEDIDO);
        String nuRecepcionProducto = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_RECEPCION_PRODUCTO);
        String coLocalDestino = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL_DESTINO);
        String deLocalDestino = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_DE_LOCAL_DESTINO);
        String feEmision = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_FE_EMISION);
        // Validaciones
        if (!AtuxVariables.vCodigoLocal.equals(coLocalDestino)) {
            JOptionPane.showMessageDialog(this, "Este Pedido no puede ser entregado en este local.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String esPedido = (String) tblListaPedidos.getValueAt(pedidoElegidoRow, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_ESTADO);
        if (!ConstantsTrasladoProducto.PEDIDO_TRASLADO_ES_PENDIENTE.equals(esPedido)) {
            JOptionPane.showMessageDialog(this, "Este Pedido no puede ser entregado.\n Solo se pueden entregar pedidos pendientes.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }

        logger.debug("Se va a Entregar el Pedido de Traslado:" + nuSolicitudPedido);
        DlgDespachoPedidoTraslado dlgPedidoTraslado = new DlgDespachoPedidoTraslado(parentFrame, "Pedido de Traslado", true);
        dlgPedidoTraslado.setValoresCabecera(coCompania, coLocal, nuSolicitudPedido, tiSolicitudPedido, nuRecepcionProducto, coLocalDestino);
        dlgPedidoTraslado.txtTiSolicitudPedido.setText(tiSolicitudPedido);
        dlgPedidoTraslado.txtNuSolicitudPedido.setText(nuSolicitudPedido);
        dlgPedidoTraslado.txtCoLocalOrigen.setText(coLocal);
        dlgPedidoTraslado.txtDeLocalOrigen.setText(deLocal);
        dlgPedidoTraslado.txtCoLocalDestino.setText(coLocalDestino);
        dlgPedidoTraslado.txtDeLocalDestino.setText(deLocalDestino);
        dlgPedidoTraslado.txtFeEmision.setText(feEmision);
        dlgPedidoTraslado.inicializar(tableModelPedidoDetalle);
        dlgPedidoTraslado.setVisible(true);
        if (AtuxVariables.vAceptar) {
            AtuxVariables.vAceptar = false;
            btnBuscar.doClick();
        }
    }

    void btnBuscar_actionPerformed(ActionEvent e) {
        clearData();
        actualizarListaPedidosTraslado();
        if (tblListaPedidos.getRowCount() > 0) {
            setearFocoEnListaPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "No existen registros que cumplan los criterios de búsqueda." , "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            cmbTipo.requestFocus();
        }

    }

    void btnTipoPedido_actionPerformed(ActionEvent e) {
        actionPerformedEnCriteriosBusqueda();
        cmbTipo.requestFocus();
    }

    private void actionPerformedEnCriteriosBusqueda() {
        chkRelacion.setSelected(true);
        chkDetalle.setSelected(false);
        enDetalle = false;
        tblPedidoDetalle.clearSelection();
    }

    private void btnPedido_actionPerformed(ActionEvent e) {
        setearFocoEnListaPedidos();
    }

    private void setearFocoEnListaPedidos() {
        enDetalle = false;
        chkRelacion.setSelected(true);
        int selectedRow = tblListaPedidos.getSelectedRow();
        final int row = Math.max(0, selectedRow);
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                tblListaPedidos.requestFocus();
                AtuxGridUtils.showCell(tblListaPedidos, row, 0);
            }
        });
    }

    void btnDetalle_actionPerformed(ActionEvent e) {
        if (tblPedidoDetalle.getRowCount() > 0) {
            chkRelacion.setSelected(false);
            chkDetalle.setSelected(true);
            enDetalle = true;
            AtuxUtility.setearPrimerRegistro(tblPedidoDetalle, null, 0);
            chkDetalle.requestFocus();
        }
    }

    void initListaPedidos() {
        tableModelListaPedidos = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaPedidoTraslados, ConstantsTrasladoProducto.defaultValuesListaPedidoTraslados, 0);
        AtuxUtility.initSimpleList(tblListaPedidos, tableModelListaPedidos, ConstantsTrasladoProducto.columnsListaPedidoTraslados);
    }

    void actualizarListaPedidosTraslado() {
        String tiPedidoTraslado = AtuxLoadCVL.getCVLCode("CMTR_TIPO_PEDIDO_TRASLADO_FILTRO", cmbTipo.getSelectedIndex());
        String esPedidoTraslado = AtuxLoadCVL.getCVLCode("PEDIDO_TRASLADO_ESTADO", cmbEstado.getSelectedIndex());
        String coLocalOrigen = txtCoLocalOrigen.getText().trim();
        String coLocalDestino = txtCoLocalDestino.getText().trim();
        String feEmisionInicial = txtFeInicial.getText().trim();
        String feEmisionFinal = txtFeFinal.getText().trim();
        boolean hayFechas = !"".equals(feEmisionInicial) || !"".equals(feEmisionFinal);
        if (hayFechas && !validaFechas()) {
            return;
        }
        try {
            DBTrasladoProducto.loadPedidosDeTraslado(tableModelListaPedidos, tiPedidoTraslado, esPedidoTraslado, feEmisionInicial, feEmisionFinal, coLocalOrigen, coLocalDestino);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente.  Verifique !!! - " + sqlException.getErrorCode(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    void initListaDetallePedido() {
        tableModelPedidoDetalle = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaPedidoTrasladoDetalle, ConstantsTrasladoProducto.defaultValuesListaPedidoTrasladoDetalle, 0);
        AtuxUtility.initSimpleList(tblPedidoDetalle, tableModelPedidoDetalle, ConstantsTrasladoProducto.columnsListaPedidoTrasladoDetalle);
    }

    void actualizaDetallePedido() {
        try {
            int filaDelPedidoActual = tblListaPedidos.getSelectedRow();
            int size = tblListaPedidos.getModel().getRowCount();
            if ((filaDelPedidoActual == -1) || size==0){
                return;
            }
            String coCompania = AtuxVariables.vCodigoCompania;
            String coLocal = (String) tblListaPedidos.getValueAt(filaDelPedidoActual, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_CO_LOCAL);
            String nuPedido = (String) tblListaPedidos.getValueAt(filaDelPedidoActual, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_NU_PEDIDO);
            String tiPedido = (String) tblListaPedidos.getValueAt(filaDelPedidoActual, ConstantsTrasladoProducto.LISTA_PEDIDO_COL_TI_PEDIDO);
            if(logger.isDebugEnabled()){
                logger.debug("Actualizando el detalle del pedido:"+nuPedido);
            }
            DBTrasladoProducto.loadDetallePedidoTraslado(tableModelPedidoDetalle, coCompania, coLocal, nuPedido);
            tblPedidoDetalle.repaint();
            lblItems.setText(String.valueOf(tblPedidoDetalle.getRowCount()));
            lblTipoPedidoDescripcion.setText(DBTrasladoProducto.getDeTipoPedido(tiPedido));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Detalle del Registro del Pedido Pendiente !!! - " + sqlException.getErrorCode(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    void clearData() {
        tableModelPedidoDetalle.clearTable();
        tblPedidoDetalle.repaint();
        tableModelListaPedidos.clearTable();
        tblListaPedidos.repaint();
        lblItems.setText("0");
        lblTipoPedidoDescripcion.setText("");
    }

    void this_windowOpened(WindowEvent e) {
        clearData();
        AtuxLoadCVL.loadCVL(cmbTipo,
                "CMTR_TIPO_PEDIDO_TRASLADO",
                "TI_PEDIDO_TRASLADO",
                "TI_PEDIDO_TRASLADO || ' - ' || DE_CORTA_TIPO_PEDIDO_TRASLADO",
                false,
                "ES_TIPO_PEDIDO_TRASLADO = 'A' /*AND IN_PEDIDO_TRASLADO = 'S'*/ and CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'",
                "NU_ORDEN_FILA",
                "CMTR_TIPO_PEDIDO_TRASLADO_FILTRO");
        AtuxLoadCVL.loadCVLfromArrays(
                cmbEstado, "PEDIDO_TRASLADO_ESTADO", ConstantsTrasladoProducto.PEDIDO_TRASLADO_ESTADO_CODIGO,
                ConstantsTrasladoProducto.PEDIDO_TRASLADO_ESTADO_DESC, false);
        cmbTipo.requestFocus();
    }

    void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

    private boolean validaFechas() {
        if (!AtuxUtility.validaFecha(txtFeInicial.getText().trim(), "dd/MM/yyyy") || txtFeInicial.getText().trim().length() < 10) {
            JOptionPane.showMessageDialog(this, "Ingrese correctamente la fecha de inicio de búsqueda.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtFeFinal.select(0, 0);
            txtFeInicial.selectAll();
            txtFeInicial.requestFocus();
            return false;
        }
        if (!AtuxUtility.validaFecha(txtFeFinal.getText().trim(), "dd/MM/yyyy") || txtFeFinal.getText().trim().length() < 10) {
            JOptionPane.showMessageDialog(this, "Ingrese correctamente la fecha de fin de búsqueda.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtFeInicial.select(0, 0);
            txtFeFinal.selectAll();
            txtFeFinal.requestFocus();
            return false;
        }
        Date dteInicio = AtuxUtility.getStringToDate(txtFeInicial.getText().trim(), "dd/MM/yyyy"),
                dteFin = AtuxUtility.getStringToDate(txtFeFinal.getText().trim(), "dd/MM/yyyy");
        if (dteInicio.after(dteFin)) {
            JOptionPane.showMessageDialog(this, "Fecha de inicio mayor a la fecha final. Verifique.", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            txtFeFinal.select(0, 0);
            txtFeInicial.selectAll();
            txtFeInicial.requestFocus();
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        TrasladoProductosInterno.init();
        DlgListaPedidoTraslado dlgPedidoTraslado = new DlgListaPedidoTraslado(null, "Lista de Pedidos de Traslado", true);
        dlgPedidoTraslado.setVisible(true);
    }

}