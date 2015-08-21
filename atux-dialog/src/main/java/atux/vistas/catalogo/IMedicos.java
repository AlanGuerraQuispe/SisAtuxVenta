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
import atux.controllers.CMedicos;
import atux.controllers.CTipoDocumento;
import atux.modelgui.ModeloTablaMedicos;
import atux.modelbd.TipoDocumento;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarMedicos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
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
public class IMedicos extends javax.swing.JInternalFrame {
    private CMedicos cp;
    private DefaultComboBoxModel mTipoDocumento;
    private ModeloTablaMedicos mtp;
    private CTipoDocumento controllerTipoDocumento;    
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    /** Creates new form IImpuestoIGV */
    public IMedicos() {
        initComponents();
        cp = new CMedicos();
        mtp = new ModeloTablaMedicos(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();

        tblMedicos.setModel(mtp);
        
        controllerTipoDocumento = new CTipoDocumento();          
        mTipoDocumento = new DefaultComboBoxModel(controllerTipoDocumento.getTipoDocumento().toArray());        
        this.cmbTipoDocumento.setModel(mTipoDocumento);        
        this.cmbTipoDocumento.setSelectedIndex(0);                       
        
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        //Helper.ajustarAnchoColumnas(tblMedicos);
        setFiltroTexto();        
        setEventSelectionModel(tblMedicos.getSelectionModel());
        DesActivarCasillas();
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
        Helper.setFiltraEntrada(txtAp_Paterno.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtAp_Materno.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtNombres.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtCMP.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);        
        Helper.setFiltraEntrada(txtNroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 11, false);
        Helper.setFiltraEntrada(txtTelefono.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, false);
        Helper.setFiltraEntrada(txtemail.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
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
        if(tblMedicos.getSelectedRow() != -1){
            numRegistros = tblMedicos.getSelectedRow();
            cp.setMedicos(mtp.getFila(tblMedicos.getSelectedRow()));
            setMedicos();
       }
    }
    
    private void setMedicos(){
        Limpiar();
        this.txtCodigo.setText(String.valueOf(cp.getMedicos().getCoMedico()));
        this.txtCMP.setText(cp.getMedicos().getNuCmp());
        this.txtAp_Paterno.setText(cp.getMedicos().getApPaternoMedico());
        this.txtAp_Materno.setText(cp.getMedicos().getApMaternoMedico());
        this.txtNombres.setText(cp.getMedicos().getNoMedico());
        this.txtTelefono.setText(cp.getMedicos().getNuTelefono());
        this.txtemail.setText(cp.getMedicos().getNoEmail());
        int ubica = 0;
        if (cp.getMedicos().getTiDocIdentidad() != null){
            ubica =Integer.parseInt(cp.getMedicos().getTiDocIdentidad());
        }

        cmbTipoDocumento.setSelectedIndex(ubica);
        this.txtNroDocumento.setText(cp.getMedicos().getNuDocIdentidad());
        
        if("A".equals(cp.getMedicos().getEsMedico())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtAp_Paterno.setText("");
        this.txtAp_Materno.setText("");
        this.txtCMP.setText("");
        this.txtNombres.setText("");
        this.txtNroDocumento.setText("");
        this.txtTelefono.setText("");
        this.txtemail.setText("");
        this.chbEstado.setSelected(false);
        chbSetActivo(false);
    }
    
    private void ActivarCasillas(){
        pnlEntradasMedicos.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradasMedicos, true, new Component[]{lblApPaterno, lblApeMaterno, lblCMP, lblCodigo, lblEmail, lblEstado, lblNombres, lblNroDoc, lblTelefono, lblTipoDoc},false,"");        
        this.tblMedicos.setEnabled(false);
        this.tblMedicos.clearSelection();        
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
        this.btnBuscar.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        
        this.cmbTipoDocumento.setEnabled(true);
        btnEspecialidad.setEnabled(false);
    }
    private void DesActivarCasillas(){
        this.pnlEntradasMedicos.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasMedicos, false, new Component[]{lblApPaterno, lblApeMaterno, lblCMP, lblCodigo, lblEmail, lblEstado, lblNombres, lblNroDoc, lblTelefono, lblTipoDoc},false,"");        
        this.tblMedicos.setEnabled(true);
        this.tblMedicos.clearSelection();
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
        this.btnBuscar.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        esActualizacion = false;
        this.cmbTipoDocumento.setEnabled(false);
        btnEspecialidad.setEnabled(true);

        logger.info(txtCodigo.getText() + " - " + txtAp_Paterno.getText() + " " + txtAp_Materno.getText() + " " + txtNombres.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtCodigo.getText().equals(String.valueOf(cp.getMedicos().getCoMedico()))){
            return true;
        }else if(!this.txtAp_Paterno.getText().equals(String.valueOf(cp.getMedicos().getApPaternoMedico()))){
            return true;
        }else if(!this.txtAp_Materno.getText().equals(String.valueOf(cp.getMedicos().getApMaternoMedico()))){
            return true;
        }else if(!this.txtCMP.getText().equals(String.valueOf(cp.getMedicos().getNuCmp()))){
            return true;
        }else if(!this.txtNombres.getText().equals(String.valueOf(cp.getMedicos().getNoMedico()))){
            return true;
        }else if(!this.txtNroDocumento.getText().equals(String.valueOf(cp.getMedicos().getNuDocIdentidad()))){
            return true;
        }else if(!this.txtTelefono.getText().equals(String.valueOf(cp.getMedicos().getNuTelefono()))){
            return true;
        }else if(!this.txtemail.getText().equals(String.valueOf(cp.getMedicos().getNoEmail()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getMedicos().getEsMedico()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{
        txtAp_Paterno.setText(txtAp_Paterno.getText().toUpperCase());
        txtAp_Materno.setText(txtAp_Materno.getText().toUpperCase());
        txtNombres.setText(txtNombres.getText().toUpperCase());
        txtCMP.setText(txtCMP.getText().toUpperCase());
        
        cp.getMedicos().setCoMedico(txtCodigo.getText());
        cp.getMedicos().setDeMedico(txtAp_Paterno.getText().trim() + " " + txtAp_Materno.getText().trim() + " " + txtNombres.getText().trim() );
        cp.getMedicos().setApPaternoMedico(txtAp_Paterno.getText());
        cp.getMedicos().setApMaternoMedico(txtAp_Materno.getText());
        cp.getMedicos().setNoMedico(txtNombres.getText());
        cp.getMedicos().setNuCmp(txtCMP.getText());
        cp.getMedicos().setNuDocIdentidad(txtNroDocumento.getText());
        cp.getMedicos().setNuTelefono(txtTelefono.getText());
        cp.getMedicos().setNoEmail(txtemail.getText());
        cp.getMedicos().setTiDocIdentidad(((TipoDocumento)cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad());

        if (chbEstado.isSelected()){
            cp.getMedicos().setEsMedico("A");
        }else{
            cp.getMedicos().setEsMedico("I");
        }
        
        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getMedicos().setIdCreaMedico(AtuxVariables.vIdUsuario);
            cp.getMedicos().setFeCreaMedico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            txtCodigo.setText(cp.getNuevoCodigo());            
            cp.getMedicos().setCoMedico(txtCodigo.getText());
            EstadoGuardar = cp.guardarRegistro(cp.getMedicos());
        }else{
            cp.getMedicos().setIdModMedico(AtuxVariables.vIdUsuario);
            cp.getMedicos().setFeModMedico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getMedicos());
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
            Logger.getLogger(IMedicos.class.getName()).log(Level.SEVERE, null, ex);
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
        pnlEntradasMedicos = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblCMP = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblApPaterno = new javax.swing.JLabel();
        lblApeMaterno = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtCMP = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        txtAp_Paterno = new elaprendiz.gui.textField.TextField();
        txtAp_Materno = new elaprendiz.gui.textField.TextField();
        txtNombres = new elaprendiz.gui.textField.TextField();
        txtemail = new elaprendiz.gui.textField.TextField();
        txtTelefono = new elaprendiz.gui.textField.TextField();
        btnEspecialidad = new elaprendiz.gui.button.ButtonRect();
        lblTipoDoc = new javax.swing.JLabel();
        lblNroDoc = new javax.swing.JLabel();
        txtNroDocumento = new elaprendiz.gui.textField.TextField();
        cmbTipoDocumento = new elaprendiz.gui.comboBox.ComboBoxRect();
        pnlBuscadorMedicos = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesMedicos = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();

        setBorder(null);
        setTitle("Formulario de Medicos");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasMedicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Medicos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasMedicos.setEnabled(false);
        pnlEntradasMedicos.setOpaque(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");

        lblCMP.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCMP.setText("CMP:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        lblApPaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApPaterno.setText("Ap.Paterno:");

        lblApeMaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApeMaterno.setText("Ap.Materno:");

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setText("Nombres:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmail.setText("E-mail:");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTelefono.setText("Teléfono:");

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

        txtCMP.setEditable(false);
        txtCMP.setDireccionDeSombra(30);
        txtCMP.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCMP.setFont(new java.awt.Font("Arial", 0, 12));
        txtCMP.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCMPKeyReleased(evt);
            }
        });

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

        txtAp_Paterno.setEditable(false);
        txtAp_Paterno.setDireccionDeSombra(30);
        txtAp_Paterno.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtAp_Paterno.setFont(new java.awt.Font("Arial", 0, 12));
        txtAp_Paterno.setName("pdescrip"); // NOI18N
        txtAp_Paterno.setPreferredSize(new java.awt.Dimension(120, 25));
        txtAp_Paterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAp_PaternoFocusLost(evt);
            }
        });
        txtAp_Paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAp_PaternoKeyReleased(evt);
            }
        });

        txtAp_Materno.setEditable(false);
        txtAp_Materno.setDireccionDeSombra(30);
        txtAp_Materno.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtAp_Materno.setFont(new java.awt.Font("Arial", 0, 12));
        txtAp_Materno.setName("pdescrip"); // NOI18N
        txtAp_Materno.setPreferredSize(new java.awt.Dimension(120, 25));
        txtAp_Materno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAp_MaternoFocusLost(evt);
            }
        });
        txtAp_Materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAp_MaternoKeyReleased(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setDireccionDeSombra(30);
        txtNombres.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtNombres.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombres.setName("pdescrip"); // NOI18N
        txtNombres.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombresFocusLost(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });

        txtemail.setEditable(false);
        txtemail.setDireccionDeSombra(30);
        txtemail.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtemail.setFont(new java.awt.Font("Arial", 0, 12));
        txtemail.setPreferredSize(new java.awt.Dimension(120, 25));
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        txtTelefono.setEditable(false);
        txtTelefono.setDireccionDeSombra(30);
        txtTelefono.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTelefono.setFont(new java.awt.Font("Arial", 0, 12));
        txtTelefono.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        btnEspecialidad.setBackground(new java.awt.Color(51, 153, 255));
        btnEspecialidad.setText("Especialidad");
        btnEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspecialidadActionPerformed(evt);
            }
        });

        lblTipoDoc.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoDoc.setText("Tipo Doc:");

        lblNroDoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNroDoc.setText("Nro Doc:");

        txtNroDocumento.setEditable(false);
        txtNroDocumento.setDireccionDeSombra(30);
        txtNroDocumento.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtNroDocumento.setFont(new java.awt.Font("Arial", 0, 12));
        txtNroDocumento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroDocumentoKeyReleased(evt);
            }
        });

        cmbTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoDocumentoActionPerformed(evt);
            }
        });
        cmbTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoDocumentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasMedicosLayout = new javax.swing.GroupLayout(pnlEntradasMedicos);
        pnlEntradasMedicos.setLayout(pnlEntradasMedicosLayout);
        pnlEntradasMedicosLayout.setHorizontalGroup(
            pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblCodigo)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(362, 362, 362)
                        .addComponent(lblCMP)
                        .addGap(4, 4, 4)
                        .addComponent(txtCMP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(lblEstado)
                        .addGap(6, 6, 6)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addComponent(lblApPaterno)
                        .addGap(4, 4, 4)
                        .addComponent(txtAp_Paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblApeMaterno)
                        .addGap(14, 14, 14)
                        .addComponent(txtAp_Materno, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblNombres)
                        .addGap(4, 4, 4)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lblEmail)
                        .addGap(13, 13, 13)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblTelefono)
                        .addGap(4, 4, 4)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblTipoDoc)
                        .addGap(10, 10, 10)
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(lblNroDoc)
                        .addGap(9, 9, 9)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEntradasMedicosLayout.setVerticalGroup(
            pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCMP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblEstado))
                            .addComponent(txtCMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAp_Paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblApeMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAp_Materno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(pnlEntradasMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasMedicosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBuscadorMedicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorMedicos.setOpaque(false);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(102, 204, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnlBuscadorMedicosLayout = new javax.swing.GroupLayout(pnlBuscadorMedicos);
        pnlBuscadorMedicos.setLayout(pnlBuscadorMedicosLayout);
        pnlBuscadorMedicosLayout.setHorizontalGroup(
            pnlBuscadorMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
            .addGroup(pnlBuscadorMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorMedicosLayout.createSequentialGroup()
                    .addGap(0, 8, Short.MAX_VALUE)
                    .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(rbTodos)
                    .addGap(5, 5, 5)
                    .addComponent(rbAtivos)
                    .addGap(5, 5, 5)
                    .addComponent(rbNoActivos)
                    .addGap(0, 8, Short.MAX_VALUE)))
        );
        pnlBuscadorMedicosLayout.setVerticalGroup(
            pnlBuscadorMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(pnlBuscadorMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorMedicosLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlBuscadorMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbTodos)
                        .addComponent(rbAtivos)
                        .addComponent(rbNoActivos))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlAccionesMedicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesMedicos.setOpaque(false);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesMedicosLayout = new javax.swing.GroupLayout(pnlAccionesMedicos);
        pnlAccionesMedicos.setLayout(pnlAccionesMedicosLayout);
        pnlAccionesMedicosLayout.setHorizontalGroup(
            pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAccionesMedicosLayout.setVerticalGroup(
            pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMedicos);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                    .addComponent(pnlAccionesMedicos, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorMedicos, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradasMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(pnlEntradasMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblMedicos.getRowCount()-1;
        numRegistros=0;
        cp.setMedicos(mtp.getFila(numRegistros));
        setMedicos();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblMedicos.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setMedicos(mtp.getFila(numRegistros));
        setMedicos();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarMedicos pvc = new BuscarMedicos(tipoSeleccion,this);
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccion==-1?"Mostrando todos los Medicos existentes":(tipoSeleccion==1?"Mostrando todo los Medicos activos":"Mostrando todo los Medicos No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getMedicos() != null){
           cp.setMedicos(pvc.getMedicos());
           setMedicos();
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblMedicos.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setMedicos(mtp.getFila(numRegistros));
        setMedicos();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblMedicos.getRowCount()-1;
        numRegistros=finalPag;
        cp.setMedicos(mtp.getFila(numRegistros));
        setMedicos();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaMedicos(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaMedicos(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaMedicos(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        ActivarCasillas();
        txtCodigo.setText(cp.getNuevoCodigo());
        txtAp_Paterno.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasMedicos,true)){
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
            ECampos.esObligatorio(this.pnlEntradasMedicos,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaMedicos(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaMedicos(tipoSeleccion,inicioPag,finalPag);
        }
        tblMedicos.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblMedicos,ModeloTablaMedicos.anchoColumnas);
        DesActivarCasillas();
        tblMedicos.requestFocus();
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
        case KeyEvent.VK_ENTER: txtCMP.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtAp_PaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_PaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtAp_Materno.requestFocus();
             break;
    }
}//GEN-LAST:event_txtAp_PaternoKeyReleased

private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtemail.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNombresKeyReleased

private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtTelefonoKeyReleased

private void cmbTipoDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNroDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoDocumentoKeyReleased

private void txtCMPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMPKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCMPKeyReleased

private void txtAp_MaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_MaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombres.requestFocus();
             break;
    }
}//GEN-LAST:event_txtAp_MaternoKeyReleased

private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtTelefono.requestFocus();
             break;
    }
}//GEN-LAST:event_txtemailKeyReleased

private void txtNroDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnEspecialidad.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNroDocumentoKeyReleased

private void cmbTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoActionPerformed
        int Largo = Integer.parseInt(((TipoDocumento)cmbTipoDocumento.getSelectedItem()).getNuCaracteres()); 
        if (Largo == 0){
            txtNroDocumento.setEnabled(false);
        }else{
            txtNroDocumento.setEnabled(true);
            Helper.setFiltraEntrada(txtNroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, Largo, false);
        }
        
}//GEN-LAST:event_cmbTipoDocumentoActionPerformed

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
    chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void txtAp_PaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAp_PaternoFocusLost
    txtAp_Paterno.setText(txtAp_Paterno.getText().toUpperCase());
}//GEN-LAST:event_txtAp_PaternoFocusLost

private void txtAp_MaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAp_MaternoFocusLost
    txtAp_Materno.setText(txtAp_Materno.getText().toUpperCase());
}//GEN-LAST:event_txtAp_MaternoFocusLost

private void txtNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusLost
    txtNombres.setText(txtNombres.getText().toUpperCase());
}//GEN-LAST:event_txtNombresFocusLost

private void btnEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspecialidadActionPerformed
        IMedicosEspecialidad pvc = new IMedicosEspecialidad(tipoSeleccion,this);
        pvc.setCodigoMedico(cp.getMedicos().getCoMedico());
        pvc.setCMP(cp.getMedicos().getNuCmp());
        pvc.setApelldosNombres(cp.getMedicos().getDeMedico());
        pvc.CargaDatos();
        
        pvc.setPreferredSize(new Dimension(630, 370));
        
        //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
        String msj = "Mostrando todas las Especialidades del Medico";
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            -1, null, new Object[]{pvc.getAviso()},null);
        
        if(pvc.getMedicos() != null){
           cp.setMedicos(pvc.getMedicos());
           setMedicos();
        }
        
}//GEN-LAST:event_btnEspecialidadActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtAp_Materno.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnEspecialidad;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoDocumento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApPaterno;
    private javax.swing.JLabel lblApeMaterno;
    private javax.swing.JLabel lblCMP;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNroDoc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoDoc;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesMedicos;
    private javax.swing.JPanel pnlBuscadorMedicos;
    private javax.swing.JPanel pnlEntradasMedicos;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblMedicos;
    private elaprendiz.gui.textField.TextField txtAp_Materno;
    private elaprendiz.gui.textField.TextField txtAp_Paterno;
    private elaprendiz.gui.textField.TextField txtCMP;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtNombres;
    private elaprendiz.gui.textField.TextField txtNroDocumento;
    private elaprendiz.gui.textField.TextField txtTelefono;
    private elaprendiz.gui.textField.TextField txtemail;
    // End of variables declaration//GEN-END:variables
}
