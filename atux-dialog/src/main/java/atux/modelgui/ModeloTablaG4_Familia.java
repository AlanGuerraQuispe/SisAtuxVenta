package atux.modelgui;

import atux.controllers.CG4_Familia;
import atux.modelbd.G4_Familia;

public class ModeloTablaG4_Familia extends ModeloTabla{

    String[] columnas = {"Cod. Nivel Sup.","Código","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {200,200,1000};
    
    public ModeloTablaG4_Familia() {
        cc = new CG4_Familia();
        this.nombreColumnas = columnas;       
        registros = ((CG4_Familia)cc).getRegistros();
    }
    
    public ModeloTablaG4_Familia(int opcion) {
        cc = new CG4_Familia();
        this.nombreColumnas = columnas;       
        registros = ((CG4_Familia)cc).getRegistros();
    }
    
    public ModeloTablaG4_Familia(String[] campo,Object[] valor) {
        cc = new CG4_Familia();
        this.nombreColumnas = columnas;       
        registros = ((CG4_Familia)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaG4_Familia(int inicio,int finalPag) {
        cc = new CG4_Familia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CG4_Familia)cc).getRegistros(null);
    }

    public ModeloTablaG4_Familia(int opcion,int inicio,int finalPag) {
        cc = new CG4_Familia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CG4_Familia)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CG4_Familia)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CG4_Familia)cc).getRegistros(null);
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
            case 0: return ((G4_Familia)registros.get(rowIndex)).getCoNivelSuperior();
            case 1: return ((G4_Familia)registros.get(rowIndex)).getCoNivel04();
            case 2: return ((G4_Familia)registros.get(rowIndex)).getDeGrupoTerapeutico();  
            default: return null;   
        }
    }
}