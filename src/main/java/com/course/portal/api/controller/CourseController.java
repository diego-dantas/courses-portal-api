package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.GridEntity;
import com.course.portal.api.model.dao.entity.SubGridEntity;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.GridDTO;
import com.course.portal.api.model.dto.SubGridDTO;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @PostMapping(value = "/createUpdateCourse")
    public ResponseEntity<Response<CourseDTO>>  createUpdateCourse(@RequestBody CourseDTO courseDTO){

        Response<CourseDTO> response = new Response<>();
        CourseEntity courseEntity = new CourseEntity();
        GridEntity gridEntity = new GridEntity();
        SubGridEntity subGridEntity = new SubGridEntity();

        try{

            gridEntity.set_id(courseDTO.getGrid().get_id());
            subGridEntity.set_id(courseDTO.getSubGrid().get_id());

            courseEntity.set_id(courseDTO.get_id());
            courseEntity.setDescription(courseDTO.getDescription());
            courseEntity.setName(courseDTO.getName());
            courseEntity.setObjective(courseDTO.getObjective());
            courseEntity.setHours(courseDTO.getHours());
            courseEntity.setStatus(courseDTO.isStatus());
            courseEntity.setPrice(courseDTO.getPrice());
            courseEntity.setWayImage(courseDTO.getWayImage());
            courseEntity.setGrid(gridEntity);
            courseEntity.setSubGrid(subGridEntity);

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


    @GetMapping(value = "/getCourses")
    public ResponseEntity<Response<List<CourseDTO>>> getCourses(){

        Response<List<CourseDTO>> response = new Response<>();
        List<CourseEntity> courseEntities = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();

        try{
            courseEntities =  courseRepository.findAll();


            for(CourseEntity courses : courseEntities ){
                CourseDTO courseDTO = new CourseDTO();
                GridDTO gridDTO = new GridDTO();
                SubGridDTO subGridDTO = new SubGridDTO();

                gridDTO.set_id(courses.getGrid().get_id());
                subGridDTO.set_id(courses.getSubGrid().get_id());
                subGridDTO.setGrid(gridDTO);

                courseDTO.set_id(courses.get_id());
                courseDTO.setName(courses.getName());
                courseDTO.setDescription(courses.getDescription());
                courseDTO.setObjective(courses.getObjective());
                courseDTO.setHours(courses.getHours());
                courseDTO.setPrice(courses.getPrice());
                courseDTO.setStatus(courses.isStatus());
                courseDTO.setGrid(gridDTO);
                courseDTO.setSubGrid(subGridDTO);

                courseDTOS.add(courseDTO);
            }

        }catch (Exception e){
            System.out.println("Erro ao buscar os cursos");
        }

        response.setData(courseDTOS);

        return ResponseEntity.ok(response);
    }

}
