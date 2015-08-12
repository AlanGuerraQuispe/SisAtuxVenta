package com.atux.config;

import com.aw.core.db.DbUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Kaiser
 * Date: 08/06/2009
 */
public class Utils {
    protected static final Log logger = LogFactory.getLog(Utils.class);

    public static boolean validateDateBeforeToday(Date fecha) {
        return fecha == null || !DbUtil.instance().getCurrentDate().before(fecha);

    }

    public static boolean validateRangeDate(Date feInicio, Date feFin) {
        return !(feInicio == null || feFin == null) && !feInicio.before(feFin);

    }

    public static boolean validateRUC(String ruc) {

        if (ruc == null || ruc.equals("")) return true; // nothing to validate
        ruc = ruc.trim();


        // longitud
        if (ruc.length() != 11) {
            logger.info("RUC[" + ruc + "] no tiene 11 digitos");
            return false;
        }

        // digitos
        Character noDigito = findNonDigit(ruc);
        if (noDigito != null) {
            logger.info("RUC[" + ruc + "] tiene caracter no valido:'" + noDigito + "'");
            return false;
        }

        String rucPrefix = ruc.substring(0, 2);
        List rucSerieList = Arrays.asList(new String[]{"10", "15", "20", "17"});
        if (rucSerieList.indexOf(rucPrefix) < 0) {
            // ruc NO pertenece a la serie
        }
        int suma = 0, x = 6;
        for (int i = 0; i < 10; i++) {
            if (i == 4) x = 8;
            int digito = ruc.charAt(i) - '0';
            x--;

            //Nota esto hace lo mismo, pero esta asi en el SP, confirmar si hay error
            if (i == 0) suma += digito * x;
            else suma += digito * x;
        }
        int resto = suma % 11;
        resto = 11 - resto;
        if (resto >= 10) resto -= 10;

        int ultDigito = ruc.charAt(10) - '0';
        boolean rucOk = resto == ultDigito;
        if (!rucOk) {
            logger.info("RUC[" + ruc + "] digito de verificacion incorrecto calculado:'" + resto + "'");
            return false;
        }
        return true;
    }

    private static Character findNonDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return new Character(str.charAt(i));
        }
        return null;
    }


    public static boolean validarEmail(String email) {
        if (email == null || StringUtils.isEmpty(email)) return false;
        //Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[A-Z,a-z]+");

        //Match the given string with the pattern
        Matcher m = p.matcher(email);

        //check whether match is found
        return m.matches();

    }
}
