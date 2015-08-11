package com.aw.core.distributed;

import com.aw.core.distributed.endpoint.InvokerClientImpl;
import com.aw.core.distributed.endpoint.InvokerServerImpl;
import com.aw.core.distributed.proxy.ProxyFactory;
import junit.framework.TestCase;

/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public class TestInvoker extends TestCase {
    public void testXXX(){
        InvokerServerImpl server = new InvokerServerImpl();

//        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
//        rmiServiceExporter.setServiceName("InvokerService");
//        rmiServiceExporter.setService(server);
//        rmiServiceExporter.setServiceInterface(IInvoker.class);
////    <!-- defaults to 1099 -->
////    <property name="registryPort" value="1199"/>
//
//
//    Registry registry = LocateRegistry.getRegistry("localhost", "1099");
//    RmiInvocationWrapper_Stub rmStub =(RmiInvocationWrapper_Stub) registry.lookup("InvokerService");
//    RemoteInvocation remoteInvocation =    new RemoteInvocation(
//            "MethodName",new Class[]{},new Object[]{});
//    Object result = rmStub.invoke(remoteInvocation);

        InvokerClientImpl client = new InvokerClientImpl();
        client.setDelegate(server);

        ProxyFactory.instance().setDelegate(client);


        RPTest rpTest = (RPTest) ProxyFactory.instance().proxyNew(RPTest.class);
        System.out.println("1:"+rpTest.metodo1String());
    }


    public static class RPTest{

        public String metodo1String(){
           return "Holas";
        }
    }
}
