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
package com.aw.core.cache;

import com.aw.core.cache.loader.Loader;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
public abstract class Cache {
    protected final Log logger = LogFactory.getLog(getClass());
    protected Loader loader;
    //public static String DEFAULT_NAME  = "cacheDropDown";


    public static Cache instance(String cacheInstance) {
        return (Cache) ApplicationBase.instance().getBean (cacheInstance, null);
    }

    /**
     * Almacena un objeto en cache. Si existe lo sobreescribe
     *
     * @param key      llave
     * @param instance objeto a almacenar
     * @return el mismo objeto almacenado
     */
    public abstract Object store(String key, Object instance);

    /**
     * Retorna un objeto de cache.
     *
     * @param key clave de busqueda
     * @return el objeto. Null si no existe
     */
    public abstract Object retrieve(String key);

    /**
     * Elimina un objeto e cache
     *
     * @param key clave de busqueda
     * @return true si el elemento fue removido
     */
    public abstract boolean remove(String key);
    /**
     * indica si un objeto existe en cache.
     *
     * @param key clave de busqueda
     * @return true si el objeto existe
     */
    public abstract boolean exist(String key);

    /**
     * Retorna un objeto de cache, si no existe trata de cargarlo
     *
     * @param key clave de busqueda
     * @return el objeto. Exception si no existe
     */
    public Object get(String key) {
        Object entry = null;
        if (exist(key)) {
            entry = retrieve(key);
        } else if (loader != null && loader.canLoad(key)) {
            entry = loader.load(key);  //load data
            store(key, entry);   //store on cache
        } else
            throw new CacheEntryNotFoundException("Cache " + key + " no existe");
        return entry;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void reload(String key) {
        Object entry = loader.load(key);  //load data
        store(key, entry);   //store on cache
    }

    public void removeAll(String rootKey) {
        List<String> allKeys = getAllKeys();
        for (String key : allKeys) {
            if (!key.startsWith(rootKey)) continue;
            logger.info("Eliminando de cache."+key);
            remove(key);
        }
    }
    public abstract List<String> getAllKeys();

    public static class CacheEntryNotFoundException extends AWBusinessException{
        public CacheEntryNotFoundException(String message) {
            super(message);
        }
    }
}
