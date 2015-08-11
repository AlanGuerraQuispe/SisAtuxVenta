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
package com.aw.support.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class used provide util methods to transform collections
 *
 * @author gmateo
 *         17-oct-2004
 */
public class CollectionTransformer {
    /**
     * Return List based on the collection
     *
     * @param collection
     * @return
     */
    public static List getListFromCollection(Collection collection) {
        if (collection == null) {
            return new ArrayList();
        }
        List listToReturn = null;
        if (collection instanceof List) {
            listToReturn = (List) collection;
        } else {
            listToReturn = new ArrayList();
            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
                Object object = iterator.next();
                listToReturn.add(object);
            }
        }
        return listToReturn;
    }
}
