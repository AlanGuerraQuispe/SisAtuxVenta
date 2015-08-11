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
package com.aw.core.db;


import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: User
 * Date: Oct 16, 2007
 */
public class RPUtils {

    public static Date getSgteDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        return cal.getTime();
    }

    /**
     * Devuelve la sentencia sql que debe ser incluida en el where para poder filtrar la tabla teniendo
     * en cuenta si un campo fecha pertenece a un determinado rango de fechas
     *
     * @param nombreCampo
     * @param pFechaIni
     * @param pFechaFin
     * @return
     */

    public static StringBuffer agregarFechaRangoFiltro(StringBuffer sql, String nombreCampo, Date pFechaIni, Date pFechaFin) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicio = pFechaIni == null ? null : simpleDateFormat.format(pFechaIni);
        String fechaFin = pFechaFin == null ? null : simpleDateFormat.format(pFechaFin);
        if (sql == null) sql = new StringBuffer();
        if ((fechaInicio != null) && (fechaFin != null)) {
            sql.append(nombreCampo);
            sql.append(" BETWEEN TO_DATE('").append(fechaInicio).append("','dd/MM/yyyy') AND TO_DATE('").append(fechaFin).append(" 23:59:59','dd/MM/yyyy HH24:MI:ss') ");
        }
        return sql;
    }

    public static StringBuffer agregarStringRangoFiltro(StringBuffer sql, String nombreCampo, String pCampoIni, String pCampoFin) {
        if (sql == null) sql = new StringBuffer();
        if (StringUtils.hasText(pCampoIni) && StringUtils.hasText(pCampoFin)) {
            sql.append("( ").append(nombreCampo);
            sql.append(" BETWEEN ").append(pCampoIni).append(" AND ").append(pCampoFin).append(")");
        }
        return sql;
    }

}

