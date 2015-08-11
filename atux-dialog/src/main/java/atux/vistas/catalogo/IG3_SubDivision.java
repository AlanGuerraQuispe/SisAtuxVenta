package atux.vistas.catalogo;

import atux.controllers.CG1_LineaComercial;
import atux.controllers.CG2_Division;
import atux.controllers.CG3_SubDivision;
import atux.modelgui.ModeloTablaG3_SubDivision;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarG1_LineaComercial;
import atux.vistas.buscar.BuscarG2_Division;
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
public class IG3_SubDivision extends javax.swing.JInternalFrame {
    private CG3_SubDivision cp;
    private CG2_Division cpDv;
    private CG1_LineaComercial cpLC;
    private ModeloTablaG3_SubDivision mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());    
    /** Creates new form IImpuestoIGV */
    public IG3_SubDivision() {
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
        }
        this.txtCodigoG3.setText("");
        this.txtSub_Divisiion.setText("");
        chbSetActivo(false);
    }

    private void ActivarCasillas(){
        this.pnlEntradasCategorias_G03.setEnabled(false);
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G03, true, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblDescripG1,lblDescripG2,lblDescripG3,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision},false,"");        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.btnSeleccionarG1.setEnabled(false);
        this.btnSeleccionarG2.setEnabled(false);
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
        this.pnlEntradasCategorias_G03.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradasCategorias_G03, false, new Component[]{lblCodigoG1,lblCodigoG2,lblCodigoG3,lblDescripG1,lblDescripG2,lblDescripG3,lblEstado,txtCodigoG1,txtLineaComercial,txtCodigoG2,txtDivision},false,"");        
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.btnSeleccionarG1.setEnabled(true);
        this.btnSeleccionarG2.setEnabled(true);
        this.chbEstado.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        
        
        ECampos.esObligatorio(this.pnlEntradasCategorias_G03,false);
        esActualizacion = false;
        this.pnlBuscadorCategorias_G03.setVisible(true);
        
        logger.info(txtCodigoG2.getText() + " - " + txtDivision.getText());
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
        Helper.setFiltraEntrada(txtCodigoG3.getDocument(), FiltraEntrada.NUM_LETRAS, 5, false);
        Helper.setFiltraEntrada(txtSub_Divisiion.getDocument(), FiltraEntrada.NUM_LETRAS, 40, false);
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
            cp.setG3_SubDivision(mtp.getFila(tblListado.getSelectedRow()));
            setG3_SubDivision();
        }
    }
    
    private void setG3_SubDivision(){
        Limpiar("No");
        this.txtCodigoG2.setText(cp.getG3_SubDivision().getCoNivelSuperior());
        this.txtCodigoG3.setText(cp.getG3_SubDivision().getCoNivel03());
        this.txtSub_Divisiion.setText(cp.getG3_SubDivision().getDeGrupoAnatomico());

        if("A".equals(cp.getG3_SubDivision().getEsGrupoAnatomico())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        this.btnModificar.setEnabled(true);
    }

    public boolean verficarCambios(){
        if(!this.txtCodigoG3.getText().equals(cp.getG3_SubDivision().getCoNivel03())){
            return true;
        }else if(!this.txtSub_Divisiion.getText().equals(cp.getG3_SubDivision().getDeGrupoAnatomico())){
            return true;
        }else if(chbEstado.isSelected() != ("A".equals(cp.getG3_SubDivision().getEsGrupoAnatomico()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getG3_SubDivision().getEsGrupoAnatomico()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException{
        txtSub_Divisiion.setText(txtSub_Divisiion.getText());
        cp.getG3_SubDivision().setCoNivel03(txtCodigoG3.getText());
        cp.getG3_SubDivision().setCoNivelSuperior(txtCodigoG2.getText());
        cp.getG3_SubDivision().setDeGrupoAnatomico(txtSub_Divisiion.getText());
        cp.getG3_SubDivision().setCoIms("Z");
        if (chbEstado.isSelected()){
            cp.getG3_SubDivision().setEsGrupoAnatomico("A");
        }else{
            cp.getG3_SubDivision().setEsGrupoAnatomico("I");
        }
        
        Boolean EstadoGuardar=false;
        if(!esActualizacion){
            cp.getG3_SubDivision().setCoGrupoAnatomico(cp.getNuevoCodigoGrupo());
            cp.getG3_SubDivision().setIdCreaGrupoAnatomico(AtuxVariables.vIdUsuario);
            cp.getG3_SubDivision().setFeCreaGrupoAnatomico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getG3_SubDivision().setCoNivel03(cp.getNuevoCodigoSubDivision(txtCodigoG2.getText()));
            EstadoGuardar = cp.guardarRegistro(cp.getG3_SubDivision());
        }else{
            cp.getG3_SubDivision().setIdModGrupoAnatomico(AtuxVariables.vIdUsuario);
            cp.getG3_SubDivision().setFeModGrupoAnatomico(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));            
            int act = cp.actualizarRegistro(cp.getG3_SubDivision());
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
            Logger.getLogger(IG3_SubDivision.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
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
        pnlEntradasCategorias_G03 = new javax.swing.JPanel();
        lblCodigoG1 = new javax.swing.JLabel();
        lblDescripG1 = new javax.swing.JLabel();
        lblCodigoG2 = new javax.swing.JLabel();
        lblDescripG2 = new javax.swing.JLabel();
        lblCodigoG3 = new javax.swing.JLabel();
        lblDescripG3 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigoG1 = new elaprendiz.gui.textField.TextField();
        txtLineaComercial = new elaprendiz.gui.textField.TextField();
        txtCodigoG2 = new elaprendiz.gui.textField.TextField();
        txtDivision = new elaprendiz.gui.textField.TextField();
        txtCodigoG3 = new elaprendiz.gui.textField.TextField();
        txtSub_Divisiion = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        btnSeleccionarG1 = new elaprendiz.gui.button.ButtonRect();
        btnSeleccionarG2 = new elaprendiz.gui.button.ButtonRect();
        pnlBuscadorCategorias_G03 = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesCategorias_G03 = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();

        setBorder(null);
        setTitle("Formulario de Set de Categorias - G3");
        setPreferredSize(new java.awt.Dimension(820, 470));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(820, 470));

        pnlEntradasCategorias_G03.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Set de Categorias - G03", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasCategorias_G03.setEnabled(false);
        pnlEntradasCategorias_G03.setOpaque(false);
        pnlEntradasCategorias_G03.setPreferredSize(new java.awt.Dimension(748, 120));

        lblCodigoG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG1.setText("G1 - Codigo:");

        lblDescripG1.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG1.setText("Linea Comercial:");

        lblCodigoG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG2.setText("G2 - Codigo:");

        lblDescripG2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG2.setText("Division:");

        lblCodigoG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigoG3.setText("G3 - Codigo:");

        lblDescripG3.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripG3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescripG3.setText("Sub_Division:");

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
        txtCodigoG3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoG3KeyReleased(evt);
            }
        });

        txtSub_Divisiion.setEditable(false);
        txtSub_Divisiion.setDireccionDeSombra(30);
        txtSub_Divisiion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSub_Divisiion.setFont(new java.awt.Font("Arial", 0, 12));
        txtSub_Divisiion.setName("pdescrip"); // NOI18N
        txtSub_Divisiion.setPreferredSize(new java.awt.Dimension(120, 25));
        txtSub_Divisiion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSub_DivisiionKeyReleased(evt);
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

        btnSeleccionarG1.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG1.setText("Seleccionar");
        btnSeleccionarG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG1ActionPerformed(evt);
            }
        });

        btnSeleccionarG2.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionarG2.setText("Seleccionar");
        btnSeleccionarG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarG2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasCategorias_G03Layout = new javax.swing.GroupLayout(pnlEntradasCategorias_G03);
        pnlEntradasCategorias_G03.setLayout(pnlEntradasCategorias_G03Layout);
        pnlEntradasCategorias_G03Layout.setHorizontalGroup(
            pnlEntradasCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                .addComponent(lblCodigoG1)
                .addGap(4, 4, 4)
                .addComponent(txtCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDescripG1)
                .addGap(4, 4, 4)
                .addComponent(txtLineaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnSeleccionarG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                .addComponent(lblCodigoG2)
                .addGap(4, 4, 4)
                .addComponent(txtCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(lblDescripG2)
                .addGap(4, 4, 4)
                .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnSeleccionarG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                .addComponent(lblCodigoG3)
                .addGap(4, 4, 4)
                .addComponent(txtCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDescripG3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtSub_Divisiion, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblEstado)
                .addGap(7, 7, 7)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlEntradasCategorias_G03Layout.setVerticalGroup(
            pnlEntradasCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                .addGroup(pnlEntradasCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigoG1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDescripG1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLineaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlEntradasCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigoG2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDescripG2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnSeleccionarG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodigoG3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDescripG3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSub_Divisiion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblEstado))
                    .addGroup(pnlEntradasCategorias_G03Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pnlBuscadorCategorias_G03.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorCategorias_G03.setOpaque(false);
        pnlBuscadorCategorias_G03.setPreferredSize(new java.awt.Dimension(575, 37));

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

        javax.swing.GroupLayout pnlBuscadorCategorias_G03Layout = new javax.swing.GroupLayout(pnlBuscadorCategorias_G03);
        pnlBuscadorCategorias_G03.setLayout(pnlBuscadorCategorias_G03Layout);
        pnlBuscadorCategorias_G03Layout.setHorizontalGroup(
            pnlBuscadorCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBuscadorCategorias_G03Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(95, 95, 95))
        );
        pnlBuscadorCategorias_G03Layout.setVerticalGroup(
            pnlBuscadorCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorCategorias_G03Layout.createSequentialGroup()
                .addGroup(pnlBuscadorCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbTodos)
                    .addComponent(rbAtivos)
                    .addComponent(rbNoActivos)
                    .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(4, Short.MAX_VALUE))
        );

        pnlAccionesCategorias_G03.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesCategorias_G03.setOpaque(false);
        pnlAccionesCategorias_G03.setPreferredSize(new java.awt.Dimension(550, 50));

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

        javax.swing.GroupLayout pnlAccionesCategorias_G03Layout = new javax.swing.GroupLayout(pnlAccionesCategorias_G03);
        pnlAccionesCategorias_G03.setLayout(pnlAccionesCategorias_G03Layout);
        pnlAccionesCategorias_G03Layout.setHorizontalGroup(
            pnlAccionesCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesCategorias_G03Layout.createSequentialGroup()
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
        pnlAccionesCategorias_G03Layout.setVerticalGroup(
            pnlAccionesCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesCategorias_G03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pnlEntradasCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAccionesCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBuscadorCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlAccionesCategorias_G03, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(panelImage1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
    }//GEN-LAST:event_chbEstadoActionPerformed

private void btnSeleccionarG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarG1ActionPerformed
        int tipoSeleccionG=1;    
        BuscarG1_LineaComercial pvc = new BuscarG1_LineaComercial(tipoSeleccionG);
        
        JLabel aviso = pvc.getAviso();
        
        String msj = (tipoSeleccionG==-1?"Mostrando Listado de Linea Comercial - G1":(tipoSeleccionG==1?"Mostrando Listado de Linea Comercial - G1 Activos":"Mostrando Listado de Linea Comercial - G1 No activos"));
        JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getG1_LineaComercial() != null){
            cpLC = new CG1_LineaComercial();
            cpLC.setG1_LineaComercial(pvc.getG1_LineaComercial());
            this.txtCodigoG1.setText(cpLC.getG1_LineaComercial().getCoNivel01());
            this.txtLineaComercial.setText(cpLC.getG1_LineaComercial().getDeLineaProdErp());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG1ActionPerformed

    private void LlenaGrid(){
        Limpiar("No");
        cp = new CG3_SubDivision();
        if (tipoSeleccion== -1){
            mtp = new ModeloTablaG3_SubDivision(new String[]{"Co_Nivel_Superior"},new String[]{txtCodigoG2.getText()});
        }else if (tipoSeleccion== 0){
            mtp = new ModeloTablaG3_SubDivision(new String[]{"Co_Nivel_Superior","ES_GRUPO_ANATOMICO"},new String[]{txtCodigoG2.getText(),"I"});
        }else{
            mtp = new ModeloTablaG3_SubDivision(new String[]{"Co_Nivel_Superior","ES_GRUPO_ANATOMICO"},new String[]{txtCodigoG2.getText(),"A"});
        }
        
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);

        Helper.ajustarAnchoColumnas(tblListado);
        setEventSelectionModel(tblListado.getSelectionModel());
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaG3_SubDivision.anchoColumnas);
    }

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
            cpDv = new CG2_Division();
            cpDv.setG2_Division(pvc.getG2_Division());
            this.txtCodigoG2.setText(cpDv.getG2_Division().getCoNivel02());
            this.txtDivision.setText(cpDv.getG2_Division().getDeGrupoProdErp());
            LlenaGrid();
        }
}//GEN-LAST:event_btnSeleccionarG2ActionPerformed

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setG3_SubDivision(mtp.getFila(numRegistros));
        setG3_SubDivision();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setG3_SubDivision(mtp.getFila(numRegistros));
        setG3_SubDivision();
        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setG3_SubDivision(mtp.getFila(numRegistros));
        setG3_SubDivision();
        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setG3_SubDivision(mtp.getFila(numRegistros));
        setG3_SubDivision();
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
        cp.getG3_SubDivision().setCoGrupoAnatomico(cp.getNuevoCodigoGrupo());
        cp.getG3_SubDivision().setCoNivel03(cp.getNuevoCodigoSubDivision(txtCodigoG2.getText()));
        txtCodigoG3.setText(cp.getG3_SubDivision().getCoNivel03());
        txtSub_Divisiion.requestFocus();
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
        if(ECampos.esObligatorio(this.pnlEntradasCategorias_G03,true)){
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
            ECampos.esObligatorio(this.pnlEntradasCategorias_G03,false);
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
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void txtSub_DivisiionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSub_DivisiionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtSub_DivisiionKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void txtCodigoG3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoG3KeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtSub_Divisiion.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoG3KeyReleased

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
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoG1;
    private javax.swing.JLabel lblCodigoG2;
    private javax.swing.JLabel lblCodigoG3;
    private javax.swing.JLabel lblDescripG1;
    private javax.swing.JLabel lblDescripG2;
    private javax.swing.JLabel lblDescripG3;
    private javax.swing.JLabel lblEstado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesCategorias_G03;
    private javax.swing.JPanel pnlBuscadorCategorias_G03;
    private javax.swing.JPanel pnlEntradasCategorias_G03;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigoG1;
    private elaprendiz.gui.textField.TextField txtCodigoG2;
    private elaprendiz.gui.textField.TextField txtCodigoG3;
    private elaprendiz.gui.textField.TextField txtDivision;
    private elaprendiz.gui.textField.TextField txtLineaComercial;
    private elaprendiz.gui.textField.TextField txtSub_Divisiion;
    // End of variables declaration//GEN-END:variables
}
