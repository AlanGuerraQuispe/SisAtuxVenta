package atux.vistas.venta.caja;

import atux.config.AppConfig;
import atux.controllers.CClienteDireccion;
import atux.controllers.CMoneda;
import atux.controllers.CPedidoVenta;
import atux.controllers.CSimpleModelo;
import atux.handlers.DonacionService;
import atux.modelbd.*;
import atux.presenter.IngresarDonacionPst;
import atux.util.AtuxLengthText;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.common.ConstantsTables;
import atux.util.common.AtuxTableModel;
import atux.util.common.cashdrawer.EckerdCashDrawerException;
import atux.util.common.cashdrawer.EckerdCashDrawerManager;
import atux.util.print.builder.*;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import atux.vistas.buscar.JBuscarCliente;
import com.atux.bean.donacion.IngresarDonacion;
import com.atux.bean.donacion.IngresarDonacionDetalle;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.core.util.StringUtils;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionManager;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author user
 */
public final class IPagoPedido extends javax.swing.JDialog {
    private DefaultComboBoxModel mMoneda;
    private DefaultComboBoxModel mFormaPago;

    private CMoneda controllerMoneda;
    private CSimpleModelo controllerFormaPago;

    PedidoVenta pedido;
    ArrayList arrayPagos = new ArrayList();
    boolean considerarRedondeo = AtuxVariables.vCobroConRedondeo;
    private String redondeoPedido = "0.0";
    public AtuxTableModel tableModelPagos;
    private final Log logger = LogFactory.getLog(getClass());

    Frame myParentFrame;

    /**
     * Almacena el monto de la Forma de Pago realizada
     */
    double monto = 0;

    String inTarjetaBancaria = "N";
    String inDetalle = "N";
    String inOrigenFormaPago = "";
    double coPago = 0.00;
    String inImprimeMontoExacto = "N";
    String inFidelizacion = "N";
    int contadorComprobantes = 0;

    private String tarjetaNumero = "";
    private String tarjetaVencimiento = "";
    private String tarjetaPaterno = "";
    private String tarjetaMaterno = "";
    private String tarjetaNombres = "";
    private String tarjetaTipoDocumento = "";
    private String tarjetaNumeroDocumento = "";
    private String tarjetaCuotas = "";
    private String fechaComprobanteRepetido = "";
    private String tiComprobanteRepetido = "";
    private String comprobanteConsultar = "";

    private String idTransaccion = "";
    private String codComercio = "";
    private String nuTerminal = "";
    private String nuLote = "";
    private String nuReferencia = "";
    private String nuAp = "";
    private String refrendo = "";
    private String voucher = "";
    private String nuBin = "";
    private boolean isDebito;

    private String inAperturaGaveta = "N";
    private static final int COLUMN_IN_APERTURA_GAVETA = 19;

    private List<IngresarDonacionDetalle> donacionList = new ArrayList<IngresarDonacionDetalle>();


    public IPagoPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setDefaultCloseOperation(0);
        AtuxUtility.centrarVentana((JDialog) this);
    }

    public IPagoPedido(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        myParentFrame = frame;
        initComponents();
        initPagos();
        txtMontoPagado.setDocument(new AtuxLengthText(10));
        this.setDefaultCloseOperation(0);
        AtuxUtility.centrarVentana((JDialog) this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCabCobro = new elaprendiz.gui.panel.PanelRoundTranslucido();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSoles = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDolares = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        panelBodyCobro = new elaprendiz.gui.panel.PanelTranslucido();
        jLabel6 = new javax.swing.JLabel();
        txtMontoPagado = new elaprendiz.gui.textField.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbMoneda = new elaprendiz.gui.comboBox.ComboBoxRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetallePago = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotalPagado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblVuelto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbFormaPago = new elaprendiz.gui.comboBox.ComboBoxRectIcon();
        bntAdicionar = new javax.swing.JToggleButton();
        panelFootCobro = new elaprendiz.gui.panel.PanelRoundTranslucido();
        jLabel11 = new javax.swing.JLabel();
        lblComprobantes = new javax.swing.JLabel();
        lblComprobantesT = new javax.swing.JLabel();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();
        bntEliminarFormaPago = new elaprendiz.gui.button.ButtonRect();

        setBackground(new java.awt.Color(0, 102, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }

            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(500, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TOTAL VENTA :");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("S/.");

        lblSoles.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblSoles.setForeground(new java.awt.Color(255, 255, 0));
        lblSoles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSoles.setText("0.00");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("US$");

        lblDolares.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblDolares.setForeground(new java.awt.Color(255, 255, 0));
        lblDolares.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDolares.setText("0.00");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tipo cambio :");

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblTipoCambio.setForeground(new java.awt.Color(255, 255, 0));
        lblTipoCambio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoCambio.setText("0.00");

        javax.swing.GroupLayout panelCabCobroLayout = new javax.swing.GroupLayout(panelCabCobro);
        panelCabCobro.setLayout(panelCabCobroLayout);
        panelCabCobroLayout.setHorizontalGroup(
                panelCabCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabCobroLayout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        panelCabCobroLayout.setVerticalGroup(
                panelCabCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabCobroLayout.createSequentialGroup()
                                .addGroup(panelCabCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabCobroLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(panelCabCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                                        .addComponent(jLabel2)))
                                        .addGroup(panelCabCobroLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(panelCabCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(lblDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14)
                                                        .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );

        panelBodyCobro.setPreferredSize(new java.awt.Dimension(400, 295));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Monto Pagado :");

        txtMontoPagado.setText("0.00");
        txtMontoPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoPagadoKeyPressed(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoPagadoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Forma Pago : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Moneda :");

        cmbMoneda.setForeground(new java.awt.Color(204, 0, 0));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Detalle de los pagos :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 0, 0))); // NOI18N
        jScrollPane1.setViewportView(tblDetallePago);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pagado :");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("S/.");

        lblTotalPagado.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblTotalPagado.setForeground(new java.awt.Color(255, 255, 0));
        lblTotalPagado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalPagado.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SALDO A PAGAR :");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("S/.");

        lblSaldo.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblSaldo.setForeground(new java.awt.Color(255, 255, 0));
        lblSaldo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSaldo.setText("0.00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("VUELTO:");

        lblVuelto.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblVuelto.setForeground(new java.awt.Color(255, 255, 0));
        lblVuelto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVuelto.setText("0.00");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("S/.");

        cmbFormaPago.setPreferredSize(new java.awt.Dimension(420, 22));
        cmbFormaPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFormaPagoItemStateChanged(evt);
            }
        });
        cmbFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFormaPagoKeyReleased(evt);
            }
        });

        bntAdicionar.setText("Adicionar");
        bntAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBodyCobroLayout = new javax.swing.GroupLayout(panelBodyCobro);
        panelBodyCobro.setLayout(panelBodyCobroLayout);
        panelBodyCobroLayout.setHorizontalGroup(
                panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel8)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblTotalPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(91, 91, 91)
                                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                                                .addGap(55, 55, 55))
                                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(bntAdicionar)))))
                                .addContainerGap())
        );
        panelBodyCobroLayout.setVerticalGroup(
                panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBodyCobroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bntAdicionar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBodyCobroLayout.createSequentialGroup()
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel10)
                                                                .addComponent(lblSaldo))
                                                        .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel13)
                                                        .addComponent(jLabel12)))
                                        .addGroup(panelBodyCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel8)
                                                .addComponent(lblTotalPagado)
                                                .addComponent(jLabel7)))
                                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Observaciones :");

        lblComprobantes.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblComprobantes.setForeground(new java.awt.Color(255, 255, 255));

        lblComprobantesT.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblComprobantesT.setForeground(new java.awt.Color(255, 255, 255));
        lblComprobantesT.setText("Comprobantes de pago que se generarán :");

        javax.swing.GroupLayout panelFootCobroLayout = new javax.swing.GroupLayout(panelFootCobro);
        panelFootCobro.setLayout(panelFootCobroLayout);
        panelFootCobroLayout.setHorizontalGroup(
                panelFootCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFootCobroLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelFootCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblComprobantes)
                                        .addComponent(lblComprobantesT))
                                .addContainerGap(145, Short.MAX_VALUE))
        );
        panelFootCobroLayout.setVerticalGroup(
                panelFootCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFootCobroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addGap(54, 54, 54))
                        .addGroup(panelFootCobroLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(panelFootCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblComprobantesT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelFootCobroLayout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(lblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Aceptar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
            }
        });

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        bntEliminarFormaPago.setBackground(new java.awt.Color(51, 153, 255));
        bntEliminarFormaPago.setText("Eliminar F.Pago");
        bntEliminarFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarFormaPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
                panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelImage1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(bntEliminarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                                .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelCabCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(panelImage1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(panelFootCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelBodyCobro, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
                                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
                panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(panelCabCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(panelBodyCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelFootCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bntEliminarFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void initPagos() {
        tableModelPagos = new AtuxTableModel(ConstantsTables.columnsPagos, ConstantsTables.defaultValuesPagos, 0);
        AtuxUtility.initSimpleList(tblDetallePago, tableModelPagos, ConstantsTables.columnsPagos);
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            AtuxUtility.setCajaTurno();
            windowOpenedIntern();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    public void windowOpenedIntern() {
        controllerFormaPago = new CSimpleModelo();
        mFormaPago = new DefaultComboBoxModel(controllerFormaPago.getFormasPago().toArray());
        this.cmbFormaPago.setModel(mFormaPago);
        this.cmbFormaPago.setSelectedIndex(0);

        controllerMoneda = new CMoneda();
        mMoneda = new DefaultComboBoxModel(controllerMoneda.getRegistros().toArray());
        this.cmbMoneda.setModel(mMoneda);
        this.cmbMoneda.setSelectedIndex(0);

        lblSoles.setText(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta()));
        logger.info("lblSoles <" + lblSoles.getText() + ">");
        lblDolares.setText(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta() / AtuxVariables.vTipoCambio));
        lblTipoCambio.setText(AtuxUtility.formatNumber(AtuxVariables.vTipoCambio));
        logger.info(" AtuxVariables.vTipoCambio <" + AtuxVariables.vTipoCambio + ">");

        if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_FACTURA) || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_FACTURA))
            lblComprobantesT.setText("FACTURA (para Cliente)");
        else
            lblComprobantesT.setText("BOLETA (para Cliente)");
        try {
            if (!(AtuxSearch.setImpresorasCaja(this))) {
                closeWindow(false);
            }

            if (AppCtx.instance().isShowDialog())
                solicitarDonaciones();

            calcularMontoPorDonacion();

        } catch (SQLException ex) {
            Logger.getLogger(IPagoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void calcularMontoPorDonacion() {
        BigDecimal totalDonacion = ListUtils.sum(donacionList, "monto", null);
        logger.info("Total de donaciones recibidas <" + donacionList.size() + "> totalDonacion<" + totalDonacion + ">");

        if (donacionList.size() > 0) {
            lblSoles.setText(AtuxUtility.formatNumber(pedido.addDonacion(totalDonacion)));
            lblDolares.setText(AtuxUtility.formatNumber(pedido.addDonacion(totalDonacion) / AtuxVariables.vTipoCambio));
            redondeoPedido = AtuxUtility.formatNumber(AtuxUtility.getRedondeo(pedido.addDonacion(totalDonacion)));
            logger.info("con donacion  lblSoles <" + lblSoles.getText() + "> redondeoPedido<" + redondeoPedido + ">");
        }
        cmbFormaPago.requestFocus();

    }

    private void solicitarDonaciones() {
        DonacionService donacionProvider = Application.instance().getBean(DonacionService.class);
        APDD apdd = Application.instance().getBean(APDD.class);
        if (apdd.INSTITUCIONES.getRows().size() == 0) {
            logger.info("No existen donaciones activas");
            return;
        }
        double montoSugerida = donacionProvider.calcularMontoSugerido(pedido, AtuxVariables.vRedondearTo);
        IngresarDonacionPst presenter = PstMgr.instance().getPst(IngresarDonacionPst.class);
        Application.instance().autowireFields(presenter);
        IngresarDonacion bean = new IngresarDonacion();
        bean.setMonto(new BigDecimal(montoSugerida));
        bean.setCoInstitucion((String)((DropDownRowImpl)apdd.INSTITUCIONES.getRows().get(0)).getValue());
        presenter.setBackBean(bean);
        presenter.init();
        if (!ActionManager.instance().cancelActionWasExecuted()) {
            donacionList = presenter.getDonacionList();
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JOptionPane.showMessageDialog(this, "Usted está¡ cerrando la ventana de manera incorrecta !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_formWindowClosing

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        clearDescuentoTarjeta();
        AtuxUtility.liberarTransaccion();
        clearData();
        pedido.setCoClienteLocal(null);
        pedido.setNuRucCliente(null);
        pedido.setDeDireccionCliente(null);
        pedido.setNoImpresoComprobante(null);
        closeWindow(false);
    }//GEN-LAST:event_bntSalirActionPerformed

    @Override
    public void requestFocus() {
        cmbFormaPago.requestFocus();
    }

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
        grabar();
    }//GEN-LAST:event_bntAceptarActionPerformed

    private void cmbFormaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFormaPagoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            doEnterCmbFormaPago();
        }
    }//GEN-LAST:event_cmbFormaPagoKeyReleased

    private void bntAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAdicionarActionPerformed
        try {
            logger.info("Método: btnAdicionar_actionPerformed. txtMontoPagado: " + AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(txtMontoPagado.getText().trim())));

            String codigoFormaPago = ((FormaPago) cmbFormaPago.getSelectedItem()).getCoFormaPago();

            if (codigoFormaPago.trim().length() == 0) {
                AtuxUtility.showInformationMessage(this, "No ha seleccionado de manera correcta la forma de pago " + (String) cmbFormaPago.getSelectedItem(), null);
                return;
            }

            if (!AtuxUtility.isDouble(String.valueOf(AtuxUtility.getDecimalNumber(txtMontoPagado.getText().trim())))) {
                txtMontoPagado.setText("");
            }

            if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_EFECTIVO)) {
                if (!AtuxUtility.validaCentimos(txtMontoPagado)) {
                    AtuxUtility.showMessage(this, "Monto Ingresado no es Válido Para pagos en Efectivo. Verifique !!!", txtMontoPagado);
                    return;
                }
            }

            String deFormaPago = ((FormaPago) cmbFormaPago.getSelectedItem()).getDeCortaFormaPago();

            txtMontoPagado.setText(AtuxUtility.formatNumber(AtuxUtility.getDecimalNumber(txtMontoPagado.getText().trim())));
            cmbFormaPago.requestFocus();

            if (codigoFormaPago.equals(AtuxVariables.FORMA_PAGO_CREDITO) && StringUtils.isEmpty(pedido.getCoClienteLocal())) {

                AtuxUtility.showInformationMessage(this, "Para los pedidos a crédito debe seleccionar el cliente. Verifique !!!", null);

                JBuscarCliente buscarCliente = new JBuscarCliente(1,this,true);
                buscarCliente.setVisible(true);

                if(buscarCliente.getCliente() != null){
                    pedido.setCoClienteLocal(((Cliente)buscarCliente.getCliente()).getCoClienteLocal());

                    ClienteDireccion cliDireccion = new CClienteDireccion().getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal, pedido.getCoClienteLocal()});

                    pedido.setNuRucCliente(StringUtils.isEmpty(((Cliente) buscarCliente.getCliente()).getNuDocIdentidad())==true?"":((Cliente) buscarCliente.getCliente()).getNuDocIdentidad());
                    pedido.setDeDireccionCliente(StringUtils.isEmpty(cliDireccion.getDeDireccion())==true?"":cliDireccion.getDeDireccion());
                    pedido.setNoImpresoComprobante(StringUtils.isEmpty(((Cliente) buscarCliente.getCliente()).getDeRazonSocial())==true?"":((Cliente) buscarCliente.getCliente()).getDeRazonSocial());

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
                        AtuxSearch.updateTipoPedidoCredito(AtuxVariables.TIPO_PEDIDO_CREDITO,pedido.getNuPedido());
                    } catch (SQLException ex) {
                        logger.error("Error en updatePersonaJuridicaAPedido "+ex);
                    }
                }
            }

            if (existeSolesDolares()) {
                AtuxUtility.showWarningMessage(this, "No se pueden duplicar las Formas de Pago : EFECTIVO SOLES y EFECTIVO USD. Verifique !!!", null);
                clearData();
                return;
            }

            if (!AtuxVariables.vCobroConRedondeo) {
                if (codigoFormaPago.equals(AtuxVariables.FORMA_PAGO_EFECTIVO) || codigoFormaPago.equals(AtuxVariables.FORMA_PAGO_DOLARES)) {
                    redondeoPedido = AtuxUtility.formatNumber(pedido.getVaSaldoRedondeo());
                    considerarRedondeo = true;
                }
            }

            String codigoMoneda = ((Moneda) this.cmbMoneda.getSelectedItem()).getCoMoneda();

            Double montoPagado = AtuxUtility.getDecimalNumber(txtMontoPagado.getText().trim());

            logger.info("Método: btnAdicionar_actionPerformed. monto: " + montoPagado);

            arrayPagos.add(codigoFormaPago);
            arrayPagos.add(deFormaPago);
            arrayPagos.add(((Moneda) this.cmbMoneda.getSelectedItem()).getNombre());
            arrayPagos.add(AtuxUtility.formatNumber(montoPagado));

            if (codigoMoneda.equalsIgnoreCase(AtuxVariables.MONEDA_DOLARES)) {
                if (AtuxUtility.getDecimalNumberRedondeado(montoPagado) != AtuxUtility.getDecimalNumber(lblDolares.getText().trim())) {
                    montoPagado = AtuxUtility.getDecimalNumberRedondeado(montoPagado * AtuxVariables.vTipoCambio);
                    montoPagado += AtuxUtility.getRedondeoHaciaArriba(montoPagado);
                    arrayPagos.add(AtuxUtility.formatNumber(montoPagado));
                } else {
                    arrayPagos.add(lblSoles.getText().trim());
                }
            } else {
                arrayPagos.add(AtuxUtility.formatNumber(montoPagado));
            }

            arrayPagos.add(codigoMoneda);
            arrayPagos.add("");

            arrayPagos.add(tarjetaNumero);
            arrayPagos.add(tarjetaVencimiento);
            arrayPagos.add(tarjetaPaterno);
            arrayPagos.add(tarjetaMaterno);
            arrayPagos.add(tarjetaNombres);
            arrayPagos.add(tarjetaTipoDocumento);
            arrayPagos.add(tarjetaNumeroDocumento);
            arrayPagos.add(tarjetaCuotas);
            arrayPagos.add("N");
            arrayPagos.add(inOrigenFormaPago);
            arrayPagos.add("0");
            arrayPagos.add(inFidelizacion);
            arrayPagos.add(inAperturaGaveta);

            arrayPagos.add(idTransaccion);
            arrayPagos.add(codComercio);
            arrayPagos.add(nuTerminal);
            arrayPagos.add(nuLote);
            arrayPagos.add(nuReferencia);
            arrayPagos.add(nuAp);
            arrayPagos.add(refrendo);
            arrayPagos.add(voucher);

            limpiarDatosTarjeta();

            actualizaPagos();

        } catch (Exception ex) {
            arrayPagos = new ArrayList();
            ex.printStackTrace();
            AtuxUtility.showMessage(this, "Problemas al agregar la Forma de Pago", cmbFormaPago);
        }
    }//GEN-LAST:event_bntAdicionarActionPerformed

    private void txtMontoPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagadoKeyTyped
        AtuxUtility.admitirDigitosDecimales(txtMontoPagado, evt);
    }//GEN-LAST:event_txtMontoPagadoKeyTyped

    private void bntEliminarFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarFormaPagoActionPerformed

        if (AtuxUtility.getDecimalNumber(lblSaldo.getText().trim()) == 0.0 &&
                AtuxUtility.getDecimalNumber(((String) tblDetallePago.getValueAt(tblDetallePago.getSelectedRow(), 4)).trim()) == 0.0) {
            AtuxUtility.showMessage(this, "No se puede eliminar una Forma de Pago. Verifique !!!", null);
            return;
        }

        String nuItem = ((String) tblDetallePago.getValueAt(tblDetallePago.getSelectedRow(), 17)).trim();

        if (AtuxUtility.eliminarRegistroEnJTable(this, tableModelPagos, tblDetallePago)) {

            clearDescuentoTarjeta();

            calculaTotales();

            if (Integer.parseInt(nuItem) > 0) {
                try {
                    AtuxSearch.deleteFormaPago(nuItem);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    AtuxUtility.showMessage(this, "Error al eliminar Forma de Pago !!! - " + sqlException.getErrorCode() + " - " + sqlException.getMessage(), null);
                }
            }
        }
    }//GEN-LAST:event_bntEliminarFormaPagoActionPerformed

    private void cmbFormaPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFormaPagoItemStateChanged
        String codigoFormaPago = ((FormaPago) cmbFormaPago.getSelectedItem()).getCoFormaPago();

        if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_DOLARES)) {
            this.cmbMoneda.setSelectedIndex(1);
        }
    }//GEN-LAST:event_cmbFormaPagoItemStateChanged

    private void txtMontoPagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoPagadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            bntAdicionar.doClick();
        }
    }//GEN-LAST:event_txtMontoPagadoKeyPressed

    void actualizaPagos() {
        System.out.println("Agregando Pago: " + arrayPagos);
        if (arrayPagos.size() > 0) {
            tableModelPagos.insertRow(arrayPagos);
            tableModelPagos.fireTableDataChanged();
            tblDetallePago.repaint();

            arrayPagos = new ArrayList();
            calculaTotales();
        }
        clearData();
    }

    boolean existeSolesDolares() {
        boolean existeSolesDolares = false;
        String codigoFormaPago = ((FormaPago) cmbFormaPago.getSelectedItem()).getCoFormaPago();
        if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_EFECTIVO) ||
                codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_DOLARES)) {
            String codigo = "";

            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                codigo = ((String) tblDetallePago.getValueAt(i, 0)).trim();
                if (codigo.equalsIgnoreCase(codigoFormaPago)) {
                    existeSolesDolares = true;
                    break;
                }
            }
        }
        return existeSolesDolares;
    }

    public void doEnterCmbFormaPago() {
        cmbMoneda.setEnabled(false);

        String codigoFormaPago = ((FormaPago) cmbFormaPago.getSelectedItem()).getCoFormaPago();

        if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_EFECTIVO)) {
            if (!AtuxVariables.vCobroConRedondeo) {
                calculaTotales();
            }
            cmbMoneda.setSelectedIndex(0);

            if (lblTotalPagado.getText().trim().equalsIgnoreCase("0.00")) {
                if (AtuxVariables.vCobroConRedondeo) {
                    txtMontoPagado.setText(AtuxUtility.formatNumber(Double.parseDouble(lblSoles.getText())));
                } else {
                    txtMontoPagado.setText(AtuxUtility.formatNumber(Double.parseDouble(lblSoles.getText()) + AtuxUtility.getRedondeo(Double.parseDouble(lblSoles.getText()))));
                }
            }

            AtuxUtility.moveFocus(txtMontoPagado);
        } else if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_DOLARES)) {
            cmbMoneda.setSelectedIndex(1);
            AtuxUtility.moveFocus(txtMontoPagado);
            calculaTotales();
        } else if (codigoFormaPago.equalsIgnoreCase(AtuxVariables.FORMA_PAGO_CREDITO)) {
            if (!AtuxVariables.vCobroConRedondeo) {
                calculaTotales();
            }

            cmbMoneda.setSelectedIndex(0);

            if (lblTotalPagado.getText().trim().equalsIgnoreCase("0.00")) {
                if (AtuxVariables.vCobroConRedondeo) {
                    txtMontoPagado.setText(AtuxUtility.formatNumber(Double.parseDouble(lblSoles.getText())));
                } else {
                    txtMontoPagado.setText(AtuxUtility.formatNumber(Double.parseDouble(lblSoles.getText()) + AtuxUtility.getRedondeo(Double.parseDouble(lblSoles.getText()))));
                }
            } else {
                txtMontoPagado.setText(AtuxUtility.formatNumber(Double.parseDouble(lblSaldo.getText()) - AtuxUtility.getRedondeo(Double.parseDouble(lblTotalPagado.getText()))));
            }

            AtuxUtility.moveFocus(txtMontoPagado);
        }

    }

    public void calculaTotales() {
        double totalPagado = 0.00;
        double vuelto = 0.00;

        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            totalPagado += AtuxUtility.getDecimalNumber(((String) tblDetallePago.getValueAt(i, 4)).trim());
        }

        logger.info("   Total Pagado: " + totalPagado);

        lblTotalPagado.setText(AtuxUtility.formatNumber(totalPagado));

        double totalAPagar = 0.00;

        totalAPagar = AtuxUtility.getDecimalNumber(lblSoles.getText().trim());

        logger.info("   Total Pagado luego de Verificar la Forma de Pago y CoPago del Cliente: " + lblTotalPagado.getText());
        logger.info("   Total A Pagar: " + totalAPagar);

        if (AtuxUtility.getDecimalNumberRedondeado(totalPagado) <= totalAPagar) {
            double diferencia = 0;

            if (AtuxVariables.vCobroConRedondeo) {
                logger.info("   Se Cobra con Redondeo");

                diferencia = totalAPagar - totalPagado;
                if (diferencia < 0.05) {
                    lblSaldo.setText("0.00");
                } else {
                    lblSaldo.setText(AtuxUtility.formatNumber(diferencia));
                }

                logger.info("   Saldo: " + lblSaldo.getText() + " (" + diferencia + ")");
            } else {
                logger.info("   Se Cobra sin Redondeo");
                if (considerarRedondeo) {
                    logger.info("   Se considera el redondeo");
                    diferencia = AtuxUtility.getDecimalNumberRedondeado(totalAPagar) +
                            AtuxUtility.getDecimalNumberRedondeado(AtuxUtility.getRedondeo(totalAPagar - AtuxUtility.getDecimalNumberRedondeado(totalPagado))) -
                            AtuxUtility.getDecimalNumberRedondeado(totalPagado);

                    logger.info("   Cálculo del redondeo a disminuir: " + AtuxUtility.getDecimalNumberRedondeado(AtuxUtility.getRedondeo(totalAPagar - AtuxUtility.getDecimalNumberRedondeado(totalPagado))));
                    logger.info("   Saldo Calculado: " + diferencia);

                } else {
                    logger.info("   No se considera el redondeo");
                    diferencia = totalAPagar - totalPagado;
                    logger.info("   Saldo Calculado: " + diferencia);
                }
            }

            if (diferencia < 0) {
                diferencia = 0;
            }

            //Para corregir el caso de pago con vale fid y la diferencia a pagar sea menor a 0.05
            if (tblDetallePago.getRowCount() == 1) {
                if (((String) tblDetallePago.getValueAt(0, 1)).equals(AtuxVariables.vFormaPagoFidelizacion)) {
                    if (AtuxUtility.getDecimalNumberRedondeado(diferencia) < 0.05) {
                        logger.info("   La diferencia se pone en 0 para considerar pagos sólo con Vale FID y cuya diferencia sea menor a 0.5");
                        diferencia = 0;
                    }
                }
            } else if (tblDetallePago.getRowCount() == 2) {
                if (((String) tblDetallePago.getValueAt(1, 1)).equals(AtuxVariables.vFormaPagoFidelizacion) &&
                        ((String) tblDetallePago.getValueAt(0, 16)).trim().equalsIgnoreCase("C")) {
                    if (AtuxUtility.getDecimalNumberRedondeado(diferencia) < 0.05) {
                        logger.info("   La diferencia se pone en 0 para considerar pagos con la combinación Vale FID y Convenios cuya diferencia sea menor a 0.5");
                        diferencia = 0;
                    }
                }
            }

            lblSaldo.setText(AtuxUtility.formatNumber(diferencia));

            lblVuelto.setText("0.00");

            logger.info("   Saldo: " + lblSaldo.getText() + " (" + diferencia + ")");
            logger.info("   Vuelto: 0.00");
        } else {
            lblSaldo.setText("0.00");
            if (AtuxVariables.vCobroConRedondeo) {
                logger.info("   Se Cobra con Redondeo");
                vuelto = totalPagado - totalAPagar;
                logger.info("   Vuelto: " + vuelto);
            } else {
                logger.info("   Se Cobra sin Redondeo");

                vuelto = totalPagado - (totalAPagar + AtuxUtility.getDecimalNumberRedondeado(AtuxUtility.getRedondeo(totalAPagar)));
                double vueltoRedondeo = AtuxUtility.getRedondeo(vuelto);

                logger.info("   Vuelto: " + vuelto);
                logger.info("   Redondeo del Vuelto: " + vueltoRedondeo);

                if (vueltoRedondeo != 0) {
                    vuelto += vueltoRedondeo;
                }

                logger.info("   Vuelto (luego de restarle su redondeo): " + vuelto);
            }

            if (vuelto < 0) {
                vuelto = 0;
            }

            lblVuelto.setText(AtuxUtility.formatNumber(vuelto));

            logger.info("   Vuelto: " + lblVuelto.getText() + " (" + vuelto + ")");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private javax.swing.JToggleButton bntAdicionar;
    private elaprendiz.gui.button.ButtonRect bntEliminarFormaPago;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private elaprendiz.gui.comboBox.ComboBoxRectIcon cmbFormaPago;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbMoneda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblComprobantes;
    private javax.swing.JLabel lblComprobantesT;
    public javax.swing.JLabel lblDolares;
    private javax.swing.JLabel lblSaldo;
    public javax.swing.JLabel lblSoles;
    public javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblTotalPagado;
    private javax.swing.JLabel lblVuelto;
    private elaprendiz.gui.panel.PanelTranslucido panelBodyCobro;
    private elaprendiz.gui.panel.PanelRoundTranslucido panelCabCobro;
    private elaprendiz.gui.panel.PanelRoundTranslucido panelFootCobro;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tblDetallePago;
    private elaprendiz.gui.textField.TextField txtMontoPagado;
    // End of variables declaration//GEN-END:variables

    void clearData() {
        AtuxUtility.moveFocus(cmbFormaPago);
        txtMontoPagado.setCaretPosition(0);
        tblDetallePago.clearSelection();
        cmbFormaPago.setSelectedIndex(0);
        cmbMoneda.setEnabled(false);
        cmbMoneda.setSelectedIndex(0);
        txtMontoPagado.setText("0.00");
    }

    void closeWindow(boolean pAceptar) {
        AtuxVariables.vAceptar = pAceptar;
        if (!pAceptar) txtMontoPagado.setText("0.00");
        this.setVisible(false);
        this.dispose();
    }

    public void setPedido(PedidoVenta pedido) {
        this.pedido = pedido;
    }

    private void limpiarDatosTarjeta() {
        tarjetaNumero = "";
        tarjetaVencimiento = "";
        tarjetaPaterno = "";
        tarjetaMaterno = "";
        tarjetaNombres = "";
        tarjetaTipoDocumento = "";
        tarjetaNumeroDocumento = "";
        tarjetaCuotas = "";

        idTransaccion = "";
        nuTerminal = "";
        nuLote = "";
        nuReferencia = "";
        codComercio = "";
        nuAp = "";
        refrendo = "";
        voucher = "";
        nuBin = "";
        isDebito = false;
    }

    public void grabar() {
        logger.info("*************** Iniciando el Cálculo de Totales (grabar) *************** ");
        calculaTotales();
        logger.info("*************** Iniciando el Cálculo de Totales (grabar) *************** ");
        logger.info(" ");
        try {
            String fecPedido = AtuxUtility.getDateToString(pedido.getFePedido(), "dd/MM/yyyy");

            if (AtuxSearch.esPedidoDiaAnterior(fecPedido) && !pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_CREDITO)) { //todo se exeptuan los pedidos a crédito
                JOptionPane.showMessageDialog(this, "No se pueden cobrar Pedidos de días anteriores. Favor de anular este pedido y volver a generarlo", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Problemas al determinar si el Pedido fue generado el dï¿½a de hoy", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (retornaIndicadorComprobanteValido() == 1) {
            if (!(AtuxUtility.getDecimalNumber(lblSaldo.getText().trim()) > 0.00)) {
//                if (!validaEstadoPedidoPendiente()) { // todo en caso de credito no interesa
//                    try {
//                        AtuxSearch.liberarTransaccion();
//                    } catch (SQLException esql) {
//                        esql.printStackTrace();
//                    }
//
//                    AtuxUtility.showMessage(this, "El Pedido no se encuentra pendiente de cobro. Verifique!!!", null);
//                    return;
//                }

                AtuxVariables.vNombresFormasPago = obtieneFormasPago();
                AtuxVariables.vFormasPago = obtieneFormasPagoCodigos();

                if (!validaOrdenPagos()) {
                    return;
                }

                if (!validaMontoTarjetas()) {
                    return;
                }                               

                if (grabarDetallePago()) {
                    if(validaPagoACredito()){
                        logger.info("Pedido a crédito generado");
                    }

                    closeWindow(true);
                } else {
                    closeWindow(false);
                }
            } else {
                AtuxUtility.showMessage(this, "Todavía existe un Saldo pendiente por Cobrar. Verifique !!!", null);
            }
        } else {
            AtuxUtility.liberarTransaccion();
            JOptionPane.showMessageDialog(this, "El Sistema a detectado que el Comprobante : " + pedido.getTiComprobante() + "-" + comprobanteConsultar + "\n" + "ya fue Registrado el día : " + fechaComprobanteRepetido + "\n" + "Verifique Numeración de Comprobantes !!! ", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }

    }

    private boolean validaPagoACredito() {
         IPedidoACredito iPedidoACredito = new IPedidoACredito(this,true);
            iPedidoACredito.setPedido(pedido);
//          iPedidoACredito.bntCobrar.setEnabled(false);
//          iPedidoACredito.bntSalir.setText("Aceptar");
//          iPedidoACredito.setInactivaCabecera();
            iPedidoACredito.setVisible(true);

        if(AtuxVariables.vAceptar){
            return true;
        }

        return false;
    }

    int retornaIndicadorComprobanteValido() {
         if (AtuxVariables.vInComprobanteManual.equals("S") || pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_CREDITO)) {
            return 1;
        } else {
            int indicador = 0;
            ArrayList resultado;

            try {
                resultado = new ArrayList();

                if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA)
                        || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_BOLETA)) {

                    comprobanteConsultar = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraBoleta);
                    if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_BOLETA)) {
                        AtuxSearch.verificaComprobanteInsertarIn(comprobanteConsultar, AtuxVariables.TIPO_BOLETA, resultado);
                    } else {
                        AtuxSearch.verificaComprobanteInsertarIn(comprobanteConsultar, AtuxVariables.TIPO_TICKET_BOLETA, resultado);
                    }
                    if (resultado.size() > 0) {
                        fechaComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(1)).trim();
                        tiComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(2)).trim();
                        return indicador = 0;
                    } else {
                        indicador = 1;
                    }
                    
                    logger.info("valor Boleta :" + indicador);
                }

                if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_FACTURA)
                        || pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_TICKET_FACTURA)) {

                    comprobanteConsultar = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraFactura);
                    if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_FACTURA)) {
                        AtuxSearch.verificaComprobanteInsertarIn(comprobanteConsultar, AtuxVariables.TIPO_FACTURA, resultado);
                    } else {
                        AtuxSearch.verificaComprobanteInsertarIn(comprobanteConsultar, AtuxVariables.TIPO_TICKET_FACTURA, resultado);
                    }
                    if (resultado.size() > 0) {
                        fechaComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(1)).trim();
                        tiComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(2)).trim();
                        return indicador = 0;
                    } else {
                        indicador = 1;
                    }
                    logger.info("valor Factura :" + indicador);
                }

                if (pedido.getTiComprobante().equalsIgnoreCase(AtuxVariables.TIPO_GUIA)) {

                    comprobanteConsultar = AtuxSearch.getNumeracionComprobante(AtuxVariables.vNuImpresoraGuia);
                    AtuxSearch.verificaComprobanteInsertarIn(comprobanteConsultar, AtuxVariables.TIPO_GUIA, resultado);
                    if (resultado.size() > 0) {
                        fechaComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(1)).trim();
                        tiComprobanteRepetido = ((String) ((ArrayList) resultado.get(0)).get(2)).trim();
                        return indicador = 0;
                    } else {
                        indicador = 1;
                    }
                    logger.info("valor Factura :" + indicador);

                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener Relacion de Formas de Pago !!! - " + sqlException.getErrorCode() + " - " + sqlException.getMessage(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
            }
            return indicador;
        }
    }

    boolean validaEstadoPedidoPendiente() {
        boolean ok = false;
        String estado = "";
        try {
            estado = AtuxDBUtility.getValueAt("VTTC_PEDIDO_VENTA", "ES_PEDIDO_VENTA",
                    "CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "' AND " +
                            "CO_LOCAL    = '" + AtuxVariables.vCodigoLocal + "' AND " +
                            "NU_PEDIDO   = '" + AtuxVariables.vNumeroPedido + "'");
        } catch (SQLException sql) {
            sql.printStackTrace();
            AtuxUtility.showMessage(this, "Error al obtener estado del Pedido !!! - " + sql.getErrorCode() + " - " + sql.getMessage(), null);
            return ok;
        }

        if (estado.trim().equalsIgnoreCase("P")) {
            ok = true;
            return ok;
        }
        return ok;
    }

    String obtieneFormasPago() {
        String strFormasPago = "";
        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            if (strFormasPago.length() > 0) {
                strFormasPago = strFormasPago + ", ";
            }
            strFormasPago = strFormasPago + ((String) tblDetallePago.getValueAt(i, 1)).trim();
        }
        return strFormasPago;
    }

    ArrayList obtieneFormasPagoCodigos() {
        ArrayList formasPago = new ArrayList();
        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            formasPago.add(((String) tblDetallePago.getValueAt(i, 0)).trim());
        }
        return formasPago;
    }
        
    private boolean validaOrdenPagos() {
        int posicion = -1;
        boolean validacionEfectivo = true;
        boolean validacionDolares = true;

        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            if (((String) tblDetallePago.getValueAt(i, 0)).equals(AtuxVariables.FORMA_PAGO_EFECTIVO)) {
                posicion = i;
            }
        }

        if (posicion != -1) {
            for (int i = 0; i < posicion; i++) {
                validacionEfectivo = validacionEfectivo && validaItemOrdenFormaPago(i, AtuxVariables.FORMA_PAGO_DOLARES);
            }
        }

        for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
            if (((String) tblDetallePago.getValueAt(i, 0)).equals(AtuxVariables.FORMA_PAGO_DOLARES)) {
                posicion = i;
            }
        }

        if (posicion != -1) {
            for (int i = 0; i < posicion; i++) {
                validacionDolares = validacionDolares && validaItemOrdenFormaPago(i, AtuxVariables.FORMA_PAGO_EFECTIVO);
            }
        }

        if (!(validacionEfectivo && validacionDolares)) {
            AtuxUtility.showMessage(this, "Las Formas de Pago en Efectivo deben estar antes que los Pagos con Tarjeta", null);
        }

        return validacionEfectivo && validacionDolares;
    }

    private boolean validaItemOrdenFormaPago(int pIndice, String pCodFormaPagoEfectivo) {
        if (((String) tblDetallePago.getValueAt(pIndice, 16)).trim().equalsIgnoreCase("C")) {
            return true;
        }

        if (((String) tblDetallePago.getValueAt(pIndice, 0)).trim().equals(pCodFormaPagoEfectivo)) {
            return true;
        }

        if (((String) tblDetallePago.getValueAt(pIndice, 1)).trim().equalsIgnoreCase("COMPROBANTE")) {
            return true;
        }

        return false;
    }

    private boolean validaMontoTarjetas() {
        double totalAPagar = AtuxUtility.getDecimalNumber(lblSoles.getText());
        double totalTarjetas = 0.00;
        String esTarjeta = "";
        String codFormaPago = "";

        try {
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                codFormaPago = (String) tblDetallePago.getValueAt(i, 0);
                if (codFormaPago.equals(AtuxVariables.FORMA_PAGO_EFECTIVO) || codFormaPago.equals(AtuxVariables.FORMA_PAGO_DOLARES)) {
                    continue;
                }

                esTarjeta = AtuxDBUtility.getValueAt("VTTR_FORMA_PAGO", "NVL(IN_TARJETA_BANCARIA, 'N')", "CO_FORMA_PAGO = '" + codFormaPago + "'");

                if (esTarjeta.equalsIgnoreCase("S") || ((String) tblDetallePago.getValueAt(i, 1)).equalsIgnoreCase("COMPROBANTE")) {
                    totalTarjetas += AtuxUtility.getDecimalNumber((String) tblDetallePago.getValueAt(i, 4));
                } else if (((String) tblDetallePago.getValueAt(i, 16)).trim().equalsIgnoreCase("C")) {
                    totalTarjetas += AtuxUtility.getDecimalNumber((String) tblDetallePago.getValueAt(i, 4));
                }
            }

            if (AtuxUtility.getDecimalNumberRedondeado(totalTarjetas) > totalAPagar) {
                AtuxUtility.showMessage(this, "El total pagado con las Tarjetas o Comprobantes no puede ser mayor al total a pagar", null);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    boolean grabarDetallePago() {
        try {
            double vuelto = 0.00;
            if (AtuxSearch.getInCajaTurnoAbierta(AtuxVariables.vNuCaja, AtuxVariables.vNuTurno).equalsIgnoreCase("S")) {
                String fechaSistema = AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA);
                String fechaModCaja = AtuxSearch.getFeModCajaPago(AtuxVariables.vNuCaja, AtuxVariables.vNuTurno);
                if (fechaModCaja.trim().length() > 0 && !(fechaModCaja.substring(0, 5).equalsIgnoreCase(fechaSistema.substring(0, 5)))) {
                    AtuxUtility.liberarTransaccion();
                    AtuxUtility.showMessage(this, "La Fecha actual no coincide con la Apertura. No olvide CERRAR caja para empezar NUEVO DIA.", null);
                    return false;
                }
            } else {
                AtuxUtility.liberarTransaccion();
                AtuxUtility.showMessage(this, "La Caja relacionada al Usuario no ha sido Aperturada. Verifique!!!", null);
                return false;
            }

            double montoTotal;
            int nuItem = 0;
            boolean existeConvenio = false;
            double montoSaldoCredito = pedido.getVaTotalPrecioVenta();

            logger.info("********** Inicio: Grabando el Detalle de Formas de Pago ***********");
            for (int i = 0; i < tblDetallePago.getRowCount(); i++) {
                nuItem += 1;
                monto = AtuxUtility.getDecimalNumber(((String) tblDetallePago.getValueAt(i, 3)).trim());
                montoTotal = AtuxUtility.getDecimalNumber(((String) tblDetallePago.getValueAt(i, 4)).trim());

                logger.info("   Item: " + nuItem + "  Monto: " + monto + "   Monto Total: " + montoTotal);

                if (((String) tblDetallePago.getValueAt(i, 0)).trim().length() > 0) {
                    if (((String) tblDetallePago.getValueAt(i, 16)).trim().equalsIgnoreCase("C")) {
                        existeConvenio = true;
                    }

                    logger.info("   Es una Forma de Pago de Convenio?: " + existeConvenio);
                    logger.info("   El Registro no tiene Número de Item: " + (Integer.parseInt(((String) tblDetallePago.getValueAt(i, 17)).trim()) == 0));

                    if (Integer.parseInt(((String) tblDetallePago.getValueAt(i, 17)).trim()) == 0) {
                        if (i == (tblDetallePago.getRowCount() - 1)) {
                            vuelto = AtuxUtility.getDecimalNumber(lblVuelto.getText().trim());

                        } else {
                            vuelto = 0.00;
                        }

                        logger.info("  Datos a Grabar : ");
                        logger.info("     - Cod Pago  : " + ((String) tblDetallePago.getValueAt(i, 0)).trim());
                        logger.info("     - Monto     : " + monto);
                        logger.info("     - Cod Moneda: " + ((String) tblDetallePago.getValueAt(i, 5)).trim());
                        logger.info("     - Total Pago: " + montoTotal);
                        logger.info("     - Vuelto    : " + vuelto);

                        String pCodigoFormaPago = ((String) tblDetallePago.getValueAt(i, 0)).trim();
                        String pImPago = String.valueOf(monto);
                        String pCoMoneda = ((String) tblDetallePago.getValueAt(i, 5)).trim();
                        String pImTotalPago = String.valueOf(montoTotal);
                        String pVuelto = String.valueOf(vuelto);
                        String pNuTarjeta = ((String) tblDetallePago.getValueAt(i, 7)).trim();
                        String pFeVenceTarjeta = ((String) tblDetallePago.getValueAt(i, 8)).trim();
                        String pPaternoTitular = ((String) tblDetallePago.getValueAt(i, 9)).trim();
                        String pMaternoTitular = ((String) tblDetallePago.getValueAt(i, 10)).trim();
                        String pNombreTitular = ((String) tblDetallePago.getValueAt(i, 11)).trim();
                        String pTiDocIdentidad = ((String) tblDetallePago.getValueAt(i, 12)).trim();
                        String pNuDocIdentidad = ((String) tblDetallePago.getValueAt(i, 13)).trim();
                        String pCaCuotaPagoTarjeta = ((String) tblDetallePago.getValueAt(i, 14)).trim();
                        String pInPagoDiferido = ((String) tblDetallePago.getValueAt(i, 15)).trim();
                        String pIdTransaccion = ((String) tblDetallePago.getValueAt(i, 20)).trim();
                        String pCodComercio = ((String) tblDetallePago.getValueAt(i, 21)).trim();
                        String pNuTerminal = ((String) tblDetallePago.getValueAt(i, 22)).trim();
                        String pNuLote = ((String) tblDetallePago.getValueAt(i, 23)).trim();
                        String pNuReferencia = ((String) tblDetallePago.getValueAt(i, 24)).trim();
                        String pAprobacion = ((String) tblDetallePago.getValueAt(i, 25)).trim();

                        if (!pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_CREDITO)) {

                            AtuxSearch.insertaPagoPedido(nuItem,
                                    pCodigoFormaPago,
                                    pImPago,
                                    pCoMoneda,
                                    pImTotalPago,
                                    pVuelto,
                                    pNuTarjeta,
                                    pFeVenceTarjeta,
                                    pPaternoTitular,
                                    pMaternoTitular,
                                    pNombreTitular,
                                    pTiDocIdentidad,
                                    pNuDocIdentidad,
                                    pCaCuotaPagoTarjeta,
                                    pInPagoDiferido,
                                    pIdTransaccion,
                                    pCodComercio,
                                    pNuTerminal,
                                    pNuLote,
                                    pNuReferencia,
                                    pAprobacion
                            );
                        } else {// Para cobros de pedido a crédito
                            CPedidoVenta cPedidoVenta = new CPedidoVenta();
                            pedido.setdPcred(new FormaPagoPedidoCredito());
                            pedido.getdPcred().setCoCompania(pedido.getCoCompania());
                            pedido.getdPcred().setCoLocal(pedido.getCoLocal());
                            pedido.getdPcred().setNuPedido(pedido.getNuPedido());
                            pedido.getdPcred().setCoFormaPagoCredito(AtuxVariables.FORMA_PAGO_CREDITO);
                            pedido.getdPcred().setNuItemFormaPago(cPedidoVenta.getSecuenciaPedCredito(pedido));
                            pedido.getdPcred().setCoMoneda(pCoMoneda);
                            pedido.getdPcred().setCoFormaPago(pCodigoFormaPago);
                            pedido.getdPcred().setFePago(AtuxSearch.getFechaHoraTimestamp());
                            pedido.getdPcred().setVaTasaCambio(AtuxVariables.vTipoCambio);
                            pedido.getdPcred().setImPago(!pCodigoFormaPago.equals(AtuxVariables.FORMA_PAGO_CREDITO) ? Double.parseDouble(pImPago) : 0d);
                            pedido.getdPcred().setImTotalPago(!pCodigoFormaPago.equals(AtuxVariables.FORMA_PAGO_CREDITO) ? Double.parseDouble(pImTotalPago) : 0d);

                            montoSaldoCredito = montoSaldoCredito -  pedido.getdPcred().getImTotalPago();

                            pedido.getdPcred().setVaSaldo(montoSaldoCredito);
                            pedido.getdPcred().setInPagoDiferido("N");
                            pedido.getdPcred().setDeVentaCredito("");
                            pedido.getdPcred().setVaVuelto(Double.parseDouble(pVuelto));
                            pedido.getdPcred().setEsFormaPagoPedido("A");
                            pedido.getdPcred().setIdCreaFormaPagoPedido(AtuxVariables.vIdUsuario);
                            pedido.getdPcred().setFeCreaFormaPagoPedido(AtuxSearch.getFechaHoraTimestamp());
                            pedido.getdPcred().setInPagoBono("N");
                            pedido.getdPcred().setInAnulado("N");
                            cPedidoVenta.guardarPagoCredito(pedido);
                        }
                    }
                }
            }

            logger.info("********** Fin: Grabando el Detalle de Formas de Pago ***********");
            logger.info(" ");

            contadorComprobantes = 0;

            logger.info("********** Inicio: Definicion del Tipo de Comprobante ***********");

            if (donacionList.size() > 0) {
                DonacionService donacionService = Application.instance().getBean(DonacionService.class);
                donacionService.grabarDonacion(pedido, donacionList);
            }

            if (!pedido.getTiPedido().equals(AtuxVariables.TIPO_PEDIDO_CREDITO)) {

                    ServicioEmisionComprobantes servicio = new ServicioEmisionComprobantes(pedido,
                            pedido.getDetallePedidoVenta(),
                            null,
                            null, obtieneFormasPago(),
                            null, tableModelPagos.data);

                    servicio.setNumeroItemsPorComprobante(AtuxVariables.ITEMS_POR_COMPROBANTE);
                    servicio.setTblPagos(tblDetallePago);
                    servicio.setParentFrame(myParentFrame);
                    servicio.setDonacionList(donacionList);

                    servicio.setVuelto(Math.abs(AtuxUtility.getDecimalNumber(lblVuelto.getText().trim())) + "");

                    if (AtuxVariables.pulsoCashDrawer && AtuxVariables.vInComprobanteManual.equalsIgnoreCase("N")
                            && isAperturaCaja(tableModelPagos.data)) {
                        try {
                            EckerdCashDrawerManager.instance().abrirCashDrawer();
                        } catch (EckerdCashDrawerException ex) {
                            JOptionPane.showMessageDialog(this, "Fallo el pulso no se pudo abrir la caja registradora.\n" + ex.getMessage(),
                                    "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    servicio.ejecutar();

                    AtuxSearch.updateCobranzaPedido(false);
               }
               else {
                ComprobanteBuilder comprobantebuilder = new TicketCreditoBuilder();
                comprobantebuilder.setTblPagos(tblDetallePago);
                comprobantebuilder.setVuelto(String.valueOf(vuelto));
                comprobantebuilder.setTipoComprobante(pedido.getTiComprobante());
                AtuxVariables.vNumeroPedido        = pedido.getNuPedido();
                AtuxVariables.vNumeroPedidoDiario  = pedido.getNuPedidoDiario();

                ComprobanteGenerator reporteador = new ComprobanteGenerator(comprobantebuilder, AtuxVariables.vImpresoraTicketBoleta, 36);
                reporteador.setTicketera(false);
                reporteador.generarComprobante();
               }

               AtuxUtility.aceptarTransaccion();
               return true;
            }catch(Exception exception){
                clearDescuentoTarjeta();
                AtuxUtility.liberarTransaccion();
                exception.printStackTrace();
                if (exception.getMessage().indexOf("CHK_NU_RUC_CLIENTE") > 0) {
                    AtuxUtility.showMessage(this, "No se pudo determinar el numero de RUC. Verifique!!!", null);
                } else {
                    AtuxUtility.showMessage(this, "Error al Grabar Formas de Pago y/o Generar Comprobante de Pago !!! - " +
                            "\n" + exception.toString(), null);
                }

                exception.printStackTrace();
                return false;
            }
    }

    void clearDescuentoTarjeta() {
        lblSoles.setText(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta()));
        lblDolares.setText(AtuxUtility.formatNumber(pedido.getVaTotalPrecioVenta() / AtuxVariables.vTipoCambio));
        lblSaldo.setText(lblSoles.getText().trim());
        calculaTotales();
    }

    private boolean isAperturaCaja(ArrayList listaFormaPago) {
        Iterator it = listaFormaPago.iterator();
        while (it.hasNext()) {
            ArrayList formaPago = (ArrayList) it.next();
            if (formaPago.get(COLUMN_IN_APERTURA_GAVETA).toString().equals("S")) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        AppCtx.instance().setLocalId(new LocalId("001", "005"));
        new LookAndFeelManager().initialize();
        Application.instance().init();
        AppCtx appCtx = AppCtx.instance();
        AtuxVariables.vCodigoCompania = appCtx.getLocalId().getCoCompania();
        AtuxVariables.vCodigoLocal = appCtx.getLocalId().getCoLocal();
        AtuxVariables.vImpresoraTestigo = appCtx.getImpresoraTestigo();
        AppConfig.configUsuario("ADMIN", "atuxpro");
        AtuxUtility.setCajaTurno();
        CPedidoVenta cpedido = new CPedidoVenta();
        String feIni = "18/02/2015";
        String feFin = "18/02/2015";
        ArrayList ArrayPedidos = cpedido.getPedidoVenta("1", "", feIni, feFin, "");
        PedidoVenta pedidoVenta = (PedidoVenta) ArrayPedidos.get(0);
        IPagoPedido iPagoPedido = new IPagoPedido(new Frame(), "Pago de pedido", false);
        iPagoPedido.setPedido(pedidoVenta);
        iPagoPedido.setVisible(true);
    }

    public void setTblDetallePago(JTable tblDetallePago) {
        this.tblDetallePago = tblDetallePago;
    }

    public AtuxTableModel getTableModelPagos() {
        return tableModelPagos;
    }

    public void setDonacionList(List<IngresarDonacionDetalle> donacionList) {
        this.donacionList = donacionList;
    }

    public JLabel getLblSoles() {
        return lblSoles;
    }

    public void setLblSoles(JLabel lblSoles) {
        this.lblSoles = lblSoles;
    }
}