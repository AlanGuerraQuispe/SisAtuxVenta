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
package com.aw.core.cache.storage;

import com.aw.core.cache.Cache;
import com.aw.core.domain.AWBusinessException;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.List;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public class CacheEHImpl extends Cache {
    protected final Log logger = LogFactory.getLog(getClass());

    private net.sf.ehcache.Cache myCache = null;
    private static String CACHE_REGION = "cache.general";
    private String name = CACHE_REGION;

    /**
     * Almacena un objeto en cache. Si existe lo sobreescribe
     *
     * @param key      llave
     * @param instance objeto a almacenar
     * @return el mismo objeto almacenado
     */
    public Object store(String key, Object instance) {
        try {
            Element element = new Element(key, (Serializable) instance);
            net.sf.ehcache.Cache cache = getCache();
            cache.put(element);
            logger.info("Cache put " + key+" size:"+cache.getSize()+
                    " memHits:"+cache.getStatistics().getInMemoryHits()+
                    " diskHits:"+cache.getStatistics().getOnDiskHits());
            return instance;
        } catch (CacheException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    /**
     * Retorna un objeto de cache.
     *
     * @param key clave de busqueda
     * @return el objeto. Null si no existe
     */
    public Object retrieve(String key) {
        try {
            Element element = getCache().get(key);
            if (element != null && !element.isExpired()) {
                if (logger.isDebugEnabled())
                    logger.debug("Cache " + key + " hit " + element.getHitCount());
                return element.getValue();
            } else {
                if (logger.isInfoEnabled())
                    logger.info("Cache hit " + key + " fail");
                return null;
            }
        } catch (CacheException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public boolean remove(String key) {
        if (logger.isInfoEnabled())
            logger.info("Cache removed " + key  );
        return getCache().remove(key);
    }

    /**
     * indica si un objeto existe en cache.
     *
     * @param key clave de busqueda
     * @return true si el objeto existe
     */
    public boolean exist(String key) {
        try {
            Element element = getCache().get(key);
            return element != null && !element.isExpired();
        } catch (CacheException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public List<String> getAllKeys() {
        return getCache().getKeys();
    }

    private net.sf.ehcache.Cache getCache() throws CacheException {
        if (myCache == null) {
            CacheManager manager = CacheManager.getInstance();
            //CacheManager.class.getClassLoader().getResource("ehcache")
            myCache = manager.getCache(name);
        }
        return myCache;
    }

    public void setName(String name) {
        this.name = name;
    }
}
