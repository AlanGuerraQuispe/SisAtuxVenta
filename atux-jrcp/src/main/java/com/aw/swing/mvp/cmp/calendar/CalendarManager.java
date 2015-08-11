package com.aw.swing.mvp.cmp.calendar;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.ui.calendar.Calendar;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.List;

/**
 * User: gmc
 * Date: 17/07/2009
 */
public class CalendarManager {
    protected Presenter presenter;

    public CalendarManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void add(BndIJTextField dateField) {
        String fieldName = dateField.getFieldName();
        String buttonName = "btn" + StringUtils.capitalize(fieldName);
        JButton button = (JButton) presenter.getIpView().getComponent(buttonName);
        Calendar calendar = new Calendar();
        JTextField jTextField = (JTextField) dateField.getJComponent();
        calendar.setField(jTextField);
        if (button!=null){
            calendar.setButton(button);
            button.setEnabled(!dateField.isUiReadOnly());                
            jTextField.putClientProperty(BindingComponent.ATTR_LINKED_BUTTON,button);           
        }
        calendar.init();
    }

    public void addAll(List<BndIJTextField> dateFields) {
        for (BndIJTextField dateField : dateFields) {
            add(dateField);
        }
    }
}
