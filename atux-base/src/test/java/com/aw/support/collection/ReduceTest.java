package com.aw.support.collection;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 25/10/2008
 * Time: 12:40:04 PM
 */
public class ReduceTest extends TestCase {
    public void testReduce() {
        List strings = new ArrayList();
        strings.add("a");
        strings.add("e");
        strings.add("b");
        strings.add("s");
        strings.add("d");
        String result = (String) Reduce.overCollectionWithInitialValue(strings, "").reduce(
                new ReduceExpression() {
                    public Object value(Object element, Object accumulator) {
                        return element.toString() + accumulator.toString();
                    }
                });
        assertEquals(result, "aebsd");
    }
}