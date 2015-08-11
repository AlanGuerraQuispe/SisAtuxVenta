package atux.modelgui;

import atux.controllers.CUbigeo;
import atux.modelbd.Ubigeo;

public class ModeloTablaUbigeo extends ModeloTabla{

    String[] columnas = {"Código","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600};
    
    public ModeloTablaUbigeo(String Buscar) {
        cc = new CUbigeo();
        this.nombreColumnas = columnas;       
        registros = ((CUbigeo)cc).getDepartamento(Buscar);
    }

    public ModeloTablaUbigeo(String Buscar, String coDepartamento) {
        cc = new CUbigeo();
        this.nombreColumnas = columnas;       
        registros = ((CUbigeo)cc).getProvincia(Buscar, coDepartamento);
    }

    public ModeloTablaUbigeo(String Buscar, String coDepartamento, String coProvincia) {
        cc = new CUbigeo();
        this.nombreColumnas = columnas;       
        registros = ((CUbigeo)cc).getDistrito(Buscar, coDepartamento, coProvincia);
    }

    public ModeloTablaUbigeo(int opcion) {
        cc = new CUbigeo();
        this.nombreColumnas = columnas;       
        registros = ((CUbigeo)cc).getRegistros();
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
            case 0: return ((Ubigeo)registros.get(rowIndex)).getCoUbigeo();
            case 1: return ((Ubigeo)registros.get(rowIndex)).getDeUbigeo();     
            default: return null;   
        }
    }
    
}
