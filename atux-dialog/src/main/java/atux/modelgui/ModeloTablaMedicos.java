package atux.modelgui;

import atux.controllers.CMedicos;
import atux.modelbd.Medicos;

public class ModeloTablaMedicos extends ModeloTabla{


    
    String[] columnas = {"Código","Apellidos y Nombres","CMP"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,600,300};
    
    public ModeloTablaMedicos() {
        cc = new CMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CMedicos)cc).getRegistros();
    }
    
    public ModeloTablaMedicos(int opcion) {
        cc = new CMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CMedicos)cc).getRegistros();
    }

    public ModeloTablaMedicos(String Filtro) {
        cc = new CMedicos();
        this.nombreColumnas = columnas;
        registros = ((CMedicos)cc).getBusquedaMedicos(Filtro);
    }        
    
    public ModeloTablaMedicos(String[] campo,Object[] valor) {
        cc = new CMedicos();
        this.nombreColumnas = columnas;       
        registros = ((CMedicos)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaMedicos(int inicio,int finalPag) {
        cc = new CMedicos();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CMedicos)cc).getRegistros(null);
    }
    public ModeloTablaMedicos(int opcion,int inicio,int finalPag) {
        cc = new CMedicos();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CMedicos)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CMedicos)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CMedicos)cc).getRegistros(null);
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
            case 0: return ((Medicos)registros.get(rowIndex)).getCoMedico();
            case 1: return ((Medicos)registros.get(rowIndex)).getDeMedico();     
            case 2: return ((Medicos)registros.get(rowIndex)).getNuCmp();  
            default: return null;
        }
    }
    
}
