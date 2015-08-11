package com.aw.core.validation;

import com.aw.core.validation.annotation.Email;
import com.aw.core.validation.annotation.Label;
import com.aw.core.validation.annotation.Url;
import com.aw.core.validation.annotation.Validation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;


/**
 * User: gmc
 * Date: 07-ago-2008
 */
public class PropertyValidationInfoFactory {

    private static final Log logger = LogFactory.getLog(PropertyValidationInfoFactory.class);

    public static PropertyValidationInfo createBasedOn(Method method, String attributeName) {
        logger.debug("Attribute <" + attributeName + ">");
        PropertyValidationInfo propertyValidationInfo = new PropertyValidationInfo(attributeName);
        if (method.isAnnotationPresent(Validation.class)) {
            Validation validation = method.getAnnotation(Validation.class);
            String validationPattern = validation.value();
            logger.debug("Validation Pattern = " + validationPattern);
            propertyValidationInfo.populate(validationPattern);
        }
        if (method.isAnnotationPresent(Label.class)) {
            Label validation = method.getAnnotation(Label.class);
            propertyValidationInfo.setLabel(validation.value());
        }
        if (method.isAnnotationPresent(Email.class)) {
            propertyValidationInfo.setEmail(true);
        }
        if (method.isAnnotationPresent(Url.class)) {
            propertyValidationInfo.setUrl(true);
        }
        return propertyValidationInfo;
    }
}

