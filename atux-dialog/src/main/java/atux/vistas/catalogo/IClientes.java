package atux.vistas.catalogo;

import atux.controllers.CCliente;
import atux.controllers.CClienteDireccion;
import atux.controllers.CTipoDocumento;
import atux.controllers.CTipoDeCliente;
import atux.controllers.CUbigeo;
import atux.controllers.CTipoVia;
import atux.controllers.CTipoPoblacion;
import atux.modelbd.ClienteDireccion;
import atux.modelbd.TipoDeCliente;
import atux.modelbd.TipoVia;
import atux.modelbd.TipoPoblacion;
import atux.modelgui.ModeloTablaCliente;
import atux.modelbd.TipoDocumento;
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
import java.util.Calendar;
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


public final class IClientes extends javax.swing.JInternalFrame {
    private CCliente cp;
    private CClienteDireccion ccd;
    private CTipoDeCliente ctc;
    private CTipoDocumento ctd;
    private CTipoVia ctv;
    private CTipoPoblacion ctp;
    private DefaultComboBoxModel mTipoDocumento;
    private DefaultComboBoxModel mTipoCliente;
    private DefaultComboBoxModel mTipoVia;
    private DefaultComboBoxModel mTipoPoblacion;
    private ModeloTablaCliente mtp;
    private CTipoDocumento controllerTipoDocumento;
    private CTipoDeCliente controllerTipoCliente;
    private CTipoVia controllerTipoVia;
    private CTipoPoblacion controllerTipoPoblacion;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());       
    
    public IClientes() {
        initComponents();
        cp = new CCliente();
        ccd = new CClienteDireccion();
        ctc = new CTipoDeCliente();
        ctd = new CTipoDocumento();
        ctv = new CTipoVia();
        ctp = new CTipoPoblacion();
        controllerTipoDocumento = new CTipoDocumento();
        mTipoDocumento = new DefaultComboBoxModel(controllerTipoDocumento.getTipoDocumento().toArray());
        this.cmbTipoDocumento.setModel(mTipoDocumento);        
        this.cmbTipoDocumento.setSelectedIndex(0);                       
        
        controllerTipoCliente = new CTipoDeCliente();
        mTipoCliente = new DefaultComboBoxModel(controllerTipoCliente.getTipoCliente().toArray());
        this.cmbTipoCliente.setModel(mTipoCliente);
        this.cmbTipoCliente.setSelectedIndex(0);

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
        mtp = new ModeloTablaCliente(Estado);
        numRegistros = mtp.getCantidadRegistros();

        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaCliente.anchoColumnas);        
        
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.SOLO_NUMEROS, 5, false);
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
        this.dteFechaNacimiento.setDate(cp.getClienteLocal().getFeNacimiento());

        TipoDocumento tipoDocumento = ctd.getRegistroPorPk(new Object[]{cp.getClienteLocal().getTiDocIdentidad()});

        if (cp.getClienteLocal().getTiDocIdentidad() != null){
            for (int i=0; i<cmbTipoVia.getItemCount(); i++){
                if (this.cmbTipoDocumento.getItemAt(i).toString().equals(tipoDocumento.getDeAbrDocumento())){
                    this.cmbTipoDocumento.setSelectedIndex(i);
                    break;
                }
            }
        }

        TipoDeCliente tipoCliente = ctc.getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania , cp.getClienteLocal().getTiCliente()});

        if (cp.getClienteLocal().getTiCliente() != null){
            for (int i=0; i<cmbTipoCliente.getItemCount(); i++){
                if (this.cmbTipoCliente.getItemAt(i).toString().equals(tipoCliente.getDeTipoCliente())){
                    this.cmbTipoCliente.setSelectedIndex(i);
                    break;
                }
            }
        }

        ClienteDireccion cliDireccion = ccd.getRegistroPorPk(new Object[]{AtuxVariables.vCodigoCompania,AtuxVariables.vCodigoLocal,cp.getClienteLocal().getCoClienteLocal()});

        if (cliDireccion != null){
            TipoVia tipoVia = ctv.getRegistroPorPk(new Object[]{cliDireccion.getTiVia()});

            if (cp.getClienteLocal().getTiCliente() != null){
                for (int i=0; i<cmbTipoVia.getItemCount(); i++){
                    if (this.cmbTipoVia.getItemAt(i).toString().equals(tipoVia.getDeTipoVia())){
                        this.cmbTipoVia.setSelectedIndex(i);
                        break;
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
                TipoPoblacion tipoPoblacion = ctp.getRegistroPorPk(new Object[]{cliDireccion.getTiPoblacion()});

                for (int i=0; i<cmbTipoVivienda.getItemCount(); i++){
                    if (this.cmbTipoVivienda.getItemAt(i).toString().equals(tipoPoblacion.getDePoblacion())){
                        this.cmbTipoVivienda.setSelectedIndex(i);
                        break;
                    }
                }
            }
            
            this.txtUrbanizacion.setText(cliDireccion.getNoPoblacion());
            this.txtReferencia.setText(cliDireccion.getDeReferencia());
            this.txtCodigoDepartamento.setText(cliDireccion.getCoDepartamento());
            this.txtCodigoProvincia.setText(cliDireccion.getCoProvincia());
            this.txtCodigoDistrito.setText(cliDireccion.getCoDistrito());

            if (cliDireccion.getCoDepartamento() != null)
                BuscarUbigeo(cliDireccion.getCoDepartamento(),null,null);
            if (cliDireccion.getCoProvincia() != null)
                BuscarUbigeo(cliDireccion.getCoDepartamento(),cliDireccion.getCoProvincia(),null);
            if (cliDireccion.getCoDistrito() != null)
                BuscarUbigeo(cliDireccion.getCoDepartamento(),cliDireccion.getCoProvincia(),cliDireccion.getCoDistrito());
        }        
        this.btnModificar.setEnabled(true);
    }

    private void BuscarUbigeo(String CodigoDepartamento, String CodigoProvincia, String CodigoDistrito){
        CUbigeo BG1 = new CUbigeo();
        if (CodigoDistrito != null){
            BG1.getBuscarDistrito(CodigoDepartamento,CodigoProvincia,CodigoDistrito);
            txtDistrito.setText(BG1.getUbigeo().getDeUbigeo().trim());
            
        }else if (CodigoProvincia != null){
            BG1.getBuscarProvincia(CodigoDepartamento,CodigoProvincia);
            txtProvincia.setText(BG1.getUbigeo().getDeUbigeo().trim());
        }else{
            BG1.getBuscarDepartamento(CodigoDepartamento);
            txtDepartamento.setText(BG1.getUbigeo().getDeUbigeo().trim());            
        }
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
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
        this.txtReferencia.setText("");
        this.txtSector.setText("");
        this.txtTelefono.setText("");
        this.txtUrbanizacion.setText("");
        this.dteFechaNacimiento.setDate(null);
        this.txtCodigoDepartamento.setText("");
        this.txtCodigoProvincia.setText("");
        this.txtCodigoDistrito.setText("");
    }
    
    private void ActivarCasillas(){
        pnlEntradas.setEnabled(false);        
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblApellidoMaterno,lblApellidoPaterno,lblBlock,lblCodigo,lblDepartamento,lblDistrito,lblDpto,lblEmail,lblFechaNacimiento,lblLote,lblManzana,lblNombreVia,lblNombres,lblNroDocumento,lblNumero,lblProvincia,lblReferencia,lblSector,lblTelefono,lblTipoCliente,lblTipoDocumento,lblTipoVia,lblUrbanizacion,lblEstado},false,"");        
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
        this.cmbTipoCliente.setEnabled(true);
        this.cmbTipoVivienda.setEnabled(true);
        this.cmbTipoVia.setEnabled(true);
        this.dteFechaNacimiento.setEnabled(true);
        this.cmbTipoCliente.setEnabled(true);
        this.chbEstado.setEnabled(true);
        
        
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblApellidoMaterno,lblApellidoPaterno,lblBlock,lblCodigo,lblDepartamento,lblDistrito,lblDpto,lblEmail,lblFechaNacimiento,lblLote,lblManzana,lblNombreVia,lblNombres,lblNroDocumento,lblNumero,lblProvincia,lblReferencia,lblSector,lblTelefono,lblTipoCliente,lblTipoDocumento,lblTipoVia,lblUrbanizacion,lblEstado},false,"");        
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
        this.cmbTipoCliente.setEnabled(false);
        this.cmbTipoVivienda.setEnabled(false);
        this.cmbTipoVia.setEnabled(false);
        this.dteFechaNacimiento.setEnabled(false);
        this.cmbTipoCliente.setEnabled(false);
        this.chbEstado.setEnabled(false);
        
        logger.info(txtCodigo.getText() + " - " + txtApellidoPaterno.getText() + " " + txtApellidoMaterno.getText() + " " + txtNombres.getText());
    }

    public boolean verficarCambios(){               
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
        txtCodigo.setText(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal));
        cp.getClienteLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getClienteLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        cp.getClienteLocal().setCoClienteLocal(txtCodigo.getText());        
        cp.getClienteLocal().setTiCliente(((TipoDeCliente)this.cmbTipoCliente.getSelectedItem()).getTiCliente());
        cp.getClienteLocal().setTiDocIdentidad(((TipoDocumento)this.cmbTipoDocumento.getSelectedItem()).getCoDocumentoIdentidad());        
        cp.getClienteLocal().setNuDocIdentidad(txtNumeroDocumento.getText());
        cp.getClienteLocal().setCoCategoriaCliente("");

        if ("002".equals(((TipoDeCliente)cmbTipoCliente.getSelectedItem()).getTiCliente())){
            cp.getClienteLocal().setDeRazonSocial(txtRazonSocial.getText());
            cp.getClienteLocal().setApPaternoCliente("");
            cp.getClienteLocal().setApMaternoCliente("");
            cp.getClienteLocal().setNoCliente("");
        }else{
            cp.getClienteLocal().setDeRazonSocial(txtApellidoPaterno.getText() + " " + txtApellidoMaterno.getText() + " " + txtNombres.getText());
            cp.getClienteLocal().setApPaternoCliente(txtApellidoPaterno.getText());
            cp.getClienteLocal().setApMaternoCliente(txtApellidoMaterno.getText());
            cp.getClienteLocal().setNoCliente(txtNombres.getText());

        }        
        
        cp.getClienteLocal().setFeNacimiento(dteFechaNacimiento.getDate());
        cp.getClienteLocal().setNuTelReferencia(txtTelefono.getText());
        cp.getClienteLocal().setNuTelFax("");
        cp.getClienteLocal().setNuTelMovil("");
        cp.getClienteLocal().setDeEmail(txtEmail.getText());
        cp.getClienteLocal().setInClienteEspecial("");
        cp.getClienteLocal().setCoClienteCompania("");
        cp.getClienteLocal().setEsCliente((chbEstado.isSelected() == true) ? "A" : "I");
        
        ccd.getClienteDireccionLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        ccd.getClienteDireccionLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        ccd.getClienteDireccionLocal().setCoClienteLocal(txtCodigo.getText());
        ccd.getClienteDireccionLocal().setTiVia(((TipoVia)this.cmbTipoVia.getSelectedItem()).getTiVia());
        ccd.getClienteDireccionLocal().setNoVia(txtNombreVia.getText());
        ccd.getClienteDireccionLocal().setNuVia(txtNumero.getText());
        ccd.getClienteDireccionLocal().setNoVia(txtNombreVia.getText());
        ccd.getClienteDireccionLocal().setNuBlock(txtBlock.getText());
        ccd.getClienteDireccionLocal().setNuSector(("".equals(txtSector.getText().trim())) ? 0 : Integer.parseInt(txtSector.getText()));
        ccd.getClienteDireccionLocal().setNuDptoVia(txtDpto.getText());
        ccd.getClienteDireccionLocal().setNuDptoVia(txtDpto.getText());
        ccd.getClienteDireccionLocal().setNuLoteVia(txtLote.getText());
        ccd.getClienteDireccionLocal().setNuManzanaVia(txtManzana.getText());
        ccd.getClienteDireccionLocal().setTiPoblacion(((TipoPoblacion)this.cmbTipoVivienda.getSelectedItem()).getTiPoblacion());
        ccd.getClienteDireccionLocal().setNoPoblacion(txtUrbanizacion.getText());
        ccd.getClienteDireccionLocal().setDeReferencia(txtReferencia.getText());
        ccd.getClienteDireccionLocal().setCoDepartamento(txtCodigoDepartamento.getText());
        ccd.getClienteDireccionLocal().setCoProvincia(txtCodigoProvincia.getText());
        ccd.getClienteDireccionLocal().setCoDistrito(txtCodigoDistrito.getText());
        ccd.getClienteDireccionLocal().setNuSecDireccion(1);
        ccd.getClienteDireccionLocal().setEsDireccionCliente(cp.getClienteLocal().getEsCliente());
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
            
        }else{
            cp.getClienteLocal().setIdModCliente(AtuxVariables.vIdUsuario);
            cp.getClienteLocal().setFeModCliente(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            ccd.getClienteDireccionLocal().setIdModDireccionCliente(cp.getClienteLocal().getIdModCliente());
            ccd.getClienteDireccionLocal().setFeModDireccionCliente(cp.getClienteLocal().getFeModCliente());
            
            int act = cp.actualizarRegistro(cp.getClienteLocal());
            if (act ==1) {
                act = ccd.actualizarRegistro(ccd.getClienteDireccionLocal());
                if (act ==1){
                    EstadoGuardar = true;
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
            Logger.getLogger(IClientes.class.getName()).log(Level.SEVERE, null, ex);
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
        lblFechaNacimiento = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtNumeroDocumento = new elaprendiz.gui.textField.TextField();
        txtUrbanizacion = new elaprendiz.gui.textField.TextField();
        txtTelefono = new elaprendiz.gui.textField.TextField();
        txtReferencia = new elaprendiz.gui.textField.TextField();
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
        cmbTipoCliente = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTipoCliente = new javax.swing.JLabel();
        dteFechaNacimiento = new com.toedter.calendar.JDateChooser();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        txtCodigoDepartamento = new elaprendiz.gui.textField.TextField();
        txtDepartamento = new elaprendiz.gui.textField.TextField();
        txtProvincia = new elaprendiz.gui.textField.TextField();
        txtCodigoProvincia = new elaprendiz.gui.textField.TextField();
        txtCodigoDistrito = new elaprendiz.gui.textField.TextField();
        txtDistrito = new elaprendiz.gui.textField.TextField();
        jpDatos = new javax.swing.JPanel();
        txtApellidoPaterno = new elaprendiz.gui.textField.TextField();
        txtApellidoMaterno = new elaprendiz.gui.textField.TextField();
        txtNombres = new elaprendiz.gui.textField.TextField();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        cmbTipoDocumento = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbTipoVivienda = new elaprendiz.gui.comboBox.ComboBoxRect();
        txtRazonSocial = new elaprendiz.gui.textField.TextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formulario datos del Cliente");
        setOpaque(true);
        setRequestFocusEnabled(false);
        setVisible(true);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setMinimumSize(new java.awt.Dimension(100, 100));

        pnlEntradas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlEntradas.setOpaque(false);

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

        lblFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFechaNacimiento.setText("Fecha Nacimiento");
        lblFechaNacimiento.setAlignmentX(0.2F);
        lblFechaNacimiento.setAlignmentY(0.2F);

        lblReferencia.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblReferencia.setText("Referencia");
        lblReferencia.setAlignmentX(0.2F);
        lblReferencia.setAlignmentY(0.2F);

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

        txtReferencia.setEditable(false);
        txtReferencia.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtReferencia.setAlignmentX(0.2F);
        txtReferencia.setAlignmentY(0.2F);
        txtReferencia.setFont(new java.awt.Font("Arial", 0, 12));
        txtReferencia.setPreferredSize(new java.awt.Dimension(120, 25));
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyReleased(evt);
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

        cmbTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoClienteActionPerformed(evt);
            }
        });
        cmbTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoClienteKeyReleased(evt);
            }
        });

        lblTipoCliente.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTipoCliente.setText("Tp. Cliente");
        lblTipoCliente.setAlignmentX(0.2F);
        lblTipoCliente.setAlignmentY(0.2F);

        dteFechaNacimiento.setBackground(new java.awt.Color(0, 0, 0));
        dteFechaNacimiento.setForeground(new java.awt.Color(255, 0, 0));
        dteFechaNacimiento.setDate(Calendar.getInstance().getTime());
        dteFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 13));
        dteFechaNacimiento.setPreferredSize(new java.awt.Dimension(120, 22));
        dteFechaNacimiento.setRequestFocusEnabled(false);
        dteFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dteFechaNacimientoKeyReleased(evt);
            }
        });

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
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

        jpDatos.setBackground(new java.awt.Color(51, 153, 255));
        jpDatos.setOpaque(false);
        jpDatos.setPreferredSize(new java.awt.Dimension(450, 60));

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

        lblApellidoPaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoPaterno.setText("Ap. Paterno");
        lblApellidoPaterno.setAlignmentX(0.2F);
        lblApellidoPaterno.setAlignmentY(0.2F);

        lblApellidoMaterno.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblApellidoMaterno.setText("Ap. Materno");
        lblApellidoMaterno.setAlignmentX(0.2F);
        lblApellidoMaterno.setAlignmentY(0.2F);

        lblNombres.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblNombres.setText("Nombres");
        lblNombres.setAlignmentX(0.2F);
        lblNombres.setAlignmentY(0.2F);

        lblRazonSocial.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblRazonSocial.setText("Razon Social");
        lblRazonSocial.setAlignmentX(0.2F);
        lblRazonSocial.setAlignmentY(0.2F);

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblApellidoPaterno)
                        .addGap(8, 8, 8)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRazonSocial)
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(lblApellidoMaterno)))
                        .addGap(85, 85, 85)
                        .addComponent(lblNombres))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setAlignmentX(0.2F);
        txtRazonSocial.setAlignmentY(0.2F);
        txtRazonSocial.setFont(new java.awt.Font("Arial", 0, 12));
        txtRazonSocial.setPreferredSize(new java.awt.Dimension(320, 25));
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblCodigo))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblTipoDocumento))
                    .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblTipoCliente))
                    .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblNroDocumento))
                    .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTipoVIa)
                .addGap(110, 110, 110)
                .addComponent(lblNombreVia)
                .addGap(59, 59, 59)
                .addComponent(lblNumero)
                .addGap(20, 20, 20)
                .addComponent(lblDpto)
                .addGap(32, 32, 32)
                .addComponent(lblBlock)
                .addGap(29, 29, 29)
                .addComponent(lblSector)
                .addGap(15, 15, 15)
                .addComponent(lblManzana)
                .addGap(29, 29, 29)
                .addComponent(lblLote)
                .addGap(29, 29, 29)
                .addComponent(lblTipoVia))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(cmbTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtSector, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtManzana, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cmbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblUrbanizacion)
                .addGap(167, 167, 167)
                .addComponent(lblReferencia)
                .addGap(128, 128, 128)
                .addComponent(lblDepartamento)
                .addGap(130, 130, 130)
                .addComponent(lblProvincia))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblDistrito)
                .addGap(205, 205, 205)
                .addComponent(lblEmail)
                .addGap(159, 159, 159)
                .addComponent(lblTelefono)
                .addGap(60, 60, 60)
                .addComponent(lblFechaNacimiento)
                .addGap(39, 39, 39)
                .addComponent(lblEstado))
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(dteFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoVIa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSector, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblManzana, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLote, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManzana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUrbanizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblEstado)))
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dteFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lblCodigo.getAccessibleContext().setAccessibleName("COPROVEEDOR");

        jScrollPane1.setOpaque(false);

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

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorTDeCambio.setOpaque(false);

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
            .addGap(0, 563, Short.MAX_VALUE)
            .addGroup(pnlBuscadorTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBuscadorTDeCambioLayout.createSequentialGroup()
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
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        txtCodigo.setText(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania, AtuxVariables.vCodigoLocal));
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
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IClientes.class.getName()).log(Level.SEVERE, null, ex);
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

private void cmbTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoClienteActionPerformed

    if ("002".equals(((TipoDeCliente)cmbTipoCliente.getSelectedItem()).getTiCliente())){
        muestraCampos(false);

    }else{
        muestraCampos(true);
    }
}//GEN-LAST:event_cmbTipoClienteActionPerformed

public void muestraCampos(boolean vMostrar){
    lblApellidoPaterno.setVisible(vMostrar);
    lblApellidoMaterno.setVisible(vMostrar);
    lblNombres.setVisible(vMostrar);
    txtApellidoPaterno.setVisible(vMostrar);        
    txtApellidoMaterno.setVisible(vMostrar);
    txtNombres.setVisible(vMostrar);    
    
    lblRazonSocial.setVisible(!vMostrar);
    txtRazonSocial.setVisible(!vMostrar);
    
}
private void cmbTipoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoClienteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtNumeroDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_cmbTipoClienteKeyReleased

private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtEmailActionPerformed

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
    chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }
}//GEN-LAST:event_chbEstadoKeyReleased

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
        case KeyEvent.VK_ENTER: cmbTipoCliente.requestFocus();
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
        case KeyEvent.VK_ENTER: cmbTipoDocumento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtNumeroDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: 
            if ("002".equals(((TipoDeCliente)cmbTipoCliente.getSelectedItem()).getTiCliente())){
                txtRazonSocial.requestFocus();
            }else{
                txtApellidoPaterno.requestFocus();
            }            
             break;
    }
}//GEN-LAST:event_txtNumeroDocumentoKeyReleased

private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoVia.requestFocus();
             break;
    }
}//GEN-LAST:event_txtRazonSocialKeyReleased

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
        case KeyEvent.VK_ENTER: txtBlock.requestFocus();
             break;
    }
}//GEN-LAST:event_txtDptoKeyReleased

private void txtBlockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBlockKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtSector.requestFocus();
             break;
    }
}//GEN-LAST:event_txtBlockKeyReleased

private void txtSectorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSectorKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtManzana.requestFocus();
             break;
    }
}//GEN-LAST:event_txtSectorKeyReleased

private void txtManzanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManzanaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtLote.requestFocus();
             break;
    }
}//GEN-LAST:event_txtManzanaKeyReleased

private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTipoVivienda.requestFocus();
             break;
    }
}//GEN-LAST:event_txtLoteKeyReleased

private void txtUrbanizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrbanizacionKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtReferencia.requestFocus();
             break;
    }
}//GEN-LAST:event_txtUrbanizacionKeyReleased

private void txtReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtCodigoDepartamento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtReferenciaKeyReleased

private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtTelefono.requestFocus();
             break;
    }
}//GEN-LAST:event_txtEmailKeyReleased

private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: dteFechaNacimiento.requestFocus();
             break;
    }
}//GEN-LAST:event_txtTelefonoKeyReleased

private void dteFechaNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dteFechaNacimientoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }
}//GEN-LAST:event_dteFechaNacimientoKeyReleased

    
    
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
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoCliente;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoDocumento;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVia;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTipoVivienda;
    private com.toedter.calendar.JDateChooser dteFechaNacimiento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblBlock;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblDpto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblManzana;
    private javax.swing.JLabel lblNombreVia;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNroDocumento;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblSector;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoCliente;
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
    private elaprendiz.gui.textField.TextField txtNumeroDocumento;
    private elaprendiz.gui.textField.TextField txtProvincia;
    private elaprendiz.gui.textField.TextField txtRazonSocial;
    private elaprendiz.gui.textField.TextField txtReferencia;
    private elaprendiz.gui.textField.TextField txtSector;
    private elaprendiz.gui.textField.TextField txtTelefono;
    private elaprendiz.gui.textField.TextField txtUrbanizacion;
    // End of variables declaration//GEN-END:variables



}
