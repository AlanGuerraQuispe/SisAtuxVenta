/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.core.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public class AWPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    public static final String PROPERTY_NAME_CONF = "app.config.fileName";

    public void setSystemPropertyName(String systemPropertyName) {
        if (systemPropertyName == null || systemPropertyName.trim().length() == 0)
            systemPropertyName = PROPERTY_NAME_CONF;
        String propertyLocation = System.getProperty(systemPropertyName);
        logger.warn("Loading Property from:" + systemPropertyName + "=" + propertyLocation);
        System.out.println("Loading Property from:" + systemPropertyName + "=" + propertyLocation); //por si aun no se configura log4j
        this.setLocation(new ClassPathResource(propertyLocation));
    }

//    public void setLocation(Resource resource) {
//        super.setLocation(resource);
//    }

    public static void registerDefault(String defaultConfigFile) {
        if (System.getProperty(PROPERTY_NAME_CONF) == null) {
            System.out.println("Registrando config por defecto(app.config.fileName):" + defaultConfigFile);
            //logger.info("Loading Property from:"+systemPropertyName+"="+propertyLocation);
            System.setProperty(AWPropertyPlaceholderConfigurer.PROPERTY_NAME_CONF, defaultConfigFile);
        }
    }
}
