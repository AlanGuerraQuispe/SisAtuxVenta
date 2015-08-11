package com.atux.desktop.comun.auditoria;

import com.aw.core.format.DateFormatter;

import java.io.Serializable;
import java.util.Date;

/**
 * User: gmc
 * Date: 07/06/2009
 */
public class BNAuditoriaInfo implements Serializable {
    private String usuaCrea;
    private String noUsuCrea;
    private Date fechCrea;
    private String fechCreaStr;
    private String nuipCrea;
    private String ussoCrea;
    private String nopcCrea;
    private String usuaModi;
    private String noUsuModi;
    private Date fechModi;
    private String fechModiStr;
    private String nuipModi;
    private String ussoModi;
    private String nopcModi;
    private String anexoCrea;
    private String anexoModi;


    public String getUsuaCrea() {
        return usuaCrea;
    }

    public void setUsuaCrea(String usuaCrea) {
        this.usuaCrea = usuaCrea;
    }

    public Date getFechCrea() {
        return fechCrea;
    }

    public void setFechCrea(Date fechCrea) {
        this.fechCrea = fechCrea;
    }

    public String getNuipCrea() {
        return nuipCrea;
    }

    public void setNuipCrea(String nuipCrea) {
        this.nuipCrea = nuipCrea;
    }

    public String getUssoCrea() {
        return ussoCrea;
    }

    public void setUssoCrea(String ussoCrea) {
        this.ussoCrea = ussoCrea;
    }

    public String getNopcCrea() {
        return nopcCrea;
    }

    public void setNopcCrea(String nopcCrea) {
        this.nopcCrea = nopcCrea;
    }

    public String getUsuaModi() {
        return usuaModi;
    }

    public void setUsuaModi(String usuaModi) {
        this.usuaModi = usuaModi;
    }

    public Date getFechModi() {
        return fechModi;
    }

    public void setFechModi(Date fechModi) {
        this.fechModi = fechModi;
    }

    public String getNuipModi() {
        return nuipModi;
    }

    public void setNuipModi(String nuipModi) {
        this.nuipModi = nuipModi;
    }

    public String getUssoModi() {
        return ussoModi;
    }

    public void setUssoModi(String ussoModi) {
        this.ussoModi = ussoModi;
    }

    public String getNopcModi() {
        return nopcModi;
    }

    public void setNopcModi(String nopcModi) {
        this.nopcModi = nopcModi;
    }

    public String getNoUsuCrea() {
        return noUsuCrea;
    }

    public void setNoUsuCrea(String noUsuCrea) {
        this.noUsuCrea = noUsuCrea;
    }

    public String getNoUsuModi() {
        return noUsuModi;
    }

    public void setNoUsuModi(String noUsuModi) {
        this.noUsuModi = noUsuModi;
    }

    public String getFechCreaStr() {
        if (fechCrea != null) {
            fechCreaStr = (String) DateFormatter.DATE_TIME_FORMATTER.format(fechCrea);
        }
        return fechCreaStr;
    }

    public String getFechModiStr() {
        if (fechModi != null) {
            fechModiStr = (String) DateFormatter.DATE_TIME_FORMATTER.format(fechModi);
        }
        return fechModiStr;
    }

    public String getAnexoCrea() {
        return anexoCrea;
    }

    public void setAnexoCrea(String anexoCrea) {
        this.anexoCrea = anexoCrea;
    }

    public String getAnexoModi() {
        return anexoModi;
    }

    public void setAnexoModi(String anexoModi) {
        this.anexoModi = anexoModi;
    }
}
