package com.aw.swing.mvp.binding.component.support;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.ActionManager;
import com.aw.swing.mvp.action.types.ShowAuditingInfoAction;
import com.aw.swing.mvp.navigation.AWWindowsManager;

import java.awt.*;

/**
 * User: gmc
 * Date: 07/06/2009
 */
public class MainAuditColumn extends ButtonColumn {

    ShowAuditingInfoAction action;

    public MainAuditColumn(String columnHeader, String fieldName, int width, int alignment) {
        super(columnHeader, fieldName, width, alignment);
        setAsEditable(ColumnInfo.LINK);
        configure();
        setRptChrs(-1);
    }

    private void configure() {
        addOnClickListener(new ColumnActionListener() {
            public boolean actionPerformed(AWTEvent actionEvent, Object rowObj, Object newValue) {
                ActionManager.instance().executeAction(getAction());
                return false;
            }
        });
    }

    public ShowAuditingInfoAction getAction() {
        if (action.getPst()==null){
            Presenter currentPst = AWWindowsManager.instance().getCurrentPst();
            action.setPst(currentPst);
        }
        return action;
    }

    public void setAction(ShowAuditingInfoAction action) {
        this.action = action;
    }
}
