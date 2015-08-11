package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.VttrPedidoDonacion;
import com.atux.infraestructura.jpa.pojo.VttrPedidoDonacionPK;
import com.atux.infraestructura.jpa.pojo.VttrProductoBono;
import com.atux.infraestructura.jpa.pojo.VttrProductoBonoPK;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface VttrPedidoDonacionRepository extends CrudRepository<VttrPedidoDonacion,VttrPedidoDonacionPK> {


    @Query(
            "select  vpd " +
                    " from VttrPedidoDonacion vpd" +
                    " where vpd.id.coCompania = ?1 "+
                    " and vpd.id.coLocal= ?2 "+
                    " and vpd.id.nuPedido= ?3"
    )
    List<VttrPedidoDonacion> obtenerDonaciones(@Param("coCompania")String coCompania,@Param("coLocal") String coLocal,@Param("nuPedido") String nuPedido);
}
