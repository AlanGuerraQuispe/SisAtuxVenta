package com.aw.swing.mvp.binding.component.support.table.header;

import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * User: gmc
 * Date: 21/08/2009
 */
public class JCheckBoxCellHeaderRenderer extends JList implements TableCellRenderer, MouseListener {
    JTable jtable;
    SelectorColumn selectorColumn;
    protected int column;
    protected boolean mousePressed = false;
    JCheckBox jCheckBox;

    public JCheckBoxCellHeaderRenderer(JTable jtable, SelectorColumn selectorColumn) {
        this.selectorColumn = selectorColumn;
        setJtable(jtable);
        init();
    }

    private void init() {
        setCellRenderer(new JCheckBoxListCellRenderer());
        Vector v = new Vector();
        v.add("value");
        setListData(v);
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setColumn(column);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
//        setBorder(BorderFactory.createLineBorder(Color.white));
        return this;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    protected void handleClickEvent(MouseEvent e) {
        if (mousePressed) {
            mousePressed = false;
            JTableHeader header = (JTableHeader) (e.getSource());
            JTable tableView = header.getTable();
            TableColumnModel columnModel = tableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = tableView.convertColumnIndexToModel(viewColumn);
            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                jCheckBox.doClick();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);
        ((JTableHeader) e.getSource()).repaint();
    }

    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private class JCheckBoxListCellRenderer extends JPanel implements ListCellRenderer {
        JCheckBoxListCellRenderer() {
            setLayout(new BorderLayout());
            createJCheckBox();
            add(jCheckBox, BorderLayout.CENTER);
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            jCheckBox.setBackground(list.getBackground());
            jCheckBox.setForeground(list.getForeground());
            return this;
        }

        private void createJCheckBox() {
            jCheckBox = new JCheckBox();
            jCheckBox.setOpaque(true);
            jCheckBox.addItemListener(getItemListener());
        }

        private ItemListener getItemListener() {
            return new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    Object source = e.getSource();
                    if (callDirectly) return;
                    if (source instanceof AbstractButton == false) return;
                    if (jtable.isEditing()){
                        jtable.removeEditor();
                    }
                    boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                    if (checked) {
                        selectorColumn.selectAll(jtable);
                    } else {
                        selectorColumn.clear(jtable);
                    }
                    jtable.repaint();
                    JTable otherTable= (JTable) jtable.getClientProperty(BndSJTable.CP_MAIN_TABLE);
                    if (otherTable==null){
                        otherTable= (JTable) jtable.getClientProperty(BndSJTable.CP_FIXED_TABLE);
                    }
                    if (otherTable!=null){
                        otherTable.repaint();
                    }
                }
            };
        }
    }

    public void setJtable(JTable jtable) {
        if (this.jtable !=null){
            this.jtable.getTableHeader().removeMouseListener(this);
        }
        this.jtable = jtable;
        if (jtable != null) {
            JTableHeader header = jtable.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
                header.addMouseListener(this);
            }
        }
    }

    boolean callDirectly = false;
    public void cleanJCheckBox(){
        callDirectly = true;
        jCheckBox.setSelected(false);
        callDirectly = false;
    }

    public void checkJCheckBox(){
        callDirectly = true;
        jCheckBox.setSelected(true);
        callDirectly = false;
    }

}