package atux.vistas.catalogo;

import atux.controllers.CLocal;
import atux.controllers.CParametroReposicion;
import atux.modelgui.ModeloTablaParametroReposicion;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class IParamXReposicion extends javax.swing.JPanel {
    private CParametroReposicion cp;
    private ModeloTablaParametroReposicion mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = 1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    public int nuOrdenParametro = 0;
    JInternalFrame ifr;
    private final Log logger = LogFactory.getLog(getClass());    
    JOptionPane op;
    public String tipoParametroReposicion;
    
    public IParamXReposicion() {
        initComponents();
        lblMensaje.setVisible(false);
        lblAviso.setVisible(false);
        
        cp = new CParametroReposicion();
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();
    }


    public final void CargaDatos(){
        if ("PD".equals(tipoParametroReposicion)){
            pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Parametros de Reposicion por Producto"));
            nuOrdenParametro=4;
        }else if ("LB".equals(tipoParametroReposicion)){
            pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Parametros de Reposicion por Laboratorio"));
            nuOrdenParametro=3;            
        }else if ("LN".equals(tipoParametroReposicion)){
            pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Parametros de Reposicion por Linea"));
            nuOrdenParametro=2;            
        }else if ("LC".equals(tipoParametroReposicion)){
            pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Parametros de Reposicion por Local"));
            nuOrdenParametro=1;            
        }
        String Estado = "T";

        if (tipoSeleccion == 0){
            Estado = "I";
        }else if (tipoSeleccion == 1){
            Estado = "A";
        }
        Object[] Datos;
        if ("T".equals(Estado)){
            Datos = new Object[]{AtuxVariables.vCodigoCompania,txtCodigo.getText(),tipoParametroReposicion};
        }else{
            Datos = new Object[]{AtuxVariables.vCodigoCompania,txtCodigo.getText(),tipoParametroReposicion,Estado};
        }

        mtp = new ModeloTablaParametroReposicion(Datos, Estado);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaParametroReposicion.anchoColumnas);
        BuscarSuministros();
    }        
    
    
    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtMinimo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtMaximo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtPeriodo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
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
            cp.setParametroReposicion(mtp.getFila(tblListado.getSelectedRow()));
            setParametroReposicion();           
            btnModificar.setEnabled(true);
       }
    }
    
    private void setParametroReposicion(){
        Limpiar();

        this.txtMinimo.setText(String.valueOf(cp.getParametroReposicion().getNuMinimo()));
        this.txtMaximo.setText(String.valueOf(cp.getParametroReposicion().getNuMaximo()));
        this.txtPeriodo.setText(String.valueOf(cp.getParametroReposicion().getNuPeriodo()));
        this.dteFechaInicio.setDate(cp.getParametroReposicion().getFeInicio());
        this.dteFechaFinal.setDate(cp.getParametroReposicion().getFeFinal());

        if("A".equals(cp.getParametroReposicion().getEsParametro())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
    }
    
    private void Limpiar(){
        this.txtMinimo.setText("");
        this.txtMaximo.setText("");
        this.txtPeriodo.setText("");
        this.dteFechaInicio.setDate(null);
        this.dteFechaFinal.setDate(null);
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigo,lblAviso,lblDescripcion,lblEstado, lblMaximo, lblMensaje, lblMinimo, lblPeriodo,lblReposicion, lblSuministro, lblVigenciaFinal, lblVigenciaInicio,txtCodigo,txtDescripcion,txtTmpReposicion,txtTmpSuministro},false,"");
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);
        this.dteFechaInicio.setEnabled(true);
        this.dteFechaFinal.setEnabled(true);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigo,lblAviso,lblDescripcion,lblEstado, lblMaximo, lblMensaje, lblMinimo, lblPeriodo,lblReposicion, lblSuministro, lblVigenciaFinal, lblVigenciaInicio,txtCodigo,txtDescripcion,txtTmpReposicion,txtTmpSuministro},false,"");
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);
        this.dteFechaInicio.setEnabled(false);
        this.dteFechaFinal.setEnabled(false);
        
        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        

        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtMinimo.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtMinimo.getText().equals(String.valueOf(cp.getParametroReposicion().getNuMinimo()))){
            return true;
        }else if(!this.txtMaximo.getText().equals(String.valueOf(cp.getParametroReposicion().getNuMaximo()))){
            return true;
        }else if(!this.txtPeriodo.getText().equals(String.valueOf(cp.getParametroReposicion().getNuPeriodo()))){
            return true;
        }else if(dteFechaInicio.getDate().compareTo(cp.getParametroReposicion().getFeInicio()) !=0 ){
            return true;
        }else if(dteFechaFinal.getDate().compareTo(cp.getParametroReposicion().getFeFinal()) !=0 ){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getParametroReposicion().getEsParametro()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{
        
        cp.getParametroReposicion().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getParametroReposicion().setCoLocal(AtuxVariables.vCodigoLocal);
        cp.getParametroReposicion().setNuOrdenParametro(nuOrdenParametro);
        cp.getParametroReposicion().setTiParametro(this.tipoParametroReposicion);
        cp.getParametroReposicion().setCoCodigo(txtCodigo.getText());
        cp.getParametroReposicion().setNuMinimo(Integer.parseInt(txtMinimo.getText()));
        cp.getParametroReposicion().setNuMaximo(Integer.parseInt(txtMaximo.getText()));
        cp.getParametroReposicion().setNuPeriodo(Integer.parseInt(txtPeriodo.getText()));
        cp.getParametroReposicion().setFeInicio(dteFechaInicio.getDate());
        cp.getParametroReposicion().setFeFinal(dteFechaFinal.getDate());

        if (chbEstado.isSelected()){
            cp.getParametroReposicion().setEsParametro("A");
        }else{
            cp.getParametroReposicion().setEsParametro("I");
        }

        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getParametroReposicion().setNuSecuencia(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,nuOrdenParametro, tipoParametroReposicion,txtCodigo.getText()));
            cp.getParametroReposicion().setIdCreaParametro(AtuxVariables.vIdUsuario);
            cp.getParametroReposicion().setFeCreaParametro(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            EstadoGuardar = cp.guardarRegistro(cp.getParametroReposicion());
        }else{
            cp.getParametroReposicion().setIdModParametro(AtuxVariables.vIdUsuario);
            cp.getParametroReposicion().setFeModParametro(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            int act = cp.actualizarRegistro(cp.getParametroReposicion());
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
            Logger.getLogger(IParamXReposicion.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setCodigoProducto(String CodProducto){
        txtCodigo.setText(CodProducto);
    }
    
    public void setDescripcionProducto(String DescripcionProducto){
        txtDescripcion.setText(DescripcionProducto);
    }
    
    public void setTipoParametroReposicion(String TipoParametro){
        this.tipoParametroReposicion = TipoParametro;
    }
    
    private void getOptionPane(){
        if(op != null){
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null){
            if(pdr instanceof JOptionPane){
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }

    public JLabel getAviso(){
        return lblAviso;
    }
    private void BuscarSuministros(){
        CLocal BG1 = new CLocal();
        BG1.getObtenerSuministrosReposicion();
        
        txtTmpReposicion.setText(BG1.getSuministroReposicion().getTrDias().toString());
        txtTmpSuministro.setText(BG1.getSuministroReposicion().getTsDias().toString());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImageMedico = new elaprendiz.gui.panel.PanelImage();
        panelCurves = new elaprendiz.gui.panel.PanelCurves();
        pnlMedicosEspecialidad = new javax.swing.JPanel();
        pnlEntradas = new javax.swing.JPanel();
        lblMinimo = new javax.swing.JLabel();
        lblMaximo = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        lblVigenciaInicio = new javax.swing.JLabel();
        lblVigenciaFinal = new javax.swing.JLabel();
        lblSuministro = new javax.swing.JLabel();
        lblReposicion = new javax.swing.JLabel();
        txtMinimo = new elaprendiz.gui.textField.TextField();
        txtMaximo = new elaprendiz.gui.textField.TextField();
        txtPeriodo = new elaprendiz.gui.textField.TextField();
        txtTmpSuministro = new elaprendiz.gui.textField.TextField();
        txtTmpReposicion = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        lblEstado = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        lblAviso = new elaprendiz.gui.label.LabelRect();
        lblMensaje = new elaprendiz.gui.label.LabelRect();
        dteFechaInicio = new com.toedter.calendar.JDateChooser();
        dteFechaFinal = new com.toedter.calendar.JDateChooser();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        jScrollPanelXPaDeRpDeProd = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(600, 373));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        panelImageMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageMedico.setLayout(new java.awt.BorderLayout());

        pnlMedicosEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlMedicosEspecialidad.setOpaque(false);

        pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Parametros de Reposicion", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradas.setEnabled(false);
        pnlEntradas.setOpaque(false);
        pnlEntradas.setPreferredSize(new java.awt.Dimension(748, 120));

        lblMinimo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMinimo.setText("Minimo:");
        lblMinimo.setAlignmentX(0.2F);
        lblMinimo.setAlignmentY(0.2F);

        lblMaximo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblMaximo.setText("Maximo:");
        lblMaximo.setAlignmentX(0.2F);
        lblMaximo.setAlignmentY(0.2F);

        lblPeriodo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPeriodo.setText("Periodo:");
        lblPeriodo.setAlignmentX(0.2F);
        lblPeriodo.setAlignmentY(0.2F);

        lblVigenciaInicio.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVigenciaInicio.setText("Vigencia Inicio");
        lblVigenciaInicio.setAlignmentX(0.2F);
        lblVigenciaInicio.setAlignmentY(0.2F);

        lblVigenciaFinal.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblVigenciaFinal.setText("Vigencia Final");

        lblSuministro.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSuministro.setText("Tiempo de Suministro-TS");

        lblReposicion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblReposicion.setText("Tiempo de Reposicion-TR");

        txtMinimo.setEditable(false);
        txtMinimo.setAlignmentX(0.2F);
        txtMinimo.setAlignmentY(0.2F);
        txtMinimo.setDireccionDeSombra(30);
        txtMinimo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtMinimo.setFont(new java.awt.Font("Arial", 0, 12));
        txtMinimo.setName("pcodigo"); // NOI18N
        txtMinimo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinimoKeyReleased(evt);
            }
        });

        txtMaximo.setEditable(false);
        txtMaximo.setAlignmentX(0.2F);
        txtMaximo.setAlignmentY(0.2F);
        txtMaximo.setDireccionDeSombra(30);
        txtMaximo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtMaximo.setFont(new java.awt.Font("Arial", 0, 12));
        txtMaximo.setName("pcodigo"); // NOI18N
        txtMaximo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaximoKeyReleased(evt);
            }
        });

        txtPeriodo.setEditable(false);
        txtPeriodo.setAlignmentX(0.2F);
        txtPeriodo.setAlignmentY(0.2F);
        txtPeriodo.setDireccionDeSombra(30);
        txtPeriodo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtPeriodo.setFont(new java.awt.Font("Arial", 0, 12));
        txtPeriodo.setName("pdescrip"); // NOI18N
        txtPeriodo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtPeriodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeriodoKeyReleased(evt);
            }
        });

        txtTmpSuministro.setEditable(false);
        txtTmpSuministro.setAlignmentX(0.2F);
        txtTmpSuministro.setAlignmentY(0.2F);
        txtTmpSuministro.setDireccionDeSombra(30);
        txtTmpSuministro.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTmpSuministro.setFont(new java.awt.Font("Arial", 0, 12));
        txtTmpSuministro.setName(""); // NOI18N
        txtTmpSuministro.setPreferredSize(new java.awt.Dimension(120, 25));

        txtTmpReposicion.setEditable(false);
        txtTmpReposicion.setAlignmentX(0.2F);
        txtTmpReposicion.setAlignmentY(0.2F);
        txtTmpReposicion.setDireccionDeSombra(30);
        txtTmpReposicion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtTmpReposicion.setFont(new java.awt.Font("Arial", 0, 12));
        txtTmpReposicion.setName(""); // NOI18N
        txtTmpReposicion.setPreferredSize(new java.awt.Dimension(120, 25));

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setAlignmentX(0.2F);
        chbEstado.setAlignmentY(0.2F);
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

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado");
        lblEstado.setAlignmentX(0.2F);
        lblEstado.setAlignmentY(0.2F);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Código:");
        lblCodigo.setAlignmentX(0.2F);
        lblCodigo.setAlignmentY(0.2F);

        txtCodigo.setEditable(false);
        txtCodigo.setAlignmentX(0.2F);
        txtCodigo.setAlignmentY(0.2F);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setText("Descripción:");
        lblDescripcion.setAlignmentX(0.2F);
        lblDescripcion.setAlignmentY(0.2F);

        txtDescripcion.setEditable(false);
        txtDescripcion.setAlignmentX(0.2F);
        txtDescripcion.setAlignmentY(0.2F);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setName("pcodigo"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));

        lblAviso.setBackground(new java.awt.Color(255, 0, 51));
        lblAviso.setForeground(new java.awt.Color(255, 255, 0));
        lblAviso.setText("Tipo de Parametro");
        lblAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        lblMensaje.setBackground(new java.awt.Color(255, 0, 51));
        lblMensaje.setForeground(new java.awt.Color(255, 255, 0));
        lblMensaje.setText("Tipo de Parametro");
        lblMensaje.setPreferredSize(new java.awt.Dimension(250, 17));

        dteFechaInicio.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaInicio.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaInicio.setDate(Calendar.getInstance().getTime());
        dteFechaInicio.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaInicio.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaInicio.setRequestFocusEnabled(false);
        dteFechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaInicioKeyReleased(evt);
            }
        });

        dteFechaFinal.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaFinal.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaFinal.setDate(Calendar.getInstance().getTime());
        dteFechaFinal.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaFinal.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaFinal.setRequestFocusEnabled(false);
        dteFechaFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaFinalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescripcion)
                        .addGap(10, 10, 10)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblMinimo)
                        .addGap(72, 72, 72)
                        .addComponent(lblMaximo)
                        .addGap(51, 51, 51)
                        .addComponent(lblPeriodo)
                        .addGap(41, 41, 41)
                        .addComponent(lblVigenciaInicio)
                        .addGap(10, 10, 10)
                        .addComponent(lblVigenciaFinal))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(dteFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(dteFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblSuministro)
                        .addGap(19, 19, 19)
                        .addComponent(txtTmpSuministro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblEstado)
                        .addGap(10, 10, 10)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblReposicion)
                        .addGap(18, 18, 18)
                        .addComponent(txtTmpReposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCodigo))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDescripcion))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblMaximo))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblPeriodo))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblVigenciaInicio))
                    .addComponent(lblVigenciaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(dteFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(dteFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblSuministro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTmpSuministro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblEstado))
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReposicion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtTmpReposicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorTDeCambio.setOpaque(false);
        pnlBuscadorTDeCambio.setPreferredSize(new java.awt.Dimension(575, 37));

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
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setSelected(true);
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

        javax.swing.GroupLayout pnlBuscadorTDeCambioLayout = new javax.swing.GroupLayout(pnlBuscadorTDeCambio);
        pnlBuscadorTDeCambio.setLayout(pnlBuscadorTDeCambioLayout);
        pnlBuscadorTDeCambioLayout.setHorizontalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBuscadorTDeCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rbTodos)
                .addGap(5, 5, 5)
                .addComponent(rbAtivos)
                .addGap(5, 5, 5)
                .addComponent(rbNoActivos)
                .addGap(99, 99, 99))
        );
        pnlBuscadorTDeCambioLayout.setVerticalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rbTodos)
            .addComponent(rbAtivos)
            .addComponent(rbNoActivos)
            .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jScrollPanelXPaDeRpDeProd.setViewportView(tblListado);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setPreferredSize(new java.awt.Dimension(550, 50));

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

        javax.swing.GroupLayout pnlAccionesTDeCambioLayout = new javax.swing.GroupLayout(pnlAccionesTDeCambio);
        pnlAccionesTDeCambio.setLayout(pnlAccionesTDeCambioLayout);
        pnlAccionesTDeCambioLayout.setHorizontalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMedicosEspecialidadLayout = new javax.swing.GroupLayout(pnlMedicosEspecialidad);
        pnlMedicosEspecialidad.setLayout(pnlMedicosEspecialidadLayout);
        pnlMedicosEspecialidadLayout.setHorizontalGroup(
            pnlMedicosEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicosEspecialidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMedicosEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPanelXPaDeRpDeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlMedicosEspecialidadLayout.setVerticalGroup(
            pnlMedicosEspecialidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicosEspecialidadLayout.createSequentialGroup()
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelXPaDeRpDeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        panelCurves.add(pnlMedicosEspecialidad, java.awt.BorderLayout.PAGE_START);

        panelImageMedico.add(panelCurves, java.awt.BorderLayout.CENTER);

        add(panelImageMedico, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    
}//GEN-LAST:event_formFocusGained

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            btnGuardar.requestFocus();
            break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void dteFechaInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaInicioKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            dteFechaFinal.requestFocus();
            break;
    }
}//GEN-LAST:event_dteFechaInicioKeyReleased

private void dteFechaFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaFinalKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            chbEstado.requestFocus();
            break;
    }
}//GEN-LAST:event_dteFechaFinalKeyReleased

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setParametroReposicion(mtp.getFila(numRegistros));
        setParametroReposicion();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setParametroReposicion(mtp.getFila(numRegistros));
        setParametroReposicion();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setParametroReposicion(mtp.getFila(numRegistros));
        setParametroReposicion();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setParametroReposicion(mtp.getFila(numRegistros));
        setParametroReposicion();           
        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();        
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        ActivarCasillas();
        txtMinimo.requestFocus();        
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtMinimo.requestFocus();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradas,true)){
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

        long vDias = AtuxUtility.diferenciaEnDias(dteFechaInicio.getCalendar(), dteFechaFinal.getCalendar());
        if (Integer.parseInt(String.valueOf(vDias))>=0){
            JOptionPane.showMessageDialog(this, "La Vigencia Inicio debe de ser Menor a la Vigencia Final", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;

        }
        
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        CargaDatos();
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
        getOptionPane();
        op.setValue(1);
}//GEN-LAST:event_btnSalirActionPerformed

private void txtMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtMaximo.requestFocus();
            break;
    }
}//GEN-LAST:event_txtMinimoKeyReleased

private void txtMaximoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtPeriodo.requestFocus();
            break;
    }
}//GEN-LAST:event_txtMaximoKeyReleased

private void txtPeriodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            dteFechaInicio.requestFocus();
            break;
    }
}//GEN-LAST:event_txtPeriodoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private com.toedter.calendar.JDateChooser dteFechaFinal;
    private com.toedter.calendar.JDateChooser dteFechaInicio;
    private javax.swing.JScrollPane jScrollPanelXPaDeRpDeProd;
    private elaprendiz.gui.label.LabelRect lblAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMaximo;
    private elaprendiz.gui.label.LabelRect lblMensaje;
    private javax.swing.JLabel lblMinimo;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblReposicion;
    private javax.swing.JLabel lblSuministro;
    private javax.swing.JLabel lblVigenciaFinal;
    private javax.swing.JLabel lblVigenciaInicio;
    private elaprendiz.gui.panel.PanelCurves panelCurves;
    private elaprendiz.gui.panel.PanelImage panelImageMedico;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JPanel pnlMedicosEspecialidad;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtMaximo;
    private elaprendiz.gui.textField.TextField txtMinimo;
    private elaprendiz.gui.textField.TextField txtPeriodo;
    private elaprendiz.gui.textField.TextField txtTmpReposicion;
    private elaprendiz.gui.textField.TextField txtTmpSuministro;
    // End of variables declaration//GEN-END:variables
     
}
