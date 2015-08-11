package com.aw.support.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 12:40:04 PM
 */
public class Map {
        private Collection collection;

        private Map(Collection collection) {
            this.collection = collection;
        }

        public static Map overCollection(Collection c) {
            return new Map(c);
        }

        public static Map overArray(Object[] array) {
            return overCollection(Arrays.asList(array));
        }

        public Collection collect(Expression e) {
            Collection result = new ArrayList();
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                Object item = e.value(iterator.next());
                if (item != null) result.add(item);
            }
            return result;
        }
    }
