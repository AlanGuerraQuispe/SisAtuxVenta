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
package com.aw.core.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Julio C. Macavilca
 * Date: 02/10/2007                                     
 */
public interface DAOExecuter {
    public Object execute(Connection connection) throws SQLException;
}
