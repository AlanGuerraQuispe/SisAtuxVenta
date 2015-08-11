package com.aw.core.domain.model;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Server
 * Date: 16/07/2009
 * Time: 11:48:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EstadoToLabelMapper {
    Map<Class<? extends Auditable>, CodigoToLabelResolver> map = new HashMap();

    public void addMap(Class<? extends Auditable> auditableClass, CodigoToLabelResolver codigoToLabelResolver) {
        map.put(auditableClass, codigoToLabelResolver);
    }

    public String resolveCodigo (Auditable auditable, Object codigo){
        CodigoToLabelResolver resolver = map.get(auditable.getClass());
        String label = codigo==null?"":codigo.toString();
        if (resolver!=null)
           label = resolver.resolve(codigo);
        return label;
    }


    public static interface  CodigoToLabelResolver{
        String resolve(Object codigo);
    }
}
