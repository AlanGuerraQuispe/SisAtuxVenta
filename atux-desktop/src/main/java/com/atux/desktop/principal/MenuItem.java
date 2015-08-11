package com.atux.desktop.principal;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Juan Carlos Vergara
 * Date: 11/12/2009
 */
public abstract class MenuItem implements ActionListener {
    protected List items = new LinkedList();

    protected String name;
    protected String descripcion = "";
    protected String imagen;

    public void add(MenuItem menuItem) {
        items.add(menuItem);
    }

    public List items() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "items=" + items.size() +
                ", name='" + name + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
