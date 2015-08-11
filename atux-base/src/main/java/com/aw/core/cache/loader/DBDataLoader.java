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

import java.util.Arrays;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public abstract class DBDataLoader extends DataLoader {
    protected Object[] sqlParams;

    public String cacheName(boolean onlyRootName) {
        String name = super.cacheName(onlyRootName);
        if (!onlyRootName && sqlParams!=null)
            name +="#params:"+ Arrays.asList(sqlParams);
        return name;
    }

    public Object[] getSqlParams() {
        return sqlParams;
    }

    public Object reload(Object[] sqlParams) {
        this.setSqlParams(sqlParams);
        return load();
    }


    public void setSqlParams(Object[] sqlParams) {
        this.sqlParams = sqlParams;
    }

}