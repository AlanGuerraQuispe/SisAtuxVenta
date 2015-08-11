package atux.util;

import atux.vistas.venta.PanelAccionProdInsumos;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AccionTableCellRendererInsumo implements TableCellRenderer{
    private PanelAccionProdInsumos test;

    public AccionTableCellRendererInsumo(final JInternalFrame ifr) {
       test = new PanelAccionProdInsumos(ifr);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        test.setTabla(table);        
        return test;
    }
    
}
