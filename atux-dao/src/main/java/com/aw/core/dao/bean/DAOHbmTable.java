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
package com.aw.core.dao.bean;

import java.lang.annotation.*;

/**
 * Soporte para mapeo de beans a DB (por Hibernate)
 *
 * @see com.aw.core.dao.DAOBean
 *      <p/>
 *      User: JCM
 *      Date: 15/10/2007
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DAOHbmTable {
    /**
     * Clase/Entity mapeada por Hibernate
     */
    Class value();
}
