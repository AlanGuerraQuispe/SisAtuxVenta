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
package com.aw.core.cache.loader;

import com.aw.core.business.AWContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public class Loader {
    protected final Log logger = LogFactory.getLog(getClass());

    public static Loader instance() {
        return (Loader) AWContext.getCtxBean("cacheLoader", null);
    }

    private Map<String, DataLoader> idRetrieverMap = new HashMap<String, DataLoader>();


    public boolean canLoad(String key) {
        return idRetrieverMap.containsKey(key);
    }

    public Object load(String key) {
        DataLoader dataLoader = idRetrieverMap.get(key);
        return dataLoader.load();
    }

    public Loader register(String key, DataLoader dataLoader) {
        idRetrieverMap.put(key, dataLoader);
        return this;
    }

    public void setRegisteredBeans(List instances) {
        List<MetaLoader> downMetaEntries = new ArrayList<MetaLoader>();
        for (Object bean : instances) {
            downMetaEntries.addAll(MetaLoader.find(bean));
        }
        for (MetaLoader metaLoader : downMetaEntries) {
            logger.debug("Registrando Loader " + metaLoader.id());
            register(metaLoader.id(), metaLoader.getDataLoader());
        }
    }

    public void registerDataLoaders(List<MetaLoader> metaloaders) {
        List<MetaLoader> downMetaEntries = metaloaders;
        for (MetaLoader metaLoader : downMetaEntries) {
            logger.debug("Registrando Loader " + metaLoader.id());
            register(metaLoader.id(), metaLoader.getDataLoader());
        }
    }
}
