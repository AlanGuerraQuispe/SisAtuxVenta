package com.aw.core.domain.model;

import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Date;

/**
 * User: Juan Carlos Vergara
 * Date: 08/06/2009
 */
public class AuditableImpl implements Auditable{
    protected final Log logger = LogFactory.getLog(getClass());
    
    String codigo;
    String estado;
    Date fechCrea;
    String usuaCrea;
    private String codigoLabel = "Código: ";

    public AuditableImpl(Object source) {
        try {
        BeanWrapper bw = new BeanWrapperImpl(source);
        Object codigoTmp = bw.getPropertyValue("codigo");
        codigo = codigoTmp==null?null:String.valueOf(codigoTmp);
        estado = (String)bw.getPropertyValue("estado");
        usuaCrea = (String)bw.getPropertyValue("usuaCrea");
        fechCrea = (Date)bw.getPropertyValue("fechCrea");
        if (source!=null){
            // Estado resolver
            EstadoToLabelMapper estadoToLabelMapper = (EstadoToLabelMapper) ApplicationBase.instance().getBean("estadoToLabelMapper", null);
            if (estadoToLabelMapper !=null && estado!=null)
                estado = estadoToLabelMapper.resolveCodigo((Auditable) source, estado);
            //CodigoLabel
            AuditableLabelInfo auditableLabelInfo = source.getClass().getAnnotation(AuditableLabelInfo.class);
            if (auditableLabelInfo!=null)
                codigoLabel= auditableLabelInfo.codigoLabel();
        }

        } catch (Exception e){
            logger.error("Error asignando campos de auditoria en " + source, e);    
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechCrea() {
        return fechCrea;
    }

    public void setFechCrea(Date fechCrea) {
        this.fechCrea = fechCrea;
    }

    public String getUsuaCrea() {
        return usuaCrea;
    }

    public void setUsuaCrea(String usuaCrea) {
        this.usuaCrea = usuaCrea;
    }

    public String getCodigoLabel() {
        return codigoLabel;
    }
}
