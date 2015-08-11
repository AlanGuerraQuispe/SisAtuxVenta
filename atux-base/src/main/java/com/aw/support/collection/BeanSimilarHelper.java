package com.aw.support.collection;

import com.aw.support.beans.BeanPropertiesComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
* User: Julio C. Macavilca
* Date: 05/06/2009
* Time: 05:08:26 PM
* To change this template use File | Settings | File Templates.
*/
public class BeanSimilarHelper<E> {
    protected final Log logger = LogFactory.getLog(getClass());

    List<E> list;

    public BeanSimilarHelper(List<E> list) {
        this.list = list;
    }

    public BeanGroups<E> group(String ... propertyNames) {
        BeanPropertiesComparator comparator = new BeanPropertiesComparator();
        for (int i = 0; i < propertyNames.length; i++) {
            comparator.eq( propertyNames[i] );
        }
        BeanGroups beanGroups = new BeanGroups(propertyNames);
        for (int i = 0; i < list.size(); i++) {
            E element = list.get(i);
            beanGroups.addElement(element, comparator);
        }
        beanGroups.print("Group");
        return beanGroups;
    }

    public void clearAfterFirst(String ... propertyNames) {
        group(propertyNames).clearAfterFirst();
    }

    public static class BeanGroups<E>{
        protected final Log logger = LogFactory.getLog(getClass());

        String[] propertyNames;
        List<List<E>> groups = new ArrayList<List<E>>();

        public BeanGroups(String[] propertyNames) {
            this.propertyNames =propertyNames;
        }

        public void addElement(E element, Comparator comparator) {
            List<E> groupMatch =null;
            for (int i = 0; i < groups.size(); i++) {
                List<E> es = groups.get(i);
                if (comparator.compare(es.get(0), element)==0){
                    groupMatch = es;
                    break;
                }
            }
            if (groupMatch==null){
                groupMatch = new ArrayList<E>();
                groups.add(groupMatch);
            }
            groupMatch.add(element);
        }

        public List<E> first(){
            return groups.size()==0? null: groups.get(0);
        }

        public int size() {
            return groups.size();
        }

        public BeanGroups<E> removeSingles() {
            for (int i = groups.size()-1; i >=0 ; i--) {
                List<E> list = groups.get(i);
                if (list.size()==1) groups.remove(i);
            }
            return this;
        }

        public BeanGroups<E> clearAfterFirst() {
            for (int i = groups.size()-1; i >=0 ; i--) {
                List<E> list = groups.get(i);
                for (int j = 1; j < list.size(); j++) { // start on 1
                    E element = list.get(j);
                    BeanWrapper wrapper = new BeanWrapperImpl(element) ;
                    for (String propertyName : propertyNames) {
                        wrapper.setPropertyValue(propertyName, null);
                    }
                }
            }
            print("CleanAfterFirst");
            return this;
        }

        public void print(String msg){
            if (first()==null || first().size()==0) return;
            PropertyDescriptor[] descriptors = new BeanWrapperImpl(first().get(0)).getPropertyDescriptors();
            List<String> tmp = new ArrayList<String>(0);
            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor descriptor = descriptors[i];
                if (descriptor.getReadMethod()!=null && descriptor.getWriteMethod()!=null)
                    tmp.add(descriptor.getName());
            }
            String[] propertyNameList = tmp.toArray(new String[tmp.size()]);


            StringBuffer str = new StringBuffer();
            str.append(msg). append("\n[");
            for (int i = 0; i < propertyNameList.length; i++) {
                String propertyName = propertyNameList[i];
                if (i>0) str.append("|");
                str.append(propertyName);
            }
            str.append("]\n");
            for (int i = 0; i < groups.size(); i++) {
                List<E> group = this.groups.get(i);
                str.append("GRP:"+i).append("\n");
                for (int j = 0; j < group.size(); j++) {
                    E element = group.get(j);
                    BeanWrapper wrapper = new BeanWrapperImpl(element) ;
                    str.append("  [");
                    for (int k = 0; k < propertyNameList.length; k++) {
                        String propertyName = propertyNameList[k];
                        if (k>0) str.append("|");
                        str.append(wrapper.getPropertyValue(propertyName));
                    }
                    str.append("]\n");
                }
            }
            System.out.println(str);
            logger.debug(str);

        }

        public List<E> removeDuplicates(List<E> mainList) {
            List copyList = new ArrayList(mainList);
            for (int i = 0; i < groups.size(); i++) {
                List<E> group = groups.get(i);
                List<E> listToRemove = group.subList(1, group.size() );
                logger.info("Removiendo:"+listToRemove);
                copyList.removeAll(listToRemove) ;
            }
            return copyList;
        }
    }
}