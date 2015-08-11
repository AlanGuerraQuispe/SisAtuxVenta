/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ObsCaja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Omar Davila
 */


public class CObsCaja extends JAbstractController{
    private static final long serialVersionUID = 1L; 
    private static ArrayList parametros = new ArrayList();

    public static final String nt = "VTTV_MOVIMIENTO_CAJA_OBS";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","FE_DIA_VENTA","NU_SEC_OBS","NU_CAJA","NU_TURNO"};
    
    
    private String  coCompania;
    private String  coLocal;
    private Date    feDiaVenta;
    private Integer nuCaja;
    private Integer nuTurno;
    private Integer nuSecObs;
    private String  deObs;
    private String  idCreaObs;
    private Date    feCreaObs;

    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA, CO_LOCAL, FE_DIA_VENTA, NU_CAJA, NU_TURNO, NU_SEC_OBS, DE_OBS, ID_CREA_OBS, FE_CREA_OBS"};

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

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        
        ObsCaja usr = (ObsCaja)mdl;

        String[] campos = FULL_NOM_CAMPOS;
        
        Object[] valores = {usr.getcoCompania(),
                            usr.getcoLocal(),
                            usr.getfeDiaVenta(),
                            usr.getnuCaja(),
                            usr.getnuTurno(),
                            usr.getnuSecObs(),
                            usr.getdeObs(),
                            usr.getidCreaObs(),
                            usr.getfeDiaVenta()};
       
        int gravado = this.agregarRegistroPs(nt, campos, valores);
       
        if(gravado==1)
        {    
            return true;
        }
        return false;
    }    

    public CObsCaja getRegistroPorPk(Object[] id){
        CObsCaja usr = null;
        try {
            rs =  this.selectPorPk(CObsCaja.nt,CObsCaja.FULL_NOM_CAMPOS, CObsCaja.COLUMNA_PK, id);
            while(rs.next())
            {
                usr = new CObsCaja();
                usr.setcoCompania(rs.getString(1));
                usr.setcoLocal(rs.getString(2));
                usr.setfeDiaVenta(rs.getDate(3));
                usr.setnuCaja(rs.getInt(4));
                usr.setnuTurno(rs.getInt(5));
                usr.setnuSecObs(rs.getInt(6));
                usr.setdeObs(rs.getString(7));
                usr.setidCreaObs(rs.getString(8));
                usr.setfeDiaVenta(rs.getDate(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }    
    
    public ArrayList LeerObservaciones(String vCodigoCompania, String vCodigoLocal, String vFecha, int vNumeroCaja, int vNumeroTurno){
        ArrayList<ObsCaja> rgs = new ArrayList<ObsCaja>();
        ObsCaja      Observaciones      = null;
        StringBuffer  query;

        query = new StringBuffer();
        query.append("SELECT CO_COMPANIA, CO_LOCAL, FE_DIA_VENTA, NU_SEC_OBS, NU_CAJA, NU_TURNO, DE_OBS");
        query.append("  FROM VTTV_MOVIMIENTO_CAJA_OBS ");
        query.append(" where co_compania = '").append(vCodigoCompania).append("' ");
        query.append("   and co_local    = '").append(vCodigoLocal).append("' ");
        query.append("   and fe_dia_venta  = '").append(vFecha).append("' ");
        query.append("   and Nu_Caja  = ").append(vNumeroCaja);
        query.append("   and Nu_Turno  = ").append(vNumeroTurno);
        query.append(" order by NU_SEC_OBS ");

        try {            
            rs =  this.getRegistrosSinFiltro(query);
            while(rs.next())
            {
                Observaciones = new ObsCaja();
                Observaciones.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                Observaciones.setcoCompania(rs.getString("CO_COMPANIA"));
                Observaciones.setcoLocal(rs.getString("CO_LOCAL"));
                Observaciones.setfeDiaVenta(rs.getDate("FE_DIA_VENTA"));
                Observaciones.setnuSecObs(rs.getInt("NU_SEC_OBS"));
                Observaciones.setnuCaja(rs.getInt("NU_CAJA"));
                Observaciones.setnuTurno(rs.getInt("NU_TURNO"));
                Observaciones.setdeObs(rs.getString("DE_OBS"));
                rgs.add(Observaciones);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CObsCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rgs;
    }
    
    public String EliminaTodasObservaciones(String vCodigoCompania, String vCodigoLocal, String vFecha, int vNumeroCaja, int vNumeroTurno){
        StringBuffer  query;

        query = new StringBuffer();
        query.append("DELETE VTTV_MOVIMIENTO_CAJA_OBS ");
        query.append(" where co_compania = '").append(vCodigoCompania).append("' ");
        query.append("   and co_local    = '").append(vCodigoLocal).append("' ");
        query.append("   and fe_dia_venta  = '").append(vFecha).append("' ");
        query.append("   and Nu_Caja  = ").append(vNumeroCaja);
        query.append("   and Nu_Turno  = ").append(vNumeroTurno);

        try {            
            rs =  this.getRegistrosSinFiltro(query);
        } catch (SQLException ex) {
            Logger.getLogger(CObsCaja.class.getName()).log(Level.SEVERE, null, ex);
            return "NoOk";
        }
        return "OK";
    }
    
    @Override
    public String[] stringToArray(String cad,String separador){
        return cad.split(separador);
    }

    @Override
    public String generarArrayAString(String[] campos)
    {
        String cad = "* ";
        if(campos != null)
        {
            if(campos.length > 0)
            {
                cad = ""; 
                if(campos.length == 1)
                {
                    return cad+campos[0];
                }
                for(int i=0;i<campos.length;i++)
                {
                    cad +=(i==0?" ":", ")+campos[i];
                }           
            }
        }
        return cad;
    }

    @Override
    public ArrayList getRegistros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
