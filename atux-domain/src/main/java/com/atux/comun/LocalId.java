package com.atux.comun;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class LocalId {
    private String coCompania="001";
    private String coLocal;

    public LocalId() {
    }

    public LocalId(String coCompania, String coLocal) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }
}
