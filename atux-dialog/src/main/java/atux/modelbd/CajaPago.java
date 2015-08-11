package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class CajaPago extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTR_CAJA_PAGO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","NU_CAJA"};
    public static final String COLUMNA_ACTIVO = "ES_CAJA";
    
    private String coCompania;
    private String coLocal;
    private Integer nuCaja;
    private Integer nuOrdenFila;
    private String deCorta;
    private String deCaja;
    private String inCajaAbierta;
    private String inTipoCaja;
    private String nuTurno;
    private Double vaMonto;
    private String esCaja;
    private String idCreaCaja;
    private Date   feCreaCaja;
    private String idModCaja;
    private Date   feModCaja;
    
    //Campos adicionales para la grid
    private String  tiComprobante;
    private String  deComprobante;
    private Integer nuImpresora;
    private String  deColaImpresion;
    private String  estado;
    
    //Campos adicionales para mostrar los numeros de comprobante
    private String nuSerie;
    private Integer nuComprobante;
    
    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA,CO_LOCAL,NU_CAJA,NU_ORDEN_FILA,DE_CORTA_CAJA,DE_CAJA,IN_CAJA_ABIERTA,IN_TIPO_CAJA,NU_TURNO,"
                          + "VA_MONTO_APERTURA,ES_CAJA,ID_CREA_CAJA,FE_CREA_CAJA,ID_MOD_CAJA,FE_MOD_CAJA"};

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getDeCaja() {
        return deCaja;
    }

    public void setDeCaja(String deCaja) {
        this.deCaja = deCaja;
    }

    public String getDeCorta() {
        return deCorta;
    }

    public void setDeCorta(String deCorta) {
        this.deCorta = deCorta;
    }

    public String getEsCaja() {
        return esCaja;
    }

    public void setEsCaja(String esCaja) {
        this.esCaja = esCaja;
    }

    public Date getFeCreaCaja() {
        return feCreaCaja;
    }

    public void setFeCreaCaja(Date feCreaCaja) {
        this.feCreaCaja = feCreaCaja;
    }

    public Date getFeModCaja() {
        return feModCaja;
    }

    public void setFeModCaja(Date feModCaja) {
        this.feModCaja = feModCaja;
    }

    public String getIdCreaCaja() {
        return idCreaCaja;
    }

    public void setIdCreaCaja(String idCreaCaja) {
        this.idCreaCaja = idCreaCaja;
    }

    public String getIdModCaja() {
        return idModCaja;
    }

    public void setIdModCaja(String idModCaja) {
        this.idModCaja = idModCaja;
    }

    public String getInCajaAbierta() {
        return inCajaAbierta;
    }

    public void setInCajaAbierta(String inCajaAbierta) {
        this.inCajaAbierta = inCajaAbierta;
    }

    public String getInTipoCaja() {
        return inTipoCaja;
    }

    public void setInTipoCaja(String inTipoCaja) {
        this.inTipoCaja = inTipoCaja;
    }

    public Integer getNuCaja() {
        return nuCaja;
    }

    public void setNuCaja(Integer nuCaja) {
        this.nuCaja = nuCaja;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getNuTurno() {
        return nuTurno;
    }

    public void setNuTurno(String nuTurno) {
        this.nuTurno = nuTurno;
    }

    public Double getVaMonto() {
        return vaMonto;
    }

    public void setVaMonto(Double vaMonto) {
        this.vaMonto = vaMonto;
    }

    public String getDeColaImpresion() {
        return deColaImpresion;
    }

    public void setDeColaImpresion(String deColaImpresion) {
        this.deColaImpresion = deColaImpresion;
    }

    public String getDeComprobante() {
        return deComprobante;
    }

    public void setDeComprobante(String deComprobante) {
        this.deComprobante = deComprobante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNuImpresora() {
        return nuImpresora;
    }

    public void setNuImpresora(Integer nuImpresora) {
        this.nuImpresora = nuImpresora;
    }

    public String getTiComprobante() {
        return tiComprobante;
    }

    public void setTiComprobante(String tiComprobante) {
        this.tiComprobante = tiComprobante;
    }

    public Integer getNuComprobante() {
        return nuComprobante;
    }

    public void setNuComprobante(Integer nuComprobante) {
        this.nuComprobante = nuComprobante;
    }

    public String getNuSerie() {
        return nuSerie;
    }

    public void setNuSerie(String nuSerie) {
        this.nuSerie = nuSerie;
    }        
    
    public CajaPago() {
        initBasic();
    }
       
   private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {   
        
        if (!(object instanceof CajaPago)) {
            return false;
        }
        CajaPago other = (CajaPago) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deCorta ;        
    } 
}