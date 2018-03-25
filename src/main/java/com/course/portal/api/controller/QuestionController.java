package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.QuestionEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import com.course.portal.api.model.dao.repository.CoursePlanRepository;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dao.repository.QuestionRepository;
import com.course.portal.api.model.dao.repository.StepsRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.QuestionDTO;
import com.course.portal.api.model.dto.StepsDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private CourseRepository courseRepository;


    @PostMapping(value = "/createUpdateQuestion")
    public ResponseEntity<Response<QuestionDTO>> createUpdateQuestion(@RequestBody QuestionDTO questionDTO){

        Response<QuestionDTO> response       = new Response<>();
        QuestionEntity        questionEntity = new QuestionEntity();
        StepsEntity           stepsEntity    = new StepsEntity();

        try{

            stepsEntity.set_id(questionDTO.getSteps().get_id());

            questionEntity.setSteps(stepsEntity);
            questionEntity.set_id(questionDTO.get_id());
            questionEntity.setDescription(questionDTO.getDescription());
            questionEntity.setStatus(questionDTO.isStatus());
            questionEntity.setCorrect(questionDTO.getCorrect());
            questionEntity.setAlterA(questionDTO.getAlterA());
            questionEntity.setAlterB(questionDTO.getAlterB());
            questionEntity.setAlterC(questionDTO.getAlterC());
            questionEntity.setAlterD(questionDTO.getAlterD());
            questionEntity.setAlterE(questionDTO.getAlterE());
            questionEntity.setWayImage(questionDTO.getWayImage());

            questionRepository.save(questionEntity);


            response.setData(questionDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar a questão " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping(value = "/deleteQuestion")
    public ResponseEntity<Response<QuestionDTO>> deleteQuestion(@RequestBody QuestionDTO questionDTO){

        Response<QuestionDTO> response = new Response<>();
        QuestionEntity questionEntity = new QuestionEntity();
        StepsEntity stepsEntity = new StepsEntity();

        try{

            stepsEntity.set_id(questionDTO.getSteps().get_id());

            questionEntity.setSteps(stepsEntity);
            questionEntity.set_id(questionDTO.get_id());
            questionEntity.setDescription(questionDTO.getDescription());
            questionEntity.setStatus(questionDTO.isStatus());
            questionEntity.setCorrect(questionDTO.getCorrect());
            questionEntity.setAlterA(questionDTO.getAlterA());
            questionEntity.setAlterB(questionDTO.getAlterB());
            questionEntity.setAlterC(questionDTO.getAlterC());
            questionEntity.setAlterD(questionDTO.getAlterD());
            questionEntity.setAlterE(questionDTO.getAlterE());
            questionEntity.setWayImage(questionDTO.getWayImage());

            questionRepository.delete(questionEntity);


            response.setData(questionDTO);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao deletar a questão " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "getQuestions")
    public ResponseEntity<Response<List<QuestionDTO>>> getQuestions(){

        Response<List<QuestionDTO>> response = new Response<>();
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        try{

            List<QuestionEntity> questionEntities = questionRepository.findAll();


            for(QuestionEntity questionEntity : questionEntities){

                QuestionDTO  questionDTO  = new QuestionDTO();
                StepsDTO     stepsDTO     = new StepsDTO();
                CourseDTO    courseDTO    = new CourseDTO();
                StepsEntity  stepsEntity  = stepsRepository.findOne(questionEntity.getSteps().get_id());
                CourseEntity courseEntity = courseRepository.findOne(stepsEntity.getCourse().get_id());

                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setDescription(courseEntity.getDescription());
                courseDTO.setName(courseEntity.getName());

                stepsDTO.setCourse(courseDTO);
                stepsDTO.set_id(stepsEntity.get_id());
                stepsDTO.setDescription(stepsEntity.getDescription());
                stepsDTO.setName(stepsEntity.getName());

                questionDTO.setSteps(stepsDTO);
                questionDTO.set_id(questionEntity.get_id());
                questionDTO.setDescription(questionEntity.getDescription());
                questionDTO.setStatus(questionEntity.isStatus());
                questionDTO.setCorrect(questionEntity.getCorrect());
                questionDTO.setAlterA(questionEntity.getAlterA());
                questionDTO.setAlterB(questionEntity.getAlterB());
                questionDTO.setAlterC(questionEntity.getAlterC());
                questionDTO.setAlterD(questionEntity.getAlterD());
                questionDTO.setAlterE(questionEntity.getAlterE());
                questionDTO.setWayImage(questionEntity.getWayImage());

                questionDTOS.add(questionDTO);
            }

            response.setData(questionDTOS);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar as questões " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/question")
    public ResponseEntity<Response<QuestionDTO>> updateQuestion(@RequestBody QuestionDTO questionDTO){
        Response<QuestionDTO> response = new Response<>();
        try{
            QuestionEntity questionEntity = questionRepository.findOne(questionDTO.get_id());
            questionEntity.setWayImage(questionDTO.getWayImage());
            questionRepository.save(questionEntity);
            response.setData(questionDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
