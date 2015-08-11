package com.atux.infraestructura.jpa.pojo;

import java.util.Date;

/**
 * Created by MATRIX-JAVA on 02/12/2014.
 */
public interface AuditoriaEntity {
    public String getCreadoPor();

    public void setCreadoPor(String creadoPor);

    public Date getFechaCreacion();

    public void setFechaCreacion(Date fechaCreacion);

    public String getModificadoPor();

    public void setModificadoPor(String modificadoPor);

    public Date getFechaModificacion();

    public void setFechaModificacion(Date fechaModificacion);

}
