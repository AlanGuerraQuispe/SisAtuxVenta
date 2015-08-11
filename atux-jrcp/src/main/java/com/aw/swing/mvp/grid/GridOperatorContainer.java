package com.aw.swing.mvp.grid;

import com.aw.support.ObjectConverter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.BndSJTable;
import com.aw.swing.mvp.binding.component.support.table.JTableModel;
import org.apache.commons.functor.UnaryPredicate;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 01/02/2008
 */
public class GridOperatorContainer {
    private TableModelListener tableModelListener = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            processTableChanged(e);
        }
    };

    private BndSJTable bndSJTable;
    private List<GridOperation> gridOperationList = new ArrayList<GridOperation>();

    public void processTableChanged(TableModelEvent e) {
        for (GridOperation gridOperation : gridOperationList) {
            gridOperation.execute(bndSJTable, e);
        }
    }

    public TableModelListener getTableModelListener() {
        return tableModelListener;
    }

    public void setBndSJTable(BndSJTable bndSJTable) {
        this.bndSJTable = bndSJTable;
    }

    public GridOperatorContainer add(GridOperation gridOperation) {
        gridOperationList.add(gridOperation);
        return this;
    }


    public interface GridOperation{
        public void execute(BndSJTable bndSJTable, TableModelEvent e);
    }

    public abstract static class ColumnOperation<E> implements GridOperation{
        protected Object columnProperty;//cached wrap
        protected BeanWrapper wrap = new BeanWrapperImpl();
        protected Object result;
        private UnaryPredicate unaryPredicate;

        public ColumnOperation(Object columnProperty) {
            this.columnProperty = columnProperty;
            if (columnProperty ==null) throw new IllegalArgumentException("Argument is null:columnProperty");
        }
        public void execute(BndSJTable bndSJTable, TableModelEvent e) {
            Object source = null;
            if (e !=null){
                source = e.getSource();
            }
            JTableModel jTableModel = null;
            if (source !=null && source instanceof JTableModel){
                jTableModel = (JTableModel) source;                
            }
            List<E> rows = jTableModel !=null ? jTableModel.getValues(): bndSJTable.getValues();
            result = initResult(result);
            for (E bean : rows) {
                if (unaryPredicate==null || unaryPredicate.test(bean)){
                    executeRow(bean);
                }
            }
            resultCalculated(result);
        }


        protected Object executeRow(E bean) {
            Object rowValue = null;
            wrap=new BeanWrapperImpl(bean);
            if (columnProperty instanceof String )rowValue  = wrap.getPropertyValue((String)columnProperty);
            else throw new IllegalArgumentException("Non String columnProperty:"+columnProperty+" not supported ad code here");

            executeRowOperation(bean, wrap, rowValue);

            return rowValue;
        }

        protected abstract Object initResult(Object result);
        protected abstract void executeRowOperation(E bean, BeanWrapper beanWrap, Object rowColValue);

        protected abstract void resultCalculated(Object result);

        protected static  void formatMoney(BindingComponent resultField) {
            resultField.setUIReadOnly(true);
            if (resultField instanceof BndIJTextField)
                ((BndIJTextField) resultField).formatAsMoney();
        }

        protected static BigDecimal  add(BigDecimal total, Number value ){
            return total.add((BigDecimal) ObjectConverter.convert(value, BigDecimal.class));
        }
        public ColumnOperation<E> setUnaryPredicate(UnaryPredicate unaryPredicate) {
            this.unaryPredicate = unaryPredicate;
            return this;
        }

    }
    /**
     * Permite sumar una columna y poner el resultado en una caja de texto.
     */
    public static class ColumnSum extends ColumnOperation {
        protected BindingComponent resultField;

        public ColumnSum(Object columnProperty, BindingComponent resultField) {
            super(columnProperty);
            if (resultField ==null) throw new IllegalArgumentException("Argument is null:resultField");
            this.resultField = resultField;
        }

        public ColumnSum formatMoney() {
            formatMoney(resultField);
//            if (resultField instanceof BndIJLabelField)
//                ((BndIJLabelField)resultField).formatAsMoney();
            return this;
        }

        public ColumnSum formatInt() {
            resultField.setUIReadOnly(true);
            if (resultField instanceof BndIJTextField)
                ((BndIJTextField)resultField).formatAsNumber();
//            if (resultField instanceof BndIJLabelField)
//                ((BndIJLabelField)resultField).formatAsMoney();
            return this;
        }

        protected Object initResult(Object result) {
            return new BigDecimal(0);
        }

        protected void executeRowOperation(Object bean, BeanWrapper beanWrap, Object rowColValue) {
            if (rowColValue!=null){
                System.out.println("resulto "+result+ " value "+rowColValue);
                result = add((BigDecimal)result, (Number) rowColValue);
            }
        }
        protected void resultCalculated(Object total) {
            resultField.setValue(total);
            resultField.setValueToBean();
        }

    }

}

