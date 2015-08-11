package com.table.calendar;

import com.aw.swing.mvp.ui.calendar.Calendar;

import javax.swing.*;
import java.awt.*;

/**
 * User: gmc
 * Date: 17/07/2009
 */
public class CalendarExample {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new FlowLayout());
        final JTextField textField = new JTextField();
        textField.setText("12/12/2009");
        JButton btn = new JButton("ShowPicker");
        final Calendar calendar = new Calendar();
        calendar.setField(textField);
        calendar.setButton(btn);
        calendar.init();
		frame.add(textField);
		frame.add(btn);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}
