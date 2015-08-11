package com.atux.comun.hibernate.interceptor;

import com.aw.core.dao.DAOIntgr;
import com.aw.core.dao.HbmFailMngr;
import com.aw.core.dao.hbm.HbmMeta;
import com.aw.core.dao.hbm.HbmPersistentVerifier;
import com.aw.core.domain.entity.UserInfo;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * User: gmc
 * Date: 15/12/2008
 */
public class HbmEntityInterceptor extends EmptyInterceptor {
    DAOIntgr daoIntgr;
    AuditoriaBeanSetter auditoriaBeanSetter = new AuditoriaBeanSetter();

    public HbmEntityInterceptor() {

    }

    public java.lang.Boolean isTransient(java.lang.Object entity) {
        Boolean isTransient = null; // por defecto dejar que Hibernate decida
        if (HbmFailMngr.isFailMode()){
            HbmMeta hbmMeta = HbmMeta.instance(entity, daoIntgr);
            if (hbmMeta!=null)
                isTransient = new HbmPersistentVerifier(hbmMeta).isTransient(entity, daoIntgr); 
        }
        return isTransient;
    }
    /**
     * Called before an object is saved. The interceptor may modify the <tt>state</tt>, which will be used for
     * the SQL <tt>INSERT</tt> and propagated to the persistent object.
     *
     * @return <tt>true</tt> if the user modified the <tt>state</tt> in any way.
     */
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException{
        return auditoriaBeanSetter.procesar(true, entity, id, state, null, propertyNames, types);
    }
    /**
     * Called when an object is detected to be dirty, during a flush. The interceptor may modify the detected
     * <tt>currentState</tt>, which will be propagated to both the database and the persistent object.
     * Note that not all flushes end in actual synchronization with the database, in which case the
     * new <tt>currentState</tt> will be propagated to the object, but not necessarily (immediately) to
     * the database. It is strongly recommended that the interceptor <b>not</b> modify the <tt>previousState</tt>.
     *
     * @return <tt>true</tt> if the user modified the <tt>currentState</tt> in any way.
     */
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException{
        return auditoriaBeanSetter.procesar(false,entity, id, currentState, previousState, propertyNames, types);
    }

    public void onDelete(Object entity,
                     Serializable id,
                     Object[] state,
                     String[] propertyNames,
                     Type[] types) {
        UserInfo userInfo = auditoriaBeanSetter.getUserInfo();
        daoIntgr.getSql().execSqlProcedure("call PK_HISTORIAL_UTIL.registrar_user_info(?,?)",
                new Object[]{userInfo.getUserName(), userInfo.getSessionIP()});
    }

    public void setDaoIntgr(DAOIntgr daoIntgr) {
        this.daoIntgr = daoIntgr;
    }
}