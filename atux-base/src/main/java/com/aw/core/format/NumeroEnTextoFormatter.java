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
package com.aw.core.format;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;


/**
 * @author jcvergara
 *         15/11/2004
 */
public class NumeroEnTextoFormatter implements Formatter{
    protected static Log logger = LogFactory.getLog(NumeroEnTextoFormatter.class);


    public  static NumeroEnTextoFormatter instance = new NumeroEnTextoFormatter();


    public NumeroEnTextoFormatter() {
    }

    public Formatter clone() {
        try {
            return (Formatter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
    }

    public Object format(Object bean, String attributeName, Object attributeValue) {
        if (attributeValue == null) return null;
        return format(attributeValue);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        return null;
    }

    public Object format(Object attributeValue) {
        double d =((Number)attributeValue).doubleValue();
        String retval = valorEnLetras(d);
        logger.info(" valor:"+d+" -->"+retval);
        return  retval ;
    }


    public boolean onSortingPreferOriginalValue() {
        return true;
    }

    public Object parseObject(String source) {
        throw new UnsupportedOperationException();
    }

    public Object parse(String source) {
        throw new UnsupportedOperationException();
    }

    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    /**
    *Devuelve en letras un monto determinado.
    *@param valor: Objeto Double que será mostrada en letras.
    *@return String: Monto en Letras.
    */
    private static String valorEnLetras(Double valor) {

      String centavos = "00";
      double doubleValor  = valor;
      int numero   = valor.intValue();
      int posPunto = String.valueOf(valor).indexOf(".");
      int posComa  = String.valueOf(valor).indexOf(",");
      double doubleNumero = Double.parseDouble(String.valueOf(numero));
      if ( posPunto>0 || posComa>0 ) {
        if ( posPunto>0 ) centavos = String.valueOf(valor).substring(posPunto+1);
        if ( posComa>0 )  centavos = String.valueOf(valor).substring(posComa+1);
      } else
        centavos = "00";

      String cadena = "";
      int millon;
      int cienMil;

      if (numero < 1000000000) {

        if (numero > 999999) {
          millon = (new Double(numero/1000000)).intValue();
          numero = numero - millon*1000000;
          cadena += base(millon, true) + (millon>1?" MILLONES ":" MILLON ");
        }
        if (numero > 999) {
          cienMil = (new Double(numero/1000)).intValue();
          numero  = numero - cienMil*1000;
          cadena += base(cienMil, false) + " MIL ";
        }

        cadena += base(numero, true);

        if (cadena != null && cadena.trim().length() > 0) {
          cadena += " CON ";
        }

        if ( centavos.trim().length() == 1 ) centavos += "0";
        cadena += String.valueOf(centavos) + "/100";

      }

      return  cadena.trim();
      //return  cadena.trim()+" Nuevos Soles";
    }

    /**
    *Retorna en letras monto concatenado.
    *@param numero: Variable int que será procesada.
    *@param fin: Indica si existen o no, procesos pendientes.
    *@return String: Monto concatenado en letras.
    */
    public static String base(int numero, boolean fin) {

      String cadena = "";
      int decena;
      int centena;

      if (numero < 1000) {

          if (numero > 99) {
              centena = (new Double(numero/100)).intValue();
              numero  = numero - centena*100;
              if (centena == 1 && numero == 0) cadena += "CIEN ";
              else cadena += centenas(centena) + " ";
          }

          if (numero > 29) {
              decena = (new Double(numero/10)).intValue();
              numero = numero - decena*10;
              if (numero > 0) cadena += decenas(decena) + " Y " + unidad(numero, false) + " ";
              else cadena += decenas(decena) + " ";
           } else {
             cadena += unidad(numero, fin);
           }
      }

      return cadena.trim();

    }

    /**
    *Retorna en letras la cantidad de unidades de un número dado.
    *@param numero: Variable int que será procesada.
    *@param fin: Indica si existen o no, procesos pendientes.
    *@return String: Número de unidades en letras.
    */
    public static String unidad(int numero, boolean fin) {
      String[] aUnidades = {"UN","DOS","TRES","CUATRO","CINCO",
                            "SEIS","SIETE","OCHO","NUEVE","DIEZ",
                            "ONCE","DOCE","TRECE","CATORCE","QUINCE",
                            "DIECISEIS","DIECISIETE","DIECIOCHO","DIECINUEVE","VEINTE",
                            "VEINTIUNO","VEINTIDOS","VEINTITRES","VEINTICUATRO","VEINTICINCO",
                            "VEINTISEIS","VEINTISIETE","VEINTIOCHO","VEINTINUEVE"};
      String cadena = "";

      if (numero > 0) {
          if (numero == 1 && fin) cadena = "UNO";
          else cadena = aUnidades[numero-1];
      }

      return cadena.trim();
    }

    /**
    *Retorna en letras la cantidad de decenas de un número dado.
    *@param numero: Variable int que será procesada.
    *@return String: Número de decenas en letras.
    */
    public static String decenas(int numero) {

      String[] aDecenas = {"DIEZ","VEINTE","TREINTA","CUARENTA","CINCUENTA",
                           "SESENTA","SETENTA","OCHENTA","NOVENTA"};

      return (numero==0?"":aDecenas[numero-1]);

    }

    /**
    *Retorna en letras la cantidad de centenas de un número dado.
    *@param numero: Variable int que será procesada.
    *@return String: Número de centenas en letras.
    */
    public static String centenas(int numero) {

      String[] aCentenas = {"CIENTO","DOSCIENTOS","TRESCIENTOS","CUATROCIENTOS",
                            "QUINIENTOS","SEISCIENTOS","SETECIENTOS","OCHOCIENTOS","NOVECIENTOS"};

      return (numero==0?"":aCentenas[numero-1]);

    }

    public static void main(String a[]) throws ParseException {
        //System.out.println("VAlue "+ new NumberFormatter( "#,###,###,###,##0.00").parseObject("12,543.32"));
        new NumeroEnTextoFormatter().format(152.45);
    }

}