package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.TipoDocumento;
import atux.util.common.AtuxDBUtility;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CTipoDocumento extends JAbstractController{
    private TipoDocumento tDocumento;
   
    
    @Override
    public ArrayList<TipoDocumento> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{TipoDocumento.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<TipoDocumento> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<TipoDocumento> getRegistros() {                 
        ArrayList<TipoDocumento> rgs = new ArrayList<TipoDocumento>();
        try {                        
//            rs = this.getRegistros(TipoDeCambio.nt,TipoDeCambio.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(TipoDocumento.nt, TipoDocumento.FULL_NOM_CAMPOS, null, null, TipoDocumento.COLUMNA_ORDER);
            while(rs.next())
            {
                tDocumento = new TipoDocumento();
                tDocumento.setPrimaryKey(new String[]{rs.getString("CO_DOCUMENTO_IDENTIDAD")});    
                tDocumento.setCoDocumentoIdentidad(rs.getString("CO_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeDocumentoIdentidad(rs.getString("DE_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeAbrDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                tDocumento.setNuCaracteres(rs.getString("NU_CARACTERES"));
                tDocumento.setEsDocumentoIdentidad(rs.getString("ES_DOCUMENTO_IDENTIDAD"));
                rgs.add(tDocumento);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;    
    }
    
    public TipoDocumento getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(TipoDocumento.nt, TipoDocumento.FULL_NOM_CAMPOS, TipoDocumento.COLUMNA_PK , id);
            while(rs.next())
            {
                tDocumento = new TipoDocumento();
                tDocumento.setPrimaryKey(new String[]{rs.getString("CO_DOCUMENTO_IDENTIDAD")});    
                tDocumento.setCoDocumentoIdentidad(rs.getString("CO_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeDocumentoIdentidad(rs.getString("DE_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeAbrDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                tDocumento.setNuCaracteres(rs.getString("NU_CARACTERES"));
                tDocumento.setEsDocumentoIdentidad(rs.getString("ES_DOCUMENTO_IDENTIDAD"));
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return tDocumento;
    }
    
    @Override
    public ArrayList<TipoDocumento> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<TipoDocumento> rgs = new ArrayList<TipoDocumento>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(TipoDocumento.nt, TipoDocumento.COLUMNA_ACTIVO, TipoDocumento.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(TipoDocumento.nt, TipoDocumento.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(TipoDocumento.nt, campos, columnaId, id, TipoDocumento.COLUMNA_ORDER);
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
                tDocumento = new TipoDocumento();
                tDocumento.setPrimaryKey(new String[]{rs.getString("CO_DOCUMENTO_IDENTIDAD")});    
                tDocumento.setCoDocumentoIdentidad(rs.getString("CO_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeDocumentoIdentidad(rs.getString("DE_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeAbrDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                tDocumento.setNuCaracteres(rs.getString("NU_CARACTERES"));
                tDocumento.setEsDocumentoIdentidad(rs.getString("ES_DOCUMENTO_IDENTIDAD"));
                rgs.add(tDocumento);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
        
    }

    public ArrayList getTipoDocumento()
     {
        ArrayList<TipoDocumento> rgs = new ArrayList<TipoDocumento>();
        try {                        
            StringBuffer  query = new StringBuffer();

            query.append("SELECT CO_DOCUMENTO_IDENTIDAD, ");
            query.append("       DE_DOCUMENTO_IDENTIDAD, ");
            query.append("       DE_ABR_DOCUMENTO      , ");
            query.append("       NU_CARACTERES         , ");
            query.append("       ES_DOCUMENTO_IDENTIDAD  ");            
            query.append("  FROM CMTM_DOCUMENTO_IDENTIDAD ");
            query.append(" WHERE ES_DOCUMENTO_IDENTIDAD ='A' ");
            query.append(" ORDER BY CO_DOCUMENTO_IDENTIDAD");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                tDocumento = new TipoDocumento();
                tDocumento.setPrimaryKey(new String[]{rs.getString("CO_DOCUMENTO_IDENTIDAD")});
                tDocumento.setCoDocumentoIdentidad(rs.getString("CO_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeDocumentoIdentidad(rs.getString("DE_DOCUMENTO_IDENTIDAD"));
                tDocumento.setDeAbrDocumento(rs.getString("DE_ABR_DOCUMENTO"));
                tDocumento.setNuCaracteres(rs.getString("NU_CARACTERES"));
                tDocumento.setEsDocumentoIdentidad(rs.getString("ES_DOCUMENTO_IDENTIDAD"));
                rgs.add(tDocumento);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
        // Se cierran los recursos de base de datos.
        try {
        if (rs != null) {
            rs.close();
            }
        } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
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
        tDocumento = (TipoDocumento)mdl;
        int gravado = 0;
        String campos = TipoDocumento.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {tDocumento.getCoDocumentoIdentidad(),
                            tDocumento.getDeDocumentoIdentidad(),
                            tDocumento.getDeAbrDocumento(),
                            tDocumento.getNuCaracteres(),
                            tDocumento.getEsDocumentoIdentidad()
                           };
       
           gravado = this.agregarRegistroPs(tDocumento.getNombreTabla(),TipoDocumento.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        tDocumento = (TipoDocumento)mdl;
        int gravado = 0;        
        
        Object[] valores = {tDocumento.getDeDocumentoIdentidad(),
                            tDocumento.getDeAbrDocumento(),
                            tDocumento.getNuCaracteres(),
                            tDocumento.getEsDocumentoIdentidad()
                           };

        gravado = this.actualizarRegistroPs(tDocumento.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(TipoDocumento.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(TipoDocumento.COLUMNA_PK, tDocumento.getPrimaryKey()) , valores);
        return gravado;
    }

    public TipoDocumento getTipodeDocumento() {
        if(tDocumento == null)
        {
            tDocumento = new TipoDocumento();
        }
        return tDocumento;
    }

    public void setTipodeDocumento(JAbstractModelBD prv) {
        this.tDocumento = (TipoDocumento)prv;
    }

    public String getNuevoCodigo(){
        String Codigo="";
        try {
            return AtuxDBUtility.getValueAt(TipoDocumento.nt,"rtrim(ltrim(to_char(max(CO_DOCUMENTO_IDENTIDAD) + 1,'00')))"," CO_DOCUMENTO_IDENTIDAD is not null");
        } catch (SQLException ex) {
            Logger.getLogger(CTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Codigo.trim();
    }
}
