package atux.vistas.venta.aperCierre;

import atux.config.AppConfig;
import atux.controllers.CSimpleModelo;
import atux.modelgui.ModeloTablaSimple;
import atux.util.Helper;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.util.text.FormatoDecimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class IAperturaCaja extends javax.swing.JInternalFrame {

   private final Log logger = LogFactory.getLog(getClass());
   private CSimpleModelo controllerCaja; 
   private DefaultComboBoxModel mCaja;    
   
    public IAperturaCaja() {
        initComponents();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageTurnoCaja = new elaprendiz.gui.panel.PanelImage();
        lblUsuario = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        txtUsuario = new elaprendiz.gui.textField.TextFieldRectIcon();
        txtPass = new elaprendiz.gui.passwordField.PasswordFieldRectIcon();
        bntValidar = new javax.swing.JButton();
        jScrollPaneRelImpresoras = new javax.swing.JScrollPane();
        tblImpresoras = new javax.swing.JTable();
        panelRectTranslucido1 = new elaprendiz.gui.panel.PanelRectTranslucido();
        lblTurno = new javax.swing.JLabel();
        cmbTurno = new javax.swing.JComboBox();
        lblFecha = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        lblHora = new javax.swing.JLabel();
        clockDigital = new elaprendiz.gui.varios.ClockDigital();
        lblNroCaja = new javax.swing.JLabel();
        cmbCaja = new javax.swing.JComboBox();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblTipoCambio = new javax.swing.JLabel();
        txtTipoCambio = new javax.swing.JFormattedTextField();
        lblCajero = new javax.swing.JLabel();
        txtCajero = new elaprendiz.gui.textField.TextField();
        lblTipoCaja = new javax.swing.JLabel();
        txtTipoCaja = new elaprendiz.gui.textField.TextField();
        pnlAccionesLab = new javax.swing.JPanel();
        btnTestigo = new elaprendiz.gui.button.ButtonRect();
        btnCaja = new elaprendiz.gui.button.ButtonRect();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setTitle("Apertura de caja");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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
                formInternalFrameOpened(evt);
            }
        });

        panelImageTurnoCaja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelImageTurnoCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario:");

        lblPass.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPass.setForeground(new java.awt.Color(255, 255, 255));
        lblPass.setText("Contraseña:");

        txtUsuario.setNextFocusableComponent(txtPass);
        txtUsuario.setPreferredSize(new java.awt.Dimension(150, 24));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        txtPass.setNextFocusableComponent(bntValidar);
        txtPass.setPreferredSize(new java.awt.Dimension(150, 24));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        bntValidar.setMnemonic('V');
        bntValidar.setText("Validar");
        bntValidar.setNextFocusableComponent(cmbTurno);
        bntValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntValidarActionPerformed(evt);
            }
        });

        tblImpresoras.setModel(new javax.swing.table.DefaultTableModel(
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
                "Nro", "Caja", "Tipo", "Comprobante", "Cola Impresora", "Estado"
            }
        ));
        tblImpresoras.setPreferredSize(new java.awt.Dimension(452, 112));
        jScrollPaneRelImpresoras.setViewportView(tblImpresoras);

        panelRectTranslucido1.setColorDeBorde(new java.awt.Color(0, 102, 204));
        panelRectTranslucido1.setColorPrimario(new java.awt.Color(106, 207, 231));

        lblTurno.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno.setText("Turno :");

        cmbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1º", "2º", "3º", "4º" }));
        cmbTurno.setNextFocusableComponent(dcFecha);

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha:");

        dcFecha.setDate(Calendar.getInstance().getTime());
        dcFecha.setNextFocusableComponent(cmbCaja);
        dcFecha.setPreferredSize(new java.awt.Dimension(120, 20));

        lblHora.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("Hora:");

        clockDigital.setForeground(new java.awt.Color(204, 0, 0));
        clockDigital.setPreferredSize(new java.awt.Dimension(120, 45));

        lblNroCaja.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblNroCaja.setForeground(new java.awt.Color(255, 255, 255));
        lblNroCaja.setText("Nro :");

        cmbCaja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Caja 1", "Caja 2", "Caja 3", "Caja 4", "Caja 5", "Caja 6", "Caja 7", "Caja 8", "Caja 9", "Caja 10", "Caja 11", "Caja 12", "Caja 13", "Caja 14", "Caja 16", "Caja 17", "Caja 18", "Caja 19", "Caja 20", "Caja 21", "Caja 22", "Caja 23", "Caja 24", "Caja 25" }));
        cmbCaja.setEnabled(false);
        cmbCaja.setNextFocusableComponent(txtMonto);

        lblMonto.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto S/:");

        txtMonto.setText("0.00");
        txtMonto.setNextFocusableComponent(txtTipoCambio);

        lblTipoCambio.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoCambio.setText("Tipo de Cambio:");

        txtTipoCambio.setEditable(false);
        txtTipoCambio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#,##0.00",true))));
        txtTipoCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTipoCambio.setNextFocusableComponent(txtCajero);
        txtTipoCambio.setPreferredSize(new java.awt.Dimension(60, 20));

        lblCajero.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblCajero.setForeground(new java.awt.Color(255, 255, 255));
        lblCajero.setText("Cajero :");

        txtCajero.setEditable(false);
        txtCajero.setNextFocusableComponent(txtTipoCaja);
        txtCajero.setPreferredSize(new java.awt.Dimension(615, 22));

        lblTipoCaja.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblTipoCaja.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoCaja.setText("Tipo caja :");

        txtTipoCaja.setEditable(false);
        txtTipoCaja.setNextFocusableComponent(bntAceptar);
        txtTipoCaja.setPreferredSize(new java.awt.Dimension(615, 22));

        javax.swing.GroupLayout panelRectTranslucido1Layout = new javax.swing.GroupLayout(panelRectTranslucido1);
        panelRectTranslucido1.setLayout(panelRectTranslucido1Layout);
        panelRectTranslucido1Layout.setHorizontalGroup(
            panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTurno)
                .addGap(16, 16, 16)
                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblFecha)
                .addGap(2, 2, 2)
                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHora)
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(clockDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblNroCaja)
                .addGap(17, 17, 17)
                .addComponent(cmbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblMonto)
                .addGap(6, 6, 6)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblTipoCambio)
                .addGap(11, 11, 11)
                .addComponent(txtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblCajero)
                .addGap(8, 8, 8)
                .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTipoCaja)
                .addGap(3, 3, 3)
                .addComponent(txtTipoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRectTranslucido1Layout.setVerticalGroup(
            panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTurno)
                    .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha)
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHora)
                            .addComponent(clockDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNroCaja)
                    .addComponent(cmbCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoCambio)
                    .addComponent(txtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelRectTranslucido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCajero))
                    .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRectTranslucido1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblTipoCaja))
                    .addComponent(txtTipoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlAccionesLab.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesLab.setOpaque(false);
        pnlAccionesLab.setPreferredSize(new java.awt.Dimension(780, 200));
        pnlAccionesLab.setLayout(null);

        btnTestigo.setBackground(new java.awt.Color(51, 153, 255));
        btnTestigo.setText("Testigo");
        btnTestigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestigoActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnTestigo);
        btnTestigo.setBounds(8, 6, 80, 25);

        btnCaja.setBackground(new java.awt.Color(51, 153, 255));
        btnCaja.setText("Caja");
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(btnCaja);
        btnCaja.setBounds(93, 6, 70, 25);

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Aceptar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(bntAceptar);
        bntAceptar.setBounds(168, 6, 74, 25);

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });
        pnlAccionesLab.add(bntSalir);
        bntSalir.setBounds(248, 6, 70, 25);

        javax.swing.GroupLayout panelImageTurnoCajaLayout = new javax.swing.GroupLayout(panelImageTurnoCaja);
        panelImageTurnoCaja.setLayout(panelImageTurnoCajaLayout);
        panelImageTurnoCajaLayout.setHorizontalGroup(
            panelImageTurnoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(lblUsuario)
                .addGap(3, 3, 3)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(lblPass)
                .addGap(3, 3, 3)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(bntValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelRectTranslucido1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
            .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPaneRelImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(pnlAccionesLab, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        panelImageTurnoCajaLayout.setVerticalGroup(
            panelImageTurnoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelImageTurnoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblUsuario))
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelImageTurnoCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImageTurnoCajaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblPass))
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntValidar))
                .addGap(11, 11, 11)
                .addComponent(panelRectTranslucido1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPaneRelImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlAccionesLab, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageTurnoCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageTurnoCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        txtUsuario.setText(AtuxVariables.vIdUsuario.trim());
        AtuxUtility.moveFocus(txtUsuario);
        controllerCaja = new CSimpleModelo();
        mCaja = new DefaultComboBoxModel(controllerCaja.getCajaPago().toArray());
        this.cmbCaja.setModel(mCaja);
        this.cmbCaja.setSelectedIndex(0);
        this.txtTipoCambio.setText(String.valueOf(AtuxVariables.vTipoCambio));
    }//GEN-LAST:event_formInternalFrameOpened

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        closeWindow(true);
    }//GEN-LAST:event_bntSalirActionPerformed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AtuxUtility.moveFocus(txtPass);
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bntValidar.doClick();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void bntValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntValidarActionPerformed
        if(!this.txtUsuario.getText().isEmpty() && !String.copyValueOf(this.txtPass.getPassword()).isEmpty())
        {
         String msg = "";    
         AppConfig.Estado configUsuario = AppConfig.configUsuario(this.txtUsuario.getText().toUpperCase(), String.copyValueOf(this.txtPass.getPassword()));
            
            if(configUsuario == AppConfig.Estado.NO_EXISTE)
            {
                msg = "El usuario: "+this.txtUsuario.getText()+" no existe.";
            }else if(configUsuario == AppConfig.Estado.ERROR_CLAVE)
            {
                msg = "Contraseña Incorrecta!";
            }else{                
                try {
                    msg = "Contraseña Correcta!";
                    
                    AtuxUtility.setCajaTurno();
                    
                    if (AtuxVariables.vNuCaja.trim().length() == 0 || !(Integer.parseInt(AtuxVariables.vNuCaja) > 0)) {
                        JOptionPane.showMessageDialog(this, "No se pudo determinar una Caja relacionada al Usuario. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                        closeWindow(false);
                    }
                    if (AtuxVariables.vInCajaAbierta.equalsIgnoreCase("S")) {
                        JOptionPane.showMessageDialog(this, "La Caja " + AtuxVariables.vNuCaja + " se encuentra ABIERTA. Verifique !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                        bntAceptar.setEnabled(false);
                    }
                    this.dcFecha.setDate(Calendar.getInstance().getTime());
                    
                    if (AtuxVariables.vTipoCaja.equalsIgnoreCase(AtuxVariables.TIPO_CAJA_TRADICIONAL))
                        txtTipoCaja.setText("Tradicional");
                    else 
                        txtTipoCaja.setText("MultiFuncional");
                    
                    txtCajero.setText(AppConfig.getUsuario().getNombreCompleto());
                    if (!AtuxVariables.vNuCaja.trim().equalsIgnoreCase("")) {
                        this.cmbCaja.setSelectedIndex(Integer.parseInt(AtuxVariables.vNuCaja)-1);
                    }
                    
                    controllerCaja = new CSimpleModelo();                    
                    ModeloTablaSimple model =  new ModeloTablaSimple(controllerCaja.getRelacionDeImpresoraCaja(Integer.parseInt(AtuxVariables.vNuCaja)), ModeloTablaSimple.IMPRESORAS);
                    tblImpresoras.setModel(model);
                    Helper.ajustarSoloAnchoColumnas(tblImpresoras, ModeloTablaSimple.anchoColumnasImpresoras);
                    
                    AtuxUtility.moveFocus(cmbTurno);
                
                } catch (SQLException ex) {
                    logger.error("Error en bntValidarActionPerformed "+ IAperturaCaja.class.getName() + ex);                    
                }
            }
      
           logger.info(msg +" "+AppConfig.getUsuario().getNombreCompleto());
        }
    }//GEN-LAST:event_bntValidarActionPerformed

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
         if (txtMonto.getText().trim().length() == 0) txtMonto.setText("0.00");
            if (!AtuxUtility.validaCentimos(txtMonto)) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un Monto valido - Centimos !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                AtuxUtility.moveFocus(txtMonto);
                return;
            }
            if (AtuxUtility.getDecimalNumber(txtMonto.getText().trim()) == 0.00) {
                if (JOptionPane.showConfirmDialog(this, "Seguro de Apertura la Caja con S/. 0.00 ?\n                          Desea Continuar?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION) {
                     AtuxUtility.moveFocus(txtMonto);
                    return;
               }
            }
            else {        
                try {
                    String fecha = AtuxUtility.getDateToString(dcFecha.getDate(),"dd/MM/yyyy");
                    AtuxVariables.vNuTurno = ((String) cmbTurno.getSelectedItem()).trim().substring(0, 1);

                    if (AtuxSearch.getExisteCierreCaja(fecha)) {
                        JOptionPane.showMessageDialog(this, "Ya existe registro de Movimiento para la Caja y el Turno", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String nuSecMovimiento = AtuxSearch.getNuSecNumeracion(AtuxVariables.NUMERACION_MOVIMIENTO_CAJA, 8);            
                    AtuxSearch.insertaMovimientoCaja(nuSecMovimiento,fecha,AtuxVariables.TIPO_MOVIMIENTO_CAJA_APERTURA, AtuxVariables.vNuSecUsuario,
                            AtuxVariables.vPaternoUsuario, AtuxVariables.vMaternoUsuario, AtuxVariables.vNoUsuario, "0", "0.00",
                            "0.00", "0", "0.00", "0.00", "0", "0.00", "0.00", "0", "0.00", "0.00", "0", "0.00", "0.00", "0", "0.00",
                            "0.00", "0.00", "0.00", "0.00", "0.00", "0.00", String.valueOf(AtuxUtility.getDecimalNumber(txtMonto.getText().trim())),
                            "0.00", "0.00", "0", "0", "0.00", "0.00", "0", "0", "0", "0", "0", "0", "0", "0", "0"
                    );

                    AtuxSearch.setNuSecNumeracion(AtuxVariables.NUMERACION_MOVIMIENTO_CAJA);
                    JOptionPane.showMessageDialog(this, "La Caja fué aperturada CORRECTAMENTE !!!", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                    closeWindow(true);
                } catch (SQLException ex) {
                    AtuxUtility.liberarTransaccion();
                    logger.error("Error en AtuxSearch.insertaMovimientoCaja "+ IAperturaCaja.class.getName() + ex);                    
                    JOptionPane.showMessageDialog(this, "Error al grabar Movimiento Caja !!! - " + ex.getMessage(), "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                }
         } 
    }//GEN-LAST:event_bntAceptarActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        IAsignacionCaja caja = new IAsignacionCaja(new Frame(),true);
        caja.setVisible(true);
    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnTestigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestigoActionPerformed
        ITestigo testigo = new ITestigo(new Frame(),true);
        testigo.setVisible(true);
    }//GEN-LAST:event_btnTestigoActionPerformed
    
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.JButton bntValidar;
    private elaprendiz.gui.button.ButtonRect btnCaja;
    private elaprendiz.gui.button.ButtonRect btnTestigo;
    private elaprendiz.gui.varios.ClockDigital clockDigital;
    private javax.swing.JComboBox cmbCaja;
    private javax.swing.JComboBox cmbTurno;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JScrollPane jScrollPaneRelImpresoras;
    private javax.swing.JLabel lblCajero;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNroCaja;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblTipoCaja;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JLabel lblUsuario;
    private elaprendiz.gui.panel.PanelImage panelImageTurnoCaja;
    private elaprendiz.gui.panel.PanelRectTranslucido panelRectTranslucido1;
    private javax.swing.JPanel pnlAccionesLab;
    private javax.swing.JTable tblImpresoras;
    private elaprendiz.gui.textField.TextField txtCajero;
    private javax.swing.JTextField txtMonto;
    private elaprendiz.gui.passwordField.PasswordFieldRectIcon txtPass;
    private elaprendiz.gui.textField.TextField txtTipoCaja;
    private javax.swing.JFormattedTextField txtTipoCambio;
    private elaprendiz.gui.textField.TextFieldRectIcon txtUsuario;
    // End of variables declaration//GEN-END:variables
}
