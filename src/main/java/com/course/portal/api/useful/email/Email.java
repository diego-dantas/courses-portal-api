package com.course.portal.api.useful.email;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.net.MalformedURLException;

public class Email {


//  private static String EmailRemetente = "thedionisio.birl@gmail.com";
//  private static String Senha = "qnvaidaroq";
//  private static String HostName = "smtp.googlemail.com";
//  private static Integer SmtpPort = 587;

//    private static String EmailRemetente = EmailParameter.SE_EMAIL;
//    private static String Senha = EmailParameter.SE_PASSWORD;
//    private static String HostName = EmailParameter.SE_HOSTNAME;
//    private static Integer SmtpPort = EmailParameter.SE_PORT;

    public static void sendSimpleEmail(String Destinatario, String Assunto, String TextoSimples, ConfigEmailEntity configEmailEntity) throws EmailException{

        SimpleEmail email = new SimpleEmail();
        email.setHostName(configEmailEntity.getHostName());
        email.setSmtpPort(configEmailEntity.getPort());
        email.setAuthenticator(new DefaultAuthenticator(configEmailEntity.getEmail(), configEmailEntity.getPassword()));
        email.setSSLOnConnect(true);
        email.setFrom(configEmailEntity.getEmail());
        email.addTo(Destinatario);
        email.setSubject(Assunto);
        email.setMsg(TextoSimples);
        email.send();

        System.out.println("Email de texto simples enviado para :" + Destinatario);

        try{

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void sendHtmlEmail(String destinatario, String assunto, String textoHtml, String textoSimples, ConfigEmailEntity configEmailEntity) throws EmailException, MalformedURLException{

        HtmlEmail email = new HtmlEmail();
        email.setHostName(configEmailEntity.getHostName());
        email.setSmtpPort(configEmailEntity.getPort());
        email.setAuthenticator(new DefaultAuthenticator(configEmailEntity.getEmail(), configEmailEntity.getPassword()));
        email.setSSLOnConnect(true);
        email.setFrom(configEmailEntity.getEmail());
        email.addTo(destinatario);
        email.setSubject(assunto);
        email.setHtmlMsg(textoHtml);
        email.setTextMsg(textoSimples);
        email.send();

        System.out.println("Email de texto html enviado para :" + destinatario);
    }
}
