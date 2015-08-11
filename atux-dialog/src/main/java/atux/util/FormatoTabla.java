package atux.util;

import atux.util.common.AtuxUtility;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

//Implementa la interfaz TableCellRenderer que servirá¡ para dar formato a la tabla
public class FormatoTabla implements TableCellRenderer{
    
    //Sobreescribimos el Método getTableCellRendererComponent para poder formatear los componentes
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        //campoTexto nos sirve como componente dentro de la tabla, es cada una de las celdas de la misma
        JFormattedTextField campoTexto = new JFormattedTextField();
        //Se establece primeramente un borde vacío o sin borde
        campoTexto.setBorder(BorderFactory.createEmptyBorder());
        
        //Si el valor que contiene actualmente es cadena se establece el valor sin formatear en ningún sentido
        if(value instanceof String){
            //System.out.println("String value = " + value);
            campoTexto.setText((String)value);
        }
        //Si el valor que contiene actualmente es entero se establece el valor y se alinea al centro 
        if(value instanceof Integer){
            //System.out.println("Integer value = " + value);
            campoTexto.setText(value+"");
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);                                 
        }
        //Si el valor que contiene actualmente es Double se establece el valor
        //y se le aplica un formato para agregar comas cada tres dÃ­tigos
        if(value instanceof Double){
            //System.out.println("Double value = " + value);
            Double valor = (Double)value;                                                      
            
            //Se aplica alineación a la izquierda
            campoTexto.setHorizontalAlignment(SwingConstants.TRAILING); 
            campoTexto.setValue(AtuxUtility.formatNumber(valor,2));
            
            //Si el valor es negativo se elimina el símbolo - y se establece un fondo de color rojo
            //if(valor.compareTo(new Double(0))==-1) { 
              //campoTexto.setText(campoTexto.getText().replace("-", "")); 
              //campoTexto.setBackground(new Color(0xFE899B)); 
              //campoTexto.setOpaque(true); 
            //}                      
            
        }
           
        //Se verifica si el último valor de la fila es CERO por Stock se establece todas las celdas de dicha fila en color rojo 
        if(((Integer)table.getValueAt(row, table.getColumnCount()-2)).compareTo(new Integer(0))==0){             
            campoTexto.setBackground(new Color(248,243,97));             
            campoTexto.setForeground(new Color(187,0,0)); 
            campoTexto.setOpaque(true); 
        }else{
            campoTexto.setForeground(new Color(0,0,100)); 
        }        
                
        //Si el campo está¡ seleccionado se establece un color gris para distinguir
        if(isSelected){ 
            campoTexto.setBackground(new Color(192,192,192)); 
        }
        
        //Por último se devuelve el componente ya con el formato establecido para mostrarlo en la tabla
        return campoTexto;
    }
    
}
