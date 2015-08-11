package com.atux.desktop.development;

import com.aw.core.distributed.InvokerConfigurer;
import com.aw.swing.spring.Application;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 01/07/2009
 * Time: 06:27:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DistServer {
    public static void main(String[] args) {
        Application.instance().init();
        new InvokerConfigurer().configureServer();
    }
}
