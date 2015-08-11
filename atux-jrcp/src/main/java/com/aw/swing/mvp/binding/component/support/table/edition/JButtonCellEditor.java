package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.swing.mvp.binding.component.support.ColumnInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonCellEditor extends AWAbstractCellEditor<JButton> {
    //private JButton delegate;


    public JButtonCellEditor(ColumnInfo columnInfo) {
        this( columnInfo, new  JButton() );
    }
    public JButtonCellEditor(ColumnInfo columnInfo, JButton delegate ) {
        super(columnInfo);
        this.delegate = delegate;


        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //AWInternaContext.instance().cellEditingStart();
                changeValue(actionEvent);
            }
        };
        this.delegate.addActionListener(actionListener);
    }

    protected Object getDelegateValue() {
        return delegate.getText();
    }

    protected void setDelegateValue(Object value) {
        delegate.setText((String) value);
    }
}