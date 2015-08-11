package com.aw.swing.mvp.ui.calendar;

import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.common.CommitEvent;
import com.michaelbaranov.microba.common.CommitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: gmc
 * Date: 17/07/2009
 */
public class Calendar {
    protected static final String POPUP_KEY = "##BasicVetoDatePickerUI.popup##";
    protected CalendarPane calendarPane;
    protected JPopupMenu popup;

    protected JTextField field;
    protected JButton button;

    protected ComponentListener componentListener;

    public void init() {
        installKeyboardActions();
        installButtonActions();
        installComponents();
    }

    protected void installKeyboardActions() {
        InputMap input = field.getInputMap(JComponent.WHEN_FOCUSED);
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), POPUP_KEY);
        field.getActionMap().put(POPUP_KEY, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                showPopup(true);
            }
        });
    }

    protected void installComponents() {
        // calendar
        calendarPane = new CalendarPane();
        calendarPane.setFocusCycleRoot(true);
        calendarPane.setBorder(BorderFactory.createEmptyBorder(1, 3, 0, 3));
        calendarPane.setStripTime(false);
        calendarPane.setShowTodayButton(true);
        calendarPane.setShowNoneButton(false);
        // popup
        popup = new JPopupMenu();
        popup.setLayout(new BorderLayout());
        popup.add(calendarPane, BorderLayout.CENTER);
        popup.setLightWeightPopupEnabled(true);
        componentListener = new ComponentListener();
        calendarPane.addPropertyChangeListener(componentListener);
        calendarPane.addCommitListener(componentListener);
    }


    protected void showPopup(boolean visible) {
        if (visible) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date value = dateFormat.parse(field.getText());
                calendarPane.removePropertyChangeListener(componentListener);
                calendarPane.setDate(value);
                calendarPane.addPropertyChangeListener(componentListener);
            } catch (ParseException e) {
            } catch (PropertyVetoException e) {
            }
            popup.show(field, 0, field.getHeight());
            calendarPane.setStyle(calendarPane.STYLE_CLASSIC);
            calendarPane.requestFocus(false);
        } else {
            popup.setVisible(false);
        }
    }


    private void installButtonActions() {
        if (button != null) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showPopup(true);
                }
            });
        }
    }


    protected class ComponentListener implements PropertyChangeListener, CommitListener {

        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getSource() == calendarPane) {
                if (CalendarPane.PROPERTY_NAME_DATE.equals(evt.getPropertyName())) {
                    showPopup(false);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fieldValue = null;
                    try {
                        fieldValue = dateFormat.parse(field.getText());
                    } catch (ParseException e) {
                    }
                    if (fieldValue != null || evt.getNewValue() != null) {
                        field.setText(dateFormat.format(evt.getNewValue()));
                        InputVerifier iv = field.getInputVerifier();
                        if (iv!=null){
                            iv.verify(field);    
                        }
                    }
                }
            }

        }

        public void commit(CommitEvent event) {
            showPopup(false);
        }
    }

    public void setField(JTextField field) {
        this.field = field;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}


