package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.*;
import com.course.portal.api.model.dao.repository.*;
import com.course.portal.api.model.dto.*;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.HibernateException;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GridRepository gridRepository;
    @Autowired
    private SubGridRepositoy subGridRepositoy;
    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private MaterialRepository materialRepository;

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
            courseEntity.setLabelUrl(courseDTO.getLabelUrl());
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
            courseEntity.setLabelUrl(courseDTO.getLabelUrl());

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
                courseDTO.setWayImage(courses.getWayImage());
                courseDTO.setStatus(courses.isStatus());
                courseDTO.setLabelUrl(courses.getLabelUrl());
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


    @GetMapping(value = "/getCourse")
    public ResponseEntity<Response<List<MaterialDTO>>> getCourse(@RequestParam String grid,
                                                          @RequestParam String subGrid){
        Response<List<MaterialDTO>> response = new Response<>();
        GridDTO gridDTO = new GridDTO();
        SubGridDTO subGridDTO = new SubGridDTO();
        List<CourseEntity> courses = new ArrayList<>();


        List<MaterialDTO> listReturn = new ArrayList<>();


        try{

            GridEntity gridEntity = gridRepository.findByLabelUrl(grid);
            SubGridEntity subGridEntity = subGridRepositoy.findByLabelUrl(subGrid);
            gridDTO.set_id(gridEntity.get_id());
            subGridDTO.set_id(subGridEntity.get_id());


            courses = courseRepository.findByGridAndSubGrid(gridEntity.get_id(), subGridEntity.get_id());

            for(CourseEntity courseEntity : courses){

                CourseDTO courseDTO = new CourseDTO();

                subGridDTO.setGrid(gridDTO);
                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setName(courseEntity.getName());
                courseDTO.setDescription(courseEntity.getDescription());
                courseDTO.setObjective(courseEntity.getObjective());
                courseDTO.setHours(courseEntity.getHours());
                courseDTO.setPrice(courseEntity.getPrice());
                courseDTO.setWayImage(courseEntity.getWayImage());
                courseDTO.setStatus(courseEntity.isStatus());
                courseDTO.setLabelUrl(courseEntity.getLabelUrl());
                courseDTO.setGrid(gridDTO);
                courseDTO.setSubGrid(subGridDTO);

                //courseDTOS.add(courseDTO);

                List<StepsEntity> stepsEntities = stepsRepository.findByCourse(courseEntity);

                for(StepsEntity stepsEntity : stepsEntities){
                    StepsDTO stepsDTO = new StepsDTO();

                    stepsDTO.setCourse(courseDTO);
                    stepsDTO.set_id(stepsEntity.get_id());
                    stepsDTO.setName(stepsEntity.getName());
                    stepsDTO.setDescription(stepsEntity.getDescription());
                    stepsDTO.setStepsOrder(stepsEntity.getStepsOrder());



                    List<MaterialEntity> materialEntities = materialRepository.findBySteps(stepsEntity);

                    for(MaterialEntity materialEntity : materialEntities){

                        MaterialDTO materialDTO = new MaterialDTO();

                        materialDTO.setSteps(stepsDTO);
                        materialDTO.set_id(materialEntity.get_id());
                        materialDTO.setName(materialEntity.getName());
                        materialDTO.setType(materialEntity.getType());
                        materialDTO.setUrl(materialEntity.getUrl());
                        materialDTO.setMaterialOrder(materialEntity.getMaterialOrder());
                        materialDTO.setDownload(materialEntity.isDownload());
                        materialDTO.setStatus(materialEntity.isStatus());

                        listReturn.add(materialDTO);
                    }

                }
            }


            response.setData(listReturn);

            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar o curso por categoria e subcategoria " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/getCourseID")
    public ResponseEntity<Response<List<StepsDTO>>> getCourseID(@RequestParam long id){

        try {
            Response<List<StepsDTO>> response = new Response<>();
            List<StepsDTO> stepsDTOs = new ArrayList<>();

            CourseEntity courseEntity = courseRepository.findOne(id);
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.set_id(courseEntity.get_id());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDescription(courseEntity.getDescription());
            courseDTO.setObjective(courseEntity.getObjective());
            courseDTO.setHours(courseEntity.getHours());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setWayImage(courseEntity.getWayImage());
            courseDTO.setStatus(courseEntity.isStatus());
            courseDTO.setLabelUrl(courseEntity.getLabelUrl());

            List<StepsEntity> stepsEntities = stepsRepository.findByCourse(courseEntity);

            for(StepsEntity stepsEntity : stepsEntities){
                StepsDTO stepsDTO = new StepsDTO();
                stepsDTO.setCourse(courseDTO);
                stepsDTO.set_id(stepsEntity.get_id());
                stepsDTO.setName(stepsEntity.getName());
                stepsDTO.setDescription(stepsEntity.getDescription());
                stepsDTO.setStepsOrder(stepsEntity.getStepsOrder());

                stepsDTOs.add(stepsDTO);
            }
            
            response.setData(stepsDTOs);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Response<CourseDTO>> updateCourses(@RequestBody CourseDTO courseDTO){
        Response<CourseDTO> response = new Response<>();
        try{
            CourseEntity courseEntity = courseRepository.findOne(courseDTO.get_id());
            courseEntity.setWayImage(courseDTO.getWayImage());
            courseRepository.save(courseEntity);
            response.setData(courseDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
