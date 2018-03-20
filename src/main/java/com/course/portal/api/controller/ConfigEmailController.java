package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dto.ConfigEmailDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ConfigEmailController {

    @Autowired
    private ConfigEmailRepository configEmailRepository;



    @PostMapping(value = "/saveConfigEmail")
    public ResponseEntity<Response<ConfigEmailDTO>>  saveConfigEmail(@RequestBody ConfigEmailDTO configEmailDTO){

        Response<ConfigEmailDTO> response = new Response<>();
        ConfigEmailEntity configEmailEntity = new ConfigEmailEntity();
        try{

            configEmailEntity.set_id(configEmailDTO.get_id());
            configEmailEntity.setEmail(configEmailDTO.getEmail());
            configEmailEntity.setPassword(configEmailDTO.getPassword());
            configEmailEntity.setPort(configEmailDTO.getPort());
            configEmailEntity.setHostName(configEmailDTO.getHostName());

            configEmailRepository.save(configEmailEntity);

            response.setData(configEmailDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao savar as cofigurações do e-mail " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getConfigEmail")
    public ResponseEntity<Response<ConfigEmailDTO>> getConfigEmail(){

        Response<ConfigEmailDTO> response = new Response<>();
        ConfigEmailDTO configEmailDTO = new ConfigEmailDTO();

        try{

            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);

            configEmailDTO.set_id(configEmailEntity.get_id());
            configEmailDTO.setEmail(configEmailEntity.getEmail());
            configEmailDTO.setPassword(configEmailEntity.getPassword());
            configEmailDTO.setPort(configEmailEntity.getPort());
            configEmailDTO.setHostName(configEmailEntity.getHostName());


            response.setData(configEmailDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao savar as cofigurações do e-mail " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


}
