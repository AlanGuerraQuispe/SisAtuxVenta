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
package com.aw.core.dao.meta;

import org.hibernate.SessionFactory;

/**
 * User: JCM
 * Date: 15/10/2007
 */
public class HbmUtilFactory {
    public static SessionFactory factory = null;

    public static HbmUtil newInstance() {
        HbmUtil hbmUtil = new HbmUtil(getFactory());
        return hbmUtil;
    }

    private static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        HbmUtilFactory.factory = factory;
    }
}
