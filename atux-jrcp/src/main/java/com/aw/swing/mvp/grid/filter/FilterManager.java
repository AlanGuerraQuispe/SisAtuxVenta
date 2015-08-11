package com.aw.swing.mvp.grid.filter;

import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.functor.UnaryPredicate;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 26-nov-2007
 */
public class FilterManager {
    protected List allValues;
    protected List lastFilteredValues;
    protected GridProvider gridProvider;

    public FilterManager(GridProvider gridProvider) {
        this.gridProvider = gridProvider;
    }

    public void filter(List attributes,String operator) {
        CriteriaFactory criteriaFactory = new CriteriaFactory();
        UnaryPredicate predicate = criteriaFactory.createCriteria(attributes,operator);
        filter(predicate);        
    }
    
    public void filter(UnaryPredicate predicate) {
        List values = getValues();
        List filteredValues = new ArrayList();
        for (int i = 0; i < values.size(); i++) {
            Object bean = values.get(i);
            BeanWrapper beanWrapper = new BeanWrapperImpl(bean);
            if (predicate.test(beanWrapper)) {
                filteredValues.add(bean);
            }
        }
        setGridValues(filteredValues);
        lastFilteredValues = filteredValues;
    }

    public void clearFilter() {
        setGridValues(allValues);
        lastFilteredValues = null;
        allValues = null;
    }

    private void setGridValues(List values) {
        gridProvider.setValues(values);
    }

    public List getValues() {
        if (lastFilteredValues == null) {
            allValues = (List) gridProvider.getValues();
            return allValues;
        }
        return lastFilteredValues;
    }
}
