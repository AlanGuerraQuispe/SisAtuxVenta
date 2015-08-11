package com.aw.core.domain.model;

import java.util.Date;

/**
 * Representa valores de campos de auditoria
 * User: Juan Carlos Vergara
 * Date: 05/06/2009
 */
//todo:jcv mover a proyecto dao
public interface Auditable {
    Object getCodigo();
    //void setCodigo(String codigo);

    String getEstado();
    //void setEstado(String estado);

    String getUsuaCrea();
    //void setUsuaCrea(String usuaCrea);

    Date getFechCrea();
    //void setFechCrea(Date fechCrea);

}
