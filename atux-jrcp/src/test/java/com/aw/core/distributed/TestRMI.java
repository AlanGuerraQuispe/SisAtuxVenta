package com.aw.core.distributed;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//import org.springframework.remoting.rmi.RmiInvocationWrapper_Stub;

/**
 * @author jcmacavilca
 *         Feb 5, 2008
 */
public class TestRMI extends TestCase {
    public void testXXX() throws RemoteException, NotBoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
   /*     InvokerServerImpl server = new InvokerServerImpl();
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("InvokerService");
        rmiServiceExporter.setService(server);
        rmiServiceExporter.setServiceInterface(IInvoker.class);
//    <!-- defaults to 1099 -->
//    <property name="registryPort" value="1199"/>


    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
    RmiInvocationWrapper_Stub rmStub =(RmiInvocationWrapper_Stub) registry.lookup("InvokerService");

    RemoteInvocation remoteInvocation =    new RemoteInvocation(
            "create",new Class[]{Class.class, String.class, (new Object[0]).getClass() },
                    new Object[]{TestInvoker.RPTest.class, "metodo1String", null});

    Object result = rmStub.invoke(remoteInvocation);*/

    }


}
