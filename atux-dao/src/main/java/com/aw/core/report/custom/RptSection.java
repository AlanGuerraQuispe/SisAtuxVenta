package com.aw.core.report.custom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public abstract class RptSection {
    protected final Log logger = LogFactory.getLog(getClass());

    public static int DOC_HEADER = 1;
    public static int PAGE_HEADER = 2;
    public static int RPT_DATA = 4;
    public static int PAGE_FOOTER = 8;
    public static int DOC_FOOTER  = 16;
    
    private String name;
    private int     type;


    public RptSection(String name) {
        this.name = name;
    }

    public RptSection(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
    public boolean isType(int type) {
        return (this.type & type) != 0 ;
    }

    public void setType(int type) {
        this.type = type;
    }

    public abstract int getLines();

    /**
     *
     * @param maxLines nro maximo de lineas a imprimir
     * @param renderer renderer usado para imprimir
     * @return TRUE si se imprimio toda la seccion
     *         FALSE si existen pendientes por imprimir
     * @throws Exception
     */
    public abstract boolean print(int maxLines, RptRenderer renderer) throws Exception;

    /**
     * Llamado antes de iniciar la impresion
     * @param renderer
     */
    public void initialize(RptRenderer renderer) throws Exception {
    }
    public String nvl(String str, String def) {
        return str!=null?str:def;
    }
}
