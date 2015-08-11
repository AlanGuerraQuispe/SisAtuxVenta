package atux.vistas.venta;

import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.modelbd.Cliente;
import atux.modelgui.ModeloTablaSimple;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.modelbd.PedidoVenta;
import atux.modelgui.ModeloTablaDetallePedidoVenta;
import atux.modelgui.ModeloTablaPedidoVenta;
import atux.util.Helper;
import atux.vistas.buscar.JBuscarCliente;
import atux.vistas.venta.caja.IPedidoACredito;
import com.components.JDialog;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */

public final class IConsultarPedidosACredito extends javax.swing.JInternalFrame {
    
    private final Log logger = LogFactory.getLog(getClass());

    private ModeloTablaPedidoVenta mtpedDet;
    private ModeloTablaSimple mtped;
    CDetallePedidoVenta cdetalle;
    private PedidoVenta pedido;
    CPedidoVenta cpedido;

    public IConsultarPedidosACredito() {
        initComponents();              
        txtCodCliente.requestFocus();
        cpedido = new CPedidoVenta();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageConsultarPedido = new elaprendiz.gui.panel.PanelImage();
        jPanelCab = new javax.swing.JPanel();
        bntBuscarPedido = new javax.swing.JButton();
        lblSaldoPendT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSaldoPendiente = new javax.swing.JLabel();
        lblTipoCambioT = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        txtNombreCliente = new elaprendiz.gui.textField.TextField();
        txtCodCliente = new elaprendiz.gui.textField.TextField();
        lblCliente1 = new javax.swing.JLabel();
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
        bntVerDetalle = new elaprendiz.gui.button.ButtonRect();

        setTitle("Consultar Ventas a Crédito");
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

        lblSaldoPendT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSaldoPendT.setText("Saldo pendiente:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("S/");

        lblSaldoPendiente.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblSaldoPendiente.setForeground(new java.awt.Color(204, 0, 0));
        lblSaldoPendiente.setText("0.00");

        lblTipoCambioT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambioT.setText("Tipo Cambio:");

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambio.setForeground(new java.awt.Color(204, 0, 0));
        lblTipoCambio.setText("0.00");

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setText("Nombres");

        txtNombreCliente.setNextFocusableComponent(bntBuscarPedido);
        txtNombreCliente.setPreferredSize(new java.awt.Dimension(615, 22));

        txtCodCliente.setPreferredSize(new java.awt.Dimension(615, 22));
        txtCodCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodClienteKeyPressed(evt);
            }
        });

        lblCliente1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCliente1.setText("Cliente");

        javax.swing.GroupLayout jPanelCabLayout = new javax.swing.GroupLayout(jPanelCab);
        jPanelCab.setLayout(jPanelCabLayout);
        jPanelCabLayout.setHorizontalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(lblCliente1)
                    .addGap(33, 33, 33)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addComponent(lblSaldoPendT)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addGap(5, 5, 5)
                    .addComponent(lblSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblTipoCambioT)
                    .addGap(11, 11, 11)
                    .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelCabLayout.setVerticalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCliente1)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblSaldoPendT))
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                        .addComponent(lblSaldoPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblTipoCambioT))
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblTipoCambio)))
                .addGap(6, 6, 6)
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelCabLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblNombres))
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelPedidos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPedidos.setOpaque(false);

        lblPedidoT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPedidoT.setText("Totales :");

        lblPedidos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblPedidos.setForeground(new java.awt.Color(255, 153, 0));
        lblPedidos.setText("Cantidad");

        lblCantPedidos.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCantPedidos.setForeground(new java.awt.Color(255, 153, 0));
        lblCantPedidos.setText("0");

        jScrollPanelPedidos.setPreferredSize(new java.awt.Dimension(452, 150));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "RUC", "Cliente", "Monto Total", "Monto Credito", "Saldo Pendiente"
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
        lblDetalle.setText("Lista de pedidos :");

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
        tblDetalle.setRowHeight(24);
        jScrollPanelDetalle.setViewportView(tblDetalle);

        javax.swing.GroupLayout jPanelDetalleLayout = new javax.swing.GroupLayout(jPanelDetalle);
        jPanelDetalle.setLayout(jPanelDetalleLayout);
        jPanelDetalleLayout.setHorizontalGroup(
            jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleLayout.createSequentialGroup()
                .addGroup(jPanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblItemsT, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelDetalleLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)))
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
                                .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE))
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
                .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
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

        bntVerDetalle.setBackground(new java.awt.Color(51, 153, 255));
        bntVerDetalle.setText("Detalle Credito");
        bntVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVerDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionButtonsLayout = new javax.swing.GroupLayout(pnlActionButtons);
        pnlActionButtons.setLayout(pnlActionButtonsLayout);
        pnlActionButtonsLayout.setHorizontalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionButtonsLayout.createSequentialGroup()
                .addContainerGap(601, Short.MAX_VALUE)
                .addComponent(bntVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        pnlActionButtonsLayout.setVerticalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImageConsultarPedidoLayout = new javax.swing.GroupLayout(panelImageConsultarPedido);
        panelImageConsultarPedido.setLayout(panelImageConsultarPedidoLayout);
        panelImageConsultarPedidoLayout.setHorizontalGroup(
            panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageConsultarPedidoLayout.createSequentialGroup()
                .addGroup(panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCab, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                    .addGroup(panelImageConsultarPedidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlActionButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImageConsultarPedidoLayout.setVerticalGroup(
            panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageConsultarPedidoLayout.createSequentialGroup()
                .addComponent(jPanelCab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(panelImageConsultarPedido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void bntBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPedidoActionPerformed
        if(!txtCodCliente.getText().isEmpty()){                                    
            cargarPedidoCredito(txtCodCliente.getText());
        }
        else{
            cargarPedidoCredito("");
        }
        
        if(tblPedidos.getSelectedRow() != -1){
              pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
              listaPedidosCredito(pedido);
        }
    }//GEN-LAST:event_bntBuscarPedidoActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void tblPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
              pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
              listaPedidosCredito(pedido);
         }
    }//GEN-LAST:event_tblPedidosKeyPressed

    private void tblPedidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
              pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
              listaPedidosCredito(pedido);
         }
    }//GEN-LAST:event_tblPedidosKeyTyped

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        if (evt.getClickCount() == 1) {
                pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
                listaPedidosCredito(pedido);
       }
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
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
        
     lblSaldoPendiente.setText("0.00");     
     lblTipoCambio.setText("0.00");
    }//GEN-LAST:event_formInternalFrameActivated

    private void tblPedidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyReleased
         if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
              pedido =(PedidoVenta)mtped.getFila(tblPedidos.getSelectedRow());
              listaPedidosCredito(pedido);
         }  
    }//GEN-LAST:event_tblPedidosKeyReleased

    private void txtCodClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         JBuscarCliente buscarCliente = new JBuscarCliente(1,new JDialog(),true);
         buscarCliente.setVisible(true);

         if(buscarCliente.getCliente() != null){
             Cliente cliente = ((Cliente)buscarCliente.getCliente());             
             txtCodCliente.setText(cliente.getCoClienteLocal());
             txtNombreCliente.setText(cliente.getDeRazonSocial());
         }
      }
    }//GEN-LAST:event_txtCodClienteKeyPressed

    private void bntVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVerDetalleActionPerformed
        IPedidoACredito iPedidoACredito = new IPedidoACredito(new JDialog(),true);
        PedidoVenta pedido =(PedidoVenta)mtpedDet.getFila(tblDetalle.getSelectedRow());
        iPedidoACredito.setPedido(pedido);
        iPedidoACredito.setInactivaCabecera();
        iPedidoACredito.setVisible(true);
        bntBuscarPedido.doClick();

    }//GEN-LAST:event_bntVerDetalleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBuscarPedido;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private elaprendiz.gui.button.ButtonRect bntVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelCab;
    private javax.swing.JPanel jPanelDetalle;
    private javax.swing.JPanel jPanelPedidos;
    private javax.swing.JScrollPane jScrollPanelDetalle;
    private javax.swing.JScrollPane jScrollPanelPedidos;
    private javax.swing.JLabel lblCantPedidos;
    private javax.swing.JLabel lblCliente1;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItemsT;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPedidoT;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JLabel lblSaldoPendT;
    private javax.swing.JLabel lblSaldoPendiente;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblTipoCambioT;
    private elaprendiz.gui.panel.PanelImage panelImageConsultarPedido;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblPedidos;
    private elaprendiz.gui.textField.TextField txtCodCliente;
    private elaprendiz.gui.textField.TextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
      
    private void cargarPedidoCredito(String codCliente) {
        try {
            ArrayList ArrayPedidos = cpedido.getTotalesPedCredito(codCliente);
            
            this.mtped = new ModeloTablaSimple(ArrayPedidos, ModeloTablaSimple.LST_TOTALES_PEDIDO_CREDITO);
            tblPedidos.setModel(mtped);
            Helper.ajustarSoloAnchoColumnas(tblPedidos, ModeloTablaSimple.anchoColumnasTotalesPedCredito);
            AtuxUtility.setearPrimerRegistro(tblPedidos, null, 0);
            AtuxUtility.moveFocus(tblPedidos);
                        
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente / Unir Pedido.  Verifique !!! - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }            
    
    void listaPedidosCredito(PedidoVenta pedido) {
        try {
            txtNombreCliente.setText(pedido.getNoImpresoComprobante());
            lblSaldoPendiente.setText(String.valueOf(AtuxUtility.formatNumber(pedido.getVaSaldoRedondeo())));

            ArrayList ArrayPedidos = cpedido.getPedidosCredito(pedido);

            this.mtpedDet = new ModeloTablaPedidoVenta(ArrayPedidos,ModeloTablaPedidoVenta.CONSULTA_PEDIDO_CREDITO);
            tblDetalle.setModel(mtpedDet);
            Helper.ajustarSoloAnchoColumnas(tblDetalle,ModeloTablaPedidoVenta.anchoColumnas);

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente / Unir Pedido.  Verifique !!! - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }
      
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }
 
}
