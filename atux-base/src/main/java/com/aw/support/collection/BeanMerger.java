package com.aw.support.collection;

import com.aw.core.domain.AWBusinessException;
import com.aw.support.ObjectConverter;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
* User: Julio C. Macavilca
* Date: 05/06/2009
* Time: 05:08:26 PM
* To change this template use File | Settings | File Templates.
*/
public class BeanMerger<Output>{
    Output resultObject;
    Integer resultIndex;
    Class<Output>  resultType;
    /** Contiene  indice-->Atributo*/
    java.util.Map<Integer,String> attributesMap= new HashMap<Integer,String>();

    public List<Output> merge(List<Object[]> listOfArrays)  {
        List<Output> list = new ArrayList<Output>(listOfArrays.size());
        for (int i = 0; i < listOfArrays.size(); i++) {
            Object[] row = listOfArrays.get(i);
            Output bean =  merge(row);
            list.add(bean);
        }
        return list;
    }
    public Output merge(Object[] input) {
        Output bean = resultObject;
        if (bean==null && resultIndex!=null) bean = (Output) input[resultIndex];
        if (bean==null && resultType!=null) try {
            bean = resultType.newInstance();
        } catch (Exception e) {
            throw AWBusinessException.wrapUnhandledException(LogFactory.getLog(getClass()), e);
        }
        if (bean==null)
            throw new AWBusinessException("Merger output not configured");

        BeanWrapper wrap = new BeanWrapperImpl(bean);
        for (Iterator<Integer> indexIter = attributesMap.keySet().iterator(); indexIter.hasNext();) {
            Integer idx =  indexIter.next();
            String attributeName = attributesMap.get(idx);
            Class propertyType = wrap.getPropertyType(attributeName);
            Object attributeVal = ObjectConverter.convert( input[idx], propertyType) ;
            wrap.setPropertyValue(attributeName, attributeVal);
        }
        return bean;
    }


    public BeanMerger<Output> mapIdxAttribute(Integer index, String attributeName) {
        attributesMap.put(index,attributeName);
        return this;
    }

    public BeanMerger setResultObject(Output resultObject) {
        this.resultObject = resultObject;
        return this;
    }

    public BeanMerger setResultType(Class<Output> resultType) {
        this.resultType = resultType;
        return this;
    }

    public BeanMerger setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
        return this;
    }
}
