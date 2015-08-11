package com.aw.swing.mvp.binding.component.support.table.header;


import com.aw.swing.mvp.binding.component.support.table.JTableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

/**
 *
 */
public class MultiLineHeaderRenderer extends JList implements TableCellRenderer {
    private boolean editable = false;

    private static final int LISTCELLRENDERER_WIDTH = 10;
    private static final int LISTCELLRENDERER_HEIGTH= 17;


    public MultiLineHeaderRenderer(boolean editable) {
        this.editable = editable;
        setOpaque(true);
        setForeground(UIManager.getColor("TableHeader.foreground"));
        setBackground(UIManager.getColor("TableHeader.background"));
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        ListCellRenderer renderer = getCellRenderer();
        setCellRenderer(renderer);

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (table != null) {
            setFont(table.getFont());
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }
        setBorder(BorderFactory.createLineBorder(Color.white));

        Icon icon = JTableModel.COLUMN;

        if (value instanceof TextAndIcon) {
            TextAndIcon textAndIcon = (TextAndIcon) value;
            value = textAndIcon.getText();
            icon = textAndIcon.getIcon();
        }

        Vector<TextAndIcon> textIcons = getTextAndIconBasedOn(value);
        if (icon != null && textIcons.size() > 0) {
            textIcons.get(0).setIcon(icon);
        }
        setListData(textIcons);
        return this;
    }

    private Vector<TextAndIcon> getTextAndIconBasedOn(Object value) {
        String str = (value == null) ? "" : value.toString();
        BufferedReader br = new BufferedReader(new StringReader(str));
        String line;
        Vector v = new Vector();
        try {
            while ((line = br.readLine()) != null) {
                v.addElement(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Vector textIcons = new Vector();
        for (Object o : v) {
            String valStr = (String) o;
            textIcons.add(new TextAndIcon(valStr, null));
        }
        return textIcons;
    }

    public ListCellRenderer getCellRenderer() {
        return new IconListCellRenderer();
    }


    class IconListCellRenderer extends JPanel implements ListCellRenderer {
        JLabel left, right;

        IconListCellRenderer() {
            setLayout(new BorderLayout());
            left = new JLabel();
            left.setHorizontalAlignment(JLabel.CENTER);
            right = new JLabel();
            right.setHorizontalAlignment(JLabel.RIGHT);
            left.setOpaque(true);
            right.setOpaque(true);
            add(left, BorderLayout.CENTER);
            add(right, BorderLayout.EAST);
            setPreferredSize(new Dimension(LISTCELLRENDERER_WIDTH , LISTCELLRENDERER_HEIGTH));
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            TextAndIcon textAndIcon = (TextAndIcon) value;
            left.setText(textAndIcon.getText());
            if (!editable && textAndIcon.getIcon() != null) {
                right.setIcon(textAndIcon.getIcon());
            } else {
                right.setIcon(null);
            }
            left.setBackground(list.getBackground());
            left.setForeground(list.getForeground());
            right.setBackground(list.getBackground());
            right.setForeground(list.getForeground());
            left.setFont(list.getFont());
            right.setFont(list.getFont());
            return this;
        }

    }


}
