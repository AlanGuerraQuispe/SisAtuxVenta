package com.aw.core.distributed;

import com.aw.core.distributed.endpoint.InvokerClientImpl;
import com.aw.core.distributed.endpoint.InvokerServerImpl;
import com.aw.core.distributed.example.SVDistributedTest;
import com.aw.core.distributed.proxy.ProxyFactory;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.spring.ApplicationBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

/**
 * User: Julio C. Macavilca
 * Date: 06/02/2008
 */
public class InvokerConfigurer {
    protected static final Log logger = LogFactory.getLog(InvokerConfigurer.class);

    private IInvoker server;
    private InvokerClientImpl client;


    /**
     * Configuracion directa (sin RMI)
     */
    private void configureAllNonRMI() {
        server = new InvokerServerImpl();

        client = new InvokerClientImpl();
        client.setDelegate(server);

        ProxyFactory.instance().setDelegate(client);
    }

    public RmiServiceExporter configureServer(){
        // configurado directamente por spring.
        // solo se requiere el RMI server activo
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("InvokerServerService");
        rmiService.setService(new InvokerServerImpl());
        rmiService.setServiceInterface(IInvoker.class);
        rmiService.setRegistryPort(1199);
        try {
            rmiService.afterPropertiesSet();
        } catch (RemoteException e) {
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
        return rmiService;

    }
    public InvokerConfigurer configureRMIClient() {
        boolean useProxy = false;
        try{
            RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
            factoryBean.setServiceInterface(IInvoker.class);
            factoryBean.setServiceUrl("rmi://localhost:1199/InvokerServerService");
            factoryBean.setRefreshStubOnConnectFailure(true);
            factoryBean.afterPropertiesSet();
            IInvoker rmiDelegate = (IInvoker) factoryBean.getObject();
            client = new InvokerClientImpl();
            client.setDelegate(rmiDelegate);
            ProxyFactory.instance().setDelegate(client);

            // test RMI server activo antes de continuar
            SVDistributedTest daoSql= ProxyFactory.instance().proxyNew(SVDistributedTest.class);
            Object result =  daoSql.test1();
            logger.debug("Distributed enviroment initiated:"+result);
//            TransactionDecorator decorator = useProxy
//                     ?   new TransactionDecoratorProxyImpl()
//                    : new TransactionDecoratorDirectImpl();
//            AWContext.getBeanRepositoryRetriever().registerCustomBean("txDecorator", decorator);

        }catch (Exception e){
            throw AWBusinessException.wrapUnhandledException(logger, e);
        }
        ApplicationBase.instance().distributedProxyFactory = ProxyFactory.instance(); 
        return this;
    }

    public static void main(String[] args) {
        new InvokerConfigurer().configureRMIClient();

        SVDistributedTest svDistributedTest = ApplicationBase.instance().getBean(SVDistributedTest.class);
        Object result =  svDistributedTest.test1();
        System.out.println(result);
    }

//    private static class TransactionDecoratorProxyImpl extends TransactionDecorator {
//        public Object getTxProxy(Class clazz) {
//            return ProxyFactory.instance().proxyNew(clazz);
//        }
//
//        public Object getTxProxy(Object object) {
//            throw new UnsupportedOperationException("Creacion de objetos desde cliente no soportado.");
//        }
//    }
//    private static class TransactionDecoratorDirectImpl extends TransactionDecorator {
//        public Object getTxProxy(Class clazz) {
//            try {
//                return clazz.newInstance();
//            } catch (Throwable e) {
//                throw AWBusinessException.wrapUnhandledException(logger, e);
//            }
//            //return ProxyFactory.instance().proxyNew(clazz);
//        }
//
//        public Object getTxProxy(Object object) {
//            return object;
//        }
//    }
}
