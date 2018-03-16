package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.EvaluationEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dao.repository.EvaluationRepository;
import com.course.portal.api.model.dao.repository.StepsRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.EvaluationDTO;
import com.course.portal.api.model.dto.StepsDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private CourseRepository courseRepository;


    @PostMapping(value = "createUpdateEvaluation")
    public ResponseEntity<Response<EvaluationDTO>> createUpdateEvaluation(@RequestBody EvaluationDTO evaluationDTO){

        Response<EvaluationDTO> response         = new Response<>();
        EvaluationEntity        evaluationEntity = new EvaluationEntity();
        StepsEntity             stepsEntity      = new StepsEntity();
        try{

            stepsEntity.set_id(evaluationDTO.getSteps().get_id());
            evaluationEntity.setSteps(stepsEntity);

            evaluationEntity.set_id(evaluationDTO.get_id());
            evaluationEntity.setName(evaluationDTO.getName());
            evaluationEntity.setStatus(evaluationDTO.isStatus());

            evaluationRepository.save(evaluationEntity);

            response.setData(evaluationDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar as questões " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping(value = "deleteEvaluation")
    public ResponseEntity<Response<EvaluationDTO>> deleteEvaluation(@RequestBody EvaluationDTO evaluationDTO){

        Response<EvaluationDTO> response         = new Response<>();
        EvaluationEntity        evaluationEntity = new EvaluationEntity();
        StepsEntity             stepsEntity      = new StepsEntity();
        try{

            stepsEntity.set_id(evaluationDTO.getSteps().get_id());
            evaluationEntity.setSteps(stepsEntity);

            evaluationEntity.set_id(evaluationDTO.get_id());
            evaluationEntity.setName(evaluationDTO.getName());
            evaluationEntity.setStatus(evaluationDTO.isStatus());

            evaluationRepository.delete(evaluationEntity);

            response.setData(evaluationDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar as questões " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "getEvaluations")
    public ResponseEntity<Response<List<EvaluationDTO>>> getEvaluations(){

        Response<List<EvaluationDTO>> response       = new Response<>();
        List<EvaluationDTO>           evaluationDTOs = new ArrayList<>();

        try{

            List<EvaluationEntity> evaluationEntities = evaluationRepository.findAll();

            for(EvaluationEntity evaluationEntity : evaluationEntities){

                EvaluationDTO evaluationDTO = new EvaluationDTO();
                StepsDTO      stepsDTO      = new StepsDTO();
                CourseDTO     courseDTO     = new CourseDTO();
                StepsEntity   stepsEntity   = stepsRepository.findOne(evaluationEntity.getSteps().get_id());
                CourseEntity  courseEntity  = courseRepository.findOne(stepsEntity.getCourse().get_id());

                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setDescription(courseEntity.getDescription());
                courseDTO.setName(courseEntity.getName());

                stepsDTO.setCourse(courseDTO);
                stepsDTO.set_id(stepsEntity.get_id());
                stepsDTO.setDescription(stepsEntity.getDescription());
                stepsDTO.setName(stepsEntity.getName());

                evaluationDTO.setSteps(stepsDTO);
                evaluationDTO.set_id(evaluationEntity.get_id());
                evaluationDTO.setName(evaluationEntity.getName());
                evaluationDTO.setStatus(evaluationEntity.isStatus());

                evaluationDTOs.add(evaluationDTO);
            }

            response.setData(evaluationDTOs);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar as questões " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
