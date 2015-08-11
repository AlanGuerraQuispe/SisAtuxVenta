package com.aw.support.collection;

import com.aw.support.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Julio C. Macavilca
* Date: 20/02/2008
*/
class FindUnaryFunction extends PropertyUnaryFunction {
    private final Object value;

    public FindUnaryFunction(String propertyName, Object value) {
        super(propertyName);
        this.value = value;
        this.result = new ArrayList();
    }

    protected Object evaluate(Object bean, Object propertyValue) {
        if (BeanUtils.equals(value, propertyValue)) {
            getList().add( bean );
        }
        return null;
    }

    public List getList() {
        return (List) result;
    }
}
