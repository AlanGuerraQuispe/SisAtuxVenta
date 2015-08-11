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
package com.aw.swing.mvp.grid;

import com.aw.core.util.DeleteableList;
import com.aw.core.util.annotation.DeleteableListField;
import com.aw.support.collection.ListProvider;
import com.aw.support.reflection.ReflectionUtils;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gmateo
 *         31/01/2005
 */
public abstract class GridInfoProvider<E> {
    protected final Log logger = LogFactory.getLog(getClass());


    protected Boolean existSequentialColumn = null;


    public GridInfoProvider() {
    }

    /**
     * Get the column information that will be used for the grid
     *
     * @return
     */
    public abstract ColumnInfo[] getColumnInfo();

    /**
     * Contain the values that will be show in the grid
     *
     * @return
     */
    public abstract List getValues(E obj);

    /**
     * Get Object that will be used to get the data to be shown
     *
     * @return
     */
    public ListProvider getListProvider(final Presenter presenter) {
        final GridInfoProvider gridInfoProvider = this;
        return new ListProvider() {
            public List getList(Object param) {
                List list = getValues((E) param);
                if (list == null) {
                    list = new ArrayList();
                }
                if (!(presenter instanceof FindPresenter)) {
                    Method method = null;
                    try {
                        method = gridInfoProvider.getClass().getMethod("getValues", param.getClass());
                    } catch (NoSuchMethodException e) {
                    }
                    if (method==null){
                        method = ReflectionUtils.findMethodByName(gridInfoProvider.getClass(), "getValues");
                    }
                    DeleteableListField deleteableListField = AnnotationUtils.getAnnotation(method, DeleteableListField.class);
                    if (deleteableListField != null) {
                        if (!(list instanceof DeleteableList)) {
                            DeleteableList deleteableList = new DeleteableList();
                            deleteableList.init(list);
                            BeanWrapper bw = new BeanWrapperImpl(param);
                            bw.setPropertyValue(deleteableListField.value(), deleteableList);
                            return deleteableList;
                        }
                    }
                }
                return list;
            }
        };
    }

    /**
     * Method call when a row is selected
     *
     * @param currentRow
     */
    public void onSelectedRow(Object currentRow) {

    }

    /**
     * Method call when the selected row is clear it means there are any selected row
     */
    public void onClearSelectedRow() {

    }

    public boolean existSequentialColumn() {
        if (existSequentialColumn == null) {
            existSequentialColumn = Boolean.TRUE;
            String seqColFieldName = getSequentialColumnFieldName();
            if (seqColFieldName == null) {
                existSequentialColumn = Boolean.FALSE;
            }
        }
        return existSequentialColumn.booleanValue();
    }

    public String getStatusColumnFieldName() {
        ColumnInfo[] columnInfo = getColumnInfo();
        for (int i = 0; i < columnInfo.length; i++) {
            ColumnInfo colInfo = columnInfo[i];
            if (colInfo.isStatus()) {
                return colInfo.getFieldName();
            }
        }
        return null;
    }

    public ColumnInfo getSequentialColumnInfo() {
        ColumnInfo[] columnInfo = getColumnInfo();
        for (int i = 0; i < columnInfo.length; i++) {
            ColumnInfo colInfo = columnInfo[i];
            if (colInfo.isSequential()) {
                return colInfo;
            }
        }
        return null;
    }

    public String getSequentialColumnFieldName() {
        ColumnInfo[] columnInfo = getColumnInfo();
        for (int i = 0; i < columnInfo.length; i++) {
            ColumnInfo colInfo = columnInfo[i];
            if (colInfo.isSequential()) {
                return colInfo.getFieldName();
            }
        }
        return null;
    }

}
