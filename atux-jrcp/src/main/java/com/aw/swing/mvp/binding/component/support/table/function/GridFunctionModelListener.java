package com.aw.swing.mvp.binding.component.support.table.function;

import com.aw.swing.mvp.binding.component.BndIJTextField;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * User: gmc
 * Date: 20/07/2009
 */
public class GridFunctionModelListener implements TableModelListener {

    BndIJTextField bndIJTextField ;
    public void tableChanged(TableModelEvent e) {
        GridFunction gridFunction = bndIJTextField.getGridFunction();
        bndIJTextField.setValue(gridFunction.execute());
    }
//    public void tableChanged(TableModelEvent e) {
//        int firstRow = e.getFirstRow();
//        int lastRow = e.getLastRow();
//        int index = e.getColumn();
//
//        switch (e.getType()) {
//            case TableModelEvent.INSERT:
//                for (int i = firstRow; i <= lastRow; i++) {
//                    System.out.println(i);
//                }
//                break;
//            case TableModelEvent.UPDATE:
//                if (firstRow == TableModelEvent.HEADER_ROW) {
//                    if (index == TableModelEvent.ALL_COLUMNS) {
//                        System.out.println("A column was added");
//                    } else {
//                        System.out.println(index + "in header changed");
//                    }
//                } else {
//                    for (int i = firstRow; i <= lastRow; i++) {
//                        if (index == TableModelEvent.ALL_COLUMNS) {
//                            System.out.println("All columns have changed");
//                        } else {
//                            System.out.println(index);
//                        }
//                    }
//                }
//                break;
//            case TableModelEvent.DELETE:
//                for (int i = firstRow; i <= lastRow; i++) {
//                    System.out.println(i);
//                }
//                break;
//        }
//    }

    public void setBndIJTextField(BndIJTextField bndIJTextField) {
        this.bndIJTextField = bndIJTextField;
    }
}
