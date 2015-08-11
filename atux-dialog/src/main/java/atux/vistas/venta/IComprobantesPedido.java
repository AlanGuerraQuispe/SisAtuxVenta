package atux.vistas.venta;

import atux.controllers.CPedidoVenta;
import atux.modelbd.ComprobantePago;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelgui.ModeloTablaComprobantePago;
import atux.modelgui.ModeloTablaDetallePedidoVenta;
import atux.util.Helper;
import atux.util.common.AtuxUtility;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class IComprobantesPedido extends javax.swing.JDialog {
            
    PedidoVenta pedido;
    ComprobantePago comPago;
    CPedidoVenta cpedido;
    private ModeloTablaComprobantePago mtcp;
    private ModeloTablaDetallePedidoVenta mtdpv;
    
    public IComprobantesPedido(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageConsultarComprobantes = new elaprendiz.gui.panel.PanelImage();
        jPanelComprobantes = new javax.swing.JPanel();
        lblComprobanteT = new javax.swing.JLabel();
        lblComprobantes = new javax.swing.JLabel();
        lblCantComprobantes = new javax.swing.JLabel();
        jScrollPanelComprobantes = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        jPanelDetalleComprobante = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        lblItemsT = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        jScrollPanelDetalleComprobante = new javax.swing.JScrollPane();
        tblDetalleComprobante = new javax.swing.JTable();
        pnlActionButtons = new javax.swing.JPanel();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImageConsultarComprobantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        jPanelComprobantes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelComprobantes.setOpaque(false);

        lblComprobanteT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblComprobanteT.setText("Comprobantes :");

        lblComprobantes.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblComprobantes.setForeground(new java.awt.Color(255, 153, 0));
        lblComprobantes.setText("Cantidad");

        lblCantComprobantes.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCantComprobantes.setForeground(new java.awt.Color(255, 153, 0));
        lblCantComprobantes.setText("0");

        jScrollPanelComprobantes.setPreferredSize(new java.awt.Dimension(452, 150));

        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nro Ped", "Correlativo", "Fecha Ped.", "Vendedor", "Cliente", "%SubTotal", "Dscto", "IGV", "Redondeo", "Total"
            }
        ));
        tblComprobantes.setRowHeight(24);
        tblComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseClicked(evt);
            }
        });
        jScrollPanelComprobantes.setViewportView(tblComprobantes);

        jPanelDetalleComprobante.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelDetalleComprobante.setOpaque(false);

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblDetalle.setText("Detalle del comprobante :");

        lblItemsT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItemsT.setForeground(new java.awt.Color(255, 153, 0));
        lblItemsT.setText("items");

        lblItems.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblItems.setForeground(new java.awt.Color(255, 153, 0));
        lblItems.setText("0");

        jScrollPanelDetalleComprobante.setPreferredSize(new java.awt.Dimension(452, 150));

        tblDetalleComprobante.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleComprobante.setEnabled(false);
        tblDetalleComprobante.setRowHeight(24);
        jScrollPanelDetalleComprobante.setViewportView(tblDetalleComprobante);

        javax.swing.GroupLayout jPanelDetalleComprobanteLayout = new javax.swing.GroupLayout(jPanelDetalleComprobante);
        jPanelDetalleComprobante.setLayout(jPanelDetalleComprobanteLayout);
        jPanelDetalleComprobanteLayout.setHorizontalGroup(
            jPanelDetalleComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleComprobanteLayout.createSequentialGroup()
                .addGroup(jPanelDetalleComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalleComprobanteLayout.createSequentialGroup()
                        .addComponent(lblDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemsT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDetalleComprobanteLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPanelDetalleComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDetalleComprobanteLayout.setVerticalGroup(
            jPanelDetalleComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalleComprobanteLayout.createSequentialGroup()
                .addGroup(jPanelDetalleComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDetalle)
                    .addComponent(lblItemsT)
                    .addComponent(lblItems))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelDetalleComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelComprobantesLayout = new javax.swing.GroupLayout(jPanelComprobantes);
        jPanelComprobantes.setLayout(jPanelComprobantesLayout);
        jPanelComprobantesLayout.setHorizontalGroup(
            jPanelComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprobantesLayout.createSequentialGroup()
                .addGroup(jPanelComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelComprobantesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPanelComprobantes, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
                    .addGroup(jPanelComprobantesLayout.createSequentialGroup()
                        .addComponent(lblComprobanteT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelComprobantesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelDetalleComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelComprobantesLayout.setVerticalGroup(
            jPanelComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComprobantesLayout.createSequentialGroup()
                .addGroup(jPanelComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComprobanteT)
                    .addComponent(lblComprobantes)
                    .addComponent(lblCantComprobantes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDetalleComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout pnlActionButtonsLayout = new javax.swing.GroupLayout(pnlActionButtons);
        pnlActionButtons.setLayout(pnlActionButtonsLayout);
        pnlActionButtonsLayout.setHorizontalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlActionButtonsLayout.createSequentialGroup()
                .addContainerGap(748, Short.MAX_VALUE)
                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        pnlActionButtonsLayout.setVerticalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelImageConsultarComprobantesLayout = new javax.swing.GroupLayout(panelImageConsultarComprobantes);
        panelImageConsultarComprobantes.setLayout(panelImageConsultarComprobantesLayout);
        panelImageConsultarComprobantesLayout.setHorizontalGroup(
            panelImageConsultarComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageConsultarComprobantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImageConsultarComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelComprobantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlActionButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelImageConsultarComprobantesLayout.setVerticalGroup(
            panelImageConsultarComprobantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageConsultarComprobantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelImageConsultarComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelImageConsultarComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();     }//GEN-LAST:event_bntSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cpedido = new CPedidoVenta();
        ArrayList ArrayPedidos = cpedido.getComprobatesPedido(pedido);                        
            
        this.mtcp = new ModeloTablaComprobantePago(ArrayPedidos);
        tblComprobantes.setModel(mtcp);
        Helper.ajustarSoloAnchoColumnas(tblComprobantes,ModeloTablaComprobantePago.anchoColumnas);   
        AtuxUtility.setearPrimerRegistro(tblComprobantes, null, 0);
        AtuxUtility.moveFocus(tblComprobantes);

        if(tblComprobantes.getSelectedRow() != -1){
              comPago =(ComprobantePago)mtcp.getFila(tblComprobantes.getSelectedRow());
              cargarDetalle(comPago);                
        }
    }//GEN-LAST:event_formWindowOpened

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
        if (evt.getClickCount() == 1) {
           if(tblComprobantes.getSelectedRow() != -1)
            {
                comPago =(ComprobantePago)mtcp.getFila(tblComprobantes.getSelectedRow());
                cargarDetalle(comPago);               
            }
       }
    }//GEN-LAST:event_tblComprobantesMouseClicked
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.JPanel jPanelComprobantes;
    private javax.swing.JPanel jPanelDetalleComprobante;
    private javax.swing.JScrollPane jScrollPanelComprobantes;
    private javax.swing.JScrollPane jScrollPanelDetalleComprobante;
    private javax.swing.JLabel lblCantComprobantes;
    private javax.swing.JLabel lblComprobanteT;
    private javax.swing.JLabel lblComprobantes;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItemsT;
    private elaprendiz.gui.panel.PanelImage panelImageConsultarComprobantes;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblDetalleComprobante;
    // End of variables declaration//GEN-END:variables

    void setPedido(PedidoVenta pedido) {
       this.pedido = pedido;
    }

    private void cargarDetalle(ComprobantePago comPago) {
        ArrayList<DetallePedidoVenta> dtpv = comPago.getDetallePedido();                        
        this.mtdpv = new ModeloTablaDetallePedidoVenta(comPago.getDetallePedido());
        tblDetalleComprobante.setModel(mtdpv); 

        Helper.ajustarSoloAnchoColumnas(tblDetalleComprobante,ModeloTablaDetallePedidoVenta.anchoColumnas);                
    }
}
