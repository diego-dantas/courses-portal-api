package com.course.portal.api.useful.email;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailParameter {

    public static final String TOKEN_HEADER = "X-Auth-Token";
    public static final String REGEX = ":::";
    public static String SE_EMAIL;
    public static String SE_PASSWORD;
    public static String SE_HOSTNAME;
    public static int SE_PORT;


    @Autowired
    private ConfigEmailRepository configEmailRepository;

    public EmailParameter(){

        try {

            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1l);

            EmailParameter.SE_EMAIL = configEmailEntity.getEmail();
            EmailParameter.SE_PASSWORD = configEmailEntity.getPassword();
            EmailParameter.SE_PORT = configEmailEntity.getPort();
            EmailParameter.SE_HOSTNAME = configEmailEntity.getHostName();

        }catch (Exception e){
            System.out.println("Erro ao alimentar os parametros do e-mail");
        }
    }

}
