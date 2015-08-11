package com.aw.builder;

/**
 * User: Kaiser
 * Date: 01/04/2009
 */
public class BNTable {
    private String tableName;
    private boolean userBN = false;
    private boolean useDetail = false;
    private boolean isMasterTable = false;
    private String parentTable;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isUserBN() {
        return userBN;
    }

    public void setUserBN(boolean userBN) {
        this.userBN = userBN;
    }

    public boolean isUseDetail() {
        return useDetail;
    }

    public void setUseDetail(boolean useDetail) {
        this.useDetail = useDetail;
    }

    public boolean isMasterTable() {
        return isMasterTable;
    }

    public void setMasterTable(boolean masterTable) {
        isMasterTable = masterTable;
    }

    public String getParentTable() {
        return parentTable;
    }

    public void setParentTable(String parentTable) {
        this.parentTable = parentTable;
    }
}
