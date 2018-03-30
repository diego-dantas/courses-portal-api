package com.course.portal.api.useful.email;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class EmailParameter {

    public static final String TOKEN_HEADER = "X-Auth-Token";
    public static final String REGEX = ":::";
    private String SE_EMAIL;
    private String SE_PASSWORD;
    private String SE_HOSTNAME;
    private int SE_PORT;

    public String getSE_EMAIL() {
        return SE_EMAIL;
    }

    public void setSE_EMAIL(String SE_EMAIL) {
        this.SE_EMAIL = SE_EMAIL;
    }

    public String getSE_PASSWORD() {
        return SE_PASSWORD;
    }

    public void setSE_PASSWORD(String SE_PASSWORD) {
        this.SE_PASSWORD = SE_PASSWORD;
    }

    public String getSE_HOSTNAME() {
        return SE_HOSTNAME;
    }

    public void setSE_HOSTNAME(String SE_HOSTNAME) {
        this.SE_HOSTNAME = SE_HOSTNAME;
    }

    public int getSE_PORT() {
        return SE_PORT;
    }

    public void setSE_PORT(int SE_PORT) {
        this.SE_PORT = SE_PORT;
    }

    @Autowired
    private ConfigEmailRepository configEmailRepository;

//    public EmailParameter(){
//
//        try {
//            System.out.println("To aqui mano ");
//            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);
//            if(configEmailEntity != null){
//
//                setSE_EMAIL(configEmailEntity.getEmail());
//                setSE_PASSWORD(configEmailEntity.getPassword());
//                setSE_PORT(configEmailEntity.getPort());
//                setSE_HOSTNAME(configEmailEntity.getHostName());
//
//                System.out.println(getSE_EMAIL());
//                System.out.println(getSE_PASSWORD());
//                System.out.println(getSE_PORT());
//                System.out.println(getSE_HOSTNAME());
//            }
//
//        }catch (Exception e){
//            System.out.println("Erro ao alimentar os parametros do e-mail");
//        }
//    }

}
