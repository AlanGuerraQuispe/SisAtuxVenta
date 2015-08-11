package atux.modelgui;

import atux.controllers.CG3_SubDivision;
import atux.modelbd.G3_SubDivision;

public class ModeloTablaG3_SubDivision extends ModeloTabla{

    String[] columnas = {"Cod. Nivel Sup.","Código","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {200,200,1000};
    
    public ModeloTablaG3_SubDivision() {
        cc = new CG3_SubDivision();
        this.nombreColumnas = columnas;       
        registros = ((CG3_SubDivision)cc).getRegistros();
    }
    
    public ModeloTablaG3_SubDivision(int opcion) {
        cc = new CG3_SubDivision();
        this.nombreColumnas = columnas;       
        registros = ((CG3_SubDivision)cc).getRegistros();
    }
    
    public ModeloTablaG3_SubDivision(String[] campo,Object[] valor) {
        cc = new CG3_SubDivision();
        this.nombreColumnas = columnas;       
        registros = ((CG3_SubDivision)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaG3_SubDivision(int inicio,int finalPag) {
        cc = new CG3_SubDivision();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CG3_SubDivision)cc).getRegistros(null);
    }
    public ModeloTablaG3_SubDivision(int opcion,int inicio,int finalPag) {
        cc = new CG3_SubDivision();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CG3_SubDivision)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CG3_SubDivision)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CG3_SubDivision)cc).getRegistros(null);
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
            case 0: return ((G3_SubDivision)registros.get(rowIndex)).getCoNivelSuperior();
            case 1: return ((G3_SubDivision)registros.get(rowIndex)).getCoNivel03();     
            case 2: return ((G3_SubDivision)registros.get(rowIndex)).getDeGrupoAnatomico();  
            default: return null;   
        }
    }
}
