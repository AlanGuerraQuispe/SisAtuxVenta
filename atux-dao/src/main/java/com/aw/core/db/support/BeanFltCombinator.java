package com.aw.core.db.support;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * <br>
 * Funcionalidad : Usado para crear combinaciones de un bean de filtro<br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          01/05/2009 Creación      <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public abstract class BeanFltCombinator<BNFlt> {
    private Class<BNFlt> bnFltType;

    int testQueryCount=0;
    List<String> ignoreAttributeList = Collections.EMPTY_LIST;
    public BeanFltCombinator(Class<BNFlt>  bnFltType) {
        this.bnFltType = bnFltType;
    }
    public BeanFltCombinator ignoreAttributes(String ... ignoreAttributeList) {
        this.ignoreAttributeList = new ArrayList<String>(Arrays.asList(ignoreAttributeList) );
        return this;
    }

    public void ejecutarCombinacionesSimple() throws IllegalAccessException, InstantiationException {
        BNFlt bnFlt = bnFltType.newInstance();
        BeanWrapperImpl wrapper = new BeanWrapperImpl(bnFlt);
        PropertyDescriptor[] descriptors = wrapper.getPropertyDescriptors();

        for (PropertyDescriptor descriptor : descriptors) {
            if (descriptor.getWriteMethod()==null)
                continue;

            String propName =  descriptor.getName();

            if (ignoreAttributeList.contains(propName))
                    continue;

            // Probar con algun valor
            Object propertyValue = buildValue(descriptor.getPropertyType());
            wrapper.setPropertyValue(propName, propertyValue);
            executeQuery(++testQueryCount,(BNFlt) wrapper.getWrappedInstance());
            wrapper.setPropertyValue(propName, null);


        }
    }
    public void ejecutarCombinacionesFull() throws IllegalAccessException, InstantiationException {
        BNFlt bnFlt = bnFltType.newInstance();
        BeanWrapperImpl wrapper = new BeanWrapperImpl(bnFlt);
        PropertyDescriptor[] descriptors = wrapper.getPropertyDescriptors();

            ejecutarCombinacionesRecursivo(wrapper , descriptors, 0);
    }

    private void ejecutarCombinacionesRecursivo(BeanWrapperImpl wrapper,
                                                PropertyDescriptor[] descriptors, int propIdx) {
        if (propIdx>=descriptors.length)
            executeQuery(++testQueryCount,(BNFlt) wrapper.getWrappedInstance());
        else{
            while(descriptors[propIdx].getWriteMethod()==null)
                propIdx++;
            String propName =  descriptors[propIdx].getName();

            // Probar con vacio
            wrapper.setPropertyValue(propName, null);
            ejecutarCombinacionesRecursivo(wrapper , descriptors, propIdx+1);

            // Probar con algun valor
            Object propertyValue = buildValue(descriptors[propIdx].getPropertyType());
            wrapper.setPropertyValue(propName, propertyValue);
            ejecutarCombinacionesRecursivo(wrapper , descriptors, propIdx+1);
        }
    }

    private Object buildValue(Class<?> propertyType) {
        if (propertyType==String.class)
            return "ABC";
        else if (propertyType==Long.class)
            return new Long (123);
        else if (propertyType==Integer.class)
            return new Integer(123);
        else if (propertyType== Date.class || propertyType== java.sql.Date.class )
            return new Date();
        else
            throw new IllegalArgumentException("Type not supported code here");
    }


    protected abstract Object executeQuery(int testQueryCount, BNFlt bnFlt);
}
