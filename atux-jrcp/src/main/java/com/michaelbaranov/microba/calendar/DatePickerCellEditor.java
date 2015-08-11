package com.michaelbaranov.microba.calendar;

import com.michaelbaranov.microba.calendar.ui.DatePickerUI;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class in a concrete implementation of {@link TableCellEditor} and
 * {@link TreeCellEditor} interfaces. Uses {@link DatePicker} control as en
 * editor. Subclass to extend functionality. *
 * <p>
 * Note: you probably will want to set the property of the {@link DatePicker}
 * {@value DatePicker#PROPERTY_NAME_DROPDOWN_FOCUSABLE} to <code>false</code>
 * before using it to construct {@link DatePickerCellEditor}.
 * 
 * @see DefaultCellEditor
 * 
 * @author Michael Baranov
 * 
 */
public class DatePickerCellEditor extends DefaultCellEditor {

	/**
	 * Constructor.
	 * <p>
	 * Note: you probably will want to set the property of the
	 * {@link DatePicker} {@value DatePicker#PROPERTY_NAME_DROPDOWN_FOCUSABLE}
	 * to <code>false</code> before using it to construct
	 * {@link DatePickerCellEditor}.
	 * 
	 * @param datePicker
	 *            the editor component
	 */
	public DatePickerCellEditor(final DatePicker datePicker) {
		// trick: supply a dummy JCheckBox
		super(new JCheckBox());
		// get back the dummy JCheckBox
		JCheckBox checkBox = (JCheckBox) this.editorComponent;
		// remove listeners installed by super()
		checkBox.removeActionListener(this.delegate);
		// replace editor component with own
		this.editorComponent = datePicker;

		// set simple look
		((DatePickerUI) datePicker.getUI()).setSimpeLook(true);

		// replace delegate with own
		this.delegate = new EditorDelegate() {
			public void setValue(Object value) {
				try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					((DatePicker) editorComponent).setDate(dateFormat.parse((String) value));
				} catch (PropertyVetoException e) {
				} catch (ParseException e) {
                    e.printStackTrace();
                }
            }

			public Object getCellEditorValue() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				return dateFormat.format (((DatePicker) editorComponent).getDate());
			}

			public void cancelCellEditing() {
				((DatePicker) editorComponent).commitOrRevert();
				super.cancelCellEditing();
			}

			public boolean stopCellEditing() {
				((DatePicker) editorComponent).commitOrRevert();
				return super.stopCellEditing();
			}

		};
		// install listeners
		datePicker.addActionListener(delegate);
		// do not set it to 1
		setClickCountToStart(2);
	}

}