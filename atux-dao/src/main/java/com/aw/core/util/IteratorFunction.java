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

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcmacavilca
 *         10/01/2005
 */
public abstract class IteratorFunction {
    private boolean breakIterationAndReturn = false;
    protected int currentIndex;

    public void startIteration() {
    }

    abstract public void execute(Object currentInstance);

    public void endIteration() {
    }

    public Object getResult() {
        return null;
    }

    protected boolean isIterationStopped() {
        return breakIterationAndReturn;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    protected void stopIteration() {
        this.breakIterationAndReturn = true;
    }

    public static class Counter extends IteratorFunction {
        private int count = 0;
        private Object entityMatcher;

        public Counter setEntityMatcher(Object entityMatcher) {
            this.entityMatcher = entityMatcher;
            return this;
        }

        public void execute(Object currentInstance) {
            if (entityMatcher == null || entityMatcher.equals(currentInstance))
                count++;
        }

        public Object getResult() {
            return new Integer(count);
        }

    }

    public static class Appender extends IteratorFunction {
        StringBuffer buf = new StringBuffer();
        private String text;

        public Appender(String textBetweenInstances) {
            this.text = textBetweenInstances;
        }

        public void execute(Object currentInstance) {
            if (currentIndex > 0) buf.append(text);
            appendCurrentInstance(buf, currentInstance);
        }

        protected void appendCurrentInstance(StringBuffer buf, Object currentInstance) {
            buf.append(currentInstance);
        }

        public Object getResult() {
            return buf;
        }
    }

    abstract static class AttributeIteratorFunction extends IteratorFunction {
        int attributeIndex = -1;
        String attributeName = null;
        BeanWrapper wrap = null;

        AttributeIteratorFunction(String attributeName) {
            this.attributeName = attributeName;
        }

        AttributeIteratorFunction(int attributeIndex) {
            this.attributeIndex = attributeIndex;
        }

        protected Object getColumnValue(Object currentInstance) {
            if (attributeIndex >= 0) {
                return ((Object[]) currentInstance)[attributeIndex];
            } else {
                if (wrap == null) wrap = new BeanWrapperImpl();
                wrap = new BeanWrapperImpl(currentInstance);
                return wrap.getPropertyValue(attributeName);
            }
        }

    }

    public static class Summatory extends AttributeIteratorFunction {
        protected double total = 0;

        public Summatory(String atributeName) {
            super(atributeName);
        }

        public Summatory(int index) {
            super(index);
        }

        public void execute(Object currentInstance) {
            Object value = getColumnValue(currentInstance);
            if (value == null) {
                // nothing
            } else if (value instanceof Number) {
                total += ((Number) value).doubleValue();
            } else {
                throw new IllegalArgumentException("Class not support implement code here :" + currentInstance.getClass() + " to Number");
            }
        }


        public double getTotal() {
            return total;
        }

        public Object getResult() {
            return new Double(total);
        }
    }

    public static class Maximun extends AttributeIteratorFunction {
        protected Comparable currentMaximun;

        public Maximun(String atributeName) {
            super(atributeName);
        }

        public Maximun(int index) {
            super(index);
        }

        public void execute(Object currentInstance) {
            Object value = getColumnValue(currentInstance);
            if (value == null) {
                // nothing
            } else if (value instanceof Comparable) {
                if (currentMaximun == null || currentMaximun.compareTo(value) < 0)
                    currentMaximun = (Comparable) value;
            } else {
                throw new IllegalArgumentException("Class not support implement code here :" + currentInstance.getClass() + " to Number");
            }
        }


        public Object getResult() {
            return currentMaximun;
        }
    }

    public static class ColumnList extends AttributeIteratorFunction {
        protected List list = new ArrayList();

        public ColumnList(String atributeName) {
            super(atributeName);
        }

        public ColumnList(int index) {
            super(index);
        }

        public void execute(Object currentInstance) {
            Object value = getColumnValue(currentInstance);
            list.add(value);
        }


        public Object getResult() {
            return list;
        }
    }

    public static class ColumnToText extends AttributeIteratorFunction {
        protected StringBuffer buffer = new StringBuffer();
        protected Object appendAtEnd;
        protected Object appendAtStart;

        public ColumnToText(String atributeName, Object appendAtStart, Object appendAtEnd) {
            super(atributeName);
            this.appendAtStart = appendAtStart;
            this.appendAtEnd = appendAtEnd;
        }

        public ColumnToText(int index, Object appendAtStart, Object appendAtEnd) {
            super(index);
            this.appendAtStart = appendAtStart;
            this.appendAtEnd = appendAtEnd;
        }

        public void execute(Object currentInstance) {
            Object value = getColumnValue(currentInstance);
            if (appendAtStart != null) buffer.append(appendAtStart);
            buffer.append(value);
            if (appendAtEnd != null) buffer.append(appendAtEnd);
        }

        public Object getResult() {
            return buffer;
        }
    }
}


