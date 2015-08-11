package com.aw.core.distributed;

import java.lang.reflect.InvocationTargetException;

/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public interface IInvoker {
    /**
     * Crea remotamente un objeto
     *
     * @param factoryClass
     * @param factoryMethod
     * @param params
     * @return Handler usado para referenciar al objeto remoto
     */
    Object create(Class factoryClass,String factoryMethod, Object params[]) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException;

    /**
     * Invoca un metodo de un objeto remoto
     * @param handler Handler retornado por {@link #create(Class, String, Object[])}
     * @param method
     * @param params
     * @param paramsTypeStr
     * @return el objeto retornado por el metodo NULL si es void
     */
    Object invoke(Object handler, String method, Object params[], String[] paramsTypeStr) throws Throwable, InvocationTargetException, ClassNotFoundException, NoSuchMethodException;

    /**
     * Destruye una instancia remota
     * @param handler Handler retornado por {@link #create(Class, String, Object[])}
     */
    void destroy(Object handler);

}
