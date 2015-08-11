package com.atux.desktop.seguridad;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import elaprendiz.gui.panel.PanelCurves;
import elaprendiz.gui.panel.PanelRound;
import elaprendiz.gui.passwordField.PasswordFieldRectIcon;
import elaprendiz.gui.textField.TextFieldRectIcon;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 03/12/2014.
 */
public class FrmLoginV2 {
    public JPanel pnlMain;
    public JPanel pnlLeft;
    public JPanel pnlRight;
    public JPanel pnlFooterPanel;
    //    public JPanel pnlInstalalcion;
    public JPanel pnlLogin;
    private PanelCurves panelLogin;
    private PanelRound pnlInfo;
    public JLabel jLabel6;
    public JLabel jLabel7;
    public PasswordFieldRectIcon txtPassword;
    public TextFieldRectIcon txtUsuario;
//    private PanelCurves panelCurves1;
    private PanelCurves panelCurves2;
    public JButton btnCancel;
    public JButton btnAceptar;
    private JLabel lblDireccionText;
    private JLabel lblEmpresaText;
    private JLabel lblPassword;
    private JLabel lblRUCText1;
    private JLabel lblTelefonoText;
    private JLabel lblUsuario;
    private JLabel lblVersionText;
    public JTextField txtDireccion;
    public JTextField txtEmpresa;
    public JTextField txtRUC;
    public JTextField txtTelefono;
    public JTextField txtVersion;


    private void createUIComponents() {
        lblVersionText = new JLabel();
        lblEmpresaText = new JLabel();
        lblDireccionText = new JLabel();
        lblTelefonoText = new JLabel();
        lblRUCText1 = new JLabel();
        txtVersion = new JTextField();
        txtEmpresa = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtRUC = new JTextField();
        pnlInfo = new PanelRound();
        panelLogin = new PanelCurves();
        lblVersionText.setFont(new Font("Tahoma", 1, 14));
        lblVersionText.setForeground(Color.white);
        lblVersionText.setText("Versión :");

        lblEmpresaText.setFont(new Font("Tahoma", 1, 14));
        lblEmpresaText.setForeground(Color.white);
        lblEmpresaText.setText("Empresa :");

        lblDireccionText.setFont(new Font("Tahoma", 1, 14));
        lblDireccionText.setForeground(Color.white);
        lblDireccionText.setText("Dirección :");

        lblTelefonoText.setFont(new Font("Tahoma", 1, 14));
        lblTelefonoText.setForeground(Color.white);
        lblTelefonoText.setText("Telefono :");

        lblRUCText1.setFont(new Font("Tahoma", 1, 14));
        lblRUCText1.setForeground(Color.white);
        lblRUCText1.setText("RUC :");

        txtVersion.setBackground(new Color(0, 153, 255));
        txtVersion.setEditable(false);
        txtVersion.setFont(new Font("Tahoma", 1, 14));
        txtVersion.setForeground(Color.white);
        txtVersion.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtEmpresa.setBackground(new Color(0, 153, 255));
        txtEmpresa.setEditable(false);
        txtEmpresa.setFont(new Font("Tahoma", 1, 14));
        txtEmpresa.setForeground(Color.white);
        txtEmpresa.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtDireccion.setBackground(new Color(0, 153, 255));
        txtDireccion.setEditable(false);
        txtDireccion.setFont(new Font("Tahoma", 1, 14));
        txtDireccion.setForeground(Color.white);
        txtDireccion.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTelefono.setBackground(new Color(0, 153, 255));
        txtTelefono.setEditable(false);
        txtTelefono.setFont(new Font("Tahoma", 1, 14));
        txtTelefono.setForeground(Color.white);
        txtTelefono.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtRUC.setBackground(new Color(0, 153, 255));
        txtRUC.setEditable(false);
        txtRUC.setFont(new Font("Tahoma", 1, 14));
        txtRUC.setForeground(Color.white);
        txtRUC.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPassword = new PasswordFieldRectIcon();
        txtUsuario = new TextFieldRectIcon();
//        panelCurves1 = new PanelCurves();
        panelCurves2 = new PanelCurves();
        btnCancel = new JButton();
        btnAceptar = new JButton();
        panelCurves2 = new PanelCurves();
        pnlRight = new JPanel();
        pnlFooterPanel = new JPanel();
        pnlLeft = new JPanel();
//        panelCurves1 = new PanelCurves();
//        pnlInstalalcion = new JPanel();
        pnlLogin = new JPanel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        txtUsuario = new TextFieldRectIcon();
        txtPassword = new PasswordFieldRectIcon();
        btnCancel = new JButton();
        btnAceptar = new JButton();

        panelCurves2.setBackground(new Color(51, 153, 255));

        panelLogin.setPreferredSize(new Dimension(450, 312));

        pnlInfo.setBackground(new Color(0, 153, 255));
        pnlInfo.setColorDeBorde(new Color(0, 204, 204));
        pnlInfo.setColorPrimario(new Color(0, 153, 255));
        pnlInfo.setColorSecundario(new Color(0, 153, 255));
        pnlInfo.setMaximumSize(new Dimension(450, 32767));


        GroupLayout panelCurves2Layout = new GroupLayout(panelCurves2);
        panelCurves2.setLayout(panelCurves2Layout);
        panelCurves2Layout.setHorizontalGroup(
                panelCurves2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 1180, Short.MAX_VALUE)
        );
        panelCurves2Layout.setVerticalGroup(
                panelCurves2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 710, Short.MAX_VALUE)
        );

        panelCurves2.setBounds(0, 0, 1180, 565);

        pnlRight.setBackground(Color.white);

        pnlRight.setBorder(new MatteBorder(new ImageIcon(getClass().getResource("/atux/resources/ImgMenuPrincipalDerecha.png")))); // NOI18N
        pnlRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        GroupLayout jPanel1Layout = new GroupLayout(pnlRight);
        pnlRight.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlRight.setBounds(850, 0, 1010, 670);

        pnlFooterPanel.setBorder(new MatteBorder(new ImageIcon(getClass().getResource("/atux/resources/ImgMenuPrincipalPie.png")))); // NOI18N
        pnlFooterPanel.setBounds(0, 670, 1380, 40);

        pnlLeft.setBackground(new Color(51, 153, 255));
        pnlLeft.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));

//        panelCurves1.setPreferredSize(new Dimension(1180, 710));

//        pnlInstalalcion.setBackground(new Color(51, 153, 255));
//        pnlInstalalcion.setBorder(new MatteBorder(new ImageIcon(getClass().getResource("/atux/resources/DatosEmpresaII.png")))); // NOI18N
//        pnlInstalalcion.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));


        GroupLayout pnlInfoLayout = new GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
                pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlInfoLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblEmpresaText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblDireccionText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTelefonoText)
                                                .addComponent(lblVersionText, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblRUCText1))
                                .addGap(18, 18, 18)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(txtRUC)
                                        .addComponent(txtEmpresa)
                                        .addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        .addComponent(txtVersion))
                                .addContainerGap(76, Short.MAX_VALUE))
        );
        pnlInfoLayout.setVerticalGroup(
                pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVersionText)
                                        .addComponent(txtVersion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEmpresaText)
                                        .addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDireccionText)
                                        .addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTelefonoText)
                                        .addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRUCText1)
                                        .addComponent(txtRUC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );


        pnlLogin.setBackground(new Color(255, 255, 255));
        pnlLogin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        pnlLogin.setOpaque(false);

        jLabel6.setFont(new Font("Tahoma", 1, 14));
        jLabel6.setText("Usuario:");

        jLabel7.setFont(new Font("Tahoma", 1, 14));
        jLabel7.setText("Contraseña:");

        txtUsuario.setIcon(new ImageIcon(getClass().getResource("/atux/resources/user_blue_32.png"))); // NOI18N
        txtUsuario.setPreferredSize(new Dimension(150, 24));


        txtPassword.setPreferredSize(new Dimension(150, 24));


        btnCancel.setText("Cancelar");

        btnAceptar.setText("Ingresar");


        GroupLayout pnlLoginLayout = new GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
                pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addGroup(pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel6)
                                                .addGap(3, 3, 3)
                                                .addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel7)
                                                .addGap(3, 3, 3)
                                                .addGroup(pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                                .addComponent(btnAceptar)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnCancel))
                                                        .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                                .addGap(22, 22, 22))
        );
        pnlLoginLayout.setVerticalGroup(
                pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addGroup(pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel6))
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel7))
                                        .addGroup(pnlLoginLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAceptar)
                                        .addComponent(btnCancel)))
        );

        GroupLayout panelLoginLayout = new GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(103, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(pnlInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        GroupLayout pnlLeftLayout = new GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
                pnlLeftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
                pnlLeftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLeftLayout.createSequentialGroup()
                                .addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(86, Short.MAX_VALUE))
        );

        pnlLeft.setBounds(0, 0, 150, 670);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        pnlMain = new JPanel();
        pnlMain.setLayout(new FormLayout("fill:230dlu:noGrow,left:2dlu:noGrow,fill:385dlu:noGrow", "fill:430dlu:noGrow,top:2dlu:noGrow,fill:40px:noGrow"));
        pnlMain.setMinimumSize(new Dimension(1250, 750));
        pnlMain.setPreferredSize(new Dimension(1250, 750));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlLeft, cc.xy(1, 1));
        pnlMain.add(pnlRight, cc.xy(3, 1));
        pnlMain.add(pnlFooterPanel, cc.xyw(1, 3, 3));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
