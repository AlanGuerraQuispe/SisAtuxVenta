package com.aw.support.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Julio C. Macavilca
* Date: 20/02/2008
*/
public class FindDuplicatedUnaryFunction extends PropertyUnaryFunction {


    public FindDuplicatedUnaryFunction(String propertyName) {
        super(propertyName);
        this.result = new HashMap<Object, List>();
    }

    protected Object evaluate(Object bean, Object propertyValue) {
        Map<Object, List> workList = getWorkList();
        List duplicates =  workList.get(propertyValue);
        if (duplicates==null){
            duplicates = new ArrayList(2);
            workList.put(propertyValue,duplicates);
        }
        duplicates.add(bean);
        return null;
    }

    private Map<Object, List> getWorkList() {
        return (Map<Object, List>)result;
    }
    public Map<Object, List> getDups() {
        Map<Object, List> dups = new HashMap<Object, List>(getWorkList());
        for (Object propertyValue : getWorkList().keySet()) {
            if (dups.get(propertyValue).size()<=1)
                dups.remove(propertyValue);
        }
        return dups;
    }
    
}