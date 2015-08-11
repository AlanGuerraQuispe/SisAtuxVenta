package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class ObsCaja extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;
   
    public static final String nt = "VTTV_MOVIMIENTO_CAJA_OBS";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","FE_DIA_VENTA","NU_SEC_OBS","NU_CAJA","NU_TURNO"};
    public static final String COLUMNA_ACTIVO = "";
    
    
    public static final String[]
        FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, FE_DIA_VENTA, NU_CAJA, NU_TURNO, NU_SEC_OBS, DE_OBS, ID_CREA_OBS, FE_CREA_OBS"};


    private String  coCompania;
    private String  coLocal;
    private Date    feDiaVenta;
    private Integer nuCaja;
    private Integer nuTurno;
    private Integer nuSecObs;
    private String  deObs;
    private String  idCreaObs;
    private Date    feCreaObs;
    

    private ArrayList<DetallePedidoVenta> dp;
    
    //Para comprobante manual
    private String nuComprobanteManual;
    
     public ObsCaja() {
         initBasic();
    }

    public ObsCaja(String coCompania, String coLocal, Date feDiaVenta, Integer nuCaja, Integer nuTurno, Integer nuSecObs, String deObs, String idCreaObs, Date feCreaObs){
        this.coCompania = coCompania;
        this.coLocal=coLocal;
        this.feDiaVenta=feDiaVenta;
        this.nuCaja=nuCaja;
        this.nuTurno=nuTurno;
        this.nuSecObs=nuSecObs;
        this.deObs=deObs;
        this.idCreaObs=idCreaObs;
        this.feCreaObs=feCreaObs;
        initBasic();
    }         
    
    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

      public String getcoCompania() {
        return coCompania;
    }

    public String getcoLocal() {
        return coLocal;
    }

    public Date getfeDiaVenta() {
        return feDiaVenta;
    }

    public Integer getnuCaja() {
        return nuCaja;
    }

    public Integer getnuTurno() {
        return nuTurno;
    }

    public Integer getnuSecObs() {
        return nuSecObs;
    }

    public String getdeObs() {
        return deObs;
    }

    public String getidCreaObs() {
        return idCreaObs;
    }

    public Date getfeCreaObs() {
        return feCreaObs;
    }

    public void setcoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public void setcoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public void setfeDiaVenta(Date feDiaVenta) {
        this.feDiaVenta = feDiaVenta;
    }

    public void setnuCaja(Integer nuCaja) {
        this.nuCaja = nuCaja;
    }

    public void setnuTurno(Integer nuTurno) {
        this.nuTurno = nuTurno;
    }

    public void setnuSecObs(Integer nuSecObs) {
        this.nuSecObs = nuSecObs;
    }

    public void setdeObs(String deObs) {
        this.deObs = deObs;
    }

    public void setidCreaObs(String idCreaObs) {
        this.idCreaObs = idCreaObs;
    }

    public void setfeCreaObs(Date feCreaObs) {
        this.feCreaObs = feCreaObs;
    }

    public String getNuComprobanteManual() {
        return nuComprobanteManual;
    }

    public void setNuComprobanteManual(String nuComprobanteManual) {
        this.nuComprobanteManual = nuComprobanteManual;
    }        

    @Override
    public int hashCode() {
        int hash = 3;
//        hash = 89 * hash + (this.nuPedido != null ? this.nuPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObsCaja other = (ObsCaja) obj;
//        if (this.nuPedido != other.nuPedido && (this.nuPedido == null || !this.nuPedido.equals(other.nuPedido))) {
//            return false;
//        }
        return true;
    }

}
