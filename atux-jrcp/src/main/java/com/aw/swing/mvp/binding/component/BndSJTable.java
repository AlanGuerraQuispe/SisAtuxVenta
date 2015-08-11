/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.binding.component;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.context.AWBaseContext;
import com.aw.core.exception.AWSystemException;
import com.aw.support.beans.AWPropertyComparator;
import com.aw.support.collection.ListProvider;
import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.support.ButtonColumn;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.MainAuditColumn;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.binding.component.support.table.FixedColumnTableDecorator;
import com.aw.swing.mvp.binding.component.support.table.FixedColumnTableMgr;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import com.aw.swing.mvp.binding.component.support.table.JTableMouseProcessor;
import com.aw.swing.mvp.binding.component.support.table.edition.*;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunction;
import com.aw.swing.mvp.binding.component.support.table.header.JCheckBoxCellHeaderRenderer;
import com.aw.swing.mvp.binding.component.support.table.header.MultiLineHeaderRenderer;
import com.aw.swing.mvp.grid.*;
import com.aw.swing.mvp.ui.MessageBlockerPanel;
import com.aw.swing.mvp.ui.UIColorConstants;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.common.monitor.SearchMsgBlocker;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * JTable Component used to show data
 *
 * @author gmateo
 *         15/10/2004
 */
public class BndSJTable {
    public static final Object CP_FIXED_TABLE = "fixedTable";
    public static final Object CP_MAIN_TABLE = "mainTable";
    protected final Log logger = LogFactory.getLog(getClass());

    private Presenter presenter;

    private GridManager gridManager;

    /**
     * Represents the number of the grid in one page.
     * The values could be 0,1,2,.. so on
     */
    private int gridIndex = 0;

    private JTable jTable;
    /**
     * Array of column information
     */
    private ColumnInfo[] columnsInfo;
    /**
     * It is used to provide the values that will be shown in the JTable
     */
    private ListProvider valuesProvider;
    /**
     * Used to sorteableColumn the view in this jComponent
     */
    private Comparator comparator;
    /**
     * It is the last added Row. It is used to select the added row after inserting a row in the table
     */
    private Object addedRow;

    /**
     * If it is true means that the records are already sorted so it is not needed to sort them again
     */
    private boolean sortedRows = false;
    /**
     * If it is true means that the records are already sorted so it is not needed to sort them again
     */
    private boolean alwaysReloadColumnInfo = false;

    private boolean refreshAbortable = false;
    /**
     * If it is defined, some rows will be differents backgrounds based on the condition that
     * was set in this object
     */
    private RowColorBkgChanger rowColorBkgChanger;

    /**
     * If it is defined, some cells will be differents backgrounds based on the condition that
     * was set in this object
     */
    private CellColorChanger cellColorChanger;


    protected TableModelListener tableModelListener = null;

    protected boolean isScrollable = false;
    protected boolean fixedWidth = false;
    protected boolean isReadOnly = false;
    protected boolean initializing = false;

    protected int fixedColumns = 0;

    private static final Color AUDITING_COLUMN_COLOR = new Color(217, 229, 242);

    private FixedColumnTableMgr fixedColumnTableMgr;

    private EditingRowPolicy editingRowPolicy;

    protected GridFunction gridFunction;

    /**
     * Indicates if the table will sort pressing its header
     */
    protected boolean createAutoSortCols = true;

    /**
     * Constructor used when it will be used the default beanWrapper of the ComponentManager
     *
     * @param jTable
     * @param columnsInfo
     */
    public BndSJTable(JTable jTable, ColumnInfo[] columnsInfo) {
        this.jTable = jTable;
        this.columnsInfo = columnsInfo;
    }

    /**
     * Constructor used when it will be used the valuesProvider
     *
     * @param jTable
     * @param columnsInfo
     * @param valuesProvider
     */
    public BndSJTable(JTable jTable, ColumnInfo[] columnsInfo, ListProvider valuesProvider) {
        this(jTable, columnsInfo);
        this.valuesProvider = valuesProvider;
    }

    protected DependentColsManager dependentColsManager = new DependentColsManager(this);

    /**
     * Init the input jComponent.
     */
    public void init() {
        initializing = true;
        initComponent();
        // init dependencies if exist
        dependentColsManager.init();
        initializing = false;
    }

    /**
     * Initialize the jComponent
     */
    public void initComponent() {
        configureJTable();
        if (!alwaysReloadColumnInfo) {
            cleanJComponentValues();
            initColumnInfo();
            configureColumns();
        }
        buildSortDefinitionFromColumns();
    }

    private void initColumnInfo() {
        for (ColumnInfo columnInfo : columnsInfo) {
            if (columnInfo.isEditable()) {
                MetaLoader metaLoader = columnInfo.getMetaLoader();
                if (metaLoader != null) {
                    metaLoader = decorateMetaLoader(columnInfo, metaLoader);
                    columnInfo.setDropDownFormatter(metaLoader);
                }
            }
            columnInfo.init();
        }
    }

    private MetaLoader decorateMetaLoader(ColumnInfo columnInfo, MetaLoader metaLoader) {
        if (!metaLoader.isTopRowSet()) {
            metaLoader = metaLoader.addSeleccioneNRow();
        }
        if (!isReadOnly) {
            metaLoader = metaLoader.activeRows();
        }
        return metaLoader;
    }


    private void configureJTable() {
        LookAndFeelManager.setLookAndFeel(jTable);
        //by default allow single line selection
        if (isScrollable || fixedWidth) {
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
        if (createAutoSortCols) {
            jTable.setAutoCreateRowSorter(true);
        }
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableMouseProcessor jTableMouseProcessor = new JTableMouseProcessor(presenter, this, gridIndex);
        jTable.addMouseListener(getJTableMouseListener(jTableMouseProcessor));
        JTableHeader header = jTable.getTableHeader();
        header.addMouseListener(getJTableHeaderMouseListener(jTableMouseProcessor));
        header.setReorderingAllowed(false);
        jTable.setName("grid" + gridIndex);
    }

    private MouseAdapter getJTableHeaderMouseListener(final JTableMouseProcessor jTableMouseProcessor) {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jTableMouseProcessor.processMouseEventsOnHeader(e);
            }
        };
    }

    private MouseAdapter getJTableMouseListener(final JTableMouseProcessor jTableMouseProcessor) {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jTableMouseProcessor.processMouseEventsOnBody(e);
            }

            /**
             * Invoked when a mouse button has been pressed on a component.
             */
            public void mousePressed(MouseEvent e) {
                gridManager.setFocusIsMouseTriggered(true);
            }

            /**
             * Invoked when a mouse button has been released on a component.
             */
            public void mouseReleased(MouseEvent e) {
                gridManager.setFocusIsMouseTriggered(false);
            }
        };
    }

    /**
     * Configure the grid columns based on the ColumnInfo information
     */
    protected void configureColumns() {
        //jcv:2.0.1
//        TableCellRenderer tableCellRenderer = createHeaderRenderer();
        JTableHeader jTableHeader = jTable.getTableHeader();
        TableCellRenderer tableCellRenderer = jTableHeader.getDefaultRenderer();
        for (int i = 0; i < columnsInfo.length; i++) {
            TableColumn col = jTable.getColumnModel().getColumn(i);
            if (columnsInfo[i] instanceof SelectorColumn) {
                final SelectorColumn selectorColumn = (SelectorColumn) columnsInfo[i];
                // Set special renderer for the column's header
                col.setHeaderRenderer(getSelectorHeaderRenderer(selectorColumn));
            } else {
                // Set special renderer for the column's header
                col.setHeaderRenderer(tableCellRenderer);
            }

            // set column width
            if (columnsInfo[i].getWidth() > 0) {
                col.setPreferredWidth(columnsInfo[i].getWidth());
            }
            if (columnsInfo[i].isEditable() || StringUtils.hasText(columnsInfo[i].getValidationRule())) {
                columnsInfo[i].initializeValidator();
            }
            // set column alignment if the cell is not editable
            if (!columnsInfo[i].isEditable() && !(columnsInfo[i] instanceof ButtonColumn)) {
                configureReadOnlyCell(col, columnsInfo[i]);
            } else {
                configureEditableCell(col, columnsInfo[i]);
            }
            columnsInfo[i].setIdx(i);
        }
        if (fixedColumns > 0) {
            fixedColumnTableMgr = FixedColumnTableDecorator.decorate(getJTable(), fixedColumns);
            JTable fixedTable = fixedColumnTableMgr.getFixedTable();
            JTableMouseProcessor jTableMouseProcessor = new JTableMouseProcessor(presenter, this, gridIndex);
            jTableMouseProcessor.setJTable(fixedTable);
            jTableMouseProcessor.setInFixedTable(true);
            fixedTable.addMouseListener(getJTableMouseListener(jTableMouseProcessor));
            fixedTable.getTableHeader().addMouseListener(getJTableHeaderMouseListener(jTableMouseProcessor));
        }
    }

    JCheckBoxCellHeaderRenderer jCheckBoxCellHeaderRenderer;

    private JCheckBoxCellHeaderRenderer getSelectorHeaderRenderer(final SelectorColumn selectorColumn) {
        if (jCheckBoxCellHeaderRenderer == null) {
            jCheckBoxCellHeaderRenderer = new JCheckBoxCellHeaderRenderer(jTable, selectorColumn);
        }
        return jCheckBoxCellHeaderRenderer;
    }

    private TableCellRenderer createHeaderRenderer() {
        return new MultiLineHeaderRenderer(isEditable());
    }

    /**
     * Configure ReadOnly col
     *
     * @param col
     */
    private void configureReadOnlyCell(TableColumn col, final ColumnInfo columnInfo) {
        TableCellRenderer cellRenderer = columnInfo.getCustomCellRenderer();
        if (cellRenderer instanceof JCheckBoxCellRenderer) {
            ((JCheckBoxCellRenderer) cellRenderer).setBndSJTable(this);
        }
        final boolean isTableEditable = isEditable();
        if (cellRenderer == null) {
            cellRenderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent
                        (JTable table, Object value, boolean isSelected,
                         boolean hasFocus, int row, final int column) {

                    Component cmp = super.getTableCellRendererComponent
                            (table, value, isSelected, hasFocus, row, column);

                    Border border = new EmptyBorder(new Insets(2, 5, 2, 5));
                    if (isSelected) {
                        if (isTableEditable) {
                            setBackground(UIColorConstants.BKG_CELL_SELECTED_DISABLED);
                        }
                    } else {
                        Color bkgColor = Color.white;
                        Color fontColor = null;
                        if (rowColorBkgChanger != null) {
                            Object obj = getValues().get(row);
                            rowColorBkgChanger.process(obj, cmp);
                        }
                        if (columnInfo.getBackground() != null) {
                            bkgColor = columnInfo.getBackground();
                        }
                        if (columnInfo.getBackground() != null) {
                            fontColor = columnInfo.getFontColor();
                        }
                        if (isTableEditable) {
                            bkgColor = UIColorConstants.BKG_CELL_DISABLED;
                        }
                        if (isAuditingColumn(columnInfo.getFieldName())) {
                            bkgColor = AUDITING_COLUMN_COLOR;
                        }
                        if (cellColorChanger != null) {
                            cellColorChanger.setDefaultColor(bkgColor);
                            Object obj = getValues().get(row);
                            bkgColor = cellColorChanger.getBackGround(obj, row, columnInfo.getFieldName());
                            Color foreGroundColor = cellColorChanger.getForeGround(obj, row, columnInfo.getFieldName());
                            if (foreGroundColor != null) {
                                setForeground(foreGroundColor);
                            }
                        }

                        if (fontColor != null) {
                            setForeground(fontColor);
                        }
                        setBackground(bkgColor);

                    }
                    setBorder(border);
                    return this;
                }
            };
        }
        if (cellRenderer instanceof DefaultTableCellRenderer) {
            ((DefaultTableCellRenderer) cellRenderer).setHorizontalAlignment(columnInfo.getAlignment());
        }
        col.setCellRenderer(cellRenderer);
    }

    private boolean isAuditingColumn(String fieldName) {
        List<String> auditingFields = AWBaseContext.instance().getConfigInfoProvider().getAuditingFields();
        return auditingFields.contains(fieldName);
    }

    private void configureEditableCell(TableColumn col, ColumnInfo columnInfo) {
        TableCellRenderer tableCellRenderer = new DefaultEditableCellRenderer(columnInfo);
        ((DefaultTableCellRenderer) tableCellRenderer).setHorizontalAlignment(columnInfo.getAlignment());

        TableCellEditor tableCellEditor = null;
        Font font = ((DefaultEditableCellRenderer) tableCellRenderer).getFont();
        if (columnInfo.getCellEditorValuesProvider() != null) {
            JComponentCellRenderer jComponentCellRenderer = new JComponentCellRenderer();
            JComponentCellEditor jComponentCellEditor = new JComponentCellEditor(columnInfo, font);
            jComponentCellEditor.setCellRenderer(jComponentCellRenderer);
            jComponentCellEditor.setCellEditorProvider(columnInfo.getCellEditorValuesProvider());
            col.setCellRenderer(jComponentCellRenderer);
            col.setCellEditor(jComponentCellEditor);
            columnInfo.setSpecialCellRenderer(jComponentCellRenderer);
            return;
        }
        if (columnInfo.getColumnEditorType() == ColumnInfo.TEXT_BOX) {
            ((DefaultEditableCellRenderer) tableCellRenderer).setCellColorChanger(cellColorChanger);
            tableCellEditor = CellEditorFactory.getJTextFielCellEditor(columnInfo, jTable, font);
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.TEXT_AREA) {
            tableCellEditor = new JTextAreaCellEditor(getJTable(), columnInfo, font);
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.CHECK_BOX) {
            tableCellRenderer = new JCheckBoxCellRenderer(columnInfo, columnInfo.getValueTrue());
            ((JCheckBoxCellRenderer) tableCellRenderer).setBndSJTable(this);
            tableCellEditor = CellEditorFactory.getJCheckBoxEditor(columnInfo, jTable, getCellEditableFocusListener());
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.RADIO_BUTTON) {
            tableCellRenderer = new JRadioButtonCellRenderer(columnInfo.getValueTrue());
            tableCellEditor = new JRadioButtonCellEditor(new JCheckBox(), columnInfo.getValueTrue());
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.COMBO_BOX) {

            if (columnInfo.hasParentCmps()) {
                CellDropDownProvider cellDropDownProvider = columnInfo.getCellDropDownProvider(this);
                tableCellRenderer = new JComboBoxDependentCellRenderer(columnInfo, new JComboBoxDependentCellRendererModel(cellDropDownProvider));
                JComboBoxDependentCellEditorModel comboBoxModel = new JComboBoxDependentCellEditorModel(cellDropDownProvider);
                final JComboBox jComboBox = getJComboBox(comboBoxModel);

                JComboBoxDependentCellEditor jComboBoxCellEditor = new JComboBoxDependentCellEditor(jComboBox);
                tableCellEditor = jComboBoxCellEditor;

            } else {
                JComboBoxCellRenderer comboBoxCellRenderer = new JComboBoxCellRenderer(columnInfo, new JComboBoxCellRendererModel(columnInfo.getMetaLoader()));
                comboBoxCellRenderer.setEditingRowPolicy(editingRowPolicy);
                tableCellRenderer = comboBoxCellRenderer;

                JComboBoxCellEditorModel comboBoxModel = new JComboBoxCellEditorModel();
                comboBoxModel.setMetaLoader(columnInfo.getMetaLoader());
                final JComboBox jComboBox = getJComboBox(comboBoxModel);

                JComboBoxCellEditor jComboBoxCellEditor = new JComboBoxCellEditor(jComboBox);
                jComboBoxCellEditor.setCol(columnInfo.getIdx());
                tableCellEditor = jComboBoxCellEditor;
            }
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.FILE_CHOOSER) {
            tableCellEditor = new JFileChooserCellEditor();
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.BUTTON) {
            tableCellEditor = new JButtonCellEditor(columnInfo);
            tableCellRenderer = new JButtonCellRenderer();
        } else if (columnInfo.getColumnEditorType() == ColumnInfo.LINK) {
            tableCellEditor = new JLabelLinkCellEditor(columnInfo);
            //tableCellEditor = new JLabelLinkCellRenderer();
            JLabelLinkCellRenderer linkCellRenderer = new JLabelLinkCellRenderer();
            linkCellRenderer.setColumnInfo(columnInfo);
            linkCellRenderer.setCellColorChanger(cellColorChanger);
            linkCellRenderer.setHorizontalAlignment(columnInfo.getAlignment());
            linkCellRenderer.setTableEditable(isEditable());
            if (isAuditingColumn(columnInfo.getFieldName())) {
                linkCellRenderer.setCustomBackground(AUDITING_COLUMN_COLOR);
            }
            tableCellRenderer = linkCellRenderer;

        }
        col.setCellRenderer(tableCellRenderer);
        col.setCellEditor(tableCellEditor);
    }

    private JComboBox getJComboBox(ComboBoxModel comboBoxModel) {
        final JComboBox jComboBox = new JComboBox(comboBoxModel);
        jComboBox.addFocusListener(new CellEditableFocusListener());
        jComboBox.setRequestFocusEnabled(true);
        jComboBox.putClientProperty(CellEditorUtils.AW_CELL_EDITOR, Boolean.TRUE);
        jComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        jComboBox.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent event) {
                jComboBox.requestFocusInWindow();
            }

            public void ancestorMoved(AncestorEvent event) {
            }

            public void ancestorRemoved(AncestorEvent event) {
            }
        });
        return jComboBox;
    }

    ///////////////////////////////////// Sorting mechanisms //////////////////////////////

    /**
     * Called on initialization or creation.
     * Search for the column that willl be used to sort the data
     */
    private void buildSortDefinitionFromColumns() {
        for (int i = 0; i < columnsInfo.length; i++) {
            if (columnsInfo[i].getSortDefinition() != null) {
                setSorteableColumn(columnsInfo[i]);
                break;
            }
        }
    }

    /**
     * Set the sorteableColumn definition for this holder.
     * Typically an instance of MutableSortDefinition.
     * By default ascending
     *
     * @param sorteableColumn
     * @see org.springframework.beans.support.MutableSortDefinition
     */
    public void setSorteableColumn(ColumnInfo sorteableColumn) {
        boolean ascending = true;
        if (sorteableColumn.getSortDefinition() != null) {
            ascending = sorteableColumn.getSortDefinition().isAscending();
        }
        setSorteableColumn(sorteableColumn, ascending);
    }

    /**
     * Set the sorteableColumn definition for this holder.
     * Typically an instance of MutableSortDefinition.
     *
     * @param sorteableColumn
     * @param ascending
     * @see org.springframework.beans.support.MutableSortDefinition
     */
    public void setSorteableColumn(ColumnInfo sorteableColumn, boolean ascending) {
        if (sorteableColumn.getSortDefinition() == null) {
            logger.debug("Sorteable column has not sort defintion, assuminng default");
            sorteableColumn.setAsSorted(ascending);
        } else {
            ((MutableSortDefinition) sorteableColumn.getSortDefinition()).setAscending(ascending);
        }
        AWPropertyComparator propertyComparator = new AWPropertyComparator(
                sorteableColumn.getSortDefinition(), sorteableColumn.getFormatter());
        setCustomSortComparator(propertyComparator);
    }
/////////////////////////////////////////////////////////////////////////////////////

    /**
     * Refresh the content of the JComponents
     */
    public void refresh(final Object param) {
        if (refreshAbortable) {
            ProcessMsgBlocker.instance().showMessage("Procesando ...");
//            final ProcessMsgBlocker msgBlocker = ProcessMsgBlocker.instance();
//            final SearchMsgBlocker msgBlocker = SearchMsgBlocker.instance();
            SwingWorker swingWorker = new SwingWorker() {
                protected Object doInBackground() throws Exception {
                    refreshInternal(param);
                    return null;
                }

                protected void done() {
//                    msgBlocker.close();
                    ProcessMsgBlocker.instance().removeMessage();
                }
            };
            swingWorker.execute();
            if (SwingUtilities.isEventDispatchThread()) {
//                msgBlocker.show();
                ProcessMsgBlocker.instance().showMessage("Procesando ...");
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            ProcessMsgBlocker.instance().showMessage("Procesando ...");
//                            msgBlocker.show();
                        }
                    });
                } catch (Throwable e) {
                    throw new AWSystemException("Problems refreshing the table:" + this, e);
                }
            }
        } else {
            if (SwingUtilities.isEventDispatchThread()) {
                refreshInternal(param);
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            refreshInternal(param);
                        }
                    });
                } catch (Throwable e) {
                    throw new AWSystemException("Problems refreshing the table:" + this, e);
                }
            }
        }
    }

    private void refreshInternal(Object param) {
//        cleanSelectedRows();
        cleanSelectedRowsForRefresh();
        resetHeader();
        setValueToJComponent(param);
    }

    public void refreshInMemory(List values) {
        resetHeader();
        setModelToJTable(values);
    }


    public void resetHeader() {
        TableColumnModel colModel = jTable.getColumnModel();
        int colsNumber = getColumnsInfo().length;
        int colsModel = colModel.getColumnCount() + fixedColumns;
        if (colsNumber == colsModel) {
            if (getSelectorColumn() != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JTable fixedJTable = (JTable) jTable.getClientProperty(CP_FIXED_TABLE);
                        if (fixedJTable != null) {
                            fixedJTable.getTableHeader().repaint();
                        } else {
                            jTable.getTableHeader().repaint();
                        }
                    }
                });
            }
            return;
        }
        if (colsNumber > colsModel) {
            int delta = colsNumber - colModel.getColumnCount();
            for (int i = 0; i < delta; i++) {
                colModel.addColumn(new TableColumn());
            }
        }
        for (int i = 0; i < colsNumber; i++) {
            TableColumn column = colModel.getColumn(i);
            column.setHeaderValue(getColumnsInfo()[i].getColumnHeader());
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTable fixedJTable = (JTable) jTable.getClientProperty(CP_FIXED_TABLE);
                if (fixedJTable != null) {
                    fixedJTable.getTableHeader().repaint();
                }
                jTable.getTableHeader().repaint();
            }
        });
    }

    /**
     * Clean the grid
     */
    public void cleanJComponentValues() {
        setModelToJTable(new ArrayList());
    }


    public void repaint() {
        jTable.repaint();
    }

    /**
     * Set the value of the bean to the JComponent
     */
    public void setValueToJComponent(Object param) {
        final List valuesToBeShown = getValuesToBeShown(param);
        if (valuesToBeShown != null) {
            if (!sortedRows && comparator != null) {
                Collections.sort(valuesToBeShown, comparator);
            }
        }
        if (valuesToBeShown != null) {
            setModelToJTable(valuesToBeShown);
            loadDropDownsForCellRenderIfApply(valuesToBeShown);
        }
        gridManager.refreshValuesCounter();
    }

    private void loadDropDownsForCellRenderIfApply(List valuesToBeShown) {
        ColumnInfo columnInfo = getColumnInfoWithCellEditorValuesProvider();
        if (columnInfo == null) {
            return;
        }
        CellEditorValuesProvider cellEditorValuesProvider = columnInfo.getCellEditorValuesProvider();
        JComponentCellRenderer jComponentCellRenderer = columnInfo.getSpecialCellRenderer();
        for (int i = 0; i < valuesToBeShown.size(); i++) {
            Object currentRow = valuesToBeShown.get(i);
            Object obj = cellEditorValuesProvider.getTableCellEditorValues(currentRow, i);
            if (obj instanceof MetaLoader) {
                jComponentCellRenderer.putMetaLoader(i, (MetaLoader) obj);
            }
        }
    }

    private ColumnInfo getColumnInfoWithCellEditorValuesProvider() {
        for (ColumnInfo columnInfo : columnsInfo) {
            if (columnInfo.getCellEditorValuesProvider() != null) {
                return columnInfo;
            }
        }
        return null;
    }

    /**
     * Get the data that will be shown
     *
     * @return
     */
    private List getValuesToBeShown(Object param) {
        return valuesProvider.getList(param);
    }

    /**
     * Set a new model to the JTable based on the list that is sent as parameter
     * Also select the object that was selected in the previous list
     *
     * @param valuesToBeShown
     */
    public void setModelToJTable(List valuesToBeShown) {
        Object rowToBeSelected = null;
        if (addedRow != null) {
            rowToBeSelected = addedRow;
            addedRow = null;
        } else {
            rowToBeSelected = getSelectedRow();
        }
        TableModel tableModel = jTable.getModel();
        if ((tableModel instanceof JTableModel) && !alwaysReloadColumnInfo) {
            ((JTableModel) tableModel).setValues(valuesToBeShown);
        } else {
            jTable.setModel(createJTableModel(valuesToBeShown));
            if (!initializing) {
                configureColumns();
            }

        }
        setSelectedRow(rowToBeSelected);
    }

    private JTableModel createJTableModel(List valuesToBeShown) {
        JTableModel jTableModel = new JTableModel(columnsInfo);
        jTableModel.setReadOnly(isReadOnly);
        if (tableModelListener != null) {
            jTableModel.addTableModelListener(tableModelListener);
        }
        jTableModel.setValues(valuesToBeShown);
        return jTableModel;
    }

    /**
     * Return the selected row bean
     *
     * @return
     */
    public Object getSelectedRow() {
        return getSelectedRow(jTable);
    }

    /**
     * Get the index of the selected row
     *
     * @return
     */
    public int getSelectedRowIdx() {
        return jTable.getSelectedRow();
    }

    public List<Integer> getSelectedRowsIdx() {
        SelectorColumn selectorColumn = getSelectorColumn();
        return selectorColumn.getSelectedRowsIdx();
    }

    public void cleanSelectedRowsForRefresh() {
        SelectorColumn selectorColumn = getSelectorColumn();
        if (selectorColumn != null) {
            jCheckBoxCellHeaderRenderer.cleanJCheckBox();
            selectorColumn.clearForRefreshGrid();
        }
    }

    public void cleanSelectedRows() {
        SelectorColumn selectorColumn = getSelectorColumn();
        if (selectorColumn != null) {
            jCheckBoxCellHeaderRenderer.cleanJCheckBox();
            selectorColumn.clear(jTable);
        }
    }

    public void checkJCheckBoxHeader() {
        SelectorColumn selectorColumn = getSelectorColumn();
        if (selectorColumn != null) {
            jCheckBoxCellHeaderRenderer.checkJCheckBox();
            selectorColumn.selectAll(jTable);
        }
    }


    public void cleanCheckOnSelectedRow() {
        int selectedRow = getSelectedRowIdx();
        if (selectedRow >= 0) {
            SelectorColumn selectorColumn = getSelectorColumn();
            selectorColumn.removeSelectedRow(new Integer(selectedRow));
        }
    }

    public List getSelectedRows() {
        List selectedRows = new ArrayList();
        List<Integer> selectedRowsIdx = getSelectedRowsIdx();
        List currentRows = getValues();
        for (Integer idx : selectedRowsIdx) {
            selectedRows.add(currentRows.get(idx.intValue()));
        }
        return selectedRows;
    }


    /**
     * Return a row at specific position
     *
     * @param rowIndex
     * @return
     */
    public Object getRowAt(int rowIndex) {
        return getRowAt(jTable, rowIndex);
    }

    public static Object getSelectedRow(JTable jTable) {
        int selectedRowIndex = jTable.getSelectedRow();
        return getRowAt(jTable, selectedRowIndex);
    }

    public static Object getRowAt(JTable jTable, int rowIndex) {
        Object row = null;
        if (rowIndex > -1) {
            JTableModel jTableModel = getJTableModel(jTable);
            if (jTableModel != null && rowIndex < jTableModel.getRowCount()) {
                row = jTableModel.getRowAt(rowIndex);
            }
        }
        return row;
    }

    /**
     * Get the index for specific row, it will use the comparator if it exist.
     *
     * @param row
     * @return
     */
    public int indexOf(Object row) {
        return indexOf(row, true);
    }

    /**
     * @param row
     * @return
     */
    public boolean contains(Object row) {
        return indexOf(row) != -1;
    }

    /**
     * Get the index for specific row, it will use the comparator if it exist.
     *
     * @param row
     * @param withComparator: true, it will be tried to use the comparator in order to get the index,
     *                        otherwise, won´t be used the comparator
     * @return
     */
    public int indexOf(Object row, boolean withComparator) {
        int valueToReturn = -1;
        List list = getValues();
        if (!withComparator || comparator == null) {
            valueToReturn = list.indexOf(row);
        } else if (row != null) {
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                if (comparator.compare(row, o) == 0) {
                    valueToReturn = i;
                }
            }
        }
        return valueToReturn;
    }

    /**
     * @param jTable
     * @param bean
     * @return true if bean is contained in the table model
     */
    public static boolean contains(JTable jTable, Object bean) {
        JTableModel jTableModel = getJTableModel(jTable);
        List values = jTableModel.getValues();
        return values.contains(bean);
    }

    /**
     * Set the last added row
     *
     * @param addedRow
     */
    public void setAddedRow(Object addedRow) {
        this.addedRow = addedRow;
    }

    public void addRow(final int idx, final Object row) {
        if (logger.isDebugEnabled()) {
            logger.debug("Adding a row to the JTable:<" + row + ">");
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTableModel jTableModel = getJTableModel();
                jTableModel.addRow(idx, row);
                jTableModel.sort(comparator);
                setSelectedRow(row);
                gridManager.refreshValuesCounter();
            }
        });
    }

    /**
     * Add a row to the list that is shown
     *
     * @param row
     */
    public void addRow(final Object row) {
        if (logger.isDebugEnabled()) {
            logger.debug("Adding a row to the JTable:<" + row + ">");
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTableModel jTableModel = getJTableModel();
                jTableModel.addRow(row);
                jTableModel.sort(comparator);
                setSelectedRow(row);
                gridManager.refreshValuesCounter();
            }
        });
    }

    /**
     * Add a rows to the list that is shown
     *
     * @param rows
     */
    public void addAllRows(final List rows) {
        if (logger.isDebugEnabled()) {
            logger.debug("Adding rows to the JTable:<" + rows.size() + ">");
        }
        if (SwingUtilities.isEventDispatchThread()) {
            addAllRowsInternal(rows);
        } else {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        addAllRowsInternal(rows);
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private void addAllRowsInternal(List rows) {
        JTableModel jTableModel = getJTableModel();
        jTableModel.addAllRows(rows);
        jTableModel.sort(comparator);
        gridManager.refreshValuesCounter();
    }

    /**
     * Update the row in the jtable
     *
     * @param index
     * @param row
     */
    public void updateRow(final int index, final Object row) {
        if (SwingUtilities.isEventDispatchThread()) {
            updateRowInternal(index, row);
        } else {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        updateRowInternal(index, row);
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    private void updateRowInternal(int index, Object row) {
        JTableModel jTableModel = getJTableModel();
        jTableModel.updateRow(index, row);
        jTableModel.sort(comparator);
    }

    /**
     * Select the row that cointains specific value send as parameter
     *
     * @param rowToBeSelected
     */
    public void setSelectedRow(Object rowToBeSelected) {
        int indexToBeSelected = indexOf(rowToBeSelected);
        setSelectedRow(indexToBeSelected);
    }

    /**
     * Select the row that cointains specific value send as parameter
     */
    public void setSelectedRow(int indexToBeSelected) {
        int jtableSize = getValues().size();
        int currentSelectedRow = jTable.getSelectedRow();
        if (currentSelectedRow == indexToBeSelected) {
            if (logger.isDebugEnabled())
                logger.debug("It was not selected the row:<" + indexToBeSelected + "> because this row is the currentSelected row.");
            return;
        }
        if ((indexToBeSelected > -1) && (indexToBeSelected < jtableSize)) {
            jTable.setRowSelectionInterval(indexToBeSelected, indexToBeSelected);
            Rectangle rect = jTable.getCellRect(indexToBeSelected, 0, true);
            jTable.scrollRectToVisible(rect);
            if (logger.isDebugEnabled())
                logger.debug("Row selected is idx:" + indexToBeSelected);
        } else {
            if (logger.isDebugEnabled())
                logger.debug("No row is selected." + indexToBeSelected);
        }
    }

    /**
     * Remove the selected row
     */
    public void removeSelectedRow() {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing the selected row");
        }
        JTableModel jTableModel = getJTableModel();
        int selectedRowIndex = jTable.getSelectedRow();
        if (selectedRowIndex > -1) {
            jTableModel.removeRow(selectedRowIndex);
        }
        gridManager.refreshValuesCounter();
    }

    /**
     * Remove the selected row
     */
    public void remove(Object row) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing the row:" + row);
        }
        int index = indexOf(row);
        JTableModel jTableModel = getJTableModel();
        if (index > -1) {
            jTableModel.removeRow(index);
        } else {
            logger.warn("Row to be removed is not on the list (check equals/hashCode):" + row);
        }
        gridManager.refreshValuesCounter();
    }

    /**
     * Sort the values currently shown in the Jtable
     */
    public void sort() {
        JTableModel jTableModel = getJTableModel(jTable);
        if (comparator == null) {
            jTableModel.sort();
        } else {
            jTableModel.sort(comparator);
        }
    }

    /**
     * Get the JTableModel
     *
     * @return
     */
    private JTableModel getJTableModel() {
        return getJTableModel(jTable);
    }

    /**
     * Get the JTableModel
     *
     * @return
     */
    private static JTableModel getJTableModel(JTable jTable) {
        JTableModel jTableModel = null;
        if (jTable.getModel() instanceof JTableModel) {
            jTableModel = (JTableModel) jTable.getModel();
        }
        return jTableModel;
    }

    /**
     * Return a reference to a ColumnInfo based on its field name
     *
     * @param fieldName
     * @return
     */
    public ColumnInfo findColumnByPropertyName(String fieldName) {
        for (int i = 0; i < columnsInfo.length; i++) {
            ColumnInfo columnInfo = columnsInfo[i];
            if (columnInfo.getFieldName().equals(fieldName)) {
                return columnInfo;
            }
        }
        throw new IllegalStateException("Warning Column Info for this property [" + fieldName + "] doesn't exist");
    }

    /**
     * Sets the custom comparator used to sort the list
     *
     * @param comparator
     */
    public void setCustomSortComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public List getValues() {
        return getJTableModel().getValues();
    }

    public void setSortedRows(boolean sortedRows) {
        this.sortedRows = sortedRows;
    }

    /**
     * Register a selection listener to the JTable related to this class
     *
     * @param rowSelectionListener that will be called when the selection of the JTable changes
     */
    public void registerRowSelectionListener(final RowSelectionListener rowSelectionListener) {
        ListSelectionModel rowSM = jTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    logger.debug("No rows are selected.");
                    rowSelectionListener.onClearSelectedRow();
                } else {
                    int selectedRowIndex = lsm.getMinSelectionIndex();
                    logger.debug("Row " + selectedRowIndex + " is now selected.");
                    Object selectedRow = getSelectedRow();
                    rowSelectionListener.onSelectedRow(selectedRowIndex, selectedRow);
                }
            }
        });
    }


    public void setColumnsInfo(ColumnInfo[] columnsInfo) {
        this.columnsInfo = columnsInfo;
    }


    public ListProvider getValuesProvider() {
        return valuesProvider;
    }

    public void setValuesProvider(ListProvider valuesProvider) {
        this.valuesProvider = valuesProvider;
    }

    public void fireDataChanged() {
        getJTableModel().fireTableDataChanged();
    }

    CellEditableFocusListener cellEditableFocusListener;

    public CellEditableFocusListener getCellEditableFocusListener() {
        return new CellEditableFocusListener();
    }

    public void removeAllRows() {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing all rows");
        }
        JTableModel jTableModel = getJTableModel();
        jTableModel.removeAllRows();
    }

    SelectorColumn selectorColumn;

    public SelectorColumn getSelectorColumn() {
        if (selectorColumn == null) {
            for (ColumnInfo columnInfo : columnsInfo) {
                if (columnInfo instanceof SelectorColumn) {
                    selectorColumn = (SelectorColumn) columnInfo;
                    break;
                }
            }
        }
        return selectorColumn;
    }

    public MainAuditColumn getMainAuditColumn() {
        List mainColumns = ListUtils.getSubList(Arrays.asList(getColumnsInfo()), new UnaryPredicate() {
            public boolean test(Object o) {
                return o instanceof MainAuditColumn;
            }
        });
        if (mainColumns.size() > 0) {
            return (MainAuditColumn) mainColumns.get(0);
        }
        return null;
    }

    public int getFirstEditableColumn() {
        int firstEditableColumn = 0;
        ColumnInfo[] cols = getColumnsInfo();
        for (int i = 0; i < cols.length; i++) {
            ColumnInfo col = cols[i];
            if (col.isEditable() && !(col instanceof SelectorColumn) && !(col instanceof MainAuditColumn)) {
                firstEditableColumn = i;
                break;
            }
        }
        return firstEditableColumn;
    }

    public void setSelectedRowsIdx(List<Integer> rowsIdx) {
        SelectorColumn selectorColumn = getSelectorColumn();
        selectorColumn.setSelectedRows(rowsIdx);
        jTable.repaint();
    }

    public void recalculateDependentDropDowns(List<Integer> rowsIdx) {
        List<CellDropDownProvider> cellDropDownProviders = new ArrayList();
        for (int i = 0; i < columnsInfo.length; i++) {
            ColumnInfo columnInfo = columnsInfo[i];
            if (columnInfo.hasCellDropDownProvider()) {
                cellDropDownProviders.add(columnInfo.getCellDropDownProvider(this));
            }
        }
        for (CellDropDownProvider cellDropDownProvider : cellDropDownProviders) {
            cellDropDownProvider.clearAllDropDowns();
        }
    }


    public void setRefreshAbortable(boolean refreshAbortable) {
        this.refreshAbortable = refreshAbortable;
    }


    public class CellEditableFocusListener extends FocusAdapter {
        public void focusGained(FocusEvent e) {
//            gridManager.selectRowOnTable();
        }
    }

    /**
     * Set the configuration of the column info setting
     *
     * @param alwaysReloadColumnInfo : if it is true then always the column info will be reloaded
     *                               otherwise not. Default value is false.
     */
    public void setAlwaysReloadColumnInfo(boolean alwaysReloadColumnInfo) {
        this.alwaysReloadColumnInfo = alwaysReloadColumnInfo;
    }

    public void setGridManager(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    public JTable getJTable() {
        return jTable;
    }

    public void setRowColorBkgChanger(RowColorBkgChanger rowColorBkgChanger) {
        this.rowColorBkgChanger = rowColorBkgChanger;
    }

    public ColumnInfo[] getColumnsInfo() {
        return columnsInfo;
    }

    public void setTableModelListener(TableModelListener tableModelListener) {
        this.tableModelListener = tableModelListener;
    }

    private Boolean editable;

    public boolean isEditable() {
        if (editable == null) {
            editable = getEditableColumns().size() > 0;
        }
        return editable;
    }


    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    public boolean isFixedWidth() {
        return fixedWidth;
    }

    public void setFixedWidth(boolean fixedWidth) {
        this.fixedWidth = fixedWidth;
    }

    public List<ColumnInfo> getRequiredColumns() {
        return ListUtils.getSubList(Arrays.asList(getColumnsInfo()), new UnaryPredicate() {
            public boolean test(Object o) {
                return ((ColumnInfo) o).isRequired();
            }
        });
    }

    public List<ColumnInfo> getUniqueColumns() {
        return ListUtils.getSubList(Arrays.asList(getColumnsInfo()), new UnaryPredicate() {
            public boolean test(Object o) {
                return ((ColumnInfo) o).isUnique();
            }
        });
    }

    public List<ColumnInfo> getEditableColumns() {
        return ListUtils.getSubList(Arrays.asList(getColumnsInfo()), new UnaryPredicate() {
            public boolean test(Object o) {
                return ((ColumnInfo) o).isEditable() && !(o instanceof MainAuditColumn) && !(o instanceof SelectorColumn);
            }
        });
    }

    public void setCellColorChanger(CellColorChanger cellColorChanger) {
        this.cellColorChanger = cellColorChanger;
    }

    public void setFixedColumns(int fixedColumns) {
        this.fixedColumns = fixedColumns;
    }

    public int getFixedColumns() {
        return fixedColumns;
    }

    public FixedColumnTableMgr getFixedColumnTableMgr() {
        return fixedColumnTableMgr;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }

    public CellColorChanger getCellColorChanger() {
        return cellColorChanger;
    }

    public void setEditingRowPolicy(EditingRowPolicy editingRowPolicy) {
        this.editingRowPolicy = editingRowPolicy;
    }

    public GridFunction getGridFunction() {
        return gridFunction;
    }

    public void setGridFunction(GridFunction gridFunction) {
        this.gridFunction = gridFunction;
    }

    public void addTableModelListener(TableModelListener tableModelListener) {
        jTable.getModel().addTableModelListener(tableModelListener);
        JTable fixedTable = (JTable) jTable.getClientProperty(CP_FIXED_TABLE);
        if (fixedTable != null) {
            fixedTable.getModel().addTableModelListener(tableModelListener);
        }
    }

    public boolean isCreateAutoSortCols() {
        return createAutoSortCols;
    }

    public void setCreateAutoSortCols(boolean createAutoSortCols) {
        this.createAutoSortCols = createAutoSortCols;
    }
}
