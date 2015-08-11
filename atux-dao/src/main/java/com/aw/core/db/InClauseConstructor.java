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
package com.aw.core.db;

import com.aw.support.beans.ValueProvider;

import java.util.Collection;
import java.util.Iterator;

/**
 * User: Kaiser
 * Date: Oct 21, 2007
 */
public class InClauseConstructor {

    /**
     * The elements of the collection must be of type InClauseSource.
     *
     * @param collection
     * @return A string that could be used in "IN" clause. Such as: "((a,b),(c,d))"
     */


    public static String getInClause(Collection collection) {
        if (collection == null || collection.size() == 0) return "";
        StringBuffer sb = new StringBuffer();
        sb.append("( ");
        for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            if (obj instanceof InClauseSource) {
                InClauseSource inClauseSource = (InClauseSource) obj;
                sb.append(inClauseSource.getAsInClause());                
            } else if (obj instanceof Object[]) {
                Object value = ((Object[]) obj)[0];
                sb.append(value);
            } else {
                sb.append(obj);
            }
            sb.append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }

    /**
     * The elements of the collection must be of type InClauseSource.
     *
     * @param collection
     * @return A string that could be used in "IN" clause. Such as: "((a,b),(c,d))"
     */
    public static String getInClause(Collection collection, ValueProvider valueProvider) {
        if (collection == null || collection.size() == 0) return "";
        StringBuffer sb = new StringBuffer();
        sb.append("( ");
        for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            sb.append(valueProvider.getValue(obj));
            sb.append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }


//    public static String getInClause(Collection collection,String fieldName) {
//        if (collection == null || collection.size() == 0) return "";
//        StringBuffer sb = new StringBuffer();
//        sb.append("( ");
//        for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
//            Object obj = iterator.next();
//            if (obj instanceof InClauseSource) {
//                InClauseSource inClauseSource = (InClauseSource) obj;
//                sb.append(inClauseSource.getAsInClause(fieldName));
//            } else if (obj instanceof Object[]) {
//                Object value = ((Object[]) obj)[0];
//                sb.append(value);
//            } else {
//                sb.append(obj);
//            }
//            sb.append(",");
//        }
//        sb.replace(sb.length() - 1, sb.length(), ")");
//        return sb.toString();
//    }

}
