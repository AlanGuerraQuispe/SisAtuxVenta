package com.aw.support.collection;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.functor.UnaryFunction;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * User: Julio C. Macavilca
 * Date: 06/02/2008
 */
public abstract class PropertyUnaryFunction implements UnaryFunction {
    String propertyName;
    BeanWrapper wrap = new BeanWrapperImpl();
    Object result;
    boolean assumeNullValueOnError = false;

    public PropertyUnaryFunction(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object evaluate(Object bean) {
        Object propertyValue = null;
        if (bean instanceof java.util.Map){
            if (!((java.util.Map)bean).containsKey(propertyName) &&
                !assumeNullValueOnError)
                throw new AWBusinessException("Property "+propertyName+" not found in "+bean);
            propertyValue = ((java.util.Map)bean).get(propertyName);
        }else{
        wrap= new BeanWrapperImpl(bean);
            if (assumeNullValueOnError)
                propertyValue = wrap.isReadableProperty(propertyName)
                            ? wrap.getPropertyValue(propertyName)
                            :null;
            else
                propertyValue=wrap.getPropertyValue(propertyName);
        }

        Object rowResult = evaluate(bean,propertyValue);
        return rowResult;
    }
    abstract  protected Object evaluate(Object bean, Object propertyValue) ;

    public Object getResult() {
        return result;
    }

    public PropertyUnaryFunction setAssumeNullValueOnError(boolean assumeNullValueOnError) {
        this.assumeNullValueOnError = assumeNullValueOnError;
        return this;
    }
}