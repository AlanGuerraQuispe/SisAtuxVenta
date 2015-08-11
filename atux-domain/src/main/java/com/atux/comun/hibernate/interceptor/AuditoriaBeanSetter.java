package com.atux.comun.hibernate.interceptor;

import com.aw.core.db.DbUtil;
import com.aw.core.domain.entity.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que setea dinamicamente los atributos
 * <p/>
 * fechaRegistro[..Nombre de Tabla…]:  echa hora en que se crea el registro.
 * fechaUlt                          : fecha hora en que se modifica el registro.
 * ip[..Nombre de Tabla…]           : Dirección IP de la maquina desde la cual se creó el registro.
 * nombrePc[..Nombre de Tabla…]     : Nombre de la PC desde la cual se creó el registro.
 * codUsuario                        : Es el usuario que creó el registro.
 * <p/>
 * User: JCM
 * Date: 19/10/2007
 */
public class AuditoriaBeanSetter implements Serializable {
    transient private static final Log logger = LogFactory.getLog(AuditoriaBeanSetter.class);
    private static final String USUA_MODI = "usuaModi";
    private static final String FECH_MODI = "fechModi";
    private static final String NUIP_MODI = "nuipModi";
    private static final String USSO_MODI = "ussoModi";
    private static final String NOPC_MODI = "nopcModi";

    private static final String USUA_CREA = "usuaCrea";
    private static final String FECH_CREA = "fechCrea";
    private static final String NUIP_CREA = "nuipCrea";
    private static final String USSO_CREA = "ussoCrea";
    private static final String NOPC_CREA = "nopcCrea";


    private static final String attributosCreacion[]     =
            {USUA_CREA, FECH_CREA, NUIP_CREA, USSO_CREA, NOPC_CREA};
    private static final String attributosModificacion[] =
            {USUA_MODI, FECH_MODI, NUIP_MODI, USSO_MODI, NOPC_MODI };

    /**
     * Called when an object is detected to be dirty, during a flush. The interceptor may modify the detected
     * <tt>currentState</tt>, which will be propagated to both the database and the persistent object.
     * Note that not all flushes end in actual synchronization with the database, in which case the
     * new <tt>currentState</tt> will be propagated to the object, but not necessarily (immediately) to
     * the database. It is strongly recommended that the interceptor <b>not</b> modify the <tt>previousState</tt>.
     *
     * @return <tt>true</tt> if the user modified the <tt>currentState</tt> in any way.
     */
    public boolean procesar(boolean saveInsert,
                            Object entity, Serializable id, Object[] currentState,
                            Object[] previousState, String[] propertyNames, Type[] types) {
        // iteramos en sentido inverso y mantenemos un flag para evitar iterando demas
        int setSizeCont = attributosCreacion.length;
        int setPendCont = setSizeCont;
        java.util.List attModif = new java.util.ArrayList(5);
        UserInfo user = getUserInfo();
        Date date = DbUtil.instance().getCurrentDate();
        //Date date = new Date();
        for (int i = propertyNames.length - 1; i >= 0 && setPendCont > 0; i--) {
            String propertyName = propertyNames[i];
            if (saveInsert) {
                if (USUA_CREA.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getUserName();
                    attModif.add(USUA_CREA + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (FECH_CREA.equalsIgnoreCase(propertyName)) {
                    currentState[i] = date;
                    attModif.add(FECH_CREA + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (NUIP_CREA.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getSessionIP();
                    attModif.add(NUIP_CREA + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (USSO_CREA.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getSo();
                    attModif.add(USSO_CREA + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (NOPC_CREA.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getNoPc();
                    attModif.add(NOPC_CREA + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
            } else {
                if (USUA_MODI.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getUserName();
                    attModif.add(USUA_MODI + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (FECH_MODI.equalsIgnoreCase(propertyName)) {
                    currentState[i] = date;
                    attModif.add(FECH_MODI + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (NUIP_MODI.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getSessionIP();
                    attModif.add(NUIP_MODI + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (USSO_MODI.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getSo();
                    attModif.add(USSO_MODI + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
                if (NOPC_MODI.equalsIgnoreCase(propertyName)) {
                    currentState[i] = user.getNoPc();
                    attModif.add(NOPC_MODI + "=" + currentState[i]);
                    setPendCont--;
                    continue;
                }
            }
        }
        boolean modified = setPendCont != setSizeCont;
        logger.debug("AtributosAuditoria modif:" + modified + " attr " + attModif + " asignado a " + entity);
        return modified;
    }

    public UserInfo getUserInfo() {

//        ApplicationUser appUser =  getAppUser();
        //UserInfo userInfo = SecurityContext.instance().getUsrInfo();
        UserInfo userInfo = new UserInfo();
/*        if (appUser == null) {
            logger.info("Creating mock user.");
            userInfo.setUserId(1111l);
            userInfo.setUserName("TEST USER");
            userInfo.setSessionIP(NetUtils.getLocalHostIp());
            userInfo.setNoPc(NetUtils.getLocalHostName());
            userInfo.setSo(NetUtils.getOperatingSystem());
        } else{*/
//            userInfo.setUserId(appUser.getPk());
//            userInfo.setUserName(appUser.getUsername());
//            userInfo.setSessionIP(appUser.getIpAddress());
//            userInfo.setNoPc(appUser.getHostName());
//            userInfo.setSo(appUser.getOS());
//        }
        return userInfo;
    }

//    private ApplicationUser getAppUser() {
//        return SPApplication.getAppUser();
//    }

}