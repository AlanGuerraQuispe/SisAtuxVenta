package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.LgtdFarinV2;
import com.atux.infraestructura.jpa.pojo.LgtdFarinV2PK;
import com.atux.infraestructura.jpa.pojo.LgtrProductoLocal;
import com.atux.infraestructura.jpa.pojo.LgtrProductoLocalPK;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface LgtrProductoLocalRepository extends JpaRepository<LgtrProductoLocal, LgtrProductoLocalPK> {

    @Query(value = "select lpl.caStockDisponible - nvl(lpl.caStockComprometido,0) " +
            " from LgtrProductoLocal lpl " +
            " where lpl.id = ?1 "
    )
    Integer obtenerStockActual(LgtrProductoLocalPK pk);

    @Modifying
    @Query("update LgtrProductoLocal " +
            " set  " +
            " caStockComprometido = NVL(caStockComprometido,0) + ?2, " +
            " inReplica= 0 , " +
            " fechaModificacion = SYSDATE  " +
            " where id = ?1"
    )
    void updateStockComprometido(@Param("id")LgtrProductoLocalPK id,@Param("cantidad")int cantidad);

}
