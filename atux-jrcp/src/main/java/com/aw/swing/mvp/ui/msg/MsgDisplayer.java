package com.aw.swing.mvp.ui.msg;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import javax.swing.*;

/**
 * User: gmc
 * Date: 25/06/2009
 */
public class MsgDisplayer{
    private static MessageDisplayer messageDisplayer;

    public static void setMessageDisplayer(MessageDisplayer messageDisplayer) {
        MsgDisplayer.messageDisplayer = getMsgDisplayerDecorated(messageDisplayer);
    }

    private static MessageDisplayer getMsgDisplayerDecorated(MessageDisplayer messageDisplayer) {
        ProxyFactory proxyFactory = new ProxyFactory(messageDisplayer);
        proxyFactory.addAdvice(new MethodInterceptor() {
            public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
                if (methodInvocation.getMethod().getName().startsWith("show")) {
                    if(SwingUtilities.isEventDispatchThread()){
                        return methodInvocation.proceed();
                    }else{
                        final Object[] response = new Object[1];
                        SwingUtilities.invokeAndWait(new Runnable(){
                            public void run() {
                                try {
                                    response[0] = methodInvocation.proceed();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            }
                        });
                        return response[0];
                    }
                }
                return methodInvocation.proceed();
            }
        });

        return (MessageDisplayer) proxyFactory.getProxy();
    }

    public static void showMessage(String message) {
        messageDisplayer.showMessage(message);
    }
    public static void showErrorMessage(String message) {
        messageDisplayer.showErrorMessage(message);
    }

    public static void showMessage(String messageKey, Object[] args) {
        messageDisplayer.showMessage(messageKey,args);
    }

    public static boolean showConfirmMessage(String messageConfirm) {
        return messageDisplayer.showConfirmMessage(messageConfirm);
    }

    public static boolean showConfirmMessage(String messageKey, Object[] args) {
        return messageDisplayer.showConfirmMessage(messageKey,args);
    }

    public static String getMessage(String messageKey, Object[] args) {
        return messageDisplayer.getMessage(messageKey, args);
    }
}
