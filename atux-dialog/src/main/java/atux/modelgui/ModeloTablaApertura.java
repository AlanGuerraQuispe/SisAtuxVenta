package atux.modelgui;

import atux.controllers.CProductoLocal;
import atux.modelbd.Almacen;
import atux.modelbd.ProductoLocal;
import atux.modelbd.Producto;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;


public class ModeloTablaApertura extends AbstractTableModel{
    private ArrayList<ProductoLocal> aps = new ArrayList<ProductoLocal>();
    private String[] nombreColumnas = {"Almacen","Cantidad"};
    private Class[] clases = {Object.class,Integer.class}; 
    private JLabel aviso;
    
    //private CAlmacen controllerAlmacen;
   // private Almacen almacen;
    private Integer totalProducto = 0;
    
    private CProductoLocal controllerAp;
    public ModeloTablaApertura(JLabel aviso) {
       
        controllerAp =  new CProductoLocal();
        this.aviso = aviso;
        aps = controllerAp.getRegistros(new Object[]{1});
        setTotal(); 
    }
    
    public ModeloTablaApertura(Producto prd) {       
        controllerAp =  new CProductoLocal();        
        //aps = controllerAp.getRegistroPorProducto(prd.getPrimaryKey());
        setTotal();
    }

    public ModeloTablaApertura(JLabel aviso,Producto prd) {
        
        controllerAp = new CProductoLocal();
        this.aviso = aviso;       
        //aps = controllerAp.getRegistroPorProducto(prd.getPrimaryKey());
        setTotal();
      
    }

    public ModeloTablaApertura() {
        setTotal();
    }
    
    private void setTotal()
    {
        if(aps.size()>0)
        {
            for(ProductoLocal al: aps)
            {
                this.totalProducto += al.getCaStockDisponible();
            }
            if(aviso != null)
            {
              aviso.setText("Cantidad Total de Productos: "+this.totalProducto);  
            } 
        }
    }
    @Override
    public int getRowCount() {
        return aps.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombreColumnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return clases[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return false;
            case 1: return true;    
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        aps.get(rowIndex).setCaStockDisponible(Integer.parseInt(aValue.toString()));
        //aps.get(rowIndex).setCantida(Integer.parseInt(aValue.toString()));
        int ctr = 0;
        for(ProductoLocal wp: aps)
        {
            ctr += wp.getCaStockDisponible();
        }
        if(aviso != null)
        {
          aviso.setText("Cantidad Total de Productos: "+ctr);  
        }        
        totalProducto = ctr;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public Integer getTotalProductos()
    {
        return totalProducto;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch(columnIndex){
//           case 0: return aps.get(rowIndex).getAlmacen().getNombre();
           case 1: return aps.get(rowIndex).getCaStockDisponible()==null?0:aps.get(rowIndex).getCaStockDisponible();
           default:return null;
       }
    }
    
    public ArrayList<ProductoLocal> getDatos()
    {        
        return aps;
    }
    
    public class WrapperProductoAlmacen{
        public Almacen almacen;
        public Producto producto;
        public Integer cantida;
        
        public ProductoLocal almacenProducto;
        
        public boolean existe = false;

        public WrapperProductoAlmacen(Almacen almacen, Producto producto, Integer cantida) {
            this.almacen = almacen;
            this.producto = producto;
            this.cantida = cantida;
        }

        public WrapperProductoAlmacen(Almacen almacen, Producto producto, Integer cantida, boolean existe) {
            this.almacen = almacen;
            this.producto = producto;
            this.cantida = cantida;
            this.existe = existe;
        }

        public WrapperProductoAlmacen(ProductoLocal almacenProducto) {
            this.almacenProducto = almacenProducto; 
//            this.almacen = this.almacenProducto.getAlmacen();
            this.producto = this.almacenProducto.getProducto();
            this.cantida = this.almacenProducto.getCaStockDisponible();
            this.existe = true;
        }
        public WrapperProductoAlmacen() {
        }

        public Almacen getAlmacen() {
            return almacen;
        }

        public void setAlmacen(Almacen almacen) {
            this.almacen = almacen;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public Integer getCantida() {
            return cantida;
        }

        public void setCantida(Integer cantida) {
            this.cantida = cantida;
        }

        public ProductoLocal getAlmacenProducto() {
            return almacenProducto;
        }

        public void setAlmacenProducto(ProductoLocal almacenProducto) {
            this.almacenProducto = almacenProducto;
        }

        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 23 * hash + (this.almacen != null ? this.almacen.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final WrapperProductoAlmacen other = (WrapperProductoAlmacen) obj;
            if (this.almacen != other.almacen && (this.almacen == null || !this.almacen.equals(other.almacen))) {
                return false;
            }
            return true;
        }
    }
    
}
