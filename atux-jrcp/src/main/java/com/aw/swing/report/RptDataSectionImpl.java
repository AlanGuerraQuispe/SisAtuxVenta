package com.aw.swing.report;

import com.aw.core.view.IColumnInfo;
import com.aw.swing.mvp.binding.component.BndSJTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptDataSectionImpl extends RptDataSection {
    private BndSJTable bndTable;


    public BndSJTable getBndTable() {
        return bndTable;
    }

    public void setBndTable(BndSJTable bndTable) {
        this.bndTable = bndTable;
    }
    public List<IColumnInfo> getCustomColumnInfo() {
        if (customColumnInfo==null){
            if (bndTable==null)
                throw new IllegalArgumentException("Set bndTable first");
            customColumnInfo = new ArrayList<IColumnInfo>(Arrays.asList(bndTable.getColumnsInfo()));
        }
        return super.getCustomColumnInfo();
    }
    protected List getCustomValues() {
        if (customValues == null){
            if (bndTable==null)
                throw new IllegalArgumentException("Set bndTable first");
            customValues = bndTable.getValues();
        }
        return super.getCustomValues();
    }

}