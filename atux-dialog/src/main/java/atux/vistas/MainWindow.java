package atux.vistas;

import atux.util.common.AtuxUtility;
import atux.vistas.venta.*;
import atux.vistas.venta.aperCierre.IAperturaCaja;
import atux.vistas.venta.aperCierre.IArqueosDeCaja;
import atux.vistas.venta.aperCierre.ICierreCajaTurno;
import atux.vistas.catalogo.ILaboratorio;
import atux.vistas.catalogo.IProveedores;
import atux.vistas.inventario.IPedidoReposicion;
import atux.vistas.venta.caja.ICobroPedido;
import atux.config.AppConfig;
import atux.controllers.CLocal;
import atux.modelbd.Local;
import atux.util.AdminIFrame;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxVariables;
import atux.vistas.catalogo.IMaestroProductos;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.*;

import atux.vistas.venta.caja.IPedidoACredito;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Fecha creación : 01/09/2014  v1.0
 *
 * @author aguerra
 */
public class MainWindow extends javax.swing.JFrame {

    private Frame parentFrame;
    private static IProveedores vntProveedores = null;
    private static ILaboratorio vntLaboratorio = null;
    private static ICobroPedido vntCobroPedido = null;
    private static IConsultarVentas vntConsultarVentas = null;
    private static IMaestroProductos vntProducto = null;
    private static IPedidoVenta vntPedidoVenta = null;
    private static IPedidoVentaInsumo vntPedidoVentaInsumo = null;
    private static IConsultarPedidosACredito vntPedidoACredito = null;
    private static ICompletarPedidoVenta vntCompPedidoVenta = null;
    private static IAperturaCaja vntAperturaCaja = null;
    private static IAnularComprobante vntAnulacionComprobante = null;
    private static IPedidoReposicion vntPedidoReposicion = null;

    private static ICierreCajaTurno vntCierreCaja = null;

    private ImageIcon imageIcono = new javax.swing.ImageIcon(getClass().getResource("/atux/resources/2793.ico"));
    private String titulo = "SA-VENTA:  MENU PRINCIPAL";
    private static final Log logger = LogFactory.getLog(MainWindow.class);

    public MainWindow() {
        initComponents();
        estadoMenu(false);

        try {
            leerProperties();
        } catch (IOException ex) {
            logger.error("Error al leerProperties " + ex);
            JOptionPane.showMessageDialog(this, "Error al obtener el parametros - " + ex.toString(), "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
        }

        Local local = new CLocal().getInfoLocal();

        txtVersion.setText(" v.1.0 ");
        txtEmpresa.setText(local.getCompania().getDeCompania());
        txtDireccion.setText(local.getDeDireccionLocal());
        txtTelefono.setText(local.getNuTelNormal());
        txtRUC.setText(local.getCompania().getNuRucCompania());

        AtuxVariables.vDistritoLocal = local.getDeDistrito();
        AtuxVariables.vProvinciaLocal = local.getDeProvincia();
        AtuxVariables.vDepartamentoLocal = local.getDeDepartamento();

        this.setIconImage(imageIcono.getImage());
        this.setTitle(titulo);
        this.panelFondo.setVisible(false);
        this.setLocationRelativeTo(null);
        jMenuItem3.setVisible(false);
        jMenuItem4.setVisible(false);
        txtUsuario.requestFocus();

        //Mac mac = new Mac();
        //mac.VisualizaVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dp = new javax.swing.JDesktopPane();
        panelFondo = new elaprendiz.gui.panel.PanelCurves();
        jPanelRight = new javax.swing.JPanel();
        jPanelLeft = new javax.swing.JPanel();
        panelLogin = new elaprendiz.gui.panel.PanelCurves();
        pnlInfo = new elaprendiz.gui.panel.PanelRound();
        lblVersionText = new javax.swing.JLabel();
        lblEmpresaText = new javax.swing.JLabel();
        lblDireccionText = new javax.swing.JLabel();
        lblTelefonoText = new javax.swing.JLabel();
        lblRUCText1 = new javax.swing.JLabel();
        txtVersion = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtRUC = new javax.swing.JTextField();
        pnlLogin = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUsuario = new elaprendiz.gui.textField.TextFieldRectIcon();
        ptxtPass = new elaprendiz.gui.passwordField.PasswordFieldRectIcon();
        bntCancelar = new javax.swing.JButton();
        bntIngresar = new javax.swing.JButton();
        jPanelFoot = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        dp.setBackground(new java.awt.Color(51, 153, 255));
        dp.setPreferredSize(new java.awt.Dimension(1100, 710));

        panelFondo.setBackground(new java.awt.Color(51, 153, 255));
        panelFondo.setMinimumSize(new java.awt.Dimension(960, 600));
        panelFondo.setPreferredSize(new java.awt.Dimension(960, 550));

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        dp.add(panelFondo);

        jPanelRight.setBackground(java.awt.Color.white);
        jPanelRight.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/ImgMenuPrincipalDerecha.png")))); // NOI18N
        jPanelRight.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRight.setLayout(jPanelRightLayout);
        jPanelRightLayout.setHorizontalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelRightLayout.setVerticalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelRight.setBounds(450, 0, 900, 670);
        dp.add(jPanelRight, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanelLeft.setBackground(new java.awt.Color(51, 153, 255));
        jPanelLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        panelLogin.setPreferredSize(new java.awt.Dimension(450, 312));

        pnlInfo.setBackground(new java.awt.Color(0, 153, 255));
        pnlInfo.setColorDeBorde(new java.awt.Color(0, 204, 204));
        pnlInfo.setColorPrimario(new java.awt.Color(0, 153, 255));
        pnlInfo.setColorSecundario(new java.awt.Color(0, 153, 255));
        pnlInfo.setMaximumSize(new java.awt.Dimension(450, 32767));

        lblVersionText.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVersionText.setForeground(java.awt.Color.white);
        lblVersionText.setText("Versión :");

        lblEmpresaText.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmpresaText.setForeground(java.awt.Color.white);
        lblEmpresaText.setText("Empresa :");

        lblDireccionText.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDireccionText.setForeground(java.awt.Color.white);
        lblDireccionText.setText("Dirección :");

        lblTelefonoText.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTelefonoText.setForeground(java.awt.Color.white);
        lblTelefonoText.setText("Telefono :");

        lblRUCText1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRUCText1.setForeground(java.awt.Color.white);
        lblRUCText1.setText("RUC :");

        txtVersion.setBackground(new java.awt.Color(0, 153, 255));
        txtVersion.setEditable(false);
        txtVersion.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtVersion.setForeground(java.awt.Color.white);
        txtVersion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtEmpresa.setBackground(new java.awt.Color(0, 153, 255));
        txtEmpresa.setEditable(false);
        txtEmpresa.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtEmpresa.setForeground(java.awt.Color.white);
        txtEmpresa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtDireccion.setBackground(new java.awt.Color(0, 153, 255));
        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtDireccion.setForeground(java.awt.Color.white);
        txtDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTelefono.setBackground(new java.awt.Color(0, 153, 255));
        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtTelefono.setForeground(java.awt.Color.white);
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtRUC.setBackground(new java.awt.Color(0, 153, 255));
        txtRUC.setEditable(false);
        txtRUC.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtRUC.setForeground(java.awt.Color.white);
        txtRUC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEmpresaText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDireccionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTelefonoText)
                                    .addComponent(lblVersionText, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblRUCText1))
                    .addGap(18, 18, 18)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(txtRUC)
                            .addComponent(txtEmpresa)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(txtVersion))
                    .addContainerGap(76, Short.MAX_VALUE))
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVersionText)
                            .addComponent(txtVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmpresaText)
                            .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDireccionText)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefonoText)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRUCText1)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));
        pnlLogin.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, java.awt.Color.white));
        pnlLogin.setOpaque(false);

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUsuario.setText("Usuario:");

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPassword.setText("Contraseña:");

        txtUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/user_blue_32.png"))); // NOI18N
        txtUsuario.setPreferredSize(new java.awt.Dimension(150, 24));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuariokeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        ptxtPass.setPreferredSize(new java.awt.Dimension(150, 24));
        ptxtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ptxtPassKeyPressed(evt);
            }
        });

        bntCancelar.setText("Cancelar");

        bntIngresar.setText("Ingresar");
        bntIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                    .addComponent(lblUsuario)
                                    .addGap(3, 3, 3))
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                    .addComponent(lblPassword)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                    .addComponent(bntIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bntCancelar))
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(ptxtPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                    .addContainerGap())
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblUsuario))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptxtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntIngresar)
                    .addComponent(bntCancelar))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLoginLayout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(103, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                    .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLeft.setBounds(0, 0, 450, 670);
        dp.add(jPanelLeft, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanelFoot.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/ImgMenuPrincipalPie.png")))); // NOI18N

        javax.swing.GroupLayout jPanelFootLayout = new javax.swing.GroupLayout(jPanelFoot);
        jPanelFoot.setLayout(jPanelFootLayout);
        jPanelFootLayout.setHorizontalGroup(
                jPanelFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelFootLayout.setVerticalGroup(
                jPanelFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFoot.setBounds(0, 670, 1400, 40);
        dp.add(jPanelFoot, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(dp);

        jMenuBar1.setBackground(new java.awt.Color(79, 129, 189));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setBackground(new java.awt.Color(79, 129, 189));
        jMenu1.setBorder(null);
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuVentas.png"))); // NOI18N
        jMenu1.setMnemonic('V');
        jMenu1.setBorderPainted(true);
        jMenu1.setName("jmVentas"); // NOI18N

        jMenuItem7.setText("Realizar Venta");
        jMenuItem7.setName("jmIRealizarVenta"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem13.setText("Productos Terminados");
        jMenuItem13.setName("jmIRealizarVenta"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuItem8.setText("Completar Ventas");
        jMenuItem8.setName("jmICompletarVenta"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem47.setText("Pedido a Crédito");
        jMenuItem47.setName("jmIRealizarVenta"); // NOI18N
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem47);

        jMenuItem9.setText("Consultar Ventas");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem10.setText("Devolución");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenu7.setBackground(new java.awt.Color(79, 129, 189));
        jMenu7.setText("Consultas");

        jMenuItem16.setText("Detalle de Ventas");
        jMenu7.add(jMenuItem16);

        jMenuItem17.setText("Ventas por Trans.");
        jMenu7.add(jMenuItem17);

        jMenuItem18.setText("Ventas por Día");
        jMenu7.add(jMenuItem18);

        jMenu1.add(jMenu7);

        jMenuItem12.setText("Caja");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuItem11.setText("Apertura de Caja");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenu9.setText("Cierre de Caja");

        jMenuItem45.setText("Arqueos");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem45);

        jMenuItem46.setText("Cierre de Caja Turno");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem46);

        jMenu1.add(jMenu9);

        jMenuBar1.add(jMenu1);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuInventarios.png"))); // NOI18N
        jMenu3.setMnemonic('I');
        jMenu3.setName("jmInventarios"); // NOI18N

        jMenuItem3.setText("Salid/Devol.x.Guia");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Guias de Ingreso");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem15.setText("Ajuste Stock");
        jMenu3.add(jMenuItem15);

        jMenuItem14.setText("Stock Inicial");
        jMenu3.add(jMenuItem14);

        jMenuItem19.setText("Kardex");
        jMenu3.add(jMenuItem19);

        jMenuItem20.setText("Rotación");
        jMenu3.add(jMenuItem20);

        jMenuItem21.setText("Ped. Reposición");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);

        jMenuBar1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuCatalogos.png"))); // NOI18N
        jMenu2.setMnemonic('C');

        jMenuItem5.setText("Productos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem2.setText("Proveedores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem22.setText("Laboratorios");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem22);

        jMenuItem23.setText("Vendedores");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem23);

        jMenuItem24.setText("Clientes");
        jMenu2.add(jMenuItem24);

        jMenuItem25.setText("IGV");
        jMenu2.add(jMenuItem25);

        jMenuItem26.setText("Tipo de Cambio");
        jMenu2.add(jMenuItem26);

        jMenuItem27.setText("Formas de Pago");
        jMenu2.add(jMenuItem27);

        jMenu8.setText("Set Categorias");

        jMenuItem1.setText("Linea               -G1");
        jMenu8.add(jMenuItem1);

        jMenuItem28.setText("Division          -G2");
        jMenu8.add(jMenuItem28);

        jMenuItem32.setText("Sub.Division  -G3");
        jMenu8.add(jMenuItem32);

        jMenuItem33.setText("Familia           -G4");
        jMenu8.add(jMenuItem33);

        jMenuItem34.setText("Sub.Familia   -G5");
        jMenu8.add(jMenuItem34);

        jMenu2.add(jMenu8);

        jMenuItem29.setText("Principio Activo");
        jMenu2.add(jMenuItem29);

        jMenuItem30.setText("Produ+Princ.Acti.");
        jMenu2.add(jMenuItem30);

        jMenuItem31.setText("Medicos");
        jMenu2.add(jMenuItem31);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuPrecios.png"))); // NOI18N
        jMenu4.setMnemonic('P');

        jMenuItem35.setText("Factores");
        jMenu4.add(jMenuItem35);

        jMenuItem36.setText("Precios");
        jMenu4.add(jMenuItem36);

        jMenuItem37.setText("Descuento Gral.");
        jMenu4.add(jMenuItem37);

        jMenuItem38.setText("Descuentos Lab.");
        jMenu4.add(jMenuItem38);

        jMenuItem39.setText("Incentivos");
        jMenu4.add(jMenuItem39);

        jMenuItem40.setText("Min.Exhibición");
        jMenu4.add(jMenuItem40);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuUtilitarios.png"))); // NOI18N
        jMenu5.setMnemonic('U');

        jMenuItem41.setText("Correlativos");
        jMenu5.add(jMenuItem41);

        jMenuItem42.setText("Num.Sunat");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem42);

        jMenuItem43.setText("Actualizar inform.");
        jMenu5.add(jMenuItem43);

        jMenuItem44.setText("Clave de Usuarios");
        jMenu5.add(jMenuItem44);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/menuSalir.png"))); // NOI18N
        jMenu6.setMnemonic('S');

        jMenuItem6.setText("Salir del sistema");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuariokeyTyped(KeyEvent evt) {
        AtuxUtility.convertirMayuscula(evt);
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.out.println("******---");
        if (vntProveedores == null) {
            vntProveedores = new IProveedores();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntProveedores);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (vntProducto == null) {
            vntProducto = new IMaestroProductos();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntProducto);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed


    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (vntPedidoVenta == null) {
            vntPedidoVenta = new IPedidoVenta();
        }

        AdminIFrame.mostrarVentanaCentrada(dp, vntPedidoVenta);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void bntIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIngresarActionPerformed
        if (!this.txtUsuario.getText().isEmpty() && !String.copyValueOf(this.ptxtPass.getPassword()).isEmpty()) {
            String msg = "";

            AppConfig.Estado configUsuario = AppConfig.configUsuario(this.txtUsuario.getText().toUpperCase(), String.copyValueOf(this.ptxtPass.getPassword()));

            if (configUsuario == AppConfig.Estado.NO_EXISTE) {
                msg = "El usuario: " + this.txtUsuario.getText() + " no existe.";
            } else if (configUsuario == AppConfig.Estado.ERROR_CLAVE) {
                msg = "Contraseña Incorrecta!";
            } else {
                msg = "Contraseña Correcta!";
                JOptionPane.showMessageDialog(this, "Bienvenido:  " + AppConfig.getUsuario().getNombreCompleto(), "Se realizó la conexión", JOptionPane.INFORMATION_MESSAGE);
                try {
                    obtenerInfoLocal();
                } catch (SQLException ex) {
                    logger.error("Error al obtenerInfoLocal " + ex);
                }
                estadoMenu(true);
                this.jPanelRight.setVisible(false);
                this.jPanelLeft.setVisible(false);
                this.jPanelFoot.setVisible(false);
                this.panelFondo.setVisible(true);
                this.txtUsuario.setText("");
                this.ptxtPass.setText("");
                return;
            }
            logger.info(msg + " " + AppConfig.getUsuario().getNombreCompleto());
        }
    }//GEN-LAST:event_bntIngresarActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed

    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ptxtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void ptxtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptxtPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bntIngresar.doClick();
        }
    }//GEN-LAST:event_ptxtPassKeyPressed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (vntCompPedidoVenta == null) {
            vntCompPedidoVenta = new ICompletarPedidoVenta();
        }

        if (JOptionPane.showConfirmDialog(this, "Se mostrará¡ la pantalla de completar pedido manual.\n                          Desea Continuar?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            AdminIFrame.mostrarVentanaCentrada(dp, vntCompPedidoVenta);
            return;
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        if (vntCobroPedido == null) {
            vntCobroPedido = new ICobroPedido();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntCobroPedido);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (vntConsultarVentas == null) {
            vntConsultarVentas = new IConsultarVentas();
        }

        AdminIFrame.mostrarVentanaCentrada(dp, vntConsultarVentas);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (vntAperturaCaja == null) {
            vntAperturaCaja = new IAperturaCaja();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntAperturaCaja);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (vntAnulacionComprobante == null) {
            vntAnulacionComprobante = new IAnularComprobante();
        } else {
            vntAnulacionComprobante.generarNuevoCobro();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntAnulacionComprobante);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
        IArqueosDeCaja vntArqueoCaja = new IArqueosDeCaja(this, true);
        vntArqueoCaja.setVisible(true);
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        if (vntCierreCaja == null) {
            try {
                vntCierreCaja = new ICierreCajaTurno();
            } catch (SQLException ex) {
                logger.error(MainWindow.class.getName() + " " + ex);
            }
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntCierreCaja);
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        if (vntPedidoReposicion == null) {
            vntPedidoReposicion = new IPedidoReposicion();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntPedidoReposicion);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        if (vntLaboratorio == null) {
            vntLaboratorio = new ILaboratorio();
        }
        AdminIFrame.mostrarVentanaCentrada(dp, vntLaboratorio);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        if (vntPedidoVenta == null) {
            vntPedidoVenta = new IPedidoVenta();
        }

        AdminIFrame.mostrarVentanaCentrada(dp, vntPedidoVenta);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        if (vntPedidoVentaInsumo == null) {
            vntPedidoVentaInsumo = new IPedidoVentaInsumo();
        }

        AdminIFrame.mostrarVentanaCentrada(dp, vntPedidoVentaInsumo);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        if (vntPedidoACredito == null) {
            vntPedidoACredito = new IConsultarPedidosACredito();
        }

        AdminIFrame.mostrarVentanaCentrada(dp, vntPedidoACredito);

    }//GEN-LAST:event_jMenuItem47ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton bntIngresar;
    private javax.swing.JDesktopPane dp;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanelFoot;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JLabel lblDireccionText;
    private javax.swing.JLabel lblEmpresaText;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRUCText1;
    private javax.swing.JLabel lblTelefonoText;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersionText;
    private elaprendiz.gui.panel.PanelCurves panelFondo;
    private elaprendiz.gui.panel.PanelCurves panelLogin;
    private elaprendiz.gui.panel.PanelRound pnlInfo;
    private javax.swing.JPanel pnlLogin;
    private elaprendiz.gui.passwordField.PasswordFieldRectIcon ptxtPass;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtTelefono;
    private elaprendiz.gui.textField.TextFieldRectIcon txtUsuario;
    private javax.swing.JTextField txtVersion;
    // End of variables declaration//GEN-END:variables

    private void estadoMenu(boolean estado) {
//        jMenuItem1.setEnabled(estado);
//        jMenuItem2.setEnabled(estado);
//        jMenuItem3.setEnabled(estado);
//        jMenuItem4.setEnabled(estado);
//        jMenuItem5.setEnabled(estado);
//        jMenuItem7.setEnabled(estado);
    }

    private void leerProperties() throws IOException {
        InputStream fis = null;
        Properties properties = null;
        fis = this.getClass().getResourceAsStream("/properties/AtuxServidor.properties");
        if (fis != null) {
            properties = new Properties();
            properties.load(fis);
            AtuxVariables.vCodigoCompania = properties.getProperty("CodigoCompania");
            AtuxVariables.vCodigoLocal = properties.getProperty("CodigoLocal");
            AtuxVariables.vImpresoraTestigo = properties.getProperty("ImpresoraTestigo");
        }
    }

    public static void obtenerInfoLocal() throws SQLException {
        Local local = AppConfig.getLocal(AtuxVariables.vCodigoLocal);
        AtuxVariables.vDescripcionLocal = local.getDeLocal().trim();
        AtuxVariables.vTipoCaja = local.getTiCaja();
        AtuxVariables.vDescripcionCompania = local.getCompania().getDeCompania();
        AtuxVariables.vCompaniaDireccion = local.getCompania().getDeDireccion();
        AtuxVariables.vCompaniaDireccionWeb = local.getCompania().getDeDireccionWeb();
        AtuxVariables.vCompaniaFono = local.getCompania().getNuTelNormal();
        AtuxVariables.vNuRucCompania = local.getCompania().getNuRucCompania();

        AtuxVariables.vTipoCambio = AtuxSearch.getTipoCambio(AtuxSearch.getFechaHoraBD(AtuxVariables.FORMATO_FECHA));
        AtuxVariables.vDeMensajeTicket = local.getDeMensajeTicket();
        AtuxVariables.vDireccionLocal = local.getDeDireccionLocal();
        AtuxVariables.vInTicketBoleta = local.getInTicketBoleta();
        AtuxVariables.vInTicketFactura = local.getInTicketFactura();
        AtuxSearch.setIGV();

        logger.info(AtuxVariables.vDescripcionLocal + " Es caja <<" + AtuxVariables.vTipoCaja + ">> : Tradicional (T) - Multifuncional (M)");
    }

}