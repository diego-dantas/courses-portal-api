package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.CoursePlanEntity;
import com.course.portal.api.model.dao.entity.PlanEntity;
import com.course.portal.api.model.dao.repository.CoursePlanRepository;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dao.repository.PlanRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.CoursePlanDTO;
import com.course.portal.api.model.dto.PlanDTO;
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
public class CoursePlanController {


    @Autowired
    private CoursePlanRepository coursePlanRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PlanRepository planRepository;


    @PostMapping(value = "createUpdateCoursePlan")
    public ResponseEntity<Response<CoursePlanDTO>> createUpdateCoursePlan(@RequestBody CoursePlanDTO coursePlanDTO){
        Response<CoursePlanDTO> response  = new Response<>();

        CourseEntity     courseEntity     = new CourseEntity();
        PlanEntity       planEntity       = new PlanEntity();
        CoursePlanEntity coursePlanEntity = new CoursePlanEntity();

        try{

            courseEntity.set_id(coursePlanDTO.getCourse().get_id());
            planEntity.set_id(coursePlanDTO.getPlan().get_id());

            coursePlanEntity.setCourse(courseEntity);
            coursePlanEntity.setPlan(planEntity);
            coursePlanEntity.set_id(coursePlanDTO.get_id());
            coursePlanEntity.setPrice(coursePlanDTO.getPrice());
            coursePlanRepository.save(coursePlanEntity);

            System.out.println("Dados salvo com sucesso " + coursePlanEntity.toString());

            response.setData(coursePlanDTO);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao criar ou atualizar o plano/curso");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "deleteCoursePlan")
    public ResponseEntity<Response<CoursePlanDTO>> deleteCoursePlan(@RequestBody CoursePlanDTO coursePlanDTO){

        Response<CoursePlanDTO> response  = new Response<>();

        try{

            coursePlanRepository.delete(coursePlanDTO.get_id());
            System.out.println("Dados Excluido com sucesso");
            response.setData(coursePlanDTO);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao excluir o plano/curso");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/getCoursesPlans")
    public ResponseEntity<Response<List<CoursePlanDTO>>> getCoursesPlans(){

        Response<List<CoursePlanDTO>> response = new Response<>();
        List<CoursePlanEntity> coursePlanEntities = new ArrayList<>();
        List<CoursePlanDTO> coursePlanDTOs = new ArrayList<>();
        try{

            coursePlanEntities = coursePlanRepository.findAll();

            for(CoursePlanEntity coursePlanEntity : coursePlanEntities){

                CourseEntity  courseEntity  = courseRepository.findOne(coursePlanEntity.getCourse().get_id());
                PlanEntity    planEntity    = planRepository.findOne(coursePlanEntity.getPlan().get_id());
                CoursePlanDTO coursePlanDTO = new CoursePlanDTO();
                CourseDTO     courseDTO     = new CourseDTO();
                PlanDTO       planDTO       = new PlanDTO();

                planDTO.set_id(planEntity.get_id());
                planDTO.setDescription(planEntity.getDescription());
                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setName(courseEntity.getName());
                courseDTO.setPrice(courseEntity.getPrice());

                coursePlanDTO.set_id(coursePlanEntity.get_id());
                coursePlanDTO.setPrice(coursePlanEntity.getPrice());
                coursePlanDTO.setPlan(planDTO);
                coursePlanDTO.setCourse(courseDTO);

                coursePlanDTOs.add(coursePlanDTO);

            }

            response.setData(coursePlanDTOs);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println("Erro ao buscar os cursos/planos");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
