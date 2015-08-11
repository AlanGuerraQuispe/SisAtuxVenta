package com.aw.swing.mvp.binding.component.support.table.header.group;

import javax.swing.table.JTableHeader;
import java.util.Iterator;

public class GroupableTableHeader extends JTableHeader {

    /**
     * Identifies the UI class which draws the header.
     */
    private static final String uiClassID = "GroupableTableHeaderUI";

    /**
     * Constructs a GroupableTableHeader which is initialized with cm as the
     * column model. If cm is null this method will initialize the table header
     * with a default TableColumnModel.
     *
     * @param model the column model for the table
     */
    public GroupableTableHeader(GroupableTableColumnModel model) {
        super(model);
        setUI(new GroupableTableHeaderUI());
        setReorderingAllowed(false);

    }


    /**
     * Sets the margins correctly for all groups within
     * the header.
     */
    public void setColumnMargin() {
        int columnMargin = getColumnModel().getColumnMargin();
        Iterator iter = ((GroupableTableColumnModel) columnModel).columnGroupIterator();
        while (iter.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup) iter.next();
            cGroup.setColumnMargin(columnMargin);
        }
    }

}

