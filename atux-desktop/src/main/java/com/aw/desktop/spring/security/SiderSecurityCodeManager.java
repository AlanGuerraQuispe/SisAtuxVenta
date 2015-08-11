package com.aw.desktop.spring.security;

import com.aw.core.spring.io.ResourceProvider;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.types.CancelAction;
import com.aw.swing.mvp.action.types.SortByColumnAction;
import com.aw.swing.mvp.cmp.pick.PickAction;
import com.aw.swing.mvp.security.SecurityCodeManager;
import com.aw.swing.mvp.support.Zone;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * User: gmc
 * Date: 04/06/2009
 */
public class SiderSecurityCodeManager extends SecurityCodeManager {


    private String actionConfigFile = "classpath:com/aw/desktop/spring/security/SecurityCodesConfig.properites";


    private Properties securityProperties = null;

    public void setSecurityCodes(Presenter presenter) {
        String pstSimpleName = presenter.getClass().getSimpleName();
        String view = pstSimpleName.substring(0, pstSimpleName.length() - 3);
        String pstSecurityCode = getSecurityProperties().getProperty(view);
        if (pstSecurityCode != null) {
            presenter.setSecurityCode(pstSecurityCode);
            Collection<Action> acciones = presenter.getActionRsr().getActions().values();
            setSecurityCodeToActions(view, acciones);
            Collection<Zone> zones = presenter.getZonekMgr().getZones();
            setSecurityCodeToZones(view, zones);
        } else {
            logger.warn("No existe el código de seguridad en el archivo para el Presenter:<" + presenter + ">");
        }
    }


    private void setSecurityCodeToZones(String view, Collection<Zone> zones) {
        for (Zone zone : zones) {
            String propName = view + "." + zone.getName();
            String zoneSecCode = securityProperties.getProperty(propName);
            if (StringUtils.hasText(zoneSecCode)) {
                zone.setSecurityCode(zoneSecCode);
            } else {
                logger.warn("No existe el código de seguridad en el archivo para la zona:<" + zone.getName() + ">");
            }
        }
    }

    private void setSecurityCodeToActions(String view, Collection<Action> acciones) {
        for (Action action : acciones) {
            if (
//                        !(action instanceof PaginateAction) &&
                    !(action instanceof SortByColumnAction) &&
                            !(action instanceof PickAction) &&
                            !(action instanceof CancelAction)
//                                &&
//                        !(action instanceof RefreshDependentGridsAction) &&
//                        !(action instanceof AlphabeticPaginateAction) &&
//                        !(action instanceof GetDependentCmbDataInfoAction)
                    ) {
                String propName = view + "." + action.getId().asString();
                String actionSecCode = securityProperties.getProperty(propName);
                if (StringUtils.hasText(actionSecCode)) {
                    action.setSecurityCode(actionSecCode);
                } else {
                    logger.warn("No existe el código de seguridad en el archivo para la acción:<" + action.getId() + ">");
                }
            }
        }
    }

    protected Properties getSecurityProperties() {
        if (securityProperties == null) {
            securityProperties = loadActionSecCodeProperties();
        }
        return securityProperties;
    }

    private Properties loadActionSecCodeProperties() {
        Resource resource = ResourceProvider.getResource(actionConfigFile);
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("No fue posible cargar el archivo con los códigos de seguridad de las acciones.", e);
        }
        return properties;
    }

}
