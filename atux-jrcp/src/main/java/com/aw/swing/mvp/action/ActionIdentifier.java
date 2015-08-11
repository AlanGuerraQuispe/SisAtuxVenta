package com.aw.swing.mvp.action;

import java.io.Serializable;

/**
 * User: gmc
 * Date: 12-feb-2008
 */
public class ActionIdentifier implements Serializable {
    private final static String SEPARATOR = "_";
    private String actionCmd;
    private Integer gridIndex;

    public static ActionIdentifier getActionIdentifier(String actionCmd) {
        ActionIdentifier actionIdentifier = new ActionIdentifier();
        actionIdentifier.actionCmd = actionCmd;
        return actionIdentifier;
    }

    public static ActionIdentifier getActionIdentifier(String actionCmd, Integer gridIndex) {
        ActionIdentifier actionIdentifier = new ActionIdentifier();
        actionIdentifier.actionCmd = actionCmd;
        actionIdentifier.gridIndex = gridIndex;
        return actionIdentifier;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionIdentifier)) return false;

        ActionIdentifier that = (ActionIdentifier) o;

        if (!actionCmd.equals(that.actionCmd)) return false;
        if (gridIndex != null ? !gridIndex.equals(that.gridIndex) : that.gridIndex != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = actionCmd.hashCode();
        result = 31 * result + (gridIndex != null ? gridIndex.hashCode() : 0);
        return result;
    }

    public String getActionCmd() {
        return actionCmd;
    }

    public void setActionCmd(String actionCmd) {
        this.actionCmd = actionCmd;
    }

    public Integer getGridIndex() {
        return gridIndex;
    }

    public void setGridIndex(Integer gridIndex) {
        this.gridIndex = gridIndex;
    }

    public String toString() {
        return "ActionIdentifier{" +
                "actionCmd='" + actionCmd + '\'' +
                ", gridIndex=" + gridIndex +
                '}';
    }

    public String asString() {
        return actionCmd + (gridIndex!=null? SEPARATOR +gridIndex.intValue() :"");
//        return actionCmd + (gridIndex != null && gridIndex.intValue()>0 ? SEPARATOR + gridIndex.intValue() : "");
//        return actionCmd + (gridIndex != null && gridIndex.intValue()>0 ? gridIndex.intValue() : "");
    }
    public String asStringWithoutSeparator() {
        return actionCmd + (gridIndex != null && gridIndex.intValue()>0 ? gridIndex.intValue() : "");
    }


//    public String asString() {
//        return actionCmd + (gridIndex != null ? SEPARATOR + gridIndex.intValue() : "");
//    }

//    public static ActionIdentifier getInstanceFrom(String actionName) {
//        String[] actionParts = StringUtils.delimitedListToStringArray(actionName, SEPARATOR);
//        ActionIdentifier actionIdentifier = new ActionIdentifier();
//        actionIdentifier.setActionCmd(actionParts[0]);
//        actionIdentifier.setGridIndex(actionParts.length > 1 ? new Integer(actionParts[1]) : null);
//        return actionIdentifier;
//    }
}
