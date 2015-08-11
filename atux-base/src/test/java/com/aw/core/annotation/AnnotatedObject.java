package com.aw.core.annotation;

/**
 * User: gmc
 * Date: 06/09/2009
 */
public class AnnotatedObject {
    @TestAnnotationUtils.AnnotationForTest
    protected String testField1;
    @TestAnnotationUtils.AnnotationForTest
    protected String testField2;
}
