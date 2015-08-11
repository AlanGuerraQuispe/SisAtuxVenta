package com.aw.support.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 03:21:31 PM
 */
public class Reduce {
    private Collection collection;
    protected Object result;

    private Reduce(Collection collection, Object result) {
        this.collection = collection;
        this.result = result;
    }

    public static Reduce overCollectionWithInitialValue(Collection c, Object initialValue) {
        return new Reduce(c, initialValue);
    }

    public static Reduce overArrayWithInitialValue(Object[] array, Object initialValue) {
        return overCollectionWithInitialValue(Arrays.asList(array), initialValue);
    }

    public Object reduce(ReduceExpression e) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            result = e.value(result, iterator.next());
        }
        return result;
    }
}
