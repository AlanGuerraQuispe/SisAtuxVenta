package com.aw.core.domain;

import com.aw.core.dao.DAOHbm;
import com.aw.core.dao.DAOIntgr;
import com.aw.core.dao.DAOSql;
import com.aw.core.util.DeleteablePersister;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 11/09/2008
 * Time: 05:55:34 PM
 */
public class BaseRepository {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    protected DAOIntgr daoIntegrator;

    public DAOHbm getHbm(){
        return daoIntegrator.getHbm();
    }

    public DAOSql getSQL(){
        return daoIntegrator.getSql();
    }

    public <T> T guardar(T entity) {
        entity = getHbm().saveOrUpdate(entity);
        getHbm().flush();
        return entity;
    }

    public void guardar(Collection entity) {
        getHbm().saveOrUpdate(entity);
        getHbm().flush();
    }

    public void eliminar(List entities) {
        getHbm().deleteAll(entities);
        getHbm().flush();
    }

    public void eliminar(Object entity) {
        getHbm().delete(entity);
        getHbm().flush();
    }

    public class SaveOrUpdatePersister<E> implements DeleteablePersister.ItemPersister<E> {
        public void execute(Collection<E> es, E entity) {
            getHbm().saveOrUpdate(entity);
        }
        public void flush() {
            getHbm().flush();
        }

        public String name() {
            return "Saver";
        }
    }
    public class DeletePersister<E> implements DeleteablePersister.ItemPersister<E> {
        public void execute(Collection<E> es, E entity) {
            getHbm().delete(entity);
        }
        public void flush() {
            getHbm().flush();
        }

        public String name() {
            return "Deleter";
        }


    }

}
