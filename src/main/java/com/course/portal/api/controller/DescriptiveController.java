package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.DescriptiveEntity;
import com.course.portal.api.model.dao.repository.DescriptiveRepository;
import com.course.portal.api.model.dto.DescriptiveDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class DescriptiveController {

    @Autowired
    private DescriptiveRepository descriptiveRepository;


    @PostMapping(value = "createUpdateDescriptive")
    public ResponseEntity<Response<DescriptiveDTO>> createUpdateDescriptive(@RequestBody DescriptiveDTO descriptiveDTO){

        Response<DescriptiveDTO> response = new Response<>();
        DescriptiveEntity descriptiveEntity = new DescriptiveEntity();

        try{

            descriptiveEntity.set_id(descriptiveDTO.get_id());
            descriptiveEntity.setTitle(descriptiveDTO.getTitle());
            descriptiveEntity.setContent(descriptiveDTO.getContent());

            descriptiveRepository.save(descriptiveEntity);

            response.setData(descriptiveDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar a descrição " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "deleteDescriptive")
    public ResponseEntity<Response<DescriptiveDTO>> deleteDescriptive(@RequestBody DescriptiveDTO descriptiveDTO){

        Response<DescriptiveDTO> response = new Response<>();
        DescriptiveEntity descriptiveEntity = new DescriptiveEntity();

        try{

            descriptiveEntity.set_id(descriptiveDTO.get_id());
            descriptiveEntity.setTitle(descriptiveDTO.getTitle());
            descriptiveEntity.setContent(descriptiveEntity.getContent());

            descriptiveRepository.delete(descriptiveEntity);

            response.setData(descriptiveDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar a descrição " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "getDescriptive")
    public ResponseEntity<Response<List<DescriptiveDTO>>> getDescriptive(){

        Response<List<DescriptiveDTO>> response = new Response<>();
        List<DescriptiveDTO> descriptiveDTOS = new ArrayList<>();

        try{

            List<DescriptiveEntity> descriptiveEntities = descriptiveRepository.findAll();

            for(DescriptiveEntity descriptiveEntity : descriptiveEntities){
                DescriptiveDTO descriptiveDTO = new DescriptiveDTO();

                descriptiveDTO.set_id(descriptiveEntity.get_id());
                descriptiveDTO.setTitle(descriptiveEntity.getTitle());
                descriptiveDTO.setContent(descriptiveEntity.getContent());

                descriptiveDTOS.add(descriptiveDTO);
            }

            response.setData(descriptiveDTOS);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar a descrição " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
