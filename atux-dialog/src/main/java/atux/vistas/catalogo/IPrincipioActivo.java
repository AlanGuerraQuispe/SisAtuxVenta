package atux.vistas.catalogo;

import atux.controllers.CPrincipioActivo;
import atux.modelgui.ModeloTablaPrincipioActivo;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarPrincipioActivo;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 *
 */
public class IPrincipioActivo extends javax.swing.JInternalFrame {
    private CPrincipioActivo cp;
    private ModeloTablaPrincipioActivo mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    /** Creates new form IImpuestoIGV */
    public IPrincipioActivo() {
        initComponents();
        cp = new CPrincipioActivo();
        mtp = new ModeloTablaPrincipioActivo(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();

        tblPrincipioActivo.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
//        Helper.ajustarAnchoColumnas(tblPrincipioActivo);
        setFiltroTexto();        
        setEventSelectionModel(tblPrincipioActivo.getSelectionModel());
        DesActivarCasillas();
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
        Helper.setFiltraEntrada(txtDescrip.getDocument(), FiltraEntrada.NUM_LETRAS, 35, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm){
        ListSelectionListener lsl = new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }
    
    private void selectionEvent(ListSelectionEvent e){
        if(tblPrincipioActivo.getSelectedRow() != -1){
            numRegistros = tblPrincipioActivo.getSelectedRow();
            cp.setPrincipioActivo(mtp.getFila(tblPrincipioActivo.getSelectedRow()));
            setPrincipioActivo();
       }
    }
    
    private void setPrincipioActivo(){
        Limpiar();
        this.txtCodigo.setText(String.valueOf(cp.getPrincipioActivo().getCoPrincipioActivo()));
        this.txtDescrip.setText(String.valueOf(cp.getPrincipioActivo().getDePrincipioActivo()));

        if("A".equals(cp.getPrincipioActivo().getEsPrincipioActivo())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtDescrip.setText("");
        this.chbEstado.setSelected(false);
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
        pnlEntradasPActivo.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradasPActivo, true, new Component[]{lblCodigo,lblDescrip,lblEstado},false,"");        
        this.tblPrincipioActivo.setEnabled(false);
        this.tblPrincipioActivo.clearSelection();        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.btnBucar.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
    }
    private void DesActivarCasillas(){
        this.pnlEntradasPActivo.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasPActivo, false, new Component[]{lblCodigo,lblDescrip,lblEstado},true,"");        
        this.tblPrincipioActivo.setEnabled(true);
        this.tblPrincipioActivo.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.btnBucar.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        esActualizacion = false;

        logger.info(txtCodigo.getText() + " " + txtDescrip.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtCodigo.getText().equals(String.valueOf(cp.getPrincipioActivo().getCoPrincipioActivo()))){
            return true;
        }else if(!this.txtDescrip.getText().equals(String.valueOf(cp.getPrincipioActivo().getDePrincipioActivo()
                ))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getPrincipioActivo().getEsPrincipioActivo()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{
        txtDescrip.setText(txtDescrip.getText().toUpperCase());
                
        txtCodigo.setText(cp.getNuevoCodigo());
        cp.getPrincipioActivo().setCoPrincipioActivo(txtCodigo.getText());
        cp.getPrincipioActivo().setDePrincipioActivo(txtDescrip.getText());

        if (chbEstado.isSelected()){
            cp.getPrincipioActivo().setEsPrincipioActivo("A");
        }else{
            cp.getPrincipioActivo().setEsPrincipioActivo("I");
        }
        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getPrincipioActivo().setIdCreaPrincipioActivo(AtuxVariables.vIdUsuario);
            cp.getPrincipioActivo().setFeCreaPrincipioActivo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            cp.getPrincipioActivo().setCoPrincipioActivo(cp.getNuevoCodigo());
            EstadoGuardar = cp.guardarRegistro(cp.getPrincipioActivo());
        }else{
            cp.getPrincipioActivo().setIdModPrincipioActivo(AtuxVariables.vIdUsuario);
            cp.getPrincipioActivo().setFeModPrincipioActivo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getPrincipioActivo());
            if (act ==1) {
                EstadoGuardar = true;
            }
        }
        
        if(EstadoGuardar){
            EstadoGuardar = true; 
        }else{
            EstadoGuardar = false; 
        }
        return EstadoGuardar;
    }
    
    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IPrincipioActivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void chbSetActivo(boolean opcion){
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104,204,0));
        chbEstado.setForeground(Color.BLACK);
        if(!opcion){
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradasPActivo = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescrip = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        pnlBuscadorPActivo = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        btnBucar = new elaprendiz.gui.button.ButtonRect();
        pnlAccionesPActivo = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrincipioActivo = new javax.swing.JTable();

        setBorder(null);
        setTitle("Formulario de Principio Activo");
        setPreferredSize(new java.awt.Dimension(780, 420));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(780, 420));
        panelImage1.setLayout(null);

        pnlEntradasPActivo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Principio Activo", 1, 2));
        pnlEntradasPActivo.setOpaque(false);
        pnlEntradasPActivo.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradasPActivo.setLayout(null);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");
        pnlEntradasPActivo.add(lblCodigo);
        lblCodigo.setBounds(16, 16, 54, 27);

        lblDescrip.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescrip.setText("Descripcion:");
        pnlEntradasPActivo.add(lblDescrip);
        lblDescrip.setBounds(167, 17, 83, 24);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");
        pnlEntradasPActivo.add(lblEstado);
        lblEstado.setBounds(559, 21, 53, 17);

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        pnlEntradasPActivo.add(txtCodigo);
        txtCodigo.setBounds(80, 18, 83, 25);

        txtDescrip.setEditable(false);
        txtDescrip.setDireccionDeSombra(30);
        txtDescrip.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescrip.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescrip.setName("pdescrip"); // NOI18N
        txtDescrip.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDescrip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripKeyReleased(evt);
            }
        });
        pnlEntradasPActivo.add(txtDescrip);
        txtDescrip.setBounds(254, 18, 301, 25);

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setEnabled(false);
        chbEstado.setPreferredSize(new java.awt.Dimension(100, 25));
        chbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEstadoActionPerformed(evt);
            }
        });
        chbEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbEstadoKeyReleased(evt);
            }
        });
        pnlEntradasPActivo.add(chbEstado);
        chbEstado.setBounds(618, 17, 100, 25);

        panelImage1.add(pnlEntradasPActivo);
        pnlEntradasPActivo.setBounds(10, 11, 748, 55);

        pnlBuscadorPActivo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorPActivo.setOpaque(false);
        pnlBuscadorPActivo.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorPActivo.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(btnPrimero);
        btnPrimero.setBounds(12, 2, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(btnAnterior);
        btnAnterior.setBounds(66, 2, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(btnSiguiente);
        btnSiguiente.setBounds(199, 2, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(btnUltimo);
        btnUltimo.setBounds(245, 2, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(rbTodos);
        rbTodos.setBounds(311, 2, 69, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(rbAtivos);
        rbAtivos.setBounds(385, 2, 77, 25);

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbNoActivos);
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(rbNoActivos);
        rbNoActivos.setBounds(464, 2, 101, 25);

        btnBucar.setBackground(new java.awt.Color(102, 204, 0));
        btnBucar.setText("Buscar");
        btnBucar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBucarActionPerformed(evt);
            }
        });
        pnlBuscadorPActivo.add(btnBucar);
        btnBucar.setBounds(112, 2, 81, 25);

        panelImage1.add(pnlBuscadorPActivo);
        pnlBuscadorPActivo.setBounds(85, 72, 597, 29);

        pnlAccionesPActivo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesPActivo.setOpaque(false);
        pnlAccionesPActivo.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesPActivo.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesPActivo.add(btnNuevo);
        btnNuevo.setBounds(12, 2, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesPActivo.add(btnModificar);
        btnModificar.setBounds(96, 2, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesPActivo.add(btnGuardar);
        btnGuardar.setBounds(200, 2, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesPActivo.add(btnCancelar);
        btnCancelar.setBounds(295, 2, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesPActivo.add(btnSalir);
        btnSalir.setBounds(395, 2, 88, 25);

        panelImage1.add(pnlAccionesPActivo);
        pnlAccionesPActivo.setBounds(132, 348, 503, 29);

        tblPrincipioActivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
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
        jScrollPane1.setViewportView(tblPrincipioActivo);

        panelImage1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 107, 748, 230);

        getContentPane().add(panelImage1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblPrincipioActivo.getRowCount()-1;
        numRegistros=0;
        cp.setPrincipioActivo(mtp.getFila(numRegistros));
        setPrincipioActivo();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblPrincipioActivo.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setPrincipioActivo(mtp.getFila(numRegistros));
        setPrincipioActivo();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnBucarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBucarActionPerformed
        BuscarPrincipioActivo pActivo = new BuscarPrincipioActivo(tipoSeleccion);
        JLabel aviso = pActivo.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Principios Activos existentes":(tipoSeleccion==1?"Mostrando todo los Principios Activos activos":"Mostrando todo los Principios Activos No activos"));
        JOptionPane.showInternalOptionDialog(this, pActivo, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pActivo.getPrincipioActivo() != null){
           cp.setPrincipioActivo(pActivo.getPrincipioActivo());
           setPrincipioActivo();
        }
}//GEN-LAST:event_btnBucarActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblPrincipioActivo.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setPrincipioActivo(mtp.getFila(numRegistros));
        setPrincipioActivo();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblPrincipioActivo.getRowCount()-1;
        numRegistros=finalPag;
        cp.setPrincipioActivo(mtp.getFila(numRegistros));
        setPrincipioActivo();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaPrincipioActivo(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblPrincipioActivo.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaPrincipioActivo(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblPrincipioActivo.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaPrincipioActivo(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblPrincipioActivo.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;
        Limpiar();
        txtCodigo.setText(cp.getNuevoCodigo());
//        cp.getPrincipioActivo().setCoPrincipioActivo(cp.getNuevoCodigo());
        txtDescrip.requestFocus();
        ActivarCasillas();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasPActivo,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IPrincipioActivo.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasPActivo,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaPrincipioActivo(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaPrincipioActivo(tipoSeleccion,inicioPag,finalPag);
        }
        tblPrincipioActivo.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblPrincipioActivo,ModeloTablaPrincipioActivo.anchoColumnas);
        DesActivarCasillas();
        tblPrincipioActivo.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }

        DesActivarCasillas();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDescrip.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtDescripKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDescripKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
    chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBucar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblEstado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesPActivo;
    private javax.swing.JPanel pnlBuscadorPActivo;
    private javax.swing.JPanel pnlEntradasPActivo;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblPrincipioActivo;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescrip;
    // End of variables declaration//GEN-END:variables
}
