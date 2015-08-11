package com.aw.core.email;

/**
 * User: gmc
 * Date: 24/11/2008
 */

abstract public class SVEmailBase {

    public EmailProxy newInstance() {
        EmailProxy emailProxy = new EmailProxy();
        configureProxy(emailProxy);
        
        return emailProxy;
    }

    protected void configureProxy(EmailProxy emailProxy) {
        throw new IllegalMonitorStateException("Metodo debe ser sobreescrito");
//        emailProxy.sender.setHost("mail.agile-works.com");
//        emailProxy.sender.setUsername("asistencia@agile-works.com");
//        emailProxy.sender.setPassword("awpassword");
//        emailProxy.message.setFrom("admin-sider@agile-works.com");
    }

}
