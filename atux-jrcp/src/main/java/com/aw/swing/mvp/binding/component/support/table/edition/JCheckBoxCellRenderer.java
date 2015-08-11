package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.CellColorChanger;
import com.aw.swing.mvp.ui.UIColorConstants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JCheckBoxCellRenderer extends JCheckBox
        implements TableCellRenderer {
    protected Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    protected Border m_focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
    protected Object valueTrue;
    protected Object valueFalse;
    protected ColumnInfo columnInfo;
    protected BndSJTable bndSJTable;

    public JCheckBoxCellRenderer(ColumnInfo columnInfo,Object valueTrue) {
        super();
        setOpaque(true);
        setBorderPainted(true);
        setBorder(m_noFocusBorder);
        setHorizontalAlignment(JCheckBox.CENTER);
        this.valueTrue = valueTrue;
        this.columnInfo = columnInfo;
    }

    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int nRow, int nCol) {
        boolean checkSelected = false;
        if (value instanceof Boolean) {
            Boolean b = (Boolean) value;
            checkSelected = b.booleanValue();
        } else if (value instanceof Object) {
            checkSelected = value.equals(valueTrue);
        }
        setSelected(checkSelected);
        boolean paint = (isSelected || hasFocus);
        setBackground(paint ?
                table.getSelectionBackground() : table.getBackground());
        setForeground(paint ?
                table.getSelectionForeground() : table.getForeground());
        setFont(table.getFont());
        if (!columnInfo.isEditable()||!table.isCellEditable(nRow, nCol)){
            if (isSelected){
                setBackground(UIColorConstants.BKG_CELL_SELECTED_DISABLED);
            }else{
                setBackground(UIColorConstants.BKG_CELL_DISABLED);                    
            }
        }
        if(!isSelected && (bndSJTable!=null &&  bndSJTable.getCellColorChanger()!=null)){
            CellColorChanger cellColorChanger = bndSJTable.getCellColorChanger();
            setBackground(cellColorChanger.getBackGround(bndSJTable.getValues().get(nRow),nRow,columnInfo.getFieldName()));
        }
        return this;
    }

    public void setBndSJTable(BndSJTable bndSJTable) {
        this.bndSJTable = bndSJTable;
    }
}
