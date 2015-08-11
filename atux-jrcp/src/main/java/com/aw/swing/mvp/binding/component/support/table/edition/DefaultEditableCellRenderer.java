package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.grid.CellColorChanger;
import com.aw.swing.mvp.ui.UIColorConstants;
import com.aw.swing.mvp.ui.painter.ColorInfoProvider;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * User: gmc
 * Date: 08/06/2009
 */
public class DefaultEditableCellRenderer extends DefaultTableCellRenderer {

    ColumnInfo columnInfo;
    CellColorChanger cellColorChanger;

    public DefaultEditableCellRenderer(ColumnInfo columnInfo) {
        this.columnInfo = columnInfo;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component cmp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (columnInfo.isRequired() && isEmpty(value)) {
            cmp.setBackground(ColorInfoProvider.COLOR_ERROR);
            setBorder(new EmptyBorder(new Insets(1, 4, 1, 4)));
            ((JComponent) cmp).setBorder(new EmptyBorder(new Insets(1, 4, 1, 4)));
        } else {
            if (isSelected) {
                super.setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                Color background = table.getBackground();
                if (background == null || background instanceof javax.swing.plaf.UIResource) {
                    Color alternateColor = DefaultLookup.getColor(this, ui, "Table.alternateRowColor");
                    if (alternateColor != null && row % 2 == 0)
                        background = alternateColor;
                }
                super.setForeground(table.getForeground());
                super.setBackground(background);
                if (cellColorChanger != null) {
                    Object obj = ((JTableModel) table.getModel()).getRowAt(row);
                    Color foreGroundColor = cellColorChanger.getForeGround(obj, row, columnInfo.getFieldName());
                    if (foreGroundColor != null) {
                        setForeground(foreGroundColor);
                    }
                }
            }

            setFont(table.getFont());

            if (hasFocus) {
                Border border = null;
                if (isSelected) {
                    border = DefaultLookup.getBorder(this, ui, "Table.focusSelectedCellHighlightBorder");
                }
                if (border == null) {
                    border = DefaultLookup.getBorder(this, ui, "Table.focusCellHighlightBorder");
                }
                setBorder(border);

                if (!isSelected && table.isCellEditable(row, column)) {
                    Color col;
                    col = DefaultLookup.getColor(this, ui, "Table.focusCellForeground");
                    if (col != null) {
                        super.setForeground(col);
                    }
                    col = DefaultLookup.getColor(this, ui, "Table.focusCellBackground");
                    if (col != null) {
                        super.setBackground(col);
                    }
                }
            } else {
                setBorder(getNoFocusBorder());
            }
            setBorder(new EmptyBorder(new Insets(1, 4, 1, 4)));
        }
        if (!table.isCellEditable(row, column)) {
            if (isSelected) {
                setBackground(UIColorConstants.BKG_CELL_SELECTED_DISABLED);
            } else {
                setBackground(UIColorConstants.BKG_CELL_DISABLED);
            }
        }
        return cmp;
    }

    private boolean isEmpty(Object value) {
        return value == null || ((value instanceof String) && "".equals(value));
    }

    public void setColumnInfo(ColumnInfo columnInfo) {
        this.columnInfo = columnInfo;
    }

    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border getNoFocusBorder() {
        Border border = DefaultLookup.getBorder(this, ui, "Table.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) return border;
            return SAFE_NO_FOCUS_BORDER;
        } else if (border != null) {
            if (noFocusBorder == null || noFocusBorder == DEFAULT_NO_FOCUS_BORDER) {
                return border;
            }
        }
        return noFocusBorder;
    }


    public void setCellColorChanger(CellColorChanger cellColorChanger) {
        this.cellColorChanger = cellColorChanger;
    }
}
