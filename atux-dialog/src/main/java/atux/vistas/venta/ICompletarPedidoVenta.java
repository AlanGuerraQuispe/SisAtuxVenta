package atux.vistas.venta;

import atux.controllers.CProductoLocal;
import atux.vistas.venta.caja.IPedidoGenerado;
import atux.vistas.venta.caja.IPagoPedido;
import atux.config.AppConfig;
import atux.controllers.CDetallePedidoVenta;
import atux.controllers.CPedidoVenta;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.Proveedores;
import atux.modelgui.ModeloTablaProveedores;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.util.AccionTableCellRenderer;
import atux.util.CeldaAccionEditor;
import atux.util.CellEditorSpinnerPedidoVenta;
import atux.util.ECampos;
import atux.util.TableCellFormatter;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.builder.TestigoBuilder;
import atux.util.print.builder.TestigoGenerator;
import atux.util.print.builder.TestigoMatricialPrinter;
import atux.util.print.builder.TestigoTicketBuilder;
import atux.util.text.FormatoDecimal;
import atux.vistas.buscar.BuscarProveedor;
import java.awt.Component;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class ICompletarPedidoVenta extends javax.swing.JInternalFrame {          
                    
    private CPedidoVenta controllerPedidoVenta;            
    private Proveedores proveedor;
    private PedidoVenta pedido;    
    private ModeloTomaPedidoVenta mtdpv;
    CProductoLocal cpl;
    
    private final Log logger = LogFactory.getLog(getClass());
    
    public ICompletarPedidoVenta() {
        initComponents();
        try {
            nuevoPedido();
        } catch (SQLException ex) {
            Logger.getLogger(ICompletarPedidoVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setEventTable()
    {
        TableModelListener tml = new TableModelListener(){

            public void tableChanged(TableModelEvent e) {
                tableHandlerEvent(e);                
            }
        };
        this.tblProductos.getModel().addTableModelListener(tml);  
       
    }
        
    private void tableHandlerEvent(TableModelEvent e)
    {
        setTotales();        
    }                  
    
    public void nuevoPedido() throws SQLException
    {       
        ECampos.buscarBotones(this.pnlActionButtons, true,
                              new Component[]{this.bntNuevo});        
        ECampos.buscarBotones(this.pnlHerramientas, true, null);        
        
        controllerPedidoVenta = new CPedidoVenta();                        
        this.jLTipoCambio.setVisible(true);
        this.ftfCambio.setText(String.valueOf(AtuxVariables.vTipoCambio));        
                
        this.bntImprimir.setEnabled(false);
        this.bntGuardar.setEnabled(false);                        
        rbTicketBoleta.setSelected(true);
        rbTicketFactura.setSelected(false);
        
        this.bntBuscarProveedor.setEnabled(true);
        this.txtObservaciones.setEditable(true);
        this.tblProductos.setEnabled(true);                    
        this.txtUsuario.setText(AppConfig.getUsuario().getNombreCompleto());
        this.txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);    
        
        if(this.tblProductos.getRowCount()>0)
        {
                this.dcFecha.setDate(Calendar.getInstance().getTime());                
                this.ftfItems.setValue(0);
                this.ftfBruto.setValue(0.00);
                this.ftfImpuesto.setValue(0.00);
                this.ftfDescuento.setValue(0.00);
                this.ftfRedeondeo.setValue(0.00);
                this.ftfTotal.setValue(0.00);                
                this.mtdpv = new ModeloTomaPedidoVenta(2);
                
                this.tblProductos.setModel(mtdpv);
                this.tblProductos.setDefaultRenderer(PanelAccionProdInsumos.class, new AccionTableCellRenderer(this));
                
                TableColumn tc = this.tblProductos.getColumnModel().getColumn(this.tblProductos.getColumnCount()-1);
                tc.setCellEditor(new CeldaAccionEditor(this));
                setEventTable();
                
                this.tblProductos.getColumnModel().getColumn(0).setPreferredWidth(300);
                this.tblProductos.getColumnModel().getColumn(1).setPreferredWidth(100);                
                
                CellEditorSpinnerPedidoVenta cnt = new CellEditorSpinnerPedidoVenta(1,this);        
                this.tblProductos.getColumnModel().getColumn(2).setCellEditor(cnt);                
                this.tblProductos.getColumnModel().getColumn(2).setCellRenderer(new TableCellFormatter(null));

                this.tblProductos.getColumnModel().getColumn(3).setCellRenderer(new TableCellFormatter());
                this.tblProductos.getColumnModel().getColumn(4).setCellRenderer(new TableCellFormatter());
                
                setProveedor(null);
                cpl = new CProductoLocal();
                AtuxVariables.arrayProductos = cpl.getProductosPedidoVenta();
        }
        
        AtuxSearch.existeCajaTurnoImpresora(new Frame());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupComprobante = new javax.swing.ButtonGroup();
        buttonGroupImpuesto = new javax.swing.ButtonGroup();
        buttonGroupEstado = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLFecha = new javax.swing.JLabel();
        jLTipoCambio = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuario = new elaprendiz.gui.textField.TextFieldRectIcon();
        ftfCambio = new elaprendiz.gui.textField.TextField();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRuc = new elaprendiz.gui.textField.TextField();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        txtObservaciones = new elaprendiz.gui.textField.TextField();
        bntBuscarProveedor = new javax.swing.JButton();
        txtProveedor = new elaprendiz.gui.textField.TextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        pnlHerramientas = new javax.swing.JPanel();
        bntImprimir = new elaprendiz.gui.button.ButtonRect();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        ftfItems = new javax.swing.JFormattedTextField();
        pnlComprobante = new javax.swing.JPanel();
        rbTicketBoleta = new javax.swing.JRadioButton();
        rbTicketFactura = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        ftfBruto = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        ftfDescuento = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        ftfAfecto = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        ftfImpuesto = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        ftfRedeondeo = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        ftfTotal = new javax.swing.JFormattedTextField();
        pnlActionButtons = new javax.swing.JPanel();
        bntNuevo = new elaprendiz.gui.button.ButtonRect();
        bntGuardar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setTitle("Completar pedido venta");
        setToolTipText("");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(930, 441));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setOpaque(false);

        jLFecha.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLFecha.setText("Fecha:");

        jLTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLTipoCambio.setText("Tipo Cambio:");
        jLTipoCambio.setMaximumSize(new java.awt.Dimension(60, 17));
        jLTipoCambio.setMinimumSize(new java.awt.Dimension(60, 17));
        jLTipoCambio.setPreferredSize(new java.awt.Dimension(60, 17));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel8.setText("Vendedor:");

        txtUsuario.setEditable(false);
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/user_blue_32.png"))); // NOI18N
        txtUsuario.setPreferredSize(new java.awt.Dimension(210, 22));

        ftfCambio.setEditable(false);
        ftfCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftfCambio.setPreferredSize(new java.awt.Dimension(120, 21));

        dcFecha.setDate(Calendar.getInstance().getTime());
        dcFecha.setPreferredSize(new java.awt.Dimension(120, 22));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ftfCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3))
                            .addComponent(jLFecha)))
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel6.setText("Cliente:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setText("Direccion:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setText("Observaciones:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel10.setText("RUC:");

        txtRuc.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtRuc.setNextFocusableComponent(txtObservaciones);
        txtRuc.setPreferredSize(new java.awt.Dimension(120, 21));

        txtDireccion.setNextFocusableComponent(txtRuc);
        txtDireccion.setPreferredSize(new java.awt.Dimension(615, 22));

        txtObservaciones.setPreferredSize(new java.awt.Dimension(450, 22));

        bntBuscarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/search32.png"))); // NOI18N
        bntBuscarProveedor.setToolTipText("Buscar Proveedor");
        bntBuscarProveedor.setNextFocusableComponent(txtProveedor);
        bntBuscarProveedor.setPreferredSize(new java.awt.Dimension(28, 20));
        bntBuscarProveedor.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/search32select.png"))); // NOI18N
        bntBuscarProveedor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/search32over.png"))); // NOI18N
        bntBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarProveedorActionPerformed(evt);
            }
        });

        txtProveedor.setNextFocusableComponent(txtDireccion);
        txtProveedor.setPreferredSize(new java.awt.Dimension(615, 22));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(bntBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel10)
                .addGap(4, 4, 4)
                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(bntBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9))
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(310, 255));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 150));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tblProductos.setCellSelectionEnabled(true);
        tblProductos.setEnabled(false);
        tblProductos.setRowHeight(24);
        tblProductos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblProductosFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        pnlHerramientas.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas"));

        bntImprimir.setBackground(new java.awt.Color(51, 153, 255));
        bntImprimir.setText("Generar Comprobante");
        bntImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHerramientasLayout = new javax.swing.GroupLayout(pnlHerramientas);
        pnlHerramientas.setLayout(pnlHerramientasLayout);
        pnlHerramientasLayout.setHorizontalGroup(
            pnlHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHerramientasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHerramientasLayout.setVerticalGroup(
            pnlHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHerramientasLayout.createSequentialGroup()
                .addComponent(bntImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles"));
        jPanel12.setPreferredSize(new java.awt.Dimension(80, 88));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel14.setText("Items:");
        jLabel14.setPreferredSize(new java.awt.Dimension(46, 20));

        ftfItems.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfItems.setEditable(false);
        ftfItems.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        ftfItems.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfItems.setPreferredSize(new java.awt.Dimension(120, 20));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ftfItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlComprobante.setBorder(javax.swing.BorderFactory.createTitledBorder("Comprobante"));

        buttonGroupComprobante.add(rbTicketBoleta);
        rbTicketBoleta.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbTicketBoleta.setText("Boleta Manual");

        buttonGroupComprobante.add(rbTicketFactura);
        rbTicketFactura.setFont(new java.awt.Font("Tahoma", 1, 14));
        rbTicketFactura.setText("Factura Manual");

        javax.swing.GroupLayout pnlComprobanteLayout = new javax.swing.GroupLayout(pnlComprobante);
        pnlComprobante.setLayout(pnlComprobanteLayout);
        pnlComprobanteLayout.setHorizontalGroup(
            pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComprobanteLayout.createSequentialGroup()
                .addComponent(rbTicketBoleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbTicketFactura))
        );
        pnlComprobanteLayout.setVerticalGroup(
            pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbTicketBoleta)
                .addComponent(rbTicketFactura))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel11.setText("Bruto:");

        ftfBruto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfBruto.setEditable(false);
        ftfBruto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfBruto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfBruto.setText("0.0");
        ftfBruto.setFont(new java.awt.Font("Tahoma", 0, 12));
        ftfBruto.setPreferredSize(new java.awt.Dimension(80, 22));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel15.setText("Descuento:");

        ftfDescuento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfDescuento.setEditable(false);
        ftfDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfDescuento.setText("0.0");
        ftfDescuento.setFont(new java.awt.Font("Tahoma", 0, 12));
        ftfDescuento.setPreferredSize(new java.awt.Dimension(80, 22));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel17.setText("Afecto:");

        ftfAfecto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfAfecto.setEditable(false);
        ftfAfecto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfAfecto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfAfecto.setText("0.0");
        ftfAfecto.setFont(new java.awt.Font("Tahoma", 0, 12));
        ftfAfecto.setPreferredSize(new java.awt.Dimension(80, 22));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel12.setText("Impuesto:");

        ftfImpuesto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfImpuesto.setEditable(false);
        ftfImpuesto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfImpuesto.setText("0.0");
        ftfImpuesto.setFont(new java.awt.Font("Tahoma", 0, 12));
        ftfImpuesto.setPreferredSize(new java.awt.Dimension(80, 22));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel16.setText("Redondeo:");

        ftfRedeondeo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfRedeondeo.setEditable(false);
        ftfRedeondeo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfRedeondeo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfRedeondeo.setText("0.0");
        ftfRedeondeo.setFont(new java.awt.Font("Tahoma", 0, 12));
        ftfRedeondeo.setPreferredSize(new java.awt.Dimension(80, 21));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Neto a Pagar:");

        ftfTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ftfTotal.setEditable(false);
        ftfTotal.setForeground(new java.awt.Color(255, 51, 51));
        ftfTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        ftfTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftfTotal.setText("0.0");
        ftfTotal.setFont(new java.awt.Font("Tahoma", 0, 14));
        ftfTotal.setPreferredSize(new java.awt.Dimension(80, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(pnlHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel11)
                .addGap(5, 5, 5)
                .addComponent(ftfBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(ftfDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel13)))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfAfecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfRedeondeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(pnlComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(ftfBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(ftfDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel16)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(ftfAfecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(ftfImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(ftfRedeondeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(ftfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        pnlActionButtons.setOpaque(false);

        bntNuevo.setBackground(new java.awt.Color(51, 153, 255));
        bntNuevo.setText("Nuevo");
        bntNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNuevoActionPerformed(evt);
            }
        });
        pnlActionButtons.add(bntNuevo);

        bntGuardar.setBackground(new java.awt.Color(51, 153, 255));
        bntGuardar.setText("Guardar");
        bntGuardar.setEnabled(false);
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });
        pnlActionButtons.add(bntGuardar);

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });
        pnlActionButtons.add(bntSalir);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarProveedorActionPerformed
       BuscarProveedor pvc = new BuscarProveedor(ModeloTablaProveedores.ACTIVOS);
       JLabel aviso = pvc.getAviso();
       String msj = "Lista de Clientes";
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{aviso},null);
   
        if(pvc.getProveedor() != null)
        {      
               setProveedor(((Proveedores)(pvc.getProveedor())));                              
        }
                
    }//GEN-LAST:event_bntBuscarProveedorActionPerformed

    private void bntNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNuevoActionPerformed
        try {
            nuevoPedido();
        } catch (SQLException ex) {
            Logger.getLogger(ICompletarPedidoVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntNuevoActionPerformed

    private void tblProductosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblProductosFocusLost
        tblProductos.clearSelection();
        setTotales();
    }//GEN-LAST:event_tblProductosFocusLost

    private void bntGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGuardarActionPerformed
        try {
            if(mtdpv.getNumItems() == 0)
            {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar al menos un Producto.", 
                        "Error: No ha seleccionado ningun producto", JOptionPane.ERROR_MESSAGE);
                this.tblProductos.addRowSelectionInterval(0, 0);
                return;
            }
                            
            guardar();                
            AtuxUtility.aceptarTransaccion();                                      
            if (AtuxVariables.vTipoCaja.equalsIgnoreCase(AtuxVariables.TIPO_CAJA_TRADICIONAL)) {
                 logger.info("Es caja tradicional <<" + AtuxVariables.vTipoCaja + ">>");
                 mostrarNumeroPedido(); 
                 this.bntNuevo.setEnabled(true);
            }
            else
            {
                logger.info("Es caja multifuncional <<" + AtuxVariables.vTipoCaja + ">>");
                this.bntImprimir.setEnabled(true);
                this.bntGuardar.setEnabled(false);
                this.tblProductos.setEnabled(false);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ICompletarPedidoVenta.class.getName()).log(Level.SEVERE, null, ex);
            AtuxUtility.liberarTransaccion();           
        }
    }//GEN-LAST:event_bntGuardarActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        try {
            nuevoPedido();
        } catch (SQLException ex) {
            Logger.getLogger(ICompletarPedidoVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeWindow(true);        
    }//GEN-LAST:event_bntSalirActionPerformed

    private void bntImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntImprimirActionPerformed
        if (AtuxVariables.vTipoCaja.equalsIgnoreCase(AtuxVariables.TIPO_CAJA_MULTIFUNCIONAL)) {
                 logger.info("Es caja multifuncional <<" + AtuxVariables.vTipoCaja + ">>");                 
                 mostrarCobroDePedidoGenerado();                                        
        }
    }//GEN-LAST:event_bntImprimirActionPerformed
        
    private void guardar() throws SQLException
    {                        
        pedido = new PedidoVenta();
        pedido.setCoCompania(AtuxVariables.vCodigoCompania);
        pedido.setCoLocal(AtuxVariables.vCodigoLocal);
        AtuxVariables.vNumeroPedido = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO, 10);
        AtuxSearch.setNuSecNumeracion(AtuxVariables.NUMERACION_PEDIDO);
        pedido.setNuPedido(AtuxVariables.vNumeroPedido);
        pedido.setFePedido(this.dcFecha.getCalendar().getTime());     
        pedido.setTiPedido(AtuxVariables.TIPO_PEDIDO_MANUAL);           
        pedido.setCaItem((Integer)this.ftfItems.getValue());        
        pedido.setVaTotalVenta((Double)this.ftfBruto.getValue());
        pedido.setVaTotalPrecioVenta((Double)this.ftfTotal.getValue());
        pedido.setNoImpresoCliente(txtProveedor.getText());
        pedido.setDeDireccionCliente(txtDireccion.getText());        
        pedido.setVaTotalDescuento((Double)this.ftfDescuento.getValue());
        pedido.setVaTotalImpuesto((Double)(this.ftfImpuesto.getValue()));
        pedido.setVaSaldoRedondeo((Double)(this.ftfRedeondeo.getValue()));        
        pedido.setInCuadreCaja("N");        
        pedido.setVaTasaCambio(Double.valueOf(this.ftfCambio.getText()));                     
        
        if(rbTicketBoleta.isSelected()) {
            pedido.setTiComprobante(AtuxVariables.TIPO_BOLETA_MANUAL);                              
         }        
         else 
           if(rbTicketFactura.isSelected()) {
            pedido.setTiComprobante(AtuxVariables.TIPO_FACTURA_MANUAL);                              
         }          
        
        pedido.setCoMoneda("01");
        pedido.setCoVendedor(AtuxVariables.vNuSecUsuario);        
        pedido.setNuRucCliente(txtRuc.getText());
        pedido.setNoImpresoComprobante(txtProveedor.getText());
        pedido.setDeDireccionComprobante("");
        pedido.setDeObservPedido(this.txtObservaciones.getText());
        pedido.setInPedidoAnulado("N");
        pedido.setEsPedidoVenta("P");
        pedido.setIdCreaPedidoVenta(AtuxVariables.vIdUsuario);       
        pedido.setFeCreaPedidoVenta(AtuxSearch.getFechaHora());
        pedido.setDetallePedidoVenta(mtdpv.getDetallesPedidoVenta());        
                
        logger.info("Revisando Numero de Pedido " + AtuxVariables.vNumeroPedido);        
        AtuxSearch.setNumeroPedidoDiario();        
        logger.info("Revisando Numero de Pedido Diario " + AtuxVariables.vNumeroPedidoDiario);
        pedido.setNuPedidoDiario(AtuxVariables.vNumeroPedidoDiario);  
        
        //Graba la cabecera del pedido de venta
        boolean rp = controllerPedidoVenta.guardarRegistro(pedido);                
        
        CDetallePedidoVenta crtlDpv = new CDetallePedidoVenta();
        if(rp)
        {
            ArrayList<DetallePedidoVenta> dtpv = pedido.getDetallePedidoVenta();
            
            //Graba el detalle del pedido                       
            for(DetallePedidoVenta item : dtpv)
            {
                item.setIdPedidoVenta(pedido); 
                item.setEsDetPedidoVenta("P");                
                item.setIdCreaDetPedidoVenta(AtuxVariables.vIdUsuario);
                item.setFeCreaDetPedidoVenta(AtuxSearch.getFechaHora());                
                crtlDpv.guardarRegistro(item);                                    
            }
            
            //Graba el Kardex
            CPedidoVenta.grabarKardexSegunPedido("",AtuxVariables.GRUPO_MOTIVO_KARDEX,
                        AtuxVariables.MOTIVO_KARDEX_VENTA, "D");
            
            //Actuliza el stock disponible y el stock comprometido
            for (DetallePedidoVenta item : dtpv) {
                CPedidoVenta.updateStocksProducto(item.getCoProducto(), item.getCaAtendida(), false);
            }
            
            AtuxSearch.setNuSecNumeracionNoCommit(AtuxVariables.NUMERACION_PEDIDO_DIARIO);
            
            //JOptionPane.showInternalMessageDialog(this, "Número de pedido : "+AtuxVariables.vNumeroPedidoDiario+
            //                                      " a cobrar.", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }        
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBuscarProveedor;
    private elaprendiz.gui.button.ButtonRect bntGuardar;
    private elaprendiz.gui.button.ButtonRect bntImprimir;
    private elaprendiz.gui.button.ButtonRect bntNuevo;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.ButtonGroup buttonGroupComprobante;
    private javax.swing.ButtonGroup buttonGroupEstado;
    private javax.swing.ButtonGroup buttonGroupImpuesto;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JFormattedTextField ftfAfecto;
    private javax.swing.JFormattedTextField ftfBruto;
    private elaprendiz.gui.textField.TextField ftfCambio;
    private javax.swing.JFormattedTextField ftfDescuento;
    private javax.swing.JFormattedTextField ftfImpuesto;
    private javax.swing.JFormattedTextField ftfItems;
    private javax.swing.JFormattedTextField ftfRedeondeo;
    private javax.swing.JFormattedTextField ftfTotal;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLTipoCambio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JPanel pnlComprobante;
    private javax.swing.JPanel pnlHerramientas;
    private javax.swing.JRadioButton rbTicketBoleta;
    private javax.swing.JRadioButton rbTicketFactura;
    private javax.swing.JTable tblProductos;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private elaprendiz.gui.textField.TextField txtObservaciones;
    private elaprendiz.gui.textField.TextField txtProveedor;
    private elaprendiz.gui.textField.TextField txtRuc;
    private elaprendiz.gui.textField.TextFieldRectIcon txtUsuario;
    // End of variables declaration//GEN-END:variables

    private void mostrarCobroDePedidoGenerado() {
        if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA)
                || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)) {
            AtuxVariables.vTipoCliente = AtuxVariables.PERSONA_NATURAL;
        } else
            AtuxVariables.vTipoCliente = AtuxVariables.PERSONA_JURIDICA;
        
        logger.info("El cliente es uan persona " + AtuxVariables.vTipoCliente);                 
        
        IPedidoGenerado dlgPedidoGenerado = new IPedidoGenerado(this, "Generación de Pedido", true);        
        
        if (AtuxVariables.vTipoCaja.equalsIgnoreCase(AtuxVariables.TIPO_CAJA_MULTIFUNCIONAL)) {
            if(rbTicketBoleta.isSelected()){
               dlgPedidoGenerado.setTiPersona(0);                           
               dlgPedidoGenerado.txtCliente.setText("");            
               dlgPedidoGenerado.txtRUC.setText("");
               dlgPedidoGenerado.txtDireccion.setText("");
               pedido.setTiComprobante(AtuxVariables.TIPO_TICKET_BOLETA); //TODO verificar cuando sea como parametro el tipo de comprobante
               dlgPedidoGenerado.rdbFactura.setSelected(false);
               dlgPedidoGenerado.rdbBoleta.setSelected(true);
            }
            else if (rbTicketFactura.isSelected()){
               dlgPedidoGenerado.setTiPersona(1);
               dlgPedidoGenerado.txtCliente.setText(txtProveedor.getText());            
               dlgPedidoGenerado.txtRUC.setText(txtRuc.getText());
               dlgPedidoGenerado.txtDireccion.setText(txtDireccion.getText());
               pedido.setTiComprobante(AtuxVariables.TIPO_FACTURA);  //TODO verificar cuando sea como parametro el tipo de comprobante
               dlgPedidoGenerado.rdbFactura.setSelected(true);
               dlgPedidoGenerado.rdbBoleta.setSelected(false);
            }
            dlgPedidoGenerado.esMultifuncional = true;
        }
        dlgPedidoGenerado.bloquearEscape = true;        

        dlgPedidoGenerado.setVisible(true);

        if (AtuxVariables.vAceptar) {
            AtuxVariables.vANombreDe   = dlgPedidoGenerado.txtCliente.getText().trim();
            pedido.setNoImpresoCliente(AtuxVariables.vANombreDe);
            pedido.setNoImpresoComprobante(AtuxVariables.vANombreDe);
            AtuxVariables.vRUCFacturar = dlgPedidoGenerado.txtRUC.getText().trim();
            
            if(dlgPedidoGenerado.txtRUC.getText().length()>0)
                pedido.setTiComprobante(AtuxVariables.TIPO_FACTURA_MANUAL);
            else
                pedido.setTiComprobante(AtuxVariables.TIPO_BOLETA_MANUAL);
            
            AtuxVariables.vDireccion   = dlgPedidoGenerado.txtDireccion.getText().trim();
            pedido.setDeDireccionCliente(AtuxVariables.vDireccion);
            
            mostrarPagoPedido();
            closeWindow(true);
        } else if (dlgPedidoGenerado.pedidoAnulado)
            closeWindow(true);
    }
    
    private void mostrarNumeroPedido() {
        logger.info("Muestra el Número de pedido diario generado");
        impresionComandaTestigo(); 
        
        INumeroPedido numeroPedido = new INumeroPedido(new Frame(),"[Enter] para continuar",true);
        numeroPedido.setVisible(true);
        
        if (AtuxVariables.vAceptar){
           this.setVisible(false);        
           this.dispose();
        }
    }
    
    private void mostrarPagoPedido() {
        logger.info("Muestra el pago del pedido");        
        IPagoPedido iPagoPedido = new IPagoPedido(new Frame(), "Pago de pedido", true);
            iPagoPedido.setPedido(pedido);
            iPagoPedido.setVisible(true);
        
        if (AtuxVariables.vAceptar){
           this.setVisible(false);
           this.dispose();
        }
    }

    private void impresionComandaTestigo() {
        logger.info("Entra a impresionTestigo()");
        String estado = "";
        try {
            estado = AtuxDBUtility.getValueAt("VTTM_LOCAL", "IN_IMPRIME_TESTIGO", "CO_COMPANIA = '"
                    + AtuxVariables.vCodigoCompania + "'" + " AND CO_LOCAL = '" + AtuxVariables.vCodigoLocal + "'");
            
            logger.info("Imprime testigo? " + estado);
            
        } catch (SQLException err) {
             JOptionPane.showInternalMessageDialog(this, ""+pedido.getNuPedido()+
                                            "Testigo", "Error al obtener Estado", JOptionPane.INFORMATION_MESSAGE);                         
            err.printStackTrace();
        }        

        if (estado.equalsIgnoreCase("S"))
            imprimeTestigoNew();

    }

    private void imprimeTestigoNew() {
      try {
            TestigoBuilder testigoPrint = null;
            ArrayList datos = new ArrayList();

            AtuxDBUtility.getImpresoraTestigo(datos);

            if (datos.isEmpty())
                return;

            String rutaImpresora = ((String) ((ArrayList) datos.get(0)).get(1)).trim();
            int espacios = AtuxUtility.trunc(((String) ((ArrayList) datos.get(0)).get(2)).trim());
            boolean ticketera = ((String) ((ArrayList) datos.get(0)).get(0)).trim().equals("02"); // TI_TESTIGO_TICKET

            if (ticketera) {
                testigoPrint = new TestigoTicketBuilder(pedido);
            } 
            else {
                testigoPrint = new TestigoMatricialPrinter(pedido);
            }
        
            TestigoGenerator reporteador = new TestigoGenerator(testigoPrint, rutaImpresora, espacios);
            reporteador.setTicketera(ticketera);
            reporteador.generarTestigo();
        } catch (Exception sqlerr) {
            logger.error("Error al obtener testigo  - ", sqlerr);
            JOptionPane.showInternalMessageDialog(this, ""+pedido.getNuPedido()+
                                            "Testigo", "No se ha podido imprimir testigo -  " + "\n" + sqlerr.toString(), JOptionPane.INFORMATION_MESSAGE);                        
            sqlerr.printStackTrace();
        }  
    }    
    
    private void setProveedor(Proveedores proveedor)
    {
        this.proveedor = proveedor;
       if(this.proveedor != null)
        {
           this.txtProveedor.setText(this.proveedor.getDeProveedor().trim());   
           this.txtRuc.setText(this.proveedor.getNuDocIdentidad().trim());
           this.txtDireccion.setText(this.proveedor.getDeDireccion().trim());                                       
           rbTicketFactura.setSelected(true);
           rbTicketBoleta.setSelected(false);               
        }
       else{
            this.txtProveedor.setText("");
            this.txtRuc.setText("");
            this.txtDireccion.setText("");
            rbTicketFactura.setSelected(false);
            rbTicketBoleta.setSelected(true);
        }
    }    
    
    public void setTotales()
    {       
        this.ftfBruto.setValue(mtdpv.getBruto());        
        this.ftfDescuento.setValue(mtdpv.getTotalDescuento());
        this.ftfAfecto.setValue(mtdpv.getAfecto());
        this.ftfImpuesto.setValue(mtdpv.getTotalImpuesto());        
        this.ftfTotal.setValue(mtdpv.getTotalPrecioVenta());
        this.ftfRedeondeo.setValue(mtdpv.getRedondeo());
        this.ftfItems.setValue(mtdpv.getNumItems());
        
        if(mtdpv.getBruto()>0){
            bntGuardar.setEnabled(true);
        }
    }
    
     public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }
         
}
