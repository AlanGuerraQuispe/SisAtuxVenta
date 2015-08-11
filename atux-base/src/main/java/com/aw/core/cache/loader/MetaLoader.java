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
package com.aw.core.cache.loader;

import com.aw.core.cache.Cache;
import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.core.cache.dropdown.MappableList;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.AWDeveloperException;
import com.aw.core.domain.ICloneable;
import com.aw.core.domain.model.EstadoToLabelMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public class MetaLoader<Row  extends DropDownRow> implements ICloneable, EstadoToLabelMapper.CodigoToLabelResolver {
    protected static final Log logger = LogFactory.getLog(MetaLoader.class);

    /** Por defecto muestra todos los datos*/
    protected boolean soloActivos = false;

    /** Indica si los registros son cacheables */
    protected boolean cacheable = true;
    /**
     * Entry id
     */
    private String entryId;

    /**
     * Object used to retrieve the data from DB, usually reused
     */
    protected DataLoader dataLoader;

    /** Usado si se requiere filtrar los datos originales */
    protected Filter filter;

    /** usado para mostrar una fila al inicio */
    protected Row topRow;

    /** Indica que cualquier modifciacion debe efectuarse sobre un clon de este objeto*/
    protected boolean inmutable = true;
    private static final String CACHE_NAME = "cacheDropDown";

    public MetaLoader(String entryId, DataLoader dataLoader,boolean cacheable) {
        this(entryId,dataLoader);
        this.cacheable = cacheable;
    }
    public MetaLoader(String entryId, DataLoader dataLoader) {
        this.entryId = entryId;
        this.dataLoader = dataLoader;
        setEntryId(entryId);
    }
    public MetaLoader clone() {
        try{
            MetaLoader metaLoader = (MetaLoader) super.clone();
            if (filter!=null)
                metaLoader.setFilter((Filter) filter.clone());
            if (dataLoader!=null)
                metaLoader.setDataLoader((DataLoader) dataLoader.clone());
            //metaLoader.setEntryId(this.id() + "#Tmp"+metaLoader.hashCode());
            //metaLoader.cacheable = false; // clones no deben ponerse en cache
            metaLoader.inmutable = false;
            return metaLoader;
        }catch (CloneNotSupportedException e){
            throw AWBusinessException.wrapUnhandledException(logger,e);
        }
    }
    public MetaLoader<Row> activeRows() {
        MetaLoader metaLoaderCloned = clone();
        metaLoaderCloned.soloActivos = true;
        return metaLoaderCloned;
    }

    public MetaLoader<Row> cfgRO(boolean isReadOnly) {
       if (isReadOnly)
            return this;
       else
           return activeRows();
    }

    public String resolve(Object codigo) {
        Row row = getMap().mapGet(codigo);
        if (row==null) throw new AWDeveloperException("No existe codigo:"+codigo);
        return row.getLabel()==null?"":row.getLabel().toString();
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
        // asumir el mismo Id para el loader
        if (this.dataLoader!=null)this.dataLoader.setLoaderName(entryId);
    }

    public String id() {
        return entryId;
    }


    public List<Row> getRows() {
        List<Row> rows = null;

        if (cacheable){
            // obtener usando cache
            String cacheNameV = dataLoader.cacheName(false);
            Cache cacheDropDown = Cache.instance(CACHE_NAME);
            if (!cacheDropDown.exist(cacheNameV))
                cacheDropDown.store(cacheNameV, load());

            rows = (List<Row>) cacheDropDown.get(cacheNameV);
        }else{
            // obtener directo desde dataLoader
            rows = load();
        }

        // filtrar
        rows = execFilter(filter, rows);

        MappableList<Row> finalResult = new MappableList<Row>(dataLoader.loaderName, rows.size()+1);
        if (this.topRow!=null) finalResult.add(topRow);
        finalResult.addAll(rows);
        // filtrar solo activos
        if (soloActivos){
            for (int i = finalResult.size()-1; i >=0 ; i--) {
                Row row =  finalResult.get(i);
                if (!row.isActive())
                    finalResult.remove(i);
            }
        }
        return finalResult;
    }

    protected List<Row> execFilter(Filter filter, List<Row> rows) {
        if (filter !=null)
            rows = filter.filter(rows);
        return rows;
    }

    private List<Row> load() {
        return (List<Row>) dataLoader.load();
    }

    public MappableList<Row> getMap() {
        List<Row> list = getRows();
        if (list instanceof MappableList)
            return (MappableList<Row>) list;
        else
            throw new AWBusinessException("DataLoader id:"+dataLoader +" no retorna MappableList. List:"+list.getClass());
    }

    public String getLabel(Object key) {
        MappableList map = getMap();
        if (key==null && !map.mapContains(null)) return "";
        if ("".equals(key) && !map.mapContains(key)) return "";
        DropDownRow row = map.mapGet(key);
        if (row == null){
            return "";
        }
        return (String) row.getLabel();
    }


    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public MetaLoader addRow(Object value, Object label) {
        MetaLoader metaLoader = this.clone();
        metaLoader.topRow = newRow(value, label);
        return metaLoader;

//        DataLoaderCompositeListImpl dataLoaderExtended = new DataLoaderCompositeListImpl()
//                    .addTop(new DropDownRowImpl(value, label))
//                    .add(this);
//        MetaLoader metaLoader = new MetaLoader(this.id() + "#Tmp", dataLoaderExtended);
//        metaLoader.setCacheable(false); // forzar que siempre use el custom loader
//        if (this.filter!=null)
//            metaLoader.setFilter(this.filter.clonar());
//
//        return metaLoader;
    }

    protected DropDownRow newRow(Object ... params) {
        DropDownRowImpl row = new DropDownRowImpl();
        row.setValue(params.length<=0?null:params[0]);
        row.setLabel(params.length<=1?null:params[1]);
        row.setInActive(params.length<=2?null:params[2]);
        return row;
    }

    public MetaLoader addSeleccioneNRow() {
        return addRow(null, "Seleccione");
    }
    /** @deprecated  usar {@link #addSeleccioneNRow()} */
    public MetaLoader addSeleccioneRow() {
        return addRow("", "Seleccione");
    }
    public MetaLoader addTodosNRow() {
        return addRow(null, "(Todos)");
    }
    /** @deprecated  usar {@link #addTodosNRow()} */
    public MetaLoader addTodosRow() {
        return addRow("", "(Todos)");
    }

    public boolean isTopRowSet(){
        return this.topRow !=null ;
    }
    public Row getTopRow(){
        return this.topRow;
    }


    /**
     * Busca en la instancia Atributos del tipo {@link com.aw.core.cache.loader.MetaLoader}
     */
    static public List<MetaLoader> find(Object instance) {
        List<MetaLoader> list = new ArrayList<MetaLoader>();

        Class cls = instance.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object value = field.get(instance);
                if (value instanceof MetaLoader)
                    list.add((MetaLoader) value);
            } catch (IllegalAccessException e) {
                throw AWBusinessException.wrapUnhandledException(logger, e);
            }
        }
        return list;
    }

    public MetaLoader<Row> setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
        return this;
    }

    public boolean isCacheable() {
        return cacheable;
    }
    public MetaLoader setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public void cleanCacheIfApply(Object dataSourceReference) {
        if (dataLoader.isDataRelated(dataSourceReference)){
            logger.info(dataSourceReference+" is related to "+dataLoader+" cleaning cache ");
            cleanCache() ;
        }
    }
    public void cleanCache() {
        String cacheNameRoot = dataLoader.cacheName(true);
        logger.info("Cleaning cache rootName:"+ cacheNameRoot+"....");
        Cache.instance(CACHE_NAME).removeAll(cacheNameRoot);
    }

    ////////////////////////////////////////////////////////

     public interface Filter<Row> extends ICloneable{
         public void setParams(Object... params);
         public List<Row> filter(List<Row> list);
     }
     public static class PropertyFilter<Row> implements Filter<Row>, Cloneable{
         public static enum PropertyNoExist{Enforce, Discard, Add }
         String propertyName;
         protected Object propertyValue;
         PropertyNoExist onNonExistentProperty = PropertyNoExist.Enforce;

         public PropertyFilter(String propertyName, Object propertyValue) {
             this.propertyName = propertyName;
             this.propertyValue = propertyValue;
         }

         public PropertyFilter<Row> setOnNonExistentProperty(PropertyNoExist onNonExistentProperty) {
             this.onNonExistentProperty = onNonExistentProperty;
             return this;
         }

         public void setParams(Object... params) {
         }


         public PropertyFilter<Row> clone() {
             try {
                 return (PropertyFilter<Row>) super.clone();
             } catch (CloneNotSupportedException e) {
                 throw AWBusinessException.wrapUnhandledException(logger, e);
             }
         }

         public List<Row> filter(List<Row> list) {
             List filtered = new MappableList();
             BeanWrapper wrap = new BeanWrapperImpl();
             for (Object bean : list) {
                 wrap=new BeanWrapperImpl(bean);
                 Boolean add = null;
                 if (!wrap.isReadableProperty(propertyName)){
                    if (onNonExistentProperty == PropertyNoExist.Discard)  add = Boolean.FALSE;
                    else if (onNonExistentProperty == PropertyNoExist.Add) add = Boolean.TRUE;
                 }

                 if (add==null){
                     Object currPropertyValue = wrap.getPropertyValue(propertyName);
                     add = (propertyValue == null && currPropertyValue == null) ||
                             (propertyValue != null && propertyValue.equals(currPropertyValue));
                 }
                 if (Boolean.TRUE.equals(add))
                     filtered.add(bean);
             }
             return filtered;
         }
     }


}
