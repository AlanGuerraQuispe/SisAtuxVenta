package com.aw.swing.mvp.support;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.binding.component.JComponentDecorator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;

/**
 * User: gmc
 * Date: 03/06/2009
 */
public class Zone {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected Presenter presenter;
    protected JTabbedPane parent;
    protected JPanel jPanel;
    protected String name;
    protected String securityCode = "";
    protected String securityLabel="";
    protected boolean initialized = false;
    protected boolean enabled = true;
    protected boolean readOnly = false;


    Zone(Presenter presenter, JTabbedPane parent, JPanel jPanel, String name) {
        this.presenter = presenter;
        this.parent = parent;
        this.jPanel = jPanel;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        if (!name.equals(zone.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public JTabbedPane getParent() {
        return parent;
    }

    public void setParent(JTabbedPane parent) {
        this.parent = parent;
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public void setJPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    public Zone setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateUIStatus();
        return this;
    }

    public Zone setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        updateUIStatus();
        return this;
    }

    public Zone setAsReadOnly() {
        this.readOnly = true;
        updateUIStatus();
        return this;
    }

    public boolean isEnabled() {
        if (enabled) {
            if (presenter.getSecurityMgr().getSecurityChecker() != null) {
                enabled = presenter.getSecurityMgr().getSecurityChecker().canAccess(this);
                if (!enabled) {
                    logger.info("Zone:<" + this + "> is disabled for Security Configuration.");
                }
            }
        }
        return enabled;
    }


    public void updateUIStatus() {
        if (!initialized){
            return;
        }
        boolean enabled = isEnabled();
        if (parent != null) {
            int index = parent.indexOfComponent(jPanel);
            if (index != -1) {
                parent.setEnabledAt(index, enabled);
                if (enabled) {
                    if (isReadOnly()) {
                        JComponentDecorator.setUIReadOnly(jPanel);
                    }else{
                        JComponentDecorator.setUINoReadOnly(jPanel);                        
                    }
                }
            } else {
                logger.info("Zone:<" + name + "> does not in:" + parent);
            }
        } else {
            if (!enabled || isReadOnly()) {
                JComponentDecorator.setUIReadOnly(jPanel);
            }
        }
    }

    public boolean isReadOnly() {
        if (readOnly){
            return true;
        }
        if (presenter.getSecurityMgr().getSecurityChecker() != null) {
            logger.info("Zone:<" + this + "> is readOnly for Security Configuration.");
            return presenter.getSecurityMgr().getSecurityChecker().isReadOnly(this);
        }
        return presenter.isReadOnly();
    }


    public String getSecurityLabel() {
        return securityLabel;
    }

    public Zone setSecurityLabel(String securityLabel) {
        this.securityLabel = securityLabel;
        return  this;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
