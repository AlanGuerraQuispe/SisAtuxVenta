package com.aw.core.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * User: gmc
 * Date: 23-sep-2008
 */
public class ApplicationContexUtils {
    protected static final Log logger = LogFactory.getLog(ApplicationContexUtils.class);

    public static Object getRegisteredBeanForType(ApplicationContext applicationContext, Class clazz){
        Map beans = applicationContext.getBeansOfType(clazz);
//jcv:2.0.1
//        if (beans == null || beans.size()==0){
//            beans = applicationContext.getParent().getBeansOfType(clazz);
//        }
        if (beans.size()==0){
            throw new IllegalStateException("There is not any Bean of:<"+clazz+">. Please check that this class be being managed by Spring");
        }
        if (beans.size()>1){
            logger.warn("There are:<"+beans.size()+"> beans of:<"+clazz+">. The first will be taken.");
        }
        return beans.values().toArray()[0];
    }
}
