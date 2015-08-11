package com.aw.swing.mvp.binding.component.support;

/**
 * User: gmc
 * Date: 19/05/2009
 */
public abstract class ChangeValueListener<E> {
    String[] modifiedCols;

    /**
     * @param rowObj
     * @param oldwValue
     * @param newValue
     * @return The columns which values have been modified programatically
     */
    public abstract String[] onChangeValue(E rowObj, Object oldwValue, Object newValue);

    public String[] getModifiedCols() {
        if (modifiedCols == null) {
            modifiedCols = new String[]{};
        }
        return modifiedCols;
    }

    public void setModifiedCols(String[] modifiedCols) {
        this.modifiedCols = modifiedCols;
    }
}
