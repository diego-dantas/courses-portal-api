package com.course.portal.api.controller.response;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dto.EmailDTO;
import com.course.portal.api.useful.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmailController {

    @Autowired
    private ConfigEmailRepository configEmailRepository;

    @PostMapping(path = "/sendEmail")
    public ResponseEntity sendEmail(@RequestBody List<EmailDTO> emailDTO){

        ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);
        System.out.println(configEmailEntity.getEmail());
        System.out.println(configEmailEntity.getPassword());
        System.out.println(configEmailEntity.getHostName());
        System.out.println(configEmailEntity.getPort());

        Email email = new Email();

        try{

            email.sendSimpleEmail("diego.dsdantas@gmail.com",
                                  "teste de envio de email",
                                  "Texto para envio de email",
                                   configEmailEntity);

            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        return new ResponseEntity(HttpStatus.OK);
    }


}
