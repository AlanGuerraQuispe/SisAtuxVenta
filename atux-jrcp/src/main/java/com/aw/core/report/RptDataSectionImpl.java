package com.aw.core.report;

import com.aw.core.view.IColumnInfo;
import com.aw.core.view.IGridInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptDataSectionImpl extends RptDataSection {
    protected IGridInfo gridInfo;

    public List<IColumnInfo> getCustomColumnInfo() {
        if (customColumnInfo == null) {
            if (gridInfo == null) {
                throw new IllegalArgumentException("Set gridInfo first");
            }
            customColumnInfo = new ArrayList<IColumnInfo>();
            IColumnInfo[] cols = gridInfo.getColumnInfo();
            for (IColumnInfo col : cols) {
                if (col.isPrintable()) {
                    customColumnInfo.add(col);
                }
            }
        }
        return customColumnInfo;
    }

    protected List getCustomValues() {
        if (customValues == null) {
            if (gridInfo == null)
                throw new IllegalArgumentException("Set gridInfo first");
            customValues = gridInfo.getValues();
        }
        return customValues;
    }

    public IGridInfo getGridInfo() {
        return gridInfo;
    }

    public void setGridInfo(IGridInfo gridInfo) {
        this.gridInfo = gridInfo;
    }

}