package com.aw.core.cache;

import com.aw.core.cache.dropdown.MappableList;
import com.aw.core.cache.loader.MetaLoader;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * User: gmc
 * Date: 16/03/2009
 */
public class MetaLoaderCacheDecorator {
    private static final String METALOADER_METHOD_TO_BE_OVERWRITTEN_GETROWS = "getRows";
    private static final String METALOADER_METHOD_TO_BE_OVERWRITTEN_GETMAP = "getMap";
    private static final String METALOADER_METHOD_TO_BE_OVERWRITTEN_RETRIEVECACHE = "retrieveCacheEntry";

    public static MetaLoader decorate(final Cache cache, final MetaLoader metaLoader){
        ProxyFactory p = new ProxyFactory(metaLoader);
        p.setProxyTargetClass(true);
        p.addAdvice(new MethodInterceptor() {
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                // TODO: Improve the use of cache and dropdowns
                if ((methodInvocation.getMethod().getName().equals(METALOADER_METHOD_TO_BE_OVERWRITTEN_GETROWS)) ||
                        (methodInvocation.getMethod().getName().equals(METALOADER_METHOD_TO_BE_OVERWRITTEN_GETMAP)) ||
                        (methodInvocation.getMethod().getName().equals(METALOADER_METHOD_TO_BE_OVERWRITTEN_RETRIEVECACHE))) {
                    return cache.get(metaLoader.id());
                }
                if (methodInvocation.getMethod().getName().equals("getLabel")) {
                    Object key = methodInvocation.getArguments()[0];
                    if (key == null){
                        return "";
                    }
                    return ((MappableList) cache.get(metaLoader.id())).mapGet(key).getLabel();
                }
                return methodInvocation.proceed();
            }
        });
        return (MetaLoader) p.getProxy();
    }
}
