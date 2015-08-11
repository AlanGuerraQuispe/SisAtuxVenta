package com.aw.core.report.pdf.support;

import com.aw.core.view.IColumnInfo;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class FormColumnInfo extends RptColumnInfo {

    public FormColumnInfo() {
    }

    public FormColumnInfo(float width) {
        this(width, IColumnInfo.LEFT);
    }

    public FormColumnInfo(float width, int alignment) {
        super(width, alignment);
    }


}
