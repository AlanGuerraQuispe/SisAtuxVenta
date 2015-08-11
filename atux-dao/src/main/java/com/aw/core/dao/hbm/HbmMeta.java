package com.aw.core.dao.hbm;

import com.aw.core.dao.DAOIntgr;
import com.aw.core.domain.AWDeveloperException;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.SingleTableEntityPersister;

/**
 * User: Julio C. Macavilca
 * Date: 17/12/2009
 */
public class HbmMeta {
    Class entityClass;
    String pkPropertyName;
    String tableName;
    String pkColName;

    public HbmMeta(Class entityClass, String pkPropertyName, String tableName,String pkColName) {
        this.entityClass = entityClass;
        this.pkColName = pkColName;
        this.pkPropertyName = pkPropertyName;
        this.tableName = tableName;
    }

    public static HbmMeta instance(Object entity, DAOIntgr daoIntgr){
        ClassMetadata classMetadata = daoIntgr.getHbm().getHSessionFactory().getClassMetadata(entity.getClass());
        if (!(classMetadata instanceof SingleTableEntityPersister)) {
            new AWDeveloperException("No se pudo extraer metadada para clase :"+entity.getClass()+". Metadata no soportada:"+classMetadata.getClass());
            return null;
        }
        SingleTableEntityPersister persister = (SingleTableEntityPersister) classMetadata;
        String tableName = persister.getTableName();
        if (persister.getIdentifierColumnNames().length!=1){
            new AWDeveloperException("No se pudo extraer metadada para clase :"+entity.getClass()+". Primary key compuesto no soportado");
            return null;
        }
        String pkColName = persister.getIdentifierColumnNames()[0];
        String pkPropertyName = persister.getIdentifierPropertyName();
        return new HbmMeta(entity.getClass(), pkPropertyName, tableName, pkColName);

    }

    public Class getEntityClass() {
        return entityClass;
    }

    public String getPkColName() {
        return pkColName;
    }

    public String getPkPropertyName() {
        return pkPropertyName;
    }

    public String getTableName() {
        return tableName;
    }
}
