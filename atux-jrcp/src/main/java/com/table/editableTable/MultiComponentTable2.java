package com.table.editableTable;

import com.aw.swing.mvp.grid.TableKeyManager;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;
import java.util.Hashtable;

public class MultiComponentTable2 extends JFrame {

    public MultiComponentTable2() {
        super("MultiComponent Table");

        DefaultTableModel dm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return true;
                }
                return false;
            }
        };
        dm.setDataVector(new Object[][]{
                {"true", "String", "JLabel", "JComboBox"},
                {"false", "String", "JLabel", "JComboBox"},
                {new Boolean(true), "Boolean", "JCheckBox", "JCheckBox"},
                {new Boolean(false), "Boolean", "JCheckBox", "JCheckBox"},
                {"true", "String", "JLabel", "JTextField"},
                {"false", "String", "JLabel", "JTextField"}}, new Object[]{
                "Component", "Data", "Renderer", "Editor"});

        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        EachRowRenderer rowRenderer = new EachRowRenderer();
        rowRenderer.add(2, checkBoxRenderer);
        rowRenderer.add(3, checkBoxRenderer);
        final JComboBox comboBox = new JComboBox();
        comboBox.addItem("true");
        comboBox.addItem("false");
        comboBox.setRequestFocusEnabled(true);
        comboBox.putClientProperty("awCellEditor", Boolean.TRUE);

        comboBox.addAncestorListener(new AncestorListener() {

            public void ancestorAdded(AncestorEvent event) {
                comboBox.requestFocusInWindow();
            }

            public void ancestorMoved(AncestorEvent event) {
            }

            public void ancestorRemoved(AncestorEvent event) {
            }
        });


        JCheckBox checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(JLabel.CENTER);
        DefaultCellEditor comboBoxEditor = new DefaultCellEditor(comboBox);
        DefaultCellEditor checkBoxEditor = new DefaultCellEditor(checkBox);
        JTable table = new JTable(dm);

        TableKeyManager.decorate(table);

// modified by java2s.com

        EachRowEditor rowEditor = new EachRowEditor(table);
        rowEditor.setEditorAt(0, comboBoxEditor);
        rowEditor.setEditorAt(1, comboBoxEditor);
        rowEditor.setEditorAt(2, checkBoxEditor);
        rowEditor.setEditorAt(3, checkBoxEditor);

// end

        table.getColumn("Component").setCellRenderer(rowRenderer);
        table.getColumn("Component").setCellEditor(rowEditor);

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(400, 160);
        setVisible(true);
    }

    public static void main(String[] args) {
        MultiComponentTable2 frame = new MultiComponentTable2();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}


class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    CheckBoxRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            //super.setBackground(table.getSelectionBackground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
    }

}

class EachRowRenderer implements TableCellRenderer {
    protected Hashtable renderers;

    protected TableCellRenderer renderer, defaultRenderer;

    public EachRowRenderer() {
        renderers = new Hashtable();
        defaultRenderer = new DefaultTableCellRenderer();
    }

    public void add(int row, TableCellRenderer renderer) {
        renderers.put(new Integer(row), renderer);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        renderer = (TableCellRenderer) renderers.get(new Integer(row));
        if (renderer == null) {
            renderer = defaultRenderer;
        }
        return renderer.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}


class EachRowEditor implements TableCellEditor {
    protected Hashtable editors;

    protected TableCellEditor editor, defaultEditor;

    JTable table;

    /**
     * Constructs a EachRowEditor. create default editor
     *
     * @see TableCellEditor
     * @see DefaultCellEditor
     */
    public EachRowEditor(JTable table) {
        this.table = table;
        editors = new Hashtable();
        defaultEditor = new DefaultCellEditor(new JTextField());
    }

    /**
     * @param row    table row
     * @param editor table cell editor
     */
    public void setEditorAt(int row, TableCellEditor editor) {
        editors.put(new Integer(row), editor);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        //editor = (TableCellEditor)editors.get(new Integer(row));
        //if (editor == null) {
        //  editor = defaultEditor;
        //}
        Component cmp = editor.getTableCellEditorComponent(table, value, isSelected,
                row, column);
        if (cmp instanceof JTextField){
            ((JTextField)cmp).selectAll();                    
        }
        return cmp;
    }

    public Object getCellEditorValue() {
        return editor.getCellEditorValue();
    }

    public boolean stopCellEditing() {
        return editor.stopCellEditing();
    }

    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    public boolean isCellEditable(EventObject anEvent) {
        selectEditor(anEvent);
        return editor.isCellEditable(anEvent);
    }

    public void addCellEditorListener(CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        selectEditor(anEvent);
        return editor.shouldSelectCell(anEvent);
    }

    protected void selectEditor(Object e) {
        int index = table.getSelectionModel().getAnchorSelectionIndex();
        System.out.println("index:" + index);
        System.out.println("editing Row:" + table.getEditingRow());
        System.out.println("selected Row:" + table.getSelectedRow());
        int row = table.getSelectedRow();

//        int row;
//        if (e == null) {
//            row = table.getSelectionModel().getAnchorSelectionIndex();
//        } else {
//            row = table.rowAtPoint(e.getPoint());
//        }
        editor = (TableCellEditor) editors.get(new Integer(row));
        if (editor == null) {
            editor = defaultEditor;
        }
    }
}

