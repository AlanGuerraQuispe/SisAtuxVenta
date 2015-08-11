package com.aw.core.email;

import com.aw.core.domain.AWBusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * User: gmc
 * Date: 24/11/2008
 */
public class EmailProxy {
    protected final Log logger = LogFactory.getLog(getClass());

    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    SimpleMailMessage message = new SimpleMailMessage();

    public JavaMailSenderImpl getSender() {
        return sender;
    }

    public SimpleMailMessage getMessage() {
        return message;
    }
    //    public void sendMessage(BNEmail bnEmail) {
//        throw new IllegalStateException("This functionality is not implemented yet");
////        try {
////            MimeMessage message = sender.createMimeMessage();
////            // use the true flag to indicate you need a multipart message
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////            helper.setFrom(bnEmail.getFrom());
////            helper.setTo(bnEmail.buildTo());
////            helper.setCc(bnEmail.buildCc());
////            helper.setSubject(bnEmail.getSubject());
////            // use the true flag to indicate the text included is HTML
////            helper.setText(bnEmail.getMessage());
////            logger.info("Host:" + sender.getHost() + " Usr:" + sender.getUsername() + " Pwd:" + sender.getPassword());
////            sender.send(message);
////        } catch (MessagingException e) {
////            e.printStackTrace();
////            throw new AWBusinessException("Error enviando correos.",e);
////        }
//    }

//    public void sendSimpleMessage(BNEmail bnEmail) {
//        SimpleMailMessage sm = new SimpleMailMessage();
//        sm.setFrom(bnEmail.getFrom());
//        sm.setTo(bnEmail.buildTo());
//        sm.setCc(bnEmail.buildCc());
//        sm.setSubject(bnEmail.getSubject());
//        sm.setText(bnEmail.getMessage());
//        logger.info("Host:" + sender.getHost() + " Usr:" + sender.getUsername() + " Pwd:" + sender.getPassword());
//        sender.send(sm);
//    }


    public EmailProxy setTo(String to) {
        message.setTo(to);
        return this;
    }

    public EmailProxy setTo(String[] to) {
        message.setTo(to);
        return this;
    }

    public EmailProxy setSubject(String subject) {
        message.setSubject(subject);
        return this;
    }

    public EmailProxy setText(String messageTxt) {
        message.setText(messageTxt);
        return this;
    }

    public void send() {
        if ("true".equals(System.getProperty("com.aw.email.disabled"))) {

        } else {
            try {
                sender.send(message);
            } catch (MailSendException ex) {
                throw new AWBusinessException("El servidor de correo no esta disponible\n por favor intentarlo más tarde");
            }

        }
    }

    public static void main(String[] args) {
        SVEmailBase svEmailFactory = new SVEmailBase() {
            protected void configureProxy(EmailProxy emailProxy) {
                emailProxy.sender.setHost("mail.agile-works.com");
                emailProxy.sender.setUsername("asistencia@agile-works.com");
                emailProxy.sender.setPassword("awpassword");
                emailProxy.message.setFrom("carolina.ramirez@agile-works.com");
            }
        };
        EmailProxy emailProxy = svEmailFactory.newInstance();
        emailProxy.setTo("todos@agile-works.com");
        emailProxy.setSubject("Voy a preparar cafecito");
        emailProxy.setText("alguien mas quiere??");
        emailProxy.send();
    }

}