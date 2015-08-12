package com.atux.config.system;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * User: Juan Carlos Vergara
 * Date: 25/09/2009
 */
@Component
public class SystemInfoManager implements ApplicationContextAware {
    protected final Log logger = LogFactory.getLog(SystemInfoManager.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        showSysInfo();
    }

    private void showSysInfo() {
        showRuntimeInfo();
        showDataSourceInfo();
        showSystemInfo();

    }

    private void showSystemInfo() {
//        System.getProperties().list(System.out);
    }

    private void showRuntimeInfo() {
        long mb = 1000000;
        long maxMemory = Runtime.getRuntime().maxMemory() / mb;
        long freeMemory = Runtime.getRuntime().freeMemory() / mb;
        long totalMemory = Runtime.getRuntime().totalMemory() / mb;

        logger.info("___ Environment ___");
        logger.info("   Maximum Allowable Memory: " + maxMemory + "MB");
        logger.info("   Total Memory: " + totalMemory + "MB");
        logger.info("   Free Memory: " + freeMemory + "MB");
        logger.info("   Used Memory: " + (maxMemory - totalMemory) + "MB");
        logger.info("___ Environment ___");


    }

    private void showDataSourceInfo() {
        BasicDataSource ds = null;
        try {
            ds = (BasicDataSource) applicationContext.getBean("dataSource");
        } catch (NoSuchBeanDefinitionException ex) {
            logger.warn(" dataSource is not defined");
        }
        if (ds == null) return;

        int dsNumActive = ds.getNumActive();
        int dsMaxActive = ds.getMaxActive();
        int dsNumIdle = ds.getNumIdle();
        long dsMaxWait = ds.getMaxWait();
        String scheme = ds.getUsername();
        String url = ds.getUrl();
        String driver = ds.getDriverClassName();

        logger.info("___ Database Configuration ___");
        logger.info("   Connecting to: " + url);
        logger.info("   Scheme: " + scheme);
        logger.info("   Driver: " + driver);
        logger.info("   # Active Connections: " + dsNumActive);
        logger.info("   # Maximum Active Connections: " + dsMaxActive);
        logger.info("   # of Idle Connections: " + dsNumIdle);
        logger.info("   # Maximum Wait period before timeout: " + dsMaxWait);
        logger.info("___ Database Configuration ___");
    }
}
