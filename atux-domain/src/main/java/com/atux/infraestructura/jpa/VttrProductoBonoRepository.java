package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.LgtdFarinV2;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2PK;
import com.atux.infraestructura.jpa.pojo.VttrProductoBono;
import com.atux.infraestructura.jpa.pojo.VttrProductoBonoPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface VttrProductoBonoRepository extends CrudRepository<VttrProductoBono,VttrProductoBonoPK> {

}
