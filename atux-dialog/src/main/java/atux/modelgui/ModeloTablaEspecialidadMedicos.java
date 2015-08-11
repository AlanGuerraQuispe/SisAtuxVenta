package atux.modelgui;

import atux.controllers.CEspecialidadMedicos;
import atux.modelbd.EspecialidadMedicos;

public class ModeloTablaEspecialidadMedicos extends ModeloTabla{

    String[] columnas = {"Código","Especialidad"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,900};
    
    public ModeloTablaEspecialidadMedicos() {
        cc = new CEspecialidadMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CEspecialidadMedicos)cc).getRegistros();
    }
    
    public ModeloTablaEspecialidadMedicos(int opcion) {
        cc = new CEspecialidadMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CEspecialidadMedicos)cc).getRegistros();
    }
    
    public ModeloTablaEspecialidadMedicos(String[] campo,Object[] valor) {
        cc = new CEspecialidadMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CEspecialidadMedicos)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaEspecialidadMedicos(int inicio,int finalPag) {
        cc = new CEspecialidadMedicos();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CEspecialidadMedicos)cc).getRegistros(null);
    }
    public ModeloTablaEspecialidadMedicos(int opcion,int inicio,int finalPag) {
        cc = new CEspecialidadMedicos();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CEspecialidadMedicos)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CEspecialidadMedicos)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CEspecialidadMedicos)cc).getRegistros(null);
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
            case 0: return ((EspecialidadMedicos)registros.get(rowIndex)).getCoEspecialidad();
            case 1: return ((EspecialidadMedicos)registros.get(rowIndex)).getDeEspecialidad();     
            default: return null;
        }
    }
    
}
