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

import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWDeveloperException;
import com.aw.core.domain.ICloneable;
import com.aw.support.beans.BeanPropertiesComparator;
import com.aw.support.beans.BeanUtils;
import org.apache.commons.functor.UnaryFunction;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map;


/**
 * @author gmateo
 *         25/01/2005
 */
public class ListUtils {
    protected static Log logger = LogFactory.getLog(ListUtils.class);

//    /**
//     * Set the value to all elements of a specific column
//     *
//     * @param list      List of Object[]
//     * @param attribute Object contained bean's metadata info.
//     * @param value     Value that will be set to all elements of the column sent as parameter
//     */
//    public static void setValueInAllBeans(Collection list, Attribute attribute, Object value) {
//        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//            attribute.setWrappedInstance(iterator.next());
//            attribute.setValue(value);
//        }
//    }
//
//    /**
//     * Set the value to all elements of a specific column
//     *
//     * @param list   List of Object[]
//     * @param column Number of the column to which the data will be set. It begins in 0
//     * @param value  Value that will be set to all elements of the column sent as parameter
//     */
//    public static void setValueInAllColumn(List list, int column, Object value) {
//        for (int i = 0; i < list.size(); i++) {
//            Object[] element = (Object[]) list.get(i);
//            element[column] = value;
//        }
//    }
//
//
//    /**
//     * Set a sequential numbers in specific column
//     *
//     * @param list   List of Object[]
//     * @param column Number of the column to which the sequential numbers will be set. It begins in 0
//     */
//    public static void setSequentialInColumn(List list, int column) {
//        for (int i = 0; i < list.size(); i++) {
//            Object[] element = (Object[]) list.get(i);
//            element[column] = new Integer(i + 1);
//        }
//    }
//
//    /**
//     * Set a sequential numbers in specific value of the bean, which is in the list
//     *
//     * @param list
//     * @param attribute
//     */
//    public static int setSequentialInColumn(List list, Attribute attribute) {
//        int seq = 0;
//        for (int i = 0; i < list.size(); i++) {
//            Object element = list.get(i);
//            attribute.setWrappedInstance(element);
//            seq = i + 1;
//            attribute.setValue(Integer.toString(seq));
//        }
//        return seq;
//    }

    //

    /**
     * Set a sequential numbers in specific value of the bean, which is in the list
     *
     * @param list
     */
    public static int setSequentialInColumn(List list, String attributeName) {
        return setSequentialInColumn(list, attributeName, false, null);
    }

    public static int setSequentialInColumn(List list, String attributeName, boolean useLetters) {
        return setSequentialInColumn(list, attributeName, useLetters, null);
    }

    public static int setSequentialInColumn(List list, String attributeName, com.aw.core.format.Formatter formatter) {
        return setSequentialInColumn(list, attributeName, true, formatter);
    }

    /**
     * Set a sequential numbers in specific value of the bean, which is in the list
     *
     * @param list
     */
    private static int setSequentialInColumn(List list, String attributeName, boolean useLetters, com.aw.core.format.Formatter formatter) {
        int seq = 0;
        BeanWrapper beanWrapper = null;
        for (int i = 0; i < list.size(); i++) {
            Object element = list.get(i);
            seq = i + 1;
            if (beanWrapper == null) {
                beanWrapper = new BeanWrapperImpl(element);
            } else {
                beanWrapper= new BeanWrapperImpl(element);
            }
            Object value;
            if (formatter != null) {
                value = 1 + i;
                value = formatter.parseObject("" + value);
            } else if (useLetters) {
                char letter = (char) (65 + i);
                value = "" + letter;
            } else {
                value = 1 + i;
            }
            beanWrapper.setPropertyValue(attributeName, value);
        }
        return seq;
    }
//    /**
//     * Set a sequential numbers in specific value of the bean, which is in the list
//     *
//     * @param list
//     */
//    public static int getMaxInColumn(List list, String attributeName) {
//        int max = 0;
//        BeanWrapper beanWrapper = null;
//        for (int i = 0; i < list.size(); i++) {
//            Object element = list.get(i);
//            if (beanWrapper == null) {
//                beanWrapper = new BeanWrapperImpl(element);
//            } else {
//                beanWrapper.setWrappedInstance(element);
//            }
//            Number  num = (Number) beanWrapper.getPropertyValue(attributeName);
//            if (num!=null) max = Math.max(num.intValue(), max);
//        }
//        return max;
//    }
//
//    public static int getMinInColumn(List list, String attributeName) {
//        int min = 1;
//        BeanWrapper beanWrapper = null;
//        for (int i = 0; i < list.size(); i++) {
//            Object element = list.get(i);
//            if (beanWrapper == null) {
//                beanWrapper = new BeanWrapperImpl(element);
//            } else {
//                beanWrapper.setWrappedInstance(element);
//            }
//            Number  num = (Number) beanWrapper.getPropertyValue(attributeName);
//            if (num!=null) min = Math.min(num.intValue(), min);
//        }
//        return min;
//    }
//
//    /**
//     * Returns a list with the elements that meet the criteria
//     *
//     * @param list
//     * @param attribute
//     * @param value
//     * @param condition true: Agregar si valor del bean es igual al valor
//     *                  condition: false: Agregar si valor del bean no es igual al valor
//     * @return
//     */
//    public static List getSubList(Collection list, Attribute attribute, Object value, boolean condition) {
//        List collection = new ArrayList();
//        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//            Object current = iterator.next();
//            attribute.setWrappedInstance(current);
//            if (!(condition ^ value.equals(attribute.getValue()))) {
//                collection.add(current);
//            }
//        }
//        return collection;
//    }
//
//    /**
//     * Returns a list with the elements that meet the criteria
//     *
//     * @param list
//     * @param attribute
//     * @param value
//     * @return
//     */
//    public static List getSubList(Collection list, Attribute attribute, Object value) {
//        return getSubList(list, attribute, value, true);
//    }

    //

    /**
     * Returns a list with the elements that meet the criteria
     *
     * @param list
     * @return
     */
    public static <E> List<E> getSubList(Collection<E> list, UnaryPredicate predicate) {
        List subList = new ArrayList();
        for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
            Object o = iterator.next();
            if (predicate.test(o)) {
                subList.add(o);
            }
        }
        return subList;
    }
    public static <E> List<E> getSubList(Collection<E> list, final String propertyName, final Object propertyValue) {
        return getSubList(list, new UnaryPredicate(){
            @Override
            public boolean test(Object o) {
                BeanWrapper wrapper = new BeanWrapperImpl(o);
                return propertyValue.equals(wrapper.getPropertyValue(propertyName)) ;
            }
        }) ;
    }

//
    //    /**
//     * Returns a list with the elements that meet the criteria.
//     * The returned elements are the results of applying the function to the elements that meet the criteria
//     *
//     * @param list
//     * @return
//     */
//    public static List getSubList(Collection list, UnaryPredicate predicate, UnaryFunction function) {
//        List subList = new ArrayList();
//        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//            Object o = iterator.next();
//            if (predicate.test(o)) {
//                subList.add(function.evaluate(o));
//            }
//        }
//        return subList;
//    }
//    public static Map<Object, List> groupSplit(List list, String propertyName) {
//        Map<Object, List> groups = new HashMap();
//        BeanWrapper wrap = new BeanWrapperImpl();
//        for (Object bean: list) {
//            wrap.setWrappedInstance(bean);
//            Object columnGroup = wrap.getPropertyValue(propertyName);
//            List subList = groups.get(columnGroup);
//            if (subList==null){
//                subList = new ArrayList();
//                groups.put(columnGroup, subList);
//            }
//            subList.add(bean);
//        }
//
//        return groups;

    //    }

    public static <E, ListClass extends Collection<E>>
        Map<Object, ListClass> groupByColumn(
            Collection<E> list, String propertyName,
            Class<ListClass> collectionClass) {
        return groupByColumn(list, propertyName,collectionClass, false);
    }
    public static <E, ListClass extends Collection<E>> Map<Object, ListClass> groupByColumn(
            Collection<E> list, String propertyName,
            Class<ListClass> collectionClass, boolean assumeNullValueOnError) {
        if (list == null)
            throw new AWDeveloperException("Lista no puede ser nulo");
        try {
            Map<Object, ListClass> map = new HashMap<Object, ListClass>();
            BeanWrapper wrap = null;
            for (E item : list) {
                wrap = new BeanWrapperImpl(item);
                Object value = getPropertyValue( wrap,propertyName, assumeNullValueOnError);
                ListClass subList = map.get(value);
                if (subList == null) {
                    subList = collectionClass.newInstance();
                    map.put(value, subList);
                }
                subList.add(item);
            }
            return map;
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }

    }
    public static java.util.Map toMap(Collection list, String propertyNameKey, final String propertyNameValue) {
        return toMap(list, propertyNameKey, propertyNameValue, false);

    }

    public static java.util.Map toMap(Collection list,
                                      String propertyNameKey, final String propertyNameValue,
                                      final boolean assumeNullValueOnError) {
        final HashMap map = new HashMap();
        PropertyUnaryFunction unaryFunction = new PropertyUnaryFunction(propertyNameKey) {
            protected Object evaluate(Object bean, Object propertyValue) {
                Object value = bean;
                if (propertyNameValue != null ){
                    value = getPropertyValue(wrap, propertyNameValue, assumeNullValueOnError);
                }
                map.put(propertyValue, value);
                return null;
            }
        }.setAssumeNullValueOnError(assumeNullValueOnError);
        apply(list, null, unaryFunction);
        return map;
    }

    private static Object getPropertyValue(BeanWrapper wrap, String propertyNameValue, boolean assumeNullValueOnError) {
        Object value;
        if(assumeNullValueOnError )
            value = wrap.isReadableProperty(propertyNameValue)
                        ?wrap.getPropertyValue(propertyNameValue)
                        :null;
        else
            value = wrap.getPropertyValue(propertyNameValue);
        return value;
    }

    public static java.util.Map<Object, Object> toMap(Collection<Object[]> list,
         final int propertyKeyIdx, final int propertyValueIdx) {
        final HashMap<Object, Object> map = new HashMap();
        UnaryFunction unaryFunction = new UnaryFunction() {
            public Object evaluate(Object o) {
                Object[] row = (Object[]) o;

                Object key = row[propertyKeyIdx];
                Object value = propertyValueIdx < 0 ? row : row[propertyValueIdx];
                map.put(key, value);
                return null;
            }
        };
        apply(list, null, unaryFunction);
        return map;
    }

    //
    public static BigDecimal sum(Collection list, String propertyName, UnaryPredicate predicate) {
        SumUnaryFunction sumUnaryFunction = new SumUnaryFunction(propertyName);
        apply(list, predicate, sumUnaryFunction);
        return sumUnaryFunction.getTotal();
    }

    public static int count(Collection list, UnaryPredicate predicate) {
        CountUnaryFunction countUnaryFunction = new CountUnaryFunction();
        apply(list, predicate, countUnaryFunction );
        return countUnaryFunction.getCount();
    }

    //

    public static List columnAsList(Collection list, String propertyName, UnaryPredicate predicate) {
        final Collection subList = new ArrayList(list.size());
        return (List) columnAsList(list, propertyName, predicate, subList);
    }

    public static Set columnAsSet(Collection list, String propertyName, UnaryPredicate predicate) {
        final Collection subList = new HashSet(list.size());
        return (Set) columnAsList(list, propertyName, predicate, subList);
    }

    private static Collection columnAsList(Collection list, final String propertyName, UnaryPredicate predicate, final Collection subList) {
        PropertyUnaryFunction unaryFunction = new PropertyUnaryFunction(propertyName) {
            protected Object evaluate(Object bean, Object propertyValue) {
                subList.add(propertyValue);
                return null;
            }
        };
        apply(list, predicate, unaryFunction);
        return subList;
    }

    public static Collection columnSet(Collection list, String propertyName,
                                       final Object newPropertyValue, UnaryPredicate predicate) {
        PropertyUnaryFunction unaryFunction = new PropertyUnaryFunction(propertyName) {
            protected Object evaluate(Object bean, Object propertyValue) {
                wrap.setPropertyValue(propertyName, newPropertyValue);
                return null;
            }
        };
        apply(list, predicate, unaryFunction);
        return list;
    }
    public static Collection columnSetIfNull(Collection list, String propertyName,
                                       final Object newPropertyValue) {
        PropertyUnaryFunction unaryFunction = new PropertyUnaryFunction(propertyName) {
            protected Object evaluate(Object bean, Object propertyValue) {
                if (wrap.getPropertyValue(propertyName)==null)
                    wrap.setPropertyValue(propertyName, newPropertyValue);
                return null;
            }
        };
        apply(list, null, unaryFunction);
        return list;
    }

    public static <E> E columnFindFirst(Collection<E> list, String propertyName, final Object value) {
        List<E> matches = columnFindList(list, propertyName, value);
        return matches.size() == 0 ? null : matches.get(0);
    }

    public static <E> List<E> columnFindList(Collection<E> list, String propertyName, final Object value) {
        FindUnaryFunction findFunction = new FindUnaryFunction(propertyName, value);
        apply(list, null, findFunction);
        return findFunction.getList();
    }

    public static <E> E columnFindFirst(Collection<E> list, final String[] propertyNames, final Object[] propertyValues) {
        List<E> matches = columnFindList(list, propertyNames, propertyValues);
        return matches.size() == 0 ? null : matches.get(0);
    }
    public static <E> List<E> columnFindList(Collection<E> list, final String[] propertyNames, final Object[] propertyValues) {
        final List result = new ArrayList();
        apply(list, null, new UnaryFunction(){

            @Override
            public  Object evaluate(Object o) {
                BeanWrapper wrapper = new BeanWrapperImpl(o);
                for (int i = 0; i < propertyNames.length; i++) {
                    String propName = propertyNames[i];
                    Object propVal = wrapper.getPropertyValue(propName);
                    if (!BeanUtils.equals(propVal, propertyValues[i])) return null;
                }
                result.add(o);
                return null;
            }
        });
        return result;
    }
    public static <E> List<E> sort(List<E> list, final String propertyName) {
        return sort(list, new BeanPropertiesComparator().asc(propertyName));
    }
    public static <E> List<E> sortRev(List<E> list, final String propertyName) {
        return sort(list, new BeanPropertiesComparator().desc(propertyName));
    }

    public static <E> List<E> sort(List<E> list, Comparator comparator) {
        final BeanWrapperImpl wrap1 = new BeanWrapperImpl();
        final BeanWrapperImpl wrap2 = new BeanWrapperImpl();
        List<E> sortedList = new ArrayList<E>(list);
//        Comparator<E> comparator2 = new Comparator<E>() {
//            public int compare(E o1, E o2) {
//                wrap1.setWrappedInstance(o1);
//                wrap2.setWrappedInstance(o2);
//                Comparable value1 = (Comparable) wrap1.getPropertyValue(propertyName);
//                Comparable value2 = (Comparable) wrap2.getPropertyValue(propertyName);
//                int retVal;
//                if (value1 != null && value2 != null)
//                    retVal = value1.compareTo(value2);
//                else if (value1 != null)
//                    retVal = 1;
//                else if (value2 != null)
//                    retVal = -1;
//                else retVal = 0;
//                return reverse ? -1 * retVal : retVal; //ambos son null
//            }
//        };
        Collections.sort(sortedList, comparator);
        return sortedList;
    }
//    public static Map<Object, List> columnFindDuplicates(Collection list, String propertyName) {
//        FindDuplicatedUnaryFunction findFunction = new FindDuplicatedUnaryFunction(propertyName);
//        apply(list, null, findFunction);
//        return findFunction.getDups();
//    }
//    public static List columnFindDuplicates(Collection list, String propertyName, Object bean) {
//        Map<Object, List>  dupsMap = columnFindDuplicates(list, propertyName);
//        Object propertySearched = new BeanWrapperImpl(bean).getPropertyValue(propertyName);
//        List dups = dupsMap.get(propertySearched);
//        if (dups!=null)dups.remove(bean);
//        return dups;
//    }
//
//    public static Collection columnSetSeq(Collection list, String propertyName, final char letter, final int size) {
//        PropertyUnaryFunction unaryFunction = new PropertyUnaryFunction(propertyName) {
//            int i = 1;
//            protected Object evaluate(Object bean, Object propertyValue) {
//                if (size >0)
//                    propertyValue = FillerFormat.fill(String.valueOf(i), letter, size, FillerFormat.ALG_RIGHT);
//                else
//                    propertyValue = new Long(i);
//                propertyValue = ObjectConverter.convert(propertyValue, wrap.getPropertyType(propertyName));
//                wrap.setPropertyValue(propertyName, propertyValue );
//                i++;
//                return null;
//            }
//        };
//        apply(list, null, unaryFunction);
//        return list;
//    }
//    public static Collection columnSetSeq(Collection list, String propertyName) {
//        return columnSetSeq(list, propertyName, '0', -1);
//    }

    //

    public static void apply(Collection list, UnaryPredicate predicate, UnaryFunction function) {
        for (Object o : list) {
            if (predicate == null || predicate.test(o)) {
                function.evaluate(o);
            }
        }
    }
//
//    /**
//     * Returns the first "nuElements" of the list
//     *
//     * @param list
//     * @param nuElements
//     * @return
//     */
//    public static List getSubList(Collection list, int nuElements) {
//        List subList = new ArrayList();
//        Iterator iterator = list.iterator();
//        for (int i = 0; i < nuElements && iterator.hasNext(); i++) {
//            Object o = iterator.next();
//            subList.add(o);
//        }
//        return subList;
//    }
//
//    /**
//     * Set a sequential numbers in specific value of the bean, which is in the list
//     *
//     * @param list
//     * @param attribute
//     */
//    public static void setSequentialFormattedInColumn(List list, Attribute attribute, String charToFill,
//                                                      int maxLength) {
//        for (int i = 0; i < list.size(); i++) {
//            Object element = list.get(i);
//            attribute.setWrappedInstance(element);
//            String strValue = Integer.toString(i + 1);
//            while (strValue.length() < maxLength) {
//                strValue = charToFill + strValue;
//            }
//            attribute.setValue(strValue);
//        }
//    }
//
//    public static int indexOf(List list, ValueProvider valueProvider, Object valueToFound) {
//        for (int i = 0; i < list.size(); i++) {
//            Object obj = list.get(i);
//            Object value = valueProvider.getValue(obj);
//            if (value.equals(valueToFound)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    //

    /**
     * Return the index of the element in the list that satisfied the predicate
     *
     * @param list
     * @param predicate
     * @return -1 if no exist any element that satisfied the predicate
     */
    public static int indexOf(List list, UnaryPredicate predicate) {
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (predicate.test(obj)) {
                return i;
            }
        }
        return -1;
    }
//
//    /**
//     * Apply the procedure to all list members that pass the predicate test
//     *
//     * @param list
//     * @param predicate
//     * @param procedure
//     */
//    public static void apply(List list, UnaryPredicate predicate, UnaryProcedure procedure) {
//        for (int i = 0; i < list.size(); i++) {
//            Object o = list.get(i);
//            if ((predicate!=null&&predicate.test(o))||predicate==null) {
//                procedure.run(o);
//            }
//        }
//    }

    /**
     * Return a new list that contains the result to apply the unaryFunction to all the elements of the list
     *
     * @param list
     * @param unaryFunction
     * @return
     */
    public static List<Object> getList(Collection list, UnaryFunction unaryFunction) {
        List<Object> newList = new ArrayList<Object>();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            newList.add(unaryFunction.evaluate(obj));

        }
        return newList;
    }


    public static <E> List<E> obtenerMinimoMaximo(Collection<E> list, Comparator<E> comparator) {
        List<E> subList = new ArrayList<E>(list);
        Collections.sort(subList, comparator);

        if (subList.size() == 0) {
            return subList;
        }

        List<E> minMax = new ArrayList<E>();
        minMax.add(subList.get(0));
        minMax.add(subList.get(subList.size() - 1));
        return minMax;
    }
//
//    public static StringBuffer concatenarSepPorComa(Collection list, String propertyName) {
//        List colList = columnAsList(list, propertyName, null);
//        return concatenarSepPorComa(colList);
//    }

    //
    //

    public static StringBuffer concatenarSepPorComa(Collection cols) {
        return concatenarSepPorStr(cols, null, ",", null);
    }

    public static StringBuffer concatenarSepPorComaYApostrofe(Collection cols) {
        return concatenarSepPorStr(cols, null, ",", "'");
    }

    public static StringBuffer concatenarSepPorStr(Collection cols, String sep) {
        return concatenarSepPorStr(cols, null, sep, null);
    }

    public static StringBuffer concatenarSepPorStr(Collection cols, String propertyName, String sep, String enclosingStr) {
        StringBuffer colsStr = new StringBuffer();
        BeanWrapper wrap = new BeanWrapperImpl();
        for (Iterator iterator = cols.iterator(); iterator.hasNext();) {
            Object col = iterator.next();
            if (propertyName != null) {
                wrap = new BeanWrapperImpl(col);
                col = wrap.getPropertyValue(propertyName);
            }
            if (enclosingStr != null) colsStr.append(enclosingStr);
            colsStr.append(col);
            if (enclosingStr != null) colsStr.append(enclosingStr);
            if (iterator.hasNext()) colsStr.append(sep);
        }
        return colsStr;
    }

    //
    public static String[] toArray(List list) {
        String[] strs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String value = (String) list.get(i);
            strs[i] = value;
        }
        return strs;
    }

    public static List toList(Set list ) {
        List result = new ArrayList();
        result.addAll(list);
        return result;
    }

    public static List obtenerNuMaxRegistros(List list, int nuRegistros) {
        int i = 1;
        List result = new ArrayList();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object o = iterator.next();
            if (i > nuRegistros) {
                break;
            }
            result.add(o);
            i++;
        }
        return result;
    }
//
//    public static Component[] toArrayOfComponent(List list) {
//        Component[] components = new Component[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            Component value = (Component) list.get(i);
//            components[i] = value;
//        }
//        return components;
//    }
//
    public static Object[] merge(Object[] arg1, Object[] arg2) {
        Object[] arr = new Object[arg1.length + arg2.length];
        for (int x = 0; x < arg1.length; x++) {
            arr[x] = arg1[x];
        }
        for (int x = 0; x < arg2.length; x++) {
            arr[x + arg1.length] = arg2[x];
        }

        return arr;
    }
//
    public static List create(Object value, int size) {
        List list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(value);
        }
        return list;
    }
//
//    public static Object find(Collection list, Object searched, Comparator comparator) {
//        for (Object current : list) {
//            if (comparator.compare(searched, current)==0) return current;
//        }
//        return null;
//    }

    public static <E extends ICloneable> List<E> cloneList(List<E> rows) {
        if (rows == null) return null;
        List<E> lstCloned = new ArrayList<E>();
        try {
            for (E tm : rows) {
                ICloneable tmClone = (ICloneable) tm;
                tmClone = (ICloneable) tmClone.clone();
                lstCloned.add((E) tmClone);
            }
        } catch (Throwable e) {
            throw AWBusinessException.wrapUnhandledException(LogFactory.getLog(ListUtils.class), e);
        }

        return lstCloned;
    }

    public static void print(String titulo, Collection list) {
        StringBuffer buf = new StringBuffer();
        buf.append(titulo).append("\n");
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            buf.append("  - ").append(iterator.next()).append("\n");
        }
        logger.debug(buf);
    }

    public static Object columnMax(Collection list, String propertyName) {
        List colList = columnAsList(list, propertyName, null);
        Collections.sort(colList);
        if (colList.size()>0)   return colList.get(colList.size()-1);
        return null;
    }
    public static Object columnMin(Collection list, String propertyName) {
        List colList = columnAsList(list, propertyName, null);
        Collections.sort(colList);
        if (colList.size()>0)   return colList.get(0);
        return null;
    }

    public static int columnNextSeq(Collection list, String propertyName) {
        Object val =  columnMax(list, propertyName);
        return val==null? 1: ((Number)val).intValue()+1 ;
    }

//     public static DeleteableList convertToDeleteableList(List lista) {
//        DeleteableList deleteableList = new DeleteableList();
//        deleteableList.init(lista);
//        return deleteableList;
//    }
}
