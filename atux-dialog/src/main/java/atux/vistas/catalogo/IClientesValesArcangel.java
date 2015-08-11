package atux.vistas.catalogo;

import atux.controllers.CCliente;
import atux.controllers.CClienteComprobante;
import atux.controllers.CClienteDireccion;
import atux.controllers.CTipoDocumento;
import atux.controllers.CUbigeo;
import atux.controllers.CTipoVia;
import atux.controllers.CTipoPoblacion;
import atux.modelbd.Cliente;
import atux.modelbd.ClienteDireccion;
import atux.modelbd.TipoVia;
import atux.modelbd.TipoPoblacion;
import atux.modelbd.TipoDocumento;
import atux.modelgui.ModeloTablaClienteValesArcangel;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarCliente;
import atux.vistas.buscar.BuscarUbigeo;
import java.awt.Color;
import java.awt.Component;
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


public final class IClientesValesArcangel extends javax.swing.JInternalFrame {
    private CCliente cp;
    private CClienteDireccion ccd;
    private CClienteComprobante cpc;
    private CTipoDocumento ctd;
    private CTipoVia ctv;
    private CTipoPoblacion ctp;
    private DefaultComboBoxModel mTipoDocumento;
    private DefaultComboBoxModel mTipoVia;
    private DefaultComboBoxModel mTipoPoblacion;
    private ModeloTablaClienteValesArcangel mtp;
    private CTipoDocumento controllerTipoDocumento;
    private CTipoVia controllerTipoVia;
    private CTipoPoblacion controllerTipoPoblacion;
    private boolean esActualizacion = false;
    private boolean esComprobanteNuevo = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());       
    
    public IClientesValesArcangel() {
        initComponents();
        cp = new CCliente();
        cpc = new CClienteComprobante();
        ccd = new CClienteDireccion();
        ctd = new CTipoDocumento();
        ctv = new CTipoVia();
        ctp = new CTipoPoblacion();
        controllerTipoDocumento = new CTipoDocumento();          
        mTipoDocumento = new DefaultComboBoxModel(controllerTipoDocumento.getTipoDocumento().toArray());
        this.cmbTipoDocumento.setModel(mTipoDocumento);        
        this.cmbTipoDocumento.setSelectedIndex(1);                       

        controllerTipoVia = new CTipoVia();
        mTipoVia = new DefaultComboBoxModel(controllerTipoVia.getTipoVia().toArray());
        this.cmbTipoVia.setModel(mTipoVia);
        this.cmbTipoVia.setSelectedIndex(0);

        controllerTipoPoblacion = new CTipoPoblacion();
        mTipoPoblacion = new DefaultComboBoxModel(controllerTipoPoblacion.getTipoPoblacion().toArray());
        this.cmbTipoVivienda.setModel(mTipoPoblacion);
        this.cmbTipoVivienda.setSelectedIndex(0);

        CargaDatos();
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();        
    }
    
    public void CargaDatos(){
        String Estado="T";
        if (tipoSeleccion==1){
            Estado = "A";
        }else if (tipoSeleccion==0){
            Estado = "I";
        }
        mtp = new ModeloTablaClienteValesArcangel(Estado);
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaClienteValesArcangel.anchoColumnas);        
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
        Helper.setFiltraEntrada(txtSerie.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtNumeroComprobante.getDocument(), FiltraEntrada.SOLO_NUMEROS, 11, false);
        Helper.setFiltraEntrada(txtApellidoPaterno.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtApellidoMaterno.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtNombres.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtNumeroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 10, false);
        Helper.setFiltraEntrada(txtTelefono.getDocument(), FiltraEntrada.SOLO_NUMEROS, 20, false);
        Helper.setFiltraEntrada(txtEmail.getDocument(), FiltraEntrada.NUM_LETRAS, 50, false);
        Helper.setFiltraEntrada(txtCodigoDepartamento.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoDistrito.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
        Helper.setFiltraEntrada(txtCodigoProvincia.getDocument(), FiltraEntrada.SOLO_NUMEROS, 3, false);
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
            cp.setCliente(mtp.getFila(tblListado.getSelectedRow()));
            setCliente();
       }
    }
    
    private void setCliente(){
        Limpiar();
        this.txtCodigo.setText(cp.getClienteLocal().getCoClienteLocal());
        this.txtApellidoMaterno.setText(cp.getClienteLocal().getApMaternoCliente());
        this.txtApellidoPaterno.setText(cp.getClienteLocal().getApPaternoCliente());
        this.txtEmail.setText(cp.getClienteLocal().getDeEmail());
        this.txtNombres.setText(cp.getClienteLocal().getNoCliente());
        this.txtNumeroDocumento.setText(cp.getClienteLocal().getNuDocIdentidad());
        this.txtTelefono.setText(cp.getClienteLocal().getNuTelReferencia());
        this.txtSerie.setText(cp.getClienteLocal().getNuComprobante().substring(0, 3));
        this.txtNumeroComprobante.setText(cp.getClienteLocal().getNuComprobante().substring(4, 14));

        TipoDocumento tipoDocumento = new TipoDocumento();
        if (cp.getClienteLocal().getTiDocIdentidad().length()!=0){
            tipoDocumento = ctd.getRegistroPorPk(new Object[]{cp.getClienteLocal().getTiDocIdentidad()});
        }
        if (cp.getClienteLocal().getTiDocIdentidad() != null){
            for (int i=0; i<=cmbTipoDocumento.getItemCount(); i++){
                if (this.cmbTipoDocumento.getItemAt(i).toString().equals(tipoDocumento.getDeAbrDocumento())){
                    this.cmbTipoDocumento.setSelectedIndex(i);
                    break;
                }
            }
        }


        ClienteDireccion cliDireccion = new ClienteDireccion();
        cliDireccion = ccd.getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,cp.getClienteLocal().getCoClienteLocal()});
        if (cliDireccion != null){ 
            TipoVia tipoVia = new TipoVia();
            if (cliDireccion.getTiVia() != null){
                tipoVia = ctv.getRegistroPorPk(new Object[]{cliDireccion.getTiVia()});
                if (tipoVia != null){
                    if (cp.getClienteLocal().getTiCliente() != null){
                        for (int i=0; i<=cmbTipoVia.getItemCount(); i++){
                            if (this.cmbTipoVia.getItemAt(i).toString().equals(tipoVia.getDeTipoVia())){
                                this.cmbTipoVia.setSelectedIndex(i);
                                break;
                            }
                        }
                    }                
                }
            }
            this.txtNombreVia.setText(cliDireccion.getNoVia());
            this.txtNumero.setText(cliDireccion.getNuVia());
            this.txtNombreVia.setText(cliDireccion.getNoVia());
            this.txtBlock.setText(cliDireccion.getNuBlock());
            this.txtSector.setText(cliDireccion.getNuSector().toString());
            this.txtDpto.setText(cliDireccion.getNuDptoVia());
            this.txtLote.setText(cliDireccion.getNuLoteVia());
            this.txtManzana.setText(cliDireccion.getNuManzanaVia());

            if (cliDireccion.getTiPoblacion() != null){
                TipoPoblacion tipoPoblacion = new TipoPoblacion();
                tipoPoblacion = ctp.getRegistroPorPk(new Object[]{cliDireccion.getTiPoblacion()});
                
                for (int i=0; i<=cmbTipoVivienda.getItemCount(); i++){
                    if (this.cmbTipoVivienda.getItemAt(i).toString().equals(tipoPoblacion.getDePoblacion())){
                        this.cmbTipoVivienda.setSelectedIndex(i);
                        break;
                    }
                }
            }

            this.txtUrbanizacion.setText(cliDireccion.getNoPoblacion());
            this.txtCodigoDepartamento.setText(cliDireccion.getCoDepartamento());
            this.txtCodigoProvincia.setText(cliDireccion.getCoProvincia());
            this.txtCodigoDistrito.setText(cliDireccion.getCoDistrito());

            CUbigeo BG1 = new CUbigeo();
            if (!"".equals(txtCodigoDepartamento.getText()))
                txtDepartamento.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),null,null));
            if (!"".equals(txtCodigoProvincia.getText()))
                txtProvincia.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),null));
            if (!"".equals(txtCodigoDistrito.getText()))
                txtDistrito.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),txtCodigoDistrito.getText()));
        }        
        this.btnModificar.setEnabled(true);
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtSerie.setText("");
        this.txtNumeroComprobante.setText("");
        this.txtApellidoMaterno.setText("");
        this.txtApellidoPaterno.setText("");
        this.txtBlock.setText("");
        this.txtCodigo.setText("");
        this.txtDpto.setText("");
        this.txtEmail.setText("");
        this.txtLote.setText("");
        this.txtManzana.setText("");
        this.txtNombreVia.setText("");
        this.txtNombres.setText("");
        this.txtNumero.setText("");
        this.txtNumeroDocumento.setText("");
        this.txtSector.setText("");
        this.txtTelefono.setText("");
        this.txtUrbanizacion.setText("");
        this.txtCodigoDepartamento.setText("");
        this.txtCodigoProvincia.setText("");
        this.txtCodigoDistrito.setText("");
        this.txtDepartamento.setText("");
        this.txtProvincia.setText("");
        this.txtDistrito.setText("");
    }
    
    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);        
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblSerie, lblNroComprobante, lblTipoVIa, lblApellidoMaterno,lblApellidoPaterno,lblBlock,lblCodigo,lblDepartamento,lblDistrito,lblDpto,lblEmail,lblLote,lblManzana,lblNombreVia,lblNombres,lblNroDocumento,lblNumero,lblProvincia,lblSector,lblTelefono,lblTipoDocumento,lblTipoVia,lblUrbanizacion},false,"");        
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();        
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        
        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.btnBuscar.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);
        
        this.cmbTipoDocumento.setEnabled(true);
        this.cmbTipoVivienda.setEnabled(true);
        this.cmbTipoVia.setEnabled(true);
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblSerie, lblNroComprobante, lblTipoVIa, lblApellidoMaterno,lblApellidoPaterno,lblBlock,lblCodigo,lblDepartamento,lblDistrito,lblDpto,lblEmail,lblLote,lblManzana,lblNombreVia,lblNombres,lblNroDocumento,lblNumero,lblProvincia,lblSector,lblTelefono,lblTipoDocumento,lblTipoVia,lblUrbanizacion},false,"");        
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        
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
        this.cmbTipoVivienda.setEnabled(false);
        this.cmbTipoVia.setEnabled(false);
        
        logger.info(txtCodigo.getText() + " - " + txtApellidoPaterno.getText() + " " + txtApellidoMaterno.getText() + " " + txtNombres.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     

        
        if(!this.txtCodigo.getText().equals(String.valueOf(cp.getClienteLocal().getCoClienteLocal()))){
            return true;
        }else if(!this.txtApellidoMaterno.getText().equals(String.valueOf(cp.getClienteLocal().getApMaternoCliente()))){
            return true;
        }else if(!this.txtApellidoPaterno.getText().equals(String.valueOf(cp.getClienteLocal().getApPaternoCliente()))){
            return true;
        }else if(!this.txtEmail.getText().equals(String.valueOf(cp.getClienteLocal().getDeEmail()))){
            return true;
        }else if(!this.txtNombres.getText().equals(String.valueOf(cp.getClienteLocal().getNoCliente()))){
            return true;
        }else if(!this.txtNumeroDocumento.getText().equals(String.valueOf(cp.getClienteLocal().getNuDocIdentidad()))){
            return true;
        }else if(!this.txtTelefono.getText().equals(String.valueOf(cp.getClienteLocal().getNuTelReferencia()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{        
        cp.getClienteLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getClienteLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        cp.getClienteLocal().setCoClienteLocal(txtCodigo.getText().toUpperCase());        
        cp.getClienteLocal().setTiCliente("001");
        cp.getClienteLocal().setTiDocIdentidad(((TipoDocumento)this.cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad());        
        cp.getClienteLocal().setNuDocIdentidad(txtNumeroDocumento.getText());
        cp.getClienteLocal().setCoCategoriaCliente("");
        cp.getClienteLocal().setDeRazonSocial((txtApellidoPaterno.getText() + " " + txtApellidoMaterno.getText() + " " + txtNombres.getText()).toUpperCase());
        cp.getClienteLocal().setApPaternoCliente(txtApellidoPaterno.getText().toUpperCase());
        cp.getClienteLocal().setApMaternoCliente(txtApellidoMaterno.getText().toUpperCase());
        cp.getClienteLocal().setNoCliente(txtNombres.getText().toUpperCase());
        cp.getClienteLocal().setNuTelReferencia(txtTelefono.getText());
        cp.getClienteLocal().setNuTelFax("");
        cp.getClienteLocal().setNuTelMovil("");
        cp.getClienteLocal().setDeEmail(txtEmail.getText().toLowerCase());
        cp.getClienteLocal().setInClienteEspecial("");
        cp.getClienteLocal().setCoClienteCompania("");
        
        ccd.getClienteDireccionLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        ccd.getClienteDireccionLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        ccd.getClienteDireccionLocal().setCoClienteLocal(txtCodigo.getText());
        ccd.getClienteDireccionLocal().setTiVia(((TipoVia)this.cmbTipoVia.getSelectedItem()).getTiVia());
        ccd.getClienteDireccionLocal().setNoVia(txtNombreVia.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setNuVia(txtNumero.getText());
        ccd.getClienteDireccionLocal().setNoVia(txtNombreVia.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setNuBlock(txtBlock.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setNuSector(("".equals(txtSector.getText().trim())) ? 0 : Integer.parseInt(txtSector.getText()));
        ccd.getClienteDireccionLocal().setNuDptoVia(txtDpto.getText().toUpperCase());
//        ccd.getClienteDireccionLocal().setNuDptoVia(txtDpto.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setNuLoteVia(txtLote.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setNuManzanaVia(txtManzana.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setTiVia(((TipoPoblacion)this.cmbTipoVivienda.getSelectedItem()).getTiPoblacion());
        ccd.getClienteDireccionLocal().setNoPoblacion(txtUrbanizacion.getText().toUpperCase());
        ccd.getClienteDireccionLocal().setCoDepartamento(txtCodigoDepartamento.getText());
        ccd.getClienteDireccionLocal().setCoProvincia(txtCodigoProvincia.getText());
        ccd.getClienteDireccionLocal().setCoDistrito(txtCodigoDistrito.getText());
        ccd.getClienteDireccionLocal().setNuSecDireccion(1);
        ccd.getClienteDireccionLocal().setEsDireccionCliente(cp.getClienteLocal().getEsCliente());
        
        cpc.getClienteComprobante().setCoCompania(AtuxVariables.vCodigoCompania);
        cpc.getClienteComprobante().setCoLocal(AtuxVariables.vCodigoLocal);
        cpc.getClienteComprobante().setCoClienteLocal(txtCodigo.getText());
        cpc.getClienteComprobante().setNuComprobante(txtSerie.getText()+"-"+txtNumeroComprobante.getText());

        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getClienteLocal().setIdCreaCliente(AtuxVariables.vIdUsuario);
            cp.getClienteLocal().setFeCreaCliente(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            ccd.getClienteDireccionLocal().setIdCreaDireccionCliente(cp.getClienteLocal().getIdCreaCliente());
            ccd.getClienteDireccionLocal().setFeCreaDireccionCliente(cp.getClienteLocal().getFeCreaCliente());
            
            txtCodigo.setText(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal));
            cp.getClienteLocal().setCoClienteLocal(txtCodigo.getText());
            EstadoGuardar = cp.guardarRegistro(cp.getClienteLocal());
            if (EstadoGuardar == true){
                ccd.getClienteDireccionLocal().setCoClienteLocal(txtCodigo.getText());
                EstadoGuardar = ccd.guardarRegistro(ccd.getClienteDireccionLocal());
            }
            if (EstadoGuardar == true){
                cpc.getClienteComprobante().setCoClienteLocal(txtCodigo.getText());
                EstadoGuardar = cpc.guardarRegistro(cpc.getClienteComprobante());
            }            
        }else{
            cp.getClienteLocal().setIdModCliente(AtuxVariables.vIdUsuario);
            cp.getClienteLocal().setFeModCliente(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            ccd.getClienteDireccionLocal().setIdModDireccionCliente(cp.getClienteLocal().getIdModCliente());
            ccd.getClienteDireccionLocal().setFeModDireccionCliente(cp.getClienteLocal().getFeModCliente());
            
            int act = cp.actualizarRegistro(cp.getClienteLocal());
            if (act ==1) {
                act = ccd.actualizarRegistro(ccd.getClienteDireccionLocal());
                if (act ==1) {
                    if (esComprobanteNuevo){
                        cpc.getClienteComprobante().setCoClienteLocal(txtCodigo.getText());
                        EstadoGuardar = cpc.guardarRegistro(cpc.getClienteComprobante());
                        
                    }else{
                        act = cpc.actualizarRegistro(cpc.getClienteComprobante());
                        if (act ==1){
                            EstadoGuardar = true;
                        }                        
                    }
                }
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
            Logger.getLogger(IClientesValesArcangel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }



    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblNroDocumento = new javax.swing.JLabel();
        lblUrbanizacion = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtNumeroDocumento = new elaprendiz.gui.textField.TextField();
        txtUrbanizacion = new elaprendiz.gui.textField.TextField();
        txtTelefono = new elaprendiz.gui.textField.TextField();
        txtEmail = new elaprendiz.gui.textField.TextField();
        lblProvincia = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        lblDistrito = new javax.swing.JLabel();
        lblNombreVia = new javax.swing.JLabel();
        txtNombreVia = new elaprendiz.gui.textField.TextField();
        lblTipoVIa = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtSector = new elaprendiz.gui.textField.TextField();
        txtManzana = new elaprendiz.gui.textField.TextField();
        lblDpto = new javax.swing.JLabel();
        lblManzana = new javax.swing.JLabel();
        txtDpto = new elaprendiz.gui.textField.TextField();
        lblLote = new javax.swing.JLabel();
        txtNumero = new elaprendiz.gui.textField.TextField();
        txtLote = new elaprendiz.gui.textField.TextField();
        lblSector = new javax.swing.JLabel();
        lblTipoVia = new javax.swing.JLabel();
        lblTipoDocumento = new javax.swing.JLabel();
        txtBlock = new elaprendiz.gui.textField.TextField();
        lblBlock = new javax.swing.JLabel();
        cmbTipoVia = new elaprendiz.gui.comboBox.ComboBoxRect();
        txtCodigoDepartamento = new elaprendiz.gui.textField.TextField();
        txtDepartamento = new elaprendiz.gui.textField.TextField();
        txtProvincia = new elaprendiz.gui.textField.TextField();
        txtCodigoProvincia = new elaprendiz.gui.textField.TextField();
        txtCodigoDistrito = new elaprendiz.gui.textField.TextField();
        txtDistrito = new elaprendiz.gui.textField.TextField();
        cmbTipoDocumento = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbTipoVivienda = new elaprendiz.gui.comboBox.ComboBoxRect();
        txtApellidoPaterno = new elaprendiz.gui.textField.TextField();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtApellidoMaterno = new elaprendiz.gui.textField.TextField();
        lblApellidoMaterno = new javax.swing.JLabel();
        txtNombres = new elaprendiz.gui.textField.TextField();
        lblNombres = new javax.swing.JLabel();
        lblNroComprobante = new javax.swing.JLabel();
        txtNumeroComprobante = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        lblSerie = new javax.swing.JLabel();
        txtSerie = new elaprendiz.gui.textField.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setTitle("Formulario datos del Cliente");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(740, 600));
        setRequestFocusEnabled(false);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setMinimumSize(new java.awt.Dimension(100, 100));

        pnlEntradas.setOpaque(false);
        pnlEntradas.setPreferredSize(new java.awt.Dimension(1050, 360));

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo");
        lblCodigo.setAlignmentX(0.2F);
        lblCodigo.setAlignmentY(0.2F);

        lblNroDocumento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNroDocumento.setText("Nro. de Documento");
        lblNroDocumento.setAlignmentX(0.2F);
        lblNroDocumento.setAlignmentY(0.2F);

        lblUrbanizacion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUrbanizacion.setText("Urbanizacion");
        lblUrbanizacion.setAlignmentX(0.2F);
        lblUrbanizacion.setAlignmentY(0.2F);

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEmail.setText("Email:");
        lblEmail.setAlignmentX(0.2F);
        lblEmail.setAlignmentY(0.2F);

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTelefono.setText("Telefono");
        lblTelefono.setAlignmentX(0.2F);
        lblTelefono.setAlignmentY(0.2F);

        txtCodigo.setEditable(false);
        txtCodigo.setAlignmentX(0.2F);
        txtCodigo.setAlignmentY(0.2F);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pruc"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtNumeroDocumento.setEditable(false);
        txtNumeroDocumento.setAlignmentX(0.2F);
        txtNumeroDocumento.setAlignmentY(0.2F);
        txtNumeroDocumento.setDireccionDeSombra(30);
        txtNumeroDocumento.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtNumeroDocumento.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroDocumento.setName("pruc"); // NOI18N
        txtNumeroDocumento.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroDocumentoKeyReleased(evt);
            }
        });

        txtUrbanizacion.setEditable(false);
        txtUrbanizacion.setAlignmentX(0.2F);
        txtUrbanizacion.setAlignmentY(0.2F);
        txtUrbanizacion.setFont(new java.awt.Font("Arial", 0, 12));
        txtUrbanizacion.setPreferredSize(new java.awt.Dimension(320, 25));
        txtUrbanizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUrbanizacionKeyReleased(evt);
            }
        });

        txtTelefono.setEditable(false);
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTelefono.setAlignmentX(0.2F);
        txtTelefono.setAlignmentY(0.2F);
        txtTelefono.setFont(new java.awt.Font("Arial", 0, 12));
        txtTelefono.setPreferredSize(new java.awt.Dimension(120, 25));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        txtEmail.setEditable(false);
        txtEmail.setAlignmentX(0.2F);
        txtEmail.setAlignmentY(0.2F);
        txtEmail.setFont(new java.awt.Font("Arial", 0, 12));
        txtEmail.setPreferredSize(new java.awt.Dimension(320, 25));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        lblProvincia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblProvincia.setText("Provincia");
        lblProvincia.setAlignmentX(0.2F);
        lblProvincia.setAlignmentY(0.2F);

        lblDepartamento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDepartamento.setText("Departamento");
        lblDepartamento.setAlignmentX(0.2F);
        lblDepartamento.setAlignmentY(0.2F);

        lblDistrito.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDistrito.setText("Distrito");
        lblDistrito.setAlignmentX(0.2F);
        lblDistrito.setAlignmentY(0.2F);

        lblNombreVia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombreVia.setText("Nombre Via");
        lblNombreVia.setAlignmentX(0.2F);
        lblNombreVia.setAlignmentY(0.2F);

        txtNombreVia.setEditable(false);
        txtNombreVia.setAlignmentX(0.2F);
        txtNombreVia.setAlignmentY(0.2F);
        txtNombreVia.setFont(new java.awt.Font("Arial", 0, 12));
        txtNombreVia.setPreferredSize(new java.awt.Dimension(320, 25));
        txtNombreVia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreViaActionPerformed(evt);
            }
        });
        txtNombreVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreViaKeyReleased(evt);
            }
        });

        lblTipoVIa.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVIa.setText("Tipo Via");
        lblTipoVIa.setAlignmentX(0.2F);
        lblTipoVIa.setAlignmentY(0.2F);

        lblNumero.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNumero.setText("Numero ");
        lblNumero.setAlignmentX(0.2F);
        lblNumero.setAlignmentY(0.2F);

        txtSector.setEditable(false);
        txtSector.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSector.setAlignmentX(0.2F);
        txtSector.setAlignmentY(0.2F);
        txtSector.setFont(new java.awt.Font("Arial", 0, 12));
        txtSector.setPreferredSize(new java.awt.Dimension(120, 25));
        txtSector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSectorKeyReleased(evt);
            }
        });

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

        lblDpto.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDpto.setText("Dpto.");
        lblDpto.setAlignmentX(0.2F);
        lblDpto.setAlignmentY(0.2F);

        lblManzana.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblManzana.setText("Manzana");
        lblManzana.setAlignmentX(0.2F);
        lblManzana.setAlignmentY(0.2F);

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

        lblLote.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblLote.setText("Lote");
        lblLote.setAlignmentX(0.2F);
        lblLote.setAlignmentY(0.2F);

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

        lblSector.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSector.setText("Sector");
        lblSector.setAlignmentX(0.2F);
        lblSector.setAlignmentY(0.2F);

        lblTipoVia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoVia.setText("Tipo Viv.");
        lblTipoVia.setAlignmentX(0.2F);
        lblTipoVia.setAlignmentY(0.2F);

        lblTipoDocumento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoDocumento.setText("Tp. Doc.");
        lblTipoDocumento.setAlignmentX(0.2F);
        lblTipoDocumento.setAlignmentY(0.2F);

        txtBlock.setEditable(false);
        txtBlock.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtBlock.setAlignmentX(0.2F);
        txtBlock.setAlignmentY(0.2F);
        txtBlock.setFont(new java.awt.Font("Arial", 0, 12));
        txtBlock.setPreferredSize(new java.awt.Dimension(120, 25));
        txtBlock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBlockKeyReleased(evt);
            }
        });

        lblBlock.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblBlock.setText("Block.");
        lblBlock.setAlignmentX(0.2F);
        lblBlock.setAlignmentY(0.2F);

        cmbTipoVia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoViaActionPerformed(evt);
            }
        });
        cmbTipoVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoViaKeyReleased(evt);
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

        txtProvincia.setEditable(false);
        txtProvincia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProvincia.setAlignmentX(0.2F);
        txtProvincia.setAlignmentY(0.2F);
        txtProvincia.setFont(new java.awt.Font("Arial", 0, 12));
        txtProvincia.setPreferredSize(new java.awt.Dimension(120, 25));

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

        txtDistrito.setEditable(false);
        txtDistrito.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDistrito.setAlignmentX(0.2F);
        txtDistrito.setAlignmentY(0.2F);
        txtDistrito.setFont(new java.awt.Font("Arial", 0, 12));
        txtDistrito.setPreferredSize(new java.awt.Dimension(120, 25));

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

        cmbTipoVivienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoViviendaActionPerformed(evt);
            }
        });
        cmbTipoVivienda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoViviendaKeyReleased(evt);
            }
        });

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

        lblApellidoPaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoPaterno.setText("Ap. Paterno");
        lblApellidoPaterno.setAlignmentX(0.2F);
        lblApellidoPaterno.setAlignmentY(0.2F);

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

        lblApellidoMaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
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

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setText("Nombres");
        lblNombres.setAlignmentX(0.2F);
        lblNombres.setAlignmentY(0.2F);

        lblNroComprobante.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNroComprobante.setText("Nro. de Comprobante");
        lblNroComprobante.setAlignmentX(0.2F);
        lblNroComprobante.setAlignmentY(0.2F);

        txtNumeroComprobante.setEditable(false);
        txtNumeroComprobante.setAlignmentX(0.2F);
        txtNumeroComprobante.setAlignmentY(0.2F);
        txtNumeroComprobante.setDireccionDeSombra(30);
        txtNumeroComprobante.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtNumeroComprobante.setFont(new java.awt.Font("Arial", 0, 12));
        txtNumeroComprobante.setName("pruc"); // NOI18N
        txtNumeroComprobante.setPreferredSize(new java.awt.Dimension(120, 25));
        txtNumeroComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroComprobanteKeyReleased(evt);
            }
        });

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        chbEstado.setBackground(new java.awt.Color(20, 142, 196));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
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

        lblSerie.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSerie.setText("Serie");
        lblSerie.setAlignmentX(0.2F);
        lblSerie.setAlignmentY(0.2F);

        txtSerie.setEditable(false);
        txtSerie.setAlignmentX(0.2F);
        txtSerie.setAlignmentY(0.2F);
        txtSerie.setDireccionDeSombra(30);
        txtSerie.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtSerie.setFont(new java.awt.Font("Arial", 0, 12));
        txtSerie.setName("pruc"); // NOI18N
        txtSerie.setPreferredSize(new java.awt.Dimension(120, 25));
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerieKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblTelefono)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDepartamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProvincia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblDistrito))
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(lblEstado))
                                            .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblManzana)
                            .addComponent(txtManzana, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblLote))
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblTipoVia)
                            .addComponent(cmbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(lblUrbanizacion))
                            .addComponent(txtUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblTipoVIa))
                            .addComponent(cmbTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(lblNombreVia))
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(lblDpto))
                            .addComponent(txtDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblBlock))
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtSector, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSector)))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblSerie)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblNroComprobante))
                            .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDocumento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEntradasLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblNroDocumento))))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblNombres)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblApellidoPaterno)
                            .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblApellidoMaterno)
                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlEntradasLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlEntradasLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlEntradasLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoVIa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSector, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblManzana, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManzana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblEstado)
                        .addGap(6, 6, 6)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(6, Short.MAX_VALUE))
        );

        lblCodigo.getAccessibleContext().setAccessibleName("COPROVEEDOR");

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 90));

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
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

        javax.swing.GroupLayout pnlBuscadorTDeCambioLayout = new javax.swing.GroupLayout(pnlBuscadorTDeCambio);
        pnlBuscadorTDeCambio.setLayout(pnlBuscadorTDeCambioLayout);
        pnlBuscadorTDeCambioLayout.setHorizontalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
            .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorTDeCambioLayout.createSequentialGroup()
                    .addGap(0, 16, Short.MAX_VALUE)
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
                    .addGap(0, 16, Short.MAX_VALUE)))
        );
        pnlBuscadorTDeCambioLayout.setVerticalGroup(
            pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorTDeCambioLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
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
                .addContainerGap()
                .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setCliente(mtp.getFila(numRegistros));
        setCliente();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setCliente(mtp.getFila(numRegistros));
        setCliente();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        tipoSeleccion=1;
        if (rbNoActivos.isSelected()){
            tipoSeleccion=0;
        }
            
        BuscarCliente tCambio = new BuscarCliente(tipoSeleccion);
        JLabel aviso = tCambio.getAviso();
        
        String msj = (tipoSeleccion==-1?"Mostrando todos los Clientes existentes":(tipoSeleccion==1?"Mostrando todo los Clientes activos":"Mostrando todo los Clientes No activos"));
        JOptionPane.showInternalOptionDialog(this, tCambio, msj,JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

        if(tCambio.getCliente() != null){
           cp.setCliente(tCambio.getCliente());
           setCliente();
        }
}//GEN-LAST:event_btnBuscarActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setCliente(mtp.getFila(numRegistros));
        setCliente();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setCliente(mtp.getFila(numRegistros));
        setCliente();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        CargaDatos();
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();        
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        esComprobanteNuevo= true;
        Limpiar();
        txtCodigo.setText(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal));
        txtSerie.requestFocus();
        ActivarCasillas();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        esComprobanteNuevo= false;
        ActivarCasillas();
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

        boolean validaComprobante = cpc.getExisteComprobante(new Object[]{AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, txtSerie.getText()+"-"+txtNumeroComprobante.getText()});
        if(validaComprobante){
            JOptionPane.showInternalMessageDialog(this, "Número de Comprobante ya esta registrado", "Mensaje del SIstema", JOptionPane.ERROR_MESSAGE);
            return;
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
            ECampos.esObligatorio(this.pnlEntradas,false);
            return;
        }        
        AtuxUtility.aceptarTransaccion();
        CargaDatos();        
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
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

private void cmbTipoViaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoViaActionPerformed
        
}//GEN-LAST:event_cmbTipoViaActionPerformed

private void cmbTipoViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNombreVia.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoViaKeyReleased

public void muestraCampos(boolean vMostrar){
    lblApellidoPaterno.setVisible(vMostrar);
    lblApellidoMaterno.setVisible(vMostrar);
    lblNombres.setVisible(vMostrar);
    txtApellidoPaterno.setVisible(vMostrar);        
    txtApellidoMaterno.setVisible(vMostrar);
    txtNombres.setVisible(vMostrar);    
    
}
private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtEmailActionPerformed

private void txtCodigoDepartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoDepartamentoKeyReleased


    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
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
            if (!txtCodigoDepartamento.getText().equals(ccd.getClienteDireccionLocal().getCoDepartamento())){
                txtCodigoProvincia.setText("");
                txtCodigoDistrito.setText("");
                txtDepartamento.setText("");
                txtProvincia.setText("");
                txtDistrito.setText("");        
            }
            if (!"".equals(txtCodigoDepartamento.getText())){
                CUbigeo BG1 = new CUbigeo();
                txtDepartamento.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),null,null));
            }
            txtCodigoProvincia.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDepartamentoKeyReleased

private void txtCodigoProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProvinciaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
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
            if (!txtCodigoProvincia.getText().equals(ccd.getClienteDireccionLocal().getCoProvincia())){
                txtCodigoDistrito.setText("");
                txtProvincia.setText("");
                txtDistrito.setText("");            
            }
            if (!"".equals(txtCodigoProvincia.getText())){
                CUbigeo BG1 = new CUbigeo();
                txtProvincia.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),null));
            }
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
            if (!txtCodigoDistrito.getText().equals(ccd.getClienteDireccionLocal().getCoDistrito())){
                txtDistrito.setText("");            
            }
            if (!"".equals(txtCodigoDistrito.getText())){
                CUbigeo BG1 = new CUbigeo();
                txtDistrito.setText(BG1.BuscarUbigeo(txtCodigoDepartamento.getText(),txtCodigoProvincia.getText(),txtCodigoDistrito.getText()));
            }
            txtEmail.requestFocus();
            break;
    }
}//GEN-LAST:event_txtCodigoDistritoKeyReleased

private void cmbTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoActionPerformed
        int Largo = Integer.parseInt(((TipoDocumento)cmbTipoDocumento.getSelectedItem()).getNuCaracteres()); 
        if (Largo == 0){
            txtNumeroDocumento.setEnabled(false);
        }else{
            txtNumeroDocumento.setEnabled(true);
            Helper.setFiltraEntrada(txtNumeroDocumento.getDocument(), FiltraEntrada.SOLO_NUMEROS, Largo, false);
        }

}//GEN-LAST:event_cmbTipoDocumentoActionPerformed

private void cmbTipoDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNumeroDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoDocumentoKeyReleased

private void txtNombreViaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreViaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtNombreViaActionPerformed

private void cmbTipoViviendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoViviendaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmbTipoViviendaActionPerformed

private void cmbTipoViviendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoViviendaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtUrbanizacion.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoViviendaKeyReleased

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtSerie.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtNumeroDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
        boolean validaDocumento = cp.getExisteDocumento(new Object[]{((TipoDocumento)this.cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad(), txtNumeroDocumento.getText()});
        if(validaDocumento){
            Cliente cliente = cp.getRegistroPorPk2(new Object[]{((TipoDocumento)this.cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad(), txtNumeroDocumento.getText()});
            cp.setCliente(cliente);
            String nuSerie, nuComprobante, nuDni;
            nuSerie = txtSerie.getText();
            nuComprobante=txtNumeroComprobante.getText();
            nuDni=txtNumeroDocumento.getText();
            setCliente();
            txtSerie.setText(nuSerie);
            txtNumeroComprobante.setText(nuComprobante);
            txtNumeroDocumento.setText(nuDni);
            this.btnModificar.setEnabled(false);
            if (!"".equals(txtCodigo.getText()))
                esActualizacion = true;
        }
        txtNombres.requestFocus();
        break;
    }
}//GEN-LAST:event_txtNumeroDocumentoKeyReleased

private void txtApellidoPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtApellidoPaterno.setText(txtApellidoPaterno.getText().toUpperCase());
            txtApellidoMaterno.requestFocus();
            break;
    }
}//GEN-LAST:event_txtApellidoPaternoKeyReleased

private void txtApellidoMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMaternoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER:
            txtApellidoMaterno.setText(txtApellidoMaterno.getText().toUpperCase());
            cmbTipoVia.requestFocus();
            break;
    }
}//GEN-LAST:event_txtApellidoMaternoKeyReleased

private void txtNombreViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreViaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombreVia.setText(txtNombreVia.getText().toUpperCase());
            txtNumero.requestFocus();
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
        case KeyEvent.VK_ENTER:
            txtDpto.setText(txtDpto.getText().toUpperCase());
            txtBlock.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDptoKeyReleased

private void txtBlockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBlockKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtBlock.setText(txtBlock.getText().toUpperCase());
            txtSector.requestFocus();
            break;
    }
}//GEN-LAST:event_txtBlockKeyReleased

private void txtSectorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSectorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtSector.setText(txtSector.getText().toUpperCase());
            txtManzana.requestFocus();
            break;
    }
}//GEN-LAST:event_txtSectorKeyReleased

private void txtManzanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManzanaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtManzana.setText(txtManzana.getText().toUpperCase());
            txtLote.requestFocus();
            break;
    }
}//GEN-LAST:event_txtManzanaKeyReleased

private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER:
            txtLote.setText(txtLote.getText().toUpperCase());
            cmbTipoVivienda.requestFocus();
            break;
    }
}//GEN-LAST:event_txtLoteKeyReleased

private void txtUrbanizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrbanizacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtUrbanizacion.setText(txtUrbanizacion.getText().toUpperCase());
            txtCodigoDepartamento.requestFocus();
            break;
    }
}//GEN-LAST:event_txtUrbanizacionKeyReleased

private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtEmail.setText(txtEmail.getText().toLowerCase());
            txtTelefono.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailKeyReleased

private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            chbEstado.requestFocus();
            break;
    }
}//GEN-LAST:event_txtTelefonoKeyReleased

private void txtNumeroComprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroComprobanteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            if ("".equals(txtSerie.getText().trim())){
                JOptionPane.showInternalMessageDialog(this, "Serie del Comprobante no Valida, favor corregir", "Mensaje del SIstema", JOptionPane.ERROR_MESSAGE);
                txtSerie.requestFocus();
            }
            if (txtSerie.getText().trim().length() != 3){
                JOptionPane.showInternalMessageDialog(this, "Serie del Comprobante no Valida, favor corregir", "Mensaje del SIstema", JOptionPane.ERROR_MESSAGE);
                txtSerie.requestFocus();
            }
            String Comprobante="";
            Comprobante = ("0000000000" + txtNumeroComprobante.getText()).substring(Comprobante.length()+txtNumeroComprobante.getText().length(), txtNumeroComprobante.getText().length()+10);
            txtNumeroComprobante.setText(Comprobante);

            boolean validaComprobante = cpc.getExisteComprobante(new Object[]{AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal, (txtSerie.getText()+"-"+txtNumeroComprobante.getText()).toString()});
            if(validaComprobante){
                JOptionPane.showInternalMessageDialog(this, "Número de Comprobante ya esta registrado", "Mensaje del SIstema", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cmbTipoDocumento.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNumeroComprobanteKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
    chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            txtNombres.setText(txtNombres.getText().toUpperCase());
            txtApellidoPaterno.requestFocus();
            break;
    }
}//GEN-LAST:event_txtNombresKeyReleased

private void txtSerieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            String Serie="";
            Serie = ("000" + txtSerie.getText()).substring(Serie.length()+txtSerie.getText().length(), txtSerie.getText().length()+3);
            txtSerie.setText(Serie);
            txtNumeroComprobante.requestFocus();
            break;
    }
}//GEN-LAST:event_txtSerieKeyReleased

public void chbSetActivo(boolean opcion){
    chbEstado.setSelected(true);
    chbEstado.setText("Activo");
    chbEstado.setBackground(new Color(20,142,196));
    chbEstado.setForeground(Color.BLACK);
    if(!opcion){
        chbEstado.setSelected(false);
        chbEstado.setText("No Activo");
        chbEstado.setBackground(Color.red);
        chbEstado.setForeground(Color.WHITE);
    }
}    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoDocumento;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVia;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVivienda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblBlock;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblDpto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblManzana;
    private javax.swing.JLabel lblNombreVia;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNroComprobante;
    private javax.swing.JLabel lblNroDocumento;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblSector;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblTipoVIa;
    private javax.swing.JLabel lblTipoVia;
    private javax.swing.JLabel lblUrbanizacion;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtApellidoMaterno;
    private elaprendiz.gui.textField.TextField txtApellidoPaterno;
    private elaprendiz.gui.textField.TextField txtBlock;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCodigoDepartamento;
    private elaprendiz.gui.textField.TextField txtCodigoDistrito;
    private elaprendiz.gui.textField.TextField txtCodigoProvincia;
    private elaprendiz.gui.textField.TextField txtDepartamento;
    private elaprendiz.gui.textField.TextField txtDistrito;
    private elaprendiz.gui.textField.TextField txtDpto;
    private elaprendiz.gui.textField.TextField txtEmail;
    private elaprendiz.gui.textField.TextField txtLote;
    private elaprendiz.gui.textField.TextField txtManzana;
    private elaprendiz.gui.textField.TextField txtNombreVia;
    private elaprendiz.gui.textField.TextField txtNombres;
    private elaprendiz.gui.textField.TextField txtNumero;
    private elaprendiz.gui.textField.TextField txtNumeroComprobante;
    private elaprendiz.gui.textField.TextField txtNumeroDocumento;
    private elaprendiz.gui.textField.TextField txtProvincia;
    private elaprendiz.gui.textField.TextField txtSector;
    private elaprendiz.gui.textField.TextField txtSerie;
    private elaprendiz.gui.textField.TextField txtTelefono;
    private elaprendiz.gui.textField.TextField txtUrbanizacion;
    // End of variables declaration//GEN-END:variables



}
