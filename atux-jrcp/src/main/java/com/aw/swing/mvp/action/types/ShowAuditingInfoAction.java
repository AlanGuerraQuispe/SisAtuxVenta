package com.aw.swing.mvp.action.types;

import com.aw.core.Identifiable;
import com.aw.core.view.ViewMode;
import com.aw.swing.mvp.action.RoundTransitionAction;
import com.aw.swing.mvp.navigation.Flow;

import java.util.HashMap;

//2.0.1import com.aw.core.db.DBExecuter;
//import com.aw.core.db.transaction.TransactionDecorator;

/**
 * User: gmc
 * Date: 05/06/2009
 */
public class ShowAuditingInfoAction extends RoundTransitionAction {
    private Class entityClass;

    public ShowAuditingInfoAction(Class entityClass) {
        targetMode = ViewMode.MODE_READONLY;
        this.entityClass = entityClass;
        allowMultiSelectedRows = false;
    }

    protected Object executeTransitionIntern(Flow flow) {
        Object selectedRow = getSelectedRow();
        Object id = getEntityId(selectedRow);
        Object objToBeShown = getObjectToBeShown(entityClass, id);
        flow.setTargetBackBeanAttribute(objToBeShown);
        flow.setAttribute(Flow.ROW_INDEX, getSelectedRowIdx());
        return new HashMap();
    }

    protected Object getObjectToBeShown(final Class entityClass, final Object id) {
//2.0.1
//        Object entity = TransactionDecorator.executeQuery(new DBExecuter() {
//            public Object execute() {
//                return daoIntgr.getHbm().getHSession().get(entityClass, (Serializable) id);
//            }
//        });
//        return entity;
        Object o = null;
        try {
            o = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return o;
    }

    protected Object getEntityId(Object selectedRow) {
        if (!(selectedRow instanceof Identifiable)) {
            throw new IllegalStateException("Class:" + selectedRow.getClass() + " must implement:Identifiable in order to use the action:" + this);
        }
        return ((Identifiable) selectedRow).getId();
    }
}
