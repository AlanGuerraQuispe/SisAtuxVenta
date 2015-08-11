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
package com.aw.core.cache.dropdown;

import com.aw.core.cache.loader.MetaLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CVL Value Provider
 * Class used to provide the values from specific CVLs based on key
 *
 * @author gmateo
 *         18/10/2004
 */
public class CVLValueProvider {
    protected final Log logger = LogFactory.getLog(getClass());
    protected MetaLoader metaLoader;
    private MappableList cvlName;

    public CVLValueProvider(MetaLoader metaLoader) {
        this.metaLoader = metaLoader;
    }

    /**
     * This must be implemented in order to provide the value for specific key
     * currently only will return the name of the cvl
     *
     * @param key
     * @return
     */
    public Object getCVLValue(Object key) {
        if (key == null && noExistNullKey()) {
            return "";
        }
        MappableList dropDownRows = getDropDownRows();

        DropDownRow dropDownRow = dropDownRows.mapGet(key);
        String label = "";
        if (dropDownRow != null) {
            label = (String) dropDownRows.mapGet(key).getLabel();
        } 
        return label;
    }

    private boolean noExistNullKey() {
        return !getDropDownRows().mapContains(null);
    }

    private MappableList getDropDownRows() {
        if (cvlName == null) {
            cvlName = (MappableList) metaLoader.getRows();
        }
        return cvlName;
    }

}
