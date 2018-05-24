package com.course.portal.api.useful.email;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class EmailParameter {

    public static final String TOKEN_HEADER = "X-Auth-Token";
    public static final String REGEX = ":::";
    public String SE_EMAIL;
    public String SE_PASSWORD;
    public Integer SE_PORT;
    public String SE_HOSTNAME;

    @Autowired
    private ConfigEmailRepository configEmailRepository;


    public EmailParameter(){

        try {
            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);
            SE_EMAIL    = configEmailEntity.getEmail();
            SE_PASSWORD = configEmailEntity.getPassword();
            SE_HOSTNAME = configEmailEntity.getHostName();
            SE_PORT     =  configEmailEntity.getPort();
            System.out.println("Dados alimentado");


        }catch (Exception e){
            System.out.println("Erro ao alimentar os parametros do e-mail");
        }
    }

}
