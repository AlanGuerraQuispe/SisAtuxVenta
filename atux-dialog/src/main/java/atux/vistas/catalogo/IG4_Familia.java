package atux.vistas.catalogo;

import atux.controllers.CG1_LineaComercial;
import atux.controllers.CG2_Division;
import atux.controllers.CG3_SubDivision;
import atux.controllers.CG4_Familia;
import atux.modelgui.ModeloTablaG4_Familia;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarG1_LineaComercial;
import atux.vistas.buscar.BuscarG2_Division;
import atux.vistas.buscar.BuscarG3_SubDivision;
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
public class IG4_Familia extends javax.swing.JInternalFrame {
    private CG4_Familia cp;
    private CG3_SubDivision cpSDv;
    private CG2_Division cpDv;
    private CG1_LineaComercial cpLC;
    private ModeloTablaG4_Familia mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    

    /** Creates new form IImpuestoIGV */
    public IG4_Familia() {
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
        }
        this.txtCodigoG4.setText("");
        this.txtFamilia.setText("");
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
        this.pnlEntradasCategorias_G04.setEnabled(false);
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G04, true, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblCodigoG4,lblDescripG1,lblDescripG2,lblDescripG3,lblDescripG4,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision,txtCodigoG3},false,"");        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.btnSeleccionarG1.setEnabled(false);
        this.btnSeleccionarG2.setEnabled(false);
        this.btnSeleccionarG3.setEnabled(false);
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
        this.pnlEntradasCategorias_G04.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G04, false, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblCodigoG4,lblDescripG1,lblDescripG2,lblDescripG3,lblDescripG4,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision,txtCodigoG3},false,"");        
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
        this.chbEstado.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        
        
        ECampos.esObligatorio(this.pnlEntradasCategorias_G04,false);
        esActualizacion = false;
        this.pnlBuscadorCategorias_G04.setVisible(true);
        
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
        Helper.setFiltraEntrada(txtCodigoG4.getDocument(), FiltraEntrada.NUM_LETRAS, 7, false);
        Helper.setFiltraEntrada(txtFamilia.getDocument(), FiltraEntrada.NUM_LETRAS, 40, false);
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
            cp.setG4_Familia(mtp.getFila(tblListado.getSelectedRow()));
            setG4_Familia();
        }
    }
    
    private void setG4_Familia(){
        Limpiar("No");
        this.txtCodigoG3.setText(cp.getG4_Familia().getCoNivelSuperior());
        this.txtCodigoG4.setText(cp.getG4_Familia().getCoNivel04());
        this.txtFamilia.setText(cp.getG4_Familia().getDeGrupoTerapeutico());

        if("A".equals(cp.getG4_Familia().getEsGrupoTerapeutico())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }

    public boolean verficarCambios(){
        if(!this.txtCodigoG4.getText().equals(cp.getG4_Familia().getCoNivel04())){
            return true;
        }else if(!this.txtFamilia.getText().equals(cp.getG4_Familia().getDeGrupoTerapeutico())){
            return true;
        }else if(chbEstado.isSelected() != ("A".equals(cp.getG4_Familia().getEsGrupoTerapeutico()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getG4_Familia().getEsGrupoTerapeutico()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException{
        txtFamilia.setText(txtFamilia.getText().toUpperCase());
        cp.getG4_Familia().setCoNivel04(txtCodigoG4.getText());
        cp.getG4_Familia().setCoNivelSuperior(txtCodigoG3.getText());
        cp.getG4_Familia().setDeGrupoTerapeutico(txtFamilia.getText());
        cp.getG4_Familia().setCoIms("Z");
        if (chbEstado.isSelected()){
            cp.getG4_Familia().setEsGrupoTerapeutico("A");
        }else{
            cp.getG4_Familia().setEsGrupoTerapeutico("I");
        }
        
        Boolean EstadoGuardar=false;
        if(!esActualizacion){
            cp.getG4_Familia().setCoGrupoTerapeutico(cp.getNuevoCodigoGrupo());
            cp.getG4_Familia().setIdCreaGrupoTerapeutico(AtuxVariables.vIdUsuario);
            cp.getG4_Familia().setFeCreaGrupoTerapeutico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getG4_Familia().setCoNivel04(cp.getNuevoCodigoFamilia(txtCodigoG3.getText()));
            EstadoGuardar = cp.guardarRegistro(cp.getG4_Familia());
        }else{
            cp.getG4_Familia().setIdModGrupoTerapeutico(AtuxVariables.vIdUsuario);
            cp.getG4_Familia().setFeModGrupoTerapeutico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));            
            int act = cp.actualizarRegistro(cp.getG4_Familia());
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
            Logger.getLogger(IG4_Familia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    private void LlenaGrid(){
        Limpiar("No");
        cp = new CG4_Familia();
        if (tipoSeleccion== -1){
            mtp = new ModeloTablaG4_Familia(new String[]{"Co_Nivel_Superior"},new String[]{txtCodigoG3.getText()});
        }else if (tipoSeleccion== 0){
            mtp = new ModeloTablaG4_Familia(new String[]{"Co_Nivel_Superior","ES_GRUPO_TERAPEUTICO"},new String[]{txtCodigoG3.getText(),"I"});
        }else{
            mtp = new ModeloTablaG4_Familia(new String[]{"Co_Nivel_Superior","ES_GRUPO_TERAPEUTICO"},new String[]{txtCodigoG3.getText(),"A"});
        }
        
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);

        Helper.ajustarAnchoColumnas(tblListado);
        setEventSelectionModel(tblListado.getSelectionModel());
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG4_Familia.anchoColumnas);
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
        pnlEntradasCategorias_G04 = new javax.swing.JPanel();
        lblCodigoG1 = new javax.swing.JLabel();
        lblDescripG1 = new javax.swing.JLabel();
        lblCodigoG2 = new javax.swing.JLabel();
        lblDescripG2 = new javax.swing.JLabel();
        lblCodigoG3 = new javax.swing.JLabel();
        lblDescripG3 = new javax.swing.JLabel();
        lblCodigoG4 = new javax.swing.JLabel();
        lblDescripG4 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigoG1 = new elaprendiz.gui.textField.TextField();
        txtLineaComercial = new elaprendiz.gui.textField.TextField();
        txtCodigoG2 = new elaprendiz.gui.textField.TextField();
        txtDivision = new elaprendiz.gui.textField.TextField();
        txtCodigoG3 = new elaprendiz.gui.textField.TextField();
        txtSub_Division = new elaprendiz.gui.textField.TextField();
        txtCodigoG4 = new elaprendiz.gui.textField.TextField();
        txtFamilia = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        btnSeleccionarG2 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG1 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG3 = new elaprendiz.gui.button.ButtonRect();
        pnlBuscadorCategorias_G04 = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesCategorias_G04 = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();

        setBorder(null);
        setTitle("Formulario de Set de Categorias - G4");
        setPreferredSize(new java.awt.Dimension(840, 545));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(840, 440));

        pnlEntradasCategorias_G04.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos de Set de Categorias - G04", 1, 2));
        pnlEntradasCategorias_G04.setEnabled(false);
        pnlEntradasCategorias_G04.setOpaque(false);
        pnlEntradasCategorias_G04.setPreferredSize(new java.awt.Dimension(748, 120));

        lblCodigoG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG1.setText("G1 - Codigo:");

        lblDescripG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG1.setText("Linea Comercial:");

        lblCodigoG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG2.setText("G2 - Codigo:");

        lblDescripG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG2.setText("Division:");

        lblCodigoG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG3.setText("G3 - Codigo:");

        lblDescripG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG3.setText("Sub_Division:");

        lblCodigoG4.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG4.setText("G4 - Codigo:");

        lblDescripG4.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG4.setText("Familia:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        txtCodigoG1.setEditable(false);
        txtCodigoG1.setDireccionDeSombra(30);
        txtCodigoG1.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG1.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG1.setName("pcodigo"); // NOI18N
        txtCodigoG1.setPreferredSize(new java.awt.Dimension(120, 25));

        txtLineaComercial.setEditable(false);
        txtLineaComercial.setDireccionDeSombra(30);
        txtLineaComercial.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtLineaComercial.setFont(new java.awt.Font("Arial", 0, 12));
        txtLineaComercial.setName("pdescrip"); // NOI18N
        txtLineaComercial.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCodigoG2.setEditable(false);
        txtCodigoG2.setDireccionDeSombra(30);
        txtCodigoG2.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG2.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG2.setName("pcodigo"); // NOI18N
        txtCodigoG2.setPreferredSize(new java.awt.Dimension(120, 25));

        txtDivision.setEditable(false);
        txtDivision.setDireccionDeSombra(30);
        txtDivision.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDivision.setFont(new java.awt.Font("Arial", 0, 12));
        txtDivision.setName("pdescrip"); // NOI18N
        txtDivision.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCodigoG3.setEditable(false);
        txtCodigoG3.setDireccionDeSombra(30);
        txtCodigoG3.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG3.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG3.setName("pcodigo"); // NOI18N
        txtCodigoG3.setPreferredSize(new java.awt.Dimension(120, 25));

        txtSub_Division.setEditable(false);
        txtSub_Division.setDireccionDeSombra(30);
        txtSub_Division.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Division.setFont(new java.awt.Font("Arial", 0, 12));
        txtSub_Division.setName("pdescrip"); // NOI18N
        txtSub_Division.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCodigoG4.setEditable(false);
        txtCodigoG4.setDireccionDeSombra(30);
        txtCodigoG4.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoG4.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoG4.setName("pcodigo"); // NOI18N
        txtCodigoG4.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoG4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG4KeyReleased(evt);
            }
        });

        txtFamilia.setEditable(false);
        txtFamilia.setDireccionDeSombra(30);
        txtFamilia.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtFamilia.setFont(new java.awt.Font("Arial", 0, 12));
        txtFamilia.setName("pdescrip"); // NOI18N
        txtFamilia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtFamilia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFamiliaKeyReleased(evt);
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

        btnSeleccionarG2.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG2.setText("Seleccionar");
        btnSeleccionarG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG2ActionPerformed(evt);
            }
        });

        btnSeleccionarG1.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG1.setText("Seleccionar");
        btnSeleccionarG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG1ActionPerformed(evt);
            }
        });

        btnSeleccionarG3.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG3.setText("Seleccionar");
        btnSeleccionarG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasCategorias_G04Layout = new javax.swing.GroupLayout(pnlEntradasCategorias_G04);
        pnlEntradasCategorias_G04.setLayout(pnlEntradasCategorias_G04Layout);
        pnlEntradasCategorias_G04Layout.setHorizontalGroup(
            pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addComponent(lblCodigoG1)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblDescripG1)
                        .addGap(8, 8, 8)
                        .addComponent(txtLineaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnSeleccionarG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addComponent(lblCodigoG2)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lblDescripG2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnSeleccionarG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addComponent(lblCodigoG3)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblDescripG3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtSub_Division, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnSeleccionarG3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addComponent(lblCodigoG4)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoG4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lblDescripG4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado)
                        .addGap(6, 6, 6)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlEntradasCategorias_G04Layout.setVerticalGroup(
            pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDescripG1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasCategorias_G04Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtLineaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSeleccionarG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripG2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripG3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSub_Division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarG3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblCodigoG4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoG4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripG4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pnlBuscadorCategorias_G04.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorCategorias_G04.setOpaque(false);
        pnlBuscadorCategorias_G04.setPreferredSize(new java.awt.Dimension(575, 37));

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

        javax.swing.GroupLayout pnlBuscadorCategorias_G04Layout = new javax.swing.GroupLayout(pnlBuscadorCategorias_G04);
        pnlBuscadorCategorias_G04.setLayout(pnlBuscadorCategorias_G04Layout);
        pnlBuscadorCategorias_G04Layout.setHorizontalGroup(
            pnlBuscadorCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorCategorias_G04Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rbTodos)
                .addGap(5, 5, 5)
                .addComponent(rbAtivos)
                .addGap(5, 5, 5)
                .addComponent(rbNoActivos)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlBuscadorCategorias_G04Layout.setVerticalGroup(
            pnlBuscadorCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rbTodos)
            .addComponent(rbAtivos)
            .addComponent(rbNoActivos)
            .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlAccionesCategorias_G04.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesCategorias_G04.setOpaque(false);
        pnlAccionesCategorias_G04.setPreferredSize(new java.awt.Dimension(550, 50));

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

        javax.swing.GroupLayout pnlAccionesCategorias_G04Layout = new javax.swing.GroupLayout(pnlAccionesCategorias_G04);
        pnlAccionesCategorias_G04.setLayout(pnlAccionesCategorias_G04Layout);
        pnlAccionesCategorias_G04Layout.setHorizontalGroup(
            pnlAccionesCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesCategorias_G04Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        pnlAccionesCategorias_G04Layout.setVerticalGroup(
            pnlAccionesCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesCategorias_G04Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAccionesCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradasCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesCategorias_G04, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setG4_Familia(mtp.getFila(numRegistros));
        setG4_Familia();
        return;
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setG4_Familia(mtp.getFila(numRegistros));
        setG4_Familia();
        return;
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setG4_Familia(mtp.getFila(numRegistros));
        setG4_Familia();
        return;
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setG4_Familia(mtp.getFila(numRegistros));
        setG4_Familia();
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
        cp.getG4_Familia().setCoGrupoTerapeutico(cp.getNuevoCodigoGrupo());
        cp.getG4_Familia().setCoNivel04(cp.getNuevoCodigoFamilia(txtCodigoG3.getText()));
        txtCodigoG4.setText(cp.getG4_Familia().getCoNivel04());
        txtFamilia.requestFocus();
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
        if(ECampos.esObligatorio(this.pnlEntradasCategorias_G04,true)){
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
            ECampos.esObligatorio(this.pnlEntradasCategorias_G04,false);
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

private void btnSeleccionarG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG3ActionPerformed
        if ("".equals(txtCodigoG1.getText())){
            JOptionPane.showMessageDialog(this, "Debe seleccionar la División", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int tipoSeleccionG=1;    
        BuscarG3_SubDivision pvc = new BuscarG3_SubDivision(tipoSeleccionG);
        pvc.setCoNivelSuperior(txtCodigoG2.getText());
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de Linea Comercial - G1":(tipoSeleccionG==1?"Mostrando Listado de Linea Comercial - G1 Activos":"Mostrando Listado de Linea Comercial - G1 No activos"));
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

private void txtCodigoG4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG4KeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtFamilia.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoG4KeyReleased

private void txtFamiliaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFamiliaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtFamiliaKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

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
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoG1;
    private javax.swing.JLabel lblCodigoG2;
    private javax.swing.JLabel lblCodigoG3;
    private javax.swing.JLabel lblCodigoG4;
    private javax.swing.JLabel lblDescripG1;
    private javax.swing.JLabel lblDescripG2;
    private javax.swing.JLabel lblDescripG3;
    private javax.swing.JLabel lblDescripG4;
    private javax.swing.JLabel lblEstado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesCategorias_G04;
    private javax.swing.JPanel pnlBuscadorCategorias_G04;
    private javax.swing.JPanel pnlEntradasCategorias_G04;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigoG1;
    private elaprendiz.gui.textField.TextField txtCodigoG2;
    private elaprendiz.gui.textField.TextField txtCodigoG3;
    private elaprendiz.gui.textField.TextField txtCodigoG4;
    private elaprendiz.gui.textField.TextField txtDivision;
    private elaprendiz.gui.textField.TextField txtFamilia;
    private elaprendiz.gui.textField.TextField txtLineaComercial;
    private elaprendiz.gui.textField.TextField txtSub_Division;
    // End of variables declaration//GEN-END:variables
}
