package com.table;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class AncesrtorListenerExample extends JFrame {

	public AncesrtorListenerExample(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500,500));
		setLayout(new BorderLayout());
		final JTable table = new JTable(new DefaultTableModel(5, 4));

		Vector<String> options = new Vector<String>();
		options.add("option 1");
		options.add("option 2");
		options.add("option 3");
		final JComboBox combobox = new JComboBox(options);
		combobox.addAncestorListener(new AncestorListener() {

			public void ancestorAdded(AncestorEvent event) {
                                                   //make sure combobox handles key events
				combobox.requestFocusInWindow();

			}

			public void ancestorMoved(AncestorEvent event) {

			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(combobox));

		add(new JScrollPane(table));
	}

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		AncesrtorListenerExample m = new AncesrtorListenerExample("test");
		m.pack();
		m.setVisible(true);
	}

}