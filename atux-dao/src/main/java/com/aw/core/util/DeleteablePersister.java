package com.aw.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
* User: Server
* Date: 16/07/2009
* Time: 02:13:04 PM
* To change this template use File | Settings | File Templates.
*/
public class DeleteablePersister<E> {
    protected final Log logger = LogFactory.getLog(getClass());

    DeleteableList<E> deleteableList;

    public DeleteablePersister(DeleteableList<E> deleteableList) {
        this.deleteableList = deleteableList;
    }

    public DeleteableList<E> getList(){
        return deleteableList;
    }
    public void deleteAll(ItemPersister<E> itemPersister){
        doPersist(deleteableList.getInitialItems(), itemPersister);
    }
    public void delete(ItemPersister<E> itemPersister){
        doPersist(deleteableList.toDelete(true), itemPersister);
    }
    public void saveOrUpdate(ItemPersister<E> itemPersister){
        doPersist(deleteableList.toPersist(), itemPersister);
    }

    private void doPersist(Collection<E> collection , ItemPersister<E> itemPersister) {
        for (E entity : collection) {
            itemPersister.execute(collection, entity);
        }
        itemPersister.flush();
    }



    public interface ItemPersister<E>{
        void execute(Collection<E> collection, E entity);
        public void flush() ;
        public String name();
    }

}
