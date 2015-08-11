package com.aw.swing.context;

import com.aw.swing.config.ConfigInfoProvider;

/**
 * User: gmc
 * Date: 21/04/2009
 */
public class AWJrcpContext {
    protected ConfigInfoProvider configInfoProvider = new ConfigInfoProvider();
    protected static AWJrcpContext instance = new AWJrcpContext();

    

    public static AWJrcpContext instance() {
        return instance;
    }

    public ConfigInfoProvider getConfigInfoProvider() {
        return configInfoProvider;
    }

    public void setConfigInfoProvider(ConfigInfoProvider configInfoProvider) {
        this.configInfoProvider = configInfoProvider;
    }
}
