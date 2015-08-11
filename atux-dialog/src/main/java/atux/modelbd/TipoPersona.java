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
public class TipoPersona extends JAbstractModelBD implements Serializable,IModel{    
    private static final long serialVersionUID = 1L; 
    
    public static final String nt = "VTTR_TIPO_CLIENTE";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","TI_CLIENTE"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_CLIENTE";
    
    private String coCompania;
    private String tiCliente;
    private String deCortaTipoCliente;
    private String deTipoCliente;
    private String tiPantalla;
    private String esTipoCliente;
    private String idCreaTipoCliente;
    private Date feCreaTipoCliente;
    
    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA,TI_CLIENTE,DE_CORTA_TIPO_CLIENTE,DE_TIPO_CLIENTE,TI_PANTALLA,"+
                            "ES_TIPO_CLIENTE,ID_CREA_TIPO_CLIENTE,FE_CREA_TIPO_CLIENTE"};
    
    

    public TipoPersona() {
        initBasic();
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getDeCortaTipoCliente() {
        return deCortaTipoCliente;
    }

    public void setDeCortaTipoCliente(String deCortaTipoCliente) {
        this.deCortaTipoCliente = deCortaTipoCliente;
    }

    public String getDeTipoCliente() {
        return deTipoCliente;
    }

    public void setDeTipoCliente(String deTipoCliente) {
        this.deTipoCliente = deTipoCliente;
    }

    public String getEsTipoCliente() {
        return esTipoCliente;
    }

    public void setEsTipoCliente(String esTipoCliente) {
        this.esTipoCliente = esTipoCliente;
    }

    public Date getFeCreaTipoCliente() {
        return feCreaTipoCliente;
    }

    public void setFeCreaTipoCliente(Date feCreaTipoCliente) {
        this.feCreaTipoCliente = feCreaTipoCliente;
    }

    public String getIdCreaTipoCliente() {
        return idCreaTipoCliente;
    }

    public void setIdCreaTipoCliente(String idCreaTipoCliente) {
        this.idCreaTipoCliente = idCreaTipoCliente;
    }

    public String getTiCliente() {
        return tiCliente;
    }

    public void setTiCliente(String tiCliente) {
        this.tiCliente = tiCliente;
    }

    public String getTiPantalla() {
        return tiPantalla;
    }

    public void setTiPantalla(String tiPantalla) {
        this.tiPantalla = tiPantalla;
    }

    public TipoPersona(String coCompania, String tiCliente, String deCortaTipoCliente, String deTipoCliente, String tiPantalla, String esTipoCliente, String idCreaTipoCliente, Date feCreaTipoCliente) {
        this.coCompania = coCompania;
        this.tiCliente = tiCliente;
        this.deCortaTipoCliente = deCortaTipoCliente;
        this.deTipoCliente = deTipoCliente;
        this.tiPantalla = tiPantalla;
        this.esTipoCliente = esTipoCliente;
        this.idCreaTipoCliente = idCreaTipoCliente;
        this.feCreaTipoCliente = feCreaTipoCliente;
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
        
        if (!(object instanceof TipoPersona)) {
            return false;
        }
        TipoPersona other = (TipoPersona) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deCortaTipoCliente ;        
    } 
}