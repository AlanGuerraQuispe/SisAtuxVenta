package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.LgtmProducto;
import com.atux.infraestructura.jpa.pojo.LgtmProductoPK;
import com.atux.infraestructura.jpa.pojo.VttrProductoBono;
import com.atux.infraestructura.jpa.pojo.VttrProductoBonoPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface LgtmProductoRepository extends CrudRepository<LgtmProducto,LgtmProductoPK> {

}
