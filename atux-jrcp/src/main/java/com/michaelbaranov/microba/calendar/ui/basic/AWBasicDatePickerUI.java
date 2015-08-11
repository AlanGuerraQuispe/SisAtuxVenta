package com.michaelbaranov.microba.calendar.ui.basic;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.michaelbaranov.microba.calendar.*;
import com.michaelbaranov.microba.calendar.resource.Resource;
import com.michaelbaranov.microba.calendar.ui.CalendarPaneUI;
import com.michaelbaranov.microba.calendar.ui.DatePickerUI;
import com.michaelbaranov.microba.common.CommitEvent;
import com.michaelbaranov.microba.common.CommitListener;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AWBasicDatePickerUI extends DatePickerUI implements
        PropertyChangeListener {

    protected static final String POPUP_KEY = "##BasicVetoDatePickerUI.popup##";

    protected DatePicker peer;

    protected CalendarPane calendarPane;

    protected JButton button;

    protected JPopupMenu popup;

    protected JTextField field;

    protected ComponentListener componentListener;

    public static ComponentUI createUI(JComponent c) {
        return new AWBasicDatePickerUI();
    }

    public void installUI(JComponent c) {
        peer = (DatePicker) c;
        installComponents();
        istallListeners();
        installKeyboardActions();
    }

    public void uninstallUI(JComponent c) {
        uninstallKeyboardActions();
        uninstallListeners();
        uninstallComponents();
        peer = null;
    }

    protected void installKeyboardActions() {
        InputMap input = peer.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), POPUP_KEY);
        peer.getActionMap().put(POPUP_KEY, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                showPopup(true);
            }
        });

    }

    protected void uninstallKeyboardActions() {
        InputMap input = peer.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        input.remove(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        peer.getActionMap().remove(POPUP_KEY);
    }

    protected void istallListeners() {
        peer.addPropertyChangeListener(this);
    }

    protected void uninstallListeners() {
        peer.removePropertyChangeListener(this);
    }

    protected void uninstallComponents() {
        button.removeActionListener(componentListener);
        field.removePropertyChangeListener(componentListener);

        calendarPane.removePropertyChangeListener(componentListener);
        calendarPane.removeCommitListener(componentListener);
        calendarPane.removeActionListener(componentListener);

        peer.remove(field);
        peer.remove(button);
        popup = null;
        calendarPane = null;
        button = null;
        field = null;

    }

    protected void installComponents() {
        field = new JTextField();
//        field.setValue(peer.getDate());
//        field.setFocusLostBehavior(peer.getFocusLostBehavior());
//        field.setEditable(peer.isFieldEditable());
//        field.setToolTipText(peer.getToolTipText());
//        field.setSize(120,20);
        // button
        button = new JButton();
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
//        button.setToolTipText(peer.getToolTipText());
//        button.setSize();

        setSimpeLook(false);
        // calendar
        calendarPane = new CalendarPane(peer.getStyle());
        calendarPane.setShowTodayButton(peer.isShowTodayButton());
//        calendarPane.setFocusLostBehavior(JFormattedTextField.REVERT);
        calendarPane.setFocusCycleRoot(true);
        calendarPane.setBorder(BorderFactory.createEmptyBorder(1, 3, 0, 3));
        calendarPane.setStripTime(false);
        calendarPane.setLocale(peer.getLocale());
        calendarPane.setZone(peer.getZone());
        calendarPane.setFocusable(peer.isDropdownFocusable());
        calendarPane.setColorOverrideMap(peer.getColorOverrideMap());
        // popup
        popup = new JPopupMenu();
        popup.setLayout(new BorderLayout());
        popup.add(calendarPane, BorderLayout.CENTER);
        popup.setLightWeightPopupEnabled(true);
        // add
        CellConstraints cc = new CellConstraints();
        peer.setLayout(new FormLayout("fill:71px:grow,left:14dlu:noGrow,fill:max(d;4px):noGrow", "center:17px:grow"));
        switch (peer.getPickerStyle()) {
            case DatePicker.PICKER_STYLE_FIELD_AND_BUTTON:
                peer.add(field, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.TOP));
                peer.add(button, cc.xy(2, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
                break;
            case DatePicker.PICKER_STYLE_BUTTON:
                peer.add(button, BorderLayout.EAST);
                break;
        }

        peer.revalidate();
        peer.repaint();

        componentListener = new ComponentListener();
        button.addActionListener(componentListener);
        field.addPropertyChangeListener(componentListener);

        calendarPane.addPropertyChangeListener(componentListener);
        calendarPane.addCommitListener(componentListener);
        calendarPane.addActionListener(componentListener);

        peerDateChanged(peer.getDate());
    }

    public void setSimpeLook(boolean b) {
        if (b) {
            field.setBorder(BorderFactory.createEmptyBorder());
            button.setText("...");
            button.setIcon(null);
        } else {
            field.setBorder(new JTextField().getBorder());
            button.setText("");
            button.setIcon(new ImageIcon(Resource.class
                    .getResource("picker-16.png")));
        }

    }

    public void showPopup(boolean visible) {
        if (visible) {
            // try to apply date to calendar pane popup, but not cause commit
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if (peer.isKeepTime())
                try {
                    //AbstractFormatter formatter = field.getFormatter();

                    Date value = dateFormat.parse(field.getText());

                    calendarPane.removePropertyChangeListener(componentListener);
                    calendarPane.setDate(value);
                    calendarPane.addPropertyChangeListener(componentListener);

                } catch (ParseException e) {
//                     ignore
                } catch (PropertyVetoException e) {
                    // can not happen
                }

            popup.show(peer, 0, peer.getHeight());
            calendarPane.requestFocus(false);
        } else {
            popup.setVisible(false);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (JComponent.TOOL_TIP_TEXT_KEY.equals(evt.getPropertyName())) {
            field.setToolTipText((String) evt.getNewValue());
            button.setToolTipText((String) evt.getNewValue());
        } else if (evt.getPropertyName().equals(DatePicker.PROPERTY_NAME_DATE)) {
            Date newValue = (Date) evt.getNewValue();
            peerDateChanged(newValue);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_FIELD_EDITABLE)) {
            field.setEditable(peer.isFieldEditable());
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_FOCUS_LOST_BEHAVIOR)) {
//            field.setFocusLostBehavior(peer.getFocusLostBehavior());
        } else if (evt.getPropertyName()
                .equals(DatePicker.PROPERTY_NAME_LOCALE)) {
//            field.setFormatterFactory(createFormatterFactory());
            calendarPane.setLocale(peer.getLocale());
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_DATE_FORMAT)) {
//            field.setFormatterFactory(createFormatterFactory());
        } else if (evt.getPropertyName().equals(DatePicker.PROPERTY_NAME_ZONE)) {
//            field.setFormatterFactory(createFormatterFactory());
            calendarPane.setZone((TimeZone) evt.getNewValue());
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_SHOW_TODAY_BTN)) {
            boolean value = ((Boolean) evt.getNewValue()).booleanValue();
            calendarPane.setShowTodayButton(value);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_SHOW_NONE_BTN)) {
            boolean value = ((Boolean) evt.getNewValue()).booleanValue();
            calendarPane.setShowNoneButton(value);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_SHOW_NUMBER_WEEK)) {
            boolean value = ((Boolean) evt.getNewValue()).booleanValue();
            calendarPane.setShowNumberOfWeek(value);
        } else if (evt.getPropertyName().equals(DatePicker.PROPERTY_NAME_STYLE)) {
            int value = ((Integer) evt.getNewValue()).intValue();
            calendarPane.setStyle(value);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_VETO_POLICY)) {
            calendarPane.setVetoPolicy((VetoPolicy) evt.getNewValue());
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_HOLIDAY_POLICY)) {
            calendarPane.setHolidayPolicy((HolidayPolicy) evt.getNewValue());
        } else if (evt.getPropertyName().equals("focusable")) {
            boolean value = ((Boolean) evt.getNewValue()).booleanValue();
            field.setFocusable(value);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_RESOURCES)) {
            CalendarResources resources = (CalendarResources) evt.getNewValue();
            calendarPane.setResources(resources);
        } else if (evt.getPropertyName().equals("enabled"/*
														 * DatePicker.PROPERTY_NAME_ENABLED
														 */)) {
            boolean value = ((Boolean) evt.getNewValue()).booleanValue();
            field.setEnabled(value);
            button.setEnabled(value);
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_PICKER_STYLE)) {
            peer.updateUI();
        } else if (evt.getPropertyName().equals(
                DatePicker.PROPERTY_NAME_DROPDOWN_FOCUSABLE)) {
            calendarPane.setFocusable(peer.isDropdownFocusable());
        }
    }

    private void peerDateChanged(Date newValue) {
        try {
            calendarPane.removePropertyChangeListener(componentListener);
            calendarPane.setDate(newValue);
            calendarPane.addPropertyChangeListener(componentListener);
        } catch (PropertyVetoException e) {
            // Ignore. CalendarPane has no VetoModel here.
        }
        field.removePropertyChangeListener(componentListener);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        field.setText(dateFormat.format(newValue));
        field.addPropertyChangeListener(componentListener);
    }

    private DefaultFormatterFactory createFormatterFactory() {
        return new DefaultFormatterFactory(new DateFormatter(peer
                .getDateFormat()));
    }

    protected class ComponentListener implements ActionListener,
            PropertyChangeListener, CommitListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() != calendarPane)
                showPopup(true);
            else
                showPopup(false);

        }

        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getSource() == calendarPane) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                if (CalendarPane.PROPERTY_NAME_DATE.equals(evt
                        .getPropertyName())) {
                    showPopup(false);

                    Date fieldValue = null;
                    try {
                        fieldValue =  dateFormat.parse(field.getText());
                    } catch (ParseException e) {
//                        fieldValue = (Date) field.getValue();
                    }

                    if (fieldValue != null || evt.getNewValue() != null) {

                        if (peer.isKeepTime() && fieldValue != null
                                && evt.getNewValue() != null) {

                            Calendar fieldCal = Calendar.getInstance(peer
                                    .getZone(), peer.getLocale());
                            fieldCal.setTime(fieldValue);

                            Calendar valueCal = Calendar.getInstance(peer
                                    .getZone(), peer.getLocale());
                            valueCal.setTime((Date) evt.getNewValue());

                            // era
                            fieldCal.set(Calendar.ERA, valueCal
                                    .get(Calendar.ERA));
                            // year
                            fieldCal.set(Calendar.YEAR, valueCal
                                    .get(Calendar.YEAR));
                            // month
                            fieldCal.set(Calendar.MONTH, valueCal
                                    .get(Calendar.MONTH));
                            // date
                            fieldCal.set(Calendar.DAY_OF_MONTH, valueCal
                                    .get(Calendar.DAY_OF_MONTH));
//                            field.setValue(fieldCal.getTime());
                        } else {
//                            field.setValue((Date) evt.getNewValue());
                            field.setText(dateFormat.format(evt.getNewValue()));
                        }
                    }
//                    }
                }
                if (evt.getSource() == field) {
                    if ("value".equals(evt.getPropertyName())) {
                        try {
                            Date value =  dateFormat.parse(field.getText());
                            peer.setDate(value);
                        } catch (PropertyVetoException e) {
                            field.setText(dateFormat.format(peer.getDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }

            }
        }

        public void commit(CommitEvent action) {
            showPopup(false);
//            if (field.getValue() != null || calendarPane.getDate() != null)
//            field.setValue(calendarPane.getDate());
            if (field.getText() != null || calendarPane.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                field.setText(dateFormat.format(calendarPane.getDate()));
            }

        }

        public void revert(CommitEvent action) {
            showPopup(false);

        }
    }

    public void commit() throws PropertyVetoException, ParseException {
//        field.commitEdit();
    }

    public void revert() {
        peerDateChanged(peer.getDate());
    }

    public void observeMonth(int year, int month) {
        CalendarPaneUI ui = (CalendarPaneUI) calendarPane.getUI();
        ui.observeMonth(year, month);
    }

}