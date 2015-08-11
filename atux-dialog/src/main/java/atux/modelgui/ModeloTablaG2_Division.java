package atux.modelgui;

import atux.controllers.CG2_Division;
import atux.modelbd.G2_Division;

public class ModeloTablaG2_Division extends ModeloTabla{

    String[] columnas = {"Cod. Nivel Sup.","Código","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {200,200,1000};
    
    public ModeloTablaG2_Division() {
        cc = new CG2_Division();
        this.nombreColumnas = columnas;       
        registros = ((CG2_Division)cc).getRegistros();
    }
    
    public ModeloTablaG2_Division(int opcion) {
        cc = new CG2_Division();
        this.nombreColumnas = columnas;       
        registros = ((CG2_Division)cc).getRegistros();
    }
    
    public ModeloTablaG2_Division(String[] campo,Object[] valor) {
        cc = new CG2_Division();
        this.nombreColumnas = columnas;       
        registros = ((CG2_Division)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaG2_Division(int inicio,int finalPag) {
        cc = new CG2_Division();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CG2_Division)cc).getRegistros(null);
    }
    public ModeloTablaG2_Division(int opcion,int inicio,int finalPag) {
        cc = new CG2_Division();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CG2_Division)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CG2_Division)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CG2_Division)cc).getRegistros(null);
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
            case 0: return ((G2_Division)registros.get(rowIndex)).getCoNivelSuperior();
            case 1: return ((G2_Division)registros.get(rowIndex)).getCoNivel02();     
            case 2: return ((G2_Division)registros.get(rowIndex)).getDeGrupoProdErp();  
            default: return null;   
        }
    }
    
}
