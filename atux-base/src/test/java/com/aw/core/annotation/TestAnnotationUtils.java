package com.aw.core.annotation;

import junit.framework.TestCase;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.List;

/**
 * User: gmc
 * Date: 23-sep-2008
 */
public class TestAnnotationUtils extends TestCase {

    public void testGetAnnotatedFieldsFrom() {
        AnnotatedObject annotatedObject = new AnnotatedObject();
        List<Field> fields = AnnotationUtils.getAnnotatedFieldsFrom(annotatedObject, AnnotationForTest.class);
        assertEquals(2,fields.size());
        assertEquals("testField1",fields.get(0).getName());
        assertEquals("testField2",fields.get(1).getName());

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface AnnotationForTest {
    }
}
