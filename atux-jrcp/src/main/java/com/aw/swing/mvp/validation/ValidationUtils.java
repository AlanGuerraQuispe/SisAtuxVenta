package com.aw.swing.mvp.validation;

import com.aw.swing.exception.AWValidationException;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.grid.GridProvider;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.util.Date;

/**
 * User: Julio Gonzales
 * Date: 05-dic-2007
 */
public class ValidationUtils {
    public static Presenter pst;
    public static Object dmn;
    static BeanWrapper bwDmn;
    public Object vsr;


    public static BeanWrapper getBeanWrapper() {
        if (bwDmn == null || (!dmn.equals(bwDmn.getWrappedInstance()))) {
            bwDmn = new BeanWrapperImpl(dmn);
        }
        return bwDmn;
    }


    public static void validarAtributoRequerido(String labelComponente, String atributo, String componente) {
        boolean esVacio = getEsVacio(atributo);
        if (esVacio) {
            throw new AWValidationException("El comun " + labelComponente + " es obligatorio", (JComponent) pst.getIpView().getComponent(componente));
        }
    }

    public static void validarAtributoRequeridoSinFoco(String labelComponente, String atributo) {
        boolean esVacio = getEsVacio(atributo);
        if (esVacio) {
            throw new AWValidationException("El comun " + labelComponente + " es obligatorio");
        }
    }

    private static boolean getEsVacio(String atributo) {
        bwDmn = getBeanWrapper();

        String atribS;
        boolean esVacio = false;
        if (bwDmn.getPropertyValue(atributo) instanceof String) {
            atribS = (String) bwDmn.getPropertyValue(atributo);
            esVacio = !StringUtils.hasText(atribS);
        } else {
            esVacio = (bwDmn.getPropertyValue(atributo) == null);
        }
        return esVacio;
    }


    public static void validarRangoFechas(String feRangoIni, String feRangoFin, String componente) {
        boolean existeError = false;
        bwDmn = getBeanWrapper();
        if (!isEmpty(bwDmn.getPropertyValue(feRangoIni)) && !isEmpty(bwDmn.getPropertyValue(feRangoFin))) {
            Date fechaIni = (Date) bwDmn.getPropertyValue(feRangoIni);
            Date fechaFin = (Date) bwDmn.getPropertyValue(feRangoFin);
            if (fechaIni.getTime() > fechaFin.getTime())
                existeError = true;
        } else {
            if (isEmpty(bwDmn.getPropertyValue(feRangoIni)) && !isEmpty(bwDmn.getPropertyValue(feRangoFin))) {
                existeError = true;
            } else if (!isEmpty(bwDmn.getPropertyValue(feRangoIni)) && isEmpty(bwDmn.getPropertyValue(feRangoFin))) {
                existeError = true;
            }
        }
        if (existeError)
            throw new AWValidationException("Rango de Periodo Inválido.", (JComponent) pst.getIpView().getComponent(componente));

    }

    public static void validarRangoNumero(String paraInicial, String paraFinal, String message) {
        bwDmn = getBeanWrapper();

        if (!isEmpty(bwDmn.getPropertyValue(paraInicial)) && !isEmpty(bwDmn.getPropertyValue(paraFinal))) {
            Long numIni = new Long(bwDmn.getPropertyValue(paraInicial).toString());
            Long numFinal = new Long(bwDmn.getPropertyValue(paraFinal).toString());
            if (numIni.compareTo(numFinal) > 0)
                throw new AWValidationException(message);
        } else {
            if (isEmpty(bwDmn.getPropertyValue(paraInicial)) && !isEmpty(bwDmn.getPropertyValue(paraFinal))) {
                throw new AWValidationException(message);
            } else if (!isEmpty(bwDmn.getPropertyValue(paraInicial)) && isEmpty(bwDmn.getPropertyValue(paraFinal))) {
                throw new AWValidationException(message);
            }
        }
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().length() == 0;
    }

    public Presenter getPst() {
        return pst;
    }

    public void setPst(Presenter pst) {
        this.pst = pst;
    }

    public Object getDmn() {
        return dmn;
    }

    public void setDmn(Object dmn) {
        this.dmn = dmn;
    }


    
    public static void validarCantidadMinimaFilas(String gridTitle, GridProvider grid, int cantMinima) {
        if (grid==null||grid.getValues()==null||grid.getValues().size()<cantMinima)
            throw new AWValidationException(gridTitle+" debe tener como mínimo "+cantMinima+" registro(s)");
    }

}


