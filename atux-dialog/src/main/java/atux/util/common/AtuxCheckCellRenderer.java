package atux.util.common;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
* Copyright (c) 2002 Eckerd Peru S.A.<br>
* <br>
* Entorno de Desarrollo   : Oracle JDeveloper 9i<br>
* Nombre de la Aplicación : AtuxCheckCellRenderer.java<br>
* <br>
* Histórico de Creación/Modificación<br>
* RCASTRO       19.11.2002   Creación<br>
* <br>
* @author Rolando Castro Morán<br>
* @version 1.0<br>
*
*/
public class AtuxCheckCellRenderer extends JCheckBox implements TableCellRenderer {

  /** Objeto que almacena el borde necesario para el JCheckBox */
  protected static Border m_noFocusBorder;

  /**
  *Constructor : Inicializa el objeto JCheckBox con el borde adecuado
  */   
  public AtuxCheckCellRenderer() {
    super();
    m_noFocusBorder = new EmptyBorder(1, 2, 1, 2);
    setOpaque(true);
    setBorder(m_noFocusBorder);
	}
  /**
  *Constructor : Inicializa la estructura de tabla, incluyendo número de
  *columnas y cabecera de columnas.  También inicializa la data de la tabla
  *con valores por defecto.
  *@param <b>table</b> Objeto JTable que contiene al Objeto JCheckBox.
  *@param <b>value</b> Valor (Boolean) que tiene actualmente el Objeto JCcheckBox.
  *@param <b>isSelected</b> Variable boolean que determina si el row se encuentra seleccionado.
  *@param <b>hasFocus</b> Variable boolean que determina si el foco está en este objeto.
  *@param <b>row</b> Variable int que almacena el número de fila del JTable.
  *@param <b>column</b> Variable int que almacena el número de columna del JTable.
  *@return <b>Component</b> Retorna el objeto JCheckBox con check o sin check.
  */
  public Component getTableCellRendererComponent(JTable table,
                                                 Object value, 
                                                 boolean isSelected, 
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {
    if (value instanceof Boolean) {
      Boolean b = (Boolean)value;
      setSelected(b.booleanValue());
    }
    setBackground(isSelected && !hasFocus ? table.getSelectionBackground() : table.getBackground());
    setForeground(isSelected && !hasFocus ? table.getSelectionForeground() : table.getForeground());
    setFont(table.getFont());
    setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : m_noFocusBorder);
    return this;
  }

}

 