package com.course.portal.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.NewsletterEntity;
import com.course.portal.api.model.dao.repository.NewsletterRepository;
import com.course.portal.api.model.dto.NewsletterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class NewsletterController{

    @Autowired
    private NewsletterRepository newsletterRepository;

    @PostMapping(value = "/createUpdateNewslestter")
    public ResponseEntity<Response<NewsletterDTO>> createUpdateNewslestter(@RequestBody NewsletterDTO newsletterDTO){

        Response<NewsletterDTO> response = new Response<>();
        try {

            NewsletterEntity newsletterEntity = new NewsletterEntity();
            newsletterEntity.set_id(newsletterDTO.get_id());
            newsletterEntity.setName(newsletterDTO.getName());
            newsletterEntity.setEmail(newsletterDTO.getEmail());
            newsletterEntity.setStatus(newsletterDTO.isStatus());

            newsletterRepository.save(newsletterEntity);
            response.setData(newsletterDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping(value = "/getNewsletter")
    public ResponseEntity<Response<List<NewsletterDTO>>> getNewsletter(){
        
        try {
            Response<List<NewsletterDTO>> response = new Response<>();
            List<NewsletterDTO> newsletterDTOs = new ArrayList<>();

            List<NewsletterEntity> newsletterEntities = newsletterRepository.findAll();
            for(NewsletterEntity newsletterEntity : newsletterEntities){
                
                NewsletterDTO newsletterDTO = new NewsletterDTO();
                newsletterDTO.set_id(newsletterEntity.get_id());
                newsletterDTO.setName(newsletterEntity.getName());
                newsletterDTO.setEmail(newsletterEntity.getEmail());
                newsletterDTO.setStatus(newsletterEntity.isStatus());

                newsletterDTOs.add(newsletterDTO);
            }

            response.setData(newsletterDTOs);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}