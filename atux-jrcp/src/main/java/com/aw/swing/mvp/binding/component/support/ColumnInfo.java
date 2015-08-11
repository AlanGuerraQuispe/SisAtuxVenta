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
package com.aw.swing.mvp.binding.component.support;

import com.aw.core.cache.loader.DBDataLoader;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.context.AWBaseContext;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.format.DateFormatter;
import com.aw.core.format.Formatter;
import com.aw.core.format.NumberFormatter;
import com.aw.core.view.IColumnInfo;
import com.aw.support.beans.BeanUtils;
import com.aw.support.beans.ValueProvider;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.ComponentHierarchyInfo;
import com.aw.swing.mvp.binding.component.Hierarchyable;
import com.aw.swing.mvp.binding.component.support.table.edition.CellDropDownProvider;
import com.aw.swing.mvp.binding.component.support.table.edition.CellEditorValuesProvider;
import com.aw.swing.mvp.binding.component.support.table.edition.JCheckBoxCellRenderer;
import com.aw.swing.mvp.binding.component.support.table.edition.JComponentCellRenderer;
import com.aw.swing.mvp.focus.AWFocusEvent;
import com.aw.swing.mvp.focus.FocusZone;
import com.aw.swing.mvp.focus.GridFocusZone;
import com.aw.swing.mvp.navigation.AWWindowsManager;
import com.aw.swing.mvp.ui.grid.AWCustomCellRenderer;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import com.aw.swing.mvp.validation.support.PatternRules;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.SortDefinition;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class used to gather all of the information needed to populate the JTables
 * to Use specific formatters see the registerXXX () methods
 *
 * @author gmateo
 *         18/10/2004
 */
public class ColumnInfo implements IColumnInfo, Hierarchyable<ColumnInfo> {
    protected final Log logger = LogFactory.getLog(getClass());
    public final static int LEFT = SwingConstants.LEFT;
    public final static int RIGHT = SwingConstants.RIGHT;
    public final static int CENTER = SwingConstants.CENTER;

    public final static int TEXT_BOX = 0;
    public final static int COMBO_BOX = 1;
    public final static int CHECK_BOX = 2;
    public final static int RADIO_BUTTON = 3;
    public final static int FILE_CHOOSER = 4;
    public final static int BUTTON = 5;
    public final static int LINK = 6;
    public final static int TEXT_AREA = 7;

    protected ComponentHierarchyInfo<ColumnInfo> cmpHierarchyInfo = new ComponentHierarchyInfo<ColumnInfo>(this);

    /**
     * It is the TITLE of the column
     */
    private String columnHeader;
    /**
     * It is the bean's attribute name that will be shown in this column
     */
    protected String fieldName;
    /**
     * It is the row that is being processed currently
     */
    private BeanWrapper row;

    private TableCellRenderer customCellRenderer;

    /**
     * Used only for special and strange cases
     */
    private JComponentCellRenderer specialCellRenderer;
    /**
     * Used to return special value for the column it has more precedence than formatters
     */
    private ValueProvider valueProvider;
    /**
     * General formatter used to transfor column value to any other value
     * Transformation logic is encapsuled inside the formatter. Common Formatters
     * Date
     * DropDow value --> label
     * Numbers
     *
     * @see #formatAsDate()
     */
    private Formatter formatter;
    /**
     * Used to sort the view based on this column
     */
    private SortDefinition sortDefinition;

    /**
     * Indicate (if positive) the size of the column
     */
    private int width = -1;
    /**
     * Aligment of the column
     */
    private int alignment = LEFT;
    /**
     * Flag that points out if this column will be editable
     */
    private boolean editable = false;
    /**
     * Point out which component will be used to Edit
     */
    private int columnEditorType = TEXT_BOX;
    private CellEditorValuesProvider cellEditorValuesProvider;
    /**
     * Point out which component will be used to Edit
     */
    private int columnRendererType = -1;

    protected boolean isPrintable = true;

    private String dropDownKey;


    public Color background;
    public Color fontColor;

    private Object valueTrue = AWBaseContext.instance().getConfigInfoProvider().getCheckTrue();
    private Object valueFalse = AWBaseContext.instance().getConfigInfoProvider().getCheckFalse();

    private boolean isSequential = false;
    private Formatter sequentialFormatter;
    private boolean isStatus = false;
    /**
     * Used to determine the minimun size this column need on reports
     */
    private int reportCharsSize = -1;
    protected int idx = -1;
    private CellDropDownProvider cellDropDownProvider;

    private PropertyValidator propertyValidator;
    private PropertyBinding propertyBinding;
    private String validationRule;
    private boolean unique = false;
    private boolean fillEmptyWithLastValue = false;


    protected VetoableChangeListener vetoableChangeListener;
    private boolean fillingEmptyWithLastValueInProcess = false;

    public ColumnInfo(String columnHeader, String fieldName) {
        this.columnHeader = columnHeader;
        this.fieldName = fieldName;
    }

    /**
     * @param columnHeader column header
     * @param width        column with (in pixels?)
     * @param alignment    use {@link #LEFT}, {@link #RIGHT} or {@link #CENTER}
     */
    public ColumnInfo(String columnHeader, int width, int alignment) {
        this(columnHeader, null, width, alignment);
    }

    /**
     * @param columnHeader column header
     * @param fieldName    bean field name
     * @param width        column with (in pixels?)
     * @param alignment    use {@link #LEFT}, {@link #RIGHT} or {@link #CENTER}
     */
    public ColumnInfo(String columnHeader, String fieldName, int width, int alignment) {
        this(columnHeader, fieldName);
        this.width = width;
        this.alignment = alignment;
    }


    public ColumnInfo setCustomCellRenderer(TableCellRenderer cellRenderer) {
        setCustomCellRenderer(cellRenderer, true);
        return this;
    }

    public ColumnInfo setCustomCellRenderer(TableCellRenderer cellRenderer, boolean withDropDownFormatter) {
        this.customCellRenderer = cellRenderer;
        if (withDropDownFormatter)
            ((AWCustomCellRenderer) customCellRenderer).setDropDownFormatter((DropDownFormatter) formatter);
        return this;
    }


    public ColumnInfo setCustomFormatter(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public ColumnInfo formatAsDate() {
        formatter = DateFormatter.DATE_FORMATTER;
        return this;
    }

    public ColumnInfo formatAsTime() {
        formatter = DateFormatter.TIME_SECONDS_FORMATTER;
        return this;
    }

    public ColumnInfo formatAsHour() {
        formatter = DateFormatter.TIME_FORMATTER;
        return this;
    }

    public ColumnInfo formatAsDateTime() {
        formatter = DateFormatter.DATE_TIME_FORMATTER;
        return this;
    }

    public ColumnInfo formatAsMoney() {
        formatter = NumberFormatter.MONEY_FORMATTER;
        return this;
    }

    public ColumnInfo formatAsMoneyExchange() {
        formatter = NumberFormatter.MONEYEXCHANGE_FORMATTER;
        return this;
    }

    public ColumnInfo setNumberFormatter(String numberFormat) {
        formatter = new NumberFormatter(numberFormat);
        return this;
    }

    public ColumnInfo formatAsNumberWithGroup() {
        formatter = NumberFormatter.INTEGER_FORMATTER_W_GRP_SYM;
        return this;
    }

    public ColumnInfo formatAsNumberWithoutGroup() {
        formatter = NumberFormatter.NUMBER_FORMATTER_WOUT_GRP_SYM;
        return this;
    }

    public ColumnInfo formatAsNumberFloatWithGroup() {
        formatter = NumberFormatter.FLOAT_FORMATTER_W_GRP_SYM;
        return this;
    }

    public ColumnInfo renderAsCheckBox() {
        columnRendererType = CHECK_BOX;
        return this;
    }

    public void init() {
        initDropDown();
    }

    protected void initDropDown() {
        if (metaLoader == null) {
            return;
        }
        formatter = new DropDownFormatter(metaLoader);
        if (customCellRenderer != null) {
            ((AWCustomCellRenderer) customCellRenderer).setDropDownFormatter((DropDownFormatter) formatter);
        }
        this.dropDownKey = metaLoader.id();
    }


    public ColumnInfo setDropDownFormatter(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
        columnEditorType = COMBO_BOX;
        return this;
    }

    // todo con el soporte de cache sacar esto luego
    MetaLoader metaLoader;

    public MetaLoader getMetaLoader() {
        return metaLoader;
    }

    public ColumnInfo setAsSorted() {
        return setAsSorted(true);
    }


    public ColumnInfo setBackgroundAsHeader() {
        background = new Color(190, 67, 57);
        fontColor = new Color(255, 255, 255);
        return this;
    }

    public ColumnInfo setBackgroundColor(Color background,Color fontColor) {
        this.background = background;
        this.fontColor = fontColor;
        return this;
    }

    public ColumnInfo setAsSorted(boolean ascending) {
        sortDefinition = new MutableSortDefinition();
        ((MutableSortDefinition) sortDefinition).setProperty(fieldName);
        ((MutableSortDefinition) sortDefinition).setAscending(ascending);
        return this;
    }

    public boolean isPrintable() {
        return false;
    }

    public String getColumnHeader() {
        return columnHeader;
    }

    public String getFieldName() {
        return fieldName;
    }


    public boolean isSequential() {
        return isSequential;
    }

    public ColumnInfo setAsSequential() {
        isSequential = true;
        return this;
    }

    public ColumnInfo setAsSequential(Formatter formatter) {
        isSequential = true;
        sequentialFormatter = formatter;
        return this;
    }


    public boolean isStatus() {
        return isStatus;
    }

    public ColumnInfo setAsStatus() {
        isStatus = true;
        return this;
    }

    public Object getValueDDRFormated(Object object, int index) {
        Object value = getValue(object, index, -1);
//        if (dropDownKey!=null){
//            MappableList dropDownRows = (MappableList) Cache.instance().get(dropDownKey);
//            value = dropDownRows.mapGet(value);
//        }
        return value;
    }

    public Object getValue(Object object, int index) {
        throw new IllegalStateException("This method should not be called");
    }

    public Object getValue(Object object, int index, int row) {
        Object value = null;
        // Value Provider has the high precedence
        if (valueProvider != null) {
            value = valueProvider.getValue(object);
        } else if (object instanceof Object[]) {
            Object[] arrayOfValues = (Object[]) object;
            int newIndex = getIndexFromFieldName(index);
            value = arrayOfValues[newIndex];
        } else if (object instanceof List) {
            List listOfValues = (List) object;
            int newIndex = getIndexFromFieldName(index);
            value = listOfValues.get(newIndex);
        } else if (object instanceof Map) {
            Map mapValues = (Map) object;
            value = mapValues.get(fieldName);
        } else {
            value = BeanUtils.getPropertyValueNullSafe(getRow(object), fieldName);
        }

        if (isEditable()) {
            if ((formatter != null) && formatter instanceof DropDownFormatter) {
                return value;
            }
        }

        if (value != null) {
            if (formatter != null) {
                try {
                    if (formatter instanceof DropDownFormatter && hasParentCmps()) {
                        List<ColumnInfo> depends=getParentCmps();
                        for (ColumnInfo dependentCmp : depends) {

                            if (metaLoader!= null) {
                                if (metaLoader.getDataLoader() instanceof DBDataLoader) {
                                    DBDataLoader dataLoader = (DBDataLoader) metaLoader.getDataLoader();
                                    dataLoader.setSqlParams(new Object[]{BeanUtils.getPropertyValueNullSafe(getRow(object), dependentCmp.getFieldName())});
                                }
                            }
                        }
                        value = formatter.format(object, fieldName, value);
                    } else {
                        value = formatter.format(object, fieldName, value);
                    }


                } catch (Throwable e) {
                    logger.error("Error applying format in column '" + fieldName + "' for value:" + value + " formater:" + formatter + " Exception:" + e);
                    value = "ERR:" + value;
                    //throw AWBusinessException.wrapUnhandledException(logger, e);
                }
            }
        } else {
            if (formatter != null && formatter instanceof DropDownFormatter) {
                try {
                    value = formatter.format(object, fieldName, value);
                } catch (Throwable e) {
                    throw AWBusinessException.wrapUnhandledException(logger, e);
                }
            }
            if (value == null) {
                value = "";
            }
        }
        return value;
    }

    private int getIndexFromFieldName(int index) {
        if (fieldName != null && fieldName.length() > 0 &&
                Character.isDigit(fieldName.charAt(0))) {
            index = Integer.parseInt(fieldName);
        }
        return index;
    }

    /**
     * Set the value to the specific cell
     *
     * @param object
     */
    public void setValue(Object object, Object value, int row) {
        // At this moment in the case of Object[] only the first element could be updated.
        if (object instanceof Object[]) {
            ((Object[]) object)[0] = value;
        } else {
            if (value instanceof Boolean) {
                value = ((Boolean) value).booleanValue() ? valueTrue : valueFalse;
            }
            BeanWrapper bwRow = getRow(object);
            Object oldValue = null;
            if (existChangeValueListener() || existVetoableChangeListener()) {
                oldValue = bwRow.getPropertyValue(fieldName);
            }
            if (formatter == DateFormatter.DATE_FORMATTER) {
                value = BndIJTextField.getValidDate((String) value);
            }
            try {
                if (existVetoableChangeListener()) {
                    Object convertedValue = getConvertedValue(object, fieldName, value);
                    vetoableChangeListener.vetoableChange(object, oldValue, convertedValue);
                }
                bwRow.setPropertyValue(fieldName, value);
                value = bwRow.getPropertyValue(fieldName);
                executeChangeValueListener(object, value, oldValue);
            } catch (AWBusinessException e) {
                bwRow.setPropertyValue(fieldName, oldValue);
                showErrorMsg(e);
                return;
            }
        }
    }

    private Object getConvertedValue(Object object, String fieldName, Object value) {
        if (value instanceof BigDecimal) {
            BeanWrapper bwRow = new BeanWrapperImpl(object);
            logger.error("no soportado ----");
//            value = NumberUtils.convertNumberToTargetClass((Number) value, bwRow.getPropertyDescriptor(fieldName).getPropertyType());
        }
        return value;
    }

    private boolean existVetoableChangeListener() {
        return vetoableChangeListener != null;
    }

    public void executeChangeValueListener(Object object, Object value, Object oldValue) {
        if (existChangeValueListener()) {
            if ((oldValue == null && value != null) ||
                    (oldValue != null && value == null)) {
                onChangeValue(object, oldValue, value);
            }
            if (oldValue != null && value != null) {
                value = getConvertedValue(object, fieldName, value);
                if (!oldValue.equals(value)) {
                    onChangeValue(object, oldValue, value);
                }
            }
        }
    }

    protected boolean existChangeValueListener() {
        return getChangeValueListenerList() != null && getChangeValueListenerList().size() > 0;
    }

    protected void onChangeValue(Object obj, Object oldValue, Object newValue) {
        List<ChangeValueListener> changeValueListeners = getChangeValueListenerList();
        for (ChangeValueListener changeValueListener : changeValueListeners) {
            String[] modifiedCols = changeValueListener.onChangeValue(obj, oldValue, newValue);
            changeValueListener.setModifiedCols(modifiedCols);
        }
    }

    public void onActionPerformed(JTable table, AWTEvent actionEvent, Object obj, Object newValue) {
        List<ColumnActionListener> columnActionListeners = getColumnActionListenerList();
        boolean refreshRow = false;

        for (ColumnActionListener columnActionListener : columnActionListeners) {
            boolean refreshRowAux = columnActionListener.actionPerformed(actionEvent, obj, newValue);
            refreshRow = refreshRow || refreshRowAux;
        }
        table.repaint();
    }

    public void onClick(JTable table, MouseEvent actionEvent, Object obj, Object newValue) {
        List<ColumnActionListener> columnActionListeners = getColumnOnClickListenerList();
        boolean refreshRow = false;

        for (ColumnActionListener columnActionListener : columnActionListeners) {
            boolean refreshRowAux = columnActionListener.actionPerformed(actionEvent, obj, newValue);
            refreshRow = refreshRow || refreshRowAux;
        }
        table.repaint();
    }

    private BeanWrapper getRow(Object object) {
        if (object instanceof Object[]) return null;
        if (row == null) {
            row = new BeanWrapperImpl();
        }
        row = new BeanWrapperImpl(object);
        row.registerCustomEditor(Date.class, new CustomDateEditor(DateFormatter.DATE_FORMATTER.getDateFormat(), true));

        if (isEditable() && hasNumberFormatter()) {
            Class type = row.getPropertyDescriptor(fieldName).getPropertyType();
            NumberFormat numberFormat = ((NumberFormatter) formatter).getFormat();
            row.registerCustomEditor(null, fieldName, new CustomNumberEditor(type, numberFormat, true));
        }
        return row;
    }

    private boolean hasNumberFormatter() {
        return formatter != null && formatter instanceof NumberFormatter;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {

    }

    public ColumnInfo setWidthX(int width) {
        this.width = width;
        return this;
    }

    public int getAlignment() {
        return alignment;
    }

    public SortDefinition getSortDefinition() {
        return sortDefinition;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public TableCellRenderer getCustomCellRenderer() {
        if (this.customCellRenderer == null && columnRendererType == CHECK_BOX) {
            return new JCheckBoxCellRenderer(this, getValueTrue());
        }
        return this.customCellRenderer;
    }

    public boolean isEditable() {
        return editable;
    }

    public ColumnInfo setAsEditable() {
        this.editable = true;
        return this;
    }

    public ColumnInfo setAsUnique() {
        this.unique = true;
        return this;
    }

    public ColumnInfo setAsEditable(int editorType) {
        this.columnEditorType = editorType;
        return setAsEditable();
    }

    public ColumnInfo setAsEditable(CellEditorValuesProvider cellEditorValuesProvider) {
        this.cellEditorValuesProvider = cellEditorValuesProvider;
        return setAsEditable();
    }

    public ColumnInfo setCellValueProvider(ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
        return this;
    }


    public String getDropDownKey() {
        return dropDownKey;
    }

    public ColumnInfo setTrueFalseValues(Object valueTrue, Object valueFalse) {
        this.valueTrue = valueTrue;
        this.valueFalse = valueFalse;
        return this;
    }


    public Object getValueTrue() {
        return valueTrue;
    }

    public Object getValueFalse() {
        return valueFalse;
    }

    public ColumnInfo setRptChrs(int reportCharsSize) {
        this.reportCharsSize = reportCharsSize;
        return this;
    }

    public int getReportCharsSize() {
        return reportCharsSize;
    }


    public ColumnInfo setAsNoPrintable() {
        this.isPrintable = false;
        return this;
    }

    public int getColumnEditorType() {
        return columnEditorType;
    }

    public ColumnInfo dependsOn(ColumnInfo masterCmp) {
        cmpHierarchyInfo.dependsOn(masterCmp);
        return this;
    }

    public void addDependentCmp(ColumnInfo dependentCmp) {
        cmpHierarchyInfo.addDependentCmp(dependentCmp);
    }

    public boolean hasDependentCmps() {
        return cmpHierarchyInfo.hasDependentCmps();
    }

    public boolean hasParentCmps() {
        return cmpHierarchyInfo.hasParentCmps();
    }

    public List<ColumnInfo> getParentCmps() {
        return cmpHierarchyInfo.getParentCmps();
    }

    public List<ColumnInfo> getDependentCmps() {
        return cmpHierarchyInfo.getDependentCmps();
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public Color getBackground() {
        return background;
    }

    public int getColumnRendererType() {
        return columnRendererType;
    }

    public Formatter getSequentialFormatter() {
        return sequentialFormatter;
    }

    public CellEditorValuesProvider getCellEditorValuesProvider() {
        return cellEditorValuesProvider;
    }

    List<ChangeValueListener> changeValueListenerList = new ArrayList();
    List<ColumnActionListener> columnActionListenerList = new ArrayList();
    List<ColumnActionListener> columnOnClickListenerList = new ArrayList();

    public ColumnInfo addChangeValueListener(ChangeValueListener changeValueListener) {
        this.changeValueListenerList.add(changeValueListener);
        return this;
    }

    public ColumnInfo addActionListener(ColumnActionListener columnActionListener) {
        this.columnActionListenerList.add(columnActionListener);
        return this;
    }

    public ColumnInfo addOnClickListener(ColumnActionListener columnActionListener) {
        this.columnOnClickListenerList.add(columnActionListener);
        return this;
    }

    public List<ChangeValueListener> getChangeValueListenerList() {
        return changeValueListenerList;
    }

    public List<ColumnActionListener> getColumnActionListenerList() {
        return columnActionListenerList;
    }

    public List<ColumnActionListener> getColumnOnClickListenerList() {
        return columnOnClickListenerList;
    }

    public JComponentCellRenderer getSpecialCellRenderer() {
        return specialCellRenderer;
    }

    public void setSpecialCellRenderer(JComponentCellRenderer specialCellRenderer) {
        this.specialCellRenderer = specialCellRenderer;
    }

    public boolean hasCellDropDownProvider() {
        return (cellDropDownProvider != null);
    }

    public CellDropDownProvider getCellDropDownProvider(BndSJTable bndSJTable) {
        if (cellDropDownProvider == null) {
            cellDropDownProvider = new CellDropDownProvider(bndSJTable, this);
        }
        return cellDropDownProvider;
    }

    public void clearCellDropDownsAt(int rowIdx) {
        if (cellDropDownProvider != null) {
            cellDropDownProvider.clearDropDownsAt(rowIdx);
        }
    }

    public ColumnInfo validateUsing(String validationRule) {
        this.validationRule = validationRule;
        return this;
    }

    public void initializeValidator() {
        boolean existValidationRule = StringUtils.hasText(validationRule);
        if (isEditable() || existValidationRule) {
            propertyValidator = new PropertyValidator();
        }
        if (existValidationRule) {
            propertyValidator = PatternRules.buildPropertyValidator(validationRule);
        }
        if (propertyValidator != null) {
            propertyBinding = new PropertyBinding();
            propertyBinding.setHorizontalAligement(alignment);
            if (formatter != null) {
                if (formatter == DateFormatter.DATE_FORMATTER) {
                    propertyValidator.setDateFormat(true);
                    // todo read the size of the date format used
                    propertyBinding.setLimitTextSize(10);
                } else if (formatter == NumberFormatter.MONEY_FORMATTER) {
                    propertyValidator.setMoneyFormat(true);
                } else if (formatter == NumberFormatter.FLOAT_FORMATTER_W_GRP_SYM || formatter == NumberFormatter.MONEYEXCHANGE_FORMATTER) {
                    propertyValidator.setFloatWithGroupFormat(true);
                }
            }
        }
    }

    public PropertyValidator getPropertyValidator() {
        return propertyValidator;
    }

    public PropertyBinding getPropertyBinding() {
        return propertyBinding;
    }

    public boolean isRequired() {
        return propertyValidator != null && propertyValidator.isRequerid();
    }

    //todo:gmc immplemntar esto
    public String getPdfColumnHeader() {
        return null;
    }

    //todo:gmc immplemntar esto
    public ColumnInfo setPdfColumnHeader(String pdfColumnHeader) {
        return null;
    }

    public boolean isUnique() {
        return unique;
    }

    public String getValidationRule() {
        return validationRule;
    }

    public ColumnInfo addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        this.vetoableChangeListener = vetoableChangeListener;
        return this;
    }

    protected void showErrorMsg(AWBusinessException e) {
        Presenter pst = AWWindowsManager.instance().getCurrentPst();
        FocusZone focusZone = pst.getAWFocusManager().getCurrentFocusZone();
        GridFocusZone gridFocusZone = null;
        if (focusZone != null && focusZone instanceof GridFocusZone) {
            gridFocusZone = (GridFocusZone) focusZone;
        }
        MsgDisplayer.showMessage(e.getMessage());
        if (gridFocusZone != null) {
            gridFocusZone.focusReGainedAfterTemporaryLost(new AWFocusEvent(null, new JButton()));
        }
    }


    public ColumnInfo fillEmptyWithLastValue() {
        fillEmptyWithLastValue = true;
        return this;
    }

    public String toString() {
        return this.fieldName + "-w:" + this.width;
    }

    public boolean isFillEmptyWithLastValue() {
        return fillEmptyWithLastValue;
    }

    public ColumnInfo formatUsingCustomFormatter(DropDownFormatter dropDownFormatter) {
        this.formatter = dropDownFormatter;
        return this;
    }

    public boolean isFillingEmptyWithLastValueInProcess() {
        return fillingEmptyWithLastValueInProcess;
    }

    public void setFillingEmptyWithLastValueInProcess(boolean fillingEmptyWithLastValueInProcess) {
        this.fillingEmptyWithLastValueInProcess = fillingEmptyWithLastValueInProcess;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
}

