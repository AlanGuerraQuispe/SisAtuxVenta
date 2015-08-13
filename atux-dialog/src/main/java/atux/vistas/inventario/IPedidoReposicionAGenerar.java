package atux.vistas.inventario;

import atux.managerbd.BaseConexion;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import atux.util.common.AtuxVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */
public final class IPedidoReposicionAGenerar extends javax.swing.JDialog{
            
    private final Log logger = LogFactory.getLog(getClass());        
    ModeloTablaSimple model;
    public ArrayList<ProductoLocal> arrayListProdReponer;
    private ProductoLocal prodLocal;
    private ArrayList outParameters_mes;
    IPedidoReposicion iPedidoRep;
    
    public IPedidoReposicionAGenerar(java.awt.Frame parent, boolean modal,IPedidoReposicion iPedidoRep) {
       super(parent, modal);
        initComponents();        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        AtuxUtility.centrarVentana(this);        
        initListaProductos();        
        this.iPedidoRep = iPedidoRep;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panelFondo = new elaprendiz.gui.panel.PanelImage();
        pnlHead = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtDeProducto = new elaprendiz.gui.textField.TextField();
        chkOrdenPorCalculada = new javax.swing.JRadioButton();
        chkOrdenOriginal = new javax.swing.JRadioButton();
        chkOrdenPorFaltaCero = new javax.swing.JRadioButton();
        pnlBody = new javax.swing.JPanel();
        jScrollPaneDet = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlFoot = new javax.swing.JPanel();
        lblMinExhibicion = new javax.swing.JLabel();
        txtMinExhibicion = new elaprendiz.gui.textField.TextField();
        lblTransitoT = new javax.swing.JLabel();
        txtTransito = new elaprendiz.gui.textField.TextField();
        lblMinDiasT = new javax.swing.JLabel();
        txtParamMinDias = new elaprendiz.gui.textField.TextField();
        lblMaxDiasT = new javax.swing.JLabel();
        txtParamMaxDias = new elaprendiz.gui.textField.TextField();
        lblNroDias = new javax.swing.JLabel();
        txtParamNroDias = new elaprendiz.gui.textField.TextField();
        lblVentasDias = new javax.swing.JLabel();
        txtVentasDias = new elaprendiz.gui.textField.TextField();
        lblMes01 = new javax.swing.JLabel();
        lblMes02 = new javax.swing.JLabel();
        txtMes01 = new elaprendiz.gui.textField.TextField();
        txtMes02 = new elaprendiz.gui.textField.TextField();
        lblMes03 = new javax.swing.JLabel();
        txtMes03 = new elaprendiz.gui.textField.TextField();
        lblMes04 = new javax.swing.JLabel();
        txtMes04 = new elaprendiz.gui.textField.TextField();
        pnlActionButtons = new javax.swing.JPanel();
        btnGrabarPedidoRep = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Pedido a enviar a Matriz");
        panelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlHead.setBackground(new java.awt.Color(51, 153, 255));
        pnlHead.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBuscar.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblBuscar.setText("Producto");

        txtDeProducto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDeProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDeProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeProductokeyReleased(evt);
            }
        });

        chkOrdenPorCalculada.setBackground(new java.awt.Color(51, 153, 255));
        chkOrdenPorCalculada.setFont(new java.awt.Font("Tahoma", 1, 14));
        chkOrdenPorCalculada.setText("x Cantidad Calculada");
        chkOrdenPorCalculada.setPreferredSize(new java.awt.Dimension(180, 25));
        chkOrdenPorCalculada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOrdenPorCalculadaActionPerformed(evt);
            }
        });

        chkOrdenPorFaltaCero.setBackground(new java.awt.Color(51, 153, 255));
        chkOrdenPorFaltaCero.setFont(new java.awt.Font("Tahoma", 1, 14));
        chkOrdenPorFaltaCero.setText("x Falta Cero");
        chkOrdenPorFaltaCero.setPreferredSize(new java.awt.Dimension(180, 25));
        chkOrdenPorFaltaCero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOrdenPorFaltaCeroActionPerformed(evt);
            }
        });

        buttonGroup.add(chkOrdenPorCalculada);
        buttonGroup.add(chkOrdenOriginal);
        buttonGroup.add(chkOrdenPorFaltaCero);
        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
                pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlHeadLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lblBuscar)
                                .addGap(29, 29, 29)
                                .addComponent(txtDeProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(chkOrdenPorCalculada, 100, 130, 170)
                                .addGap(32, 32, 32)
                                .addComponent(chkOrdenPorFaltaCero, 120, 180, 190))
                        .addGroup(pnlHeadLayout.createSequentialGroup()
                                .addContainerGap(465, Short.MAX_VALUE))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblBuscar)
                                            .addComponent(txtDeProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkOrdenPorCalculada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkOrdenPorFaltaCero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBody.setBackground(new java.awt.Color(51, 153, 255));
        pnlBody.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista final de productos a reponer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 0))); // NOI18N

        jScrollPaneDet.setPreferredSize(new java.awt.Dimension(452, 150));

        tblDetalle.setRowHeight(24);
        tblDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDetalleKeyPressed(evt);
            }
        });
        jScrollPaneDet.setViewportView(tblDetalle);

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneDet, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneDet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
        );

        pnlFoot.setBackground(new java.awt.Color(51, 153, 255));
        pnlFoot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMinExhibicion.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMinExhibicion.setText("Min.Exhibicion");

        txtMinExhibicion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTransitoT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTransitoT.setText("Tránsito");

        txtTransito.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMinDiasT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMinDiasT.setText("Min.Días");

        txtParamMinDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMaxDiasT.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMaxDiasT.setText("Max.Días");

        txtParamMaxDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNroDias.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblNroDias.setText("Nro.Días");

        txtParamNroDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblVentasDias.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblVentasDias.setText("Ventas/Días");

        txtVentasDias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                .addGap(31, 31, 31)
                .addComponent(lblMinExhibicion)
                .addGap(10, 10, 10)
                .addComponent(txtMinExhibicion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lblMinDiasT)
                .addGap(7, 7, 7)
                .addComponent(txtParamMinDias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblNroDias)
                .addGap(29, 29, 29)
                .addComponent(txtParamNroDias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblMes01)
                .addGap(10, 10, 10)
                .addComponent(txtMes01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblMes03)
                .addGap(14, 14, 14)
                .addComponent(txtMes03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlFootLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblTransitoT)
                .addGap(44, 44, 44)
                .addComponent(txtTransito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lblMaxDiasT)
                .addGap(5, 5, 5)
                .addComponent(txtParamMaxDias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblVentasDias)
                .addGap(18, 18, 18)
                .addComponent(txtVentasDias, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblMes02)
                .addGap(10, 10, 10)
                .addComponent(txtMes02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblMes04)
                .addGap(14, 14, 14)
                .addComponent(txtMes04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFootLayout.setVerticalGroup(
            pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFootLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMinExhibicion)
                    .addComponent(txtMinExhibicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblMinDiasT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtParamMinDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblNroDias))
                    .addComponent(txtParamNroDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblMes01, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMes01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblMes03, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMes03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblTransitoT))
                    .addComponent(txtTransito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaxDiasT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParamMaxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFootLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblVentasDias))
                    .addComponent(txtVentasDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes02, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMes04, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlActionButtons.setOpaque(false);

        btnGrabarPedidoRep.setBackground(new java.awt.Color(51, 153, 255));
        btnGrabarPedidoRep.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnGrabarPedidoRep.setForeground(new java.awt.Color(255, 255, 255));
        btnGrabarPedidoRep.setText( "<html><body>Grabar<br>Reposición</body></html>");
        btnGrabarPedidoRep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnGrabarPedidoRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarPedidoRepActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText( "<html><body>Salir</body></html>");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 204, 255), new java.awt.Color(0, 102, 204), new java.awt.Color(0, 153, 153), new java.awt.Color(0, 102, 153)));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlActionButtonsLayout = new javax.swing.GroupLayout(pnlActionButtons);
        pnlActionButtons.setLayout(pnlActionButtonsLayout);
        pnlActionButtonsLayout.setHorizontalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActionButtonsLayout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(btnGrabarPedidoRep)
                .addGap(10, 10, 10)
                .addComponent(btnSalir)
                .addContainerGap(382, Short.MAX_VALUE))
        );
        pnlActionButtonsLayout.setVerticalGroup(
            pnlActionButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGrabarPedidoRep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlActionButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFoot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                    .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHead, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(pnlFoot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlActionButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    final void initListaProductos() {
        model =  new ModeloTablaSimple(new ArrayList(), ModeloTablaSimple.REPOSICION);
        tblDetalle.setModel(model);
        Helper.ajustarSoloAnchoColumnas(tblDetalle, ModeloTablaSimple.anchoColumnasPedidoReposicion);
    }
    
    void getListaPedidoRepFinal() {        
        double dblCantidadReposCalc  = 0,
               dblCantidadReposSolic = 0;
        String strInPedidoReposicion = "";
        
        for (int i = 0; i < arrayListProdReponer.size(); i++) {
            ProductoLocal productoLocal = arrayListProdReponer.get(i);
            
            strInPedidoReposicion = productoLocal.getInProductoReponer();
            dblCantidadReposCalc =  productoLocal.getCaStockReponerCalcula();
            
            if (productoLocal.getCaStockReponer()==0)
                dblCantidadReposSolic = -1;
            else
                dblCantidadReposSolic = productoLocal.getCaStockReponer();
            
            if ((dblCantidadReposCalc > ((dblCantidadReposSolic == 0) ? dblCantidadReposCalc : 0) || dblCantidadReposSolic > 0) && strInPedidoReposicion.equalsIgnoreCase(""))
                model.agregar(productoLocal);                
        }
                        
        model.fireTableDataChanged();        
        tblDetalle.repaint();
        AtuxUtility.setearPrimerRegistro(tblDetalle, txtDeProducto, 1);
        AtuxUtility.moveFocus(txtDeProducto);
        prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
        muestraDetalleProd(prodLocal);
    }

    private void tblDetalleKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
            ingresaCantPedida(prodLocal,tblDetalle.getSelectedRow());
            evt.consume();
            muestraDetalleProd(prodLocal);
        }
//        else if (tblDetalle != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
//            AtuxGridUtils.aceptarTeclaPresionada(evt, tblDetalle, null, 1);
//            evt.consume();
//        }
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

    private void chkOrdenPorCalculadaActionPerformed(ActionEvent evt) {
        ArrayList arrayListProdReponerAux = new ArrayList();

        for (int i = 0; i < model.getRowCount(); i++) {
            ProductoLocal productoLocal = ((ProductoLocal) model.getFila(i));
            arrayListProdReponerAux.add(productoLocal);
        }

        Collections.sort(arrayListProdReponerAux, new Comparator<ProductoLocal>() {
            public int compare(ProductoLocal o1, ProductoLocal o2) {
                return o2.getCaStockReponerCalcula().compareTo(o1.getCaStockReponerCalcula());
            }
        });

        model =  new ModeloTablaSimple(arrayListProdReponerAux, ModeloTablaSimple.REPOSICION);
        model.fireTableDataChanged();
        tblDetalle.setModel(model);
        Helper.ajustarSoloAnchoColumnas(tblDetalle, ModeloTablaSimple.anchoColumnasPedidoReposicion);
        tblDetalle.repaint();
        AtuxUtility.setearPrimerRegistro(tblDetalle, txtDeProducto, 1);
        AtuxUtility.moveFocus(txtDeProducto);
    }

    private void chkOrdenPorFaltaCeroActionPerformed(ActionEvent evt) {
        ArrayList arrayListProdReponerAux = new ArrayList();

        for (int i = 0; i < model.getRowCount(); i++) {
            ProductoLocal productoLocal = ((ProductoLocal) model.getFila(i));
            arrayListProdReponerAux.add(productoLocal);
        }

        Collections.sort(arrayListProdReponerAux, new Comparator<ProductoLocal>() {
            public int compare(ProductoLocal o1, ProductoLocal o2) {
                return o2.getCaProdNoAtendido().compareTo(o1.getCaProdNoAtendido());
            }
        });

        model =  new ModeloTablaSimple(arrayListProdReponerAux, ModeloTablaSimple.REPOSICION);
        model.fireTableDataChanged();
        tblDetalle.setModel(model);
        Helper.ajustarSoloAnchoColumnas(tblDetalle, ModeloTablaSimple.anchoColumnasPedidoReposicion);
        tblDetalle.repaint();
        AtuxUtility.setearPrimerRegistro(tblDetalle, txtDeProducto, 1);
        AtuxUtility.moveFocus(txtDeProducto);
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtDeProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeProductoKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
            ingresaCantPedida(prodLocal,tblDetalle.getSelectedRow());
            evt.consume();
            muestraDetalleProd(prodLocal);
        }
//        else if (tblDetalle != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
//            AtuxGridUtils.aceptarTeclaPresionada(evt, tblDetalle, txtDeProducto, 1);
//            evt.consume();
//        }

    }//GEN-LAST:event_txtDeProductoKeyPressed

    private void txtDeProductokeyReleased(KeyEvent evt) {
        prodLocal =(ProductoLocal)model.getFila(tblDetalle.getSelectedRow());
        if (tblDetalle != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            AtuxGridUtils.aceptarTeclaPresionada(evt, tblDetalle, txtDeProducto, 1);
            evt.consume();
        }
        muestraDetalleProd(prodLocal);
    }

    private void btnGrabarPedidoRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarPedidoRepActionPerformed
        generarPedidoRep();
    }//GEN-LAST:event_btnGrabarPedidoRepActionPerformed
    
    void muestraDetalleProd(ProductoLocal prodLocal) {                  
        try {                            
                txtTransito.setText(AtuxUtility.formatNumber(AtuxSearch.obtienePRODUCTOTRANSITO(prodLocal.getCoProducto()), ",##0"));
                txtMinExhibicion.setText(String.valueOf(prodLocal.getCaMinProdExhibicion()));
                txtParamMinDias.setText(String.valueOf(prodLocal.getNuMinDiasReposicion()));
                txtParamMaxDias.setText(String.valueOf(prodLocal.getNuMaxDiasReposicion()));                
                txtParamNroDias.setText(String.valueOf(prodLocal.getNuDiasRotacionPromedio()));                
                txtVentasDias.setText(String.valueOf(AtuxSearch.obtieneROTACIONPRODUCTO(prodLocal.getNuDiasRotacionPromedio(),prodLocal.getCoProducto())));
                
                outParameters_mes = AtuxSearch.obtieneROTACIONPRODUCTOMES(prodLocal.getCoProducto());
                txtMes01.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(0)).trim()), "###,##0.00"));
                txtMes02.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(1)).trim()), "###,##0.00"));
                txtMes03.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(2)).trim()), "###,##0.00"));
                txtMes04.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(((String) ((ArrayList) outParameters_mes.get(0)).get(3)).trim()), "###,##0.00"));
            
        } catch (SQLException ex) {
            logger.error("Error al mostrar el detalle del producto muestraDetalleProd" + ex.getMessage());
        }
    }

    synchronized void generarPedidoRep() {
        if (JOptionPane.showConfirmDialog(this,
                "Seguro de Generar el Pedido de Reposición ?",
                "Mensaje del Sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                BaseConexion.closeConnection();
                AtuxSearch.grabarCabeceraPedidoReposicionNoCommit(Integer.parseInt(txtParamNroDias.getText()), Integer.parseInt(txtParamMinDias.getText()), Integer.parseInt(txtParamMaxDias.getText()));
                actualizaIndicador();
                AtuxUtility.aceptarTransaccion();
                JOptionPane.showMessageDialog(this,
                        "Número de Pedido Reposición Generado: " + AtuxVariables.vNumeroPedidoProd,
                        "Mensaje del Sistema",
                        JOptionPane.INFORMATION_MESSAGE);
                iPedidoRep.vPedidoOK = true;
                iPedidoRep.requestFocus();
                this.setVisible(false);
                this.dispose();
            } catch (Throwable sqlerr) {
                AtuxUtility.liberarTransaccion();
                AtuxUtility.showMessage(this,
                        "Error en el envio del Pedido de Reposición !!!" +
                                "\n" + sqlerr.getMessage(), null);
                sqlerr.printStackTrace();
            }            
        }
    }

    void actualizaIndicador() throws SQLException {
        try {
            String indicador = AtuxSearch.devuelveEstadoIndicador();
            if (indicador.equalsIgnoreCase("S")) {
                AtuxSearch.actualizaIndicadorPedLocalNoCommit();
            }
        } catch (SQLException sqlerr) {
            logger.error("Error al actualizar el indicador de Pedido de Reposición del Local - " + sqlerr.getErrorCode() + sqlerr.toString());            
            throw sqlerr;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrabarPedidoRep;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPaneDet;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblMaxDiasT;
    private javax.swing.JLabel lblMes01;
    private javax.swing.JLabel lblMes02;
    private javax.swing.JLabel lblMes03;
    private javax.swing.JLabel lblMes04;
    private javax.swing.JLabel lblMinDiasT;
    private javax.swing.JLabel lblMinExhibicion;
    private javax.swing.JLabel lblNroDias;
    private javax.swing.JLabel lblTransitoT;
    private javax.swing.JLabel lblVentasDias;
    private elaprendiz.gui.panel.PanelImage panelFondo;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFoot;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JTable tblDetalle;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton chkOrdenPorCalculada;
    private javax.swing.JRadioButton chkOrdenOriginal;
    private javax.swing.JRadioButton chkOrdenPorFaltaCero;
    private elaprendiz.gui.textField.TextField txtDeProducto;
    private elaprendiz.gui.textField.TextField txtMes01;
    private elaprendiz.gui.textField.TextField txtMes02;
    private elaprendiz.gui.textField.TextField txtMes03;
    private elaprendiz.gui.textField.TextField txtMes04;
    private elaprendiz.gui.textField.TextField txtMinExhibicion;
    private elaprendiz.gui.textField.TextField txtParamMaxDias;
    private elaprendiz.gui.textField.TextField txtParamMinDias;
    private elaprendiz.gui.textField.TextField txtParamNroDias;
    private elaprendiz.gui.textField.TextField txtTransito;
    private elaprendiz.gui.textField.TextField txtVentasDias;
    // End of variables declaration//GEN-END:variables
}
