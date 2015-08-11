package com.atux.desktop.comun.auditoria;


import com.aw.core.db.DBExecuter;
import com.aw.core.db.transaction.TransactionDecorator;
import com.aw.core.domain.AWDeveloperException;
import com.aw.core.exception.FlowBreakSilentlyException;
import com.aw.core.util.StringUtils;
import com.aw.swing.mvp.action.types.ShowAuditingInfoAction;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//import com.atux.dominio.entity.UsuarioTmImpl;

/**
 * User: gmc
 * Date: 07/06/2009
 */
public class MostrarAuditoriaInfoAction extends ShowAuditingInfoAction {

    private boolean isDynamicEntityClass = false;
    private Class dynamicEntityClass;
    private static final String NOMBRE_COMPLETO = "nombreCompleto";
    private static final String ANEXO = "anexo";

    public MostrarAuditoriaInfoAction(Class entityClass) {
        super(entityClass);
        setTargetPstClass(AuditoriaInfoPst.class);
        needVisualComponent = false;
    }

    @Override
    public void checkBasicConditions() {
        Object selectedRow = getSelectedRow();
        Object id = getEntityId(selectedRow);
        if (id == null) {
            throw new FlowBreakSilentlyException();
        }
    }


    protected Object getObjectToBeShown(final Class entityClass, final Object id) {
        if (id == null)
            throw new FlowBreakSilentlyException();
        return TransactionDecorator.executeQuery(new DBExecuter() {
            public Object execute() {
                Object entity;
                if (isDynamicEntityClass)
                    entity = daoIntgr.getHbm().getHSession().get(getDynamicEntityClass(), (Serializable) id);
                else
                    entity = daoIntgr.getHbm().getHSession().get(entityClass, (Serializable) id);
                BNAuditoriaInfo bn = new BNAuditoriaInfo();
                BeanUtils.copyProperties(entity, bn);

                Map<String,String> nombreCompletoYAnexo = new HashMap<String, String>();

                bn.setNoUsuCrea(nombreCompletoYAnexo.get(NOMBRE_COMPLETO));
                bn.setAnexoCrea(nombreCompletoYAnexo.get(ANEXO));

                String usuModi = bn.getUsuaModi();
                if (!StringUtils.isEmpty(usuModi)) {
                    nombreCompletoYAnexo = new HashMap<String, String>();
                    bn.setNoUsuModi(nombreCompletoYAnexo.get(NOMBRE_COMPLETO));
                    bn.setAnexoModi(nombreCompletoYAnexo.get(ANEXO));
                }
                return bn;
            }
        });
    }



    public boolean isDynamicEntityClass() {
        return isDynamicEntityClass;
    }

    public MostrarAuditoriaInfoAction setAsDynamicEntityClass() {
        isDynamicEntityClass = true;
        return this;
    }

    public Class getDynamicEntityClass() {
        throw new AWDeveloperException("Falta implementar getDynamicEntityClass()");
    }
}
