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
    public ResponseEntity<Response<List<CoursePlanDTO>>> createUpdateCoursePlan(@RequestBody List<CoursePlanDTO> coursePlanDTOS){
        Response<List<CoursePlanDTO>> response  = new Response<>();



        try{

            for(CoursePlanDTO coursePlanDTO : coursePlanDTOS){

                CourseEntity     courseEntity     = new CourseEntity();
                PlanEntity       planEntity       = new PlanEntity();
                CoursePlanEntity coursePlanEntity = new CoursePlanEntity();

                courseEntity.set_id(coursePlanDTO.getCourse().get_id());
                planEntity.set_id(coursePlanDTO.getPlan().get_id());
                int qtd = coursePlanRepository.countByCourseAndPlan(courseEntity, planEntity);

                if(qtd == 1){
                    System.out.println("Tem valor");
                    CoursePlanEntity coursePlan = coursePlanRepository.findByCourseAndPlan(courseEntity, planEntity);
                    coursePlanEntity.set_id(coursePlan.get_id());
                }

                coursePlanEntity.setCourse(courseEntity);
                coursePlanEntity.setPlan(planEntity);
                coursePlanEntity.setPrice(coursePlanDTO.getPrice());
                coursePlanEntity.setPercentage(coursePlanDTO.getPercentage());
                coursePlanRepository.save(coursePlanEntity);


            }


            response.setData(coursePlanDTOS);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao criar ou atualizar o plano/curso");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/deleteCoursePlan")
    public ResponseEntity<Response<List<CoursePlanDTO>>> deleteCoursePlan(@RequestBody List<CoursePlanDTO> coursePlanDTOs){

        Response<List<CoursePlanDTO>> response  = new Response<>();

        try{

            PlanEntity planEntity = new PlanEntity();
            planEntity.set_id(coursePlanDTOs.get(0).getPlan().get_id());

            List<CoursePlanEntity> coursePlanEntites = coursePlanRepository.findByPlan(planEntity);

            for(CoursePlanEntity coursePlanEntity : coursePlanEntites){
                System.out.println(coursePlanEntity.toString());
                coursePlanRepository.delete(coursePlanEntity.get_id());
            }

            System.out.println("Dados Excluido com sucesso");
            response.setData(coursePlanDTOs);
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
                coursePlanDTO.setPercentage(coursePlanEntity.getPercentage());
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
