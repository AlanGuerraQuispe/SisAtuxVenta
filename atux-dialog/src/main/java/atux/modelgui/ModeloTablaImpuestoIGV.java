package atux.modelgui;

import atux.controllers.CImpuestoIGV;
import atux.modelbd.ImpuestoIGV;

public class ModeloTablaImpuestoIGV extends ModeloTabla{

    String[] columnas = {"Codigo","Abreviatura","Descripcion","Valor"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {140,300,600,200};
    
    public ModeloTablaImpuestoIGV() {
        cc = new CImpuestoIGV();
        this.nombreColumnas = columnas;       
        registros = ((CImpuestoIGV)cc).getRegistros();
    }
    
    public ModeloTablaImpuestoIGV(int opcion) {
        cc = new CImpuestoIGV();
        this.nombreColumnas = columnas;       
        registros = ((CImpuestoIGV)cc).getRegistros();
    }
    
    public ModeloTablaImpuestoIGV(String[] campo,Object[] valor) {
        cc = new CImpuestoIGV();
        this.nombreColumnas = columnas;       
        registros = ((CImpuestoIGV)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaImpuestoIGV(int inicio,int finalPag) {
        cc = new CImpuestoIGV();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CImpuestoIGV)cc).getRegistros(null);
    }
    public ModeloTablaImpuestoIGV(int opcion,int inicio,int finalPag) {
        cc = new CImpuestoIGV();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CImpuestoIGV)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CImpuestoIGV)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CImpuestoIGV)cc).getRegistros(null);
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
            case 0: return ((ImpuestoIGV)registros.get(rowIndex)).getCoImpuesto();
            case 1: return ((ImpuestoIGV)registros.get(rowIndex)).getDeCortaImpuesto();     
            case 2: return ((ImpuestoIGV)registros.get(rowIndex)).getDeImpuesto();  
            case 3: return ((ImpuestoIGV)registros.get(rowIndex)).getPcImpuesto();  
            default: return null;   
        }
    }
    
}
