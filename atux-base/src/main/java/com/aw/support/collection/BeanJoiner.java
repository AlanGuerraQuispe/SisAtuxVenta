package com.aw.support.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
* User: Julio C. Macavilca
* Date: 05/06/2009
* Time: 05:08:26 PM
* To change this template use File | Settings | File Templates.
*/
public class BeanJoiner{
    String propertykeyName1;
    String propertykeyName2;
    boolean assumeNullValueOnError = false;
    boolean skipNullKeys = true;

    // output results
    private Map<Object, ArrayList> bean1Map;
    private Map<Object, ArrayList> bean2Map;

    public BeanJoiner(String propertykeyName1, String propertykeyName2) {
        this.propertykeyName1 = propertykeyName1;
        this.propertykeyName2 = propertykeyName2;
    }

    public BeanJoiner setAssumeNullValueOnError(boolean assumeNullValueOnError) {
        this.assumeNullValueOnError = assumeNullValueOnError;
        return this;
    }

    public <Bean1,Bean2> List<Bean2> execute(List<Bean1> bean1List, List<Bean2> bean2List,
                        OneToManyListener<Bean1,Bean2> oneToManyListener) {
        bean1Map = ListUtils.groupByColumn(bean1List, propertykeyName1, ArrayList.class, true);
        bean2Map = ListUtils.groupByColumn(bean2List, propertykeyName2, ArrayList.class, true);
        List<Bean2> unusedBean2List = new ArrayList(bean2List);
        for (Object key : bean1Map.keySet()) {
            if (key==null && skipNullKeys) continue; 
            Collection<Bean1> list1 = bean1Map.get(key);
            Collection<Bean2> list2 = bean2Map.get(key);
            if (list2!=null)
                unusedBean2List.removeAll(list2);
            if (list1==null) continue;
            for (Bean1 bean1 : list1) {
                oneToManyListener.onMatch(bean1, (List<Bean2>) list2);
            }
        }
        return unusedBean2List;
    }

    public Map<Object, ArrayList> getBean1Map() {
        return bean1Map;
    }

    public Map<Object, ArrayList> getBean2Map() {
        return bean2Map;
    }

    private interface Listener<Bean1, Bean2>{

    }
    public interface OneToManyListener<Bean1, Bean2> extends Listener<Bean1, Bean2>{
        public void onMatch(Bean1 bean1, List<Bean2> bean2List);
    }
}