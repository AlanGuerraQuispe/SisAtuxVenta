package com.aw.core.distributed.proxy;

import com.aw.core.distributed.IInvoker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;


/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public class ProxyFactory {
    protected final Log logger = LogFactory.getLog(getClass());

    static ProxyFactory _instance = new ProxyFactory();
    IInvoker delegate;

    public static ProxyFactory instance() {
        return _instance;
    }

    public void setDelegate(IInvoker delegate) {
        this.delegate = delegate;
    }

    public <T> T proxyNew( Class<T> clazz ){
          try{
//              CallbackProxyImpl callbackProxyImpl = new CallbackProxyImpl(clazz);
//              Enhancer e = new Enhancer();
//              e.setSuperclass(clazz);
//              e.setCallback(callbackProxyImpl);
//              Object proxy = e.create();
//              return (T) proxy;
              return null;
          }catch( Throwable e ){
              e.printStackTrace();
              throw new Error(e.getMessage());
          }

      }
//    public class CallbackProxyImpl implements MethodInterceptor {
//
//        private Class clazz;
//        private Object handler;
//        public CallbackProxyImpl(Class clazz) {
//            this.clazz = clazz;
//            try {
//                handler = delegate.create(clazz, null, null);
//            } catch (Exception e) {
//                throw AWBusinessException.wrapUnhandledException(logger, e);
//            }
//        }
//
//        public Object intercept(Object object, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
//            logger.info("Invocando "+method);
//            if (method.getName().equals("finalize") && (params==null||params.length==0)){
//                delegate.destroy(handler);
//                return null;
//            }else{
//                Class[] paramsType = method.getParameterTypes();
//                String[] paramsTypeStr = new String[paramsType.length];
//                for (int i = 0; i < paramsType.length; i++) {
//                    paramsTypeStr[i] = paramsType[i].getName();
//                }
//                return delegate.invoke(handler,  method.getName(), params, paramsTypeStr);
//            }
//        }
//
//        protected void finalize() throws Throwable {
//            delegate.destroy(handler);
//            super.finalize();
//        }
//    }

}
