package atux.util.print;

import atux.util.common.AtuxUtility;

  public class AtuxPRNUtility {
  
  public static final char[] blanco = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
  
  public AtuxPRNUtility() {
  }

  /**
  *Alinea la variable tipo int hacia la derecha colocando espacios en blanco a
  *la izquierda según la longitud que se establezca.
  *@param <b>parmint</b> Variable int que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la derecha.
  */
  public static String alinearDerecha(int parmint, int parmLen) {
    return alinearDerecha(String.valueOf(parmint),parmLen);
  }

  /**
  *Alinea la variable tipo long hacia la derecha colocando espacios en blanco a
  *la izquierda según la longitud que se establezca.
  *@param <b>parmlong</b> Variable long que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación. @return <b>String</b> String alineado a la derecha.
  */
  public static String alinearDerecha(long parmlong, int parmLen) {
    return alinearDerecha(String.valueOf(parmlong),parmLen);
  }

  /**
  *Alinea la variable tipo double hacia la derecha colocando espacios en blanco a
  *la izquierda según la longitud que se establezca.
  *@param <b>parmdouble</b> Variable double que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la derecha.
  */
  public static String alinearDerecha(double parmdouble, int parmLen) {
    return alinearDerecha(String.valueOf(parmdouble),parmLen); }

  /**
  *Alinea el texto hacia la derecha colocando espacios en blanco a la
  *izquierda según la longitud que se establezca.
  *@param <b>parmString</b> Cadena String que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la derecha.
  */
  public static String alinearDerecha(String parmString, int parmLen) {

     String       tempString;
     int          addLen;
     StringBuffer sb = new StringBuffer(parmLen);

     if (parmString.length() > parmLen)
        tempString = parmString.substring(parmString.length()-parmLen,
                                          parmString.length());
     else {

        addLen = parmLen - parmString.length();
        while (addLen > 70) {
          sb = sb.append(blanco,0,70);
          addLen -= 70;
        }
        tempString = sb.append(blanco,0,addLen).append(parmString).toString();

     }

     return tempString;

  }

  /**
  *Alinea la variable tipo int hacia la izquierda colocando espacios en blanco a
  *la derecha según la longitud que se establezca.
  *@param <b>parmint</b> Variable int que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la izquierda.
  */
  public static String alinearIzquierda(int parmint, int parmLen) {
    return alinearIzquierda(String.valueOf(parmint),parmLen);
  }

  /**
  *Alinea la variable tipo long hacia la izquierda colocando espacios en blanco a
  *la derecha según la longitud que se establezca.
  *@param <b>parmlong</b> Variable long que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la izquierda.
  */
  public static String alinearIzquierda(long parmlong, int parmLen) {
    return alinearIzquierda(String.valueOf(parmlong),parmLen);
  }

  /**
  *Alinea la variable tipo double hacia la izquierda colocando espacios en blanco a
  *la derecha según la longitud que se establezca.
  *@param <b>parmint</b> Variable double que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la izquierda.
  */
  public static String alinearIzquierda(double parmdouble, int parmLen) {
    return alinearIzquierda(String.valueOf(parmdouble),parmLen);
  }

  /**
  *Alinea el texto hacia la izquierda colocando espacios en blanco a la
  *derecha según la longitud que se establezca.
  *@param <b>parmString</b> Cadena String que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@return <b>String</b> String alineado a la izquierda.
  */
  public static String alinearIzquierda(String parmString, int parmLen) {

     String       tempString;
     int          addLen;
     StringBuffer sb = new StringBuffer(parmLen);

     if (parmString.length() > parmLen)
        tempString = parmString.substring(0,parmLen);
     else {
        addLen = parmLen - parmString.length();
        sb = sb.append(parmString);
        while (addLen > 70) {
          sb = sb.append(blanco,0,70);
          addLen -= 70;
        }
        tempString = sb.append(blanco,0,addLen).toString();
     }

     return tempString;

  }

  /**
  *Alinea la variable int hacia la derecha colocando CARACTERES a la izquierda
  *según la longitud que se establezca y el CARACTER dado.
  *@param <b>parmString</b> Variable int que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@param <b>parmCaracter</b> String de relleno para la alineación.
  *@return <b>String</b> String alineado a la derecha con el CARACTER.
  */
  public static String caracterIzquierda(int parmint, int parmLen, String parmCaracter) {
    return caracterIzquierda(String.valueOf(parmint),parmLen,parmCaracter);
  }

  /**
  *Alinea la variable long hacia la derecha colocando CARACTERES a la izquierda
  *según la longitud que se establezca y el CARACTER dado.
  *@param <b>parmString</b> Variable long que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@param <b>parmCaracter</b> String de relleno para la alineación.
  *@return <b>String</b> String alineado a la derecha con el CARACTER.
  */
  public static String caracterIzquierda(long parmint, int parmLen, String parmCaracter) {
    return caracterIzquierda(String.valueOf(parmint),parmLen,parmCaracter);
  }

  /**
  *Alinea la variable double hacia la derecha colocando CARACTERES a la izquierda
  *según la longitud que se establezca y el CARACTER dado.
  *@param <b>parmString</b> Variable double que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@param <b>parmCaracter</b> String de relleno para la alineación.
  *@return <b>String</b> String alineado a la derecha con el CARACTER.
  */
  public static String caracterIzquierda(double parmint, int parmLen, String parmCaracter) {
    return caracterIzquierda(String.valueOf(parmint),parmLen,parmCaracter);
  }

  /**
  *Alinea la variable String hacia la derecha colocando CARACTERES a la izquierda
  *según la longitud que se establezca y el CARACTER dado.
  *@param <b>parmint</b> Variable int que será¡ alineada.
  *@param <b>parmLen</b> Longitud dentro de la cual será¡ la alineación.
  *@param <b>parmCaracter</b> String de relleno para la alineación.
  *@return <b>String</b> String alineado a la derecha con el CARACTER.
  */
  public static String caracterIzquierda(String parmString, int parmLen, String parmCaracter) {

     String  tempString = parmString;

     if (tempString.length() > parmLen)
        tempString = tempString.substring(tempString.length()-parmLen,
                                          tempString.length());
     else {
        while (tempString.length() < parmLen)
          tempString = parmCaracter+tempString;
     }

     return tempString;

  }

  /**
  *Retorna la Fecha y Hora del dï¿½a.
  *@return <b>String</b> String en formato dd/mm/yyyy hh:mm.
  */
  public static String getFechaHoraPC() {

    java.util.Calendar rightNow = java.util.Calendar.getInstance();
    String   today    = caracterIzquierda(rightNow.get(java.util.Calendar.DAY_OF_MONTH),2,"0")+"/"+
                        caracterIzquierda(rightNow.get(java.util.Calendar.MONTH)+1,2,"0")+"/"+
                        rightNow.get(java.util.Calendar.YEAR)+" "+
                        caracterIzquierda(rightNow.get(java.util.Calendar.HOUR_OF_DAY),2,"0")+":"+
                        caracterIzquierda(rightNow.get(java.util.Calendar.MINUTE),2,"0")+":"+
                        caracterIzquierda(rightNow.get(java.util.Calendar.SECOND),2,"0");
     return today;

  }



  /**
  *Devuelve en letras un monto determinado.
  *@param <b>monto</b> Variable int que será¡ mostrada en letras.
  *@return <b>String</b> Monto en letras.
  */
  public static String montoEnLetras(int monto) {
    return valorEnLetras(new Double(String.valueOf(monto)));
  }

  /**
  *Devuelve en letras un monto determinado.
  *@param <b>monto</b> Variable long que será¡ mostrada en letras.
  *@return <b>String</b> Monto en Letras.
  */
  public static String montoEnLetras(long monto) {
    return valorEnLetras(new Double(String.valueOf(monto)));
  }

  /**
  *Devuelve en letras un monto determinado.
  *@param <b>monto</b> Variable double que será¡ mostrada en letras.
  *@return <b>String</b> Monto en Letras.
  */
  public static String montoEnLetras(double monto) {
    return valorEnLetras(new Double(monto));
  }

  /**
  *Devuelve en letras un monto determinado.
  *@param <b>monto</b> Variable String que será¡ mostrada en letras.
  *@return <b>String</b> Monto en Letras.
  */
  public static String montoEnLetras(String monto) {
    return valorEnLetras(new Double(AtuxUtility.getDecimalNumber(monto)));
  }

  /**
  *Devuelve en letras un monto determinado.
  *@param <b>valor</b> Objeto Double que será¡ mostrada en letras.
  *@return <b>String</b> Monto en Letras.
  */
  private static String valorEnLetras(Double valor) {

    String centavos = "00";
    double doubleValor  = Double.parseDouble(valor.toString());
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
    int millon    = 0;
    int cienMil   = 0;

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

    return  cadena.trim()+" Nuevos Soles";

  }

  /**
  *Retorna en letras monto concatenado.
  *@param <b>numero</b> Variable int que será¡ procesada.
  *@param <b>fin</b> Indica si existen o no, procesos pendientes.
  *@return <b>String</b> Monto concatenado en letras.
  */
  public static String base(int numero, boolean fin) {

    String cadena = "";
    int unidad = 0;
    int decena = 0;
    int centena = 0;

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
  *Retorna en letras la cantidad de unidades de un Número dado.
  *@param <b>numero</b> Variable int que será¡ procesada.
  *@param <b>fin</b> Indica si existen o no, procesos pendientes.
  *@return <b>String</b> Número de unidades en letras.
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
  *Retorna en letras la cantidad de decenas de un Número dado.
  *@param <b>numero</b> Variable int que será¡ procesada.
  *@return <b>String</b> Número de decenas en letras.
  */
  public static String decenas(int numero) {

    String[] aDecenas = {"DIEZ","VEINTE","TREINTA","CUARENTA","CINCUENTA",
                         "SESENTA","SETENTA","OCHENTA","NOVENTA"};

    return (numero==0?"":aDecenas[numero-1]);

  }

  /**
  *Retorna en letras la cantidad de centenas de un Número dado.
  *@param <b>numero</b> Variable int que será¡ procesada.
  *@return <b>String</b> Número de centenas en letras.
  */
  public static String centenas(int numero) {

    String[] aCentenas = {"CIENTO","DOSCIENTOS","TRESCIENTOS","CUATROCIENTOS",
                          "QUINIENTOS","SEISCIENTOS","SETECIENTOS","OCHOCIENTOS","NOVECIENTOS"};

    return (numero==0?"":aCentenas[numero-1]);

  }

  /**
  *Retorna una cadena de blancos según una longitud establecido.
  *@param <b>parmLen</b> Variable int que determina la longitud de la cadena.
  *@return <b>String</b> Cadena de blancos.
  */
  public static String llenarBlancos(int parmLen) {

    String tempString = "";
    for (int i=0; i<parmLen; i++) tempString += " ";
    return tempString;

  }

  public static String fillBlanks(int parmLen) {
    String tempString = "";
    for (int i=0; i<parmLen; i++) tempString += " ";
    return tempString;
  }

}
