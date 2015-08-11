package atux.modelgui;

import atux.controllers.CG5_SubFamilia;
import atux.modelbd.G5_SubFamilia;

public class ModeloTablaG5_SubFamilia extends ModeloTabla{

    String[] columnas = {"Cod. Nivel Sup.","Código","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {200,200,1000};
    
    public ModeloTablaG5_SubFamilia() {
        cc = new CG5_SubFamilia();
        this.nombreColumnas = columnas;       
        registros = ((CG5_SubFamilia)cc).getRegistros();
    }
    
    public ModeloTablaG5_SubFamilia(int opcion) {
        cc = new CG5_SubFamilia();
        this.nombreColumnas = columnas;       
        registros = ((CG5_SubFamilia)cc).getRegistros();
    }
    
    public ModeloTablaG5_SubFamilia(String[] campo,Object[] valor) {
        cc = new CG5_SubFamilia();
        this.nombreColumnas = columnas;       
        registros = ((CG5_SubFamilia)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaG5_SubFamilia(int inicio,int finalPag) {
        cc = new CG5_SubFamilia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CG5_SubFamilia)cc).getRegistros(null);
    }

    public ModeloTablaG5_SubFamilia(int opcion,int inicio,int finalPag) {
        cc = new CG5_SubFamilia();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CG5_SubFamilia)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CG5_SubFamilia)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CG5_SubFamilia)cc).getRegistros(null);
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
            case 0: return ((G5_SubFamilia)registros.get(rowIndex)).getCoNivelSuperior();
            case 1: return ((G5_SubFamilia)registros.get(rowIndex)).getCoNivel05();
            case 2: return ((G5_SubFamilia)registros.get(rowIndex)).getDeAccionTerapeutica();  
            default: return null;   
        }
    }
}