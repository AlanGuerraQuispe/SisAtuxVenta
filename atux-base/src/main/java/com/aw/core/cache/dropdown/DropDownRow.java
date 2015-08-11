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

import java.io.Serializable;

/**
 * @author Administrator
 *         20/07/2004
 */
public interface DropDownRow extends Serializable {
    Object getValue();

    Object getLabel();

    boolean isActive();

    String toString();
}
