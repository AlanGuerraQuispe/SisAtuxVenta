package com.aw.swing.mvp.grid;

import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.format.Formatter;
import com.aw.swing.exception.AWGridValidationException;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.ui.grid.Cell;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * User: gmc
 * Date: 07/06/2009
 */
public class GridValidator {

    protected GridProvider gridProvider;

    protected String enforceRowUniquenessMsg = "";


    public GridValidator(GridProvider gridProvider) {
        this.gridProvider = gridProvider;
    }

    public void validate() {
        if (!gridProvider.isEditable() || gridProvider.getValues().size() == 0) {
            return;
        }
        validateRequiredColumns();
        validateOtherRules();
    }

    private void validateOtherRules() {
        List<ColumnInfo> uniqueColumns = gridProvider.getBndSJTable().getUniqueColumns();
        if (uniqueColumns.size()>0){
            validateUniqueness(uniqueColumns);
        }
        if (StringUtils.hasText(enforceRowUniquenessMsg)) {
            validateUniquenessBasedOnEquals();
        }
    }



    private void validateRequiredColumns() {
        List<ColumnInfo> requiredColums = gridProvider.getBndSJTable().getRequiredColumns();
        if (requiredColums.size() == 0) return;
        List values = gridProvider.getValues();
        boolean editable = true;
        EditingRowPolicy editingRowPolicy = gridProvider.getEditingRowPolicy();
        for (int i = 0; i < values.size(); i++) {
            Object row = values.get(i);
            BeanWrapper bw = new BeanWrapperImpl(row);
            for (ColumnInfo requiredColum : requiredColums) {
                if (editingRowPolicy!=null){
                    editable = editingRowPolicy.isEditable(row,requiredColum.getFieldName());
                }
                if (editable){
                    Object value = bw.getPropertyValue(requiredColum.getFieldName());
                    if ((value == null) ||
                            ((value instanceof String) && "".equals(value))) {
                        throw new AWGridValidationException("Debe ingresar un valor en la columna: " + requiredColum.getColumnHeader() + "", null, gridProvider.getJTable(), new Cell(i, requiredColum.getIdx()));
                    }
                }
            }
        }
    }

    private void validateUniqueness(List<ColumnInfo> uniqueColumns) {
        List values = gridProvider.getValues();
        Set set = new HashSet();
        Map<Integer,Set> verifier = new HashMap();
        for (int i = 0; i < values.size(); i++) {
            Object row = values.get(i);
            BeanWrapper bw = new BeanWrapperImpl(row);
            for (ColumnInfo uniqueColumn : uniqueColumns) {
                if (!verifier.containsKey(uniqueColumn.getIdx())){
                    verifier.put(uniqueColumn.getIdx(),new HashSet());
                }
                Object value = bw.getPropertyValue(uniqueColumn.getFieldName());
                boolean agregado = verifier.get(uniqueColumn.getIdx()).add(value);
                if (!agregado) {
                    Formatter formatter = uniqueColumn.getFormatter();
                    if (formatter instanceof DropDownFormatter)
                        value = formatter.format(value);
                    throw new AWGridValidationException("Los valores de la columna: " +uniqueColumn.getColumnHeader() +
                            " no se pueden repetir.\nValor repetido: "+value , 
                            null, gridProvider.getJTable(), new Cell(i, uniqueColumn.getIdx()));
                }
            }
        }
    }

    private void validateUniquenessBasedOnEquals() {
        List values = gridProvider.getValues();
        Set set = new HashSet();
        for (int i = 0; i < values.size(); i++) {
            Object row = values.get(i);
            if (!set.add(row)) {
                throw new AWGridValidationException(enforceRowUniquenessMsg, null, gridProvider.getJTable(), new Cell(i, 0));
            }
        }
    }


    public void enforceRowUniqueness(String msg) {
        enforceRowUniquenessMsg = msg;
    }
}
