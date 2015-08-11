package com.aw.support.collection;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 12:40:04 PM
 */
public class MapTest extends TestCase {
    public void testCollect() {
        List strings = new ArrayList();
        strings.add("a");
        strings.add("e");
        strings.add("b");
        strings.add("s");
        strings.add("d");

        List upperCaseLetters = (List) Map.overCollection(strings).collect(
            new Expression() {
                public Object value(Object element) {
                    return ((String) element).toUpperCase();
                }
        });
        
        assertTrue(upperCaseLetters.size() == 5);
        assertTrue(upperCaseLetters.get(0).equals("A"));
        assertTrue(upperCaseLetters.get(1).equals("E"));
        assertTrue(upperCaseLetters.get(2).equals("B"));
        assertTrue(upperCaseLetters.get(3).equals("S"));
        assertTrue(upperCaseLetters.get(4).equals("D"));
    }
}