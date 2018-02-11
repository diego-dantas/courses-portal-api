package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @PostMapping(value = "/createUpdateCourse")
    public ResponseEntity<Response<CourseDTO>>  createUpdateCourse(@RequestBody CourseDTO courseDTO){

        Response<CourseDTO> response = new Response<>();
        CourseEntity courseEntity = new CourseEntity();

        try{
            courseEntity.set_id(courseDTO.get_id());
            courseEntity.setDescription(courseDTO.getDescription());
            courseEntity.setName(courseDTO.getName());
            courseEntity.setObjective(courseDTO.getObjective());
            courseEntity.setHours(courseDTO.getHours());
            courseEntity.setStatus(courseDTO.isStatus());
            courseEntity.setPrice(courseDTO.getPrice());
            courseEntity.setWayImage(courseDTO.getWayImage());

            courseRepository.save(courseEntity);

            response.setData(courseDTO);

        }catch (Exception e){
            System.out.print("Erro ao criar o curso " + e);
        }

        return ResponseEntity.ok(response);
    }



    @PostMapping(value = "/deleteCourse")
    public ResponseEntity<Response<CourseDTO>> deleteCourse(@RequestBody CourseDTO courseDTO){


        Response<CourseDTO> response = new Response<>();
        CourseEntity courseEntity = new CourseEntity();

        try{
            courseEntity.set_id(courseDTO.get_id());
            courseEntity.setDescription(courseDTO.getDescription());
            courseEntity.setName(courseDTO.getName());
            courseEntity.setObjective(courseDTO.getObjective());
            courseEntity.setHours(courseDTO.getHours());
            courseEntity.setStatus(courseDTO.isStatus());
            courseEntity.setPrice(courseDTO.getPrice());
            courseEntity.setWayImage(courseDTO.getWayImage());

            courseRepository.delete(courseEntity);

            response.setData(courseDTO);

        }catch (Exception e){
            System.out.print("Erro ao deletar o curso " + e);
        }

        return ResponseEntity.ok(response);
    }

}
