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
package com.aw.core.util;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * @author jcvergara
 *         07/11/2004
 */
public class EntityHelper {
    public static List remove(Collection entityList1,
                              Collection entityList2, Comparator comparator) {
        List result = new ArrayList();
        for (Iterator iter1 = entityList1.iterator(); iter1.hasNext();) {
            Object o1 = iter1.next();
            boolean containedOnList2 = false;
            for (Iterator iter2 = entityList2.iterator(); iter2.hasNext();) {
                Object o2 = iter2.next();
                if (comparator.compare(o1, o2) == 0) {
                    containedOnList2 = true;
                    break;
                }
            }

            if (!containedOnList2)
                result.add(o1);
        }
        return result;
    }

    /**
     * Create a subList with the objects that match the criteria
     *
     * @param entityList
     * @param entityMatcher any object that has the {@link Object#equals(java.lang.Object)} overwrittten
     * @return
     */
    public static List filterList(Collection entityList, Object entityMatcher) {
        List list = new ArrayList();
        Object bean;
        for (Iterator iterator = entityList.iterator(); iterator.hasNext();) {
            bean = iterator.next();
            if (entityMatcher.equals(bean)) list.add(bean);
        }
        return list;
    }

    /**
     * Search for the first ocurrence of the entity on the List
     *
     * @param entityList
     * @param entityMatcher
     * @return the matched instance or nulll if no exists
     */
    public static Object searchEntity(Collection entityList, final EntityMatcher entityMatcher) {
        IteratorFunction matcherIterator = new IteratorFunction() {
            private Object matchedInstance = null;

            public void execute(Object currentInstance) {
//                if (classFilter!=null && currentInstance.getClass() != classFilter) {
//                    //ignore this test
//                }else{
                if (entityMatcher.equals(currentInstance)) {
                    this.stopIteration();
                    matchedInstance = currentInstance;
                }
//                }
            }

            public Object getResult() {
                return matchedInstance;
            }
        };
        return process(entityList, matcherIterator);
    }

    /**
     * Search in the collection for the first object that matches the criteria
     *
     * @param entityList
     * @param codeToSearch
     * @param entityAttributeName
     * @return the instance, or NULL if no instance matching the criteria is found
     * @deprecated use {@link #searchEntity(java.util.Collection, com.aw.core.domain.util.EntityMatcher)}
     */
    public static Object searchEntity(Collection entityList, Object codeToSearch, String entityAttributeName) {
        return searchEntity(entityList, null, new Object[]{codeToSearch}, new String[]{entityAttributeName});
    }

    /**
     * Search in the collection for the first object that matches the criteria
     *
     * @param entityList
     * @param classFilter
     * @param codeToSearch
     * @param entityAttributeName
     * @return the instance, or NULL if no instance matching the criteria is found
     * @deprecated use {@link #searchEntity(java.util.Collection, com.aw.core.domain.util.EntityMatcher)}
     */
    public static Object searchEntity(Collection entityList, Class classFilter, Object[] codeToSearch, String[] entityAttributeName) {
        // search for the entity
        // (considerar todas activas e inactivas)
        BeanWrapper wrap = new BeanWrapperImpl();
        for (Iterator iter = entityList.iterator(); iter.hasNext();) {
            Object bean = iter.next();
            if (classFilter != null && bean.getClass() != classFilter) continue;

            wrap = new BeanWrapperImpl(bean);

            // test every atribute
            boolean failedMatch = false;
            for (int i = 0; i < codeToSearch.length; i++) {
                if (!codeToSearch[i].equals(wrap.getPropertyValue(entityAttributeName[i]))) {
                    failedMatch = true;
                    break;
                }
            }
            if (!failedMatch)
                return wrap.getWrappedInstance();
        }
        return null;
    }

    public static void setEntitiesAtribute(Collection entityList,
                                           Object codeToSet, String entityAttributeName) {
        setEntitiesAtribute(entityList,
                new Object[]{codeToSet}, new String[]{entityAttributeName});

    }

    public static void setEntitiesAtribute(Collection entityList,
                                           Object[] codeToSet, String[] entityAttributeName) {

        // search for the entity
        // (considerar todas activas e inactivas)
        BeanWrapper wrap = new BeanWrapperImpl();
        for (Iterator iter = entityList.iterator(); iter.hasNext();) {
            Object bean = iter.next();

            wrap = new BeanWrapperImpl(bean);

            // test every atribute
            boolean failedMatch = false;
            for (int i = 0; i < codeToSet.length; i++) {
                wrap.setPropertyValue(entityAttributeName[i], codeToSet[i]);
            }
        }
    }


    public static Object process(Collection entityList, IteratorFunction function) {
        if (entityList == null)
            throw new IllegalArgumentException("Lista es nula");
        function.startIteration();
        int idx = 0;
        for (Iterator iter = entityList.iterator();
             !function.isIterationStopped() && iter.hasNext(); idx++) {

            function.setCurrentIndex(idx);
            function.execute(iter.next());
        }
        function.endIteration();
        return function.getResult();
    }

    /**
     * Remueve los entities que estan duplicados en la coleccion pasada como parametro
     * Para comparar si los entities estan repetidos se usara su metodo equals
     *
     * @param entityList
     * @return
     */
    public static List removeDuplicate(Collection entityList) {
        Set set = new HashSet(entityList);
        return Arrays.asList(set.toArray());
    }

}
