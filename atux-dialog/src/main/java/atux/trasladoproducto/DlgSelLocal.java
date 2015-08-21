package atux.trasladoproducto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;
import app.JVentas;
import atux.inventario.reference.DBInventario;
import atux.trasladoproducto.reference.ConstantsTrasladoProducto;
import atux.trasladoproducto.reference.VariablesTrasladoProducto;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxTableModel;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;


public class DlgSelLocal extends JDialog {
    Frame parentFrame;
    AtuxTableModel tableModelListaLocales;
    ImageIcon iconBuscar = new ImageIcon(JVentas.class.getResource("/atux/resources/search32.png"));
    JLabel lblLocal = new JLabel();
    JTextField txtLocal = new JTextField();
    JScrollPane scrLocales = new JScrollPane();
    JTable tblLocales = new JTable();
    JPanel pnlRelacionLocales = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JLabel lblRelacionLocales = new JLabel();
    JLabel lblOpcionesT = new JLabel();
    JLabel lblSeleccionarT = new JLabel();
    JLabel lblSalirT = new JLabel();
    JButton btnBuscar = new JButton();

    public DlgSelLocal() {
        this(null, "", false);
    }

    public DlgSelLocal(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        parentFrame = parent;
        try {
            jbInit();
            AtuxUtility.centrarVentana(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(494, 490));
        this.getContentPane().setLayout(null);
        this.setFont(new Font("SansSerif", 0, 11));
        this.setTitle("búsqueda de Locales");
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                this_windowOpened(e);
            }
        });
        lblLocal.setText("Local :");
        lblLocal.setBounds(new Rectangle(20, 25, 40, 15));
        lblLocal.setFont(new Font("SansSerif", 0, 11));
        txtLocal.setBounds(new Rectangle(65, 20, 245, 25));
        txtLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                txtLocal_keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                txtLocal_keyReleased(e);
            }
        });
        scrLocales.setBounds(new Rectangle(20, 90, 440, 300));
        scrLocales.setFont(new Font("SansSerif", 0, 11));
        tblLocales.setFont(new Font("SansSerif", 0, 11));
        pnlRelacionLocales.setBounds(new Rectangle(20, 65, 440, 25));
        pnlRelacionLocales.setBackground(new Color(32, 105, 29));
        pnlRelacionLocales.setFont(new Font("SansSerif", 0, 11));
        pnlRelacionLocales.setLayout(xYLayout1);
        lblRelacionLocales.setText("Relacion de Locales :");
        lblRelacionLocales.setFont(new Font("SansSerif", 1, 11));
        lblRelacionLocales.setForeground(Color.white);
        lblOpcionesT.setText("Opciones :");
        lblOpcionesT.setBounds(new Rectangle(20, 400, 75, 15));
        lblOpcionesT.setFont(new Font("SansSerif", 1, 11));
        lblSeleccionarT.setText("[ Enter ] Seleccionar Local");
        lblSeleccionarT.setBounds(new Rectangle(20, 425, 145, 15));
        lblSeleccionarT.setFont(new Font("SansSerif", 1, 11));
        lblSeleccionarT.setForeground(new Color(32, 105, 29));
        lblSalirT.setText("[ Esc ] Salir");
        lblSalirT.setBounds(new Rectangle(380, 425, 75, 15));
        lblSalirT.setFont(new Font("SansSerif", 1, 11));
        lblSalirT.setForeground(new Color(32, 105, 29));
        btnBuscar.setBounds(new Rectangle(350, 15, 110, 30));
        btnBuscar.setFont(new Font("SansSerif", 0, 11));
        btnBuscar.setIcon(iconBuscar);
        btnBuscar.setMnemonic('b');
        btnBuscar.setText("Buscar");
        btnBuscar.setRequestFocusEnabled(false);
        this.getContentPane().add(btnBuscar, null);
        this.getContentPane().add(lblSalirT, null);
        this.getContentPane().add(lblSeleccionarT, null);
        this.getContentPane().add(lblOpcionesT, null);
        pnlRelacionLocales.add(lblRelacionLocales, new XYConstraints(10, 5, 165, 15));
        this.getContentPane().add(pnlRelacionLocales, null);
        scrLocales.getViewport().add(tblLocales, null);
        this.getContentPane().add(scrLocales, null);
        this.getContentPane().add(txtLocal, null);
        this.getContentPane().add(lblLocal, null);
    }

    void initListaLocales() {
        tableModelListaLocales = new AtuxTableModel(ConstantsTrasladoProducto.columnsListaLocalCodigoDescripcion, ConstantsTrasladoProducto.defaultValuesListaLocalCodigoDescripcion, 0);
        AtuxUtility.initSimpleList(tblLocales, tableModelListaLocales, ConstantsTrasladoProducto.columnsListaLocalCodigoDescripcion);
        actualizaLocales();
    }

    void actualizaLocales() {
        try {
            DBInventario.loadRelacionLocales(tableModelListaLocales, VariablesTrasladoProducto.vTipoLocal);
        } catch (SQLException sqlerr) {
            AtuxUtility.showMessage(this, "Error al obtener Lista de Locales !!! - " + sqlerr.getErrorCode() +
                    "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }
    }

    void txtLocal_keyPressed(KeyEvent e) {
        AtuxGridUtils.aceptarTeclaPresionada(e, tblLocales, txtLocal, 1);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) closeWindow(false);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = tblLocales.getSelectedRow();
            VariablesTrasladoProducto.vCodLocal = (String) tblLocales.getValueAt(row, 0);
            VariablesTrasladoProducto.vNoLocal = (String) tblLocales.getValueAt(row, 1);
            closeWindow(true);
        }
    }

    void txtLocal_keyReleased(KeyEvent e) {
        if (tblLocales.getRowCount() > 0) {
            AtuxGridUtils.buscarDescripcion(e, tblLocales, txtLocal, 1);
        }
    }

    void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }


    void this_windowOpened(WindowEvent e) {
        initListaLocales();
        AtuxUtility.setearPrimerRegistro(tblLocales, txtLocal, 1);
        txtLocal.requestFocus();
    }

}