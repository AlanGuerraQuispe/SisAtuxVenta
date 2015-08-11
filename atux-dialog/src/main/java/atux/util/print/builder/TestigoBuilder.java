package atux.util.print.builder;

import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import atux.util.print.AtuxPrint;
import javax.swing.*;
import java.sql.SQLException;

/**
 * 
 */
public abstract class TestigoBuilder {

    protected String fechaActual;
    
    protected JTable tblProductos;

    public abstract void printTiTle(AtuxPrint vPrint);

    public abstract void printHeader(AtuxPrint vPrint);

    public abstract void printColumnHeader(AtuxPrint vPrint);

    public abstract void printDetail(AtuxPrint vPrint);

    public abstract void printPageFooter(AtuxPrint vPrint);

    public abstract void printFooter(AtuxPrint vPrint);

    public String getFechaActual() {
        try {
            fechaActual = AtuxDBUtility.getFechaHoraBD(AtuxVariables.FORMATO_FECHA_HORA);
        } catch (SQLException sqlerr) {
            sqlerr.printStackTrace();
        }
        return fechaActual;
    }


    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }
}
