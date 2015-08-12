package com.atux.config.dropdown;

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.loader.DataLoader;
import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.domain.model.Auditable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Kaiser
 * Date: 03-nov-2008
 */
public class MetaLoaderDDAppImpl<Row extends DropDownRow> extends MetaLoader<Row> {
    private List<Class<? extends Auditable>> auditableEntityClassList = null;

    public MetaLoaderDDAppImpl() {
        super("",null);
    }

    public MetaLoaderDDAppImpl(String entryId, DataLoader dataLoader) {
        super(entryId, dataLoader);
    }
    public MetaLoaderDDAppImpl(String entryId, DataLoader dataLoader, boolean cacheable) {
        super(entryId, dataLoader,cacheable);
    }

    public List<Class<? extends Auditable>> getAuditableEntityClassList() {
        return auditableEntityClassList;
    }

    public MetaLoaderDDAppImpl<Row> setAsAuditableEstadoResolver(Class<? extends Auditable> ... entityClassList) {
        if (auditableEntityClassList==null) auditableEntityClassList = new ArrayList<Class<? extends Auditable>>();
        Collections.addAll(auditableEntityClassList, entityClassList);
//        EstadoToLabelMapper codigoToLabelMapper = (EstadoToLabelMapper) ApplicationBase.instance().getBean("codigoToLabelMapper", null);
//        if (codigoToLabelMapper!=null) {
//            for (Class<? extends Auditable> aClass : entityClassList) {
//                codigoToLabelMapper.addMap(aClass, new EstadoToLabelMapper.CodigoToLabelResolver(){
//                    public String resolve(Object codigo) {
//                    }
//                });
//            }
//        }
        return this;
    }


}
