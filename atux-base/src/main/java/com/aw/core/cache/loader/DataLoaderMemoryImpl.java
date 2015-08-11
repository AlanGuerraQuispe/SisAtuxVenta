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

import com.aw.core.cache.dropdown.DropDownRow;
import com.aw.core.cache.dropdown.DropDownRowImpl;
import com.aw.core.cache.dropdown.MappableList;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Iterator;
import java.util.List;

/**
 * User: JCM
 * Date: 09/10/2007
 */
public class DataLoaderMemoryImpl extends DataLoader {
    private MappableList beans = new MappableList();


    public Object load() {
        beans.setName(loaderName);
        return beans;
    }

    public DataLoaderMemoryImpl addRow(DropDownRow row) {
        beans.add(row);
        return this;
    }

    public DataLoaderMemoryImpl addAllRows(List<? extends DropDownRow> dropDownRowList) {
        beans.addAll(dropDownRowList);
        return this;
    }

    public DataLoader addEnums(List enumList) {
        for (Object next : enumList) {
            BeanWrapper bw = new BeanWrapperImpl(next);
            beans.add(new DropDownRowImpl(bw.getPropertyValue("codigo"), bw.getPropertyValue("nombre")));
        }
        return this;
    }
}
