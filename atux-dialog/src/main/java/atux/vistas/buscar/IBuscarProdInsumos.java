package atux.vistas.buscar;

import atux.controllers.CProductoLocal;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaProducto;
import atux.util.ECampos;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @Alan Guerra
 */
public class IBuscarProdInsumos extends javax.swing.JDialog implements ActionListener{

    private ProductoLocal prodLocal;
    private ModeloTablaProducto mtp;
    private ModeloTablaProducto mtpInsumos;
    private ModeloTablaProducto mtpPromocion;
    private int COL_DE_PRODUCTO=1;
    CProductoLocal   cProdLoc;

    private final Log logger = LogFactory.getLog(getClass());

    public IBuscarProdInsumos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblAviso.setVisible(false);
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelImageBusquedaProducto = new elaprendiz.gui.panel.PanelImage();
        jPanelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField(new Dimension(400,50));
        bntBuscar = new elaprendiz.gui.button.ButtonRect();
        lblAviso = new javax.swing.JLabel();
        jPanelOpciones = new javax.swing.JPanel();
        rbTodos = new javax.swing.JRadioButton();
        rbConStock = new javax.swing.JRadioButton();
        rbPrinActivos = new javax.swing.JRadioButton();
        jPanelProductos = new javax.swing.JPanel();
        jPanelProducto = new javax.swing.JPanel();
        jScrollPaneProducto = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanelDetalleProd = new javax.swing.JPanel();
        jlGrupo = new javax.swing.JLabel();
        jLDGrupo = new javax.swing.JLabel();
        jLinea = new javax.swing.JLabel();
        jDLinea = new javax.swing.JLabel();
        jlCajas = new javax.swing.JLabel();
        jlDCajas = new javax.swing.JLabel();
        jlPrecio = new javax.swing.JLabel();
        jlDPrecio = new javax.swing.JLabel();
        jlStock = new javax.swing.JLabel();
        jlDStock = new javax.swing.JLabel();
        jLaboratorio = new javax.swing.JLabel();
        jDLaboratorio = new javax.swing.JLabel();
        jPanelInsumos = new javax.swing.JPanel();
        jScrollPaneInsumos = new javax.swing.JScrollPane();
        tblInsumosProductos = new javax.swing.JTable();
        jPanelDetalleInsumos = new javax.swing.JPanel();
        jlGrupo2 = new javax.swing.JLabel();
        jLDGrupoComplementario = new javax.swing.JLabel();
        jLinea2 = new javax.swing.JLabel();
        jDLineaComplementario = new javax.swing.JLabel();
        jlCajas2 = new javax.swing.JLabel();
        jlDCajasComplementario = new javax.swing.JLabel();
        jlPrecio2 = new javax.swing.JLabel();
        jlDPrecioComplementario = new javax.swing.JLabel();
        jlStock2 = new javax.swing.JLabel();
        jlDStockComplementario = new javax.swing.JLabel();
        jLaboratorio2 = new javax.swing.JLabel();
        jDLaboratorioComplementario = new javax.swing.JLabel();
        jPanelPromocion = new javax.swing.JPanel();
        jScrollPanePromocion = new javax.swing.JScrollPane();
        tblProductosPromocion = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(860, 339));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelImageBusquedaProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageBusquedaProducto.setPreferredSize(new java.awt.Dimension(860, 320));
        panelImageBusquedaProducto.setLayout(new java.awt.BorderLayout());

        jPanelBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelBusqueda.setAlignmentX(0.9F);
        jPanelBusqueda.setAlignmentY(0.9F);
        jPanelBusqueda.setAutoscrolls(true);
        jPanelBusqueda.setOpaque(false);
        jPanelBusqueda.setPreferredSize(new java.awt.Dimension(860, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Buscar Por:");

        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        txtDato.setToolTipText("");
        txtDato.setAlignmentX(0.0F);
        txtDato.setPreferredSize(new java.awt.Dimension(180, 27));
        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatokeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDatoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatoKeyReleased(evt);
            }
        });

        bntBuscar.setBackground(new java.awt.Color(51, 153, 255));
        bntBuscar.setText("Buscar");
        bntBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarActionPerformed(evt);
            }
        });

        lblAviso.setFont(new java.awt.Font("Segoe Print", 1, 17));
        lblAviso.setForeground(new java.awt.Color(255, 255, 0));
        lblAviso.setText("Promoción");

        jPanelOpciones.setBackground(new java.awt.Color(51, 153, 255));

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });

        rbConStock.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbConStock);
        rbConStock.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbConStock.setForeground(new java.awt.Color(255, 255, 255));
        rbConStock.setText("Con Stock");
        rbConStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbConStockActionPerformed(evt);
            }
        });

        rbPrinActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbPrinActivos);
        rbPrinActivos.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbPrinActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbPrinActivos.setText("Prin Activo");
        rbPrinActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPrinActivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOpcionesLayout = new javax.swing.GroupLayout(jPanelOpciones);
        jPanelOpciones.setLayout(jPanelOpcionesLayout);
        jPanelOpcionesLayout.setHorizontalGroup(
            jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbConStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbPrinActivos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOpcionesLayout.setVerticalGroup(
            jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbConStock, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbPrinActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBusquedaLayout = new javax.swing.GroupLayout(jPanelBusqueda);
        jPanelBusqueda.setLayout(jPanelBusquedaLayout);
        jPanelBusquedaLayout.setHorizontalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(bntBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanelBusquedaLayout.setVerticalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(bntBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImageBusquedaProducto.add(jPanelBusqueda, java.awt.BorderLayout.PAGE_START);

        jPanelProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelProductos.setForeground(new java.awt.Color(255, 255, 255));
        jPanelProductos.setOpaque(false);
        jPanelProductos.setPreferredSize(new java.awt.Dimension(860, 300));
        jPanelProductos.setLayout(null);

        jPanelProducto.setAutoscrolls(true);

        jScrollPaneProducto.setPreferredSize(new java.awt.Dimension(452, 250));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto", "Unidad", "Laboratorio", "Pre.Venta", "Descuento", "Pre.Público", "Stock", "Bono"
            }
        ));
        tblProductos.setToolTipText("Productos");
        tblProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductos.setRowHeight(18);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPaneProducto.setViewportView(tblProductos);

        javax.swing.GroupLayout jPanelProductoLayout = new javax.swing.GroupLayout(jPanelProducto);
        jPanelProducto.setLayout(jPanelProductoLayout);
        jPanelProductoLayout.setHorizontalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
        );
        jPanelProductoLayout.setVerticalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
        );

        jPanelProductos.add(jPanelProducto);
        jPanelProducto.setBounds(1, 1, 840, 310);

        jPanelDetalleProd.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleProd.setAlignmentX(0.0F);
        jPanelDetalleProd.setAlignmentY(0.0F);
        jPanelDetalleProd.setEnabled(false);
        jPanelDetalleProd.setLayout(null);

        jlGrupo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlGrupo.setForeground(new java.awt.Color(153, 0, 0));
        jlGrupo.setText("Grupo :");
        jlGrupo.setName(""); // NOI18N
        jPanelDetalleProd.add(jlGrupo);
        jlGrupo.setBounds(10, 4, 40, 20);

        jLDGrupo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLDGrupo.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jLDGrupo);
        jLDGrupo.setBounds(50, 4, 140, 20);

        jLinea.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLinea.setForeground(new java.awt.Color(153, 0, 0));
        jLinea.setText("Linea :");
        jPanelDetalleProd.add(jLinea);
        jLinea.setBounds(190, 4, 36, 20);

        jDLinea.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLinea.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jDLinea);
        jDLinea.setBounds(230, 4, 150, 20);

        jlCajas.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCajas.setForeground(new java.awt.Color(153, 0, 0));
        jlCajas.setText("Cajas :");
        jPanelDetalleProd.add(jlCajas);
        jlCajas.setBounds(380, 4, 43, 20);

        jlDCajas.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDCajas.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDCajas);
        jlDCajas.setBounds(420, 4, 65, 20);

        jlPrecio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPrecio.setForeground(new java.awt.Color(153, 0, 0));
        jlPrecio.setText("Precio :");
        jPanelDetalleProd.add(jlPrecio);
        jlPrecio.setBounds(490, 4, 42, 20);

        jlDPrecio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDPrecio);
        jlDPrecio.setBounds(532, 4, 40, 20);

        jlStock.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlStock.setForeground(new java.awt.Color(153, 0, 0));
        jlStock.setText("Stock :");
        jPanelDetalleProd.add(jlStock);
        jlStock.setBounds(572, 4, 39, 20);

        jlDStock.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDStock.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDStock);
        jlDStock.setBounds(611, 4, 25, 20);

        jLaboratorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLaboratorio.setForeground(new java.awt.Color(153, 0, 0));
        jLaboratorio.setText("Lab :");
        jPanelDetalleProd.add(jLaboratorio);
        jLaboratorio.setBounds(630, 4, 30, 20);

        jDLaboratorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLaboratorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jDLaboratorio);
        jDLaboratorio.setBounds(660, 4, 180, 19);

        jPanelProductos.add(jPanelDetalleProd);
        jPanelDetalleProd.setBounds(1, 312, 840, 32);

        jPanelInsumos.setPreferredSize(new java.awt.Dimension(769, 105));

        jScrollPaneInsumos.setPreferredSize(new java.awt.Dimension(452, 400));

        tblInsumosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto complementario", "Unidad", "Laboratorio", "Pre.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
            }
        ));
        tblInsumosProductos.setToolTipText("Productos complementarios");
        tblInsumosProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblInsumosProductos.setPreferredSize(new java.awt.Dimension(820, 400));
        tblInsumosProductos.setRowHeight(18);
        jScrollPaneInsumos.setViewportView(tblInsumosProductos);

        javax.swing.GroupLayout jPanelInsumosLayout = new javax.swing.GroupLayout(jPanelInsumos);
        jPanelInsumos.setLayout(jPanelInsumosLayout);
        jPanelInsumosLayout.setHorizontalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelInsumosLayout.setVerticalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
        );

        jPanelProductos.add(jPanelInsumos);
        jPanelInsumos.setBounds(1, 334, 840, 130);

        jPanelDetalleInsumos.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleInsumos.setEnabled(false);
        jPanelDetalleInsumos.setLayout(null);

        jlGrupo2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlGrupo2.setForeground(new java.awt.Color(153, 0, 0));
        jlGrupo2.setText("Grupo :");
        jlGrupo2.setName(""); // NOI18N
        jPanelDetalleInsumos.add(jlGrupo2);
        jlGrupo2.setBounds(10, 4, 40, 20);

        jLDGrupoComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLDGrupoComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jLDGrupoComplementario);
        jLDGrupoComplementario.setBounds(50, 4, 140, 20);

        jLinea2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLinea2.setForeground(new java.awt.Color(153, 0, 0));
        jLinea2.setText("Linea :");
        jPanelDetalleInsumos.add(jLinea2);
        jLinea2.setBounds(190, 4, 36, 20);

        jDLineaComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLineaComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jDLineaComplementario);
        jDLineaComplementario.setBounds(230, 4, 150, 20);

        jlCajas2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCajas2.setForeground(new java.awt.Color(153, 0, 0));
        jlCajas2.setText("Cajas :");
        jPanelDetalleInsumos.add(jlCajas2);
        jlCajas2.setBounds(380, 4, 43, 20);

        jlDCajasComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDCajasComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jlDCajasComplementario);
        jlDCajasComplementario.setBounds(420, 4, 70, 20);

        jlPrecio2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPrecio2.setForeground(new java.awt.Color(153, 0, 0));
        jlPrecio2.setText("Precio :");
        jPanelDetalleInsumos.add(jlPrecio2);
        jlPrecio2.setBounds(490, 4, 42, 20);

        jlDPrecioComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDPrecioComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jlDPrecioComplementario);
        jlDPrecioComplementario.setBounds(532, 4, 40, 20);

        jlStock2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlStock2.setForeground(new java.awt.Color(153, 0, 0));
        jlStock2.setText("Stock :");
        jPanelDetalleInsumos.add(jlStock2);
        jlStock2.setBounds(572, 4, 39, 20);

        jlDStockComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDStockComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jlDStockComplementario);
        jlDStockComplementario.setBounds(611, 4, 25, 20);

        jLaboratorio2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLaboratorio2.setForeground(new java.awt.Color(153, 0, 0));
        jLaboratorio2.setText("Lab :");
        jPanelDetalleInsumos.add(jLaboratorio2);
        jLaboratorio2.setBounds(630, 4, 30, 20);

        jDLaboratorioComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLaboratorioComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleInsumos.add(jDLaboratorioComplementario);
        jDLaboratorioComplementario.setBounds(660, 4, 180, 19);

        jPanelProductos.add(jPanelDetalleInsumos);
        jPanelDetalleInsumos.setBounds(1, 465, 840, 27);

        jPanelPromocion.setPreferredSize(new java.awt.Dimension(787, 90));

        jScrollPanePromocion.setPreferredSize(new java.awt.Dimension(452, 250));

        tblProductosPromocion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto promoción", "Unidad", "Laboratorio", "Prec.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
            }
        ));
        tblProductosPromocion.setToolTipText("Promociones");
        tblProductosPromocion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosPromocion.setPreferredSize(new java.awt.Dimension(820, 200));
        tblProductosPromocion.setRowHeight(18);
        jScrollPanePromocion.setViewportView(tblProductosPromocion);

        javax.swing.GroupLayout jPanelPromocionLayout = new javax.swing.GroupLayout(jPanelPromocion);
        jPanelPromocion.setLayout(jPanelPromocionLayout);
        jPanelPromocionLayout.setHorizontalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelPromocionLayout.setVerticalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jPanelProductos.add(jPanelPromocion);
        jPanelPromocion.setBounds(1, 493, 840, 55);

        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(0);
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        //btnSalir.setAccelerator(Accelerator.ACTION_EXIT);
        btnSalir.addActionListener(this);
        jPanelProductos.add(btnSalir);
        btnSalir.setBounds(700, 565, 110, 19);

        panelImageBusquedaProducto.add(jPanelProductos, java.awt.BorderLayout.CENTER);

        jPanel1.add(panelImageBusquedaProducto, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDatokeyTyped(KeyEvent evt) {
        AtuxUtility.convertirMayuscula(evt);
    }

    public ProductoLocal getProductoLocal() {
        return prodLocal;
    }

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
                if (prodLocal.getCaStockDisponible() == 0) {
                    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                } else {
                    logger.info("ProductoLocal: " + prodLocal.getProducto().getDeProducto());
                    AtuxVariables.vAceptar = true;
                    this.setVisible(false);
                }
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            prodLocal = null;
            AtuxVariables.vAceptar = false;
        }
        else if (tblProductos != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            checkTeclaPresionada(evt, tblProductos);
        }
    }//GEN-LAST:event_txtDatoKeyPressed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
//            String vFindText = txtDato.getText().trim();
//            String currentCodigo = prodLocal.getCoProducto();
//            String currentProducto = prodLocal.getProducto().getDeProducto().toUpperCase();

//            if ((currentCodigo.equalsIgnoreCase(vFindText) || currentProducto.substring(0, vFindText.length()).equalsIgnoreCase(vFindText))) {                            
//                   bntBuscar.doClick();
//            }
            if(prodLocal.getCaStockDisponible()==0){
                AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", null);
            }
            else {
                logger.info("ProductoLocal: "+prodLocal.getProducto().getDeProducto());
                AtuxVariables.vAceptar = true;
            }
        }
        else if (!(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_O)) {
            AtuxGridUtils.buscarDescripcion(evt, tblProductos, txtDato, COL_DE_PRODUCTO);
            prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
            mostrarDetalleProducto(prodLocal);
        }
    }//GEN-LAST:event_txtDatoKeyReleased

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbConStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbConStockActionPerformed
        ProductoLocal productoLoc;
        ArrayList<ProductoLocal> prodSoloConStock = new ArrayList();

        Iterator<ProductoLocal> iter = AtuxVariables.arrayProductos.iterator();
        while (iter.hasNext()){
            productoLoc = iter.next();

            if(productoLoc.getCaStockDisponible()>0){
                prodSoloConStock.add(productoLoc);
            }
        }
        inicializarCarga(prodSoloConStock);
        AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_rbConStockActionPerformed

    private void rbPrinActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPrinActivosActionPerformed
        ProductoLocal productoLoc;
        ArrayList<ProductoLocal> prodSoloConStock = new ArrayList();
        AtuxVariables.vMuestraListaPrincipiosActivos = true;

        cProdLoc = new CProductoLocal();
        Iterator<ProductoLocal> iter = cProdLoc.getProductosPedidoVenta().iterator();
        while (iter.hasNext()){
            productoLoc = (ProductoLocal) iter.next();
            prodSoloConStock.add(productoLoc);
        }

        inicializarCarga(prodSoloConStock);
        AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_rbPrinActivosActionPerformed

    private void bntBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarActionPerformed
        if (tblProductos.getRowCount() <= 0) {
            return;
        }

        String vCodigo = this.txtDato.getText().trim();
        prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());

        if (vCodigo.trim().length() > 0) {
            if (vCodigo.trim().length() == 6 && AtuxUtility.isLong(vCodigo)) { //CODIGO DE PRODUCTO
                if (!AtuxUtility.findTextInJTable(tblProductos, vCodigo, 0, COL_DE_PRODUCTO)) {
                    AtuxUtility.showMessage(null, "Producto no encontrado según criterio de búsqueda !!!", null);
                    AtuxUtility.moveFocus(txtDato);
                } else {
                    txtDato.setText(((String) tblProductos.getValueAt(tblProductos.getSelectedRow(), 1)).trim());
                   mostrarDetalleProducto(prodLocal);
                }
            } else if (vCodigo.trim().length() >= 6 && vCodigo.trim().length() <= 13) {
                if (AtuxUtility.findTextInJTable(tblProductos, vCodigo, 0, COL_DE_PRODUCTO)) {
                    vCodigo = txtDato.getText().trim();
                    AtuxUtility.findTextInJTable(tblProductos, vCodigo, 0, COL_DE_PRODUCTO);
                    mostrarDetalleProducto(prodLocal);
                    return;
                }
                /*try { TODO ver el uso del codigo de barra
                    VariablesVentas.vCodigoBarra = vCodigo;
                    vCodigo = DBVentas.getCodigoProductoBarra();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    AtuxUtility.showMessage(this, "Error en la búsqueda del Producto !!! - " + sqlException.getErrorCode(), null);
                }

                if (vCodigo.equalsIgnoreCase("0000000")) {
                    AtuxUtility.showMessage(null, "Producto no encontrado según criterio de búsqueda !!!", null);                    
                    AtuxUtility.moveFocus(txtDato);
                } else {
                    AtuxUtility.findTextInJTable(tblProductos, vCodigo, 1, 2);
                    txtDato.setText(((String) tblProductos.getValueAt(tblProductos.getSelectedRow(), 2)).trim());
                    System.out.println("...FILTRO CODIGO DE BARRAS ");
                    mostrarDetalleProducto();
                }*/

            } else if (AtuxUtility.findTextInJTable(tblProductos, vCodigo, 0, COL_DE_PRODUCTO)) {
                vCodigo = txtDato.getText().trim();
                AtuxUtility.findTextInJTable(tblProductos, vCodigo, 0, COL_DE_PRODUCTO);
                logger.info("...Penultima condición ");
               mostrarDetalleProducto(prodLocal);
            } else {
                AtuxUtility.showMessage(null, "Producto no encontrado según criterio de búsqueda !!!", txtDato);
            }
        }
    }//GEN-LAST:event_bntBuscarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (evt.getClickCount() == 2) {
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
                if (prodLocal.getCaStockDisponible() == 0) {
                    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                } else {
                    logger.info("ProductoLocal: " + prodLocal.getProducto().getDeProducto());
                    AtuxVariables.vAceptar = true;
                    this.setVisible(false);
                }
            }
        }

        if (evt.getClickCount() == 1) {
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
                mostrarDetalleProducto(prodLocal);
                txtDato.setText(prodLocal.getProducto().getDeProducto());
                prodLocal = null;
                AtuxUtility.moveFocus(txtDato);
            }
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {
        AtuxUtility.moveFocus(txtDato);
    }

    public final void inicializarCarga(ArrayList lista){
        mtp = new ModeloTablaProducto(lista,ModeloTablaProducto.PRO_LISTA);
        this.tblProductos.setModel(mtp);
        this.tblProductos.repaint();
        mtp.fireTableDataChanged();

        Helper.ajustarAnchoColumnasNew(tblProductos,ModeloTablaProducto.anchoColumnas);
        AtuxGridUtils.setearPrimerRegistro(tblProductos,txtDato,COL_DE_PRODUCTO);

        prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
        mostrarDetalleProducto(prodLocal);

    }

    private void checkTeclaPresionada(KeyEvent evt, JTable tblProductos) {
        AtuxGridUtils.aceptarTeclaPresionada(evt, tblProductos, txtDato, COL_DE_PRODUCTO);
        prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
        mostrarDetalleProducto(prodLocal);
    }

    private void mostrarDetalleProducto(ProductoLocal prodLocal) {

        jLDGrupo.setText(prodLocal.getDeJ5());
        jDLinea.setText(prodLocal.getDeJ1());
        jDLaboratorio.setText(prodLocal.getProducto().getLaboratorio().getDeLaboratorio());

        jlDCajas.setText(prodLocal.getProducto().getDeUnidadProducto());

        if  (prodLocal.getInProdFraccionado().equals("S")){
            double preCaja = Helper.redondear(prodLocal.getVaPrecioPublico()*prodLocal.getVaFraccion(), 2);
            jlDPrecio.setText(String.valueOf(preCaja));
            int stockCaja = Math.round(prodLocal.getCaStockDisponible()/prodLocal.getVaFraccion());
            jlDStock.setText(String.valueOf(stockCaja));
        }
        else{
            jlDPrecio.setText(prodLocal.getVaPrecioPublico().toString());
            jlDStock.setText(String.valueOf(prodLocal.getCaStockDisponible()));
        }

        cProdLoc = new CProductoLocal();

        this.tblInsumosProductos.removeAll();

        mtpInsumos = new ModeloTablaProducto(cProdLoc.getInsumosProductos(prodLocal), ModeloTablaProducto.PRO_INSUMOS);
        this.tblInsumosProductos.setModel(mtpInsumos);

        this.tblInsumosProductos.repaint();
        mtpInsumos.fireTableDataChanged();
        Helper.ajustarSoloAnchoColumnas(tblInsumosProductos, ModeloTablaProducto.anchoColumnas);

        this.tblProductosPromocion.removeAll();

        mtpPromocion = new ModeloTablaProducto(cProdLoc.getProductosPromocion(prodLocal.getCoProducto()),ModeloTablaProducto.PRO_PROMOCION);
        this.tblProductosPromocion.setModel(mtpPromocion);
        Helper.ajustarSoloAnchoColumnas(tblProductosPromocion, ModeloTablaProducto.anchoColumnasPromo);

        ECampos.setEditableTexto(this.jPanelDetalleInsumos, true, new Component[]{jlGrupo2, jLinea2, jlCajas2, jlPrecio2, jlStock2, jLaboratorio2}, true, "");

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            dispose();
            prodLocal = null;
            AtuxVariables.vAceptar = false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jDLaboratorio;
    private javax.swing.JLabel jDLaboratorioComplementario;
    private javax.swing.JLabel jDLinea;
    private javax.swing.JLabel jDLineaComplementario;
    private javax.swing.JLabel jLDGrupo;
    private javax.swing.JLabel jLDGrupoComplementario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLaboratorio;
    private javax.swing.JLabel jLaboratorio2;
    private javax.swing.JLabel jLinea;
    private javax.swing.JLabel jLinea2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelDetalleInsumos;
    private javax.swing.JPanel jPanelDetalleProd;
    private javax.swing.JPanel jPanelInsumos;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JPanel jPanelProductos;
    private javax.swing.JPanel jPanelPromocion;
    private javax.swing.JScrollPane jScrollPaneInsumos;
    private javax.swing.JScrollPane jScrollPaneProducto;
    private javax.swing.JScrollPane jScrollPanePromocion;
    private javax.swing.JLabel jlCajas;
    private javax.swing.JLabel jlCajas2;
    private javax.swing.JLabel jlDCajas;
    private javax.swing.JLabel jlDCajasComplementario;
    private javax.swing.JLabel jlDPrecio;
    private javax.swing.JLabel jlDPrecioComplementario;
    private javax.swing.JLabel jlDStock;
    private javax.swing.JLabel jlDStockComplementario;
    private javax.swing.JLabel jlGrupo;
    private javax.swing.JLabel jlGrupo2;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JLabel jlPrecio2;
    private javax.swing.JLabel jlStock;
    private javax.swing.JLabel jlStock2;
    private javax.swing.JLabel lblAviso;
    private elaprendiz.gui.panel.PanelImage panelImageBusquedaProducto;
    private javax.swing.JRadioButton rbConStock;
    private javax.swing.JRadioButton rbPrinActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblInsumosProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosPromocion;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
}