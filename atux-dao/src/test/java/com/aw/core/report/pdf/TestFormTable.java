package com.aw.core.report.pdf;

import com.aw.core.report.pdf.support.FormCell;
import com.aw.core.report.pdf.support.FormColumnInfo;
import com.aw.core.report.pdf.support.SeparatorFormColumnInfo;
import junit.framework.TestCase;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class TestFormTable extends TestCase {


    public void test(){
        FormTable formTable = new FormTable();
        formTable.setColumnsInfo(getColumnsInfo());
        formTable.add(new FormCell("1.1"));
        formTable.add(new FormCell("1.2"));
        assertEquals(1, formTable.getRowsCount());
        formTable.add(new FormCell("2.1"));
        formTable.add(new FormCell("2.2"));
        assertEquals(2, formTable.getRowsCount());
        formTable.add(new FormCell("3.1").setColSpan(4));
        formTable.add(new FormCell("4.1"));
        assertEquals(4, formTable.getRowsCount());

    }

    public FormColumnInfo[] getColumnsInfo() {
        FormColumnInfo[] columnsInfo = new FormColumnInfo[]{
                new FormColumnInfo(),
                new SeparatorFormColumnInfo(),
                new FormColumnInfo(),
                new SeparatorFormColumnInfo()
        };
        return columnsInfo;
    }
}
