package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.DetallePedidoVenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CDetallePedidoVenta extends JAbstractController{

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

     public ArrayList<DetallePedidoVenta> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
     
     public ArrayList<DetallePedidoVenta> getRegistrosPorPedidoVenta(String[] pk) {
        return this.getRegistros(new String[]{}, new String[]{"CO_COMPANIA","CO_LOCAL","NU_PEDIDO"},pk);
    }



    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        DetallePedidoVenta detPedVenta = (DetallePedidoVenta)mdl;
        int gravado = 0;
        String[] campos = DetallePedidoVenta.CAMPOS_INSERTS;
        
        Object[] valores = {detPedVenta.getIdPedidoVenta().getCoCompania(),
                            detPedVenta.getIdPedidoVenta().getCoLocal(),
                            detPedVenta.getIdPedidoVenta().getNuPedido(),
                            detPedVenta.getNuItemPedido(),
                            detPedVenta.getIdPedidoVenta().getCoVendedor(),
                            detPedVenta.getCoProducto(),
                            detPedVenta.getNuRevisionProducto(),
                            detPedVenta.getVaVenta(),
                            detPedVenta.getVaPrecioPublico(),
                            detPedVenta.getCaAtendida(),
                            detPedVenta.getVaPrecioVenta(),
                            detPedVenta.getInProductoFraccion(),
                            detPedVenta.getVaFraccion(),
                            detPedVenta.getCoImpuesto_1(),
                            detPedVenta.getPcImpuesto_1(),
                            detPedVenta.getPcDescuento_1(),                            
                            detPedVenta.getDeUnidadProducto(),
                            detPedVenta.getVaBono(),
                            detPedVenta.getEsDetPedidoVenta(),
                            detPedVenta.getIdCreaDetPedidoVenta(),
                            detPedVenta.getFeCreaDetPedidoVenta(),
                            detPedVenta.getPcDescuentoConvenio(),
                            detPedVenta.getPcDescuentoBaseLocal(),
                            detPedVenta.getVaPrecioOriginal(),
                            detPedVenta.getVaPrecioPromedio(),
                            detPedVenta.getCoProductoPrincipal(),
                            detPedVenta.getInProductoPrincipal()
            };
       
           gravado = this.agregarRegistroPs(DetallePedidoVenta.nt, campos, valores);
       
        if(gravado==1)
        {          
            detPedVenta.getProdLocal();
        }
        return false;
    }
    
    private Integer getUltimoPk() throws SQLException
    {
        ResultSet rsTmp = this.getUltimoRegistro(DetallePedidoVenta.nt, "iddetallec");
        Integer pk=null;
        try{
            while(rsTmp.next())
            {
               pk =  rsTmp.getInt(1);
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return pk;
    }
    
    

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        DetallePedidoVenta detPedVenta = (DetallePedidoVenta)mdl;
        int gravado = 0;
        String campos = DetallePedidoVenta.CAMPOS_INSERTS.toString();
        
        Object[] valores = {detPedVenta.getIdPedidoVenta().getPrimaryKey(),
                            detPedVenta.getProdLocal().getPrimaryKey(),
                            detPedVenta.getCaAtendida(),
                            detPedVenta.getVaVenta(),
                            detPedVenta.getDeUnidadProducto(),
                            detPedVenta.getPrimaryKey()};
       
           gravado = this.actualizarRegistroPs(DetallePedidoVenta.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+DetallePedidoVenta.COLUMNA_PK+" = ? ", valores);
               
        return gravado;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
       ArrayList<DetallePedidoVenta> rgs = new ArrayList<DetallePedidoVenta>();        
        try {            
            rs = this.getRegistros(DetallePedidoVenta.nt,DetallePedidoVenta.CAMPOS_INSERTS,columnaId,id,null);
      
            DetallePedidoVenta detPedVenta = null;            
            while(rs.next())
            {                   
                   detPedVenta = new DetallePedidoVenta();
                   detPedVenta.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});                                      
                   detPedVenta.setCoCompania(rs.getString(1));
                   detPedVenta.setCoLocal(rs.getString(2));
                   detPedVenta.setNuPedido(rs.getString(3));
                   detPedVenta.setNuItemPedido(rs.getInt(4));
                   detPedVenta.setCoVendedor(rs.getString(5));
                   detPedVenta.setCoProducto(rs.getString(6));                   
                   detPedVenta.setNuRevisionProducto(rs.getString(7));
                   detPedVenta.setProdLocal(new CProductoLocal().getRegistro(new Object[]{rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(7)}));                   
                   detPedVenta.setVaVenta(rs.getDouble(8));
                   detPedVenta.setVaPrecioPublico(rs.getDouble(9));
                   detPedVenta.setCaAtendida(rs.getInt(10));
                   detPedVenta.setVaPrecioVenta(rs.getDouble(11));
                   detPedVenta.setInProductoFraccion(rs.getString(12));
                   detPedVenta.setVaFraccion(rs.getInt(13));
                   detPedVenta.setCoImpuesto_1(rs.getString(14));
                   detPedVenta.setPcImpuesto_1(rs.getDouble(15));
                   detPedVenta.setPcDescuento_1(rs.getDouble(16));        
                   detPedVenta.setDeUnidadProducto(rs.getString(17));
                   detPedVenta.setVaBono(rs.getDouble(18));
                   detPedVenta.setEsDetPedidoVenta(rs.getString(19));
                   detPedVenta.setIdCreaDetPedidoVenta(rs.getString(20));
                   detPedVenta.setFeCreaDetPedidoVenta(rs.getDate(21));
                   detPedVenta.setPcDescuentoConvenio(rs.getDouble(22));
                   detPedVenta.setPcDescuentoBaseLocal(rs.getDouble(23));
                   detPedVenta.setVaPrecioOriginal(rs.getDouble(24));
                   detPedVenta.setCoProductoPrincipal(rs.getString(26));
                   detPedVenta.setInProductoPrincipal(rs.getString(27));
                   rgs.add(detPedVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
}
