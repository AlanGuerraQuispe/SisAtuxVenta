package com.aw.core.validation;

import com.aw.core.binding.DomainBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: gmc
 * Date: 17-sep-2008
 */
public class BasicValidationGenerator {

    /**
     * Generate the needed information for Basic validation
     * @param domain
     */
    public List<PropertyValidationInfo> generateBasicValidationsInfoBasedOn(Object domain) {
        List<PropertyValidationInfo> basicValidations = new ArrayList();
        DomainBuilder domainBuilder = new DomainBuilder(domain);
        List attributes = domainBuilder.getAllAttributes();
        for (Iterator iterator = attributes.iterator(); iterator.hasNext();) {
            String attrName = (String) iterator.next();
            Method method = domainBuilder.getMethodFor(attrName);
            if (method != null) {
                PropertyValidationInfo propertyValidationInfo = PropertyValidationInfoFactory.createBasedOn(method, attrName);
                basicValidations.add(propertyValidationInfo);
            }
        }
        return basicValidations;
    }

}
