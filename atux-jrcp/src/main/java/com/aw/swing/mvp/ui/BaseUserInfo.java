package com.aw.swing.mvp.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 23-nov-2007
 * Time: 19:05:37
 */
public class BaseUserInfo {
    protected List lines = new ArrayList();

    public Iterator getIterator(){
        return lines.iterator();
    }

    public String getLine(int index){
        return (String)lines.get(index);
    }

    public void addLine(String line){
        lines.add(line);
    }
}
