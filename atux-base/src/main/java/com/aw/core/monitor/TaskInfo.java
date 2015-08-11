package com.aw.core.monitor;

/**
 * User: gmc
 * Date: 15/12/2009
 */
public class TaskInfo {
    private int numRetrievedRows;
    private boolean canceled;

    public int getNumRetrievedRows() {
        return numRetrievedRows;
    }

    public void setNumRetrievedRows(int numRetrievedRows) {
        this.numRetrievedRows = numRetrievedRows;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}
