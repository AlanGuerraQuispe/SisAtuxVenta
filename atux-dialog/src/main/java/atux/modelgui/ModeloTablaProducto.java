package atux.modelgui;

import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxVariables;
import com.atux.bean.promocion.PromocionDetalle;

import java.util.ArrayList;


public class ModeloTablaProducto extends ModeloTabla{
    
    /**
     *  Indica si este modelo se aplicara a una tabla
     *  que se mostrara en un formulario de busqueda,compra o venta
     *  
     */
    public final static int PRO_LISTA= 1;
    public final static int PRO_SUSTITUTOS = 2;           
    public final static int PRO_COMPLEMENTARIOS = 3;
    public final static int PRO_LABORATORIO = 4;
    public final static int PRO_PROMOCION = 5;
    public final static int PRO_INSUMOS = 6;

    public static final String[] columnasVenta  = {"Código","Producto","Unidad","Laboratorio","Prec.Venta",
                                                    "Descuento","Prec.Público", "Stock","Bono"};              
    
    public static final String[] columnasProSustitutos  = {"Código","Producto sustituto","Unidad","Laboratorio","Prec.Venta",
                                                    "Descuento","Prec.Público", "Stock","Bono"}; 
    
    public static final String[] columnasProComplementarios  = {"Código","Producto complementario","Unidad","Laboratorio","Prec.Venta",
                                                    "Descuento","Prec.Público", "Stock","Bono"};

    public static final String[] columnasInsumosPro  = {"Código","Insumo para Producto","Unidad","Laboratorio","Prec.Venta",
                                                        "Descuento","Prec.Público", "Stock","Bono"};

    public static final String[] columnasPromociones= {"Promoción","Laboratorio"};

    public static final Integer[] anchoColumnas  = {50,257,85,155,65,65,71,37,37};
    public static final Integer[] anchoColumnasPromo  = {550,80};

    private int tipoTabla;    
    
    public ModeloTablaProducto(ArrayList productos,int tipoTabla) {
         if(tipoTabla==PRO_LISTA){
            this.nombreColumnas = columnasVenta;
             this.tipoTabla = PRO_LISTA;
         }
         else if(tipoTabla==PRO_SUSTITUTOS){
             this.nombreColumnas = columnasProSustitutos;
             this.tipoTabla = PRO_LISTA;
         }
         else if(tipoTabla==PRO_COMPLEMENTARIOS){
             this.nombreColumnas = columnasProComplementarios;
             this.tipoTabla = PRO_LISTA;
         }
         else if(tipoTabla==PRO_INSUMOS){
             this.nombreColumnas = columnasInsumosPro;
             this.tipoTabla = PRO_INSUMOS;
         }
         else if(tipoTabla==PRO_PROMOCION){
             this.nombreColumnas = columnasPromociones;
             this.tipoTabla = PRO_PROMOCION;
         }
        this.registros = productos;
    }        
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {
            case PRO_LISTA: 
                switch(columnIndex)
                 {                     
                     case 0:return ((ProductoLocal)registros.get(rowIndex)).getProducto().getCoProducto();
                     case 1:return ((ProductoLocal)registros.get(rowIndex)).getProducto().getDeProducto();
                     case 2:return ((ProductoLocal)registros.get(rowIndex)).getDeUnidadFraccion();
                     case 3: return (AtuxVariables.vMuestraListaPrincipiosActivos==true)?((ProductoLocal) registros.get(rowIndex)).getProducto().getDePrincipioActivo():((ProductoLocal)registros.get(rowIndex)).getProducto().getLaboratorio().getDeLaboratorio();
                     case 4:return ((ProductoLocal)registros.get(rowIndex)).getVaVenta();                     
                     case 5:return ((ProductoLocal)registros.get(rowIndex)).getPcDescuento_1();
                     case 6:return ((ProductoLocal)registros.get(rowIndex)).getVaPrecioPublico();
                     case 7:return ((ProductoLocal)registros.get(rowIndex)).getCaStockDisponible();              
                     case 8:return ((ProductoLocal)registros.get(rowIndex)).getProducto().getVaBono();
//                     case 8:return 0;
                 }
                break;
            case PRO_INSUMOS:
                switch(columnIndex)
                {
                    case 0:return ((ProductoLocal)registros.get(rowIndex)).getCoProducto();
                    case 1:return ((ProductoLocal)registros.get(rowIndex)).getDeProducto();
                    case 2:return ((ProductoLocal)registros.get(rowIndex)).getDeUnidadFraccion();
                    case 3: return "";
                    case 4:return 0;
                    case 5:return 0;
                    case 6:return ((ProductoLocal)registros.get(rowIndex)).getVaPrecioPublico();
                    case 7:return 0;
                    case 8:return 0;
                }
                break;
            case PRO_PROMOCION:
                switch(columnIndex)
                 {
                     case 0:return ((PromocionDetalle)registros.get(rowIndex)).getPromocion();
                     case 1:return ((PromocionDetalle)registros.get(rowIndex)).getDeLaboratorioP ();
                 }
                break;
            default: return null;
        }
        
        return null;
    }    

  
}
