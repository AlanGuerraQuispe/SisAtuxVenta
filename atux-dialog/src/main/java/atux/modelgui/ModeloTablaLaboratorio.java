package atux.modelgui;

import atux.controllers.CLaboratorio;
import atux.modelbd.Laboratorio;

public class ModeloTablaLaboratorio extends ModeloTabla{

    String[] columnas = {"Abreviatura","Proveedor","Direccion","Telefono"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,500,600,200};
    
    public ModeloTablaLaboratorio() {
        cc = new CLaboratorio();
        this.nombreColumnas = columnas;       
        registros = ((CLaboratorio)cc).getRegistros();
    }
    
    public ModeloTablaLaboratorio(int opcion) {
        cc = new CLaboratorio();
        this.nombreColumnas = columnas;       
        registros = ((CLaboratorio)cc).getRegistros();
    }
    
    public ModeloTablaLaboratorio(String[] campo,Object[] valor) {
        cc = new CLaboratorio();
        this.nombreColumnas = columnas;       
        registros = ((CLaboratorio)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaLaboratorio(int inicio,int finalPag) {
        cc = new CLaboratorio();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CLaboratorio)cc).getRegistros(null);
    }
    public ModeloTablaLaboratorio(int opcion,int inicio,int finalPag) {
        cc = new CLaboratorio();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CLaboratorio)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CLaboratorio)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CLaboratorio)cc).getRegistros(null);
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
            case 0: return ((Laboratorio)registros.get(rowIndex)).getDeAbrevLab();
            case 1: return ((Laboratorio)registros.get(rowIndex)).getDeLaboratorio();     
            case 2: return ((Laboratorio)registros.get(rowIndex)).getDeDirecLaboratorio();  
            case 3: return ((Laboratorio)registros.get(rowIndex)).getNuTelefLaboratorio();  
            default: return null;   
        }
    }
    
}
