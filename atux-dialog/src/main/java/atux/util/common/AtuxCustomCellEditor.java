package atux.util.common;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class AtuxCustomCellEditor extends DefaultCellEditor implements TableCellEditor {

  final JTextField mytext = new JTextField();

	public class FocusEventHandler implements java.awt.event.FocusListener {
  
		public void focusGained(java.awt.event.FocusEvent e) {
      javax.swing.SwingUtilities.invokeLater(new Runnable () {
        public void run() {
          mytext.selectAll();
			  }
		  } );
    }

    public void focusLost(java.awt.event.FocusEvent e) {}
  
	}

	/**
	* Constructor
	*/
  AtuxCustomCellEditor() {
    super(new JTextField());
    this.editorComponent = mytext;
    this.clickCountToStart = 0;
    mytext.addFocusListener(new FocusEventHandler());
  }

  public Object getCellEditorValue() {
    return new String(mytext.getText());
  }

  public Component getTableCellEditorComponent(JTable table, 
                                               Object value,
  			                                       boolean isSelected, 
                                               int row, 
                                               int column) {

    mytext.setText(value+"");
    mytext.selectAll();
    editorComponent.requestFocus();
    mytext.requestFocus();
    return editorComponent;

  }

}

 