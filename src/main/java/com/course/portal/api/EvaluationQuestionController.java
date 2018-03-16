package com.course.portal.api;


import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.EvaluationEntity;
import com.course.portal.api.model.dao.entity.EvaluationQuestionEntity;
import com.course.portal.api.model.dao.entity.QuestionEntity;
import com.course.portal.api.model.dao.repository.EvaluationQuestionRepository;
import com.course.portal.api.model.dto.EvaluationDTO;
import com.course.portal.api.model.dto.EvaluationQuestionDTO;
import com.course.portal.api.model.dto.QuestionDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EvaluationQuestionController {

    @Autowired
    private EvaluationQuestionRepository evaluationQuestionRepository;


    @PostMapping(value = "/saveEvaluationQuestion")
    public ResponseEntity<Response<List<EvaluationQuestionDTO>>> saveEvaluationQuestion(@RequestBody List<EvaluationQuestionDTO> evaluationQuestionDTOs){

        Response<List<EvaluationQuestionDTO>> response = new Response<>();

        try{

            EvaluationEntity evaluation = new EvaluationEntity();
            evaluation.set_id(evaluationQuestionDTOs.get(0).getEvaluation().get_id());

            List<EvaluationQuestionEntity> evaluationQuestionEntities =  evaluationQuestionRepository.findByEvaluation(evaluation);

            for(EvaluationQuestionEntity evaluationQuestionEntity : evaluationQuestionEntities){
                evaluationQuestionRepository.delete(evaluationQuestionEntity.get_id());
            }


            for(EvaluationQuestionDTO evaluationQuestionDTO : evaluationQuestionDTOs){

                QuestionEntity questionEntity = new QuestionEntity();
                EvaluationEntity evaluationEntity = new EvaluationEntity();
                EvaluationQuestionEntity evaluationQuestionEntity = new EvaluationQuestionEntity();

                questionEntity.set_id(evaluationQuestionDTO.getQuestion().get_id());
                evaluationEntity.set_id(evaluationQuestionDTO.getEvaluation().get_id());

                evaluationQuestionEntity.setQuestion(questionEntity);
                evaluationQuestionEntity.setEvaluation(evaluationEntity);

                evaluationQuestionRepository.save(evaluationQuestionEntity);

            }

            response.setData(evaluationQuestionDTOs);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar as questões da prova " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getEvaluationQuestion")
    public ResponseEntity<Response<List<EvaluationQuestionDTO>>> getEvaluationQuestion(){

        Response<List<EvaluationQuestionDTO>> response = new Response<>();
        List<EvaluationQuestionDTO> evaluationQuestionDTOs = new ArrayList<>();
        try{

            List<EvaluationQuestionEntity> evaluationQuestionEntities = evaluationQuestionRepository.findAll();
            for (EvaluationQuestionEntity evaluationQuestionEntity : evaluationQuestionEntities){

                EvaluationDTO evaluationDTO = new EvaluationDTO();
                QuestionDTO questionDTO = new QuestionDTO();
                EvaluationQuestionDTO evaluationQuestionDTO = new EvaluationQuestionDTO();

                evaluationDTO.set_id(evaluationQuestionEntity.getEvaluation().get_id());
                questionDTO.set_id(evaluationQuestionEntity.getQuestion().get_id());

                evaluationQuestionDTO.set_id(evaluationQuestionEntity.get_id());
                evaluationQuestionDTO.setEvaluation(evaluationDTO);
                evaluationQuestionDTO.setQuestion(questionDTO);

                evaluationQuestionDTOs.add(evaluationQuestionDTO);
            }

            response.setData(evaluationQuestionDTOs);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar as questões da prova " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
