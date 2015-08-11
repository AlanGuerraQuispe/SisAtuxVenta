package com.aw.core.distributed.endpoint;

import com.aw.core.distributed.IInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public class InvokerClientImpl implements IInvoker {
    IInvoker delegate ;

    public void setDelegate(IInvoker delegate) {
        this.delegate = delegate;
    }

    /**
     * Crea remotamente un objeto
     *
     * @param factoryClass
     * @param factoryMethod
     * @param params
     * @return Handler usado para referenciar al objeto remoto
     */
    public Object create(Class factoryClass, String factoryMethod, Object params[]) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return delegate.create(factoryClass, factoryMethod, params);
    }

    /**
     * Invoca un metodo de un objeto remoto
     *
     * @param handler       Handler retornado por {@link #create(Class, String, Object[])}
     * @param method
     * @param params
     * @param paramsTypeStr
     * @return el objeto retornado por el metodo NULL si es void
     */
    public Object invoke(Object handler, String method, Object params[], String[] paramsTypeStr) throws Throwable{
        try{
            return delegate.invoke(handler, method, params, paramsTypeStr);
        }catch (Throwable e){
            if (e.getCause()!=null && e!=e.getCause())
                e = e.getCause();
            throw e;
        }
    }

    /**
     * Destruye una instancia remota
     *
     * @param handler Handler retornado por {@link #create(Class, String, Object[])}
     */
    public void destroy(Object handler) {
        delegate.destroy(handler);
    }
}
