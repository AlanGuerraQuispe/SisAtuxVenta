package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ProductoLote;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;


public class CProductoLote extends JAbstractController {
    
    private ProductoLote prd;
    private InputStream itmp;

    
    public void setProductoLote(JAbstractModelBD prv) {
        this.prd = (ProductoLote)prv;
    }    
    
    public ProductoLote getProductoLote() {
        if(prd == null)
        {
            prd = new ProductoLote();
        }
        return prd;
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros() {
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

    public ProductoLote getRegistro(int id)
    {
        ArrayList<ProductoLote> registros = this.getRegistros(ProductoLote.COLUMNA_PK,new Object[]{id});
        return registros.get(0);
    }
    
     public ProductoLote getRegistroPorCodigo(String id)
    {
        ArrayList<ProductoLote> registros = this.getRegistros(new String[]{"codigo"},new Object[]{id});
        return registros.get(0);
    }
    
    public ArrayList<ProductoLote> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
    
    public ProductoLote getRegistroPorPk(Object[] id)
    {
            ProductoLote mProductoLote = null;
        try {
            
            rs =  this.selectPorPk(ProductoLote.nt,ProductoLote.FULL_CAMPOS, ProductoLote.COLUMNA_PK, id);
            
            while(rs.next())
            {
                mProductoLote = new ProductoLote();
                mProductoLote.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_PRODUCTO"),rs.getString("NU_LOTE")});
                mProductoLote.setCoCompania(rs.getString("CO_COMPANIA"));
                mProductoLote.setCoLocal(rs.getString("CO_LOCAL"));
                mProductoLote.setCoProducto(rs.getString("CO_PRODUCTO"));
                mProductoLote.setFeIngreso(rs.getDate("FE_INGRESO"));
                mProductoLote.setFeVencimiento(rs.getDate("FE_VENCIMIENTO"));
                mProductoLote.setNuLote(rs.getString("NU_LOTE"));
                mProductoLote.setTiDocumentoRecepcion(rs.getString("TI_DOCUMENTO_RECEPCION"));
                mProductoLote.setNuDocumentoRecepcion(rs.getString("NU_DOCUMENTO_RECEPCION"));
                mProductoLote.setIdCreaLote(rs.getString("ID_CREA_LOTE"));
                mProductoLote.setFeCreaLote(rs.getDate("FE_CREA_LOTE"));
                mProductoLote.setEsLote(rs.getString("ES_LOTE"));
                mProductoLote.setIdModLote(rs.getString("ID_MOD_LOTE"));
                mProductoLote.setFeModLote(rs.getDate("FE_MOD_LOTE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mProductoLote;
    }               
    
    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        ProductoLote mProductoLote = (ProductoLote)mdl;
        int gravado = 0;
        String campos = ProductoLote.FULL_CAMPOS.toString();
        
        Object[] valores = {mProductoLote.getCoCompania(),
                            mProductoLote.getCoLocal(),
                            mProductoLote.getCoProducto(),
                            mProductoLote.getFeIngreso(),
                            mProductoLote.getFeVencimiento(),
                            mProductoLote.getNuLote(),
                            mProductoLote.getTiDocumentoRecepcion(),
                            mProductoLote.getNuDocumentoRecepcion(),
                            mProductoLote.getIdCreaLote(),
                            mProductoLote.getFeCreaLote(),
                            mProductoLote.getEsLote(),
                            mProductoLote.getIdModLote(),
                            mProductoLote.getFeModLote()
                           };
              
        gravado = this.agregarRegistroPs(ProductoLote.nt, ProductoLote.FULL_CAMPOS, valores);
       
        if(gravado==1){   
            return true;
        }

        return false;
    }
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        ProductoLote mProductoLote = (ProductoLote)mdl;
        int gravado = 0;
        String campos = ProductoLote.CAMPOS_SIN_ID.toString();  
        
        Object[] valores = {mProductoLote.getFeIngreso(),
                            mProductoLote.getFeVencimiento(),
                            mProductoLote.getTiDocumentoRecepcion(),
                            mProductoLote.getNuDocumentoRecepcion(),
                            mProductoLote.getIdCreaLote(),
                            mProductoLote.getFeCreaLote(),
                            mProductoLote.getEsLote(),
                            mProductoLote.getIdModLote(),
                            mProductoLote.getFeModLote()
                           };
              
        gravado = this.actualizarRegistroPs(ProductoLote.nt, this.adjuntarSimbolo(generarArrayAString(ProductoLote.CAMPOS_SIN_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ProductoLote.COLUMNA_PK, mProductoLote.getPrimaryKey()) , valores);
        return gravado;
    }  
    
    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ProductoLote> rgs = new ArrayList<ProductoLote>();        
        try {
                     
            rs = this.getRegistros(ProductoLote.nt,campos,columnaId,id,null);
            
            ProductoLote mProductoLote = null;
            while(rs.next())
            {
                mProductoLote = new ProductoLote();
                mProductoLote.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_PRODUCTO"),rs.getString("NU_LOTE")});
                mProductoLote.setCoCompania(rs.getString("CO_COMPANIA"));
                mProductoLote.setCoLocal(rs.getString("CO_LOCAL"));
                mProductoLote.setCoProducto(rs.getString("CO_PRODUCTO"));
                mProductoLote.setFeIngreso(rs.getDate("FE_INGRESO"));
                mProductoLote.setFeVencimiento(rs.getDate("FE_VENCIMIENTO"));
                mProductoLote.setNuLote(rs.getString("NU_LOTE"));
                mProductoLote.setTiDocumentoRecepcion(rs.getString("TI_DOCUMENTO_RECEPCION"));
                mProductoLote.setNuDocumentoRecepcion(rs.getString("NU_DOCUMENTO_RECEPCION"));
                mProductoLote.setIdCreaLote(rs.getString("ID_CREA_LOTE"));
                mProductoLote.setFeCreaLote(rs.getDate("FE_CREA_LOTE"));
                mProductoLote.setEsLote(rs.getString("ES_LOTE"));
                mProductoLote.setIdModLote(rs.getString("ID_MOD_LOTE"));
                mProductoLote.setFeModLote(rs.getDate("FE_MOD_LOTE"));
                rgs.add(mProductoLote);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public boolean existeRegSanitario(String RegSanitario){
        return this.existe(ProductoLote.nt, "NU_REG_SANITARIO", RegSanitario);
    }

//    public String getNuevoCodigo(){
//        String Codigo="";
//        try {
//            return AtuxDBUtility.getValueAt(ProductoLote.nt,"rtrim(ltrim(to_char(max(co_ProductoLote) + 1,'000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "' and nu_revision_ProductoLote =0");
//        } catch (SQLException ex) {
//            Logger.getLogger(CTipoDeCambio.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Codigo.trim();
//    }


}
