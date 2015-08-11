package com.aw.swing.mvp.action;

import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 26/05/2009
 */
public class AWInternaContext {
    private static AWInternaContext instance = new AWInternaContext();
    private final String CELL_EDITING = "cellEditing";
    private Map context = new HashMap();


    private AWInternaContext() {
    }

    public static AWInternaContext instance() {
        return instance;
    }

    public void cellEditingStart() {
        context.put(CELL_EDITING, Boolean.TRUE);
    }

    public void cellEditingEnd() {
        context.remove(CELL_EDITING);
    }

    public boolean isCellEditing() {
        Boolean value = (Boolean) context.get(CELL_EDITING);
        return (value != null && value);
    }


}
