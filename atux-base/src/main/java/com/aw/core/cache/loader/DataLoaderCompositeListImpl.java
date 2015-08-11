package com.aw.core.cache.loader;

import com.aw.core.cache.dropdown.MappableList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public class DataLoaderCompositeListImpl extends DataLoader {
    List topRows = null;
    MetaLoader metaLoader;
    List bottomRows = null;

    public DataLoaderCompositeListImpl() {
    }

    public DataLoaderCompositeListImpl add(MetaLoader metaLoader){
        this.metaLoader = metaLoader;
        return this;
    }
    public DataLoaderCompositeListImpl addTop(Object row){
        if (topRows==null) topRows = new ArrayList();
        topRows.add(row);
        return this;
    }
    public DataLoaderCompositeListImpl addBottom(Object row){
        if (bottomRows==null) bottomRows = new ArrayList();
        bottomRows.add(row);
        return this;
    }

    public MappableList load() {
        MappableList mappableList =  new MappableList();
        if (topRows!=null)    mappableList.addAll(topRows);
        if (metaLoader!=null) mappableList.addAll(metaLoader.getRows());
        if (bottomRows!=null) mappableList.addAll(bottomRows);
        return mappableList;
    }
}
