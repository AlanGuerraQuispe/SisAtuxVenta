package atux.vistas.inventario;

import atux.controllers.CSimpleModelo;
import atux.modelbd.ListaPedidosReposicion;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import atux.util.common.AtuxSearch;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */

public final class IListaPedidosReposicion extends javax.swing.JInternalFrame {                 
    private CSimpleModelo controllerPedidoRep;
    private ListaPedidosReposicion pedido;
    private ArrayList<ListaPedidosReposicion> lstPedidos;
    ModeloTablaSimple mtped,mtDetped;
    private final Log logger = LogFactory.getLog(getClass());
    
    public IListaPedidosReposicion() {
        initComponents();                    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageConsultarPedido = new elaprendiz.gui.panel.PanelImage();
        jPanelCab = new javax.swing.JPanel();
        bntBuscarPedido = new javax.swing.JButton();
        jLFecha = new javax.swing.JLabel();
        dcFechaIni = new com.toedter.calendar.JDateChooser();
        jLFecha1 = new javax.swing.JLabel();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
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

        setTitle("Consultar Pedidos Reposición");
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

        jLFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLFecha.setText("Fecha Inicio:");

        dcFechaIni.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaIni.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaIni.setDate(Calendar.getInstance().getTime());
        dcFechaIni.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaIni.setNextFocusableComponent(dcFechaFin);
        dcFechaIni.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaIni.setRequestFocusEnabled(false);

        jLFecha1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLFecha1.setText("Fecha Fin:");

        dcFechaFin.setBackground(new java.awt.Color(0, 0, 0));
        dcFechaFin.setForeground(new java.awt.Color(255, 0, 0));
        dcFechaFin.setDate(Calendar.getInstance().getTime());
        dcFechaFin.setFont(new java.awt.Font("Tahoma", 1, 13));
        dcFechaFin.setPreferredSize(new java.awt.Dimension(120, 22));
        dcFechaFin.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanelCabLayout = new javax.swing.GroupLayout(jPanelCab);
        jPanelCab.setLayout(jPanelCabLayout);
        jPanelCabLayout.setHorizontalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLFecha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntBuscarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addGap(303, 303, 303))
        );
        jPanelCabLayout.setVerticalGroup(
            jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntBuscarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(dcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFecha1)
                    .addComponent(jLFecha)
                    .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nro Pedido", "Tipo Pedido", "Fecha Pedido", "Items", "Min reposicion", "Max Reposicion", "Dias rotación"
            }
        ));
        tblPedidos.setRowHeight(24);
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        tblPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
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

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nro Pedido", "Item", "Producto", "Descripción", "Unidad", "Ca.Pedida", "Ca.Sugerida"
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
                        .addGap(13, 13, 13)
                        .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblItemsT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelPedidosLayout = new javax.swing.GroupLayout(jPanelPedidos);
        jPanelPedidos.setLayout(jPanelPedidosLayout);
        jPanelPedidosLayout.setHorizontalGroup(
            jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPedidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
                    .addGroup(jPanelPedidosLayout.createSequentialGroup()
                        .addComponent(lblPedidoT, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPanelPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(748, Short.MAX_VALUE)
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
                    .addComponent(jPanelCab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelImageConsultarPedidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlActionButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImageConsultarPedidoLayout.setVerticalGroup(
            panelImageConsultarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageConsultarPedidoLayout.createSequentialGroup()
                .addComponent(jPanelCab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelImageConsultarPedido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void bntBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarPedidoActionPerformed
        String feIni = AtuxUtility.getDateToString(dcFechaIni.getCalendar().getTime(),dcFechaIni.getDateFormatString());
        String feFin = AtuxUtility.getDateToString(dcFechaFin.getCalendar().getTime(),dcFechaFin.getDateFormatString());
                           
        cargarPedido(feIni,feFin);        
        
        if(tblPedidos.getSelectedRow() != -1){
              pedido =(ListaPedidosReposicion)mtped.getFila(tblPedidos.getSelectedRow());
              actualizaDetallePedido(pedido);
        }
    }//GEN-LAST:event_bntBuscarPedidoActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void tblPedidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
              pedido =(ListaPedidosReposicion)mtped.getFila(tblPedidos.getSelectedRow());
              actualizaDetallePedido(pedido);
         }
    }//GEN-LAST:event_tblPedidosKeyTyped

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        if (evt.getClickCount() == 1) {
           if(tblPedidos.getSelectedRow() != -1)
            {
                pedido =(ListaPedidosReposicion)mtped.getFila(tblPedidos.getSelectedRow());
                actualizaDetallePedido(pedido);                
            }
       }
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        String feIni = null;
        String feFin = null;
        try {
            feIni = AtuxSearch.getFechaHoraParametroBD(AtuxVariables.FORMATO_FECHA, 30*-1);
            feFin = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA);
            
        } catch (SQLException ex) {
            logger.error("Error al obtener el numero de dias de rotación en formInternalFrameActivated" + ex.getMessage());            
        }                
        
        this.dcFechaIni.setDate(AtuxUtility.getStringToDate(feIni,"dd/MM/yyyy"));    
        this.dcFechaFin.setDate(AtuxUtility.getStringToDate(feFin,"dd/MM/yyyy"));           
        
        AtuxUtility.moveFocus(dcFechaIni);                
        if(tblPedidos.getRowCount()>0){                
            try {                      
                ModeloTablaSimple modelo=(ModeloTablaSimple) tblPedidos.getModel();

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
                ModeloTablaSimple modelo=(ModeloTablaSimple) tblDetalle.getModel();

                for (int i = tblDetalle.getRowCount() -1; i >= 0; i--) {
                    modelo.quitarFila(i);
                }                                        
                modelo.fireTableDataChanged();
                } catch (Exception e) {
                    logger.info("Error al limpiar la tabla. " + e.getMessage());                        
                }
       }
             
    }//GEN-LAST:event_formInternalFrameActivated

    private void tblPedidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPedidosKeyReleased
         if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
              pedido =(ListaPedidosReposicion)mtped.getFila(tblPedidos.getSelectedRow());
              actualizaDetallePedido(pedido);              
         }  
    }//GEN-LAST:event_tblPedidosKeyReleased
    
    private void cargarPedido(String feIni,String feFin) {
        try {            
            controllerPedidoRep = new CSimpleModelo();
            mtped =  new ModeloTablaSimple(controllerPedidoRep.getRelacionPedidosReposicion(feIni,feFin), ModeloTablaSimple.LST_PED_REPOSICION);
            tblPedidos.setModel(mtped);
            
            Helper.ajustarSoloAnchoColumnas(tblPedidos, ModeloTablaSimple.anchoColumnasPedidosReposicion);
            AtuxUtility.setearPrimerRegistro(tblPedidos, null, 0);
            AtuxUtility.moveFocus(tblPedidos);
                        
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener Registro del Pedido Pendiente / Unir Pedido.  Verifique !!! - " + sqlException.toString(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }            
    
    void actualizaDetallePedido(ListaPedidosReposicion pedido) {        
         try {
            if (pedido !=null) {                
                tblDetalle.removeAll();

                this.mtDetped = new ModeloTablaSimple(controllerPedidoRep.getDetallePedidoReposicion(pedido), ModeloTablaSimple.LST_DETPED_REPOSICION);
                tblDetalle.setModel(mtDetped);

                Helper.ajustarSoloAnchoColumnas(tblDetalle, ModeloTablaSimple.anchoColumnasDetPedidosReposicion);
            }
        }catch (Exception ex) {
            tblDetalle.repaint();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problemas al cargar el detalle del pedido", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
      
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBuscarPedido;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaIni;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLFecha1;
    private javax.swing.JPanel jPanelCab;
    private javax.swing.JPanel jPanelDetalle;
    private javax.swing.JPanel jPanelPedidos;
    private javax.swing.JScrollPane jScrollPanelDetalle;
    private javax.swing.JScrollPane jScrollPanelPedidos;
    private javax.swing.JLabel lblCantPedidos;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblItemsT;
    private javax.swing.JLabel lblPedidoT;
    private javax.swing.JLabel lblPedidos;
    private elaprendiz.gui.panel.PanelImage panelImageConsultarPedido;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
