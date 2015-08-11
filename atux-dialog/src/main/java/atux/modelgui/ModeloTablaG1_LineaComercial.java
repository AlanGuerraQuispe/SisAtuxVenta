package atux.modelgui;

import atux.controllers.CG1_LineaComercial;
import atux.modelbd.G1_LineaComercial;

public class ModeloTablaG1_LineaComercial extends ModeloTabla{

    String[] columnas = {"Codigo Nivel Superior","Codigo","Descripción"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {100,100,800};
    
    public ModeloTablaG1_LineaComercial() {
        cc = new CG1_LineaComercial();
        this.nombreColumnas = columnas;       
        registros = ((CG1_LineaComercial)cc).getRegistros();
    }
    
    public ModeloTablaG1_LineaComercial(int opcion) {
        cc = new CG1_LineaComercial();
        this.nombreColumnas = columnas;       
        registros = ((CG1_LineaComercial)cc).getRegistros();
    }
    
    public ModeloTablaG1_LineaComercial(String[] campo,Object[] valor) {
        cc = new CG1_LineaComercial();
        this.nombreColumnas = columnas;       
        registros = ((CG1_LineaComercial)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaG1_LineaComercial(int inicio,int finalPag) {
        cc = new CG1_LineaComercial();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CG1_LineaComercial)cc).getRegistros(null);
    }
    public ModeloTablaG1_LineaComercial(int opcion,int inicio,int finalPag) {
        cc = new CG1_LineaComercial();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CG1_LineaComercial)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CG1_LineaComercial)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CG1_LineaComercial)cc).getRegistros(null);
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
            case 0: return ((G1_LineaComercial)registros.get(rowIndex)).getCoNivelSuperior();
            case 1: return ((G1_LineaComercial)registros.get(rowIndex)).getCoNivel01();     
            case 2: return ((G1_LineaComercial)registros.get(rowIndex)).getDeLineaProdErp();  
            default: return null;   
        }
    }
    
}
