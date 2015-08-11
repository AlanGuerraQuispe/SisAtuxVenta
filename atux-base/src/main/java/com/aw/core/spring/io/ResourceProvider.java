package com.aw.core.spring.io;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * User: gmc
 * Date: 27-sep-2008
 */
public class ResourceProvider {
    private static ResourceLoader resourceLoader;

    public static Resource getResource(String path) {
        return resourceLoader.getResource(path);
    }
    public static void setResourceLoader(ResourceLoader resourceLoader) {
        ResourceProvider.resourceLoader = resourceLoader;
    }
}
