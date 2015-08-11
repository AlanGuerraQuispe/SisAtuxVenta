package com.atux.infraestructura.jpa;

import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.pojo.AuditoriaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

//import com.dpwc.seguridad.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;

/**
 * User: AW
 * Date: 24/02/13
 */
@Component
public class SaveListenerImpl implements SaveListener, ApplicationContextAware {
    protected static final Logger LOG = LoggerFactory.getLogger(SaveListenerImpl.class);


    @PostConstruct
    public void init() {
    }

    @PrePersist
    public void onPersist(Object entity) {

        if (entity instanceof AuditoriaEntity) {

            AuditoriaEntity auditable = (AuditoriaEntity) entity;
            try {
                auditable.setCreadoPor(AppCtx.instance().getUsuario().getIdUsuario());
            } catch (Exception e) {
                auditable.setCreadoPor("COMPONENTE");
                LOG.info("No se ha definido un usuario de la sesion. Se ha creado por componente automatico");
            }
            auditable.setFechaCreacion(new Date());
        }
    }

    @PreUpdate
    public void onUpdate(Object entity) {
        if (entity instanceof AuditoriaEntity) {

            AuditoriaEntity auditable = (AuditoriaEntity) entity;
            try {
                auditable.setModificadoPor(AppCtx.instance().getUsuario().getIdUsuario());
            } catch (Exception e) {
                //auditable.setModificadoPor("COMPONENTE");
                LOG.info("No se ha definido un usuario de la sesion. Se ha creado por componente automatico");
            }
            auditable.setFechaModificacion(new Date());
        }
    }

    static ApplicationContext appCtx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }
}

