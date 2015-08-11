package com.atux.service.qryMapper;

import com.atux.bean.donacion.DonacionDetalle;
import com.atux.bean.donacion.DonacionFlt;

import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
public interface DonacionQryMapper {

    List<DonacionDetalle> findDonacionDetalleList(DonacionFlt filtro);

    List findInstitucionList(DonacionFlt filtro);

    void deleteDonacionLocal(String coInstitucion);
}
