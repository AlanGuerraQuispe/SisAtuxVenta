package com.aw.swing.spring;

import com.aw.core.spring.ApplicationBase;
import com.aw.swing.config.ConfigInfoProvider;
import com.aw.swing.context.SwingContext;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.ui.msg.MsgDisplayer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

//jcv.2.0.1-import com.aw.core.domain.entity.ApplicationUser;

/**
 * User: gmc
 * Date: 22/04/2009
 */

public class Application extends ApplicationBase {

    static{
        _instance = new Application();
    }

    public static Application instance() {
        return (Application) _instance;
    }

    public void init() {
        super.init();
//        RepaintManager.setCurrentManager(new CheckAndLogRepaintManager());
/*
        ConfigInfoProvider configInfoProvider = getBean(ConfigInfoProvider.class);
        if (configInfoProvider!=null){
            SwingContext.setMsgBaseNames(configInfoProvider.getMsgBaseNames());
        }
*/
        SwingContext.init();
        MsgDisplayer.setMessageDisplayer(SwingContext.getMsgDisplayer());
    }
    protected void onLoadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        String[] presenters = beanFactory.getBeanNamesForType(Presenter.class);
        for (String presenter : presenters) {
            BeanDefinition bn = beanFactory.getBeanDefinition(presenter);
            bn.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        }
    }

}
