package com.aw.core.distributed.endpoint;

import com.aw.core.distributed.IInvoker;
import com.aw.core.distributed.example.SVDistributedTest;
import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public class InvokerServerImpl implements IInvoker {
    protected final Log logger = LogFactory.getLog(getClass());

    public static String SPRING_CONFIG_FILE = "appCtx-dist-rem.xml";

    private int handlerSeq = 1;
    private Map instanceRepository = new HashMap();

    /**
     * Crea remotamente un objeto
     *
     * @param factoryClass
     * @param factoryMethod
     * @param params
     * @return Handler usado para referenciar al objeto remoto
     */
    public Object create(Class factoryClass, String factoryMethod, Object params[]) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Object newInstance = null;
        if (factoryClass.equals(SVDistributedTest.class))
            newInstance = new SVDistributedTest();
        else
            newInstance = ApplicationBase.instance().getBean(factoryClass);

//        }else if (factoryClass.equals(DAOSql.class)){
//            newInstance = DAOIntgr.instance().getSql();
//        }else if (factoryClass.equals(DAOBean.class)){
//            newInstance = DAOIntgr.instance().getBean();
//        }else if (factoryClass.equals(HbmUtil.class)){
//            newInstance = HbmUtilFactory.newInstance();
//        }else if (factoryMethod!=null){
//            Method methodToCall = searchMethod(factoryClass, factoryMethod, null);
//            newInstance  = invoke(factoryClass, methodToCall, params);
//            newInstance = AWContext.getTxDec().getTxProxy(newInstance);
//        }else{
//            newInstance = AWContext.getTxDec().getTxProxy(factoryClass);
//        }
        // guardar objeto para posterior reuso
        Object handler = storeInstance(newInstance);
        logger.info("["+handler+"]Nueva instancia "+newInstance);
        return handler;
    }



    /**
     * Invoca un metodo de un objeto remoto
     *
     * @param handler       Handler retornado por {@link #create(Class, String, Object[])}
     * @param methodName
     * @param params
     * @param paramsTypeStr
     * @return el objeto retornado por el metodo NULL si es void
     */
    public Object invoke(Object handler, String methodName, Object params[], String[] paramsTypeStr) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        Object instancia = instanceRepository.get(handler);
        logger.info("["+handler+"]Invocando "+instancia.getClass()+"."+methodName);
        Class[] paramsType = new Class[paramsTypeStr.length];
        for (int i = 0; i < paramsType.length; i++) {
            if (Boolean.TYPE.getName().equals(paramsTypeStr[i]) )
                paramsType[i] = Boolean.TYPE;
            else
                paramsType[i] = Class.forName(paramsTypeStr[i]);
        }

        Method method = searchMethod(instancia.getClass(), methodName, paramsType);
        Object result = method.invoke(instancia, params);
        return result;
    }

    /**
     * Destruye una instancia remota
     *
     * @param handler Handler retornado por {@link #create(Class, String, Object[])}
     */
    public void destroy(Object handler) {
        Object instancia = instanceRepository.remove(handler);
        logger.info("["+handler+"]removiendo instancia :"+instancia );
    }

    private Method searchMethod(Class factoryClass, String methodName, Class[] paramsType) throws NoSuchMethodException {
        Method methodToCall = null;
        if (paramsType==null){
            for (int i = 0; i < factoryClass.getMethods().length; i++) {
                Method method = factoryClass.getMethods()[i];
                if (method.getName().equals(methodName)){
                    methodToCall = method;
                    break;
                }
            }
            if (methodToCall==null)
                throw new IllegalArgumentException("Method name is invalid:"+methodName);
        }else{
            methodToCall = factoryClass.getMethod(methodName, paramsType);
        }
        return methodToCall;
    }

    private Object invoke(Class factoryClass, Method method, Object[] params) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Object caller = null;
        if (!Modifier.isStatic(method.getModifiers())){
            caller = factoryClass.newInstance();
        }
        Object result = method.invoke(caller, params);
        return result;
    }
    private Object storeInstance(Object newInstance) {
        String handler = "H"+new Long(handlerSeq++);
        instanceRepository.put(handler, newInstance);
        return handler;
    }

}
