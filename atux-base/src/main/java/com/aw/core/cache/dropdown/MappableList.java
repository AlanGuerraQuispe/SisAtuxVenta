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
package com.aw.core.cache.dropdown;

import com.aw.support.ObjectConverter;

import java.util.*;

/**
 * @author jcvergara
 *         01/09/2004
 */
public class MappableList<Row extends DropDownRow> extends ArrayList<Row> {
    private String name;
    protected Map map = null;

    public MappableList() {
        super();
        this.name = "NotSetYet";
    }

    public MappableList(String name, int size) {
        super(size);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized Map<Object, Row> buildMap() {
        if (map == null) {
            HashMap newMmap = new HashMap(this.size());
            for (int i = 0; i < this.size(); i++) {
                Object row = this.get(i);
                //add row, if it is not of DropDownRow type then throw exception
                if (row instanceof DropDownRow)
                    newMmap.put(((DropDownRow) row).getValue(), row);
                else
                    throw new IllegalArgumentException("Row cannot be mappable:" + row.getClass());
            }
            map = newMmap;
        }
        return map;
    }

    public Row mapGet(Object value) {
        Map map = buildMap();
        value = convertKey(value);
        if (!map.containsKey(value) ) {
            if (value!=null && !"".equals(value)  ) {
                throw new IllegalArgumentException("Entry does not exist. Value searched:<"
                        + value + "> on List:" + this.toString());
            }
            return null;
        }
        return (Row) map.get(value);
    }

    public Object convertKey(Object value) {
        if (this.size()==0 || value == null) return value;

        Object key = ((DropDownRow) this.get(0)).getValue();
        // todo jcm chequear luego esto
        if (key == null || ((key instanceof String)&& "".equals(key))  ){
            if (size()<2){
                return value;
            }
            key = ((DropDownRow) this.get(1)).getValue();

        }
        if (key==null)
            value = value;
        else
            value = ObjectConverter.convert(value, key.getClass());
        return value;
    }

    public boolean mapContains(Object value) {
        if (value!=null){
            value = convertKey(value);
        }
        return buildMap().containsKey(value);
    }


    public List labels() {
        List labels = new ArrayList();
        for (int j = 0; j < size(); j++) {
            DropDownRow dropDownRow = (DropDownRow) get(j);
            labels.add(dropDownRow.getLabel());
        }
        return labels;
    }

    public void clearMap() {
        map = null;
    }
    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	for (Iterator iterator = this.iterator(); iterator.hasNext();) {
			DropDownRow ddr = (DropDownRow) iterator.next();
			buf.append(ddr.getValue()).append("->").append(ddr.getLabel()).append("|");
		}
        return "MapList[" + name + "]:" + buf.toString();
    }
}
