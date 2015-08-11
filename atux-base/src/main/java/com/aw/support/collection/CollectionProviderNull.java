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
package com.aw.support.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author GMC
 *         Jun 9, 2006
 */
public class CollectionProviderNull implements CollectionProvider {
    public Collection getCollection() {
        return new ArrayList();
    }
}
