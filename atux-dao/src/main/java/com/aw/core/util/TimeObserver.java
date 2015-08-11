package com.aw.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * User: matrix
 * Date: Apr 19, 2008
 */
public class TimeObserver {


    protected final Log logger = LogFactory.getLog(getClass());
    private String msg;
    private Date inicio;
    private Date fin;

    public TimeObserver(String msg) {
        this.msg = msg;
    }

    public void empezar() {
        inicio = new Date();
    }

    public void terminar() {
        fin = new Date();
        mostrarMsg();
    }

    private void mostrarMsg() {
        logger.info(msg + "(ms)" + (fin.getTime() - inicio.getTime()));
    }

}
