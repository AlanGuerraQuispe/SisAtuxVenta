package com.atux.dominio.precios;

import com.atux.bean.central.Fraccionamiento;
import com.atux.bean.central.FraccionamientoLocal;
import com.atux.bean.central.ProductoFraccionFlt;
import com.atux.bean.kardex.Producto;
import com.atux.bean.kardex.ProductoFlt;
import com.atux.bean.precios.Incentivo;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.LgtmProductoRepository;
import com.atux.infraestructura.jpa.LgtrProductoLocalRepository;
import com.atux.infraestructura.jpa.VttrProductoBonoRepository;
import com.atux.infraestructura.jpa.pojo.*;
import com.atux.service.qryMapper.PrecioQryMapper;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.aw.core.domain.AWBusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    LgtrProductoLocalRepository lgtrProductoLocalRepository;

    @Autowired
    PrecioQryMapper precioQryMapper;

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    EntityManager entityManager;

    @Autowired
    LgtmProductoRepository lgtmProductoRepository;

    @Autowired
    VttrProductoBonoRepository vttrProductoBonoRepository;

    @Autowired
    ConversionService conversionService;

    @Transactional
    public void modificarPrecioDescuento(Producto producto) {

        LgtrProductoLocalPK pk=
                new LgtrProductoLocalPK(
                        producto.getCoCompania(),
                        AppCtx.instance().getLocalId().getCoLocal(),
                        producto.getCoProducto());
        LgtrProductoLocal entity=lgtrProductoLocalRepository.findOne(pk);
        entity.setVaDescuento(producto.getPorcentajeDescuento());
        entity.setVaPrecioPublico(producto.getPrecioVentaPublico());
        entity.setVaVenta(producto.getPrecioVenta());
        lgtrProductoLocalRepository.save(entity);
    }

    @Transactional
    public void modificarVaExhibicion(Producto producto) {

        LgtrProductoLocalPK pk=
                new LgtrProductoLocalPK(
                        producto.getCoCompania(),
                        AppCtx.instance().getLocalId().getCoLocal(),
                        producto.getCoProducto());
        LgtrProductoLocal entity=lgtrProductoLocalRepository.findOne(pk);
        entity.setVaExhibicion(producto.getVaExhibicion());
        lgtrProductoLocalRepository.save(entity);
    }

    @Transactional
    public void grabarFraccion(Fraccionamiento backBean) {

        List<FraccionamientoLocal> fraccionLocalList=backBean.getDetalleList();

        for (FraccionamientoLocal fraccionamientoLocal : fraccionLocalList) {
            if(StringUtils.isBlank(fraccionamientoLocal.getInProdFraccionadoNuevo())) continue;
            ProductoFraccionFlt productoFlt = new ProductoFraccionFlt();
            productoFlt.setCoCompania(fraccionamientoLocal.getCoCompania());
            productoFlt.setCoLocal(fraccionamientoLocal.getCoLocal());
            productoFlt.setCoProducto(backBean.getProducto().getCoProducto());
            Long nuSecProd = productoQryMapper.findNextNuSecFraccion(productoFlt);
            productoFlt.setUsuario(AppCtx.instance().getUsuario().getIdUsuario());
            productoQryMapper.inactivarProductoFraccion(productoFlt);
            fraccionamientoLocal.setProducto(backBean.getProducto());
            fraccionamientoLocal.setNuSecProdFraccion(nuSecProd + 1);
            fraccionamientoLocal.setUsuario(AppCtx.instance().getUsuario().getIdUsuario());
            productoQryMapper.insertProductoFraccion(fraccionamientoLocal);

        }
    }

    @Transactional
    public void grabarIncentivo(Incentivo backBean) {

        Date fechaActual= new Date(); // todo jalar la fecha de base de datos
        if(backBean.getFeInicio().compareTo(fechaActual)<=0){
            throw new AWBusinessException("La fecha de inicio debe ser superior al dia de hoy");
        }

        if(backBean.getFeFin().compareTo(backBean.getFeInicio())<=0){
            throw new AWBusinessException("El rango de fechas es incorrecto");
        }


        if(backBean.getVaIncentivo().compareTo(BigDecimal.ZERO)<=0){
            throw new AWBusinessException("Ingrese un incentivo mayor a cero");
        }

        VttrProductoBono entity = conversionService.convert(backBean, VttrProductoBono.class);
        LgtmProductoPK productoPK = new LgtmProductoPK(backBean.getCoCompania(), backBean.getCoProducto());
        LgtmProducto producto = lgtmProductoRepository.findOne(productoPK);
        producto.setVaBono(backBean.getVaIncentivo());
        lgtmProductoRepository.save(producto);
        ProductoFlt productoFlt = new ProductoFlt(backBean.getProducto().getCoProducto());
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        Long nuSecProd = precioQryMapper.findNextNuSecProd(productoFlt);
        backBean.setIdUsuario(AppCtx.instance().getUsuario().getIdUsuario());
        precioQryMapper.inactivarProductoBono(backBean);
        entity.getId().setNuSecProdBono(nuSecProd + 1);
        vttrProductoBonoRepository.save(entity);
        backBean.setFeCreacion(new Date());

    }
}
