package atux.vistas.venta;

import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPRNUtility;
import atux.modelbd.PedidoVenta;
import atux.modelgui.ModeloTablaDetallePedidoVenta;
import atux.util.Helper;
import atux.util.ECampos;
import atux.vistas.ICajeroAnulacion;
import atux.vistas.venta.caja.IPagoPedido;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.awt.Component;

/**
 *
 * @author user
 */

public final class IAnularComprobante extends javax.swing.JInternalFrame {    
    
    private ModeloTablaDetallePedidoVenta mtdpv;
    CDetallePedidoVenta cdetalle;
    private PedidoVenta pedido;
    private String modulo = "ANULACION";
    CPedidoVenta cpedido;    
    
    private final Log logger = LogFactory.getLog(getClass());    
    
    public IAnularComprobante() {
        initComponents();                      
        txtNumeroPedido.requestFocus();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        jPanelCab = new javax.swing.JPanel();
        bntBuscarPedido = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        lblTotal = new javax.swing.JLabel();
        lblMonSoles = new javax.swing.JLabel();
        lblSoles = new javax.swing.JLabel();
        lblMonDolares = new javax.swing.JLabel();
        lblDolares = new javax.swing.JLabel();
        lblTipoCambioT = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        lblNumPedido = new javax.swing.JLabel();
        txtNumeroPedido = new elaprendiz.gui.textField.TextField();
        lblVendedor = new javax.swing.JLabel();
        txtVendedor = new elaprendiz.gui.textField.TextField();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new elaprendiz.gui.textField.TextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        lblSubtotal = new javax.swing.JLabel();
        txtSubTotal = new elaprendiz.gui.textField.TextField();
        lblDscto = new javax.swing.JLabel();
        txtDescuento = new elaprendiz.gui.textField.TextField();
        lblIGV = new javax.swing.JLabel();
        txtIGV = new elaprendiz.gui.textField.TextField();
        lblRedondeo = new javax.swing.JLabel();
        txtRedondeo = new elaprendiz.gui.textField.TextField();
        lblNuDoc = new javax.swing.JLabel();
        txtDocSunat = new elaprendiz.gui.textField.TextField();
        lblRuc = new javax.swing.JLabel();
        txtRUC = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new elaprendiz.gui.textField.TextField();
        jPanelDet = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPaneDet = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lblDetalle = new javax.swing.JLabel();
        lblItemsT = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        pnlActionButtons = new javax.swing.JPanel();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        bntAnular = new elaprendiz.gui.button.ButtonRect();
        bntVerComprobantes = new elaprendiz.gui.button.ButtonRect();

        setTitle("Anulación de pedidos");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        jPanelCab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCab.setOpaque(false);

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

        lblNumPedido.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumPedido.setText("No. Pedido ");

        txtNumeroPedido.setPreferredSize(new java.awt.Dimension(615, 22));
        txtNumeroPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroPedidoKeyTyped(evt);
            }
        });

        lblVendedor.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVendedor.setText("Vendedor");

        txtVendedor.setPreferredSize(new java.awt.Dimension(615, 22));

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCliente.setText("Cliente");

        txtCliente.setPreferredSize(new java.awt.Dimension(615, 22));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDireccion.setText("Dirección");

        txtDireccion.setPreferredSize(new java.awt.Dimension(615, 22));

        lblSubtotal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSubtotal.setText("SubTotal");

        txtSubTotal.setPreferredSize(new java.awt.Dimension(615, 22));

        lblDscto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDscto.setText("Descto");

        txtDescuento.setPreferredSize(new java.awt.Dimension(615, 22));

        lblIGV.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblIGV.setText("IGV");

        txtIGV.setPreferredSize(new java.awt.Dimension(615, 22));

        lblRedondeo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRedondeo.setText("Redondeo");

        txtRedondeo.setPreferredSize(new java.awt.Dimension(615, 22));

        lblNuDoc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNuDoc.setText("#Doc SUNAT");

        txtDocSunat.setPreferredSize(new java.awt.Dimension(615, 22));

        lblRuc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRuc.setText("RUC");

        txtRUC.setPreferredSize(new java.awt.Dimension(615, 22));

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado");

        txtEstado.setPreferredSize(new java.awt.Dimension(615, 22));

        javax.swing.GroupLayout jPanelCabLayout = new javax.swing.GroupLayout(jPanelCab);
        jPanelCab.setLayout(jPanelCabLayout);
        jPanelCabLayout.setHorizontalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(lblRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addComponent(lblFecha)
                        .addGap(35, 35, 35)
                        .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCabLayout.createSequentialGroup()
                            .addComponent(lblNumPedido)
                            .addGap(1, 1, 1)
                            .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstado)
                            .addGap(18, 18, 18)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCabLayout.createSequentialGroup()
                            .addComponent(lblVendedor)
                            .addGap(12, 12, 12)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22)
                            .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelCabLayout.createSequentialGroup()
                                    .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                            .addComponent(lblDscto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(2, 2, 2)
                                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                            .addComponent(lblIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                            .addComponent(lblTotal)
                                            .addGap(7, 7, 7)
                                            .addComponent(lblMonSoles)
                                            .addGap(15, 15, 15)
                                            .addComponent(lblSoles)
                                            .addGap(22, 22, 22)
                                            .addComponent(lblMonDolares)
                                            .addGap(12, 12, 12)
                                            .addComponent(lblDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblTipoCambioT)
                                            .addGap(11, 11, 11)
                                            .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                                .addGroup(jPanelCabLayout.createSequentialGroup()
                                    .addComponent(lblSubtotal)
                                    .addGap(8, 8, 8)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                    .addComponent(lblRedondeo)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtRedondeo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCabLayout.createSequentialGroup()
                            .addComponent(lblCliente)
                            .addGap(33, 33, 33)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(190, 190, 190)
                            .addComponent(lblNuDoc)
                            .addGap(10, 10, 10)
                            .addComponent(txtDocSunat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanelCabLayout.setVerticalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha)
                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumPedido)
                            .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblDolares)
                    .addComponent(lblMonDolares)
                    .addComponent(lblSoles)
                    .addComponent(lblTotal)
                    .addComponent(lblMonSoles)
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoCambioT)
                            .addComponent(lblTipoCambio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado))))
                .addGap(8, 8, 8)
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVendedor)
                                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRedondeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRedondeo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente)
                            .addComponent(lblNuDoc)
                            .addComponent(txtDocSunat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion)
                            .addComponent(lblRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCabLayout.createSequentialGroup()
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDscto)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIGV)
                            .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDet.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelDet.setOpaque(false);

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
        ));
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
                        .addGap(29, 29, 29)
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

        javax.swing.GroupLayout jPanelDetLayout = new javax.swing.GroupLayout(jPanelDet);
        jPanelDet.setLayout(jPanelDetLayout);
        jPanelDetLayout.setHorizontalGroup(
            jPanelDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelDetLayout.setVerticalGroup(
            jPanelDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlActionButtons.setOpaque(false);

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

        bntVerComprobantes.setBackground(new java.awt.Color(51, 153, 255));
        bntVerComprobantes.setText("Ver Comprobantes");
        bntVerComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVerComprobantesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionButtonsLayout = new javax.swing.GroupLayout(pnlActionButtons);
        pnlActionButtons.setLayout(pnlActionButtonsLayout);
        pnlActionButtonsLayout.setHorizontalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlActionButtonsLayout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(bntAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntVerComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );
        pnlActionButtonsLayout.setVerticalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bntVerComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelDet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(jPanelCab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void bntBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPedidoActionPerformed
        AtuxVariables.vNumeroPedidoDiario = AtuxPRNUtility.caracterIzquierda(txtNumeroPedido.getText(), 4, "0");
        txtNumeroPedido.setText(AtuxVariables.vNumeroPedidoDiario);        

        logger.info("Carga del pedido");

        try {

        int diferencia = AtuxSearch.getDiferencia(AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(), "dd/MM/yyyy"));

            if(diferencia>3){
                    JOptionPane.showMessageDialog(this, "No se pueden anular pedidos anteriores a 3 días ", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }
            else {
                cargarPedido();


                if (pedido != null) {
                    if (AtuxSearch.esPedidoDiaAnterior(AtuxUtility.getDateToString(pedido.getFePedido(), "dd/MM/yyyy"))) {
                        JOptionPane.showMessageDialog(this, "No se pueden cobrar Pedidos de días anteriores. Favor de anular este pedido y volver a generarlo", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                    }

                    AtuxVariables.vTipoCambioPedido = pedido.getVaTasaCambio();
                    lblTipoCambio.setText(AtuxUtility.formatNumber(AtuxVariables.vTipoCambioPedido));
                    calcularTotalDolar();
                    actualizaDetallePedido();
                }
                    AtuxUtility.moveFocus(txtNumeroPedido);
                }
            } catch (Exception ex) {
                tblDetalle.repaint();
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Problemas al determinar si el Pedido fue generado el día de hoy", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }
    }//GEN-LAST:event_bntBuscarPedidoActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void bntAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAnularActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Desea anular el pedido seleccionado?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
            ArrayList lsPedido = AtuxSearch.validaFraccionDiferentePedido(pedido.getNuPedido());

            if (lsPedido.size() > 0) {
                String mensaje = "La fracción de los siguientes productos no coindicen con la fracción actual\n";

                for (int i = 0; i < lsPedido.size(); i++) {
                    ArrayList temp = (ArrayList) lsPedido.get(i);

                    mensaje += ((String) temp.get(0)).trim() + " " + ((String) temp.get(1)).trim() + " (Fracción Venta: " + temp.get(2) + "; Fracción Actual: " + temp.get(3) + ")\n";
                }

                JOptionPane.showMessageDialog(this, mensaje, "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                AtuxUtility.moveFocus(txtNumeroPedido);

                return;}
            } catch (Exception ex) {
                logger.error("Diferencias de fracción " + IAnularComprobante.class.getName()+ ex.getMessage());
                return;
            }
            
            try {
                
                ICajeroAnulacion iCajeroAnulacion = new ICajeroAnulacion(new Frame(),true);
                iCajeroAnulacion.setVisible(true);
                
                if(AtuxVariables.vAceptar) {
                 AtuxSearch.grabarKardexSegunPedido(pedido.getNuPedido(),"",
                                          AtuxVariables.GRUPO_MOTIVO_KARDEX,
                                          AtuxVariables.MOTIVO_KARDEX_DEVOLUCION_VENTA,
                                          "A");

                 AtuxSearch.anularCantidadesCambio(pedido.getNuPedido());
                
                 String numeroPedidoNuevo = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO, 10);                

                 AtuxSearch.grabarPedidoNegativoDevolucion(pedido.getNuPedido(),
                                            numeroPedidoNuevo,
                                            AtuxVariables.GRUPO_MOTIVO_ANULACION_PEDIDO,
                                            AtuxVariables.MOTIVO_ANULACION_DEVOLUCION_VENTA,                        
                                            "N");

                 AtuxSearch.setNuSecNumeracionNoCommit(AtuxVariables.NUMERACION_PEDIDO);
                 
                 AtuxVariables.vNumeroPedido = numeroPedidoNuevo;
                 
                 AtuxSearch.setNumeroPedidoDiario();
                
                 AtuxSearch.updateNuPedidoDiario(false);

                 AtuxSearch.setNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO_DIARIO);
                
                 AtuxUtility.aceptarTransaccion();   
               } 
            } catch (SQLException ex) {
                logger.error("Error al anular el pedido" + IAnularComprobante.class.getName()+ ex.getMessage());                
            }
            generarNuevoCobro();
        }
    }//GEN-LAST:event_bntAnularActionPerformed

    private void bntVerComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVerComprobantesActionPerformed
       IComprobantesPedido iComPedido = new IComprobantesPedido(new Frame(),true);                
           iComPedido.setPedido(pedido);
           iComPedido.setVisible(true);              
            
    }//GEN-LAST:event_bntVerComprobantesActionPerformed

    private void txtNumeroPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPedidoKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {             evt.consume();             bntBuscarPedido.doClick();         }     }//GEN-LAST:event_txtNumeroPedidoKeyPressed

    private void txtNumeroPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroPedidoKeyTyped

        AtuxUtility.admitirDigitos(txtNumeroPedido, evt);     }//GEN-LAST:event_txtNumeroPedidoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAnular;
    private javax.swing.JButton bntBuscarPedido;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private elaprendiz.gui.button.ButtonRect bntVerComprobantes;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelCab;
    private javax.swing.JPanel jPanelDet;
    private javax.swing.JScrollPane jScrollPaneDet;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDolares;
    private javax.swing.JLabel lblDscto;
    private javax.swing.JLabel lblEstado;
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
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JTable tblDetalle;
    private elaprendiz.gui.textField.TextField txtCliente;
    private elaprendiz.gui.textField.TextField txtDescuento;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private elaprendiz.gui.textField.TextField txtDocSunat;
    private elaprendiz.gui.textField.TextField txtEstado;
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
            
            String feIni = AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(),dcFecha.getDateFormatString());
            String feFin = AtuxUtility.getDateToString(dcFecha.getCalendar().getTime(),dcFecha.getDateFormatString());
        
            ArrayList ArrayPedidos = cpedido.getPedidoVenta(AtuxVariables.vNumeroPedidoDiario,"",feIni,feFin,modulo);
            
                Iterator<PedidoVenta> iter = ArrayPedidos.iterator();
                while (iter.hasNext()){
                        pedido = (PedidoVenta) iter.next();
                }
            
            if (pedido==null){                              
                ECampos.setEditableTexto(jScrollPaneDet,true,new Component[]{null},true,"");
                JOptionPane.showMessageDialog(this, "El pedido nro : "+ txtNumeroPedido.getText() +" no se encuentra cobrado." , "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                generarNuevoCobro();                                                
            }
            else{    
                txtVendedor.setText(pedido.getUsuario().getNombreCompleto());
                txtCliente.setText(pedido.getNoImpresoComprobante());
                txtDireccion.setText(pedido.getDeDireccionComprobante());
                txtEstado.setText(pedido.getEsPedidoVenta());
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

                System.out.println("AtuxVariables.vTipoPedido: " + AtuxVariables.vTipoPedido);
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
        
        logger.debug("Total Dólares: " + total + " En Dolares: " + (total / tipoCambio));                
        
        if (total != 0) {
            lblDolares.setText(AtuxUtility.formatNumber(total / tipoCambio));
        } else {
            lblDolares.setText("0.00");
        }        
        
    }            
    
    private void mostrarPagoPedido() {
        logger.info("Muestra el pago del pedido");        
        IPagoPedido iPagoPedido = new IPagoPedido(new Frame(), "Pago de pedido", true);
            iPagoPedido.setPedido(pedido);
            iPagoPedido.setVisible(true);
        
        if (AtuxVariables.vAceptar){
           generarNuevoCobro();
        }
    }
    
    public void generarNuevoCobro() {
        AtuxUtility.moveFocus(txtNumeroPedido);        
        ECampos.setEditableTexto(jPanelCab,true,new Component[]{lblFecha,lblNumPedido,lblVendedor,lblCliente,
                                                                lblDireccion,lblSubtotal,lblDscto,lblIGV,lblRedondeo,
                                                                lblNuDoc,lblRuc,lblTotal,lblMonSoles,lblSoles,lblMonDolares,
                                                                lblDolares,lblTipoCambioT,lblTipoCambio,lblEstado},true,"");
        if(tblDetalle.getRowCount()>0){                
            try {                      
                ModeloTablaDetallePedidoVenta modelo=(ModeloTablaDetallePedidoVenta) tblDetalle.getModel();

                for (int i = tblDetalle.getRowCount() -1; i >= 0; i--) {
                    modelo.quitarFila(i);
                }                                        
                modelo.fireTableDataChanged();
                } catch (Exception e) {
                    logger.info("Error en generarNuevoCobro al limpiar la tabla. " + e.getMessage());                        
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
