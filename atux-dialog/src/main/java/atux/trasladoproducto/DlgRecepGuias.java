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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.DBTrasladoProducto;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;

/**
 * Copyright (c) 2010 Eckerd Pero S.A.<br>
 * <br>
 * Funcionalidad : Pantalla para la recepcion de Guías <br>
 * <br>
 * Historico de Creacion/Modificacion<br>
 * <br>
 * ID    PROGRAMADOR    FECHA/HORA            TIPO                 OBSERVACIoN
 * 000    GMATEO       10/03/2010 10:00:00   Creacion <br>
 * <br>
 *
 * @version 1.0<br>
 */
public class DlgRecepGuias extends JDialog {
    private final Log logger = LogFactory.getLog(getClass());
    JScrollPane scrListaGuias = new JScrollPane();
    JTable tblListaGuias = new JTable();
    AtuxTableModel tableModelListaGuias;
    Frame parentFrame;
    JPanel pnlGuiasPendientesProc = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JButton btnRelacion = new JButton();
    JLabel lblVerGuiaT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JLabel lblOpcionesT = new JLabel();
    JPanel pnlPieRelGuiasPend = new JPanel();
    JLabel lblTotalGuiasT = new JLabel();
    JLabel lblTotalGuias = new JLabel();

    public DlgRecepGuias() {
        this(null, "", true);
    }

    public DlgRecepGuias(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            AtuxUtility.centrarVentana(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(710, 500));
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setFont(new Font("SansSerif", 0, 11));
        //this.setResizable(true);
        this.setTitle("Recepción de Productos");
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        scrListaGuias.setFont(new Font("SansSerif", 0, 11));
        scrListaGuias.setBounds(new Rectangle(5, 30, 685, 345));
        tblListaGuias.setFont(new Font("SansSerif", 0, 11));
        tblListaGuias.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tblListaGuias_keyPressed(e);
            }
        });
        pnlGuiasPendientesProc.setBackground(new Color(32, 105, 29));
        pnlGuiasPendientesProc.setLayout(xYLayout2);
        pnlGuiasPendientesProc.setFont(new Font("SansSerif", 0, 11));
        pnlGuiasPendientesProc.setBounds(new Rectangle(5, 5, 685, 25));
        btnRelacion.setText("Relacion Guías Pendientes de Proceso");
        btnRelacion.setHorizontalAlignment(SwingConstants.LEFT);
        btnRelacion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRelacion.setBackground(new Color(32, 105, 29));
        btnRelacion.setFont(new Font("SansSerif", 1, 11));
        btnRelacion.setForeground(Color.white);
        btnRelacion.setMnemonic('R');
        btnRelacion.setDefaultCapable(false);
        btnRelacion.setRequestFocusEnabled(false);
        btnRelacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRelacion_actionPerformed(e);
            }
        });
        lblVerGuiaT.setText("[ F5 ] Ver Guía");
        lblVerGuiaT.setFont(new Font("SansSerif", 1, 11));
        lblVerGuiaT.setForeground(new Color(32, 105, 29));
        lblVerGuiaT.setBounds(new Rectangle(10, 425, 80, 20));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setForeground(new Color(32, 105, 29));
        lblSalirT.setBounds(new Rectangle(625, 425, 70, 20));
        lblOpcionesT.setText("Opciones:");
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        lblOpcionesT.setBounds(new Rectangle(10, 405, 55, 15));
        pnlPieRelGuiasPend.setBounds(new Rectangle(5, 375, 685, 25));
        pnlPieRelGuiasPend.setLayout(null);
        pnlPieRelGuiasPend.setForeground(Color.white);
        pnlPieRelGuiasPend.setFont(new Font("SansSerif", 1, 11));
        pnlPieRelGuiasPend.setBackground(new Color(32, 105, 29));
        lblTotalGuiasT.setText("Total de Guías:");
        lblTotalGuiasT.setBounds(new Rectangle(15, 5, 85, 15));
        lblTotalGuiasT.setFont(new Font("SansSerif", 1, 11));
        lblTotalGuiasT.setForeground(Color.white);
        lblTotalGuias.setText("0");
        lblTotalGuias.setBounds(new Rectangle(100, 5, 50, 15));
        lblTotalGuias.setFont(new Font("SansSerif", 1, 11));
        lblTotalGuias.setForeground(Color.white);
        lblTotalGuias.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlPieRelGuiasPend.add(lblTotalGuias, null);
        pnlPieRelGuiasPend.add(lblTotalGuiasT, null);
        this.getContentPane().add(pnlPieRelGuiasPend, null);
        this.getContentPane().add(lblOpcionesT, null);
        this.getContentPane().add(lblSalirT, null);
        this.getContentPane().add(lblVerGuiaT, null);
        pnlGuiasPendientesProc.add(btnRelacion, new XYConstraints(10, 5, 240, 15));
        this.getContentPane().add(pnlGuiasPendientesProc, null);
        scrListaGuias.getViewport().add(tblListaGuias, null);
        this.getContentPane().add(scrListaGuias, null);

        muestrarListaGuiasPend();
    }

    void muestrarListaGuiasPend() {
        tableModelListaGuias = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaGuiaRecepcionProducto, ConstantsTrasladoProducto.defaultValuesListaGuiaRecepcionProducto, 0);
        AtuxUtility.initSimpleList(tblListaGuias, tableModelListaGuias, ConstantsTrasladoProducto.columnsListaGuiaRecepcionProducto);
        obtenerListaGuias();
    }

    void obtenerListaGuias() {
        try {
            DBTrasladoProducto.obtenerGuiasPendientes(tableModelListaGuias);
            if (tblListaGuias.getRowCount() > 0) {
                lblTotalGuias.setText(String.valueOf(tblListaGuias.getRowCount()));
            }
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
            AtuxUtility.showMessage(this, "Error al obtener la relacion de Guías Pendientes - " + sqlerr.getErrorCode() + "\n" + sqlerr.toString(), null);
        }
    }

    void mostrarDetalleGuia() {
        int row = tblListaGuias.getSelectedRow();
        if (row == -1) {
            return;
        }
        DlgRecepProd dlgRecepProd = new DlgRecepProd(parentFrame, "Detalle de Guía", true);
        dlgRecepProd.setValoresCabecera(
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_CO_LOCAL_ORIGEN),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_NU_RECEPCION_PRODUCTO),
                ((String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_NU_SOLICITUD_PEDIDO)).trim(),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_GUIA_MATRIZ),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_FECHA),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_ITEMS),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_CA_PRODUCTOS),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_CARGADO),
                (String) tblListaGuias.getValueAt(row, ConstantsTrasladoProducto.LISTA_GUIA_RECEPCION_PRODUCTO_IN_AFECTA_STOCK)
        );
        dlgRecepProd.setVisible(true);
        tblListaGuias.requestFocus();
        obtenerListaGuias();
        tblListaGuias.repaint();
        tableModelListaGuias.fireTableDataChanged();
        if (tblListaGuias.getRowCount() > 0) {
            AtuxGridUtils.showCell(tblListaGuias, row, 0);
            lblTotalGuias.setText(String.valueOf(tblListaGuias.getRowCount()));
        }
    }

    void tblListaGuias_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F5) {
            e.consume();
            mostrarDetalleGuia();
        } else if (e.getKeyCode() == KeyEvent.VK_F10) {
            e.consume();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            e.consume();
            cancelarOperacion();
        }
    }

    void btnRelacion_actionPerformed(ActionEvent e) {
        colocarFocoEnLista();
    }

    void colocarFocoEnLista() {
        System.out.println(String.valueOf(tblListaGuias.getRowCount()));
        if (tblListaGuias.getRowCount() > 0) {
            AtuxGridUtils.showCell(tblListaGuias, 0, 0);
        }
        tblListaGuias.requestFocus();
    }

    void cancelarOperacion() {
        this.setVisible(false);
        this.dispose();
    }

    void this_windowOpened(WindowEvent e) {
        colocarFocoEnLista();
    }

}