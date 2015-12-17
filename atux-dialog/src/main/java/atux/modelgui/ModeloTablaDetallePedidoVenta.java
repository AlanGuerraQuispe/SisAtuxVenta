package atux.modelgui;

import atux.controllers.CDetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.vistas.venta.ICompletarPedidoVenta;
import atux.vistas.venta.IPedidoVenta;
import java.util.ArrayList;


public class ModeloTablaDetallePedidoVenta extends ModeloTabla{
    private Integer numItems = 0;
    private CDetallePedidoVenta controllerDetallePedidoVenta;    
    
    public final static int COBRO_DE_PEDIDDO  = 2;
    public static final Integer[] anchoColumnas  = {50,240,90,60,50,60,
                                                    50,60,160};
    private int tipoTabla;

    public ModeloTablaDetallePedidoVenta(PedidoVenta cpv) {
        controllerDetallePedidoVenta = new CDetallePedidoVenta();
        this.registros = controllerDetallePedidoVenta.getRegistrosPorPedidoVenta(cpv.getPrimaryKey());
        
        this.nombreColumnas = new String[]{"Código","Producto","Unidad","Precio","%Dscto","P.Venta",
                                           "Cantidad","Total","Laboratorio"};               
        
        this.tipoTabla = COBRO_DE_PEDIDDO;
    }
        
    public ModeloTablaDetallePedidoVenta(ArrayList<DetallePedidoVenta> detalle) {
        this.registros = detalle;
        
        this.nombreColumnas = new String[]{"Código","Producto","Unidad","Precio","%Dscto","P.Venta",
                                           "Cantidad","Total","Laboratorio"};        
        
        this.tipoTabla = COBRO_DE_PEDIDDO;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        DetallePedidoVenta dp = (DetallePedidoVenta)this.registros.get(rowIndex);
        if(dp.getProdLocal().getPrimaryKey() != null)
        {
            if(columnIndex > 0 && columnIndex!=3)
            {
                return true;
            }
        }else if(dp.getProdLocal().getPrimaryKey() == null)
        {
            if(columnIndex == this.nombreColumnas.length-1)
            {
                return true;
            }
        }
        return false;
    }

    public void setValueAt(IPedidoVenta pedidoVenta,Object aValue, int rowIndex, int columnIndex)
    {
        this.setValueAt(aValue, rowIndex, columnIndex);
        pedidoVenta.setTotales();
    }
    
    public void setValueAt(ICompletarPedidoVenta pedidoVenta,Object aValue, int rowIndex, int columnIndex)
    {
        this.setValueAt(aValue, rowIndex, columnIndex);
        pedidoVenta.setTotales();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {        
        DetallePedidoVenta dp = (DetallePedidoVenta)this.registros.get(rowIndex);
        if(dp.getProdLocal().getPrimaryKey() != null)
        {
                switch(columnIndex)
               {
                   case 2:
                       ((DetallePedidoVenta)registros.get(rowIndex)).setCaAtendida(Integer.parseInt(aValue.toString()));                                               
                       break;                
                   case 4:                                           
                       ((DetallePedidoVenta)registros.get(rowIndex)).setVaPrecioVenta((Double)aValue);
                       break;
               }
                
               this.fireTableCellUpdated(rowIndex, columnIndex);           
        }
    }
    
       
    public void agregar(ProductoLocal prd)
    {
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(prd);       
        this.registros.add(dc);                      
        contarItems();
        this.fireTableRowsInserted(this.registros.size(), this.registros.size());
    }
    
    public void remplazarProducto(ProductoLocal prd,int index)
    {
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(prd); 
        registros.remove(index);
        this.agregar(prd);               
    }
    
    @Override
    public void quitarFila(int fila)
    {
        registros.remove(fila);
    }
    
    public boolean existe(ProductoLocal prd)
    {
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc = (DetallePedidoVenta)obj;
            if(dc.getProdLocal().getPrimaryKey() != null)
            {
                if(dc.getProdLocal().equals(prd))
                {
                    return true;                   
                }
            }
        }
        return false;
    }       
    public void contarItems()
    {
        numItems = 0;
        for(Object obj:this.registros)
        {            
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                
                numItems++;
            }
        }
    }
    
    public Integer getNumItems()
    {
        return numItems;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {            
              case COBRO_DE_PEDIDDO: 
                 switch(columnIndex)
                 {                    
                    case 0:return ((DetallePedidoVenta)registros.get(rowIndex)).getCoProducto();  
                    case 1:return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getProducto().getDeProducto();
                    case 2:return ((DetallePedidoVenta)registros.get(rowIndex)).getDeUnidadProducto();    
                    case 3:return ((DetallePedidoVenta)registros.get(rowIndex)).getVaVenta();
                    case 4:return ((DetallePedidoVenta)registros.get(rowIndex)).getPcDescuento_1();
                    case 5:return ((DetallePedidoVenta)registros.get(rowIndex)).getVaPrecioPublico();                        
                    case 6:return ((DetallePedidoVenta)registros.get(rowIndex)).getCaAtendida();
                    case 7:return ((DetallePedidoVenta)registros.get(rowIndex)).getVaPrecioVenta();    
                    case 8:return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getProducto().getLaboratorio().getDeLaboratorio();
                 }
                break;  
                default:return null;    
        }
        return null;
    }
    
    public void limpiar()
    {
        registros.clear();
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(new ProductoLocal());
        registros.add(dc);
    }
    
    public ArrayList<DetallePedidoVenta> getDetallesPedidoVenta()
    {
        ArrayList<DetallePedidoVenta> dp = new ArrayList<DetallePedidoVenta>();
        for(Object obj:this.registros)
        {            
            DetallePedidoVenta dp2 = (DetallePedidoVenta)obj;
            if(dp2.getProdLocal().getPrimaryKey() != null)
            {                
                dp.add(dp2);
            }
        }
        return dp;
    }
}
