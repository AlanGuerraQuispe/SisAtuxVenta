package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.TipoVia;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CTipoVia extends JAbstractController{
    private TipoVia tVia;
   
    
    @Override
    public ArrayList<TipoVia> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{TipoVia.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<TipoVia> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<TipoVia> getRegistros() {                 
        ArrayList<TipoVia> rgs = new ArrayList<TipoVia>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(TipoVia.nt, TipoVia.FULL_NOM_CAMPOS, null, null, TipoVia.COLUMNA_ORDER);
            while(rs.next())
            {
                tVia = new TipoVia();
                tVia.setPrimaryKey(new String[]{rs.getString("TI_VIA")});
                tVia.setTiVia(rs.getString("TI_VIA"));
                tVia.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tVia.setDeCortaTipoVia(rs.getString("DE_CORTA_TIPO_VIA"));
                tVia.setDeTipoVia(rs.getString("DE_TIPO_VIA"));
                tVia.setEsTipoVia(rs.getString("ES_TIPO_VIA"));
                tVia.setIdCreaTipoVia(rs.getString("ID_CREA_TIPO_VIA"));
                tVia.setFeCreaTipoVia(rs.getDate("FE_CREA_TIPO_VIA"));
                tVia.setIdModTipoVia(rs.getString("ID_MOD_TIPO_VIA"));
                tVia.setFeModTipoVia(rs.getDate("FE_MOD_TIPO_VIA"));
                rgs.add(tVia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public TipoVia getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(TipoVia.nt, TipoVia.FULL_NOM_CAMPOS, TipoVia.COLUMNA_PK , id);
            while(rs.next())
            {
                tVia = new TipoVia();
                tVia.setPrimaryKey(new String[]{rs.getString("TI_VIA")});
                tVia.setTiVia(rs.getString("TI_VIA"));
                tVia.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tVia.setDeCortaTipoVia(rs.getString("DE_CORTA_TIPO_VIA"));
                tVia.setDeTipoVia(rs.getString("DE_TIPO_VIA"));
                tVia.setEsTipoVia(rs.getString("ES_TIPO_VIA"));
                tVia.setIdCreaTipoVia(rs.getString("ID_CREA_TIPO_VIA"));
                tVia.setFeCreaTipoVia(rs.getDate("FE_CREA_TIPO_VIA"));
                tVia.setIdModTipoVia(rs.getString("ID_MOD_TIPO_VIA"));
                tVia.setFeModTipoVia(rs.getDate("FE_MOD_TIPO_VIA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tVia;
    }
    
    @Override
    public ArrayList<TipoVia> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<TipoVia> rgs = new ArrayList<TipoVia>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(TipoVia.nt, TipoVia.COLUMNA_ACTIVO, TipoVia.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(TipoVia.nt, TipoVia.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(TipoVia.nt, campos, columnaId, id, TipoVia.COLUMNA_ORDER);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next())
            {
                tVia = new TipoVia();
                tVia.setPrimaryKey(new String[]{rs.getString("TI_VIA")});
                tVia.setTiVia(rs.getString("TI_VIA"));
                tVia.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tVia.setDeCortaTipoVia(rs.getString("DE_CORTA_TIPO_VIA"));
                tVia.setDeTipoVia(rs.getString("DE_TIPO_VIA"));
                tVia.setEsTipoVia(rs.getString("ES_TIPO_VIA"));
                tVia.setIdCreaTipoVia(rs.getString("ID_CREA_TIPO_VIA"));
                tVia.setFeCreaTipoVia(rs.getDate("FE_CREA_TIPO_VIA"));
                tVia.setIdModTipoVia(rs.getString("ID_MOD_TIPO_VIA"));
                tVia.setFeModTipoVia(rs.getDate("FE_MOD_TIPO_VIA"));
                rgs.add(tVia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getTipoVia()
     {
        ArrayList<TipoVia> rgs = new ArrayList<TipoVia>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_VIA ");
            query.append(" WHERE ES_TIPO_VIA ='A' ");
            query.append(" ORDER BY DE_TIPO_VIA");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tVia = new TipoVia();
                tVia.setPrimaryKey(new String[]{rs.getString("TI_VIA")});
                tVia.setTiVia(rs.getString("TI_VIA"));
                tVia.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tVia.setDeCortaTipoVia(rs.getString("DE_CORTA_TIPO_VIA"));
                tVia.setDeTipoVia(rs.getString("DE_TIPO_VIA"));
                tVia.setEsTipoVia(rs.getString("ES_TIPO_VIA"));
                tVia.setIdCreaTipoVia(rs.getString("ID_CREA_TIPO_VIA"));
                tVia.setFeCreaTipoVia(rs.getDate("FE_CREA_TIPO_VIA"));
                tVia.setIdModTipoVia(rs.getString("ID_MOD_TIPO_VIA"));
                tVia.setFeModTipoVia(rs.getDate("FE_MOD_TIPO_VIA"));
                rgs.add(tVia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
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
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        tVia = (TipoVia)mdl;
        int gravado = 0;
        String campos = TipoVia.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tVia.getTiVia(),
                            tVia.getNuOrdenFila(),
                            tVia.getDeCortaTipoVia(),
                            tVia.getDeTipoVia(),
                            tVia.getEsTipoVia(),
                            tVia.getIdCreaTipoVia(),
                            tVia.getFeCreaTipoVia(),
                            tVia.getIdModTipoVia(),
                            tVia.getFeModTipoVia()
                           };
       
           gravado = this.agregarRegistroPs(tVia.getNombreTabla(),TipoVia.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        tVia = (TipoVia)mdl;
        int gravado = 0;        
        
        Object[] valores = {tVia.getNuOrdenFila(),
                            tVia.getDeCortaTipoVia(),
                            tVia.getDeTipoVia(),
                            tVia.getEsTipoVia(),
                            tVia.getIdCreaTipoVia(),
                            tVia.getFeCreaTipoVia(),
                            tVia.getIdModTipoVia(),
                            tVia.getFeModTipoVia()
                           };

        gravado = this.actualizarRegistroPs(tVia.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(TipoVia.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(TipoVia.COLUMNA_PK, tVia.getPrimaryKey()) , valores);
        return gravado;
    }

    public TipoVia getTipodeDocumento() {
        if(tVia == null)
        {
            tVia = new TipoVia();
        }
        return tVia;
    }

    public void setTipodeDocumento(JAbstractModelBD prv) {
        this.tVia = (TipoVia)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(TipoVia.nt,"rtrim(ltrim(to_char(max(TI_VIA) + 1,'000')))"," TI_VIA is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CTipoVia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
