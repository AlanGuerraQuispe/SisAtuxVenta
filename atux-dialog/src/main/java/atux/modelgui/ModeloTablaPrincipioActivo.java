package atux.modelgui;

import atux.controllers.CPrincipioActivo;
import atux.modelbd.PrincipioActivo;
import atux.util.common.AtuxUtility;


public class ModeloTablaPrincipioActivo extends ModeloTabla{

    String[] columnas = {"CODIGO", "DESCRIPCION", "ESTADO", "FECHA CREACION"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,800,200,200};
    
    public ModeloTablaPrincipioActivo() {
        cc = new CPrincipioActivo();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivo)cc).getRegistros();
    }
    
    public ModeloTablaPrincipioActivo(int opcion) {
        cc = new CPrincipioActivo();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivo)cc).getRegistros();
    }
    
    public ModeloTablaPrincipioActivo(String[] campo,Object[] valor) {
        cc = new CPrincipioActivo();
        this.nombreColumnas = columnas;       
        registros = ((CPrincipioActivo)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaPrincipioActivo(int inicio,int finalPag) {
        cc = new CPrincipioActivo();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CPrincipioActivo)cc).getRegistros(null);
    }
    public ModeloTablaPrincipioActivo(int opcion,int inicio,int finalPag) {
        cc = new CPrincipioActivo();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CPrincipioActivo)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CPrincipioActivo)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CPrincipioActivo)cc).getRegistros(null);
        }
        
    }
    
    public int getCantidadRegistros()
    {
        return cc.getCantidadRegistros();
    }  

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {        
        switch(columnIndex)
        {
            case 0: return ((PrincipioActivo)registros.get(rowIndex)).getCoPrincipioActivo();
            case 1: return ((PrincipioActivo)registros.get(rowIndex)).getDePrincipioActivo();     
            case 2: return ((PrincipioActivo)registros.get(rowIndex)).getEsPrincipioActivo();  
            case 3: return AtuxUtility.getDateToString(((PrincipioActivo)registros.get(rowIndex)).getFeCreaPrincipioActivo(), "dd/MM/yyyy");                    
            default: return null;   
        }
    }
}
