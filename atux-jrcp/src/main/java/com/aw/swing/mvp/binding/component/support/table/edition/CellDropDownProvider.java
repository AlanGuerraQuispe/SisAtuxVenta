package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.MappableList;
import com.aw.core.cache.loader.DBDataLoader;
import com.aw.core.cache.loader.DataLoader;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * Used in dependent JComboBox
 * User: gmc
 * Date: 30/05/2009
 */
public class CellDropDownProvider {
    ColumnInfo columnInfo;
    BndSJTable bndSJTable;

    public CellDropDownProvider(BndSJTable bndSJTable, ColumnInfo columnInfo) {
        this.bndSJTable = bndSJTable;
        this.columnInfo = columnInfo;
    }

    Map<Integer, MappableList> dropDownRows = new HashMap();


    public void put(int row, MappableList values) {
        dropDownRows.put(new Integer(row), values);
    }


    public void remove(int row) {
        dropDownRows.remove(new Integer(row));
    }


    public MappableList getDropDowns(int rowIdx) {
        if (rowIdx == -1) {
            return new MappableList();
        }
        if (!dropDownRows.containsKey(new Integer(rowIdx))) {
            System.out.println("LOADING DROP DOWN VALUES <r,c>:<" + rowIdx + "," + columnInfo.getIdx() + ">");
            Object row = bndSJTable.getValues().get(rowIdx);
            List masterValues = getMasterValues(columnInfo, row);
            MetaLoader metaLoader = columnInfo.getMetaLoader();
            MappableList values = (MappableList) refreshMetaLoader(metaLoader, masterValues.toArray());
            put(rowIdx, values);
        }
        return dropDownRows.get(new Integer(rowIdx));
    }

    private List getMasterValues(ColumnInfo columnInfo, Object row) {
        List masterValues = new ArrayList();
        List<ColumnInfo> parents = columnInfo.getParentCmps();
        ColumnInfo parentCmp = parents.get(0);
        BeanWrapper bw = new BeanWrapperImpl(row);
        masterValues.add(bw.getPropertyValue(parentCmp.getFieldName()));
        if (parentCmp.hasParentCmps()) {
            masterValues.addAll(getMasterValues(parentCmp, row));
        }
        Collections.reverse(masterValues);
        return masterValues;
    }


    public List<DropDownRow> refreshMetaLoader(MetaLoader metaLoader, Object... params) {
        DataLoader dataLoader = metaLoader.getDataLoader();
        if (dataLoader instanceof DBDataLoader) {
            DBDataLoader dbDataLoader = (DBDataLoader) dataLoader;
            if (params != null) {
                dbDataLoader.setSqlParams(params);
            }
        }
        return metaLoader.getRows();
    }

    public void clearDropDownsAt(int row) {
        remove(row);
    }

    public void clearAllDropDowns() {
        dropDownRows.clear();
    }
}

