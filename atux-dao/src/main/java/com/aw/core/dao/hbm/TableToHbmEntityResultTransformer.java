package com.aw.core.dao.hbm;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;
import java.util.List;

/**
 * Esta clase debio heredar de AliasToBeanResultTransformer, y solo modificar
 * AliasToBeanResultTransformer#propertyAccessor, pero este atributo es private
 * 
 * User: Julio C. Macavilca
 * Date: 08/01/2008
 *
 */
public class TableToHbmEntityResultTransformer implements ResultTransformer, Serializable {
    private final Class resultClass;
    private Setter[] setters;
    transient private PropertyAccessor propertyAccessor=null;

    public TableToHbmEntityResultTransformer(Class resultClass) {
        if(resultClass==null) throw new IllegalArgumentException("resultClass cannot be null");
        this.resultClass = resultClass;
        initPropertyAccessor();
    }

    private PropertyAccessor initPropertyAccessor() {
        if (propertyAccessor ==null)
            propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {new TableToEntityPropertyAccessor(), PropertyAccessorFactory.getPropertyAccessor(resultClass,null), PropertyAccessorFactory.getPropertyAccessor("field")});
        return propertyAccessor ;
    }

    /** Same as {@link AliasToBeanResultTransformer#transformTuple(Object[], String[])}  */
    public Object transformTuple(Object[] tuple, String[] aliases) {
        initPropertyAccessor();

        Object result;

        try {
            if(setters==null) {
                setters = new Setter[aliases.length];
                for (int i = 0; i < aliases.length; i++) {
                    String alias = aliases[i];
                    if(alias != null) {
                        setters[i] = propertyAccessor.getSetter(resultClass, alias);
                    }
                }
            }
            result = resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if(setters[i]!=null) {
                    setters[i].set(result, tuple[i], null);
                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }

        return result;
    }

    /** Same as {@link AliasToBeanResultTransformer#transformList(java.util.List)}  */
    public List transformList(List collection) {
        return collection;
    }



}
