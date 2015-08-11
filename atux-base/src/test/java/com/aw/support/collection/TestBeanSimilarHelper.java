package com.aw.support.collection;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
* User: Julio C. Macavilca
* Date: 05/06/2009
* Time: 05:08:26 PM
* To change this template use File | Settings | File Templates.
*/
public class TestBeanSimilarHelper extends TestCase {
    private List<Bean1> createList() {
        List<Bean1> list = new ArrayList<Bean1>();
        list.add(new Bean1("B1","abc", "ABC", (long)12));
        list.add(new Bean1("B2","abc", "ABX", (long)13));
        list.add(new Bean1("B3","abc", "ABC", (long)13));
        return list;
    }

    public void testCleanSimilar(){
        List<Bean1> list = createList();

        BeanSimilarHelper<Bean1> helper = new BeanSimilarHelper<Bean1>(list);
        BeanSimilarHelper.BeanGroups<Bean1> beanGroups = helper.group("atr1", "atr2");

        assertEquals(2,beanGroups.size());
        beanGroups = beanGroups.removeSingles();
        assertEquals(1,beanGroups.size());
        List<Bean1> group1 = beanGroups.first();
        assertEquals("B1",group1.get(0).getId());
        assertEquals("B3",group1.get(1).getId());


        assertEquals("abc",group1.get(0).getAtr1());
        assertEquals("abc",group1.get(1).getAtr1());
        beanGroups.clearAfterFirst();
        assertEquals("abc",group1.get(0).getAtr1());
        assertEquals(null,group1.get(1).getAtr1());

    }


    public void testRemoveSimilar(){
        List<Bean1> list = createList();

        BeanSimilarHelper<Bean1> helper = new BeanSimilarHelper<Bean1>(list);
        BeanSimilarHelper.BeanGroups<Bean1> beanGroups = helper.group("atr1", "atr2");

        list = beanGroups.removeDuplicates(list);
    }

    public static class Bean1{
        String id;
        String atr1;
        String atr2;
        Long atr3;

        public Bean1(String id, String atr1, String atr2, Long atr3) {
            this.id = id;
            this.atr1 = atr1;
            this.atr2 = atr2;
            this.atr3 = atr3;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAtr1() {
            return atr1;
        }

        public void setAtr1(String atr1) {
            this.atr1 = atr1;
        }

        public String getAtr2() {
            return atr2;
        }

        public void setAtr2(String atr2) {
            this.atr2 = atr2;
        }

        public Long getAtr3() {
            return atr3;
        }

        public void setAtr3(Long atr3) {
            this.atr3 = atr3;
        }
    }
}