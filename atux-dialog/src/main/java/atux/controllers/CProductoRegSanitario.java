package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ProductoRegSanitario;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;


public class CProductoRegSanitario extends JAbstractController {
    
    private ProductoRegSanitario prd;
    private InputStream itmp;

    
    public void setProductoRegSanitario(JAbstractModelBD prv) {
        this.prd = (ProductoRegSanitario)prv;
    }    
    
    public ProductoRegSanitario getProductoRegSanitario() {
        if(prd == null)
        {
            prd = new ProductoRegSanitario();
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

    public ProductoRegSanitario getRegistro(int id)
    {
        ArrayList<ProductoRegSanitario> registros = this.getRegistros(ProductoRegSanitario.COLUMNA_PK,new Object[]{id});
        return registros.get(0);
    }
    
     public ProductoRegSanitario getRegistroPorCodigo(String id)
    {
        ArrayList<ProductoRegSanitario> registros = this.getRegistros(new String[]{"codigo"},new Object[]{id});
        return registros.get(0);
    }
    
    public ArrayList<ProductoRegSanitario> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
    
    public ProductoRegSanitario getRegistroPorPk(Object[] id)
    {
            ProductoRegSanitario prodRegSanitario = null;
        try {
            
            rs =  this.selectPorPk(ProductoRegSanitario.nt,ProductoRegSanitario.FULL_CAMPOS, ProductoRegSanitario.COLUMNA_PK, id);
            
            while(rs.next())
            {
                prodRegSanitario = new ProductoRegSanitario();
                prodRegSanitario.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REG_SANITARIO")});
                prodRegSanitario.setCoCompania(rs.getString("CO_COMPANIA"));
                prodRegSanitario.setCoLocal(rs.getString("CO_LOCAL"));
                prodRegSanitario.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodRegSanitario.setNuRegSanitario(rs.getString("NU_REG_SANITARIO"));
                prodRegSanitario.setFeVenRegSanitario(rs.getDate("FE_VEN_REG_SANITARIO"));
                prodRegSanitario.setEsRegSanitario(rs.getString("ES_REG_SANITARIO"));
                prodRegSanitario.setIdCreaRegSanitario(rs.getString("ID_CREA_REG_SANITARIO"));
                prodRegSanitario.setFeCreaRegSanitario(rs.getDate("FE_CREA_REG_SANITARIO"));
                prodRegSanitario.setIdModRegSanitario(rs.getString("ID_MOD_REG_SANITARIO"));
                prodRegSanitario.setFeModRegSanitario(rs.getDate("FE_MOD_REG_SANITARIO"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prodRegSanitario;
    }               
    
    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        ProductoRegSanitario prodRegSanitario = (ProductoRegSanitario)mdl;
        int gravado = 0;
        String campos = ProductoRegSanitario.FULL_CAMPOS.toString();
        
        Object[] valores = {prodRegSanitario.getCoCompania(),
                            prodRegSanitario.getCoLocal(),
                            prodRegSanitario.getCoProducto(),
                            prodRegSanitario.getNuRegSanitario(),
                            prodRegSanitario.getFeVenRegSanitario(),
                            prodRegSanitario.getEsRegSanitario(),
                            prodRegSanitario.getIdCreaRegSanitario(),
                            prodRegSanitario.getFeCreaRegSanitario(),
                            prodRegSanitario.getIdModRegSanitario(),
                            prodRegSanitario.getFeModRegSanitario()
                           };
              
        gravado = this.agregarRegistroPs(ProductoRegSanitario.nt, ProductoRegSanitario.FULL_CAMPOS, valores);
       
        if(gravado==1){   
            return true;
        }

        return false;
    }
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        ProductoRegSanitario prodRegSanitario = (ProductoRegSanitario)mdl;
        int gravado = 0;
        String campos = ProductoRegSanitario.CAMPOS_SIN_ID.toString();  
        
        Object[] valores = {prodRegSanitario.getFeVenRegSanitario(),
                            prodRegSanitario.getEsRegSanitario(),
                            prodRegSanitario.getIdCreaRegSanitario(),
                            prodRegSanitario.getFeCreaRegSanitario(),
                            prodRegSanitario.getIdModRegSanitario(),
                            prodRegSanitario.getFeModRegSanitario()
                           };
              
        gravado = this.actualizarRegistroPs(ProductoRegSanitario.nt, this.adjuntarSimbolo(generarArrayAString(ProductoRegSanitario.CAMPOS_SIN_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ProductoRegSanitario.COLUMNA_PK, prodRegSanitario.getPrimaryKey()) , valores);
        return gravado;
    }  
    
    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ProductoRegSanitario> rgs = new ArrayList<ProductoRegSanitario>();        
        try {
                     
            rs = this.getRegistros(ProductoRegSanitario.nt,campos,columnaId,id,null);
            
            ProductoRegSanitario prodRegSanitario = null;
            while(rs.next())
            {
                prodRegSanitario = new ProductoRegSanitario();
                prodRegSanitario.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REG_SANITARIO")});
                prodRegSanitario.setCoCompania(rs.getString("CO_COMPANIA"));
                prodRegSanitario.setCoLocal(rs.getString("CO_LOCAL"));
                prodRegSanitario.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodRegSanitario.setNuRegSanitario(rs.getString("NU_REG_SANITARIO"));
                prodRegSanitario.setFeVenRegSanitario(rs.getDate("FE_VEN_REG_SANITARIO"));
                prodRegSanitario.setEsRegSanitario(rs.getString("ES_REG_SANITARIO"));
                prodRegSanitario.setIdCreaRegSanitario(rs.getString("ID_CREA_REG_SANITARIO"));
                prodRegSanitario.setFeCreaRegSanitario(rs.getDate("FE_CREA_REG_SANITARIO"));
                prodRegSanitario.setIdModRegSanitario(rs.getString("ID_MOD_REG_SANITARIO"));
                prodRegSanitario.setFeModRegSanitario(rs.getDate("FE_MOD_REG_SANITARIO"));
                rgs.add(prodRegSanitario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }

    public boolean existeRegSanitario(String RegSanitario){
        return this.existe(ProductoRegSanitario.nt, "NU_REG_SANITARIO", RegSanitario);
    }

//    public String getNuevoCodigo(){
//        String Codigo="";
//        try {
//            return AtuxDBUtility.getValueAt(ProductoRegSanitario.nt,"rtrim(ltrim(to_char(max(co_ProductoRegSanitario) + 1,'000000')))"," co_compania = '" + AtuxVariables.vCodigoCompania + "' and nu_revision_ProductoRegSanitario =0");
//        } catch (SQLException ex) {
//            Logger.getLogger(CTipoDeCambio.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Codigo.trim();
//    }


}
