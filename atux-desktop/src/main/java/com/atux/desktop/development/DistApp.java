package com.atux.desktop.development;

import com.aw.core.distributed.example.SVDistributedTest;
import com.aw.core.distributed.proxy.ProxyFactory;
import com.aw.core.spring.ApplicationBase;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 01/07/2009
 * Time: 06:27:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DistApp {
    public static void main(String[] args) {
//        new InvokerConfigurer().configureRMIClient();
        SVDistributedTest svDistributedTest = (SVDistributedTest) ProxyFactory.instance().proxyNew(SVDistributedTest.class);
        Object result =  svDistributedTest.test1();
        System.out.println(result);

        SVDistributedTest svDistributedTest2 = ApplicationBase.instance().getBean(SVDistributedTest.class);
        Object result2 =  svDistributedTest2.test1();
        System.out.println(result2);

    }
}