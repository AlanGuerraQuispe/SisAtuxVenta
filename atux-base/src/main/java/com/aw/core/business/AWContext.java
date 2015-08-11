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
package com.aw.core.business;


import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to store public global shared singleton objects.
 * This class is equivalent to the ApplicationContext on web applications
 *
 * @author jcvergara
 *         20/10/2004
 */
@Deprecated
public abstract class AWContext {
    protected static final Log logger = LogFactory.getLog(AWContext.class);

    protected static ApplicationContext appCtx;

    protected static String buildNumber;

    protected static boolean modoDistribuido;

    protected static void webInit(ApplicationContext appCtx) {

    }

    protected static ApplicationContext _init(String[] configFiles) {
        try {
            appCtx = new ClassPathXmlApplicationContext(configFiles);
        } catch (Throwable e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
        return appCtx;
    }

    private static BeanRepositoryRetriever beanRepositoryRetriever = new BeanRepositoryRetriever();

    public static Object getCtxBean(String beanName, Object defaultBean) {
        return beanRepositoryRetriever.getCtxBean(beanName, defaultBean);
    }

    public static BeanRepositoryRetriever getBeanRepositoryRetriever() {
        return beanRepositoryRetriever;
    }

//    public static void destroy() {
//        ((AbstractSessionFactoryBean) getCtxBean("sessionFactory", null)).destroy();
//    }

    public static ApplicationContext getAppCtx() {
        return appCtx;
    }

    public static boolean isModoDistribuido() {
        return modoDistribuido;
    }

    //    public static DropDownProvider getDropDownProvider() {
//        return null;
//    }

    public static AWRolAuthorizator getAuth() {
        return (AWRolAuthorizator) getCtxBean("rolAuthorizator", null);
    }


    protected static void _initLogging(String defaultFileName) {
        String log4File = System.getProperty("log4j.configfile");
        if (log4File == null) log4File = defaultFileName;
        try {
            Log4jConfigurer.initLogging("classpath:" + log4File);
        } catch (FileNotFoundException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }


    public static String getBuildNumber() {
        return buildNumber;
    }

    public static void initBuildInfo() {
        buildNumber = "<Undefined>";
        java.util.Properties props = new java.util.Properties();
//        java.net.URL url = ClassLoader.getSystemResource("version.properties");
        Resource r = new ClassPathResource("version.properties");
        try {
            props.load(r.getInputStream());
            buildNumber = (String) props.get("version");
        } catch (Throwable e) {
            logger.warn("Error loading project version Info", e);
            e.printStackTrace();
        }

    }

    public static void setAppCtx(ApplicationContext ctx) {
        if (appCtx == null) {
            appCtx = ctx;
        }
    }

    public static class BeanRepositoryRetriever {
        Map customBeanMap = new HashMap();

        public Object getCtxBean(String beanName, Object defaultBean) {
            Object bean = null;
            try {
                bean = customBeanMap.get(beanName);
                if (bean == null)
                    bean = appCtx.getBean(beanName);
            } catch (Throwable e) {
                bean = defaultBean;
            }
            return bean;
        }

        public Object registerCustomBean(String beanName, Object defaultBean) {
            customBeanMap.put(beanName, defaultBean);
            return this;
        }
    }
}


