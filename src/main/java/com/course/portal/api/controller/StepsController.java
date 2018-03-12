package com.course.portal.api.controller;


import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dao.repository.StepsRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.StepsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class StepsController {

    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private CourseRepository courseRepository;



    @PostMapping(value = "/createUpdateSteps")
    public ResponseEntity<Response<StepsDTO>> createUpdateSteps(@RequestBody StepsDTO stepsDTO){

        Response<StepsDTO> response = new Response<>();
        StepsEntity stepsEntity = new StepsEntity();
        CourseEntity courseEntity = new CourseEntity();
        try{

            courseEntity.set_id(stepsDTO.getCourse().get_id());
            stepsEntity.setCourse(courseEntity);
            stepsEntity.set_id(stepsDTO.get_id());
            stepsEntity.setName(stepsDTO.getName());
            stepsEntity.setDescription(stepsDTO.getDescription());
            stepsEntity.setStepsOrder(stepsDTO.getStepsOrder());


            stepsRepository.save(stepsEntity);
            response.setData(stepsDTO);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao salvar o passo " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/deleteSteps")
    public ResponseEntity<Response<StepsDTO>> deleteSteps(@RequestBody StepsDTO stepsDTO){

        Response<StepsDTO> response = new Response<>();
        StepsEntity stepsEntity = new StepsEntity();
        CourseEntity courseEntity = new CourseEntity();
        try{

            courseEntity.set_id(stepsDTO.getCourse().get_id());
            stepsEntity.setCourse(courseEntity);
            stepsEntity.set_id(stepsDTO.get_id());
            stepsEntity.setName(stepsDTO.getName());
            stepsEntity.setDescription(stepsDTO.getDescription());
            stepsEntity.setStepsOrder(stepsDTO.getStepsOrder());

            stepsRepository.delete(stepsEntity);
            response.setData(stepsDTO);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao deletar o passo " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getSteps")
    public ResponseEntity<Response<List<StepsDTO>>> getSteps(){

        Response<List<StepsDTO>> response = new Response<>();
        List<StepsEntity> stepsEntities = new ArrayList<>();
        List<StepsDTO> stepsDTOS = new ArrayList<>();
        try{

            stepsEntities = stepsRepository.findAll();

            for(StepsEntity stepsEntity : stepsEntities){

                StepsDTO stepsDTO = new StepsDTO();
                CourseDTO courseDTO = new CourseDTO();
                CourseEntity courseEntity = courseRepository.findOne(stepsEntity.getCourse().get_id());

                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setName(courseEntity.getName());
                courseDTO.setDescription(courseEntity.getDescription());

                stepsDTO.setCourse(courseDTO);
                stepsDTO.set_id(stepsEntity.get_id());
                stepsDTO.setName(stepsEntity.getName());
                stepsDTO.setDescription(stepsEntity.getDescription());
                stepsDTO.setStepsOrder(stepsEntity.getStepsOrder());


                stepsDTOS.add(stepsDTO);
            }

            response.setData(stepsDTOS);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao listar os steps " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
