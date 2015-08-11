package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;

import javax.swing.*;
import java.util.EventObject;

public class JLabelLinkCellEditor extends AWAbstractCellEditor<JLabel> {
    //private JButton delegate;


    public JLabelLinkCellEditor(ColumnInfo columnInfo) {
        super(columnInfo);
        this.delegate = new JLabel();
        

//        MouseAdapter mouseListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                changeValue(e);
//            }
//            public void mouseEntered(MouseEvent e) {
//                super.mouseEntered(e);
//            }
//            public void mouseExited(MouseEvent e) {
//                super.mouseExited(e);
//            }
//        };
//        this.delegate.addMouseListener(mouseListener);
    }

    protected Object getDelegateValue() {
        return delegate.getText();
    }

    protected void setDelegateValue(Object value) {
        delegate.setText(value == null ?"":value.toString());
        JLabelLinkCellRenderer.configure(delegate);
    }
    public boolean isCellEditable(EventObject e) {
    	return false; 
    }

    protected void onCellEditingEnd() {
    }
    protected void onCellEditingStart() {
    }

}