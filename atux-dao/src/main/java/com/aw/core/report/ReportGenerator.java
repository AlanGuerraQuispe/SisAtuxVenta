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
package com.aw.core.report;

/**
 * User: Manuel Flores
 * Date: 23/10/2007
 */
public interface ReportGenerator {
    /**
     * @param dataAccesor Optional object that provide access to the data source
     *                    Commonly it is the JDBC DataSource or Connection
     * @return
     */
    public Object execute(Object dataAccesor);
}
