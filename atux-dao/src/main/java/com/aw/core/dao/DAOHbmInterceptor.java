package com.aw.core.dao;

import java.util.List;

/**
 * User: JCM
 * Date: 26/11/2007
 */
public interface DAOHbmInterceptor {
    boolean onUpdateFields(Class entityClass, Object entity, List<String> fieldNames, List fieldValues, List<String> pkNames, List pkValues) ;
}
