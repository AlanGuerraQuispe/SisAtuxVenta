package com.aw.swing.mvp.binding.component.support.table.edition;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.domain.AWDeveloperException;
import com.aw.swing.mvp.ui.UIColorConstants;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: gmc
 * Date: 18/05/2009
 */
public class JComponentCellRenderer extends DefaultTableCellRenderer {

    private Map<Integer, MetaLoader> metaLoaderMap = new HashMap();


    public JComponentCellRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel component = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!table.isCellEditable(row, column)) {
            if (isSelected) {
                setBackground(UIColorConstants.BKG_CELL_SELECTED_DISABLED);
            } else {
                setBackground(UIColorConstants.BKG_CELL_DISABLED);
            }
        }
        if (metaLoaderMap.containsKey(new Integer(row))) {
            MetaLoader metaLoader = metaLoaderMap.get(new Integer(row));
            String label = String.valueOf(value) ;
            try{
                label = metaLoader.getLabel(value);
            }catch (IllegalArgumentException e){
                LogFactory.getLog(getClass()).error("ERROR metaloader:"+e.toString());
                new AWDeveloperException("ERROR metaloader value:"+value, e);
            }
            component.setText(label);
        } else {
            if (value == null){
                value = "";
            }
            component.setText(String.valueOf(value));
        }
        return component;
    }


    public void putMetaLoader(int row, MetaLoader metaLoader) {
        metaLoaderMap.put(new Integer(row), metaLoader);
    }

    public void removeMetaLoader(int row) {
        metaLoaderMap.remove(new Integer(row));
    }

    public Map<Integer, MetaLoader> getMetaLoaderMap() {
        return metaLoaderMap;
    }
}