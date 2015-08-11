package com.aw.core.context;

import com.aw.core.config.ConfigInfoProvider;

/**
 * Class use to gather all the comon info for the other layers.
 * User: gmc
 * Date: 24-sep-2008
 */
public class AWBaseContext {
    protected ConfigInfoProvider configInfoProvider = new ConfigInfoProvider();
    protected static AWBaseContext instance = new AWBaseContext();

    public static AWBaseContext instance() {
        return instance;
    }

    public ConfigInfoProvider getConfigInfoProvider() {
        return configInfoProvider;
    }
}
