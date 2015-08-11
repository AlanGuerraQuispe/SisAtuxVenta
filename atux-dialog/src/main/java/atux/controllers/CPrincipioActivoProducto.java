package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.PrincipioActivoProducto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CPrincipioActivoProducto extends JAbstractController{
    private PrincipioActivoProducto prodPrincipio;
   
    
    @Override
    public ArrayList<PrincipioActivoProducto> getRegistros(Object[] op) {        
        return this.getRegistros(new String[]{}, op!=null?new String[]{PrincipioActivoProducto.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<PrincipioActivoProducto> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<PrincipioActivoProducto> getRegistros() {                 
        ArrayList<PrincipioActivoProducto> rgs = new ArrayList<PrincipioActivoProducto>();
        try {                        
//            rs = this.getRegistros(PrincipioActivoProducto.nt,PrincipioActivoProducto.FULL_NOM_CAMPOS);                       
            rs = this.getRegistros(PrincipioActivoProducto.nt, PrincipioActivoProducto.FULL_NOM_CAMPOS, null, null, PrincipioActivoProducto.COLUMNA_ORDER);
            while(rs.next())
            {
                prodPrincipio = new PrincipioActivoProducto();
                prodPrincipio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRINCIPIO_ACTIVO")});
                prodPrincipio.setCoCompania(rs.getString("CO_COMPANIA"));
                prodPrincipio.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodPrincipio.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
//                prodPrincipio.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
//                prodPrincipio.setInPrincipioActivoPrincipal(rs.getString("IN_PRINCIPIO_ACTIVO_PRINCIPAL"));
//                prodPrincipio.setDeObservacion(rs.getString("DE_OBSERVACION"));
//                prodPrincipio.setEsProductoPrincipio(rs.getString("ES_PRODUCTO_PRINCIPIO"));
//                prodPrincipio.setIdCreaProductoServicio(rs.getString("ID_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeCreaProductoServicio(rs.getDate("FE_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setIdModProductoServicio(rs.getString("ID_MOD_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeModProductoServicio(rs.getDate("FE_MOD_PRODUCTO_SERVICIO"));
                rgs.add(prodPrincipio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public PrincipioActivoProducto getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(PrincipioActivoProducto.nt, PrincipioActivoProducto.FULL_NOM_CAMPOS, PrincipioActivoProducto.COLUMNA_PK , id);
            while(rs.next())
            {
                prodPrincipio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRINCIPIO_ACTIVO")});
                prodPrincipio.setCoCompania(rs.getString("CO_COMPANIA"));
                prodPrincipio.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodPrincipio.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
//                prodPrincipio.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
//                prodPrincipio.setInPrincipioActivoPrincipal(rs.getString("IN_PRINCIPIO_ACTIVO_PRINCIPAL"));
//                prodPrincipio.setDeObservacion(rs.getString("DE_OBSERVACION"));
//                prodPrincipio.setEsProductoPrincipio(rs.getString("ES_PRODUCTO_PRINCIPIO"));
//                prodPrincipio.setIdCreaProductoServicio(rs.getString("ID_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeCreaProductoServicio(rs.getDate("FE_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setIdModProductoServicio(rs.getString("ID_MOD_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeModProductoServicio(rs.getDate("FE_MOD_PRODUCTO_SERVICIO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prodPrincipio;
    }
    
    @Override
    public ArrayList<PrincipioActivoProducto> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<PrincipioActivoProducto> rgs = new ArrayList<PrincipioActivoProducto>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(PrincipioActivoProducto.nt, PrincipioActivoProducto.COLUMNA_ACTIVO, PrincipioActivoProducto.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(PrincipioActivoProducto.nt, PrincipioActivoProducto.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(PrincipioActivoProducto.nt, campos, columnaId, id, PrincipioActivoProducto.COLUMNA_ORDER);
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
                prodPrincipio= new PrincipioActivoProducto();
                prodPrincipio.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRINCIPIO_ACTIVO")});
                prodPrincipio.setCoCompania(rs.getString("CO_COMPANIA"));
                prodPrincipio.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodPrincipio.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
//                prodPrincipio.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
//                prodPrincipio.setInPrincipioActivoPrincipal(rs.getString("IN_PRINCIPIO_ACTIVO_PRINCIPAL"));
//                prodPrincipio.setDeObservacion(rs.getString("DE_OBSERVACION"));
//                prodPrincipio.setEsProductoPrincipio(rs.getString("ES_PRODUCTO_PRINCIPIO"));
//                prodPrincipio.setIdCreaProductoServicio(rs.getString("ID_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeCreaProductoServicio(rs.getDate("FE_CREA_PRODUCTO_SERVICIO"));
//                prodPrincipio.setIdModProductoServicio(rs.getString("ID_MOD_PRODUCTO_SERVICIO"));
//                prodPrincipio.setFeModProductoServicio(rs.getDate("FE_MOD_PRODUCTO_SERVICIO"));
                rgs.add(prodPrincipio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getPrincipioActivoPrincipio(String CoCompania, String CoProducto, String Estado){
        ArrayList<PrincipioActivoProducto> rgs = new ArrayList<PrincipioActivoProducto>();        
        PrincipioActivoProducto      prod      = null;
        StringBuffer  query;
        try {

            query = new StringBuffer();
            query.append("SELECT INS.CO_COMPANIA,  ");
            query.append("       INS.CO_PRODUCTO,  ");
            query.append("       INS.NU_REVISION_PRODUCTO,  ");
            query.append("       INS.CO_PRODUCTO_INSUMO,  ");
            query.append("       INS.IN_PRODUCTO_INSUMO_PRINCIPAL,  ");
            query.append("       INS.ES_PRODUCTO_INSUMO,  ");
            query.append("       PRO.DE_PRODUCTO, ");
            query.append("       INS.DE_UNIDAD_INSUMO, ");
            query.append("       INS.VA_COSTO, ");
            query.append("       INS.VA_PRECIO_PROMEDIO, ");
            query.append("       INS.IN_IMPRESION ");
            query.append("  FROM LGTM_PRODUCTO PRO, ");
            query.append("       LGTR_PRODUCTO_INSUMO INS ");
            query.append(" WHERE PRO.CO_COMPANIA=INS.CO_COMPANIA ");
            query.append(" AND PRO.CO_PRODUCTO  =INS.CO_PRODUCTO_INSUMO ");
            query.append("   and INS.CO_COMPANIA = '").append(CoCompania).append("'");
            query.append("   and INS.CO_PRODUCTO = '").append(CoProducto).append("' ");
         
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                prodPrincipio= new PrincipioActivoProducto();
                prodPrincipio.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRODUCTO_INSUMO")});
                prodPrincipio.setCoCompania(rs.getString("CO_COMPANIA"));
                prodPrincipio.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodPrincipio.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                prodPrincipio.setCoProductoInsumo(rs.getString("CO_PRODUCTO_INSUMO"));
                prodPrincipio.setInProductoInsumoPrincipal(rs.getString("IN_PRODUCTO_INSUMO_PRINCIPAL"));
                prodPrincipio.setEsProductoInsumo(rs.getString("ES_PRODUCTO_INSUMO"));
                prodPrincipio.setDeProductoInsumo(rs.getString("DE_PRODUCTO"));
                prodPrincipio.setDeUnidad(rs.getString("DE_UNIDAD_INSUMO"));
                prodPrincipio.setVaCosto(rs.getBigDecimal("VA_COSTO"));
                prodPrincipio.setVaPrecioPromedio(rs.getBigDecimal("VA_PRECIO_PROMEDIO"));
                prodPrincipio.setInImpresion(rs.getString("IN_IMPRESION"));
                rgs.add(prodPrincipio);
            }
            rs.close();            
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
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
        prodPrincipio = (PrincipioActivoProducto)mdl;
        int gravado = 0;
        String campos = PrincipioActivoProducto.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {prodPrincipio.getCoCompania(),
                            prodPrincipio.getCoProducto(),
                            prodPrincipio.getNuRevisionProducto(),
//                            prodPrincipio.getCoPrincipioActivo(),
//                            prodPrincipio.getInPrincipioActivoPrincipal(),
//                            prodPrincipio.getDeObservacion(),
//                            prodPrincipio.getEsProductoPrincipio(),
//                            prodPrincipio.getIdCreaProductoServicio(),
//                            prodPrincipio.getFeCreaProductoServicio(),
//                            prodPrincipio.getIdModProductoServicio(),
//                            prodPrincipio.getFeModProductoServicio()
                           };
       
           gravado = this.agregarRegistroPs(prodPrincipio.getNombreTabla(),PrincipioActivoProducto.FULL_NOM_CAMPOS, valores);
       
        if(gravado==1)
            return true;
        
        return false;
    }

    public boolean eliminarRegistro(JAbstractModelBD mdl) throws SQLException {
        int gravado = 0;
        prodPrincipio = (PrincipioActivoProducto)mdl;
        gravado = this.eliminacionReal(prodPrincipio);

        if(gravado==1){
            return true;
        }
        return false;
    }
    
    
    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        prodPrincipio = (PrincipioActivoProducto)mdl;
        int gravado = 0;        
        
        Object[] valores = {
//                prodPrincipio.getInPrincipioActivoPrincipal(),
//                            prodPrincipio.getDeObservacion(),
//                            prodPrincipio.getEsProductoPrincipio(),
//                            prodPrincipio.getIdCreaProductoServicio(),
//                            prodPrincipio.getFeCreaProductoServicio(),
//                            prodPrincipio.getIdModProductoServicio(),
//                            prodPrincipio.getFeModProductoServicio()
                           };

        gravado = this.actualizarRegistroPs(prodPrincipio.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(PrincipioActivoProducto.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(PrincipioActivoProducto.COLUMNA_PK, prodPrincipio.getPrimaryKey()) , valores);
        return gravado;
    }

    public PrincipioActivoProducto getPrincipioActivoProducto() {
        if(prodPrincipio == null)
        {
            prodPrincipio = new PrincipioActivoProducto();
        }
        return prodPrincipio;
    }

    public void setPrincipioActivoProducto(JAbstractModelBD prv) {
        this.prodPrincipio = (PrincipioActivoProducto)prv;
    }
}
