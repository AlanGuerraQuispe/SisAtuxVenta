package com.atux.bean.promocion;

/**
 * Created by MATRIX-JAVA on 5/2/2015.
 */
public class PromocionLocal {

    private String coLocal;
    private String deLocal;

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromocionLocal that = (PromocionLocal) o;

        if (coLocal != null ? !coLocal.equals(that.coLocal) : that.coLocal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coLocal != null ? coLocal.hashCode() : 0;
    }
}
