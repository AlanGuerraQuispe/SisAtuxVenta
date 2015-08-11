package com.atux.comun;

import com.atux.comun.context.AppCtx;

/**
 * Created by MATRIX-JAVA on 19/11/2014.
 */
public class FilterBaseLocal {

    private LocalId localId = new LocalId();

    public FilterBaseLocal() {
        localId = AppCtx.instance().getLocalId();
    }

    public LocalId getLocalId() {
        return localId;
    }

    public void setLocalId(LocalId localId) {
        this.localId = localId;
    }
}
