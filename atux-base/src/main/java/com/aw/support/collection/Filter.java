package com.aw.support.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 03:17:36 PM
 */
public class Filter {
    private Collection collection;

    private Filter(Collection collection) {
        this.collection = collection;
    }

    public static Filter overCollection(Collection c) {
        return new Filter(c);
    }

    public static Filter overArray(Object[] array) {
        return overCollection(Arrays.asList(array));
    }

    public Object select(BooleanExpression booleanExpression) {
        Collection result = new ArrayList();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (booleanExpression.value(item)) result.add(item);
        }

        return result;
    }

    public Object detect(BooleanExpression booleanExpression) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (booleanExpression.value(item)) return item;
        }
        return null;
    }

    public Object reject(BooleanExpression booleanExpression) {
        Collection result = new ArrayList();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (!booleanExpression.value(item)) result.add(item);
        }
        return result;
    }
}
