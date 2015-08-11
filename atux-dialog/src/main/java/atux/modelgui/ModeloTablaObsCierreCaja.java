package atux.modelgui;

import atux.modelbd.ObsCaja;
import java.util.ArrayList;


public class ModeloTablaObsCierreCaja extends ModeloTabla{

    private Class[] tipoColumnas;    
        
    public static final Integer[] anchoColumnas  = {20,550};

    public ModeloTablaObsCierreCaja(ArrayList<ObsCaja> pedidos) {
        this.registros = pedidos;

        this.nombreColumnas = new String[]{"Orden","Descripción"};
        
        tipoColumnas = new Class[]{String.class,String.class};
    } 
    
    public void agregarFila(ObsCaja obsCaja){
        this.registros.add(obsCaja);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }                    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex)
           {             
                case 0:return ((ObsCaja)registros.get(rowIndex)).getnuSecObs(); 
                case 1:return ((ObsCaja)registros.get(rowIndex)).getdeObs(); 
            default:return null;    
        }       
    }       
}
