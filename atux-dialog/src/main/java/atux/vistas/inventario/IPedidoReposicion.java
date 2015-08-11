package atux.vistas.inventario;

import app.JVentas;
import atux.controllers.CLocal;
import atux.controllers.CSimpleModelo;
import atux.modelbd.Local;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.buscar.BuscarProductosFaltaCero;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */
public class IPedidoReposicion extends javax.swing.JInternalFrame {
    
    private final Log logger = LogFactory.getLog(getClass());    
    private CSimpleModelo controllerPedidoRep;
    private ProductoLocal prodLocal;
    private ArrayList<ProductoLocal> lstProductos;
    ModeloTablaSimple model;
    private ArrayList outParameters_mes;
    public boolean vPedidoOK;
    
    public IPedidoReposicion() {
        initComponents();           
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new elaprendiz.gui.panel.PanelImage();
        pnlHead = new javax.swing.JPanel();
        lblLocal = new javax.swing.JLabel();
        txtLocal = new elaprendiz.gui.textField.TextField();
        lblOpcionT = new javax.swing.JLabel();
        lblOpcion = new javax.swing.JLabel();
        lblParametroMax = new javax.swing.JLabel();
        txtParamMaxDias = new elaprendiz.gui.textField.TextField();
        txtParamMinDias = new elaprendiz.gui.textField.TextField();
        lblParametroMin = new javax.swing.JLabel();
        txtParamDiasRotacion = new elaprendiz.gui.textField.TextField();
        lblPeriodo = new javax.swing.JLabel();
        lblIgnorarProSinSaldo = new javax.swing.JLabel();
        lblTiempoSuninistro = new javax.swing.JLabel();
        lblTransito = new javax.swing.JLabel();
        lblMinExhibicion = new javax.swing.JLabel();
        lblSumarCompras = new javax.swing.JLabel();
        lblTipoOperacion = new javax.swing.JLabel();
        lblOrigenProductos = new javax.swing.JLabel();
        lblProdActivos = new javax.swing.JLabel();
        lblProdFraccionados = new javax.swing.JLabel();
        lblProdFaltaCero = new javax.swing.JLabel();
        btnIgnorarSaldo = new javax.swing.JButton();
        btnSumarSuministro = new javax.swing.JButton();
        btnSumarTransito = new javax.swing.JButton();
        btnSumarMinExhibicion = new javax.swing.JButton();
        btnSumarComPendientes = new javax.swing.JButton();
        btnProductosActivos = new javax.swing.JButton();
        btnProductosFraccionados = new javax.swing.JButton();
        btnFaltaCero = new javax.swing.JButton();
        jcbTipoOperacion = new javax.swing.JComboBox();
        jcbOrigenProductos = new javax.swing.JComboBox();
        dcFechaIni = new com.toedter.calendar.JDateChooser();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        pnlBody = new javax.swing.JPanel();
        jScrollPaneDet = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlFoot = new javax.swing.JPanel();
        lblStockPromedio = new javax.swing.JLabel();
        txtStockPromedio = new elaprendiz.gui.textField.TextField();
        lblPrecioStockProm = new javax.swing.JLabel();
        txtPrecioStockProm = new elaprendiz.gui.textField.TextField();
        lblCostoStockProm = new javax.swing.JLabel();
        txtCostoStockCalculado = new elaprendiz.gui.textField.TextField();
        lblTotalStockPos = new javax.swing.JLabel();
        txtTotalStockPos = new elaprendiz.gui.textField.TextField();
        lblTotalStockNeg = new javax.swing.JLabel();
        txtTotalStockNeg = new elaprendiz.gui.textField.TextField();
        lblStockRepFinal = new javax.swing.JLabel();
        txtStockRepFinal = new elaprendiz.gui.textField.TextField();
        lblMes01 = new javax.swing.JLabel();
        lblMes02 = new javax.swing.JLabel();
        txtMes01 = new elaprendiz.gui.textField.TextField();
        txtMes02 = new elaprendiz.gui.textField.TextField();
        lblMes03 = new javax.swing.JLabel();
        txtMes03 = new elaprendiz.gui.textField.TextField();
        lblMes04 = new javax.swing.JLabel();
        txtMes04 = new elaprendiz.gui.textField.TextField();
        pnlButtons = new javax.swing.JPanel();
        btnPedidoDefault = new javax.swing.JButton();
        btnCalcularReposicion = new javax.swing.JButton();
        btnVerPedido = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnProductoFaltaCero = new javax.swing.JButton();

        setTitle("Pedido de reposición");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        panelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlHead.setBackground(new java.awt.Color(51, 153, 255));
        pnlHead.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblLocal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblLocal.setText("Local");

        txtLocal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOpcionT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblOpcionT.setText("Opción");

        lblOpcion.setBackground(new java.awt.Color(204, 204, 255));
        lblOpcion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblOpcion.setForeground(new java.awt.Color(255, 255, 0));
        lblOpcion.setText("Reponer");

        lblParametroMax.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblParametroMax.setText("Max y Min (#Días Rep)");

        txtParamMaxDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtParamMinDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblParametroMin.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblParametroMin.setText("Días de rotación");

        txtParamDiasRotacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPeriodo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPeriodo.setText("Periodo de revisión");

        lblIgnorarProSinSaldo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblIgnorarProSinSaldo.setText("Ignorar Prod. Sin Saldo");

        lblTiempoSuninistro.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTiempoSuninistro.setText("Sumar Tiempo de Suministro");

        lblTransito.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTransito.setText("Sumar Transito");

        lblMinExhibicion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMinExhibicion.setText("Sumar Minimo Exhibición");

        lblSumarCompras.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblSumarCompras.setText("Sumar Compras Pendientes");

        lblTipoOperacion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoOperacion.setText("Tipo de Operación");

        lblOrigenProductos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblOrigenProductos.setText("Origen de Prod.");

        lblProdActivos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblProdActivos.setText("Solo Prod. Activos");

        lblProdFraccionados.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblProdFraccionados.setText("Productos Fraccionados");

        lblProdFaltaCero.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblProdFaltaCero.setText("Falta 0");

        btnIgnorarSaldo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnIgnorarSaldo.setContentAreaFilled(false);
        btnIgnorarSaldo.setMaximumSize(new java.awt.Dimension(4, 4));
        btnIgnorarSaldo.setMinimumSize(new java.awt.Dimension(4, 4));
        btnIgnorarSaldo.setPreferredSize(new java.awt.Dimension(4, 4));
        btnIgnorarSaldo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIgnorarSaldoMouseClicked(evt);
            }
        });

        btnSumarSuministro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSumarSuministro.setContentAreaFilled(false);
        btnSumarSuministro.setMaximumSize(new java.awt.Dimension(4, 4));
        btnSumarSuministro.setMinimumSize(new java.awt.Dimension(4, 4));
        btnSumarSuministro.setPreferredSize(new java.awt.Dimension(4, 4));
        btnSumarSuministro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSumarSuministroMouseClicked(evt);
            }
        });

        btnSumarTransito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSumarTransito.setContentAreaFilled(false);
        btnSumarTransito.setMaximumSize(new java.awt.Dimension(4, 4));
        btnSumarTransito.setMinimumSize(new java.awt.Dimension(4, 4));
        btnSumarTransito.setPreferredSize(new java.awt.Dimension(4, 4));
        btnSumarTransito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSumarTransitoMouseClicked(evt);
            }
        });

        btnSumarMinExhibicion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSumarMinExhibicion.setContentAreaFilled(false);
        btnSumarMinExhibicion.setMaximumSize(new java.awt.Dimension(4, 4));
        btnSumarMinExhibicion.setMinimumSize(new java.awt.Dimension(4, 4));
        btnSumarMinExhibicion.setPreferredSize(new java.awt.Dimension(4, 4));
        btnSumarMinExhibicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSumarMinExhibicionMouseClicked(evt);
            }
        });

        btnSumarComPendientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSumarComPendientes.setContentAreaFilled(false);
        btnSumarComPendientes.setMaximumSize(new java.awt.Dimension(4, 4));
        btnSumarComPendientes.setMinimumSize(new java.awt.Dimension(4, 4));
        btnSumarComPendientes.setPreferredSize(new java.awt.Dimension(4, 4));
        btnSumarComPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSumarComPendientesMouseClicked(evt);
            }
        });

        btnProductosActivos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductosActivos.setContentAreaFilled(false);
        btnProductosActivos.setMaximumSize(new java.awt.Dimension(4, 4));
        btnProductosActivos.setMinimumSize(new java.awt.Dimension(4, 4));
        btnProductosActivos.setPreferredSize(new java.awt.Dimension(4, 4));
        btnProductosActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosActivosMouseClicked(evt);
            }
        });

        btnProductosFraccionados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductosFraccionados.setContentAreaFilled(false);
        btnProductosFraccionados.setMaximumSize(new java.awt.Dimension(4, 4));
        btnProductosFraccionados.setMinimumSize(new java.awt.Dimension(4, 4));
        btnProductosFraccionados.setPreferredSize(new java.awt.Dimension(4, 4));
        btnProductosFraccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosFraccionadosMouseClicked(evt);
            }
        });

        btnFaltaCero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFaltaCero.setContentAreaFilled(false);
        btnFaltaCero.setMaximumSize(new java.awt.Dimension(4, 4));
        btnFaltaCero.setMinimumSize(new java.awt.Dimension(4, 4));
        btnFaltaCero.setPreferredSize(new java.awt.Dimension(4, 4));
        btnFaltaCero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFaltaCeroMouseClicked(evt);
            }
        });

        jcbTipoOperacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));

        jcbOrigenProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "W", "X", "X", "Z" }));

        dcFechaIni.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaIni.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaIni.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaIni.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaIni.setRequestFocusEnabled(false);

        dcFechaFin.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaFin.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaFin.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaFin.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaFin.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(lblLocal)
                        .addGap(29, 29, 29)
                        .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(lblIgnorarProSinSaldo)
                        .addGap(36, 36, 36)
                        .addComponent(btnIgnorarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(lblTipoOperacion)
                        .addGap(30, 30, 30)
                        .addComponent(jcbTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(lblOpcionT)
                        .addGap(28, 28, 28)
                        .addComponent(lblOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(lblTiempoSuninistro)
                        .addGap(34, 34, 34)
                        .addComponent(btnSumarSuministro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(lblOrigenProductos)
                        .addGap(34, 34, 34)
                        .addComponent(jcbOrigenProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(lblPeriodo)
                        .addGap(33, 33, 33)
                        .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(lblProdFaltaCero)
                        .addGap(39, 39, 39)
                        .addComponent(btnFaltaCero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(lblTransito)
                        .addGap(47, 47, 47)
                        .addComponent(btnSumarTransito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlHeadLayout.createSequentialGroup()
                                .addComponent(lblParametroMin)
                                .addGap(51, 51, 51)
                                .addComponent(txtParamDiasRotacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlHeadLayout.createSequentialGroup()
                                .addComponent(lblParametroMax)
                                .addGap(12, 12, 12)
                                .addComponent(txtParamMaxDias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlHeadLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtParamMinDias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(lblSumarCompras)
                                .addGap(42, 42, 42)
                                .addComponent(btnSumarComPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(lblProdActivos)
                                .addGap(37, 37, 37)
                                .addComponent(btnProductosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(lblMinExhibicion)
                                .addGap(38, 38, 38)
                                .addComponent(btnSumarMinExhibicion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lblProdFraccionados)
                                .addGap(45, 45, 45)
                                .addComponent(btnProductosFraccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblLocal))
                    .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIgnorarProSinSaldo)
                    .addComponent(btnIgnorarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoOperacion)
                    .addComponent(jcbTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblOpcionT))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblOpcion))
                    .addComponent(lblTiempoSuninistro)
                    .addComponent(btnSumarSuministro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrigenProductos)
                    .addComponent(jcbOrigenProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblParametroMax))
                    .addComponent(txtParamMaxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParamMinDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSumarCompras)
                    .addComponent(btnSumarComPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProdActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProductosActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblParametroMin)
                    .addComponent(lblMinExhibicion)
                    .addComponent(btnSumarMinExhibicion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProdFraccionados)
                    .addComponent(btnProductosFraccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParamDiasRotacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeriodo)
                    .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProdFaltaCero)
                    .addComponent(btnFaltaCero, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTransito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSumarTransito, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlBody.setBackground(new java.awt.Color(51, 153, 255));
        pnlBody.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPaneDet.setAutoscrolls(true);
        jScrollPaneDet.setHorizontalScrollBar(null);
        jScrollPaneDet.setPreferredSize(new java.awt.Dimension(452, 150));

        tblDetalle.setRowHeight(24);
        tblDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDetalleKeyReleased(evt);
            }
        });
        jScrollPaneDet.setViewportView(tblDetalle);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneDet, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneDet, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        pnlFoot.setBackground(new java.awt.Color(51, 153, 255));
        pnlFoot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStockPromedio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblStockPromedio.setText("Stock Promedio");

        txtStockPromedio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrecioStockProm.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPrecioStockProm.setText("S/. Stock Prom");

        txtPrecioStockProm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCostoStockProm.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCostoStockProm.setText("S/. Rep. Costo");

        txtCostoStockCalculado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTotalStockPos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotalStockPos.setText("Tot Stock ( + )");

        txtTotalStockPos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTotalStockNeg.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotalStockNeg.setText("Tot Stock ( - )");

        txtTotalStockNeg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStockRepFinal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblStockRepFinal.setText("Stock. Rep.Final");

        txtStockRepFinal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMes01.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMes01.setText("MES_01");

        lblMes02.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMes02.setText("MES_02");

        txtMes01.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtMes02.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMes03.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMes03.setText("MES_03");

        txtMes03.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMes04.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMes04.setText("MES_04");

        txtMes04.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlFootLayout = new javax.swing.GroupLayout(pnlFoot);
        pnlFoot.setLayout(pnlFootLayout);
        pnlFootLayout.setHorizontalGroup(
            pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFootLayout.createSequentialGroup()
                .addGroup(pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblStockPromedio)
                        .addGap(13, 13, 13)
                        .addComponent(txtStockPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblCostoStockProm)
                        .addGap(9, 9, 9)
                        .addComponent(txtCostoStockCalculado, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblTotalStockNeg)
                        .addGap(23, 23, 23)
                        .addComponent(txtTotalStockNeg, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMes01)
                        .addGap(10, 10, 10)
                        .addComponent(txtMes01, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblMes03)
                        .addGap(10, 10, 10)
                        .addComponent(txtMes03, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblPrecioStockProm)
                        .addGap(6, 6, 6)
                        .addComponent(txtPrecioStockProm, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblTotalStockPos)
                        .addGap(8, 8, 8)
                        .addComponent(txtTotalStockPos, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblStockRepFinal)
                        .addGap(12, 12, 12)
                        .addComponent(txtStockRepFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMes02)
                        .addGap(10, 10, 10)
                        .addComponent(txtMes02, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblMes04)
                        .addGap(10, 10, 10)
                        .addComponent(txtMes04, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlFootLayout.setVerticalGroup(
            pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFootLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStockPromedio)
                    .addComponent(txtStockPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostoStockProm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoStockCalculado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalStockNeg)
                    .addComponent(txtTotalStockNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes01, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes03, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioStockProm)
                    .addComponent(txtPrecioStockProm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalStockPos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalStockPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockRepFinal)
                    .addComponent(txtStockRepFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes02, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes04, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlButtons.setOpaque(false);
        pnlButtons.setPreferredSize(new java.awt.Dimension(882, 45));

        btnPedidoDefault.setBackground(new java.awt.Color(51, 153, 255));
        btnPedidoDefault.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPedidoDefault.setForeground(new java.awt.Color(255, 255, 255));
        btnPedidoDefault.setText( "<html><body>Valores x<br>Default</body></html>");
        btnPedidoDefault.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnPedidoDefault.setPreferredSize(new java.awt.Dimension(80, 18));

        btnCalcularReposicion.setBackground(new java.awt.Color(51, 153, 255));
        btnCalcularReposicion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCalcularReposicion.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcularReposicion.setText( "<html><body>Calcular<br>Reposición</body></html>");
        btnCalcularReposicion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnCalcularReposicion.setPreferredSize(new java.awt.Dimension(80, 18));
        btnCalcularReposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularReposicionActionPerformed(evt);
            }
        });

        btnVerPedido.setBackground(new java.awt.Color(51, 153, 255));
        btnVerPedido.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnVerPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPedido.setText( "<html><body>Ver pedido<br>Reposición</body></html>");
        btnVerPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnVerPedido.setPreferredSize(new java.awt.Dimension(80, 18));
        btnVerPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPedidoActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText( "<html><body>Salir</body></html>");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnSalir.setPreferredSize(new java.awt.Dimension(75, 18));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnProductoFaltaCero.setBackground(new java.awt.Color(51, 153, 255));
        btnProductoFaltaCero.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnProductoFaltaCero.setForeground(new java.awt.Color(255, 255, 255));
        btnProductoFaltaCero.setText( "<html><body>Productos<br>Falta 0</body></html>");
        btnProductoFaltaCero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnProductoFaltaCero.setPreferredSize(new java.awt.Dimension(80, 18));
        btnProductoFaltaCero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoFaltaCeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnPedidoDefault, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnProductoFaltaCero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnCalcularReposicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnVerPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPedidoDefault, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnProductoFaltaCero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCalcularReposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnVerPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlFoot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHead, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlFoot, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnlButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtLocal.setText(AtuxVariables.vDescripcionLocal);            
        CLocal cLocal = new CLocal();
        Local local = cLocal.getInfoParametrosPedidoRep();
        
        txtParamMaxDias.setText(String.valueOf(local.getNuMaxDiasReposicion()));
        txtParamMinDias.setText(String.valueOf(local.getNuMinDiasReposicion()));        
        txtParamDiasRotacion.setText(String.valueOf(local.getNuDiasRotacionPromedio()));        
        
        String feIni = null;
        String feFin = null;
        try {
            feIni = AtuxSearch.getFechaHoraParametroBD(AtuxVariables.FORMATO_FECHA, local.getNuDiasRotacionPromedio()*-1);
            feFin = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA);
            
        } catch (SQLException ex) {
            logger.error("Error al obtener el numero de dias de rotación en formInternalFrameActivated" + ex.getMessage());            
        }                
        
        this.dcFechaIni.setDate(AtuxUtility.getStringToDate(feIni,"dd/MM/yyyy"));    
        this.dcFechaFin.setDate(AtuxUtility.getStringToDate(feFin,"dd/MM/yyyy"));                                                
        
        estadoBoton(btnIgnorarSaldo,    local.getInIgnorarProdSinSaldo().equalsIgnoreCase("S"));
        estadoBoton(btnSumarSuministro, local.getInSumarTiempoSuministro().equalsIgnoreCase("S"));        
        estadoBoton(btnSumarTransito,   local.getInSumarTransito().equalsIgnoreCase("S"));
        estadoBoton(btnSumarMinExhibicion,local.getInSumarMinExhibicion().equalsIgnoreCase("S"));
        estadoBoton(btnSumarComPendientes,local.getInSumarComprasPendientes().equalsIgnoreCase("S"));
        estadoBoton(btnProductosActivos,local.getInSoloProdActivos().equalsIgnoreCase("S"));
        estadoBoton(btnProductosFraccionados,local.getInProductosFraccionados().equalsIgnoreCase("S"));
        estadoBoton(btnFaltaCero,local.getInFaltaCero().equalsIgnoreCase("S"));

        try {
            String indicador = AtuxSearch.devuelveEstadoIndicador();            
            if(indicador.equals("S")){
                obtieneListaPedidoReposicion();
            }            
        } catch (SQLException e) {
            logger.error("Error al obtener el indicador de reposición "+ e.getMessage());
        }

        if (tblDetalle.getRowCount() > 0) {                
            AtuxGridUtils.showCell(tblDetalle, 0, 1);
            prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
            muestraDetalleProd(prodLocal);
        }            
    }//GEN-LAST:event_formInternalFrameActivated
            
    private void btnVerPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPedidoActionPerformed
        if (tblDetalle.getRowCount() > 0) {
                lstProductos = new ArrayList<ProductoLocal>();
             try {                      
                 model=(ModeloTablaSimple) tblDetalle.getModel();
                 for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                         prodLocal = (ProductoLocal)model.getFila(i);
                         lstProductos.add(prodLocal);
                 }                                 
                } catch (Exception e) {
                    logger.info("Error al cargar la lista final " + e.getMessage());                        
                }
             
            IPedidoReposicionAGenerar iPedidoRepAGenerar = new IPedidoReposicionAGenerar(new Frame(),true,this);
            iPedidoRepAGenerar.arrayListProdReponer = lstProductos;
            iPedidoRepAGenerar.getListaPedidoRepFinal();
            iPedidoRepAGenerar.setVisible(true);            
            }
                               
    }//GEN-LAST:event_btnVerPedidoActionPerformed

    private void btnCalcularReposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularReposicionActionPerformed
        try {
            guardarParametros();
            recalMAXMIN(true);
        } catch (SQLException ex) {
            Logger.getLogger(IPedidoReposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCalcularReposicionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIgnorarSaldoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIgnorarSaldoMouseClicked
       estadoBoton(btnIgnorarSaldo,!btnIgnorarSaldo.isSelected());
    }//GEN-LAST:event_btnIgnorarSaldoMouseClicked

    private void btnSumarSuministroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSumarSuministroMouseClicked
       estadoBoton(btnSumarSuministro,!btnSumarSuministro.isSelected());       
    }//GEN-LAST:event_btnSumarSuministroMouseClicked

    private void btnSumarTransitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSumarTransitoMouseClicked
       estadoBoton(btnSumarTransito,!btnSumarTransito.isSelected());
    }//GEN-LAST:event_btnSumarTransitoMouseClicked

    private void btnSumarMinExhibicionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSumarMinExhibicionMouseClicked
       estadoBoton(btnSumarMinExhibicion,!btnSumarMinExhibicion.isSelected());
    }//GEN-LAST:event_btnSumarMinExhibicionMouseClicked

    private void btnSumarComPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSumarComPendientesMouseClicked
       estadoBoton(btnSumarComPendientes,!btnSumarComPendientes.isSelected());
    }//GEN-LAST:event_btnSumarComPendientesMouseClicked

    private void btnProductosActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosActivosMouseClicked
       estadoBoton(btnProductosActivos,!btnProductosActivos.isSelected());
    }//GEN-LAST:event_btnProductosActivosMouseClicked

    private void btnProductosFraccionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosFraccionadosMouseClicked
       estadoBoton(btnProductosFraccionados,!btnProductosFraccionados.isSelected());
    }//GEN-LAST:event_btnProductosFraccionadosMouseClicked

    private void btnFaltaCeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFaltaCeroMouseClicked
        estadoBoton(btnFaltaCero,!btnFaltaCero.isSelected());
    }//GEN-LAST:event_btnFaltaCeroMouseClicked

    private void tblDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetalleKeyReleased
        if (tblDetalle != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
            muestraDetalleProd(prodLocal);
            evt.consume();
        }
    }//GEN-LAST:event_tblDetalleKeyReleased

    private void tblDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetalleKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
            muestraDetalleProd(prodLocal);
            ingresaCantPedida(prodLocal,tblDetalle.getSelectedRow());
            evt.consume();
        }
        else if (tblDetalle != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            AtuxGridUtils.aceptarTeclaPresionada(evt, tblDetalle, null, 1);
            evt.consume();
        }
    }//GEN-LAST:event_tblDetalleKeyPressed

    private void btnProductoFaltaCeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoFaltaCeroActionPerformed
        BuscarProductosFaltaCero pvc = new BuscarProductosFaltaCero();        
        pvc.setPreferredSize(new Dimension(640, 350));        
        JOptionPane.showInternalOptionDialog(this, pvc, "Mostrando los productos no atendidos",JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{pvc.getAviso()},null);        
    }//GEN-LAST:event_btnProductoFaltaCeroActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        if(vPedidoOK){
            this.dispose();
        }
    }//GEN-LAST:event_formFocusGained
    
    void recalMAXMIN(boolean vAceptarRec) {
        if (vAceptarRec) {
            if (JOptionPane.showConfirmDialog(this,
                    "Esta seguro de Recalcular los Máximos y Mínimos de los Productos?",
                    "Mensaje del Sistema",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
                return;
        }

        try {
            Frame processFrame = AtuxUtility.openProcessWindow();
            AtuxSearch.recalculaMAXMIN();
            AtuxUtility.closeProcessWindow(processFrame);                   
            obtieneListaPedidoReposicion();
        }
        catch (SQLException sqlerr) {
            AtuxUtility.showMessageComponent(this,
                    "Error al recalcular Máximos / Mínimos - " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString(), null);
            sqlerr.printStackTrace();
        }        
    }
    
    public void ingresaCantPedida(ProductoLocal prodLocal, int rowSelected) {
        
        IAgregarCantPedida dlgInvIngCantPed = new IAgregarCantPedida(new Frame(), true);
        dlgInvIngCantPed.txtCodigo.setText(prodLocal.getCoProducto());
        dlgInvIngCantPed.txtDescripcion.setText(prodLocal.getDeProducto());
        dlgInvIngCantPed.txtUnidad.setText(prodLocal.getDeUnidadProducto());
        dlgInvIngCantPed.txtCantidad.setText(String.valueOf(prodLocal.getCaStockReponerCalcula()));
        dlgInvIngCantPed.txtCantidad.selectAll();        
        try {            
            dlgInvIngCantPed.cantidadMaxima = AtuxSearch.getCantidadMaximaReponer(prodLocal.getCoProducto());            
        } catch (SQLException sqlerr) {
            logger.error("Error al obtener la cantidad máxiam a reponer del producto – " + sqlerr.getErrorCode()+
                    "\n" + sqlerr.toString());            
        }
        dlgInvIngCantPed.setVisible(true);
        if (AtuxVariables.vAceptar) {
            if (!dlgInvIngCantPed.txtCantidad.getText().equalsIgnoreCase("")) {
                String strCant = dlgInvIngCantPed.txtCantidad.getText();
                tblDetalle.repaint();
                prodLocal.setCaStockReponer(Integer.parseInt(strCant));
                model.agregar(prodLocal);
                model.fireTableDataChanged();
                try {
                    AtuxSearch.updateCANTIDADPEDIDOREP(prodLocal.getCoProducto(), strCant);
                } catch (SQLException sqlerr) {
                    logger.error("Error al actualizar los datos del producto – " + sqlerr.getErrorCode() +
                            "\n" + sqlerr.toString());
                }
            }
        }
        AtuxGridUtils.showCell(tblDetalle, rowSelected, 0);
    }

    void obtieneListaPedidoReposicion() {
        controllerPedidoRep = new CSimpleModelo();
        model =  new ModeloTablaSimple(controllerPedidoRep.getRelacionProductosReposicion(), ModeloTablaSimple.REPOSICION);
        tblDetalle.setModel(model);
        Helper.ajustarSoloAnchoColumnas(tblDetalle, ModeloTablaSimple.anchoColumnasPedidoReposicion);
        AtuxUtility.setearPrimerRegistro(tblDetalle, null, 0);
        AtuxUtility.moveFocus(tblDetalle);
    }    
    
    private void guardarParametros() throws SQLException {
        Local local = new Local();      
        long vDiasRotacion = AtuxUtility.diferenciaEnDias(this.dcFechaFin.getCalendar(),this.dcFechaIni.getCalendar());
        local.setNuDiasRotacionPromedio(Integer.parseInt(String.valueOf(vDiasRotacion)));
        local.setNuMaxDiasReposicion(Integer.parseInt(txtParamMaxDias.getText()));
        local.setNuMinDiasReposicion(Integer.parseInt(txtParamMinDias.getText()));        
        local.setInIgnorarProdSinSaldo(btnIgnorarSaldo.isSelected()?"S":"N");
        local.setInSumarTiempoSuministro(btnSumarSuministro.isSelected()?"S":"N");
        local.setInSumarTransito(btnSumarTransito.isSelected()?"S":"N");
        local.setInSumarMinExhibicion(btnSumarMinExhibicion.isSelected()?"S":"N");
        local.setInSumarComprasPendientes(btnSumarComPendientes.isSelected()?"S":"N");
        local.setInTipoOperacion((String)jcbTipoOperacion.getSelectedItem());
        local.setInOrigenProductos((String)jcbOrigenProductos.getSelectedItem());
        local.setInSoloProdActivos(btnProductosActivos.isSelected()?"S":"N");
        local.setInProductosFraccionados(btnProductosFraccionados.isSelected()?"S":"N");
        local.setInFaltaCero(btnFaltaCero.isSelected()?"S":"N");
        AtuxSearch.updateParametrosPedidoRep(local);        
    }
    
    private void estadoBoton(javax.swing.JButton jButton, boolean estado){
        if(estado){
            jButton.setIcon(new ImageIcon(JVentas.class.getResource("/atux/resources/check_2.png")));                         
            jButton.setSelected(true);
        }
        else{
           jButton.setIcon(null); 
           jButton.setSelected(false); 
        }
    }
          
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularReposicion;
    private javax.swing.JButton btnFaltaCero;
    private javax.swing.JButton btnIgnorarSaldo;
    private javax.swing.JButton btnPedidoDefault;
    private javax.swing.JButton btnProductoFaltaCero;
    private javax.swing.JButton btnProductosActivos;
    private javax.swing.JButton btnProductosFraccionados;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSumarComPendientes;
    private javax.swing.JButton btnSumarMinExhibicion;
    private javax.swing.JButton btnSumarSuministro;
    private javax.swing.JButton btnSumarTransito;
    private javax.swing.JButton btnVerPedido;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaIni;
    private javax.swing.JScrollPane jScrollPaneDet;
    private javax.swing.JComboBox jcbOrigenProductos;
    private javax.swing.JComboBox jcbTipoOperacion;
    private javax.swing.JLabel lblCostoStockProm;
    private javax.swing.JLabel lblIgnorarProSinSaldo;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblMes01;
    private javax.swing.JLabel lblMes02;
    private javax.swing.JLabel lblMes03;
    private javax.swing.JLabel lblMes04;
    private javax.swing.JLabel lblMinExhibicion;
    private javax.swing.JLabel lblOpcion;
    private javax.swing.JLabel lblOpcionT;
    private javax.swing.JLabel lblOrigenProductos;
    private javax.swing.JLabel lblParametroMax;
    private javax.swing.JLabel lblParametroMin;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblPrecioStockProm;
    private javax.swing.JLabel lblProdActivos;
    private javax.swing.JLabel lblProdFaltaCero;
    private javax.swing.JLabel lblProdFraccionados;
    private javax.swing.JLabel lblStockPromedio;
    private javax.swing.JLabel lblStockRepFinal;
    private javax.swing.JLabel lblSumarCompras;
    private javax.swing.JLabel lblTiempoSuninistro;
    private javax.swing.JLabel lblTipoOperacion;
    private javax.swing.JLabel lblTotalStockNeg;
    private javax.swing.JLabel lblTotalStockPos;
    private javax.swing.JLabel lblTransito;
    private elaprendiz.gui.panel.PanelImage panelFondo;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlFoot;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JTable tblDetalle;
    private elaprendiz.gui.textField.TextField txtCostoStockCalculado;
    private elaprendiz.gui.textField.TextField txtLocal;
    private elaprendiz.gui.textField.TextField txtMes01;
    private elaprendiz.gui.textField.TextField txtMes02;
    private elaprendiz.gui.textField.TextField txtMes03;
    private elaprendiz.gui.textField.TextField txtMes04;
    private elaprendiz.gui.textField.TextField txtParamDiasRotacion;
    private elaprendiz.gui.textField.TextField txtParamMaxDias;
    private elaprendiz.gui.textField.TextField txtParamMinDias;
    private elaprendiz.gui.textField.TextField txtPrecioStockProm;
    private elaprendiz.gui.textField.TextField txtStockPromedio;
    private elaprendiz.gui.textField.TextField txtStockRepFinal;
    private elaprendiz.gui.textField.TextField txtTotalStockNeg;
    private elaprendiz.gui.textField.TextField txtTotalStockPos;
    // End of variables declaration//GEN-END:variables

    private void muestraDetalleProd(ProductoLocal prodLocal) {
        try {
                txtStockPromedio.setText(String.valueOf(AtuxSearch.obtieneROTACIONPRODUCTO(prodLocal.getNuDiasRotacionPromedio(), prodLocal.getCoProducto())));
                txtPrecioStockProm.setText(String.valueOf(Double.parseDouble(txtStockPromedio.getText())*prodLocal.getVaCostoProducto()));
                txtCostoStockCalculado.setText(String.valueOf(prodLocal.getCaStockReponerCalcula()*prodLocal.getVaCostoProducto()));
                txtStockRepFinal.setText(String.valueOf(prodLocal.getCaStockReponer()>0?prodLocal.getCaStockReponer():prodLocal.getCaStockReponerCalcula()));
                
                outParameters_mes = AtuxSearch.obtieneROTACIONPRODUCTOMES(prodLocal.getCoProducto());
                txtMes01.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(0)).trim()), "###,##0.00"));
                txtMes02.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(1)).trim()), "###,##0.00"));
                txtMes03.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(2)).trim()), "###,##0.00"));
                txtMes04.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(3)).trim()), "###,##0.00"));
            
        } catch (SQLException ex) {
            logger.error("Error al mostrar el detalle del producto muestraDetalleProd" + ex.getMessage());
        }
    }
}
