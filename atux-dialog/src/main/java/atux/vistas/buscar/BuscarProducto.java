package atux.vistas.buscar;

import atux.controllers.CProductoLocal;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaProducto;
import atux.util.ECampos;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.venta.ISaldoLocales;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class BuscarProducto extends javax.swing.JPanel {
    
    private ProductoLocal prodLocal;    
    private ModeloTablaProducto mtp;    
    private ModeloTablaProducto mtpPrinActivo;
    private ModeloTablaProducto mtpComplementario;    
    private ModeloTablaProducto mtpPromocion;

    CProductoLocal   cProdLoc;
    private JOptionPane op;
    
    private final Log logger = LogFactory.getLog(getClass());
    private int COL_DE_PRODUCTO=1;   
    
     public BuscarProducto(int compra) {              
        initComponents();                 
        inicializarCarga(AtuxVariables.arrayProductos);
        lblAviso.setVisible(false);
    }

    public JLabel getLbAviso() {
        return lblAviso;
    }

    private void getOptionPane()
    {
        if(op != null)
        {
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }

    public ProductoLocal getProductoLocal() {
        return prodLocal;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panelImageBusquedaProducto = new elaprendiz.gui.panel.PanelImage();
        jPanelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField(new Dimension(400,50));
        bntBuscar = new elaprendiz.gui.button.ButtonRect();
        lblAviso = new javax.swing.JLabel();
        jPanelOpciones = new javax.swing.JPanel();
        rbTodos = new javax.swing.JRadioButton();
        rbConStock = new javax.swing.JRadioButton();
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
        jPanelPrincipiosActivos = new javax.swing.JPanel();
        jScrollPanePrincipiosActivos = new javax.swing.JScrollPane();
        tblProductosPrincipiosActivos = new javax.swing.JTable();
        jPanelDetalleSustitutorio = new javax.swing.JPanel();
        jlGrupo1 = new javax.swing.JLabel();
        jLDGrupoSustitutorio = new javax.swing.JLabel();
        jLinea1 = new javax.swing.JLabel();
        jDLineaSustitutorio = new javax.swing.JLabel();
        jlCajas1 = new javax.swing.JLabel();
        jlDCajasSustitutorio = new javax.swing.JLabel();
        jlPrecio1 = new javax.swing.JLabel();
        jlDPrecioSustitutorio = new javax.swing.JLabel();
        jlStock1 = new javax.swing.JLabel();
        jlDStockSustitutorio = new javax.swing.JLabel();
        jLaboratorio1 = new javax.swing.JLabel();
        jDLaboratorioSustitutorio = new javax.swing.JLabel();
        jPanelComplementarios = new javax.swing.JPanel();
        jScrollPaneComplementarios = new javax.swing.JScrollPane();
        tblProductosComplementarios = new javax.swing.JTable();
        jPanelDetalleComplementarios = new javax.swing.JPanel();
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
        lblFaltarCero = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();
        lblVerStockEnLocales = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(860, 339));
        setLayout(new java.awt.BorderLayout());

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

        txtDato.setToolTipText("");
        txtDato.setAlignmentX(0.0F);
        txtDato.setPreferredSize(new java.awt.Dimension(180, 27));
        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
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
        rbConStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbConStock.setForeground(new java.awt.Color(255, 255, 255));
        rbConStock.setText("Solo con Stock");
        rbConStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbConStockActionPerformed(evt);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOpcionesLayout.setVerticalGroup(
            jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionesLayout.createSequentialGroup()
                .addGroup(jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbConStock, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBusquedaLayout = new javax.swing.GroupLayout(jPanelBusqueda);
        jPanelBusqueda.setLayout(jPanelBusquedaLayout);
        jPanelBusquedaLayout.setHorizontalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(bntBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanelBusquedaLayout.setVerticalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(10, 10, 10)
                .addComponent(jPanelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addGap(0, 224, Short.MAX_VALUE)
            .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
        );

        jPanelProductos.add(jPanelProducto);
        jPanelProducto.setBounds(1, 1, 840, 224);

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
        jPanelDetalleProd.setBounds(1, 226, 840, 26);

        jPanelPrincipiosActivos.setAutoscrolls(true);
        jPanelPrincipiosActivos.setPreferredSize(new java.awt.Dimension(769, 105));

        jScrollPanePrincipiosActivos.setAutoscrolls(true);
        jScrollPanePrincipiosActivos.setPreferredSize(new java.awt.Dimension(452, 400));

        tblProductosPrincipiosActivos.setModel(new javax.swing.table.DefaultTableModel(
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
                "Código", "Producto sustituto", "Unidad", "Laboratorio", "Pre.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
            }
        ));
        tblProductosPrincipiosActivos.setToolTipText("Productos sustitutorios");
        tblProductosPrincipiosActivos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosPrincipiosActivos.setPreferredSize(new java.awt.Dimension(820, 400));
        tblProductosPrincipiosActivos.setRowHeight(18);
        tblProductosPrincipiosActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosPrincipiosActivosMouseClicked(evt);
            }
        });
        tblProductosPrincipiosActivos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosPrincipiosActivosKeyReleased(evt);
            }
        });
        jScrollPanePrincipiosActivos.setViewportView(tblProductosPrincipiosActivos);

        javax.swing.GroupLayout jPanelPrincipiosActivosLayout = new javax.swing.GroupLayout(jPanelPrincipiosActivos);
        jPanelPrincipiosActivos.setLayout(jPanelPrincipiosActivosLayout);
        jPanelPrincipiosActivosLayout.setHorizontalGroup(
            jPanelPrincipiosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePrincipiosActivos, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelPrincipiosActivosLayout.setVerticalGroup(
            jPanelPrincipiosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePrincipiosActivos, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        jPanelProductos.add(jPanelPrincipiosActivos);
        jPanelPrincipiosActivos.setBounds(1, 253, 840, 91);

        jPanelDetalleSustitutorio.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleSustitutorio.setEnabled(false);
        jPanelDetalleSustitutorio.setLayout(null);

        jlGrupo1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlGrupo1.setForeground(new java.awt.Color(153, 0, 0));
        jlGrupo1.setText("Grupo :");
        jlGrupo1.setName(""); // NOI18N
        jPanelDetalleSustitutorio.add(jlGrupo1);
        jlGrupo1.setBounds(10, 4, 40, 20);

        jLDGrupoSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLDGrupoSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jLDGrupoSustitutorio);
        jLDGrupoSustitutorio.setBounds(50, 4, 140, 20);

        jLinea1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLinea1.setForeground(new java.awt.Color(153, 0, 0));
        jLinea1.setText("Linea :");
        jPanelDetalleSustitutorio.add(jLinea1);
        jLinea1.setBounds(190, 4, 36, 20);

        jDLineaSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLineaSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jDLineaSustitutorio);
        jDLineaSustitutorio.setBounds(230, 4, 150, 20);

        jlCajas1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCajas1.setForeground(new java.awt.Color(153, 0, 0));
        jlCajas1.setText("Cajas :");
        jPanelDetalleSustitutorio.add(jlCajas1);
        jlCajas1.setBounds(380, 4, 43, 20);

        jlDCajasSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDCajasSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jlDCajasSustitutorio);
        jlDCajasSustitutorio.setBounds(420, 4, 70, 20);

        jlPrecio1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPrecio1.setForeground(new java.awt.Color(153, 0, 0));
        jlPrecio1.setText("Precio :");
        jPanelDetalleSustitutorio.add(jlPrecio1);
        jlPrecio1.setBounds(490, 4, 42, 20);

        jlDPrecioSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDPrecioSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jlDPrecioSustitutorio);
        jlDPrecioSustitutorio.setBounds(532, 4, 40, 20);

        jlStock1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlStock1.setForeground(new java.awt.Color(153, 0, 0));
        jlStock1.setText("Stock :");
        jPanelDetalleSustitutorio.add(jlStock1);
        jlStock1.setBounds(572, 4, 39, 20);

        jlDStockSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDStockSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jlDStockSustitutorio);
        jlDStockSustitutorio.setBounds(611, 4, 25, 20);

        jLaboratorio1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLaboratorio1.setForeground(new java.awt.Color(153, 0, 0));
        jLaboratorio1.setText("Lab :");
        jPanelDetalleSustitutorio.add(jLaboratorio1);
        jLaboratorio1.setBounds(630, 4, 30, 20);

        jDLaboratorioSustitutorio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLaboratorioSustitutorio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleSustitutorio.add(jDLaboratorioSustitutorio);
        jDLaboratorioSustitutorio.setBounds(660, 4, 180, 19);

        jPanelProductos.add(jPanelDetalleSustitutorio);
        jPanelDetalleSustitutorio.setBounds(1, 345, 840, 27);

        jPanelComplementarios.setPreferredSize(new java.awt.Dimension(769, 105));

        jScrollPaneComplementarios.setPreferredSize(new java.awt.Dimension(452, 400));

        tblProductosComplementarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductosComplementarios.setToolTipText("Productos complementarios");
        tblProductosComplementarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosComplementarios.setPreferredSize(new java.awt.Dimension(820, 400));
        tblProductosComplementarios.setRowHeight(18);
        tblProductosComplementarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosComplementariosMouseClicked(evt);
            }
        });
        tblProductosComplementarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosComplementariosKeyReleased(evt);
            }
        });
        jScrollPaneComplementarios.setViewportView(tblProductosComplementarios);

        javax.swing.GroupLayout jPanelComplementariosLayout = new javax.swing.GroupLayout(jPanelComplementarios);
        jPanelComplementarios.setLayout(jPanelComplementariosLayout);
        jPanelComplementariosLayout.setHorizontalGroup(
            jPanelComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneComplementarios, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelComplementariosLayout.setVerticalGroup(
            jPanelComplementariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneComplementarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        jPanelProductos.add(jPanelComplementarios);
        jPanelComplementarios.setBounds(1, 373, 840, 91);

        jPanelDetalleComplementarios.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleComplementarios.setEnabled(false);
        jPanelDetalleComplementarios.setLayout(null);

        jlGrupo2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlGrupo2.setForeground(new java.awt.Color(153, 0, 0));
        jlGrupo2.setText("Grupo :");
        jlGrupo2.setName(""); // NOI18N
        jPanelDetalleComplementarios.add(jlGrupo2);
        jlGrupo2.setBounds(10, 4, 40, 20);

        jLDGrupoComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLDGrupoComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jLDGrupoComplementario);
        jLDGrupoComplementario.setBounds(50, 4, 140, 20);

        jLinea2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLinea2.setForeground(new java.awt.Color(153, 0, 0));
        jLinea2.setText("Linea :");
        jPanelDetalleComplementarios.add(jLinea2);
        jLinea2.setBounds(190, 4, 36, 20);

        jDLineaComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLineaComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jDLineaComplementario);
        jDLineaComplementario.setBounds(230, 4, 150, 20);

        jlCajas2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCajas2.setForeground(new java.awt.Color(153, 0, 0));
        jlCajas2.setText("Cajas :");
        jPanelDetalleComplementarios.add(jlCajas2);
        jlCajas2.setBounds(380, 4, 43, 20);

        jlDCajasComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDCajasComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jlDCajasComplementario);
        jlDCajasComplementario.setBounds(420, 4, 70, 20);

        jlPrecio2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPrecio2.setForeground(new java.awt.Color(153, 0, 0));
        jlPrecio2.setText("Precio :");
        jPanelDetalleComplementarios.add(jlPrecio2);
        jlPrecio2.setBounds(490, 4, 42, 20);

        jlDPrecioComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDPrecioComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jlDPrecioComplementario);
        jlDPrecioComplementario.setBounds(532, 4, 40, 20);

        jlStock2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlStock2.setForeground(new java.awt.Color(153, 0, 0));
        jlStock2.setText("Stock :");
        jPanelDetalleComplementarios.add(jlStock2);
        jlStock2.setBounds(572, 4, 39, 20);

        jlDStockComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDStockComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jlDStockComplementario);
        jlDStockComplementario.setBounds(611, 4, 25, 20);

        jLaboratorio2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLaboratorio2.setForeground(new java.awt.Color(153, 0, 0));
        jLaboratorio2.setText("Lab :");
        jPanelDetalleComplementarios.add(jLaboratorio2);
        jLaboratorio2.setBounds(630, 4, 30, 20);

        jDLaboratorioComplementario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLaboratorioComplementario.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleComplementarios.add(jDLaboratorioComplementario);
        jDLaboratorioComplementario.setBounds(660, 4, 180, 19);

        jPanelProductos.add(jPanelDetalleComplementarios);
        jPanelDetalleComplementarios.setBounds(1, 465, 840, 27);

        jPanelPromocion.setPreferredSize(new java.awt.Dimension(787, 90));

        jScrollPanePromocion.setPreferredSize(new java.awt.Dimension(452, 250));

        tblProductosPromocion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Promo", "Laboratorio"
            }
        ));
        tblProductosPromocion.setToolTipText("Promociones");
        tblProductosPromocion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosPromocion.setPreferredSize(new java.awt.Dimension(820, 200));
        tblProductosPromocion.setRowHeight(18);
        tblProductosPromocion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosPromocionMouseClicked(evt);
            }
        });
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

        lblFaltarCero.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFaltarCero.setForeground(new java.awt.Color(255, 255, 0));
        lblFaltarCero.setText("[F1] Falta CERO");
        jPanelProductos.add(lblFaltarCero);
        lblFaltarCero.setBounds(410, 570, 123, 17);

        lblSalir.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSalir.setForeground(new java.awt.Color(255, 255, 0));
        lblSalir.setText("[ESCAPE] Salir");
        jPanelProductos.add(lblSalir);
        lblSalir.setBounds(690, 570, 103, 17);

        lblVerStockEnLocales.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVerStockEnLocales.setForeground(new java.awt.Color(255, 255, 0));
        lblVerStockEnLocales.setText("[F3] Stock Locales");
        jPanelProductos.add(lblVerStockEnLocales);
        lblVerStockEnLocales.setBounds(540, 570, 138, 17);

        panelImageBusquedaProducto.add(jPanelProductos, java.awt.BorderLayout.CENTER);

        add(panelImageBusquedaProducto, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void bntBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarActionPerformed
        // TODO add your handling code here:               
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

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
             prodLocal = null;
             AtuxVariables.vAceptar = false;
         }
        else if (tblProductos != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            checkTeclaPresionada(evt, tblProductos);
        }        
    }//GEN-LAST:event_txtDatoKeyPressed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        // TODO add your handling code here:
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
                getOptionPane();
                op.setValue(1);                                                                            
              }               
        }
        else if (evt.getKeyCode() == KeyEvent.VK_F1) {
            try {
                prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());  
                if (JOptionPane.showConfirmDialog(this, "Desea colocar al producto " + prodLocal.getProducto().getDeProducto() +"\n"+" como falta CERO", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    AtuxSearch.updateProdNoAtendido(prodLocal,1);
                }
            } catch (SQLException ex) {
                logger.info("Error al actualizar la cantidad no atendida: "+ex.getMessage());
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_F3) {
            prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());    
            
            ISaldoLocales iSaldoLocales = new ISaldoLocales(new Frame(), true, prodLocal.getCoProducto());
                    iSaldoLocales.setVisible(true);
        }
        else if (!(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_O)) {
            AtuxGridUtils.buscarDescripcion(evt, tblProductos, txtDato, COL_DE_PRODUCTO);
            prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
            mostrarDetalleProducto(prodLocal);
       }
        
    }//GEN-LAST:event_txtDatoKeyReleased

    private void tblProductosPrincipiosActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosPrincipiosActivosMouseClicked
        if (evt.getClickCount() == 2) {
           if(tblProductosPrincipiosActivos.getSelectedRow() != -1)
            {                                                 
                
                prodLocal =(ProductoLocal)mtpPrinActivo.getFila(tblProductosPrincipiosActivos.getSelectedRow());
                
                if(prodLocal.getCaStockDisponible()==0){
                    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                }
                else {
                logger.info("ProductoLocal: "+prodLocal.getProducto().getDeProducto());
                AtuxVariables.vAceptar = true;
                getOptionPane();
                op.setValue(1);                                                                            
                } 
            }
           //else{
               //lblAviso.setVisible(true);
            //}    
        }
       
        if (evt.getClickCount() == 1) {
           if(tblProductosPrincipiosActivos.getSelectedRow() != -1)
            {
                prodLocal =(ProductoLocal)mtpPrinActivo.getFila(tblProductosPrincipiosActivos.getSelectedRow());                
                mostrarDetalleProductoSustitutorio(prodLocal);
                //txtDato.setText(prodLocal.getProducto().getDeProducto());
                prodLocal = null;
                //AtuxUtility.moveFocus(txtDato);               
            }
            //else{
               //lblAviso.setVisible(true);
            //}
       }
    }//GEN-LAST:event_tblProductosPrincipiosActivosMouseClicked
            
    private void tblProductosComplementariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosComplementariosMouseClicked
        if (evt.getClickCount() == 2) {
           if(tblProductosComplementarios.getSelectedRow() != -1)
            {                                                 
                
                prodLocal =(ProductoLocal)mtpComplementario.getFila(tblProductosComplementarios.getSelectedRow());
                
                if(prodLocal.getCaStockDisponible()==0){
                    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                }
                else {
                logger.info("ProductoLocal: "+prodLocal.getProducto().getDeProducto());
                AtuxVariables.vAceptar = true;
                getOptionPane();
                op.setValue(1);                                                                            
                } 
            }
            //else{
               //lblAviso.setVisible(true);
            //}    
        }
       
        if (evt.getClickCount() == 1) {
           if(tblProductosComplementarios.getSelectedRow() != -1)
            {
                prodLocal =(ProductoLocal)mtpComplementario.getFila(tblProductosComplementarios.getSelectedRow());                
                mostrarDetalleProductoComplementario(prodLocal);
                //txtDato.setText(prodLocal.getProducto().getDeProducto());
                prodLocal = null;
                //AtuxUtility.moveFocus(txtDato);               
            }
           //else{
               //lblAviso.setVisible(true);
            //}
       }
    }//GEN-LAST:event_tblProductosComplementariosMouseClicked

    private void tblProductosPromocionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosPromocionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosPromocionMouseClicked

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (evt.getClickCount() == 2) {             
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());                  
                if (prodLocal.getCaStockDisponible() == 0) {   
                    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);                 
                } else {                     
                    logger.info("ProductoLocal: " + prodLocal.getProducto().getDeProducto());                     
                    AtuxVariables.vAceptar = true;                     
                    getOptionPane();                     
                    op.setValue(1);                 
                  }             
                } 
                //else {                 
                    //lblAviso.setVisible(true);             
                //}         
         }
        
        if (evt.getClickCount() == 1) {             
            if (tblProductos.getSelectedRow() != -1) {                 
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());                 
                mostrarDetalleProducto(prodLocal);                 
                txtDato.setText(prodLocal.getProducto().getDeProducto());                 
                prodLocal = null;                 
                AtuxUtility.moveFocus(txtDato);             
            } 
            //else {                 
                //lblAviso.setVisible(true);             
            //}         
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosComplementariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosComplementariosKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_F1) {
            try {
                prodLocal =(ProductoLocal)mtpComplementario.getFila(tblProductosComplementarios.getSelectedRow());  
                if (JOptionPane.showConfirmDialog(this, "Desea colocar al producto " + prodLocal.getProducto().getDeProducto() +"\n"+" como falta CERO", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    AtuxSearch.updateProdNoAtendido(prodLocal,1);
                }
            } catch (SQLException ex) {
                logger.info("Error al actualizar la cantidad no atendida: "+ex.getMessage());
            }
       }
       if (!(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_O)) {
            //AtuxGridUtils.buscarDescripcion(evt, tblProductosComplementarios, txtDato, COL_DE_PRODUCTO);
            prodLocal =(ProductoLocal)mtpComplementario.getFila(tblProductosComplementarios.getSelectedRow());
            mostrarDetalleProductoComplementario(prodLocal);
       }
    }//GEN-LAST:event_tblProductosComplementariosKeyReleased

    private void tblProductosPrincipiosActivosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosPrincipiosActivosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            try {
                prodLocal =(ProductoLocal)mtpPrinActivo.getFila(tblProductosPrincipiosActivos.getSelectedRow());  
                if (JOptionPane.showConfirmDialog(this, "Desea colocar al producto " + prodLocal.getProducto().getDeProducto() +"\n"+" como falta CERO", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    AtuxSearch.updateProdNoAtendido(prodLocal,1);
                }
            } catch (SQLException ex) {
                logger.info("Error al actualizar la cantidad no atendida: "+ex.getMessage());
            }
        }
        if (!(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_O)) {
            //AtuxGridUtils.buscarDescripcion(evt, tblProductosComplementarios, txtDato, COL_DE_PRODUCTO);
            prodLocal =(ProductoLocal)mtpPrinActivo.getFila(tblProductosPrincipiosActivos.getSelectedRow());
            mostrarDetalleProductoSustitutorio(prodLocal);
        }       
    }//GEN-LAST:event_tblProductosPrincipiosActivosKeyReleased

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbConStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbConStockActionPerformed
       ProductoLocal productoLoc;
       ArrayList<ProductoLocal> prodSoloConStock = new ArrayList();
       
        Iterator<ProductoLocal> iter = AtuxVariables.arrayProductos.iterator();
                while (iter.hasNext()){
                        productoLoc = (ProductoLocal) iter.next();
                        
                        if(productoLoc.getCaStockDisponible()>0){
                            prodSoloConStock.add(productoLoc);
                        }
                }                
                inicializarCarga(prodSoloConStock);                
                AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_rbConStockActionPerformed
           
    public void inicializarCarga(ArrayList lista){
        mtp = new ModeloTablaProducto(lista,ModeloTablaProducto.PRO_LISTA);        
        this.tblProductos.setModel(mtp);                                
        this.tblProductos.repaint();
        mtp.fireTableDataChanged();
        
        Helper.ajustarAnchoColumnasNew(tblProductos,ModeloTablaProducto.anchoColumnas);        
        //this.lblAviso.setVisible(true);
        AtuxGridUtils.setearPrimerRegistro(tblProductos,txtDato,COL_DE_PRODUCTO);                
        
        prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow()); 
        mostrarDetalleProducto(prodLocal);
                
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntBuscar;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jDLaboratorio;
    private javax.swing.JLabel jDLaboratorioComplementario;
    private javax.swing.JLabel jDLaboratorioSustitutorio;
    private javax.swing.JLabel jDLinea;
    private javax.swing.JLabel jDLineaComplementario;
    private javax.swing.JLabel jDLineaSustitutorio;
    private javax.swing.JLabel jLDGrupo;
    private javax.swing.JLabel jLDGrupoComplementario;
    private javax.swing.JLabel jLDGrupoSustitutorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLaboratorio;
    private javax.swing.JLabel jLaboratorio1;
    private javax.swing.JLabel jLaboratorio2;
    private javax.swing.JLabel jLinea;
    private javax.swing.JLabel jLinea1;
    private javax.swing.JLabel jLinea2;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelComplementarios;
    private javax.swing.JPanel jPanelDetalleComplementarios;
    private javax.swing.JPanel jPanelDetalleProd;
    private javax.swing.JPanel jPanelDetalleSustitutorio;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPrincipiosActivos;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JPanel jPanelProductos;
    private javax.swing.JPanel jPanelPromocion;
    private javax.swing.JScrollPane jScrollPaneComplementarios;
    private javax.swing.JScrollPane jScrollPanePrincipiosActivos;
    private javax.swing.JScrollPane jScrollPaneProducto;
    private javax.swing.JScrollPane jScrollPanePromocion;
    private javax.swing.JLabel jlCajas;
    private javax.swing.JLabel jlCajas1;
    private javax.swing.JLabel jlCajas2;
    private javax.swing.JLabel jlDCajas;
    private javax.swing.JLabel jlDCajasComplementario;
    private javax.swing.JLabel jlDCajasSustitutorio;
    private javax.swing.JLabel jlDPrecio;
    private javax.swing.JLabel jlDPrecioComplementario;
    private javax.swing.JLabel jlDPrecioSustitutorio;
    private javax.swing.JLabel jlDStock;
    private javax.swing.JLabel jlDStockComplementario;
    private javax.swing.JLabel jlDStockSustitutorio;
    private javax.swing.JLabel jlGrupo;
    private javax.swing.JLabel jlGrupo1;
    private javax.swing.JLabel jlGrupo2;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JLabel jlPrecio1;
    private javax.swing.JLabel jlPrecio2;
    private javax.swing.JLabel jlStock;
    private javax.swing.JLabel jlStock1;
    private javax.swing.JLabel jlStock2;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblFaltarCero;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblVerStockEnLocales;
    private elaprendiz.gui.panel.PanelImage panelImageBusquedaProducto;
    private javax.swing.JRadioButton rbConStock;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosComplementarios;
    private javax.swing.JTable tblProductosPrincipiosActivos;
    private javax.swing.JTable tblProductosPromocion;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables

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
        this.tblProductosPrincipiosActivos.removeAll();                               
                
        mtpPrinActivo = new ModeloTablaProducto(cProdLoc.getProductosConMismoPrincipioActivo(prodLocal.getCoProducto()),ModeloTablaProducto.PRO_SUSTITUTOS);        
        this.tblProductosPrincipiosActivos.setModel(mtpPrinActivo);                                
        Helper.ajustarSoloAnchoColumnas(tblProductosPrincipiosActivos,ModeloTablaProducto.anchoColumnas);
        
        this.tblProductosComplementarios.removeAll();
        
        mtpComplementario = new ModeloTablaProducto(cProdLoc.getProductosComplementarios(prodLocal.getCoJ5()),ModeloTablaProducto.PRO_COMPLEMENTARIOS);        
        this.tblProductosComplementarios.setModel(mtpComplementario);                                
        Helper.ajustarSoloAnchoColumnas(tblProductosComplementarios,ModeloTablaProducto.anchoColumnas);

        this.tblProductosPromocion.removeAll();

        mtpPromocion = new ModeloTablaProducto(cProdLoc.getProductosPromocion(prodLocal.getCoProducto()),ModeloTablaProducto.PRO_PROMOCION);
        this.tblProductosPromocion.setModel(mtpPromocion);
        Helper.ajustarSoloAnchoColumnas(tblProductosPromocion, ModeloTablaProducto.anchoColumnasPromo);

        ECampos.setEditableTexto(this.jPanelDetalleSustitutorio, true, new Component[]{jlGrupo1,jLinea1,jlCajas1,jlPrecio1,jlStock1,jLaboratorio1},true,"");
        ECampos.setEditableTexto(this.jPanelDetalleComplementarios, true, new Component[]{jlGrupo2,jLinea2,jlCajas2,jlPrecio2,jlStock2,jLaboratorio2},true,"");        
        
        
    }
    
    private void mostrarDetalleProductoSustitutorio(ProductoLocal prodLocal) {
        
        jLDGrupoSustitutorio.setText(prodLocal.getDeJ5());
        jDLineaSustitutorio.setText(prodLocal.getDeJ1());
        jDLaboratorioSustitutorio.setText(prodLocal.getProducto().getLaboratorio().getDeLaboratorio());
        
        jlDCajasSustitutorio.setText(prodLocal.getProducto().getDeUnidadProducto());
        
        if  (prodLocal.getInProdFraccionado().equals("S")){  
            double preCaja = Helper.redondear(prodLocal.getVaPrecioPublico()*prodLocal.getVaFraccion(), 2);                    
            jlDPrecioSustitutorio.setText(String.valueOf(preCaja));   
            int stockCaja = Math.round(prodLocal.getCaStockDisponible()/prodLocal.getVaFraccion());
            jlDStockSustitutorio.setText(String.valueOf(stockCaja));   
        }
        else{
           jlDPrecioSustitutorio.setText(AtuxUtility.formatNumber(prodLocal.getVaPrecioPublico(),2));    
           jlDStockSustitutorio.setText(String.valueOf(prodLocal.getCaStockDisponible())); 
        }
        
    }
    
    private void mostrarDetalleProductoComplementario(ProductoLocal prodLocal) {
        
        jLDGrupoComplementario.setText(prodLocal.getDeJ5());
        jDLineaComplementario.setText(prodLocal.getDeJ1());
        jDLaboratorioComplementario.setText(prodLocal.getProducto().getLaboratorio().getDeLaboratorio());
        
        jlDCajasComplementario.setText(prodLocal.getProducto().getDeUnidadProducto());
        
        if  (prodLocal.getInProdFraccionado().equals("S")){  
            double preCaja = Helper.redondear(prodLocal.getVaPrecioPublico()*prodLocal.getVaFraccion(), 2);                    
            jlDPrecioComplementario.setText(String.valueOf(preCaja));   
            int stockCaja = Math.round(prodLocal.getCaStockDisponible()/prodLocal.getVaFraccion());
            jlDStockComplementario.setText(String.valueOf(stockCaja));   
        }
        else{
           jlDPrecioComplementario.setText(AtuxUtility.formatNumber(prodLocal.getVaPrecioPublico(),2));    
           jlDStockComplementario.setText(String.valueOf(prodLocal.getCaStockDisponible())); 
        }
        
    }
          
}