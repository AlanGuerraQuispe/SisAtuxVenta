package atux.util;

import atux.util.common.AtuxUtility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Created by user on 07/04/2015.
 */
public class FormatoTablaPedidos extends DefaultTableCellRenderer
{
    public Component getTableCellRendererComponent
            (JTable table, Object value, boolean isSelected, boolean focused, int row, int column)
    {

        JFormattedTextField campoTexto = new JFormattedTextField();
        //Se establece primeramente un borde vacío o sin borde
        campoTexto.setBorder(BorderFactory.createEmptyBorder());

        // SI EN CADA FILA DE LA TABLA LA CELDA 2 ES IGUAL PENDIENTE

        if(value instanceof String){
            campoTexto.setText((String) value);
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
            campoTexto.setValue(AtuxUtility.formatNumber(valor, 2));
        }

        if(value.equals("PENDIENTE")){
            campoTexto.setBackground(new Color(248,243,97));
            campoTexto.setForeground(new Color(187,0,0));
            campoTexto.setOpaque(true);
        }

        if(String.valueOf(table.getValueAt(row,2)).equals("PENDIENTE")){
            campoTexto.setBackground(new Color(248, 243, 97));
            campoTexto.setForeground(new Color(187,0,0));
            campoTexto.setOpaque(true);
        }else{
            campoTexto.setForeground(Color.black);
        }

        //Si el campo está¡ seleccionado se establece un color gris para distinguir
        if(isSelected){
            campoTexto.setBackground(new Color(192,192,192));
        }

        super.getTableCellRendererComponent(table, value, isSelected, focused, row, column);
        return campoTexto;
    }
}
