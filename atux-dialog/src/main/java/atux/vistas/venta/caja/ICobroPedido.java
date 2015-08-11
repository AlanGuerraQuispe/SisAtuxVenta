package atux.vistas.venta.caja;

import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.handlers.DonacionService;
import atux.modelbd.DetallePedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.modelbd.PedidoVenta;
import atux.modelgui.ModeloTablaDetallePedidoVenta;
import atux.util.Helper;
import atux.util.ECampos;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.awt.Component;

/**
 *
 * @author user
 */

public final class ICobroPedido extends javax.swing.JInternalFrame {
    String numeroPedidoACobrar = new String();
    String fechaPedidoCobrar = new String();
    private final Log logger = LogFactory.getLog(getClass());
    private ModeloTablaDetallePedidoVenta mtdpv;
    CDetallePedidoVenta cdetalle;
    private PedidoVenta pedido;
    private String modulo = "COBRO";
    CPedidoVenta cpedido;
    
    public ICobroPedido() {
        super();
        initComponents();      
        AtuxSearch.existeCajaTurnoImpresora(new Frame());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage = new elaprendiz.gui.panel.PanelImage();
        pnlCabecera = new javax.swing.JPanel();
        lblNumPedido = new javax.swing.JLabel();
        bntBuscarPedido = new javax.swing.JButton();
        txtNumeroPedido = new elaprendiz.gui.textField.TextField();
        lblFecha = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        lblTotal = new javax.swing.JLabel();
        lblMonSoles = new javax.swing.JLabel();
        lblSoles = new javax.swing.JLabel();
        lblMonDolares = new javax.swing.JLabel();
        lblDolares = new javax.swing.JLabel();
        lblTipoCambioT = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        lblIGV = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtVendedor = new elaprendiz.gui.textField.TextField();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        txtCliente = new elaprendiz.gui.textField.TextField();
        lblVendedor = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        lblDscto = new javax.swing.JLabel();
        txtIGV = new elaprendiz.gui.textField.TextField();
        txtRUC = new elaprendiz.gui.textField.TextField();
        txtDescuento = new elaprendiz.gui.textField.TextField();
        lblSubtotal = new javax.swing.JLabel();
        txtSubTotal = new elaprendiz.gui.textField.TextField();
        lblRedondeo = new javax.swing.JLabel();
        txtRedondeo = new elaprendiz.gui.textField.TextField();
        lblNuDoc = new javax.swing.JLabel();
        txtDocSunat = new elaprendiz.gui.textField.TextField();
        pnlDetalle = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPaneDet = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lblDetalle = new javax.swing.JLabel();
        lblItemsT = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        pnlAcciones = new javax.swing.JPanel();
        bntCobrar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        bntAnular = new elaprendiz.gui.button.ButtonRect();

        setTitle("Cobro de pedidos");

        panelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlCabecera.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCabecera.setOpaque(false);

        lblNumPedido.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumPedido.setText("No. Pedido ");

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

        txtNumeroPedido.setPreferredSize(new java.awt.Dimension(615, 22));
        txtNumeroPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyTyped(evt);
            }
        });

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFecha.setText("Fecha:");

        dcFecha.setBackground(new java.awt.Color(0, 0, 0));
        dcFecha.setForeground(new java.awt.Color(255, 0, 0));
        dcFecha.setDate(Calendar.getInstance().getTime());
        dcFecha.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dcFecha.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFecha.setRequestFocusEnabled(false);

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTotal.setText("TOTAL:");

        lblMonSoles.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMonSoles.setText("S/");

        lblSoles.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblSoles.setForeground(new java.awt.Color(204, 0, 0));
        lblSoles.setText("0.00");

        lblMonDolares.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMonDolares.setText("$");

        lblDolares.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDolares.setForeground(new java.awt.Color(204, 0, 0));
        lblDolares.setText("0.00");

        lblTipoCambioT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambioT.setText("Tipo Cambio:");

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambio.setForeground(new java.awt.Color(204, 0, 0));
        lblTipoCambio.setText("0.00");

        lblIGV.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblIGV.setText("IGV");

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCliente.setText("Cliente");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDireccion.setText("Dirección");

        txtVendedor.setPreferredSize(new java.awt.Dimension(615, 22));

        txtDireccion.setPreferredSize(new java.awt.Dimension(615, 22));

        txtCliente.setPreferredSize(new java.awt.Dimension(615, 22));

        lblVendedor.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVendedor.setText("Vendedor");

        lblRuc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRuc.setText("RUC");

        lblDscto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDscto.setText("Descto");

        txtIGV.setPreferredSize(new java.awt.Dimension(615, 22));

        txtRUC.setPreferredSize(new java.awt.Dimension(615, 22));

        txtDescuento.setPreferredSize(new java.awt.Dimension(615, 22));

        lblSubtotal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSubtotal.setText("SubTotal");

        txtSubTotal.setPreferredSize(new java.awt.Dimension(615, 22));

        lblRedondeo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRedondeo.setText("Redondeo");

        txtRedondeo.setPreferredSize(new java.awt.Dimension(615, 22));

        lblNuDoc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNuDoc.setText("#Doc SUNAT");

        txtDocSunat.setPreferredSize(new java.awt.Dimension(615, 22));

        javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
        pnlCabecera.setLayout(pnlCabeceraLayout);
        pnlCabeceraLayout.setHorizontalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addComponent(lblNumPedido)
                        .addGap(1, 1, 1)
                        .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addComponent(lblCliente)
                        .addGap(33, 33, 33)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(lblNuDoc)
                        .addGap(10, 10, 10)
                        .addComponent(txtDocSunat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(lblRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addComponent(lblVendedor)
                                .addGap(12, 12, 12)
                                .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addComponent(lblFecha)
                                .addGap(35, 35, 35)
                                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addGap(7, 7, 7)
                                .addComponent(lblMonSoles)
                                .addGap(15, 15, 15)
                                .addComponent(lblSoles)
                                .addGap(22, 22, 22)
                                .addComponent(lblMonDolares)
                                .addGap(12, 12, 12)
                                .addComponent(lblDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(lblTipoCambioT)
                                .addGap(11, 11, 11)
                                .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                        .addComponent(lblDscto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                        .addComponent(lblIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                        .addComponent(lblSubtotal)
                                        .addGap(8, 8, 8)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lblRedondeo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtRedondeo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))))))
                .addGap(91, 91, 91))
        );
        pnlCabeceraLayout.setVerticalGroup(
            pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumPedido)
                            .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTotal)
                    .addComponent(lblMonSoles)
                    .addComponent(lblSoles)
                    .addComponent(lblMonDolares)
                    .addComponent(lblDolares)
                    .addComponent(lblTipoCambioT)
                    .addComponent(lblTipoCambio))
                .addGap(8, 8, 8)
                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVendedor)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRedondeo)
                                .addComponent(txtRedondeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente)
                            .addComponent(lblNuDoc)
                            .addComponent(txtDocSunat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion)
                            .addComponent(lblRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDscto)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIGV)
                            .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlDetalle.setOpaque(false);

        jPanel7.setOpaque(false);

        jScrollPaneDet.setPreferredSize(new java.awt.Dimension(452, 150));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto", "Unidad", "Precio", "%Dscto", "P.Venta", "Cantidad", "Total", "Laboratorio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDetalle.setEnabled(false);
        tblDetalle.setRowHeight(24);
        jScrollPaneDet.setViewportView(tblDetalle);

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDetalle.setText("Detalle del pedido :");

        lblItemsT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItemsT.setForeground(new java.awt.Color(255, 153, 0));
        lblItemsT.setText("items");

        lblItems.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItems.setForeground(new java.awt.Color(255, 153, 0));
        lblItems.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblItemsT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneDet, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetalle)
                    .addComponent(lblItemsT)
                    .addComponent(lblItems))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDet, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAcciones.setOpaque(false);

        bntCobrar.setBackground(new java.awt.Color(51, 153, 255));
        bntCobrar.setText("Cobrar");
        bntCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCobrarActionPerformed(evt);
            }
        });

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        bntAnular.setBackground(new java.awt.Color(51, 153, 255));
        bntAnular.setText("Anular");
        bntAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(bntCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bntCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageLayout.createSequentialGroup()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void requestFocus() {
        txtNumeroPedido.requestFocus();
    }

    private void bntBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPedidoActionPerformed
        AtuxVariables.vNumeroPedidoDiario = AtuxPRNUtility.caracterIzquierda(txtNumeroPedido.getText(), 4, "0");
        txtNumeroPedido.setText(AtuxVariables.vNumeroPedidoDiario);
                
        cargarPedido();
        
        try {
            if (pedido !=null) {
                if (AtuxSearch.esPedidoDiaAnterior(AtuxUtility.getDateToString(pedido.getFePedido(), "dd/MM/yyyy"))) {
                    JOptionPane.showMessageDialog(this, "No se pueden cobrar Pedidos de días anteriores. Favor de anular este pedido y volver a generarlo", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                    bntCobrar.setEnabled(false);
                }
                                                
                AtuxVariables.vTipoCambioPedido = pedido.getVaTasaCambio();
                lblTipoCambio.setText(AtuxUtility.formatNumber(AtuxVariables.vTipoCambioPedido));            
                calcularTotalDolar();
                actualizaDetallePedido();
                numeroPedidoACobrar = txtNumeroPedido.getText();
                fechaPedidoCobrar = AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(),dcFecha.getDateFormatString());
                AtuxVariables.vInComprobanteManual = pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_MANUAL)?"S":"N";
                DesActivarCasillas();
            }
            
            AtuxUtility.moveFocus(txtNumeroPedido);
            
        }catch (Exception ex) {            
            tblDetalle.repaint();
            logger.error("Error al mostrar datos cargados del pedido :" + ex.getMessage());            
            JOptionPane.showMessageDialog(this, "Problemas al determinar si el Pedido fue generado el día de hoy", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_bntBuscarPedidoActionPerformed
    
    private void DesActivarCasillas(){
        this.pnlCabecera.setEnabled(true);
        ECampos.setEditableTexto(this.pnlCabecera, false, new Component[]{lblFecha,dcFecha,lblTotal,lblMonSoles,lblSoles,lblMonDolares,lblDolares,lblTipoCambioT,lblTipoCambio,lblIGV,lblCliente,lblDireccion,lblVendedor,
                                                                         lblRuc,lblDscto,lblNumPedido,txtNumeroPedido,lblSubtotal,lblRedondeo,lblNuDoc},false,"");
    }
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
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void bntCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCobrarActionPerformed
        mostrarCobroDePedidoGenerado();
    }//GEN-LAST:event_bntCobrarActionPerformed

    private void bntAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAnularActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Desea anular el pedido seleccionado?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                AtuxSearch.anularPedido(pedido.getNuPedido(),AtuxVariables.MOTIVO_ANULACION_NO_COBRADO,false);
                AtuxSearch.grabarKardexSegunPedido(pedido.getNuPedido(),"", AtuxVariables.GRUPO_MOTIVO_KARDEX,
                                AtuxVariables.MOTIVO_KARDEX_ANULACION_PEDIDO, "A");


                AtuxSearch.anularCantidadesCambio(pedido.getNuPedido());
                        
                ArrayList<DetallePedidoVenta> dtpv = pedido.getDetallePedidoVenta();
                               
                for(DetallePedidoVenta item : dtpv)
                {
                    AtuxSearch.updateADDStockProductoDisp(item.getCoProducto(),
                                item.getCaAtendida(),
                                false);                            
                }
                DonacionService donacionService= Application.instance().getBean(DonacionService.class);
                donacionService.anularDonacion(pedido.getNuPedido());
                AtuxUtility.aceptarTransaccion();
                bntCobrar.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ICobroPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            generarNuevoCobro();
        }
    }//GEN-LAST:event_bntAnularActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAnular;
    private javax.swing.JButton bntBuscarPedido;
    private elaprendiz.gui.button.ButtonRect bntCobrar;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPaneDet;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDolares;
    private javax.swing.JLabel lblDscto;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIGV;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItemsT;
    private javax.swing.JLabel lblMonDolares;
    private javax.swing.JLabel lblMonSoles;
    private javax.swing.JLabel lblNuDoc;
    private javax.swing.JLabel lblNumPedido;
    private javax.swing.JLabel lblRedondeo;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblSoles;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblTipoCambioT;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVendedor;
    private elaprendiz.gui.panel.PanelImage panelImage;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JTable tblDetalle;
    private elaprendiz.gui.textField.TextField txtCliente;
    private elaprendiz.gui.textField.TextField txtDescuento;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private elaprendiz.gui.textField.TextField txtDocSunat;
    private elaprendiz.gui.textField.TextField txtIGV;
    private elaprendiz.gui.textField.TextField txtNumeroPedido;
    private elaprendiz.gui.textField.TextField txtRUC;
    private elaprendiz.gui.textField.TextField txtRedondeo;
    private elaprendiz.gui.textField.TextField txtSubTotal;
    private elaprendiz.gui.textField.TextField txtVendedor;
    // End of variables declaration//GEN-END:variables
        
    void cargarPedido() {
        try {
            
            cpedido = new CPedidoVenta();
            pedido = null;
            
            String feIni = AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(),"dd/MM/yyyy");
            String feFin = AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(),"dd/MM/yyyy");
        
            ArrayList ArrayPedidos = cpedido.getPedidoVenta(AtuxVariables.vNumeroPedidoDiario,"",feIni,feFin,modulo);
            
                Iterator<PedidoVenta> iter = ArrayPedidos.iterator();
                while (iter.hasNext()){
                        pedido = iter.next();
                }
            
            if (pedido==null){                              
                ECampos.setEditableTexto(jScrollPaneDet,true,new Component[]{null},true,"");
                JOptionPane.showMessageDialog(this, "El pedido nro : "+ txtNumeroPedido.getText() +" no se encuentra pendiente de cobro." , "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                generarNuevoCobro();                                                
            }
            else{    
                txtVendedor.setText(pedido.getUsuario().getNombreCompleto());
                txtCliente.setText(pedido.getNoImpresoComprobante());
                txtDireccion.setText(pedido.getDeDireccionComprobante());
                txtSubTotal.setText(String.valueOf(pedido.getVaTotalVenta()));
                txtDescuento.setText(String.valueOf(pedido.getVaTotalDescuento()));
                txtIGV.setText(String.valueOf(pedido.getVaTotalImpuesto()));
                txtRedondeo.setText(String.valueOf(pedido.getVaSaldoRedondeo()));
                txtDocSunat.setText("");
                txtRUC.setText(pedido.getNuRucCliente());
                lblSoles.setText(String.valueOf(pedido.getVaTotalPrecioVenta()));
                AtuxVariables.vNumeroPedido = pedido.getNuPedido();
                AtuxVariables.vTipoPedido   = pedido.getTiPedido();
                lblItems.setText(pedido.getCaItem().toString());
                logger.info("AtuxVariables.vTipoPedido:" + AtuxVariables.vTipoPedido);
                
            }
            
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente / Unir Pedido.  Verifique !!! - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }    
    
    void actualizaDetallePedido() {            
        cdetalle = new CDetallePedidoVenta();
        this.mtdpv = new ModeloTablaDetallePedidoVenta(pedido);
        tblDetalle.setModel(mtdpv); 
        pedido.setDetallePedidoVenta(mtdpv.getDetallesPedidoVenta());
        Helper.ajustarSoloAnchoColumnas(tblDetalle,ModeloTablaDetallePedidoVenta.anchoColumnas);
    }
    
    void calcularTotalDolar() {
        double total = 0.00;
        double tipoCambio = 0.00;

        tipoCambio = AtuxUtility.getDecimalNumber(lblTipoCambio.getText());

        total = AtuxUtility.getDecimalNumber(String.valueOf(pedido.getVaTotalPrecioVenta()));
        
        logger.info("Total Dólares: " + total + " En Dolares: " + (total / tipoCambio));                
        
        if (total != 0) {
            lblDolares.setText(AtuxUtility.formatNumber(total / tipoCambio));
        } else {
            lblDolares.setText("0.00");
        }        
        
    }    
    
    private void mostrarCobroDePedidoGenerado() {                                
        IPedidoGenerado dlgPedidoGenerado = new IPedidoGenerado(this, "Generación de Pedido", true);

        if (   pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA)
            || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)
            || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA_MANUAL)) {
               AtuxVariables.vTipoCliente = AtuxVariables.PERSONA_NATURAL; 
               dlgPedidoGenerado.setTiPersona(0);                           
               dlgPedidoGenerado.txtCliente.setText("");            
               dlgPedidoGenerado.txtRUC.setText("");
               dlgPedidoGenerado.txtDireccion.setText("");               
               dlgPedidoGenerado.rdbFactura.setSelected(false);
               dlgPedidoGenerado.rdbBoleta.setSelected(true);
            }
            else {
               AtuxVariables.vTipoCliente = AtuxVariables.PERSONA_JURIDICA; 
               dlgPedidoGenerado.setTiPersona(1);
               dlgPedidoGenerado.txtCliente.setText(pedido.getNoImpresoComprobante());            
               dlgPedidoGenerado.txtRUC.setText(pedido.getNuRucCliente());
               dlgPedidoGenerado.txtDireccion.setText(pedido.getDeDireccionComprobante());               
               dlgPedidoGenerado.rdbFactura.setSelected(true);
               dlgPedidoGenerado.rdbBoleta.setSelected(false);
            }
            logger.info("El cliente es uan persona " + AtuxVariables.vTipoCliente);
                        
        dlgPedidoGenerado.setVisible(true);

        if (AtuxVariables.vAceptar) {
            AtuxVariables.vANombreDe   = dlgPedidoGenerado.txtCliente.getText().trim();
            pedido.setNoImpresoCliente(AtuxVariables.vANombreDe);
            pedido.setNoImpresoComprobante(AtuxVariables.vANombreDe);
            AtuxVariables.vRUCFacturar = dlgPedidoGenerado.txtRUC.getText().trim();
            
            if(dlgPedidoGenerado.rdbFactura.isSelected()) {
              if(pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_NORMAL))  
                if(AtuxVariables.vInTicketFactura.equalsIgnoreCase("S")) {
                pedido.setTiComprobante(AtuxVariables.TIPO_TICKET_FACTURA);                              
                }
                else  
                  if(AtuxVariables.vInTicketFactura.equalsIgnoreCase("N")) {
                    pedido.setTiComprobante(AtuxVariables.TIPO_FACTURA);    
                }
              else
                if(pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_MANUAL)){
                    pedido.setTiComprobante(AtuxVariables.TIPO_FACTURA_MANUAL);    
                }
              }  
            else {
             if(pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_NORMAL))   
               if(AtuxVariables.vInTicketBoleta.equalsIgnoreCase("S")) {
                pedido.setTiComprobante(AtuxVariables.TIPO_TICKET_BOLETA);                              
               }
               else
                if(AtuxVariables.vInTicketBoleta.equalsIgnoreCase("N")) {
                pedido.setTiComprobante(AtuxVariables.TIPO_BOLETA);                              
                }
              else{
               if(pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_MANUAL)){
                    pedido.setTiComprobante(AtuxVariables.TIPO_BOLETA_MANUAL);    
                }      
              }
                if(dlgPedidoGenerado.getCliente()!=null)
                    pedido.setCoClienteLocal(dlgPedidoGenerado.getCliente().getCoClienteLocal());
            }
            
            AtuxVariables.vDireccion   = dlgPedidoGenerado.txtDireccion.getText().trim();
            pedido.setDeDireccionCliente(AtuxVariables.vDireccion);
            try {
                if(pedido.getTiComprobante().equals(AtuxVariables.TIPO_TICKET_FACTURA) ||
                   pedido.getTiComprobante().equals(AtuxVariables.TIPO_FACTURA)) {
                    AtuxSearch.updatePersonaJuridicaAPedido(pedido.getNuRucCliente(),
                            pedido.getNoImpresoComprobante(),
                            pedido.getTiComprobante(),
                            pedido.getDeDireccionCliente(),
                            pedido.getNuPedido());
                }
                else{
                    AtuxSearch.updatePersonaNaturalAPedido(pedido.getCoClienteLocal(),
                            pedido.getNoImpresoComprobante(),
                            pedido.getDeDireccionCliente(),
                            pedido.getNuPedido());
                }
            } catch (SQLException ex) {
                logger.error("Error en updatePersonaJuridicaAPedido "+ex);
            }
            mostrarPagoPedido();            
        } else if (dlgPedidoGenerado.pedidoAnulado)
            closeWindow(true);
    }
    
    private void mostrarPagoPedido() {
        logger.info("Muestra el pago del pedido");        
        IPagoPedido iPagoPedido = new IPagoPedido(AWWindowsManager.instance().getFrame(), "Pago de pedido", true);
            iPagoPedido.setPedido(pedido);
            iPagoPedido.setVisible(true);
        
        if (AtuxVariables.vAceptar){
           generarNuevoCobro();
        }
    }
    
    public void generarNuevoCobro() {
      
        AtuxUtility.moveFocus(txtNumeroPedido);                
        ECampos.setEditableTexto(pnlCabecera,true,new Component[]{lblFecha,lblNumPedido,lblVendedor,lblCliente,
                                                                lblDireccion,lblSubtotal,lblDscto,lblIGV,lblRedondeo,
                                                                lblNuDoc,lblRuc,lblTotal,lblMonSoles,lblSoles,lblMonDolares,
                                                                lblDolares,lblTipoCambioT,lblTipoCambio},true,"");      
        
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
        
       lblItems.setText("0");
       lblSoles.setText("0.00");
       lblDolares.setText("0.00");
       lblTipoCambio.setText("0.00");
    }
    
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }


}
