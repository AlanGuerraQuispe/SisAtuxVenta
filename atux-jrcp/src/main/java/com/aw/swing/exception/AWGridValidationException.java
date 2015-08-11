package com.aw.swing.exception;

import com.aw.swing.mvp.ui.grid.Cell;

import javax.swing.*;

/**
 * Date: Sep 19, 2007
 */
public class AWGridValidationException extends AWSwingException {

    public static final int EXCEPTION_UNIQUE = 0;
    public static final int EXCEPTION_WITH_LIST_OBJECTS = 1;


    private String message = null;
    private Object[] args = null;
    private int typeException = -1;
    private Cell cell;


    protected AWGridValidationException(String message) {
        this.message = message;
        this.typeException = EXCEPTION_UNIQUE;
    }

    public AWGridValidationException(String message, JTable jComponent, Cell cell) {
        this(message);
        this.jComponent = jComponent;
        this.cell = cell;

    }

    public AWGridValidationException(String message, Object[] args, JTable jComponent, Cell cell) {
        this(message, jComponent, cell);
        this.args = args;
    }

    public AWGridValidationException(String message, Object[] args) {
        this(message);
        this.args = args;
    }


    public String getMessage() {
        return message;
    }

    public Object[] getArgs() {
        return args;
    }


    public int getTypeException() {
        return typeException;
    }

    public Cell getCell() {
        return cell;
    }
}