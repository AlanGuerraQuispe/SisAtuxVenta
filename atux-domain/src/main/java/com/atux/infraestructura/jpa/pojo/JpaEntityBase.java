package com.atux.infraestructura.jpa.pojo;



import com.atux.infraestructura.jpa.SaveListenerImpl;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * User: AW
 * Date: 24/02/13
 */
@MappedSuperclass
@EntityListeners(SaveListenerImpl.class)
public abstract class JpaEntityBase implements Serializable {


}
