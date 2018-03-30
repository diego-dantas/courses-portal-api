package com.course.portal.api.useful.email;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.net.MalformedURLException;

public class Email {

    EmailParameter emailParameter = new EmailParameter();

    private static String EmailRemetente;
    private static String Senha;
    private static String HostName;
    private static Integer SmtpPort;

    public static void sendSimpleEmail(String Destinatario, String Assunto, String TextoSimples, ConfigEmailEntity configEmailEntity) throws EmailException{

        SimpleEmail email = new SimpleEmail();
        email.setHostName(configEmailEntity.getHostName());
        email.setSmtpPort(465);
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

    public static void sendHtmlEmail(String destinatario, String assunto, String textoHtml, String textoSimples) throws EmailException, MalformedURLException{

        HtmlEmail email = new HtmlEmail();
        email.setHostName(HostName);
        email.setSmtpPort(SmtpPort);
        email.setAuthenticator(new DefaultAuthenticator(EmailRemetente, Senha));
        email.setSSLOnConnect(true);
        email.setFrom(EmailRemetente);
        email.addTo(destinatario);
        email.setSubject(assunto);
        email.setHtmlMsg(textoHtml);
        email.setTextMsg(textoSimples);
        email.send();

        System.out.println("Email de texto html enviado para :" + destinatario);
    }
}
