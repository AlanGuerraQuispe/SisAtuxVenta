package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.CodigoBarra;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aguerra
 */
public class CCodigoBarra extends JAbstractController{
    private CodigoBarra cBarra;
   
    
    @Override
    public ArrayList<CodigoBarra> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{CodigoBarra.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<CodigoBarra> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<CodigoBarra> getRegistros() {                 
        ArrayList<CodigoBarra> rgs = new ArrayList<CodigoBarra>();
        try {                        
//            rs = this.getRegistros(CodigoBarra.nt,CodigoBarra.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(CodigoBarra.nt, CodigoBarra.FULL_NOM_CAMPOS, null, null, CodigoBarra.COLUMNA_ORDER);
            while(rs.next())
            {
                cBarra = new CodigoBarra();
                cBarra.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_BARRA")});    
                cBarra.setCoCompania(rs.getString("CO_COMPANIA"));
                cBarra.setCoProducto(rs.getString("CO_PRODUCTO"));
                cBarra.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                cBarra.setCoBarra(rs.getString("CO_BARRA"));
                cBarra.setEsProdCodBarra(rs.getString("ES_PROD_COD_BARRA"));
                cBarra.setIdCreaProdCodBarra(rs.getString("ID_CREA_PROD_COD_BARRA"));
                cBarra.setFeCreaProdCodBarra(rs.getDate("FE_CREA_PROD_COD_BARRA"));
                cBarra.setIdModProdCodBarra(rs.getString("ID_MOD_PROD_COD_BARRA"));
                cBarra.setFeModProdCodBarra(rs.getDate("FE_MOD_PROD_COD_BARRA"));
                rgs.add(cBarra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public CodigoBarra getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(CodigoBarra.nt, CodigoBarra.FULL_NOM_CAMPOS, CodigoBarra.COLUMNA_PK , id);
            while(rs.next())
            {
                cBarra.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_BARRA")});    
                cBarra.setCoCompania(rs.getString("CO_COMPANIA"));
                cBarra.setCoProducto(rs.getString("CO_PRODUCTO"));
                cBarra.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                cBarra.setCoBarra(rs.getString("CO_BARRA"));
                cBarra.setEsProdCodBarra(rs.getString("ES_PROD_COD_BARRA"));
                cBarra.setIdCreaProdCodBarra(rs.getString("ID_CREA_PROD_COD_BARRA"));
                cBarra.setFeCreaProdCodBarra(rs.getDate("FE_CREA_PROD_COD_BARRA"));
                cBarra.setIdModProdCodBarra(rs.getString("ID_MOD_PROD_COD_BARRA"));
                cBarra.setFeModProdCodBarra(rs.getDate("FE_MOD_PROD_COD_BARRA"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cBarra;
    }
    
    @Override
    public ArrayList<CodigoBarra> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<CodigoBarra> rgs = new ArrayList<CodigoBarra>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(CodigoBarra.nt, CodigoBarra.COLUMNA_ACTIVO, CodigoBarra.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(CodigoBarra.nt, CodigoBarra.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(CodigoBarra.nt, campos, columnaId, id, CodigoBarra.COLUMNA_ORDER);
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
                cBarra= new CodigoBarra();
                cBarra.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_BARRA")});    
                cBarra.setCoCompania(rs.getString("CO_COMPANIA"));
                cBarra.setCoProducto(rs.getString("CO_PRODUCTO"));
                cBarra.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                cBarra.setCoBarra(rs.getString("CO_BARRA"));
                cBarra.setEsProdCodBarra(rs.getString("ES_PROD_COD_BARRA"));
                cBarra.setIdCreaProdCodBarra(rs.getString("ID_CREA_PROD_COD_BARRA"));
                cBarra.setFeCreaProdCodBarra(rs.getDate("FE_CREA_PROD_COD_BARRA"));
                cBarra.setIdModProdCodBarra(rs.getString("ID_MOD_PROD_COD_BARRA"));
                cBarra.setFeModProdCodBarra(rs.getDate("FE_MOD_PROD_COD_BARRA"));
                rgs.add(cBarra);
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
        cBarra = (CodigoBarra)mdl;
        int gravado = 0;
        String campos = CodigoBarra.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {cBarra.getCoCompania(),
                            cBarra.getCoProducto(),
                            cBarra.getNuRevisionProducto(),
                            cBarra.getCoBarra(),
                            cBarra.getEsProdCodBarra(),
                            cBarra.getIdCreaProdCodBarra(),
                            cBarra.getFeCreaProdCodBarra(),
                            cBarra.getIdModProdCodBarra(),
                            cBarra.getFeModProdCodBarra()
                           };
       
           gravado = this.agregarRegistroPs(cBarra.getNombreTabla(),CodigoBarra.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    public boolean eliminarRegistro(JAbstractModelBD mdl) throws SQLException {
        int gravado = 0;
        cBarra = (CodigoBarra)mdl;
        gravado = this.eliminacionReal(cBarra);

        if(gravado==1){
            return true;
        }
        return false;
    }
    
    
    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        cBarra = (CodigoBarra)mdl;
        int gravado = 0;        
        
        Object[] valores = {cBarra.getEsProdCodBarra(),
                            cBarra.getIdCreaProdCodBarra(),
                            cBarra.getFeCreaProdCodBarra(),
                            cBarra.getIdModProdCodBarra(),
                            cBarra.getFeModProdCodBarra()
                           };

        gravado = this.actualizarRegistroPs(cBarra.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(CodigoBarra.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(CodigoBarra.COLUMNA_PK, cBarra.getPrimaryKey()) , valores);
        return gravado;
    }

    public CodigoBarra getCodigoBarra() {
        if(cBarra == null)
        {
            cBarra = new CodigoBarra();
        }
        return cBarra;
    }

    public void setCodigoBarra(JAbstractModelBD prv) {
        this.cBarra = (CodigoBarra)prv;
    }
}
