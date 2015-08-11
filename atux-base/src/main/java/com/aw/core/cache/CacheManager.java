package com.aw.core.cache;

import com.aw.core.cache.loader.MetaLoader;

/**
 * User: gmc
 * Date: 25/02/2009
 */
public class CacheManager {
    private static Cache cache;
    private static CacheManager cacheManager = new CacheManager();

    public static CacheManager intance(){
        return cacheManager;
    }

    public void reload(String key){
        cache.reload(key);
    }
    public MetaLoader getMetaLoader(String key){
        return (MetaLoader) cache.get(key);
    }

    public static void setCache(Cache cache) {
        CacheManager.cache = cache;
    }

    public Object decorate(MetaLoader metaloader) {
        if (cache != null){
            return MetaLoaderCacheDecorator.decorate(cache, metaloader);            
        }
        return metaloader;  
    }
}
