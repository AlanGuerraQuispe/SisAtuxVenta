package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.TipoPoblacion;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CTipoPoblacion extends JAbstractController{
    private TipoPoblacion tPoblacion;
   
    
    @Override
    public ArrayList<TipoPoblacion> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{TipoPoblacion.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<TipoPoblacion> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<TipoPoblacion> getRegistros() {                 
        ArrayList<TipoPoblacion> rgs = new ArrayList<TipoPoblacion>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(TipoPoblacion.nt, TipoPoblacion.FULL_NOM_CAMPOS, null, null, TipoPoblacion.COLUMNA_ORDER);
            while(rs.next())
            {
                tPoblacion = new TipoPoblacion();
                tPoblacion.setPrimaryKey(new String[]{rs.getString("TI_POBLACION")});
                tPoblacion.setTiPoblacion(rs.getString("TI_POBLACION"));
                tPoblacion.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tPoblacion.setDeCortaPoblacion(rs.getString("DE_CORTA_POBLACION"));
                tPoblacion.setDePoblacion(rs.getString("DE_POBLACION"));
                tPoblacion.setEsTipoPoblacion(rs.getString("ES_TIPO_POBLACION"));
                tPoblacion.setIdCreaTipoPoblacion(rs.getString("ID_CREA_TIPO_POBLACION"));
                tPoblacion.setFeCreaTipoPoblacion(rs.getDate("FE_CREA_TIPO_POBLACION"));
                tPoblacion.setIdModTipoPoblacion(rs.getString("ID_MOD_TIPO_POBLACION"));
                tPoblacion.setFeModTipoPoblacion(rs.getDate("FE_MOD_TIPO_POBLACION"));
                rgs.add(tPoblacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public TipoPoblacion getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(TipoPoblacion.nt, TipoPoblacion.FULL_NOM_CAMPOS, TipoPoblacion.COLUMNA_PK , id);
            while(rs.next())
            {
                tPoblacion = new TipoPoblacion();
                tPoblacion.setPrimaryKey(new String[]{rs.getString("TI_POBLACION")});
                tPoblacion.setTiPoblacion(rs.getString("TI_POBLACION"));
                tPoblacion.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tPoblacion.setDeCortaPoblacion(rs.getString("DE_CORTA_POBLACION"));
                tPoblacion.setDePoblacion(rs.getString("DE_POBLACION"));
                tPoblacion.setEsTipoPoblacion(rs.getString("ES_TIPO_POBLACION"));
                tPoblacion.setIdCreaTipoPoblacion(rs.getString("ID_CREA_TIPO_POBLACION"));
                tPoblacion.setFeCreaTipoPoblacion(rs.getDate("FE_CREA_TIPO_POBLACION"));
                tPoblacion.setIdModTipoPoblacion(rs.getString("ID_MOD_TIPO_POBLACION"));
                tPoblacion.setFeModTipoPoblacion(rs.getDate("FE_MOD_TIPO_POBLACION"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tPoblacion;
    }
    
    @Override
    public ArrayList<TipoPoblacion> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<TipoPoblacion> rgs = new ArrayList<TipoPoblacion>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(TipoPoblacion.nt, TipoPoblacion.COLUMNA_ACTIVO, TipoPoblacion.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(TipoPoblacion.nt, TipoPoblacion.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(TipoPoblacion.nt, campos, columnaId, id, TipoPoblacion.COLUMNA_ORDER);
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
                tPoblacion = new TipoPoblacion();
                tPoblacion.setPrimaryKey(new String[]{rs.getString("TI_POBLACION")});
                tPoblacion.setTiPoblacion(rs.getString("TI_POBLACION"));
                tPoblacion.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tPoblacion.setDeCortaPoblacion(rs.getString("DE_CORTA_POBLACION"));
                tPoblacion.setDePoblacion(rs.getString("DE_POBLACION"));
                tPoblacion.setEsTipoPoblacion(rs.getString("ES_TIPO_POBLACION"));
                tPoblacion.setIdCreaTipoPoblacion(rs.getString("ID_CREA_TIPO_POBLACION"));
                tPoblacion.setFeCreaTipoPoblacion(rs.getDate("FE_CREA_TIPO_POBLACION"));
                tPoblacion.setIdModTipoPoblacion(rs.getString("ID_MOD_TIPO_POBLACION"));
                tPoblacion.setFeModTipoPoblacion(rs.getDate("FE_MOD_TIPO_POBLACION"));
                rgs.add(tPoblacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getTipoPoblacion()
     {
        ArrayList<TipoPoblacion> rgs = new ArrayList<TipoPoblacion>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT *  ");            
            query.append("  FROM CMTR_TIPO_POBLACION ");
            query.append(" WHERE ES_TIPO_POBLACION ='A' ");
            query.append(" ORDER BY DE_POBLACION");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                tPoblacion = new TipoPoblacion();
                tPoblacion.setPrimaryKey(new String[]{rs.getString("TI_POBLACION")});
                tPoblacion.setTiPoblacion(rs.getString("TI_POBLACION"));
                tPoblacion.setNuOrdenFila(rs.getInt("NU_ORDEN_FILA"));
                tPoblacion.setDeCortaPoblacion(rs.getString("DE_CORTA_POBLACION"));
                tPoblacion.setDePoblacion(rs.getString("DE_POBLACION"));
                tPoblacion.setEsTipoPoblacion(rs.getString("ES_TIPO_POBLACION"));
                tPoblacion.setIdCreaTipoPoblacion(rs.getString("ID_CREA_TIPO_POBLACION"));
                tPoblacion.setFeCreaTipoPoblacion(rs.getDate("FE_CREA_TIPO_POBLACION"));
                tPoblacion.setIdModTipoPoblacion(rs.getString("ID_MOD_TIPO_POBLACION"));
                tPoblacion.setFeModTipoPoblacion(rs.getDate("FE_MOD_TIPO_POBLACION"));
                rgs.add(tPoblacion);
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
        tPoblacion = (TipoPoblacion)mdl;
        int gravado = 0;
        String campos = TipoPoblacion.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tPoblacion.getTiPoblacion(),
                            tPoblacion.getNuOrdenFila(),
                            tPoblacion.getDeCortaPoblacion(),
                            tPoblacion.getDePoblacion(),
                            tPoblacion.getEsTipoPoblacion(),
                            tPoblacion.getIdCreaTipoPoblacion(),
                            tPoblacion.getFeCreaTipoPoblacion(),
                            tPoblacion.getIdModTipoPoblacion(),
                            tPoblacion.getFeModTipoPoblacion()
                           };
       
           gravado = this.agregarRegistroPs(tPoblacion.getNombreTabla(),TipoPoblacion.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        tPoblacion = (TipoPoblacion)mdl;
        int gravado = 0;        
        
        Object[] valores = {tPoblacion.getNuOrdenFila(),
                            tPoblacion.getDeCortaPoblacion(),
                            tPoblacion.getDePoblacion(),
                            tPoblacion.getEsTipoPoblacion(),
                            tPoblacion.getIdCreaTipoPoblacion(),
                            tPoblacion.getFeCreaTipoPoblacion(),
                            tPoblacion.getIdModTipoPoblacion(),
                            tPoblacion.getFeModTipoPoblacion()
                           };

        gravado = this.actualizarRegistroPs(tPoblacion.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(TipoPoblacion.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(TipoPoblacion.COLUMNA_PK, tPoblacion.getPrimaryKey()) , valores);
        return gravado;
    }

    public TipoPoblacion getTipodeDocumento() {
        if(tPoblacion == null)
        {
            tPoblacion = new TipoPoblacion();
        }
        return tPoblacion;
    }

    public void setTipodeDocumento(JAbstractModelBD prv) {
        this.tPoblacion = (TipoPoblacion)prv;
    }


}
