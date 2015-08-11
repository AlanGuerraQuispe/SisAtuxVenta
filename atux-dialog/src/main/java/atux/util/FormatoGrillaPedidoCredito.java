package atux.util;

import atux.util.common.AtuxUtility;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//Implementa la interfaz TableCellRenderer que servirá¡ para dar formato a la tabla
public class FormatoGrillaPedidoCredito implements TableCellRenderer{

    //Sobreescribimos el método getTableCellRendererComponent para poder formatear los componentes
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        //campoTexto nos sirve como componente dentro de la tabla, es cada una de las celdas de la misma
        JFormattedTextField campoTexto = new JFormattedTextField();
        //Se establece primeramente un borde vacío o sin borde
        campoTexto.setBorder(BorderFactory.createEmptyBorder());

        //Si el valor que contiene actualmente es cadena se establece el valor sin formatear en ningún sentido

        if(value instanceof String){
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
            campoTexto.setText((String)value);
        }

        if(row==0){
            if(column==7){ //Posición de la columna del monto a Credito.
                campoTexto.setForeground(new Color(187, 0, 0));
            }
            campoTexto.setBackground(new Color(248, 243, 97));
            campoTexto.setOpaque(true);
        }

        //Si el valor que contiene actualmente es entero se establece el valor y se alinea al centro 
        if(value instanceof Integer){
            //System.out.println("Integer value = " + value);
            campoTexto.setText(value+"");
            campoTexto.setHorizontalAlignment(SwingConstants.CENTER);
        }
        //Si el valor que contiene actualmente es Double se establece el valor
        //y se le aplica un formato para agregar comas cada tres dítigos
        if(value instanceof Double){
            //System.out.println("Double value = " + value);
            Double valor = (Double)value;

            //Se aplica alineación a la izquierda
            campoTexto.setHorizontalAlignment(SwingConstants.TRAILING);
            campoTexto.setValue(AtuxUtility.formatNumber(valor,2));

        }

        //Si el campo está¡ seleccionado se establece un color gris para distinguir
        if(isSelected){
            campoTexto.setBackground(new Color(192,192,192));
        }

        //Por último se devuelve el componente ya con el formato establecido para mostrarlo en la tabla
        return campoTexto;
    }

}
