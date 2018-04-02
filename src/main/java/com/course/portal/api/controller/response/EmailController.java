package com.course.portal.api.controller.response;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dto.EmailDTO;
import com.course.portal.api.useful.email.Email;
import com.course.portal.api.useful.email.EmailParameter;
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
    public ResponseEntity<Response<List<EmailDTO>>> sendEmail(@RequestBody List<EmailDTO> emailDTOs){


        Response<List<EmailDTO>> response = new Response<>();
        Email email = new Email();

        try{
            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);

            for(EmailDTO emailDTO : emailDTOs){
//                System.out.println(emailDTO.getStudent().getEmail());
//                System.out.println(emailDTO.getAssunto());
//                System.out.println(emailDTO.getTextoHtml());
//                System.out.println(emailDTO.getTextoSimples());

                email.sendHtmlEmail(
                        emailDTO.getStudent().getEmail(),
                        emailDTO.getAssunto(),
                        emailDTO.getTextoHtml(),
                        emailDTO.getTextoSimples(), configEmailEntity);
            }

            response.setData(emailDTOs);

            return ResponseEntity.ok(response);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        return new ResponseEntity(HttpStatus.OK);
    }


}
