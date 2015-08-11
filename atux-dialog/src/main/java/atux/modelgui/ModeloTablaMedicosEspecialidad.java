package atux.modelgui;

import atux.controllers.CMedicosEspecialidad;
import atux.modelbd.MedicosEspecialidad;

public class ModeloTablaMedicosEspecialidad extends ModeloTabla{


    
    String[] columnas = {"Código","Especialidad"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {90,1200};
    
    public ModeloTablaMedicosEspecialidad() {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;       
        registros = ((CMedicosEspecialidad)cc).getRegistros();
    }
    
    public ModeloTablaMedicosEspecialidad(int opcion) {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;       
        registros = ((CMedicosEspecialidad)cc).getRegistros();
    }

    public ModeloTablaMedicosEspecialidad(String opcion) {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;       
        registros = ((CMedicosEspecialidad)cc).getRegistros(opcion);
    }
    
    public ModeloTablaMedicosEspecialidad(String[] campo,Object[] valor) {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;       
        registros = ((CMedicosEspecialidad)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaMedicosEspecialidad(int inicio,int finalPag) {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CMedicosEspecialidad)cc).getRegistros("");
    }

    public ModeloTablaMedicosEspecialidad(int opcion,int inicio,int finalPag) {
        cc = new CMedicosEspecialidad();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CMedicosEspecialidad)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CMedicosEspecialidad)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CMedicosEspecialidad)cc).getRegistros("");
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
            case 0: return ((MedicosEspecialidad)registros.get(rowIndex)).getCoEspecialidad();
            case 1: return ((MedicosEspecialidad)registros.get(rowIndex)).getDeEspecialidad();     
//            case 2: return ((MedicosEspecialidad)registros.get(rowIndex)).getFeCreaMedico();  
            default: return null;
        }
    }
    
}
