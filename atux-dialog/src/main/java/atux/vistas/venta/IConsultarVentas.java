package atux.vistas.venta;

import atux.controllers.CPedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.modelbd.PedidoVenta;
import atux.modelgui.ModeloTablaDetallePedidoVenta;
import atux.modelgui.ModeloTablaPedidoVenta;
import atux.util.Helper;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */

public final class IConsultarVentas extends javax.swing.JInternalFrame {
    private final Log logger = LogFactory.getLog(getClass());
    private ModeloTablaDetallePedidoVenta mtdpv;
    private ModeloTablaPedidoVenta mtped;
    private PedidoVenta pedido;
    private String modulo = "CONSULTA";

    public IConsultarVentas() {
        initComponents();
        txtNumeroPedido.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageConsultarPedido = new elaprendiz.gui.panel.PanelImage();
        jPanelCab = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        bntBuscarPedido = new javax.swing.JButton();
        txtNumeroPedido = new elaprendiz.gui.textField.TextField();
        jLFecha = new javax.swing.JLabel();
        dcFechaIni = new com.toedter.calendar.JDateChooser();
        jLbTotalT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSoles = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDolares = new javax.swing.JLabel();
        lblTipoCambioT = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        jLFecha1 = new javax.swing.JLabel();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroCorrelativo = new elaprendiz.gui.textField.TextField();
        lblDonacionT = new javax.swing.JLabel();
        lblDonacion = new javax.swing.JLabel();
        lblTotalSumadoDonacion = new javax.swing.JLabel();
        jPanelPedidos = new javax.swing.JPanel();
        lblPedidoT = new javax.swing.JLabel();
        lblPedidos = new javax.swing.JLabel();
        lblCantPedidos = new javax.swing.JLabel();
        jScrollPanelPedidos = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jPanelDetalle = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        lblItemsT = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        jScrollPanelDetalle = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlActionButtons = new javax.swing.JPanel();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setTitle("Consultar Ventas");
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

        panelImageConsultarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        jPanelCab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCab.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel6.setText("No. Pedido ");

        bntBuscarPedido.setBackground(new java.awt.Color(204, 204, 255));
        bntBuscarPedido.setForeground(new java.awt.Color(0, 0, 51));
        bntBuscarPedido.setMnemonic('B');
        bntBuscarPedido.setText("Buscar");
        bntBuscarPedido.setToolTipText("Buscar Pedido");
        bntBuscarPedido.setPreferredSize(new java.awt.Dimension(28, 20));
        bntBuscarPedido.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/search32select.png"))); // NOI18N
        bntBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarPedidoActionPerformed(evt);
            }
        });

        txtNumeroPedido.setNextFocusableComponent(txtNumeroCorrelativo);
        txtNumeroPedido.setPreferredSize(new java.awt.Dimension(615, 22));
        txtNumeroPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyTyped(evt);
            }
        });

        jLFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLFecha.setText("Fecha Inicio:");

        dcFechaIni.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaIni.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaIni.setDate(Calendar.getInstance().getTime());
        dcFechaIni.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaIni.setNextFocusableComponent(dcFechaFin);
        dcFechaIni.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaIni.setRequestFocusEnabled(false);

        jLbTotalT.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLbTotalT.setText("TOTAL:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("S/");

        lblSoles.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblSoles.setForeground(new java.awt.Color(204, 0, 0));
        lblSoles.setText("0.00");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("$");

        lblDolares.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDolares.setForeground(new java.awt.Color(204, 0, 0));
        lblDolares.setText("0.00");

        lblTipoCambioT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambioT.setText("Tipo Cambio:");

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambio.setForeground(new java.awt.Color(204, 0, 0));
        lblTipoCambio.setText("0.00");

        jLFecha1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLFecha1.setText("Fecha Fin:");

        dcFechaFin.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaFin.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaFin.setDate(Calendar.getInstance().getTime());
        dcFechaFin.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaFin.setNextFocusableComponent(txtNumeroPedido);
        dcFechaFin.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaFin.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setText("Correlativo");

        txtNumeroCorrelativo.setNextFocusableComponent(bntBuscarPedido);
        txtNumeroCorrelativo.setPreferredSize(new java.awt.Dimension(615, 22));
        txtNumeroCorrelativo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroCorrelativoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroCorrelativoKeyTyped(evt);
            }
        });

        lblDonacionT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDonacionT.setText("Donación:");

        lblDonacion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDonacion.setForeground(new java.awt.Color(204, 0, 0));
        lblDonacion.setText("0.00");

        lblTotalSumadoDonacion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotalSumadoDonacion.setForeground(new java.awt.Color(204, 0, 0));
        lblTotalSumadoDonacion.setText("0.00");

        javax.swing.GroupLayout jPanelCabLayout = new javax.swing.GroupLayout(jPanelCab);
        jPanelCab.setLayout(jPanelCabLayout);
        jPanelCabLayout.setHorizontalGroup(
                jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(jLFecha)
                                                .addGap(4, 4, 4)
                                                .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLFecha1)
                                                .addGap(4, 4, 4)
                                                .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)
                                                .addComponent(jLbTotalT)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1)
                                                .addGap(6, 6, 6)
                                                .addComponent(lblSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(lblTipoCambioT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(548, 548, 548)
                                                .addComponent(lblDonacionT)
                                                .addGap(21, 21, 21)
                                                .addComponent(lblDonacion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(jLabel6)
                                                .addGap(1, 1, 1)
                                                .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(txtNumeroCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(96, 96, 96)
                                                .addComponent(lblTotalSumadoDonacion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel2)
                                                .addGap(8, 8, 8)
                                                .addComponent(lblDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15))
        );
        jPanelCabLayout.setVerticalGroup(
                jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLFecha))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLFecha1))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLbTotalT))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(lblSoles))
                                        .addComponent(lblTipoCambioT)
                                        .addComponent(lblTipoCambio))
                                .addGap(1, 1, 1)
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDonacionT)
                                        .addComponent(lblDonacion))
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumeroCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel2)
                                                                .addComponent(lblDolares))
                                                        .addComponent(lblTotalSumadoDonacion)))))
        );

        jPanelPedidos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPedidos.setOpaque(false);

        lblPedidoT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPedidoT.setText("Pedidos :");

        lblPedidos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPedidos.setForeground(new java.awt.Color(255, 153, 0));
        lblPedidos.setText("Cantidad");

        lblCantPedidos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCantPedidos.setForeground(new java.awt.Color(255, 153, 0));
        lblCantPedidos.setText("0");

        jScrollPanelPedidos.setPreferredSize(new java.awt.Dimension(452, 150));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                        "Nro Ped", "Correlativo", "Estado", "Fecha Ped.", "Vendedor", "Cliente", "%SubTotal", "Dscto", "IGV", "Redondeo", "Total"
                }
        ));
        tblPedidos.setRowHeight(24);
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        tblPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblPedidosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblPedidosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblPedidosKeyTyped(evt);
            }
        });
        jScrollPanelPedidos.setViewportView(tblPedidos);

        jPanelDetalle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelDetalle.setOpaque(false);

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDetalle.setText("Detalle del pedido :");

        lblItemsT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItemsT.setForeground(new java.awt.Color(255, 153, 0));
        lblItemsT.setText("items");

        lblItems.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItems.setForeground(new java.awt.Color(255, 153, 0));
        lblItems.setText("0");

        jScrollPanelDetalle.setPreferredSize(new java.awt.Dimension(452, 150));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                        "Código", "Producto", "Unidad", "Precio", "%Dscto", "P.Venta", "Cantidad", "Total", "Laboratorio"
                }
        ));
        tblDetalle.setEnabled(false);
        tblDetalle.setRowHeight(24);
        jScrollPanelDetalle.setViewportView(tblDetalle);

        javax.swing.GroupLayout jPanelDetalleLayout = new javax.swing.GroupLayout(jPanelDetalle);
        jPanelDetalle.setLayout(jPanelDetalleLayout);
        jPanelDetalleLayout.setHorizontalGroup(
                jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                .addGroup(jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblItemsT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelDetalleLayout.setVerticalGroup(
                jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                .addGroup(jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDetalle)
                                        .addComponent(lblItemsT)
                                        .addComponent(lblItems))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelPedidosLayout = new javax.swing.GroupLayout(jPanelPedidos);
        jPanelPedidos.setLayout(jPanelPedidosLayout);
        jPanelPedidosLayout.setHorizontalGroup(
                jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPedidosLayout.createSequentialGroup()
                                .addGroup(jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPedidosLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPedidosLayout.createSequentialGroup()
                                                .addComponent(lblPedidoT, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblCantPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPedidosLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelPedidosLayout.setVerticalGroup(
                jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPedidosLayout.createSequentialGroup()
                                .addGroup(jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPedidoT)
                                        .addComponent(lblPedidos)
                                        .addComponent(lblCantPedidos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pnlActionButtons.setOpaque(false);

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionButtonsLayout = new javax.swing.GroupLayout(pnlActionButtons);
        pnlActionButtons.setLayout(pnlActionButtonsLayout);
        pnlActionButtonsLayout.setHorizontalGroup(
                pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlActionButtonsLayout.createSequentialGroup()
                                .addContainerGap(760, Short.MAX_VALUE)
                                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );
        pnlActionButtonsLayout.setVerticalGroup(
                pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelImageConsultarPedidoLayout = new javax.swing.GroupLayout(panelImageConsultarPedido);
        panelImageConsultarPedido.setLayout(panelImageConsultarPedidoLayout);
        panelImageConsultarPedidoLayout.setHorizontalGroup(
                panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageConsultarPedidoLayout.createSequentialGroup()
                                .addGroup(panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanelPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanelCab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelImageConsultarPedidoLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(pnlActionButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelImageConsultarPedidoLayout.setVerticalGroup(
                panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelImageConsultarPedidoLayout.createSequentialGroup()
                                .addComponent(jPanelCab, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelImageConsultarPedido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPedidoActionPerformed

        String feIni = AtuxUtility.getDateToString(dcFechaIni.getCalendar().getTime(),dcFechaIni.getDateFormatString());
        String feFin = AtuxUtility.getDateToString(dcFechaFin.getCalendar().getTime(),dcFechaFin.getDateFormatString());

        if(!txtNumeroPedido.getText().isEmpty() || !txtNumeroCorrelativo.getText().isEmpty()){
            if(!txtNumeroPedido.getText().isEmpty()){
                AtuxVariables.vNumeroPedidoDiario = AtuxPRNUtility.caracterIzquierda(txtNumeroPedido.getText(), 4, "0");
                txtNumeroPedido.setText(AtuxVariables.vNumeroPedidoDiario);
                cargarPedido(AtuxVariables.vNumeroPedidoDiario,"",feIni,feFin);
            }
            else if(!txtNumeroCorrelativo.getText().isEmpty()){
                String numCorrelativo = txtNumeroPedido.getText().trim();
                cargarPedido("",numCorrelativo,feIni,feFin);
            }
        }
        else{
            cargarPedido("","",feIni,feFin);
        }

        if(tblPedidos.getSelectedRow() != -1){
            pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
            actualizaDetallePedido(pedido);
        }
    }//GEN-LAST:event_bntBuscarPedidoActionPerformed

    private void txtNumeroPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPedidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            bntBuscarPedido.doClick();
        }
    }//GEN-LAST:event_txtNumeroPedidoKeyPressed

    private void txtNumeroPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPedidoKeyTyped
        AtuxUtility.admitirDigitos(txtNumeroPedido, evt);
    }//GEN-LAST:event_txtNumeroPedidoKeyTyped

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void tblPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
//              pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
//              actualizaDetallePedido(pedido);              
//         }        
    }//GEN-LAST:event_tblPedidosKeyPressed

    private void tblPedidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
            actualizaDetallePedido(pedido);
        }
    }//GEN-LAST:event_tblPedidosKeyTyped

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        if (evt.getClickCount() == 1) {
            if(tblPedidos.getSelectedRow() != -1)
            {
                pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
                actualizaDetallePedido(pedido);
            }
        }
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void txtNumeroCorrelativoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCorrelativoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCorrelativoKeyPressed

    private void txtNumeroCorrelativoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroCorrelativoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCorrelativoKeyTyped

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        AtuxUtility.moveFocus(dcFechaIni);
        if(tblPedidos.getRowCount()>0){
            try {
                ModeloTablaPedidoVenta modelo=(ModeloTablaPedidoVenta) tblPedidos.getModel();

                for (int i = tblPedidos.getRowCount() -1; i >= 0; i--) {
                    modelo.quitarFila(i);
                }
                modelo.fireTableDataChanged();
            } catch (Exception e) {
                logger.info("Error al limpiar la tabla. " + e.getMessage());
            }
        }

        if(tblDetalle.getRowCount()>0){
            try {
                ModeloTablaDetallePedidoVenta modelo=(ModeloTablaDetallePedidoVenta) tblDetalle.getModel();

                for (int i = tblDetalle.getRowCount() -1; i >= 0; i--) {
                    modelo.quitarFila(i);
                }
                modelo.fireTableDataChanged();
            } catch (Exception e) {
                logger.info("Error al limpiar la tabla. " + e.getMessage());
            }
        }

        lblSoles.setText("0.00");
        lblDolares.setText("0.00");
        lblTipoCambio.setText("0.00");
        lblDonacion.setText("0.00");
        lblTotalSumadoDonacion.setText("0.00");
        txtNumeroPedido.setText("");
        txtNumeroCorrelativo.setText("");
    }//GEN-LAST:event_formInternalFrameActivated

    private void tblPedidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
            actualizaDetallePedido(pedido);
        }
    }//GEN-LAST:event_tblPedidosKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBuscarPedido;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaIni;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLFecha1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLbTotalT;
    private javax.swing.JPanel jPanelCab;
    private javax.swing.JPanel jPanelDetalle;
    private javax.swing.JPanel jPanelPedidos;
    private javax.swing.JScrollPane jScrollPanelDetalle;
    private javax.swing.JScrollPane jScrollPanelPedidos;
    private javax.swing.JLabel lblCantPedidos;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblDolares;
    private javax.swing.JLabel lblDonacion;
    private javax.swing.JLabel lblDonacionT;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItemsT;
    private javax.swing.JLabel lblPedidoT;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JLabel lblSoles;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblTipoCambioT;
    private javax.swing.JLabel lblTotalSumadoDonacion;
    private elaprendiz.gui.panel.PanelImage panelImageConsultarPedido;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblPedidos;
    private elaprendiz.gui.textField.TextField txtNumeroCorrelativo;
    private elaprendiz.gui.textField.TextField txtNumeroPedido;
    // End of variables declaration//GEN-END:variables

    private void cargarPedido(String numPedidoDiario,String numCorrelativo,String feIni,String feFin) {
        try {
            CPedidoVenta cpedido = new CPedidoVenta();
            ArrayList ArrayPedidos = cpedido.getPedidoVenta(numPedidoDiario,numCorrelativo,feIni,feFin,modulo);
            this.mtped = new ModeloTablaPedidoVenta(ArrayPedidos,ModeloTablaPedidoVenta.CONSULTA_PEDIDO_NORMAL);
            tblPedidos.setModel(mtped);
            Helper.ajustarSoloAnchoColumnasTablaConsulta(tblPedidos,ModeloTablaPedidoVenta.anchoColumnas);
            AtuxUtility.setearPrimerRegistro(tblPedidos, null, 0);
            AtuxUtility.moveFocus(tblPedidos);

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente / Unir Pedido.  Verifique !!! - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    void actualizaDetallePedido(PedidoVenta pedido) {
        try {
            if (pedido !=null) {
                tblDetalle.removeAll();

                this.mtdpv = new ModeloTablaDetallePedidoVenta(pedido.getDetallePedidoVenta());
                tblDetalle.setModel(mtdpv);

                Helper.ajustarSoloAnchoColumnas(tblDetalle,ModeloTablaDetallePedidoVenta.anchoColumnas);

                AtuxVariables.vTipoCambioPedido = pedido.getVaTasaCambio();
                lblSoles.setText(String.valueOf(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta() - pedido.getVaSaldoRedondeo())));
                lblDonacion.setText(AtuxSearch.getValorDonacion(pedido));
                lblTotalSumadoDonacion.setText(String.valueOf(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta() - pedido.getVaSaldoRedondeo() + Double.parseDouble(lblDonacion.getText()))));
                lblTipoCambio.setText(AtuxUtility.formatNumber(AtuxVariables.vTipoCambioPedido));
                calcularTotalDolar();
            }
        }catch (Exception ex) {
            tblDetalle.repaint();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problemas al determinar si el Pedido fue generado el día de hoy", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    void calcularTotalDolar() {
        double total = 0.00;
        double tipoCambio = 0.00;

        tipoCambio = AtuxUtility.getDecimalNumber(lblTipoCambio.getText());

        total = pedido.getVaTotalPrecioVenta() - pedido.getVaSaldoRedondeo() + Double.parseDouble(lblDonacion.getText());

        if (total != 0) {
            lblDolares.setText(AtuxUtility.formatNumber(total / tipoCambio));
        } else {
            lblDolares.setText("0.00");
        }

    }

    public void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        this.setVisible(false);
        this.dispose();
    }

}
