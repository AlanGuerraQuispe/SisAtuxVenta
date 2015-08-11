package atux.modelgui;

import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxUtility;
import atux.vistas.venta.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class ModeloTomaPedidoVenta extends ModeloTabla{

    private Class[] tipoColumnas;
    private Double mBruto    = 0.0;
    private Double mDscto    = 0.0;
    private Double mAfecto   = 0.0;
    private Double mImpuesto = 0.0;    
    private Double mRedondeo = 0.0;    
    private Double mTotalPreVenta = 0.0;
    private Integer numItems = 0;

    public final static int GENERACION_PEDIDO = 1;

    public static final Integer[] anchoColumnas  = {50,240,90,60,50,60,
                                                    50,60,160};
    
    private int tipoTabla;

    public ModeloTomaPedidoVenta(ArrayList registros) {
        super(registros);
    }

    public ModeloTomaPedidoVenta(int tipoColumna) {
        this.registros = new ArrayList<DetallePedidoVenta>();
        DetallePedidoVenta dp = new DetallePedidoVenta();
        dp.setProdLocal(new ProductoLocal());
        this.registros.add(dp);
        this.nombreColumnas = new String[]{"Producto Seleccionado","Unidad","Cantidad","Precio",
                                           "Importe","Accion"};
        if(tipoColumna ==1) {
            tipoColumnas = new Class[]{String.class, String.class, Integer.class, Double.class,
                    Double.class, PanelAccionProdInsumos.class};
        }
        else{
            tipoColumnas = new Class[]{String.class, String.class, Integer.class, Double.class,
                    Double.class, PanelAccion.class};
        }
        this.tipoTabla = GENERACION_PEDIDO;
    }   
        
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        DetallePedidoVenta dp = (DetallePedidoVenta)this.registros.get(rowIndex);

        if(StringUtils.isNotBlank(dp.getCoVentaPromocion())) return false;

        if(dp.getProdLocal().getPrimaryKey() != null)
        {
            if(columnIndex==2 || columnIndex==5)
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

    public void setValueAt(IPedidoVentaInsumo pedidoVenta,Object aValue, int rowIndex, int columnIndex)
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
                
               this.mBruto = getBruto(); 
               this.fireTableCellUpdated(rowIndex, columnIndex);           
        }
    }
    
       
    public void agregar(ProductoLocal prd)
    {
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(prd);       
        this.registros.add(dc);               
        this.mBruto = getBruto();  
        contarItems();
        this.fireTableRowsInserted(this.registros.size(), this.registros.size());
    }

    public void fireTableRowsInserted(){
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
    
    public Double getBruto()
    {
        Double tmpBruto = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                   
                Double bruto = AtuxUtility.getDecimalNumberRedondeado((dc2.getVaVenta()/((dc2.getPcImpuesto_1()/100) + 1))*dc2.getCaAtendida());                 
                tmpBruto += bruto;               
            }
        }
        this.mBruto = tmpBruto;
        return this.mBruto;
    }
    
    public Double getTotalDescuento()
    {
        Double tmpDsc = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                
                Double descuento = AtuxUtility.getDecimalNumberRedondeado(dc2.getVaPrecioVenta()/((dc2.getPcImpuesto_1()/100) + 1));
                tmpDsc += descuento;
            }
        }
                
        this.mDscto = this.mBruto - tmpDsc;
        return this.mDscto;
    }
    
    public Double getAfecto()
    {                
        this.mAfecto = this.mBruto - mDscto;
        return this.mAfecto;
    }
    
    public Double getTotalImpuesto()
    {
        Double tmpImp = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                
                Double tmpVenta  = dc2.getVaPrecioVenta();
                Double descuento = AtuxUtility.getDecimalNumberRedondeado(dc2.getVaPrecioVenta()/((dc2.getPcImpuesto_1()/100) + 1));                                
                Double impuesto  = AtuxUtility.getDecimalNumberRedondeado(tmpVenta - descuento);
                tmpImp += impuesto;
            }
        }
        this.mImpuesto = tmpImp;
        return this.mImpuesto;
    }        
    
    public Double getTotalPrecioVenta()
    {            
        Double tmpTotPreVenta = (mBruto + mImpuesto - mDscto) + getRedondeo();
        
        this.mTotalPreVenta = tmpTotPreVenta;
        return this.mTotalPreVenta;
    }
    
    public Double getRedondeo()
    {            
        Double tmpRedondeo = AtuxUtility.getRedondeo(mBruto + mImpuesto - mDscto);       
        
        this.mRedondeo = tmpRedondeo;
        return this.mRedondeo;
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {
            case GENERACION_PEDIDO: 
                switch(columnIndex)
                 {
                    case 0: if(((DetallePedidoVenta)registros.get(rowIndex)).getCoCompania()==null){
                       return ""; 
                        }
                        else
                       return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getProducto().getDeProducto(); 
                    case 1:return ((DetallePedidoVenta)registros.get(rowIndex)).getDeUnidadProducto();    
                    case 2:return ((DetallePedidoVenta)registros.get(rowIndex)).getCaAtendida();
                    case 3:{
                        if((StringUtils.isNotBlank(((DetallePedidoVenta)registros.get(rowIndex)).getCoVentaPromocion()))){
                            if( ((DetallePedidoVenta)registros.get(rowIndex)).getCaAtendida()==null) return null;
                            else return 0;
                        }else{
                            return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getVaPrecioPublico();
                        }
                    }
                    case 4:return ((DetallePedidoVenta)registros.get(rowIndex)).getVaPrecioVenta();
                    case 5:return "Agregar/Eliminar";
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
