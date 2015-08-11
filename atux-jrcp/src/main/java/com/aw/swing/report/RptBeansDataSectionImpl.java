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
public class RptBeansDataSectionImpl extends RptDataSection {
    private List beanList;
    private List<IColumnInfo> columnInfoList;

    public void setBeanList(List beanList) {
        this.beanList = beanList;
    }

    public void setColumnInfoList(List<IColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

    public List<IColumnInfo> getCustomColumnInfo() {
        return columnInfoList;
    }

    protected List getCustomValues() {
        return beanList;
    }

}