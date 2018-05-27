package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dto.EmailDTO;
import com.course.portal.api.useful.email.Email;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmailController {

    @Autowired
    private ConfigEmailRepository configEmailRepository;

    @PostMapping(path = "/sendEmail")
    public ResponseEntity<Response<List<EmailDTO>>> sendEmail(@RequestBody List<EmailDTO> emailDTOs) throws MalformedURLException, EmailException{


        Response<List<EmailDTO>> response = new Response<>();
        
        try{
            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);

            for(EmailDTO emailDTO : emailDTOs){
                Email.sendHtmlEmail(
                        emailDTO.getStudent().getEmail(),
                        emailDTO.getAssunto(),
                        emailDTO.getTextoHtml(),
                        configEmailEntity);
            }

            response.setData(emailDTOs);

            return ResponseEntity.ok(response);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
