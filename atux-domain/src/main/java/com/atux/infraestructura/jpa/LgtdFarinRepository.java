package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.LgtdFarinV2;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2PK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface LgtdFarinRepository extends CrudRepository<LgtdFarinV2,LgtdFarinV2PK> {

}
