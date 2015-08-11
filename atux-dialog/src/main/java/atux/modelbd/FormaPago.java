/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class FormaPago extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTR_FORMA_PAGO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_FORMA_PAGO"};
    public static final String COLUMNA_ACTIVO = "ES_FORMA_PAGO";
    public static final String[] COLUMNA_ORDER = {"DE_FORMA_PAGO"};
    
    private String coCompania;
    private String coFormaPago;
    private Integer nuOrdenFila;
    private String deCortaFormaPago;
    private String deFormaPago;
    private String inPagoExacto;
    private String inTarjetaBancaria;
    private String inDeficitCaja;
    private String inDetalle;
    private String inSobreHermes;
    private String esFormaPago;
    private String coClienteCompania;
    private String tiTransaccionTarjeta;
    private String inCreditoConvenioCompania;
    private String inAperturaGaveta;
    private String inMuestraCajaElectronica;
    private String idCreaFormaPago;
    private Date feCreaFormaPago;
    private String idModFormaPago;
    private Date feModFormaPago;

    
    public static final String[]
        FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_FORMA_PAGO, NU_ORDEN_FILA, DE_CORTA_FORMA_PAGO, DE_FORMA_PAGO,"
                        + "IN_PAGO_EXACTO, IN_TARJETA_BANCARIA, IN_DEFICIT_CAJA, IN_DETALLE, IN_SOBRE_HERMES, "
                        + "ES_FORMA_PAGO, CO_CLIENTE_COMPANIA, TI_TRANSACCION_TARJETA, IN_CREDITO_CONVENIO_COMPANIA, "
                        + "IN_APERTURA_GAVETA, IN_MUESTRA_CAJA_ELECTRONICA, ID_CREA_FORMA_PAGO, FE_CREA_FORMA_PAGO,"
                        + "ID_MOD_FORMA_PAGO, FE_MOD_FORMA_PAGO"};

    public static final String[]
           CAMPOS_NO_ID ={"NU_ORDEN_FILA, DE_CORTA_FORMA_PAGO, DE_FORMA_PAGO,"
                        + "IN_PAGO_EXACTO, IN_TARJETA_BANCARIA, IN_DEFICIT_CAJA, IN_DETALLE, IN_SOBRE_HERMES, "
                        + "ES_FORMA_PAGO, CO_CLIENTE_COMPANIA, TI_TRANSACCION_TARJETA, IN_CREDITO_CONVENIO_COMPANIA, "
                        + "IN_APERTURA_GAVETA, IN_MUESTRA_CAJA_ELECTRONICA, ID_CREA_FORMA_PAGO, FE_CREA_FORMA_PAGO,"
                        + "ID_MOD_FORMA_PAGO, FE_MOD_FORMA_PAGO"};
    
    

    public FormaPago() {
         initBasic();
    }

    public String getCoClienteCompania() {
        return coClienteCompania;
    }

    public void setCoClienteCompania(String coClienteCompania) {
        this.coClienteCompania = coClienteCompania;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoFormaPago() {
        return coFormaPago;
    }

    public void setCoFormaPago(String coFormaPago) {
        this.coFormaPago = coFormaPago;
    }

    public String getDeCortaFormaPago() {
        return deCortaFormaPago;
    }

    public void setDeCortaFormaPago(String deCortaFormaPago) {
        this.deCortaFormaPago = deCortaFormaPago;
    }

    public String getDeFormaPago() {
        return deFormaPago;
    }

    public void setDeFormaPago(String deFormaPago) {
        this.deFormaPago = deFormaPago;
    }

    public String getEsFormaPago() {
        return esFormaPago;
    }

    public void setEsFormaPago(String esFormaPago) {
        this.esFormaPago = esFormaPago;
    }

    public Date getFeCreaFormaPago() {
        return feCreaFormaPago;
    }

    public void setFeCreaFormaPago(Date feCreaFormaPago) {
        this.feCreaFormaPago = feCreaFormaPago;
    }

    public Date getFeModFormaPago() {
        return feModFormaPago;
    }

    public void setFeModFormaPago(Date feModFormaPago) {
        this.feModFormaPago = feModFormaPago;
    }

    public String getIdCreaFormaPago() {
        return idCreaFormaPago;
    }

    public void setIdCreaFormaPago(String idCreaFormaPago) {
        this.idCreaFormaPago = idCreaFormaPago;
    }

    public String getIdModFormaPago() {
        return idModFormaPago;
    }

    public void setIdModFormaPago(String idModFormaPago) {
        this.idModFormaPago = idModFormaPago;
    }

    public String getInAperturaGaveta() {
        return inAperturaGaveta;
    }

    public void setInAperturaGaveta(String inAperturaGaveta) {
        this.inAperturaGaveta = inAperturaGaveta;
    }

    public String getInCreditoConvenioCompania() {
        return inCreditoConvenioCompania;
    }

    public void setInCreditoConvenioCompania(String inCreditoConvenioCompania) {
        this.inCreditoConvenioCompania = inCreditoConvenioCompania;
    }

    public String getInDeficitCaja() {
        return inDeficitCaja;
    }

    public void setInDeficitCaja(String inDeficitCaja) {
        this.inDeficitCaja = inDeficitCaja;
    }

    public String getInDetalle() {
        return inDetalle;
    }

    public void setInDetalle(String inDetalle) {
        this.inDetalle = inDetalle;
    }

    public String getInMuestraCajaElectronica() {
        return inMuestraCajaElectronica;
    }

    public void setInMuestraCajaElectronica(String inMuestraCajaElectronica) {
        this.inMuestraCajaElectronica = inMuestraCajaElectronica;
    }

    public String getInPagoExacto() {
        return inPagoExacto;
    }

    public void setInPagoExacto(String inPagoExacto) {
        this.inPagoExacto = inPagoExacto;
    }

    public String getInSobreHermes() {
        return inSobreHermes;
    }

    public void setInSobreHermes(String inSobreHermes) {
        this.inSobreHermes = inSobreHermes;
    }

    public String getInTarjetaBancaria() {
        return inTarjetaBancaria;
    }

    public void setInTarjetaBancaria(String inTarjetaBancaria) {
        this.inTarjetaBancaria = inTarjetaBancaria;
    }

    public Integer getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(Integer nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getTiTransaccionTarjeta() {
        return tiTransaccionTarjeta;
    }

    public void setTiTransaccionTarjeta(String tiTransaccionTarjeta) {
        this.tiTransaccionTarjeta = tiTransaccionTarjeta;
    }


    
    
    
    public FormaPago(String coCompania, String coFormaPago, Integer nuOrdenFila, String deCortaFormaPago, String deFormaPago, String inPagoExacto, String inTarjetaBancaria, String inDeficitCaja, String inDetalle, String inSobreHermes, String esFormaPago, String coClienteCompania, String tiTransaccionTarjeta, String inCreditoConvenioCompania, String inAperturaGaveta, String inMuestraCajaElectronica, String idCreaFormaPago, Date feCreaFormaPago, String idModFormaPago, Date feModFormaPago) {
        this.coCompania = coCompania;
        this.coFormaPago = coFormaPago;
        this.nuOrdenFila = nuOrdenFila;
        this.deCortaFormaPago = deCortaFormaPago;
        this.deFormaPago = deFormaPago;
        this.inPagoExacto = inPagoExacto;
        this.inTarjetaBancaria = inTarjetaBancaria;
        this.inDeficitCaja = inDeficitCaja;
        this.inDetalle = inDetalle;
        this.inSobreHermes = inSobreHermes;
        this.esFormaPago = esFormaPago;
        this.coClienteCompania = coClienteCompania;
        this.tiTransaccionTarjeta = tiTransaccionTarjeta;
        this.inCreditoConvenioCompania = inCreditoConvenioCompania;
        this.inAperturaGaveta = inAperturaGaveta;
        this.inMuestraCajaElectronica = inMuestraCajaElectronica;
        this.idCreaFormaPago = idCreaFormaPago;
        this.feCreaFormaPago = feCreaFormaPago;
        this.idModFormaPago = idModFormaPago;
        this.feModFormaPago = feModFormaPago;
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
        
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deCortaFormaPago ;        
    }
}
