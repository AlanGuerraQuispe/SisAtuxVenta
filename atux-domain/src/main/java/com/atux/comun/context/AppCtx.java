package com.atux.comun.context;

import com.atux.comun.LocalId;
import com.atux.dominio.modelo.UsuarioDTO;
import com.aw.core.domain.AWBusinessException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;


public class AppCtx {

    protected final Log LOG = LogFactory.getLog(getClass());
    private static AppCtx instance = new AppCtx();

    private boolean enviromentTest = false;
    private boolean showDialog = true;
    private UsuarioDTO usuario;
    private BigDecimal vaImpuesto = new BigDecimal(18);
    private String impresoraTestigo;

    private AppCtx() {
        InputStream fis = null;
        Properties properties = null;
        fis = this.getClass().getResourceAsStream("/properties/AtuxServidor.properties");

        if (fis != null) {
            properties = new Properties();
            try {
                properties.load(fis);
            } catch (IOException e) {
                throw new AWBusinessException("Error al leer properties");
            }
            impresoraTestigo = properties.getProperty("ImpresoraTestigo");
            localId.setCoCompania(properties.getProperty("CodigoCompania"));
            localId.setCoLocal(properties.getProperty("CodigoLocal"));
            if (StringUtils.isNotBlank(System.getProperty("Env.test"))) {
                LOG.info("Entorno TEST");
                setEnviromentTest(true);
            }
            if (StringUtils.isNotBlank(System.getProperty("Env.showDialog"))) {
                LOG.info("SHOW DIALOG FALSE");
                setShowDialog(false);
            }
        } else {
            throw new AWBusinessException("Error al leer properties");
        }
    }

    private LocalId localId = new LocalId();

    public LocalId getLocalId() {
        return localId;
    }

    public void setLocalId(LocalId localId) {
        this.localId = localId;
    }

    public static AppCtx instance() {
        return instance;
    }

    public String getCoLocal() {
        return instance().getLocalId().getCoLocal();
    }

    public String getCoCompania() {
        return instance().getLocalId().getCoCompania();
    }

    public UsuarioDTO getUsuario() {
        if (usuario == null) {
            usuario = new UsuarioDTO();
            //usuario.setIdUsuario("AGUERRA");
        }
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getVaImpuesto() {
        return vaImpuesto;
    }

    public void setVaImpuesto(BigDecimal vaImpuesto) {
        this.vaImpuesto = vaImpuesto;
    }

    public String getImpresoraTestigo() {
        return impresoraTestigo;
    }

    public void setImpresoraTestigo(String impresoraTestigo) {
        this.impresoraTestigo = impresoraTestigo;
    }

    public boolean isEnviromentTest() {
        return enviromentTest;
    }

    public void setEnviromentTest(boolean enviromentTest) {
        this.enviromentTest = enviromentTest;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
