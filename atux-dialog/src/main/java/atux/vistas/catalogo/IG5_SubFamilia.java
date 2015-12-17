package atux.vistas.catalogo;

import atux.controllers.CG1_LineaComercial;
import atux.controllers.CG2_Division;
import atux.controllers.CG3_SubDivision;
import atux.controllers.CG4_Familia;
import atux.controllers.CG5_SubFamilia;
import atux.modelgui.ModeloTablaG5_SubFamilia;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarG1_LineaComercial;
import atux.vistas.buscar.BuscarG2_Division;
import atux.vistas.buscar.BuscarG3_SubDivision;
import atux.vistas.buscar.BuscarG4_Familia;
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
 * @author Cesar Ruiz  PC
 */
public class IG5_SubFamilia extends javax.swing.JInternalFrame {
    private CG5_SubFamilia cp;
    private CG4_Familia cpF;
    private CG3_SubDivision cpSDv;
    private CG2_Division cpDv;
    private CG1_LineaComercial cpLC;
    private ModeloTablaG5_SubFamilia mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    /** Creates new form IImpuestoIGV */
    public IG5_SubFamilia() {
        initComponents();
        Limpiar("Si");
        DesActivarCasillas();        
        setFiltroTexto();        
        rbAtivos.setSelected(true);
        LlenaGrid();
    }

    private void Limpiar(String Todo){
        if ("Si".equals(Todo)){
            this.txtCodigoG1.setText("");
            this.txtLineaComercial.setText("");            
            this.txtCodigoG2.setText("");
            this.txtDivision.setText("");
            this.txtCodigoG3.setText("");
            this.txtSub_Division.setText("");
            this.txtCodigoG4.setText("");
            this.txtFamilia.setText("");
        }
        this.txtCodigoG5.setText("");
        this.txtSub_Familia.setText("");
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
        this.pnlEntradasCategorias_G05.setEnabled(false);
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G05, true, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblCodigoG4,lblCodigoG5,lblDescripG1,lblDescripG2,lblDescripG3,lblDescripG4,lblDescripG5,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision,txtCodigoG3,txtCodigoG4},false,"");        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.btnSeleccionarG1.setEnabled(false);
        this.btnSeleccionarG2.setEnabled(false);
        this.btnSeleccionarG3.setEnabled(false);
        this.btnSeleccionarG4.setEnabled(false);
        this.chbEstado.setEnabled(true);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
    }

    private void DesActivarCasillas(){
        this.pnlEntradasCategorias_G05.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G05, false, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblCodigoG4,lblCodigoG5,lblDescripG1,lblDescripG2,lblDescripG3,lblDescripG4,lblDescripG5,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision,txtCodigoG3,txtCodigoG4},false,"");                
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.btnSeleccionarG1.setEnabled(true);
        this.btnSeleccionarG2.setEnabled(true);
        this.btnSeleccionarG3.setEnabled(true);
        this.btnSeleccionarG4.setEnabled(true);
        this.chbEstado.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        
        
        ECampos.esObligatorio(this.pnlEntradasCategorias_G05,false);
        esActualizacion = false;
        this.pnlBuscadorCategorias_G05.setVisible(true);
        
        logger.info(txtCodigoG4.getText() + " - " + txtFamilia.getText());
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

    public void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigoG5.getDocument(), FiltraEntrada.NUM_LETRAS, 9, false);
        Helper.setFiltraEntrada(txtSub_Familia.getDocument(), FiltraEntrada.NUM_LETRAS, 40, false);
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
            cp.setG5_SubFamilia(mtp.getFila(tblListado.getSelectedRow()));
            setG5_SubFamilia();
        }
    }
    
    private void setG5_SubFamilia(){
        Limpiar("No");
        this.txtCodigoG4.setText(cp.getG5_SubFamilia().getCoNivelSuperior());
        this.txtCodigoG5.setText(cp.getG5_SubFamilia().getCoNivel05());
        this.txtSub_Familia.setText(cp.getG5_SubFamilia().getDeAccionTerapeutica());

        if("A".equals(cp.getG5_SubFamilia().getEsAccionTerapeutica())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }

    public boolean verficarCambios(){
        if(!this.txtCodigoG4.getText().equals(cp.getG5_SubFamilia().getCoNivel05())){
            return true;
        }else if(!this.txtFamilia.getText().equals(cp.getG5_SubFamilia().getDeAccionTerapeutica())){
            return true;
        }else if(chbEstado.isSelected() != ("A".equals(cp.getG5_SubFamilia().getEsAccionTerapeutica()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getG5_SubFamilia().getEsAccionTerapeutica()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException{
        txtSub_Familia.setText(txtSub_Familia.getText().toUpperCase());
        cp.getG5_SubFamilia().setCoNivel05(txtCodigoG5.getText());
        cp.getG5_SubFamilia().setCoNivelSuperior(txtCodigoG4.getText());
        cp.getG5_SubFamilia().setDeAccionTerapeutica(txtSub_Familia.getText());
        cp.getG5_SubFamilia().setCoIms("Z");
        if (chbEstado.isSelected()){
            cp.getG5_SubFamilia().setEsAccionTerapeutica("A");
        }else{
            cp.getG5_SubFamilia().setEsAccionTerapeutica("I");
        }
        
        Boolean EstadoGuardar=false;
        if(!esActualizacion){
            cp.getG5_SubFamilia().setCoAccionTerapeutica(cp.getNuevoCodigoGrupo());
            cp.getG5_SubFamilia().setIdCreaAccionTerapeutica(AtuxVariables.vIdUsuario);
            cp.getG5_SubFamilia().setFeCreaAccionTerapeutica(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getG5_SubFamilia().setCoNivel05(cp.getNuevoCodigoSubFamilia(txtCodigoG4.getText()));
            EstadoGuardar = cp.guardarRegistro(cp.getG5_SubFamilia());
        }else{
            cp.getG5_SubFamilia().setIdModAccionTerapeutica(AtuxVariables.vIdUsuario);
            cp.getG5_SubFamilia().setFeModAccionTerapeutica(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));            
            int act = cp.actualizarRegistro(cp.getG5_SubFamilia());
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
            Logger.getLogger(IG5_SubFamilia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    private void LlenaGrid(){
        Limpiar("No");
        cp = new CG5_SubFamilia();
        if (tipoSeleccion== -1){
            mtp = new ModeloTablaG5_SubFamilia(new String[]{"Co_Nivel_Superior"},new String[]{txtCodigoG4.getText()});
        }else if (tipoSeleccion== 0){
            mtp = new ModeloTablaG5_SubFamilia(new String[]{"Co_Nivel_Superior","ES_ACCION_TERAPEUTICA"},new String[]{txtCodigoG4.getText(),"I"});
        }else{
            mtp = new ModeloTablaG5_SubFamilia(new String[]{"Co_Nivel_Superior","ES_ACCION_TERAPEUTICA"},new String[]{txtCodigoG4.getText(),"A"});
        }
        
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);

        Helper.ajustarAnchoColumnas(tblListado);
        setEventSelectionModel(tblListado.getSelectionModel());
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG5_SubFamilia.anchoColumnas);
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
        pnlEntradasCategorias_G05 = new javax.swing.JPanel();
        lblCodigoG1 = new javax.swing.JLabel();
        lblDescripG1 = new javax.swing.JLabel();
        lblCodigoG2 = new javax.swing.JLabel();
        lblDescripG2 = new javax.swing.JLabel();
        lblCodigoG3 = new javax.swing.JLabel();
        lblDescripG3 = new javax.swing.JLabel();
        lblCodigoG4 = new javax.swing.JLabel();
        lblDescripG4 = new javax.swing.JLabel();
        lblCodigoG5 = new javax.swing.JLabel();
        lblDescripG5 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigoG1 = new elaprendiz.gui.textField.TextField();
        txtLineaComercial = new elaprendiz.gui.textField.TextField();
        txtCodigoG2 = new elaprendiz.gui.textField.TextField();
        txtDivision = new elaprendiz.gui.textField.TextField();
        txtCodigoG3 = new elaprendiz.gui.textField.TextField();
        txtSub_Division = new elaprendiz.gui.textField.TextField();
        txtCodigoG4 = new elaprendiz.gui.textField.TextField();
        txtFamilia = new elaprendiz.gui.textField.TextField();
        txtCodigoG5 = new elaprendiz.gui.textField.TextField();
        txtSub_Familia = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        btnSeleccionarG1 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG2 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG3 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG4 = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlBuscadorCategorias_G05 = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesCategorias_G05 = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();

        setBorder(null);
        setTitle("Formulario de Set de Categorias - G5");
        setPreferredSize(new java.awt.Dimension(860, 545));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(860, 440));

        pnlEntradasCategorias_G05.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Set de Categorias - G05", 1, 2));
        pnlEntradasCategorias_G05.setOpaque(false);
        pnlEntradasCategorias_G05.setPreferredSize(new java.awt.Dimension(748, 120));
        pnlEntradasCategorias_G05.setLayout(null);

        lblCodigoG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG1.setText("G1 - Codigo:");
        pnlEntradasCategorias_G05.add(lblCodigoG1);
        lblCodigoG1.setBounds(16, 16, 87, 27);

        lblDescripG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG1.setText("Linea Comercial:");
        pnlEntradasCategorias_G05.add(lblDescripG1);
        lblDescripG1.setBounds(214, 17, 118, 24);

        lblCodigoG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG2.setText("G2 - Codigo:");
        pnlEntradasCategorias_G05.add(lblCodigoG2);
        lblCodigoG2.setBounds(16, 48, 87, 27);

        lblDescripG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG2.setText("Division:");
        pnlEntradasCategorias_G05.add(lblDescripG2);
        lblDescripG2.setBounds(270, 50, 57, 24);

        lblCodigoG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG3.setText("G3 - Codigo:");
        pnlEntradasCategorias_G05.add(lblCodigoG3);
        lblCodigoG3.setBounds(20, 80, 87, 27);

        lblDescripG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG3.setText("Sub_Division:");
        pnlEntradasCategorias_G05.add(lblDescripG3);
        lblDescripG3.setBounds(240, 80, 100, 24);

        lblCodigoG4.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG4.setText("G4 - Codigo:");
        pnlEntradasCategorias_G05.add(lblCodigoG4);
        lblCodigoG4.setBounds(20, 110, 87, 27);

        lblDescripG4.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG4.setText("Familia:");
        pnlEntradasCategorias_G05.add(lblDescripG4);
        lblDescripG4.setBounds(280, 110, 53, 24);

        lblCodigoG5.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG5.setText("G5 - Codigo:");
        pnlEntradasCategorias_G05.add(lblCodigoG5);
        lblCodigoG5.setBounds(20, 140, 87, 27);

        lblDescripG5.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG5.setText("Sub_Familia:");
        pnlEntradasCategorias_G05.add(lblDescripG5);
        lblDescripG5.setBounds(250, 140, 87, 24);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");
        pnlEntradasCategorias_G05.add(lblEstado);
        lblEstado.setBounds(660, 137, 53, 30);

        txtCodigoG1.setEditable(false);
        txtCodigoG1.setDireccionDeSombra(30);
        txtCodigoG1.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG1.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG1.setName("pcodigo"); // NOI18N
        txtCodigoG1.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtCodigoG1);
        txtCodigoG1.setBounds(110, 20, 83, 25);

        txtLineaComercial.setEditable(false);
        txtLineaComercial.setDireccionDeSombra(30);
        txtLineaComercial.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtLineaComercial.setFont(new java.awt.Font("Arial", 0, 12));
        txtLineaComercial.setName("pdescrip"); // NOI18N
        txtLineaComercial.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtLineaComercial);
        txtLineaComercial.setBounds(340, 17, 301, 25);

        txtCodigoG2.setEditable(false);
        txtCodigoG2.setDireccionDeSombra(30);
        txtCodigoG2.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG2.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG2.setName("pcodigo"); // NOI18N
        txtCodigoG2.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtCodigoG2);
        txtCodigoG2.setBounds(110, 50, 83, 25);

        txtDivision.setEditable(false);
        txtDivision.setDireccionDeSombra(30);
        txtDivision.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDivision.setFont(new java.awt.Font("Arial", 0, 12));
        txtDivision.setName("pdescrip"); // NOI18N
        txtDivision.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtDivision);
        txtDivision.setBounds(340, 50, 301, 25);

        txtCodigoG3.setEditable(false);
        txtCodigoG3.setDireccionDeSombra(30);
        txtCodigoG3.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG3.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG3.setName("pcodigo"); // NOI18N
        txtCodigoG3.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtCodigoG3);
        txtCodigoG3.setBounds(110, 80, 83, 25);

        txtSub_Division.setEditable(false);
        txtSub_Division.setDireccionDeSombra(30);
        txtSub_Division.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Division.setFont(new java.awt.Font("Arial", 0, 12));
        txtSub_Division.setName("pdescrip"); // NOI18N
        txtSub_Division.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtSub_Division);
        txtSub_Division.setBounds(340, 80, 301, 25);

        txtCodigoG4.setEditable(false);
        txtCodigoG4.setDireccionDeSombra(30);
        txtCodigoG4.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG4.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG4.setName("pcodigo"); // NOI18N
        txtCodigoG4.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtCodigoG4);
        txtCodigoG4.setBounds(110, 110, 83, 25);

        txtFamilia.setEditable(false);
        txtFamilia.setDireccionDeSombra(30);
        txtFamilia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtFamilia.setFont(new java.awt.Font("Arial", 0, 12));
        txtFamilia.setName("pdescrip"); // NOI18N
        txtFamilia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasCategorias_G05.add(txtFamilia);
        txtFamilia.setBounds(340, 110, 301, 25);

        txtCodigoG5.setEditable(false);
        txtCodigoG5.setDireccionDeSombra(30);
        txtCodigoG5.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG5.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG5.setName("pcodigo"); // NOI18N
        txtCodigoG5.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG5KeyReleased(evt);
            }
        });
        pnlEntradasCategorias_G05.add(txtCodigoG5);
        txtCodigoG5.setBounds(110, 140, 83, 25);

        txtSub_Familia.setEditable(false);
        txtSub_Familia.setDireccionDeSombra(30);
        txtSub_Familia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Familia.setFont(new java.awt.Font("Arial", 0, 12));
        txtSub_Familia.setName("pdescrip"); // NOI18N
        txtSub_Familia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtSub_Familia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSub_FamiliaKeyReleased(evt);
            }
        });
        pnlEntradasCategorias_G05.add(txtSub_Familia);
        txtSub_Familia.setBounds(340, 140, 301, 25);

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
        pnlEntradasCategorias_G05.add(chbEstado);
        chbEstado.setBounds(720, 140, 100, 25);

        btnSeleccionarG1.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG1.setText("Seleccionar");
        btnSeleccionarG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG1ActionPerformed(evt);
            }
        });
        pnlEntradasCategorias_G05.add(btnSeleccionarG1);
        btnSeleccionarG1.setBounds(660, 17, 115, 25);

        btnSeleccionarG2.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG2.setText("Seleccionar");
        btnSeleccionarG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG2ActionPerformed(evt);
            }
        });
        pnlEntradasCategorias_G05.add(btnSeleccionarG2);
        btnSeleccionarG2.setBounds(660, 50, 115, 25);

        btnSeleccionarG3.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG3.setText("Seleccionar");
        btnSeleccionarG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG3ActionPerformed(evt);
            }
        });
        pnlEntradasCategorias_G05.add(btnSeleccionarG3);
        btnSeleccionarG3.setBounds(660, 80, 115, 25);

        btnSeleccionarG4.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG4.setText("Seleccionar");
        btnSeleccionarG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG4ActionPerformed(evt);
            }
        });
        pnlEntradasCategorias_G05.add(btnSeleccionarG4);
        btnSeleccionarG4.setBounds(660, 110, 115, 25);

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

        pnlBuscadorCategorias_G05.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorCategorias_G05.setOpaque(false);
        pnlBuscadorCategorias_G05.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorCategorias_G05.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G05.add(btnPrimero);
        btnPrimero.setBounds(12, 2, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G05.add(btnAnterior);
        btnAnterior.setBounds(65, 2, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G05.add(btnSiguiente);
        btnSiguiente.setBounds(111, 2, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorCategorias_G05.add(btnUltimo);
        btnUltimo.setBounds(156, 2, 48, 25);

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
        pnlBuscadorCategorias_G05.add(rbTodos);
        rbTodos.setBounds(209, 2, 69, 25);

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
        pnlBuscadorCategorias_G05.add(rbAtivos);
        rbAtivos.setBounds(283, 2, 77, 25);

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
        pnlBuscadorCategorias_G05.add(rbNoActivos);
        rbNoActivos.setBounds(365, 2, 101, 25);

        pnlAccionesCategorias_G05.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesCategorias_G05.setOpaque(false);
        pnlAccionesCategorias_G05.setPreferredSize(new java.awt.Dimension(550, 50));
        pnlAccionesCategorias_G05.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G05.add(btnNuevo);
        btnNuevo.setBounds(12, 2, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G05.add(btnModificar);
        btnModificar.setBounds(96, 2, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G05.add(btnGuardar);
        btnGuardar.setBounds(200, 2, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G05.add(btnCancelar);
        btnCancelar.setBounds(295, 2, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesCategorias_G05.add(btnSalir);
        btnSalir.setBounds(395, 2, 88, 25);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addComponent(pnlBuscadorCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(pnlAccionesCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccionesCategorias_G05, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(panelImage1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbEstadoActionPerformed

private void btnSeleccionarG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG1ActionPerformed
        int tipoSeleccionG=1;    
        BuscarG1_LineaComercial pvc = new BuscarG1_LineaComercial(tipoSeleccionG);
        
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de Linea Comercial - G1":(tipoSeleccionG==1?"Mostrando Listado de Linea Comercial - G1 Activos":"Mostrando Listado de Linea Comercial - G1 No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getG1_LineaComercial() != null){
            Limpiar("Si");
            cpLC = new CG1_LineaComercial();
            cpLC.setG1_LineaComercial(pvc.getG1_LineaComercial());
            this.txtCodigoG1.setText(cpLC.getG1_LineaComercial().getCoNivel01());
            this.txtLineaComercial.setText(cpLC.getG1_LineaComercial().getDeLineaProdErp());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG1ActionPerformed

private void btnSeleccionarG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG2ActionPerformed
        if ("".equals(txtCodigoG1.getText())){
            JOptionPane.showMessageDialog(this, "Debe seleccionar la Linea Comercial", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int tipoSeleccionG=1;    
        BuscarG2_Division pvc = new BuscarG2_Division(tipoSeleccionG);
        pvc.setCoNivelSuperior(txtCodigoG1.getText());
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de División - G2":(tipoSeleccionG==1?"Mostrando Listado de División - G2 Activos":"Mostrando Listado de División - G2 No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getG2_Division() != null){
            txtDivision.setText("");
            txtFamilia.setText("");            
            cpDv = new CG2_Division();
            cpDv.setG2_Division(pvc.getG2_Division());
            this.txtCodigoG2.setText(cpDv.getG2_Division().getCoNivel02());
            this.txtDivision.setText(cpDv.getG2_Division().getDeGrupoProdErp());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG2ActionPerformed

private void btnSeleccionarG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG3ActionPerformed
        if ("".equals(txtCodigoG1.getText())){
            JOptionPane.showMessageDialog(this, "Debe seleccionar la División", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int tipoSeleccionG=1;    
        BuscarG3_SubDivision pvc = new BuscarG3_SubDivision(tipoSeleccionG);
        pvc.setCoNivelSuperior(txtCodigoG2.getText());
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de Sub Division - G3":(tipoSeleccionG==1?"Mostrando Listado de Linea Comercial - G1 Activos":"Mostrando Listado de Linea Comercial - G1 No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getG3_SubDivision() != null){
            cpSDv = new CG3_SubDivision();
            cpSDv.setG3_SubDivision(pvc.getG3_SubDivision());
            this.txtCodigoG3.setText(cpSDv.getG3_SubDivision().getCoNivel03());
            this.txtSub_Division.setText(cpSDv.getG3_SubDivision().getDeGrupoAnatomico());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG3ActionPerformed

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setG5_SubFamilia(mtp.getFila(numRegistros));
        setG5_SubFamilia();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setG5_SubFamilia(mtp.getFila(numRegistros));
        setG5_SubFamilia();
        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setG5_SubFamilia(mtp.getFila(numRegistros));
        setG5_SubFamilia();
        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setG5_SubFamilia(mtp.getFila(numRegistros));
        setG5_SubFamilia();
        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        LlenaGrid();
        chbSetActivo(true);
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        LlenaGrid();
        chbSetActivo(true);
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        LlenaGrid();
        chbSetActivo(false);
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if ("".equals(txtCodigoG2.getText())){
            JOptionPane.showMessageDialog(this, "Debe seleccionar la Division", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Limpiar("No");
        
        esActualizacion = false;        
        cp.getG5_SubFamilia().setCoAccionTerapeutica(cp.getNuevoCodigoGrupo());
        cp.getG5_SubFamilia().setCoNivel05(cp.getNuevoCodigoSubFamilia(txtCodigoG4.getText()));
        txtCodigoG5.setText(cp.getG5_SubFamilia().getCoNivel05());
        txtSub_Familia.requestFocus();
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
        if(ECampos.esObligatorio(this.pnlEntradasCategorias_G05,true)){
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
            ECampos.esObligatorio(this.pnlEntradasCategorias_G05,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
        LlenaGrid();
        DesActivarCasillas();
        tblListado.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        Limpiar("Si");
        DesActivarCasillas();
        LlenaGrid();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void btnSeleccionarG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG4ActionPerformed
        if ("".equals(txtCodigoG1.getText())){
            JOptionPane.showMessageDialog(this, "Debe seleccionar la Familia", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int tipoSeleccionG=1;    
        BuscarG4_Familia pvc = new BuscarG4_Familia(tipoSeleccionG);
        pvc.setCoNivelSuperior(txtCodigoG3.getText());
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de Familia - G4":(tipoSeleccionG==1?"Mostrando Listado de Familia - G4 Activos":"Mostrando Listado de Familia - G4 No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getG4_Familia() != null){
            cpF = new CG4_Familia();
            cpF.setG4_Familia(pvc.getG4_Familia());
            this.txtCodigoG4.setText(cpF.getG4_Familia().getCoNivel04());
            this.txtFamilia.setText(cpF.getG4_Familia().getDeGrupoTerapeutico());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG4ActionPerformed

private void txtCodigoG5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG5KeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtSub_Familia.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoG5KeyReleased

private void txtSub_FamiliaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSub_FamiliaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtSub_FamiliaKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSeleccionarG1;
    private elaprendiz.gui.button.ButtonRect btnSeleccionarG2;
    private elaprendiz.gui.button.ButtonRect btnSeleccionarG3;
    private elaprendiz.gui.button.ButtonRect btnSeleccionarG4;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoG1;
    private javax.swing.JLabel lblCodigoG2;
    private javax.swing.JLabel lblCodigoG3;
    private javax.swing.JLabel lblCodigoG4;
    private javax.swing.JLabel lblCodigoG5;
    private javax.swing.JLabel lblDescripG1;
    private javax.swing.JLabel lblDescripG2;
    private javax.swing.JLabel lblDescripG3;
    private javax.swing.JLabel lblDescripG4;
    private javax.swing.JLabel lblDescripG5;
    private javax.swing.JLabel lblEstado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesCategorias_G05;
    private javax.swing.JPanel pnlBuscadorCategorias_G05;
    private javax.swing.JPanel pnlEntradasCategorias_G05;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigoG1;
    private elaprendiz.gui.textField.TextField txtCodigoG2;
    private elaprendiz.gui.textField.TextField txtCodigoG3;
    private elaprendiz.gui.textField.TextField txtCodigoG4;
    private elaprendiz.gui.textField.TextField txtCodigoG5;
    private elaprendiz.gui.textField.TextField txtDivision;
    private elaprendiz.gui.textField.TextField txtFamilia;
    private elaprendiz.gui.textField.TextField txtLineaComercial;
    private elaprendiz.gui.textField.TextField txtSub_Division;
    private elaprendiz.gui.textField.TextField txtSub_Familia;
    // End of variables declaration//GEN-END:variables
}
