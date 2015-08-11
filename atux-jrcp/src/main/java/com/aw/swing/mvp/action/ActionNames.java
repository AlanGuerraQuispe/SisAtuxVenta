package com.aw.swing.mvp.action;

/**
 * Actions that could be assigned to a controller
 *
 * @author GMC
 *         21/10/2005
 */
public interface ActionNames {
    /**
     * Page Actions
     */
    public static String ACTION_SEARCH = "Search";
    public static String ACTION_CLEAN_FILTER = "CleanFilter";
    public static String ACTION_SORT_BY_COLUMN = "SortByColumn";
    public static String ACTION_DELETE = "Delete";
    public static String ACTION_DELETE_ITEM = "DeleteItem";
    public static String ACTION_DELETE_LOGICAL = "DeleteLogical";
    public static String ACTION_REFRESH_DEPENDENT_GRIDS = "RefreshDependentGrids";
    public static String ACTION_CLEAN_SELECTED_ROW = "CleanSelectedRow";
    public static String ACTION_GET_DEPENDENT_CMB_DATA_INFO = "GetDependentCmbDataInfo";
    public static String ACTION_AUTO_COMPLETE = "AutoComplete";
    public static String ACTION_UPDATE_CELL_VALUE = "UpdateCellValue";
    public static String ACTION_PRINT_GRID = "PrintGrid";
    public static String ACTION_EXPORT_GRID = "ExportGrid";

    public static String ACTION_EXPORT_PDF = "ExportPdf";
    public static String ACTION_EXPORT_EXCEL = "ExportExcel";

    /**
     * Transition actions
     */
    public static String ACTION_EDIT = "Edit";
    public static String ACTION_VIEW = "View";
    public static String ACTION_ADD = "Add";
    public static String ACTION_SHOW_PST = "ShowPst";
    public static String ACTION_SHOW_AUDIT_INFO = "ShowAuditInfo";
    public static String ACTION_ADD_ITEM = "AddItem";

    public static String ACTION_OK = "Ok";
    public static String ACTION_SAVE = "Save";
    public static String ACTION_CANCEL = "Cancel";
    public static String ACTION_SELECT = "Select";
    public static String ACTION_SELECT_ROWS = "SelectRows";
    public static String ACTION_PICK_SELECT = "PickSelect";
    /**
     * Other Actions
     */
    public static String ACTION_PICK = "Pick";
    public static String ACTION_PICK_CLEAN_DATA = "PickCleanData";

}
