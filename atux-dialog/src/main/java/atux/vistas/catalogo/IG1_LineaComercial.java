/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IImpuestoIGV.java
 *
 * Created on 22/12/2014, 04:20:23 PM
 */
package atux.vistas.catalogo;
import atux.controllers.CG1_LineaComercial;
import atux.modelgui.ModeloTablaG1_LineaComercial;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cesar Ruiz  PC
 */
public final class IG1_LineaComercial extends javax.swing.JInternalFrame {
    private CG1_LineaComercial cp;
    private ModeloTablaG1_LineaComercial mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    /** Creates new form IImpuestoIGV */
    public IG1_LineaComercial() {
        initComponents();
        cp = new CG1_LineaComercial();
        mtp = new ModeloTablaG1_LineaComercial(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado, ModeloTablaG1_LineaComercial.anchoColumnas);
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();        
        setFiltroTexto();        
        rbAtivos.setSelected(true);
        rbAtivosActionPerformed(null);
        txtLineaComercial.setBounds(279, 16, 283, 25);
        txtCodigo.setBounds(109, 16, 44, 25);
        
    }

    private boolean guardarActualizar() throws SQLException{
        txtLineaComercial.setText(txtLineaComercial.getText().toUpperCase());

        cp.getG1_LineaComercial().setCoNivel01(txtCodigo.getText());
        cp.getG1_LineaComercial().setCoNivelSuperior("1");
        cp.getG1_LineaComercial().setDeLineaProdErp(txtLineaComercial.getText());
        
        if (chbEstado.isSelected()){
            cp.getG1_LineaComercial().setEsLineaProdErp("A");
        }else{
            cp.getG1_LineaComercial().setEsLineaProdErp("I");
        }
        
        Boolean EstadoGuardar=false;
        if(!esActualizacion){
            cp.getG1_LineaComercial().setCoLineaProdErp(cp.getNuevoCodigoLinea());
            cp.getG1_LineaComercial().setIdCreaLineaProdErp(AtuxVariables.vIdUsuario);
            cp.getG1_LineaComercial().setFeCreaLineaProdErp(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getG1_LineaComercial().setCoLineaProdErp(cp.getNuevoCodigoLinea());
            EstadoGuardar = cp.guardarRegistro(cp.getG1_LineaComercial());
        }else{
            cp.getG1_LineaComercial().setIdModLineaProdErp(AtuxVariables.vIdUsuario);
            cp.getG1_LineaComercial().setFeModLineaProdErp(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));            
            int act = cp.actualizarRegistro(cp.getG1_LineaComercial());
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
            Logger.getLogger(IG1_LineaComercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    
    public void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_LETRAS, 1, false);
        Helper.setFiltraEntrada(txtLineaComercial.getDocument(), FiltraEntrada.NUM_LETRAS, 40, false);
    }    

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtCodigo.getText().equals(cp.getG1_LineaComercial().getCoNivel01())){
            return true;
        }else if(!this.txtLineaComercial.getText().equals(cp.getG1_LineaComercial().getDeLineaProdErp())){
            return true;
        }else if(chbEstado.isSelected() != ("A".equals(cp.getG1_LineaComercial().getEsLineaProdErp()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getG1_LineaComercial().getEsLineaProdErp()))){
            return true;
        }
        return false;
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
        if(tblListado.getSelectedRow() != -1){
            numRegistros = tblListado.getSelectedRow();
            cp.setG1_LineaComercial(mtp.getFila(tblListado.getSelectedRow()));
            setG1_LineaComercial();
        }
    }

    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtLineaComercial.setText("");
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
//        this.pnlEntradasCategorias_G01.setEnabled(false);
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G01, true, new Component[]{lblCodigo,lblDescrip,lblEstado},false,"");
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
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        this.bntParametrosReposicion.setEnabled(false);
    }
    private void DesActivarCasillas(){
//        this.pnlEntradasCategorias_G01.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G01, false, new Component[]{lblCodigo,lblDescrip,lblEstado},false,"");

        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
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
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true); 
        this.bntParametrosReposicion.setEnabled(true);
        
        ECampos.esObligatorio(this.pnlEntradasCategorias_G01,false);
        esActualizacion = false;
        this.pnlBuscadorCategorias_G01.setVisible(true);
        
        logger.info(txtCodigo.getText() + " - " + txtLineaComercial.getText());
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

    private void setG1_LineaComercial(){
        Limpiar();
        this.txtCodigo.setText(cp.getG1_LineaComercial().getCoNivel01());
        this.txtLineaComercial.setText(cp.getG1_LineaComercial().getDeLineaProdErp());

        if("A".equals(cp.getG1_LineaComercial().getEsLineaProdErp())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
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
        pnlEntradasCategorias_G01 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtLineaComercial = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        pnlBuscadorCategorias_G01 = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesCategorias_G01 = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        bntParametrosReposicion = new elaprendiz.gui.button.ButtonRect();

        setBorder(null);
        setTitle("Formulario de Set de Categorias - G1");
        setPreferredSize(new java.awt.Dimension(820, 435));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(780, 440));
        panelImage1.setLayout(null);

        pnlEntradasCategorias_G01.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Set de Categorias - G1", 1, 2));
        pnlEntradasCategorias_G01.setOpaque(false);
        pnlEntradasCategorias_G01.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradasCategorias_G01.setLayout(null);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("G1- Codigo:");
        pnlEntradasCategorias_G01.add(lblCodigo);
        lblCodigo.setBounds(16, 16, 83, 27);

        lblDescrip.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescrip.setText("Linea Comercial:");
        pnlEntradasCategorias_G01.add(lblDescrip);
        lblDescrip.setBounds(163, 17, 112, 24);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");
        pnlEntradasCategorias_G01.add(lblEstado);
        lblEstado.setBounds(580, 21, 53, 17);

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G01.add(txtCodigo);
        txtCodigo.setBounds(109, 18, 44, 25);

        txtLineaComercial.setEditable(false);
        txtLineaComercial.setDireccionDeSombra(30);
        txtLineaComercial.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtLineaComercial.setFont(new java.awt.Font("Arial", 0, 12));
        txtLineaComercial.setName("pdescrip"); // NOI18N
        txtLineaComercial.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G01.add(txtLineaComercial);
        txtLineaComercial.setBounds(279, 16, 283, 25);

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
        pnlEntradasCategorias_G01.add(chbEstado);
        chbEstado.setBounds(639, 17, 100, 25);

        panelImage1.add(pnlEntradasCategorias_G01);
        pnlEntradasCategorias_G01.setBounds(14, 6, 783, 50);

        pnlBuscadorCategorias_G01.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorCategorias_G01.setOpaque(false);
        pnlBuscadorCategorias_G01.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorCategorias_G01.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G01.add(btnPrimero);
        btnPrimero.setBounds(12, 2, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G01.add(btnAnterior);
        btnAnterior.setBounds(66, 2, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G01.add(btnSiguiente);
        btnSiguiente.setBounds(116, 2, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G01.add(btnUltimo);
        btnUltimo.setBounds(169, 2, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G01.add(rbTodos);
        rbTodos.setBounds(223, 2, 69, 25);

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
        pnlBuscadorCategorias_G01.add(rbAtivos);
        rbAtivos.setBounds(302, 2, 77, 25);

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
        pnlBuscadorCategorias_G01.add(rbNoActivos);
        rbNoActivos.setBounds(389, 2, 101, 25);

        panelImage1.add(pnlBuscadorCategorias_G01);
        pnlBuscadorCategorias_G01.setBounds(80, 60, 504, 31);

        pnlAccionesCategorias_G01.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesCategorias_G01.setOpaque(false);
        pnlAccionesCategorias_G01.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesCategorias_G01.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G01.add(btnNuevo);
        btnNuevo.setBounds(12, 2, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G01.add(btnModificar);
        btnModificar.setBounds(96, 2, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G01.add(btnGuardar);
        btnGuardar.setBounds(200, 2, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G01.add(btnCancelar);
        btnCancelar.setBounds(295, 2, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G01.add(btnSalir);
        btnSalir.setBounds(395, 2, 88, 25);

        panelImage1.add(pnlAccionesCategorias_G01);
        pnlAccionesCategorias_G01.setBounds(160, 370, 490, 29);

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblListado);

        panelImage1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 791, 260);

        bntParametrosReposicion.setBackground(new java.awt.Color(0, 204, 0));
        bntParametrosReposicion.setText("*** REPOSICION");
        bntParametrosReposicion.setAlignmentX(0.1F);
        bntParametrosReposicion.setAlignmentY(0.1F);
        bntParametrosReposicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntParametrosReposicionActionPerformed(evt);
            }
        });
        panelImage1.add(bntParametrosReposicion);
        bntParametrosReposicion.setBounds(650, 60, 140, 31);

        getContentPane().add(panelImage1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setG1_LineaComercial(mtp.getFila(numRegistros));
        setG1_LineaComercial();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setG1_LineaComercial(mtp.getFila(numRegistros));
        setG1_LineaComercial();

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setG1_LineaComercial(mtp.getFila(numRegistros));
        setG1_LineaComercial();

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setG1_LineaComercial(mtp.getFila(numRegistros));
        setG1_LineaComercial();

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaG1_LineaComercial(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG1_LineaComercial.anchoColumnas);
        chbSetActivo(true);
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaG1_LineaComercial(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG1_LineaComercial.anchoColumnas);
        chbSetActivo(true);
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaG1_LineaComercial(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG1_LineaComercial.anchoColumnas);
        chbSetActivo(false);
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Limpiar();
        esActualizacion = false;        
        cp.getG1_LineaComercial().setCoLineaProdErp(cp.getNuevoCodigoLinea());
        txtCodigo.requestFocus();
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
        if(ECampos.esObligatorio(this.pnlEntradasCategorias_G01,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 

        if(!this.verficarCambios()){
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasCategorias_G01,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaG1_LineaComercial(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaG1_LineaComercial(tipoSeleccion,inicioPag,finalPag);
        }
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG1_LineaComercial.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG1_LineaComercial.anchoColumnas);
        DesActivarCasillas();
        tblListado.requestFocus();
        
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

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void bntParametrosReposicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntParametrosReposicionActionPerformed
        IParamXReposicion pvc = new IParamXReposicion();
        pvc.setCodigoProducto(txtCodigo.getText());
        pvc.setDescripcionProducto(txtLineaComercial.getText());
        pvc.setTipoParametroReposicion("LN");
        pvc.CargaDatos();
        pvc.setPreferredSize(new Dimension(630, 480)); 
        
        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando todas los Parametros de Reposición por Producto";
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{pvc.getAviso()},null);
}//GEN-LAST:event_bntParametrosReposicionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntParametrosReposicion;
    private elaprendiz.gui.button.ButtonRect btnAnterior;
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
    private javax.swing.JPanel pnlAccionesCategorias_G01;
    private javax.swing.JPanel pnlBuscadorCategorias_G01;
    private javax.swing.JPanel pnlEntradasCategorias_G01;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtLineaComercial;
    // End of variables declaration//GEN-END:variables
}
