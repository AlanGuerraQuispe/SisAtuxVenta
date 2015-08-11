package com.aw.core.spring;

import com.aw.core.annotation.AnnotationUtils;
import com.aw.core.distributed.proxy.ProxyFactory;
import com.aw.core.domain.AWBusinessException;
import com.aw.support.reflection.AttributeAccessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <br>
 * Funcionalidad : <br>
 * <br>
 * Histórico de Creación/Modificación<br>
 * <br>
 * ID   PROGRAMADOR  FECHA/HORA  TIPO          OBSERVACIÓN
 * 001   JCM          01/05/2009 Creación      <br>
 * 002   JCM          06/10/2008  Modificación  <br>
 * <br>
 * <br>
 *
 * @author Julio C. Macavilca - AW<br>
 * @version 1.0<br>
 */
public class ApplicationBase {

    protected final Log logger = LogFactory.getLog(getClass());

    protected ApplicationContext appCtx;

    protected static ApplicationBase _instance = new ApplicationBase();

    public static ApplicationBase instance() {
        return _instance;
    }

    public void init() {
        initSpringContext();
    }

    public ProxyFactory distributedProxyFactory;

    public <T> T getBean(Class<T> clazz) {
        if (distributedProxyFactory != null)
            return distributedProxyFactory.proxyNew(clazz);
        String[] names = appCtx.getBeanNamesForType(clazz);
        if (names.length == 0) throw new AWBusinessException("Bean for " + clazz.getName() + " does not exist");
        return (T) appCtx.getBean(names[0]);
    }

    public Object getBean(String beanName, Object clazz) {
        return appCtx.getBean(beanName);
    }

    protected void initSpringContext() {
        String configFiles = "classpath*:/appCtx/**/*.xml";
        appCtx = new ClassPathXmlApplicationContext(configFiles) {
            protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws IOException {
                super.loadBeanDefinitions(beanFactory);
                onLoadBeanDefinitions(beanFactory);
            }
        };
    }

    @Deprecated
    public void initForTest(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public void setAppCtx(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public ApplicationContext getAppCtx(){
        return this.appCtx;
    }

    protected void onLoadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
    }

    public void autowireFields(Object component) {
        List<Field> fields = AnnotationUtils.getAnnotatedFieldsFrom(component, Autowired.class);
        for (Field field : fields) {
            Object bean = getBean(field.getType());
            AttributeAccessor.set(component, field, bean);
        }
    }

    public Resource getResource(String resourcePath) {
        return appCtx.getResource(resourcePath);
    }

    public InputStream getStreamFromClasspath(String path) throws IOException {
        System.out.println("Trayendo file desde classpathXXXXX");
        ClassPathResource r = new ClassPathResource(path, path.getClass().getClassLoader());
        return r.getInputStream();
    }

	public void destroyAppCtx(){
		((ClassPathXmlApplicationContext)appCtx).close();
		((ClassPathXmlApplicationContext)appCtx).destroy();
		this.appCtx = null;
	}
}
