package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class MedioControlAcceso extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "CMTV_MEDIO_CONTROL_ACCESO";
    public static final String[] COLUMNA_PK ={"de_Direccion_MAC"};
    public static final String COLUMNA_ACTIVO = "de_Direccion_MAC";
    public static final String[] COLUMNA_ORDER ={"de_Direccion_MAC"};
    
    private int nuPc;
    private String deDireccionMAC;
    private String deCodigoHDD;
    private Date feCreaReg;

    public static final String[] 
      FULL_NOM_CAMPOS ={"nu_Pc,de_Direccion_MAC,de_Codigo_HDD,FE_CREA_REG"};

    public String getDeCodigoHDD() {
        return deCodigoHDD;
    }

    public void setDeCodigoHDD(String deCodigoHDD) {
        this.deCodigoHDD = deCodigoHDD;
    }

    public String getDeDireccionMAC() {
        return deDireccionMAC;
    }

    public void setDeDireccionMAC(String deDireccionMAC) {
        this.deDireccionMAC = deDireccionMAC;
    }

    public int getNuPc() {
        return nuPc;
    }

    public void setNuPc(int nuPc) {
        this.nuPc = nuPc;
    }

    public Date getFeCreaReg() {
        return feCreaReg;
    }

    public void setFeCreaReg(Date feCreaReg) {
        this.feCreaReg = feCreaReg;
    }


    public MedioControlAcceso() {
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
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedioControlAcceso)) {
            return false;
        }
        MedioControlAcceso other = (MedioControlAcceso) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }              
}