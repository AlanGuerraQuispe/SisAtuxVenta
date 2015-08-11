package com.aw.support.collection;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 12:40:04 PM
 */
public class FilterTest extends TestCase {
    List strings;

    public void setUp() {
        strings = new ArrayList();
        strings.add("a");
        strings.add("e");
        strings.add("B");
        strings.add("s");
        strings.add("B");
    }

    public void testSelect() {
        List newList = (List) Filter.overCollection(strings).select(
                new BooleanExpression() {
                    public boolean value(Object element) {
                        return ((String) element).charAt(0) == 'B';
                    }
                });
        assertTrue(newList.size() == 2);
        assertTrue(newList.get(0).equals("B"));
        assertTrue(newList.get(1).equals("B"));
    }

    public void testDetect() {
        String b = (String) Filter.overCollection(strings).detect(
                new BooleanExpression() {
                    public boolean value(Object element) {
                        return ((String) element).charAt(0) == 'B';
                    }
                });
        assertTrue(b.equals("B"));
    }

    public void testReject() {
        List newList = (List) Filter.overCollection(strings).reject(
                new BooleanExpression() {
                    public boolean value(Object element) {
                        return ((String) element).charAt(0) == 'B';
                    }
                });
        assertTrue(newList.size() == 3);
        assertTrue(newList.get(0).equals("a"));
        assertTrue(newList.get(1).equals("e"));
        assertTrue(newList.get(2).equals("s"));
    }
}