package com.aw.core.spring.init;

import com.aw.core.version.VersionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * User: Juan Carlos Vergara
 * Date: 11/02/2009
 * Time: 08:10:03 PM
 */
@Component
public class VersionManager implements ResourceLoaderAware {
    protected Log logger = LogFactory.getLog(getClass());
    protected ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        loadVersionInfoFromClasspath("classpath:version.properties", resourceLoader);
    }

    private void loadVersionInfoFromClasspath(String location, ResourceLoader resourceLoader) {
        //location = "classpath:" + location;
        if (logger.isDebugEnabled()) {
            logger.debug("Loading version number from: " + location);
        }
        java.util.Properties props = new java.util.Properties();
        Resource r = resourceLoader.getResource(location);
        try {
            props.load(r.getInputStream());
            String version = (String) props.get("version");
            logger.info("App version and build number: " + version);
            VersionInfo.instance().setVersion(version);
        } catch (Throwable e) {
            logger.warn("Error loading project version Info", e);
            e.printStackTrace();
            VersionInfo.instance().setVersion("");
        }
    }

    //Old version based on after properties set...
    public void afterPropertiesSet() throws Exception {
        initBuildInfo();
    }

    public void initBuildInfo() {
        String version = "<Undefined>";
        java.util.Properties props = new java.util.Properties();
        Resource r = new ClassPathResource("version.properties", version.getClass().getClassLoader());
        try {
            props.load(r.getInputStream());
            version = (String) props.get("version");
            logger.info("App version and build number: " + version);
            VersionInfo.instance().setVersion(version);
        } catch (Throwable e) {
            logger.warn("Error loading project version Info", e);
            e.printStackTrace();
            VersionInfo.instance().setVersion("");
        }

    }
}
