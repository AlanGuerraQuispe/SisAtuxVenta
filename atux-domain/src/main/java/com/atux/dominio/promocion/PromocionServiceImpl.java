package com.atux.dominio.promocion;

import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionLocal;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.PromocionQryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
@Service
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    PromocionQryMapper promocionQryMapper;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ConversionService conversionService;



}
