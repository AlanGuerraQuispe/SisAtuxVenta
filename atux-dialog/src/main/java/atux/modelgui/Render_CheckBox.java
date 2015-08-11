package atux.modelgui;

import atux.modelbd.ProductoLocal;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Render_CheckBox extends JCheckBox implements TableCellRenderer {

    private JComponent component = new JCheckBox();
    ModeloTablaProducto mtp;
    javax.swing.JTable tblProductos;
    ProductoLocal prodLocal;
    
    /** Constructor de clase */
    public Render_CheckBox() {
        setOpaque(true);
    }

    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      //Color de fondo de la celda
      ( (JCheckBox) component).setBackground( new Color(255,255,255) );
      
      boolean b = ((Boolean) value).booleanValue();
      ( (JCheckBox) component).setSelected( b );
                
      return ( (JCheckBox) component);
  }
        
}