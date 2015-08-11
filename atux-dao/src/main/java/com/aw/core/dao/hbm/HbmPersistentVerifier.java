package com.aw.core.dao.hbm;

import com.aw.core.dao.DAOIntgr;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * User: Julio C. Macavilca
 * Date: 17/12/2009
 */
public class HbmPersistentVerifier {
    HbmMeta hbmMeta;
    private boolean aTransient;

    public HbmPersistentVerifier(HbmMeta hbmMeta) {
        this.hbmMeta = hbmMeta;
    }

    public boolean isTransient(Object entity, DAOIntgr daoIntgr) {
        Object pkValue = getPrimaryKeyValue(entity);
        if (pkValue==null)
            return Boolean.TRUE;
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from ").append(hbmMeta.getTableName()).append(" where ").append(hbmMeta.getPkColName()).append(" = ?");
        Number number = (Number) daoIntgr.getSql().findObjectGetColumn(sql.toString(), new Object[]{pkValue});
        return number.intValue()==0?Boolean.TRUE:Boolean.FALSE;
    }

    private Object getPrimaryKeyValue(Object entity) {
        BeanWrapper wrapper = new BeanWrapperImpl(entity);
        return wrapper.getPropertyValue(hbmMeta.getPkPropertyName());
    }
}