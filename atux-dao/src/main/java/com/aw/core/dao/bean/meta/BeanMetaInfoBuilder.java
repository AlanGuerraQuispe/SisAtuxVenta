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
package com.aw.core.dao.bean.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class BeanMetaInfoBuilder {
    static BeanMetaInfoBuilder _instance = new BeanMetaInfoBuilder();

    public static BeanMetaInfoBuilder instance() {
        return _instance;
    }

    Map<Class, BeanMetaInfo> map = new HashMap<Class, BeanMetaInfo>();

    public BeanMetaInfo build(Class beanClass) {
        BeanMetaInfo metaInfo = map.get(beanClass);
        if (metaInfo == null) {
            metaInfo = new BeanMetaInfo(beanClass);
            map.put(beanClass, metaInfo);
        }
        return metaInfo;
    }

}
