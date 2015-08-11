package atux.modelgui;

import atux.controllers.CTipoDocumento;
import atux.modelbd.TipoDocumento;

public class ModeloTablaTipoDocumento extends ModeloTabla{

    String[] columnas = {"CODIGO", "DESCRIPCION", "ESTADO", "FECHA CREACION"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,200,300};
    
    public ModeloTablaTipoDocumento() {
        cc = new CTipoDocumento();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDocumento)cc).getRegistros();
    }
    
    public ModeloTablaTipoDocumento(int opcion) {
        cc = new CTipoDocumento();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDocumento)cc).getRegistros();
    }
    
    public ModeloTablaTipoDocumento(String[] campo,Object[] valor) {
        cc = new CTipoDocumento();
        this.nombreColumnas = columnas;       
        registros = ((CTipoDocumento)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaTipoDocumento(int inicio,int finalPag) {
        cc = new CTipoDocumento();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CTipoDocumento)cc).getRegistros(null);
    }
    public ModeloTablaTipoDocumento(int opcion,int inicio,int finalPag) {
        cc = new CTipoDocumento();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CTipoDocumento)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CTipoDocumento)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CTipoDocumento)cc).getRegistros(null);
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
            case 0: return ((TipoDocumento)registros.get(rowIndex)).getCoDocumentoIdentidad();
            case 1: return ((TipoDocumento)registros.get(rowIndex)).getDeDocumentoIdentidad();     
            case 2: return ((TipoDocumento)registros.get(rowIndex)).getDeAbrDocumento();  
            case 3: return ((TipoDocumento)registros.get(rowIndex)).getEsDocumentoIdentidad(); 
            default: return null;   
        }
    }
}
