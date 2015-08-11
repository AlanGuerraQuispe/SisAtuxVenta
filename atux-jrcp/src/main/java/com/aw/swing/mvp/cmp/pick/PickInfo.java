package com.aw.swing.mvp.cmp.pick;

/**
 * User: gmc
 * Date: 16/05/2009
 */
public class PickInfo {
    private String propertyName;
    private String actionCmd;
    private Pick pick;

    public PickInfo() {
    }

    public PickInfo(Pick pick, String propertyName, String actionCmd) {
        this.pick = pick;
        this.propertyName = propertyName;
        this.actionCmd = actionCmd;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getActionCmd() {
        return actionCmd;
    }

    public void setActionCmd(String actionCmd) {
        this.actionCmd = actionCmd;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }
}

