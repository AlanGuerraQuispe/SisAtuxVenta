package atux.vistas.catalogo;

import atux.controllers.CPrincipioActivo;
import atux.controllers.CPrincipioActivoProducto;
import atux.modelgui.ModeloTablaPrincipioActivoProducto;
import atux.modelgui.ModeloTablaSimple;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarPrincipioActivo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.JLabel;

public class IPrincipioActivoProducto extends javax.swing.JPanel {
    private CPrincipioActivoProducto cp;
    private ModeloTablaPrincipioActivoProducto mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = 1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());
    JOptionPane op;


    public IPrincipioActivoProducto() {
        initComponents();
        lblMensaje.setVisible(false);
        cp = new CPrincipioActivoProducto();
        setFiltroTexto();
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();
        lbAviso.setVisible(false);
        cbxPrincipal.addItem("-----");
        cbxPrincipal.addItem("Si");
        cbxPrincipal.addItem("No");
        cbxPrincipal.setBounds(510, 41, 63, 25);
        txtCbxPrincipal.setBounds(510, 41, 63, 25);
    }

    public final void CargaDatos() {
        String Estado = "T";

        if (tipoSeleccion == 0) {
            Estado = "I";
        } else if (tipoSeleccion == 1) {
            Estado = "A";
        }

        mtp = new ModeloTablaPrincipioActivoProducto(AtuxVariables.vCodigoCompania, txtCodigo.getText(), Estado);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado, ModeloTablaPrincipioActivoProducto.anchoColumnas);
    }


    private void setFiltroTexto() {
        Helper.setFiltraEntrada(txtCodigoPrincipioActivo.getDocument(), FiltraEntrada.NUM_LETRAS, 10, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm) {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }

    private void selectionEvent(ListSelectionEvent e) {
        if (tblListado.getSelectedRow() != -1) {
            numRegistros = tblListado.getSelectedRow();
            cp.setPrincipioActivoProducto(mtp.getFila(tblListado.getSelectedRow()));
            setPrincipioActivoProducto();
            btnModificar.setEnabled(true);
        }
    }

    private void setPrincipioActivoProducto() {
        Limpiar();

        this.txtCodigoPrincipioActivo.setText(String.valueOf(cp.getPrincipioActivoProducto().getCoProductoInsumo()));
        txtDescripcionPrincipioActivo.setText(String.valueOf(cp.getPrincipioActivoProducto().getDeProductoInsumo()));

        //cbxPrincipal.setSelectedIndex(0);
        if ("S".equals(cp.getPrincipioActivoProducto().getInProductoInsumoPrincipal())) {
            cbxPrincipal.setSelectedIndex(1);
        } else if ("N".equals(cp.getPrincipioActivoProducto().getInProductoInsumoPrincipal())) {
            cbxPrincipal.setSelectedIndex(2);
        }

        if ("A".equals(cp.getPrincipioActivoProducto().getEsProductoInsumo())) {
            chbSetActivo(true);
        } else {
            chbSetActivo(false);
        }
        txtCbxPrincipal.setText(cbxPrincipal.getSelectedItem().toString());

    }

    private void Limpiar() {
        this.txtCodigoPrincipioActivo.setText("");
        txtDescripcionPrincipioActivo.setText("");
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas() {
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigo, lblCodigoPrincipioActivo, lblUnidad, lblCodigoPrincipioActivo, lblPrincipal, lblEstado, txtCodigo, txtDescripcion, txtUnidad}, false, "");
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);

        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        
        this.cbxPrincipal.setVisible(true);
        this.txtCbxPrincipal.setVisible(false);
    }

    private void DesActivarCasillas() {
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigo, lblCodigoPrincipioActivo, lblUnidad, lblCodigoPrincipioActivo, lblPrincipal, lblEstado, txtCodigo, txtDescripcion, txtUnidad}, false, "");
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);

        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);
        
        this.cbxPrincipal.setVisible(false);
        this.txtCbxPrincipal.setVisible(true);

        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtCodigoPrincipioActivo.getText());
    }

    public boolean verficarCambios() {
        // TODO aguerra verificar     

        if (!this.txtCodigoPrincipioActivo.getText().equals(String.valueOf(cp.getPrincipioActivoProducto().getCoProductoInsumo()))) {
            return true;
        } else if (!chbEstado.isSelected() != ("I".equals(cp.getPrincipioActivoProducto().getEsProductoInsumo()))) {
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException {
        cp.getPrincipioActivoProducto().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getPrincipioActivoProducto().setCoProducto(txtCodigo.getText());
        cp.getPrincipioActivoProducto().setNuRevisionProducto("0");
        cp.getPrincipioActivoProducto().setCoProductoInsumo(txtCodigoPrincipioActivo.getText());


        if (cbxPrincipal.getSelectedIndex() == 0) {
            cp.getPrincipioActivoProducto().setInProductoInsumoPrincipal(null);
        } else {
            cp.getPrincipioActivoProducto().setInProductoInsumoPrincipal(cbxPrincipal.getSelectedItem().toString().substring(0, 1));
        }
        cp.getPrincipioActivoProducto().setDeObservacion(null);
        if (chbEstado.isSelected()) {
            cp.getPrincipioActivoProducto().setEsProductoInsumo("A");
        } else {
            cp.getPrincipioActivoProducto().setEsProductoInsumo("I");
        }

        Boolean EstadoGuardar = false;

        if (!esActualizacion) {
            cp.getPrincipioActivoProducto().setIdCreaProductoInsumo(AtuxVariables.vIdUsuario);
            cp.getPrincipioActivoProducto().setFeCreaProductoInsumo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            EstadoGuardar = cp.guardarRegistro(cp.getPrincipioActivoProducto());
        } else {
            cp.getPrincipioActivoProducto().setIdModProductoInsumo(AtuxVariables.vIdUsuario);
            cp.getPrincipioActivoProducto().setFeModProductoInsumo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            int act = cp.actualizarRegistro(cp.getPrincipioActivoProducto());
            if (act == 1) {
                EstadoGuardar = true;
            }
        }

        if (EstadoGuardar) {
            EstadoGuardar = true;
        } else {
            EstadoGuardar = false;
        }
        return EstadoGuardar;
    }

    private Date FormatoFecha(String oldFecha) {
        Date Fecha = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IPrincipioActivoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void chbSetActivo(boolean opcion) {
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104, 204, 0));
        chbEstado.setForeground(Color.BLACK);
        if (!opcion) {
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    public void setCodigoProducto(String CodProducto) {
        txtCodigo.setText(CodProducto);
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        txtDescripcion.setText(DescripcionProducto);
    }

    public void setUnidadProducto(String UnidadProducto) {
        txtUnidad.setText(UnidadProducto);
    }

    private void getOptionPane() {
        if (op != null) {
            return;
        }
        Component pdr = this.getParent();
        while (pdr.getParent() != null) {
            if (pdr instanceof JOptionPane) {
                op = (JOptionPane) pdr;
                break;
            }
            pdr = pdr.getParent();
        }
    }

    public JLabel getAviso() {
        return lbAviso;
    }

    private void BuscarInfoPrincipioActivo(String Codigo) {
        CPrincipioActivo BG1 = new CPrincipioActivo();
        BG1.getRegistroPorPk(new String[]{Codigo, "1"});
        txtDescripcionPrincipioActivo.setText(BG1.getPrincipioActivo().getDePrincipioActivo().trim());
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        lblCodigoPrincipioActivo = new javax.swing.JLabel();
        lblPrincipal = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        txtUnidad = new elaprendiz.gui.textField.TextField();
        txtDescripcionPrincipioActivo = new elaprendiz.gui.textField.TextField();
        txtCodigoPrincipioActivo = new elaprendiz.gui.textField.TextField();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        cbxPrincipal = new javax.swing.JComboBox();
        txtCbxPrincipal = new elaprendiz.gui.textField.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        lblMensaje = new javax.swing.JLabel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(new java.awt.Dimension(778, 330));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelImage1FocusGained(evt);
            }
        });

        pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlEntradas.setEnabled(false);
        pnlEntradas.setOpaque(false);
        pnlEntradas.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradas.setLayout(null);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblCodigo.setText("Codigo:");
        pnlEntradas.add(lblCodigo);
        lblCodigo.setBounds(16, 6, 48, 27);

        lblUnidad.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblUnidad.setText("Unidad:");
        pnlEntradas.add(lblUnidad);
        lblUnidad.setBounds(450, 11, 48, 16);

        lblCodigoPrincipioActivo.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblCodigoPrincipioActivo.setText("Insumo:");
        pnlEntradas.add(lblCodigoPrincipioActivo);
        lblCodigoPrincipioActivo.setBounds(16, 39, 60, 24);

        lblPrincipal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPrincipal.setText("Principal:");
        pnlEntradas.add(lblPrincipal);
        lblPrincipal.setBounds(450, 41, 55, 22);

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtCodigo);
        txtCodigo.setBounds(80, 8, 80, 24);

        txtDescripcion.setEditable(false);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setName("pdescrip"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtDescripcion);
        txtDescripcion.setBounds(173, 8, 270, 24);

        txtUnidad.setEditable(false);
        txtUnidad.setDireccionDeSombra(30);
        txtUnidad.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtUnidad.setFont(new java.awt.Font("Arial", 0, 12));
        txtUnidad.setName("pdescrip"); // NOI18N
        txtUnidad.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtUnidad);
        txtUnidad.setBounds(508, 8, 130, 24);

        txtDescripcionPrincipioActivo.setEditable(false);
        txtDescripcionPrincipioActivo.setDireccionDeSombra(30);
        txtDescripcionPrincipioActivo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcionPrincipioActivo.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcionPrincipioActivo.setName("pdescrip"); // NOI18N
        txtDescripcionPrincipioActivo.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtDescripcionPrincipioActivo);
        txtDescripcionPrincipioActivo.setBounds(172, 39, 270, 24);

        txtCodigoPrincipioActivo.setEditable(false);
        txtCodigoPrincipioActivo.setToolTipText("Presione F1 para ver mas datos");
        txtCodigoPrincipioActivo.setDireccionDeSombra(30);
        txtCodigoPrincipioActivo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoPrincipioActivo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoPrincipioActivo.setName("pcodigo"); // NOI18N
        txtCodigoPrincipioActivo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoPrincipioActivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoPrincipioActivoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoPrincipioActivoFocusLost(evt);
            }
        });
        txtCodigoPrincipioActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoPrincipioActivoActionPerformed(evt);
            }
        });
        txtCodigoPrincipioActivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPrincipioActivoKeyReleased(evt);
            }
        });
        pnlEntradas.add(txtCodigoPrincipioActivo);
        txtCodigoPrincipioActivo.setBounds(80, 39, 80, 24);

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Médico");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));
        pnlEntradas.add(lbAviso);
        lbAviso.setBounds(650, 10, 41, 17);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblEstado.setText("Estado:");
        pnlEntradas.add(lblEstado);
        lblEstado.setBounds(580, 43, 49, 16);

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setEnabled(false);
        chbEstado.setPreferredSize(new java.awt.Dimension(100, 25));
        chbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEstadoActionPerformed(evt);
            }
        });
        chbEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbEstadoKeyReleased(evt);
            }
        });
        pnlEntradas.add(chbEstado);
        chbEstado.setBounds(635, 39, 108, 25);

        pnlEntradas.add(cbxPrincipal);
        cbxPrincipal.setBounds(510, 41, 63, 25);

        txtCbxPrincipal.setEditable(false);
        txtCbxPrincipal.setDireccionDeSombra(30);
        txtCbxPrincipal.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCbxPrincipal.setFont(new java.awt.Font("Arial", 0, 12));
        txtCbxPrincipal.setName("pdescrip"); // NOI18N
        txtCbxPrincipal.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradas.add(txtCbxPrincipal);
        txtCbxPrincipal.setBounds(590, 60, 20, 20);

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblListado);

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorTDeCambio.setOpaque(false);
        pnlBuscadorTDeCambio.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorTDeCambio.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnPrimero);
        btnPrimero.setBounds(8, 6, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnAnterior);
        btnAnterior.setBounds(61, 6, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnSiguiente);
        btnSiguiente.setBounds(106, 6, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnUltimo);
        btnUltimo.setBounds(152, 6, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbTodos);
        rbTodos.setBounds(205, 6, 69, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbAtivos);
        rbAtivos.setBounds(279, 6, 77, 25);

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbNoActivos);
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbNoActivos);
        rbNoActivos.setBounds(360, 6, 101, 25);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesTDeCambio.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnNuevo);
        btnNuevo.setBounds(9, 6, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnModificar);
        btnModificar.setBounds(93, 6, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnGuardar);
        btnGuardar.setBounds(196, 6, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnCancelar);
        btnCancelar.setBounds(290, 6, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnSalir);
        btnSalir.setBounds(390, 6, 88, 25);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("<html><body>Presione F1<br>para ver mas Datos</body></html>");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = 0;
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setPrincipioActivoProducto(mtp.getFila(numRegistros));
        setPrincipioActivoProducto();
        return;
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1) {
            numRegistros = 0;
        }
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setPrincipioActivoProducto(mtp.getFila(numRegistros));
        setPrincipioActivoProducto();

        return;
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros) {
            numRegistros = finalPag;
        }
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setPrincipioActivoProducto(mtp.getFila(numRegistros));
        setPrincipioActivoProducto();

        return;
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = finalPag;
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setPrincipioActivoProducto(mtp.getFila(numRegistros));
        setPrincipioActivoProducto();

        return;
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0;
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
    }//GEN-LAST:event_rbAtivosActionPerformed

    private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0;
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(false);
        Limpiar();
    }//GEN-LAST:event_rbNoActivosActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;
        Limpiar();
        ActivarCasillas();
        txtCodigoPrincipioActivo.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtCodigoPrincipioActivo.requestFocus();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        if (ECampos.esObligatorio(this.pnlEntradas, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!this.verficarCambios()) {
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
        }

        boolean correcto = false;

        if (!this.verficarCambios()) {
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!correcto) {
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas, false);
            return;
        }

        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        CargaDatos();
        DesActivarCasillas();
        tblListado.requestFocus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }

        DesActivarCasillas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
    }//GEN-LAST:event_chbEstadoActionPerformed

    private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                btnGuardar.requestFocus();
                break;
        }
    }//GEN-LAST:event_chbEstadoKeyReleased

    private void txtCodigoPrincipioActivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrincipioActivoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                int tipoSeleccionG = 1;
                BuscarPrincipioActivo pvc = new BuscarPrincipioActivo(tipoSeleccionG, this);
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccionG == -1 ? "Mostrando Listado de Principios Activos" : (tipoSeleccionG == 1 ? "Mostrando Listado de Principios Activos Activos" : "Mostrando Listado de Principios Activos No activos"));
                JOptionPane.showInternalOptionDialog(this, pvc, msj, JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);


                if (pvc.getPrincipioActivo() != null) {
                    CPrincipioActivo cpF = new CPrincipioActivo();
                    cpF.setPrincipioActivo(pvc.getPrincipioActivo());
                    this.txtCodigoPrincipioActivo.setText(cpF.getPrincipioActivo().getCoPrincipioActivo());
                    this.txtDescripcionPrincipioActivo.setText(cpF.getPrincipioActivo().getDePrincipioActivo());
                }
                break;
            case KeyEvent.VK_ENTER:
                BuscarInfoPrincipioActivo(txtCodigoPrincipioActivo.getText());
                cbxPrincipal.requestFocus();
                break;
        }
    }//GEN-LAST:event_txtCodigoPrincipioActivoKeyReleased

    private void txtCodigoPrincipioActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoPrincipioActivoActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPrincipioActivoActionPerformed

    private void panelImage1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelImage1FocusGained

    }//GEN-LAST:event_panelImage1FocusGained

    private void txtCodigoPrincipioActivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPrincipioActivoFocusGained
        lblMensaje.setVisible(true);
    }//GEN-LAST:event_txtCodigoPrincipioActivoFocusGained

    private void txtCodigoPrincipioActivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPrincipioActivoFocusLost
        lblMensaje.setVisible(false);
    }//GEN-LAST:event_txtCodigoPrincipioActivoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxPrincipal;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoPrincipioActivo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPrincipal;
    private javax.swing.JLabel lblUnidad;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    public elaprendiz.gui.textField.TextField txtCbxPrincipal;
    private elaprendiz.gui.textField.TextField txtCodigo;
    public elaprendiz.gui.textField.TextField txtCodigoPrincipioActivo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    public elaprendiz.gui.textField.TextField txtDescripcionPrincipioActivo;
    private elaprendiz.gui.textField.TextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
