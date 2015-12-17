package atux.vistas.catalogo;
import atux.controllers.CUbigeo;
import atux.controllers.CCompania;
import atux.controllers.CPaises;
import atux.controllers.CTipoPoblacion;
import atux.controllers.CTipoVia;
import atux.modelbd.TipoPoblacion;
import atux.modelbd.TipoVia;
import atux.modelgui.ModeloTablaCompania;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarCompania;
import atux.vistas.buscar.BuscarPaises;
import atux.vistas.buscar.BuscarUbigeo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ICompania extends javax.swing.JInternalFrame {
    private CCompania cp;
    private CTipoVia ctv;
    private CTipoPoblacion ctp;    
    private ModeloTablaCompania mtp;
    private DefaultComboBoxModel mTipoVia;
    private DefaultComboBoxModel mTipoPoblacion;    
    private CTipoVia controllerTipoVia;
    private CTipoPoblacion controllerTipoPoblacion;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());

    public ICompania() {
        initComponents();
        cp = new CCompania();
        ctv = new CTipoVia();
        ctp = new CTipoPoblacion();
        tblListado.setVisible(false);
        lblMensaje.setVisible(false);
        controllerTipoVia = new CTipoVia();
        mTipoVia = new DefaultComboBoxModel(controllerTipoVia.getTipoVia().toArray());
        this.cmbTipoVia.setModel(mTipoVia);
        this.cmbTipoVia.setSelectedIndex(0);

        controllerTipoPoblacion = new CTipoPoblacion();
        mTipoPoblacion = new DefaultComboBoxModel(controllerTipoPoblacion.getTipoPoblacion().toArray());
        this.cmbTipoPoblacion.setModel(mTipoPoblacion);
        this.cmbTipoPoblacion.setSelectedIndex(0);

        rbAtivosActionPerformed(null);        
        
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();
        rbAtivosActionPerformed(null);
        
        cmbTipoVia.setBounds(10, 135, 90, 25);
        txtCmbTipoVia.setBounds(10, 135, 90, 25);
        cmbTipoPoblacion.setBounds(530, 135, 95, 25);
        txtCmbTipoPoblacion.setBounds(530, 135, 95, 25);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.NUM_LETRAS, 8, false);
        Helper.setFiltraEntrada(txtApellidoPaterno.getDocument(), FiltraEntrada.SOLO_LETRAS, 40, false);
        Helper.setFiltraEntrada(txtApellidoMaterno.getDocument(), FiltraEntrada.SOLO_LETRAS, 40, false);
        Helper.setFiltraEntrada(txtNombres.getDocument(), FiltraEntrada.SOLO_LETRAS, 60, false);
        
        Helper.setFiltraEntrada(txtCodigoPais.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtCodigoDepartamento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtCodigoProvincia.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
        Helper.setFiltraEntrada(txtCodigoDistrito.getDocument(), FiltraEntrada.SOLO_NUMEROS, 2, false);
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
        if(tblListado.getSelectedRow() != -1){
            numRegistros = tblListado.getSelectedRow();
            cp.setMaestroCompania(mtp.getFila(tblListado.getSelectedRow()));
            try {
                setCompania();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
    private void setCompania() throws FileNotFoundException, IOException, SQLException{
        Limpiar();
        
        txtPais.setText("");
        txtDepartamento.setText("");
        txtProvincia.setText("");
        txtDistrito.setText("");
        
        txtCodigo.setText(cp.getMaestroCompania().getCoCompania());
        txtRUC.setText(cp.getMaestroCompania().getNuRucCompania());
        txtDescripcion.setText(cp.getMaestroCompania().getDeCompania());
        txtDescripcionCorta.setText(cp.getMaestroCompania().getDeCortaCompania());
        txtCodigoPais.setText(cp.getMaestroCompania().getCoPais());
        txtCodigoDepartamento.setText(cp.getMaestroCompania().getCoDepartamento());
        txtCodigoProvincia.setText(cp.getMaestroCompania().getCoProvincia());
        txtCodigoDistrito.setText(cp.getMaestroCompania().getCoDistrito());
        txtNombreVia.setText(cp.getMaestroCompania().getNoVia());
        txtNumero.setText(cp.getMaestroCompania().getNuVia().toString());
        txtDpto.setText(cp.getMaestroCompania().getNuInteriorVia());
        txtManzana.setText(cp.getMaestroCompania().getNuManzanaVia());
        txtLote.setText(cp.getMaestroCompania().getNuLoteVia());
        txtNombrePoblacion.setText(cp.getMaestroCompania().getNoPoblacion());
        txtApellidoPaterno.setText(cp.getMaestroCompania().getApPaternoRespCompania());
        txtApellidoMaterno.setText(cp.getMaestroCompania().getApMaternoRespCompania());
        txtNombres.setText(cp.getMaestroCompania().getNoRespCompania());
        txtCodigoTelefPais.setText(cp.getMaestroCompania().getCoTelPais());
        txtCodigoTelefCiudad.setText(cp.getMaestroCompania().getCoTelCiudad());
        txtNumeroTelefono.setText(cp.getMaestroCompania().getNuTelNormal());
        txtNumeroFax.setText(cp.getMaestroCompania().getNuTelFax());
        txtEmail.setText(cp.getMaestroCompania().getDeEmail());
        txtURL.setText(cp.getMaestroCompania().getDeDireccionWeb());        
        CPaises BG2 = new CPaises();
        if (cp.getMaestroCompania().getCoPais()!= null){            
            BG2.getRegistroPorPk(new Object[]{cp.getMaestroCompania().getCoPais()});
            txtPais.setText(BG2.getPaises().getDePais());
        }
        
        CUbigeo BG1 = new CUbigeo();
        if (cp.getMaestroCompania().getCoDepartamento()!= null){
            txtDepartamento.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),null,null));
        }
        if (cp.getMaestroCompania().getCoProvincia()!= null){
            txtProvincia.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),null));
        }
        if (cp.getMaestroCompania().getCoDistrito()!= null){
            txtDistrito.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),txtCodigoDistrito.getText()));
        }

        if (cp.getMaestroCompania().getTiVia() != null){
            TipoVia tipoVia = new TipoVia();
            tipoVia = ctv.getRegistroPorPk(new Object[]{cp.getMaestroCompania().getTiVia()});

            for (int i=0; i<=cmbTipoVia.getItemCount(); i++){
                if (this.cmbTipoVia.getItemAt(i).toString().equals(tipoVia.getDeTipoVia())){
                    this.cmbTipoVia.setSelectedIndex(i);
                    break;
                }
            }
        }
        
        if (cp.getMaestroCompania().getTiPoblacion() != null){
            TipoPoblacion tipoPoblacion = new TipoPoblacion();

            tipoPoblacion = ctp.getRegistroPorPk(new Object[]{cp.getMaestroCompania().getTiPoblacion()});

            for (int i=0; i<=cmbTipoPoblacion.getItemCount(); i++){
                if (this.cmbTipoPoblacion.getItemAt(i).toString().equals(tipoPoblacion.getDePoblacion())){
                    this.cmbTipoPoblacion.setSelectedIndex(i);
                    break;
                }
            }
        }
        
        if("A".equals(cp.getMaestroCompania().getEsCompania())){
            chbSetActivo(true);
        }else{
            chbSetActivo(false);
        }
        
        txtCmbTipoVia.setText(cmbTipoVia.getSelectedItem().toString());
        txtCmbTipoPoblacion.setText(cmbTipoPoblacion.getSelectedItem().toString());
        
        this.btnModificar.setEnabled(true);
    }

    private void Limpiar(){
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtDescripcionCorta.setText("");
        txtCodigoPais.setText("");
        txtCodigoDepartamento.setText("");
        txtCodigoProvincia.setText("");
        txtCodigoDistrito.setText("");
        txtPais.setText("");
        txtDepartamento.setText("");
        txtProvincia.setText("");
        txtDistrito.setText("");
        txtNombreVia.setText("");
        txtNumero.setText("");
        txtDpto.setText("");
        txtManzana.setText("");
        txtLote.setText("");
        txtNombrePoblacion.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtNombres.setText("");
        txtCodigoTelefPais.setText("");
        txtCodigoTelefCiudad.setText("");
        txtNumeroTelefono.setText("");
        txtNumeroFax.setText("");
        txtEmail.setText("");
        txtEmail.setText("");        
        cmbTipoVia.setSelectedIndex(0);
        cmbTipoPoblacion.setSelectedIndex(0);
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        bntCodigoDeBarras.setEnabled(false);
        pnlEntradasObligatorias.setEnabled(false);
        pnlEntradasComplementarias.setEnabled(false);

        ECampos.setEditableTexto(this.pnlEntradasObligatorias, true, new Component[]{lblCodigo,lblRUC,lblDescripcion,lblDescripcionCorta,lblEstado,lblPais,lblDepartamento,lblProvincia,lblDistrito,lblTipoVIa,lblNombreVia,lblNumero,lblDpto,lblManzana,lblLote,lblTipoVivienda,lblPoblacion},false,"");
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, true, new Component[]{lblApellidoPaterno,lblApellidoMaterno,lblNombres,lblCodTelefPais,lblCodTelefCiudad,lblNumeroTelefono,lblNumeroFax,lblEmail,lblURL},false,"");

        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();
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

        this.cmbTipoVia.setEnabled(true);
        this.cmbTipoPoblacion.setEnabled(true);
        
        txtCmbTipoVia.setVisible(false);
        txtCmbTipoPoblacion.setVisible(false);

    }
    private void DesActivarCasillas(){
        bntCodigoDeBarras.setEnabled(true);
        this.pnlEntradasObligatorias.setEnabled(true);
        this.pnlEntradasComplementarias.setEnabled(true);
        
        ECampos.setEditableTexto(this.pnlEntradasObligatorias, false, new Component[]{lblCodigo,lblRUC,lblDescripcion,lblDescripcionCorta,lblEstado,lblPais,lblDepartamento,lblProvincia,lblDistrito,lblTipoVIa,lblNombreVia,lblNumero,lblDpto,lblManzana,lblLote,lblTipoVivienda,lblPoblacion},false,"");
        ECampos.setEditableTexto(this.pnlEntradasComplementarias, false, new Component[]{lblApellidoPaterno,lblApellidoMaterno,lblNombres,lblCodTelefPais,lblCodTelefCiudad,lblNumeroTelefono,lblNumeroFax,lblEmail,lblURL},false,"");

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

        this.cmbTipoVia.setEnabled(false);
        this.cmbTipoPoblacion.setEnabled(false);

        txtCmbTipoVia.setVisible(true);
        txtCmbTipoPoblacion.setVisible(true);

        esActualizacion = false;
//        this.pnlBuscador.setVisible(true);
        logger.info(txtCodigo.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        String Ubigeo = txtCodigoDepartamento.getText()+txtCodigoProvincia.getText()+txtCodigoDistrito.getText();
        if(!this.txtCodigo.getText().equals(cp.getMaestroCompania().getCoCompania())){
            return true;
        }else if(!this.txtRUC.getText().equals(cp.getMaestroCompania().getNuRucCompania())){
            return true;
        }else if(!this.txtDescripcion.getText().equals(cp.getMaestroCompania().getDeCompania())){
            return true;
        }else if(!this.txtDescripcionCorta.getText().equals(cp.getMaestroCompania().getDeCortaCompania())){
            return true;
        }else if(!this.txtCodigoPais.getText().equals(cp.getMaestroCompania().getCoPais())){
            return true;
        }else if(!this.txtCodigoDepartamento.getText().equals(cp.getMaestroCompania().getCoDepartamento())){
            return true;
        }else if(!this.txtCodigoProvincia.getText().equals(cp.getMaestroCompania().getCoProvincia())){
            return true;
        }else if(!this.txtCodigoDistrito.getText().equals(cp.getMaestroCompania().getCoDistrito())){
            return true;
        }else if(!this.txtNombreVia.getText().equals(cp.getMaestroCompania().getNoVia())){
            return true;
        }else if(Integer.parseInt(this.txtNumero.getText()) != cp.getMaestroCompania().getNuVia()){
            return true;
        }else if(!this.txtDpto.getText().equals(cp.getMaestroCompania().getNuInteriorVia())){
            return true;
        }else if(!this.txtManzana.getText().equals(cp.getMaestroCompania().getNuManzanaVia())){
            return true;
        }else if(!this.txtLote.getText().equals(cp.getMaestroCompania().getNuLoteVia())){
            return true;
        }else if(!this.txtNombrePoblacion.getText().equals(cp.getMaestroCompania().getNoPoblacion())){
            return true;
        }else if(!this.txtApellidoPaterno.getText().equals(cp.getMaestroCompania().getApPaternoRespCompania())){
            return true;
        }else if(!this.txtApellidoMaterno.getText().equals(cp.getMaestroCompania().getApMaternoRespCompania())){
            return true;
        }else if(!this.txtNombres.getText().equals(cp.getMaestroCompania().getNoRespCompania())){
            return true;
        }else if(!this.txtCodigoTelefPais.getText().equals(cp.getMaestroCompania().getCoTelPais())){
            return true;
        }else if(!this.txtCodigoTelefCiudad.getText().equals(cp.getMaestroCompania().getCoTelCiudad())){
            return true;
        }else if(!this.txtNumeroTelefono.getText().equals(cp.getMaestroCompania().getNuTelNormal())){
            return true;
        }else if(!this.txtNumeroFax.getText().equals(cp.getMaestroCompania().getNuTelFax())){
            return true;
        }else if(!this.txtEmail.getText().equals(cp.getMaestroCompania().getDeEmail())){
            return true;
        }else if(!this.txtURL.getText().equals(cp.getMaestroCompania().getDeDireccionWeb())){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getMaestroCompania().getEsCompania()))){
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException, FileNotFoundException{
        txtDescripcionCorta.setText(txtDescripcionCorta.getText().toUpperCase());
        txtDescripcion.setText(txtDescripcion.getText().toUpperCase());
        txtNombreVia.setText(txtNombreVia.getText().toUpperCase());
        txtDpto.setText(txtDpto.getText().toUpperCase());
        txtManzana.setText(txtManzana.getText().toUpperCase());
        txtLote.setText(txtLote.getText().toUpperCase());
        txtNombrePoblacion.setText(txtNombrePoblacion.getText().toUpperCase());
        txtApellidoPaterno.setText(txtApellidoPaterno.getText().toUpperCase());
        txtApellidoMaterno.setText(txtApellidoMaterno.getText().toUpperCase());
        txtNombres.setText(txtNombres.getText().toUpperCase());

        cp.getMaestroCompania().setDeCortaCompania(txtDescripcionCorta.getText());
        cp.getMaestroCompania().setDeCompania(txtDescripcion.getText());
        cp.getMaestroCompania().setNuRucCompania(txtRUC.getText());
        cp.getMaestroCompania().setCoPais(txtCodigoPais.getText());
        cp.getMaestroCompania().setCoDepartamento(txtCodigoDepartamento.getText());
        cp.getMaestroCompania().setCoProvincia(txtCodigoProvincia.getText());
        cp.getMaestroCompania().setCoDistrito(txtCodigoDistrito.getText());
        
        cp.getMaestroCompania().setTiVia(((TipoVia)cmbTipoVia.getSelectedItem()).getTiVia());
        
        cp.getMaestroCompania().setNoVia(txtNombreVia.getText());
        if ("".equals(txtNumero.getText())){
            cp.getMaestroCompania().setNuVia(null);
        }else{
            cp.getMaestroCompania().setNuVia(Integer.parseInt(txtNumero.getText()));
        }
        
        cp.getMaestroCompania().setNuInteriorVia(txtDpto.getText());
        cp.getMaestroCompania().setNuManzanaVia(txtManzana.getText());
        cp.getMaestroCompania().setNuLoteVia(txtLote.getText());
        cp.getMaestroCompania().setTiPoblacion(((TipoPoblacion) cmbTipoPoblacion.getSelectedItem()).getTiPoblacion());
        cp.getMaestroCompania().setNoPoblacion(txtNombrePoblacion.getText());
        cp.getMaestroCompania().setCoTelPais(txtCodigoTelefPais.getText());
        cp.getMaestroCompania().setCoTelCiudad(txtCodigoTelefCiudad.getText());
        cp.getMaestroCompania().setNuTelNormal(txtNumeroTelefono.getText());
        cp.getMaestroCompania().setNuTelFax(txtNumeroFax.getText());
        cp.getMaestroCompania().setApPaternoRespCompania(txtApellidoPaterno.getText());
        cp.getMaestroCompania().setApMaternoRespCompania(txtApellidoMaterno.getText());
        cp.getMaestroCompania().setNoRespCompania(txtNombres.getText());
        cp.getMaestroCompania().setDeDireccion(txtNombreVia.getText() + " " + cp.getMaestroCompania().getNuVia() );
        cp.getMaestroCompania().setDeDireccionWeb(txtURL.getText());
        cp.getMaestroCompania().setDeEmail(txtEmail.getText());

        if (chbEstado.isSelected()){
            cp.getMaestroCompania().setEsCompania("A");
        }else{
            cp.getMaestroCompania().setEsCompania("I");
        }


        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getMaestroCompania().setCoCompania(cp.getNuevoCodigo());
            cp.getMaestroCompania().setCoSucursal(cp.getMaestroCompania().getCoCompania());
            cp.getMaestroCompania().setIdCreaCompania(AtuxVariables.vIdUsuario);
            cp.getMaestroCompania().setFeCreaCompania(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            EstadoGuardar = cp.guardarRegistro(cp.getMaestroCompania());
        }else{
            cp.getMaestroCompania().setIdModCompania(AtuxVariables.vIdUsuario);
            cp.getMaestroCompania().setFeModCompania(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getMaestroCompania());
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
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
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
        lblCodigo = new javax.swing.JLabel();
        txtCodigoDepartamento = new elaprendiz.gui.textField.TextField();
        txtDepartamento = new elaprendiz.gui.textField.TextField();
        lblDepartamento = new javax.swing.JLabel();
        txtCodigoProvincia = new elaprendiz.gui.textField.TextField();
        txtProvincia = new elaprendiz.gui.textField.TextField();
        lblProvincia = new javax.swing.JLabel();
        txtDistrito = new elaprendiz.gui.textField.TextField();
        txtCodigoDistrito = new elaprendiz.gui.textField.TextField();
        lblDistrito = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        lblRUC = new javax.swing.JLabel();
        txtRUC = new elaprendiz.gui.textField.TextField();
        txtCodigoPais = new elaprendiz.gui.textField.TextField();
        txtPais = new elaprendiz.gui.textField.TextField();
        lblPais = new javax.swing.JLabel();
        txtDescripcionCorta = new elaprendiz.gui.textField.TextField();
        lblDescripcionCorta = new javax.swing.JLabel();
        txtCmbTipoVia = new elaprendiz.gui.textField.TextField();
        txtCmbTipoPoblacion = new elaprendiz.gui.textField.TextField();
        cmbTipoVia = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTipoVIa = new javax.swing.JLabel();
        lblNombreVia = new javax.swing.JLabel();
        txtNombreVia = new elaprendiz.gui.textField.TextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new elaprendiz.gui.textField.TextField();
        lblDpto = new javax.swing.JLabel();
        txtDpto = new elaprendiz.gui.textField.TextField();
        lblManzana = new javax.swing.JLabel();
        txtManzana = new elaprendiz.gui.textField.TextField();
        txtLote = new elaprendiz.gui.textField.TextField();
        lblLote = new javax.swing.JLabel();
        cmbTipoPoblacion = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTipoVivienda = new javax.swing.JLabel();
        txtNombrePoblacion = new elaprendiz.gui.textField.TextField();
        lblPoblacion = new javax.swing.JLabel();
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
        pnlEntradasComplementarias = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new elaprendiz.gui.textField.TextField();
        tblListado = new javax.swing.JTable();
        lblApellidoMaterno = new javax.swing.JLabel();
        txtNombres = new elaprendiz.gui.textField.TextField();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtApellidoPaterno = new elaprendiz.gui.textField.TextField();
        lblNombres = new javax.swing.JLabel();
        txtApellidoMaterno = new elaprendiz.gui.textField.TextField();
        lblCodTelefPais = new javax.swing.JLabel();
        txtCodigoTelefPais = new elaprendiz.gui.textField.TextField();
        lblCodTelefCiudad = new javax.swing.JLabel();
        txtCodigoTelefCiudad = new elaprendiz.gui.textField.TextField();
        lblNumeroTelefono = new javax.swing.JLabel();
        txtNumeroTelefono = new elaprendiz.gui.textField.TextField();
        lblNumeroFax = new javax.swing.JLabel();
        txtNumeroFax = new elaprendiz.gui.textField.TextField();
        txtURL = new elaprendiz.gui.textField.TextField();
        lblURL = new javax.swing.JLabel();
        bntCodigoDeBarras = new elaprendiz.gui.button.ButtonRect();
        bntLicencias = new elaprendiz.gui.button.ButtonRect();
        lblMensaje = new javax.swing.JLabel();

        setBorder(null);
        setTitle("Formulario de Companias");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasObligatorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos Obligatorios", 1, 2));
        pnlEntradasObligatorias.setOpaque(false);
        pnlEntradasObligatorias.setLayout(null);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Estado:");
        pnlEntradasObligatorias.add(lblEstado);
        lblEstado.setBounds(640, 16, 110, 17);

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
        pnlEntradasObligatorias.add(chbEstado);
        chbEstado.setBounds(640, 36, 116, 25);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigo.setText("Codigo:");
        pnlEntradasObligatorias.add(lblCodigo);
        lblCodigo.setBounds(10, 16, 70, 17);

        txtCodigoDepartamento.setEditable(false);
        txtCodigoDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDepartamento.setAlignmentX(0.2F);
        txtCodigoDepartamento.setAlignmentY(0.2F);
        txtCodigoDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDepartamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoDepartamentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoDepartamentoFocusLost(evt);
            }
        });
        txtCodigoDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDepartamentoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoDepartamento);
        txtCodigoDepartamento.setBounds(200, 84, 25, 25);

        txtDepartamento.setEditable(false);
        txtDepartamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDepartamento.setAlignmentX(0.2F);
        txtDepartamento.setAlignmentY(0.2F);
        txtDepartamento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDepartamento.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtDepartamento);
        txtDepartamento.setBounds(230, 84, 150, 25);

        lblDepartamento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDepartamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDepartamento.setText("Departamento");
        lblDepartamento.setAlignmentX(0.2F);
        lblDepartamento.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDepartamento);
        lblDepartamento.setBounds(200, 66, 180, 17);

        txtCodigoProvincia.setEditable(false);
        txtCodigoProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoProvincia.setAlignmentX(0.2F);
        txtCodigoProvincia.setAlignmentY(0.2F);
        txtCodigoProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoProvincia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoProvincia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoProvinciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoProvinciaFocusLost(evt);
            }
        });
        txtCodigoProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProvinciaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoProvincia);
        txtCodigoProvincia.setBounds(386, 84, 25, 25);

        txtProvincia.setEditable(false);
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProvincia.setAlignmentX(0.2F);
        txtProvincia.setAlignmentY(0.2F);
        txtProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtProvincia.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtProvincia);
        txtProvincia.setBounds(416, 84, 150, 25);

        lblProvincia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProvincia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProvincia.setText("Provincia");
        lblProvincia.setAlignmentX(0.2F);
        lblProvincia.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblProvincia);
        lblProvincia.setBounds(390, 66, 176, 17);

        txtDistrito.setEditable(false);
        txtDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDistrito.setAlignmentX(0.2F);
        txtDistrito.setAlignmentY(0.2F);
        txtDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtDistrito.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtDistrito);
        txtDistrito.setBounds(602, 84, 150, 25);

        txtCodigoDistrito.setEditable(false);
        txtCodigoDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoDistrito.setAlignmentX(0.2F);
        txtCodigoDistrito.setAlignmentY(0.2F);
        txtCodigoDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoDistrito.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoDistrito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoDistritoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoDistritoFocusLost(evt);
            }
        });
        txtCodigoDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoDistritoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoDistrito);
        txtCodigoDistrito.setBounds(572, 84, 25, 25);

        lblDistrito.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDistrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDistrito.setText("Distrito");
        lblDistrito.setAlignmentX(0.2F);
        lblDistrito.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDistrito);
        lblDistrito.setBounds(572, 61, 50, 17);

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
        pnlEntradasObligatorias.add(txtCodigo);
        txtCodigo.setBounds(10, 36, 70, 25);

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcion.setText("Descripción");
        pnlEntradasObligatorias.add(lblDescripcion);
        lblDescripcion.setBounds(260, 16, 220, 17);

        txtDescripcion.setEditable(false);
        txtDescripcion.setAlignmentX(0.2F);
        txtDescripcion.setAlignmentY(0.2F);
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDescripcion);
        txtDescripcion.setBounds(260, 36, 220, 25);

        lblRUC.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRUC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRUC.setText("RUC:");
        pnlEntradasObligatorias.add(lblRUC);
        lblRUC.setBounds(90, 16, 160, 17);

        txtRUC.setEditable(false);
        txtRUC.setAlignmentX(0.2F);
        txtRUC.setAlignmentY(0.2F);
        txtRUC.setFont(new java.awt.Font("Arial", 0, 12));
        txtRUC.setPreferredSize(new java.awt.Dimension(320, 25));
        txtRUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRUCKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtRUC);
        txtRUC.setBounds(90, 36, 160, 25);

        txtCodigoPais.setEditable(false);
        txtCodigoPais.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigoPais.setAlignmentX(0.2F);
        txtCodigoPais.setAlignmentY(0.2F);
        txtCodigoPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoPais.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoPais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoPaisFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoPaisFocusLost(evt);
            }
        });
        txtCodigoPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPaisKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCodigoPais);
        txtCodigoPais.setBounds(10, 84, 25, 25);

        txtPais.setEditable(false);
        txtPais.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPais.setAlignmentX(0.2F);
        txtPais.setAlignmentY(0.2F);
        txtPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtPais.setPreferredSize(new java.awt.Dimension(120, 25));
        pnlEntradasObligatorias.add(txtPais);
        txtPais.setBounds(44, 84, 150, 25);

        lblPais.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPais.setText("Pais");
        lblPais.setAlignmentX(0.2F);
        lblPais.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblPais);
        lblPais.setBounds(10, 66, 180, 17);

        txtDescripcionCorta.setEditable(false);
        txtDescripcionCorta.setAlignmentX(0.2F);
        txtDescripcionCorta.setAlignmentY(0.2F);
        txtDescripcionCorta.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcionCorta.setPreferredSize(new java.awt.Dimension(320, 25));
        txtDescripcionCorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionCortaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDescripcionCorta);
        txtDescripcionCorta.setBounds(490, 36, 140, 25);

        lblDescripcionCorta.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcionCorta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcionCorta.setText("Descrip Corta");
        pnlEntradasObligatorias.add(lblDescripcionCorta);
        lblDescripcionCorta.setBounds(490, 16, 140, 17);

        txtCmbTipoVia.setEditable(false);
        txtCmbTipoVia.setAlignmentX(0.2F);
        txtCmbTipoVia.setAlignmentY(0.2F);
        txtCmbTipoVia.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTipoVia.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTipoVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTipoViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCmbTipoVia);
        txtCmbTipoVia.setBounds(750, 120, 10, 20);

        txtCmbTipoPoblacion.setEditable(false);
        txtCmbTipoPoblacion.setAlignmentX(0.2F);
        txtCmbTipoPoblacion.setAlignmentY(0.2F);
        txtCmbTipoPoblacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtCmbTipoPoblacion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtCmbTipoPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCmbTipoPoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtCmbTipoPoblacion);
        txtCmbTipoPoblacion.setBounds(750, 140, 10, 20);

        cmbTipoVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(cmbTipoVia);
        cmbTipoVia.setBounds(10, 135, 90, 20);

        lblTipoVIa.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVIa.setText("Tipo Via");
        lblTipoVIa.setAlignmentX(0.2F);
        lblTipoVIa.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblTipoVIa);
        lblTipoVIa.setBounds(30, 115, 55, 20);

        lblNombreVia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombreVia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreVia.setText("Nombre Via");
        lblNombreVia.setAlignmentX(0.2F);
        lblNombreVia.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblNombreVia);
        lblNombreVia.setBounds(110, 115, 170, 20);

        txtNombreVia.setEditable(false);
        txtNombreVia.setAlignmentX(0.2F);
        txtNombreVia.setAlignmentY(0.2F);
        txtNombreVia.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombreVia.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombreVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreViaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNombreVia);
        txtNombreVia.setBounds(110, 135, 170, 25);

        lblNumero.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumero.setText("Num");
        lblNumero.setAlignmentX(0.2F);
        lblNumero.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblNumero);
        lblNumero.setBounds(291, 115, 50, 20);

        txtNumero.setEditable(false);
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumero.setAlignmentX(0.2F);
        txtNumero.setAlignmentY(0.2F);
        txtNumero.setDireccionDeSombra(80);
        txtNumero.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumero.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNumero);
        txtNumero.setBounds(286, 135, 60, 25);

        lblDpto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDpto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDpto.setText("Dpto.");
        lblDpto.setAlignmentX(0.2F);
        lblDpto.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblDpto);
        lblDpto.setBounds(354, 115, 50, 20);

        txtDpto.setEditable(false);
        txtDpto.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDpto.setAlignmentX(0.2F);
        txtDpto.setAlignmentY(0.2F);
        txtDpto.setFont(new java.awt.Font("Arial", 0, 12));
        txtDpto.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDpto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDptoKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtDpto);
        txtDpto.setBounds(352, 135, 50, 25);

        lblManzana.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblManzana.setText("Manzana");
        lblManzana.setAlignmentX(0.2F);
        lblManzana.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblManzana);
        lblManzana.setBounds(410, 115, 61, 20);

        txtManzana.setEditable(false);
        txtManzana.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtManzana.setAlignmentX(0.2F);
        txtManzana.setAlignmentY(0.2F);
        txtManzana.setFont(new java.awt.Font("Arial", 0, 12));
        txtManzana.setPreferredSize(new java.awt.Dimension(120, 25));
        txtManzana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtManzanaKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtManzana);
        txtManzana.setBounds(408, 135, 65, 25);

        txtLote.setEditable(false);
        txtLote.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtLote.setAlignmentX(0.2F);
        txtLote.setAlignmentY(0.2F);
        txtLote.setFont(new java.awt.Font("Arial", 0, 12));
        txtLote.setPreferredSize(new java.awt.Dimension(120, 25));
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtLote);
        txtLote.setBounds(484, 135, 40, 25);

        lblLote.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblLote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLote.setText("Lote");
        lblLote.setAlignmentX(0.2F);
        lblLote.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblLote);
        lblLote.setBounds(483, 115, 40, 20);

        cmbTipoPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoPoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(cmbTipoPoblacion);
        cmbTipoPoblacion.setBounds(530, 137, 95, 20);

        lblTipoVivienda.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVivienda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoVivienda.setText("Tipo Pobl.");
        lblTipoVivienda.setAlignmentX(0.2F);
        lblTipoVivienda.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblTipoVivienda);
        lblTipoVivienda.setBounds(529, 115, 90, 20);

        txtNombrePoblacion.setEditable(false);
        txtNombrePoblacion.setAlignmentX(0.2F);
        txtNombrePoblacion.setAlignmentY(0.2F);
        txtNombrePoblacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombrePoblacion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombrePoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePoblacionKeyReleased(evt);
            }
        });
        pnlEntradasObligatorias.add(txtNombrePoblacion);
        txtNombrePoblacion.setBounds(631, 135, 119, 25);

        lblPoblacion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPoblacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPoblacion.setText("Población");
        lblPoblacion.setAlignmentX(0.2F);
        lblPoblacion.setAlignmentY(0.2F);
        pnlEntradasObligatorias.add(lblPoblacion);
        lblPoblacion.setBounds(641, 115, 100, 20);

        pnlBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscador.setOpaque(false);

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
            .addGroup(pnlBuscadorLayout.createSequentialGroup()
                .addGap(2, 2, 2)
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
                .addGap(3, 3, 3)
                .addComponent(rbNoActivos)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlBuscadorLayout.setVerticalGroup(
            pnlBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rbTodos)
            .addComponent(rbAtivos)
            .addComponent(rbNoActivos)
        );

        pnlAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
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

        javax.swing.GroupLayout pnlAccionesLayout = new javax.swing.GroupLayout(pnlAcciones);
        pnlAcciones.setLayout(pnlAccionesLayout);
        pnlAccionesLayout.setHorizontalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlAccionesLayout.setVerticalGroup(
            pnlAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlEntradasComplementarias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Datos Contactos"));
        pnlEntradasComplementarias.setOpaque(false);

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

        lblApellidoMaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoMaterno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApellidoMaterno.setText("Ap. Materno");
        lblApellidoMaterno.setAlignmentX(0.2F);
        lblApellidoMaterno.setAlignmentY(0.2F);

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

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombres.setText("Nombres");
        lblNombres.setAlignmentX(0.2F);
        lblNombres.setAlignmentY(0.2F);

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

        lblCodTelefPais.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodTelefPais.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodTelefPais.setText("Cod Telf Pais");
        lblCodTelefPais.setAlignmentX(0.2F);
        lblCodTelefPais.setAlignmentY(0.2F);

        txtCodigoTelefPais.setEditable(false);
        txtCodigoTelefPais.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCodigoTelefPais.setAlignmentX(0.2F);
        txtCodigoTelefPais.setAlignmentY(0.2F);
        txtCodigoTelefPais.setDireccionDeSombra(80);
        txtCodigoTelefPais.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoTelefPais.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoTelefPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoTelefPaisKeyReleased(evt);
            }
        });

        lblCodTelefCiudad.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodTelefCiudad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodTelefCiudad.setText("Cod Telf Ciudad");
        lblCodTelefCiudad.setAlignmentX(0.2F);
        lblCodTelefCiudad.setAlignmentY(0.2F);

        txtCodigoTelefCiudad.setEditable(false);
        txtCodigoTelefCiudad.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCodigoTelefCiudad.setAlignmentX(0.2F);
        txtCodigoTelefCiudad.setAlignmentY(0.2F);
        txtCodigoTelefCiudad.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoTelefCiudad.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoTelefCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoTelefCiudadKeyReleased(evt);
            }
        });

        lblNumeroTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumeroTelefono.setText("Número Teléfono");
        lblNumeroTelefono.setAlignmentX(0.2F);
        lblNumeroTelefono.setAlignmentY(0.2F);

        txtNumeroTelefono.setEditable(false);
        txtNumeroTelefono.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumeroTelefono.setAlignmentX(0.2F);
        txtNumeroTelefono.setAlignmentY(0.2F);
        txtNumeroTelefono.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroTelefono.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroTelefonoKeyReleased(evt);
            }
        });

        lblNumeroFax.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumeroFax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroFax.setText("Número Fax");
        lblNumeroFax.setAlignmentX(0.2F);
        lblNumeroFax.setAlignmentY(0.2F);

        txtNumeroFax.setEditable(false);
        txtNumeroFax.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNumeroFax.setAlignmentX(0.2F);
        txtNumeroFax.setAlignmentY(0.2F);
        txtNumeroFax.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroFax.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFaxKeyReleased(evt);
            }
        });

        txtURL.setEditable(false);
        txtURL.setAlignmentX(0.2F);
        txtURL.setAlignmentY(0.2F);
        txtURL.setFont(new java.awt.Font("Arial", 0, 12));
        txtURL.setPreferredSize(new java.awt.Dimension(320, 25));
        txtURL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtURLKeyReleased(evt);
            }
        });

        lblURL.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblURL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblURL.setText("Página WEB");
        lblURL.setAlignmentX(0.2F);
        lblURL.setAlignmentY(0.2F);

        javax.swing.GroupLayout pnlEntradasComplementariasLayout = new javax.swing.GroupLayout(pnlEntradasComplementarias);
        pnlEntradasComplementarias.setLayout(pnlEntradasComplementariasLayout);
        pnlEntradasComplementariasLayout.setHorizontalGroup(
            pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombres))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCodTelefPais)
                        .addGap(6, 6, 6)
                        .addComponent(lblCodTelefCiudad)
                        .addGap(10, 10, 10)
                        .addComponent(lblNumeroTelefono)
                        .addGap(46, 46, 46)
                        .addComponent(lblNumeroFax, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(tblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtCodigoTelefPais, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtCodigoTelefCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtNumeroFax, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(lblURL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEntradasComplementariasLayout.setVerticalGroup(
            pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasComplementariasLayout.createSequentialGroup()
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoPaterno)
                        .addComponent(lblApellidoMaterno))
                    .addComponent(lblNombres))
                .addGap(3, 3, 3)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodTelefPais)
                    .addComponent(lblCodTelefCiudad)
                    .addComponent(lblNumeroTelefono)
                    .addComponent(lblNumeroFax)
                    .addComponent(tblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoTelefPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoTelefCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblURL))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasComplementariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bntCodigoDeBarras.setBackground(new java.awt.Color(0, 204, 0));
        bntCodigoDeBarras.setText("CADENAS DE BOTICAS");
        bntCodigoDeBarras.setAlignmentX(0.1F);
        bntCodigoDeBarras.setAlignmentY(0.1F);
        bntCodigoDeBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCodigoDeBarrasActionPerformed(evt);
            }
        });

        bntLicencias.setBackground(new java.awt.Color(0, 204, 0));
        bntLicencias.setText("Licencias");
        bntLicencias.setAlignmentX(0.1F);
        bntLicencias.setAlignmentY(0.1F);
        bntLicencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLicenciasActionPerformed(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 16));
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("F1 para ver mas datos");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(pnlBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntLicencias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelImage1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(324, 324, 324))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradasObligatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEntradasComplementarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bntLicencias, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Formulario Tipo de Cambio");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setMaestroCompania(mtp.getFila(numRegistros));
        try {
            setCompania();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setMaestroCompania(mtp.getFila(numRegistros));
        try {
            setCompania();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setMaestroCompania(mtp.getFila(numRegistros));
        try {
            setCompania();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setMaestroCompania(mtp.getFila(numRegistros));
        try {
            setCompania();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaCompania(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaCompania(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaCompania(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        cp.getMaestroCompania().setCoCompania(cp.getNuevoCodigo());
        txtCodigo.setText(cp.getMaestroCompania().getCoCompania());
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
        
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasObligatorias,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

//        if(tipoSeleccion == -1){
//            this.mtp = new ModeloTablaCompania(inicioPag,finalPag);
//        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaCompania(tipoSeleccion,inicioPag,finalPag);
//        }
        tblListado.setModel(mtp);
        this.numRegistros = mtp.getCantidadRegistros();
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

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtCodigoPais.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarCompania bCompania = new BuscarCompania(tipoSeleccion);
        JLabel aviso = bCompania.getAviso();
        String msj = (tipoSeleccion==-1?"Mostrando todos los Tipos de Cambios existentes":(tipoSeleccion==1?"Mostrando todo los Tipos de Cambios activos":"Mostrando todo los Tipos de Cambios No activos"));
        JOptionPane.showInternalOptionDialog(this, bCompania, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(bCompania.getCompania() != null){
            try {
                cp.setMaestroCompania(bCompania.getCompania());
                 setCompania();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ICompania.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

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
            cmbTipoVia.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDistritoKeyReleased

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtRUC.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtDescripcion.setText(txtDescripcion.getText());
            txtDescripcionCorta.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDescripcionKeyReleased

private void txtRUCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUCKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDescripcion.requestFocus();
             break;
    }
}//GEN-LAST:event_txtRUCKeyReleased

private void txtCodigoPaisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPaisKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            int tipoSeleccionG=1;    
            BuscarPaises pvc = new BuscarPaises();
            JLabel aviso = pvc.getAviso();

            String msj = (tipoSeleccionG==-1?"Mostrando Listado Paises":(tipoSeleccionG==1?"Mostrando Listado de Paises Activos":"Mostrando Listado de Paises No activos"));
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getPais() != null){
                CPaises cpF = new CPaises();
                cpF.setPaises(pvc.getPais());
                this.txtCodigoPais.setText(cpF.getPaises().getCoPais());
                this.txtPais.setText(cpF.getPaises().getDePais());
            }
            break;
        case KeyEvent.VK_ENTER: 
            txtCodigoDepartamento.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoPaisKeyReleased

private void txtDescripcionCortaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionCortaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDescripcionCortaKeyReleased

private void cmbTipoViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombreVia.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoViaKeyReleased

private void txtNombreViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNumero.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNombreViaKeyReleased

private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDpto.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNumeroKeyReleased

private void txtDptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDptoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtManzana.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDptoKeyReleased

private void txtManzanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManzanaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtLote.requestFocus();
             break;
    }
}//GEN-LAST:event_txtManzanaKeyReleased

private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoPoblacion.requestFocus();
             break;
    }
}//GEN-LAST:event_txtLoteKeyReleased

private void cmbTipoPoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoPoblacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombrePoblacion.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoPoblacionKeyReleased

private void txtNombrePoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePoblacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoTelefPais.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNombrePoblacionKeyReleased

private void txtNumeroFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFaxKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtEmail.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNumeroFaxKeyReleased

private void txtNumeroTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroTelefonoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNumeroFax.requestFocus();
             break;
    }
}//GEN-LAST:event_txtNumeroTelefonoKeyReleased

private void txtCodigoTelefCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTelefCiudadKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNumeroTelefono.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoTelefCiudadKeyReleased

private void txtCodigoTelefPaisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTelefPaisKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtCodigoTelefCiudad.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoTelefPaisKeyReleased

private void txtApellidoMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombres.requestFocus();
             break;
    }
}//GEN-LAST:event_txtApellidoMaternoKeyReleased

private void txtApellidoPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtApellidoMaterno.requestFocus();
             break;
    }
}//GEN-LAST:event_txtApellidoPaternoKeyReleased

private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtCodigoTelefPais.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtNombresKeyReleased

private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtURL.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailKeyReleased

private void txtURLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtURLKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_txtURLKeyReleased

private void bntCodigoDeBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCodigoDeBarrasActionPerformed
    if ("".equals(txtCodigo.getText())){
        JOptionPane.showMessageDialog(this, "No se ha seleccionado un laboratorio","Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        return;
    }
    ICadena pvc = new ICadena(this);
    pvc.setCodigoCompania(txtCodigo.getText());
    pvc.setDescripcionCompania(txtDescripcion.getText());

    pvc.CargaDatos();
    pvc.setPreferredSize(new Dimension(740, 380));

    //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
    String msj = "Mostrando todas los Codigos de Barras del Producto";
    JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                        -1, null, new Object[]{pvc.getAviso()},null);
}//GEN-LAST:event_bntCodigoDeBarrasActionPerformed

private void bntLicenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLicenciasActionPerformed
    if ("".equals(txtCodigo.getText())){
        JOptionPane.showMessageDialog(this, "No se ha seleccionado una Compania","Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        return;
    }
    ICompaniaLicencia pvc = new ICompaniaLicencia(this);
    pvc.setCodigoCompania(txtCodigo.getText());
    pvc.setDescripcionCompania(txtDescripcion.getText());

    pvc.CargaDatos();
    pvc.setPreferredSize(new Dimension(740, 380));

    //String msj = (tipoSeleccion==-1?"Mostrando todas las Especialidades del Medico":(tipoSeleccion==1?"Mostrando todo los usuarios activos":"Mostrando todo los usuarios No activos"));
    String msj = "Mostrando todas los Codigos de Barras del Producto";
    JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                        -1, null, new Object[]{pvc.getAviso()},null);
}//GEN-LAST:event_bntLicenciasActionPerformed

private void txtCodigoPaisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPaisFocusGained
    if (txtCodigoPais.isEditable()) lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoPaisFocusGained

private void txtCodigoPaisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoPaisFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoPaisFocusLost

private void txtCodigoDepartamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoFocusGained
    if (txtCodigoDepartamento.isEditable()) lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoDepartamentoFocusGained

private void txtCodigoDepartamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoDepartamentoFocusLost

private void txtCodigoProvinciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaFocusGained
    if (txtCodigoProvincia.isEditable()) lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoProvinciaFocusGained

private void txtCodigoProvinciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoProvinciaFocusLost

private void txtCodigoDistritoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDistritoFocusGained
    if (txtCodigoDistrito.isEditable()) lblMensaje.setVisible(true);
}//GEN-LAST:event_txtCodigoDistritoFocusGained

private void txtCodigoDistritoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDistritoFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtCodigoDistritoFocusLost

private void txtCmbTipoViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTipoViaKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTipoViaKeyReleased

private void txtCmbTipoPoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCmbTipoPoblacionKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txtCmbTipoPoblacionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntCodigoDeBarras;
    private elaprendiz.gui.button.ButtonRect bntLicencias;
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
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoPoblacion;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVia;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblCodTelefCiudad;
    private javax.swing.JLabel lblCodTelefPais;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcionCorta;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblDpto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblManzana;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNombreVia;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroFax;
    private javax.swing.JLabel lblNumeroTelefono;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPoblacion;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblRUC;
    private javax.swing.JLabel lblTipoVIa;
    private javax.swing.JLabel lblTipoVivienda;
    private javax.swing.JLabel lblURL;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBuscador;
    private javax.swing.JPanel pnlEntradasComplementarias;
    private javax.swing.JPanel pnlEntradasObligatorias;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtApellidoMaterno;
    private elaprendiz.gui.textField.TextField txtApellidoPaterno;
    private elaprendiz.gui.textField.TextField txtCmbTipoPoblacion;
    private elaprendiz.gui.textField.TextField txtCmbTipoVia;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCodigoDepartamento;
    private elaprendiz.gui.textField.TextField txtCodigoDistrito;
    private elaprendiz.gui.textField.TextField txtCodigoPais;
    private elaprendiz.gui.textField.TextField txtCodigoProvincia;
    private elaprendiz.gui.textField.TextField txtCodigoTelefCiudad;
    private elaprendiz.gui.textField.TextField txtCodigoTelefPais;
    private elaprendiz.gui.textField.TextField txtDepartamento;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtDescripcionCorta;
    private elaprendiz.gui.textField.TextField txtDistrito;
    private elaprendiz.gui.textField.TextField txtDpto;
    private elaprendiz.gui.textField.TextField txtEmail;
    private elaprendiz.gui.textField.TextField txtLote;
    private elaprendiz.gui.textField.TextField txtManzana;
    private elaprendiz.gui.textField.TextField txtNombrePoblacion;
    private elaprendiz.gui.textField.TextField txtNombreVia;
    private elaprendiz.gui.textField.TextField txtNombres;
    private elaprendiz.gui.textField.TextField txtNumero;
    private elaprendiz.gui.textField.TextField txtNumeroFax;
    private elaprendiz.gui.textField.TextField txtNumeroTelefono;
    private elaprendiz.gui.textField.TextField txtPais;
    private elaprendiz.gui.textField.TextField txtProvincia;
    private elaprendiz.gui.textField.TextField txtRUC;
    private elaprendiz.gui.textField.TextField txtURL;
    // End of variables declaration//GEN-END:variables
}
