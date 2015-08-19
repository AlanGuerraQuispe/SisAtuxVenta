package atux.vistas.catalogo;

import atux.controllers.CUbigeo;
import atux.controllers.CUsuario;
import atux.controllers.CTipoDocumento;
import atux.core.DatoArchivo;
import atux.modelbd.TipoDocumento;
import atux.modelbd.Usuario;
import atux.modelgui.ModeloTablaUsuario;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.Thumbnail;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.util.filechooser.FiltraExtensionArchivos;
import atux.util.filechooser.PanelVistaPrevia;
import atux.util.filechooser.VistaArchivos;
import atux.vistas.buscar.BuscarUbigeo;
import atux.vistas.buscar.BuscarVendedores;
import atux.vistas.utilitario.CambiarPassword;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Cesar Ruiz  PC
 */
public class IVendedores extends javax.swing.JInternalFrame {
    private FileInputStream imagen;
    private CUsuario cp;
    private CUsuario cpf;
    private CTipoDocumento ctd;
    private JFileChooser se;
    private DatoArchivo dat;
    private ModeloTablaUsuario mtp;
    private DefaultComboBoxModel mTipoDocumento;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());
    private final CTipoDocumento controllerTipoDocumento;

    public IVendedores() {
        initComponents();
        se = new JFileChooser();
        cp = new CUsuario();
        cpf = new CUsuario();
        ctd = new CTipoDocumento();
        tblUsuario.setVisible(false);
        controllerTipoDocumento = new CTipoDocumento();          
        mTipoDocumento = new DefaultComboBoxModel(controllerTipoDocumento.getTipoDocumento().toArray());
        this.cmbTipoDocumento.setModel(mTipoDocumento);        
        this.cmbTipoDocumento.setSelectedIndex(0);
        mtp = new ModeloTablaUsuario(inicioPag,finalPag);
        tblUsuario.setModel(mtp);

        setFiltroTexto();        
        setEventSelectionModel(tblUsuario.getSelectionModel());
        DesActivarCasillas();
        rbAtivosActionPerformed(null);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtLogin.getDocument(), FiltraEntrada.NUM_LETRAS, 15, false);
        Helper.setFiltraEntrada(txtPassword.getDocument(), FiltraEntrada.NUM_LETRAS, 8, false);
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.NUM_LETRAS, 8, false);
        Helper.setFiltraEntrada(txtNroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 8, false);
        Helper.setFiltraEntrada(txtApellidoPaterno.getDocument(), FiltraEntrada.SOLO_LETRAS, 40, false);
        Helper.setFiltraEntrada(txtApellidoMaterno.getDocument(), FiltraEntrada.SOLO_LETRAS, 40, false);
        Helper.setFiltraEntrada(txtNombres.getDocument(), FiltraEntrada.SOLO_LETRAS, 60, false);
        Helper.setFiltraEntrada(txtDireccion.getDocument(), FiltraEntrada.SOLO_LETRAS, 100, false);
        Helper.setFiltraEntrada(txtCodigoDepartamento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtCodigoProvincia.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtCodigoDistrito.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtFijo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtMovil.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtMovil2.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtEmail.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
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
        if(tblUsuario.getSelectedRow() != -1){
            numRegistros = tblUsuario.getSelectedRow();
            cp.setUsuario(mtp.getFila(tblUsuario.getSelectedRow()));
            try {
                setUsuario();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
    private void setUsuario() throws FileNotFoundException, IOException, SQLException{
        Limpiar();
        txtLogin.setText(cp.getUsuario().getIdUsuario());
        txtPassword.setText(cp.getUsuario().getDeClave());
        txtCodigo.setText(cp.getUsuario().getNuEmpleado());
        txtNroDocumento.setText(cp.getUsuario().getNuDocIdentidad());
        txtApellidoPaterno.setText(cp.getUsuario().getApPaterno());
        txtApellidoMaterno.setText(cp.getUsuario().getApMaterno());
        txtNombres.setText(cp.getUsuario().getNombre());
        txtDireccion.setText(cp.getUsuario().getDeDireccionUsuario());
        if (cp.getUsuario().getDeUbigeoDireccion()!= null){
            txtCodigoDepartamento.setText(cp.getUsuario().getDeUbigeoDireccion().substring(0,3));
            txtCodigoProvincia.setText(cp.getUsuario().getDeUbigeoDireccion().substring(3,6));
            txtCodigoDistrito.setText(cp.getUsuario().getDeUbigeoDireccion().substring(6,9));
            CUbigeo BG1 = new CUbigeo();
            if (!"".equals(txtCodigoDepartamento.getText()))
                txtDepartamento.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),null,null));
            if (!"".equals(txtCodigoProvincia.getText()))
                txtProvincia.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),null));
            if (!"".equals(txtCodigoDistrito.getText()))
                txtDistrito.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),txtCodigoDistrito.getText()));
            
        }
        txtFijo.setText(cp.getUsuario().getNuTelNormal());
        txtMovil.setText(cp.getUsuario().getNuTelMovil());
        txtMovil2.setText(cp.getUsuario().getNuTelRef());
        txtEmail.setText(cp.getUsuario().getNoEmail());
        this.dteFechaIngreso.setDate(cp.getUsuario().getFeIngreso());
        this.dteFechaNacimiento.setDate(cp.getUsuario().getFeNacimiento());
        this.dteFechaCese.setDate(cp.getUsuario().getFeCese());

        this.cmbSexo.setSelectedIndex(0);
        if ("M".equals(cp.getUsuario().getInSexo())){
            this.cmbSexo.setSelectedIndex(1);
        }else if ("D".equals(cp.getUsuario().getInSexo())){
            this.cmbSexo.setSelectedIndex(2);
        }
        if (cp.getUsuario().getTiDocIdentidad()!= null){
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento = ctd.getRegistroPorPk(new Object[]{cp.getUsuario().getTiDocIdentidad()});

            for (int i=0; i<=cmbTipoDocumento.getItemCount(); i++){
                if (this.cmbTipoDocumento.getItemAt(i).toString().equals(tipoDocumento.getDeAbrDocumento())){
                    this.cmbTipoDocumento.setSelectedIndex(i);
                    break;
                }
            }
        }
        if("A".equals(cp.getUsuario().getEsUsuario())){
            chbSetActivo(true);
        }else{
            chbSetActivo(false);
        }
        

        File file = new File("oracle.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        cpf = new CUsuario();
        Usuario fotoUsuario = new Usuario();
        fotoUsuario = cpf.getFotoPersonal(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,cp.getUsuario().getNuSecUsuario()});
        
        if (fotoUsuario != null){
            Blob bin = fotoUsuario.getDeFotoUsuario();
            InputStream inStream = bin.getBinaryStream();
            int size = (int)bin.length();
            byte[] buffer = new byte[size];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1)
                fos.write(buffer, 0, length);
            fos.close();
            ImageIcon fileImg=new ImageIcon("oracle.jpg");
            ImageIcon fileImg1=new ImageIcon(fileImg.getImage().getScaledInstance(-1,-1, Image.SCALE_DEFAULT));
            int w=fileImg1.getIconWidth();
            int h=fileImg1.getIconHeight();
            System.out.println("Real: "+w+" "+h);
            if((w >= 900 && w<=1200) || (h >= 800 && w<=900))
            {
                w=(int)(w*0.7);
                h=(int)(h*0.7);
            }
            else if(w>1200 || h>900)
            {
                while(w>1200 || h>900)
                {
                    w=(int)(w*0.5);
                    h=(int)(h*0.5);
                }
            }
            System.out.println("Modificada: "+w+" "+h);
            pnlImagen.setIcon(new ImageIcon(fileImg1.getImage().getScaledInstance(w,h, Image.SCALE_DEFAULT)));
            pnlImagen.setBounds(new Rectangle(10,10,w,h));
        }

        this.btnModificar.setEnabled(true);
        this.bntPass.setEnabled(true);
    }

    private void Limpiar(){
        txtLogin.setText("");
        txtPassword.setText("");
        txtCodigo.setText("");
        txtNroDocumento.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtNombres.setText("");
        txtDireccion.setText("");
        txtCodigoDepartamento.setText("");
        txtCodigoProvincia.setText("");
        txtCodigoDistrito.setText("");
        
        txtDepartamento.setText("");
        txtProvincia.setText("");
        txtDistrito.setText("");
        
        txtFijo.setText("");
        txtMovil.setText("");
        txtMovil2.setText("");
        txtEmail.setText("");
        
        this.dteFechaIngreso.setDate(null);
        this.dteFechaNacimiento.setDate(null);
        this.dteFechaCese.setDate(null);
        cmbSexo.setSelectedIndex(0);
        cmbTipoDocumento.setSelectedIndex(0);

        this.chbEstado.setSelected(true);
        chbSetActivo(true);
//        this.pnlImagen.setVisible(false);
//        this.pnlImagen.setIcon(new ImageIcon("null"));
        this.pnlImagen.updateUI();        
        this.pnlImagen.setVisible(true);
        
    }

    private void ActivarCasillas(){
        pnlEntradasObligatorias.setEnabled(false);
        pnlEntradasComplementarias.setEnabled(false);

        ECampos.setEditableTexto(this.pnlEntradasObligatorias, true, new Component[]{lblApellidoMaterno,lblApellidoPaterno,lblCodigo,lblDepartamento,lblDireccion,lblDistrito,lblEstado,lblLogin,lblNombres,lblNroDocumento,lblPassword,lblProvincia,lblSexo,lblTipoDocumento},false,"");
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, true, new Component[]{lblEmail,lblTeleFijo,lblTeleMovil,lblTeleMovil2,lblFechaCese,lblFechaIngreso,lblFechaNacimiento},false,"");
        txtLogin.setEnabled(false);
        txtPassword.setEnabled(false);
        cmbTipoDocumento.setEnabled(true);
        cmbSexo.setEnabled(true);
        dteFechaCese.setEnabled(true);
        dteFechaIngreso.setEnabled(true);
        dteFechaNacimiento.setEnabled(true);
        
        this.tblUsuario.setEnabled(false);
        this.tblUsuario.clearSelection();
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);
        this.bntPass.setEnabled(true);

        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.btnBuscar.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        this.btnAgregarFoto.setEnabled(true);
    }
    private void DesActivarCasillas(){
        this.pnlEntradasObligatorias.setEnabled(true);
        this.pnlEntradasComplementarias.setEnabled(true);
        txtLogin.setEnabled(true);
        txtPassword.setEnabled(true);
        
        ECampos.setEditableTexto(this.pnlEntradasObligatorias, false, new Component[]{lblApellidoMaterno,lblApellidoPaterno,lblCodigo,lblDepartamento,lblDireccion,lblDistrito,lblEstado,lblLogin,lblNombres,lblNroDocumento,lblPassword,lblProvincia,lblSexo,lblTipoDocumento},true,"");        
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, false, new Component[]{lblEmail,lblTeleFijo,lblTeleMovil,lblTeleMovil2,lblFechaCese,lblFechaIngreso,lblFechaNacimiento},true,"");

        cmbTipoDocumento.setEnabled(false);
        cmbSexo.setEnabled(false);
        dteFechaCese.setEnabled(false);
        dteFechaIngreso.setEnabled(false);
        dteFechaNacimiento.setEnabled(false);
        
        this.tblUsuario.setEnabled(true);
        this.tblUsuario.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);
        this.bntPass.setEnabled(true);

        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.btnBuscar.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);        
        this.btnAgregarFoto.setEnabled(false);
        
        esActualizacion = false;
//        this.pnlBuscador.setVisible(true);
        logger.info(txtCodigo.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        String Ubigeo = txtCodigoDepartamento.getText()+txtCodigoProvincia.getText()+txtCodigoDistrito.getText();
        if(!this.txtLogin.getText().equals(cp.getUsuario().getIdUsuario())){
            return true;
        }else if(!this.txtPassword.getText().equals(cp.getUsuario().getDeClave())){
            return true;
        }else if(!this.txtCodigo.getText().equals(cp.getUsuario().getNuEmpleado())){
            return true;
        }else if(!this.txtNroDocumento.getText().equals(cp.getUsuario().getNuDocIdentidad())){
            return true;
        }else if(!this.txtApellidoPaterno.getText().equals(cp.getUsuario().getApPaterno())){
            return true;
        }else if(!this.txtApellidoMaterno.getText().equals(cp.getUsuario().getApMaterno())){
            return true;
        }else if(!this.txtNombres.getText().equals(cp.getUsuario().getNombre())){
            return true;
        }else if(!this.txtDireccion.getText().equals(cp.getUsuario().getDeDireccionUsuario())){
            return true;
        }else if(!Ubigeo.equals(cp.getUsuario().getDeUbigeoDireccion())){
            return true;
        }else if(!this.txtFijo.getText().equals(cp.getUsuario().getNuTelNormal())){
            return true;
        }else if(!this.txtMovil.getText().equals(cp.getUsuario().getNuTelMovil())){
            return true;
        }else if(!this.txtMovil2.getText().equals(cp.getUsuario().getNuTelRef())){
            return true;
        }else if(!this.txtEmail.getText().equals(cp.getUsuario().getNoEmail())){
            return true;
        }else if(this.dteFechaCese.getDate() != cp.getUsuario().getFeCese()){
            return true;
        }else if(this.dteFechaIngreso.getDate() != cp.getUsuario().getFeIngreso()){
            return true;
        }else if(this.dteFechaNacimiento.getDate() != cp.getUsuario().getFeNacimiento()){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getUsuario().getEsUsuario()))){
            return true;
        }

        return false;
    }
    
    private boolean guardarActualizar() throws SQLException, FileNotFoundException{
        txtApellidoPaterno.setText(txtApellidoPaterno.getText().toUpperCase());
        txtApellidoMaterno.setText(txtApellidoMaterno.getText().toUpperCase());
        txtNombres.setText(txtNombres.getText().toUpperCase());
        txtDireccion.setText(txtDireccion.getText().toUpperCase());

        cp.getUsuario().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getUsuario().setCoLocal(AtuxVariables.vCodigoLocal);

        cp.getUsuario().setIdUsuario(txtLogin.getText());
        cp.getUsuario().setDeClave(txtPassword.getText());
        cp.getUsuario().setNuEmpleado(txtCodigo.getText());
        cp.getUsuario().setNuDocIdentidad(txtNroDocumento.getText());
        cp.getUsuario().setApPaterno(txtApellidoPaterno.getText());
        cp.getUsuario().setApMaterno(txtApellidoMaterno.getText());
        cp.getUsuario().setNombre(txtNombres.getText());
        cp.getUsuario().setDeDireccionUsuario(txtDireccion.getText());
        cp.getUsuario().setDeUbigeoDireccion(txtCodigoDepartamento.getText()+txtCodigoProvincia.getText()+txtCodigoDistrito.getText());
        cp.getUsuario().setNuTelNormal(txtFijo.getText());
        cp.getUsuario().setNuTelMovil(txtMovil.getText());
        cp.getUsuario().setNuTelRef(txtMovil2.getText());
        cp.getUsuario().setNoEmail(txtEmail.getText());
        cp.getUsuario().setFeIngreso(this.dteFechaIngreso.getDate());
        cp.getUsuario().setFeNacimiento(this.dteFechaNacimiento.getDate());
        cp.getUsuario().setFeCese(this.dteFechaCese.getDate());
        cp.getUsuario().setInSexo(cmbSexo.getSelectedItem().toString().substring(0, 1));
        cp.getUsuario().setTiDocIdentidad(((TipoDocumento)this.cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad());
        if(imagen != null){
            cp.getUsuario().setFotoDA(dat);
        }
        if (chbEstado.isSelected()){
            cp.getUsuario().setEsUsuario("A");
        }else{
            cp.getUsuario().setEsUsuario("I");
        }

        Boolean EstadoGuardar=false;

        if(!esActualizacion){
            cp.getUsuario().setIdCreaUsuario(AtuxVariables.vIdUsuario);
            cp.getUsuario().setFeCreaUsuario(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            cp.getUsuario().setNuSecUsuario(cp.getNuevoCodigo());
            EstadoGuardar = cp.guardarRegistro(cp.getUsuario());
            
        }else{
            cp.getUsuario().setIdModUsuario(AtuxVariables.vIdUsuario);
            cp.getUsuario().setFeModUsuario(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getUsuario());
            if (act ==1) {
                EstadoGuardar = true;
            }
        }

        if(imagen != null){
            int eliFoto = cp.eliminarFotoPersonal(cp.getUsuario());
//            if (eliFoto==1)
                EstadoGuardar = cp.guardarFotoPersonal(cp.getUsuario());
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
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
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
        pnlEntradasObligatorias = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        lblNroDocumento = new javax.swing.JLabel();
        lblTipoDocumento = new javax.swing.JLabel();
        cmbTipoDocumento = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblCodigo = new javax.swing.JLabel();
        pnlFoto = new javax.swing.JPanel();
        pnlImagen = new elaprendiz.gui.panel.PanelImage();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtApellidoPaterno = new elaprendiz.gui.textField.TextField();
        txtApellidoMaterno = new elaprendiz.gui.textField.TextField();
        txtNombres = new elaprendiz.gui.textField.TextField();
        lblNombres = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        cmbSexo = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        txtCodigoDepartamento = new elaprendiz.gui.textField.TextField();
        txtDepartamento = new elaprendiz.gui.textField.TextField();
        lblDepartamento = new javax.swing.JLabel();
        txtCodigoProvincia = new elaprendiz.gui.textField.TextField();
        txtProvincia = new elaprendiz.gui.textField.TextField();
        lblProvincia = new javax.swing.JLabel();
        txtDistrito = new elaprendiz.gui.textField.TextField();
        txtCodigoDistrito = new elaprendiz.gui.textField.TextField();
        lblDistrito = new javax.swing.JLabel();
        btnAgregarFoto = new elaprendiz.gui.button.ButtonRect();
        txtNroDocumento = new elaprendiz.gui.textField.TextField();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        jPanel2 = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtLogin = new elaprendiz.gui.textField.TextField();
        txtPassword = new elaprendiz.gui.passwordField.PasswordField();
        pnlEntradasComplementarias = new javax.swing.JPanel();
        lblFechaNacimiento = new javax.swing.JLabel();
        dteFechaNacimiento = new com.toedter.calendar.JDateChooser();
        dteFechaIngreso = new com.toedter.calendar.JDateChooser();
        lblFechaIngreso = new javax.swing.JLabel();
        dteFechaCese = new com.toedter.calendar.JDateChooser();
        lblFechaCese = new javax.swing.JLabel();
        lblTeleFijo = new javax.swing.JLabel();
        txtFijo = new elaprendiz.gui.textField.TextField();
        txtMovil = new elaprendiz.gui.textField.TextField();
        lblTeleMovil = new javax.swing.JLabel();
        txtMovil2 = new elaprendiz.gui.textField.TextField();
        lblTeleMovil2 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new elaprendiz.gui.textField.TextField();
        tblUsuario = new javax.swing.JTable();
        pnlBuscador = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAcciones = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        bntPass = new elaprendiz.gui.button.ButtonRect();

        setBorder(null);
        setTitle("Formulario de Vendedores");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasObligatorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Obligatorios", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasObligatorias.setOpaque(false);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

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

        lblNroDocumento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNroDocumento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNroDocumento.setText("Nro Documento:");

        lblTipoDocumento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoDocumento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDocumento.setText("Tipo Documento");
        lblTipoDocumento.setAlignmentX(0.2F);
        lblTipoDocumento.setAlignmentY(0.2F);

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

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigo.setText("Codigo:");

        pnlFoto.setToolTipText("Click Para cambiar de Imagen");
        pnlFoto.setEnabled(false);
        pnlFoto.setOpaque(false);
        pnlFoto.setPreferredSize(new java.awt.Dimension(140, 140));
        pnlFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlFotoMouseClicked(evt);
            }
        });

        pnlImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/defaultlarge.gif"))); // NOI18N

        javax.swing.GroupLayout pnlImagenLayout = new javax.swing.GroupLayout(pnlImagen);
        pnlImagen.setLayout(pnlImagenLayout);
        pnlImagenLayout.setHorizontalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlImagenLayout.setVerticalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFotoLayout = new javax.swing.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblApellidoPaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoPaterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApellidoPaterno.setText("Ap. Paterno");
        lblApellidoPaterno.setAlignmentX(0.2F);
        lblApellidoPaterno.setAlignmentY(0.2F);

        txtApellidoPaterno.setEditable(false);
        txtApellidoPaterno.setAlignmentX(0.2F);
        txtApellidoPaterno.setAlignmentY(0.2F);
        txtApellidoPaterno.setFont(new java.awt.Font("Arial", 0, 12));
        txtApellidoPaterno.setPreferredSize(new java.awt.Dimension(320, 25));
        txtApellidoPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoPaternoKeyReleased(evt);
            }
        });

        txtApellidoMaterno.setEditable(false);
        txtApellidoMaterno.setAlignmentX(0.2F);
        txtApellidoMaterno.setAlignmentY(0.2F);
        txtApellidoMaterno.setFont(new java.awt.Font("Arial", 0, 12));
        txtApellidoMaterno.setName("prover"); // NOI18N
        txtApellidoMaterno.setPreferredSize(new java.awt.Dimension(320, 25));
        txtApellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoMaternoKeyReleased(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setAlignmentX(0.2F);
        txtNombres.setAlignmentY(0.2F);
        txtNombres.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombres.setName("prover"); // NOI18N
        txtNombres.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombres.setText("Nombres");
        lblNombres.setAlignmentX(0.2F);
        lblNombres.setAlignmentY(0.2F);

        lblApellidoMaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoMaterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApellidoMaterno.setText("Ap. Materno");
        lblApellidoMaterno.setAlignmentX(0.2F);
        lblApellidoMaterno.setAlignmentY(0.2F);

        lblSexo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSexo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSexo.setText("Sexo:");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "----------------", "Masculino", "Femenino" }));
        cmbSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSexoKeyReleased(evt);
            }
        });

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDireccion.setText("Direcci�n");
        lblDireccion.setAlignmentX(0.2F);
        lblDireccion.setAlignmentY(0.2F);

        txtDireccion.setEditable(false);
        txtDireccion.setAlignmentX(0.2F);
        txtDireccion.setAlignmentY(0.2F);
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDireccion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtCodigoDepartamento.setEditable(false);
        txtCodigoDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDepartamento.setAlignmentX(0.2F);
        txtCodigoDepartamento.setAlignmentY(0.2F);
        txtCodigoDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDepartamentoKeyReleased(evt);
            }
        });

        txtDepartamento.setEditable(false);
        txtDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDepartamento.setAlignmentX(0.2F);
        txtDepartamento.setAlignmentY(0.2F);
        txtDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));

        lblDepartamento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDepartamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDepartamento.setText("Departamento");
        lblDepartamento.setAlignmentX(0.2F);
        lblDepartamento.setAlignmentY(0.2F);

        txtCodigoProvincia.setEditable(false);
        txtCodigoProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoProvincia.setAlignmentX(0.2F);
        txtCodigoProvincia.setAlignmentY(0.2F);
        txtCodigoProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoProvincia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProvinciaKeyReleased(evt);
            }
        });

        txtProvincia.setEditable(false);
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProvincia.setAlignmentX(0.2F);
        txtProvincia.setAlignmentY(0.2F);
        txtProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtProvincia.setPreferredSize(new java.awt.Dimension(120, 25));

        lblProvincia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProvincia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProvincia.setText("Provincia");
        lblProvincia.setAlignmentX(0.2F);
        lblProvincia.setAlignmentY(0.2F);

        txtDistrito.setEditable(false);
        txtDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDistrito.setAlignmentX(0.2F);
        txtDistrito.setAlignmentY(0.2F);
        txtDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtDistrito.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCodigoDistrito.setEditable(false);
        txtCodigoDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDistrito.setAlignmentX(0.2F);
        txtCodigoDistrito.setAlignmentY(0.2F);
        txtCodigoDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDistrito.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDistritoKeyReleased(evt);
            }
        });

        lblDistrito.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDistrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistrito.setText("Distrito");
        lblDistrito.setAlignmentX(0.2F);
        lblDistrito.setAlignmentY(0.2F);

        btnAgregarFoto.setBackground(new java.awt.Color(102, 204, 0));
        btnAgregarFoto.setText("Agregar Foto");
        btnAgregarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFotoActionPerformed(evt);
            }
        });

        txtNroDocumento.setEditable(false);
        txtNroDocumento.setAlignmentX(0.2F);
        txtNroDocumento.setAlignmentY(0.2F);
        txtNroDocumento.setFont(new java.awt.Font("Arial", 0, 12));
        txtNroDocumento.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroDocumentoKeyReleased(evt);
            }
        });

        txtCodigo.setEditable(false);
        txtCodigo.setAlignmentX(0.2F);
        txtCodigo.setAlignmentY(0.2F);
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        jPanel2.setEnabled(false);
        jPanel2.setOpaque(false);

        lblLogin.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblLogin.setText("Login:");

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPassword.setText("Contrase�a:");

        txtLogin.setEditable(false);
        txtLogin.setAlignmentX(0.2F);
        txtLogin.setAlignmentY(0.2F);
        txtLogin.setFont(new java.awt.Font("Arial", 0, 12));
        txtLogin.setPreferredSize(new java.awt.Dimension(320, 25));

        txtPassword.setText("passwordField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogin)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPassword)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlEntradasObligatoriasLayout = new javax.swing.GroupLayout(pnlEntradasObligatorias);
        pnlEntradasObligatorias.setLayout(pnlEntradasObligatoriasLayout);
        pnlEntradasObligatoriasLayout.setHorizontalGroup(
            pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(lblTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(lblNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(lblEstado))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnAgregarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlEntradasObligatoriasLayout.setVerticalGroup(
            pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                .addGroup(pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(lblSexo))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblCodigo))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblNroDocumento))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblEstado))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasObligatoriasLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlEntradasObligatoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEntradasComplementarias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Complementarios"));
        pnlEntradasComplementarias.setOpaque(false);

        lblFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaNacimiento.setText("Fecha Nacimiento:");
        lblFechaNacimiento.setAlignmentX(0.2F);
        lblFechaNacimiento.setAlignmentY(0.2F);

        dteFechaNacimiento.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaNacimiento.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaNacimiento.setDate(Calendar.getInstance().getTime());
        dteFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dteFechaNacimiento.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaNacimiento.setRequestFocusEnabled(false);
        dteFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaNacimientoKeyReleased(evt);
            }
        });

        dteFechaIngreso.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaIngreso.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaIngreso.setDate(Calendar.getInstance().getTime());
        dteFechaIngreso.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dteFechaIngreso.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaIngreso.setRequestFocusEnabled(false);
        dteFechaIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaIngresoKeyReleased(evt);
            }
        });

        lblFechaIngreso.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaIngreso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaIngreso.setText("Fecha Ingreso:");
        lblFechaIngreso.setAlignmentX(0.2F);
        lblFechaIngreso.setAlignmentY(0.2F);

        dteFechaCese.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaCese.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaCese.setDate(Calendar.getInstance().getTime());
        dteFechaCese.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dteFechaCese.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaCese.setRequestFocusEnabled(false);
        dteFechaCese.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaCeseKeyReleased(evt);
            }
        });

        lblFechaCese.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaCese.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaCese.setText("Fecha Cese:");
        lblFechaCese.setAlignmentX(0.2F);
        lblFechaCese.setAlignmentY(0.2F);

        lblTeleFijo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTeleFijo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeleFijo.setText("Telef. Fijo");
        lblTeleFijo.setAlignmentX(0.2F);
        lblTeleFijo.setAlignmentY(0.2F);

        txtFijo.setEditable(false);
        txtFijo.setAlignmentX(0.2F);
        txtFijo.setAlignmentY(0.2F);
        txtFijo.setFont(new java.awt.Font("Arial", 0, 12));
        txtFijo.setPreferredSize(new java.awt.Dimension(320, 25));
        txtFijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFijoKeyReleased(evt);
            }
        });

        txtMovil.setEditable(false);
        txtMovil.setAlignmentX(0.2F);
        txtMovil.setAlignmentY(0.2F);
        txtMovil.setFont(new java.awt.Font("Arial", 0, 12));
        txtMovil.setPreferredSize(new java.awt.Dimension(320, 25));
        txtMovil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMovilKeyReleased(evt);
            }
        });

        lblTeleMovil.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTeleMovil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeleMovil.setText("Telef. Movil");
        lblTeleMovil.setAlignmentX(0.2F);
        lblTeleMovil.setAlignmentY(0.2F);

        txtMovil2.setEditable(false);
        txtMovil2.setAlignmentX(0.2F);
        txtMovil2.setAlignmentY(0.2F);
        txtMovil2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMovil2.setPreferredSize(new java.awt.Dimension(320, 25));
        txtMovil2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMovil2KeyReleased(evt);
            }
        });

        lblTeleMovil2.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTeleMovil2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeleMovil2.setText("Telef. Movil 02:");
        lblTeleMovil2.setAlignmentX(0.2F);
        lblTeleMovil2.setAlignmentY(0.2F);

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("E-mail");
        lblEmail.setAlignmentX(0.2F);
        lblEmail.setAlignmentY(0.2F);

        txtEmail.setEditable(false);
        txtEmail.setAlignmentX(0.2F);
        txtEmail.setAlignmentY(0.2F);
        txtEmail.setFont(new java.awt.Font("Arial", 0, 12));
        txtEmail.setPreferredSize(new java.awt.Dimension(320, 25));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
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

        javax.swing.GroupLayout pnlEntradasComplementariasLayout = new javax.swing.GroupLayout(pnlEntradasComplementarias);
        pnlEntradasComplementarias.setLayout(pnlEntradasComplementariasLayout);
        pnlEntradasComplementariasLayout.setHorizontalGroup(
            pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addComponent(lblFechaNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dteFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(dteFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFechaCese, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dteFechaCese, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTeleFijo)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                                .addComponent(txtFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTeleMovil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTeleMovil2)
                                .addGap(2, 2, 2)
                                .addComponent(txtMovil2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(tblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlEntradasComplementariasLayout.setVerticalGroup(
            pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaCese, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dteFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dteFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dteFechaCese, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTeleFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTeleMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMovil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTeleMovil2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscador.setOpaque(false);
        pnlBuscador.setPreferredSize(new java.awt.Dimension(575, 37));

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

        javax.swing.GroupLayout pnlBuscadorLayout = new javax.swing.GroupLayout(pnlBuscador);
        pnlBuscador.setLayout(pnlBuscadorLayout);
        pnlBuscadorLayout.setHorizontalGroup(
            pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
            .addGroup(pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorLayout.createSequentialGroup()
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
        pnlBuscadorLayout.setVerticalGroup(
            pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAcciones.setOpaque(false);

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

        bntPass.setBackground(new java.awt.Color(51, 153, 255));
        bntPass.setText("Cambiar Contrase�a");
        bntPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bntPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlBuscador, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Formulario Tipo de Cambio");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblUsuario.getRowCount()-1;
        numRegistros=0;
        cp.setUsuario(mtp.getFila(numRegistros));
        try {
            setUsuario();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblUsuario.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setUsuario(mtp.getFila(numRegistros));
        try {
            setUsuario();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblUsuario.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setUsuario(mtp.getFila(numRegistros));
        try {
            setUsuario();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblUsuario.getRowCount()-1;
        numRegistros=finalPag;
        cp.setUsuario(mtp.getFila(numRegistros));
        try {
            setUsuario();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaUsuario(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblUsuario.setModel(mtp);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaUsuario(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblUsuario.setModel(mtp);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaUsuario(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblUsuario.setModel(mtp);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        cp.getUsuario().setNuSecUsuario(cp.getNuevoCodigo());        
        txtCodigo.requestFocus();
        ActivarCasillas();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!esActualizacion){
            cp.getUsuario().setNuSecUsuario(cp.getNuevoCodigo());
        }

        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasObligatorias,true)){
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

        if (cmbTipoDocumento.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Debe Seleccionar un tipo de Documento Valido", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (cmbSexo.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Debe Seleccionar el Sexo del Personal", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;    
        }

        txtLogin.setText(txtNombres.getText().substring(0, 1) + txtApellidoPaterno.getText());
        if (txtLogin.getText().length()>8){
            txtPassword.setText(txtLogin.getText().substring(0, 8));
        }else{
            txtPassword.setText(txtLogin.getText());
        }

        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasObligatorias,false);
            return;
        }

        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Informaci�n Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaUsuario(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaUsuario(tipoSeleccion,inicioPag,finalPag);
        }
        tblUsuario.setModel(mtp);
        this.numRegistros = mtp.getCantidadRegistros();
        DesActivarCasillas();
        tblUsuario.requestFocus();
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

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarVendedores bUsuario = new BuscarVendedores();
        JLabel aviso = bUsuario.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Tipos de Cambios existentes":(tipoSeleccion==1?"Mostrando todo los Tipos de Cambios activos":"Mostrando todo los Tipos de Cambios No activos"));
        JOptionPane.showInternalOptionDialog(this, bUsuario, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(bUsuario.getUsuario() != null){
           cp.setUsuario(bUsuario.getUsuario());
            try {
                setUsuario();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void cmbTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoActionPerformed
        int Largo = Integer.parseInt(((TipoDocumento)cmbTipoDocumento.getSelectedItem()).getNuCaracteres()); 
        if (Largo == 0){
            txtNroDocumento.setEnabled(false);
        }else{
            txtNroDocumento.setEnabled(true);
            Helper.setFiltraEntrada(txtNroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, Largo, false);
        }
}//GEN-LAST:event_cmbTipoDocumentoActionPerformed

private void cmbTipoDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNroDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoDocumentoKeyReleased

private void pnlFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFotoMouseClicked
//    if(pnlFoto.isEnabled())
//    {
//        JFileChooser se = new JFileChooser();
//        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        se.setMultiSelectionEnabled(false);
//        se.setAccessory(new PanelVistaPrevia(se));
//        se.setFileFilter(new FiltraExtensionArchivos());
//        se.setFileView(new VistaArchivos());
//        int estado = se.showOpenDialog(null);
//        if(estado == JFileChooser.APPROVE_OPTION)
//        {
//            try {
//                Thumbnail tn;
//                imagen = new FileInputStream(se.getSelectedFile());
//                Image icono = ImageIO.read(imagen).getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);
//                tn = new Thumbnail(icono,System.getProperty("user.dir")+"/"+se.getSelectedFile().getName());
//                imagen = tn.generarThumbnail();
//                this.pnlImagen.setIcon(new ImageIcon(System.getProperty("user.dir")+"/"+se.getSelectedFile().getName()));
//                this.pnlImagen.updateUI();
//                dat = new DatoArchivo(imagen,(int)se.getSelectedFile().length());
//                    
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(IUsuario.class.getName()).log(Level.SEVERE, null, ex);
//            }catch (IOException ex) {
//                        Logger.getLogger(IUsuario.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//        }
//    }
}//GEN-LAST:event_pnlFotoMouseClicked

private void txtApellidoPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtApellidoMaterno.requestFocus();
             break;
    }
}//GEN-LAST:event_txtApellidoPaternoKeyReleased

private void txtApellidoMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombres.requestFocus();
             break;
    }
}//GEN-LAST:event_txtApellidoMaternoKeyReleased

private void cmbSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSexoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDireccion.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbSexoKeyReleased

private void dteFechaNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaNacimientoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaIngreso.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaNacimientoKeyReleased

private void dteFechaIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaIngresoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaCese.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaIngresoKeyReleased

private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtCodigoDepartamento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDireccionKeyReleased

private void txtCodigoDepartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            txtCodigoProvincia.setText("");
            txtCodigoDistrito.setText("");
            txtProvincia.setText("");
            txtDistrito.setText("");

            BuscarUbigeo pvc = new BuscarUbigeo();

            JLabel aviso = pvc.getAviso();

                        String msj = "Mostrando Departamento";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoDepartamento.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtDepartamento.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoProvincia.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDepartamentoKeyReleased

private void txtCodigoProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            txtCodigoDistrito.setText("");
            txtDistrito.setText("");

            BuscarUbigeo pvc = new BuscarUbigeo(txtCodigoDepartamento.getText());
            pvc.setDetalleDepartamento(txtDepartamento.getText());
            JLabel aviso = pvc.getAviso();

            String msj = "Mostrando Provincias";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoProvincia.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtProvincia.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoDistrito.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoProvinciaKeyReleased

private void txtCodigoDistritoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDistritoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            BuscarUbigeo pvc = new BuscarUbigeo(txtCodigoDepartamento.getText(), txtCodigoProvincia.getText());
            JLabel aviso = pvc.getAviso();
            pvc.setDetalleDepartamento(txtDepartamento.getText());
            pvc.setDetalleProvincia(txtProvincia.getText());
            String msj = "Mostrando Provincias";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getUbigeo() != null){
                CUbigeo cpLC = new CUbigeo();
                cpLC.setUbigeo(pvc.getUbigeo());
                this.txtCodigoDistrito.setText(cpLC.getUbigeo().getCoUbigeo());
                this.txtDistrito.setText(cpLC.getUbigeo().getDeUbigeo());
            }            
            break;
        case KeyEvent.VK_ENTER: 
            dteFechaNacimiento.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDistritoKeyReleased

private void btnAgregarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFotoActionPerformed
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        se.setMultiSelectionEnabled(false);
        se.setAccessory(new PanelVistaPrevia(se));
        se.setFileFilter(new FiltraExtensionArchivos());
        se.setFileView(new VistaArchivos());
        int estado = se.showOpenDialog(null);
        if(estado == JFileChooser.APPROVE_OPTION)
        {
            try {

                Thumbnail tn;
                imagen = new FileInputStream(se.getSelectedFile());
                Image icono = ImageIO.read(imagen).getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);
                tn = new Thumbnail(icono,System.getProperty("user.dir")+"/"+se.getSelectedFile().getName());
                imagen = tn.generarThumbnail();
                this.pnlImagen.setIcon(new ImageIcon(System.getProperty("user.dir")+"/"+se.getSelectedFile().getName()));
                this.pnlImagen.updateUI();
                dat = new DatoArchivo(imagen,(int)se.getSelectedFile().length());
                    
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                        Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    
    
    
    
    //        se = new JFileChooser();
//        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        se.setMultiSelectionEnabled(false);
//        se.setAccessory(new PanelVistaPrevia(se));
//        se.setFileFilter(new FiltraExtensionArchivos());
//        se.setFileView(new VistaArchivos());
//        int estado = se.showOpenDialog(null);
//        if(estado == JFileChooser.APPROVE_OPTION)
//        {
//            try {
//
//                Thumbnail tn;
//                imagen = new FileInputStream(se.getSelectedFile());
//                Image icono = ImageIO.read(imagen).getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);
//                tn = new Thumbnail(icono,System.getProperty("user.dir")+"/"+se.getSelectedFile().getName());
//                imagen = tn.generarThumbnail();
//                this.pnlImagen.setIcon(new ImageIcon(System.getProperty("user.dir")+"/"+se.getSelectedFile().getName()));
//                this.pnlImagen.updateUI();
//                dat = new DatoArchivo(imagen,(int)se.getSelectedFile().length());
//                    
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(IUsuario.class.getName()).log(Level.SEVERE, null, ex);
//            }catch (IOException ex) {
//                        Logger.getLogger(IUsuario.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//        }
}//GEN-LAST:event_btnAgregarFotoActionPerformed

private void dteFechaCeseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaCeseKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtFijo.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaCeseKeyReleased

private void txtFijoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFijoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtMovil.requestFocus();
             break;
    }
}//GEN-LAST:event_txtFijoKeyReleased

private void txtMovilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMovilKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtMovil2.requestFocus();
             break;
    }
}//GEN-LAST:event_txtMovilKeyReleased

private void txtMovil2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMovil2KeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtEmail.requestFocus();
             break;
    }
}//GEN-LAST:event_txtMovil2KeyReleased

private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailKeyReleased

private void txtNroDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtApellidoPaterno.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtNroDocumentoKeyReleased

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoDocumento.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtCodigoKeyReleased

private void bntPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPassActionPerformed

    int nu = JOptionPane.showInternalConfirmDialog(this, "Desea cambiar la contraseña", "Cambiar Contraseña", JOptionPane.YES_NO_CANCEL_OPTION);
    Usuario usuario = null;

    usuario=cp.getUsuario();

    if(nu == JOptionPane.OK_OPTION){
        CambiarPassword pvc = new CambiarPassword(usuario);
        JLabel aviso = pvc.getLbAviso();

        JOptionPane.showInternalOptionDialog(this, pvc, "Cambiar Contraseña",JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(pvc.getSeActualizo() == 1){
            int numeroPos = numRegistros;
            AtuxUtility.aceptarTransaccion();
            if (rbTodos.isSelected()){
                rbTodosActionPerformed(evt);
            }else if (rbAtivos.isSelected()){
                rbAtivosActionPerformed(evt);
            }else if (rbNoActivos.isSelected()){
                rbNoActivosActionPerformed(evt);
            }
        
            JOptionPane.showInternalMessageDialog(this, "Contraseña Cambiada correctamente", "Contraseña cambiada", JOptionPane.INFORMATION_MESSAGE);
            numRegistros=numeroPos;
            cp.setUsuario(mtp.getFila(numRegistros));
            try {
                setUsuario();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(IVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}//GEN-LAST:event_bntPassActionPerformed

private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbSexo.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtNombresKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntPass;
    private elaprendiz.gui.button.ButtonRect btnAgregarFoto;
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnBuscar;
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
    private elaprendiz.gui.comboBox.ComboBoxRect cmbSexo;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoDocumento;
    private com.toedter.calendar.JDateChooser dteFechaCese;
    private com.toedter.calendar.JDateChooser dteFechaIngreso;
    private com.toedter.calendar.JDateChooser dteFechaNacimiento;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaCese;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNroDocumento;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTeleFijo;
    private javax.swing.JLabel lblTeleMovil;
    private javax.swing.JLabel lblTeleMovil2;
    private javax.swing.JLabel lblTipoDocumento;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnlEntradasComplementarias;
    private javax.swing.JPanel pnlEntradasObligatorias;
    private javax.swing.JPanel pnlFoto;
    private elaprendiz.gui.panel.PanelImage pnlImagen;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblUsuario;
    private elaprendiz.gui.textField.TextField txtApellidoMaterno;
    private elaprendiz.gui.textField.TextField txtApellidoPaterno;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCodigoDepartamento;
    private elaprendiz.gui.textField.TextField txtCodigoDistrito;
    private elaprendiz.gui.textField.TextField txtCodigoProvincia;
    private elaprendiz.gui.textField.TextField txtDepartamento;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private elaprendiz.gui.textField.TextField txtDistrito;
    private elaprendiz.gui.textField.TextField txtEmail;
    private elaprendiz.gui.textField.TextField txtFijo;
    private elaprendiz.gui.textField.TextField txtLogin;
    private elaprendiz.gui.textField.TextField txtMovil;
    private elaprendiz.gui.textField.TextField txtMovil2;
    private elaprendiz.gui.textField.TextField txtNombres;
    private elaprendiz.gui.textField.TextField txtNroDocumento;
    private elaprendiz.gui.passwordField.PasswordField txtPassword;
    private elaprendiz.gui.textField.TextField txtProvincia;
    // End of variables declaration//GEN-END:variables
}
