package com.aw.swing.mvp.grid;

import com.aw.swing.mvp.grid.filter.CriteriaAttribute;
import com.aw.swing.mvp.grid.filter.CriteriaFactory;
import junit.framework.TestCase;
import org.apache.commons.functor.UnaryPredicate;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 26-nov-2007
 */
public class TestCriteria extends TestCase {


    public void testCreateCriteria(){
        CriteriaFactory criteriaFactory = new CriteriaFactory();
        UnaryPredicate predicate = criteriaFactory.createCriteria(getAttributes(), CriteriaFactory.AND);
        BeanForTest beanForTest = new BeanForTest("value1","value2","value3");
        assertTrue(predicate.test(new BeanWrapperImpl(beanForTest)));
        beanForTest = new BeanForTest("value1","value22","value3");
        assertFalse(predicate.test(new BeanWrapperImpl(beanForTest)));
    }

    public List getAttributes(){
        List attributes = new ArrayList();
        CriteriaAttribute attr = new CriteriaAttribute("criter1","value1");
        attributes.add(attr);
        attr = new CriteriaAttribute("criter2","value2");
        attributes.add(attr);
        attr = new CriteriaAttribute("criter3","value3");
        attributes.add(attr);
        return attributes;
    }

    public List getOperators() {
        List operators = new ArrayList();
        operators.add(CriteriaFactory.AND);
        operators.add(CriteriaFactory.OR);
        return operators;
    }
    public class BeanForTest{
        private String criter1;
        private String criter2;
        private String criter3;

        public BeanForTest(String criter1, String criter2, String criter3) {
            this.criter1 = criter1;
            this.criter2 = criter2;
            this.criter3 = criter3;
        }

        public String getCriter1() {
            return criter1;
        }

        public void setCriter1(String criter1) {
            this.criter1 = criter1;
        }

        public String getCriter2() {
            return criter2;
        }

        public void setCriter2(String criter2) {
            this.criter2 = criter2;
        }

        public String getCriter3() {
            return criter3;
        }

        public void setCriter3(String criter3) {
            this.criter3 = criter3;
        }
    }
}
