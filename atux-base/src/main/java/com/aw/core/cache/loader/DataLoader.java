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

import com.aw.core.domain.ICloneable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public abstract class DataLoader implements ICloneable{
    protected final Log logger = LogFactory.getLog(getClass());
    protected String loaderName = String.valueOf(hashCode());

    public abstract Object load();

    public Object clone() throws CloneNotSupportedException{
        return super.clone();        
    }


    public DataLoader setLoaderName(String loaderName) {
        this.loaderName = loaderName;
        return this;
    }

    /** Nombre usado para almacenar las filas de este data loader en cache
     * @param onlyRootName*/
    public String cacheName(boolean onlyRootName) {
        return toString();
    }
    @Override
    public String toString() {
        return "DL:"+loaderName;
    }

    public boolean isDataRelated(Object dataSourceReference) {
        return false;
    }
}
