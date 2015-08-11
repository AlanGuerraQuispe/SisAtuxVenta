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
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.stereotype.Repository;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007
 */
@Repository
public class CacheMemoryImpl extends Cache {
    Map cache = new HashMap();

    /**
     * Almacena un objeto en cache. Si existe lo sobreescribe
     *
     * @param key      llave
     * @param instance objeto a almacenar
     * @return el mismo objeto almacenado
     */
    public Object store(String key, Object instance) {
        cache.put(key, instance);
        return instance;
    }

    /**
     * Retorna un objeto de cache.
     *
     * @param key clave de busqueda
     * @return el objeto. Null si no existe
     */
    public Object retrieve(String key) {
        return cache.get(key);
    }

    public boolean remove(String key) {
        return cache.remove(key)!=null;
    }

    /**
     * indica si un objeto existe en cache.
     *
     * @param key clave de busqueda
     * @return true si el objeto existe
     */
    public boolean exist(String key) {
        return cache.containsKey(key);
    }

    public List<String> getAllKeys() {
        return new ArrayList(cache.keySet()); 
    }
}
